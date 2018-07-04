package nc.bs.pu.m24.maintain.rewrite;

import java.io.Serializable;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.ic.m45.m24.IParameter45For24;
import nc.vo.pub.lang.UFDouble;

public class Parameter45For24 implements IParameter45For24, Serializable {
  private static final long serialVersionUID = -1302270788220799295L;

  private String bid;

  private String hid;

  private UFDouble ninnum;

  public Parameter45For24(RewritePara rwPara) {
    this.hid = rwPara.getCsrcid();
    this.bid = rwPara.getCsrcbid();
    this.ninnum = rwPara.getNnum();
  }

  @Override
  public String getBid() {
    return this.bid;
  }

  @Override
  public String getHid() {
    return this.hid;
  }

  @Override
  public UFDouble getNinnum() {
    // Ö÷ÊýÁ¿
    return this.ninnum;
  }

}
