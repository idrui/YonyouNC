/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-29 上午09:18:11
 */
package nc.vo.pu.est.entity;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>费用暂估顶层VO(子VO)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-29 上午09:18:11
 */
public class FeeEstVO extends SuperVO {
  /** 逆向征税 */
  public static final String BOPPTAXFLAG = "bopptaxflag";

  /** 传应付标志 **/
  public static final String BTOAP = "btoap";

  /** 传成本标志 **/
  public static final String BTOIA = "btoia";

  /** 三角贸易 */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /** 收货国家/地区 */
  public static final String CRECECOUNTRYID = "crececountryid";

  /** 发货国家/地区 */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /** 税码 */
  public static final String CTAXCODEID = "ctaxcodeid";

  /** 报税国家/地区 */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /** 暂估日期 **/
  public static final String DESTDATE = "destdate";

  /** dr **/
  public static final String DR = "dr";

  /** 购销类型 */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /** 扣税类别 */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /** 计成本金额 */
  public static final String NCALCOSTMNY = "ncalcostmny";

  /** 计税金额 */
  public static final String NCALTAXMNY = "ncaltaxmny";

  /** 折本汇率 **/
  public static final String NESTEXCHGRATE = "nestexchgrate";

  /** 本币无税金额 **/
  public static final String NESTMNY = "nestmny";

  /** 原币无税金额 **/
  public static final String NESTOMNY = "nestomny";

  /** 原币价税合计 **/
  public static final String NESTOTOTALMNY = "nestototalmny";

  /** 本币税额 **/
  public static final String NESTTAXMNY = "nesttaxmny";

  /** 税率 **/
  public static final String NESTTAXRATE = "nesttaxrate";

  /** 本币价税合计 **/
  public static final String NESTTOTALMNY = "nesttotalmny";

  /** 不可抵扣税额 */
  public static final String NNOSUBTAX = "nnosubtax";

  /** 不可抵扣税率 */
  public static final String NNOSUBTAXRATE = "nnosubtaxrate";

  /** 成本要素 **/
  public static final String PK_COSTFACTOR = "pk_costfactor";

  /** 币种 **/
  public static final String PK_ESTCURRENCY = "pk_estcurrency";

  /** 暂估人 **/
  public static final String PK_ESTPSN = "pk_estpsn";

  /** 费用项 **/
  public static final String PK_FEEMATERIAL = "pk_feematerial";

  /** 财务组织 **/
  public static final String PK_FINANCEORG = "pk_financeorg";

  /** 暂估费用物料第一次结算的结算单 **/
  public static final String PK_FIRSTSETTLE = "pk_firstsettle";

  /** 暂估费用物料第一次结算的结算单行 **/
  public static final String PK_FIRSTSETTLE_B = "pk_firstsettle_b";

  /** 集团 **/
  public static final String PK_GROUP = "pk_group";

  /** 本币 **/
  public static final String PK_LOCALCURRENCY = "pk_localcurrency";

  /** 暂估费用物料 **/
  public static final String PK_SRCFEEMATERIAL = "pk_srcfeematerial";

  /** 采购入财务头 **/
  public static final String PK_STOCKPS = "pk_stockps";

  /** 采购入财务体_主键 **/
  public static final String PK_STOCKPS_B = "pk_stockps_b";

  /** 采购入费用暂估明细 **/
  public static final String PK_STOCKPS_FEE = "pk_stockps_fee";

  /** 供应商名称 **/
  public static final String PK_SUPPLIER = "pk_supplier";

  /** 时间戳 **/
  public static final String TS = "ts";

  /**
   * 
   */
  private static final long serialVersionUID = 4595030884504463762L;

  /** 成本要素号，临时属性 **/
  private transient int costfacotorNum;

  /** 本行对应的费用分摊VO主键--用于按分摊VO分行的情况 **/
  private transient String pk_stockps_fd;

  /** 逆向征税 getter 方法 */
  public UFBoolean getBopptaxflag() {
    return (UFBoolean) this.getAttributeValue(FeeEstVO.BOPPTAXFLAG);
  }

  /** 传应付标志 **/
  public UFBoolean getBtoap() {
    return (UFBoolean) this.getAttributeValue(FeeEstVO.BTOAP);
  }

  /** 传成本标志 **/
  public UFBoolean getBtoia() {
    return (UFBoolean) this.getAttributeValue(FeeEstVO.BTOIA);
  }

  /** 三角贸易 getter 方法 */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(FeeEstVO.BTRIATRADEFLAG);
  }

  /**
   * @return costfacotorNum 成本要素序号
   */
  public int getCostfacotorNum() {
    return this.costfacotorNum;
  }

  /** 收货国家/地区 getter 方法 */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(FeeEstVO.CRECECOUNTRYID);
  }

  /** 发货国家/地区 getter 方法 */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(FeeEstVO.CSENDCOUNTRYID);
  }

  /** 税码 getter 方法 */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(FeeEstVO.CTAXCODEID);
  }

  /** 报税国家/地区 getter 方法 */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(FeeEstVO.CTAXCOUNTRYID);
  }

  /** 暂估日期 **/
  public UFDate getDestdate() {
    return (UFDate) this.getAttributeValue(FeeEstVO.DESTDATE);
  }

  /** dr **/
  public Integer getDr() {
    return (Integer) this.getAttributeValue(FeeEstVO.DR);
  }

  /** 购销类型 getter 方法 */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(FeeEstVO.FBUYSELLFLAG);
  }

  /** 扣税类别 getter 方法 */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(FeeEstVO.FTAXTYPEFLAG);
  }

  /** 计成本金额 getter 方法 */
  public UFDouble getNcalcostmny() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NCALCOSTMNY);
  }

  /** 计税金额 getter 方法 */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NCALTAXMNY);
  }

  /** 折本汇率 **/
  public UFDouble getNestexchgrate() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTEXCHGRATE);
  }

  /** 本币无税金额 **/
  public UFDouble getNestmny() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTMNY);
  }

  /** 原币无税金额 **/
  public UFDouble getNestomny() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTOMNY);
  }

  /** 原币价税合计 **/
  public UFDouble getNestototalmny() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTOTOTALMNY);
  }

  /** 本币税额 **/
  public UFDouble getNesttaxmny() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTTAXMNY);
  }

  /** 税率 **/
  public UFDouble getNesttaxrate() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTTAXRATE);
  }

  /** 本币价税合计 **/
  public UFDouble getNesttotalmny() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTTOTALMNY);
  }

  /** 不可抵扣税额 getter 方法 */
  public UFDouble getNnosubtax() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NNOSUBTAX);
  }

  /** 不可抵扣税率 getter 方法 */
  public UFDouble getNnosubtaxrate() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NNOSUBTAXRATE);
  }

  /** 成本要素 **/
  public String getPk_costfactor() {
    return (String) this.getAttributeValue(FeeEstVO.PK_COSTFACTOR);
  }

  /** 币种 **/
  public String getPk_estcurrency() {
    return (String) this.getAttributeValue(FeeEstVO.PK_ESTCURRENCY);
  }

  /** 暂估人 **/
  public String getPk_estpsn() {
    return (String) this.getAttributeValue(FeeEstVO.PK_ESTPSN);
  }

  /** 费用项 **/
  public String getPk_feematerial() {
    return (String) this.getAttributeValue(FeeEstVO.PK_FEEMATERIAL);
  }

  /** 财务组织 **/
  public String getPk_financeorg() {
    return (String) this.getAttributeValue(FeeEstVO.PK_FINANCEORG);
  }

  /** 暂估费用物料第一次结算的结算单 **/
  public String getPk_firstsettle() {
    return (String) this.getAttributeValue(FeeEstVO.PK_FIRSTSETTLE);
  }

  /** 暂估费用物料第一次结算的结算单行 **/
  public String getPk_firstsettle_b() {
    return (String) this.getAttributeValue(FeeEstVO.PK_FIRSTSETTLE_B);
  }

  /** 集团 **/
  public String getPk_group() {
    return (String) this.getAttributeValue(FeeEstVO.PK_GROUP);
  }

  /** 本币 **/
  public String getPk_localcurrency() {
    return (String) this.getAttributeValue(FeeEstVO.PK_LOCALCURRENCY);
  }

  /** 暂估费用物料 **/
  public String getPk_srcfeematerial() {
    return (String) this.getAttributeValue(FeeEstVO.PK_SRCFEEMATERIAL);
  }

  /** 采购入财务头 **/
  public String getPk_stockps() {
    return (String) this.getAttributeValue(FeeEstVO.PK_STOCKPS);
  }

  /** 采购入财务体_主键 **/
  public String getPk_stockps_b() {
    return (String) this.getAttributeValue(FeeEstVO.PK_STOCKPS_B);
  }

  /**
   * @return pk_stockps_fd 行对应的费用分摊VO主键--用于按分摊VO分行的情况
   */
  public String getPk_stockps_fd() {
    return this.pk_stockps_fd;
  }

  /** 采购入费用暂估明细 **/
  public String getPk_stockps_fee() {
    return (String) this.getAttributeValue(FeeEstVO.PK_STOCKPS_FEE);
  }

  /** 供应商名称 **/
  public String getPk_supplier() {
    return (String) this.getAttributeValue(FeeEstVO.PK_SUPPLIER);
  }

  /** 时间戳 **/
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(FeeEstVO.TS);
  }

  /** 逆向征税 setter 方法 */
  public void setBopptaxflag(UFBoolean bopptaxflag) {
    this.setAttributeValue(FeeEstVO.BOPPTAXFLAG, bopptaxflag);
  }

  /** 传应付标志 **/
  public void setBtoap(UFBoolean btoap) {
    this.setAttributeValue(FeeEstVO.BTOAP, btoap);
  }

  /** 传成本标志 **/
  public void setBtoia(UFBoolean btoia) {
    this.setAttributeValue(FeeEstVO.BTOIA, btoia);
  }

  /** 三角贸易 setter 方法 */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(FeeEstVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /**
   * @param costfacotorNum 要设置的 成本要素序号
   */
  public void setCostfacotorNum(int costfacotorNum) {
    this.costfacotorNum = costfacotorNum;
  }

  /** 收货国家/地区 setter 方法 */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(FeeEstVO.CRECECOUNTRYID, crececountryid);
  }

  /** 发货国家/地区 setter 方法 */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(FeeEstVO.CSENDCOUNTRYID, csendcountryid);
  }

  /** 税码 setter 方法 */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(FeeEstVO.CTAXCODEID, ctaxcodeid);
  }

  /** 报税国家/地区 setter 方法 */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(FeeEstVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /** 暂估日期 **/
  public void setDestdate(UFDate destdate) {
    this.setAttributeValue(FeeEstVO.DESTDATE, destdate);
  }

  /** dr **/
  public void setDr(Integer dr) {
    this.setAttributeValue(FeeEstVO.DR, dr);
  }

  /** 购销类型 setter 方法 */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(FeeEstVO.FBUYSELLFLAG, fbuysellflag);
  }

  /** 扣税类别 setter 方法 */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(FeeEstVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /** 计成本金额 setter 方法 */
  public void setNcalcostmny(UFDouble ncalcostmny) {
    this.setAttributeValue(FeeEstVO.NCALCOSTMNY, ncalcostmny);
  }

  /** 计税金额 setter 方法 */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(FeeEstVO.NCALTAXMNY, ncaltaxmny);
  }

  /** 折本汇率 **/
  public void setNestexchgrate(UFDouble nestexchgrate) {
    this.setAttributeValue(FeeEstVO.NESTEXCHGRATE, nestexchgrate);
  }

  /** 本币无税金额 **/
  public void setNestmny(UFDouble nestmny) {
    this.setAttributeValue(FeeEstVO.NESTMNY, nestmny);
  }

  /** 原币无税金额 **/
  public void setNestomny(UFDouble nestomny) {
    this.setAttributeValue(FeeEstVO.NESTOMNY, nestomny);
  }

  /** 原币价税合计 **/
  public void setNestototalmny(UFDouble nestototalmny) {
    this.setAttributeValue(FeeEstVO.NESTOTOTALMNY, nestototalmny);
  }

  /** 本币税额 **/
  public void setNesttaxmny(UFDouble nesttaxmny) {
    this.setAttributeValue(FeeEstVO.NESTTAXMNY, nesttaxmny);
  }

  /** 税率 **/
  public void setNesttaxrate(UFDouble nesttaxrate) {
    this.setAttributeValue(FeeEstVO.NESTTAXRATE, nesttaxrate);
  }

  /** 本币价税合计 **/
  public void setNesttotalmny(UFDouble nesttotalmny) {
    this.setAttributeValue(FeeEstVO.NESTTOTALMNY, nesttotalmny);
  }

  /** 不可抵扣税额 setter 方法 */
  public void setNnosubtax(UFDouble nnosubtax) {
    this.setAttributeValue(FeeEstVO.NNOSUBTAX, nnosubtax);
  }

  /** 不可抵扣税率 setter 方法 */
  public void setNnosubtaxrate(UFDouble nnosubtaxrate) {
    this.setAttributeValue(FeeEstVO.NNOSUBTAXRATE, nnosubtaxrate);
  }

  /** 成本要素 **/
  public void setPk_costfactor(String pk_costfactor) {
    this.setAttributeValue(FeeEstVO.PK_COSTFACTOR, pk_costfactor);
  }

  /** 币种 **/
  public void setPk_estcurrency(String pk_estcurrency) {
    this.setAttributeValue(FeeEstVO.PK_ESTCURRENCY, pk_estcurrency);
  }

  /** 暂估人 **/
  public void setPk_estpsn(String pk_estpsn) {
    this.setAttributeValue(FeeEstVO.PK_ESTPSN, pk_estpsn);
  }

  /** 费用项 **/
  public void setPk_feematerial(String pk_feematerial) {
    this.setAttributeValue(FeeEstVO.PK_FEEMATERIAL, pk_feematerial);
  }

  /** 财务组织 **/
  public void setPk_financeorg(String pk_financeorg) {
    this.setAttributeValue(FeeEstVO.PK_FINANCEORG, pk_financeorg);
  }

  /** 暂估费用物料第一次结算的结算单 **/
  public void setPk_firstsettle(String pk_firstsettle) {
    this.setAttributeValue(FeeEstVO.PK_FIRSTSETTLE, pk_firstsettle);
  }

  /** 暂估费用物料第一次结算的结算单行 **/
  public void setPk_firstsettle_b(String pk_firstsettle_b) {
    this.setAttributeValue(FeeEstVO.PK_FIRSTSETTLE_B, pk_firstsettle_b);
  }

  /** 集团 **/
  public void setPk_group(String pk_group) {
    this.setAttributeValue(FeeEstVO.PK_GROUP, pk_group);
  }

  /** 本币 **/
  public void setPk_localcurrency(String pk_localcurrency) {
    this.setAttributeValue(FeeEstVO.PK_LOCALCURRENCY, pk_localcurrency);
  }

  /** 暂估费用物料 **/
  public void setPk_srcfeematerial(String pk_srcfeematerial) {
    this.setAttributeValue(FeeEstVO.PK_SRCFEEMATERIAL, pk_srcfeematerial);
  }

  /** 采购入财务头 **/
  public void setPk_stockps(String pk_stockps) {
    this.setAttributeValue(FeeEstVO.PK_STOCKPS, pk_stockps);
  }

  /** 采购入财务体_主键 **/
  public void setPk_stockps_b(String pk_stockps_b) {
    this.setAttributeValue(FeeEstVO.PK_STOCKPS_B, pk_stockps_b);
  }

  /**
   * @param pk_stockps_fd 行对应的费用分摊VO主键--用于按分摊VO分行的情况
   */
  public void setPk_stockps_fd(String pk_stockps_fd) {
    this.pk_stockps_fd = pk_stockps_fd;
  }

  /** 采购入费用暂估明细 **/
  public void setPk_stockps_fee(String pk_stockps_fee) {
    this.setAttributeValue(FeeEstVO.PK_STOCKPS_FEE, pk_stockps_fee);
  }

  /** 供应商名称 **/
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(FeeEstVO.PK_SUPPLIER, pk_supplier);
  }

  /** 时间戳 **/
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(FeeEstVO.TS, ts);
  }

}
