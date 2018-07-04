/**
 * $文件说明$
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-25 上午10:27:46
 */
package nc.pubimpl.pu.m20.so.m30.action;

import nc.bs.pu.m20.maintain.PraybillInsertBP;
import nc.bs.pu.m20.maintain.rule.pub.ChangeRateRule;
import nc.bs.pu.m20.maintain.rule.pub.SetPraytypeRule;
import nc.bs.pu.m20.maintain.rule.pub.SetPsnAndDeptRule;
import nc.bs.pu.m20.maintain.rule.pub.SetSctypeRule;
import nc.impl.pu.m20.rule.ChkPrayVONotNullRule;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.pu.reference.so.M30SOServices;
import nc.pubimpl.pu.m20.plugin.ActionPlugInPoint;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.CalculateDateRule;
import nc.vo.pu.m20.rule.CalculateNumRule;
import nc.vo.pu.m20.rule.SetAstunitRule;
import nc.vo.pu.m20.rule.SetDefaultValueRule;
import nc.vo.pu.m20.rule.SetEmployeeRule;
import nc.vo.pu.m20.rule.SetOrdertrantypeRule;
import nc.vo.pu.m20.rule.SetOrgCurrRule;
import nc.vo.pu.m20.rule.SetPriceRule;
import nc.vo.pu.m20.rule.SetPurchaseorgRule;
import nc.vo.pu.m20.rule.SetRownoRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.scmpub.res.billtype.SOBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售订单推式保存请购单
 * <li>1：首先 直运安排
 * <li>2： 委外默认值设置，并分单处理，直运类型的请购单不分单。非直运类型的按照委外属性分单、
 * <li>3: 默认值设置。
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-25 上午10:27:46
 */
public class PushSave20For30Action {
  /**
   * 方法功能描述： 销售订单推式保存请购单
   * <p>
   * <b>参数说明</b>
   * 
   * @param praybills
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-31 上午09:30:03
   */
  public void pushSave(PraybillVO[] praybills) {
    if (ArrayUtils.isEmpty(praybills)) {
      return;
    }
    TimeLog.logStart();
    PraybillVO[] insertVOs = null;
    // 获取差异VO
    BillTransferTool<PraybillVO> transferTool =
        new BillTransferTool<PraybillVO>(praybills);
    insertVOs = transferTool.getClientFullInfoBill();

    AroundProcesser<PraybillVO> processer =
        new AroundProcesser<PraybillVO>(ActionPlugInPoint.PushSave20For30Action);
    // 加入Action中的执行前/后规则
    this.addBeforeRule(processer);

    // 直运处理
    this.setDirecttransit(insertVOs);
    // 补全是否委外 并分单
    PraybillVO[] afterSplitVos = new SetSctypeRule().process(insertVOs);

    processer.before(afterSplitVos);

    new PraybillInsertBP().insert(afterSplitVos);

    processer.after(afterSplitVos);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004020_0", "04004020-0052")/* @res "业务日志:后台插入单据数据" */);

  }

  private void addBeforeRule(AroundProcesser<PraybillVO> processer) {
    // 添加规则顺序不能更改！！
    // 参数正确性检查
    processer.addBeforeFinalRule(new ChkPrayVONotNullRule());
    // 补全默认值
    processer.addBeforeFinalRule(new SetDefaultValueRule());
    // 补全请购类型
    processer.addBeforeFinalRule(new SetPraytypeRule());
    // 补全计划员和计划部门
    processer.addBeforeFinalRule(new SetPsnAndDeptRule());
    // 补全行号
    processer.addBeforeFinalRule(new SetRownoRule());
    // 补全本币币种
    processer.addBeforeFinalRule(new SetOrgCurrRule());

    // 补全采购组织
    processer.addBeforeFinalRule(new SetPurchaseorgRule());
    // 补全采购员
    processer.addBeforeFinalRule(new SetEmployeeRule());
    // 补全订单类型
    processer.addBeforeFinalRule(new SetOrdertrantypeRule());
    // 补全单位
    processer.addBeforeFinalRule(new SetAstunitRule());
    // 补全换算率
    processer.addBeforeFinalRule(new ChangeRateRule());
    // 补全数量
    processer.addBeforeFinalRule(new CalculateNumRule());

    // 补全需求日期和建议订货日期
    processer.addBeforeFinalRule(new CalculateDateRule());
    // 补全含税单价和价税合计
    processer.addBeforeFinalRule(new SetPriceRule());
    // 推单时进行尾差处理(数量)
    // processer.addBeforeRule(new PrayBillMarginRule(MMBillType.PlanOrder
    // .getCode(), null));
  }

  private String getDirecttype(PraybillVO vo) {
    String flag = "N";
    String csourcetype = vo.getBVO()[0].getCsourcetypecode();
    if (!SOBillType.Order.getCode().equals(csourcetype)) {
      return flag;
    }
    String vtrantypecode = vo.getBVO()[0].getVsrctrantypecode();
    // vtrantypecode = PfServiceScmUtil.getTrantypecodeByid(vtrantypecode);
    UFBoolean direct = M30SOServices.queryIsDirectPO(vtrantypecode);
    if (UFBoolean.TRUE.equals(direct)) {
      flag = "Y";
    }
    return flag;
  }

  private void setDirecttransit(PraybillVO[] praybills) {
    for (PraybillVO vo : praybills) {

      if ("Y".equals(this.getDirecttype(vo))) {
        PraybillHeaderVO head = vo.getHVO();
        head.setBdirecttransit(UFBoolean.TRUE);
      }
      else {
        PraybillHeaderVO head = vo.getHVO();
        head.setBdirecttransit(UFBoolean.FALSE);
      }
    }
  }
}
