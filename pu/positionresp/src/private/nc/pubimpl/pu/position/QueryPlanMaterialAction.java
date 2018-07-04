/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-12 ����01:51:35
 */
package nc.pubimpl.pu.position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.rbac.UserManageQuery;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pu.position.enumeration.EnumUseMove;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.UserVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-12 ����01:51:35
 */
public class QueryPlanMaterialAction {
  /**
   * �����������������˵���ǰ����Ա(�ƻ�Ա)û����Ȩ�޵�����(����ѯ��ǰ��Ȩ�޵�����)��
   * <p>
   * <b>����˵��</b>
   * 
   * @param pkOrg
   * @param pkOperator
   * @param pkMaterials
   * @return <p>
   *         ��Ȩ�޵�����vid
   * @since 6.0
   * @author GGR
   * @time 2010-7-12 ����01:52:55
   */
  public ArrayList<String> filterMaterialByPlanner(String pkOrg,
      String pkOperator, ArrayList<String> pkMaterials) {
    // ����vid��ѯoid
    List<String> pkSrcMaterials = this.getMaterialOids(pkMaterials);
    ArrayList<String> ret = new ArrayList<String>();
    // ����oid��vid ӳ��
    MapList<String, String> oidVidMap =
        this.buildOidVidMap(pkMaterials, pkSrcMaterials);
    // ��������(oid)��ѯ
    String[][] materials = this.queryPlanByMaterial(pkOrg, pkSrcMaterials);
    HashSet<String> used = new HashSet<String>();
    if (!ArrayUtils.isEmpty(materials)) {
      for (String[] row : materials) {
        if (pkOperator.equals(row[1])
            && EnumUseMove.USE == Integer.parseInt(row[2])) {
          ret.addAll(oidVidMap.get(row[0]));
        }
        // �Ѿ����ݴ����ϲ�ѯ�����
        used.addAll(oidVidMap.get(row[0]));
      }
    }

    // �������Ϸ����ѯ
    ret.addAll(this.getByClass(pkOrg, pkOperator, pkMaterials, used));

    return ret;
  }

  /**
   * ���ݲ���Ա��ѯ���Ӧ��ҵ��Ա���ƻ�Ա������Ӧ�ļƻ���ID
   * 
   * @param pk_org �����֯
   * @param pk_user ����Ա���û���
   * @param positype ��λ���� ��0 �ƻ��ڣ� 1 �ɹ���
   * @return �ƻ���ID���飨�������Աû������ҵ��Ա���򷵻�null,�������Ա��Ӧ��ҵ��Աδ���üƻ��ڣ�Ҳ����null)
   */
  public String[] queryPosForUser(String pk_org, String pk_user, int positype) {
    String pk_psn = null;
    UserVO user = UserManageQuery.queryUserVOByUserid(pk_user);
    pk_psn = user.getPk_base_doc();
    if (null == pk_psn) {
      return null;
    }
    SqlBuilder sql = new SqlBuilder();
    sql.append("select " + PositionHeaderVO.PK_POSITION);
    sql.append(" from " + PUEntity.POSITION_TAB_H);
    sql.append(" where dr=0 and ");
    sql.append(PositionHeaderVO.PK_ORG, pk_org);
    sql.append(" and ");
    sql.append(PositionHeaderVO.FPOSITIONTYPE, positype);
    sql.append(" and ");
    sql.append(PositionHeaderVO.CEMPLOYEEID, pk_psn);
    IRowSet rs = new DataAccessUtils().query(sql.toString());
    if (rs.size() == 0) {
      return null;
    }
    return rs.toOneDimensionStringArray();
  }

  private MapList<String, String> buildOidVidMap(List<String> pkMaterials,
      List<String> pkSrcMaterials) {
    MapList<String, String> oidVidMap = new MapList<String, String>();
    for (String oid : pkSrcMaterials) {
      for (String vid : pkMaterials) {
        oidVidMap.put(oid, vid);
      }

    }
    return oidVidMap;
  }

  private ArrayList<String> getByClass(String pkOrg, String pkOperator,
      ArrayList<String> pkMaterials, HashSet<String> used) {

    ArrayList<String> ret = new ArrayList<String>();
    // ȡ��δ��ѯ��������PK
    ArrayList<String> notUse = new ArrayList<String>();
    for (String marerial : pkMaterials) {
      if (!used.contains(marerial)) {
        notUse.add(marerial);
      }
    }
    // ��������PK�������Ϸ���
    Map<String, MaterialVO> map = this.getMaterialClass(notUse);
    HashSet<String> marClass = new HashSet<String>();
    if (map != null) {
      for (Map.Entry<String, MaterialVO> material : map.entrySet()) {
        marClass.add(material.getValue().getPk_marbasclass());
      }
    }

    // �������Ϸ����Ƿ񱻼ƻ�ԱӦ��
    HashSet<String> usemarClass =
        this.queryPlanByClass(pkOrg, pkOperator, marClass);
    if (null != usemarClass && usemarClass.size() > 0 && map != null) {
      for (Map.Entry<String, MaterialVO> material : map.entrySet()) {
        if (usemarClass.contains(material.getValue().getPk_marbasclass())) {
          ret.add(material.getKey());
        }
      }
    }

    return ret;
  }

  private Map<String, MaterialVO> getMaterialClass(ArrayList<String> pkMaterials) {
    return MaterialPubService.queryMaterialBaseInfo(
        pkMaterials.toArray(new String[pkMaterials.size()]), new String[] {
          MaterialVO.PK_MARBASCLASS, MaterialVO.PK_MATERIAL
        });
  }

  private List<String> getMaterialOids(ArrayList<String> pkMaterials) {
    List<String> oids = new ArrayList<String>();
    Map<String, MaterialVO> vidMap =
        MaterialPubService.queryMaterialBaseInfo(
            pkMaterials.toArray(new String[pkMaterials.size()]), new String[] {
              MaterialVO.PK_SOURCE, MaterialVO.PK_MATERIAL
            });
    for (Entry<String, MaterialVO> entry : vidMap.entrySet()) {
      oids.add(entry.getValue().getPk_source());
    }
    return oids;
  }

  private HashSet<String> queryPlanByClass(String pkOrg, String pkOperator,
      HashSet<String> marClass) {
    if (marClass == null || marClass.size() == 0) {
      return null;
    }
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select distinct t.pk_marclass ");
    sql.append(" from po_position_t t ");
    sql.append(" where  t.fpositiontype = 0 and ");
    sql.append("t.pk_org", pkOrg);
    sql.append(" and ");
    sql.append("t.cemployeeid", pkOperator);
    sql.append(" and ");
    // ��ʱ��
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_pos_08.name());
    sql.append(builder.buildSQL("t.pk_marclass",
        marClass.toArray(new String[marClass.size()])));

    String[] result =
        new DataAccessUtils().query(sql.toString()).toOneDimensionStringArray();

    HashSet<String> ret = new HashSet<String>();
    if (!ArrayUtils.isEmpty(result)) {
      for (String row : result) {
        ret.add(row);
      }
    }

    return ret;
  }

  private String[][] queryPlanByMaterial(String pkOrg, List<String> pkMaterials) {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" select distinct b.pk_srcmaterial,h.cemployeeid,b.fflag ");
    sql.append(" from po_position_b b,po_position h  ");
    sql.append(" where  b.pk_position = h.pk_position and h.dr =0 and ");
    sql.append(" h.fpositiontype = 0 and ");
    sql.append("h.pk_org", pkOrg);
    sql.append(" and ");
    // ��ʱ��
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_pos_05.name());
    sql.append(builder.buildSQL("b.pk_srcmaterial",
        pkMaterials.toArray(new String[pkMaterials.size()])));

    return new DataAccessUtils().query(sql.toString())
        .toTwoDimensionStringArray();
  }
}
