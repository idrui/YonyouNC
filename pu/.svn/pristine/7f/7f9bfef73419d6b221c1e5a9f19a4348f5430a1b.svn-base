package nc.vo.pu.m25.rule.maintain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.StringUtils;

/**
 * 默认（自制或发货国为空时）设置发货国<br>
 * 取供应商的所属国家
 * 
 * @since 6.0
 * @version 2012-2-14 下午10:07:11
 * @author zhaoyha
 */
public class DefaultSendcountrySetter implements ICountrySetter {

  @Override
  public void setCountry(IKeyValue[] bills) {
    // 自制时，可编辑，默认供应商档案国家
    List<IKeyValue> filterBillLst = new ArrayList<IKeyValue>();
    List<String> supplierLst = new ArrayList<String>();
    for (IKeyValue bill : bills) {
      String sendcountry =
          (String) bill.getHeadValue(InvoiceHeaderVO.CSENDCOUNTRYID);
      String pk_supplier =
          (String) bill.getHeadValue(InvoiceHeaderVO.PK_SUPPLIER);
      // 有发货国或没有供应商，均不用处理
      if (StringUtils.isNotBlank(sendcountry)
          || StringUtils.isBlank(pk_supplier)) {
        continue;
      }
      filterBillLst.add(bill);
      supplierLst.add(pk_supplier);
    }
    if (filterBillLst.size() == 0) {
      return;
    }
    Map<String, String> supCountryMap =
        SupplierPubService.queryCountryBySupplier(supplierLst
            .toArray(new String[supplierLst.size()]));
    for (IKeyValue bill : filterBillLst) {
      bill.setHeadValue(InvoiceHeaderVO.CSENDCOUNTRYID,
          supCountryMap.get(bill.getHeadValue(InvoiceHeaderVO.PK_SUPPLIER)));
    }
  }

}
