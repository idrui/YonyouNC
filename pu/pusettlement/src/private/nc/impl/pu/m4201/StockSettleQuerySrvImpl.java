package nc.impl.pu.m4201;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.bs.pu.m4203.SubcontinFIQueryBP;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.pu.m4201.IStockFinanceQuery;
import nc.itf.pu.m4202.IVmiFinanceQuery;
import nc.vo.ic.m4a.entity.GeneralInVO;
import nc.vo.ic.m4e.entity.TransInVO;
import nc.vo.pu.m27.entity.PurchaseInSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.entity.StockSettleVOUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pu.m4203.entity.SubcontinFIHeaderVO;
import nc.vo.pu.m4203.entity.SubcontinFIItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.res.Variable;
import nc.vo.scmpub.util.FromWhereParseUtil;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��������Ϣ��ѯ��
 * <li>
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author wangyf
 * @time 2010-4-6 ����09:45:54
 */
public class StockSettleQuerySrvImpl implements IStockFinanceQuery {

  /**
   * �����������������˵õ����Ͻ����itemvo��
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVOs
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-15 ����11:05:05
   */
  public String[] filterStockManageItemVOs(PurchaseinFIItemVO[] itemVOs) {

    if (ArrayUtils.isEmpty(itemVOs)) {
      return null;
    }
    return null;
  }

  @Override
  public GeneralInVO[] queryGeneralinAggVOs(String[] hids, String[] bids) {
    return null;
  }

  @Override
  public PurchaseinFIVO[] queryPurchaseinAggVOs(String[] hids, String[] bids) {
    // WYF ���ٸ���bids����
    try {
      PurchaseinFIVO[] stockVOs =
          new BillQuery<PurchaseinFIVO>(PurchaseinFIVO.class).query(hids);

      return stockVOs;
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  @Override
  public StockSettleVO[] queryPurchaseInByBID(String[] bid)
      throws BusinessException {
    if (bid == null) {
      return null;
    }
    try {
      ViewQuery<PurchaseInSettleVO> vQuery =
          new ViewQuery<PurchaseInSettleVO>(PurchaseInSettleVO.class);
      StockSettleVO[] voaStock = vQuery.query(bid);
      if (voaStock != null) {
        // ����δ�������������
        StockSettleVOUtil.calcStockCanSettle(voaStock);
      }
      return voaStock;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public StockSettleVO[] queryPurchaseInByOrderBID(String[] bid)
      throws BusinessException {
    try {
      IDExQueryBuilder ids =
          new IDExQueryBuilder(PUTempTable.tmp_po_4203_2.name());
      SqlBuilder sql = new SqlBuilder();
      sql.append("select distinct pk_stockps_b from po_purchaseinfi_b where dr=0 and ");
      sql.append(ids.buildSQL("pk_order_b", bid));
      DataAccessUtils util = new DataAccessUtils();
      IRowSet result = util.query(sql.toString());
      return this.queryPurchaseInByBID(result.toOneDimensionStringArray());
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public StockSettleVO[] queryPurchaseInByWhereSql(String fandw)
      throws BusinessException {
    try {
      String sql = this.getM45QuerySql(fandw);
      if (StringUtils.isEmpty(sql)) {
        return null;
      }

      DataAccessUtils dataAccessUtils = new DataAccessUtils();
      // ���ݲ�ѯ�����õ�����ID����
      String[] itemIDs = dataAccessUtils.query(sql).toOneDimensionStringArray();

      if (ArrayUtils.isEmpty(itemIDs)) {
        return null;
      }
      // �����󷵻�����
      int maxRow = Variable.getMaxQueryCount();
      if (itemIDs.length > maxRow) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("common", "04004000-0000", null, new String[] {
              String.valueOf(maxRow)
            })/* ��ѯ������ؼ�¼������{0}�������޸Ĳ�ѯ�������ٲ�ѯ��¼���� */);
      }

      // �����ӱ�ID��ѯ
      StockSettleVO[] stockSettVOs =
          new ViewQuery<PurchaseInSettleVO>(PurchaseInSettleVO.class)
              .query(itemIDs);
      // ����δ�������������
      StockSettleVOUtil.calcStockCanSettle(stockSettVOs);

      return stockSettVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public PurchaseinFIVO[] querySubcontractAggVOs(String[] hids, String[] bids) {
    // WYF PurchaseinFIVO[]��Ҫ�滻Ϊ�����ί����VO[]
    return null;
  }

  @Override
  public StockSettleVO[] querySubcontractByBID(String[] bid)
      throws BusinessException {
    try {
      SqlBuilder frmWhr = this.getFromWhr();// ���ޱ����ķ�ʽ����ί�мӹ��������Ӳ�ѯ��������
      IDExQueryBuilder idqb =
          new IDExQueryBuilder(PUTempTable.tmp_po_4203_3.name());
      String name =
          PUEntity.SUBCONTIN_B_TAB + "." + SubcontinFIItemVO.PK_STOCKPS_B;
      idqb.buildSQL(name, bid);
      frmWhr.append(idqb.buildSQL(name, bid));
      return new SubcontinFIQueryBP().settleQuery(frmWhr.toString());
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public StockSettleVO[] querySubcontractByOrderBID(String[] bid)
      throws BusinessException {
    try {
      SqlBuilder frmWhr = this.getFromWhr();// ���ޱ����ķ�ʽ����ί�мӹ��������Ӳ�ѯ��������
      IDExQueryBuilder idqb =
          new IDExQueryBuilder(PUTempTable.tmp_po_4203_4.name());
      String name =
          PUEntity.SUBCONTIN_B_TAB + "." + SubcontinFIItemVO.PK_ORDER_B;
      idqb.buildSQL(name, bid);
      frmWhr.append(idqb.buildSQL(name, bid));
      return new SubcontinFIQueryBP().settleQuery(frmWhr.toString());
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;

  }

  @Override
  public TransInVO[] queryTransinAggVOs(String[] hids, String[] bids) {
    return null;
  }

  @Override
  public PurchaseinFIVO[] queryVoiconsumeAggVOs(String[] hids, String[] bids) {
    // WYF PurchaseinFIVO[]��Ҫ�滻Ϊ�������Ļ���VO[]
    return null;
  }

  @Override
  public StockSettleVO[] queryVoiconsumeByBID(String[] bid)
      throws BusinessException {
    try {
      return NCLocator.getInstance().lookup(IVmiFinanceQuery.class)
          .queryVMIByIds(bid);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private SqlBuilder getFromWhr() {
    SqlBuilder frmWhr = new SqlBuilder();
    frmWhr.append(" from ");
    frmWhr.append(PUEntity.SUBCONTIN_H_TAB);
    frmWhr.append(" inner join ");
    frmWhr.append(PUEntity.SUBCONTIN_B_TAB);
    frmWhr.append(" on ");
    frmWhr.append(PUEntity.SUBCONTIN_H_TAB);
    frmWhr.append(".");
    frmWhr.append(SubcontinFIHeaderVO.PK_STOCKPS);
    frmWhr.append("=");
    frmWhr.append(PUEntity.SUBCONTIN_B_TAB);
    frmWhr.append(".");
    frmWhr.append(SubcontinFIItemVO.PK_STOCKPS);
    frmWhr.append(" where ");
    return frmWhr;
  }

  /**
   * ����������������ò�ѯSQL���<br>
   * a)
   * �ɹ���ⵥ���Ĵ湩Ӧ�̡��ֶ�Ϊ�յĲɹ���ⵥ�����Ĵ湩Ӧ�̡��ֶηǿ���ΪVMI�ɹ�ҵ�񣺼���ԭ������VMI�ֿ�������ж��Ƿ�ɲ���ɹ����㡢���ݹ���
   * ����Ϊ���ݲɹ���ⵥ���ֶ��Ƿ�ǿ����жϣ�<br>
   * ��������ʱ�Ѿ����˵������ﲻ���ٿ��� by zhaoyha at 2011.1.29<br>
   * b) ��ȫ������Ĳɹ���ⵥֻ�ܲ�����ý��㣨��˴˴������й��ˣ���Ȼ��Ҫ��ѯ������<br>
   * c) �ʲ��������ʲ���������������Ҳ���Բ�����㣨��˴˴������й��ˣ���Ȼ��Ҫ��ѯ������<br>
   * d) ����Ʒ<br>
   * <p>
   * <b>����˵��</b>
   * 
   * @param sqlWhere
   * @return <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-3 ����02:31:08
   */
  private String getM45QuerySql(String sqlWhere) {
    if (StringUtils.isEmpty(sqlWhere)) {
      return null;
    }
    FromWhereParseUtil util = new FromWhereParseUtil(sqlWhere);
    String alias = util.getTableAlias("po_purchaseinfi_b");

    SqlBuilder builder = new SqlBuilder();
    builder.append("select " + alias + ".pk_stockps_b ");
    builder.append(sqlWhere);
    builder.append(" and po_purchaseinfi.dr", 0);
    builder.append(" and " + alias + ".dr", 0);
    // ����Ʒ
    builder.append(" and isnull(" + alias + ".blargess, 'N')", "N");
    return builder.toString();
  }

}
