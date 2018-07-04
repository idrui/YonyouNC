package nc.impl.pu.m27.merge;

import java.util.ArrayList;
import java.util.HashSet;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

/**
 * <p>
 * <b>��Ʊ�Զ�ƥ�䣬�����п�浥��</b>
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
 * @time 2010-3-25 ����09:14:30
 */
public class InvoiceAutoMatchWithStockMerge extends AutoMatchMerge {

  /**
   * InvoiceAutoMatchWithStockMerge �Ĺ�����
   * 
   * @param voaInvoice
   * @param voaStock
   * @param voaFee
   * @param voaDiscount
   * @param settleEnv
   */
  public InvoiceAutoMatchWithStockMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, settleEnv);

    // ���ݷ�Ʊ�ҵ���Դ���ݣ����û����Դ��Ҳ���ý���
    HashSet<String> hsetSrcBillType =
        CirVOUtil.getDistinctFieldSet(this.getInvoiceVOs(),
            InvoiceSettleVO.CSOURCETYPECODE);
    if ((hsetSrcBillType == null) || (hsetSrcBillType.size() == 0)) {
      return;
    }

    String sSrcBillType = hsetSrcBillType.iterator().next();
    if (PubAppTool.isEqual(sSrcBillType, POBillType.Order.getCode())
        || PubAppTool.isEqual(sSrcBillType, SCBillType.Order.getCode())) {
      // ֻ�ܺ���Դ��������ⵥ����
      this.setAddedMergeCondition(this.AddedMergeCondition_ByOrder);
    }
    else {
      // ֻ�ܺ���Դ��浥�ݽ���
      this.setAddedMergeCondition(this.AddedMergeCondition_ByStock);
    }

  }

  /**
   * ����������������Ҫ�����������Ĺ��ܡ�
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2010-3-10 ����03:09:32
   */
  @Override
  protected ArrayList<SettleBillItemVO> mergeProcess(){

    // -----------3---------------
    // ������кϲ���Ľ�����
    ArrayList<SettleBillItemVO> listItemVO = new ArrayList<SettleBillItemVO>();
    ArrayList<SettleBillItemVO> listTempItemVO = null;
    for (int i = 0; i < this.getDataClassify().length; i++) {

      // ������
      listTempItemVO =
          this.mergeInvoiceStock(this.getDataClassify()[i],
              GoodsMatchMerge.CombineType_MinusInvoiceStock);
      if (listTempItemVO != null) {
        listItemVO.addAll(listTempItemVO);
      }

      // ������
      listTempItemVO =
          this.mergeInvoiceStock(this.getDataClassify()[i],
              GoodsMatchMerge.CombineType_PlusInvoiceStock);
      if (listTempItemVO != null) {
        listItemVO.addAll(listTempItemVO);
      }

      // �ѽ�����ɵĺͽ���δ��ɵļ�¼����
      this.getDataClassify()[i].sumupResidualBill();
      if (this.getDataClassify()[i].getFinishedInvoices() != null) {
        this.getTotalFinishedInvoices().addAll(
            this.getDataClassify()[i].getFinishedInvoices());
      }
      if (this.getDataClassify()[i].getUnfinishedInvoices() != null) {
        this.getTotalUnfinishedInvoices().addAll(
            this.getDataClassify()[i].getUnfinishedInvoices());
      }
    }

    return listItemVO;

  }

}
