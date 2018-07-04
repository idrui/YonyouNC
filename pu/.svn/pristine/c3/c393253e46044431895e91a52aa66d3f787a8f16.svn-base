package nc.bs.pu.m27.settlebill.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.it.m5805.entity.DetailBVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.ICBillType;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.pubitf.it.m5801.pu.m25.IRewrite5801For25;
import nc.pubitf.it.m5801.pu.m25.Rewrite5801For25Para;
import nc.pubitf.it.m5805.pu.m27.IRewrite5805For27;
import nc.pubitf.it.m5805.pu.m25.Rewrite5805For25Para;
import nc.pubitf.it.m5805.pu.m27.IQuery5805For27;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m27.settlebill.rule.WritebackPoint;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description 无来源发票结算回写合同和明细
 * 
 * @since 6.36
 * @version 2015-3-18 上午10:28:12
 * @author yixl
 */

public class WriteM5801AndM5805Rule implements IRule<SettleBillVO> {

  private WritebackPoint point = WritebackPoint.SAVE;

  private SettleEnvironment settleEnv;

  /**
   * 
   * @param point
   */
  public WriteM5801AndM5805Rule(WritebackPoint point) {
    this.point = point;
  }

  /**
   * 
   * @param point
   * @param settleEnv
   */
  public WriteM5801AndM5805Rule(WritebackPoint point,
      SettleEnvironment settleEnv) {
    this.settleEnv = settleEnv;
    this.point = point;
  }

  /**
   * 获得回写时点
   * 
   * @return WritebackPoint
   */
  public WritebackPoint getPoint() {
    return this.point;
  }

  /**
   * 结算环境信息
   * 
   * @return SettleEnvironment
   */
  public SettleEnvironment getSettleEnv() {
    return this.settleEnv;
  }

  @Override
  public void process(SettleBillVO[] vos) {
    if (!SysInitGroupQuery.isITEnabled()) {
      return;
    }

    // 获得发票不是来源于订单的采购结算单行
    SettleBillItemVO[] items = this.getNotFromDetailItemVOs(vos);
    if (items.length == 0) {
      return;
    }
    // 根据结算单行构造回写参数对象数组
    Rewrite5805For25Para[] dtParams = new Rewrite5805For25Para[items.length];
    Rewrite5801For25Para[] ctParams = new Rewrite5801For25Para[items.length];
    this.getWriteBackParams(items, dtParams, ctParams);
//回写明细
    IRewrite5805For27 rewriteDetail =
        NCLocator.getInstance().lookup(IRewrite5805For27.class);
    // 回写合同
    IRewrite5801For25 rewriteContract =
        NCLocator.getInstance().lookup(IRewrite5801For25.class);
    try {
      rewriteDetail.rewrite5805VoiceNumFor25(dtParams);
      rewriteContract.rewrite5801VoiceNumFor25(ctParams);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 获得发票不是来源于明细单的行
   * 
   * @param vos
   * @return
   */
  private SettleBillItemVO[] getNotFromDetailItemVOs(SettleBillVO[] vos) {
    List<SettleBillItemVO> items = new ArrayList<SettleBillItemVO>();
    for (SettleBillVO vo : vos) {
      SettleBillItemVO[] itemVos = vo.getChildrenVO();
      for (SettleBillItemVO itemVo : itemVos) {
        Integer rowType = itemVo.getFrowtype();
        if (EnumMatchRowType.StockInvoiceMatch.value().equals(rowType)
            && ICBillType.PurchaseIn.getCode().equals(
                itemVo.getVstockbilltype())
            && itemVo.getPk_invoiceorder_b() == null
            && itemVo.getPk_stockorder_b() != null) {
          items.add(itemVo);
        }
      }
    }
    return items.toArray(new SettleBillItemVO[items.size()]);
  }

  private void getWriteBackParams(SettleBillItemVO[] items,
      Rewrite5805For25Para[] dtParams, Rewrite5801For25Para[] ctParams) {
    // 获得发票无税单价
    Set<String> detaiBid = new HashSet<String>();
    for (int i = 0; i < items.length; i++) {
      UFDouble diffNum = MathTool.nvl(items[i].getNsettlenum());
      if (this.point == WritebackPoint.DELETE) {
        diffNum = MathTool.oppose(diffNum);
      }
      dtParams[i] =
          new Rewrite5805For25Para(items[i].getPk_stockorder_b(), diffNum);
      detaiBid.add(items[i].getPk_stockorder_b());
    }

    // 根据进口明细ID查询明细单来源合同的id
    IQuery5805For27 queryCt =
        NCLocator.getInstance().lookup(IQuery5805For27.class);
    String[] bids = new String[detaiBid.size()];
    Map<String, DetailBVO> dtBvos = new HashMap<String, DetailBVO>();
    try {
      dtBvos = queryCt.querySrcCtbyBids(detaiBid.toArray(bids));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (dtBvos.size() > 0) {
      for (int i = 0; i < dtParams.length; i++) {
        String dtbid = dtParams[i].getCdetailbid();
        DetailBVO dtBvo = dtBvos.get(dtbid);
        ctParams[i] =
            new Rewrite5801For25Para(dtBvo.getCsrcbid(),
                dtParams[i].getNchangenum());
      }

    }
  }

}
