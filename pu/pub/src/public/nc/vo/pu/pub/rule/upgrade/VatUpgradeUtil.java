package nc.vo.pu.pub.rule.upgrade;

import nc.vo.pu.pub.enumeration.EnumDiscounttaxtype;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 如果为应税外加、应税内含：仍保留原来的扣税类别。
 * 如果为不计税，升级为应税外加，同时税率升为0；
 * 
 * @since 6.0
 * @version 2012-3-22 上午08:51:49
 * @author wuxla
 */
public class VatUpgradeUtil {
  private String ftaxtypeflag = PuAttrNameEnum.ftaxtypeflag.name();

  private String nmny = PuAttrNameEnum.nmny.name();

  private String ntaxmny = PuAttrNameEnum.ntaxmny.name();

  private String ntaxrate = PuAttrNameEnum.ntaxrate.name();

  /**
   * 字段和默认值一样
   */
  public VatUpgradeUtil() {
  }

  /**
   * 应税内含：本币价税合计
   * 应税外加：本币无税金额
   * 不计税时本币价税合计和本币无税金额相等
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public String getNcaltaxmnyValue() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("(");
    sql.appendCaseWhen(this.ftaxtypeflag + "="
        + EnumDiscounttaxtype.TAXOUT.toInteger().toString(), this.nmny,
        this.ntaxmny);
    sql.append(")");
    return sql.toString();
  }

  /**
   * 如果为不计税，升级为应税外加，同时税率升为0；
   * <p>
   * 使用场景：
   * <ul>
   * <li>采购订单、采购发票、期初暂估单 、估处理--货物暂估、消耗汇总暂估--货物暂估
   * </ul>
   * 
   * @return
   */
  public String getTaxrateValue() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("(");
    sql.appendCaseWhen(this.ftaxtypeflag + " = " + 2, String.valueOf(0),
        this.ntaxrate);
    sql.append(")");
    return sql.toString();
  }

  /**
   * 如果为不计税，升级为应税外加
   * <p>
   * 使用场景：
   * <ul>
   * <li>采购订单、采购发票、期初暂估单 、估处理--货物暂估、消耗汇总暂估--货物暂估
   * </ul>
   * 
   * @return
   */
  public String getTaxtypeFlagValue() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("(");
    sql.appendCaseWhen(this.ftaxtypeflag + " = " + 2,
        EnumDiscounttaxtype.TAXOUT.toInteger().toString(), this.ftaxtypeflag);
    sql.append(")");
    return sql.toString();
  }

  public void setFtaxtypeflag(String ftaxtypeflag) {
    this.ftaxtypeflag = ftaxtypeflag;
  }

  public void setNmny(String nmny) {
    this.nmny = nmny;
  }

  public void setNtaxmny(String ntaxmny) {
    this.ntaxmny = ntaxmny;
  }

  public void setNtaxrate(String ntaxrate) {
    this.ntaxrate = ntaxrate;
  }
}
