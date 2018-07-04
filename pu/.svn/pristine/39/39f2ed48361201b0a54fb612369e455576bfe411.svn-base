/**
 * 
 */
package nc.bs.pu.m25.ap.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstHeaderVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceRowType;
import nc.vo.pu.m25.pub.VORelationCalculate;
import nc.vo.pu.m27.query.SettleBillInfo;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.ICBillType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 确认应付调差规则
 * @scene
 * 传应付
 * @param
 * estVOs 采购入库单暂估VO 
 * sttlInfoMap 发票对象的结算明细 
 * @since 6.3
 * @version 2014-10-22 下午3:46:19
 * @author zhangshqb
 */
public class MakeDiffConfirmAPRule implements IRule<InvoiceVO> {
  private PurchaseInEstVO[] confirmVos;

  private MapList<String, SettleBillInfo> sttlInfoMap;

  public MakeDiffConfirmAPRule(PurchaseInEstVO[] estVOs,
      MapList<String, SettleBillInfo> sttlInfoMap) {
    this.sttlInfoMap = sttlInfoMap;
    this.initConfirmPurFIVO(estVOs);
  }

  @Override
  public void process(InvoiceVO[] vos) {
    // 不需要调差，则直接返回
    if (ArrayUtils.isEmpty(vos) || ArrayUtils.isEmpty(this.confirmVos)
        || (null == this.sttlInfoMap) || (0 == this.sttlInfoMap.size())) {
      return;
    }
    for (InvoiceVO vo : vos) {
      this.makeDiff(vo);
    }
  }

  /** 得到本次与发票行结算的多张入库单行的确认应付价税合计的和 **/
  private UFDouble getNoSpltConfirmAPMny(List<SettleBillInfo> sttlInfoList) {
    if (CollectionUtils.isEmpty(sttlInfoList)) {
      return UFDouble.ZERO_DBL;
    }
    UFDouble apmny = UFDouble.ZERO_DBL;
    Map<String, PurchaseInEstHeaderVO> confirmVOMap =
        AggVOUtil.createHeadMap(this.confirmVos);
    for (SettleBillInfo info : sttlInfoList) {
      if (!this.isConfirmedAP(confirmVOMap, info)) {
        continue;
      }
      UFDouble stlAjsApMny = info.getNoppconfirmapmny();
      apmny = MathTool.add(apmny, stlAjsApMny);
    }
    return apmny;
  }

  /** 已经按结算明细拆分的行，只取结算明细上的入库单确认应付值 **/
  private UFDouble getSpltConfirmAPMny(String pk_stockps_b,
      String pk_invoice_b, List<SettleBillInfo> sttlInfoList) {
    Map<String, PurchaseInEstHeaderVO> confirmVOMap =
        AggVOUtil.createHeadMap(this.confirmVos);
    for (SettleBillInfo info : sttlInfoList) {
      if (!this.isConfirmedAP(confirmVOMap, info)) {
        continue;
      }
      String stlStockb = info.getPk_stock_b();
      String stlInvcb = info.getPk_invoice_b();
      if (!pk_stockps_b.equals(stlStockb) || !pk_invoice_b.equals(stlInvcb)) {
        continue;
      }
      return info.getNoppconfirmapmny();
    }
    return UFDouble.ZERO_DBL;
  }

  private void initConfirmPurFIVO(PurchaseInEstVO[] estVOs) {
    if (ArrayUtils.isEmpty(estVOs)) {
      return;
    }
    List<PurchaseInEstVO> confirmVOList = new ArrayList<PurchaseInEstVO>();
    for (PurchaseInEstVO vo : estVOs) {
      GoodsEstVO head = vo.getParentVO();
      if (EnumToAPFlag.ConfirmToAP.value().equals(head.getFtoapflag())) {
        confirmVOList.add(vo);
      }
    }
    int size = confirmVOList.size();
    this.confirmVos = confirmVOList.toArray(new PurchaseInEstVO[size]);
  }

  /** 判断此条结算信息的入库单是否作过确认应付 **/
  private boolean isConfirmedAP(
      Map<String, PurchaseInEstHeaderVO> confirmVOMap, SettleBillInfo info) {
    String billtype = info.getVstockbilltype();
    // 非采购入，V60第一版不支持其它库存单据直接确认应付
    if (!ICBillType.PurchaseIn.getCode().equals(billtype)) {
      return false;
    }
    String pk_stockps_b = info.getPk_stock_b();
    // 红蓝发票对冲的不处理
    if (StringUtil.isEmptyWithTrim(pk_stockps_b)) {
      return false;
    }
    // 此条结算信息的入库单未确认过应付
    if (!confirmVOMap.containsKey(pk_stockps_b)) {
      return false;
    }
    return true;
  }

  private void makeDiff(InvoiceVO vo) {
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      int rowtype = item.getFrowtype().intValue();
      if (InvoiceRowType.GOODS_ROW != rowtype) {
        continue;// 费用行不处理
      }
      String pk_invoice_b = item.getPk_invoice_b();
      List<SettleBillInfo> stlInfo = this.sttlInfoMap.get(pk_invoice_b);
      String pk_stockps_b = item.getPk_stockps_b();
      UFDouble stlStockApMny = UFDouble.ZERO_DBL;
      if (StringUtil.isEmptyWithTrim(pk_stockps_b)) {
        // 未按结算明细拆分的行处理
        stlStockApMny = this.getNoSpltConfirmAPMny(stlInfo);
      }
      else {
        // 已经按结算明细拆分的行处理
        stlStockApMny =
            this.getSpltConfirmAPMny(pk_stockps_b, pk_invoice_b, stlInfo);
      }
      if (UFDouble.ZERO_DBL.equals(stlStockApMny)) {
        continue;// 未确认过，不处理
      }
      UFDouble invcMny = item.getNorigtaxmny();
      // 确认与发票相等，则处理为零，后续规则会过滤掉
      UFDouble diffMny = MathTool.sub(invcMny, stlStockApMny);
      this.updateInvcOrigTaxMny(vo.getParentVO(), item, diffMny);
    }
  }

  private void updateInvcOrigTaxMny(InvoiceHeaderVO head, InvoiceItemVO item,
      UFDouble newOrigTaxMny) {
    item.setNorigtaxmny(newOrigTaxMny);
    // TODO 赵玉行 调差单不考虑数量和单价（需要需求确认）
    item.setNnum(UFDouble.ZERO_DBL);
    item.setNastnum(UFDouble.ZERO_DBL);
    item.setNastorigprice(UFDouble.ZERO_DBL);
    item.setNastorigtaxprice(UFDouble.ZERO_DBL);
    item.setNorigprice(UFDouble.ZERO_DBL);
    item.setNorigtaxprice(UFDouble.ZERO_DBL);
    new VORelationCalculate().calculate(head, item, InvoiceItemVO.NORIGTAXMNY);
  }

}
