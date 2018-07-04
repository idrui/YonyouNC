package nc.vo.pu.m25.rule.maintain;

import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.itf.scmpub.reference.uap.bd.vat.CustSuppVATCodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.OppTaxFlagQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.OrgVATCodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.pub.rule.vat.BuysellflagSetter;
import nc.vo.pu.pub.rule.vat.TriatradeflagSetter;
import nc.vo.pu.pub.rule.vat.VATCodeTaxValue;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.StringUtils;

/**
 * 采购发票VAT相关信息默认值填充类，在前台支持合并远程调用<br>
 * 用于：（1）自制单据（2） 转单后处理类中设置默认值，也使用此规则<br>
 * 此规则处理：国家的设置、组织供应商VAT码设置、逆向征税设置、税码税率等VAT税的设置
 * 
 * @since 6.0
 * @version 2012-2-14 下午9:02:07
 * @author zhaoyha
 */
public class InvoiceVatDefaultValueFillRule implements IPURemoteCallCombinator {

  /**
   * 国家设置接口，根据不同场景，使用不同的规则来设置
   * 
   * @since 6.0
   * @version 2012-2-14 下午9:53:53
   * @author zhaoyha
   */
  public static interface ICountrySetter {
    void setCountry(IKeyValue[] bills);
  }

  private IKeyValue[] bills;

  private List<ICountrySetter> countrysetterList;

  private VATCodeTaxValue vatQueryRule;

  /**
   * @param bills 单据VO或UI编辑数据
   * @param countrysetterList 国家地区设置器
   */
  public InvoiceVatDefaultValueFillRule(IKeyValue[] bills,
      List<ICountrySetter> countrysetterList) {
    super();
    this.bills = bills;
    this.countrysetterList = countrysetterList;
  }

  @Override
  public void prepare() {
    // 以下不走远程调用合并，直接在此处处理，准备好远程调用数据
    this.setCountry();// 设置发货国，报税国，收货国
    this.setBuysellflag();// 设置购销类型
    this.setTriatrade();// 设置是否三角贸易
    this.register_vatqueryrule();// 注册vat信息查询rule
  }

  @Override
  public void process() {
    // 先执行一下远程调用合并规则
    this.vatQueryRule.process();
    // 设置VAT相关信息默认值
    this.setOpptaxFlag();
    this.setSupplierVatcode();
    this.setOrgVatcode();
    this.setVatTaxInfo();
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

  private VATInfoQueryVO[] getVatInfoQueryVos() {
    VATInfoQueryVO[] vatInfoQueryVOs =
        InvoiceVOUtil.getVatInfoQueryVO(this.bills);
    VATInfoQueryVO[] validVos = new VATInfoQueryVO[vatInfoQueryVOs.length];
    for (int i = 0; i < validVos.length; i++) {
      if (null == vatInfoQueryVOs[i].getDbizdate()
          || null == vatInfoQueryVOs[i].getFbuysellfalg()
          || null == vatInfoQueryVOs[i].getPk_custsupp()
          || null == vatInfoQueryVOs[i].getCmaterialvid()) {
        continue;
      }
      validVos[i] = vatInfoQueryVOs[i];
    }
    return validVos;
  }

  private void register_vatqueryrule() {
    VATInfoQueryVO[] vatqueryvos = this.getVatInfoQueryVos();
    OppTaxFlagQueryVO[] otfqvos = this.getOppTaxFlagQueryVos();
    CustSuppVATCodeQueryVO[] csvcqvos = this.getSupplierVatCodeQueryVos();
    OrgVATCodeQueryVO[] ovcqvos = this.getOrgVatCodeQueryVos();
    this.vatQueryRule =
        new VATCodeTaxValue(vatqueryvos, otfqvos, csvcqvos, ovcqvos);
    this.vatQueryRule.prepare();
  }

  private void setBuysellflag() {
    // 转单没有影响
    new BuysellflagSetter().setHeadBuysellFlag(this.bills);
  }

  private void setCountry() {
    if (null == this.countrysetterList) {
      return;
    }
    for (ICountrySetter setter : this.countrysetterList) {
      setter.setCountry(this.bills);
    }
  }

  private void setOpptaxFlag() {
    UFBoolean[] opptaxflags = this.vatQueryRule.getOpptaxflags();
    for (int i = 0; i < this.bills.length; i++) {
      this.bills[i].setHeadValue(InvoiceHeaderVO.BOPPTAXFLAG, opptaxflags[i]);
    }
  }

  private void setOrgVatcode() {
    String[] vatCodes = this.vatQueryRule.getVatCodes();
    for (int i = 0; i < this.bills.length; i++) {
      if (StringUtils.isNotBlank((String) this.bills[i]
          .getHeadValue(InvoiceHeaderVO.VVATCODE))) {
        continue;
      }
      this.bills[i].setHeadValue(InvoiceHeaderVO.VVATCODE, vatCodes[i]);
    }
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

  private void setTriatrade() {
    new TriatradeflagSetter().setHeadTriatradeflag(this.bills);
  }

  private void setVatTaxInfo() {
    VATInfoVO[] vatinfos = this.vatQueryRule.getVatinfos();
    int procIndx = 0;
    for (IKeyValue bill : this.bills) {
      int rowcnt = bill.getItemCount();
      for (int i = 0; i < rowcnt; i++) {
        VATInfoVO vo = vatinfos[procIndx++];
        if (null == vo) {
          continue;
        }
        this.setBillItemVatTaxInfo(vo, bill, i);
      }
    }
  }

  protected void setBillItemVatTaxInfo(VATInfoVO vo, IKeyValue bill, int row) {
    bill.setBodyValue(row, InvoiceItemVO.CTAXCODEID, vo.getCtaxcodeid());
    bill.setBodyValue(row, InvoiceItemVO.FTAXTYPEFLAG, vo.getFtaxtypeflag());
    bill.setBodyValue(row, InvoiceItemVO.NNOSUBTAXRATE, vo.getNnosubtaxrate());
    bill.setBodyValue(row, InvoiceItemVO.NTAXRATE, vo.getNtaxrate());
  }
}
