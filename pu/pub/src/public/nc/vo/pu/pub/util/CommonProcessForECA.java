package nc.vo.pu.pub.util;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.database.TempTable;
import nc.pubitf.mmf.busi.eco.IBill4ECAResult;
import nc.pubitf.mmf.busi.eco.IECAItemMaterialPara;
import nc.pubitf.mmf.busi.eco.IECAItemPara;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 提供给离散制造ECA，用于查询请购单和采购订单信息的公共处理部分
 * 
 * @since 6.3.1
 * @version 2013-8-19 下午01:59:12
 * @author fanly3
 */
public class CommonProcessForECA {
  // 参数临时表字段名常量
  public static final String ANALYZEDATEFROM = "analyzedatefrom";

  public static final String ANALYZEDATETO = "analyzedateto";

  public static final String ANALYZEORG = "analyzeorg";

  public static final String CMATERIALVID = "cmaterialvid";

  public static final String CVENDORID = "cvendorid";

  public static final String CPRODUCTORID = "cproductorid";

  public static final String CPROJECTID = "cprojectid";

  public static final String CCUSTOMERID = "ccustomerid";

  public static final String VFREE1 = "vfree1";

  public static final String VFREE2 = "vfree2";

  public static final String VFREE3 = "vfree3";

  public static final String VFREE4 = "vfree4";

  public static final String VFREE5 = "vfree5";

  public static final String VFREE6 = "vfree6";

  public static final String VFREE7 = "vfree7";

  public static final String VFREE8 = "vfree8";

  public static final String VFREE9 = "vfree9";

  public static final String VFREE10 = "vfree10";

  private IECAItemPara[] paras;

  private IBill4ECAResult temptable;

  public CommonProcessForECA(IECAItemPara[] paras, IBill4ECAResult temptable) {
    this.paras = paras;
    this.temptable = temptable;
  }

  /**
   * 构造制造参数值的临时表
   * 
   * @param tempTableName 临时表名
   * @return 构造的临时表名
   */
  public String constructParasTempTable(String tempTableName) {
    String[] columns =
        new String[] {
          ANALYZEDATEFROM, ANALYZEDATETO, ANALYZEORG, CMATERIALVID, CVENDORID,
          CPRODUCTORID, CPROJECTID, CCUSTOMERID, VFREE1, VFREE2, VFREE3,
          VFREE4, VFREE5, VFREE6, VFREE7, VFREE8, VFREE9, VFREE10
        };
    String[] columnTypes =
        new String[] {
          "char(19)", "char(19)", "char(20)", "char(20)", "char(20)",
          "char(20)", "char(20)", "char(20)", "varchar(101)", "varchar(101)",
          "varchar(101)", "varchar(101)", "varchar(101)", "varchar(101)",
          "varchar(101)", "varchar(101)", "varchar(101)", "varchar(101)"
        };
    JavaType[] types =
        new JavaType[] {
          JavaType.String, JavaType.String, JavaType.String, JavaType.String,
          JavaType.String, JavaType.String, JavaType.String, JavaType.String,
          JavaType.String, JavaType.String, JavaType.String, JavaType.String,
          JavaType.String, JavaType.String, JavaType.String, JavaType.String,
          JavaType.String, JavaType.String
        };
    List<List<Object>> data = this.getParasData();

    TempTable tempTable = new TempTable();
    String ttName =
        tempTable
            .getTempTable(tempTableName, columns, columnTypes, types, data);

    // TODO 多语位置换一下
    if (StringUtil.isEmpty(ttName)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0139")/*
                                                                   * @res
                                                                   * "生成临时表失败"
                                                                   */);
    }

    return ttName;
  }

  /**
   * 返回制造参数插入临时表时所构建的数据
   * 
   * @return
   */
  private List<List<Object>> getParasData() {
    List<List<Object>> data = new ArrayList<List<Object>>();
    for (IECAItemPara para : paras) {
      IECAItemMaterialPara[] materialParas = para.getMatrials();
      for (IECAItemMaterialPara materPara : materialParas) {
        List<Object> list = new ArrayList<Object>();
        list.add(para.getAnalyzedatefrom());
        list.add(para.getAnalyzedateto());
        list.add(materPara.getAnalyzeorg());
        list.add(materPara.getCmaterialvid());
        list.add(materPara.getCvendorid());
        list.add(materPara.getCproductorid());
        list.add(materPara.getCprojectid());
        list.add(materPara.getCcustomerid());
        list.add(materPara.getVfree1());
        list.add(materPara.getVfree2());
        list.add(materPara.getVfree3());
        list.add(materPara.getVfree4());
        list.add(materPara.getVfree5());
        list.add(materPara.getVfree6());
        list.add(materPara.getVfree7());
        list.add(materPara.getVfree8());
        list.add(materPara.getVfree9());
        list.add(materPara.getVfree10());

        data.add(list);
      }
    }
    return data;
  }

  /**
   * 构造insert语句
   * 
   * @return insert语句
   */
  public String constructInsertSql() {
    SqlBuilder insertSql = new SqlBuilder();
    String tempTableName = temptable.getTempTableName();
    insertSql.append("insert into " + tempTableName + "(");
    insertSql.append(temptable.getBillTypeCode() + ",");
    insertSql.append(temptable.getPk_org() + ",");
    insertSql.append(temptable.getPk_org_v() + ",");
    insertSql.append(temptable.getBillid() + ",");
    insertSql.append(temptable.getBillcode() + ",");
    insertSql.append(temptable.getBill_bid() + ",");
    insertSql.append(temptable.getRowno() + ",");
    insertSql.append(temptable.getBilldate() + ",");
    insertSql.append(temptable.getSupplyDate() + ",");
    insertSql.append(temptable.getWarehouseid() + ",");
    insertSql.append(temptable.getMaterialoid() + ",");
    insertSql.append(temptable.getMaterialvid() + ",");
    insertSql.append(temptable.getVendorid() + ",");
    insertSql.append(temptable.getProductorid() + ",");
    insertSql.append(temptable.getProjectid() + ",");
    insertSql.append(temptable.getCustomerid() + ",");
    insertSql.append(temptable.getFree1() + ",");
    insertSql.append(temptable.getFree2() + ",");
    insertSql.append(temptable.getFree3() + ",");
    insertSql.append(temptable.getFree4() + ",");
    insertSql.append(temptable.getFree5() + ",");
    insertSql.append(temptable.getFree6() + ",");
    insertSql.append(temptable.getFree7() + ",");
    insertSql.append(temptable.getFree8() + ",");
    insertSql.append(temptable.getFree9() + ",");
    insertSql.append(temptable.getFree10() + ",");
    insertSql.append(temptable.getProdbomid() + ",");
    insertSql.append(temptable.getPackbomid() + ",");
    insertSql.append(temptable.getUnitid() + ",");
    insertSql.append(temptable.getAstunitid() + ",");
    insertSql.append(temptable.getChangerate() + ",");
    insertSql.append(temptable.getAstnum() + ",");
    insertSql.append(temptable.getNum() + ",");
    insertSql.append(temptable.getAstSupplyNum() + ",");
    insertSql.append(temptable.getReqrsnum() + ") ");

    return insertSql.toString();
  }
}
