package nc.pubimpl.pu.it.merge;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.merge.MatchMerge;
import nc.vo.pu.m27.pub.SettleEnvironment;

/**
 * 为进口提供的匹配服务工厂
 * 
 * @since 6.31
 * @version 2013-9-17 上午10:27:58
 * @author mengjian
 */
public class MergeFactoryForIT {

  public MatchMerge getMerge(final InvoiceSettleVO[] voaInvoice,
      FeeDiscountSettleVO[] adjustInvcVos, final StockSettleVO[] voaStock,
      final FeeDiscountSettleVO[] voaFee,
      final FeeDiscountSettleVO[] voaDiscount, final SettleEnvironment settleEnv) {
    if (EnumSettleType.IT_UI_AUTO == settleEnv.getSettleType()) {
      return new AutoMatchMergeForIT(voaInvoice, voaStock, voaFee, voaDiscount,
          adjustInvcVos, settleEnv);
    }
    else if (EnumSettleType.IT_FEE == settleEnv.getSettleType()) {
      return new FeeMatchMergeForIT(voaStock, voaFee, voaDiscount,
          adjustInvcVos, settleEnv);
    }
    else if (EnumSettleType.IT_DIFFERENT_MATERIAL == settleEnv.getSettleType()) {
      return new DifferentMaterialMatchMergeForIT(voaInvoice, voaStock, voaFee,
          voaDiscount, adjustInvcVos, settleEnv);
    }
    else if (EnumSettleType.IT_SAME_MATERIAL == settleEnv.getSettleType()) {
      return new SameMaterialMergeForIT(voaInvoice, voaStock, voaFee,
          voaDiscount, adjustInvcVos, settleEnv);
    }
    else if (EnumSettleType.IT_WITHOUT_INVOICE == settleEnv.getSettleType()) {
      return new WithoutInoiceMatchMergeForIT(voaInvoice, voaStock, voaFee,
          voaDiscount, adjustInvcVos, settleEnv);
    }
    return null;

  }
}
