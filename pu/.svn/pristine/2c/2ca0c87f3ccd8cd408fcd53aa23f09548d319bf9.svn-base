package nc.ui.pu.m23.query;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.pubapp.uif2app.query2.model.ModelDataManager;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.editor.BillListView;
import nc.ui.uif2.editor.IEditor;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.qc.pub.util.QCSysParamUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单 检验 查询按钮处理类Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author guizhw
 * @time 2011-10-11 下午02:15:12
 */
public class ArrivalCheckQueryAction extends DefaultQueryAction {

  private ModelDataManager dataManager = null;

  private IEditor editor = null;

  private BillListView list;

  // modified by fanly3 at 2013-07-19
  // 下边方法注释掉，主要是因为super.doAction()方法中会调用一次this.getQryDLGDelegator().getQueryScheme()
  // 而我们自己的doAction方法中要得到queryScheme对象，要么通过IQueryScheme queryScheme =
  // this.getDataManager().getQueryScheme();得到，
  // 要么通过IQueryScheme queryScheme =
  // this.getQryDLGDelegator().getQueryScheme();得到。然而通过前者得到的queryScheme对象可能为null，主要是异步执行导致dataManager对象中的queryScheme对象还没有赋值
  // 通过后者得到queryScheme对象，又会导致第一查询或是重复点击查询时，报解析日期函数错误，#day(0)#
  // 所以最后，解决办法是找pubapp郭婷，修改了DefaultQueryAction中的processQuery方法，提供了一个afterProcessQuery方法供业务组查询后操作使用
  // 这样使得业务组不需要自己再去查一遍queryScheme对象，直接使用即可。

  // @Override
  // public void doAction(ActionEvent e) throws Exception {
  // super.doAction(e);
  // String pk_org = "";
  // // IQueryScheme queryScheme = this.getDataManager().getQueryScheme();
  // // modified by fanly3 at 2013-07-17
  // // 上边注释得到的queryScheme有可能为null，主要是异步执行导致dataManager对象中的queryScheme对象还没有赋值
  // IQueryScheme queryScheme = this.getQryDLGDelegator().getQueryScheme();
  // if (null != queryScheme) {
  // QuerySchemeProcessor qrySchemeProcessor =
  // new QuerySchemeProcessor(queryScheme);
  //
  // QueryCondition orgqc =
  // qrySchemeProcessor.getQueryCondition(ArriveHeaderVO.PK_ORG);
  // String[] pk_orgvals = orgqc == null ? null : orgqc.getValues();
  // if (pk_orgvals != null && pk_orgvals.length != 0) {
  // pk_org = pk_orgvals[0];
  // }
  // if (!this.isQCEnabled(pk_org)) {
  // BillListPanel panel = this.list.getBillListPanel();
  // panel.showHeadTableCol(ArriveItemVO.NWILLELIGNUM);
  // panel.showHeadTableCol(ArriveItemVO.NWILLNOTELIGNUM);
  // panel.showHeadTableCol(ArriveItemVO.NCHECKNUM);
  //
  // BillModel bm = panel.getHeadBillModel();
  // this.setItemsEnable(bm);
  // }
  // else {
  // BillListPanel panel = this.list.getBillListPanel();
  // panel.hideHeadTableCol(ArriveItemVO.NWILLELIGNUM);
  // panel.hideHeadTableCol(ArriveItemVO.NWILLNOTELIGNUM);
  // panel.hideHeadTableCol(ArriveItemVO.NCHECKNUM);
  // }
  // }
  // }

  @Override
  protected void afterProcessQuery(IQueryScheme queryScheme) {
    String pk_org = "";
    if (null != queryScheme) {
      QuerySchemeProcessor qrySchemeProcessor =
          new QuerySchemeProcessor(queryScheme);

      QueryCondition orgqc =
          qrySchemeProcessor.getQueryCondition(ArriveHeaderVO.PK_ORG);
      String[] pk_orgvals = orgqc == null ? null : orgqc.getValues();
      if (pk_orgvals != null && pk_orgvals.length != 0) {
        pk_org = pk_orgvals[0];
      }
      if (!this.isQCEnabled(pk_org)) {
        BillListPanel panel = this.list.getBillListPanel();
        panel.showHeadTableCol(ArriveItemVO.NWILLELIGNUM);
        panel.showHeadTableCol(ArriveItemVO.NWILLNOTELIGNUM);
        panel.showHeadTableCol(ArriveItemVO.NCHECKNUM);

        BillModel bm = panel.getHeadBillModel();
        this.setItemsEnable(bm);
      }
      else {
        BillListPanel panel = this.list.getBillListPanel();
        panel.hideHeadTableCol(ArriveItemVO.NWILLELIGNUM);
        panel.hideHeadTableCol(ArriveItemVO.NWILLNOTELIGNUM);
        panel.hideHeadTableCol(ArriveItemVO.NCHECKNUM);
      }
    }
  }

  @Override
  public ModelDataManager getDataManager() {
    return this.dataManager;
  }

  public IEditor getEditor() {
    return this.editor;
  }

  public void setDataManager(ModelDataManager dataManager) {
    this.dataManager = dataManager;
  }

  public void setEditor(IEditor editor) {
    this.editor = editor;
  }

  public void setList(BillListView list) {
    this.list = list;
  }

  private boolean isQCEnabled(String pk_org) {
    return SysInitGroupQuery.isQCEnabled()
        && UFBoolean.TRUE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
            .getINI01(pk_org)));
  }

  private void setItemsEnable(BillModel bm) {
    bm.setEnabled(true);
    BillItem NWillElignum =
        this.list.getBillListPanel().getHeadItem(ArriveItemVO.NWILLELIGNUM);
    BillItem NChecknum =
        this.list.getBillListPanel().getHeadItem(ArriveItemVO.NCHECKNUM);
    NWillElignum.setEnabled(true);
    NChecknum.setEnabled(true);
  }
}
