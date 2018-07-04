package nc.bs.pu.m27.settlebill.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.pu.m25.IOrderWriteBackFor25;
import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleQuery;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * 
 * @description
 * ���Ʒ�Ʊ����Դ�ڶ�������ⵥ����ʱ��д�������ۼƿ�Ʊ����
 * @scene
 * ɾ�����㵥,������ϱ�����㵥
 * @param
 * point ��дʱ���ö���ࣨ����ʱ��ɾ��ʱ�� 
 *
 * @since 6.3
 * @version 2014-10-22 ����4:08:39
 * @author zhangshqb
 */
public class WritebackOrderRule implements IRule<SettleBillVO> {
  private WritebackPoint point = WritebackPoint.SAVE;

  private SettleEnvironment settleEnv;

  public WritebackOrderRule(WritebackPoint point) {
    this.point = point;
  }

  public WritebackOrderRule(WritebackPoint point, SettleEnvironment settleEnv) {
    this.settleEnv = settleEnv;
    this.point = point;
  }

  public WritebackPoint getPoint() {
    return this.point;
  }

  public SettleEnvironment getSettleEnv() {
    return this.settleEnv;
  }

  @Override
  public void process(SettleBillVO[] vos) {
    // ��÷�Ʊ������Դ�ڶ����Ĳɹ����㵥��
    SettleBillItemVO[] items = this.getNotFromOrderItemVOs(vos);
    if (items.length == 0) {
      return;
    }
    // ���ݽ��㵥�й����д������������
    OrderWriteBackParaFor25[] params = this.getWriteBackParams(items);

    // ��д����
    IOrderWriteBackFor25 service = this.getWriteBackService();
    try {
      service.invoicePriceChk(params);
      service.writeBackFor25(params);
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ����������������÷�Ʊ������Դ�ڶ����Ľ��㵥��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos ���㵥����
   * @return ��Ʊ������Դ�ڶ����Ľ��㵥��
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-20 ����03:17:39
   */
  private SettleBillItemVO[] getNotFromOrderItemVOs(SettleBillVO[] vos) {
    List<SettleBillItemVO> items = new ArrayList<SettleBillItemVO>();
    for (SettleBillVO vo : vos) {
      SettleBillItemVO[] itemVos = vo.getChildrenVO();
      for (SettleBillItemVO itemVo : itemVos) {
        Integer rowType = itemVo.getFrowtype();
        if (EnumMatchRowType.StockInvoiceMatch.value().equals(rowType)
            && ICBillType.PurchaseIn.getCode().equals(
                itemVo.getVstockbilltype()) // TODO ������ ֻ�Բɹ���ⵥ���д����Ƿ񻹰����ڳ�
            && itemVo.getPk_invoiceorder_b() == null
            && itemVo.getPk_stockorder_b() != null) {
          items.add(itemVo);
        }
      }
    }
    return items.toArray(new SettleBillItemVO[items.size()]);
  }

  private OrderWriteBackParaFor25[] getWriteBackParams(SettleBillItemVO[] items) {
    // ��÷�Ʊ��˰����
    Map<String, UFDouble> taxPrices = this.queryTaxPrice(items);
    // ��÷�Ʊ��˰����
    Map<String, UFDouble> prices = this.queryPrice(items);
    OrderWriteBackParaFor25[] temps = new OrderWriteBackParaFor25[items.length];
    for (int i = 0; i < items.length; i++) {
      temps[i] = new OrderWriteBackParaFor25();
      temps[i].setBid(items[i].getPk_stockorder_b());
      temps[i].setHid(items[i].getPk_stockorder());
      temps[i].setInvoicebid(items[i].getPk_invoice_b());
      temps[i].setDiscard(this.point == WritebackPoint.DELETE);
      // Ҫ��д������
      UFDouble diffNum = MathTool.nvl(items[i].getNsettlenum());
      if (this.point == WritebackPoint.DELETE) {
        diffNum = MathTool.oppose(diffNum);
      }
      temps[i].setDiffNum(diffNum);
      // ��˰����
      UFDouble taxPrice =
          MathTool.nvl(taxPrices.get(items[i].getPk_invoice_b()));
      temps[i].setNtaxprice(taxPrice);
      // ��˰����
      UFDouble price = MathTool.nvl(prices.get(items[i].getPk_invoice_b()));
      temps[i].setPrice(price);
      // Ҫ��д�Ŀ�Ʊ���=��Ʊ��˰����*��������
      // ����������ȷ�ϣ��˴�������β��
      temps[i].setDiffMny(taxPrice.multiply(diffNum));
      if (this.getSettleEnv() != null) {
        temps[i].setPriceUserConfirm(this.getSettleEnv()
            .isInvoicePriceOverOder());
      }
    }
    return temps;
  }

  private IOrderWriteBackFor25 getWriteBackService() {
    return NCLocator.getInstance().lookup(IOrderWriteBackFor25.class);
  }

  private Map<String, UFDouble> queryPrice(SettleBillItemVO[] items) {
    Set<String> bids = new LinkedHashSet<String>();
    for (SettleBillItemVO item : items) {
      bids.add(item.getPk_invoice_b());
    }
    IInvoiceSettleQuery service =
        NCLocator.getInstance().lookup(IInvoiceSettleQuery.class);
    Map<String, UFDouble> map = new HashMap<String, UFDouble>();
    try {
      map = service.queryPrice(bids.toArray(new String[bids.size()]));
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return map;
  }

  private Map<String, UFDouble> queryTaxPrice(SettleBillItemVO[] items) {
    Set<String> bids = new LinkedHashSet<String>();
    for (SettleBillItemVO item : items) {
      bids.add(item.getPk_invoice_b());
    }
    IInvoiceSettleQuery service =
        NCLocator.getInstance().lookup(IInvoiceSettleQuery.class);
    Map<String, UFDouble> map = new HashMap<String, UFDouble>();
    try {
      map = service.queryTaxPrice(bids.toArray(new String[bids.size()]));
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return map;
  }
}
