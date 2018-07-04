package nc.impl.pu.report.supplierestdetail.sqlbuilder;

import nc.bs.pubapp.AppBsContext;
import nc.vo.pu.report.queryinfo.supplierest.PuSupplierEstQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.StringUtils;

/**
 * 供应商暂估明细构造器抽象类
 * 
 * @since 6.0
 * @version 2011-5-21 下午07:04:12
 * @author 田锋涛
 */

public abstract class AbstractEstDetailSqlBuilder {

  private PuSupplierEstQryInfoPara para;

  /**
   * @param para
   */
  public AbstractEstDetailSqlBuilder(PuSupplierEstQryInfoPara para) {
    this.para = para;
  }

  public PuSupplierEstQryInfoPara getPara() {
    return this.para;
  }

  public String getPk_group() {
    return AppBsContext.getInstance().getPkGroup();
  }

  /**
   * 查询语句，各个子类实现
   * 
   * @return
   */
  public abstract String getQuerySql();

  /**
   * 是否需要关联物料表(bd_material)
   * 
   * @return
   */
  protected boolean containMaterial() {
    return this.getQryDlgWhereSql().contains("pk_marbasclass");
  }

  /**
   * 是否需要关联供应商表（bd_supplier）
   * 
   * @return
   */
  protected boolean containSupplier() {
    return this.getQryDlgWhereSql().contains("pk_areacl");
  }

  protected String getDtocostapdate() {
    return "eb.dtocostapdate";
  }

  /**
   * 费用暂估时的where，拼费用的物料和供应商
   * 
   * @return
   */
  protected String getFeeEstCommonWhere() {
    String goodWhere =
        this.getGoodCommonWhere().replace("eb.pk_supplier", "fe.pk_supplier");
    String feeWhere = goodWhere;
    feeWhere = feeWhere.replace("eb.pk_supplier", "fe.pk_supplier");
    // 费用暂估替换
    feeWhere = feeWhere.replace("eb.pk_srcmaterial", "fe.pk_srcfeematerial");
    return " ( " + goodWhere + " or " + feeWhere + ")";
  }

  /**
   * 费用结算时的where，拼费用的物料和供应商
   * 
   * @return
   */
  protected String getFeeSettleCommonWhere() {
    return this.getFeeEstCommonWhere().replace("fe.", "ef.");

  }

  /**
   * 货物暂估或结算时的where
   * 
   * @return
   */
  protected String getGoodCommonWhere() {
    SqlBuilder sb = new SqlBuilder();
    // 已全部结算入库单是否统计
    sb.append("   eb.pk_group ", this.getPk_group());// 当前集团
    if (!this.getPara().isBincldfinish()) {
      sb.append(" and eb.bsettlefinish", UFBoolean.FALSE.toString());
    }
    sb.append(this.getQryDlgWhereSql());
    return sb.toString();
  }

  /**
   * 查询条件语句的拼装，子类有特殊需要自己覆写
   * 
   * @return
   */
  protected String getQryDlgWhereSql() {
    if (StringUtils.isEmpty(this.para.getWheresql())) {
      return "";
    }
    String where = " and " + this.para.getWheresql();
    where = where.replace("pk_group", "eb.pk_group");
    return where;
  }

}
