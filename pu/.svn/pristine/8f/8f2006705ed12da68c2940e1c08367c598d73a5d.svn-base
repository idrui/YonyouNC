package nc.bs.pu.m27.settlebill.rule;

import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.tool.ViewConcurrentTool;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 消耗汇总、采购入在采购暂估的结算后回写基类
 * 
 * @since 6.0
 * @version 2011-2-11 下午11:13:08
 * @author zhaoyha
 */
public abstract class AbstractEstSettleUpdateRule<E extends GoodsEstVO> extends
    AbstractSettleUpdateRule {
  private Class<E> clazz;

  protected WritebackPoint wbp;

  public AbstractEstSettleUpdateRule(WritebackPoint wbp, Class<E> goodsEstClazz) {
    this.wbp = wbp;
    this.clazz = goodsEstClazz;
  }

  /**
   * 在VO中设置完结算执行信息后的检查<br>
   * 父类完成对累计结算、暂估回冲等信息的检查
   * 
   * @param gevo
   */
  protected void check(E gevo) {
    UFDouble naccwashmny = gevo.getNaccestcostwashmny();
    // UFDouble nestmny = gevo.getNestmny();
    // wuxla V61 使用记成本金额
    UFDouble nestcalcostmny = gevo.getNestcalcostmny();
    UFDouble naccstlnum = gevo.getNaccumsettlenum();
    UFDouble ninnum = gevo.getNinnum();
    UFDouble estnum = gevo.getNestnum();
    UFDouble acceststlnum = gevo.getNaccestcoststtlnum();
    String code = gevo.getVbillcode();
    if (MathTool.absCompareTo(naccstlnum, ninnum) > 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0210", null, new String[] {
            code
          })/* [{0}]累计结算主数量超过入库／汇总主数量！ */);
    }
    if (MathTool.isDiffSign(naccstlnum, ninnum)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0211", null, new String[] {
            code
          })/* [{0}]累计结算主数量正负与入库／汇总主数量不一致！ */);
    }
    // if (UFBoolean.TRUE.equals(gevo.getBsettlefinish())
    // && (!MathTool.equals(naccwashmny, nestmny) || !MathTool.equals(
    // acceststlnum, estnum))) {
    // ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
    // .getStrByID("4004060_0", "04004060-0212", null, new String[] {
    // code
    // })/* [{0}]已经结算完毕，暂估金额或数量未全部调整！ */);
    // }
    // wuxla v61 记成本金额
    if (UFBoolean.TRUE.equals(gevo.getBsettlefinish())
        && (!MathTool.equals(naccwashmny, nestcalcostmny) || !MathTool.equals(
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
    // if (MathTool.absCompareTo(naccwashmny, nestmny) > 0
    // || MathTool.absCompareTo(acceststlnum, estnum) > 0) {
    // ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
    // .getStrByID("4004060_0", "04004060-0214", null, new String[] {
    // code
    // })/* [{0}]累计回冲暂估数量和金额不能大于暂估的数量或金额！ */);
    // }
    // wuxla v61 记成本金额
    if (MathTool.absCompareTo(naccwashmny, nestcalcostmny) > 0
        || MathTool.absCompareTo(acceststlnum, estnum) > 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004060_0", "04004060-0214", null, new String[] {
            code
          })/* [{0}]累计回冲暂估数量和金额不能大于暂估的数量或金额！ */);
    }
  }

  /**
   * 删除结算单回写的检查
   * 
   * @param gevo
   * @param list
   */
  protected void checkDel(GoodsEstVO gevo, List<SettleBillItemVO> list) {

    if (MathTool.isZero(gevo.getNestnum())) {
      return;
    }
    String code = gevo.getVbillcode();
    for (SettleBillItemVO sbitem : list) {
      if (UFBoolean.FALSE.equals(sbitem.getBwashest())
          && !EnumMatchRowType.StockFeeSettle.toInteger().equals(// 纯费用结算不校验
              sbitem.getFrowtype())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004060_0", "04004060-0215", null, new String[] {
              code
            })/* [{0}]结算后存在暂估数据，不能删除此结算单！ */);
      }
    }
  }

  /**
   * 设置VO的一些结算信息 <br>
   * 父类完成对于累计结算、暂估回冲信息的处理
   * 
   * @param gevo
   * @param list
   */
  protected void setSettleInfo(E gevo, List<SettleBillItemVO> list) {
    UFDouble accstlnum = gevo.getNaccumsettlenum();
    UFDouble accwashmny = gevo.getNaccestcostwashmny();
    UFDouble acceststlnum = gevo.getNaccestcoststtlnum();
    UFDouble accpreeststlmny = gevo.getNaccpreeststtlmny();
    UFDouble estnum = gevo.getNestnum();
    UFDouble accgoodssettlemny = gevo.getNaccgoodssettlemny();
    UFDouble accsettlemny = gevo.getNaccsettlemny();
    UFDouble accfeesettlemny = gevo.getNaccfeesettlemny();
    // mengjian 累计调整货物结算金额
    UFDouble accadjustmny = gevo.getNaccadjustmny();

    for (SettleBillItemVO sbitem : list) {
      UFDouble stlnum =
          WritebackPoint.SAVE == this.wbp ? sbitem.getNsettlenum() : MathTool
              .oppose(sbitem.getNsettlenum());
      UFDouble washmny =
          WritebackPoint.SAVE == this.wbp ? sbitem.getNclashestmoney()
              : MathTool.oppose(sbitem.getNclashestmoney());
      UFDouble stlmny =
          WritebackPoint.SAVE == this.wbp ? sbitem.getNgoodsmoney() : MathTool
              .oppose(sbitem.getNgoodsmoney());
      UFDouble goodssettlemny =
          WritebackPoint.SAVE == this.wbp ? sbitem.getNgoodsmoney() : MathTool
              .oppose(sbitem.getNgoodsmoney());
      UFDouble settlemny =
          WritebackPoint.SAVE == this.wbp ? sbitem.getNmoney() : MathTool
              .oppose(sbitem.getNmoney());
      UFDouble feesettlemny =
          WritebackPoint.SAVE == this.wbp ? this.getTotalFeemny(sbitem)
              : MathTool.oppose(this.getTotalFeemny(sbitem));
      UFDouble adjustsettlemny =
          WritebackPoint.SAVE == this.wbp ? sbitem.getNadjustmny() : MathTool
              .oppose(sbitem.getNadjustmny());
      accstlnum = MathTool.add(accstlnum, stlnum);
      accwashmny = MathTool.add(accwashmny, washmny);
      accgoodssettlemny = MathTool.add(accgoodssettlemny, goodssettlemny);
      accsettlemny = MathTool.add(accsettlemny, settlemny);
      accfeesettlemny = MathTool.add(accfeesettlemny, feesettlemny);
      accadjustmny = MathTool.add(accadjustmny, adjustsettlemny);
      if (MathTool.isZero(estnum)) {
        accpreeststlmny = MathTool.add(accpreeststlmny, stlmny);
      }
      else {
        acceststlnum = MathTool.add(acceststlnum, stlnum);
      }
    }
    gevo.setNaccumsettlenum(accstlnum);
    gevo.setNaccestcostwashmny(accwashmny);
    gevo.setNaccestcoststtlnum(acceststlnum);
    gevo.setNaccpreeststtlmny(accpreeststlmny);
    gevo.setNaccfeesettlemny(accfeesettlemny);
    gevo.setNaccgoodssettlemny(accgoodssettlemny);
    gevo.setNaccsettlemny(accsettlemny);
    gevo.setNaccadjustmny(accadjustmny);
    if (MathTool.compareTo(accstlnum, gevo.getNinnum()) == 0) {
      gevo.setBsettlefinish(UFBoolean.TRUE);
    }
    else {
      gevo.setBsettlefinish(UFBoolean.FALSE);
    }
  }

  /**
   * 执行数据库的更新
   * 
   * @param gevos
   */
  protected abstract void updateDB(E[] gevos);

  @Override
  protected void updateSettleInfo(String[] stockBIDs,
      MapList<String, SettleBillItemVO> sbidStlMap) {

    ViewQuery<E> vq = new ViewQuery<E>(this.clazz);
    E[] gevos = vq.query(stockBIDs);

    // 规则很多地方引用，引用的地方没有加锁，出现并发。
    // 比如删除结算单和费用结算。
    new ViewConcurrentTool().lock(gevos);

    for (E gevo : gevos) {
      // 对删除进行特殊检查，如暂估后不能删除以前结算单
      if (WritebackPoint.DELETE == this.wbp) {
        this.checkDel(gevo, sbidStlMap.get(gevo.getPk_stockps_b()));
      }
      this.setSettleInfo(gevo, sbidStlMap.get(gevo.getPk_stockps_b()));// 更新每一个表体结算信息
      this.check(gevo);// 检查回写的结算信息是否正确
    }
    this.updateDB(gevos);// 持久化到数据库
  }

}
