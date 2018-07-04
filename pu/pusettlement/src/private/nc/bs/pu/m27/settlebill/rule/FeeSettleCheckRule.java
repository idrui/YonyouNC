package nc.bs.pu.m27.settlebill.rule;

import java.util.HashSet;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.est.entity.m45.PurchaseinFIFDVO;
import nc.vo.pu.est.entity.m50.VmiFIFDVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * 
 * @description
 * ���㵥ɾ��/ȡ�������ʱ������Ƿ���й����÷�̯
 * �����ڷ��ý��㣬���ߴ��ڷ����ݹ���̯��������ϸ����
 * ������ڷ��÷�̯�����Ƚ����ý��㵥ɾ����
 * @scene
 * ȡ�����������,ɾ�����㵥
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-22 ����3:58:52
 * @author zhangshqb
 */
public class FeeSettleCheckRule implements IRule<SettleBillVO> {

  public FeeSettleCheckRule() {
    //
  }

  @Override
  public void process(SettleBillVO[] vos) {
    Set<String> bids = new HashSet<String>();
    Set<String> hids = new HashSet<String>();
    Set<String> m45StlBids = new HashSet<String>();
    Set<String> m50StlBids = new HashSet<String>();
    for (SettleBillVO vo : vos) {
      for (SettleBillItemVO item : vo.getChildrenVO()) {
        bids.add(item.getPk_settlebill_b());
        if (ICBillType.VmiSum.getCode().equals(item.getVstockbilltype())) {
          m50StlBids.add(item.getPk_stock_b());
        }
        else if (ICBillType.PurchaseIn.getCode().equals(
            item.getVstockbilltype())) {
          m45StlBids.add(item.getPk_stock_b());
        }
      }
      hids.add(vo.getParentVO().getPk_settlebill());
    }
    String sql = this.getQuerySql(bids, m45StlBids, m50StlBids, hids);
    DataAccessUtils util = new DataAccessUtils();
    IRowSet rowSet = util.query(sql);
    while (rowSet.next()) {
      int count = rowSet.getInt(0);
      if (count > 0) {
        ExceptionUtils
            .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
                "4004060_0", "04004060-0240")/* ���ں������ý��㵥������ݹ����޷�ִ�д˲����� */);
      }
    }
  }

  /**
   * �����ݹ�У�飬�����ɹ�������Ļ���������
   * 
   * @param inWhere
   * @param fdClass
   * @return
   */
  private String getFeeEstQuerySql(String inWhere,
      Class<? extends FeeEstDistVO> fdClass) {
    SqlBuilder sqlbuilder = new SqlBuilder();
    try {
      FeeEstDistVO fdVo = fdClass.newInstance();
      String fdTab =
          fdVo.getMetaData().getPrimaryAttribute().getColumn().getTable()
              .getName();
      sqlbuilder.append(" select count(*) from  " + fdTab + " where dr=0 and ");
      sqlbuilder.append(inWhere.replace("po_settle_feeallot."
          + SettleFeeAllotDetailVO.PK_ALLOTBILLBID,
          FeeEstDistVO.PK_DISTBYBILL_B));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return sqlbuilder.toString();
  }

  /**
   * ���㵥bid��ʱ����
   * 
   * @param bids
   * @return
   */
  private String getInWhereSql(Set<String> bids) {
    return new IDExQueryBuilder(PUTempTable.tmp_po_27_06.name()).buildSQL(
        " po_settle_feeallot." + SettleFeeAllotDetailVO.PK_ALLOTBILLBID,
        bids.toArray(new String[bids.size()]));

  }

  /**
   * ����ʱ�ķ��÷�̯��飬�����ǰ���鵥�Ѿ����������ý����̯�����������Լ�����һ�Σ����Ͳ�����ɾ����ȡ���������
   * 
   * @param inWhereSql
   * @param hids
   * @return
   */
  private String getM27FeeAllotSql(String inWhereSql, Set<String> hids) {
    SqlBuilder sqlbuilder = new SqlBuilder();
    sqlbuilder.append(" select count(*) from  po_settle_feeallot ");
    sqlbuilder
        .append(" inner join po_settlebill on po_settle_feeallot.pk_settlebill = po_settlebill.pk_settlebill");
    sqlbuilder.append(" where po_settle_feeallot.dr=0 and po_settlebill.dr=0 ");
    IDExQueryBuilder idBuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_27_07.name());
    sqlbuilder.append(" and ");
    sqlbuilder.append(inWhereSql);
    String[] hid = hids.toArray(new String[hids.size()]);
    sqlbuilder.append(" and ");
    // ������������ѡ���㵥
    if (hids.size() == 1) {
      sqlbuilder.append(" po_settle_feeallot."
          + SettleFeeAllotDetailVO.PK_SETTLEBILL, "<>", hid[0]);
    }
    else {
      String hIdSql =
          idBuilder.buildAnotherSQL(" po_settle_feeallot."
              + SettleFeeAllotDetailVO.PK_SETTLEBILL + " not ",
              hids.toArray(new String[hids.size()]));
      sqlbuilder.append(hIdSql);
    }
    return sqlbuilder.toString();
  }

  /**
   * У��sql
   * 
   * @param bids ���㵥��id
   * @param m45bids �ɹ������id
   * @param m50bids ���Ļ�����id
   * @param hids ���㵥ͷid
   * @return У��sql
   */
  private String getQuerySql(Set<String> bids, Set<String> m45bids,
      Set<String> m50bids, Set<String> hids) {
    SqlBuilder sqlbuilder = new SqlBuilder();
    String inwhereSql = this.getInWhereSql(bids);
    sqlbuilder.append(this.getM27FeeAllotSql(inwhereSql, hids));
    // �ɹ����ķ����ݹ�У��
    if (m45bids.size() > 0) {
      sqlbuilder.append(" union all ");
      sqlbuilder.append(this.getFeeEstQuerySql(inwhereSql,
          PurchaseinFIFDVO.class));
    }
    // ���Ļ��ܵķ����ݹ�У��
    if (m50bids.size() > 0) {
      sqlbuilder.append(" union all ");
      sqlbuilder.append(this.getFeeEstQuerySql(inwhereSql, VmiFIFDVO.class));
    }

    return sqlbuilder.toString();
  }
}
