package nc.vo.pu.m23.rule.margin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
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
import nc.vo.sc.m61.entity.SCOrderItemVO;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 到货单尾差处理器生成工厂。
 * 
 * @since 6.0
 * @version 2012-4-24 上午11:08:10
 * @author lixyp
 */
public class ArriveMarginProcessFactory extends AbstractPuMarginProcessFactory {

  public ArriveMarginProcessFactory(String srcBilltype,
      AggregatedValueObject[] curBillVos, AggregatedValueObject[] srcBillVos) {
    super(srcBilltype, curBillVos, srcBillVos);
  }

  /**
   * 对于采购订单，需要区分是否存在到货计划。
   */
  public List<List<ArriveItemVO>> filterFromOrderBorBB1() {
    ArriveItemVO[] curItems = (ArriveItemVO[]) this.getCurItemVos();
    List<ArriveItemVO> from21BItemList = new ArrayList<ArriveItemVO>();
    List<ArriveItemVO> from21BB1ItemList = new ArrayList<ArriveItemVO>();
    for (ArriveItemVO curItem : curItems) {
      if (StringUtils.isBlank(curItem.getPk_order_bb1())) {
        from21BItemList.add(curItem);
      }
      else {
        from21BB1ItemList.add(curItem);
      }
    }
    List<List<ArriveItemVO>> lists = new ArrayList<List<ArriveItemVO>>();
    lists.add(from21BItemList);
    lists.add(from21BB1ItemList);
    return lists;
  }

  /**
   * 创建尾差处理器（集合）。
   * 
   * @param isFirstTime 是否只做第一次的尾差处理?
   * @return 到货单尾差处理器集合
   */
  private List<IPuMarginProcess> createMarginProcess(boolean isFirstTime) {
    // 设置当前要倒挤的行VO
    this.setCurItemVos((SuperVO[]) AggVOUtil.getCombinItemVOs(this
        .getCurBillVos()));

    // 处理器集合，下面将根据来源单据类型分别创建尾差处理器，并放入集合，统一处理。
    List<IPuMarginProcess> list = new ArrayList<IPuMarginProcess>();
    if (POBillType.Order.getCode().equals(this.getSrcBilltype())) {// 与采购订单倒挤
      this.setSrcItemVoBySrcBillBody();
      List<List<ArriveItemVO>> fromOrderBorBB1List =
          this.filterFromOrderBorBB1();
      List<ArriveItemVO> fromOrderBList = fromOrderBorBB1List.get(0);
      if (!CollectionUtils.isEmpty(fromOrderBList)) {
        this.setCurItemVos(fromOrderBList
            .toArray(new ArriveItemVO[fromOrderBList.size()]));
        list.add(this.createSrc21B(isFirstTime));
      }
      List<ArriveItemVO> fromOrderBB1List = fromOrderBorBB1List.get(1);
      if (!CollectionUtils.isEmpty(fromOrderBB1List)) {
        this.setCurItemVos(fromOrderBB1List
            .toArray(new ArriveItemVO[fromOrderBB1List.size()]));
        list.add(this.createSrc21RecvPlan(isFirstTime));
      }
    }
    else if (SCBillType.Order.getCode().equals(this.getSrcBilltype())) {// 与委外订单倒挤
      // 委外订单的数量在表体，所以这里将委外订单的表体vo赋到srcItemVo集合中。
      this.setSrcItemVoBySrcBillBody();
      list.add(this.createSrc61(isFirstTime));
    }

    return list;
  }

  /**
   * 创建来源是采购订单（不存在到货计划）的尾差处理器。
   * 
   * @param isFirstTime
   * @return 来源是采购订单（不存在到货计划）的尾差处理器
   */
  private IPuMarginProcess createSrc21B(boolean isFirstTime) {
    // 使用pubapp的MarginContextFactory工具时，需要提供来源和当前单据的倒挤字段，所以这里使用映射将它们存入Condition。
    // 判断能否进行倒挤的字段映射。
    Map<String, String> curSrcCondJudgeField = new HashMap<String, String>();
    curSrcCondJudgeField.put(ArriveItemVO.CASTUNITID, OrderItemVO.CASTUNITID);

    // 需要进行倒挤的字段映射。
    Map<String, String> curSrcMarginField = new HashMap<String, String>();
    curSrcMarginField.put(ArriveItemVO.NASTNUM, OrderItemVO.NASTNUM);
    curSrcMarginField.put(ArriveItemVO.NPLANASTNUM, OrderItemVO.NASTNUM);

    // 创建比较条件，根据主数量进行比较，因为主数量比较准确。
    IPuMarginCondition cond =
        new DefaultPuMarginCondition(curSrcCondJudgeField, curSrcMarginField,
            ArriveItemVO.NNUM);

    List<IPuMarginCondition> condList = new ArrayList<IPuMarginCondition>();
    condList.add(cond);

    // 查询上游采购订单表体VO
    if (ArrayUtils.isEmpty(this.getSrcItemVos())) {
      this.querySrcItemVO(OrderItemVO.class, condList, OrderItemVO.NNUM,
          ArriveItemVO.CSOURCEBID);
    }
    // 做多次转单倒挤时才需要查询下游兄弟VO
    if (!isFirstTime && ArrayUtils.isEmpty(this.getCurSiblingVos())) {
      this.queryCurSiblingVO(ArriveItemVO.class, condList, ArriveItemVO.NNUM,
          ArriveItemVO.CSOURCEBID, ArriveItemVO.CSOURCEID);
    }
    // 创建VO加工器
    IPuMarginVOProcess voproc =
        new DefaultPuMarginVOProc(ArriveItemVO.CSOURCEBID,
            ArriveItemVO.CSOURCEID, OrderItemVO.PK_ORDER_B);
    return new DefaultPuMarginProcess(cond, voproc, this.getCurItemVos(),
        this.getCurSiblingVos(), this.getSrcItemVos());
  }

  /**
   * 创建来源是采购订单（存在到货计划）的尾差处理器。
   * 
   * @param isFirstTime
   * @return 来源是采购订单（存在到货计划）的尾差处理器
   */
  private IPuMarginProcess createSrc21RecvPlan(boolean isFirstTime) {
    // 使用pubapp的MarginContextFactory工具时，需要提供来源和当前单据的倒挤字段，所以这里使用映射将它们存入Condition。
    // 判断能否进行倒挤的字段映射。
    Map<String, String> curSrcCondJudgeField = new HashMap<String, String>();
    curSrcCondJudgeField.put(ArriveItemVO.CASTUNITID, OrderItemVO.CASTUNITID);

    // 需要进行倒挤的字段映射。
    Map<String, String> curSrcMarginField = new HashMap<String, String>();
    curSrcMarginField.put(ArriveItemVO.NASTNUM, OrderItemVO.NASTNUM);
    curSrcMarginField.put(ArriveItemVO.NPLANASTNUM, OrderItemVO.NASTNUM);

    // 创建比较条件，根据主数量进行比较，因为主数量比较准确。
    IPuMarginCondition cond =
        new DefaultPuMarginCondition(curSrcCondJudgeField, curSrcMarginField,
            ArriveItemVO.NNUM);

    List<IPuMarginCondition> condList = new ArrayList<IPuMarginCondition>();
    condList.add(cond);

    this.querySrcItemVO(OrderReceivePlanVO.class, condList,
        OrderReceivePlanVO.NNUM, ArriveItemVO.PK_ORDER_BB1);
    // 做多次转单倒挤时才需要查询下游兄弟VO
    if (!isFirstTime && ArrayUtils.isEmpty(this.getCurSiblingVos())) {
      this.queryCurSiblingVO(ArriveItemVO.class, condList, ArriveItemVO.NNUM,
          ArriveItemVO.PK_ORDER_BB1, ArriveItemVO.CSOURCEID);
    }
    // 创建VO加工器
    IPuMarginVOProcess voproc =
        new DefaultPuMarginVOProc(ArriveItemVO.PK_ORDER_BB1,
            ArriveItemVO.CSOURCEID, OrderReceivePlanVO.PK_ORDER_BB1);
    return new DefaultPuMarginProcess(cond, voproc, this.getCurItemVos(),
        this.getCurSiblingVos(), this.getSrcItemVos());
  }

  /**
   * 创建来源是委外订单的尾差处理器。
   * 
   * @param isFirstTime
   * @return 来源是委外订单的尾差处理器
   */
  private IPuMarginProcess createSrc61(boolean isFirstTime) {
    // 判断能否进行倒挤的字段映射。
    Map<String, String> curSrcCondJudgeField = new HashMap<String, String>();
    curSrcCondJudgeField.put(ArriveItemVO.CASTUNITID, SCOrderItemVO.CQTUNITID);

    // 需要进行倒挤的字段映射。
    Map<String, String> curSrcMarginField = new HashMap<String, String>();
    curSrcMarginField.put(ArriveItemVO.NASTNUM, SCOrderItemVO.NQTUNITNUM);
    curSrcMarginField.put(ArriveItemVO.NPLANASTNUM, SCOrderItemVO.NQTUNITNUM);

    // 创建比较条件，根据主数量进行比较，因为主数量比较准确。
    IPuMarginCondition cond =
        new DefaultPuMarginCondition(curSrcCondJudgeField, curSrcMarginField,
            ArriveItemVO.NNUM);
    List<IPuMarginCondition> condList = new ArrayList<IPuMarginCondition>();
    condList.add(cond);

    // 查询上游采购订单表体VO
    if (ArrayUtils.isEmpty(this.getSrcItemVos())) {
      this.querySrcItemVO(SCOrderItemVO.class, condList, SCOrderItemVO.NNUM,
          ArriveItemVO.CSOURCEBID);
    }
    // 做多次转单倒挤时才需要查询下游兄弟VO
    if (!isFirstTime && ArrayUtils.isEmpty(this.getCurSiblingVos())) {
      this.queryCurSiblingVO(ArriveItemVO.class, condList, ArriveItemVO.NNUM,
          ArriveItemVO.CSOURCEBID, ArriveItemVO.CSOURCEID);
    }
    // 创建VO加工器
    IPuMarginVOProcess voproc =
        new DefaultPuMarginVOProc(ArriveItemVO.CSOURCEBID,
            ArriveItemVO.CSOURCEID, SCOrderItemVO.PK_ORDER_B);
    return new DefaultPuMarginProcess(cond, voproc, this.getCurItemVos(),
        this.getCurSiblingVos(), this.getSrcItemVos());
  }

  @Override
  protected List<IPuMarginProcess> createFirstTimeMarginProcess() {
    return this.createMarginProcess(true);
  }

}
