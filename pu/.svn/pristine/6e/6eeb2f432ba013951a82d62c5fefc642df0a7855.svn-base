package nc.vo.pu.report.queryinfo.eststat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.report.enumeration.PuEstStatGroupEnum;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.scale.PUPubMetaNameConst;
import nc.vo.pub.lang.UFDate;

/**
 * 暂估月统计自由报表查询参数
 * 
 * @since 6.0
 * @version 2011-3-5 下午03:35:07
 * @author yinfy
 */

public class PuEstStatQryInfoPara extends PuQueryInfoPara {

  private static final String CURR = "CURR";

  private static final String PRE = "PRE";

  private static final long serialVersionUID = 4385620516185743144L;

  /** 未冲销暂估金额 */
  private static final String UNESTMNY = "unestmny";

  /** 未冲销暂估数量 */
  private static final String UNESTNUM = "unestnum";

  /**
   * 起始日期
   */
  private UFDate beginDate = null;

  /** 本月入库本月暂估 日期sql */
  private String curStockCurEstDateSql;

  /** 本月入库本月结算 */
  private String curStockCurSettleDateSql;

  private String dlgWherePart = null;

  /**
   * 结束日期
   */
  private UFDate endDate = null;

  /** 本月前暂估本月未结算 */
  private String preEstCurNoSettleDateSql;

  /** 本月前暂估本月结算 */
  private String preEstCurSettleDateSql;

  private String queryTypes = null;

  public UFDate getBeginDate() {
    return this.beginDate;
  }

  public String getCurStockCurEstDateSql() {
    return this.curStockCurEstDateSql;
  }

  public String getCurStockCurSettleDateSql() {
    return this.curStockCurSettleDateSql;
  }

  public String getDlgWherePart() {
    return this.dlgWherePart;
  }

  public UFDate getEndDate() {
    return this.endDate;
  }

  @Override
  public String[] getGroupKeys() {
    String[] groupconds = this.getGroupcondtion().split(",");
    List<String> groupcondls = Arrays.asList(groupconds);
    List<String> groupflds = new ArrayList<String>();
    // 因为财务组织唯一，本位币唯一，这里也作为分组的依据
    groupflds.add("pk_financeorg");
    groupflds.add("ccurrencyid");
    /** 包含物料 **/
    if (groupcondls.contains(PuEstStatGroupEnum.MAR.value())) {
      /** 分组字段：物料 **/
      groupflds.add(SettleBillItemVO.PK_MATERIAL);
      // 单位字段必须添加，不然精度不处理
      groupflds.add(PUPubMetaNameConst.CUNITID);
    }
    /** 包含物料分类 **/
    if (groupcondls.contains(PuEstStatGroupEnum.MARCLASS.value())) {
      /** 分组字段：物料分类 **/
      groupflds.add(MaterialVO.PK_MARBASCLASS);
    }
    /** 包含采购部门 **/
    if (groupcondls.contains(PuEstStatGroupEnum.PUR_DEPT.value())) {
      /** 分组字段：采购部门 **/
      groupflds.add(SettleBillItemVO.PK_DEPT_V);
    }
    /** 包含采购员 **/
    if (groupcondls.contains(PuEstStatGroupEnum.PUR_PSN.value())) {
      /** 分组字段：采购员 **/
      groupflds.add(SettleBillItemVO.PK_PSNDOC);
    }
    /** 包含仓库 **/
    if (groupcondls.contains(PuEstStatGroupEnum.STORDOC.value())) {
      /** 分组字段：仓库 **/
      groupflds.add(PurchaseinFIHeaderVO.PK_STORDOC);
    }
    /** 包含库存组织 **/
    if (groupcondls.contains(PuEstStatGroupEnum.STORE_ORG.value())) {
      /** 分组字段：库存组织 **/
      groupflds.add(VmiFIHeaderVO.PK_STOREORG_V);
    }
    /** 包含供应商 **/
    if (groupcondls.contains(PuEstStatGroupEnum.SUPPLIER.value())) {
      /** 分组字段：供应商 **/
      groupflds.add(SettleBillItemVO.PK_SUPPLIER);
      // /** 分组字段：供应商名称 **/
      // groupflds.add(PuEstStatQryInfoPara.SUPPLIERNAME);
    }
    /** 包含库管员 **/
    if (groupcondls.contains(PuEstStatGroupEnum.WHSMANAGER.value())) {
      /** 分组字段：库管员 **/
      groupflds.add(PurchaseinFIHeaderVO.CWHSMANAGERID);
    }
    return groupflds.toArray(new String[groupflds.size()]);
  }

  /**
   * 需要隐藏的字段
   */
  @Override
  public String[] getHideKeys() {
    String[] groupconds = this.getGroupcondtion().split(",");
    List<String> groupcondls = Arrays.asList(groupconds);
    List<String> hideflds = new ArrayList<String>();
    // hideflds.add("cunitid");
    // hideflds.add("ccurrencyid");
    // hideflds.add("PK_MATERIAL.pk_measdoc");

    /** 不包含供应商 **/
    if (!groupcondls.contains(PuEstStatGroupEnum.SUPPLIER.value())) {
      /** 隐藏字段：供应商 **/
      hideflds.add(SettleBillItemVO.PK_SUPPLIER);
      hideflds.add("this.pk_supplier.name");
    }
    /** 不包含库存组织 **/
    if (!groupcondls.contains(PuEstStatGroupEnum.STORE_ORG.value())) {
      /** 隐藏字段：库存组织 **/
      hideflds.add(VmiFIHeaderVO.PK_STOREORG_V);
      hideflds.add("this.pk_storeorg_v.name");
    }
    /** 不包含采购部门 **/
    if (!groupcondls.contains(PuEstStatGroupEnum.PUR_DEPT.value())) {
      /** 隐藏字段：采购部门 **/
      hideflds.add(SettleBillItemVO.PK_DEPT_V);
      hideflds.add("this.pk_dept_v.name");
    }
    /** 不包含采购员 **/
    if (!groupcondls.contains(PuEstStatGroupEnum.PUR_PSN.value())) {
      /** 隐藏字段：采购员 **/
      hideflds.add(SettleBillItemVO.PK_PSNDOC);
      hideflds.add("this.pk_psndoc.name");
    }
    /** 不包含物料 **/
    if (!groupcondls.contains(PuEstStatGroupEnum.MAR.value())) {
      /** 隐藏字段：物料 **/
      hideflds.add("pk_material");
      hideflds.add("this.pk_material." + MaterialVO.CODE);
      /** 隐藏字段：物料名称 **/
      hideflds.add("this.pk_material." + MaterialVO.NAME);
      /** 隐藏字段：物料规格 **/
      hideflds.add("this.pk_material." + MaterialVO.MATERIALSPEC);
      /** 隐藏字段：物料型号 **/
      hideflds.add("this.pk_material." + MaterialVO.MATERIALTYPE);
    }
    /** 不包含物料分类 **/
    if (!groupcondls.contains(PuEstStatGroupEnum.MARCLASS.value())) {
      /** 隐藏字段：物料分类 **/
      hideflds.add(MaterialVO.PK_MARBASCLASS);
      hideflds.add("this.pk_marbasclass.name");
      hideflds.add("this.pk_material.pk_marbasclass.name");
    }
    /** 不包含仓库 **/
    if (!groupcondls.contains(PuEstStatGroupEnum.STORDOC.value())) {
      /** 隐藏字段：仓库 **/
      hideflds.add(PurchaseinFIHeaderVO.PK_STORDOC);
      hideflds.add("this.pk_stordoc.name");
    }
    /** 不包含库管员 **/
    if (!groupcondls.contains(PuEstStatGroupEnum.WHSMANAGER.value())) {
      /** 隐藏字段：库管员 **/
      hideflds.add(PurchaseinFIHeaderVO.CWHSMANAGERID);
      hideflds.add("this.cwhsmanagerid.name");
    }
    return hideflds.toArray(new String[hideflds.size()]);
  }

  public String getPreEstCurNoSettleDateSql() {
    return this.preEstCurNoSettleDateSql;
  }

  public String getPreEstCurSettleDateSql() {
    return this.preEstCurSettleDateSql;
  }

  public String getQueryTypes() {
    return this.queryTypes;
  }

  @Override
  public String[] getSortKeys() {
    String[] groupconds = this.getGroupcondtion().split(",");
    List<String> groupcondls = Arrays.asList(groupconds);
    List<String> sortFields = new ArrayList<String>();
    // 供应商排序
    if (groupcondls.contains(PuEstStatGroupEnum.SUPPLIER.value())) {
      sortFields.add(SettleBillItemVO.PK_SUPPLIER);
    }
    // 物料排序
    if (groupcondls.contains(PuEstStatGroupEnum.MAR.value())) {
      sortFields.add(SettleBillItemVO.PK_MATERIAL);
    }

    return sortFields.toArray(new String[sortFields.size()]);
    // return null;
  }

  /**
   * 得到合计字段
   */
  @Override
  public String[] getTotalKeys() {
    /**
     * 合计字段：结算数量,货物结算金额,冲销暂估金额 ,未冲销暂估数量,未冲销暂估金额
     */
    return new String[] {
      PuEstStatQryInfoPara.PRE + SettleBillItemVO.NSETTLENUM,
      PuEstStatQryInfoPara.PRE + SettleBillItemVO.NGOODSMONEY,
      PuEstStatQryInfoPara.PRE + SettleBillItemVO.NCLASHESTMONEY,
      PuEstStatQryInfoPara.CURR + SettleBillItemVO.NSETTLENUM,
      PuEstStatQryInfoPara.CURR + SettleBillItemVO.NGOODSMONEY,
      PuEstStatQryInfoPara.CURR + SettleBillItemVO.NCLASHESTMONEY,
      PuEstStatQryInfoPara.PRE + PuEstStatQryInfoPara.UNESTNUM,
      PuEstStatQryInfoPara.PRE + PuEstStatQryInfoPara.UNESTMNY,
      PuEstStatQryInfoPara.CURR + PuEstStatQryInfoPara.UNESTNUM,
      PuEstStatQryInfoPara.CURR + PuEstStatQryInfoPara.UNESTMNY
    };
  }

  public void setBeginDate(UFDate beginDate) {
    this.beginDate = beginDate;
  }

  public void setCurStockCurEstDateSql(String curStockCurEstDateSql) {
    this.curStockCurEstDateSql = curStockCurEstDateSql;
  }

  public void setCurStockCurSettleDateSql(String curStockCurSettleDateSql) {
    this.curStockCurSettleDateSql = curStockCurSettleDateSql;
  }

  public void setDlgWherePart(String dlgWherePart) {
    this.dlgWherePart = dlgWherePart;
  }

  public void setEndDate(UFDate endDate) {
    this.endDate = endDate;
  }

  public void setPreEstCurNoSettleDateSql(String preEstCurNoSettleDateSql) {
    this.preEstCurNoSettleDateSql = preEstCurNoSettleDateSql;
  }

  public void setPreEstCurSettleDateSql(String preEstCurSettleDateSql) {
    this.preEstCurSettleDateSql = preEstCurSettleDateSql;
  }

  public void setQueryTypes(String queryTypes) {
    this.queryTypes = queryTypes;
  }
}
