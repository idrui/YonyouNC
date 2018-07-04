/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-7 ����11:45:20
 */
package nc.vo.pu.m21.entity;

import java.io.Serializable;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���������ѯ��ϢVO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-7 ����11:45:20
 */
public class ECOrderQueryVO implements Serializable {

  private static final long serialVersionUID = 1933986447341561236L;

  /** �����ر� **/
  private UFBoolean barriveclose;

  /** ��Ʊ�ر� **/
  private UFBoolean binvoiceclose;

  /** ����ر� **/
  private UFBoolean bpayclose;

  /** ���ر� **/
  private UFBoolean bstockclose;

  /** �ۼƵ������� **/
  private UFDouble naccumarrvnum;

  /** �ۼƷ�Ʊ���� **/
  private UFDouble naccuminvoicenum;

  /** �ۼƸ����� **/
  private UFDouble naccumpayorgmny;

  /** �ۼ�������� **/
  private UFDouble naccumstorenum;

  /** �ۼ��˻����� **/
  private UFDouble nbackarrvnum;

  /** �ۼ��˿����� **/
  private UFDouble nbackstorenum;

  /** ������ **/
  private UFDouble nnum;

  /** �����ر� **/
  public UFBoolean getBarriveclose() {
    return this.barriveclose;
  }

  /** ��Ʊ�ر� **/
  public UFBoolean getBinvoiceclose() {
    return this.binvoiceclose;
  }

  /** ����ر� **/
  public UFBoolean getBpayclose() {
    return this.bpayclose;
  }

  /** ���ر� **/
  public UFBoolean getBstockclose() {
    return this.bstockclose;
  }

  /** �ۼƵ������� **/
  public UFDouble getNaccumarrvnum() {
    return this.naccumarrvnum;
  }

  /** �ۼƷ�Ʊ���� **/
  public UFDouble getNaccuminvoicenum() {
    return this.naccuminvoicenum;
  }

  /** �ۼƸ����� **/
  public UFDouble getNaccumpayorgmny() {
    return this.naccumpayorgmny;
  }

  /** �ۼ�������� **/
  public UFDouble getNaccumstorenum() {
    return this.naccumstorenum;
  }

  /** �ۼ��˻����� **/
  public UFDouble getNbackarrvnum() {
    return this.nbackarrvnum;
  }

  /** �ۼ��˿����� **/
  public UFDouble getNbackstorenum() {
    return this.nbackstorenum;
  }

  /** ������ **/
  public UFDouble getNnum() {
    return this.nnum;
  }

  /** �����ر� **/
  public void setBarriveclose(UFBoolean barriveclose) {
    this.barriveclose = barriveclose;
  }

  /** ��Ʊ�ر� **/
  public void setBinvoiceclose(UFBoolean binvoiceclose) {
    this.binvoiceclose = binvoiceclose;
  }

  /** ����ر� **/
  public void setBpayclose(UFBoolean bpayclose) {
    this.bpayclose = bpayclose;
  }

  /** ���ر� **/
  public void setBstockclose(UFBoolean bstockclose) {
    this.bstockclose = bstockclose;
  }

  /** �ۼƵ������� **/
  public void setNaccumarrvnum(UFDouble naccumarrvnum) {
    this.naccumarrvnum = naccumarrvnum;
  }

  /** �ۼƷ�Ʊ���� **/
  public void setNaccuminvoicenum(UFDouble naccuminvoicenum) {
    this.naccuminvoicenum = naccuminvoicenum;
  }

  /** �ۼƸ����� **/
  public void setNaccumpayorgmny(UFDouble naccumpayorgmny) {
    this.naccumpayorgmny = naccumpayorgmny;
  }

  /** �ۼ�������� **/
  public void setNaccumstorenum(UFDouble naccumstorenum) {
    this.naccumstorenum = naccumstorenum;
  }

  /** �ۼ��˻����� **/
  public void setNbackarrvnum(UFDouble nbackarrvnum) {
    this.nbackarrvnum = nbackarrvnum;
  }

  /** �ۼ��˿����� **/
  public void setNbackstorenum(UFDouble nbackstorenum) {
    this.nbackstorenum = nbackstorenum;
  }

  /** ������ **/
  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }
}
