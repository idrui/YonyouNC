/**
 * $�ļ�˵��$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-9 ����12:33:19
 */
package nc.impl.pu.m25.action.rule.sendapprove;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.wfengine.definition.WorkflowTypeEnum;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-9 ����12:33:19
 */
public class ApproveFlowCheckRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      InvoiceHeaderVO header = vo.getParentVO();
      if (!PfServiceScmUtil.isExistWorkflowDefinition(
          header.getVtrantypecode(), header.getPk_org(), header.getBillmaker(),
          WorkflowTypeEnum.Approveflow.getIntValue())) {
        ExceptionUtils.wrappBusinessException(header.getVbillcode()
            + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                "4004050_0", "04004050-0031")/* @res "����û�п�������������!" */);
      }
      // ״̬�����ɵĲ����ύ
      if (!vo.getParentVO().getFbillstatus()
          .equals(POEnumBillStatus.FREE.value())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0274", null, new String[] {
              vo.getParentVO().getVbillcode()
            })/* ����{0}��״̬����ȷ�������ύ�� */);
      }
    }
  }

}
