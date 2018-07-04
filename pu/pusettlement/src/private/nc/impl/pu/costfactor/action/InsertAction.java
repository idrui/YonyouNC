package nc.impl.pu.costfactor.action;

import nc.bs.pu.costfactor.maintain.InsertBP;
import nc.bs.uif2.validation.IValidationService;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFrameworkUtil;
import nc.impl.pu.costfactor.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.costfactor.entity.CostfactorHeaderVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.util.BDUniqueRuleValidate;

/**
 * 成本要素定义新增保存动作
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>成本要素定义新增保存
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 下午02:42:48
 */
public class InsertAction {
  /**
   * 方法功能描述：新增成本要素定义。
   * <p>
   * <b>参数说明</b>
   *
   * @param bills
   *          需要插入数据库的成本要素定义。
   * @return 保存后的成本要素定义
   * @since 6.0
   */
  public CostfactorVO[] insert(CostfactorVO[] bills) {
    TimeLog.logStart();
    new BillTransferTool<CostfactorVO>(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0098")/*@res "保存前台VO，组织返回值时使用"*/);

    AroundProcesser<CostfactorVO> processer =
        new AroundProcesser<CostfactorVO>(ActionPlugInPoint.InsertAction);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0099")/*@res "调用新增保存BP前执行业务规则"*/);

    this.validate(bills);
    TimeLog.logStart();
    InsertBP action = new InsertBP();
    CostfactorVO[] vos = action.insert(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0100")/*@res "调用新增保存BP，进行保存"*/);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0101")/*@res "调用新增保存BP后执行业务规则"*/);

    TimeLog.logStart();
    // vos = transferTool.getBillForToClient(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0102")/*@res "组织返回值,返回轻量级VO"*/);

    TimeLog.logStart();
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0097")/*@res "业务日志"*/);
    return vos;
  }

  private void validate(CostfactorVO[] bills) {
    if ((null != bills) && (bills.length > 0)) {
      CostfactorHeaderVO[] head = new CostfactorHeaderVO[bills.length];
      for (int i = 0, len = bills.length; i < len; i++) {
        head[i] = bills[i].getParentVO();
      }

      BDUniqueRuleValidate ruleValidate = new BDUniqueRuleValidate();
      IValidationService vService =
          ValidationFrameworkUtil.createValidationService(ruleValidate);
      try {
        vService.validate(head);
      }
      catch (ValidationException e) {
        // 日志异常
        ExceptionUtils.wrappException(e);

      }
    }

  }
}