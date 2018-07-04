/**
 * $文件说明$
 *
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-11 下午02:59:00
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m25.model.InvoiceBillManageModel;
import nc.ui.pu.m25.view.InvoiceBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>主要用于冻结解冻
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-11 下午02:59:00
 */
public class InvoiceNormalAction extends NCAction {

  private static final long serialVersionUID = -1937750105725637011L;

  private InvoiceBillForm editor = null;

  private String message = null;

  private InvoiceBillManageModel model = null;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    return;
  }

  /**
   * @return editor
   */
  public InvoiceBillForm getEditor() {
    return this.editor;
  }

  /**
   * @return message
   */
  public String getMessage() {
    return this.message;
  }

  public InvoiceBillManageModel getModel() {
    return this.model;
  }

  /**
   * @param editor
   *          要设置的 editor
   */
  public void setEditor(InvoiceBillForm editor) {
    this.editor = editor;
  }

  /**
   * @param message
   *          要设置的 message
   */
  public void setMessage(String message) {
    this.message = message;
  }

  public void setModel(InvoiceBillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  /**
   * 只要发票未冻结按钮就可用
   */
  protected boolean canBeFrozen(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return false;
    }
    // 批处理时，只要有未冻结的就可用
    for (InvoiceVO vo : vos) {
      if (vo.getParentVO().getBfrozen() == null
          || !vo.getParentVO().getBfrozen().booleanValue()) {
        return true;
      }
    }
    return false;
  }

  protected boolean canBeUnFrozen(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return false;
    }
    // 批处理时，只要有冻结的就可用
    for (InvoiceVO vo : vos) {
      if (vo.getParentVO().getBfrozen() != null
          && vo.getParentVO().getBfrozen().booleanValue()) {
        return true;
      }
    }
    return false;
  }

  protected InvoiceVO getCurrentVO() {
    if (null == this.getModel().getSelectedData()) {
      return null;
    }
    return (InvoiceVO) this.getModel().getSelectedData();
  }

  protected InvoiceVO[] getSelectedVOs() {
    Object[] vos = ((BillManageModel) this.model).getSelectedOperaDatas();
    if (vos == null) {
      return null;
    }
    InvoiceVO[] invoiceVOs = new InvoiceVO[vos.length];
    System.arraycopy(vos, 0, invoiceVOs, 0, vos.length);
    return invoiceVOs;
  }

  protected void processReturnObjs(Object[] objs) {
    try {
      ((BillManageModel) this.getModel()).directlyUpdate(objs);
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage(), e);
    }

  }

  protected void showMessage() {
    if (StringUtil.isEmptyWithTrim(this.message)) {
      return;
    }
    ExceptionUtils.wrappBusinessException(this.getMessage());
  }

}
