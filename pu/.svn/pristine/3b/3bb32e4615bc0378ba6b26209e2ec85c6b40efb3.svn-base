/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-23 下午03:34:09
 */
package nc.ui.pu.m25.editor.utils;

import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>币种的工具类,适用于发票
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-1 上午09:37:03
 */
public class CurrencyUtil {

  /**
   * 方法功能描述：设置表头原币币种
   * <p>
   * <b>参数说明</b>
   * 
   * @param util
   *          <p>
   * @since 6.0
   * @author tianft
   * @time 2010-3-31 下午04:54:38
   */
  public static void setOrigcurrency(CardEditorHelper util) {
    // 供应商主键
    String pk_supplier = util.getHeadStringValue(InvoiceHeaderVO.PK_SUPPLIER);
    // 组织主键
    String pk_org = util.getHeadStringValue(InvoiceHeaderVO.PK_ORG);
    // 默认币种
    String pk_currtype =
        SupplierPubService.getDefaultCurrtype(pk_supplier, pk_org);
    // 先取供应商币种，没有取组织本位币
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
