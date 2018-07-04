package nc.bs.pu.m20.maintain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.pu.m20.maintain.rule.pub.IRewite;
import nc.bs.pu.m20.maintain.rule.pub.RewiteUtil;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.enumeration.EnumOperate;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.constant.ForeignForPUConstant;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.ETBillType;
import nc.vo.scmpub.res.billtype.INVPBillType;
import nc.vo.scmpub.res.billtype.MMBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              请购单回写来源规则（重构）
 * @scene
 *        请购单删除、修改
 * @param EnumOperate operateStatus 操作状态
 * @since 6.3
 * @version 2014-10-21 上午9:19:30
 * @author yanxm5
 */
public class PraybillWriteBackSourceRule implements ICompareRule<PraybillVO> {

  private ItemKeyMapping mapping;

  private EnumOperate operateStatus;

  // @Override
  // public void process(PraybillVO[] vos) {
  // this.process(vos, null);
  // }

  public PraybillWriteBackSourceRule(EnumOperate operateStatus) {
    this.operateStatus = operateStatus;
  }

  @Override
  public void process(PraybillVO[] vos, PraybillVO[] originVOs) {
    this.writeback(vos, originVOs, this.getItemKeyMapping());
  }

  private PraybillVO[] buidlPraybillsBySc(PraybillVO[] vos, UFBoolean sc) {
    List<PraybillVO> voList = new ArrayList<PraybillVO>();
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    for (PraybillVO vo : vos) {
      if (sc.equals(vo.getHVO().getBsctype())) {
        voList.add(vo);
      }
    }
    if (voList.size() == 0) {
      return null;
    }
    return voList.toArray(new PraybillVO[voList.size()]);
  }

  /**
   * <p>
   * 使用场景：构造bid为主键的voMap
   * <ul>
   * <li>
   * </ul>
   * 
   * @param vos
   * @param originVOs
   * @return bid为主键的voMap
   */
  private Map<String, PraybillVO> buildBidVOMap(PraybillVO[] vos,
      PraybillVO[] originVOs) {
    Map<String, PraybillVO> bidVOMap = new HashMap<String, PraybillVO>();
    // 考虑新增、修改、删除等，先以原vo构造，再以原vo构造
    if (!ArrayUtils.isEmpty(originVOs)) {
      for (PraybillVO vo : originVOs) {
        for (PraybillItemVO item : vo.getBVO()) {
          bidVOMap.put(item.getPk_praybill_b(), vo);
        }
      }
    }
    // 再以原vo构造
    if (!ArrayUtils.isEmpty(vos)) {
      for (PraybillVO vo : vos) {
        for (PraybillItemVO item : vo.getBVO()) {
          bidVOMap.put(item.getPk_praybill_b(), vo);
        }
      }
    }
    return bidVOMap;
  }

  private Map<String, List<RewritePara>> combinReritePara(
      Map<String, List<RewritePara>> scPara,
      Map<String, List<RewritePara>> noscPara) {
    if (MapUtils.isEmpty(scPara)) {
      return noscPara;
    }
    if (MapUtils.isEmpty(noscPara)) {
      return scPara;
    }
    for (Entry<String, List<RewritePara>> entry : scPara.entrySet()) {
      String key = entry.getKey();
      if (noscPara.containsKey(key)) {
        entry.getValue().addAll(noscPara.get(key));
      }
    }
    return scPara;
  }

  private ItemKeyMapping getItemKeyMapping() {
    if (this.mapping == null) {
      this.mapping = new ItemKeyMapping();
      this.mapping.setVsrctypeKey(PraybillItemVO.CSOURCETYPECODE);
      this.mapping.setCsrcidKey(PraybillItemVO.CSOURCEID);
      this.mapping.setCsrcbidKey(PraybillItemVO.CSOURCEBID);
      this.mapping.setSrcTSKey(PraybillItemVO.SOURCETS);
      this.mapping.setSrcbTSKey(PraybillItemVO.SOURCEBTS);
      this.mapping.setNnumKey(PraybillItemVO.NNUM);// 请购主数量
    }
    return this.mapping;
  }

  @SuppressWarnings("unchecked")
  private BillRewriter getRWTool(ItemKeyMapping itemMapping) {
    BillRewriter tool = new BillRewriter(itemMapping);
    if (SysInitGroupQuery.isEWMEnabled()) {
      // 资产工单
      try {
        tool.addSRCHeadClazz(ForeignForPUConstant.WORKORDER,
            (Class<? extends ISuperVO>) Class
                .forName("nc.vo.ewm.workorder.WorkOrderHeadVO"));
        tool.addSRCItemClazz(ForeignForPUConstant.WORKORDER,
            (Class<? extends ISuperVO>) Class
                .forName("nc.vo.ewm.workorder.WOPlanInVVO"));
      }
      catch (ClassNotFoundException e) {
        ExceptionUtils.wrappException(e);
      }
    }

    if (SysInitGroupQuery.isSOEnabled()) {
      // 销售订单
      try {
        tool.addSRCHeadClazz(SOBillType.Order.getCode(),
            (Class<? extends ISuperVO>) Class
                .forName("nc.vo.so.m30.entity.SaleOrderHVO"));
        tool.addSRCItemClazz(SOBillType.Order.getCode(),
            (Class<? extends ISuperVO>) Class
                .forName("nc.vo.so.m30.entity.SaleOrderBVO"));
      }
      catch (ClassNotFoundException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    if (SysInitGroupQuery.isMMPACEnabled()) {
      try {
        // 生产订单
        tool.addSRCHeadClazz(MMBillType.ProduceOrder.getCode(),
            (Class<? extends ISuperVO>) Class
                .forName("nc.vo.mmpac.pmo.pac0002.entity.PMOHeadVO"));
        tool.addSRCItemClazz(MMBillType.ProduceOrder.getCode(),
            (Class<? extends ISuperVO>) Class
                .forName("nc.vo.mmpac.pmo.pac0002.entity.PMOItemVO"));
        // 离散生产订单
        tool.addSRCHeadClazz(MMBillType.LsProduceOrder.getCode(),
            (Class<? extends ISuperVO>) Class
                .forName("nc.vo.mmpac.dmo.entity.DmoVO"));
      }
      catch (ClassNotFoundException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    if (SysInitGroupQuery.isMMEnabled()) {
      // 计划订单（制造）
      try {
        tool.addSRCHeadClazz(MMBillType.PlanOrder.getCode(),
            (Class<? extends ISuperVO>) Class
                .forName("nc.vo.mmpps.mps0202.PoVO"));
      }
      catch (ClassNotFoundException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    if (SysInitGroupQuery.isTOEnabled()) {
      // 调拨订单（内部交易）
      try {
        tool.addSRCHeadClazz(TOBillType.TransOrder.getCode(),
            (Class<? extends ISuperVO>) Class
                .forName("nc.vo.to.m5x.entity.BillHeaderVO"));
        tool.addSRCItemClazz(TOBillType.TransOrder.getCode(),
            (Class<? extends ISuperVO>) Class
                .forName("nc.vo.to.m5x.entity.BillItemVO"));
      }
      catch (ClassNotFoundException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    if (SysInitGroupQuery.isINVPEnabled()) {
      // 计划订单（库存计划）
      try {
        tool.addSRCHeadClazz(INVPBillType.PoOrder.getCode(),
            (Class<? extends ISuperVO>) Class
                .forName("nc.vo.invp.po.entity.PoVO"));
      }
      catch (ClassNotFoundException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    // 物资需求申请单
    tool.addSRCHeadClazz(POBillType.MRBill.getCode(), StoreReqAppHeaderVO.class);
    tool.addSRCItemClazz(POBillType.MRBill.getCode(), StoreReqAppItemVO.class);

    if (SysInitGroupQuery.isETEnabled()) {
      // 出口合同(自营出口)
      try {
        tool.addSRCHeadClazz(ETBillType.CONTRACT.getCode(),
            (Class<? extends ISuperVO>) Class
                .forName("nc.vo.et.m5720.entity.ContractHVO"));
        tool.addSRCItemClazz(ETBillType.CONTRACT.getCode(),
            (Class<? extends ISuperVO>) Class
                .forName("nc.vo.et.m5720.entity.ContractBVO"));
      }
      catch (ClassNotFoundException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return tool;
  }

  /**
   * 回写上游。注意,请购单的上游计划订单存在拆行的场景，即一行数据可能生成委外的请购单
   * 和非委外的请购单，此时回写时应按是否委外区分回写。
   * 
   * @param vos
   * @param originVOs
   * @param itemMapping
   */
  private void writeback(PraybillVO[] vos, PraybillVO[] originVOs,
      ItemKeyMapping itemMapping) {
    BillRewriter tool = this.getRWTool(itemMapping);
    Map<String, List<RewritePara>> noscRwParaMap = null;
    Map<String, List<RewritePara>> scRwParaMap = null;
    // origvo,带委外标志
    PraybillVO[] scOrigVOs = this.buidlPraybillsBySc(originVOs, UFBoolean.TRUE);
    // origvo,不带委外标志
    PraybillVO[] noscOrigVOs =
        this.buidlPraybillsBySc(originVOs, UFBoolean.FALSE);
    // 更新的vo,带委外标志
    PraybillVO[] scVOs = this.buidlPraybillsBySc(vos, UFBoolean.TRUE);
    // 更新的vo,带委外标志
    PraybillVO[] noscVOs = this.buidlPraybillsBySc(vos, UFBoolean.FALSE);
    if (EnumOperate.DELETE.equals(this.operateStatus)) {
      // rwParaMap = tool.splitForDelete(originVOs);
      if (noscOrigVOs != null) {
        noscRwParaMap = tool.splitForDelete(noscOrigVOs);
      }
      if (scOrigVOs != null) {
        scRwParaMap = tool.splitForDelete(scOrigVOs);
      }
    }
    else if (EnumOperate.ADD.equals(this.operateStatus)) {
      // rwParaMap = tool.splitForInsert(vos);
      if (noscVOs != null) {
        noscRwParaMap = tool.splitForInsert(noscVOs);
      }
      if (scVOs != null) {
        scRwParaMap = tool.splitForInsert(scVOs);
      }
    }
    else {
      // rwParaMap = tool.splitForUpdate(vos, originVOs);
      if (noscVOs != null && noscOrigVOs != null) {
        noscRwParaMap = tool.splitForUpdate(noscVOs, noscOrigVOs);
      }
      if (scVOs != null && scOrigVOs != null) {
        scRwParaMap = tool.splitForUpdate(scVOs, scOrigVOs);
      }
      if (scVOs != null && noscOrigVOs != null) {
        scRwParaMap = tool.splitForUpdate(scVOs, noscOrigVOs);
      }

    }
    Map<String, List<RewritePara>> rwParaMap =
        this.combinReritePara(scRwParaMap, noscRwParaMap);
    // 真正回写上游
    this.writeBackSource(vos, originVOs, rwParaMap);
  }

  private void writeBackSource(PraybillVO[] vos, PraybillVO[] originVOs,
      Map<String, List<RewritePara>> rwParaMap) {
    if (null == rwParaMap) {
      return;
    }
    for (Entry<String, List<RewritePara>> entry : rwParaMap.entrySet()) {
      String billtype = entry.getKey();
      if (0 == rwParaMap.get(billtype).size()) {
        continue;
      }
      IRewite rw = RewiteUtil.getInstance().getRewite(billtype);
      // 按上游单据类型执行真正回写
      if (null != rw) {
        rw.writeback(rwParaMap.get(billtype),
            this.buildBidVOMap(vos, originVOs));
      }
    }
  }

}
