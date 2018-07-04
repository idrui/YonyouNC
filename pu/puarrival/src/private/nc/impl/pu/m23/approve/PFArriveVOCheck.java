package nc.impl.pu.m23.approve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pf.CheckStatusCallbackContext;
import nc.bs.pub.pf.ICheckStatusCallback;
import nc.bs.scmpub.pf.PfBeforeAndAfterAction;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.pubitf.pu.m21.pub.IOrderPubQuery;
import nc.pubitf.scmf.ic.mbatchcode.IBatchcodePubService;
import nc.vo.pf.change.IActionDriveChecker;
import nc.vo.pf.change.IChangeVOCheck;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.BatchCodeFieldMap;
import nc.vo.pu.m23.utils.FillBillInfoFor23;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.pf.IPFSourceBillFinder;
import nc.vo.pub.pf.SourceBillInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmf.ic.mbatchcode.BatchcodeVO;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.collections.CollectionUtils;

/**
 * <p>
 * <b>动作驱动前的校验接口,本类主要完成以下功能：</b>
 * <ul>
 * <li>校验当前驱动是否允许
 * <li>审批状态回调接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 下午06:08:41
 */
public class PFArriveVOCheck extends PfBeforeAndAfterAction implements
    IActionDriveChecker, IChangeVOCheck, ICheckStatusCallback,
    IPFSourceBillFinder {

  // 父类方法接口参数声明导致无法使用泛型
  @Override
  public Object afterAction(AggregatedValueObject reloadVO,
      Object objAfterAction, HashMap hmPfExParams) throws BusinessException {
    // 可以对动作处理后的VO进行处理
    return super.afterAction(reloadVO, objAfterAction, hmPfExParams);
  }

  // 父类方法接口参数声明导致无法使用泛型
  @Override
  public Object[] afterBatch(AggregatedValueObject[] reloadVOs,
      Object[] objsAfterAction, HashMap hmPfExParams) throws BusinessException {
    // 可以对动作处理后的VO进行处理
    // return super.afterBatch(reloadVOs, objsAfterAction, hmPfExParams);

    // 提前对VO处理 进行差异VO后计算属性丢失
    Map<String, BatchcodeVO> batchMap =
        this.getBatch((ArriveVO[]) objsAfterAction);
    Map<String, UFBoolean> bfixedrateMap =
        this.getBfixedrateMap((ArriveVO[]) objsAfterAction);

    Object[] reloadICBillVOs =
        super.afterBatch(reloadVOs, objsAfterAction, hmPfExParams);
    if (reloadICBillVOs == null || reloadICBillVOs.length <= 0) {
      return reloadICBillVOs;
    }
    // 补批次
    this.fillBatch((ArriveVO[]) reloadICBillVOs, batchMap);
    // 补固定换算率
    this.fillBfixedrate((ArriveVO[]) reloadICBillVOs, bfixedrateMap);
    return reloadICBillVOs;
  }

  @Override
  public void callCheckStatus(CheckStatusCallbackContext cscc)
      throws BusinessException {
    try {
      // 是否终止
      if (!cscc.isTerminate()) {
        return;
      }
      if (null == cscc.getBillVo()
          || !(cscc.getBillVo() instanceof AbstractBill)) {
        return;
      }
      AbstractBill billVO = (AbstractBill) cscc.getBillVo();
      if (billVO.getParentVO() == null) {
        return;
      }
      // 2013-1-29 流程终止，需要更新状态和审批信息，不更新审批信息，再次提交无法收回。
      VOUpdate<ISuperVO> bo = new VOUpdate<ISuperVO>();
      String[] names =
          new String[] {
            ArriveHeaderVO.FBILLSTATUS, ArriveHeaderVO.APPROVER,
            ArriveHeaderVO.TAUDITTIME
          };
      bo.update(new ISuperVO[] {
        billVO.getParent()
      }, names);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public boolean checkValidOrNeed(AggregatedValueObject srcBillVo,
      String srcAction, String destBilltype, String drivedAction)
      throws BusinessException {
    return true;
  }

  /**
   * 父类方法重写:支持下游单据给订单发送上游消息，查询采购订单返回平台要求信息
   * 
   * @see nc.vo.pub.pf.IPFSourceBillFinder#findSourceBill(java.lang.String,
   *      nc.vo.pub.AggregatedValueObject)
   */
  @Override
  public SourceBillInfo[] findSourceBill(String strUpBilltype, Object voThisBill) {
    // 数据合法性校验
    if (voThisBill == null || strUpBilltype == null) {
      return null;
    }
    // 只查询采购订单
    if (!strUpBilltype.startsWith(POBillType.Order.getCode())) {
      return null;
    }
    CircularlyAccessibleValueObject[] items =
        ((AggregatedValueObject) voThisBill).getChildrenVO();
    if (items == null || items.length == 0) {
      return null;
    }
    int iLen = items.length;
    ArrayList<String> listOrderId = new ArrayList<String>();
    for (int i = 0; i < iLen; i++) {
      if (POBillType.Order.getCode().equalsIgnoreCase(
          (String) items[i].getAttributeValue(ArriveItemVO.CSOURCETYPECODE))) {
        listOrderId.add((String) items[i]
            .getAttributeValue(ArriveItemVO.CSOURCEID));
      }
    }
    if (listOrderId.size() == 0) {
      return null;
    }
    // 返回
    SourceBillInfo[] voaRet = null;
    try {
      voaRet = this.getSourceBillInfo(listOrderId);
    }
    catch (Exception e) {
      //
    }
    return voaRet;
  }

  @Override
  public boolean isEnableDrive(String srcBilltype,
      AggregatedValueObject srcBillVO, String srcAction, String destBillType,
      String beDrivedActionName) throws BusinessException {
    return true;
  }

  private void fillBatch(ArriveVO[] bills, Map<String, BatchcodeVO> batchMap) {
    if (batchMap == null) {
      return;
    }
    BatchCodeFieldMap fields = new BatchCodeFieldMap();
    String[] calfields = fields.getBillFields();
    Map<String, String> billbatchm = fields.getBilltoBatchFields();
    String[] batchfields = new String[calfields.length];
    for (int i = 0; i < calfields.length; i++) {
      batchfields[i] = billbatchm.get(calfields[i]);
    }
    if (calfields.length <= 0 || calfields.length != batchfields.length) {
      return;
    }
    for (ArriveVO bill : bills) {
      // 批处理时，操作失败的单据返回为空
      if (bill == null) {
        continue;
      }

      for (ArriveItemVO body : bill.getBVO()) {
        if (body == null) {
          continue;
        }
        BatchcodeVO vo = batchMap.get(body.getPk_arriveorder_b());
        if (vo == null) {
          continue;
        }

        for (int i = 0, loop = calfields.length; i < loop; i++) {
          if (calfields[i] != null) {
            body.setAttributeValue(calfields[i],
                vo.getAttributeValue(batchfields[i]));
          }
        }

      }
    }
  }

  private void fillBfixedrate(ArriveVO[] reloadICBillVOs,
      Map<String, UFBoolean> bfixedrateMap) {
    if (null == reloadICBillVOs) {
      return;
    }
    for (ArriveVO arriveVO : reloadICBillVOs) {
      if (arriveVO == null) {
        continue;
      }
      for (ArriveItemVO itemvo : arriveVO.getBVO()) {
        String key = itemvo.getPk_arriveorder_b();
        itemvo.setBfixedrate(bfixedrateMap.get(key));
      }
    }
  }

  private Map<String, BatchcodeVO> getBatch(ArriveVO[] bills) {
    List<String> pk_batchcodes = new ArrayList<String>();
    Map<String, BatchcodeVO> retMap = new HashMap<String, BatchcodeVO>();
    if (null == bills) {
      return retMap;
    }
    for (ArriveVO bill : bills) {
      // 批处理时，操作失败的单据返回为空
      if (bill == null) {
        continue;
      }
      // Set<String> temp_pks =
      // VOEntityUtil.getVOsValueSet(bill.getBVO(), ArriveItemVO.PK_BATCHCODE);
      String[] temp_pks =
          (String[]) AggVOUtil.getDistinctItemFieldArray(
              new AggregatedValueObject[] {
                bill
              }, ArriveItemVO.PK_BATCHCODE, String.class);
      if (null == temp_pks) {
        continue;
      }
      for (int i = 0; i < temp_pks.length; i++) {
        pk_batchcodes.add(temp_pks[i]);
      }
    }
    if (CollectionUtils.isEmpty(pk_batchcodes)) {
      return retMap;
    }
    Map<String, BatchcodeVO> batchMap = new HashMap<String, BatchcodeVO>();
    try {
      BatchcodeVO[] batchcodes =
          NCLocator
              .getInstance()
              .lookup(IBatchcodePubService.class)
              .queryBatchcodesByPks(
                  pk_batchcodes.toArray(new String[pk_batchcodes.size()]));
      for (BatchcodeVO batchcodeVO : batchcodes) {
        batchMap.put(batchcodeVO.getPk_batchcode(), batchcodeVO);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    for (ArriveVO bill : bills) {
      // 批处理时，操作失败的单据返回为空
      if (bill == null) {
        continue;
      }
      for (ArriveItemVO body : bill.getBVO()) {
        if (null == body.getPk_batchcode() || "".equals(body.getPk_batchcode())
            || null == batchMap) {
          continue;
        }

        retMap.put(body.getPk_arriveorder_b(),
            batchMap.get(body.getPk_batchcode()));
      }
    }
    return retMap;
  }

  private Map<String, UFBoolean> getBfixedrateMap(ArriveVO[] objsAfterAction) {
    FillBillInfoFor23.fillBillInfo(objsAfterAction);
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    if (null == objsAfterAction) {
      return map;
    }
    for (ArriveVO arriveVO : objsAfterAction) {
      if (arriveVO == null) {
        continue;
      }
      for (ArriveItemVO itemvo : arriveVO.getBVO()) {
        String key = itemvo.getPk_arriveorder_b();
        UFBoolean value = itemvo.getBfixedrate();
        map.put(key, value);
      }
    }
    return map;
  }

  private SourceBillInfo[] getSourceBillInfo(ArrayList<String> listOrderId) {
    try {
      return NCLocator
          .getInstance()
          .lookup(IOrderPubQuery.class)
          .getOrderBillInfo(listOrderId.toArray(new String[listOrderId.size()]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
