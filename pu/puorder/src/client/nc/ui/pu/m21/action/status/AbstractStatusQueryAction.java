package nc.ui.pu.m21.action.status;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.editor.IEditor;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * @since 6.0
 * @version 2011-1-26 ����12:06:52
 * @author wuxla
 */

public abstract class AbstractStatusQueryAction extends DefaultQueryAction {
  private static final long serialVersionUID = -2576560309254810327L;

  private IEditor editor;

  public IEditor getEditor() {
    return this.editor;
  }

  public void setEditor(IEditor editor) {
    this.editor = editor;
  }

  private void setItemsEnable(BillModel bm) {
    bm.setEnabled(true);
    // ��ͷ�Զ�����ɱ༭����
    BillItem[] bihs =
        ((BillForm) this.editor).getBillCardPanel().getHeadItems();
    for (BillItem bi : bihs) {
      // �Զ��������ÿɱ༭
      if (bi.getKey().contains("vhdef") && bi.isEdit()) {
        bi.setEnabled(true);
      }
    }

    BillItem[] bibs =
        ((BillForm) this.editor).getBillCardPanel().getBodyItems();
    // �Է������š��Է������кš�ȷ��������ȷ������
    for (BillItem bi : bibs) {
      // �Զ�������ģ������
      // ��������
      if (bi.getKey().equals(OrderOnwayItemVO.VBILLCODE)
      // ���η�������
          || bi.getKey().equals(OrderOnwayItemVO.NSENDOUTNUM)
          // ��������
          || bi.getKey().equals(OrderOnwayItemVO.DBILLDATE)
          // �ƻ���������
          || bi.getKey().equals(OrderOnwayItemVO.DPLANARRVDATE)
          // װ���ۿ�
          || bi.getKey().equals(OrderOnwayItemVO.CLOADPORT)
          // �����ۿ�
          || bi.getKey().equals(OrderOnwayItemVO.CLANDPORT)
          // ����
          || bi.getKey().equals(OrderOnwayItemVO.CSHIPNAME)
          // ���κ�
          || bi.getKey().equals(OrderOnwayItemVO.CSHIPLINE)
          // �ƻ���������
          || bi.getKey().equals(OrderOnwayItemVO.DPLANFREIGHTDATE)
          // �����
          || bi.getKey().equals(OrderOnwayItemVO.CCASECODE)
          // ������
          || bi.getKey().equals(OrderOnwayItemVO.CCARRIER)
          // �Զ�����
          || bi.getKey().contains("vbdef")) {
        continue;
      }
      bi.setEnabled(false);
    }
  }

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    QueryCondition outputCond =
        qrySchemeProcessor.getQueryCondition(this.getOnwayStatusStr());
    Object[] values = outputCond.getValues();
    UFBoolean output = UFBoolean.valueOf((String) values[0]);

    super.executeQuery(queryScheme);

    BillCardPanel cardpanel = ((BillForm) this.editor).getBillCardPanel();
    // ���ò�ѯ����ʾ��֯
    // cardpanel.setHeadItem(OrderHeaderVO.PK_ORG, pk_org);

    // ���ع�ʽ�������
    BillModel bm = cardpanel.getBillModel();
    bm.loadLoadRelationItemValue();
    bm.execLoadFormula();

    // �����ֶβ��ɱ༭,��ȷ��ʱ���Ա༭,��ȷ��ʱ���ɱ༭
    bm.setEnabled(false);

    if (!output.booleanValue()) {
      this.setItemsEnable(bm);
      this.setHiddenItem(cardpanel, true);
      // this.setOperate(false);
    }
    else {
      this.setHiddenItem(cardpanel, false);
      // this.setOperate(true);
    }

    // cardpanel.setBillData(cardpanel.getBillData());
    // ���¶��役��
    cardpanel.requestFocus();
  }

  /**
   * ��������������ȡ����;״̬��int
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-8 ����10:53:28
   */
  protected abstract int getOnwayStatusInt();

  /**
   * ��������������ȡ����;״̬���ַ���
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-8 ����10:52:28
   */
  protected abstract String getOnwayStatusStr();

  protected void setHiddenItem(BillCardPanel cardpanel, boolean isDone) {
    // �ѷ�������
    BillItem ownumItem = cardpanel.getBodyItem("nonwaynum");
    // ownumItem.setShareTableCode("pk_order_b");
    ownumItem.setShow(isDone);
    // �ѷ������
    BillItem owmnyItem = cardpanel.getBodyItem("nonwaymny");
    // owmnyItem.setShareTableCode("pk_order_b");
    owmnyItem.setShow(isDone);
  }
}
