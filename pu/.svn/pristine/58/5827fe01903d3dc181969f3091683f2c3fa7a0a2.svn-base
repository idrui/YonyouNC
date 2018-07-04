package nc.vo.pu.m25.margin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.margin.MuiltyWordMarginJudgement;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-6-21 下午08:00:58
 * @author 田锋涛
 */

public class InvoiceMuiltyWordMarginJudgement extends MuiltyWordMarginJudgement {

  private static final long serialVersionUID = 1L;

  private Map<String, InvoiceVO> voMap;

  /**
   * @param sourceCompareFields
   * @param destCompareFields
   */
  public InvoiceMuiltyWordMarginJudgement(String[] sourceCompareFields,
      String[] destCompareFields, InvoiceVO[] vos) {
    super(sourceCompareFields, destCompareFields);
    this.voMap = this.creatVOMapBySrcBid(vos);
  }

  @Override
  public boolean shouldBeEqualizedMargin(
      CircularlyAccessibleValueObject childVO,
      CircularlyAccessibleValueObject[] brotherVO,
      CircularlyAccessibleValueObject sourceVO) {

    if (childVO == null || sourceVO == null) {
      return false;
    }

    boolean shouldEqualized = this.shouldBeEqualizedMargin(childVO, sourceVO);
    if (shouldEqualized && !ArrayUtils.isEmpty(brotherVO)) {
      // 兄弟vo的map构造添加，用于后续使用
      this.addBrotherVOMap(brotherVO);
      for (CircularlyAccessibleValueObject brother : brotherVO) {
        shouldEqualized = this.shouldBeEqualizedMargin(brother, sourceVO);
        if (!shouldEqualized) {
          break;
        }
      }
    }
    return shouldEqualized;
  }

  /**
   * 兄弟vo的map构造添加，用于后续使用
   * 
   * @param brotherVO
   */
  private void addBrotherVOMap(CircularlyAccessibleValueObject[] brotherVO) {
    Set<String> brotherVOpks = new HashSet<String>();
    for (CircularlyAccessibleValueObject brother : brotherVO) {
      String pk_invoice =
          (String) brother.getAttributeValue(InvoiceItemVO.PK_INVOICE);
      if (pk_invoice != null) {
        brotherVOpks.add((String) brother
            .getAttributeValue(InvoiceItemVO.PK_INVOICE));
      }
    }
    if (brotherVOpks.size() == 0) {
      return;
    }
    BillQuery<InvoiceVO> billQuery = new BillQuery<InvoiceVO>(InvoiceVO.class);
    InvoiceVO[] brotherAggvos =
        billQuery.query(brotherVOpks.toArray(new String[brotherVOpks.size()]));
    Map<String, InvoiceVO> brothervoMap =
        this.creatVOMapBySrcBid(brotherAggvos);
    this.voMap.putAll(brothervoMap);
  }

  /**
   * @param vos
   */
  private Map<String, InvoiceVO> creatVOMapBySrcBid(InvoiceVO[] vos) {
    Map<String, InvoiceVO> map = new HashMap<String, InvoiceVO>();
    for (InvoiceVO invoiceVO : vos) {
      for (InvoiceItemVO item : invoiceVO.getChildrenVO()) {
        if (item.getCsourcebid() != null) {
          map.put(item.getCsourcebid(), invoiceVO);
        }
      }
    }
    return map;
  }

  private boolean shouldBeEqualizedMargin(
      CircularlyAccessibleValueObject childVO,
      CircularlyAccessibleValueObject sourceVO) {
    boolean shouldEqualized = true;
    for (int i = 0; i < this.getSourceCompareFields().length; i++) {
      String sourceCompareField = this.getSourceCompareFields()[i];
      String destCompareField = this.getDestCompareFields()[i];
      Object parentValue = sourceVO.getAttributeValue(sourceCompareField);
      Object childValue = childVO.getAttributeValue(destCompareField);
      if (InvoiceItemVO.NNUM.equals(destCompareField)) {
        UFDouble parentNum = (UFDouble) parentValue;
        UFDouble childNum = (UFDouble) childValue;
        if (MathTool.isDiffSign(parentNum, childNum)) {
          return false;
        }
        // 数量只比较符号，不比较具体值
        continue;
      }
      // 币种要特殊处理，发票的币种在表头
      if (InvoiceHeaderVO.CORIGCURRENCYID.equals(destCompareField)) {
        if (childVO.getAttributeValue(InvoiceItemVO.CSOURCEBID) != null) {
          childValue =
              this.voMap
                  .get(childVO.getAttributeValue(InvoiceItemVO.CSOURCEBID))
                  .getParentVO().getCorigcurrencyid();
        }
      }
      if (childValue != null && !childValue.equals(parentValue)
          || parentValue != null && !parentValue.equals(childValue)) {
        shouldEqualized = false;
        break;
      }
    }
    return shouldEqualized;
  }
}
