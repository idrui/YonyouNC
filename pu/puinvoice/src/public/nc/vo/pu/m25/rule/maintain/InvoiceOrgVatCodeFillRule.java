package nc.vo.pu.m25.rule.maintain;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.bd.vat.OrgVATCodeQueryVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.pub.rule.vat.VATCodeTaxValue;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.StringUtils;

/**
 * �ɹ���Ʊ������֯VAT��Ĭ��ֵ����࣬��ǰ̨֧�ֺϲ�Զ�̵���<br>
 * ���ڣ���1��ǰ̨�༭�¼� ��2�� ת��������������Ĭ��ֵ��Ҳʹ�ô˹���<br>
 * 
 * @since 6.0
 * @version 2012-2-20 ����12:01:50
 * @author zhaoyha
 */
public class InvoiceOrgVatCodeFillRule implements IPURemoteCallCombinator {

  private IKeyValue[] bills;

  private VATCodeTaxValue vatQueryRule;

  /**
   * @param bills ����VO��UI�༭����
   */
  public InvoiceOrgVatCodeFillRule(IKeyValue[] bills) {
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
    this.setOrgVatcode();
  }

  private OrgVATCodeQueryVO[] getOrgVatCodeQueryVos() {
    OrgVATCodeQueryVO[] queryvos =
        InvoiceVOUtil.getOrgVatCodeQueryVos(this.bills);
    OrgVATCodeQueryVO[] validqueryvos =
        new OrgVATCodeQueryVO[this.bills.length];
    for (int i = 0; i < queryvos.length; i++) {
      String pk_org = queryvos[i].getPk_org();
      String pk_country = queryvos[i].getPk_country();
      if (StringUtils.isBlank(pk_country) || StringUtils.isBlank(pk_org)) {
        continue;
      }
      validqueryvos[i] = queryvos[i];
    }
    return validqueryvos;
  }

  private void register_vatqueryrule() {
    OrgVATCodeQueryVO[] ovcqvos = this.getOrgVatCodeQueryVos();
    this.vatQueryRule = new VATCodeTaxValue(null, null, null, ovcqvos);
    this.vatQueryRule.prepare();
  }

  private void setOrgVatcode() {
    String[] vatCodes = this.vatQueryRule.getVatCodes();
    for (int i = 0; i < this.bills.length; i++) {
      // ����vo�������ڴ����࣬����Ҫ������vatע���벻�ٴ����벻Ҫȥ�����жϡ�
      // ����ÿ�ζ����¼���vatע���룬���ڵ��ô����vatע���롣
      if (StringUtils.isNotBlank((String) this.bills[i]
          .getHeadValue(InvoiceHeaderVO.VVATCODE))) {
        continue;
      }
      this.bills[i].setHeadValue(InvoiceHeaderVO.VVATCODE, vatCodes[i]);
    }
  }

}
