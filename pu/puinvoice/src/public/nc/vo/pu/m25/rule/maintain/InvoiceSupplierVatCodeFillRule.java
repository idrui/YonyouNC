package nc.vo.pu.m25.rule.maintain;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.bd.vat.CustSuppVATCodeQueryVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.pub.rule.vat.VATCodeTaxValue;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.StringUtils;

/**
 * �ɹ���Ʊ��Ӧ��VAT��Ĭ��ֵ����࣬��ǰ̨֧�ֺϲ�Զ�̵���<br>
 * ���ڣ���1��ǰ̨�༭�¼� ��2�� ת��������������Ĭ��ֵ��Ҳʹ�ô˹���<br>
 * 
 * @since 6.0
 * @version 2012-2-20 ����12:01:50
 * @author zhaoyha
 */
public class InvoiceSupplierVatCodeFillRule implements IPURemoteCallCombinator {

  private IKeyValue[] bills;

  private VATCodeTaxValue vatQueryRule;

  /**
   * @param bills ����VO��UI�༭����
   */
  public InvoiceSupplierVatCodeFillRule(IKeyValue[] bills) {
    super();
    this.bills = bills;
  }

  @Override
  public void prepare() {
    this.register_vatqueryrule();// ע��vat��Ϣ��ѯrule
  }

  @Override
  public void process() {
    // ��ִ��һ��Զ�̵��úϲ�����
    this.vatQueryRule.process();
    // ����VAT�����ϢĬ��ֵ
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
