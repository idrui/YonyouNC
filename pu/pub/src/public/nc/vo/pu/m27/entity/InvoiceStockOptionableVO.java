/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-17 ����11:37:01
 */
package nc.vo.pu.m27.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�Զ�����֮��Ʊ��ⵥ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-17 ����11:37:01
 */
public class InvoiceStockOptionableVO extends SuperVO {

  /** ������ͬ **/
  public static final String BBATCHCODESAME = "bbatchcodesame";

  /** �ɹ�Ա��ͬ **/
  public static final String BBUYERSAME = "bbuyersame";

  /** ������ͬ **/
  public static final String BDEPTSAME = "bdeptsame";

  /** ��Դͬһ������ϸ���� **/
  public static final String BDETAILBIDSAME = "bdetailbidsame";

  /** ������֯��ͬ **/
  public static final String BFINANCEORGSAME = "bfinanceorgsame";

  /** ���ɸ���������ͬ **/
  public static final String BFREEITEMSAME = "bfreeitemsame";

  /** ������ͬ **/
  public static final String BMATERIALSAME = "bmaterialsame";

  /** ��Ʊ����ⵥ������ͬ **/
  public static final String BNUMSAME = "bnumsame";

  /** ԭ����˰������ͬ **/
  public static final String BORIGPRICESAME = "borigpricesame";

  /** ����������ͬ **/
  public static final String BPRODUCTORSAME = "bproductorsame";

  /** ��Ŀ��ͬ **/
  public static final String BPROJECTSAME = "bprojectsame";

  /** ��Ӧ����ͬ **/
  public static final String BSUPPLIERSAME = "bsuppliersame";

  /** ��Ʊ��ⵥ���� **/
  public static final String PK_INVOICESTOCK = "pk_invoicestock";

  /** ʱ��� **/
  public static final String TS = "ts";

  private static final long serialVersionUID = 8626754510645429181L;

  /** ������ͬ **/
  public UFBoolean getBbatchcodesame() {
    return (UFBoolean) this
        .getAttributeValue(InvoiceStockOptionableVO.BBATCHCODESAME);
  }

  /** �ɹ�Ա��ͬ **/
  public UFBoolean getBbuyersame() {
    return (UFBoolean) this
        .getAttributeValue(InvoiceStockOptionableVO.BBUYERSAME);
  }

  /** ������ͬ **/
  public UFBoolean getBdeptsame() {
    return (UFBoolean) this
        .getAttributeValue(InvoiceStockOptionableVO.BDEPTSAME);
  }

  /**
   * ��Դͬһ������ϸ����
   * 
   * @return Bdetailbidsame
   **/
  public UFBoolean getBdetailbidsame() {
    return (UFBoolean) this
        .getAttributeValue(InvoiceStockOptionableVO.BDETAILBIDSAME);
  }

  /** ������֯��ͬ **/
  public UFBoolean getBfinanceorgsame() {
    return (UFBoolean) this
        .getAttributeValue(InvoiceStockOptionableVO.BFINANCEORGSAME);
  }

  /** ���ɸ���������ͬ **/
  public UFBoolean getBfreeitemsame() {
    return (UFBoolean) this
        .getAttributeValue(InvoiceStockOptionableVO.BFREEITEMSAME);
  }

  /** ������ͬ **/
  public UFBoolean getBmaterialsame() {
    return (UFBoolean) this
        .getAttributeValue(InvoiceStockOptionableVO.BMATERIALSAME);
  }

  /** ��Ʊ����ⵥ������ͬ **/
  public UFBoolean getBnumsame() {
    return (UFBoolean) this
        .getAttributeValue(InvoiceStockOptionableVO.BNUMSAME);
  }

  /** ԭ����˰������ͬ **/
  public UFBoolean getBorigpricesame() {
    return (UFBoolean) this
        .getAttributeValue(InvoiceStockOptionableVO.BORIGPRICESAME);
  }

  /** ����������ͬ **/
  public UFBoolean getBproductorsame() {
    return (UFBoolean) this
        .getAttributeValue(InvoiceStockOptionableVO.BPRODUCTORSAME);
  }

  /** ��Ŀ��ͬ **/
  public UFBoolean getBprojectsame() {
    return (UFBoolean) this
        .getAttributeValue(InvoiceStockOptionableVO.BPROJECTSAME);
  }

  /** ��Ӧ����ͬ **/
  public UFBoolean getBsuppliersame() {
    return (UFBoolean) this
        .getAttributeValue(InvoiceStockOptionableVO.BSUPPLIERSAME);
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pub.SuperVO#getMetaData()
   */
  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta("pu.po_autosettle_invoicestock");
    return meta;
  }

  /** ��Ʊ��ⵥ���� **/
  public String getPk_invoicestock() {
    return (String) this
        .getAttributeValue(InvoiceStockOptionableVO.PK_INVOICESTOCK);
  }

  /** ʱ��� **/
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(InvoiceStockOptionableVO.TS);
  }

  /** ������ͬ **/
  public void setBbatchcodesame(UFBoolean bbatchcodesame) {
    this.setAttributeValue(InvoiceStockOptionableVO.BBATCHCODESAME,
        bbatchcodesame);
  }

  /** �ɹ�Ա��ͬ **/
  public void setBbuyersame(UFBoolean bbuyersame) {
    this.setAttributeValue(InvoiceStockOptionableVO.BBUYERSAME, bbuyersame);
  }

  /** ������ͬ **/
  public void setBdeptsame(UFBoolean bdeptsame) {
    this.setAttributeValue(InvoiceStockOptionableVO.BDEPTSAME, bdeptsame);
  }

  /**
   * ��Դͬһ������ϸ����
   * 
   * @param bdetailbidsame
   **/
  public void setBdetailbidsame(UFBoolean bdetailbidsame) {
    this.setAttributeValue(InvoiceStockOptionableVO.BDETAILBIDSAME,
        bdetailbidsame);
  }

  /** ������֯��ͬ **/
  public void setBfinanceorgsame(UFBoolean bfinanceorgsame) {
    this.setAttributeValue(InvoiceStockOptionableVO.BFINANCEORGSAME,
        bfinanceorgsame);
  }

  /** ���ɸ���������ͬ **/
  public void setBfreeitemsame(UFBoolean bfreeitemsame) {
    this.setAttributeValue(InvoiceStockOptionableVO.BFREEITEMSAME,
        bfreeitemsame);
  }

  /** ������ͬ **/
  public void setBmaterialsame(UFBoolean bmaterialsame) {
    this.setAttributeValue(InvoiceStockOptionableVO.BMATERIALSAME,
        bmaterialsame);
  }

  /** ��Ʊ����ⵥ������ͬ **/
  public void setBnumsame(UFBoolean bnumsame) {
    this.setAttributeValue(InvoiceStockOptionableVO.BNUMSAME, bnumsame);
  }

  /** ԭ����˰������ͬ **/
  public void setBorigpricesame(UFBoolean borigpricesame) {
    this.setAttributeValue(InvoiceStockOptionableVO.BORIGPRICESAME,
        borigpricesame);
  }

  /** ����������ͬ **/
  public void setBproductorsame(UFBoolean bproductorsame) {
    this.setAttributeValue(InvoiceStockOptionableVO.BPRODUCTORSAME,
        bproductorsame);
  }

  /** ��Ŀ��ͬ **/
  public void setBprojectsame(UFBoolean bprojectsame) {
    this.setAttributeValue(InvoiceStockOptionableVO.BPROJECTSAME, bprojectsame);
  }

  /** ��Ӧ����ͬ **/
  public void setBsuppliersame(UFBoolean bsuppliersame) {
    this.setAttributeValue(InvoiceStockOptionableVO.BSUPPLIERSAME,
        bsuppliersame);
  }

  /** ��Ʊ��ⵥ���� **/
  public void setPk_invoicestock(String pk_invoicestock) {
    this.setAttributeValue(InvoiceStockOptionableVO.PK_INVOICESTOCK,
        pk_invoicestock);
  }

  /** ʱ��� **/
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(InvoiceStockOptionableVO.TS, ts);
  }
}
