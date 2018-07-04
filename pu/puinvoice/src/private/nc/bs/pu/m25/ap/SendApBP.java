/**
 * 
 */
package nc.bs.pu.m25.ap;

import java.util.Map;

import nc.bs.pu.m25.ap.rule.BackWashEstAPRule;
import nc.bs.pu.m25.ap.rule.InvcAPInfoUpdateRule;
import nc.bs.pu.m25.ap.rule.MakeDiffConfirmAPRule;
import nc.bs.pu.m25.ap.rule.NoSourceSplitRule;
import nc.bs.pu.m25.ap.rule.RedBlueSettleTOAPRule;
import nc.bs.pu.m25.ap.rule.SendAPAfterEventRuel;
import nc.bs.pu.m25.ap.rule.SendAPBeforeEventRuel;
import nc.bs.pu.m25.ap.rule.SendAPBusitypeCheckRule;
import nc.bs.pu.m25.ap.rule.SendAPTrantypeChkRule;
import nc.bs.pu.m25.plugin.InvoicePluginPoint;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m25.rule.ap.SendAPStateFilterRule;
import nc.vo.pu.m27.query.SettleBillInfo;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.ICBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>传应付</li>
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-2 下午04:47:53
 */
public class SendApBP {

  private InvoiceUIToBSEnv env;

  public SendApBP(InvoiceUIToBSEnv env) {
    this.env = env;
  }

  public InvoiceVO[] sendAP(InvoiceVO[] invoiceVOs, InvoiceVO[] orgVos) {
    if (!SysInitGroupQuery.isAPEnabled()) {
      return invoiceVOs;
    }
    InvoiceVO[] vos = invoiceVOs;
    AroundProcesser<InvoiceVO> processer =
        new AroundProcesser<InvoiceVO>(InvoicePluginPoint.SEND_AP);
    MapList<String, SettleBillInfo> sttlInfoMap =
        EstInfoMaintainUtil.getSttlInfo(vos, this.env);
    Map<String, EstVO[]> estVOMap =
        EstInfoMaintainUtil.queryEstInfo(vos, sttlInfoMap);
    this.addRule(processer, sttlInfoMap, estVOMap);
    vos = processer.before(vos);
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    vos = new BillUpdate<InvoiceVO>().update(vos, orgVos);
    vos = processer.after(vos);
    return vos;
  }

  private void addRule(AroundProcesser<InvoiceVO> processer,
      MapList<String, SettleBillInfo> sttlInfo, Map<String, EstVO[]> estVOMap) {
    // 可传应付的发票状态(标志)检查
    processer.addBeforeFinalRule(new SendAPStateFilterRule(true));
    // 检查传应付是否收付的保存动作
    processer.addBeforeFinalRule(new SendAPBusitypeCheckRule());
    // 红蓝发票对冲过的处理(放在更新标志之前)
    processer.addBeforeFinalRule(new RedBlueSettleTOAPRule(sttlInfo, this.env));
    // 更新发票的传应付信息(标志，VO状态等)
    processer.addBeforeFinalRule(new InvcAPInfoUpdateRule());
    // 传应付时的发票交易类型检查
   // processer.addBeforeFinalRule(new SendAPTrantypeChkRule(sttlInfo));
    // 业务事件 前
    processer.addBeforeFinalRule(new SendAPBeforeEventRuel());
    // 对暂估应付进行回冲--一定要到放前规则(后规则中可能改变发票VO结构)
    processer.addBeforeFinalRule(new BackWashEstAPRule(estVOMap, sttlInfo));

    // 业务事件 后
    processer.addAfterFinalRule(new SendAPAfterEventRuel());
    // 暂估就付的回冲－－放到前规则，要更新发票的一些信息
    // 无来源订单的发票按结算明细进行拆分(此规则要放到调差规则之前)
    processer.addAfterFinalRule(new NoSourceSplitRule(sttlInfo));
    // 采购入库单的确认应付的调差－－放到后规则，有可能改变发票VO的结构（用于推应付的脚本）
    processer.addAfterFinalRule(new MakeDiffConfirmAPRule(
        null == estVOMap ? null : (PurchaseInEstVO[]) estVOMap
            .get(ICBillType.PurchaseIn.getCode()), sttlInfo));
  }
}
