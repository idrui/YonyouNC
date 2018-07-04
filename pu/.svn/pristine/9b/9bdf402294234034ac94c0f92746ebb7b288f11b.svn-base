/**
 * 
 */
package nc.bs.pu.m25.ap.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.arap.verify.AdjuestVO;
import nc.vo.arap.verify.EstParamVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceRowType;
import nc.vo.pu.m27.query.SettleBillInfo;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.util.TimeUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 暂估应付回冲规则
 * @scene
 * 传应付
 * @param
 * estVOMap 发票对象的暂估VO
 * sttlInfoMap 发票对象的结算明细 
 *
 * @since 6.3
 * @version 2014-10-22 下午3:43:09
 * @author zhangshqb
 */
public class BackWashEstAPRule implements IRule<InvoiceVO> {
  private Map<String, FeeEstVO> feeEstVOMap = new HashMap<String, FeeEstVO>();

  protected Map<String, GoodsEstVO> goodsEstVOMap =
      new HashMap<String, GoodsEstVO>();

  protected MapList<String, SettleBillInfo> sttlInfoMap;

  /**
   * BackwashEstAPRule 的构造子
   * 
   * @param estVOMap
   * @param sttlInfoMap
   */
  public BackWashEstAPRule(Map<String, EstVO[]> estVOMap,
      MapList<String, SettleBillInfo> sttlInfoMap) {
    this.sttlInfoMap = sttlInfoMap;
    this.filterEstVO(estVOMap);
  }

  @Override
  public void process(InvoiceVO[] vos) {
    boolean isAPEnabled = SysInitGroupQuery.isAPEnabled();
    if (!isAPEnabled) {
      return;
    }
    if (ArrayUtils.isEmpty(vos) || MapUtils.isEmpty(this.goodsEstVOMap)
        && MapUtils.isEmpty(this.feeEstVOMap)) {
      return;
    }
    this.procInvoiceVOs(vos);
  }

  private void backWashOneInvoiceEstAp(InvoiceVO vo) {
    List<AdjuestVO> adjVOList = new ArrayList<AdjuestVO>();
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      String pk_invoice_b = item.getPk_invoice_b();
      List<SettleBillInfo> sbiList = this.sttlInfoMap.get(pk_invoice_b);
      if (CollectionUtils.isEmpty(sbiList)) {
        continue;
      }
      int rowtype = item.getFrowtype().intValue();
      if (InvoiceRowType.GOODS_ROW == rowtype) {
        adjVOList.addAll(this.getGoodsAdjVO(sbiList));
      }
      else if (InvoiceRowType.FEE_ROW == rowtype) {
        adjVOList.addAll(this.getFeeAdjVO(item, sbiList));
        // 赵玉行 零数量发票行需要和需求确认
      }
    }
    if (adjVOList.size() == 0) {
      return;
    }
    AdjuestVO[] adjVos = adjVOList.toArray(new AdjuestVO[adjVOList.size()]);
    ArapServicesForPUUtil.washEstPayable(adjVos);
  }

  private void filterEstVO(Map<String, EstVO[]> allEstVOMap) {
    if (MapUtils.isEmpty(allEstVOMap)) {
      return;
    }
    for (Entry<String, EstVO[]> entry : allEstVOMap.entrySet()) {
      EstVO[] allevos = entry.getValue();
      if (ArrayUtils.isEmpty(allevos)) {
        continue;
      }
      for (EstVO vo : allevos) {
        // 找出做过货物暂估应付的数据
        GoodsEstVO head = vo.getParentVO();
        // String pk_stockps_b = head.getPk_stockps_b();
        String pk_stockps_b =
            (String) head.getAttributeValue(GoodsEstVO.PK_STOCKPS_B);
        if (EnumToAPFlag.EstimateToAP.value().equals(head.getFtoapflag())) {
          this.goodsEstVOMap.put(pk_stockps_b, head);
        }
        FeeEstVO[] fees = vo.getChildrenVO();
        if (ArrayUtils.isEmpty(fees)) {
          continue;
        }
        // 找出做过费用暂估应付的数据
        for (FeeEstVO fee : fees) {
          if (!ValueUtils.getBoolean(fee.getBtoap())) {
            continue;
          }
          String key = pk_stockps_b + fee.getPk_srcfeematerial();
          this.feeEstVOMap.put(key, fee);
        }
      }
    }
  }

  private List<AdjuestVO> getFeeAdjVO(InvoiceItemVO item,
      List<SettleBillInfo> sbiList) {
    List<AdjuestVO> adjVOList = new ArrayList<AdjuestVO>();
    for (SettleBillInfo sbi : sbiList) {
      String pk_srcmaterial = item.getPk_srcmaterial();
      FeeEstVO fevo = this.getFeeWashEstVO(pk_srcmaterial, sbi);
      if (null == fevo) {
        continue;
      }
      AdjuestVO adjVo = new AdjuestVO();
      adjVo.setTop_itemid(fevo.getPk_stockps_fee());
      adjVo.setEst_top_itemid(fevo.getPk_stockps_fee());
      adjVo.setClbh(sbi.getPk_invoice());
      adjVo.setQuantity(UFDouble.ZERO_DBL);// 费用暂估一次全部冲完
      adjVOList.add(adjVo);
    }
    return adjVOList;
  }

  private FeeEstVO getFeeWashEstVO(String pk_srcmaterial, SettleBillInfo sbi) {
    String pk_stock_b = sbi.getPk_stock_b();
    if (StringUtil.isEmptyWithTrim(pk_stock_b)) {
      return null;
    }
    String key = pk_stock_b + pk_srcmaterial;
    FeeEstVO fevo = this.feeEstVOMap.get(key);
    if (null == fevo) {
      return null;
    }
    String pk_firststl_b = fevo.getPk_firstsettle_b();
    String pk_stl_b = sbi.getPk_settlebill_b();
    // 只有第一次结算的发票才回冲费用暂估
    if (pk_stl_b.equals(pk_firststl_b)) {
      return fevo;
    }
    return null;
  }

  private List<AdjuestVO> getGoodsAdjVO(List<SettleBillInfo> sttlInfoList) {
    List<AdjuestVO> adjVOList = new ArrayList<AdjuestVO>();
    for (SettleBillInfo info : sttlInfoList) {
      GoodsEstVO gevo = this.getWashGoodEstVO(info);
      if (null == gevo) {
        continue;
      }
      String pk_stock_b = info.getPk_stock_b();
      AdjuestVO adjVo = new AdjuestVO();
      adjVo.setTop_itemid(pk_stock_b);
      adjVo.setEst_top_itemid(pk_stock_b);
      adjVo.setQuantity(info.getNsettlenum());
      adjVo.setClbh(info.getPk_invoice());
      adjVOList.add(adjVo);
    }
    return adjVOList;
  }

  /**
   * @param vo
   * @return
   */
  protected EstParamVO getAPPara(InvoiceVO vo) {
    EstParamVO apPara = new EstParamVO();
    apPara.setOperator(BSContext.getInstance().getUserID());
    apPara.setOpertime(TimeUtils.getSrvBaseTime());
    return apPara;
  }

  protected GoodsEstVO getWashGoodEstVO(SettleBillInfo sbi) {
    String pk_stock_b = sbi.getPk_stock_b();
    if (StringUtil.isEmptyWithTrim(pk_stock_b)) {
      return null;
    }
    GoodsEstVO gevo = this.goodsEstVOMap.get(pk_stock_b);
    if (null != gevo
        && EnumToAPFlag.EstimateToAP.value().equals(gevo.getFtoapflag())
        && UFBoolean.TRUE.equals(sbi.getBwashest())) {
      // 对应的结算单行必须回冲过暂估应付才处理
      // 避免这种情况：入库->部分开票1->结算->剩余部分暂估应付，发票1传应付（此时不能回冲暂估）
      return gevo;
    }
    return null;
  }

  protected void procInvoiceVOs(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      this.backWashOneInvoiceEstAp(vo);
    }
  }

}
