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
 * ���ռƻ��ڹ�������
 * 
 * @since 6.0
 * @version 2011-4-15 ����12:48:57
 * @author luojw
 */
public class FilterForPlanPosUtil {
  
  /**
   * ���ռƻ��ڹ�������
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
   * ��������
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
   * ���ݴ��˵���֯�͸�λ���ͷ��ع���sql
   * 
   * @param pk_orgs
   * @param fpositiontype 0Ϊ�ƻ��ڣ�1Ϊ�ɹ���
   * @return
   */
  public static String filterByPosition(String[] pk_orgs, int fpositiontype) {
    // �����Ƿ��ж�Ӧ�ļƻ������ã�
    // rs�����0����ʾӦ�û��ų���1����ʾ���Ϸ��ࣻ2����ʾ����
    IRowSet rs = FilterForPlanPosUtil.queryPosition(pk_orgs, fpositiontype);
    
    if (rs == null || rs.size() == 0) {
      return null;
    }
    // ����
    List<String> materials = new ArrayList<String>();
    // �ų�������
    List<String> excludes = new ArrayList<String>();
    // ���Ϸ���
    List<String> classes = new ArrayList<String>();
    // ����������鵽list��
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
    // ��ȡ��sqlΪһ���Ӳ�ѯ��
    SqlBuilder pk_material = new SqlBuilder();
    pk_material.append(" select " + MaterialVO.PK_MATERIAL);
    pk_material.append(" from bd_material where dr = 0 ");
    // 1�����ֻ���ų���û�����塣���üӡ���Ҫ�ж���û�����Ϻͷ���
    // 2��������У������ϣ�Ȼ���ںͷ�����ų��Ľ�����or
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
   * ������֯��ѯ��Ӧ��λ����
   * 
   * @param pk_orgs
   * @return
   */
  private static IRowSet queryPosition(String[] pk_orgs, int fpositiontype) {
    String pk_user = AppContext.getInstance().getPkUser();
    UserVO user = UserManageQuery.queryUserVOByUserid(pk_user);
    String pk_psn = user.getPk_base_doc();
    // ���û�в鵽�û���Ӧ����Ա���򷵻�
    if (pk_psn == null) {
      return null;
    }
    // ��λ���ñ�ͷ_TAB sql
    SqlBuilder sqlHeader = new SqlBuilder();
    // ��λ���ñ���_TAB sql
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
    // ��ͷsql��Ϊ�Ӳ�ѯ�����ر�ͷpk
    sqlItem.append(PositionItemVO.PK_POSITION + " in ( ");
    sqlItem.append(sqlHeader.toString() + " )");
    IRowSet rs = new DataAccessUtils().query(sqlItem.toString());
    return rs;
  }
  
}
