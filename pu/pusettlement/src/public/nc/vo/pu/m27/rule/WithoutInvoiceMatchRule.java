package nc.vo.pu.m27.rule;

import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.merge.MatchMergeData;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>无发票结算规则</b>
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
 * @time 2010-1-6 下午03:01:32
 */
public class WithoutInvoiceMatchRule extends GoodsMatchRule {

  @Override
  public void check(MatchMergeData mmData, SettleEnvironment settleEnvironment)
      throws ValidationException {

    if (ArrayUtils.isEmpty(mmData.getStockVos())) {
      throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004060_0", "04004060-0152")/* @res "请选择入库单！" */);
    }

    if (this.hasNonVirtualInvoice(mmData.getGoodsInvcVos())
        || !ArrayUtils.isEmpty(mmData.getFeeInvcVos())
        || !ArrayUtils.isEmpty(mmData.getDiscountInvcVos())) {
      throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004060_0", "04004060-0168")/* @res "无发票结算不允许选择采购发票!" */);
    }

    // 2013-5-24 痛苦中
    // 按照王印芬、唐江峰、杨永伟的意见改为60形式，可以结算，虚拟发票改为原币和入库单一致。
    // this.checkConfirm(mmData.getStockVos());

    super.check(mmData, settleEnvironment);
  }

  /**
   * <p>
   * 使用场景：
   * <ul>
   * <li>NCdp204550401
   * <li>采购入库单进行确认成本和应付后，再无发票结算时， 1、虽然进行结算，但未生成结算单；
   * 2、生成的虚拟发票流程可逆不了，即由于没有结算单，虚拟发票不能删除； 3、在结算的时候，此虚拟发票还能查询出来（虚拟发票应该是在结算的时候要过滤掉）；
   * <li>王印芬、秦刚、杨永伟、唐江峰、吴小亮确认
   * <li>确认应付和成本的入库单不能进行无发票结算，在点按钮时进行提示。
   * </ul>
   * 
   * @param vos
   * @throws ValidationException
   */
  // private void checkConfirm(StockSettleVO[] vos) throws ValidationException {
  // for (StockSettleVO vo : vos) {
  // if (EnumToAPFlag.ConfirmToAP.value().equals(vo.getFdirtoaptype())) {
  // throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl
  // .getNCLangRes().getStrByID("4004060_0", "04004060-0249")/*
  // * @res
  // * "确认应付和成本的入库单不能进行无发票结算"
  // */);
  // }
  // }
  // }

  private boolean hasNonVirtualInvoice(InvoiceSettleVO[] vos) {
    for (InvoiceSettleVO vo : vos) {
      if (!UFBoolean.TRUE.equals(vo.getBvirtual())) {
        return true;
      }
    }
    return false;
  }

}
