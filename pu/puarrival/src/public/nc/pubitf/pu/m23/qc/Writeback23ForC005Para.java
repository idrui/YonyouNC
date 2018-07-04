package nc.pubitf.pu.m23.qc;

import java.io.Serializable;

import nc.vo.pu.pub.writeback.IWriteBackPubPara;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>紧急放行单回写到货单时的参数接口
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 下午04:16:08
 */
public class Writeback23ForC005Para implements IWriteBackPubPara, Serializable {

  private static final long serialVersionUID = -361677967040404523L;

  // 到货单表体主键
  private String BID;

  // 紧急放行单行号
  private String cpassbollrowno;

  // 紧急放行差异数量
  private UFDouble diffNum;

  // 到货单表头主键
  private String HID;

  // 紧急放行单表体主键
  private String passBid;

  // 紧急放行单主键
  private String passHid;

  // 紧急放行单号
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
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0140")/*@res "回写到货单的参数中到货单表头主键不允许为空！"*/);
    }
    if (StringUtils.isEmpty(this.getBID())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0141")/*@res "回写到货单的参数中到货单表体主键不允许为空！"*/);
    }
    if (StringUtils.isEmpty(this.passHid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0142")/*@res "回写到货单的参数中紧急放行单表头主键不允许为空！"*/);
    }
    if (StringUtils.isEmpty(this.getPassBid())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0143")/*@res "回写到货单的参数中紧急放行单表体主键不允许为空！"*/);
    }
    if (StringUtils.isEmpty(this.getVpassbillcode())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0144")/*@res "回写到货单的参数中紧急放行单号不允许为空！"*/);
    }
    if (StringUtils.isEmpty(this.getCpassbollrowno())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0145")/*@res "回写到货单的参数中紧急放行单行号不允许为空！"*/);
    }
  }
}