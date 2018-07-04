package nc.impl.pu.m27.merge;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m27.merge.MatchMerge;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.util.SettlePublicUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>����ƥ��,������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ҫ���ڷ��ý���ʱƥ������෢Ʊ����ⵥ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wangyf
 * @time 2010-7-30 ����04:51:30
 */
public class FeeMatchMerge extends MatchMerge {

  public FeeMatchMerge(StockSettleVO[] stocks, FeeDiscountSettleVO[] fees,
      FeeDiscountSettleVO[] discounts, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment envi) {
    // ���ý���ʱ�����﷢Ʊ�϶�Ϊ��
    super(null, stocks, fees, discounts, adjustInvcVos, envi);
  }

  public FeeMatchMerge(StockSettleVO[] stocks, FeeDiscountSettleVO[] fees,
      FeeDiscountSettleVO[] discounts, SettleEnvironment envi) {
    // ���ý���ʱ�����﷢Ʊ�϶�Ϊ��
    super(null, stocks, fees, discounts, envi);
  }

  @Override
  protected void checkAfter() throws BusinessException {
    //
  }

  @Override
  protected void checkBefore() throws BusinessException {
    //
  }

  @Override
  protected SettleBillItemVO[] formSettleBillItems() throws BusinessException {

    // �ϲ���ⵥ����������Ʊ
    ArrayList<SettleBillItemVO> listItemVO = new ArrayList<SettleBillItemVO>();
    for (StockSettleVO stockVO : this.getStockVOs()) {
      SettleBillItemVO itemvo =
          this.createItemByStock(stockVO, UFDouble.ZERO_DBL,
              EnumMatchRowType.StockFeeSettle);
      listItemVO.add(itemvo); // ���ɿ�浥������ITEM
    }
    this.setStockFeeRowMnyNumInfo(listItemVO);// �������ý������������۵�
    ArrayList<SettleBillItemVO> listTempItemVO = this.mergeFeeDiscount();
    if (listTempItemVO != null) {
      // ������ⵥ�����еķ�Ʊ����
      this.setinvoicebilldate(listItemVO, listTempItemVO);
      listItemVO.addAll(listTempItemVO);// ����+�ۿ����ɽ���ITEM
    }

    return listItemVO.toArray(new SettleBillItemVO[listItemVO.size()]);
  }

  @Override
  protected void packOrigData() throws BusinessException {
    //
  }

  // modify by liangchen1 private to protected
  protected void setStockFeeRowMnyNumInfo(List<SettleBillItemVO> listItemVO) {
    CostfactorViewVO[] cfviews = this.getSettleEnv().getCostFactorViews();
    for (SettleBillItemVO item : listItemVO) {
      item.setNgoodsmoney(null);
      item.setNgoodsprice(null);
      item.setNprice(null);
      item.setNsettlenum(null);
      UFDouble sumMny = SettlePublicUtil.getSumCostfactor(item, cfviews);
      sumMny = MathTool.add(sumMny, item.getNdiscount());
      item.setNmoney(sumMny);
    }
  }

  @Override
  protected SettleBillVO[] splitSettleBills(SettleBillItemVO[] voaOrgItem)
      throws BusinessException {
    // �ֵ�
    if (ArrayUtils.isEmpty(voaOrgItem)) {
      return null;
    }

    // ���ý��㲻���ڷֵ����˷����л������Ϣ�����š�����֯�������ˡ��������ڵ���Ϣ
    SettleBillVO[] bills = this.splitBill(voaOrgItem);
    for (SettleBillVO bill : bills) {
      // ���ý��㣬Ĭ�϶��ᴫIA
      bill.getParentVO().setBtoia(UFBoolean.TRUE);
    }
    return bills;
  }

  /**
   * ���� ֻ�з��÷�Ʊ����ⵥ������ ����ڼ�������ڲ���Ϊ��! �����⣬
   * wangss 2015��2��9�� (����һ) 12:59 �ʼ�
   * �ɹ���Ʊ�����÷�Ʊ������һ����ⵥ����ʱ��ȡ��Ʊ��Ʊ�������ֵ��Ϊ����ɹ���Ļ���ڼ�������ڡ�
   * added by wangzhqf Ϊ�˸�������I9ItemVO.DACCOUNTDATE[����ڼ��������]ʹ��),
   * ��Ҫ��֤ͨ����ⵥ���ɵĽ��㵥�е�Invoicebilldate��ֵ
   * 
   * @param listItemVO ��ⵥ������
   * @param listTempItemVO �����ۿ� ������
   */

  private void setinvoicebilldate(ArrayList<SettleBillItemVO> listItemVO,
      ArrayList<SettleBillItemVO> listTempItemVO) {
    // ����ʱ��
    UFDate lastestDate = null;
    for (SettleBillItemVO feeDisctItemVO : listTempItemVO) {
      if (lastestDate == null) {
        lastestDate = feeDisctItemVO.getInvoicebilldate();
      }
      else {
        if (feeDisctItemVO.getInvoicebilldate().after(lastestDate)) {
          lastestDate = feeDisctItemVO.getInvoicebilldate();
        }
      }
    }

    // ������ⵥ������.��Ʊ����
    for (SettleBillItemVO item : listItemVO) {
      item.setInvoicebilldate(lastestDate);
    }

  }
}
