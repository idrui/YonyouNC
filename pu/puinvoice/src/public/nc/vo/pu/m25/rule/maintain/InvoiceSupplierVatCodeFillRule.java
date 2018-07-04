package nc.vo.pu.m25.rule.maintain;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.bd.vat.CustSuppVATCodeQueryVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.pub.rule.vat.VATCodeTaxValue;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.StringUtils;

/**
 * 采购发票供应商VAT码默认值填充类，在前台支持合并远程调用<br>
 * 用于：（1）前台编辑事件 （2） 转单后处理类中设置默认值，也使用此规则<br>
 * 
 * @since 6.0
 * @version 2012-2-20 下午12:01:50
 * @author zhaoyha
 */
public class InvoiceSupplierVatCodeFillRule implements IPURemoteCallCombinator {

  private IKeyValue[] bills;

  private VATCodeTaxValue vatQueryRule;

  /**
   * @param bills 单据VO或UI编辑数据
   */
  public InvoiceSupplierVatCodeFillRule(IKeyValue[] bills) {
    super();
    this.bills = bills;
  }

  @Override
  public void prepare() {
    this.register_vatqueryrule();// 注册vat信息查询rule
  }

  @Override
  public void process() {
    // 先执行一下远程调用合并规则
    this.vatQueryRule.process();
    // 设置VAT相关信息默认值
    this.setSupplierVatcode();
  }

  private CustSuppVATCodeQueryVO[] getSupplierVatCodeQueryVos() {
    CustSuppVATCodeQueryVO[] queryvos =
        InvoiceVOUtil.getSupplierVatCodeQueryVos(this.bills);
    CustSuppVATCodeQueryVO[] validqueryvos =
        new CustSuppVATCodeQueryVO[this.bills.length];
    for (int i = 0; i < queryvos.length; i++) {
      String pk_supplier = queryvos[i].getPk_custsupp();
      String pk_country = queryvos[i].getPk_country();
      if (StringUtils.isBlank(pk_country) || StringUtils.isBlank(pk_supplier)) {
        continue;
      }
      validqueryvos[i] = queryvos[i];
    }
    return validqueryvos;
  }

  private void register_vatqueryrule() {
    CustSuppVATCodeQueryVO[] csvcqvos = this.getSupplierVatCodeQueryVos();
    this.vatQueryRule = new VATCodeTaxValue(null, null, csvcqvos, null);
    this.vatQueryRule.prepare();
  }

  private void setSupplierVatcode() {
    String[] supVatCodes = this.vatQueryRule.getSupVatCodes();
    for (int i = 0; i < this.bills.length; i++) {
      if (StringUtils.isNotBlank((String) this.bills[i]
          .getHeadValue(InvoiceHeaderVO.VVENDORVATCODE))) {
        continue;
      }
      this.bills[i]
          .setHeadValue(InvoiceHeaderVO.VVENDORVATCODE, supVatCodes[i]);
    }
  }

}
