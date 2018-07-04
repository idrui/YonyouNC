/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-12 下午02:55:56
 */
package nc.pubimpl.pu.m25.pf.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 传应付前过滤掉一些不能传应付的发票(如果审批后自动结算完成时已经传过应付的发票)
 * @scene
 * 传应付
 * @param
 * isManual 是否手工传应付
 *
 * @since 6.3
 * @version 2014-10-22 下午3:38:34
 * @author zhangshqb
 */
public class FilterSendAPInvoiceRule implements IFilterRule<InvoiceVO> {
  private boolean isManual = false;

  /**
   * FilterSendAPInvoiceRule 的构造子
   * 
   * @param isManual
   */
  public FilterSendAPInvoiceRule(boolean isManual) {
    this.isManual = isManual;
  }

  @Override
  public InvoiceVO[] process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    InvoiceVO[] filterVos = vos;
    if (!this.isManual) {
      filterVos = this.filterSent(filterVos);// 过滤已经传应付的单据
    }
    filterVos = this.filterZeroRow(filterVos);
    return filterVos;
  }

  private InvoiceVO[] filterSent(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    List<InvoiceVO> sendVos = new ArrayList<InvoiceVO>();
    // 没办法，这里只能重新查询
    String[] pks = AggVOUtil.getPrimaryKeys(vos);
    InvoiceVO[] newVos = new BillQuery<InvoiceVO>(InvoiceVO.class).query(pks);
    if (ArrayUtils.isEmpty(newVos) || vos.length != newVos.length) {
      ExceptionUtils
          .wrappBusinessException("Fatal error happended,please retry!");/*
                                                                          * -=
                                                                          * notranslate
                                                                          * =-
                                                                          */
    }
    Map<String, InvoiceVO> voMap = AggVOUtil.createVOMap(newVos);
    for (InvoiceVO vo : vos) {
      String pk_invoice = vo.getParentVO().getPk_invoice();
      InvoiceVO newVO = voMap.get(pk_invoice);
      UFBoolean bap = newVO.getParentVO().getBapflag();
      if (null == bap || bap.booleanValue()) {
        continue;// 如果已经传过应付，则过滤掉
      }
      sendVos.add(vo);
    }
    return sendVos.toArray(new InvoiceVO[sendVos.size()]);
  }

  private InvoiceVO[] filterZeroRow(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    List<InvoiceVO> filterVos = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : vos) {
      List<InvoiceItemVO> filterItems = new ArrayList<InvoiceItemVO>();
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        UFDouble taxmny = MathTool.nvl(item.getNorigtaxmny());
        if (UFDouble.ZERO_DBL.equals(taxmny)) {
          continue; // 零价税合计行要过滤掉(确认应付的调差行和无发票结算有可能为零)
        }
        filterItems.add(item);
      }
      int size = filterItems.size();
      if (0 == size) {
        continue; // 一行符合要求的行都没有，则整单过滤掉
      }
      InvoiceItemVO[] newItems = filterItems.toArray(new InvoiceItemVO[size]);
      InvoiceHeaderVO head = vo.getParentVO();
      InvoiceVO newVo = new InvoiceVO();
      newVo.setParentVO(head);
      newVo.setChildrenVO(newItems);
      filterVos.add(newVo);
    }
    if (0 == filterVos.size()) {
      return null;
    }
    return filterVos.toArray(new InvoiceVO[filterVos.size()]);
  }

}
