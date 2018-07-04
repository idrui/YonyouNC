/**
 * $文件说明$
 *
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-11 上午10:30:11
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>删除费用行
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-8-11 上午10:30:11
 */
public class CostFactorDelLineAction extends DelLineAction {

  private static final long serialVersionUID = 2729749141685478384L;

  private ICostFactorQueryService costQuerySrv = null;

  /**
   * 父类方法重写
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
                                                                   * "没有选中成本要素行！"
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
                                                                   * "没有选中存货行！"
                                                                   */);
    }
    // 2011-12-20 tianft 需求建议，表体为空也可以保存
    // if (this.getListEditor().getBillListPanel().getBodyBillModel()
    // .getRowCount() == 1) {
    // MessageDialog.showErrorDlg(this.getListEditor(),
    // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC001-0000013")/*@res
    // "删行"*/,
    // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0002")/*@res
    // "表体行不能为空！"*/);
    // return;
    // }

    if (this.beReferenced()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0003")/*
                                                                   * @res
                                                                   * "该存货已进入结算，不能删除！"
                                                                   */);
    }
    this.getListEditor().getBillListPanel().getBodyBillModel()
        .delLine(nDelRows);
  }

  /**
   * 方法功能描述：费用项是否被引用
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-8-11 上午11:19:20
   */
  private boolean beReferenced() {
    int delRow =
        this.getListEditor().getBillListPanel().getBodyTable().getSelectedRow();
    int nState =
        this.getListEditor().getBillListPanel().getBodyBillModel()
            .getRowState(delRow);
    // 新增行、修改行不判断
    if (nState == BillModel.ADD || nState == BillModel.MODIFICATION) {
      return false;
    }
    String pk_org =
        ((CostfactorVO) this.getModel().getSelectedData()).getParentVO()
            .getPk_org();
    Object pk_srcmaterial =
        this.getListEditor().getBillListPanel().getBodyBillModel()
            .getValueAt(delRow, CostfactorItemVO.PK_SRCMATERIAL);
    // 组织或费用为空，不判断
    if (StringUtils.isEmpty(pk_org) || pk_srcmaterial == null
        || StringUtils.isEmpty(pk_srcmaterial.toString())) {
      return false;
    }
    String pk_group =
        ((CostfactorVO) this.getModel().getSelectedData()).getParentVO()
            .getPk_group();
    try {
      // 查询是否被暂估、结算引用
      return this.getCostQuerySrv().beReferenced(pk_group, pk_org,
          pk_srcmaterial.toString());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);

    }
    return false;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-8-11 上午11:19:27
   */
  private ICostFactorQueryService getCostQuerySrv() {
    if (this.costQuerySrv == null) {
      this.costQuerySrv =
          NCLocator.getInstance().lookup(ICostFactorQueryService.class);
    }
    return this.costQuerySrv;
  }

}
