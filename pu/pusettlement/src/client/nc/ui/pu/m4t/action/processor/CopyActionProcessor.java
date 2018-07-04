/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-9 ����04:10:38
 */
package nc.ui.pu.m4t.action.processor;

import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.pub.util.BusitypeSetter;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.enumeration.InitialEstStatus;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ڳ��ݹ������ƴ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-9 ����04:10:38
 */
public class CopyActionProcessor<E extends InitialEstVO> implements
    ICopyActionProcessor<E> {

  @Override
  public void processVOAfterCopy(InitialEstVO billVO, LoginContext context) {
    this.batchProc(billVO, context);
    this.setHeadValue(billVO, context);
    this.setItemValue(billVO);
  }

  private void batchProc(InitialEstVO vo, LoginContext context) {
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

  private void copyTranstype(InitialEstVO vo, String transtype,
      String pk_trantype) {
    // ҵ�����̣�����գ������Ჹ��
    vo.getHeader().setPk_busitype(null);
    if (StringUtils.isBlank(transtype)) {
      // �������ͣ��������ͣ�
      vo.getHeader().setVtrantypecode(null);
      // ��������
      vo.getHeader().setCtrantypeid(null);
    }
    else {
      // �������ͣ��������ͣ�
      vo.getHeader().setVtrantypecode(transtype);
      // ��������
      vo.getHeader().setCtrantypeid(pk_trantype);
    }
  }

  private void register_BizRule(List<IPURemoteCallCombinator> rccRuleLst,
      InitialEstVO vo, LoginContext context) {
    // ����������
    String transtype = TrantypeFuncUtils.getTrantype(context);
    String pk_trantype = TrantypeFuncUtils.getTrantypePk(context);
    this.copyTranstype(vo, transtype, pk_trantype);
    new BusitypeSetter(POBillType.InitEstimate,
        new BillHelper<InitialEstVO>(vo), context).copySet(rccRuleLst);
  }

  /**
   * ������������������ĳһ�����ֵ
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 ����04:23:57
   */
  private void setDelaultItemValue(InitialEstItemVO itemVO) {
    // ��������
    itemVO.setPk_initialest_b(null);
    // ��ͷ����
    itemVO.setPk_initialest(null);
    // ts
    itemVO.setTs(null);

    // ��Դ
    itemVO.setCsourcebid(null);
    itemVO.setCsourceid(null);
    itemVO.setCsourcetypecode(null);
    itemVO.setVsourcecode(null);
    itemVO.setVsourcerowno(null);
    itemVO.setVsourcetrantype(null);
    itemVO.setSourcebts(null);
    itemVO.setSourcets(null);
    // ��Դ����
    itemVO.setPk_order(null);
    itemVO.setPk_order_b(null);
    itemVO.setCorderrowno(null);
    itemVO.setVordercode(null);
    itemVO.setVordertrantype(null);

    // �ۼ�����
    itemVO.setNaccinvoicenum(null);
    itemVO.setNaccsettlenum(null);
    itemVO.setNaccwashmny(null);

    // �ݹ�Ӧ����־
    itemVO.setBestimateap(UFBoolean.FALSE);
    // ������
//    itemVO.setCffileid(null);
  }

  /**
   * �����������������ñ�ͷֵ
   * <p>
   * <b>����˵��</b>
   * 
   * @param billVO
   * @param context <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 ����04:12:47
   */
  private void setHeadValue(InitialEstVO billVO, LoginContext context) {
    if (null == billVO) {
      return;
    }

    InitialEstHeaderVO headVO = billVO.getHeader();
    if (null == headVO) {
      return;
    }

    // ����
    headVO.setPk_initialest(null);
    // ���뵥��
    headVO.setVbillcode(null);
    // ����״̬
    headVO.setFbillstatus((Integer) InitialEstStatus.FEE.value());
    // ������
    headVO.setCreator(context.getPk_loginUser());
    // �Ƶ���
    headVO.setBillmaker(context.getPk_loginUser());
    // �Ƶ�ʱ��
    headVO.setCreationtime(null);
    // ����޸���
    headVO.setModifier(context.getPk_loginUser());
    // ����޸�ʱ��
    headVO.setModifiedtime(null);
    // ������
    headVO.setApprover(null);
    // ����ʱ��
    headVO.setTaudittime(null);
    headVO.setTs(null);
    // �Ƶ�����
    headVO.setDmakedate(null);
  }

  /**
   * �����������������ñ���ֵ
   * <p>
   * <b>����˵��</b>
   * 
   * @param billVO <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 ����04:12:50
   */
  private void setItemValue(InitialEstVO billVO) {
    if (null == billVO) {
      return;
    }

    InitialEstItemVO[] itemVOs = billVO.getItems();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return;
    }

    for (InitialEstItemVO itemVO : itemVOs) {
      // ȥ��Ӱ�����ı�־
      itemVO.setBaffectpccost(null);
      this.setDelaultItemValue(itemVO);
    }
  }
}
