///**
// * $�ļ�˵��$
// * 
// * @author wanghuid
// * @version 6.0
// * @see
// * @since 6.0
// * @time 2010-9-1 ����08:59:26
// */
package nc.ui.pu.m21.view.onwayquerylistener;

//
//import nc.ui.pubapp.uif2app.query2.refregion.AbstractCommonListener;
//import nc.ui.querytemplate.CriteriaChangedEvent;
//import nc.ui.querytemplate.filter.IFilter;
//
///**
// * <p>
// * <b>������Ҫ������¹��ܣ�</b>
// * <ul>
// * <li>��;״̬��ѯ�Ի����¼�������
// * </ul>
// * <p>
// * <p>
// * 
// * @version 6.0
// * @since 6.0
// * @author wanghuid
// * @time 2010-9-1 ����08:59:26
// */
//public class OnwayQueryListener extends AbstractCommonListener {
//
//  private String status = "";
//
//  /**
//   * OnwayQueryListener �Ĺ�����
//   * 
//   * @param dlg
//   */
//  public OnwayQueryListener(SCMQueryConditionDlg dlg, String status) {
//    super(dlg);
//    this.status = status;
//  }
//
//  /**
//   * ���෽����д
//   * 
//   * @see nc.ui.querytemplate.ICriteriaChangedListener#criteriaChanged(nc.ui.querytemplate.CriteriaChangedEvent)
//   */
//  @Override
//  public void criteriaChanged(CriteriaChangedEvent event) {
//
//    if (event.getEventtype() == CriteriaChangedEvent.FILTER_CHANGED) {
//
//      if (event.getFieldCode().equals(this.status)) {
//        // ȡ�ÿ���Ԫ��
//        IFilter bissendoutFilter = event.getFilter();
//
//        OnwayQryListenTools listenTool = new OnwayQryListenTools();
//
//        listenTool.controlSealshow(bissendoutFilter, this.dlg);
//      }
//    }
//  }
// }
