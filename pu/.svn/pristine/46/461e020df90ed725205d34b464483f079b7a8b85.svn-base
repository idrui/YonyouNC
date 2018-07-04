package nc.vo.pu.m27.merge;

import nc.vo.pu.costfactor.enumeration.ApportionMode;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author wangyf
 * @time 2010-3-29 下午04:33:12
 */
public class DifferentMaterialAllot extends FeeAllot {
  private InvoiceSettleVO[] m_voaInvoice = null;

  /**
   * FeeAllot 的构造子
   * 
   * @param voaAllotObject
   * @param voaInvoice
   * @param settleEnv
   */
  public DifferentMaterialAllot(StockSettleVO[] voaAllotObject,
      InvoiceSettleVO[] voaInvoice, SettleEnvironment settleEnv) {

    super(voaAllotObject, null, null, settleEnv);

    this.m_voaInvoice = voaInvoice;
  }

  /**
   * 异物料结算的分摊
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param allotType
   *          取ApportionMode的按数量、按金额
   * @throws BusinessException
   *           <p>
   * @author wangyf
   * @time 2010-3-29 下午03:10:59
   */
  public void allot(ApportionMode allotMode) throws BusinessException {

    this.discountAndDifferentMaterialAllot(FeeAllot.AllotUse_DifferentMaterial,
        allotMode, this.getInvoiceVOs());

    return;
  }

  private InvoiceSettleVO[] getInvoiceVOs() {
    return this.m_voaInvoice;
  }

}
