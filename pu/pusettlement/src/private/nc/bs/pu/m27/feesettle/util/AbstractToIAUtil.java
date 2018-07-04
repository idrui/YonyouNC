package nc.bs.pu.m27.feesettle.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.pu.reference.ia.M27IAServices;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.ia.mi9.entity.I9BillVO;
import nc.vo.ia.mi9.entity.I9ItemVO;
import nc.vo.ia.mig.entity.IGBillVO;
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
import nc.vo.scmpub.res.billtype.IABillType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>费用结算传IA的公共抽象类，提供公共方法
 * </ul>
 * 最新需求描述：（2011.5.31wangyf）<br>
 * 费用暂估、结算生成单据的处理<br>
 * 1. 费用暂估时生成入库调整单，即暂估时进入物料成本。<br>
 * 2. 费用结算是影响物料成本（形成入库调整单）还是进入费用（形成损益调整单），和采购入库单之前是否传过成本有关。<br>
 * a) 如果作为费用分摊依据的采购入库单之前进行过暂估成本、或确认成本、或进行过货物结算，则费用和入库单进行自动结算、同物料结算、异物料结算、消耗汇总结算（
 * 即货物和费用同时结算）、或费用结算（含消耗汇总费用结算）时，如果是单到回冲，形成红字暂估回冲单、蓝字入库调整单；如果是单到补差，
 * 则根据参数差异转入方式形成入库调整单或损益调整单。<br>
 * b) 如果作为费用分摊依据的采购入库单之前未传过存货核算，则费用和入库单进行自动结算、同物料结算、异物料结算、消耗汇总结算（即货物和费用同时结算）时，
 * 费用直接生成入库调整单，即直接进入成本、和参数差异转入方式无关。
 * <p>
 * 
 * @since 6.0
 * @author hanbin
 * @time 2010-9-10 上午09:10:32
 */
abstract public class AbstractToIAUtil {

  protected SettleBillHeaderVO header;

  protected Collection<SettleBillItemVO> items;

  protected Map<String, StockSettleVO> ssVOMap;

  public AbstractToIAUtil(SettleBillHeaderVO header,
      Collection<SettleBillItemVO> items) {
    this.header = header;
    this.items = items;
  }

  // protected List<SettleBillItemVO> getDirectToI9Data(SettleBillItemVO item,
  // List<SettleFeeAllotDetailVO> noneEstBbvos) {
  // return this.getDirectToIAStlItem(item, noneEstBbvos);
  // }

  // protected List<SettleBillItemVO> getDirectToIGData(SettleBillItemVO item,
  // List<SettleFeeAllotDetailVO> noneEstBbvos) {
  // List<SettleBillItemVO> sItemLst =
  // this.getDirectToIAStlItem(item, noneEstBbvos);
  // Map<String, SettleFeeAllotDetailVO> allotMap =
  // this.getStlAllotMap(noneEstBbvos);
  // for (SettleBillItemVO sItem : sItemLst) {
  // String pk_allot = sItem.getPk_settle_feeallot();
  // // 将bid恢复为真正的结算单BID，后期传存货前会根据BID（也是IG的来源BID）合并
  // sItem.setPk_settlebill_b(allotMap.get(pk_allot).getPk_settlebill_b());
  // }
  // return sItemLst;
  // }

  public void toIA(FeeSettleDataContext datactx) {
    if (this.items == null || this.items.size() == 0 || datactx == null) {
      return;
    }
    this.ssVOMap = datactx.getStockSettleVOMap();
    List<SettleBillItemVO> toI9Datas = new ArrayList<SettleBillItemVO>();
    List<SettleBillItemVO> toIGDatas = new ArrayList<SettleBillItemVO>();
    Set<SettleFeeAllotDetailVO> allAllotVOLst =
        new HashSet<SettleFeeAllotDetailVO>();
    // String po13 = this.getParaPO13(); // PO13(差异转入方式)
    for (SettleBillItemVO item : this.items) {
      // 其他入不存在费用暂估
      String stockbid = item.getPk_stock_b();
      List<SettleFeeAllotDetailVO> noneEstBbvos =
          this.getFeeAllotDetailVO(datactx).get(stockbid);
      allAllotVOLst.addAll(noneEstBbvos);
      this.genToIAAdjBillStlItems(item, toI9Datas, toIGDatas, noneEstBbvos,
          false);
      // if (po13.equals(PUParaValue.PO14_Cost)) {
      // toI9Datas.addAll(this.getDirectToI9Data(item, noneEstBbvos)); // 成本
      // }
      // else {
      // toIGDatas.addAll(this.getDirectToIGData(item, noneEstBbvos)); // 损益
      // }
    }
    // 传单据到IA
    this.submitToIA(toI9Datas, toIGDatas, allAllotVOLst);
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

  private List<SettleBillItemVO> combineToI9SettleItem(
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

  private List<SettleBillItemVO> combineToIGSettleItem(
      List<SettleBillItemVO> orgItemLst) {
    List<SettleBillItemVO> newItemLst = new ArrayList<SettleBillItemVO>();
    Set<String> processedItem = new HashSet<String>();
    for (int i = 0; i < orgItemLst.size(); ++i) {
      SettleBillItemVO orgItem = orgItemLst.get(i);
      // 此处的BID是真正的BID
      String pk_stl_b = orgItem.getPk_settlebill_b();
      if (processedItem.contains(pk_stl_b)) {
        continue;
      }
      processedItem.add(pk_stl_b);
      // 找到BID相同的行进行合并
      for (int j = i + 1; j < orgItemLst.size(); ++j) {
        SettleBillItemVO nextOrgItem = orgItemLst.get(j);
        String pk_stl_b_next = nextOrgItem.getPk_settlebill_b();
        if (!pk_stl_b.equals(pk_stl_b_next)) {
          continue;
        }
        this.combineSettleItem(orgItem, nextOrgItem);
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

  private void setAdjustSrcInfo(I9BillVO[] i9s,
      Collection<SettleFeeAllotDetailVO> allAllotVOLst) {
    Map<String, SettleFeeAllotDetailVO> allotMap =
        this.getStlAllotMap(allAllotVOLst);
    // Map<String, String> i9SrcBIdMap = new HashMap<String, String>();
    for (I9BillVO i9 : i9s) {
      for (I9ItemVO item : i9.getChildrenVO()) {
        String pk_allot = item.getCsrcbid();
        SettleFeeAllotDetailVO allotVo = allotMap.get(pk_allot);
        item.setCadjustbillid(allotVo.getPk_allotbillid());
        item.setCadjustbillitemid(allotVo.getPk_allotbillbid());
        UFDouble adjnum = allotVo.getNallotbillnum();
        item.setNadjustnum(adjnum);
        // String pk_i9srcbid = pk_allot;
        // if (i9SrcBIdMap.containsKey(allotVo.getPk_allotbillbid())) {
        // pk_i9srcbid = i9SrcBIdMap.get(allotVo.getPk_allotbillbid());
        // }
        // // 分摊依据BID相同的行来源相同（以便后面合并），取第一个结算分摊明细VO的ID
        // i9SrcBIdMap.put(allotVo.getPk_allotbillbid(), pk_i9srcbid);
        // item.setCsrcbid(pk_i9srcbid);
      }
    }
  }

  protected SettleBillItemVO adjustForIGStlItem(SettleBillItemVO toIAStlItem,
      SettleFeeAllotDetailVO stlAllotVo) {
    // 将bid恢复为真正的结算单BID，后期传存货前会根据BID（也是IG的来源BID）合并
    toIAStlItem.setPk_settlebill_b(stlAllotVo.getPk_settlebill_b());
    return toIAStlItem;
  }

  /**
   * 将原始的结算行，按分摊明细拆分<br>
   * 并根据参数及分摊依据的货物结算和暂估情况来生成到I9或IG的结算新行
   * 
   * @param orgItem 原始结算行
   * @param toI9Lst 存放到I9的拆分后结算行
   * @param toIgLst 存放到IG的拆分后结算行
   * @param stlAllotVoLst 结算拆分明细
   * @param forceToCost 是否强制进成本走I9
   */
  protected void genToIAAdjBillStlItems(SettleBillItemVO orgItem,
      List<SettleBillItemVO> toI9Lst, List<SettleBillItemVO> toIgLst,
      List<SettleFeeAllotDetailVO> stlAllotVoLst, boolean forceToCost) {
    List<SettleBillItemVO> allToLst =
        this.getToIAStlItemsByAllot(orgItem, stlAllotVoLst);
    Map<String, SettleFeeAllotDetailVO> stlAllotMap =
        this.getStlAllotMap(stlAllotVoLst);
    for (SettleBillItemVO toIAStlItem : allToLst) {
      // 根据需求（见此类的描述），如果费用结算对应的分摊货物已经传过成本，
      // 调差方式为补差，则走损益，forceToCost代表暂估过的费用回冲时，强制走成本
      if (!forceToCost
          && PUParaValue.po13.profit_loss.equals(this.getParaPO13())
          && this.goodsHavenToIA(toIAStlItem, stlAllotMap)) {
        toIgLst.add(this.adjustForIGStlItem(toIAStlItem,
            stlAllotMap.get(toIAStlItem.getPk_settle_feeallot()))); // 对IG进行一些处理
      }
      else {
        toI9Lst.add(toIAStlItem);
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
  protected SettleBillVO[] getToIASettleVO(List<SettleBillItemVO> sItemLst) {
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
  protected SettleBillItemVO getToIAStlItem(SettleBillItemVO origItem,
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
//    copyItem.setPk_supplier(allotVo.getPk_supplier()); //按照V65需求，取入库单供应商

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

  protected List<SettleBillItemVO> getToIAStlItemsByAllot(
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
          this.getToIAStlItem(item, allotmoney, noneEstBbvos.get(i));
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
  protected void processToI9SettleItem(List<SettleBillItemVO> toI9Datas) {
    // 采购不做处理，进口结算时需处理进口调整货物行
  }

  /**
   * mengjian 采购不做处理，进口结算时需处理进口调整货物行
   * 
   * @param toIGDatas
   */
  protected void processToIGSettleItem(List<SettleBillItemVO> toIGDatas) {
    // 采购不做处理，进口结算时需处理进口调整货物行
  }
  
  /**
   * mengjian by 20150122
   * 过滤掉金额为零或空的调整行
   * 
   * @param settlevos
   * @return
   */
  private List<SettleBillVO> filterVo(SettleBillVO[] settlevos) {
    List<SettleBillVO> aggvolist = new ArrayList<SettleBillVO>();
    for (SettleBillVO settVO : settlevos) {
      SettleBillItemVO[] childrenVO = settVO.getChildrenVO();
      SettleBillHeaderVO hvo = settVO.getParentVO();
      List<SettleBillItemVO> itemvolist = new ArrayList<SettleBillItemVO>();
      for (SettleBillItemVO itemVO : childrenVO) {
        UFDouble nmoney = itemVO.getNmoney();
        if (nmoney != null && !nmoney.equals(UFDouble.ZERO_DBL)){
          itemvolist.add(itemVO);
        }
      }
      if (itemvolist.size() > 0) {
        SettleBillVO vo = new SettleBillVO();
        vo.setParent(hvo);
        vo.setChildrenVO(itemvolist.toArray(new SettleBillItemVO[0]));
        aggvolist.add(vo);
      }
    }
    return aggvolist;
  }

  protected void submitToIA(List<SettleBillItemVO> toI9Datas,
      List<SettleBillItemVO> toIGDatas,
      Collection<SettleFeeAllotDetailVO> allAllotVOLst) {
    String srcBt = this.getSourceTypeForVOChg();
    if (toI9Datas.size() > 0) {
      List<SettleBillItemVO> newToI9Datas =
          this.combineToI9SettleItem(toI9Datas, allAllotVOLst);
      // mengjian 处理进口调整货物结算单行
      // this.processToI9SettleItem(toI9Datas);
      SettleBillVO[] settlevos = this.getToIASettleVO(newToI9Datas);
      SettleBillItemVOUtil.clearCenterPurInfo(settlevos, this.ssVOMap);
   // mengjian by 20150122 过滤掉金额为零或空的调整行
      List<SettleBillVO> aggvolist = this.filterVo(settlevos);
      if(aggvolist.size()>0){
      	I9BillVO[] i9s =
            PfServiceScmUtil.executeVOChange(srcBt, IABillType.RKTZ.getCode(),
                settlevos);
        // 设置调整对象和来源BID等信息
        this.setAdjustSrcInfo(i9s, allAllotVOLst);
        // // 按成本要素合并I9行
        // EstTOIAUtil.combineAdjustBillRow(i9s);
        // 传入库单调整单
        M27IAServices.insertI9ForPOFeeSettle(i9s);
      }   
    }
    if (toIGDatas.size() > 0) {
      List<SettleBillItemVO> newToIGDatas =
          this.combineToIGSettleItem(toIGDatas);
      // mengjian 处理进口调整货物结算单行
      // this.processToIGSettleItem(toIGDatas);
      SettleBillVO[] settlevos = this.getToIASettleVO(newToIGDatas);
      SettleBillItemVOUtil.clearCenterPurInfo(settlevos, this.ssVOMap);
      IGBillVO[] igs =
          PfServiceScmUtil.executeVOChange(srcBt, IABillType.SYTZ.getCode(),
              settlevos);
      // 传损益
      M27IAServices.insertIGForPOFeeSettle(igs);
    }
  }
}
