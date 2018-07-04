package nc.bs.pu.m27.feesettle.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.pu.reference.ia.M27IAServices;
import nc.pubitf.ia.mi9.po.I9AdjustBackData;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购入库单进行费用结算时传存货核算
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-12 下午01:53:24
 */
public class M45FeeSettleToIAUtil extends AbstractToIAUtil {

  public M45FeeSettleToIAUtil(SettleBillHeaderVO header,
      Collection<SettleBillItemVO> items) {
    super(header, items);
  }

  @Override
  public void toIA(FeeSettleDataContext datactx) {
    if (this.items == null || this.items.size() == 0 || datactx == null) {
      return;
    }
    this.ssVOMap = datactx.getStockSettleVOMap();
    List<I9AdjustBackData> rushDatas = new ArrayList<I9AdjustBackData>();
    List<SettleBillItemVO> toI9Datas = new ArrayList<SettleBillItemVO>();
    List<SettleBillItemVO> toIGDatas = new ArrayList<SettleBillItemVO>();
    Set<SettleFeeAllotDetailVO> allAllotVOLst =
        new HashSet<SettleFeeAllotDetailVO>();

    PUParaValue.po12 po12 = this.getParaPO12();// 得到暂估调整方式
    // String po13 = this.getParaPO13(); // 参数PO13(差异转入方式)

    FeeSettleEstMatchUtil matchUtil = new FeeSettleEstMatchUtil(datactx);
    for (SettleBillItemVO item : this.items) {
      matchUtil.match(item);
      // 存在费用暂估
      List<FeeSettleEstMapping> mappings = matchUtil.getFeeSettleEstMappings();
      if (mappings != null && mappings.size() != 0) {
        // 如果是回冲，则按暂估数据准备回冲数据，生成回冲单、正式存货入库单，即暂估回冲传成本I9。
        if (po12.equals(PUParaValue.po12.rush)) {
          rushDatas.addAll(this.getRushToI9Data(item, mappings));
          this.genToIAAdjBillStlItems(item, toI9Datas, toIGDatas,
              this.getSettleFeeAllot(mappings), true);
          // if (po13.equals(PUParaValue.PO14_Cost)) {
          // toI9Datas.addAll(this.getEstDirectToI9Data(item, mappings));
          // }
          // else {
          // toIGDatas.addAll(this.getEstDirectToIGData(item, mappings));
          // }
        }
        else {
          // 费用暂估后进行调差
          this.genEstToIAAdjBillStlItems(item, toI9Datas, toIGDatas, mappings);
        }
        allAllotVOLst.addAll(this.getSettleFeeAllot(mappings));
      }
      // 不存在费用暂估
      List<SettleFeeAllotDetailVO> noneEstBbvos = matchUtil.getNoneEstBbvos();
      if (noneEstBbvos != null && noneEstBbvos.size() != 0) {
        // if (po13.equals(PUParaValue.PO14_Cost)) {
        // // 成本
        // toI9Datas.addAll(this.getDirectToI9Data(item, noneEstBbvos));
        // }
        // else {
        // // 损益
        // toIGDatas.addAll(this.getDirectToIGData(item, noneEstBbvos));
        // }
        this.genToIAAdjBillStlItems(item, toI9Datas, toIGDatas, noneEstBbvos,
            false);
        allAllotVOLst.addAll(noneEstBbvos);
      }
    }
    // 费用暂估回冲
    I9AdjustBackData[] torushdata =
        rushDatas.toArray(new I9AdjustBackData[rushDatas.size()]);
    M27IAServices.insertI9ForPOFeeSettleAdjustBack(torushdata);
    // 将I9、IG数据传存货
    this.submitToIA(toI9Datas, toIGDatas, allAllotVOLst);
  }

  private List<I9AdjustBackData> combineI9AdjustData(
      List<I9AdjustBackData> i9adjdataLst) {
    Map<String, I9AdjustBackData> srcbAdjMap =
        new HashMap<String, I9AdjustBackData>();
    for (I9AdjustBackData adjData : i9adjdataLst) {
      // 暂估时传I9的行ID进行合并，保证不产生过多的单据
      String srcbid = adjData.getCapportionid();
      I9AdjustBackData sumAdjData = srcbAdjMap.get(srcbid);
      if (null != sumAdjData) {
        UFDouble mny = sumAdjData.getNmny();
        sumAdjData.setNmny(MathTool.add(mny, adjData.getNmny()));
        UFDouble factor = sumAdjData.getNfactor1();
        sumAdjData.setNfactor1(MathTool.add(factor, adjData.getNfactor1()));
        factor = sumAdjData.getNfactor2();
        sumAdjData.setNfactor2(MathTool.add(factor, adjData.getNfactor2()));
        factor = sumAdjData.getNfactor3();
        sumAdjData.setNfactor3(MathTool.add(factor, adjData.getNfactor3()));
        factor = sumAdjData.getNfactor4();
        sumAdjData.setNfactor4(MathTool.add(factor, adjData.getNfactor4()));
        factor = sumAdjData.getNfactor5();
        sumAdjData.setNfactor5(MathTool.add(factor, adjData.getNfactor5()));
        factor = sumAdjData.getNfactor6();
        sumAdjData.setNfactor6(MathTool.add(factor, adjData.getNfactor6()));
        factor = sumAdjData.getNfactor7();
        sumAdjData.setNfactor7(MathTool.add(factor, adjData.getNfactor7()));
        factor = sumAdjData.getNfactor8();
        sumAdjData.setNfactor8(MathTool.add(factor, adjData.getNfactor8()));
      }
      else {
        srcbAdjMap.put(srcbid, adjData);
      }
    }
    return new ArrayList<I9AdjustBackData>(srcbAdjMap.values());
  }

  private void genEstToIAAdjBillStlItems(SettleBillItemVO item,
      List<SettleBillItemVO> toI9Lst, List<SettleBillItemVO> toIgLst,
      List<FeeSettleEstMapping> mappings) {
    // 存在费用暂估时，进行调差处理
    if (item == null || mappings == null || mappings.size() == 0) {
      return;
    }
    for (int i = 0, len = mappings.size(); i < len; i++) {
      if (!UFBoolean.TRUE.equals(mappings.get(i).getFeeestvo().getBtoia())) {
        continue;
      }
      // 调整金额 = 费用暂估金额-费用结算金额
      UFDouble estmny = mappings.get(i).getEstallotvo().getNdistmny();
      SettleFeeAllotDetailVO settleallotvo = mappings.get(i).getSettleallotvo();
      UFDouble settlemny = settleallotvo.getNallotmoney();
      SettleBillItemVO newItem =
          this.getToIAStlItem(item, MathTool.sub(settlemny, estmny),
              settleallotvo);
      if (null == newItem) {
        continue;
      }
      if (PUParaValue.po13.cost.equals(this.getParaPO13())) {
        toI9Lst.add(newItem);
      }
      else {
        toIgLst.add(this.adjustForIGStlItem(newItem, settleallotvo));
      }
    }
  }

  private PUParaValue.po12 getParaPO12() {
    // 参数PO12(暂估处理方式)
    String org = this.header.getPk_org();
    if (StringUtils.isEmpty(org)) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0068")/* @res "入库单/消耗汇总进行费用结算取参数时，出现主组织为空错误！" */;
      ExceptionUtils.wrappBusinessException(msg);
      return null;
    }
    return PUSysParamUtil.getPO12(org);
  }

  // private List<SettleBillItemVO> getEstDirectToI9Data(SettleBillItemVO item,
  // List<FeeSettleEstMapping> mappings) {
  // // 存在费用暂估时，回冲后进行的传I9
  // if (item == null || mappings == null || mappings.size() == 0) {
  // return null;
  // }
  // // 得到结算费用分摊数据
  // List<SettleFeeAllotDetailVO> bbs = this.getSettleFeeAllot(mappings);
  // return this.getDirectToI9Data(item, bbs);
  // }

  // private List<SettleBillItemVO> getEstDirectToIGData(SettleBillItemVO item,
  // List<FeeSettleEstMapping> mappings) {
  // // 没有进行过费用暂估，费用结算时直接传IG
  // if (item == null || mappings == null || mappings.size() == 0) {
  // return null;
  // }
  // // 得到结算费用分摊数据
  // List<SettleFeeAllotDetailVO> bbs = this.getSettleFeeAllot(mappings);
  // return this.getDirectToIGData(item, bbs);
  // }

  private List<I9AdjustBackData> getRushToI9Data(SettleBillItemVO item,
      List<FeeSettleEstMapping> mappings) {
    // 存在费用暂估，需要进行回冲费用暂估时使用
    if (item == null || mappings == null || mappings.size() == 0) {
      return null;
    }
    // 使用I9的调整vo类，基于费用分摊明细进行回冲
    List<I9AdjustBackData> i9bills = new ArrayList<I9AdjustBackData>();
    for (FeeSettleEstMapping mapping : mappings) {
      if (!UFBoolean.TRUE.equals(mapping.getFeeestvo().getBtoia())) {
        continue;
      }
      SettleFeeAllotDetailVO settleallotvo = mapping.getSettleallotvo();
      FeeEstDistVO estallotvo = mapping.getEstallotvo();
      I9AdjustBackData voRush = new I9AdjustBackData();
      voRush.setVsettlecode(this.header.getVbillcode());
      voRush.setCsrcid(item.getPk_settlebill());
      voRush.setCsrcbid(settleallotvo.getPk_settle_feeallot());
      voRush.setVsettlerowno(item.getCrowno());
      voRush.setCicid(item.getPk_stock());// 暂估费用时的I9来源ID（即采购入ID）
      voRush.setCapportionid(estallotvo.getPk_iasrc_b());// 暂估费用时的I9来源行ID
      voRush.setNmny(estallotvo.getNdistmny());// 费用分摊金额
      // by 20141209 mengjian 取不到发票日期，取业务日期
      UFDate date = item.getInvoicebilldate();
      if(date == null){
        date = AppContext.getInstance().getBusiDate();
      }
      voRush.setDaccountdate(date);

      CostfactorViewVO cfVo = settleallotvo.getCostfactorvo();
      Integer cfNo = cfVo.getIfactororder();
      if (Integer.valueOf(1).equals(cfNo)) {
        voRush.setNfactor1(estallotvo.getNdistmny());
      }
      else if (Integer.valueOf(2).equals(cfNo)) {
        voRush.setNfactor2(estallotvo.getNdistmny());
      }
      else if (Integer.valueOf(3).equals(cfNo)) {
        voRush.setNfactor3(estallotvo.getNdistmny());
      }
      else if (Integer.valueOf(4).equals(cfNo)) {
        voRush.setNfactor4(estallotvo.getNdistmny());
      }
      else if (Integer.valueOf(5).equals(cfNo)) {
        voRush.setNfactor5(estallotvo.getNdistmny());
      }
      else if (Integer.valueOf(6).equals(cfNo)) {
        voRush.setNfactor6(estallotvo.getNdistmny());
      }
      else if (Integer.valueOf(7).equals(cfNo)) {
        voRush.setNfactor7(estallotvo.getNdistmny());
      }
      else if (Integer.valueOf(8).equals(cfNo)) {
        voRush.setNfactor8(estallotvo.getNdistmny());
      }
      i9bills.add(voRush);
    }
    // 根据暂估时传I9的行ID进行合并，保证不产生过多的回冲单据
    i9bills = this.combineI9AdjustData(i9bills);
    return i9bills;
  }

  private List<SettleFeeAllotDetailVO> getSettleFeeAllot(
      List<FeeSettleEstMapping> mappings) {
    List<SettleFeeAllotDetailVO> bbs = new ArrayList<SettleFeeAllotDetailVO>();
    for (FeeSettleEstMapping mapping : mappings) {
      SettleFeeAllotDetailVO settleallotvo = mapping.getSettleallotvo();
      bbs.add(settleallotvo);
    }
    return bbs;
  }

  @Override
  protected String getSourceTypeForVOChg() {
    return POBillType.PurchaseInSettleBill.getCode();
  }
}
