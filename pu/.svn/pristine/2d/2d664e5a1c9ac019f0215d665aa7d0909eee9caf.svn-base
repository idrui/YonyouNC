package nc.itf.pu.reference.ic;

import nc.pubitf.ic.m4a.IParameter4AForFeeSettle;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>库存其他入库单提供给采购费用结算的参数
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-9-8 上午10:21:58
 */
public class M4AWriteParaForFeeSettle implements IParameter4AForFeeSettle {

  private String bid;

  private String hid;

  private Integer nfeesettlecount = Integer.valueOf(0);

  @Override
  public String getBid() {
    return this.bid;
  }

  @Override
  public String getHid() {
    return this.hid;
  }

  public int getNaccumfeesettlecount() {
    return 0;
  }

  @Override
  public Integer getNfeesettlecount() {
    return this.nfeesettlecount;
  }

  public void setBid(String bid) {
    this.bid = bid;
  }

  public void setHid(String hid) {
    this.hid = hid;
  }

  public void setNfeesettlecount(Integer nfeesettlecount) {
    this.nfeesettlecount = nfeesettlecount;
  }
}
