package nc.bs.pu.m27.settlebill.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.m27.ISettleMatch;
import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleQuery;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * ȡ�����㣨ɾ�����㵥��ǰ����Ҫ�жϷ�Ʊ�Ƿ��Ѿ���Ӧ���ļ�飺<br>
 * �����Ʊ�Ѿ���Ӧ����������ⵥҲ�Ѿ�����Ӧ����������ȡ������<br>
 * ���ɹ���Ʊ�����Ӧ��ʱ�س�����ݹ�Ӧ������÷�Ʊ��Ӧ�Ľ��㵥������ɾ��<br>
 * ��ⵥ�Ѿ����й������ݹ�Ӧ�������÷�Ʊ�Ѿ���Ӧ����Ҳ������ȡ������<br>
 * @scene
 * ɾ�����㵥
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-22 ����4:10:17
 * @author zhangshqb
 */
public class InvoiceToAPCheckRule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
    // ���������
    this.checkGoodSettle(vos);
    // �����ý���
    this.checkFeeSettle(vos);
  }

  private void checkFee(FeeDiscountSettleVO[] feeVos,
      StockSettleVO[] stockSettleVOs) {

    if (ArrayUtils.isEmpty(feeVos)) {
      return;
    }
    MapList<String, String> stockFeeEstApMap = null;
    try {
      stockFeeEstApMap =
          NCLocator.getInstance().lookup(ISettleMatch.class)
              .getEstAPFeeMaterial(stockSettleVOs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (null == stockFeeEstApMap || stockFeeEstApMap.size() == 0) {
      return;
    }
    List<String> estStockLst = new ArrayList<String>();
    for (Entry<String, List<String>> entry : stockFeeEstApMap.entrySet()) {
      Set<String> feeMarSet = new HashSet<String>(entry.getValue());
      for (FeeDiscountSettleVO fee : feeVos) {
        if (UFBoolean.TRUE.equals(fee.getBapflag())
            && feeMarSet.contains(fee.getPk_material())) {
          estStockLst.add(entry.getKey());
          break;
        }
      }
    }
    if (estStockLst.size() > 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0075")/*@res "��ⵥ�Ѿ����й������ݹ�Ӧ�������÷�Ʊ�Ѿ���Ӧ��,����ȡ�����㣡"*/);
    }

  }

  private void checkFeeSettle(SettleBillVO[] vos) {
    // ��ȡ��ⵥ��bID�ͷ�Ʊ��bID
    List<String> invoicebids = new ArrayList<String>();
    List<String> stockBids = new ArrayList<String>();
    for (SettleBillVO vo : vos) {
      SettleBillItemVO[] items = vo.getChildrenVO();
      for (SettleBillItemVO item : items) {
        if (EnumMatchRowType.Fee.toInteger().equals(item.getFrowtype())
            || EnumMatchRowType.Discount.toInteger().equals(item.getFrowtype())) {
          if (item.getPk_invoice_b() != null) {
            invoicebids.add(item.getPk_invoice_b());
          }
        }
        // �����bid
        if (item.getPk_stock_b() != null) {
          stockBids.add(item.getPk_stock_b());
        }
      }
    }
    if (stockBids.size() == 0 || invoicebids.size() == 0) {
      return;
    }
    StockInfoUtil stockUtil = new StockInfoUtil(vos);
    List<StockSettleVO> stockSettleVOList = new ArrayList<StockSettleVO>();
    for (String bid : stockBids) {
      StockSettleVO vo = stockUtil.getStockSettleVO(bid);
      stockSettleVOList.add(vo);
    }
    if (stockSettleVOList.size() == 0) {
      return;
    }
    // ��ѯ���÷�Ʊ����vo
    ViewQuery<FeeDiscountSettleVO> query =
        new ViewQuery<FeeDiscountSettleVO>(FeeDiscountSettleVO.class);
    FeeDiscountSettleVO[] fees =
        query.query(invoicebids.toArray(new String[invoicebids.size()]));
    this.checkFee(fees,
        stockSettleVOList.toArray(new StockSettleVO[stockSettleVOList.size()]));
  }

  /**
   * @param vos
   */
  private void checkGoodSettle(SettleBillVO[] vos) {
    // ��ȡ��ⵥ��ID�ͷ�Ʊ��ID
    List<String> invoiceHids = new ArrayList<String>();
    List<String> stockBids = new ArrayList<String>();
    for (SettleBillVO vo : vos) {
      SettleBillItemVO[] items = vo.getChildrenVO();
      for (SettleBillItemVO item : items) {
        if (item.getPk_invoice() != null) {
          invoiceHids.add(item.getPk_invoice());
        }
        // �����id
        if (item.getPk_stock_b() != null) {
          stockBids.add(item.getPk_stock_b());
        }
      }
    }

    boolean isInvoiceToAP = false;
    // �������ⵥ�У�����Ҫ�жϷ�Ʊ�Ƿ��Ѿ���Ӧ��
    if (stockBids.size() > 0) {
      isInvoiceToAP = this.isInvoiceToAP(invoiceHids);
    }
    // ���û����ⵥ�У���ֱ�ӷ���
    else {
      return;
    }

    // �����Ʊû�д�Ӧ��������û�з�Ʊ�У���ֱ�ӷ���
    if (!isInvoiceToAP) {
      return;
    }
    // ��ⵥ�Ƿ��ݹ�Ӧ��
    boolean isStockToAP = this.isStockToAP(vos, stockBids);
    if (isStockToAP) {
      String errMessage = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0076")/*@res "��ⵥ�ͷ�Ʊ���Ѿ�����Ӧ��������ȡ�����㣡"*/;
      ExceptionUtils.wrappBusinessException(errMessage);
    }
  }

  private boolean isInvoiceToAP(List<String> invoiceHids) {
    boolean flag = false;
    int hidLength = invoiceHids.size();
    if (hidLength == 0) {
      return flag;
    }

    IInvoiceSettleQuery query =
        NCLocator.getInstance().lookup(IInvoiceSettleQuery.class);
    try {
      flag = query.isExistsSentAP(invoiceHids.toArray(new String[hidLength]));
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return flag;
  }

  private boolean isStockToAP(SettleBillVO[] vos, List<String> stockBids) {
    boolean flag = false;
    int hidLength = stockBids.size();
    if (hidLength == 0) {
      return flag;
    }
    StockInfoUtil stockUtil = new StockInfoUtil(vos);
    for (String bid : stockBids) {
      StockSettleVO vo = stockUtil.getStockSettleVO(bid);
      if (EnumToAPFlag.EstimateToAP.value().equals(vo.getFdirtoaptype())
          || EnumToAPFlag.ConfirmToAP.value().equals(vo.getFdirtoaptype())) {
        flag = true;
        break;
      }
    }

    return flag;
  }
}