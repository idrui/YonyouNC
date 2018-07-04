package nc.bs.pu.m23.maintain.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m23.pubquery.IArrivePubQuery;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 对于基于原到货单退货的情况，检查是否能够退货
 * @scene
 * 
 * @param
 * 无
 *
 * @since 6.3
 * @version 2012-8-14 下午03:45:43
 * @author lixyp
 */

public class ChkBackFor23Rule implements IRule<ArriveVO> {

  private List<String> checkResults = new ArrayList<String>();

  @Override
  public void process(ArriveVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    ArriveItemVO[] backItemVos = this.querySourceArriveItems(vos);
    if (ArrayUtils.isEmpty(backItemVos)) {
      // 不是退货单或不是基于原到货单退货的场景。
      return;
    }

    for (ArriveItemVO itemVo : backItemVos) {
      // 设备卡片检查
      this.checkFA(itemVo);
      // 转固检查
      this.checkTransasset(itemVo);
      // 检查入库。
      this.checkPurchaseIn(itemVo);
      // 检查紧急放行
      this.checkUrgenLetGo(itemVo);
    }

    // 拼接异常信息。
    if (!this.checkResults.isEmpty()) {
      StringBuffer buffer = new StringBuffer();
      for (String result : this.checkResults) {
        buffer.append(result);
        buffer.append("\n");
      }

      ExceptionUtils.wrappBusinessException(buffer.toString());
    }
  }

  /**
   * 检查是否生成了设备卡片
   * 
   * @param itemVo 到货单表体VO
   */
  private void checkFA(ArriveItemVO itemVo) {
    if (itemVo.getBfaflag() != null
        && UFBoolean.TRUE.equals(itemVo.getBfaflag())) {
      this.checkResults.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004040_0", "04004040-0196", null, new String[] {
            itemVo.getCrowno()
          })/*
             * @res
             * "第{0}行已经生成设备卡片，不允许退货！"
             */);
    }
  }

  /**
   * 检查是否存在采购入库。
   * 
   * @param itemVo 到货单表体VO
   */
  private void checkPurchaseIn(ArriveItemVO itemVo) {
    if (MathTool.greaterThan(itemVo.getNaccumstorenum(), UFDouble.ZERO_DBL)) {
      this.checkResults.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004040_0", "04004040-0198", null, new String[] {
            itemVo.getCrowno()
          })/*
             * @res
             * "第{0}行的已生成采购入库单，不允许退货！"
             */);
    }
  }

  /**
   * 检查是否已经转固定资产
   * 
   * @param itemVo 到货单表体VO
   */
  private void checkTransasset(ArriveItemVO itemVo) {
    if (itemVo.getBtransasset() != null
        && UFBoolean.TRUE.equals(itemVo.getBtransasset())) {
      this.checkResults.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004040_0", "04004040-0197", null, new String[] {
            itemVo.getCrowno()
          })/*
             * @res
             * "第{0}行已经转固定资产，不允许退货！"
             */);
    }
  }

  /**
   * 检查紧急放行
   * 
   * @param itemVo 到货单表体VO
   */
  private void checkUrgenLetGo(ArriveItemVO itemVo) {
    // 合格数量
    UFDouble nelignum = itemVo.getNelignum();
    // 不合格数量
    UFDouble nnotelignum = itemVo.getNnotelignum();
    // 合格数量 + 不合格数量 = 0并且累计紧急放行数量不等于0，说明已生成紧急放行单，且没有质检完成，不能退货。
    // 紧急放行状态标志在此不适用，该标志是在紧急放行单审批的时候置为true的。所以无法区分刚刚生成紧急放行单和质检完成这两种场景。
    if (MathTool.isZero(MathTool.add(nelignum, nnotelignum))
        && MathTool.greaterThan(itemVo.getNaccumletgonum(), UFDouble.ZERO_DBL)) {
      this.checkResults.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004040_0", "04004040-0200", null, new String[] {
            itemVo.getCrowno()
          })/*
             * @res
             * "第{0}行已经产生紧急放行单，且质检未完成，不允许退货！"
             */);
    }
  }

  /**
   * 查询原到货单表体数据。
   * 
   * @param arriveVos
   * @return
   */
  private ArriveItemVO[] querySourceArriveItems(ArriveVO[] arriveVos) {
    List<String> bids = new ArrayList<String>();
    try {

      for (ArriveVO arriveVo : arriveVos) {
        // 只检查退货单。
        if (!arriveVo.getHVO().getBisback().booleanValue()) {
          continue;
        }
        for (ArriveItemVO itemVo : arriveVo.getBVO()) {
          // 只检查基于原到货单退货的情况。
          if (itemVo.getCsourcearrivebid() == null) {
            continue;
          }
          bids.add(itemVo.getCsourcearrivebid());
        }
      }

      if (bids.isEmpty()) {
        return null;
      }

      IArrivePubQuery arriveQuery =
          NCLocator.getInstance().lookup(IArrivePubQuery.class);
      return arriveQuery
          .queryItemVOByBids(bids.toArray(new String[bids.size()]));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
