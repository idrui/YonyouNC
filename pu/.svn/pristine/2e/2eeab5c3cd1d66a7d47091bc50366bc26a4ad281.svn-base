/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-19 上午10:44:32
 */
package nc.pubimpl.pu.m25.pu.settle;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m25.ap.rule.RedBlueSettleTOAPRule;
import nc.bs.pu.m25.plugin.InvoicePluginPoint;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.uap.pf.IPFBusiAction;
import nc.pubimpl.pu.m25.pu.settle.rule.SettleAutoSendAPFilterRule;
import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleSendAP;
import nc.pubitf.pu.m27.ISettleBillQueryFor25;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceBillAction;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m25.rule.ap.SendAPStateFilterRule;
import nc.vo.pu.m27.query.SettleBillInfo;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票结算完毕后自动传应付的实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-19 上午10:44:32
 */
public class InvoiceSettleSendAPImpl implements IInvoiceSettleSendAP {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m25.pu.settle.IInvoiceSettleSendAP#autoSendAP(java.lang.String[])
   */
  @Override
  public InvoiceVO[] autoSendAP(String[] ids) throws BusinessException {
    try {
      if (ArrayUtils.isEmpty(ids)) {
        return null;
      }
      InvoiceVO[] vos = new BillQuery<InvoiceVO>(InvoiceVO.class).query(ids);
      // 查询结算明细
      ISettleBillQueryFor25 srv =
          NCLocator.getInstance().lookup(ISettleBillQueryFor25.class);
      MapList<String, SettleBillInfo> stlInfo = srv.querySettleBills(ids);
      AroundProcesser<InvoiceVO> processer =
          new AroundProcesser<InvoiceVO>(
              InvoicePluginPoint.SEND_AP_AFTER_SETTLE);
      this.addRule(processer, stlInfo);
      vos = processer.before(vos);
      if (ArrayUtils.isEmpty(vos)) {
        return vos;
      }
      InvoiceUIToBSEnv[] envs = this.getSendAPEnvs(vos, stlInfo);
      // 直接调用流程平台,传应付脚本
      IPFBusiAction pfsrv = NCLocator.getInstance().lookup(IPFBusiAction.class);
      pfsrv.processBatch(InvoiceBillAction.SENDAP.name(),
          POBillType.Invoice.getCode(), vos, envs, null, null);
      processer.after(vos);
      return vos;
    }
    catch (Exception e) {
      // 日志异常
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private void addRule(AroundProcesser<InvoiceVO> processer,
      MapList<String, SettleBillInfo> stlInfo) {
    // 按结算明细处理红蓝对冲的发票,此规则要放在过滤规则之前,因为红蓝对冲的发票有已经传过应付的
    // 规则中要使用这样的发票，因此不能过滤掉，处理完后可以滤掉
    processer.addBeforeFinalRule(new RedBlueSettleTOAPRule(stlInfo, null));
    // 根据交易类型过滤自动传应付的发票 -- 此规则请放在前一规则之后
    processer.addBeforeFinalRule(new SettleAutoSendAPFilterRule());
    // 可传应付的发票状态过滤
    processer.addBeforeFinalRule(new SendAPStateFilterRule(false));
  }

  /**
   * 方法功能描述：简要描述本方法的功能。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-19 下午04:37:07
   */
  private InvoiceUIToBSEnv[] getSendAPEnvs(InvoiceVO[] vos,
      MapList<String, SettleBillInfo> stlInfo) {
    InvoiceUIToBSEnv[] envs = new InvoiceUIToBSEnv[vos.length];
    InvoiceUIToBSEnv env = new InvoiceUIToBSEnv();
    /**
     * 因为这里是主动调用的脚本，认为是手工操作
     * 这样会减少传应付的BP规则中查询发票的次数
     * 这里的发票VO已经是最新的，传应付中再进行并发检查也不会有问题，动态锁再加一次也无影响
     * 今后用杜勇的单据类型中注册的业务流程检查前类后，就不会再并发检查和加锁
     */
    env.setBManual(UFBoolean.TRUE);
    env.setTrigger(InvoiceBillAction.SENDAP);
    env.setSttlInfoMap(stlInfo);
    env.setAutoSettleSendAP(true);// 结算自动传应付
    for (int i = 0; i < envs.length; i++) {
      envs[i] = env;
    }
    return envs;
  }
}
