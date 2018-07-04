/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-26 上午10:57:28
 */
package nc.impl.pu.m25.action;

import nc.bs.pu.m25.maintain.InvoiceSaveBP;
import nc.bs.pu.m25.plugin.InvoicePluginPoint;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m25.rule.ParaValidityChkRule;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票新增动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-26 上午10:57:28
 */
public class InvoiceInsertAction {

  public InvoiceVO[] insert(InvoiceVO[] invoiceVOs, InvoiceUIToBSEnv env) {
    InvoiceVO[] vos = invoiceVOs;
    // BillTransferTool<InvoiceVO> tool = new BillTransferTool<InvoiceVO>(vos);
    // v61 根据协议接口调用者传入的一定是全VO
    // vos = tool.getClientFullInfoBill(); // 前台过来的是修改过的行,这里补充为完成VO
    CompareAroundProcesser<InvoiceVO> processer =
        new CompareAroundProcesser<InvoiceVO>(InvoicePluginPoint.UI_INSERT);
    this.addRule(processer);
    processer.before(vos, null);
    InvoiceVO[] savedVos = new InvoiceSaveBP(env).save(vos, null, null);
    processer.after(savedVos, null);
    return savedVos;
  }

  private void addRule(CompareAroundProcesser<InvoiceVO> processer) {
    processer.addBeforeFinalRule(new ParaValidityChkRule());// 参数正确性检查
    // processer.addAfterRule(new WriteSysLogRule());// 写系统日志
  }

}
