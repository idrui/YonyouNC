package nc.impl.pu.m23.approve.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.wfengine.definition.WorkflowTypeEnum;

/**
 * 
 * @description <ul>
 *              <li>����Ƿ��������
 *              <li>��������Ѿ����󣬲�������
 *              <li>��������Ѿ������ɣ���������
 *              <li>�������������������������
 *              </ul>
 * @scene
 * ����������
 * @param ��
 * 
 * @since 6.3
 * @version 2010-1-19 ����09:34:01
 * @author hanbin
 */
public class ChkCanSendRule implements IRule<ArriveVO> {

  public ChkCanSendRule() {
    return;
  }

  @Override
  public void process(ArriveVO[] voArray) {
    for (ArriveVO aggVO : voArray) {
      this.chkCanSave(aggVO);
    }
  }

  /*
   * ����Ƿ��������
   */
  private void chkCanSave(ArriveVO aggVO) {
    String userid = AppContext.getInstance().getPkUser();
    ArriveHeaderVO header = aggVO.getHVO();
    if(!header.getBillmaker().equals(userid)){
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4004000_0", "04004000-0158")/*
                                                               * @res
                                                               * "��ǰ�û������Ƶ��ˣ��������ύ��"
                                                               */);
    }
    Integer fbillstatus = aggVO.getHVO().getFbillstatus();
    if (POEnumBillStatus.APPROVING.value().equals(fbillstatus)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0119")/*@res "�����������󣬲���������"*/);
    }

    if (POEnumBillStatus.APPROVE.value().equals(fbillstatus)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0120")/*@res "����������ˣ�����������"*/);
    }

    // û������������������������
    String trancode = header.getVtrantypecode();
    String org = header.getPk_org();
    if (!PfServiceScmUtil.isExistWorkflowDefinition(trancode, org,
        header.getBillmaker(), WorkflowTypeEnum.Approveflow.getIntValue())) {
      ExceptionUtils.wrappBusinessException(header.getVbillcode()
          + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0121")/*@res "����û�п�������������!"*/);
    }
  }
}