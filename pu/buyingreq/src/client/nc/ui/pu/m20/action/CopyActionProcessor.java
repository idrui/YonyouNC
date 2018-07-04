package nc.ui.pu.m20.action;

import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.enumeration.EnumPraySource;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����ActionProcessor
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-13 ����06:06:48
 */
public class CopyActionProcessor<E extends PraybillVO> implements
    ICopyActionProcessor<E> {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor#processVOAfterCopy(nc.vo.pubapp.pattern.model.entity.bill.AbstractBill,
   *      nc.vo.uif2.LoginContext)
   */
  @Override
  public void processVOAfterCopy(PraybillVO billVO, LoginContext context) {
    // �趨��ͷ
    this.setHeadValue(billVO, context);

    // �趨����
    this.setBodyValue(billVO);

  }

  /**
   * �趨����ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param billVO
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-13 ����06:07:59
   */
  private void setBodyValue(PraybillVO billVO) {
    PraybillItemVO[] itemVOs = billVO.getBVO();

    for (int i = 0; i < itemVOs.length; ++i) {
      // �빺����
      itemVOs[i].setPk_praybill_b(null);
      // �빺����ͷ_����
      itemVOs[i].setPk_praybill(null);
      // �ۼƶ�������
      itemVOs[i].setNaccumulatenum(null);
      // ���ɺ�ͬ����
      itemVOs[i].setNgenct(null);
      // ���ɼ۸�����������
      itemVOs[i].setNpriceauditbill(null);
      // ����ѯ���۵�����
      itemVOs[i].setNquotebill(null);
      // �йر�
      itemVOs[i].setBrowclose(UFBoolean.FALSE);
      // ��������������
      itemVOs[i].setBpublishtoec(UFBoolean.FALSE);
      // ��Դ���ݺ�
      itemVOs[i].setVsourcecode(null);
      // ��Դ����ID
      itemVOs[i].setCsourceid(null);
      // ��Դ������ID
      itemVOs[i].setCsourcebid(null);
      // ��Դ��������
      itemVOs[i].setCsourcetypecode(null);
      // ��Դ��������
      itemVOs[i].setVsrctrantypecode(null);
      // ��Դ�����к�
      itemVOs[i].setVsourcerowno(null);
      // Դͷ����ID
      itemVOs[i].setCfirstid(null);
      // �ϲ���Դ������ID
      itemVOs[i].setCfirstbid(null);
      // Դͷ��������
      itemVOs[i].setCfirsttypecode(null);
      // Դͷ���ݺ�
      itemVOs[i].setVfirstcode(null);
      // Դͷ�����к�
      itemVOs[i].setVfirstrowno(null);
      // Դͷ��������
      itemVOs[i].setVfirsttrantype(null);
      // ts
      itemVOs[i].setTs(null);
      // ��������������
      itemVOs[i].setBisgensaorder(UFBoolean.FALSE);
      // �빺����
      itemVOs[i].setBisarrange(UFBoolean.FALSE);
      // �빺����
      UFDate busidate = AppContext.getInstance().getBusiDate();
      itemVOs[i].setDbilldate(busidate);
      // ������
//      itemVOs[i].setCffileid(null);
    }
  }

  /**
   * �趨��ͷֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param billVO
   *          �빺��
   * @param context
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-13 ����06:07:40
   */
  private void setHeadValue(PraybillVO billVO, LoginContext context) {
    PraybillHeaderVO headerVO = billVO.getHVO();

    // �빺��
    headerVO.setPk_praybill(null);
    // ֱ��
    headerVO.setBdirecttransit(null);
    // �빺�����
    headerVO.setVbillcode(null);
    // �빺������
    UFDate busidate = AppContext.getInstance().getBusiDate();
    headerVO.setDbilldate(busidate);
    // �빺����Դ
    headerVO.setFpraysource(EnumPraySource.MANUAL.toInteger());
    // �빺��״̬
    headerVO.setFbillstatus(POEnumBillStatus.FREE.toInteger());
    // �汾��Ϣ
    headerVO.setNversion(Integer.valueOf(1));
    // ��ӡ����
    headerVO.setIprintcount(null);
    // ts
    headerVO.setTs(null);
    // �Ƶ���
    headerVO.setBillmaker(context.getPk_loginUser());
    // ����ʱ��
    headerVO.setCreationtime(null);
    // �޸���
    headerVO.setModifier(null);
    // �޸�ʱ��
    headerVO.setModifiedtime(null);
    // �޶���
    headerVO.setCreviseoperid(null);
    // �޶�ʱ��
    headerVO.setTrevisiontime(null);
    // ������
    headerVO.setApprover(null);
    // ����ʱ��
    headerVO.setTaudittime(null);
    // �빺����
    headerVO.setDmakedate(null);
  }

}
