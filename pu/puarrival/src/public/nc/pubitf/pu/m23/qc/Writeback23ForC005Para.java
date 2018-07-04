package nc.pubitf.pu.m23.qc;

import java.io.Serializable;

import nc.vo.pu.pub.writeback.IWriteBackPubPara;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������е���д������ʱ�Ĳ����ӿ�
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 ����04:16:08
 */
public class Writeback23ForC005Para implements IWriteBackPubPara, Serializable {

  private static final long serialVersionUID = -361677967040404523L;

  // ��������������
  private String BID;

  // �������е��к�
  private String cpassbollrowno;

  // �������в�������
  private UFDouble diffNum;

  // ��������ͷ����
  private String HID;

  // �������е���������
  private String passBid;

  // �������е�����
  private String passHid;

  // �������е���
  private String vpassbillcode;

  @Override
  public String getBID() {
    return this.BID;
  }

  public String getCpassbollrowno() {
    return this.cpassbollrowno;
  }

  @Override
  public UFDouble getDiffNum() {
    return this.diffNum;
  }

  @Override
  public String getHID() {
    return this.HID;
  }

  public String getPassBid() {
    return this.passBid;
  }

  public String getPassHid() {
    return this.passHid;
  }

  public String getVpassbillcode() {
    return this.vpassbillcode;
  }

  public void setBID(String bID) {
    this.BID = bID;
  }

  public void setCpassbollrowno(String cpassbollrowno) {
    this.cpassbollrowno = cpassbollrowno;
  }

  public void setDiffNum(UFDouble diffNum) {
    this.diffNum = diffNum;
  }

  public void setHID(String hID) {
    this.HID = hID;
  }

  public void setPassBid(String passBid) {
    this.passBid = passBid;
  }

  public void setPassHid(String passHid) {
    this.passHid = passHid;
  }

  public void setVpassbillcode(String vpassbillcode) {
    this.vpassbillcode = vpassbillcode;
  }

  public void validate() {
    if (StringUtils.isEmpty(this.getHID())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0140")/*@res "��д�������Ĳ����е�������ͷ����������Ϊ�գ�"*/);
    }
    if (StringUtils.isEmpty(this.getBID())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0141")/*@res "��д�������Ĳ����е�������������������Ϊ�գ�"*/);
    }
    if (StringUtils.isEmpty(this.passHid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0142")/*@res "��д�������Ĳ����н������е���ͷ����������Ϊ�գ�"*/);
    }
    if (StringUtils.isEmpty(this.getPassBid())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0143")/*@res "��д�������Ĳ����н������е���������������Ϊ�գ�"*/);
    }
    if (StringUtils.isEmpty(this.getVpassbillcode())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0144")/*@res "��д�������Ĳ����н������е��Ų�����Ϊ�գ�"*/);
    }
    if (StringUtils.isEmpty(this.getCpassbollrowno())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0145")/*@res "��д�������Ĳ����н������е��кŲ�����Ϊ�գ�"*/);
    }
  }
}