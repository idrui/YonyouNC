/**
 * $�ļ�˵��$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-12 ����08:43:13
 */
package nc.ui.pu.m21.action.orderclose;

import java.util.List;

import nc.ui.pu.m21.view.OrderCloseListView;
import nc.ui.pu.m21.view.OrderCloseOrgPanel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ������رղ�ѯ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-12 ����08:43:13
 */
public class CloseQueryAction extends DefaultQueryAction {

  private static final long serialVersionUID = 1313368043443341557L;

  private OrderCloseListView list;

  private OrderCloseOrgPanel orgPanel;

  public OrderCloseListView getList() {
    return this.list;
  }

  public OrderCloseOrgPanel getOrgPanel() {
    return this.orgPanel;
  }

  public void setList(OrderCloseListView list) {
    this.list = list;
  }

  public void setOrgPanel(OrderCloseOrgPanel orgPanel) {
    this.orgPanel = orgPanel;
  }

  /**
   * �����������
   * 
   * @param vos
   */
  // private void calNumAndMny(OrderCloseVO[] vos) {
  // for (OrderCloseVO vo : vos) {
  // // δ�����ԭ�ң�
  // if (vo.getNnopayorgmny() == null) {
  // vo.setNnopayorgmny(MathTool.sub(vo.getNorigtaxmny(),
  // vo.getNaccumpayorgmny()));
  // }
  // // �ɵ�������
  // if (vo.getNcanarrivenum() == null) {
  // vo.setNcanarrivenum(MathTool.sub(vo.getNnum(), vo.getNaccumarrvnum()));
  // }
  // // ���������
  // if (vo.getNcaninnum() == null) {
  // vo.setNcaninnum(MathTool.sub(vo.getNnum(), vo.getNaccumstorenum()));
  // }
  // // �ɿ�Ʊ����
  // if (vo.getNcaninvoicenum() == null) {
  // vo.setNcaninvoicenum(MathTool.sub(vo.getNnum(),
  // vo.getNaccuminvoicenum()));
  // }
  // }
  // }

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    super.executeQuery(queryScheme);
    // super.executeQuery(sqlWhere);
    // try {
    // // ��ȡ��ѯ����֯
    // String temp = "pk_org = '";
    // int beginIndex = sqlWhere.indexOf(temp);
    // int start = beginIndex + temp.length();
    // int end = sqlWhere.indexOf("'", start);
    // String pkOrg = sqlWhere.substring(start, end);
    // // ˢ������֯���
    // this.getOrgPanel().setPkOrg(pkOrg);
    // // ����̨�ű���ѯ����
    // OrderCloseVO[] vos =
    // NCLocator.getInstance().lookup(IOrderQuery.class)
    // .closeQuery(sqlWhere);
    // this.calNumAndMny(vos);
    // this.getModel().initModel(vos);
    //
    // // ((OrderCloseManageModel)
    // // this.getModel()).directlyUpdate(vos);//����Ӱ�쾫�ȣ�tianft
    //
    // // ���������
    // if (null != this.list) {
    // this.list.getBillListPanel().getHeadBillModel().execLoadFormula();
    // this.list.getBillListPanel().getHeadBillModel()
    // .loadLoadRelationItemValue();
    // }
    OrderCloseVO vo = (OrderCloseVO) this.getModel().getSelectedData();
    if (vo == null) {
      return;
    }
    List<?> voList = ((BillManageModel) this.getModel()).getData();
    OrderCloseVO[] vos =
        new ListToArrayTool<OrderCloseVO>(OrderCloseVO.class)
            .convertToArray((List<OrderCloseVO>) voList);
    String pk_org = vo.getPk_org();
    this.getOrgPanel().setPkOrg(pk_org);

    // ���������
    if (null != this.list) {
      this.list.getBillListPanel().getHeadBillModel().execLoadFormula();
      this.list.getBillListPanel().getHeadBillModel()
          .loadLoadRelationItemValue();
    }
    this.getModel().initModel(vos);

  }
}
