package nc.impl.pu.costfactor.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.pu.costfactor.maintain.UpdateBP;
import nc.bs.uif2.validation.IValidationService;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFrameworkUtil;
import nc.impl.pu.costfactor.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.costfactor.entity.CostfactorHeaderVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.costfactor.validate.CostFactorUniqueRuleValidate;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

/**
 * 成本要素定义修改保存动作。
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>成本要素定义修改保存
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 下午03:08:06
 */
public class UpdateAction {

  /**
   * 方法功能描述：成本要素定义修改保存动作入口方法。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   *          需要保存的成本要素定义数组
   * @return 保存后的成本要素定义数组，轻量级VO
   */
  public CostfactorVO[] update(CostfactorVO[] bills) {
    TimeLog.logStart();
    BillTransferTool<CostfactorVO> transferTool =
        new BillTransferTool<CostfactorVO>(bills);
    CostfactorVO[] clientBills = transferTool.getClientFullInfoBill();
    CostfactorVO[] originBills = transferTool.getOriginBills();
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0103")/* @res "补全前台VO、获得修改前VO、加锁、检查时间戳" */);

    TimeLog.logStart();
    this.checkSrcTs(clientBills, originBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0104")/* @res "检查参照5A的时间戳、锁来源" */);

    CompareAroundProcesser<CostfactorVO> compareProcesser =
        new CompareAroundProcesser<CostfactorVO>(ActionPlugInPoint.UpdateAction);

    TimeLog.logStart();
    compareProcesser.before(clientBills, originBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0105")/* @res "调用更新保存BP前执行业务规则" */);

    this.validate(clientBills);

    UpdateBP action = new UpdateBP();
    CostfactorVO[] ret = action.update(clientBills, originBills);

    TimeLog.logStart();
    compareProcesser.after(ret, originBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0106")/* @res "调用更新保存BP后执行业务规则" */);

    TimeLog.logStart();
    // ret = transferTool.getBillForToClient(ret);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0102")/* @res "组织返回值,返回轻量级VO" */);

    TimeLog.logStart();
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0097")/* @res "业务日志" */);

    // 2012-04-26 tianft 秦发项目：重新查一下，同步表体ts
    return this.queryFromDb(ret);
  }

  private void checkSrcTs(IBill[] bills, IBill[] originBills) {
    ISuperVO[] lBbills = new ISuperVO[bills.length];
    ISuperVO[] lOriginBills = new ISuperVO[bills.length];
    for (int i = 0; i < bills.length; i++) {
      lBbills[i] = bills[i].getParent();
      lOriginBills[i] = originBills[i].getParent();

    }
    new VOConcurrentTool().checkTS(lBbills, lOriginBills);
  }

  private CostfactorVO[] queryFromDb(CostfactorVO[] bills) {
    BillQuery<CostfactorVO> query =
        new BillQuery<CostfactorVO>(CostfactorVO.class);
    Set<String> pk_cost = new HashSet<String>();
    for (CostfactorVO vo : bills) {
      pk_cost.add(vo.getParentVO().getPk_costfactor());
    }
    CostfactorVO[] queryVos =
        query.query(pk_cost.toArray(new String[pk_cost.size()]));
    Map<String, CostfactorVO> voMap = new HashMap<String, CostfactorVO>();
    // 此处要保证与原vo的顺序，因为到前台要处理差异用，顺序不一致会有问题
    for (CostfactorVO vo : queryVos) {
      voMap.put(vo.getParentVO().getPk_costfactor(), vo);
    }
    for (CostfactorVO vo : bills) {
      CostfactorVO tempVO = voMap.get(vo.getParentVO().getPk_costfactor());
      if (tempVO != null) {
        vo = tempVO;
      }
    }
    return bills;

  }

  private void validate(CostfactorVO[] bills) {
    if (null != bills && bills.length > 0) {
      CostfactorHeaderVO[] head = new CostfactorHeaderVO[bills.length];
      for (int i = 0, len = bills.length; i < len; i++) {
        head[i] = bills[i].getParentVO();
      }

      CostFactorUniqueRuleValidate ruleValidate =
          new CostFactorUniqueRuleValidate();
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
