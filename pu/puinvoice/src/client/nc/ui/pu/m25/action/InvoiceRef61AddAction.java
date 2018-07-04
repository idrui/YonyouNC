/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-27 ����04:55:29
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;


/**
 * �ɹ���Ʊ����ί�ⶩ��
 * 
 * @since 6.0
 * @version 2010-11-11 ����10:58:10
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
                                                                   * "ί��ģ��û�����ã����ܲ���ί�ⶩ����"
                                                                   */);
      return;
    }
    super.doAction(e);
  }

}
