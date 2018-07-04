package nc.bs.pu.m23.writeback.qc;

import nc.bs.pu.m23.writeback.qc.c003rule.ChkWriteParaForC003Rule;
import nc.bs.pu.m23.writeback.qc.c003rule.SynchBtableEligNumRule;
import nc.bs.pu.m23.writeback.qc.c003rule.WriteC005WhenResultFeedbackRule;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>质检报告回写到货单的BP类(包括：质检报告审批、质检报告取消审批)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-9-29 上午09:17:52
 */
public class Writeback23ForC003BP {

  public void writebackWhenApprove(ArriveBbVO[] paras) {

    // 添加执行业务规则
    AroundProcesser<ArriveBbVO> proc = new AroundProcesser<ArriveBbVO>(null);
    this.addBeforeRule(proc, true);
    this.addAfterRule(proc, true);

    proc.before(paras);

    // 2、插入子子表数据
    VOInsert<ArriveBbVO> util = new VOInsert<ArriveBbVO>();
    util.insert(paras);

    proc.after(paras);
  }

  public void writebackWhenUnApprove(ArriveBbVO[] paras) {

    // 添加执行业务规则
    AroundProcesser<ArriveBbVO> proc = new AroundProcesser<ArriveBbVO>(null);
    this.addBeforeRule(proc, false);
    this.addAfterRule(proc, false);

    proc.before(paras);

    // 按到货单行主键查询子子表数据
    String[] reportids =
        (String[]) AggVOUtil.getDistinctFieldArray(paras,
            ArriveBbVO.PK_QCREPORT, String.class);
    if (ArrayUtils.isEmpty(reportids)) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
              "04004040-0109")/* @res "回写到货单质检结果时，质检报告的主键不能为空！" */;
      ExceptionUtils.wrappBusinessException(message);
    }
    SqlBuilder sqlBld = new SqlBuilder();
    sqlBld.append("select pk_arriveorder_bb from po_arriveorder_bb where ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_23_17.name());
    sqlBld.append(builder.buildSQL(ArriveBbVO.PK_QCREPORT, reportids));
    sqlBld.append(" and dr = 0");

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sqlBld.toString());
    String[] bbids = rowset.toOneDimensionStringArray();
    if (!ArrayUtils.isEmpty(bbids)) {
      // 到货单在复检走完全部的流程再点检验，报错找不到到货单子子表对应的记录.到货单已经删除，故此处不删除也可以
      // 改为判断bbids不为空时才去删
      // String message =
      // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0110")/*@res
      // "回写到货单质检结果时，找不到到货单子子表对应的记录，出现并发！"*/;
      // ExceptionUtils.wrappBusinessException(message);

      // 删除对应的子子表数据
      VOQuery<ArriveBbVO> util = new VOQuery<ArriveBbVO>(ArriveBbVO.class);
      ArriveBbVO[] bbItems = util.query(bbids);
      new VODelete<ArriveBbVO>().delete(bbItems);
    }

    proc.after(paras);
  }

  private void addAfterRule(AroundProcesser<ArriveBbVO> proc, boolean isApprove) {
    // 更新子表中合格数量、不合格数量
    proc.addAfterRule(new SynchBtableEligNumRule(isApprove));

    // 当回写、删除到货单的质检结果时，回写紧急放行单的是否反馈质检结果的标志
    proc.addAfterRule(new WriteC005WhenResultFeedbackRule(isApprove));
  }

  private void addBeforeRule(AroundProcesser<ArriveBbVO> proc, boolean isApprove) {
    // 检查回写参数
    proc.addBeforeRule(new ChkWriteParaForC003Rule(isApprove));
  }
}
