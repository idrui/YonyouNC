package nc.bs.pu.m4t.settle.rule;

import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.pu.m27.settlebill.rule.AbstractSettleUpdateRule;
import nc.bs.pu.m27.settlebill.rule.WritebackPoint;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 * 期初暂估单结算后的回写
 * @scene
 * 期初暂估单结算后的回写
 * @param
 * 无
 *
 * @since 6.3
 * @version 2011-1-29 上午10:32:24
 * @author zhaoyha
 */
public class InitialSettleUpdateRule extends AbstractSettleUpdateRule {
  private WritebackPoint wbp;

  public InitialSettleUpdateRule(WritebackPoint wbp) {
    this.wbp = wbp;
  }

  private void check(InitialEstItemVO item, List<SettleBillItemVO> itemlst) {
    UFDouble naccwashmny = item.getNaccwashmny();
    // wuxla v61使用记成本金额
    // UFDouble nestmny = item.getNmny();
    UFDouble ncalcostmny = item.getNcalcostmny();
    UFDouble naccstlnum = item.getNaccsettlenum();
    UFDouble ninnum = item.getNnum();
    UFDouble estnum = ninnum;
    UFDouble acceststlnum = naccstlnum;
    String code = itemlst.get(0).getVstockcode();
    if (MathTool.absCompareTo(naccstlnum, ninnum) > 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0217", null, new String[] {
            code
          })/* [{0}]累计结算主数量超过入库主数量！ */);
    }
    if (MathTool.isDiffSign(naccstlnum, ninnum)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0218", null, new String[] {
            code
          })/* [{0}]累计结算主数量正负与入库主数量不一致！ */);
    }
    if (UFBoolean.TRUE.equals(item.getBsettlefinish())
        && (!MathTool.equals(naccwashmny, ncalcostmny) || !MathTool.equals(
            acceststlnum, estnum))) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0212", null, new String[] {
            code
          })/* [{0}]已经结算完毕，暂估金额或数量未全部调整！ */);
    }
    if (MathTool.isZero(naccstlnum)
        && (!MathTool.isZero(naccwashmny) || !MathTool.isZero(acceststlnum))) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0213", null, new String[] {
            code
          })/* [{0}]结算已经全部取消，但仍有冲销暂估金额或数量未取消！ */);
    }
    if (MathTool.absCompareTo(naccwashmny, ncalcostmny) > 0
        || MathTool.absCompareTo(acceststlnum, estnum) > 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0214", null, new String[] {
            code
          })/* [{0}]累计回冲暂估数量和金额不能大于暂估的数量或金额！ */);
    }
  }

  private void updateDB(InitialEstItemVO[] items) {
    VOUpdate<InitialEstItemVO> vu = new VOUpdate<InitialEstItemVO>();
    vu.update(items, new String[] {
      InitialEstItemVO.BSETTLEFINISH, InitialEstItemVO.NACCSETTLENUM,
      InitialEstItemVO.NACCWASHMNY, InitialEstItemVO.NACCSETTLEMNY,
      InitialEstItemVO.NACCGOODSSETTLEMNY, InitialEstItemVO.NACCFEESETTLEMNY
    });
  }

  private void updateItem(InitialEstItemVO item, List<SettleBillItemVO> list) {
    UFDouble accstlnum = item.getNaccsettlenum();
    UFDouble accwashmny = item.getNaccwashmny();
    UFDouble accgoodssettlemny = item.getNaccgoodssettlemny();
    UFDouble accsettlemny = item.getNaccsettlemny();
    UFDouble accfeesettlemny = item.getNaccfeesettlemny();
    for (SettleBillItemVO sbitem : list) {
      UFDouble stlnum =
          WritebackPoint.SAVE == this.wbp ? sbitem.getNsettlenum() : MathTool
              .oppose(sbitem.getNsettlenum());
      UFDouble washmny =
          WritebackPoint.SAVE == this.wbp ? sbitem.getNclashestmoney()
              : MathTool.oppose(sbitem.getNclashestmoney());
      UFDouble goodssettlemny =
          WritebackPoint.SAVE == this.wbp ? sbitem.getNgoodsmoney() : MathTool
              .oppose(sbitem.getNgoodsmoney());
      UFDouble settlemny =
          WritebackPoint.SAVE == this.wbp ? sbitem.getNmoney() : MathTool
              .oppose(sbitem.getNmoney());
      UFDouble feesettlemny =
          WritebackPoint.SAVE == this.wbp ? this.getTotalFeemny(sbitem)
              : MathTool.oppose(this.getTotalFeemny(sbitem));
      accstlnum = MathTool.add(accstlnum, stlnum);
      accwashmny = MathTool.add(accwashmny, washmny);
      accgoodssettlemny = MathTool.add(accgoodssettlemny, goodssettlemny);
      accsettlemny = MathTool.add(accsettlemny, settlemny);
      accfeesettlemny = MathTool.add(accfeesettlemny, feesettlemny);
    }
    item.setNaccsettlenum(accstlnum);
    item.setNaccwashmny(accwashmny);
    item.setNaccfeesettlemny(accfeesettlemny);
    item.setNaccgoodssettlemny(accgoodssettlemny);
    item.setNaccsettlemny(accsettlemny);
    if (MathTool.compareTo(accstlnum, item.getNnum()) == 0) {
      item.setBsettlefinish(UFBoolean.TRUE);
    }
    else {
      item.setBsettlefinish(UFBoolean.FALSE);
    }

  }

  @Override
  protected String getStockBIDField() {
    return SettleBillItemVO.PK_INITIALEST_B;
  }

  @Override
  protected void updateSettleInfo(String[] stockBIDs,
      MapList<String, SettleBillItemVO> sbidStlMap) {
    new VOConcurrentTool().lock(InitialEstItemVO.class, stockBIDs);
    VOQuery<InitialEstItemVO> vq =
        new VOQuery<InitialEstItemVO>(InitialEstItemVO.class);
    InitialEstItemVO[] items = vq.query(stockBIDs);
    for (InitialEstItemVO item : items) {
      List<SettleBillItemVO> itemlst =
          sbidStlMap.get(item.getPk_initialest_b());
      this.updateItem(item, itemlst);// 更新每一个表体结算信息
      this.check(item, itemlst);// 检查回写的结算信息是否正确
    }
    this.updateDB(items);// 持久化到数据库
  }

}
