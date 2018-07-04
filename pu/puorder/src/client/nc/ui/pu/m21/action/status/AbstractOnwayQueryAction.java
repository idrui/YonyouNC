///**
// * $�ļ�˵��$
// * 
// * @author wanghuid
// * @version 6.0
// * @see
// * @since 6.0
// * @time 2010-8-17 ����04:35:38
// */
package nc.ui.pu.m21.action.status;

//
//import javax.swing.Action;
//
//import nc.ui.pub.bill.BillCardPanel;
//import nc.ui.pub.bill.BillItem;
//import nc.ui.pub.bill.BillModel;
//import nc.ui.pubapp.uif2app.view.BillForm;
//import nc.ui.scmpub.action.SCMQueryAction;
//import nc.ui.scmpub.query.SCMQueryConditionDlg;
//import nc.ui.uif2.editor.IEditor;
//import nc.vo.pu.m21.entity.OrderHeaderVO;
//import nc.vo.pu.m21.entity.OrderOnwayItemVO;
//import nc.vo.pub.BusinessRuntimeException;
//import nc.vo.pub.lang.UFBoolean;
//
///**
// * <p>
// * <b>������Ҫ������¹��ܣ�</b>
// * <ul>
// * <li>��;״̬��ѯ������
// * </ul>
// * <p>
// * <p>
// * 
// * @version 6.0
// * @since 6.0
// * @author wanghuid
// * @time 2010-8-17 ����04:35:38
// */
//public abstract class AbstractOnwayQueryAction extends SCMQueryAction {
//
//  private static final long serialVersionUID = 6421230924409458915L;
//
//  private IEditor editor = null;
//
//  private UFBoolean isOperate = UFBoolean.TRUE;
//
//  public AbstractOnwayQueryAction() {
//    super();
//    this.putValue(Action.SHORT_DESCRIPTION, "��ѯ(F3)");
//  }
//
//  public IEditor getEditor() {
//    return this.editor;
//  }
//
//  /**
//   * @return isOperate
//   */
//  public UFBoolean getOperate() {
//    return this.isOperate;
//  }
//
//  public void setEditor(IEditor editor) {
//    this.editor = editor;
//  }
//
//  public void setOperate(UFBoolean isDone) {
//    this.isOperate = isDone;
//  }
//
//  /**
//   * �����������������ÿɱ༭��
//   * <p>
//   * <b>����˵��</b>
//   * 
//   * @param bm
//   *          <p>
//   * @since 6.0
//   * @author wanghuid
//   * @time 2010-10-8 ����10:51:05
//   */
//  private void setItemsEnable(BillModel bm) {
//    bm.setEnabled(true);
//    // ��ͷ�Զ�����ɱ༭����
//    BillItem[] bihs =
//        ((BillForm) this.editor).getBillCardPanel().getHeadItems();
//    for (BillItem bi : bihs) {
//      // �Զ��������ÿɱ༭
//      if (bi.getKey().contains("vhdef") && bi.isEdit()) {
//        bi.setEnabled(true);
//      }
//    }
//
//    BillItem[] bibs =
//        ((BillForm) this.editor).getBillCardPanel().getBodyItems();
//    // �Է������š��Է������кš�ȷ��������ȷ������
//    for (BillItem bi : bibs) {
//      // �Զ�������ģ������
//      // ��������
//      if (bi.getKey().equals(OrderOnwayItemVO.VBILLCODE)
//      // ���η�������
//          || bi.getKey().equals(OrderOnwayItemVO.NSENDOUTNUM)
//          // ��������
//          || bi.getKey().equals(OrderOnwayItemVO.DBILLDATE)
//          // �ƻ���������
//          || bi.getKey().equals(OrderOnwayItemVO.DPLANARRVDATE)
//          // װ���ۿ�
//          || bi.getKey().equals(OrderOnwayItemVO.CLOADPORT)
//          // �����ۿ�
//          || bi.getKey().equals(OrderOnwayItemVO.CLANDPORT)
//          // ����
//          || bi.getKey().equals(OrderOnwayItemVO.CSHIPNAME)
//          // ���κ�
//          || bi.getKey().equals(OrderOnwayItemVO.CSHIPLINE)
//          // �ƻ���������
//          || bi.getKey().equals(OrderOnwayItemVO.DPLANFREIGHTDATE)
//          // �����
//          || bi.getKey().equals(OrderOnwayItemVO.CCASECODE)
//          // ������
//          || bi.getKey().equals(OrderOnwayItemVO.CCARRIER)
//          // �Զ�����
//          || bi.getKey().contains("vbdef")) {
//        continue;
//      }
//      bi.setEnabled(false);
//    }
//  }
//
//  @Override
//  protected void executeQuery(String sqlWhere) {
//
//    try {
//      SCMQueryConditionDlg dlg = (SCMQueryConditionDlg) this.getQueryDlg();
//
//      this.isOperate =
//          StatusActionTools.getIsOperated(dlg, this.getOnwayStatusStr());
//
//      String pk_org = StatusActionTools.getpkorg(dlg);
//
//      // ȡ������sql
//      String sql =
//          StatusActionTools.getSql(sqlWhere, this.isOperate,
//              this.getOnwayStatusInt());
//
//      // ��ѯ����
//      super.executeQuery(sql);
//
//      BillCardPanel cardpanel = ((BillForm) this.editor).getBillCardPanel();
//      // ���ò�ѯ����ʾ��֯
//      cardpanel.setHeadItem(OrderHeaderVO.PK_ORG, pk_org);
//
//      // ���ع�ʽ�������
//      BillModel bm = cardpanel.getBillModel();
//      bm.loadLoadRelationItemValue();
//      bm.execLoadFormula();
//
//      // �����ֶβ��ɱ༭,��ȷ��ʱ���Ա༭,��ȷ��ʱ���ɱ༭
//      bm.setEnabled(false);
//
//      if (!this.isOperate.booleanValue()) {
//        this.setItemsEnable(bm);
//        this.setHiddenItem(cardpanel, true);
//        // this.setOperate(false);
//      }
//      else {
//        this.setHiddenItem(cardpanel, false);
//        // this.setOperate(true);
//      }
//
//      cardpanel.setBillData(cardpanel.getBillData());
//      // ���¶��役��
//      cardpanel.requestFocus();
//    }
//
//    catch (Exception e) {
//      throw new BusinessRuntimeException("��ѯ���ݷ����쳣", e);
//    }
//  }
//
//  /**
//   * ��������������ȡ����;״̬��int
//   * <p>
//   * <b>����˵��</b>
//   * 
//   * @return <p>
//   * @since 6.0
//   * @author wanghuid
//   * @time 2010-10-8 ����10:53:28
//   */
//  protected abstract int getOnwayStatusInt();
//
//  /**
//   * ��������������ȡ����;״̬���ַ���
//   * <p>
//   * <b>����˵��</b>
//   * 
//   * @return <p>
//   * @since 6.0
//   * @author wanghuid
//   * @time 2010-10-8 ����10:52:28
//   */
//  protected abstract String getOnwayStatusStr();
//
//  /**
//   * �����������������ݲ�ѯ���Ƿ��Ѳ���������ҳ����ʾ��
//   * <p>
//   * <b>����˵��</b>
//   * 
//   * @param cardpanel
//   *          <p>
//   * @since 6.0
//   * @author wanghuid
//   * @time 2010-8-13 ����09:15:19
//   */
//  protected void setHiddenItem(BillCardPanel cardpanel, boolean isDone) {
//    // �ѷ�������
//    BillItem ownumItem = cardpanel.getBodyItem("nonwaynum");
//    // ownumItem.setShareTableCode("pk_order_b");
//    ownumItem.setShow(isDone);
//    // �ѷ������
//    BillItem owmnyItem = cardpanel.getBodyItem("nonwaymny");
//    // owmnyItem.setShareTableCode("pk_order_b");
//    owmnyItem.setShow(isDone);
//  }
// }
