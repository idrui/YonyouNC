package nc.impl.pu.m27.merge;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>�޷�Ʊƥ��</b>
 * <ul>
 * <li>֧�ֵ��ۺͽ�����Ϊ��Ľ��㣬�����ݹ�Ӧ����ֻ���ݹ�������Ӧ��
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
 * @time 2010-3-25 ����10:24:27
 */
public class WithoutInoiceMatchMerge extends SameMaterialMerge {

  /**
   * Ϊ�����ṩ
   * 
   * @param voaInvoice
   * @param voaStock
   * @param voaFee
   * @param voaDiscount
   * @param adjustInvcVos
   * @param settleEnv
   */
  public WithoutInoiceMatchMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, adjustInvcVos, settleEnv);

    // �Զ���������������ɳ�����Ʊ����
    this.getSettleEnv().setAllowStockBeyondInvoice(true);
    this.getSettleEnv().setStockHaveToMatchAll(false);
    // ������Դ������ⵥƥ��
    this.setAddedMergeCondition(this.AddedMergeCondition_ByStock);
  }

  public WithoutInoiceMatchMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, settleEnv);

    // �Զ���������������ɳ�����Ʊ����
    this.getSettleEnv().setAllowStockBeyondInvoice(true);
    this.getSettleEnv().setStockHaveToMatchAll(false);
    // ������Դ������ⵥƥ��
    this.setAddedMergeCondition(this.AddedMergeCondition_ByStock);
  }

  @Override
  protected void checkAfter() throws BusinessException {
    // �޷�Ʊ���㲻�ü��
  }

  @Override
  protected boolean isCanSplit(SettleBillVO vo) {
    // �޷�Ʊ���㲻�ֵ�
    return false;
  }

  /**
   * ���෽����д
   * 
   * @see nc.impl.pu.m27.merge.SameMaterialMerge#splitSettleBills(nc.vo.pu.m27.entity.SettleBillItemVO[])
   */
  @Override
  protected SettleBillVO[] splitSettleBills(SettleBillItemVO[] voaOrgItem)
      throws BusinessException {
    SettleBillVO[] vosBill = super.splitSettleBills(voaOrgItem);

    if (ArrayUtils.isEmpty(vosBill)) {
      return null;
    }

    // �������ⷢƱ�����־
    for (int i = 0; i < vosBill.length; i++) {
      vosBill[i].getParentVO().setBvirtualsettle(UFBoolean.TRUE);
    }
    // �޷�Ʊ���㲻�ֵ�
    return vosBill;
  }

}
