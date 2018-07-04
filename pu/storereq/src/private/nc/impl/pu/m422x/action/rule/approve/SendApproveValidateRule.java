/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-26 ����07:07:24
 */
package nc.impl.pu.m422x.action.rule.approve;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

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
 * @author wuxla
 * @time 2010-7-26 ����07:07:24
 */
public class SendApproveValidateRule implements IRule<StoreReqAppVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(StoreReqAppVO[] vos) {
    this.statusCheck(vos);
  }

  private void statusCheck(StoreReqAppVO[] vos) {
    String userid = AppContext.getInstance().getPkUser();
    for (StoreReqAppVO vo : vos) {
      StoreReqAppHeaderVO header = vo.getHVO();
      if(!header.getBillmaker().equals(userid)){
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004000_0", "04004000-0158")/*
                                                                 * @res
                                                                 * "��ǰ�û������Ƶ��ˣ��������ύ��"
                                                                 */);
      }
      // ״̬�����ɵĲ����ύ
      if (!vo.getHVO().getFbillstatus().equals(POEnumBillStatus.FREE.value())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4004010_0", "04004010-0022", null, new String[]{vo.getHVO().getVbillcode()})/*����{0}��״̬����ȷ�������ύ��*/);
      }
    }
  }

}
