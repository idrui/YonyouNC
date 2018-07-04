/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-31 ����09:02:24
 */
package nc.ui.pu.m21.action.processor;

import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.pub.util.BusitypeSetter;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

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
 * @time 2010-3-31 ����09:02:24
 */
public class CopyActionProcessor<E extends OrderVO> implements
    ICopyActionProcessor<E> {

  @Override
  public void processVOAfterCopy(OrderVO billVO, LoginContext context) {
		// ������
		this.batchProc(billVO, context);
		// �趨��ͷ
		this.setHeadValue(billVO, context);

		// �趨����
		this.setBodyValue(billVO);

		// ���ø���Э��ҳǩ����
		this.setBodyPaymentValue(billVO);
  }
  
	private void setBodyPaymentValue(OrderVO ordervo) {
		OrderPaymentVO[] vos = (OrderPaymentVO[]) ordervo
				.getChildren(OrderPaymentVO.class);
		 if (ArrayUtils.isEmpty(vos)) {
	      return;
	    }
		for (OrderPaymentVO vo : vos) {
		  if(vo==null) {
		    continue;
		  }
			vo.setPk_payment(null);
		}
	}

  private void batchProc(OrderVO vo, LoginContext context) {
    List<IPURemoteCallCombinator> rccRuleLst =
        new ArrayList<IPURemoteCallCombinator>();
    // ע��ִ��Զ�̵��ù��򣭽������ͼ�ҵ�����̴���
    this.register_BizRule(rccRuleLst, vo, context);
    // ִ��Զ�̵��úϲ�����
    for (IPURemoteCallCombinator rccRule : rccRuleLst) {
      if (null != rccRule) {
        rccRule.process();
      }
    }
  }

  private void copyTranstype(OrderVO vo, String transtype, String pk_trantype) {
    // ҵ�����̣�����գ������Ჹ��
    vo.getHVO().setPk_busitype(null);
    if (StringUtils.isBlank(transtype)) {
      // �������ͣ��������ͣ�
      vo.getHVO().setVtrantypecode(null);
      // ��������
      vo.getHVO().setCtrantypeid(null);
    }
    else {
      // �������ͣ��������ͣ�
      vo.getHVO().setVtrantypecode(transtype);
      // ��������
      vo.getHVO().setCtrantypeid(pk_trantype);
    }
  }

  private void register_BizRule(List<IPURemoteCallCombinator> rccRuleLst,
      OrderVO vo, LoginContext context) {
    // ����������
    String transtype = TrantypeFuncUtils.getTrantype(context);
    String pk_trantype = TrantypeFuncUtils.getTrantypePk(context);
    this.copyTranstype(vo, transtype, pk_trantype);
    new BusitypeSetter(POBillType.Order, new BillHelper<OrderVO>(vo), context)
        .copySet(rccRuleLst);
  }

  /**
   * ���������������趨����ֵ
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVOs
   * @param context <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-31 ����10:12:29
   */
  private void setBodyValue(OrderVO billVO) {
    if (null == billVO) {
      return;
    }

    OrderItemVO[] itemVOs = billVO.getBVO();

    if (ArrayUtils.isEmpty(itemVOs)) {
      return;
    }

    for (int i = 0; i < itemVOs.length; ++i) {
      // �ɹ�������ID
      itemVOs[i].setPk_order_b(null);
      // �ɹ�������ͷ_����
      itemVOs[i].setPk_order(null);
      // �ƻ��������ڣ���Action�н�����ֵ
      itemVOs[i].setDplanarrvdate(null);
      // �ۼƵ�������
      itemVOs[i].setNaccumarrvnum(null);
      // �ۼƷ�Ʊ����
      itemVOs[i].setNaccuminvoicenum(null);
      // �ۼƵ����ƻ�����
      itemVOs[i].setNaccumrpnum(null);
      // �ۼ��������
      itemVOs[i].setNaccumstorenum(null);
      // �ۼ�;������
      itemVOs[i].setNaccumwastnum(null);
      // �ۼ��˻�����
      itemVOs[i].setNbackarrvnum(null);
      // �ۼ��˿�����
      itemVOs[i].setNbackstorenum(null);
      // �ۼ���������
      itemVOs[i].setNaccumdevnum(null);
      // �ۼ��Ѻ������ҿ�Ʊ���
      itemVOs[i].setNacccancelinvmny(null);
      // �ۼƱ��ҿ�Ʊ���
      itemVOs[i].setNaccuminvoicemny(null);
      // �����ۼƿ�Ʊ���
      itemVOs[i].setNfeemny(null);
      // Ԥ������
      itemVOs[i].setNsuprsnum(null);
      // �Ƿ�����ر�
      itemVOs[i].setBtransclosed(UFBoolean.FALSE);
      // �����ر�
      itemVOs[i].setBtransclosed(UFBoolean.FALSE);
      // �Ƿ�����ر�
      itemVOs[i].setBtransclosed(UFBoolean.FALSE);
      // �����ر�
      itemVOs[i].setBarriveclose(UFBoolean.FALSE);
      // ��Ʊ�ر�
      itemVOs[i].setBinvoiceclose(UFBoolean.FALSE);
      // ����ر�
      itemVOs[i].setBpayclose(UFBoolean.FALSE);
      // ���ر�
      itemVOs[i].setBstockclose(UFBoolean.FALSE);
      // ȷ������
      itemVOs[i].setNconfirmnum(null);
      // ȷ������
      itemVOs[i].setDconfirmdate(null);
      // ��������
      itemVOs[i].setDcorrectdate(null);
      // �Է�������
      itemVOs[i].setVvendorordercode(null);
      // �Է������к�
      itemVOs[i].setVvendororderrow(null);
      // ����ԱID
      itemVOs[i].setChandler(null);
      // ��Դ���ݺ�
      itemVOs[i].setVsourcecode(null);
      // ��Դ����ID
      itemVOs[i].setCsourceid(null);
      // ��Դ������ID
      itemVOs[i].setCsourcebid(null);
      // ��Դ��������
      itemVOs[i].setCsourcetypecode(null);
      // ��Դ��������
      itemVOs[i].setVsourcetrantype(null);
      // ��Դ�����к�
      itemVOs[i].setVsourcerowno(null);
      // Դͷ����ID
      itemVOs[i].setCfirstid(null);
      // Դͷ������ID
      itemVOs[i].setCfirstbid(null);
      // Դͷ��������
      itemVOs[i].setCfirsttypecode(null);
      // Դͷ���ݺ�
      itemVOs[i].setVfirstcode(null);
      // Դͷ�����к�
      itemVOs[i].setVfirstrowno(null);
      // Դͷ��������
      itemVOs[i].setVfirsttrantype(null);
      // �빺��
      itemVOs[i].setCpraybillbid(null);
      itemVOs[i].setCpraybillcode(null);
      itemVOs[i].setCpraybillhid(null);
      itemVOs[i].setCpraybillrowno(null);
      itemVOs[i].setCpraytypecode(null);
      // ��������
      itemVOs[i].setCecbillbid(null);
      itemVOs[i].setCecbillid(null);
      itemVOs[i].setCectypecode(null);
      itemVOs[i].setVecbillcode(null);
      // �۸���������
      itemVOs[i].setVpriceauditcode(null);
      // �۸�������ID
      itemVOs[i].setCpriceauditid(null);
      // �۸�����������ӱ�ID
      itemVOs[i].setCpriceaudit_bid(null);
      // �۸�������������ӱ�ID
      itemVOs[i].setCpriceaudit_bb1id(null);
      // ��ͬͷID
      itemVOs[i].setCcontractid(null);
      // ��ͬ��ID
      itemVOs[i].setCcontractrowid(null);
      // ��ͬ��
      itemVOs[i].setVcontractcode(null);
      // ts
      itemVOs[i].setTs(null);
      // sourcebts
      itemVOs[i].setSourcebts(null);
      // sourcets
      itemVOs[i].setSourcets(null);
      // ����������ƣ�ȥ��������״̬
      // ����״̬
      itemVOs[i].setFisactive((Integer) EnumActive.ACTIVE.value());
      // ���ڵ����ƻ�
      itemVOs[i].setBreceiveplan(UFBoolean.FALSE);
      itemVOs[i].setNorigordernum(null);// ԭʼ��������
      itemVOs[i].setNorigorderprice(null);// ԭʼ��������
      itemVOs[i].setDorigplanarrvdate(null);// ԭʼ�ƻ���������
      itemVOs[i].setBborrowpur(UFBoolean.FALSE);// ����ת�ɹ�
      itemVOs[i].setVvendinventorycode(null);// ��Ӧ���ϱ���
      itemVOs[i].setVvendinventoryname(null);// ��Ӧ��������
      itemVOs[i].setFneedpurtype(null);
      itemVOs[i].setFactpurtype(null);
      itemVOs[i].setPk_cenpurule_b(null);
      itemVOs[i].setPk_discount(null);// �����ۿ۹������ᵼ���ڽ����޷��༭
      itemVOs[i].setIstorestatus(null);// ��Ӧ�̽���״̬��������չ�ֶΣ�����ջ�Ӱ�칩Ӧ���Ż�����Ϣ��
      itemVOs[i].setNsendplannum(null);
      itemVOs[i].setPk_schedule(null);
      itemVOs[i].setPk_schedule_b(null);
      itemVOs[i].setNaccumpickupnum(null);// �ۼƼ��������
      // ������
//      itemVOs[i].setCffileid(null);
    }
  }

  /**
   * ���������������趨��ͷֵ
   * <p>
   * <b>����˵��</b>
   * 
   * @param headerVO <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-31 ����09:25:50
   */
  private void setHeadValue(OrderVO billVO, LoginContext context) {
    if (null == billVO) {
      return;
    }

    OrderHeaderVO headerVO = billVO.getHVO();

    if (null == headerVO) {
      return;
    }

    // �ɹ�����ID
    headerVO.setPk_order(null);
    // �������
    headerVO.setVbillcode(null);
    // ԭ��Ԥ�����޶�
    headerVO.setNorgprepaylimit(null);
    // �Ƿ���Эͬ�������۶���
    headerVO.setBcooptoso(UFBoolean.FALSE);
    // �Ƿ������۶���Эͬ����
    headerVO.setBsocooptome(UFBoolean.FALSE);
    // �Է�������
    headerVO.setVcoopordercode(null);
    // ��������
    UFDate busidate = AppContext.getInstance().getBusiDate();
    headerVO.setDbilldate(busidate);
    // ����״̬
    headerVO.setForderstatus((Integer) POEnumBillStatus.FREE.value());
    // �汾��Ϣ
    headerVO.setNversion(Integer.valueOf(1));
    // �Ƿ����°汾
    headerVO.setBislatest(UFBoolean.TRUE);
    // ������־
    headerVO.setBisreplenish(UFBoolean.FALSE);
    // ��ӡ����
    headerVO.setIprintcount(null);
    // ���չر�
    headerVO.setBfinalclose(UFBoolean.FALSE);
    // �Ƿ񶳽�
    headerVO.setBfrozen(UFBoolean.FALSE);
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
    headerVO.setCrevisepsn(null);
    // �޶�ʱ��
    headerVO.setTrevisiontime(null);
    // ������
    headerVO.setApprover(null);
    // ����ʱ��
    headerVO.setTaudittime(null);
    // ������
    headerVO.setPk_freezepsndoc(null);
    // ����ʱ��
    headerVO.setTfreezetime(null);
    // ����ԭ��
    headerVO.setVfrozenreason(null);
    headerVO.setIrespstatus(null);
    headerVO.setBpublish(UFBoolean.FALSE);
    // ��������
    headerVO.setBreleasedover(null);
    // �Ƶ�����
    headerVO.setDmakedate(null);
    // ��Ӧ��
    headerVO.setPk_resppsn(null);
    // ��Ӧ����
    headerVO.setTresptime(null);
    // �ܾ�/�������
    headerVO.setVreason(null);
  }

}
