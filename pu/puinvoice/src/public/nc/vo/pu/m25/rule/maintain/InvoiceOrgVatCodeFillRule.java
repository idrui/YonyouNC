package nc.vo.pu.m25.rule.maintain;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.bd.vat.OrgVATCodeQueryVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.pub.rule.vat.VATCodeTaxValue;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.StringUtils;

/**
 * 采购发票财务组织VAT码默认值填充类，在前台支持合并远程调用<br>
 * 用于：（1）前台编辑事件 （2） 转单后处理类中设置默认值，也使用此规则<br>
 * 
 * @since 6.0
 * @version 2012-2-20 下午12:01:50
 * @author zhaoyha
 */
public class InvoiceOrgVatCodeFillRule implements IPURemoteCallCombinator {

  private IKeyValue[] bills;

  private VATCodeTaxValue vatQueryRule;

  /**
   * @param bills 单据VO或UI编辑数据
   */
  public InvoiceOrgVatCodeFillRule(IKeyValue[] bills) {
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
      // 用于vo交换后期处理类，需求要求已有vat注册码不再处理，请不要去掉此判断。
      // 如需每次都重新计算vat注册码，请在调用处清空vat注册码。
      if (StringUtils.isNotBlank((String) this.bills[i]
          .getHeadValue(InvoiceHeaderVO.VVATCODE))) {
        continue;
      }
      this.bills[i].setHeadValue(InvoiceHeaderVO.VVATCODE, vatCodes[i]);
    }
  }

}
