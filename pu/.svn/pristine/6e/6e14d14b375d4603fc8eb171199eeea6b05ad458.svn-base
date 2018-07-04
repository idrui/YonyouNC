package nc.pubimpl.pu.m21.ecn.eco;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.mmf.busi.eco.IBill4ECAResult;
import nc.pubitf.mmf.busi.eco.IECAItemPara;
import nc.pubitf.mmf.busi.eco.MaterialAssFieldConst;
import nc.pubitf.pu.m21.ecn.eco.IQueryOrderBillForECA;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.CommonProcessForECA;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 离散制造工程变更分析时，查询采购订单信息接口实现类
 * 
 * @since 6.3.1
 * @version 2013-8-15 下午01:54:43
 * @author fanly3
 */
public class QueryOrderBillForECAImpl implements IQueryOrderBillForECA {

  @Override
  public void qryECAResult(IECAItemPara[] paras, IBill4ECAResult temptable)
      throws BusinessException {
    try {
      CommonProcessForECA utils = new CommonProcessForECA(paras, temptable);
      // 临时表
      String tempTableName =
          utils.constructParasTempTable(PUTempTable.tmp_po_21_66.name());
      String finalSql = this.constructFinalSql(tempTableName, utils, paras);
      DataAccessUtils dao = new DataAccessUtils();
      dao.update(finalSql);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 构造最终SQL
   * 
   * @param tempTableName 采购这边新建的临时表名
   * @param utils 采购对ECA公共处理类对象
   * @param paras ECA子项参数
   * @return 最终的SQL
   */
  private String constructFinalSql(String tempTableName,
      CommonProcessForECA utils, IECAItemPara[] paras) {
    SqlBuilder finalSql = new SqlBuilder();
    finalSql.append(utils.constructInsertSql());
    finalSql.append(this.constructQuerySql(tempTableName, paras));
    return finalSql.toString();
  }

  /**
   * 构造最终完整的查询条件SQL
   * 
   * @param tempTableName 采购这边新建的临时表名
   * @param paras ECA子项参数
   * @return 完整的查询采购订单信息SQL
   */
  private String constructQuerySql(String tempTableName, IECAItemPara[] paras) {
    SqlBuilder finalSql = new SqlBuilder();
    finalSql.append(this.constructSelectSql());
    finalSql.append(this.constructFromWhereSql(tempTableName, paras));
    return finalSql.toString();
  }

  /**
   * 构造采购订单的select语句
   * 
   * @return 采购订单的select语句
   */
  private String constructSelectSql() {
    SqlBuilder selectSql = new SqlBuilder();
    selectSql.append("select '" + POBillType.Order.getCode() + "'");
    selectSql.append(",po_order." + OrderHeaderVO.PK_ORG);
    selectSql.append(",po_order." + OrderHeaderVO.PK_ORG_V);
    selectSql.append(",po_order." + OrderHeaderVO.PK_ORDER);
    selectSql.append(",po_order." + OrderHeaderVO.VBILLCODE);
    selectSql.append(",po_order_b." + OrderItemVO.PK_ORDER_B);
    selectSql.append(",po_order_b." + OrderItemVO.CROWNO);
    selectSql.append(",po_order." + OrderHeaderVO.DBILLDATE);
    // 供给日期 = 计划到货日期
    selectSql.append(",po_order_b." + OrderItemVO.DPLANARRVDATE);
    // 仓库
    selectSql.append(",null");
    selectSql.append(",po_order_b." + OrderItemVO.PK_SRCMATERIAL);
    selectSql.append(",po_order_b." + OrderItemVO.PK_MATERIAL);
    // 供应商
    selectSql.append(",null");
    selectSql.append(",po_order_b." + OrderItemVO.CPRODUCTORID);
    selectSql.append(",po_order_b." + OrderItemVO.CPROJECTID);
    selectSql.append(",po_order_b." + OrderItemVO.CASSCUSTID);
    selectSql.append(",po_order_b." + OrderItemVO.VFREE1);
    selectSql.append(",po_order_b." + OrderItemVO.VFREE2);
    selectSql.append(",po_order_b." + OrderItemVO.VFREE3);
    selectSql.append(",po_order_b." + OrderItemVO.VFREE4);
    selectSql.append(",po_order_b." + OrderItemVO.VFREE5);
    selectSql.append(",po_order_b." + OrderItemVO.VFREE6);
    selectSql.append(",po_order_b." + OrderItemVO.VFREE7);
    selectSql.append(",po_order_b." + OrderItemVO.VFREE8);
    selectSql.append(",po_order_b." + OrderItemVO.VFREE9);
    selectSql.append(",po_order_b." + OrderItemVO.VFREE10);
    // 生产BOMID
    selectSql.append(",null");
    // 包装BOMID
    selectSql.append(",null");
    selectSql.append(",po_order_b." + OrderItemVO.CUNITID);
    selectSql.append(",po_order_b." + OrderItemVO.CASTUNITID);
    selectSql.append(",po_order_b." + OrderItemVO.VCHANGERATE);
    selectSql.append(",po_order_b." + OrderItemVO.NASTNUM);
    selectSql.append(",po_order_b." + OrderItemVO.NNUM);
    // 未执行主数量 = 主数量-累计入库主数量
    selectSql.append(",po_order_b." + OrderItemVO.NNUM + "- isnull(po_order_b."
        + OrderItemVO.NACCUMSTORENUM + ",0)");
    // 预留主数量
    selectSql.append(",po_order_b." + OrderItemVO.NSUPRSNUM);
    return selectSql.toString();
  }

  /**
   * 构建From和Where语句
   * 
   * @param tempTableName 新增的临时表名
   * @param paras ECA子项参数
   * @return From和Where语句的SQL
   */
  private String constructFromWhereSql(String tempTableName,
      IECAItemPara[] paras) {
    SqlBuilder fromSql = new SqlBuilder();
    fromSql.append(" from po_order_b po_order_b inner join po_order po_order ");
    fromSql.append(" on po_order_b.pk_order = po_order.pk_order ");
    fromSql.append(" inner join " + tempTableName + " " + tempTableName);
    // 收货库存组织用oid查，和制造李辉确认过
    fromSql.append(" on " + tempTableName + "."
        + CommonProcessForECA.ANALYZEORG + " = po_order_b.pk_arrvstoorg ");

    SqlBuilder whereSql = new SqlBuilder();
    whereSql.append(" where po_order.dr", 0);
    whereSql.append(" and po_order_b.dr", 0);
    // 最新版本
    whereSql.append(" and po_order.bislatest", UFBoolean.TRUE);
    // 未入库关闭
    whereSql.append(" and po_order_b.bstockclose", UFBoolean.FALSE);
    // 未执行量大于0
    whereSql
        .append(" and (po_order_b.nnum - isnull(po_order_b.naccumstorenum,0)) > 0");
    // 日期条件
    whereSql.append(" and po_order_b.dplanarrvdate between " + tempTableName
        + "." + CommonProcessForECA.ANALYZEDATEFROM + " and " + tempTableName
        + "." + CommonProcessForECA.ANALYZEDATETO);
    // 物料条件
    whereSql.append(" and po_order_b.pk_material = " + tempTableName + "."
        + CommonProcessForECA.CMATERIALVID);

    String assWhere = this.constructAssWhereSql(tempTableName, paras);
    if (assWhere != null && assWhere.length() > 0) {
      whereSql.append(" and (");
      whereSql.append(assWhere);
      whereSql.append(")");
    }

    SqlBuilder fromWhereSql = new SqlBuilder();
    fromWhereSql.append(fromSql.toString());
    fromWhereSql.append(whereSql.toString());
    return fromWhereSql.toString();
  }

  /**
   * 构造采购订单辅助属性where条件
   * 
   * @param tempTableName 新增的临时表名
   * @param paras ECA子项参数
   * @return ECA查询采购订单时，辅助属性构成的where条件
   */
  private String constructAssWhereSql(String tempTableName, IECAItemPara[] paras) {
    SqlBuilder sql = new SqlBuilder();
    for (IECAItemPara para : paras) {
      String[] asslist = para.getAsslist();
      if (asslist != null && asslist.length == 1
          && CommonProcessForECA.CVENDORID.equals(asslist[0])) {
        continue;
      }
      if (asslist != null && asslist.length > 0) {
        sql.append("(");
        sql.append(this.constructAssPropSql(asslist, tempTableName));
        sql.append(") or ");
      }
    }
    String assWhereSql = sql.toString();
    if (assWhereSql != null && assWhereSql.length() > 0) {
      assWhereSql = assWhereSql.substring(0, assWhereSql.lastIndexOf("or"));
    }

    return assWhereSql;
  }

  /**
   * 构造采购订单的辅助属性SQL
   * 
   * @param asslist 参数中用于查询的辅助属性字段数组
   * @param tempTableName 新增的临时表名
   * @return 辅助属性构成的where条件
   */
  private String constructAssPropSql(String[] asslist, String tempTableName) {
    SqlBuilder sql = new SqlBuilder();
    for (String str : asslist) {
      // 客户
      if (str.equals(MaterialAssFieldConst.getCcustomerid())) {
        sql.append(tempTableName + "." + CommonProcessForECA.CCUSTOMERID
            + " = " + "po_order_b." + OrderItemVO.CASSCUSTID);
      }
      // 生产厂商
      else if (str.equals(MaterialAssFieldConst.getCproductorid())) {
        sql.append(" and " + tempTableName + "."
            + CommonProcessForECA.CPRODUCTORID + " = " + "po_order_b."
            + OrderItemVO.CPRODUCTORID);
      }
      // 项目
      else if (str.equals(MaterialAssFieldConst.getCprojectid())) {
        sql.append(" and " + tempTableName + "."
            + CommonProcessForECA.CPROJECTID + " = " + "po_order_b."
            + OrderItemVO.CPROJECTID);
      }
      // 采购订单的自由辅助属性只有项目，生产厂商，客户，具体可以参考order_config.xml文件中<!-- 自由项组件的配置 -->处
      // // 供应商
      // else if (str.equals(MaterialAssFieldConst.getCvendorid())) {
      // sql.append(" and " + tempTableName + "."
      // + CommonProcessForECA.CVENDORID + " = " + "po_order_b."
      // + OrderItemVO.PK_SUPPLIER);
      // }
      else if (str.equals(MaterialAssFieldConst.getVfree1())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE1
            + " = " + "po_order_b." + OrderItemVO.VFREE1);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree2())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE2
            + " = " + "po_order_b." + OrderItemVO.VFREE2);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree3())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE3
            + " = " + "po_order_b." + OrderItemVO.VFREE3);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree4())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE4
            + " = " + "po_order_b." + OrderItemVO.VFREE4);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree5())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE5
            + " = " + "po_order_b." + OrderItemVO.VFREE5);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree6())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE6
            + " = " + "po_order_b." + OrderItemVO.VFREE6);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree7())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE7
            + " = " + "po_order_b." + OrderItemVO.VFREE7);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree8())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE8
            + " = " + "po_order_b." + OrderItemVO.VFREE8);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree9())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE9
            + " = " + "po_order_b." + OrderItemVO.VFREE9);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree10())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE10
            + " = " + "po_order_b." + OrderItemVO.VFREE10);
      }
    }
    String sqlStr = sql.toString();
    if (sqlStr.startsWith(" and ")) {
      sqlStr = sqlStr.replaceFirst(" and ", "");
    }
    return sqlStr;
  }
}
