package nc.vo.pu.m25.vochange;

import java.util.ArrayList;
import java.util.List;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.margin.InvoiceMuiltyWordMarginJudgement;
import nc.vo.pu.m25.vochange.processor.InvoiceExchangeProcessor;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.CalculatorUtil;
import nc.vo.pubapp.margin.BillMarginContext;
import nc.vo.pubapp.margin.MarginContextFactory;
import nc.vo.pubapp.margin.MarginEntry;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.MMBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * 生产工序委外
 * 
 * @since 6.5
 * @version 2014-3-24 下午04:47:30
 * @author mengjian
 */
public class ChangeVOAdjustor55E6To25 extends InvoiceChangeVOAdjustor {
  /**
   * 倒挤之后需调整一下计成本金额以尽量保证跟上游是一致的
   * 
   * @param vos
   */
  private void adjustCalCostMny(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        // 计成本金额=本币无税金额+不可抵扣税额（加法不会影响精度）
        UFDouble calcostMny =
            CalculatorUtil.add(item.getNmny(), item.getNnosubtax());
        item.setNcalcostmny(calcostMny);
      }
    }
  }

  private InvoiceVO filterByOrder(InvoiceVO vo) {
    List<InvoiceItemVO> items = new ArrayList<InvoiceItemVO>();
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      if (StringUtils.isNotBlank(item.getPk_order_b())) {
        items.add(item);
      }
    }
    if (items.size() == 0) {
      return null;
    }
    InvoiceVO newVO = new InvoiceVO();
    newVO.setParent(vo.getParentVO());
    newVO.setChildrenVO(items.toArray(new InvoiceItemVO[items.size()]));

    return newVO;
  }

  /**
   * 倒挤处理
   * 
   * @param vos
   */
  private void processMargin(InvoiceVO[] vos) {
    List<InvoiceVO> noTaxPriorVOs = new ArrayList<InvoiceVO>();
    List<InvoiceVO> taxPriorVOs = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : vos) {
      InvoiceVO filterVO = this.filterByOrder(vo);
      if (filterVO == null) {
        continue;
      }
      String pk_org = filterVO.getParentVO().getPk_purchaseorg();
      if (StringUtils.isBlank(pk_org)) {
        noTaxPriorVOs.add(filterVO);
      }
      else {
        if (PUSysParamUtil.getPO28For25(pk_org)) {
          taxPriorVOs.add(filterVO);
        }
        else {
          noTaxPriorVOs.add(filterVO);
        }
      }
    }
    // 无税优先
    if (noTaxPriorVOs.size() > 0) {
      this.processMargin(
          noTaxPriorVOs.toArray(new InvoiceVO[noTaxPriorVOs.size()]), false);
    }
    // 含税优先
    if (taxPriorVOs.size() > 0) {
      this.processMargin(
          taxPriorVOs.toArray(new InvoiceVO[taxPriorVOs.size()]), true);
    }
  }

  /**
   * 根据含税优先还是无税优先处理尾差（倒挤）
   * 
   * @param vos
   * @param taxPrior true-含税优先；false-无税优先
   */
  private void processMargin(InvoiceVO[] vos, boolean taxPrior) {
    // 只根据是否源头是订单判断是够进行，暂且不加任何判断
    BillMarginContext context;
    String[] compareFields = null;
    // 本币和原本相关的金额都要倒挤
    String[] mnyFields =
        new String[] {
          InvoiceItemVO.NORIGTAXMNY, InvoiceItemVO.NORIGMNY,
          InvoiceItemVO.NMNY, InvoiceItemVO.NTAXMNY, InvoiceItemVO.NTAX
        // 2012-8-17 wuxla、tianft、suncong
        // 需要倒挤税额，否则无税金额、价税合计、税额关系不相等
        // 2012-8-19 孙聪、王印芬、张宇欣、田锋涛、吴小亮不倒挤记成本金额
        // InvoiceItemVO.NCALCOSTMNY, InvoiceItemVO.NCALTAXMNY
        };
    // 含税优先
    if (taxPrior) {
      compareFields = new String[] {
        InvoiceHeaderVO.CORIGCURRENCYID, InvoiceItemVO.NORIGTAXPRICE,
        // 数量只比较符号，不比较具体值
        InvoiceItemVO.NNUM
      };
    }
    // 无税优先
    else {
      compareFields = new String[] {
        InvoiceHeaderVO.CORIGCURRENCYID, InvoiceItemVO.NORIGPRICE,
        // 数量只比较符号，不比较具体值
        InvoiceItemVO.NNUM
      };
    }

    try {
      context =
          MarginContextFactory.getInstance().produceMarginContext(
              vos,
              MMBillType.PscSettle.getCode(),
              OrderItemVO.NNUM,
              mnyFields,
              POBillType.Invoice.getCode(),
              InvoiceItemVO.NNUM,
              mnyFields,
              InvoiceItemVO.CSOURCEBID,
              InvoiceItemVO.CSOURCEID,
              new InvoiceMuiltyWordMarginJudgement(compareFields,
                  compareFields, vos));
      MarginEntry.getInstance().process(context);
      // 倒挤之后需调整一下计成本金额以尽量保证跟上游是一致的
      this.adjustCalCostMny(vos);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 转单后的默认处理，子类有需求可以覆写此方法
   * 
   * @param resultVOs
   * @return
   */
  @Override
  protected InvoiceVO[] doDefaultAfterChange(InvoiceVO[] resultVOs) {
    // 设置业务日期
    this.setBusiDate(resultVOs);
    // 转单时的联动计算
    this.reCalculateFromSource(resultVOs);
    // 倒挤处理,根据采购订单倒挤，要保证是最后一次拉单并且币种和价格与订单相同
    this.processMargin(resultVOs);
    // 如果汇率有变化，重新取
    new InvoiceExchangeProcessor().resetExchangeByQuery(resultVOs);
    // 如果来源或者源头有采购订单，供应商和付款单位取订单上的信息
    this.getInvoiceOrderInfoProcessor().fillDataByOrder(resultVOs);
    // 根据订单未找到付款单位的话，默认取供应商的值
    this.setPayUnit(resultVOs);
    // 计划价
    this.setPlanPrice(resultVOs);
    // 询价处理
    this.queryPriceForVOs(resultVOs);
    // 设置VAT相关信息
    this.setDefaultVatInfo(resultVOs);
    // 设置银行账户和账号信息。
    this.setBankAccInfo(resultVOs);

    // 设置业务流程 由于加工费结算单是非业务流程，拉单操作时无法由上游带入
    PfServiceScmUtil.setBusiType(resultVOs, POBillType.Invoice.getCode());
    
    // add by liangchen1 NC631需求，区分普通采购与进出口采购
    this.setInvoiceType(resultVOs);
    return resultVOs;
  }
}
