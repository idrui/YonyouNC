package nc.bs.pu.m23.maintain.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m23.pubquery.IArrivePubQuery;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.env.ArrivalUIToBSEnv;
import nc.vo.pu.m23.exception.AskWithCheckException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 * 在退货操作时，对是否已经生成报检单，以及质检报告的可入库数量进行校验。
 * @scene
 * 
 * @param
 * 无
 *
 * @since 6.3
 * @version 2012-8-31 上午09:16:09
 * @author lixyp
 */

public class ChkCheckNumOnBackRule implements IRule<ArriveVO> {

  // key=来源到货单BID, value=是否存在质检报告。
  // 存在质检报告的情况不显示报检的提示信息。
  private Map<String, Boolean> checkReportStatus =
      new HashMap<String, Boolean>();

  private ArrivalUIToBSEnv env;

  private StringBuffer errorBuffer = new StringBuffer();

  public ChkCheckNumOnBackRule(ArrivalUIToBSEnv env) {
    this.env = env;
  }

  @Override
  public void process(ArriveVO[] vos) {
    if (null != this.env.getbBack() && this.env.getbBack().booleanValue()) {
      return;
    }

    // 获取来源到货单，如不存在来源到货单，则不需要执行此规则，退出。
    ArriveItemVO[] itemVos = this.getSourceItemVos(vos);
    if (itemVos == null) {
      return;
    }

    // 这里需要先检查质检报告，再检查报检单。
    this.validateWithCheckReport(itemVos, vos);
    this.validateWithSendCheck(itemVos, vos);

    if (!StringUtils.isEmpty(this.errorBuffer.toString())) {
      this.errorBuffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004000_0", "04004000-0133"));/*
                                                      * @res
                                                      * "是否继续？"
                                                      */

      ExceptionUtils.wrappException(new AskWithCheckException(this.errorBuffer
          .toString()));
    }
  }

  /**
   * 获取来源表体VO
   * 
   * @param vos
   * @return
   */
  private ArriveItemVO[] getSourceItemVos(ArriveVO[] vos) {
    try {
      List<String> bids = new ArrayList<String>();
      for (ArriveVO vo : vos) {
        // 只有基于到货单退货的退货单进行此校验。
        for (ArriveItemVO itemVo : vo.getBVO()) {
          if (itemVo.getCsourcearrivebid() != null) {
            bids.add(itemVo.getCsourcearrivebid());
          }
        }
      }
      if (!bids.isEmpty()) {
        IArrivePubQuery arriveQuery =
            NCLocator.getInstance().lookup(IArrivePubQuery.class);
        return arriveQuery.queryItemVOByBids(bids.toArray(new String[bids
            .size()]));
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * 查询来源到货单BB表信息，BB表中存储了关于到货单质检的结果信息。
   * 
   * @return BB表信息结果集
   */
  private IRowSet querySourceArriveBBInfo(ArriveItemVO[] sourceItemVos) {
    List<String> sourcebids = new ArrayList<String>();
    for (ArriveItemVO itemVo : sourceItemVos) {
      sourcebids.add(itemVo.getPk_arriveorder_b());
    }

    // 关联孙表查询可入库数量。
    SqlBuilder sqlBuilder = new SqlBuilder();
    sqlBuilder
        .append("select po_arriveorder_b.pk_arriveorder_b, po_arriveorder_b.crowno, po_arriveorder_b.nnum, po_arriveorder_b.naccumbacknum, a.ncanstorenum ");
    sqlBuilder.append("from po_arriveorder_b ");
    sqlBuilder.append("inner join (");
    sqlBuilder.append("select po_arriveorder_bb.pk_arriveorder_b, ");
    sqlBuilder.append("sum(po_arriveorder_bb.nnum) ncanstorenum ");
    sqlBuilder.append("from po_arriveorder_bb ");
    sqlBuilder.append("where bcanstore = 'Y' and dr = 0 ");
    sqlBuilder.append("group by po_arriveorder_bb.pk_arriveorder_b ");
    sqlBuilder
        .append(") a on a.pk_arriveorder_b = po_arriveorder_b.pk_arriveorder_b where ");
    sqlBuilder.append("po_arriveorder_b.pk_arriveorder_b",
        sourcebids.toArray(new String[sourcebids.size()]));

    DataAccessUtils utils = new DataAccessUtils();
    return utils.query(sqlBuilder.toString());
  }

  /**
   * 验证质检报告数量。
   * 
   * @param vos 到货单VO
   */
  private void validateWithCheckReport(ArriveItemVO[] sourceItemVos,
      ArriveVO[] vos) {
    IRowSet rowset = this.querySourceArriveBBInfo(sourceItemVos);
    String sourcebid = null, crowno = null;
    UFDouble sourceNum = null, naccumBackNum = null, canStoreNum = null;

    while (rowset.next()) {
      sourcebid = rowset.getString(0);// 来源到货单BID
      crowno = rowset.getString(1);// 来源到货单行号
      sourceNum = rowset.getUFDouble(2); // 来源到货单主数量
      naccumBackNum = rowset.getUFDouble(3);// 来源到货单累计退货主数量
      canStoreNum = rowset.getUFDouble(4);// 关联孙表查询的可入库主数量

      for (ArriveVO vo : vos) {
        for (ArriveItemVO itemVo : vo.getBVO()) {
          if (itemVo.getCsourcearrivebid().equals(sourcebid)) {
            // 记录存在质检报告的来源到货单BID，在后续的报检检查中不检查相应的到货单表体了。
            this.checkReportStatus.put(sourcebid, Boolean.TRUE);

            // 剩余主数量与可入库主数量比较
            // 剩余主数量 = 来源到货单主数量 - 累计退货主数量 + 当前退货单主数量（负数）
            if (MathTool.lessThan(
                MathTool.add(itemVo.getNnum(),
                    MathTool.sub(sourceNum, naccumBackNum)), canStoreNum)) {
              this.errorBuffer.append(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4004040_0", "04004040-0201",
                      null, new String[] {
                        crowno
                      }));/*
                           * @res
                           * "第{0}行退货后，剩余主数量将小于质检结果判定的可入库数量，建议先修改质检结果再退货。"
                           */
              this.errorBuffer.append("\n");
            }
          }
        }
      }
    }
  }

  /**
   * 验证报检。
   * 
   * @param vos
   */
  private void validateWithSendCheck(ArriveItemVO[] sourceItemVos,
      ArriveVO[] vos) {
    UFDouble remainNum = null;
    for (ArriveItemVO sourceItemVo : sourceItemVos) {
      if (MathTool.isZero(sourceItemVo.getNaccumchecknum())
          || sourceItemVo.getNaccumchecknum() == null
          || this.checkReportStatus.containsKey(sourceItemVo
              .getPk_arriveorder_b())) {
        continue;
      }
      for (ArriveVO vo : vos) {
        for (ArriveItemVO backItemVo : vo.getBVO()) {
          if (sourceItemVo.getPk_arriveorder_b().equals(
              backItemVo.getCsourcearrivebid())) {
            // 计算剩余数量
            remainNum =
                MathTool.add(
                    MathTool.sub(sourceItemVo.getNnum(),
                        sourceItemVo.getNaccumbacknum()), backItemVo.getNnum());
            if (MathTool.lessThan(remainNum, sourceItemVo.getNaccumchecknum())) {
              this.errorBuffer.append(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4004040_0", "04004040-0202",
                      null, new String[] {
                        backItemVo.getCrowno()
                      }));/*
                           * @res
                           * "第{0}行退货后，剩余主数量将小于累计报检主数量，建议先修改报检单再退货。"
                           */
              this.errorBuffer.append("\n");
            }
          }
        }
      }
    }
  }
}
