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
 * 采购发票逆向征税默认值填充类，在前台支持合并远程调用<br>
 * 用于：（1）前台编辑事件 （2） 转单后处理类中设置默认值，也使用此规则<br>
 * 
 * @since 6.0
 * @version 2012-2-20 下午1:16:24
 * @author zhaoyha
 */
public class InvoiceOppTaxFlagFillRule implements IPURemoteCallCombinator {

  private IKeyValue[] bills;

  private VATCodeTaxValue vatQueryRule;

  /**
   * 
   * @param bills 单据VO或UI编辑数据
   */
  public InvoiceOppTaxFlagFillRule(IKeyValue[] bills) {
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
