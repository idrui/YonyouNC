package nc.vo.pu.m27.rule;

import java.util.LinkedHashSet;
import java.util.Set;

import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.GeneralInSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.merge.MatchMergeData;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.util.SettlePublicUtil;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>�Զ��������</b>
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
 * @time 2010-1-6 ����03:00:44
 */
public class DifferentMaterialMatchRule extends GoodsMatchRule {

  @Override
  public void check(MatchMergeData mmData, SettleEnvironment settleEnvironment)
      throws ValidationException {
    // ִ�м��
    this.doCheck(mmData.getGoodsInvcVos(), mmData.getStockVos());
    this.checkInvoiceByOrder(mmData.getGoodsInvcVos(), mmData.getStockVos());
    super.check(mmData, settleEnvironment);
  }

  /**
   * ���ն������߷����ƵĲɹ���ⵥ���ɵĲɹ���Ʊ�������������ɹ���������ⵥ���н���
   * 
   * @param voaInvoice
   * @param voaStock
   */
  private void checkInvoiceByOrder(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock) {

    if (SettlePublicUtil.fromSameOrder(voaInvoice, voaStock)) {
      return;
    }
    ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004060_0", "04004060-0108")/*
                                                                 * @res
                                                                 * "���ն������߷����ƵĲɹ���ⵥ���ɵĲɹ���Ʊ����������������Դ�ɹ���������ⵥ���н��㣡"
                                                                 */);

  }

  private StringBuilder checkStockToAP(StockSettleVO[] ssVos) {
    StringBuilder sb = new StringBuilder();
    Set<String> billcode = new LinkedHashSet<String>();
    Set<String> generalinBillcode = new LinkedHashSet<String>();
    for (StockSettleVO ssVo : ssVos) {
      if (EnumToAPFlag.NotToAP.toInteger().equals(ssVo.getFdirtoaptype())) {
        continue;
      }
      if (ssVo instanceof GeneralInSettleVO) {
        generalinBillcode.add(ssVo.getVbillcode());
      }
      else {
        billcode.add(ssVo.getVbillcode());
      }
    }
    if (generalinBillcode.size() > 0) {
      sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004060_0", "04004060-0149")/* @res "������ⵥ���ܽ��������Ͻ��㣺" */);
      for (String bc : generalinBillcode) {
        sb.append("[");
        sb.append(bc);
        sb.append("]");
      }
      sb.append("\n");
    }
    if (billcode.size() > 0) {
      sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004060_0", "04004060-0150")/* @res "������ⵥ�Ѿ��ݹ���ȷ�Ϲ�Ӧ������������������Ͻ��㣺" */);
      for (String bc : billcode) {
        sb.append("[");
        sb.append(bc);
        sb.append("]");
      }
    }

    if (sb.length() == 0) {
      return null;
    }
    return sb;
  }

  private void doCheck(InvoiceSettleVO[] voaInvoice, StockSettleVO[] voaStock)
      throws ValidationException {
    if (ArrayUtils.isEmpty(voaInvoice)) {
      throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004060_0", "04004060-0151")/* @res "��ѡ����﷢Ʊ��" */);
    }
    if (ArrayUtils.isEmpty(voaStock)) {
      throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004060_0", "04004060-0152")/* @res "��ѡ����ⵥ��" */);
    }

    // 1�� ѡ�е�����뷢Ʊ���У�������������뷢Ʊ������������һ�µ�����,������ѡ��
    boolean bStockPlus = false;
    boolean bStockMinus = false;
    {
      for (StockSettleVO stockVO : voaStock) {
        if (MathTool.compareTo(stockVO.getNinnum(), UFDouble.ZERO_DBL) < 0) {
          bStockMinus = true;
        }
        else {
          bStockPlus = true;
        }
      }
      // ��浥�ݲ�������ͬʱ����
      if (bStockMinus && bStockPlus) {
        throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0153")/*
                                                                     * @res
                                                                     * "��ⵥ��������ͬʱ���ڣ�"
                                                                     */);
      }
    }
    boolean bInvoicePlus = false;
    boolean bInvoiceMinus = false;
    {
      for (InvoiceSettleVO invoiceVO : voaInvoice) {
        if (MathTool.compareTo(invoiceVO.getNnum(), UFDouble.ZERO_DBL) < 0) {
          bInvoiceMinus = true;
        }
        else {
          bInvoicePlus = true;
        }
      }
      if (bInvoiceMinus && bInvoicePlus) {
        throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0154")/*
                                                                     * @res
                                                                     * "��Ʊ��������ͬʱ���ڣ�"
                                                                     */);
      }
    }
    if (bInvoiceMinus && bStockPlus || bInvoicePlus && bStockMinus) {
      throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004060_0", "04004060-0155")/*
                                                    * @res
                                                    * "ѡ�е�����뷢Ʊ���У�������������뷢Ʊ������������һ�µ�����,������ѡ��"
                                                    */);
    }
    StringBuilder sb = this.checkStockToAP(voaStock);
    if (null != sb) {
      // �����ⵥ�Ƿ��ݹ���ȷ�Ϲ�Ӧ��
      throw new ValidationException(sb.toString());
    }
  }

}
