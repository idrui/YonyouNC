package nc.ui.pu.m27.match.view.listener;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pub.bill.IBillRelaSortListener;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.util.CombineToInvoiceSettleVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票的排序对象获取监听
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-30 上午11:19:26
 */
public class InvoiceSortRelaObjectListener implements IBillRelaSortListener {
  private MatchManageModel model;

  public InvoiceSortRelaObjectListener(MatchManageModel model) {
    this.model = model;
  }

  @Override
  public List<DataRowNo> getRelaSortObject() {
    List<DataRowNo> listToSort = new ArrayList<DataRowNo>();
    // 把各种发票类型合并成InvoiceSettleVO
    CombineToInvoiceSettleVO combine = new CombineToInvoiceSettleVO();
    combine.setGoodsInvoices(this.model.getQueryInvoiceVOs());
    combine.setFeeInvoices(this.model.getQueryFeeInvoices());
    combine.setDiscountInvoices(this.model.getQueryDiscountInvoices());
    InvoiceSettleVO[] invoices = combine.combineInvoice();

    for (int i = 0; i < invoices.length; i++) {
      listToSort.add(new DataRowNo(invoices[i], i));
    }

    return listToSort;
  }

}
