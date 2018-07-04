package nc.vo.pu.m21.rule.margin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.ct.purdaily.entity.CtPuBVO;
import nc.vo.et.m5720.entity.ContractBVO;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m49.entity.BorrowInBodyVO;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.mmpac.dmo.entity.DmoVO;
import nc.vo.mmpac.pmo.pac0002.entity.PMOItemVO;
import nc.vo.mmpps.mps0202.PoVO;
import nc.vo.pp.m28.entity.PriceAuditItemVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.margin.AbstractPuMarginProcessFactory;
import nc.vo.pu.margin.DefaultPuMarginCondition;
import nc.vo.pu.margin.DefaultPuMarginProcess;
import nc.vo.pu.margin.DefaultPuMarginVOProc;
import nc.vo.pu.margin.IPuMarginCondition;
import nc.vo.pu.margin.IPuMarginProcess;
import nc.vo.pu.margin.IPuMarginVOProcess;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.scmpub.res.billtype.ECBillType;
import nc.vo.scmpub.res.billtype.ETBillType;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.INVPBillType;
import nc.vo.scmpub.res.billtype.MMBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.PPBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.to.m5x.entity.BillItemVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 采购订单尾差处理器生成工厂
 * 
 * @since 6.0
 * @version 2012-4-23 下午1:50:54
 * @author zhaoyha
 */
public class OrderMarginProcessFactory extends AbstractPuMarginProcessFactory {

  /** 通用的倒挤条件，通过JIT方式加载，避免多次实例化容器 */
  private List<IPuMarginCondition> marginConds = null;

  public OrderMarginProcessFactory(String srcBilltype,
      AggregatedValueObject[] curBillVos, AggregatedValueObject[] srcBillVos) {
    super(srcBilltype, curBillVos, srcBillVos);
  }

  private List<IPuMarginProcess> createMarginProcess(boolean isFirstTime) {
    // 设置当前要倒挤的行VO
    this.setCurItemVos((SuperVO[]) AggVOUtil.getCombinItemVOs(this
        .getCurBillVos()));
    List<IPuMarginProcess> list = new ArrayList<IPuMarginProcess>();
    if (POBillType.PrayBill.getCode().equals(this.getSrcBilltype())) {// 与请购单倒挤
      this.setSrcItemVoBySrcBillBody();
      list.addAll(this.createSrcNumProcessor(isFirstTime, PraybillItemVO.class,
          PraybillItemVO.PK_PRAYBILL_B, OrderItemVO.NNUM,
          this.getMarginConditions(OrderItemVO.NNUM)));
    }
    else if (MMBillType.PlanOrder.getCode().equals(this.getSrcBilltype())) {// 与生产计划订单倒挤
      this.setSrcItemVoBySrcBillHead();
      list.addAll(this.createSrc55B4Processor(isFirstTime, PoVO.class,
          PoVO.CPOID, PoVO.NNUMBER));
    }
    else if (MMBillType.ProduceOrder.getCode().equals(this.getSrcBilltype())) {// 与生产订单倒挤
      this.setSrcItemVoBySrcBillHead();
      list.addAll(this.createSrc55A2Processor(isFirstTime, PMOItemVO.class,
          PMOItemVO.CMOID, PMOItemVO.NNUM));
    }
    else if (MMBillType.LsProduceOrder.getCode().equals(this.getSrcBilltype())) {// 与离散生产订单倒挤
      this.setSrcItemVoBySrcBillHead();
      // fanly3 20121114 适配制作新代码
      list.addAll(this.createSrc55C2Processor(isFirstTime, DmoVO.class,
          DmoVO.PK_DMO, DmoVO.NNUM));
    }

    else if (INVPBillType.PoOrder.getCode().equals(this.getSrcBilltype())) {// 与库存计划计划订单倒挤
      this.setSrcItemVoBySrcBillHead();
      list.addAll(this.createSrcNumProcessor(isFirstTime,
          nc.vo.invp.po.entity.PoVO.class, nc.vo.invp.po.entity.PoVO.PK_PO,
          OrderItemVO.NNUM, this.getMarginConditions(OrderItemVO.NNUM)));
    }
    else if (POBillType.Arrive.getCode().equals(this.getSrcBilltype())) {// 与退货单倒挤
                                                                         // tianft考虑正负倒挤
      this.setSrcItemVoBySrcBillBody();
      list.addAll(this.createSrcNumProcessor(isFirstTime, ArriveItemVO.class,
          ArriveItemVO.PK_ARRIVEORDER_B, OrderItemVO.NNUM,
          this.getMarginConditions(OrderItemVO.NNUM)));
    }
    else if (SOBillType.Order.getCode().equals(this.getSrcBilltype())) {// 与销售订单倒挤
      this.setSrcItemVoBySrcBillBody();
      list.addAll(this.createSrcNumProcessor(isFirstTime, SaleOrderBVO.class,
          SaleOrderBVO.CSALEORDERBID, OrderItemVO.NNUM,
          this.getMarginConditions(OrderItemVO.NNUM)));
    }
    else if (TOBillType.TransOrder.getCode().equals(this.getSrcBilltype())) {// 与调拨订单倒挤
      this.setSrcItemVoBySrcBillBody();
      list.addAll(this.createSrcNumProcessor(isFirstTime, BillItemVO.class,
          BillItemVO.CBILL_BID, OrderItemVO.NNUM,
          this.getMarginConditions(OrderItemVO.NNUM)));
    }
    else if (ICBillType.BorrowIn.getCode().equals(this.getSrcBilltype())) {// 与库存借入单倒挤
      this.setSrcItemVoBySrcBillBody();
      list.addAll(this.createSrcICProcessor(isFirstTime, BorrowInBodyVO.class,
          MetaNameConst.CGENERALBID, OrderItemVO.NNUM));
    }
    else if (ICBillType.PurchaseIn.getCode().equals(this.getSrcBilltype())) {// 与库存退库单倒挤
                                                                             // tianft考虑正负倒挤
      this.setSrcItemVoBySrcBillBody();
      list.addAll(this.createSrcICProcessor(isFirstTime,
          PurchaseInBodyVO.class, MetaNameConst.CGENERALBID, OrderItemVO.NNUM));
    }
    else if (CTBillType.PurDaily.getCode().equals(this.getSrcBilltype())) {// 与采购合同倒挤
      this.setSrcItemVoBySrcBillBody();
      list.addAll(this.createSrcNumProcessor(isFirstTime, CtPuBVO.class,
          CtPuBVO.PK_CT_PU_B, OrderItemVO.NNUM,
          this.getMarginConditions(OrderItemVO.NNUM)));
    }
    else if (PPBillType.PriceAudit.getCode().equals(this.getSrcBilltype())) {// 与价格审批单倒挤
      this.setSrcItemVoBySrcBillBody();
      list.addAll(this.createSrcNumProcessor(isFirstTime,
          PriceAuditItemVO.class, PriceAuditItemVO.PK_PRICEAUDIT_B,
          OrderItemVO.NNUM, this.getMarginConditions(OrderItemVO.NNUM)));
    }
    else if (ECBillType.EC47.getCode().equals(this.getSrcBilltype())) {// 与电子商务分标分量倒挤
      this.setSrcItemVoBySrcBillBody();
    }
    else if (ETBillType.CONTRACT.getCode().equals(this.getSrcBilltype())) {// 与出口合同倒挤
      this.setSrcItemVoBySrcBillBody();
      list.addAll(this.createSrcNumProcessor(isFirstTime, ContractBVO.class,
          ContractBVO.PK_CONTRACT_B, OrderItemVO.NNUM,
          this.getMarginConditions(OrderItemVO.NNUM)));
    }
    return list;
  }

  /** 为生产订单创建尾差处理器 */
  private List<IPuMarginProcess> createSrc55A2Processor(boolean isFirstTime,
      Class srcBillClass, String sourceBid, String sourcNum) {
    // 判断数量能否倒挤的字段。
    Map<String, String> astJudgeFields = new HashMap<String, String>();
    astJudgeFields.put(OrderItemVO.CASTUNITID, PMOItemVO.CASTUNITID);
    // 数量的字段。
    Map<String, String> astMarginFields = new HashMap<String, String>();
    astMarginFields.put(OrderItemVO.NASTNUM, PMOItemVO.NASTNUM);
    // 判断报价数量能否倒挤的字段。
    Map<String, String> qtJudgeFields = new HashMap<String, String>();
    qtJudgeFields.put(OrderItemVO.CQTUNITID, PMOItemVO.CASTUNITID);
    // 报价数量字段。
    Map<String, String> qtMarginFields = new HashMap<String, String>();
    qtMarginFields.put(OrderItemVO.NQTUNITNUM, PMOItemVO.NASTNUM);

    IPuMarginCondition astNumCond =
        new DefaultPuMarginCondition(astJudgeFields, astMarginFields, sourcNum);
    IPuMarginCondition qtNumCond =
        new DefaultPuMarginCondition(qtJudgeFields, qtMarginFields, sourcNum);
    List<IPuMarginCondition> marginCondsFor55A2 =
        new ArrayList<IPuMarginCondition>();
    marginCondsFor55A2.add(astNumCond);
    marginCondsFor55A2.add(qtNumCond);

    return this.createSrcNumProcessor(isFirstTime, srcBillClass, sourceBid,
        sourcNum, marginCondsFor55A2);
  }

  /** 为计划订单创建尾差处理器 */
  private List<IPuMarginProcess> createSrc55B4Processor(boolean isFirstTime,
      Class srcBillClass, String sourceBid, String sourcNum) {
    // 判断数量能否倒挤的字段。
    Map<String, String> astJudgeFields = new HashMap<String, String>();
    astJudgeFields.put(OrderItemVO.CASTUNITID, PoVO.CASSMEASUREID);
    // 数量的字段。
    Map<String, String> astMarginFields = new HashMap<String, String>();
    astMarginFields.put(OrderItemVO.NASTNUM, PoVO.NASTNUM);
    // 判断报价数量能否倒挤的字段。
    Map<String, String> qtJudgeFields = new HashMap<String, String>();
    qtJudgeFields.put(OrderItemVO.CQTUNITID, PoVO.CASSMEASUREID);
    // 报价数量字段。
    Map<String, String> qtMarginFields = new HashMap<String, String>();
    qtMarginFields.put(OrderItemVO.NQTUNITNUM, PoVO.NASTNUM);

    IPuMarginCondition astNumCond =
        new DefaultPuMarginCondition(astJudgeFields, astMarginFields, sourcNum);
    IPuMarginCondition qtNumCond =
        new DefaultPuMarginCondition(qtJudgeFields, qtMarginFields, sourcNum);
    List<IPuMarginCondition> marginCondsFor55B4 =
        new ArrayList<IPuMarginCondition>();
    marginCondsFor55B4.add(astNumCond);
    marginCondsFor55B4.add(qtNumCond);

    return this.createSrcNumProcessor(isFirstTime, srcBillClass, sourceBid,
        sourcNum, marginCondsFor55B4);
  }

  /** 为离散生产订单创建尾差处理器 */
  private List<IPuMarginProcess> createSrc55C2Processor(boolean isFirstTime,
      Class srcBillClass, String sourceBid, String sourcNum) {

    // 判断数量能否倒挤的字段。
    Map<String, String> astJudgeFields = new HashMap<String, String>();
    astJudgeFields.put(OrderItemVO.CASTUNITID, DmoVO.CASTUNITID);
    // 数量的字段。
    Map<String, String> astMarginFields = new HashMap<String, String>();
    astMarginFields.put(OrderItemVO.NASTNUM, DmoVO.NASTNUM);
    // 判断报价数量能否倒挤的字段。
    Map<String, String> qtJudgeFields = new HashMap<String, String>();
    qtJudgeFields.put(OrderItemVO.CQTUNITID, DmoVO.CASTUNITID);
    // 报价数量字段。
    Map<String, String> qtMarginFields = new HashMap<String, String>();
    qtMarginFields.put(OrderItemVO.NQTUNITNUM, DmoVO.NASTNUM);

    IPuMarginCondition astNumCond =
        new DefaultPuMarginCondition(astJudgeFields, astMarginFields, sourcNum);
    IPuMarginCondition qtNumCond =
        new DefaultPuMarginCondition(qtJudgeFields, qtMarginFields, sourcNum);
    List<IPuMarginCondition> marginCondsFor55C2 =
        new ArrayList<IPuMarginCondition>();
    marginCondsFor55C2.add(astNumCond);
    marginCondsFor55C2.add(qtNumCond);

    return this.createSrcNumProcessor(isFirstTime, srcBillClass, sourceBid,
        sourcNum, marginCondsFor55C2);
  }

  /** 为采购入库和库存借入创建尾差处理器 */
  private List<IPuMarginProcess> createSrcICProcessor(boolean isFirstTime,
      Class srcBillClass, String sourceBid, String sourcNum) {
    // 判断数量能否倒挤的字段。
    Map<String, String> astJudgeFields = new HashMap<String, String>();
    astJudgeFields.put(OrderItemVO.CASTUNITID, ICPubMetaNameConst.CASTUNITID);
    // 数量的字段。
    Map<String, String> astMarginFields = new HashMap<String, String>();
    astMarginFields.put(OrderItemVO.NASTNUM, ICPubMetaNameConst.NASSISTNUM);
    // 判断报价数量能否倒挤的字段。
    Map<String, String> qtJudgeFields = new HashMap<String, String>();
    qtJudgeFields.put(OrderItemVO.CQTUNITID, ICPubMetaNameConst.CASTUNITID);
    // 报价数量字段。
    Map<String, String> qtMarginFields = new HashMap<String, String>();
    qtMarginFields.put(OrderItemVO.NQTUNITNUM, ICPubMetaNameConst.NASSISTNUM);

    IPuMarginCondition astNumCond =
        new DefaultPuMarginCondition(astJudgeFields, astMarginFields, sourcNum);
    IPuMarginCondition qtNumCond =
        new DefaultPuMarginCondition(qtJudgeFields, qtMarginFields, sourcNum);
    List<IPuMarginCondition> marginCondsForIC =
        new ArrayList<IPuMarginCondition>();
    marginCondsForIC.add(astNumCond);
    marginCondsForIC.add(qtNumCond);

    return this.createSrcNumProcessor(isFirstTime, srcBillClass, sourceBid,
        sourcNum, marginCondsForIC);
  }

  /**
   * 创建通用的数量和报价数量的尾差倒挤器。
   * 
   * @param isFirstTime 只处理第一次转单。
   * @param srcBillClass 来源单据VO类
   * @param sourceBid 来源BID
   * @param sourcNum 来源主数量字段
   * @param condList 条件集合
   * @return 尾差倒挤器集合
   */
  private List<IPuMarginProcess> createSrcNumProcessor(boolean isFirstTime,
      Class srcBillClass, String sourceBid, String sourcNum,
      List<IPuMarginCondition> condList) {
    // 查询上游单据头或体VO（如果已经存在，则不查询）
    if (ArrayUtils.isEmpty(this.getSrcItemVos())) {
      this.querySrcItemVO(srcBillClass, condList, sourcNum,
          OrderItemVO.CSOURCEBID);
    }
    // 做多次转单倒挤时才需要查询下游兄弟VO
    if (!isFirstTime && ArrayUtils.isEmpty(this.getCurSiblingVos())) {
      this.queryCurSiblingVO(OrderItemVO.class, condList, OrderItemVO.NNUM,
          OrderItemVO.CSOURCEBID, OrderItemVO.CSOURCEID);
    }
    // 创建VO加工器
    IPuMarginVOProcess voproc =
        new DefaultPuMarginVOProc(OrderItemVO.CSOURCEBID,
            OrderItemVO.CSOURCEID, sourceBid);
    List<IPuMarginProcess> procList = new ArrayList<IPuMarginProcess>();
    for (int i = 0; i < condList.size(); i++) {
      procList.add(new DefaultPuMarginProcess(condList.get(i), voproc, this
          .getCurItemVos(), this.getCurSiblingVos(), this.getSrcItemVos()));
    }
    return procList;
  }

  /**
   * 通用的尾差倒挤条件。
   * 
   * @param sourcNum 主数量字段。
   * @return 通用的尾差倒挤条件集合。
   */
  private List<IPuMarginCondition> getMarginConditions(String sourcNum) {
    if (this.marginConds == null) {
      // 判断数量能否倒挤的字段。
      Map<String, String> astJudgeFields = new HashMap<String, String>();
      astJudgeFields.put(OrderItemVO.CASTUNITID, OrderItemVO.CASTUNITID);
      // 数量的字段。
      Map<String, String> astMarginFields = new HashMap<String, String>();
      astMarginFields.put(OrderItemVO.NASTNUM, OrderItemVO.NASTNUM);
      // 判断报价数量能否倒挤的字段。
      Map<String, String> qtJudgeFields = new HashMap<String, String>();
      qtJudgeFields.put(OrderItemVO.CQTUNITID, OrderItemVO.CASTUNITID);
      // 报价数量字段。
      Map<String, String> qtMarginFields = new HashMap<String, String>();
      qtMarginFields.put(OrderItemVO.NQTUNITNUM, OrderItemVO.NASTNUM);

      Map<String, String> mnyJudgeFields = new HashMap<String, String>();

      IPuMarginCondition astNumCond =
          new DefaultPuMarginCondition(astJudgeFields, astMarginFields,
              sourcNum);
      IPuMarginCondition qtNumCond =
          new DefaultPuMarginCondition(qtJudgeFields, qtMarginFields, sourcNum);
      this.marginConds = new ArrayList<IPuMarginCondition>();
      this.marginConds.add(astNumCond);
      this.marginConds.add(qtNumCond);
      
      if(SOBillType.Order.getCode().equals(this.getSrcBilltype())){
        // 判断金额能否倒挤的字段。
        mnyJudgeFields.put(OrderItemVO.NQTORIGPRICE, OrderItemVO.NQTORIGPRICE);
        mnyJudgeFields.put(OrderItemVO.CORIGCURRENCYID,
            OrderItemVO.CORIGCURRENCYID);
        Map<String, String> mnyMarginFields = new HashMap<String, String>();
        // 金额字段。
        mnyMarginFields.put(OrderItemVO.NORIGMNY, OrderItemVO.NORIGMNY);
        mnyMarginFields.put(OrderItemVO.NORIGTAXMNY, OrderItemVO.NORIGTAXMNY);
        IPuMarginCondition mnyCond =
            new DefaultPuMarginCondition(mnyJudgeFields, mnyMarginFields,
                sourcNum);
        this.marginConds.add(mnyCond);
      }
    }
    return this.marginConds;
  }

  @Override
  protected List<IPuMarginProcess> createFirstTimeMarginProcess() {
    return this.createMarginProcess(false);
  }

}
