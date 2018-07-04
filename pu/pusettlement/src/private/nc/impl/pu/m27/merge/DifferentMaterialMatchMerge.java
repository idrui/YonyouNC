package nc.impl.pu.m27.merge;

import java.util.ArrayList;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.util.SettlePublicUtil;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������ƥ��</b>
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
 * @time 2010-3-25 ����10:21:23
 */
public class DifferentMaterialMatchMerge extends GoodsMatchMerge {

  public DifferentMaterialMatchMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, adjustInvcVos, settleEnv);

    // ��������Ƿ�ɳ�����Ʊ����
    this.getSettleEnv().setAllowStockBeyondInvoice(true);
    this.getSettleEnv().setStockHaveToMatchAll(true);

  }

  public DifferentMaterialMatchMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, settleEnv);

    // ��������Ƿ�ɳ�����Ʊ����
    this.getSettleEnv().setAllowStockBeyondInvoice(true);
    this.getSettleEnv().setStockHaveToMatchAll(true);

  }

  @Override
  protected void checkAfter() throws BusinessException {
    //
  }

  @Override
  protected void checkBefore() throws BusinessException {

    // ��ⵥ�еı��η�Ʊ������֮�Ͳ����ڷ�Ʊ�еı��η�Ʊ������֮��,�������㲻�ܽ��У�
    DataClassify curData = this.getDataClassify()[0];
    UFDouble dAccumInvoiceMoney = UFDouble.ZERO_DBL;
    {
      for (InvoiceSettleVO voInvoice : curData.getOrigInvoices()) {
        // --------check begin
        dAccumInvoiceMoney =
            MathTool.add(dAccumInvoiceMoney,
                voInvoice.getNcurrentinvoicesettlemny());
      }
    }
    UFDouble dAccumStockInvoiceMoney = UFDouble.ZERO_DBL;
    {
      for (StockSettleVO voStock : curData.getOrigStockes()) {
        // --------check begin
        dAccumStockInvoiceMoney =
            MathTool.add(dAccumStockInvoiceMoney,
                voStock.getNcurrentinvoicesettlemny());
      }
    }
    if (MathTool.compareTo(dAccumInvoiceMoney, dAccumStockInvoiceMoney) != 0) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004060_0", "04004060-0109")/*
                                                    * @res
                                                    * "��ⵥ�еı��η�Ʊ������֮�Ͳ����ڷ�Ʊ�еı��η�Ʊ������֮��,�������㲻�ܽ��У�"
                                                    */);
    }
    this.checkInvoiceByOrder(
        curData.getOrigInvoices().toArray(
            new InvoiceSettleVO[curData.getOrigInvoices().size()]),
        curData.getOrigStockes().toArray(
            new StockSettleVO[curData.getOrigStockes().size()]));
  }

  /**
   * ���ն������߷����ƵĲɹ���ⵥ���ɵĲɹ���Ʊ�������������ɹ���������ⵥ���н���
   * 
   * @param voaInvoice
   * @param voaStock
   */
  protected void checkInvoiceByOrder(InvoiceSettleVO[] voaInvoice,
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

  /**
   * ���෽����д
   * 
   * @see nc.vo.pu.m27.merge.MatchMerge#formSettleBillItems()
   */
  @Override
  protected SettleBillItemVO[] formSettleBillItems() throws BusinessException {

    // -----------3---------------
    // ������кϲ���Ľ����壺��桢��Ʊ
    ArrayList<SettleBillItemVO> listItemVO = new ArrayList<SettleBillItemVO>();
    for (StockSettleVO stockVO : this.getDataClassify()[0].getOrigStockes()) {
      SettleBillItemVO newItem =
          this.createItemByStock(stockVO, stockVO.getNcurrentsettlenum(),
              EnumMatchRowType.StockInDiffMatch);
      // ���¼��������͵���
      newItem.setNgoodsmoney(stockVO.getNcurrentinvoicesettlemny());// ǰ̨�ǵķ�̯����ⵥ�ķ�Ʊ���
      UFDouble gdPrice =
          ScaleUtils.getScaleUtilAtBS().adjustSoPuPriceScale(
              stockVO.getNcurrentinvoicesettlemny().div(
                  stockVO.getNcurrentsettlenum(), UFDouble.DEFAULT_POWER),
              stockVO.getCorigcurrencyid());
      newItem.setNgoodsprice(gdPrice);
      newItem.setNprice(gdPrice);
      newItem.setNmoney(newItem.getNgoodsmoney());
      // ���з��ý��㣬���ܵĽ�����͵��ۺ���������д���
      listItemVO.add(newItem);
    }
    for (InvoiceSettleVO invoiceVO : this.getDataClassify()[0]
        .getOrigInvoices()) {
      listItemVO
          .add(this.createItemByInvoice(invoiceVO,
              invoiceVO.getNcurrentsettlenum(),
              EnumMatchRowType.InvoiceInDiffMatch));
    }

    // �����ۿ�
    ArrayList<SettleBillItemVO> listTempItemVO = this.mergeFeeDiscount();
    if (listTempItemVO != null) {
      listItemVO.addAll(listTempItemVO);
    }

    return ListUtil.isEmpty(listItemVO) ? null : listItemVO
        .toArray(new SettleBillItemVO[listItemVO.size()]);

  }

  @Override
  protected void packOrigData() throws BusinessException {

    DataClassify data =
        new DataClassify(this.getInvoiceVOs(), this.getStockVOs());
    data.classifyPlusMinus();

    this.setDataClassify(new DataClassify[] {
      data
    });

  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pu.m27.merge.MatchMerge#splitSettleBills()
   */
  @Override
  protected SettleBillVO[] splitSettleBills(SettleBillItemVO[] voaOrgItem)
      throws BusinessException {
    // �ֵ�
    if (ArrayUtils.isEmpty(voaOrgItem)) {
      return null;
    }
    // �����Ͻ����޷ֵ�
    return this.splitBill(voaOrgItem);
  }
}
