package nc.impl.pu.m27.merge;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>VMIƥ��</b>
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
 * @time 2010-3-25 ����10:24:14
 */
public class VoiConsumeMatchMerge extends SameMaterialMerge {

  public VoiConsumeMatchMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, settleEnv);

    // �Զ���������������ɳ�����Ʊ����
    this.getSettleEnv().setAllowStockBeyondInvoice(true);
    this.getSettleEnv().setStockHaveToMatchAll(false);

  }

  @Override
  protected void checkAfter() throws BusinessException {
    //
  }

  @Override
  protected void checkBefore() throws BusinessException {
    //
  }

}
