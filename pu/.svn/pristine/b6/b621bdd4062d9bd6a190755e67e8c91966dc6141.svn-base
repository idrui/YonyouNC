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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>newVoLst
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-5 下午04:54:16
 */
public class InvoicePushSaveFor45Impl implements IInvoicePushSaveFor45 {

  /**
   * 父类方法重写
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
        // 需要前台确认环境变量，但推保存不需求前台确认，一定不会超容差。
        InvoiceUIToBSEnv env = new InvoiceUIToBSEnv();
        InvoiceVO[] savedVos =
            new InvoiceSaveBP(env).save(procedVos, null, null);
        processer.after(savedVos, null);
        return savedVos;
      }
    }
    catch (Exception e) {
      // 日志异常
      ExceptionUtils.marsh(e);

    }
    return null;
  }

  private void addRule(CompareAroundProcesser<InvoiceVO> processer) {
    processer.addBeforeFinalRule(new DefaultRowNoSetRule<InvoiceVO>());
    processer.addBeforeFinalRule(new ParaValidityChkRule());// 参数正确性检查
    processer.addBeforeFinalRule(new InvalidInvoiceDataFilterRule());// 过滤掉不符合条件的发票，同时清空上游TS，保证不出现并发（推式保存可以不检查并发）
  }

}
