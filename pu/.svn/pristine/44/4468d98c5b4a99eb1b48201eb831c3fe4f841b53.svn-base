package nc.bs.pu.m422x.query;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.scmpub.reference.uap.rbac.UserManageQuery;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pu.position.entity.PositionItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.sm.UserVO;

/**
 * 按照计划岗过滤物料
 * 
 * @since 6.0
 * @version 2011-4-15 下午12:48:57
 * @author luojw
 */
public class FilterForPlanPosUtil {
  
  /**
   * 按照计划岗过滤物料
   * 
   * @param processor
   * @param sql
   */
  public static void filterMaterialByPos(QuerySchemeProcessor processor,
      SqlBuilder sql) {
    QueryCondition con =
        processor.getQueryCondition("bisplanposition");
    if (con != null && con.getValues() != null) {
      Object bPosition = con.getValues()[0];
      if (UFBoolean.valueOf(bPosition.toString()).booleanValue()) {
        String inSql = FilterForPlanPosUtil.getFilterSql(processor);
        if (null != inSql) {
          sql.append(inSql);
        }
      }
    }
  }

  /**
   * 过滤物料
   * 
   * @param processor
   * @return
   */
  public static String getFilterSql(QuerySchemeProcessor processor) {
    StringBuilder ret = new StringBuilder();
    QueryCondition orgCon =
        processor.getQueryCondition(StoreReqAppHeaderVO.PK_ORG);
    String inSql = null;
    if (null == orgCon) {
      return null;
    }

    inSql = FilterForPlanPosUtil.filterByPosition(orgCon.getValues(), 0);
    if (null != inSql) {
      String tablealias = PUEntity.M422X_B_TABLE;          
      ret.append(" and ");
      ret.append(tablealias + "." + StoreReqAppItemVO.PK_MATERIAL);
      ret.append(" in( ");
      ret.append(inSql);
      ret.append(")");
    }
    return ret.toString();
  }
  
  /**
   * 根据传人的组织和岗位类型返回过滤sql
   * 
   * @param pk_orgs
   * @param fpositiontype 0为计划岗，1为采购岗
   * @return
   */
  public static String filterByPosition(String[] pk_orgs, int fpositiontype) {
    // 查找是否有对应的计划岗设置，
    // rs结果：0、表示应用或排除；1、表示物料分类；2、表示物料
    IRowSet rs = FilterForPlanPosUtil.queryPosition(pk_orgs, fpositiontype);
    
    if (rs == null || rs.size() == 0) {
      return null;
    }
    // 物料
    List<String> materials = new ArrayList<String>();
    // 排出的物料
    List<String> excludes = new ArrayList<String>();
    // 物料分类
    List<String> classes = new ArrayList<String>();
    // 将结果集分组到list中
    while (rs.next()) {
      if (rs.getString(2) != null && rs.getInt(0) == 1) {
        materials.add(rs.getString(2));
      }
      else if (rs.getString(2) != null && rs.getInt(0) == 2) {
        excludes.add(rs.getString(2));
      }
      else if (rs.getString(1) != null && rs.getInt(0) == 1) {
        classes.add(rs.getString(1));
      }
    }
    // 获取的sql为一个子查询。
    SqlBuilder pk_material = new SqlBuilder();
    pk_material.append(" select " + MaterialVO.PK_MATERIAL);
    pk_material.append(" from bd_material where dr = 0 ");
    // 1、如果只有排除，没有意义。不用加。主要判断有没有物料和分类
    // 2、如果都有，先物料，然后在和分类和排除的交集做or
    if (materials.size() != 0) {
      pk_material.append(" and ");
      if (classes.size() != 0) {
        pk_material.append("(");
      }
      pk_material.append(MaterialVO.PK_MATERIAL,
          materials.toArray(new String[materials.size()]));
      if (classes.size() != 0) {
        pk_material.append(" or ");
      }
    }
    if (classes.size() != 0) {
      if (excludes.size() != 0) {
        if (materials.size() != 0) {
          pk_material.append("(");
        }
        else {
          pk_material.append(" and ");
        }
        pk_material.appendNot(MaterialVO.PK_MATERIAL,
            excludes.toArray(new String[excludes.size()]));
        pk_material.append(" and " + MaterialVO.PK_MARBASCLASS,
            classes.toArray(new String[classes.size()]));
        if (materials.size() != 0) {
          pk_material.append(")");
        }
      }
      else {
        pk_material.append(MaterialVO.PK_MARBASCLASS,
            classes.toArray(new String[classes.size()]));
      }
      if (materials.size() != 0) {
        pk_material.append(")");
      }
    }
    return pk_material.toString();
  }

  /**
   * 根据组织查询对应岗位设置
   * 
   * @param pk_orgs
   * @return
   */
  private static IRowSet queryPosition(String[] pk_orgs, int fpositiontype) {
    String pk_user = AppContext.getInstance().getPkUser();
    UserVO user = UserManageQuery.queryUserVOByUserid(pk_user);
    String pk_psn = user.getPk_base_doc();
    // 如果没有查到用户对应的人员，则返回
    if (pk_psn == null) {
      return null;
    }
    // 岗位设置表头_TAB sql
    SqlBuilder sqlHeader = new SqlBuilder();
    // 岗位设置表体_TAB sql
    SqlBuilder sqlItem = new SqlBuilder();
    sqlHeader.append("select " + PositionHeaderVO.PK_POSITION);
    sqlHeader.append(" from " + PUEntity.POSITION_TAB_H);
    sqlHeader.append(" where dr=0 and ");
    sqlHeader.append(PositionHeaderVO.PK_ORG, pk_orgs);
    sqlHeader.append(" and ");
    sqlHeader.append(PositionHeaderVO.FPOSITIONTYPE, fpositiontype);
    sqlHeader.append(" and ");
    sqlHeader.append(PositionHeaderVO.CEMPLOYEEID, pk_psn);

    sqlItem.append("select " + PositionItemVO.FFLAG);
    sqlItem.append(", " + PositionItemVO.PK_MARBASCLASS);
    sqlItem.append(", " + PositionItemVO.PK_MATERIAL);
    sqlItem.append(" from " + PUEntity.POSITION_TAB_B);
    sqlItem.append(" where dr = 0 and ");
    // 表头sql作为子查询，返回表头pk
    sqlItem.append(PositionItemVO.PK_POSITION + " in ( ");
    sqlItem.append(sqlHeader.toString() + " )");
    IRowSet rs = new DataAccessUtils().query(sqlItem.toString());
    return rs;
  }
  
}
