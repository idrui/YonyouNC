package nc.test.pu.m25;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubitf.pu.m25.ap.f1.IInvoiceQuery4F1;
import nc.vo.arap.termitem.PaymentDateVO;
import nc.vo.arap.termitem.PaymentDateVO.AccountDate;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.tool.performance.DeepCloneTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.collections.MapUtils;

public class InvoiceAPQueryTest extends AbstractTestCase {

  public void testQueryByOrderBID() throws BusinessException {
    List<String> lst = new ArrayList<String>();
    lst.add("1002C31000000000M9PC");
    // lst =
    // NCLocator.getInstance().lookup(IInvoiceAPQuery.class).getBIDByOrderBID(
    // lst);
    System.out.println(lst);
  }

  public void testQueryPaytermDate() throws BusinessException {
    List<PaymentDateVO> list = this.getStoreList();
    list.addAll(this.getInvoiceList());
    Map<String, UFDate> map =
        NCLocator.getInstance().lookup(IInvoiceQuery4F1.class)
            .queryPaytermDate(list);
    MapUtils.debugPrint(new PrintStream(System.out), "·µ»Ø½á¹û£º", map);
  }

  private List<PaymentDateVO> getInvoiceList() {
    PaymentDateVO vo = new PaymentDateVO();
    vo.setItemid("1008Z810000000002FZB");
    vo.setBilltype(POBillType.Invoice.getCode());
    List<PaymentDateVO> list = new ArrayList<PaymentDateVO>();
    vo.setDateType(AccountDate.PURCHASE_CONTRACT_EFFECTIVE_DATE.getValue());
    list.add(vo);
    vo = (PaymentDateVO) new DeepCloneTool().deepClone(vo);
    vo.setDateType(AccountDate.PURCHASE_INVOICE_APPROVE_DATE.getValue());
    list.add(vo);
    vo = (PaymentDateVO) new DeepCloneTool().deepClone(vo);
    vo.setDateType(AccountDate.PURCHASE_INVOICE_DATE.getValue());
    list.add(vo);
    vo = (PaymentDateVO) new DeepCloneTool().deepClone(vo);
    vo.setDateType(AccountDate.PURCHASE_ORDER_APPROVE_DATE.getValue());
    list.add(vo);
    vo = (PaymentDateVO) new DeepCloneTool().deepClone(vo);
    vo.setDateType(AccountDate.RECEIPT_APPROVE_DATE.getValue());
    list.add(vo);
    vo = (PaymentDateVO) new DeepCloneTool().deepClone(vo);
    vo.setDateType(AccountDate.STORE_RECEIPT_DATE.getValue());
    list.add(vo);
    vo = (PaymentDateVO) new DeepCloneTool().deepClone(vo);
    vo.setDateType(AccountDate.STORE_RECEIPT_SIGNATURE_DATE.getValue());
    list.add(vo);
    return list;
  }

  private List<PaymentDateVO> getStoreList() {
    PaymentDateVO vo = new PaymentDateVO();
    vo.setItemid("1008Z810000000002FZ7");
    vo.setBilltype(ICBillType.PurchaseIn.getCode());
    List<PaymentDateVO> list = new ArrayList<PaymentDateVO>();
    vo.setDateType(AccountDate.PURCHASE_CONTRACT_EFFECTIVE_DATE.getValue());
    list.add(vo);
    vo = (PaymentDateVO) new DeepCloneTool().deepClone(vo);
    vo.setDateType(AccountDate.PURCHASE_INVOICE_APPROVE_DATE.getValue());
    list.add(vo);
    vo = (PaymentDateVO) new DeepCloneTool().deepClone(vo);
    vo.setDateType(AccountDate.PURCHASE_INVOICE_DATE.getValue());
    list.add(vo);
    vo = (PaymentDateVO) new DeepCloneTool().deepClone(vo);
    vo.setDateType(AccountDate.PURCHASE_ORDER_APPROVE_DATE.getValue());
    list.add(vo);
    vo = (PaymentDateVO) new DeepCloneTool().deepClone(vo);
    vo.setDateType(AccountDate.RECEIPT_APPROVE_DATE.getValue());
    list.add(vo);
    vo = (PaymentDateVO) new DeepCloneTool().deepClone(vo);
    vo.setDateType(AccountDate.STORE_RECEIPT_DATE.getValue());
    list.add(vo);
    vo = (PaymentDateVO) new DeepCloneTool().deepClone(vo);
    vo.setDateType(AccountDate.STORE_RECEIPT_SIGNATURE_DATE.getValue());
    list.add(vo);
    return list;
  }

}
