/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-26 上午10:58:30
 */
package nc.vo.pu.m21.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.ScaleObjectFactory;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.pubapp.scale.TotalValueVOScaleProcessor;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据参数PO08设置采购单位
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-26 下午10:58:30
 */
public class AutoSwitchPUAssistUnitRule implements IRule<OrderVO> {

  private Map<String, String> assistunitMap = null;

  private Map<String, UFBoolean> switchMap = new HashMap<String, UFBoolean>();

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    List<OrderVO> list = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      String pk_org = vo.getHVO().getPk_org();
      UFBoolean autoSwitch = this.autoSwitch(pk_org);
      if (autoSwitch.booleanValue()) {
        list.add(vo);
      }
    }

    if (list.size() == 0) {
      return;
    }

    OrderVO[] changeVOs = list.toArray(new OrderVO[list.size()]);
    // 取默认采购单位
    this.loadAssistunitMap(changeVOs);
    for (OrderVO vo : changeVOs) {
      this.switchPUAssistUnit(vo);
    }
  }

  /**
   * 方法功能描述：根据组织取参数PO08，判断是否自动转换
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org
   * @return <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-26 上午11:35:33
   */
  private UFBoolean autoSwitch(String pk_org) {
    if (this.switchMap.containsKey(pk_org)) {
      return this.switchMap.get(pk_org);
    }

    UFBoolean po08 = PUSysParamUtil.getPO08(pk_org);
    if (UFBoolean.TRUE.equals(po08)) {
      this.switchMap.put(pk_org, UFBoolean.TRUE);
      return UFBoolean.TRUE;
    }

    this.switchMap.put(pk_org, UFBoolean.FALSE);
    return UFBoolean.FALSE;
  }

  /**
   * <p>
   * 备份
   * 
   * @param vo
   */
  private OrderVO cloneVO(OrderVO vo) {
    OrderItemVO[] itemVOs = vo.getBVO();
    // 在更改单位前，对vo进行备份，供下面比较用。
    OrderVO oldVO = new OrderVO();
    oldVO.setHVO(vo.getHVO());
    OrderItemVO[] oldItems = new OrderItemVO[itemVOs.length];
    for (int i = 0; i < oldItems.length; i++) {
      oldItems[i] = (OrderItemVO) itemVOs[i].clone();
    }
    oldVO.setBVO(oldItems);
    return oldVO;
  }

  private boolean isEqualsRate(String rate1, String rate2, int digit) {
    if (rate1 != null && rate2 == null || rate2 == null && rate1 != null) {
      return false;
    }
    if (rate1 == null && rate2 == null) {
      return true;
    }
    UFDouble[] rates1 = HslParseUtil.parseHsl(rate1);
    UFDouble[] rates2 = HslParseUtil.parseHsl(rate2);
    UFDouble[] nrate = new UFDouble[2];
    nrate[0] = rates1[0].div(rates1[1]);
    nrate[1] = rates2[0].div(rates2[1]);
    nrate[0] = nrate[0].setScale(digit, UFDouble.ROUND_HALF_UP);
    nrate[1] = nrate[1].setScale(digit, UFDouble.ROUND_HALF_UP);

    UFDouble[] irate = HslParseUtil.toInteger(nrate);
    if (irate[0].intValue() == irate[1].intValue()) {
      return true;
    }

    return false;
  }

  /**
   * 方法功能描述：取物料档案上的默认采购单位
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVOs
   * @return <p>
   *         key为物料ID，value为采购单位
   * @since 6.1
   * @author lixyp
   * @time 2011-12-26 下午01:31:23
   */
  private void loadAssistunitMap(OrderVO[] vos) {
    if (this.assistunitMap == null) {
      Set<String> materialSet = new HashSet<String>();
      for (OrderVO orderVO : vos) {
        for (OrderItemVO orderItemVO : orderVO.getBVO()) {
          materialSet.add(orderItemVO.getPk_material());
        }
      }

      this.assistunitMap =
          MaterialPubService.queryPuMeasdocIDByPks(materialSet
              .toArray(new String[materialSet.size()]));

      if (this.assistunitMap == null) {
        this.assistunitMap = new HashMap<String, String>();
      }
    }
  }

  /**
   * 方法功能描述：自动转换单位
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-26 下午01:29:08
   */
  private void switchPUAssistUnit(OrderVO vo) {
    OrderItemVO[] itemVOs = vo.getBVO();

    // 在更改单位前，对vo进行备份，供下面比较用。
    OrderVO oldVO = this.cloneVO(vo);

    // 设置单位
    List<Integer> rowList = new ArrayList<Integer>();
    for (int i = 0; i < itemVOs.length; ++i) {
      String assist = this.assistunitMap.get(itemVOs[i].getPk_material());
      if (StringUtil.isEmptyWithTrim(assist)) {
        continue;
      }
      if (!assist.equals(itemVOs[i].getCastunitid())) {
        itemVOs[i].setCastunitid(assist);
        rowList.add(Integer.valueOf(i));
      }
    }

    // 同步换算率、报价换算率和单价等信息。
    if (!rowList.isEmpty()) {
      int[] rows = new int[rowList.size()];
      for (int i = 0; i < rowList.size(); ++i) {
        rows[i] = rowList.get(i).intValue();
      }

      this.syncUnitChangeRate(vo, rows);
      this.syncNum(vo, oldVO, rows);

      // 处理精度
      OrderVO[] vos = new OrderVO[] {
        vo
      };
      BillVOScaleProcessor scale =
          new BillVOScaleProcessor(vo.getHVO().getPk_group(), vos);
      TotalValueVOScaleProcessor totalScale =
          new TotalValueVOScaleProcessor(vos);
      new OrderScaleRule().setScale(scale, totalScale);
    }
  }

  private void syncNum(OrderVO vo, OrderVO oldVO, int[] rows) {
    OrderItemVO[] itemVOs = vo.getBVO();
    List<OrderItemVO> vos = new ArrayList<OrderItemVO>();
    OrderItemVO[] oldItemVOs = oldVO.getBVO();
    ScaleObjectFactory sofc = new ScaleObjectFactory(vo.getHVO().getPk_group());

    // 收集变更过单位的行
    for (int i = 0; i < rows.length; ++i) {
      int j = rows[i];
      String assist = this.assistunitMap.get(itemVOs[j].getPk_material());
      if (StringUtil.isEmptyWithTrim(assist)
          || StringUtil.isEmptyWithTrim(itemVOs[j].getVchangerate())) {
        continue;
      }
      if (!this.isEqualsRate(itemVOs[j].getVchangerate(),
          oldItemVOs[j].getVchangerate(), sofc.getHslScaleObject().getDigit())) {
        vos.add(itemVOs[j]);
      }
    }
    // 更改数量、报价数量，并进行联动计算
    if (vos.size() > 0) {
      OrderItemVO[] calItemVOs = vos.toArray(new OrderItemVO[vos.size()]);
      ScaleUtils scale = new ScaleUtils(AppContext.getInstance().getPkGroup());
      boolean cancalute = true;
      for (OrderItemVO calItemVO : calItemVOs) {
        UFDouble nastnum =
            HslParseUtil.hslDivUFDouble(calItemVO.getVchangerate(),
                calItemVO.getNnum());
        scale.adjustNumScale(nastnum, calItemVO.getCastunitid());
        calItemVO.setNastnum(nastnum);
        calItemVO.setNqtunitnum(nastnum);
        if (cancalute && calItemVO.getNorigtaxmny() == null) {
          cancalute = false;
        }
      }
      if (cancalute) {
        OrderVO calVo = new OrderVO();
        calVo.setHVO(vo.getHVO());
        calVo.setBVO(calItemVOs);
        // 通过价税合计调整单价
        RelationCalculate cal = new RelationCalculate();
        cal.calculate(calVo, OrderItemVO.NORIGTAXMNY);
      }
    }
  }

  /**
   * 同步换算率、报价换算率
   * 
   * @param vo
   * @param rows
   */
  private void syncUnitChangeRate(OrderVO vo, int[] rows) {
    // 设置换算率，同步报价换算率和报价单位
    UnitAndChangeRate rate = new UnitAndChangeRate(new BillHelper<OrderVO>(vo));
    rate.setChangeRate(rows);
  }
}
