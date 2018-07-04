/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-28 下午04:46:43
 */
package nc.ui.pu.pub.editor.card.handler;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.itf.scmpub.reference.uap.org.DeptPubService;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.bd.supplier.stock.SupStockVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-28 下午04:46:43
 */
public class SupplierValueHandler {

  private BillCardPanel billCardPanel = null;

  // 以下的key根据不同的单据可通过setter方法做调整

  /** 银行账户 */
  private String pk_bankaccbasKey = "pk_bankaccbas";

  /** 业务员 */
  private String pk_bizpsnKey = "pk_bizpsn";

  /** 采购部门 */
  private String pk_deptKey = "pk_dept";

  /** 采购部门vid */
  private String pk_deptvKey = "pk_dept_v";

  /** 财务组织 */
  private String pk_financeorgKey = "pk_financeorg";

  /** 散户 */
  private String pk_freecustKey = "pk_freecust";

  /** 付款协议 */
  private String pk_paytermKey = "pk_payterm";

  /** 付款单位 */
  private String pk_paytosupplierKey = "pk_paytosupplier";

  /** 采购组织 */
  private String pk_purchaseorgKey = "pk_purchaseorg";

  /** 供应商 */
  private String pk_supplierKey = "pk_supplier";

  /** 供应商电话 */
  private String supplierphoneKey = "supplierphone";

  public SupplierValueHandler(BillCardPanel billCardPanel) {
    this.billCardPanel = billCardPanel;
  }

  /**
   * @return billCardPanel
   */
  public BillCardPanel getBillCardPanel() {
    return this.billCardPanel;
  }

  /**
   * @return pk_bankaccbasKey
   */
  public String getPk_bankaccbasKey() {
    return this.pk_bankaccbasKey;
  }

  /**
   * @return pk_bizpsnKey
   */
  public String getPk_bizpsnKey() {
    return this.pk_bizpsnKey;
  }

  /**
   * @return pk_deptKey
   */
  public String getPk_deptKey() {
    return this.pk_deptKey;
  }

  /**
   * @return pk_deptKey
   */
  public String getPk_deptvKey() {
    return this.pk_deptvKey;
  }

  /**
   * @return pk_financeorgKey
   */
  public String getPk_financeorgKey() {
    return this.pk_financeorgKey;
  }

  /**
   * @return pk_freecustKey
   */
  public String getPk_freecustKey() {
    return this.pk_freecustKey;
  }

  /**
   * @return pk_paytermKey
   */
  public String getPk_paytermKey() {
    return this.pk_paytermKey;
  }

  /**
   * @return pk_paytosupplierKey
   */
  public String getPk_paytosupplierKey() {
    return this.pk_paytosupplierKey;
  }

  /**
   * @return pk_purchaseorgKey
   */
  public String getPk_purchaseorgKey() {
    return this.pk_purchaseorgKey;
  }

  /**
   * @return pk_supplierKey
   */
  public String getPk_supplierKey() {
    return this.pk_supplierKey;
  }

  /**
   * @return supplierphoneKey
   */
  public String getSupplierphoneKey() {
    return this.supplierphoneKey;
  }

  /**
   * 方法功能描述：供应商变化后相关处理
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author tianft
   * @time 2010-4-29 下午05:09:53
   */
  public void handleHeadDefaultValue() {
    this.setHeadPurchaseValue();
    this.handleHeadFreeCust();
  }

  /**
   * 方法功能描述：供应商散户处理
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author tianft
   * @time 2010-4-29 下午05:10:00
   */
  public void handleHeadFreeCust() {
    CardEditorHelper helper = new CardEditorHelper(this.getBillCardPanel());
    // 供应商
    String pk_supplier = helper.getHeadStringValue(this.getPk_supplierKey());
    SupplierVO[] supplierVO = SupplierPubService.getSupplierVO(new String[] {
      pk_supplier
    }, new String[] {
      SupplierVO.ISFREECUST
    });
    if (ArrayUtils.isEmpty(supplierVO)) {
      return;
    }
    // 散户
    if (UFBoolean.TRUE.equals(supplierVO[0].getIsfreecust())) {
      // 散户
      helper.getEditor().getHeadItem(this.getPk_freecustKey()).setEnabled(true);
      // 银行账户
      helper.getEditor().getHeadItem(this.getPk_bankaccbasKey())
          .setEnabled(false);
      helper.setHeadValue(this.getPk_bankaccbasKey(), null);
      // 供应商电话
      helper.setHeadValue(this.getSupplierphoneKey(), null);
    }
    else {
      // 散户置灰,清空相应信息
      helper.getEditor().getHeadItem(this.getPk_freecustKey())
          .setEnabled(false);
      helper.setHeadValue(this.getPk_freecustKey(), null);
      helper.getEditor().getHeadItem(this.getPk_bankaccbasKey())
          .setEnabled(true);
    }
  }

  /**
   * @param billCardPanel
   *          要设置的 billCardPanel
   */
  public void setBillCardPanel(BillCardPanel billCardPanel) {
    this.billCardPanel = billCardPanel;
  }

  /**
   * 方法功能描述：根据供应商,查找采购组织对应的专管部门和专管业务员
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org
   *          <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-28 下午04:53:57
   */
  public void setHeadPurchaseValue() {
    CardEditorHelper helper = new CardEditorHelper(this.getBillCardPanel());
    // 采购部门
    String pk_dept = helper.getHeadStringValue(this.getPk_deptKey());
    // 采购部门vid
    String pk_dept_v = helper.getHeadStringValue(this.getPk_deptvKey());

    // 业务员
    String pk_bizpsn = helper.getHeadStringValue(this.getPk_bizpsnKey());
    // 采购组织
    String pk_purchaseorg =
        helper.getHeadStringValue(this.getPk_purchaseorgKey());
    // 供应商
    String pk_supplier = helper.getHeadStringValue(this.getPk_supplierKey());

    // 付款单位->供应商自身
    helper.setHeadValue(this.pk_paytosupplierKey, pk_supplier);

    // 采购组织为空，不处理
    if (StringUtil.isEmptyWithTrim(pk_purchaseorg)
        || StringUtil.isEmptyWithTrim(pk_supplier)) {
      return;
    }

    SupStockVO[] supStockVOs =
        SupplierPubService.getSupStockVO(new String[] {
          pk_supplier
        }, pk_purchaseorg, new String[] {
          SupStockVO.PK_SUPPLIER, SupStockVO.RESPDEPT, SupStockVO.RESPPERSON,
          SupStockVO.PK_ORG, SupStockVO.PAYTERMDEFAULT
        });
    if (ArrayUtils.isEmpty(supStockVOs)) {
      return;
    }
    // 采购部门 -> 专管部门
    if (StringUtil.isEmptyWithTrim(pk_dept)) {
      helper.setHeadValue(this.getPk_deptKey(), supStockVOs[0].getRespdept());
    }
    // 采购部门vid -> 专管部门vid
    if (StringUtil.isEmptyWithTrim(pk_dept_v)) {
      helper.setHeadValue(this.getPk_deptvKey(),
          this.getRespDept_v(supStockVOs[0].getRespdept()));
    }
    // 采购员 -> 专管人员
    if (StringUtil.isEmptyWithTrim(pk_bizpsn)) {
      helper.setHeadValue(this.getPk_bizpsnKey(),
          supStockVOs[0].getRespperson());
    }
    // 付款协议
    if (!StringUtil.isEmptyWithTrim(supStockVOs[0].getPaytermdefault())) {
      helper.setHeadValue(this.getPk_paytermKey(),
          supStockVOs[0].getPaytermdefault());
    }
  }

  /**
   * @param pkBankaccbasKey
   *          要设置的 pk_bankaccbasKey
   */
  public void setPk_bankaccbasKey(String pkBankaccbasKey) {
    this.pk_bankaccbasKey = pkBankaccbasKey;
  }

  /**
   * @param pkBizpsnKey
   *          要设置的 pk_bizpsnKey
   */
  public void setPk_bizpsnKey(String pkBizpsnKey) {
    this.pk_bizpsnKey = pkBizpsnKey;
  }

  /**
   * @param pkDeptKey
   *          要设置的 pk_deptKey
   */
  public void setPk_deptKey(String pkDeptKey) {
    this.pk_deptKey = pkDeptKey;
  }

  /**
   * @param pkFinanceorgKey
   *          要设置的 pk_financeorgKey
   */
  public void setPk_financeorgKey(String pkFinanceorgKey) {
    this.pk_financeorgKey = pkFinanceorgKey;
  }

  /**
   * @param pkFreecustKey
   *          要设置的 pk_freecustKey
   */
  public void setPk_freecustKey(String pkFreecustKey) {
    this.pk_freecustKey = pkFreecustKey;
  }

  /**
   * @param pkPaytermKey
   *          要设置的 pk_paytermKey
   */
  public void setPk_paytermKey(String pkPaytermKey) {
    this.pk_paytermKey = pkPaytermKey;
  }

  /**
   * @param pkPaytosupplierKey
   *          要设置的 pk_paytosupplierKey
   */
  public void setPk_paytosupplierKey(String pkPaytosupplierKey) {
    this.pk_paytosupplierKey = pkPaytosupplierKey;
  }

  /**
   * @param pkPurchaseorgKey
   *          要设置的 pk_purchaseorgKey
   */
  public void setPk_purchaseorgKey(String pkPurchaseorgKey) {
    this.pk_purchaseorgKey = pkPurchaseorgKey;
  }

  /**
   * @param pkSupplierKey
   *          要设置的 pk_supplierKey
   */
  public void setPk_supplierKey(String pkSupplierKey) {
    this.pk_supplierKey = pkSupplierKey;
  }

  /**
   * @param supplierphoneKey
   *          要设置的 supplierphoneKey
   */
  public void setSupplierphoneKey(String supplierphoneKey) {
    this.supplierphoneKey = supplierphoneKey;
  }

  private String getRespDept_v(String pk_dept) {
    if (StringUtil.isEmptyWithTrim(pk_dept)) {
      return null;
    }
    Map<String, String> map = DeptPubService.getLastVIDSByDeptIDS(new String[] {
      pk_dept
    });
    if (null == map) {
      return null;
    }
    return map.get(pk_dept);
  }
}
