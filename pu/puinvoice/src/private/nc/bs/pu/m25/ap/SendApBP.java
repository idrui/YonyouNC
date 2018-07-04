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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ӧ��</li>
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-2 ����04:47:53
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
    // �ɴ�Ӧ���ķ�Ʊ״̬(��־)���
    processer.addBeforeFinalRule(new SendAPStateFilterRule(true));
    // ��鴫Ӧ���Ƿ��ո��ı��涯��
    processer.addBeforeFinalRule(new SendAPBusitypeCheckRule());
    // ������Ʊ�Գ���Ĵ���(���ڸ��±�־֮ǰ)
    processer.addBeforeFinalRule(new RedBlueSettleTOAPRule(sttlInfo, this.env));
    // ���·�Ʊ�Ĵ�Ӧ����Ϣ(��־��VO״̬��)
    processer.addBeforeFinalRule(new InvcAPInfoUpdateRule());
    // ��Ӧ��ʱ�ķ�Ʊ�������ͼ��
   // processer.addBeforeFinalRule(new SendAPTrantypeChkRule(sttlInfo));
    // ҵ���¼� ǰ
    processer.addBeforeFinalRule(new SendAPBeforeEventRuel());
    // ���ݹ�Ӧ�����лس�--һ��Ҫ����ǰ����(������п��ܸı䷢ƱVO�ṹ)
    processer.addBeforeFinalRule(new BackWashEstAPRule(estVOMap, sttlInfo));

    // ҵ���¼� ��
    processer.addAfterFinalRule(new SendAPAfterEventRuel());
    // �ݹ��͸��Ļس壭���ŵ�ǰ����Ҫ���·�Ʊ��һЩ��Ϣ
    // ����Դ�����ķ�Ʊ��������ϸ���в��(�˹���Ҫ�ŵ��������֮ǰ)
    processer.addAfterFinalRule(new NoSourceSplitRule(sttlInfo));
    // �ɹ���ⵥ��ȷ��Ӧ���ĵ�����ŵ�������п��ܸı䷢ƱVO�Ľṹ��������Ӧ���Ľű���
    processer.addAfterFinalRule(new MakeDiffConfirmAPRule(
        null == estVOMap ? null : (PurchaseInEstVO[]) estVOMap
            .get(ICBillType.PurchaseIn.getCode()), sttlInfo));
  }
}
