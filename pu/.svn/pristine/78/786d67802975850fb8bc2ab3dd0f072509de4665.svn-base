/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-6 上午11:03:19
 */
package nc.vo.pu.est.entity.m50;

import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总暂估表头(货物暂估)VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-6 上午11:03:19
 */
public class VmiEstHeaderVO extends GoodsEstVO {

  /**
   * 
   */
  private static final long serialVersionUID = 5958714811810362082L;

  @Override
  public String getBillType() {
    return ICBillType.VmiSum.getCode();
  }

  /** 原币币种 -- 消耗汇总取本币，不支持外币 **/
  @Override
  public String getCorigcurrencyid() {
    return super.getCcurrencyid();
  }

  @Override
  public Integer getFtaxtypeflag() {
    return super.getFtaxtypeflag();
  }

  @Override
  public IDataViewMeta getMetaData() {
    return new DataViewMeta(VmiFIHeaderVO.class);
  }

  @Override
  public UFDouble getNchangestdrate() {
    // 消耗汇总无外币，折本汇率返回1
    return UFDouble.ONE_DBL;
  }

  @Override
  public UFDouble getNorignetprice() {
    // 消耗汇总暂不支持外币，这里取本币
    return super.getNnetprice();
  }

  @Override
  public UFDouble getNorigprice() {
    // 与净价一致
    return this.getNorignetprice();
  }

  @Override
  public UFDouble getNorigtaxnetprice() {
    // 消耗汇总暂不支持外币，这里取本币
    return super.getNtaxnetprice();
  }

  @Override
  public UFDouble getNorigtaxprice() {
    // 与含税净价一致
    return this.getNorigtaxnetprice();
  }

  @Override
  public UFDouble getNprice() {
    // 与本币净价一致
    return super.getNnetprice();
  }

  @Override
  public UFDouble getNtaxprice() {
    // 与本币含净价一致
    return super.getNtaxnetprice();
  }

  /** 应付财务组织 -- 消耗汇总取结算财务组织 **/
  @Override
  public String getPk_apfinanceorg() {
    return super.getPk_financeorg();
  }

  @Override
  public String getPk_order() {
    return null;
  }

  @Override
  public String getPk_order_b() {
    return null;
  }

  @Override
  public String getPk_purchaseOrg() {
    return null;
  }

  /**
   * 获取库存组织
   * wuxla V61
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public String getPk_storeorg() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_STOREORG);
  }

  /** 应付财务组织 -- 消耗汇总忽略 **/
  @Override
  public void setPk_apfinanceorg(String pk_apfinanceorg) {
    //
  }

}
