package nc.impl.pu.m23.maintain.action;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m23.plugin.ArriveActionPlugInPoint;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ic.batchcode.BatchSynchronizer;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.BatchCodeFieldMap;
import nc.vo.pu.m23.rule.BatchCodeItemAdapter;
import nc.vo.pu.m23.utils.FillBillInfoFor23;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.rule.PUBillLazyQuery;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��������ѯAction
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-5-13 ����07:38:11
 */
public class ArriveQueryAction {

  public ArriveVO[] lazyQuery(IQueryScheme qs) {
    ArriveVO[] bills =
        (ArriveVO[]) new PUBillLazyQuery<ArriveVO>(ArriveVO.class,
            POBillType.Arrive).queryOrderByBillcode(qs);
    // ������������
    bills = this.dealCanstorenum(bills);
    if (bills != null) {
      // ���ÿ��ӿ�ͬ�����κ�����
      bills = this.synchBatchCodeData(bills);
      // �����ݼ������� �̶�������
      FillBillInfoFor23.fillBillInfo(bills);
    }

    AroundProcesser<ArriveVO> processer =
        new AroundProcesser<ArriveVO>(ArriveActionPlugInPoint.ArriveQueryAction);
    bills = processer.before(bills);
    bills = processer.after(bills);

    return bills;
  }

  /**
   * ����������ʱˢ�±���
   * 
   * @param oldBills
   * @return
   */
  public ArriveVO[] refreshQuery(ArriveVO[] oldBills) {
    ArriveVO[] bills =
        (ArriveVO[]) new PUBillLazyQuery<ArriveVO>(ArriveVO.class,
            POBillType.Arrive).refreshItem(oldBills);
    // ������������
    bills = this.dealCanstorenum(bills);
    // ���ÿ��ӿ�ͬ�����κ�����
    bills = this.synchBatchCodeData(bills);
    // �����ݼ������� �̶�������
    FillBillInfoFor23.fillBillInfo(bills);
    return bills;
  }

  private ArriveVO[] dealCanstorenum(ArriveVO[] bills) {
    if (ArrayUtils.isEmpty(bills)) {
      return null;
    }

    String[] bids =
        (String[]) AggVOUtil.getDistinctItemFieldArray(bills,
            ArriveItemVO.PK_ARRIVEORDER_B, String.class);

    // ��ѯ��Ӧ�ļ�����ϸ
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select pk_arriveorder_bb from po_arriveorder_bb");
    sql.append(" where dr = 0 and ");

    IDExQueryBuilder idbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_23_04.name());
    String inpart = idbuilder.buildSQL("pk_arriveorder_b", bids);
    sql.append(inpart);

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    List<String> bbids = new ArrayList<String>();
    while (rowset.next()) {
      String bbid = rowset.getString(0);
      bbids.add(bbid);
    }

    ArriveBbVO[] bbItems = null;
    if (bbids.size() > 0) {
      VOQuery<ArriveBbVO> queryUtil = new VOQuery<ArriveBbVO>(ArriveBbVO.class);
      bbItems = queryUtil.query(bbids.toArray(new String[0]));
    }
    for (ArriveVO vo : bills) {
      if (null == vo.getBVO()) {
        continue;
      }
      for (ArriveItemVO item : vo.getBVO()) {
        if (item == null) {
          continue;
        }
        String bid = item.getPk_arriveorder_b();
        ArriveBbVO[] bbvos = this.findBbVOByBid(bbItems, bid);
        if (ValueUtils.getBoolean(vo.getHVO().getBisback())) {
          item.setNcanstorenum(UFDouble.ZERO_DBL);// �˻�ʱ�����������Ϊ0
        }
        else if (ArrayUtils.isEmpty(bbvos)) {
          UFDouble nnum = MathTool.nvl(item.getNnum());
          UFDouble innum = MathTool.nvl(item.getNaccumstorenum());
          item.setNcanstorenum(nnum.sub(innum));// ͬ�����������
        }
        else {
          UFDouble canstorenum = UFDouble.ZERO_DBL;
          for (ArriveBbVO bbitem : bbvos) {
            if (!bbitem.getBcanstore().booleanValue()) {
              continue;
            }

            UFDouble bbnnum = MathTool.nvl(bbitem.getNnum());
            UFDouble bbinnum = MathTool.nvl(bbitem.getNaccumstorenum());
            UFDouble bbcanstorenum = MathTool.sub(bbnnum, bbinnum);
            canstorenum = MathTool.add(canstorenum, bbcanstorenum);
          }
          item.setNcanstorenum(canstorenum);// ͬ��������ϸ���п��������
        }
      }
    }
    return bills;
  }

  private ArriveBbVO[] findBbVOByBid(ArriveBbVO[] bbitems, String bid) {
    // ���ݵ�������ID,���Ҷ�Ӧ�����еļ�������ϸ
    if (ArrayUtils.isEmpty(bbitems)) {
      return null;
    }

    List<ArriveBbVO> bbitemList = new ArrayList<ArriveBbVO>();
    for (ArriveBbVO bbitem : bbitems) {
      if (!bid.equals(bbitem.getPk_arriveorder_b())) {
        continue;
      }
      bbitemList.add(bbitem);
    }
    return bbitemList.toArray(new ArriveBbVO[0]);
  }

  private ArriveVO[] synchBatchCodeData(ArriveVO[] vos) {
    List<BatchCodeItemAdapter> list = new ArrayList<BatchCodeItemAdapter>();
    for (int i = 0, len = vos.length; i < len; i++) {
      if (null == vos[i].getBVO()) {
        continue;
      }
      for (ArriveItemVO item : vos[i].getBVO()) {
        if (null == item || StringUtils.isEmpty(item.getPk_batchcode())) {
          continue;
        }
        item.setAttributeValue(ArriveHeaderVO.VBILLCODE, vos[i].getHVO()
            .getVbillcode());
        list.add(new BatchCodeItemAdapter(item));
      }
    }
    if (list.size() == 0) {
      return vos;
    }

    BatchCodeFieldMap filedMap = new BatchCodeFieldMap();
    BatchSynchronizer service = new BatchSynchronizer(filedMap);
    try {
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004040_0", "04004040-0130")/* @res "���ÿ���ṩ��ͬ�����κŽӿ�" */);
      BatchCodeItemAdapter[] paras =
          list.toArray(new BatchCodeItemAdapter[list.size()]);
      service.fillBatchVOtoBill(paras);
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004040_0", "04004040-0131")/* @res "�ɹ����ÿ���ṩ��ͬ�����κŽӿ�" */);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    return vos;
  }
}
