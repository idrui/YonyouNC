package nc.pubitf.pu.m25;

import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * <b>Ϊ�ڲ����׽����ṩ�ķ�Ʊ��ѯ��,ͨ��UILocator����</b><br>
 * 
 * <pre>
 * ����ʾ����
 * IInvoiceForToQueryAction invoiceForToQueryAction =
 *     NCUILocator.getInstance().lookup(IInvoiceForToQueryAction.class, NCModule.PO);
 * 
 * </pre>
 * 
 * @since 6.0
 * @version 2011-1-24 ����01:53:44
 * @author �����
 */

public interface IInvoiceForToQueryAction {

  /**
   * @return
   */
  public IQueryScheme getCurQueryScheme(String pk_finaorg);

}
