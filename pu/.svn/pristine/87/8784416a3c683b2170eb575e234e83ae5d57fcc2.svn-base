package nc.bs.pu.m422x.ewm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m422x.maintain.StoreReqAppDeleteBP;
import nc.bs.pu.m422x.maintain.StoreReqAppSaveBP;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.ewm.pub.IRepairPlanForPUService;
import nc.itf.pu.m422x.IStoreReqAppClose;
import nc.itf.uap.pf.IplatFormEntry;
import nc.vo.pu.m422x.context.StorereqContext;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pu.m422x.exception.StoreqDeleteException;
import nc.vo.pu.pub.constant.ForeignForPUConstant;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.uap.pf.PfProcessBatchRetObject;

import org.apache.commons.lang.ArrayUtils;

/**
 * 物资需求申请单提供给资产维修计划的删除或关闭action
 * 
 * @since 6.5
 * @version 2014-2-19 上午10:23:33
 * @author fanly3
 */
public class DeleteClose422XFor4B32Action {

  private IStoreReqAppClose service = null;

  // key=表头pk，value=aggvo
  Map<String, StoreReqAppVO> aggMap = new HashMap<String, StoreReqAppVO>();

  // 提交态，审批中
  List<StoreReqAppVO> approvingList = new ArrayList<StoreReqAppVO>();

  // 自由态，审批不通过
  List<StoreReqAppVO> freeNopassList = new ArrayList<StoreReqAppVO>();

  // key=表头pk，value=aggvo
  Map<String, StoreReqAppVO> origAggMap = new HashMap<String, StoreReqAppVO>();

  // 审批通过
  List<StoreReqAppVO> passList = new ArrayList<StoreReqAppVO>();

  /**
   * 场景：资产维修计划在关闭维修计划时，可能对下游物资需求申请单的删除和关闭操作
   * 
   * @param pk_repair_plan
   *          维修计划主表主键
   * @param pk_repair_plan_inv
   *          维修计划物料行主键
   * @param isDelete
   *          是否删除标志
   * @throws BusinessException
   */
  public void deleteCloseStoreReqApp(String[] pk_repair_plan,
      String[] pk_repair_plan_inv, boolean isDelete) throws BusinessException {

    StoreReqAppVO[] aggVos =
        this.queryAggVos(pk_repair_plan, pk_repair_plan_inv);
    // 分类并且初始化aggMap
    for (StoreReqAppVO vo : aggVos) {
      this.aggMap.put(vo.getHVO().getPk_storereq(), vo);
      Integer billStatus = vo.getHVO().getFbillstatus();
      if (POEnumBillStatus.APPROVING.toInteger() == billStatus
          || POEnumBillStatus.COMMIT.toInteger() == billStatus) {
        this.approvingList.add(vo);
      }
      else if (POEnumBillStatus.FREE.toInteger() == billStatus
          || POEnumBillStatus.NOPASS.toInteger() == billStatus) {
        this.freeNopassList.add(vo);
      }
      else if (POEnumBillStatus.APPROVE.toInteger() == billStatus) {
        this.passList.add(vo);
      }
    }
    // 查询原始origAggVos
    StoreReqAppVO[] origAggVos = this.queryOrigAggVos();
    // 初始化origAggMap
    for (StoreReqAppVO vo : origAggVos) {
      this.origAggMap.put(vo.getHVO().getPk_storereq(), vo);
    }
    // 检查是否删除，还是关闭，或是抛异常
    this.check(isDelete);
  }

  public void deleteStoreReqApp(String[] pk_repair_plan,
      String[] pk_repair_plan_inv, boolean writeback) throws BusinessException {

    StoreReqAppVO[] aggVos =
        this.queryAggVos(pk_repair_plan, pk_repair_plan_inv);
    if (aggVos == null || aggVos.length < 1) {
      return;
    }
    // 弃审
    // 调用审批脚本
    IplatFormEntry iIplatFormEntry =
        (IplatFormEntry) NCLocator.getInstance().lookup(
            IplatFormEntry.class.getName());
    Object retObj = null;
    for (int i = 0; i < aggVos.length; i++) {

      aggVos[i].getHVO().setStatus(VOStatus.UPDATED);

    }
    HashMap eParam = new HashMap();
//    StorereqContext context = StorereqContext.getInstance();
//    context.setSession("SrcAction", ForeignForPUConstant.M4B32);
    try {
      retObj =
          iIplatFormEntry.processBatch(
              new StringBuilder().append("UNAPPROVE")
                  .append(InvocationInfoProxy.getInstance().getUserId())
                  .toString(), "422X", null, aggVos, null, eParam);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
//    finally {
//      // 防止出现异常后session中状态错误
//      context.setSession("SrcBillType", null);
//    }
    if (retObj != null) {
      PfProcessBatchRetObject returnobj =
          (nc.vo.uap.pf.PfProcessBatchRetObject) retObj;
      if (returnobj.getExceptionMsg() != null
          && !returnobj.getExceptionMsg().equalsIgnoreCase("")) {
        ExceptionUtils.wrappBusinessException(returnobj.getExceptionMsg());
      }
      else {
        aggVos = (StoreReqAppVO[]) returnobj.getRetObj();
      }

    }
    else {
      // StringBuffer msg = new StringBuffer("维修计划生成的物资需求申请单弃审报错");
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4004010_0", "04004010-0031")/* 维修计划生成的物资需求申请单弃审报错 */);

    }
    // 删除
    retObj =
        iIplatFormEntry.processBatch(new StringBuilder().append("DISCARD")
            .append(InvocationInfoProxy.getInstance().getUserId()).toString(),
            "422X", null, aggVos, null, eParam);
    if (retObj != null) {

      PfProcessBatchRetObject returnobj =
          (nc.vo.uap.pf.PfProcessBatchRetObject) retObj;
      if (returnobj.getExceptionMsg() != null
          && !returnobj.getExceptionMsg().equalsIgnoreCase("")) {
        ExceptionUtils.wrappBusinessException(returnobj.getExceptionMsg());
      }

      aggVos = (StoreReqAppVO[]) returnobj.getRetObj();

      aggVos =
          (StoreReqAppVO[]) ((nc.vo.uap.pf.PfProcessBatchRetObject) retObj)
              .getRetObj();
      NCLocator.getInstance().lookup(IRepairPlanForPUService.class)
          .writeBackPlanForDeleteStoreReq(aggVos, writeback);
    }
    else {
      // StringBuffer msg = new StringBuffer("维修计划生成的物资需求申请单删除报错");
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4004010_0", "04004010-0032")/* 维修计划生成的物资需求申请单删除报错 */);

    }

  }

  /**
   * 对物资需求申请单的不同状态进行不同业务逻辑操作
   * 
   * @param isDelete
   * @throws BusinessException
   */
  private void check(boolean isDelete) throws BusinessException {
    // 校验approvingList
    this.checkApprovingList();
    // 校验freeNopassList
    this.checkFreeNopassList(isDelete);
    // 校验passList
    this.checkPassList();
  }

  /**
   * 检查approvingList
   */
  private void checkApprovingList() {
    // 如果存在已提交或审批中的，提示：“维修计划生成的物资需求申请单[XXXxxxxxx],[XXXxxxxx]审批中，不能关闭“。
    if (!this.approvingList.isEmpty()) {
      // TODO 多语
      StringBuffer msg = new StringBuffer();
      msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004010_0", "04004010-0033")/* 维修计划生成的物资需求申请单 */);
      for (StoreReqAppVO vo : this.approvingList) {
        msg.append("[");
        msg.append(vo.getHVO().getVbillcode());
        msg.append("],");
      }
      msg.deleteCharAt(msg.length() - 1);
      // TODO 多语
      msg.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004010_0", "04004010-0034")/* 审批中，不能关闭 */);
      ExceptionUtils.wrappBusinessException(msg.toString());
    }
  }

  /**
   * 检查freeNopassList
   * 
   * @param isDelete
   * @throws StoreqDeleteException
   */
  private void checkFreeNopassList(boolean isDelete)
      throws StoreqDeleteException {
    if (this.freeNopassList.isEmpty()) {
      return;
    }
    // 如果不存在已提交或审批中的，检查是否存在自由态或审批不通过的
    if (!isDelete) {
      // TODO 多语
      throw new StoreqDeleteException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004010_0", "04004010-0035")/* 存在自由态或审批不通过的单据 */);
    }

    // 得到需要删除的原始聚合vo
    StoreReqAppVO[] origAggVos = this.getDelOrigAggVos();
    // 构造需要删除的聚合VO
    StoreReqAppVO[] newAggVos = this.getDelAggVos();

    Map<String, StoreReqAppVO> origMap = new HashMap<String, StoreReqAppVO>();
    for (StoreReqAppVO aggVo : origAggVos) {
      origMap.put(aggVo.getHVO().getPk_storereq(), aggVo);
    }
    Map<String, StoreReqAppVO> newMap = new HashMap<String, StoreReqAppVO>();
    for (StoreReqAppVO aggVo : newAggVos) {
      newMap.put(aggVo.getHVO().getPk_storereq(), aggVo);
    }

    // 保存行删除的聚合VO
    List<StoreReqAppVO> lineDel = new ArrayList<StoreReqAppVO>();
    // 保存整单删除的聚合VO
    List<StoreReqAppVO> entireDel = new ArrayList<StoreReqAppVO>();
    for (Entry<String, StoreReqAppVO> entry : newMap.entrySet()) {
      String key = entry.getKey();
      int newItemCount = newMap.get(key).getBVO().length;
      int origItemCount = origMap.get(key).getBVO().length;
      if (newItemCount < origItemCount) {
        // 删除行
        lineDel.add(newMap.get(key));
      }
      if (newItemCount == origItemCount) {
        // 整单删除
        entireDel.add(newMap.get(key));
      }
    }

    if (!lineDel.isEmpty()) {
      // 保证两个数组中聚合vo的顺序
      StoreReqAppVO[] origLineDel = new StoreReqAppVO[lineDel.size()];
      StoreReqAppVO[] newLineDel = new StoreReqAppVO[lineDel.size()];
      // key=聚合VO主表pk，value存储的是该聚合vo子表pk
      MapSet<String, String> mapSet = new MapSet<String, String>();
      for (int i = 0; i < lineDel.size(); i++) {
        String key = lineDel.get(i).getHVO().getPk_storereq();
        origLineDel[i] = origMap.get(key);
        newLineDel[i] = newMap.get(key);
        StoreReqAppItemVO[] newItemVOs = newMap.get(key).getBVO();
        for (StoreReqAppItemVO itemVo : newItemVOs) {
          mapSet.put(key, itemVo.getPk_storereq_b());
        }
      }

      for (int i = 0; i < newLineDel.length; i++) {
        List<StoreReqAppItemVO> newBodyList =
            new ArrayList<StoreReqAppItemVO>();
        StoreReqAppItemVO[] newBodyItems = newLineDel[i].getBVO();
        for (StoreReqAppItemVO itemVo : newBodyItems) {
          itemVo.setStatus(VOStatus.DELETED);
          newBodyList.add(itemVo);
        }
        String key = newLineDel[i].getHVO().getPk_storereq();
        StoreReqAppItemVO[] origBodyItems = origLineDel[i].getBVO();
        for (StoreReqAppItemVO itemVO : origBodyItems) {
          if (!mapSet.get(key).contains(itemVO.getPk_storereq_b())) {
            StoreReqAppItemVO cloneItem = (StoreReqAppItemVO) itemVO.clone();
            cloneItem.setStatus(VOStatus.UNCHANGED);
            newBodyList.add(cloneItem);
          }
        }
        newLineDel[i].getHVO().setStatus(VOStatus.UPDATED);
        newLineDel[i].setBVO(newBodyList
            .toArray(new StoreReqAppItemVO[newBodyList.size()]));
      }
      new StoreReqAppSaveBP().save(null, newLineDel, origLineDel);
    }

    if (!entireDel.isEmpty()) {
      StoreReqAppVO[] aggVos =
          entireDel.toArray(new StoreReqAppVO[entireDel.size()]);
      new StoreReqAppDeleteBP().delete(aggVos);
    }
  }

  private void checkPassList() throws BusinessException {
    // 如果只有审批通过状态的物资需求申请，直接关闭物资需求申请单的对应表体行，不需要提示。
    if (!this.passList.isEmpty()) {
      StoreReqAppVO[] aggVos = this.getCloseAggVos();
      IStoreReqAppClose serviceStoreReq = this.getCloseService();
      serviceStoreReq.rowClose(aggVos);
    }
  }

  /**
   * 得到满足关闭条件的请购单聚合vo
   * 
   * @param passList
   *          满足关闭条件的聚合vo列表
   * @return 满足关闭条件的请购单聚合vo
   */
  private StoreReqAppVO[] getCloseAggVos() {
    List<StoreReqAppVO> close = new ArrayList<StoreReqAppVO>();
    for (StoreReqAppVO vo : this.passList) {
      close.add(this.aggMap.get(vo.getHVO().getPk_storereq()));
    }
    return close.toArray(new StoreReqAppVO[close.size()]);
  }

  /**
   * 返回物资需求申请单关闭服务
   * 
   * @return
   */
  private IStoreReqAppClose getCloseService() {
    if (this.service == null) {
      this.service = NCLocator.getInstance().lookup(IStoreReqAppClose.class);
    }
    return this.service;
  }

  /**
   * 得到满足删除条件的请购单聚合vo，删除包括（行删除，整单删除）
   * 
   * @param freeNopassList
   *          满足删除条件的聚合vo列表
   * @return 满足删除条件的请购单聚合vo
   */
  private StoreReqAppVO[] getDelAggVos() {
    List<StoreReqAppVO> del = new ArrayList<StoreReqAppVO>();
    for (StoreReqAppVO vo : this.freeNopassList) {
      del.add(this.aggMap.get(vo.getHVO().getPk_storereq()));
    }
    return del.toArray(new StoreReqAppVO[del.size()]);
  }

  /**
   * 得到满足删除条件的请购单原始聚合vo，删除包括（行删除，整单删除）
   * 
   * @param freeNopassList
   *          满足删除条件的聚合vo列表
   * @return 满足删除条件的请购单原始聚合vo
   */
  private StoreReqAppVO[] getDelOrigAggVos() {
    List<StoreReqAppVO> del = new ArrayList<StoreReqAppVO>();
    for (StoreReqAppVO vo : this.freeNopassList) {
      del.add(this.origAggMap.get(vo.getHVO().getPk_storereq()));
    }
    return del.toArray(new StoreReqAppVO[del.size()]);
  }

  /**
   * 根据维修计划物料行pk，查询对应的物资需求申请单聚合vo
   * 
   * @param pk_repair_plan_inv
   *          维修计划物料行pk
   * @return 对应的物资需求申请单聚合vo
   */
  private StoreReqAppVO[] queryAggVos(String[] pk_repair_plan,
      String[] pk_repair_plan_inv) {
    String[] bids = this.queryStoreqBids(pk_repair_plan, pk_repair_plan_inv);
    ViewQuery<StoreReqAppViewVO> query =
        new ViewQuery<StoreReqAppViewVO>(StoreReqAppViewVO.class);
    StoreReqAppViewVO[] viewVos = query.query(bids);

    if (ArrayUtils.isEmpty(viewVos)) {
      return null;
    }
    return StoreReqAppViewVO.getStoreReqAppVO(viewVos);
  }

  /**
   * 根据表头pk查询数据库中对应的聚合vo
   * 
   * @param headList
   * @return
   */
  private StoreReqAppVO[] queryOrigAggVos() {
    // 根据表头主键，查询单据vo
    BillQuery<StoreReqAppVO> billQuery =
        new BillQuery<StoreReqAppVO>(StoreReqAppVO.class);
    StoreReqAppVO[] origAggVos =
        billQuery.query(this.aggMap.keySet().toArray(
            new String[this.aggMap.keySet().size()]));
    return origAggVos;
  }

  /**
   * 查询物资需求申请单子表pk
   * 
   * @param pk_repair_plan_inv
   *          计划物料行pk
   * @return 物资需求申请单子表pk
   */
  private String[] queryStoreqBids(String[] pk_repair_plan,
      String[] pk_repair_plan_inv) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ");
    sql.append(StoreReqAppItemVO.PK_STOREREQ_B);
    sql.append(" from ");
    sql.append(PUEntity.M422X_B_TABLE);
    sql.append(" where ");
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_422X_01.name());
    sql.append(builder.buildSQL(PUEntity.M422X_B_TABLE + "."
        + StoreReqAppItemVO.CSOURCEID, pk_repair_plan));
    sql.append(" and ");
    IDExQueryBuilder builder2 =
        new IDExQueryBuilder(PUTempTable.tmp_po_422X_02.name());
    sql.append(builder2.buildSQL(PUEntity.M422X_B_TABLE + "."
        + StoreReqAppItemVO.CSOURCEBID, pk_repair_plan_inv));
    sql.append(" and ");
    sql.append(PUEntity.M422X_B_TABLE + "." + StoreReqAppItemVO.DR, 0);

    DataAccessUtils utils = new DataAccessUtils();
    String[] bids = utils.query(sql.toString()).toOneDimensionStringArray();
    return bids;
  }
}
