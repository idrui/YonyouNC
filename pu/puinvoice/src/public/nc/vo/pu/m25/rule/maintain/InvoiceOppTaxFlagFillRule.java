package nc.vo.pu.m25.rule.maintain;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.itf.scmpub.reference.uap.bd.vat.OppTaxFlagQueryVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.pub.rule.vat.VATCodeTaxValue;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.StringUtils;

/**
 * �ɹ���Ʊ������˰Ĭ��ֵ����࣬��ǰ̨֧�ֺϲ�Զ�̵���<br>
 * ���ڣ���1��ǰ̨�༭�¼� ��2�� ת��������������Ĭ��ֵ��Ҳʹ�ô˹���<br>
 * 
 * @since 6.0
 * @version 2012-2-20 ����1:16:24
 * @author zhaoyha
 */
public class InvoiceOppTaxFlagFillRule implements IPURemoteCallCombinator {

  private IKeyValue[] bills;

  private VATCodeTaxValue vatQueryRule;

  /**
   * 
   * @param bills ����VO��UI�༭����
   */
  public InvoiceOppTaxFlagFillRule(IKeyValue[] bills) {
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
    this.setOpptaxFlag();
  }

  private OppTaxFlagQueryVO[] getOppTaxFlagQueryVos() {
    OppTaxFlagQueryVO[] queryVos =
        InvoiceVOUtil.getOppTaxFlagQueryVos(this.bills);
    OppTaxFlagQueryVO[] validqueryVos =
        new OppTaxFlagQueryVO[this.bills.length];
    for (int i = 0; i < this.bills.length; i++) {
      String pk_org = queryVos[i].getPk_org();
      String pk_country = queryVos[i].getPk_country();
      BuySellFlagEnum bsflag = queryVos[i].getFbuysellflag();
      if (StringUtils.isBlank(pk_org) || StringUtils.isBlank(pk_country)
          || null == bsflag) {
        continue;
      }
      validqueryVos[i] = queryVos[i];
    }
    return validqueryVos;
  }

  private void register_vatqueryrule() {
    OppTaxFlagQueryVO[] otfqvos = this.getOppTaxFlagQueryVos();
    this.vatQueryRule = new VATCodeTaxValue(null, otfqvos, null, null);
    this.vatQueryRule.prepare();
  }

  private void setOpptaxFlag() {
    UFBoolean[] opptaxflags = this.vatQueryRule.getOpptaxflags();
    for (int i = 0; i < this.bills.length; i++) {
      this.bills[i].setHeadValue(InvoiceHeaderVO.BOPPTAXFLAG, opptaxflags[i]);
    }
  }

}
