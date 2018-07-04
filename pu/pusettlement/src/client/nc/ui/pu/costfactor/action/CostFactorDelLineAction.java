/**
 * $�ļ�˵��$
 *
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-11 ����10:30:11
 */
package nc.ui.pu.costfactor.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.costfactor.ICostFactorQueryService;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.actions.billlist.DelLineAction;
import nc.ui.uif2.UIState;
import nc.vo.pu.costfactor.entity.CostfactorItemVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ɾ��������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-8-11 ����10:30:11
 */
public class CostFactorDelLineAction extends DelLineAction {

  private static final long serialVersionUID = 2729749141685478384L;

  private ICostFactorQueryService costQuerySrv = null;

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.billlist.DelLineAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (this.getModel().getSelectedRow() < 0
        && UIState.EDIT == this.getModel().getUiState()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0000")/*
                                                                   * @res
                                                                   * "û��ѡ�гɱ�Ҫ���У�"
                                                                   */);
    }

    int nDelRows[] =
        this.getListEditor().getBillListPanel().getBodyTable()
            .getSelectedRows();
    if (nDelRows == null
        || nDelRows.length == 0
        || nDelRows.length > this.getListEditor().getBillListPanel()
            .getBodyTable().getRowCount()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0001")/*
                                                                   * @res
                                                                   * "û��ѡ�д���У�"
                                                                   */);
    }
    // 2011-12-20 tianft �����飬����Ϊ��Ҳ���Ա���
    // if (this.getListEditor().getBillListPanel().getBodyBillModel()
    // .getRowCount() == 1) {
    // MessageDialog.showErrorDlg(this.getListEditor(),
    // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC001-0000013")/*@res
    // "ɾ��"*/,
    // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0002")/*@res
    // "�����в���Ϊ�գ�"*/);
    // return;
    // }

    if (this.beReferenced()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0003")/*
                                                                   * @res
                                                                   * "�ô���ѽ�����㣬����ɾ����"
                                                                   */);
    }
    this.getListEditor().getBillListPanel().getBodyBillModel()
        .delLine(nDelRows);
  }

  /**
   * ���������������������Ƿ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-8-11 ����11:19:20
   */
  private boolean beReferenced() {
    int delRow =
        this.getListEditor().getBillListPanel().getBodyTable().getSelectedRow();
    int nState =
        this.getListEditor().getBillListPanel().getBodyBillModel()
            .getRowState(delRow);
    // �����С��޸��в��ж�
    if (nState == BillModel.ADD || nState == BillModel.MODIFICATION) {
      return false;
    }
    String pk_org =
        ((CostfactorVO) this.getModel().getSelectedData()).getParentVO()
            .getPk_org();
    Object pk_srcmaterial =
        this.getListEditor().getBillListPanel().getBodyBillModel()
            .getValueAt(delRow, CostfactorItemVO.PK_SRCMATERIAL);
    // ��֯�����Ϊ�գ����ж�
    if (StringUtils.isEmpty(pk_org) || pk_srcmaterial == null
        || StringUtils.isEmpty(pk_srcmaterial.toString())) {
      return false;
    }
    String pk_group =
        ((CostfactorVO) this.getModel().getSelectedData()).getParentVO()
            .getPk_group();
    try {
      // ��ѯ�Ƿ��ݹ�����������
      return this.getCostQuerySrv().beReferenced(pk_group, pk_org,
          pk_srcmaterial.toString());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);

    }
    return false;
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-8-11 ����11:19:27
   */
  private ICostFactorQueryService getCostQuerySrv() {
    if (this.costQuerySrv == null) {
      this.costQuerySrv =
          NCLocator.getInstance().lookup(ICostFactorQueryService.class);
    }
    return this.costQuerySrv;
  }

}
