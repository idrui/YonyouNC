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
 * �ɱ�Ҫ�ض����޸ı��涯����
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɱ�Ҫ�ض����޸ı���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 ����03:08:06
 */
public class UpdateAction {

  /**
   * ���������������ɱ�Ҫ�ض����޸ı��涯����ڷ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param bills
   *          ��Ҫ����ĳɱ�Ҫ�ض�������
   * @return �����ĳɱ�Ҫ�ض������飬������VO
   */
  public CostfactorVO[] update(CostfactorVO[] bills) {
    TimeLog.logStart();
    BillTransferTool<CostfactorVO> transferTool =
        new BillTransferTool<CostfactorVO>(bills);
    CostfactorVO[] clientBills = transferTool.getClientFullInfoBill();
    CostfactorVO[] originBills = transferTool.getOriginBills();
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0103")/* @res "��ȫǰ̨VO������޸�ǰVO�����������ʱ���" */);

    TimeLog.logStart();
    this.checkSrcTs(clientBills, originBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0104")/* @res "������5A��ʱ���������Դ" */);

    CompareAroundProcesser<CostfactorVO> compareProcesser =
        new CompareAroundProcesser<CostfactorVO>(ActionPlugInPoint.UpdateAction);

    TimeLog.logStart();
    compareProcesser.before(clientBills, originBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0105")/* @res "���ø��±���BPǰִ��ҵ�����" */);

    this.validate(clientBills);

    UpdateBP action = new UpdateBP();
    CostfactorVO[] ret = action.update(clientBills, originBills);

    TimeLog.logStart();
    compareProcesser.after(ret, originBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0106")/* @res "���ø��±���BP��ִ��ҵ�����" */);

    TimeLog.logStart();
    // ret = transferTool.getBillForToClient(ret);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0102")/* @res "��֯����ֵ,����������VO" */);

    TimeLog.logStart();
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0097")/* @res "ҵ����־" */);

    // 2012-04-26 tianft �ط���Ŀ�����²�һ�£�ͬ������ts
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
    // �˴�Ҫ��֤��ԭvo��˳����Ϊ��ǰ̨Ҫ��������ã�˳��һ�»�������
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
        // ��־�쳣
        ExceptionUtils.wrappException(e);

      }
    }

  }
}
