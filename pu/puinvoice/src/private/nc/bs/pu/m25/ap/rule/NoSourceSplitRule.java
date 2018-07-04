/**
 * 
 */
package nc.bs.pu.m25.ap.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceRowType;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.m25.pub.VORelationCalculate;
import nc.vo.pu.m27.query.SettleBillInfo;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleObjectFactory;
import nc.vo.scmpub.res.billtype.ICBillType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              无源头或来源订单的发票拆分规则,按结算明细拆分发票行
 * @scene
 *        传应付
 * @param sttlInfoMap 发票对象的结算明细
 * @since 6.3
 * @version 2014-10-22 下午3:45:29
 * @author zhangshqb
 */
public class NoSourceSplitRule implements IFilterRule<InvoiceVO> {
  private MapList<String, SettleBillInfo> sttlInfoMap;

  /**
   * NoSourceSplitRule 的构造子
   * 
   * @param sttlInfoMap
   *          结算明细
   */
  public NoSourceSplitRule(MapList<String, SettleBillInfo> sttlInfoMap) {
    this.sttlInfoMap = sttlInfoMap;
  }

  @Override
  public InvoiceVO[] process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos) || null == this.sttlInfoMap
        || 0 == this.sttlInfoMap.size()) {
      return vos;
    }
    // 这里使用克隆，否则会改变原来的VO的结构，返回前台时会有问题
    InvoiceVO[] cvos = InvoiceVOUtil.getCloneVOs(vos);
    this.split(cvos);
    return cvos;
  }

  private List<SettleBillInfo> getSplitSttlInfo(InvoiceItemVO item) {
    // 有来源的不用处理
    if (!StringUtil.isEmptyWithTrim(item.getPk_order_b())) {
      return null;
    }
    // 费用折扣行，不用处理
    int rowtype = item.getFrowtype().intValue();
    if (InvoiceRowType.GOODS_ROW != rowtype) {
      return null;
    }
    String pk_invoice_b = item.getPk_invoice_b();
    if (!this.sttlInfoMap.containsKey(pk_invoice_b)) {
      return null;
    }
    List<SettleBillInfo> sttlInfoList = this.sttlInfoMap.get(pk_invoice_b);
    if (null == sttlInfoList || 1 > sttlInfoList.size()) {
      return null;
    }
    return sttlInfoList;
  }

  private UFDouble getSplitTotalMny(InvoiceHeaderVO head, InvoiceItemVO item,
      UFDouble stlNum) {
    UFDouble num = item.getNnum();
    UFDouble taxmny = item.getNorigtaxmny();
    String pk_currency = head.getCorigcurrencyid();
    int digit =
        new ScaleObjectFactory(item.getPk_group()).getMnyScaleObject()
            .getDigit(pk_currency);
    UFDouble spltMny = stlNum.div(num).multiply(taxmny, digit);
    return spltMny;
  }

  private boolean isCanSplit(SettleBillInfo stlInfo) {
    String stockBT = stlInfo.getVstockbilltype();
    // 非与采购入库单的结算，则不处理
    if (!ICBillType.PurchaseIn.getCode().equals(stockBT)) {
      return false;
    }
    // 与发票对冲的行不处理
    if (StringUtil.isEmptyWithTrim(stlInfo.getPk_stock_b())) {
      return false;
    }
    return true;
  }

  private void makeRemain(InvoiceItemVO[] origItems, InvoiceItemVO[] splitItems) {
    // 要倒挤的字段
    String[] itemkeys =
        new String[] {
          InvoiceItemVO.NASTNUM, InvoiceItemVO.NCALCOSTMNY,
          InvoiceItemVO.NCALTAXMNY, InvoiceItemVO.NGLOBALMNY,
          InvoiceItemVO.NGLOBALTAXMNY, InvoiceItemVO.NGROUPMNY,
          InvoiceItemVO.NGROUPTAXMNY, InvoiceItemVO.NMNY,
          InvoiceItemVO.NNOSUBTAX, InvoiceItemVO.NORIGMNY, InvoiceItemVO.NTAX,
          InvoiceItemVO.NTAXMNY
        };
    Map<String, InvoiceItemVO> origItemMap =
        CirVOUtil.createKeyVOMap(origItems);
    MapList<String, InvoiceItemVO> splitItemMap =
        new MapList<String, InvoiceItemVO>();
    for (InvoiceItemVO splitItem : splitItems) {
      splitItemMap.put(splitItem.getPk_invoice_b(), splitItem);
    }
    for (Map.Entry<String, InvoiceItemVO> entry : origItemMap.entrySet()) {
      String pk_invoice_b = entry.getKey();
      List<InvoiceItemVO> splitItemList = splitItemMap.get(pk_invoice_b);
      this.makeRemainOneItem(entry.getValue(), splitItemList, itemkeys);
    }
  }

  private void makeRemainOneItem(InvoiceItemVO origItem,
      List<InvoiceItemVO> splitItemList, String[] targetItemKey) {
    for (String itemkey : targetItemKey) {
      UFDouble totalValue = (UFDouble) origItem.getAttributeValue(itemkey);
      UFDouble sumSplitValue = null;
      for (InvoiceItemVO splitItem : splitItemList) {
        sumSplitValue =
            MathTool.add(sumSplitValue,
                (UFDouble) splitItem.getAttributeValue(itemkey));
      }
      UFDouble remainValue = MathTool.sub(totalValue, sumSplitValue);
      if (!MathTool.isZero(remainValue)) {
        UFDouble value =
            (UFDouble) splitItemList.get(splitItemList.size() - 1)
                .getAttributeValue(itemkey);
        splitItemList.get(splitItemList.size() - 1).setAttributeValue(itemkey,
            MathTool.add(remainValue, value));
      }
    }
  }

  private void procSpltItemInfo(InvoiceHeaderVO head, InvoiceItemVO spltItem,
      UFDouble stlNum, UFDouble spltTTMny) {
    spltItem.setNnum(stlNum);
    VORelationCalculate tool = new VORelationCalculate();
    tool.calculate(head, spltItem, InvoiceItemVO.NNUM);// 先用数量算一次
    spltItem.setNorigtaxmny(spltTTMny);
    tool.calculate(head, spltItem, InvoiceItemVO.NORIGTAXMNY);// 再按金额算一次
  }

  private void setSplitItemSrcInfo(SettleBillInfo stlInfo,
      InvoiceItemVO spltItem) {
    spltItem.setPk_order(stlInfo.getPk_stockorder());
    spltItem.setPk_order_b(stlInfo.getPk_stockorder_b());
    spltItem.setCfirstbid(stlInfo.getPk_stockorder_b());
    spltItem.setCfirstid(stlInfo.getPk_stockorder());
    spltItem.setPk_stockps_b(stlInfo.getPk_stock_b());
  }

  private void split(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      List<InvoiceItemVO> splitItems = new ArrayList<InvoiceItemVO>();
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        this.splitRow(splitItems, item, vo.getParentVO());
      }
      if (splitItems.size() == vo.getChildrenVO().length) {
        continue;
      }
      InvoiceItemVO[] arySplitItems =
          splitItems.toArray(new InvoiceItemVO[splitItems.size()]);
      // 进行尾差处理，做倒挤
      this.makeRemain(vo.getChildrenVO(), arySplitItems);
      vo.setChildrenVO(arySplitItems);
    }
  }

  private void splitRow(List<InvoiceItemVO> splitItems, InvoiceItemVO item,
      InvoiceHeaderVO head) {
    splitItems.add(item);
    List<SettleBillInfo> sttlInfoList = this.getSplitSttlInfo(item);
    if (CollectionUtils.isEmpty(sttlInfoList)) {
      return;
    }
    if (1 == sttlInfoList.size()) {
      this.setSplitItemSrcInfo(sttlInfoList.get(0), item);
      return;
    }
    // mengjian 2014-06-27 这里应该是移除最后一个
    int size = splitItems.size();
    splitItems.remove(size - 1);// 都用克隆的
    UFDouble splittedTotalMny = UFDouble.ZERO_DBL;
    UFDouble splittedNum = UFDouble.ZERO_DBL;
    for (SettleBillInfo stlInfo : sttlInfoList) {
      if (!this.isCanSplit(stlInfo)) {
        continue;
      }
      InvoiceItemVO spltItem = (InvoiceItemVO) item.clone();
      this.setSplitItemSrcInfo(stlInfo, spltItem);
      UFDouble stlNum = stlInfo.getNsettlenum();
      splittedNum = MathTool.add(splittedNum, stlNum);
      UFDouble spltTTMny = null;
      // 最后一笔做倒挤，因结算数量中也没有合理损耗，因此这里也不用考虑
      if (splittedNum.equals(item.getNnum())) {
        spltTTMny = MathTool.sub(item.getNorigtaxmny(), splittedTotalMny);
      }
      else {
        spltTTMny = this.getSplitTotalMny(head, item, stlNum);
      }
      splittedTotalMny = MathTool.add(spltTTMny, splittedTotalMny);
      this.procSpltItemInfo(head, spltItem, stlNum, spltTTMny);
      splitItems.add(spltItem);
      // 已经将发票的数量拆分完成，如再有数据，则发生严重数据错误
      if (splittedNum.equals(item.getNnum())) {
        break;
      }
    }
    // 如果未克隆过数据，则还用原来的item
    if (0 == splitItems.size()) {
      splitItems.add(item);
    }
    else if (!splittedNum.equals(item.getNnum())) {
      UFDouble unSpltNum = MathTool.sub(item.getNnum(), splittedNum);
      UFDouble unSpltmny =
          MathTool.sub(item.getNorigtaxmny(), splittedTotalMny);
      InvoiceItemVO spltItem = (InvoiceItemVO) item.clone();
      this.procSpltItemInfo(head, spltItem, unSpltNum, unSpltmny);
      splitItems.add(spltItem);
    }
  }
}
