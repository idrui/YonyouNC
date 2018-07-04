/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 上午09:03:23
 */
package nc.bs.pu.m21.maintain.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.pu.m21.writeback.source.WriteBack28;
import nc.bs.pu.m21.writeback.source.WriteBackAddSRCClazzUtil;
import nc.bs.pu.m21.writeback.source.WriteBackCoop30;
import nc.bs.pu.m21.writeback.source.WriteBackUtil;
import nc.bs.pu.m21.writeback.source.WriteBackZ2;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.rule.WriteBackPIMRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.writeback.IWriteBackSource;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.scmpub.res.billtype.ETBillType;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.INVPBillType;
import nc.vo.scmpub.res.billtype.MMBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * @description
 *              <ul>
 *              <li>回写上游单据
 *              <li>和5系列区别：不需要处理赠品，不管是不是赠品，全部回写。
 *              <li>因为修订订单是直接保存，没有使用OrderSaveBP，所以也不需要过滤修订历史订单
 *              </ul>
 * @scene
 *        采购订单删除、保存修改
 * @param OrderContext ctx 订单环境信息
 * @since 6.3
 * @version 2014-10-21 上午8:42:02
 * @author luojw
 */
public class WriteBackSourceRule implements ICompareRule<OrderVO> {

  private OrderContext ctx;

  public WriteBackSourceRule(OrderContext ctx) {
    this.ctx = ctx;
  }

  /**
   * @param vos
   *          保存后的vos，删除时null
   * @param originVOs
   *          原始vos，新增时为null
   * @see nc.impl.pubapp.pattern.rule.ICompareRule#process(E[], E[])
   */
  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    // 回写来源
    this.writebackSource(vos, originVOs);

    // 回写合同
    this.writebackCnt(vos, originVOs);

    // 回写价格审批单
    this.writeback28(vos, originVOs);

    this.writeback30Coop(vos, originVOs);

    // 回写请购单
    this.writeback20(vos, originVOs);
    // 回写项目
    new WriteBackPIMRule<OrderVO>(POBillType.Order.getCode()).process(vos,
        originVOs);

    OrderVO[] pickupedVos = this.pickup(vos);
    OrderVO[] pickupedOriginVOs = this.pickup(originVOs);
    if (null == pickupedVos && null == pickupedOriginVOs) {
      return;
    }
    // 回写源头
    this.writebackFirst(pickupedVos, pickupedOriginVOs);
  }

  private void addSRCCalzz(BillRewriter billRewriter, String[] billTypes) {
    WriteBackAddSRCClazzUtil util = WriteBackAddSRCClazzUtil.getInstance();
    for (String billtype : billTypes) {
      if (SOBillType.Order.getCode().equals(billtype)) {
        util.addSRCClazzFor30(billRewriter);// 销售订单
      }
      else if (ICBillType.PurchaseIn.getCode().equals(billtype)) {
        util.addSRCClazzFor45(billRewriter); // 退库单
      }
      else if (ICBillType.BorrowIn.getCode().equals(billtype)) {
        util.addSRCClazzFor49(billRewriter); // 库存借入单
      }
/*      else if (POBillType.PrayBill.getCode().equals(billtype)) {
        util.addSRCClazzFor20(billRewriter); // 请购单
      }*/
      else if (POBillType.Arrive.getCode().equals(billtype)) {
        util.addSRCClazzFor23(billRewriter); // 退货单
      }
      else if (TOBillType.TransOrder.getCode().equals(billtype)) {
        util.addSRCClazzFor5X(billRewriter);// 调拨订单
      }
      else if (MMBillType.PlanOrder.getCode().equals(billtype)) {
        util.addSRCClazzFor55B4(billRewriter);// 生产制造-计划订单
      }
      else if (MMBillType.ProduceOrder.getCode().equals(billtype)) {
        util.addSRCClazzFor55A2(billRewriter);// 生产订单
      }
      else if (MMBillType.LsProduceOrder.getCode().equals(billtype)) {
        util.addSRCClazzFor55C2(billRewriter);// 生产订单
      }
      else if (CTBillType.PurDaily.getCode().equals(billtype)) {
        // 合同
        util.addSRCClazzForZ2(billRewriter);
      }
      else if (INVPBillType.PoOrder.getCode().equals(billtype)) {
        util.addSRCClazzFor4F(billRewriter);// 库存计划-计划订单
      }
      else if (ETBillType.CONTRACT.getCode().equals(billtype)) {
        util.addSRCClazzForET(billRewriter);// 出口合同
      }
    }
  }

  private String[] getSourceType(OrderVO[] vos, OrderVO[] orgVos) {
    Set<String> sourceTypeSet = new HashSet<String>();
    OrderVO[] allVos = ArrayUtil.combinArrays(vos, orgVos);

    for (OrderVO vo : allVos) {
      if (UFBoolean.TRUE.equals(vo.getHVO().getBsocooptome())) {
        continue;
      }
      for (OrderItemVO itemVO : vo.getBVO()) {
        String sourceType = itemVO.getCsourcetypecode();
        String firstType = itemVO.getCfirsttypecode();
        if (!StringUtil.isEmptyWithTrim(sourceType)) {
          sourceTypeSet.add(sourceType);
        }
        if (!StringUtil.isEmptyWithTrim(firstType)) {
          sourceTypeSet.add(firstType);
        }
      }
    }

    if (!sourceTypeSet.isEmpty()) {
      return sourceTypeSet.toArray(new String[0]);
    }

    return null;
  }

  private IWriteBackSource getWriteBacker(String billtype) {
    WriteBackUtil util = WriteBackUtil.getInstance();
    IWriteBackSource source = null;
    // if (POBillType.PrayBill.getCode().equals(billtype)) {
    // source = util.getWriteBack20(this.ctx);// 请购单回写器
    // }
    // else
    if (SOBillType.Order.getCode().equals(billtype)) {
      source = util.getWriteBack30();// 销售订单回写器
    }
    else if (ICBillType.PurchaseIn.getCode().equals(billtype)) {
      source = util.getWriteBack45(); // 退库单回写器
    }
    else if (ICBillType.BorrowIn.getCode().equals(billtype)) {
      source = util.getWriteBack49(); // 库存借入单回写器
    }
    else if (POBillType.Arrive.getCode().equals(billtype)) {
      source = util.getWriteBack23(); // 退货单回写器
    }
    else if (TOBillType.TransOrder.getCode().equals(billtype)) {
      source = util.getWriteBack5X();// 调拨订单回写器
    }
    else if (MMBillType.PlanOrder.getCode().equals(billtype)) {
      source = util.getWriteBack55B4();// 计划订单回写器
    }
    else if (MMBillType.ProduceOrder.getCode().equals(billtype)) {
      source = util.getWriteBack55A2();// 生产订单回写器
    }
    else if (MMBillType.LsProduceOrder.getCode().equals(billtype)) {
      source = util.getWriteBack55C2();// 离散生产订单回写器
    }
    else if (INVPBillType.PoOrder.getCode().equals(billtype)) {
      source = util.getWriteBack4F();// 库存计划计划订单回写器
    }
    else if (ETBillType.CONTRACT.getCode().equals(billtype)) {
      source = util.getWriteBackET();// 出口合同回写器
    }
    return source;
  }

  private OrderVO[] pickup(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    Set<OrderVO> invoiceSet = new HashSet<OrderVO>();
    for (OrderVO vo : vos) {
      Set<OrderItemVO> set = new HashSet<OrderItemVO>();
      for (OrderItemVO itemVO : vo.getBVO()) {
        String cfirsttypecode = itemVO.getCfirsttypecode();
        if (SOBillType.Order.getCode().equals(cfirsttypecode)
            || TOBillType.TransOrder.getCode().equals(cfirsttypecode)
            || MMBillType.PlanOrder.getCode().equals(cfirsttypecode)
            || MMBillType.ProduceOrder.getCode().equals(cfirsttypecode)
            || MMBillType.LsProduceOrder.getCode().equals(cfirsttypecode)
            || ETBillType.CONTRACT.getCode().equals(cfirsttypecode)) {
          continue;
        }
        if (!ObjectUtils.equals(itemVO.getCfirstbid(), itemVO.getCsourcebid())) {
          set.add(itemVO);
        }
      }

      if (set.size() > 0) {
        vo = (OrderVO) vo.clone();
        vo.setBVO(set.toArray(new OrderItemVO[set.size()]));
        invoiceSet.add(vo);
      }
    }

    if (invoiceSet.size() > 0) {
      return invoiceSet.toArray(new OrderVO[invoiceSet.size()]);
    }

    return null;
  }

  /**
   * 订单根据表体请购单信息回写请购单时，回写框架需要单据类型
   * 补请购单单据类型
   * 
   * @author mengjian
   * @param vos
   */
  private void setPrayBillType(OrderVO[] vos) {
    for (OrderVO orderVO : vos) {
      OrderItemVO[] bvos = orderVO.getBVO();
      for (OrderItemVO bvo : bvos) {
        bvo.setCpraytypecode(POBillType.PrayBill.getCode());
      }
    }
  }

  /**
   * 方法功能描述：回写来源或者源头。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param orgVos
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-2-5 上午09:33:56
   */
  private void writeback(OrderVO[] vos, OrderVO[] orgVos, ItemKeyMapping mapping) {

    String[] sourceTypes = this.getSourceType(vos, orgVos);
    if (null == sourceTypes) {
      return;
    }

    BillRewriter billRewriter = new BillRewriter(mapping);
    this.addSRCCalzz(billRewriter, sourceTypes);

    Map<String, List<RewritePara>> rwParaMap = null;
    if (ArrayUtils.isEmpty(vos)) {
      rwParaMap = billRewriter.splitForDelete(orgVos);
    }
    else if (ArrayUtils.isEmpty(orgVos)) {
      rwParaMap = billRewriter.splitForInsert(vos);
    }
    else {
      rwParaMap = billRewriter.splitForUpdate(vos, orgVos);
    }

    for (Map.Entry<String, List<RewritePara>> entry : rwParaMap.entrySet()) {
      List<RewritePara> paraList = entry.getValue();
      if (0 == paraList.size()) {
        continue;
      }

      String billtype = entry.getKey();
      IWriteBackSource wbacker = this.getWriteBacker(billtype);
      if (null != wbacker) {
        wbacker.writeback(paraList);// 按上游单据类型执行真正回写
      }
    }
  }

  /**
   * @description 回写请购单 依照请购单单据号和请购单行号
   * @param vos
   * @param originVOs
   */
  private void writeback20(OrderVO[] vos, OrderVO[] orgVos) {
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setCsrcbidKey(OrderItemVO.CPRAYBILLBID);
    mapping.setCsrcidKey(OrderItemVO.CPRAYBILLHID);
    mapping.setNnumKey(OrderItemVO.NNUM);
    mapping.setVsrctypeKey(OrderItemVO.CPRAYTYPECODE);

    BillRewriter billRewriter = new BillRewriter(mapping);
    WriteBackAddSRCClazzUtil util = WriteBackAddSRCClazzUtil.getInstance();
    util.addSRCClazzFor20(billRewriter);// 请购单
    Map<String, List<RewritePara>> rwParaMap = null;
    if (ArrayUtils.isEmpty(vos)) {
      this.setPrayBillType(orgVos);
      rwParaMap = billRewriter.splitForDelete(orgVos);
    }
    else if (ArrayUtils.isEmpty(orgVos)) {
      this.setPrayBillType(vos);
      rwParaMap = billRewriter.splitForInsert(vos);
    }
    else {
      this.setPrayBillType(orgVos);
      this.setPrayBillType(vos);
      rwParaMap = billRewriter.splitForUpdate(vos, orgVos);
    }

    for (Map.Entry<String, List<RewritePara>> entry : rwParaMap.entrySet()) {
      List<RewritePara> paraList = entry.getValue();
      if (0 == paraList.size()) {
        continue;
      }
      List<RewritePara> list = new ArrayList<RewritePara>();
      for (RewritePara para : paraList) {
        if (para.getCsrcid() == null) {
          continue;
        }
        if (para.getCsrcbid() == null) {
          continue;
        }
        list.add(para);
      }
      if (list.size() == 0) {
        return;
      }
      WriteBackUtil.getInstance().getWriteBack20(this.ctx).writeback(list);
    }
  }

  /**
   * 方法功能描述：回写价格审批单
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param originVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-31 下午06:08:10
   */
  private void writeback28(OrderVO[] vos, OrderVO[] originVOs) {
    if (!SysInitGroupQuery.isPPEnabled()) {
      return;
    }
    new WriteBack28().writeback(vos, originVOs);
  }

  private void writeback30Coop(OrderVO[] vos, OrderVO[] orgVos) {
    new WriteBackCoop30().writeback30Coop(vos, orgVos);
  }

  /**
   * 方法功能描述：回写合同。合同需要单独回写，因为通过通过合同信息而不是通过来源进行回写。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param originVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-31 下午05:12:06
   */
  private void writebackCnt(OrderVO[] vos, OrderVO[] originVOs) {
    boolean isCTEnabled = SysInitGroupQuery.isCTEnabled();
    if (!isCTEnabled) {
      return;
    }

    new WriteBackZ2(this.ctx).writeback(vos, originVOs);
  }

  /**
   * 方法功能描述：回写源头。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param orgVos
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-2-5 上午09:35:54
   */
  private void writebackFirst(OrderVO[] vos, OrderVO[] orgVos) {
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(OrderItemVO.CFIRSTTYPECODE);
    mapping.setCsrcbidKey(OrderItemVO.CFIRSTBID);
    mapping.setCsrcidKey(OrderItemVO.CFIRSTID);
    mapping.setNnumKey(OrderItemVO.NNUM);
    this.writeback(vos, orgVos, mapping);
  }

  /**
   * 方法功能描述：回写来源。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param orgVos
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-2-5 上午09:35:29
   */
  private void writebackSource(OrderVO[] vos, OrderVO[] orgVos) {
    OrderItemVO itemVO = null;
    if (ArrayUtils.isEmpty(orgVos)) {
      itemVO = vos[0].getBVO()[0];
    }
    else if (ArrayUtils.isEmpty(vos)) {
      itemVO = orgVos[0].getBVO()[0];
    }
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(OrderItemVO.CSOURCETYPECODE);
    mapping.setCsrcbidKey(OrderItemVO.CSOURCEBID);
    mapping.setCsrcidKey(OrderItemVO.CSOURCEID);
    
    /* * change by wandl 
     * 配额分配请购单表体分单以后回写报并发问题临时补丁解决！*/
     
    if (itemVO != null
        && StringUtil.isEmptyWithTrim(itemVO.getCpriceaudit_bid())) {
      mapping.setSrcTSKey(OrderItemVO.SOURCETS);
      mapping.setSrcbTSKey(OrderItemVO.SOURCEBTS);
    }
    mapping.setNnumKey(OrderItemVO.NNUM);
    mapping.setNnum2Key(OrderItemVO.NORIGTAXMNY);
    this.writeback(vos, orgVos, mapping);
  }

}
