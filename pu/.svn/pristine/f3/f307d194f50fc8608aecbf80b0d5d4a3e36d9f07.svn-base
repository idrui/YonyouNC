package nc.bs.pu.m4203.rule;

import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.pu.m27.settlebill.rule.AbstractSettleUpdateRule;
import nc.bs.pu.m27.settlebill.rule.WritebackPoint;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m4203.entity.SubcontinFIItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 * 结算时回写，更新委托加工入财务结算信息-正向
 * @scene
 * 删除结算单,费用结算,结算完毕保存结算单
 * @param
 * wrPoint 回写时点的枚举类（保存时、删除时）
 *
 * @since 6.3
 * @version 2014-10-22 下午4:07:22
 * @author zhangshqb
 */
public class SubcontInSettleUpdateRule extends AbstractSettleUpdateRule {

  private WritebackPoint wrPoint = WritebackPoint.SAVE;

  /**
   * @param wrPoint 生成结算单- WritebackPoint.SAVE 删除结算单-WritebackPoint.DELETE
   */
  public SubcontInSettleUpdateRule(WritebackPoint wrPoint) {
    super();
    this.wrPoint = wrPoint;
  }

  private void checkSettleInfo(SubcontinFIItemVO item,
      List<SettleBillItemVO> itemlst) {
    UFDouble naccwashmny = item.getNaccestcostwashmny();
    UFDouble nestmny = item.getNcostmny();
    UFDouble naccstlnum = item.getNaccumsettlenum();
    UFDouble ninnum = item.getNinnum();
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
        && (!MathTool.equals(naccwashmny, nestmny) || !MathTool.equals(
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
    if (MathTool.absCompareTo(naccwashmny, nestmny) > 0
        || MathTool.absCompareTo(acceststlnum, estnum) > 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0214", null, new String[] {
            code
          })/* [{0}]累计回冲暂估数量和金额不能大于暂估的数量或金额！ */);
    }
  }

  private UFDouble getsettlenum(SettleBillItemVO stlitem) {
    return this.wrPoint == WritebackPoint.SAVE ? stlitem.getNsettlenum()
        : MathTool.oppose(stlitem.getNsettlenum());
  }

  private UFDouble getsettlewashmny(SettleBillItemVO stlitem) {
    return this.wrPoint == WritebackPoint.SAVE ? stlitem.getNclashestmoney()
        : MathTool.oppose(stlitem.getNclashestmoney());
  }

  private void setSettleInfo(SubcontinFIItemVO item,
      List<SettleBillItemVO> stlItemList) {
    UFDouble accstlnum = item.getNaccumsettlenum();
    UFDouble accwashmny = item.getNaccestcostwashmny();
    UFDouble accgoodssettlemny = item.getNaccgoodssettlemny();
    UFDouble accsettlemny = item.getNaccsettlemny();
    UFDouble accfeesettlemny = item.getNaccfeesettlemny();
    for (SettleBillItemVO stlitem : stlItemList) {
      accstlnum = MathTool.add(accstlnum, this.getsettlenum(stlitem));
      accwashmny = MathTool.add(accwashmny, this.getsettlewashmny(stlitem));
      UFDouble goodssettlemny =
          WritebackPoint.SAVE == this.wrPoint ? stlitem.getNgoodsmoney()
              : MathTool.oppose(stlitem.getNgoodsmoney());
      UFDouble settlemny =
          WritebackPoint.SAVE == this.wrPoint ? stlitem.getNmoney() : MathTool
              .oppose(stlitem.getNmoney());
      UFDouble feesettlemny =
          WritebackPoint.SAVE == this.wrPoint ? this.getTotalFeemny(stlitem)
              : MathTool.oppose(this.getTotalFeemny(stlitem));
      accgoodssettlemny = MathTool.add(accgoodssettlemny, goodssettlemny);
      accsettlemny = MathTool.add(accsettlemny, settlemny);
      accfeesettlemny = MathTool.add(accfeesettlemny, feesettlemny);
    }
    item.setNaccumsettlenum(accstlnum);
    item.setNaccestcostwashmny(accwashmny);
    item.setNaccfeesettlemny(accfeesettlemny);
    item.setNaccgoodssettlemny(accgoodssettlemny);
    item.setNaccsettlemny(accsettlemny);
    if (accstlnum.equals(item.getNinnum())) {
      item.setBsettlefinish(UFBoolean.TRUE);
    }
    else {
      item.setBsettlefinish(UFBoolean.FALSE);
    }
    this.checkSettleInfo(item, stlItemList);// 检查结算回写信息是否正确
  }

  private void updateDB(SubcontinFIItemVO[] items) {
    String[] updtFields =
        {
          SubcontinFIItemVO.NACCUMSETTLENUM,
          SubcontinFIItemVO.NACCESTCOSTWASHMNY,
          SubcontinFIItemVO.BSETTLEFINISH, SubcontinFIItemVO.NACCSETTLEMNY,
          SubcontinFIItemVO.NACCGOODSSETTLEMNY,
          SubcontinFIItemVO.NACCFEESETTLEMNY
        };
    new VOUpdate<SubcontinFIItemVO>().update(items, updtFields);
  }

  @Override
  protected String getStockBIDField() {
    return SettleBillItemVO.PK_SUBCONTRACT_B;
  }

  @Override
  protected void updateSettleInfo(String[] stockBIDs,
      MapList<String, SettleBillItemVO> sbidStlMap) {
    new VOConcurrentTool().lock(SubcontinFIItemVO.class, stockBIDs);

    VOQuery<SubcontinFIItemVO> vq =
        new VOQuery<SubcontinFIItemVO>(SubcontinFIItemVO.class);
    SubcontinFIItemVO[] items = vq.query(stockBIDs);

    for (SubcontinFIItemVO item : items) {
      // 更新委托加工入财务表体结算信息
      this.setSettleInfo(item, sbidStlMap.get(item.getPk_stockps_b()));
    }
    // 更新到数据库
    this.updateDB(items);
  }

}
