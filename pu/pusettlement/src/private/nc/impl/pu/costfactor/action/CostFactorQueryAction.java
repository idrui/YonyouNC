/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-5-18 ����04:34:19
 */
package nc.impl.pu.costfactor.action;

import nc.bs.pu.pub.PUIDQueryBuilder;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.ArrayUtils;

/**
 * �ɱ�Ҫ�ض����ѯ��
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ѯ�ɱ�Ҫ�ض���
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version v60
 * @since v60
 * @author zhaoyha
 * @time 2009-5-18 ����04:34:19
 */
public class CostFactorQueryAction {

  /**
   * ���������������ɱ�Ҫ�ط������Ƿ�����(Ŀǰ�Ƿ񱻽��㡢�ݹ�����)
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_group
   * @param pk_org
   * @param pk_srcmaterial
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-8-11 ����09:36:27
   */
  public boolean beReferenced(String pk_group, String pk_org,
      String pk_srcmaterial) {
    DataAccessUtils utils = new DataAccessUtils();
    // 1.
    String sql = this.getPurchaseinfeeSql(pk_group, pk_org, pk_srcmaterial);
    IRowSet rowset = utils.query(sql.toString());
    if (rowset.next()) {
      return true;
    }
    // 2.
    sql = this.getVmifeeSql(pk_group, pk_org, pk_srcmaterial);
    rowset = utils.query(sql.toString());
    if (rowset.next()) {
      return true;
    }
    // 3.
    sql = this.getSettlebillSql(pk_group, pk_org, pk_srcmaterial);
    rowset = utils.query(sql.toString());
    if (rowset.next()) {
      return true;
    }
    return false;
  }

  public CostfactorVO[] querty(String where) {
    String sql =
        "select distinct pk_costfactor from po_costfactor where " + where;
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    String[] cbillids = rowset.toOneDimensionStringArray();

    // ����id��ѯVO
    BillQuery<CostfactorVO> query =
        new BillQuery<CostfactorVO>(CostfactorVO.class);
    return query.query(cbillids);
  }

  /**
   * ����������������ѯ�ɱ�Ҫ�ض��塣
   * <p>
   * <b>����˵��</b>
   * 
   * @param context �û���¼��Ϣ��
   * @return �ɱ�Ҫ�ض���VO����
   * @since 6.0
   */
  public CostfactorVO[] query(LoginContext context) {
    return this.query(context, null);
  }

  /**
   * ��ѯ�ɱ�Ҫ�أ�����֯Ȩ��
   * 
   * @param context
   * @param permissonOrgs
   * @return
   */
  public CostfactorVO[] query(LoginContext context, String[] permissonOrgs) {
    SqlBuilder where = new SqlBuilder();
    where.append(" dr=0 ");
    // String where = " dr=0 ";
    // String visibleCondition = VisibleUtil.getVisibleCondition(context,
    // CostfactorHeaderVO.class);
    // if (!StringUtil.isEmptyWithTrim(visibleCondition)) {
    // where += " and " + visibleCondition;
    // }
    String pk_group = context.getPk_group();
    if (!StringUtil.isEmptyWithTrim(pk_group)) {
      where.append(" and pk_group ", pk_group);
    }
		if (!ArrayUtils.isEmpty(permissonOrgs)) {
			//�ϲ�ͨ�油�� 2015-09-23 zhangshqb 
			PUIDQueryBuilder builder = new PUIDQueryBuilder();
			where.append(builder.buildSQL(" and pk_org ", permissonOrgs));
		}

    return this.querty(where.toString());
  }

  public CostfactorViewVO[] queryEstItems(String pk_org) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select b.pk_costfactor_b from po_costfactor a, po_costfactor_b b ");
    sql.append("where a.pk_costfactor=b.pk_costfactor and a.dr=0 and b.dr=0 ");
    sql.append(" and a.pk_org", pk_org);
    sql.append(" and a.bentercost='Y'");
    // �ݹ��Ƿ���ʾ
    sql.append(" and b.bshow='Y'");
    // ���������
    sql.append(" order by b.ishoworder");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    if (0 == rowset.size()) {
      return null;
    }
    String[] ids = rowset.toOneDimensionStringArray();
    return new ViewQuery<CostfactorViewVO>(CostfactorViewVO.class).query(ids);
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_group
   * @param pk_org
   * @param pk_srcmaterial
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-8-11 ����10:11:36
   */
  private String getPurchaseinfeeSql(String pk_group, String pk_org,
      String pk_srcmaterial) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select dr from ");
    sql.append(PUEntity.PurchaseinFI_Fee_TAB);
    sql.append(" where  dr = 0 and ");
    sql.append(FeeEstVO.PK_FINANCEORG, pk_org);
    sql.append(" and ");
    sql.append(FeeEstVO.PK_GROUP, pk_group);
    sql.append(" and ");
    sql.append(FeeEstVO.PK_SRCFEEMATERIAL, pk_srcmaterial);
    return sql.toString();
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_group
   * @param pk_org
   * @param pk_srcmaterial
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-8-11 ����10:11:47
   */
  private String getSettlebillSql(String pk_group, String pk_org,
      String pk_srcmaterial) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select dr from ");
    sql.append(PUEntity.SettleBill_B_TAB);
    sql.append(" where  dr = 0 and ");
    sql.append(SettleBillItemVO.PK_ORG, pk_org);
    sql.append(" and ");
    sql.append(SettleBillItemVO.PK_GROUP, pk_group);
    sql.append(" and ");
    sql.append(SettleBillItemVO.PK_SRCMATERIAL, pk_srcmaterial);
    return sql.toString();
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_group
   * @param pk_org
   * @param pk_srcmaterial
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-8-11 ����10:11:41
   */
  private String getVmifeeSql(String pk_group, String pk_org,
      String pk_srcmaterial) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select dr from ");
    sql.append(PUEntity.VMIFI_Fee_TAB);
    sql.append(" where  dr = 0 and ");
    sql.append(FeeEstVO.PK_FINANCEORG, pk_org);
    sql.append(" and ");
    sql.append(FeeEstVO.PK_GROUP, pk_group);
    sql.append(" and ");
    sql.append(FeeEstVO.PK_SRCFEEMATERIAL, pk_srcmaterial);
    return sql.toString();
  }
}
