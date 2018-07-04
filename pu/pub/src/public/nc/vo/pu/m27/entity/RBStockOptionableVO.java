/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-17 ����11:36:02
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
 * <li>�Զ�����֮������ⵥ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-17 ����11:36:02
 */
public class RBStockOptionableVO extends SuperVO {

  /** ������ͬ **/
  public static final String BBATCHCODESAME = "bbatchcodesame";

  /** �ɹ�Ա��ͬ **/
  public static final String BBUYERSAME = "bbuyersame";

  /** ������ͬ **/
  public static final String BDEPTSAME = "bdeptsame";

  /** ������֯��ͬ **/
  public static final String BFINANCEORGSAME = "bfinanceorgsame";

  /** ���ɸ���������ͬ **/
  public static final String BFREEITEMSAME = "bfreeitemsame";

  /** ������ͬ **/
  public static final String BMATERIALSAME = "bmaterialsame";

  /** ������ⵥ��������ֵ��ͬ **/
  public static final String BNUMABSSAME = "bnumabssame";

  /** ��Դͬһ���� **/
  public static final String BORDERSAME = "bordersame";

  /** ԭ����˰������ͬ **/
  public static final String BORIGPRICESAME = "borigpricesame";

  /** ����������ͬ **/
  public static final String BPRODUCTORSAME = "bproductorsame";

  /** ��Ŀ��ͬ **/
  public static final String BPROJECTSAME = "bprojectsame";

  /** ��Ӧ����ͬ **/
  public static final String BSUPPLIERSAME = "bsuppliersame";

  /** ���������ͬ **/
  public static final String BTRANTYPESAME = "btrantypesame";

  /** ������ⵥ���� **/
  public static final String PK_RBSTOCK = "pk_rbstock";

  /** ʱ��� **/
  public static final String TS = "ts";

  private static final long serialVersionUID = 937199485488568826L;

  /** ������ͬ **/
  public UFBoolean getBbatchcodesame() {
    return (UFBoolean) this
        .getAttributeValue(RBStockOptionableVO.BBATCHCODESAME);
  }

  /** �ɹ�Ա��ͬ **/
  public UFBoolean getBbuyersame() {
    return (UFBoolean) this.getAttributeValue(RBStockOptionableVO.BBUYERSAME);
  }

  /** ������ͬ **/
  public UFBoolean getBdeptsame() {
    return (UFBoolean) this.getAttributeValue(RBStockOptionableVO.BDEPTSAME);
  }

  /** ������֯��ͬ **/
  public UFBoolean getBfinanceorgsame() {
    return (UFBoolean) this
        .getAttributeValue(RBStockOptionableVO.BFINANCEORGSAME);
  }

  /** ���ɸ���������ͬ **/
  public UFBoolean getBfreeitemsame() {
    return (UFBoolean) this
        .getAttributeValue(RBStockOptionableVO.BFREEITEMSAME);
  }

  /** ������ͬ **/
  public UFBoolean getBmaterialsame() {
    return (UFBoolean) this
        .getAttributeValue(RBStockOptionableVO.BMATERIALSAME);
  }

  /** ������ⵥ��������ֵ��ͬ **/
  public UFBoolean getBnumabssame() {
    return (UFBoolean) this.getAttributeValue(RBStockOptionableVO.BNUMABSSAME);
  }

  /** ��Դͬһ���� **/
  public UFBoolean getBordersame() {
    return (UFBoolean) this.getAttributeValue(RBStockOptionableVO.BORDERSAME);
  }

  /** ԭ����˰������ͬ **/
  public UFBoolean getBorigpricesame() {
    return (UFBoolean) this
        .getAttributeValue(RBStockOptionableVO.BORIGPRICESAME);
  }

  /** ����������ͬ **/
  public UFBoolean getBproductorsame() {
    return (UFBoolean) this
        .getAttributeValue(RBStockOptionableVO.BPRODUCTORSAME);
  }

  /** ��Ŀ��ͬ **/
  public UFBoolean getBprojectsame() {
    return (UFBoolean) this.getAttributeValue(RBStockOptionableVO.BPROJECTSAME);
  }

  /** ��Ӧ����ͬ **/
  public UFBoolean getBsuppliersame() {
    return (UFBoolean) this
        .getAttributeValue(RBStockOptionableVO.BSUPPLIERSAME);
  }

  /** ���������ͬ **/
  public UFBoolean getBtrantypesame() {
    return (UFBoolean) this
        .getAttributeValue(RBStockOptionableVO.BTRANTYPESAME);
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pub.SuperVO#getMetaData()
   */
  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta("pu.po_autosettle_rbstock");
    return meta;
  }

  /** ������ⵥ���� **/
  public String getPk_rbstock() {
    return (String) this.getAttributeValue(RBStockOptionableVO.PK_RBSTOCK);
  }

  /** ʱ��� **/
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(RBStockOptionableVO.TS);
  }

  /** ������ͬ **/
  public void setBbatchcodesame(UFBoolean bbatchcodesame) {
    this.setAttributeValue(RBStockOptionableVO.BBATCHCODESAME, bbatchcodesame);
  }

  /** �ɹ�Ա��ͬ **/
  public void setBbuyersame(UFBoolean bbuyersame) {
    this.setAttributeValue(RBStockOptionableVO.BBUYERSAME, bbuyersame);
  }

  /** ������ͬ **/
  public void setBdeptsame(UFBoolean bdeptsame) {
    this.setAttributeValue(RBStockOptionableVO.BDEPTSAME, bdeptsame);
  }

  /** ������֯��ͬ **/
  public void setBfinanceorgsame(UFBoolean bfinanceorgsame) {
    this.setAttributeValue(RBStockOptionableVO.BFINANCEORGSAME, bfinanceorgsame);
  }

  /** ���ɸ���������ͬ **/
  public void setBfreeitemsame(UFBoolean bfreeitemsame) {
    this.setAttributeValue(RBStockOptionableVO.BFREEITEMSAME, bfreeitemsame);
  }

  /** ������ͬ **/
  public void setBmaterialsame(UFBoolean bmaterialsame) {
    this.setAttributeValue(RBStockOptionableVO.BMATERIALSAME, bmaterialsame);
  }

  /** ������ⵥ��������ֵ��ͬ **/
  public void setBnumabssame(UFBoolean bnumabssame) {
    this.setAttributeValue(RBStockOptionableVO.BNUMABSSAME, bnumabssame);
  }

  /** ��Դͬһ���� **/
  public void setBordersame(UFBoolean bordersame) {
    this.setAttributeValue(RBStockOptionableVO.BORDERSAME, bordersame);
  }

  /** ԭ����˰������ͬ **/
  public void setBorigpricesame(UFBoolean borigpricesame) {
    this.setAttributeValue(RBStockOptionableVO.BORIGPRICESAME, borigpricesame);
  }

  /** ����������ͬ **/
  public void setBproductorsame(UFBoolean bproductorsame) {
    this.setAttributeValue(RBStockOptionableVO.BPRODUCTORSAME, bproductorsame);
  }

  /** ��Ŀ��ͬ **/
  public void setBprojectsame(UFBoolean bprojectsame) {
    this.setAttributeValue(RBStockOptionableVO.BPROJECTSAME, bprojectsame);
  }

  /** ��Ӧ����ͬ **/
  public void setBsuppliersame(UFBoolean bsuppliersame) {
    this.setAttributeValue(RBStockOptionableVO.BSUPPLIERSAME, bsuppliersame);
  }

  /** ���������ͬ **/
  public void setBtrantypesame(UFBoolean btrantypesame) {
    this.setAttributeValue(RBStockOptionableVO.BTRANTYPESAME, btrantypesame);
  }

  /** ������ⵥ���� **/
  public void setPk_rbstock(String pk_rbstock) {
    this.setAttributeValue(RBStockOptionableVO.PK_RBSTOCK, pk_rbstock);
  }

  /** ʱ��� **/
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(RBStockOptionableVO.TS, ts);
  }

}
