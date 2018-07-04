package nc.bs.pu.m23.writeback.qc.c003rule;

import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.jdbc.framework.util.DBConsts;
import nc.pubitf.qc.c005.pu.m23.IWriteBackFor23;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.qc.c005.pub.rewrite.WriteBackC005Para;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>当回写、删除到货单的质检结果时，回写紧急放行单的是否反馈质检结果的标志
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-9-29 上午09:11:48
 */
public class WriteC005WhenResultFeedbackRule implements IRule<ArriveBbVO> {

  // //质检报告是否审批操作
  private boolean isApprove;

  public WriteC005WhenResultFeedbackRule(boolean isApprove) {
    this.isApprove = isApprove;
  }

  @Override
  public void process(ArriveBbVO[] paras) {
    // 查询进行过紧急放行的到货单行、到货单行的之前质检结果反馈
    ArriveItemVO[] items = this.queryBeenLetgoItemVO(paras);
    if (items == null || items.length == 0) {
      return;
    }
    ArriveBbVO[] otherResults = this.queryOtherBbVO(items, paras);
    // 如果之前反馈过质检结果，就不需要回写紧急放行单
    if (otherResults != null && otherResults.length != 0) {
      return;
    }
    // 同时把到货单行的紧急放行状态=N(紧急放行单会自动关闭)
    if (this.isApprove) {
      for (ArriveItemVO item : items) {
        item.setBletgostate(UFBoolean.FALSE);
        item.setStatus(VOStatus.UPDATED);
      }
      String[] names = new String[1];
      names[0] = ArriveItemVO.BLETGOSTATE;
      new VOUpdate<ArriveItemVO>().update(items, names);
    }
    // 到货单的质检结果时,之前没有反馈过质检结果，此时需要回写紧急放行单
    this.writeback(items);
  }

  private ArriveItemVO[] queryBeenLetgoItemVO(ArriveBbVO[] paras) {
    if (paras == null) {
      return null;
    }
    Set<String> bidset =
        CirVOUtil.getDistinctFieldSet(paras, ArriveBbVO.PK_ARRIVEORDER_B);
    String[] bids = bidset.toArray(new String[bidset.size()]);
    // 进行过紧急放行的过滤条件
    String cond =
        " and (pk_passbill<>'" + DBConsts.NULL_WAVE + "' or pk_passbill_b<>'"
            + DBConsts.NULL_WAVE + "')";
    return new VOQuery<ArriveItemVO>(ArriveItemVO.class).query(bids, cond);
  }

  private ArriveBbVO[] queryOtherBbVO(ArriveItemVO[] items, ArriveBbVO[] paras) {
    if (items == null || paras == null) {
      return null;
    }
    Set<String> bidset =
        CirVOUtil.getDistinctFieldSet(items, ArriveBbVO.PK_ARRIVEORDER_B);
    String[] bids = bidset.toArray(new String[bidset.size()]);
    Set<String> bbidset =
        CirVOUtil.getDistinctFieldSet(paras, ArriveBbVO.PK_ARRIVEORDER_BB);
    String[] bbids = bbidset.toArray(new String[bbidset.size()]);
    if (bids == null || bids.length == 0 || bbids == null || bbids.length == 0) {
      return null;
    }
    // 到货单行的之前质检结果反馈
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select pk_arriveorder_bb from po_arriveorder_bb ");
    sql.append(" where dr=0 and ");

    // TODO 平台目前没有not in的逻辑，所以改回原来的方法，后续考虑。
    // sql.appendNot(ArriveBbVO.PK_ARRIVEORDER_BB, bbids);
    // IDExQueryBuilder builder =
    // new IDExQueryBuilder(PUTempTable.tmp_po_23_16.name());
    // sql.append(builder.buildSQL(ArriveBbVO.PK_ARRIVEORDER_BB, bbids));

    if (bbids.length == 1) {
      sql.appendNot(ArriveBbVO.PK_ARRIVEORDER_BB, bbids);
    }
    else {
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_23_16.name());
      sql.append(builder
          .buildSQL(ArriveBbVO.PK_ARRIVEORDER_BB + " not ", bbids));
    }

    IDExQueryBuilder idquery =
        new IDExQueryBuilder(PUTempTable.tmp_po_23_03.name());
    sql.append(" and " + idquery.buildSQL(ArriveBbVO.PK_ARRIVEORDER_B, bids));
    String[] totherbbids =
        new DataAccessUtils().query(sql.toString()).toOneDimensionStringArray();
    if (totherbbids == null || totherbbids.length == 0) {
      return null;
    }
    return new VOQuery<ArriveBbVO>(ArriveBbVO.class).query(totherbbids);
  }

  private void writeback(ArriveItemVO[] items) {
    if (items == null || items.length == 0) {
      return;
    }
    WriteBackC005Para[] paras = new WriteBackC005Para[items.length];
    for (int i = 0, len = items.length; i < len; i++) {
      paras[i] = new WriteBackC005Para();
      paras[i].setCheckResult(this.isApprove);
      paras[i].setPk_passbill(items[i].getPk_passbill());
      paras[i].setPk_passbill_b(items[i].getPk_passbill_b());
    }
    IWriteBackFor23 sv = NCLocator.getInstance().lookup(IWriteBackFor23.class);
    try {
      if (this.isApprove) {
        sv.writeBackWhenResult(paras);
      }
      else {
        sv.writeBackWhenNoResult(paras);
      }
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }
}
