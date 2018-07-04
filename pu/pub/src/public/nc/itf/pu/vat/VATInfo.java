package nc.itf.pu.vat;

import java.io.Serializable;

import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.vo.pub.lang.UFBoolean;

public class VATInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * �Ƿ�������˰
   */
  private UFBoolean[] opptaxflags;

  /**
   * ��Ӧ��VATע����
   */
  private String[] supvatcodes;

  /**
   * VATע����
   */
  private String[] vatcodes;

  /**
   * VAT˰��˰��
   */
  private VATInfoVO[] vatinfovos;

  public UFBoolean[] getOpptaxflags() {
    return this.opptaxflags;
  }

  public String[] getSupvatcodes() {
    return this.supvatcodes;
  }

  public String[] getVatcodes() {
    return this.vatcodes;
  }

  public VATInfoVO[] getVatinfovos() {
    return this.vatinfovos;
  }

  public void setOpptaxflags(UFBoolean[] opptaxflags) {
    this.opptaxflags = opptaxflags;
  }

  public void setSupvatcodes(String[] supvatcodes) {
    this.supvatcodes = supvatcodes;
  }

  public void setVatcodes(String[] vatcodes) {
    this.vatcodes = vatcodes;
  }

  public void setVatinfovos(VATInfoVO[] vatinfovos) {
    this.vatinfovos = vatinfovos;
  }
}
