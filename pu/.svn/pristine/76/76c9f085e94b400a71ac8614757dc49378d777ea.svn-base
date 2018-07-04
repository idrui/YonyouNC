package nc.vo.pu.m25.vochange;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.pubitf.sc.m61.pu.ISCOrderFor25;
import nc.pubitf.sc.m61.pu.SCOrderForPUInvoiceViewVO;
import nc.vo.ic.m47.entity.SubcontInBodyVO;
import nc.vo.ic.m47.entity.SubcontInVO;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.m25.pub.VORelationCalculate;
import nc.vo.pu.m25.rule.maintain.DefaultRececountrySetter;
import nc.vo.pu.m25.rule.maintain.DefaultSendcountrySetter;
import nc.vo.pu.m25.rule.maintain.DefaultTaxcountrySetter;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule.ICountrySetter;
import nc.vo.pu.m25.vochange.processor.InvoiceExchangeProcessor;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.StringUtils;

/**
 * vo交换处理，包括交换前和交换后的处理
 * 
 * @since 6.0
 * @version 2011-4-18 下午05:10:42
 * @author 田锋涛
 */
public class ChangeVOAdjustor47To25 extends InvoiceChangeVOAdjustor {

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    return super.batchAdjustAfterChange(srcVOs, destVOs, adjustContext);
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    // 交换前处理
    SubcontInVO[] sourceVOs =
        (SubcontInVO[]) ArrayUtil.convertArrayType(srcVOs);
    for (SubcontInVO vo : sourceVOs) {
      // 对于明确的推式保存及拉单，IC已经补充了可开票数量
      // 这里再做补充是考虑采购结算时有自己调用vo交换的情况
      for (SubcontInBodyVO item : vo.getBodys()) {
        if (MathTool.isZero(item.getNinvoicenum())) {
          item.setNinvoicenum(MathTool.sub(item.getNnum(), item.getNsignnum()));
        }
      }
    }
    return sourceVOs;
  }

  private void calByMny(InvoiceVO[] vos) {
    // 根据需求意见，转单过来后根据无税金额反算其他
    VORelationCalculate calculate = new VORelationCalculate();
    // 强制无税优先
    calculate.setForceNoTaxPrior(true);
    for (InvoiceVO vo : vos) {
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        // 根据无税金额反算其他
        calculate.calculate(vo.getParentVO(), item, InvoiceItemVO.NORIGMNY);
      }
    }
  }

  private InvoiceVO[] filterFromSelf47Invoice(InvoiceVO[] vos) {
    List<InvoiceVO> filterLst = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : vos) {
      List<InvoiceItemVO> itemLst = new ArrayList<InvoiceItemVO>();
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        if (StringUtils.isBlank(item.getPk_order_b())) {
          itemLst.add(item);
        }
      }
      // 部分或全部行来源于自制委外入，取全部VAT相关信息
      // 全部行有来源委外订单，则只取表头的逆向征税、供应商VAT码、组织VAT码
      InvoiceVO filterVo = new InvoiceVO();
      filterVo.setParentVO(vo.getParentVO());
      filterVo
          .setChildrenVO(itemLst.toArray(new InvoiceItemVO[itemLst.size()]));
      filterLst.add(filterVo);
    }
    return filterLst.toArray(new InvoiceVO[filterLst.size()]);
  }

  private String[] filterSrc61Bid(InvoiceVO[] vos) {
    List<String> src61BIDLst = new ArrayList<String>();
    for (InvoiceVO vo : vos) {
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        // 随机从源头委外订单查询
        if (StringUtils.isNotBlank(item.getPk_order_b())) {
          src61BIDLst.add(item.getPk_order_b());
        }
      }
    }
    return src61BIDLst.toArray(new String[src61BIDLst.size()]);
  }

  /**
   * 设置币种
   * 
   * @param vos
   */
  private void setCurrType(InvoiceVO[] vos) {
    Set<String> pk_orgSet = new HashSet<String>();
    // 1.分类主组织
    for (InvoiceVO vo : vos) {
      String pk_org = vo.getParentVO().getPk_org();
      if (pk_orgSet.contains(pk_org)) {
        continue;
      }
      pk_orgSet.add(pk_org);
    }

    // 2.根据组织找组织本位币
    Map<String, String> curMap =
        OrgUnitPubService.queryOrgCurrByPk(pk_orgSet
            .toArray(new String[pk_orgSet.size()]));
    if (curMap == null) {
      return;
    }
    // 3.设置币种
    for (InvoiceVO vo : vos) {
      String currType = curMap.get(vo.getParentVO().getPk_org());
      vo.getParentVO().setCcurrencyid(currType);
      vo.getParentVO().setCorigcurrencyid(currType);
    }
  }

  private void setDefaultVatInfoFrom61(InvoiceVO[] retvos, String[] m61bids) {
    ISCOrderFor25 service = NCLocator.getInstance().lookup(ISCOrderFor25.class);
    Map<String, SCOrderForPUInvoiceViewVO> m61vatinfo = null;
    try {
      m61vatinfo = service.querySCOrderVATInfo(m61bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (null == m61vatinfo) {
      return;
    }
    for (InvoiceVO vo : retvos) {
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        String pk_order_b = item.getPk_order_b();
        if (StringUtils.isBlank(pk_order_b)) {
          continue;
        }
        SCOrderForPUInvoiceViewVO m61view = m61vatinfo.get(pk_order_b);
        InvoiceHeaderVO head = vo.getParentVO();
        if (StringUtils.isBlank(head.getCrececountryid())) {
          head.setCrececountryid(m61view.getCrececountryid());
        }
        if (StringUtils.isBlank(head.getCsendcountryid())) {
          head.setCsendcountryid(m61view.getCsendcountryid());
        }
        if (StringUtils.isBlank(head.getCtaxcountryid())) {
          head.setCtaxcountryid(m61view.getCtaxcountryid());
        }
        head.setBtriatradeflag(m61view.getBtriatradeflag());
        head.setFbuysellflag(m61view.getFbuysellflag());
        head.setCtradewordid(m61view.getCtradewordid());
        item.setCtaxcodeid(m61view.getCtaxcodeid());
        item.setFtaxtypeflag(m61view.getFtaxtypeflag());
        item.setNtaxrate(m61view.getNtaxrate());
        item.setNnosubtaxrate(m61view.getNnosubtaxrate());
      }
    }
  }

  private void setDefaultVatInfoFromSelf47(InvoiceVO[] fromSelf47) {
    List<ICountrySetter> csetterLst =
        new ArrayList<InvoiceVatDefaultValueFillRule.ICountrySetter>();
    csetterLst.add(new DefaultSendcountrySetter());
    csetterLst.add(new DefaultRececountrySetter());
    csetterLst.add(new DefaultTaxcountrySetter());
    IKeyValue[] bills = InvoiceVOUtil.getBillHelper(fromSelf47);
    IPURemoteCallCombinator rccrule =
        new InvoiceVatDefaultValueFillRule(bills, csetterLst);
    rccrule.prepare();
    rccrule.process();
  }

  @Override
  protected InvoiceVO[] doDefaultAfterChange(InvoiceVO[] vos) {
    // 设置业务日期
    this.setBusiDate(vos);
    // 设置本位币
    this.setCurrType(vos);
    // 从主数量重新进行计算，多次转单后数量不同,因为都是无税的，金额会与上游一致
    // 得考虑全部转单的情况，此时不应该计算
    InvoiceVOUtil.reCalculateBasedNum(vos, true);
    // 设置VAT信息，在联动计算之前
    this.setDefaultVatInfo(vos);
    // 根据需求意见，转单过来后根据无税金额反算其他
    this.calByMny(vos);
    // 如果汇率有变化，重新取
    new InvoiceExchangeProcessor().resetExchangeByQuery(vos);
    // 根据订单未找到付款单位的话，默认取供应商的值
    this.setPayUnit(vos);
    // add by liangchen1 NC631需求，区分普通采购与进出口采购
    this.setInvoiceType(vos);
    return vos;
  }

  @Override
  protected InvoiceVO[] processVirtureInvoice(InvoiceVO[] resultVOs) {
    // 委托加工入库生成虚拟发票的跟正常vo交换区别不大
    return this.doDefaultAfterChange(resultVOs);
  }

  /**
   * 上游单据为委托加工入库单时，<br>
   * 匹配税码的维度、税码、税率、不可抵扣税率，从源头的委外订单寻找。<br>
   * 如果委托加工入库单为自制，则采购发票根据自身信息寻找。
   */
  @Override
  protected void setDefaultVatInfo(InvoiceVO[] retvos) {
    // 处理有来源委外订单的发票
    String[] m61bids = this.filterSrc61Bid(retvos);
    if (m61bids.length > 0) {
      this.setDefaultVatInfoFrom61(retvos, m61bids);
    }
    // 处理来源于自制委外入库单的发票
    InvoiceVO[] fromSelf47 = this.filterFromSelf47Invoice(retvos);
    if (fromSelf47.length > 0) {
      this.setDefaultVatInfoFromSelf47(fromSelf47);
    }

  }
}
