package nc.bs.pu.m27.feesettle.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.pu.reference.pcia.PCIAServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pcia.m4639.entity.P4639BillVO;
import nc.vo.pcia.m4639.entity.P4639ItemVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.est.util.EstTOIAUtil;
import nc.vo.pu.m27.entity.ICostfactorDiscountUtil;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillItemVOUtil;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.bill.SplitBill;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.PCIABillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>费用结算传PCIA的公共抽象类，提供公共方法
 * </ul>
 * <p>
 * 
 * @since 6.36
 * @author mengjian
 * @time 2014-10-25 下午09:10:32
 */
abstract public class AbstractToPCIAUtil {

  protected SettleBillHeaderVO header;

  protected Collection<SettleBillItemVO> items;

  protected Map<String, StockSettleVO> ssVOMap;

  public AbstractToPCIAUtil(SettleBillHeaderVO header,
      Collection<SettleBillItemVO> items) {
    this.header = header;
    this.items = items;
  }

  /**
   * 费用结算传利润中心存货
   * 
   * @param datactx
   */
  public void toPCIA(FeeSettleDataContext datactx) {
    if (this.items == null || this.items.size() == 0 || datactx == null) {
      return;
    }
    this.ssVOMap = datactx.getStockSettleVOMap();
    List<SettleBillItemVO> toP4639Datas = new ArrayList<SettleBillItemVO>();
    Set<SettleFeeAllotDetailVO> allAllotVOLst =
        new HashSet<SettleFeeAllotDetailVO>();
    for (SettleBillItemVO item : this.items) {
      String stockbid = item.getPk_stock_b();
      List<SettleFeeAllotDetailVO> noneEstBbvos =
          this.getFeeAllotDetailVO(datactx).get(stockbid);
      allAllotVOLst.addAll(noneEstBbvos);
      this.genToPCIAAdjBillStlItems(item, toP4639Datas, noneEstBbvos, false);
    }
    // 传单据到PCIA
    this.submitToPCIA(toP4639Datas, allAllotVOLst);

  }

  private void combineSettleItem(SettleBillItemVO sumItem,
      SettleBillItemVO detailItem) {
    EstTOIAUtil.sumCostfactor(sumItem, detailItem,
        SettleBillItemVO.NCOSTFACTOR1.substring(0, 11));
    String[] sumFields = {
      SettleBillItemVO.NMONEY
    };
    EstTOIAUtil.sumNumValueFields(sumItem, detailItem, sumFields);
  }

  private List<SettleBillItemVO> combineToP4639SettleItem(
      List<SettleBillItemVO> orgItemLst,
      Collection<SettleFeeAllotDetailVO> allAllotVOLst) {
    Map<String, SettleFeeAllotDetailVO> allotMap =
        this.getStlAllotMap(allAllotVOLst);
    List<SettleBillItemVO> newItemLst = new ArrayList<SettleBillItemVO>();
    Set<String> processedItem = new HashSet<String>();
    for (int i = 0; i < orgItemLst.size(); ++i) {
      SettleBillItemVO orgItem = orgItemLst.get(i);
      // 此时结算单行PK已经设置为分摊明细PK
      String pk_allot = orgItem.getPk_settlebill_b();
      String pk_supplier_org = orgItem.getPk_supplier();
      if (processedItem.contains(pk_allot)) {
        continue;
      }
      SettleFeeAllotDetailVO feeallotVo = allotMap.get(pk_allot);
      // 找到分摊依据BID相同的行进行合并
      for (int j = i + 1; j < orgItemLst.size(); ++j) {
        SettleBillItemVO nextOrgItem = orgItemLst.get(j);
        String pk_allot_next = nextOrgItem.getPk_settlebill_b();
        SettleFeeAllotDetailVO nextFeeallotVo = allotMap.get(pk_allot_next);
        String pk_supplier_next = nextOrgItem.getPk_supplier();
        if (!nextFeeallotVo.getPk_allotbillbid().equals(
            feeallotVo.getPk_allotbillbid())
            || StringUtils.isNotBlank(pk_supplier_org)
            && !pk_supplier_org.equals(pk_supplier_next)
            || StringUtils.isNotBlank(pk_supplier_next)
            && !pk_supplier_next.equals(pk_supplier_org)) {// 要求费用供应商也相同才合并
          continue;
        }
        this.combineSettleItem(orgItem, nextOrgItem);
        processedItem.add(pk_allot_next);
      }
      newItemLst.add(orgItem);
    }
    return newItemLst;
  }

  private MapList<String, SettleFeeAllotDetailVO> getFeeAllotDetailVO(
      FeeSettleDataContext datactx) {
    if (datactx.getNoneSavedAllotDetails() == null) {
      return datactx.getBeenSavedAllotDetails();
    }
    return datactx.getNoneSavedAllotDetails();
  }

  private Map<String, SettleFeeAllotDetailVO> getStlAllotMap(
      Collection<SettleFeeAllotDetailVO> noneEstBbvos) {
    if (CollectionUtils.isEmpty(noneEstBbvos)) {
      return new HashMap<String, SettleFeeAllotDetailVO>();
    }
    return CirVOUtil.createKeyVOMap(noneEstBbvos
        .toArray(new SettleFeeAllotDetailVO[noneEstBbvos.size()]));
  }

  private void setAdjustSrcInfo(P4639BillVO[] p4639s,
      Collection<SettleFeeAllotDetailVO> allAllotVOLst) {
    Map<String, SettleFeeAllotDetailVO> allotMap =
        this.getStlAllotMap(allAllotVOLst);
    for (P4639BillVO p4639 : p4639s) {
      for (P4639ItemVO item : p4639.getChildrenVO()) {
        String pk_allot = item.getCsrcbid();
        SettleFeeAllotDetailVO allotVo = allotMap.get(pk_allot);
        item.setCadjustbillid(allotVo.getPk_allotbillid());
        item.setCadjustbillitemid(allotVo.getPk_allotbillbid());
        UFDouble adjnum = allotVo.getNallotbillnum();
        item.setNadjustnum(adjnum);
        if(POBillType.InitialEstSettleBill.getCode()
            .equals(this.getSourceTypeForVOChg())){
          item.setVsrctype(POBillType.InitialEstSettleBill.getCode());
        }
      }
    }
  }

  // private void setAdjustSrcInfo(I9BillVO[] i9s,
  // Collection<SettleFeeAllotDetailVO> allAllotVOLst) {
  // Map<String, SettleFeeAllotDetailVO> allotMap =
  // this.getStlAllotMap(allAllotVOLst);
  // for (I9BillVO i9 : i9s) {
  // for (I9ItemVO item : i9.getChildrenVO()) {
  // String pk_allot = item.getCsrcbid();
  // SettleFeeAllotDetailVO allotVo = allotMap.get(pk_allot);
  // item.setCadjustbillid(allotVo.getPk_allotbillid());
  // item.setCadjustbillitemid(allotVo.getPk_allotbillbid());
  // UFDouble adjnum = allotVo.getNallotbillnum();
  // item.setNadjustnum(adjnum);
  // }
  // }
  // }

  protected void genToPCIAAdjBillStlItems(SettleBillItemVO item,
      List<SettleBillItemVO> toP4639Datas,
      List<SettleFeeAllotDetailVO> noneEstBbvos, boolean forceToCost) {
    List<SettleBillItemVO> allToLst =
        this.getToPCIAStlItemsByAllot(item, noneEstBbvos);
    Map<String, SettleFeeAllotDetailVO> stlAllotMap =
        this.getStlAllotMap(noneEstBbvos);
    for (SettleBillItemVO toIAStlItem : allToLst) {
      // 根据需求（见此类的描述），如果费用结算对应的分摊货物已经传过成本，
      // 调差方式为补差，则走损益，forceToCost代表暂估过的费用回冲时，强制走成本
      if (!forceToCost
          && PUParaValue.po13.profit_loss.equals(this.getParaPO13())
          && this.goodsHavenToIA(toIAStlItem, stlAllotMap)) {
      }
      else {
        toP4639Datas.add(toIAStlItem);
      }
    }
  }

  protected PUParaValue.po13 getParaPO13() {
    // 参数PO13(差异转入方式)
    String org = this.header.getPk_org();
    if (StringUtils.isEmpty(org)) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0065")/* @res "进行费用结算去参数时，出现主组织为空错误！" */;
      ExceptionUtils.wrappBusinessException(msg);
      return null;
    }
    return PUSysParamUtil.getPO13(org);
  }

  /**
   * 方法功能描述：VO交换时源头单据类型编码
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-10 上午09:21:32
   */
  abstract protected String getSourceTypeForVOChg();

  /**
   * 根据可传存货的结算表体列表生成用于VO交换的聚合结算VO，并进行分单等处理
   * 
   * @param sItemLst
   * @return
   */
  protected SettleBillVO[] getToPCIASettleVO(List<SettleBillItemVO> sItemLst) {
    SettleBillVO vo = new SettleBillVO();
    vo.setParent(this.header);
    vo.setChildrenVO(sItemLst.toArray(new SettleBillItemVO[sItemLst.size()]));
    SplitBill<SettleBillVO> split = new SplitBill<SettleBillVO>();
    // 库存单据、成本域是默认分单条件(可以覆盖按单据类型、库存单据、供应商、成本域分单）
    // 按会计期间和成本域分单是存货处理
    split.appendKey(SettleBillItemVO.PK_COSTREGION);
    split.appendKey(SettleBillItemVO.PK_STOCK);
    split.appendKey(SettleBillItemVO.PK_SUPPLIER);
    return split.split(new SettleBillVO[] {
      vo
    });
  }

  /**
   * 由原始的结算行生成传存货的克隆结算行
   * 
   * @param origItem 原始结算行
   * @param adjMny 要传存货调整单的金额
   * @param allotVo 对应的分摊明细
   * @return
   */
  protected SettleBillItemVO getToPCIAStlItem(SettleBillItemVO origItem,
      UFDouble adjMny, SettleFeeAllotDetailVO allotVo) {
    if (MathTool.isZero(adjMny)
        || !UFBoolean.TRUE.equals(this.ssVOMap.get(origItem.getPk_stock_b())
            .getBaffectcost())
        || !UFBoolean.TRUE.equals(this.header.getBtoia())) {
      return null;// 对调整金额为零的劳务类发票、不影响成本的入库单或货物结算未传成本分摊明细不需要传IA,
    }
    // 复制ITEM
    SettleBillItemVO copyItem = (SettleBillItemVO) origItem.clone();
    // 调整金额
    copyItem.setNmoney(adjMny);
    // 记录明细ID
    String bbid = allotVo.getPk_settle_feeallot();
    copyItem.setPk_settle_feeallot(bbid);
    copyItem.setPk_settlebill_b(bbid);
    // 按V5X的逻辑，费用结算使用费用发票的供应商
//    copyItem.setPk_supplier(allotVo.getPk_supplier());//按照V65需求，取入库单供应商

    // 重置差异金额；去掉数量、单价、成本要素，其余的全部复制。
    copyItem.setNsettlenum(null);
    copyItem.setNprice(null);
    copyItem.setNgoodsmoney(null);
    copyItem.setNgoodsprice(null);
    for (int j = 0; j < CostfactorVO.MAX_NUM; j++) {
      ICostfactorDiscountUtil.setNcostfactor(copyItem, j, null);
    }
    if (null != allotVo.getCostfactorvo()) {// 为空代表是折扣
      ICostfactorDiscountUtil.setNcostfactor(copyItem, allotVo
          .getCostfactorvo().getIfactororder().intValue() - 1, adjMny);
    }
    return copyItem;
  }

  protected List<SettleBillItemVO> getToPCIAStlItemsByAllot(
      SettleBillItemVO item, List<SettleFeeAllotDetailVO> noneEstBbvos) {
    List<SettleBillItemVO> sItemLst = new ArrayList<SettleBillItemVO>();
    // 没有进行过费用暂估，费用结算时直接传IA
    if (CollectionUtils.isEmpty(noneEstBbvos)) {
      return sItemLst;
    }
    for (int i = 0, len = noneEstBbvos.size(); i < len; i++) {
      CostfactorViewVO cfvo = noneEstBbvos.get(i).getCostfactorvo();
      // 只有进成本的费用和折扣才加入传存货
      if (null != cfvo && UFBoolean.FALSE.equals(cfvo.getBentercost())) {
        continue;
      }
      UFDouble allotmoney = noneEstBbvos.get(i).getNallotmoney();
      SettleBillItemVO copyItem =
          this.getToPCIAStlItem(item, allotmoney, noneEstBbvos.get(i));
      if (null == copyItem) {
        continue;
      }
      sItemLst.add(copyItem);
    }
    return sItemLst;
  }

  protected boolean goodsHavenToIA(SettleBillItemVO toIAStlItem,
      Map<String, SettleFeeAllotDetailVO> stlAllotMap) {
    String pk_allot = toIAStlItem.getPk_settle_feeallot();
    SettleFeeAllotDetailVO stlAllotVo = stlAllotMap.get(pk_allot);
    // 如果分摊依据的ID是本次结算的ID，说明以前未传存货，则返回false
    if (toIAStlItem.getPk_settlebill().equals(stlAllotVo.getPk_allotbillid())) {
      return false;
    }
    return true;
  }

  /**
   * mengjian 采购不做处理，进口结算时需处理进口调整货物行
   * 
   * @param toI9Datas
   */
  protected void processToP4639SettleItem(List<SettleBillItemVO> toI9Datas) {
    // 采购不做处理，进口结算时需处理进口调整货物行
  }

  /**
   * 传单据到PCIA
   * 
   * @param toP4639Datas
   * @param allAllotVOLst
   */
  protected void submitToPCIA(List<SettleBillItemVO> toP4639Datas,
      Set<SettleFeeAllotDetailVO> allAllotVOLst) {
    String srcBt = this.getSourceTypeForVOChg();
    if (toP4639Datas.size() > 0) {
      List<SettleBillItemVO> newToI9Datas =
          this.combineToP4639SettleItem(toP4639Datas, allAllotVOLst);
      SettleBillVO[] settlevos = this.getToPCIASettleVO(newToI9Datas);
      SettleBillItemVOUtil.clearCenterPurInfo(settlevos, this.ssVOMap);
      P4639BillVO[] p4639s =
          PfServiceScmUtil.executeVOChange(srcBt, PCIABillType.RKTZ.getCode(),
              settlevos);
      // 设置调整对象和来源BID等信息
      this.setAdjustSrcInfo(p4639s, allAllotVOLst);
      // 传入库单调整单
      // mengjian by 20141021 启用利润中心存货核算时
      if (SysInitGroupQuery.isPCIAEnabled()) {
        PCIAServices.insertI9ForPOFeeSettle(p4639s);
      }
    }
  }

}
