package nc.bs.pu.m25.upgrade.v61.vat;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.m25.rule.maintain.DefaultRececountrySetter;
import nc.vo.pu.m25.rule.maintain.DefaultSendcountrySetter;
import nc.vo.pu.m25.rule.maintain.DefaultTaxcountrySetter;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

/**
 * 发票vat国家、税码升级处理类
 * 
 * @since 6.0
 * @version 2012-3-6 上午10:47:28
 * @author tianft
 */
public class InvoiceVatUpgradeRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    List<ICountrySetter> csetterLst =
        new ArrayList<InvoiceVatDefaultValueFillRule.ICountrySetter>();
    csetterLst.add(new DefaultSendcountrySetter());
    csetterLst.add(new DefaultRececountrySetter());
    csetterLst.add(new DefaultTaxcountrySetter());
    IKeyValue[] bills = InvoiceVOUtil.getBillHelper(vos);
    IPURemoteCallCombinator rccrule =
        new InvoiceVatUpgradeRemoteRule(bills, csetterLst);
    rccrule.prepare();
    rccrule.process();
  }

}
