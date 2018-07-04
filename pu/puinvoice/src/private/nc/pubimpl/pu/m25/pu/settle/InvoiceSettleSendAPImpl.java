/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-19 ����10:44:32
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ʊ������Ϻ��Զ���Ӧ����ʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-19 ����10:44:32
 */
public class InvoiceSettleSendAPImpl implements IInvoiceSettleSendAP {

  /**
   * ���෽����д
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
      // ��ѯ������ϸ
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
      // ֱ�ӵ�������ƽ̨,��Ӧ���ű�
      IPFBusiAction pfsrv = NCLocator.getInstance().lookup(IPFBusiAction.class);
      pfsrv.processBatch(InvoiceBillAction.SENDAP.name(),
          POBillType.Invoice.getCode(), vos, envs, null, null);
      processer.after(vos);
      return vos;
    }
    catch (Exception e) {
      // ��־�쳣
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private void addRule(AroundProcesser<InvoiceVO> processer,
      MapList<String, SettleBillInfo> stlInfo) {
    // ��������ϸ��������Գ�ķ�Ʊ,�˹���Ҫ���ڹ��˹���֮ǰ,��Ϊ�����Գ�ķ�Ʊ���Ѿ�����Ӧ����
    // ������Ҫʹ�������ķ�Ʊ����˲��ܹ��˵��������������˵�
    processer.addBeforeFinalRule(new RedBlueSettleTOAPRule(stlInfo, null));
    // ���ݽ������͹����Զ���Ӧ���ķ�Ʊ -- �˹��������ǰһ����֮��
    processer.addBeforeFinalRule(new SettleAutoSendAPFilterRule());
    // �ɴ�Ӧ���ķ�Ʊ״̬����
    processer.addBeforeFinalRule(new SendAPStateFilterRule(false));
  }

  /**
   * ����������������Ҫ�����������Ĺ��ܡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-19 ����04:37:07
   */
  private InvoiceUIToBSEnv[] getSendAPEnvs(InvoiceVO[] vos,
      MapList<String, SettleBillInfo> stlInfo) {
    InvoiceUIToBSEnv[] envs = new InvoiceUIToBSEnv[vos.length];
    InvoiceUIToBSEnv env = new InvoiceUIToBSEnv();
    /**
     * ��Ϊ�������������õĽű�����Ϊ���ֹ�����
     * ��������ٴ�Ӧ����BP�����в�ѯ��Ʊ�Ĵ���
     * ����ķ�ƱVO�Ѿ������µģ���Ӧ�����ٽ��в������Ҳ���������⣬��̬���ټ�һ��Ҳ��Ӱ��
     * ����ö��µĵ���������ע���ҵ�����̼��ǰ��󣬾Ͳ����ٲ������ͼ���
     */
    env.setBManual(UFBoolean.TRUE);
    env.setTrigger(InvoiceBillAction.SENDAP);
    env.setSttlInfoMap(stlInfo);
    env.setAutoSettleSendAP(true);// �����Զ���Ӧ��
    for (int i = 0; i < envs.length; i++) {
      envs[i] = env;
    }
    return envs;
  }
}
