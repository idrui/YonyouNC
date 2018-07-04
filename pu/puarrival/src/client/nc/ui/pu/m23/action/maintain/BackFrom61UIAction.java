package nc.ui.pu.m23.action.maintain;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pu.m23.editor.card.utils.BackReasonEditUtil;
import nc.ui.pu.pub.util.PuVDefFilterUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������ ί�ⶩ���˻� ��ť������Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 ����02:15:12
 */
public class BackFrom61UIAction extends AbstractReferenceAction {

  private static final long serialVersionUID = -2558867775704561245L;

  private AbstractAppModel model = null;

  public BackFrom61UIAction() {
    super();
    this.setSourceBillName(SCBillType.Order.getName());
    this.setSourceBillType(SCBillType.Order.getCode());

    this.setCode("BackArriveFrom61");
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isSCEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0002")/*
                                                                   * @res
                                                                   * "ί��ӹ�ģ��δ����,�޷�ת��!"
                                                                   */);
    }
    PfUtilClient.childButtonClickedNew(this.createPfButtonClickContext());
    if (PfUtilClient.isCloseOK()) {
      AggregatedValueObject[] retObjArray = PfUtilClient.getRetVos();
      if (ArrayUtils.isEmpty(retObjArray)) {
        return;
      }

      ArriveVO[] retVOArray = (ArriveVO[]) retObjArray;
      // ���±�ͷ���Ƿ��˻���־
      retVOArray = this.updateBackFlag(retVOArray);
      this.getTransferViewProcessor().processBillTransfer(retVOArray);

      BillCardPanel card =
          this.getTransferViewProcessor().getBillForm().getBillCardPanel();
      // �����˻�����
      new BackReasonEditUtil(card).setEnabled();
      // �����༭�¼�
      String stockOrg = retVOArray[0].getHVO().getPk_org();
      this.model.fireEvent(new OrgChangedEvent(null, stockOrg));
      new PuVDefFilterUtil()
          .setOrgForDefRef(card, this.getModel().getContext());
    }
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

  private PfButtonClickContext createPfButtonClickContext() {
    PfButtonClickContext context = new PfButtonClickContext();
    context.setParent(this.getModel().getContext().getEntranceUI());
    context.setSrcBillType(this.getSourceBillType());
    context.setPk_group(this.getModel().getContext().getPk_group());
    context.setUserId(this.getModel().getContext().getPk_loginUser());
    // ����ýڵ����ɽ������ͷ����ģ���ô�������Ӧ�ô��������ͣ����򴫵�������
    String vtrantype =
        TrantypeFuncUtils.getTrantype(this.getModel().getContext());
    if (StringUtil.isEmptyWithTrim(vtrantype)) {
      context.setCurrBilltype(POBillType.Arrive.getCode());
    }
    else {
      context.setCurrBilltype(vtrantype);
    }
    context.setUserObj("RETURN");
    context.setSrcBillId(null);
    context.setBusiTypes(this.getBusitypes());
    // ����Ĳ�����ԭ�����õķ����ж����漰��ֻ���������һ�����ṹ�����������������¼ӵĲ���
    // ���εĽ������ͼ���
    context.setTransTypes(this.getTranstypes());
    // ��־�ڽ�������Ŀ�Ľ������ͷ���ʱ������Ŀ�Ľ������͵����ݣ�������������ֵ��1�����ݽӿڶ��壩��
    // 2�������������ã���-1�������ݽ������ͷ��飩
    context.setClassifyMode(PfButtonClickContext.ClassifyByBusiflow);
    return context;
  }

  /**
   * �����˻���־
   * 
   * @param voArray �˻���VO����
   * @return �˻���VO����
   */
  private ArriveVO[] updateBackFlag(ArriveVO[] voArray) {
    for (ArriveVO vo : voArray) {
      vo.getHVO().setBisback(UFBoolean.TRUE);
    }
    return voArray;
  }
}
