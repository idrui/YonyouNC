package nc.vo.pu.est.rule;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>获得暂估询价的准备信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-17 下午03:36:12
 */
public interface IEstPriceQueryInfoProvide {

  public String getBID();

  /** 入库折本汇率 **/
  public UFDouble getBillExchgRate();

  /** 入库（汇总）金额 **/
  public UFDouble getBillMny();

  /** 入库（汇总）数量 **/
  public UFDouble getBillNum();

  /** 入库（汇总）金额 **/
  public UFDouble getBillOMny();

  /** 入库（汇总）单价 **/
  public UFDouble getBillOPrice();

  /** 入库（汇总）税额 **/
  public UFDouble getBillOTax();

  /** 入库（汇总）含税单价 **/
  public UFDouble getBillOTaxPrice();

  /** 入库（汇总）价税合计 **/
  public UFDouble getBillOTotalmny();

  /** 入库（汇总）单价 **/
  public UFDouble getBillPrice();

  /** 供应商 **/
  public String getBillSupplier();

  /** 入库（汇总）税额 **/
  public UFDouble getBillTax();

  /** 入库（汇总）含税单价 **/
  public UFDouble getBillTaxPrice();

  /** 入库（汇总）价税合计 **/
  public UFDouble getBillTotalmny();

  /** 源头单据表体 getter 方法 */
  public String getCfirstbid();

  /** 源头单据表头 getter 方法 */
  public String getCfirstid();

  /** 得到成本域 **/
  public String getCostregion();

  /** 来源单据表体 getter 方法 */
  public String getCsourcebid();

  /** 来源单据表头 getter 方法 */
  public String getCsourceid();

  /** 暂估日期 **/
  public UFDate getEstDate();

  /** 暂估折本汇率 **/
  public UFDouble getExchgRate();

  public String getHID();

  public UFDouble getNcalcostmny();

  public UFDouble getNcaltaxmny();

  /**
   * <p>
   * 使用场景：不可抵扣税率
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public UFDouble getNestnosubtaxrate();

  public UFDouble getNnosubtax();

  /** 暂估数量 **/
  public UFDouble getnum();

  /** 暂估外币 **/
  public String getorigcurr();

  /** 结算财务组织 **/
  public String getPk_fiorg();

  /** 本币 **/
  public String getPk_loccurr();

  /** 物料VID **/
  public String getPk_material();

  /** 采购订单头ID **/
  public String getPk_order();

  /** 采购订单体ID **/
  public String getPk_order_b();

  /** 采购组织 **/
  public String getPk_purchaseOrg();

  /** 物料OID **/
  public String getPk_srcmaterial();

  /** 暂估税率 **/
  public UFDouble getTaxRate();

  /**
   * 暂估扣税类别 0－应税内含 1－应税外加 2－不计税
   */
  public int getTaxtype();

  /** 源头单据号 getter 方法 */
  public String getVfirstcode();

  /** 源头单据行号 getter 方法 */
  public String getVfirstrowno();

  /** 来源单据号 getter 方法 */
  public String getVsourcecode();

  /** 来源单据行号 getter 方法 */
  public String getVsourcerowno();

}
