/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-26 ����06:38:43
 */
package nc.vo.pu.m20.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * �۸���֤��vo
 * 
 * @since 6.0
 * @version 2011-3-17 ����03:33:55
 * @author �����
 */
public class PrayPriceInfoVO extends SuperVO {

  /** ԭ�ұ��� */
  public static final String CCURRENCYID = "ccurrencyid";

  /** �������� */
  public static final String DCURDATE = "dcurdate";

  /** ������ʼ���� */
  public static final String DFROMDATE = "dfromdate";

  /** dr */
  public static final String DR = "dr";

  /** ���۽������� */
  public static final String DTODATE = "dtodate";

  /** �󶨽�� */
  public static final String NAUDITPRICE = "nauditprice";

  /** ���¼� */
  public static final String NLATESTPRICE = "nlatestprice";

  /** ����1 */
  public static final String NQUOTEPRICE1 = "nquoteprice1";

  /** ����2 */
  public static final String NQUOTEPRICE2 = "nquoteprice2";

  /** ����3 */
  public static final String NQUOTEPRICE3 = "nquoteprice3";

  /** ���� */
  public static final String PK_GROUP = "pk_group";

  /** ���ϣ�VID�� */
  public static final String PK_MATERIAL = "pk_material";

  /** �ɹ���֯ */
  public static final String PK_ORG = "pk_org";

  /** �ɹ���֯�汾 */
  public static final String PK_ORG_V = "pk_org_v";

  /** �빺����id */
  public static final String PK_PRAYBILL_B = "pk_praybill_b";

  /** �۸���֤�� */
  public static final String PK_PRICEINFO = "pk_priceinfo";

  /** ���ϣ�OID�� */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** ��һ��Ӧ�� */
  public static final String PK_SUPPLIER1 = "pk_supplier1";

  /** �ڶ���Ӧ�� */
  public static final String PK_SUPPLIER2 = "pk_supplier2";

  /** ������Ӧ�� */
  public static final String PK_SUPPLIER3 = "pk_supplier3";

  /** ts */
  public static final String TS = "ts";

  private static final long serialVersionUID = 1L;

  /** ԭ�ұ��� getter ���� */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(PrayPriceInfoVO.CCURRENCYID);
  }

  /** �������� getter ���� */
  public UFDate getDcurdate() {
    return (UFDate) this.getAttributeValue(PrayPriceInfoVO.DCURDATE);
  }

  /** ������ʼ���� getter ���� */
  public UFDate getDfromdate() {
    return (UFDate) this.getAttributeValue(PrayPriceInfoVO.DFROMDATE);
  }

  /** dr getter ���� */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(PrayPriceInfoVO.DR);
  }

  /** ���۽������� getter ���� */
  public UFDate getDtodate() {
    return (UFDate) this.getAttributeValue(PrayPriceInfoVO.DTODATE);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M20_PRICEINFO);
  }

  /** �󶨽�� getter ���� */
  public UFDouble getNauditprice() {
    return (UFDouble) this.getAttributeValue(PrayPriceInfoVO.NAUDITPRICE);
  }

  /** ���¼� getter ���� */
  public UFDouble getNlatestprice() {
    return (UFDouble) this.getAttributeValue(PrayPriceInfoVO.NLATESTPRICE);
  }

  /** ����1 getter ���� */
  public UFDouble getNquoteprice1() {
    return (UFDouble) this.getAttributeValue(PrayPriceInfoVO.NQUOTEPRICE1);
  }

  /** ����2 getter ���� */
  public UFDouble getNquoteprice2() {
    return (UFDouble) this.getAttributeValue(PrayPriceInfoVO.NQUOTEPRICE2);
  }

  /** ����3 getter ���� */
  public UFDouble getNquoteprice3() {
    return (UFDouble) this.getAttributeValue(PrayPriceInfoVO.NQUOTEPRICE3);
  }

  /** ���� getter ���� */
  public String getPk_group() {
    return (String) this.getAttributeValue(PrayPriceInfoVO.PK_GROUP);
  }

  /** ���ϣ�VID�� getter ���� */
  public String getPk_material() {
    return (String) this.getAttributeValue(PrayPriceInfoVO.PK_MATERIAL);
  }

  /** �ɹ���֯ getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(PrayPriceInfoVO.PK_ORG);
  }

  /** �ɹ���֯�汾 getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(PrayPriceInfoVO.PK_ORG_V);
  }

  /** �빺����id getter ���� */
  public String getPk_praybill_b() {
    return (String) this.getAttributeValue(PrayPriceInfoVO.PK_PRAYBILL_B);
  }

  /** �۸���֤�� getter ���� */
  public String getPk_priceinfo() {
    return (String) this.getAttributeValue(PrayPriceInfoVO.PK_PRICEINFO);
  }

  /** ���ϣ�OID�� getter ���� */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(PrayPriceInfoVO.PK_SRCMATERIAL);
  }

  /** ��һ��Ӧ�� getter ���� */
  public String getPk_supplier1() {
    return (String) this.getAttributeValue(PrayPriceInfoVO.PK_SUPPLIER1);
  }

  /** �ڶ���Ӧ�� getter ���� */
  public String getPk_supplier2() {
    return (String) this.getAttributeValue(PrayPriceInfoVO.PK_SUPPLIER2);
  }

  /** ������Ӧ�� getter ���� */
  public String getPk_supplier3() {
    return (String) this.getAttributeValue(PrayPriceInfoVO.PK_SUPPLIER3);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(PrayPriceInfoVO.TS);
  }

  /** ԭ�ұ��� setter ���� */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(PrayPriceInfoVO.CCURRENCYID, ccurrencyid);
  }

  /** �������� setter ���� */
  public void setDcurdate(UFDate ncurdate) {
    this.setAttributeValue(PrayPriceInfoVO.DCURDATE, ncurdate);
  }

  /** ������ʼ���� setter ���� */
  public void setDfromdate(UFDate dfromdate) {
    this.setAttributeValue(PrayPriceInfoVO.DFROMDATE, dfromdate);
  }

  /** dr setter ���� */
  public void setDr(Integer dr) {
    this.setAttributeValue(PrayPriceInfoVO.DR, dr);
  }

  /** ���۽������� setter ���� */
  public void setDtodate(UFDate dtodate) {
    this.setAttributeValue(PrayPriceInfoVO.DTODATE, dtodate);
  }

  /** �󶨽�� setter ���� */
  public void setNauditprice(UFDouble nauditprice) {
    this.setAttributeValue(PrayPriceInfoVO.NAUDITPRICE, nauditprice);
  }

  /** ���¼� setter ���� */
  public void setNlatestprice(UFDouble nlatestprice) {
    this.setAttributeValue(PrayPriceInfoVO.NLATESTPRICE, nlatestprice);
  }

  /** ����1 setter ���� */
  public void setNquoteprice1(UFDouble nquoteprice1) {
    this.setAttributeValue(PrayPriceInfoVO.NQUOTEPRICE1, nquoteprice1);
  }

  /** ����2 setter ���� */
  public void setNquoteprice2(UFDouble nquoteprice2) {
    this.setAttributeValue(PrayPriceInfoVO.NQUOTEPRICE2, nquoteprice2);
  }

  /** ����3 setter ���� */
  public void setNquoteprice3(UFDouble nquoteprice3) {
    this.setAttributeValue(PrayPriceInfoVO.NQUOTEPRICE3, nquoteprice3);
  }

  /** ���� setter ���� */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(PrayPriceInfoVO.PK_GROUP, pk_group);
  }

  /** ���ϣ�VID�� setter ���� */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(PrayPriceInfoVO.PK_MATERIAL, pk_material);
  }

  /** �ɹ���֯ setter ���� */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(PrayPriceInfoVO.PK_ORG, pk_org);
  }

  /** �ɹ���֯�汾 setter ���� */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(PrayPriceInfoVO.PK_ORG_V, pk_org_v);
  }

  /** �빺����id setter ���� */
  public void setPk_praybill_b(String pk_praybill_b) {
    this.setAttributeValue(PrayPriceInfoVO.PK_PRAYBILL_B, pk_praybill_b);
  }

  /** �۸���֤�� setter ���� */
  public void setPk_priceinfo(String pk_priceinfo) {
    this.setAttributeValue(PrayPriceInfoVO.PK_PRICEINFO, pk_priceinfo);
  }

  /** ���ϣ�OID�� setter ���� */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(PrayPriceInfoVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** ��һ��Ӧ�� setter ���� */
  public void setPk_supplier1(String pk_supplier1) {
    this.setAttributeValue(PrayPriceInfoVO.PK_SUPPLIER1, pk_supplier1);
  }

  /** �ڶ���Ӧ�� setter ���� */
  public void setPk_supplier2(String pk_supplier2) {
    this.setAttributeValue(PrayPriceInfoVO.PK_SUPPLIER2, pk_supplier2);
  }

  /** ������Ӧ�� setter ���� */
  public void setPk_supplier3(String pk_supplier3) {
    this.setAttributeValue(PrayPriceInfoVO.PK_SUPPLIER3, pk_supplier3);
  }

  /** ts setter ���� */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(PrayPriceInfoVO.TS, ts);
  }

}
