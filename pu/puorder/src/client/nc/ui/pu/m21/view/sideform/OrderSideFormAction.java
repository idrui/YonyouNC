package nc.ui.pu.m21.view.sideform;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.scmbd.tpa.SCMTpaAction;
import nc.ui.uif2.DefaultExceptionHanler;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>处理"显示当前存量"的超链
 * </ul>
 * <p>
 * 
 * @since 6.0
 * @version 2011-4-20 上午08:33:38
 * @author xihy1
 */

public class OrderSideFormAction extends SCMTpaAction {

  private static final long serialVersionUID = 822404501823361654L;

  private OrderSideForm sideForm;
  
  private String nameValue;
  
  private String descripValue;

  public OrderSideFormAction(OrderSideForm sideForm, LoginContext context, String[] value) {
    super();
    this.initAction(value);
    this.sideForm = sideForm;
    this.setContext(context);
    this.setExceptionHandler();
  }

  private void initAction(String[] value) {
    this.putValue(Action.NAME, value[0]);
    this.putValue(Action.SHORT_DESCRIPTION, value[1]);
  }

  private void setExceptionHandler() {
    DefaultExceptionHanler handler = new DefaultExceptionHanler();
    handler.setContext(this.getContext());
    this.exceptionHandler = handler;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (null == this.sideForm.getSfMediator()
        || this.sideForm.getSfMediator().getBillCardPanel() == null
        || this.sideForm.getSfMediator().getCurrentRow() < 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0083")/*
                                                                   * @res
                                                                   * "请选一个订单行！"
                                                                   */);
    }
    this.sideForm.getSfMediator().display(this.sideForm);
  }

  public void initAction() {
    this.putValue(Action.NAME, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004030_0", "04004030-0087")/* @res "查看当前存量" */);
    this.putValue(Action.SHORT_DESCRIPTION, nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004030_0", "04004030-0088")/*
                                                                 * @res
                                                                 * " 查看当前选中的行的现存量"
                                                                 */);
  }

  public String getNameValue() {
    return this.nameValue;
  }

  public void setNameValue(String nameValue) {
    this.nameValue = nameValue;
  }

  public String getDescripValue() {
    return this.descripValue;
  }

  public void setDescripValue(String descripValue) {
    this.descripValue = descripValue;
  }

}
