/**
 * $文件说明$
 *
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-26 下午04:22:14
 */
package nc.pubimpl.pu.m20.mm.action;

import nc.bs.pu.m20.maintain.PraybillInsertBP;
import nc.bs.pu.m20.maintain.rule.pub.ChangeRateRule;
import nc.bs.pu.m20.maintain.rule.pub.SetPraytypeRule;
import nc.bs.pu.m20.maintain.rule.pub.SetPsnAndDeptRule;
import nc.bs.pu.m20.maintain.rule.pub.SetSctypeRule;
import nc.impl.pu.m20.rule.ChkPrayVONotNullRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.pu.m20.plugin.ActionPlugInPoint;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.BDirectRule;
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
import nc.vo.pu.m20.rule.margin.PrayBillMarginRule;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.scmpub.res.billtype.MMBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计划订单推式保存请购单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-26 下午04:22:14
 */
public class PushSave20For55B4Action {
  public void pushSave(PraybillVO[] praybills) {
    if (ArrayUtils.isEmpty(praybills)) {
      return;
    }
    TimeLog.logStart();
    AroundProcesser<PraybillVO> processer =
        new AroundProcesser<PraybillVO>(ActionPlugInPoint.PushSave20ForA1Action);
    // 加入Action中的执行前/后规则
    this.addBeforeRule(processer);

    // 直运处理
    new BDirectRule().process(praybills);
    // 分单处理
    PraybillVO[] retvos = new SetSctypeRule().process(praybills);

    processer.before(retvos);
    PraybillVO[] savedVOs = new PraybillInsertBP().insert(praybills);
    processer.after(savedVOs);

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
    processer.addBeforeRule(new PrayBillMarginRule(this.getBillCode(), null));
  }

  protected String getBillCode() {
    return MMBillType.PlanOrder.getCode();

  }
}
