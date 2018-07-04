/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-22 上午10:56:43
 */
package nc.bs.pu.m25.maintain;

import nc.bs.pu.m25.maintain.rule.InvocieWriteBackOrderRule;
import nc.bs.pu.m25.maintain.rule.WriteBackSourceRule;

import nc.bs.pu.m25.maintain.rule.save.DirectTranTypeChkRule;

import nc.bs.pu.m25.maintain.rule.save.InvcSaveAfterEventRule;
import nc.bs.pu.m25.maintain.rule.save.InvcSaveBeforeEventRule;



import nc.bs.pu.m25.maintain.rule.save.OrderPriceCheckRule;
import nc.bs.pu.m25.maintain.rule.save.ScOrderPriceCheckRule;

import nc.bs.pu.m25.plugin.InvoicePluginPoint;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m25.rule.InvoiceAuditInfoFillRule;
import nc.vo.pu.m25.rule.InvoiceDateChkRule;
import nc.vo.pu.m25.rule.InvoiceNotNullChkRule;
import nc.vo.pu.m25.rule.InvoiceNumValueChkRule;
import nc.vo.pu.m25.rule.InvoiceSourceInfoChkRule;
import nc.vo.pu.m25.rule.ParaValidityChkRule;
import nc.vo.pu.m25.rule.maintain.InvoiceLimitCheckRule;
import nc.vo.pu.m25.rule.maintain.InvoiceRowNoCheckRule;
import nc.vo.pu.m25.rule.maintain.InvoiceScaleCheckRule;
import nc.vo.pu.m25.rule.maintain.InvoiceTotalValueCalcRule;
import nc.vo.pu.m25.rule.maintain.save.ConfirmInvoiceBiztypeRule;
import nc.vo.pu.m25.rule.maintain.save.FeeMaterialCheckRule;
import nc.vo.pu.m25.rule.maintain.save.InvoiceCodeProcRule;
import nc.vo.pu.m25.rule.maintain.save.InvoiceCodeUniqueCheckRule;
import nc.vo.pu.m25.rule.maintain.save.InvoiceRowTypeFillRule;
import nc.vo.pu.m25.rule.maintain.save.SynchronizeDupDataRule;
import nc.vo.pu.pub.rule.NewestOrgVersionFillRule;
import nc.vo.pu.pub.rule.TrantypeNotNullCheckRule;
import nc.vo.pu.pub.rule.pf.NoPassUpdateRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.scmpub.rule.FinanceOrgEnableCheckRule;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票保存的核心BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-22 上午10:56:43
 */
public class InvoiceSaveBP {

  private InvoiceUIToBSEnv env;

  /**
   * InvoiceSaveBP 的构造子
   * 
   * @param env
   */
  public InvoiceSaveBP(InvoiceUIToBSEnv env) {
    this.env = env;
  }

  public InvoiceVO[] save(InvoiceVO[] insertInvoiceVOs,
      InvoiceVO[] updateInvoiceVOs, InvoiceVO[] updateOrgVos) {
    InvoiceVO[] insertVos = insertInvoiceVOs;
    InvoiceVO[] updateVos = updateInvoiceVOs;
    CompareAroundProcesser<InvoiceVO> processer =
        new CompareAroundProcesser<InvoiceVO>(InvoicePluginPoint.SAVE_BP);
    this.addRule(processer);
    if (!ArrayUtils.isEmpty(insertVos)) {
      processer.before(insertVos, null);
      insertVos = new BillInsert<InvoiceVO>().insert(insertVos);
      processer.after(insertVos, null);
    }
    if (!ArrayUtils.isEmpty(updateVos)) {
      processer.before(updateVos, updateOrgVos);
      updateVos = new BillUpdate<InvoiceVO>().update(updateVos, updateOrgVos);
      processer.after(updateVos, updateOrgVos);
    }
    InvoiceVO[] savedVos = ArrayUtil.combinArrays(insertVos, updateVos);
    return savedVos;
  }

  private void addRule(CompareAroundProcesser<InvoiceVO> processer) {
    processer.addBeforeFinalRule(new ParaValidityChkRule());// 参数正确性检查
    processer.addBeforeRule(new InvoiceSourceInfoChkRule());// 检查来源信息是否完整
    processer.addBeforeFinalRule(new NewestOrgVersionFillRule<InvoiceVO>(
        InvoiceHeaderVO.PK_INVOICE));// 计算主组织最新版
    processer.addBeforeRule(new InvoiceAuditInfoFillRule());// 单据时间填充
    processer.addBeforeRule(new InvoiceCodeProcRule()); // 设置单据号
    processer.addBeforeFinalRule(new InvoiceRowTypeFillRule());// 填充发票行类型
    processer.addBeforeFinalRule(new InvoiceTotalValueCalcRule());// 表头合计计算规则
    processer.addBeforeFinalRule(new InvoiceNotNullChkRule());// 非空校验
    processer.addBeforeFinalRule(new TrantypeNotNullCheckRule<InvoiceVO>()); // 检查交易类型是否为空
    processer.addBeforeFinalRule(new InvoiceDateChkRule()); // 发票日期检查
    processer.addBeforeFinalRule(new InvoiceNumValueChkRule());// 数值字段检查
    processer.addBeforeFinalRule(new InvoiceScaleCheckRule());// 精度检查
    processer.addBeforeRule(new InvoiceRowNoCheckRule());// 行号合法性检查
    processer.addBeforeRule(new FinanceOrgEnableCheckRule<InvoiceVO>());// 主组织停用检查
    processer.addBeforeFinalRule(new DirectTranTypeChkRule());// 直运业务库存组织检查
    processer.addBeforeFinalRule(new InvoiceLimitCheckRule());// 极限值检查
    processer.addBeforeFinalRule(new ConfirmInvoiceBiztypeRule()); // 确定业务流程(自制)
    processer.addBeforeFinalRule(new SynchronizeDupDataRule());// 同步表体冗余信息
    // 自由辅助属性校验
    // MarAssistantSaveRule<InvoiceVO> marRule =
    // new MarAssistantSaveRule<InvoiceVO>();
    // marRule.setNotNullValidate(InvoiceItemVO.PK_MATERIAL);
    // processer.addBeforeRule(marRule);
    // 自定义项检查
    processer.addBeforeRule(new UserDefSaveRule<InvoiceVO>(new Class[] {
      InvoiceHeaderVO.class, InvoiceItemVO.class
    }));
    processer.addBeforeFinalRule(new NoPassUpdateRule<InvoiceVO>()); // 审批不通过时修改后的状态及审批人审批日期等处理
    processer.addBeforeRule(new InvcSaveBeforeEventRule());// 生产工序委外加工费结算单回写
    processer.addAfterRule(new InvoiceCodeUniqueCheckRule());// 单据号唯一性校验
    processer.addAfterRule(new FeeMaterialCheckRule());// 费用发票检查是否费用物料
    processer.addAfterFinalRule(new InvocieWriteBackOrderRule(this.env));// 回写来源
    processer.addAfterFinalRule(new WriteBackSourceRule(this.env));// 回写来源
    processer.addAfterFinalRule(new OrderPriceCheckRule(this.env));// 检查是否超采购订单单价容差
    processer.addAfterFinalRule(new ScOrderPriceCheckRule(this.env));// 检查是否超委外订单单价容差
    processer.addAfterFinalRule(new InvcSaveAfterEventRule());// 生产工序委外加工费结算单回写
  }

}
