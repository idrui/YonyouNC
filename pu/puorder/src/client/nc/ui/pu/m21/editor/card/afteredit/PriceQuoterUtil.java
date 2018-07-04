package nc.ui.pu.m21.editor.card.afteredit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.ui.pu.m21.rule.RelationCalculateAfterQuoter;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.PriceQuoter;
import nc.vo.pu.pub.enumeration.PriceParam;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 供应商、物料等编辑后询价处理事件
 * 
 * @since 6.5
 * @version 2014-1-17 上午09:15:57
 * @author mengjian
 */
public class PriceQuoterUtil {

  public PriceQuoterUtil() {

  }

  public void setDefaultPrice(BillCardPanel panel, PriceParam paramKey,
      int[] rows) {
    String pk_org =
        (String) panel.getHeadTailItem(OrderHeaderVO.PK_ORG).getValueObject();
    PriceParam[] pss = PUSysParamUtil.getPO16(pk_org);
    if (!this.isExistParam(pss, paramKey)) {
      return;
    }
    if (ArrayUtils.isEmpty(rows)) {
      return;
    }
    CardEditorHelper editor = new CardEditorHelper(panel);
    PriceQuoter quoter = new PriceQuoter(editor);
    Integer[] pricerows = this.notContractFilter(panel, rows);
    if (ArrayUtils.isEmpty(pricerows)) {
      return;
    }
    Map<Integer, String> map = quoter.setDefaultPrice(pricerows);
    if (null == map || 0 == map.size()) {
      return;
    }
    RelationCalculateAfterQuoter tool = new RelationCalculateAfterQuoter(panel);
    tool.relationCalculate(map);
  }

  /**
   * 判断当前编辑字段是否在已设置的采购订单自动询价条件列表中
   * 
   * @param pss
   * @param paramKey
   * @return
   */
  private boolean isExistParam(PriceParam[] pss, PriceParam paramKey) {
    if (pss == null || pss.length < 1) {
      return false;
    }
    for (int i = 0; i < pss.length; i++) {
      if (paramKey.equals(pss[i])) {
        return true;
      }
    }
    return false;
  }

  /**
   * 过滤掉关联到合同的项
   * 
   * @param panel
   * @param rows
   * @return
   */
  private Integer[] notContractFilter(BillCardPanel panel, int[] rows) {
    if (ArrayUtils.isEmpty(rows)) {
      return null;
    }
    List<Integer> intList = new ArrayList<Integer>();
    for (int row : rows) {
      Object pk_material = panel.getBodyValueAt(row, OrderItemVO.PK_MATERIAL);
      if (ObjectUtil.isEmptyWithTrim(pk_material)) {
        continue;
      }
      Object obj = panel.getBodyValueAt(row, OrderItemVO.CCONTRACTROWID);
      if (obj == null || StringUtils.isEmpty(obj.toString())) {
        intList.add(Integer.valueOf(row));
      }
      else {
        Object price = panel.getBodyValueAt(row, OrderItemVO.NQTORIGTAXPRICE);
        if (price == null || StringUtils.isEmpty(price.toString())) {
          intList.add(Integer.valueOf(row));
        }
      }
    }

    return intList.toArray(new Integer[intList.size()]);
  }
}
