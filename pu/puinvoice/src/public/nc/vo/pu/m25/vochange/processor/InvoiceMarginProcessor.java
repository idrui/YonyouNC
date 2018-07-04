package nc.vo.pu.m25.vochange.processor;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.margin.InvoiceMuiltyWordMarginJudgement;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.CalculatorUtil;
import nc.vo.pubapp.margin.BillMarginContext;
import nc.vo.pubapp.margin.MarginContextFactory;
import nc.vo.pubapp.margin.MarginEntry;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * 发票倒挤处理器
 * 
 * @since 6.0
 * @version 2011-7-2 下午01:39:55
 * @author 田锋涛
 */

public class InvoiceMarginProcessor {

  /**
   * 倒挤处理
   * 
   * @param vos
   */
  public void processMargin(InvoiceVO[] vos) {
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
              POBillType.Order.getCode(),
              OrderItemVO.NNUM,
              mnyFields,
              POBillType.Invoice.getCode(),
              InvoiceItemVO.NNUM,
              mnyFields,
              InvoiceItemVO.PK_ORDER_B,
              InvoiceItemVO.PK_ORDER,
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
}
