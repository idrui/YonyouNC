package nc.bs.pu.est.m45.rule.settle;

import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.pu.m27.settlebill.rule.AbstractEstSettleUpdateRule;
import nc.bs.pu.m27.settlebill.rule.WritebackPoint;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.vo.pu.est.entity.m45.PurchaseInEstHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 * 结算时回写，更新采购入财务结算信息-正向
 * @scene
 * 删除结算单,费用结算,结算完毕保存结算单
 * @param
 * wbp  回写时点的枚举类
 *
 * @since 6.3
 * @version 2014-10-22 下午4:06:57
 * @author zhangshqb
 */
public class PurchsInSettleUpdateRule extends
    AbstractEstSettleUpdateRule<PurchaseInEstHeaderVO> {

  public PurchsInSettleUpdateRule(WritebackPoint wbp) {
    super(wbp, PurchaseInEstHeaderVO.class);
  }

  private void checkConfirm(PurchaseInEstHeaderVO gevo) {
    UFDouble accadjmny = gevo.getNacctocostadjstmny();
    UFDouble accadjapmny = gevo.getNacctoapadjstotmny();
    // wuxla v61
    UFDouble ncalcostmny = gevo.getNcalcostmny();
    // UFDouble mny = gevo.getNmny();
    UFDouble origtaxmny = gevo.getNorigtaxmny();
    UFDouble naccstlnum = gevo.getNaccumsettlenum();
    String code = gevo.getVbillcode();
    // 结算完毕，确认成本比较确认金额，确认应付比较应付价税合计
    if (UFBoolean.TRUE.equals(gevo.getBsettlefinish())
        && (EnumToIAFlag.ConfirmToIA.toInteger().equals(gevo.getFtoiaflag())
            && !MathTool.equals(accadjmny, ncalcostmny) || EnumToAPFlag.ConfirmToAP
            .toInteger().equals(gevo.getFtoapflag())
            && !MathTool.equals(accadjapmny, origtaxmny))) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0206", null, new String[] {
            code
          })/* [{0}]已经结算完毕，确认成本金额／应付价税合计未全部调整！ */);
    }
    if (MathTool.isZero(naccstlnum)
        && (!MathTool.isZero(accadjmny) || !MathTool.isZero(accadjapmny))) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0207", null, new String[] {
            code
          })/* [{0}]结算已经全部取消，但仍有调整确认成本金额／应付价税合计未取消！ */);
    }
    if (MathTool.absCompareTo(accadjmny, ncalcostmny) > 0
        || MathTool.absCompareTo(accadjapmny, origtaxmny) > 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0208", null, new String[] {
            code
          })/* [{0}]累计调整确认成本的金额／应付价税合计不能大于确认成本的金额／应付价税合计！ */);
    }

  }

  /** 调整确认应付原币价税合计 **/
  private UFDouble getSettleAdjApMny(SettleBillItemVO item) {
    return WritebackPoint.SAVE == this.wbp ? item.getNoppoconfmapmny()
        : MathTool.oppose(item.getNoppoconfmapmny());
  }

  /** 调整确认金额 **/
  private UFDouble getSettleAdjustMny(SettleBillItemVO item) {
    return WritebackPoint.SAVE == this.wbp ? item.getNoppoconfmmoney()
        : MathTool.oppose(item.getNoppoconfmmoney());
  }

  @Override
  protected void check(PurchaseInEstHeaderVO gevo) {
    super.check(gevo);
    // 执行采购入结算回写的确认成本和应付检查
    if (EnumToIAFlag.ConfirmToIA.value().equals(gevo.getFtoiaflag())
        || EnumToAPFlag.ConfirmToAP.value().equals(gevo.getFtoapflag())) {
      this.checkConfirm(gevo);
    }

  }

  @Override
  protected String getStockBIDField() {
    return SettleBillItemVO.PK_PURCHASEIN_B;
  }

  @Override
  protected void setSettleInfo(PurchaseInEstHeaderVO gevo,
      List<SettleBillItemVO> list) {
    super.setSettleInfo(gevo, list);
    for (SettleBillItemVO item : list) {
      UFDouble adjustmny = this.getSettleAdjustMny(item);
      UFDouble accajst = gevo.getNacctocostadjstmny();
      UFDouble adjustApMny = this.getSettleAdjApMny(item);
      UFDouble accAjsApMny = gevo.getNacctoapadjstotmny();
      // 累计调整确认金额
      gevo.setNacctocostadjstmny(MathTool.add(accajst, adjustmny));
      // 累计调整确认应付原币价税合计
      gevo.setNacctoapadjstotmny(MathTool.add(accAjsApMny, adjustApMny));
    }
  }

  @Override
  protected void updateDB(PurchaseInEstHeaderVO[] vos) {

    ViewUpdate<PurchaseInEstHeaderVO> vupdate =
        new ViewUpdate<PurchaseInEstHeaderVO>();
    String[] names =
        new String[] {
          PurchaseinFIItemVO.BSETTLEFINISH, PurchaseinFIItemVO.NACCUMSETTLENUM,
          PurchaseinFIItemVO.NACCESTCOSTSTTLNUM,
          PurchaseinFIItemVO.NACCPREESTSTTLMNY,
          PurchaseinFIItemVO.NACCESTCOSTWASHMNY,
          PurchaseinFIItemVO.NACCTOCOSTADJSTMNY,
          PurchaseinFIItemVO.NACCTOAPADJSTOTMNY,
          PurchaseinFIItemVO.NACCSETTLEMNY,
          PurchaseinFIItemVO.NACCGOODSSETTLEMNY,
          PurchaseinFIItemVO.NACCFEESETTLEMNY, PurchaseinFIItemVO.NACCADJUSTMNY
        };
    vupdate.update(vos, PurchaseinFIItemVO.class, names);
  }

}
