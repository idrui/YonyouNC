/**
 * 
 */
package nc.bs.pu.m25.maintain.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.pu.m25.writeback.source.IInvoiceWriteBackSource;
import nc.bs.pu.m25.writeback.source.WriteBack45;
import nc.bs.pu.m25.writeback.source.WriteBack47;
import nc.bs.pu.m25.writeback.source.WriteBack4T;
import nc.bs.pu.m25.writeback.source.WriteBack50;
import nc.bs.pu.m25.writeback.source.WriteBack61;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInHeadVO;
import nc.vo.ic.m47.entity.SubcontInBodyVO;
import nc.vo.ic.m47.entity.SubcontInHeadVO;
import nc.vo.ic.m50.entity.VmiSumHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.sc.m61.entity.SCOrderHeaderVO;
import nc.vo.sc.m61.entity.SCOrderItemVO;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * 
 * @description
 * 回写上游单据:具体规则见BP－回写特征集设计
 * @scene
 * 删除,保存的BP
 * @param
 * env 采购发票操作时前台到后台的环境信息，一般随平台动作的userObj向外传 
 *
 * @since 6.3
 * @version 2014-10-22 下午3:11:30
 * @author zhangshqb
 */
public class WriteBackSourceRule implements ICompareRule<InvoiceVO> {

  private InvoiceUIToBSEnv env;

  /**
   * WriteBackSourceRule 的构造子
   * 
   * @param env
   */
  public WriteBackSourceRule(InvoiceUIToBSEnv env) {
    this.env = env;
  }

  /**
   * @param vos 保存后的vos，删除时null
   * @param originVOs 原始vos，新增时为null
   * @see nc.impl.pubapp.pattern.rule.ICompareRule#process(E[], E[])
   */
  @Override
  public void process(InvoiceVO[] invoiceVOs, InvoiceVO[] originInvoiceVOs) {
    InvoiceVO[] vos = invoiceVOs;
    InvoiceVO[] originVOs = originInvoiceVOs;
    // 计算临时属性（回写上游主数量）
    this.calcWritebackNum(vos);
    this.calcWritebackNum(originVOs);

    InvoiceVO[] sourceVOs = this.pickupNotOrderSource(vos);
    InvoiceVO[] sourceOriginVOs = this.pickupNotOrderSource(originVOs);
    // 回写来源
    this.writebackSource(sourceVOs, sourceOriginVOs);

    vos = this.pickup(vos);
    originVOs = this.pickup(originVOs);
    // 回写源头
    this.writebackFirst(vos, originVOs);
  }

  private void calcWritebackNum(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (InvoiceVO vo : vos) {
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        item.setNnumwrbck(MathTool.sub(item.getNnum(),
            item.getNreasonwastenum()));
      }
    }
  }

  // private Map<String, UFBoolean> getBidFeeMap(InvoiceVO[] allVOs) {
  // Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
  //
  // for (InvoiceVO vo : allVOs) {
  // InvoiceItemVO[] itemVOs = vo.getChildrenVO();
  // // UFBoolean bfee = ValueUtils.getUFBoolean(vo.getParentVO().getBfee());
  // for (InvoiceItemVO itemVO : itemVOs) {
  // boolean bfee =
  // itemVO.getFrowtype() != null
  // && (InvoiceRowType.DISCOUNT_ROW == itemVO.getFrowtype()
  // .intValue() || InvoiceRowType.FEE_ROW == itemVO
  // .getFrowtype().intValue());
  // map.put(itemVO.getPk_invoice_b(), UFBoolean.valueOf(bfee));
  // }
  // }
  //
  // return map;
  // }

  private BillRewriter getRWTool(ItemKeyMapping mapping) {
    BillRewriter tool = new BillRewriter(mapping);
    // 采购入库单
    tool.addSRCHeadClazz(ICBillType.PurchaseIn.getCode(),
        PurchaseInHeadVO.class);
    tool.addSRCItemClazz(ICBillType.PurchaseIn.getCode(),
        PurchaseInBodyVO.class);
    // 采购订单
    // tool.addSRCHeadClazz(POBillType.Order.getCode(), OrderHeaderVO.class);
    // tool.addSRCItemClazz(POBillType.Order.getCode(), OrderItemVO.class);
    // VMI50 消耗汇总
    tool.addSRCHeadClazz(ICBillType.VmiSum.getCode(), VmiSumHeaderVO.class);
    // tool.addSRCItemClazz(ICBillType.VmiSum.getCode(), VmiSumBodyVO.class);
    // 期初暂估入库单4T
    tool.addSRCHeadClazz(POBillType.InitEstimate.getCode(),
        InitialEstHeaderVO.class);
    tool.addSRCItemClazz(POBillType.InitEstimate.getCode(),
        InitialEstItemVO.class);
    // 委外订单61
    tool.addSRCHeadClazz(SCBillType.Order.getCode(), SCOrderHeaderVO.class);
    tool.addSRCItemClazz(SCBillType.Order.getCode(), SCOrderItemVO.class);
    // 委外加入库单47
    tool.addSRCHeadClazz(ICBillType.SubContinIn.getCode(),
        SubcontInHeadVO.class);
    tool.addSRCItemClazz(ICBillType.SubContinIn.getCode(),
        SubcontInBodyVO.class);
    return tool;
  }

  private IInvoiceWriteBackSource getWriteBacker(String billtype,
      InvoiceVO[] allVOs) {
    if (ICBillType.PurchaseIn.getCode().equals(billtype)) {
      return new WriteBack45();// 采购入库单回写器
    }
    // else if (POBillType.Order.getCode().equals(billtype)) {
    // return new WriteBack21(this.getBidFeeMap(allVOs));// 采购订单回写器
    // }
    else if (ICBillType.VmiSum.getCode().equals(billtype)) {
      return new WriteBack50();
    }
    else if (POBillType.InitEstimate.getCode().equals(billtype)) {// 期初暂估入库单4T
      return new WriteBack4T();
    }
    else if (SCBillType.Order.getCode().equals(billtype)) {// 委外订单61
      return new WriteBack61();
    }
    else if (ICBillType.SubContinIn.getCode().equals(billtype)) {// 委托加工入库47
      return new WriteBack47();
    }
    else {
      return null;
    }
  }

  private InvoiceVO[] pickup(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    List<InvoiceVO> invoiceSet = new ArrayList<InvoiceVO>();
    String ordertype = POBillType.Order.getCode();
    for (InvoiceVO vo : vos) {
      List<InvoiceItemVO> set = new ArrayList<InvoiceItemVO>();
      for (InvoiceItemVO itemVO : vo.getChildrenVO()) {
        if (!ordertype.equals(itemVO.getCfirsttypecode())
            && !ObjectUtils.equals(itemVO.getCfirstbid(),
                itemVO.getCsourcebid())) {
          set.add(itemVO);
        }
      }

      if (set.size() > 0) {
        InvoiceVO wbVo = new InvoiceVO();
        wbVo.setParentVO(vo.getParentVO());
        wbVo.setChildrenVO(set.toArray(new InvoiceItemVO[set.size()]));
        invoiceSet.add(wbVo);
      }
    }

    if (invoiceSet.size() > 0) {
      return invoiceSet.toArray(new InvoiceVO[invoiceSet.size()]);
    }

    return null;
  }

  private InvoiceVO[] pickupNotOrderSource(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    List<InvoiceVO> invoiceSet = new ArrayList<InvoiceVO>();
    String ordertype = POBillType.Order.getCode();
    for (InvoiceVO vo : vos) {
      List<InvoiceItemVO> set = new ArrayList<InvoiceItemVO>();
      for (InvoiceItemVO itemVO : vo.getChildrenVO()) {
        if (itemVO.getCsourcetypecode() != null
            && !ordertype.equals(itemVO.getCsourcetypecode())) {
          set.add(itemVO);
        }
      }

      if (set.size() > 0) {
        InvoiceVO wbVo = new InvoiceVO();
        wbVo.setParentVO(vo.getParentVO());
        wbVo.setChildrenVO(set.toArray(new InvoiceItemVO[set.size()]));
        invoiceSet.add(wbVo);
      }
    }

    if (invoiceSet.size() > 0) {
      return invoiceSet.toArray(new InvoiceVO[invoiceSet.size()]);
    }

    return null;
  }

  /**
   * 方法功能描述：简要描述本方法的功能。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param orgVos <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-2-5 上午09:33:56
   */
  private void writeback(InvoiceVO[] vos, InvoiceVO[] orgVos,
      ItemKeyMapping mapping) {
    BillRewriter tool = this.getRWTool(mapping);
    Map<String, List<RewritePara>> rwParaMap = null;
    if (ArrayUtils.isEmpty(vos)) {
      rwParaMap = tool.splitForDelete(orgVos);
    }
    else if (ArrayUtils.isEmpty(orgVos)) {
      rwParaMap = tool.splitForInsert(vos);
    }
    else {
      rwParaMap = tool.splitForUpdate(vos, orgVos);
    }
    InvoiceVO[] allVOs = ArrayUtil.combinArrays(vos, orgVos);
    for (Entry<String, List<RewritePara>> entry : rwParaMap.entrySet()) {
      String billtype = entry.getKey();
      if (0 == rwParaMap.get(billtype).size()) {
        continue;
      }
      IInvoiceWriteBackSource wbacker = this.getWriteBacker(billtype, allVOs);
      // 按上游单据类型执行真正回写
      if (null != wbacker) {
        wbacker.writeback(rwParaMap.get(billtype), this.env);
      }
    }
  }

  /**
   * 方法功能描述：回写源头。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param orgVos <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-2-5 上午09:35:54
   */
  private void writebackFirst(InvoiceVO[] vos, InvoiceVO[] orgVos) {
    if (vos == null && orgVos == null) {
      return;
    }

    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(InvoiceItemVO.CFIRSTTYPECODE);
    mapping.setCsrcbidKey(InvoiceItemVO.CFIRSTBID);
    mapping.setCsrcidKey(InvoiceItemVO.CFIRSTID);
    mapping.setNnumKey(InvoiceItemVO.NNUMWRBCK);
    mapping.setNnum2Key(InvoiceItemVO.NTAXMNY);
    this.writeback(vos, orgVos, mapping);
  }

  /**
   * 方法功能描述：回写来源。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param orgVos <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-2-5 上午09:35:29
   */
  private void writebackSource(InvoiceVO[] vos, InvoiceVO[] orgVos) {
    if (vos == null && orgVos == null) {
      return;
    }

    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(InvoiceItemVO.CSOURCETYPECODE);
    mapping.setCsrcbidKey(InvoiceItemVO.CSOURCEBID);
    mapping.setCsrcidKey(InvoiceItemVO.CSOURCEID);
    mapping.setSrcTSKey(InvoiceItemVO.SOURCETS);
    mapping.setSrcbTSKey(InvoiceItemVO.SOURCEBTS);
    mapping.setNnumKey(InvoiceItemVO.NNUMWRBCK);
    mapping.setNnum2Key(InvoiceItemVO.NTAXMNY);
    this.writeback(vos, orgVos, mapping);
  }

}
