package nc.impl.pu.m27.merge;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.merge.MatchMerge;
import nc.vo.pu.m27.pub.SettleEnvironment;

/**
 * <p>
 * <b>ƥ���๤��</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author wangyf
 * @time 2010-3-25 ����10:22:54
 */
public class MergeFactory {

  public MatchMerge getMerge(final InvoiceSettleVO[] voaInvoice,
      final StockSettleVO[] voaStock, final FeeDiscountSettleVO[] voaFee,
      final FeeDiscountSettleVO[] voaDiscount, final SettleEnvironment settleEnv) {
    if (EnumSettleType.SAME_MATERIAL == settleEnv.getSettleType()) {
      return new SameMaterialMerge(voaInvoice, voaStock, voaFee, voaDiscount,
          settleEnv);
    }
    else if (EnumSettleType.DIFFERENT_MATERIAL == settleEnv.getSettleType()) {
      return new DifferentMaterialMatchMerge(voaInvoice, voaStock, voaFee,
          voaDiscount, settleEnv);
    }
    else if (EnumSettleType.UI_AUTO == settleEnv.getSettleType()) {
      return new AutoMatchMerge(voaInvoice, voaStock, voaFee, voaDiscount,
          settleEnv);
    }
    else if (EnumSettleType.WITHOUT_INVOICE == settleEnv.getSettleType()) {
      return new WithoutInoiceMatchMerge(voaInvoice, voaStock, voaFee,
          voaDiscount, settleEnv);
    }
    else if (EnumSettleType.FEE == settleEnv.getSettleType()) {
      return new FeeMatchMerge(voaStock, voaFee, voaDiscount, settleEnv);
    }
    else if (EnumSettleType.VOI_CONSUME == settleEnv.getSettleType()) {
      return new VoiConsumeMatchMerge(voaInvoice, voaStock, voaFee,
          voaDiscount, settleEnv);
    }
    else if (EnumSettleType.VOI_CONSUME_FEE == settleEnv.getSettleType()) {
      return new VoiConsumeFeeMatchMerge(voaStock, voaFee, voaDiscount,
          settleEnv);
    }

    return null;

  }
}
