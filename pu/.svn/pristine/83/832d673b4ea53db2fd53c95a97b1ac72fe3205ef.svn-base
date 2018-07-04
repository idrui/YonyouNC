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
 * @version 2011-1-26 下午12:06:52
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
    // 表头自定义项可编辑设置
    BillItem[] bihs =
        ((BillForm) this.editor).getBillCardPanel().getHeadItems();
    for (BillItem bi : bihs) {
      // 自定义项配置可编辑
      if (bi.getKey().contains("vhdef") && bi.isEdit()) {
        bi.setEnabled(true);
      }
    }

    BillItem[] bibs =
        ((BillForm) this.editor).getBillCardPanel().getBodyItems();
    // 对方订单号、对方订单行号、确认数量、确认日期
    for (BillItem bi : bibs) {
      // 自定义项走模板配置
      // 发货单号
      if (bi.getKey().equals(OrderOnwayItemVO.VBILLCODE)
      // 本次发货数量
          || bi.getKey().equals(OrderOnwayItemVO.NSENDOUTNUM)
          // 单据日期
          || bi.getKey().equals(OrderOnwayItemVO.DBILLDATE)
          // 计划到货日期
          || bi.getKey().equals(OrderOnwayItemVO.DPLANARRVDATE)
          // 装船港口
          || bi.getKey().equals(OrderOnwayItemVO.CLOADPORT)
          // 到货港口
          || bi.getKey().equals(OrderOnwayItemVO.CLANDPORT)
          // 船名
          || bi.getKey().equals(OrderOnwayItemVO.CSHIPNAME)
          // 船次号
          || bi.getKey().equals(OrderOnwayItemVO.CSHIPLINE)
          // 计划到港日期
          || bi.getKey().equals(OrderOnwayItemVO.DPLANFREIGHTDATE)
          // 货柜号
          || bi.getKey().equals(OrderOnwayItemVO.CCASECODE)
          // 承运商
          || bi.getKey().equals(OrderOnwayItemVO.CCARRIER)
          // 自定义项
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
    // 设置查询的显示组织
    // cardpanel.setHeadItem(OrderHeaderVO.PK_ORG, pk_org);

    // 加载公式和相关项
    BillModel bm = cardpanel.getBillModel();
    bm.loadLoadRelationItemValue();
    bm.execLoadFormula();

    // 设置字段不可编辑,在确认时可以编辑,反确认时不可编辑
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
    // 重新定义焦点
    cardpanel.requestFocus();
  }

  /**
   * 方法功能描述：取得在途状态的int
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-8 上午10:53:28
   */
  protected abstract int getOnwayStatusInt();

  /**
   * 方法功能描述：取得在途状态的字符串
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-8 上午10:52:28
   */
  protected abstract String getOnwayStatusStr();

  protected void setHiddenItem(BillCardPanel cardpanel, boolean isDone) {
    // 已发货数量
    BillItem ownumItem = cardpanel.getBodyItem("nonwaynum");
    // ownumItem.setShareTableCode("pk_order_b");
    ownumItem.setShow(isDone);
    // 已发货金额
    BillItem owmnyItem = cardpanel.getBodyItem("nonwaymny");
    // owmnyItem.setShareTableCode("pk_order_b");
    owmnyItem.setShow(isDone);
  }
}
