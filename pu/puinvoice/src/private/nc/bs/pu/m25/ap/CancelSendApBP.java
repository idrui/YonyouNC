/**
 * 
 */
package nc.bs.pu.m25.ap;

import java.util.Map;

import nc.bs.pu.m25.ap.rule.CancelSendAPAfterEventRuel;
import nc.bs.pu.m25.ap.rule.CancelSendAPBeforeEventRuel;
import nc.bs.pu.m25.ap.rule.InvoiceCancelAPFilterRule;
import nc.bs.pu.m25.ap.rule.UnBackWashEstAPRule;
import nc.bs.pu.m25.plugin.InvoicePluginPoint;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.arap.ArapServicesUtil;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.rule.ap.CancelSendAPStateChkRule;
import nc.vo.pu.m27.query.SettleBillInfo;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;

/**
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ȡ����Ӧ��</li>
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-2 ����04:52:33
 */
public class CancelSendApBP {
  private boolean ismanual;

  public InvoiceVO[] cancelSendAp(InvoiceVO[] invoiceVOs, InvoiceVO[] orgVos) {
    if (!SysInitGroupQuery.isAPEnabled()) {
      return invoiceVOs;
    }
    InvoiceVO[] vos = invoiceVOs;
    AroundProcesser<InvoiceVO> processer =
        new AroundProcesser<InvoiceVO>(InvoicePluginPoint.CANCEL_SEND_AP);
    MapList<String, SettleBillInfo> sttlInfoMap =
        EstInfoMaintainUtil.getSttlInfo(vos, null);
    Map<String, EstVO[]> estVOMap =
        EstInfoMaintainUtil.queryEstInfo(vos, sttlInfoMap);
    this.addRule(processer, sttlInfoMap, estVOMap);
    vos = processer.before(vos);
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    String[] ids = AggVOUtil.getPrimaryKeys(vos);
    ArapServicesUtil.delApBillByPUInvc(ids);
    for (InvoiceVO vo : vos) {
      vo.getParentVO().setStatus(VOStatus.UPDATED);
      vo.getParentVO().setBapflag(UFBoolean.FALSE);
    }
    vos = new BillUpdate<InvoiceVO>().update(vos, orgVos);
    processer.after(vos);
    return vos;
  }

  /**
   * @param ismanual
   *          �Ƿ��ֹ�ȡ��ȷ��Ӧ��
   */
  public void setIsmanual(boolean ismanual) {
    this.ismanual = ismanual;
  }

  private void addRule(AroundProcesser<InvoiceVO> processer,
      MapList<String, SettleBillInfo> sttlInfo, Map<String, EstVO[]> estVOMap) {
    // ҵ���¼� ǰ
    processer.addBeforeFinalRule(new CancelSendAPBeforeEventRuel());
    // ���˵�δ����Ӧ���ķ�Ʊ
    processer.addBeforeFinalRule(new InvoiceCancelAPFilterRule());
    // ��ȡ����Ӧ���ķ�Ʊ״̬���(ǰ��̨)
    processer.addBeforeFinalRule(new CancelSendAPStateChkRule(this.ismanual));
    // ȡ����Ӧ��ʱ�س���ݹ�Ӧ��
    processer.addBeforeFinalRule(new UnBackWashEstAPRule(estVOMap, sttlInfo));
    // ҵ���¼� ��
    processer.addAfterFinalRule(new CancelSendAPAfterEventRuel());
  }
}
