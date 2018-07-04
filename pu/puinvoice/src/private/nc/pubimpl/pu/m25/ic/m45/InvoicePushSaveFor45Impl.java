package nc.pubimpl.pu.m25.ic.m45;

import nc.bs.pu.m25.maintain.InvoiceSaveBP;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.pubimpl.pu.m25.ic.m45.rule.InvalidInvoiceDataFilterRule;
import nc.pubitf.pu.m25.ic.m45.IInvoicePushSaveFor45;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m25.rule.ParaValidityChkRule;
import nc.vo.pu.pub.rule.DefaultRowNoSetRule;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>newVoLst
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-5 ����04:54:16
 */
public class InvoicePushSaveFor45Impl implements IInvoicePushSaveFor45 {

  /**
   * ���෽����д
   * 
   * @see nc.pubitf.pu.m25.ic.m45.IInvoicePushSaveFor45#pushSave(nc.vo.pu.m25.entity.InvoiceVO[])
   */
  @Override
  public InvoiceVO[] pushSave(InvoiceVO[] vos) throws BusinessException {
    try {
      CompareAroundProcesser<InvoiceVO> processer =
          new CompareAroundProcesser<InvoiceVO>(null);
      this.addRule(processer);
      InvoiceVO[] procedVos = processer.before(vos, null);
      if (!ArrayUtils.isEmpty(procedVos)) {
        // ��Ҫǰ̨ȷ�ϻ������������Ʊ��治����ǰ̨ȷ�ϣ�һ�����ᳬ�ݲ
        InvoiceUIToBSEnv env = new InvoiceUIToBSEnv();
        InvoiceVO[] savedVos =
            new InvoiceSaveBP(env).save(procedVos, null, null);
        processer.after(savedVos, null);
        return savedVos;
      }
    }
    catch (Exception e) {
      // ��־�쳣
      ExceptionUtils.marsh(e);

    }
    return null;
  }

  private void addRule(CompareAroundProcesser<InvoiceVO> processer) {
    processer.addBeforeFinalRule(new DefaultRowNoSetRule<InvoiceVO>());
    processer.addBeforeFinalRule(new ParaValidityChkRule());// ������ȷ�Լ��
    processer.addBeforeFinalRule(new InvalidInvoiceDataFilterRule());// ���˵������������ķ�Ʊ��ͬʱ�������TS����֤�����ֲ�������ʽ������Բ���鲢����
  }

}
