package nc.pubimpl.pu.m23.ic.m47;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.pu.m23.ic.m47.IQuery23For47;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.utils.ArriveTrans45QueryUtil;
import nc.vo.pu.m23.utils.BatchSynchronizerM23;
import nc.vo.pu.m23.utils.FillBillInfoFor23;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.StringUtils;

/**
 * �������ṩ������ί�мӹ���ⵥ�Ĳ�ѯ����ʵ����
 * 
 * @since 6.0
 * @version 2010-11-12 ����03:59:08
 * @author hanbin
 */
public class Query23For47Impl implements IQuery23For47 {

  @Override
  public ArriveVO[] queryArrive(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      String sql = this.getCompleteSql(queryScheme);
      ArriveVO[] vos = this.queryArriveBySql(sql);
      FillBillInfoFor23.fillBillInfo(vos);
      return BatchSynchronizerM23.synchBatchCodeData(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  // @Override
  // public ArriveVO[] queryArrive(String whereSql) throws BusinessException {
  // // �������sql
  // String sql = this.getCompleteSql(whereSql);
  // ArriveVO[] bills = null;
  // try {
  // DataAccessUtils utils = new DataAccessUtils();
  // IRowSet rowset = utils.query(sql);
  // List<String> hids = new ArrayList<String>();
  // List<String> itemids = new ArrayList<String>();
  // List<String> bb1ids = new ArrayList<String>();
  // while (rowset.next()) {
  // String hid = rowset.getString(0);
  // if (StringUtils.isNotEmpty(hid) && !hids.contains(hid)) {
  // hids.add(hid);
  // }
  // String bid = rowset.getString(1);
  // if (StringUtils.isNotEmpty(bid) && !itemids.contains(bid)) {
  // itemids.add(bid);
  // }
  // String bbid = rowset.getString(2);
  // if (StringUtils.isNotEmpty(bbid) && !bb1ids.contains(bbid)) {
  // bb1ids.add(bbid);
  // }
  // }
  // if (hids.size() == 0 || itemids.size() == 0) {
  // return null;
  // }
  //
  // // 1����ѯArriveHeaderVO
  // VOQuery<ArriveHeaderVO> query1 =
  // new VOQuery<ArriveHeaderVO>(ArriveHeaderVO.class);
  // ArriveHeaderVO[] headers = query1.query(hids.toArray(new String[0]));
  //
  // // 2����ѯArriveItemVO
  // VOQuery<ArriveItemVO> query2 =
  // new VOQuery<ArriveItemVO>(ArriveItemVO.class);
  // ArriveItemVO[] bitems = query2.query(itemids.toArray(new String[0]));
  //
  // // 3����ѯArriveBbVO
  // ArriveBbVO[] bbItems = null;
  // if (bb1ids.size() > 0) {
  // VOQuery<ArriveBbVO> query3 = new VOQuery<ArriveBbVO>(ArriveBbVO.class);
  // bbItems = query3.query(bb1ids.toArray(new String[0]));
  // }
  // // ����ɢ�ı�ͷ���������ݽ�����ӱ����ݣ�����������ľۺ�VO
  // ArriveTrans45QueryUtil tranQueryUtil =
  // new ArriveTrans45QueryUtil(headers, bitems, bbItems);
  // bills = tranQueryUtil.combine();
  // }
  // catch (Exception ex) {
  // ExceptionUtils.marsh(ex);
  // }
  // return bills;
  // }

  private String getCompleteSql(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String htname = qrySchemeProcessor.getMainTableAlias();
    String btname =
        qrySchemeProcessor
            .getTableAliasOfAttribute("pk_arriveorder_b.pk_arriveorder_b");
    StringBuffer from = new StringBuffer();
    // ����ί�ⶩ���ӱ������ж��Ƿ�ر�
    from.append(" inner join sc_order_b");
    from.append(" on " + btname + ".csourcebid = sc_order_b.pk_order_b ");

    // �������������ӱ����ڿ������
    String bbtable = "(select pk_arriveorder_b,pk_arriveorder_bb";
    bbtable +=
        " from po_arriveorder_bb where  upper(isnull(bcanstore,'Y')) = 'Y'";
    bbtable += " and isnull(nnum,0) - isnull(naccumstorenum,0)> 0 and dr = 0)";
    from.append(" left join " + bbtable + " po_arriveorder_bb");
    from.append(" on " + btname + ".pk_arriveorder_b");
    from.append(" = po_arriveorder_bb.pk_arriveorder_b");
    qrySchemeProcessor.appendFrom(from.toString());
    StringBuilder where = new StringBuilder();
    where.append(" and " + htname + ".fbillstatus = 3 ");// ���״̬
    where.append(" and upper(isnull(" + htname + ".bisback,'N')) = 'N'");// �Ƿ��˻�=N
    where.append(" and " + btname + ".csourcetypecode ='61' ");// ��Դ��ί�ⶩ��
    where.append(" and " + btname + ".nnum > 0 ");
    where.append(" and (isnull(" + btname + ".nnum,0)");// ����������-�ۼ��������>0
    where.append(" -isnull(" + btname + ".naccumstorenum,0))>0 ");
    where.append(" and upper(isnull(" + btname + ".bfaflag,'N')) = 'N' ");// �����ʲ���Ƭ��ʶ=N
    where.append(" and upper(isnull(sc_order_b.bclose,'N')) = 'N'");// �����ر�=N
    qrySchemeProcessor.appendWhere(where.toString());

    StringBuilder sql = new StringBuilder();
    sql.append(" select " + htname + ".pk_arriveorder, ");// ����ѡ��Ƭ��
    sql.append(btname + ".pk_arriveorder_b,");// ����ѡ��Ƭ��
    sql.append(" po_arriveorder_bb.pk_arriveorder_bb");// ����ѡ��Ƭ��
    sql.append(qrySchemeProcessor.getFinalFromWhere());
    return sql.toString();
  }

  // private String getCompleteSql(String fAndW) {
  // FromWhereParseUtil util = new FromWhereParseUtil(fAndW);
  // // ��ñ�ı���
  // String htname = util.getTableAlias("po_arriveorder");
  // if (StringUtils.isEmpty(htname)) {
  // htname = "po_arriveorder";
  // }
  // String btname = util.getTableAlias("po_arriveorder_b");
  // if (StringUtils.isEmpty(btname)) {
  // btname = "po_arriveorder_b";
  // }
  //
  // StringBuilder sql = new StringBuilder();
  // sql.append(" select po_arriveorder.pk_arriveorder,");// ����ѡ��Ƭ��
  // sql.append(" po_arriveorder_b.pk_arriveorder_b,");// ����ѡ��Ƭ��
  // sql.append(" po_arriveorder_bb.pk_arriveorder_bb");// ����ѡ��Ƭ��
  // sql.append(fAndW);
  // // ���ӹ̶�����
  // sql.append(" and po_arriveorder.dr = 0 ");
  // sql.append(" and po_arriveorder_b.dr = 0 ");
  // sql.append(" and po_arriveorder.fbillstatus = 3 ");// ���״̬
  // sql.append(" and upper(isnull(po_arriveorder.bisback,'N')) = 'N'");//
  // �Ƿ��˻�=N
  // sql.append(" and po_arriveorder_b.csourcetypecode ='61' ");// ��Դ��ί�ⶩ��
  // sql.append(" and po_arriveorder_b.nnum > 0 ");
  // sql.append(" and (isnull(po_arriveorder_b.nnum,0)");// ����������-�ۼ��������>0
  // sql.append(" -isnull(po_arriveorder_b.naccumstorenum,0))>0 ");
  // sql.append(" and upper(isnull(po_arriveorder_b.bfaflag,'N')) = 'N' ");//
  // �����ʲ���Ƭ��ʶ=N
  // sql.append(" and upper(isnull(sc_order_b.bclose,'N')) = 'N'");// �����ر�=N
  // String finalSql = sql.toString();
  // finalSql = StringUtils.replace(finalSql, "po_arriveorder.", htname + ".");
  // finalSql = StringUtils.replace(finalSql, "po_arriveorder_b.", btname +
  // ".");
  // return finalSql;
  // }

  private ArriveVO[] queryArriveBySql(String wholeSql) {
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(wholeSql);
    List<String> hids = new ArrayList<String>();
    List<String> itemids = new ArrayList<String>();
    List<String> bb1ids = new ArrayList<String>();
    while (rowset.next()) {
      String hid = rowset.getString(0);
      if (StringUtils.isNotEmpty(hid) && !hids.contains(hid)) {
        hids.add(hid);
      }
      String bid = rowset.getString(1);
      if (StringUtils.isNotEmpty(bid) && !itemids.contains(bid)) {
        itemids.add(bid);
      }
      String bbid = rowset.getString(2);
      if (StringUtils.isNotEmpty(bbid) && !bb1ids.contains(bbid)) {
        bb1ids.add(bbid);
      }
    }
    if (hids.size() == 0 || itemids.size() == 0) {
      return null;
    }

    // 1����ѯArriveHeaderVO
    VOQuery<ArriveHeaderVO> query1 =
        new VOQuery<ArriveHeaderVO>(ArriveHeaderVO.class);
    ArriveHeaderVO[] headers = query1.query(hids.toArray(new String[0]));

    // 2����ѯArriveItemVO
    VOQuery<ArriveItemVO> query2 =
        new VOQuery<ArriveItemVO>(ArriveItemVO.class);
    ArriveItemVO[] bitems = query2.query(itemids.toArray(new String[0]));

    // 3����ѯArriveBbVO
    ArriveBbVO[] bbItems = null;
    if (bb1ids.size() > 0) {
      VOQuery<ArriveBbVO> query3 = new VOQuery<ArriveBbVO>(ArriveBbVO.class);
      bbItems = query3.query(bb1ids.toArray(new String[0]));
    }
    // ����ɢ�ı�ͷ���������ݽ�����ӱ����ݣ�����������ľۺ�VO
    ArriveTrans45QueryUtil tranQueryUtil =
        new ArriveTrans45QueryUtil(headers, bitems, bbItems);
    return tranQueryUtil.combine();
  }

}
