/**
 * $�ļ�˵��$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-23 ����03:34:09
 */
package nc.ui.pu.m25.editor.utils;

import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ֵĹ�����,�����ڷ�Ʊ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-1 ����09:37:03
 */
public class CurrencyUtil {

  /**
   * �����������������ñ�ͷԭ�ұ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param util
   *          <p>
   * @since 6.0
   * @author tianft
   * @time 2010-3-31 ����04:54:38
   */
  public static void setOrigcurrency(CardEditorHelper util) {
    // ��Ӧ������
    String pk_supplier = util.getHeadStringValue(InvoiceHeaderVO.PK_SUPPLIER);
    // ��֯����
    String pk_org = util.getHeadStringValue(InvoiceHeaderVO.PK_ORG);
    // Ĭ�ϱ���
    String pk_currtype =
        SupplierPubService.getDefaultCurrtype(pk_supplier, pk_org);
    // ��ȡ��Ӧ�̱��֣�û��ȡ��֯��λ��
    if (StringUtil.isEmptyWithTrim(pk_currtype)) {
      pk_currtype = OrgUnitPubService.queryOrgCurrByPk(pk_org);
      if (StringUtil.isEmptyWithTrim(pk_currtype)) {
        return;
      }
    }
    util.getEditor().getHeadItem(InvoiceHeaderVO.CORIGCURRENCYID)
        .setValue(pk_currtype);
  }

}
