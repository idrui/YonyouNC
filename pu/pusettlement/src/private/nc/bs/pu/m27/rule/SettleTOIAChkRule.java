package nc.bs.pu.m27.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * ���ý��㡢�ݹ�ǰ�����Ӱ��ɱ�����ⵥ/���Ļ����еĽ����Ƿ��Ѿ����ɱ�
 * 
 * @since 6.0
 * @version 2011-3-10 ����10:31:23
 * @author zhaoyha
 */
public class SettleTOIAChkRule {

  /**
   * ���Ӱ��ɱ�����ⵥ/���Ļ����еĽ����Ƿ��Ѿ����ɱ�
   * 
   * @param views ���������ݹ�VO
   */
  public void process(AbstractDataView[] views) {
    String[] bids = this.getCheckBID(views);
    if (bids.length == 0) {
      return;
    }
    this.check(bids);// ����Ƿ���ڽ���δ���ɱ�

  }

  private void check(String[] bids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select count(h." + SettleBillHeaderVO.PK_SETTLEBILL + ")");
    sql.append(" from ");
    sql.append(PUEntity.SettleBill_H_TAB + " h inner join ");
    sql.append(PUEntity.SettleBill_B_TAB);
    sql.append(" b on h." + SettleBillHeaderVO.PK_SETTLEBILL + "=");
    sql.append(" b." + SettleBillItemVO.PK_SETTLEBILL);
    sql.append(" where h.dr=0 and b.dr=0");
    sql.append(" and isnull(h." + SettleBillHeaderVO.BTOIA + ",'"
        + UFBoolean.FALSE);
    sql.append("')", UFBoolean.FALSE);
    sql.appendNot(" and b." + SettleBillItemVO.FROWTYPE, new Integer[] {
      EnumMatchRowType.StockFeeSettle.toInteger()
    });
    String inName = "b." + SettleBillItemVO.PK_STOCK_B;
    sql.append(" and "
        + new IDExQueryBuilder(PUTempTable.tmp_po_27_04.name()).buildSQL(
            inName, bids));
    IRowSet rs = new DataAccessUtils().query(sql.toString());
    if (rs.size() == 0) {
      return;
    }
    while (rs.next()) {
      if (rs.getInt(0) > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0071")/*
                                                                     * @res
                                                                     * "��ⵥ/���Ļ��ܴ����Ѿ�����Ĳ���δ���ɱ������ܽ��д˲�����"
                                                                     */);
      }
    }
  }

  private String[] getCheckBID(AbstractDataView[] views) {
    List<String> bidLst = new ArrayList<String>();
    for (AbstractDataView view : views) {
      UFBoolean affcost =
          (UFBoolean) view.getAttributeValue(GoodsEstVO.BAFFECTCOST);

      if (UFBoolean.TRUE.equals(affcost)) {
        bidLst.add((String) view.getAttributeValue(GoodsEstVO.PK_STOCKPS_B));
      }
    }
    return bidLst.toArray(new String[bidLst.size()]);
  }
}
