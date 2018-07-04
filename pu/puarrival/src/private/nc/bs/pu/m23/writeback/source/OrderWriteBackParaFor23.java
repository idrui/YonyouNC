package nc.bs.pu.m23.writeback.source;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.pu.m21.pu.m23.IOrderWriteBackParaFor23;
import nc.vo.pub.lang.UFDouble;

public class OrderWriteBackParaFor23 implements IOrderWriteBackParaFor23 {

  private boolean isClose = false;

  private boolean isNumUserConfirm;

  private boolean isReturn;

  private RewritePara rwPara;

  public OrderWriteBackParaFor23(RewritePara rwPara) {
    this.rwPara = rwPara;
  }

  @Override
  public String getBBID() {
    // 到货主计划
    return this.rwPara.getCsrcbbid();
  }

  @Override
  public String getBID() {
    return this.rwPara.getCsrcbid();
  }

  @Override
  public UFDouble getDiffNum() {
    // 累计到货主数量
    return this.rwPara.getNnum();
  }

  @Override
  public UFDouble getDiffWasteNum() {
    // 累计途耗主数量
    return this.rwPara.getNnum2();
  }

  @Override
  public String getHID() {
    return this.rwPara.getCsrcid();
  }

  @Override
  public boolean isClose() {
    return this.isClose;
  }

  @Override
  public boolean isNumUserConfirm() {
    return this.isNumUserConfirm;
  }

  @Override
  public boolean isReturn() {
    return this.isReturn;
  }

  /**
   * @param isClose 要设置的 isClose
   */
  public void setClose(boolean isClose) {
    this.isClose = isClose;
  }

  /**
   * @param isNumUserConfirm 要设置的 isNumUserConfirm
   */
  public void setNumUserConfirm(boolean isNumUserConfirm) {
    this.isNumUserConfirm = isNumUserConfirm;
  }

  /**
   * @param isReturn 要设置的 isReturn
   */
  public void setReturn(boolean isReturn) {
    this.isReturn = isReturn;
  }

}
