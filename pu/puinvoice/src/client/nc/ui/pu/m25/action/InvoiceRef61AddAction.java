/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-27 下午04:55:29
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;


/**
 * 采购发票参照委外订单
 * 
 * @since 6.0
 * @version 2010-11-11 上午10:58:10
 * @author tianft
 */
public class InvoiceRef61AddAction extends InvoiceRefAddAction {

  private static final long serialVersionUID = 1089453135128707152L;
  
  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isSCEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004050_0", "04004050-0138")/*
                                                                   * @res
                                                                   * "委外模块没有启用，不能参照委外订单！"
                                                                   */);
      return;
    }
    super.doAction(e);
  }

}
