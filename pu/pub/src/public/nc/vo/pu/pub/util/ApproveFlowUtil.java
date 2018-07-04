package nc.vo.pu.pub.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.pf.IPFWorkflowQry;
import nc.itf.uap.pf.metadata.IFlowBizItf;
import nc.md.MDBaseQueryFacade;
import nc.md.model.IBusinessEntity;
import nc.md.model.MetaDataException;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.SuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 审批流的一些公共处理
 * 
 * @since 6.0
 * @version 2011-6-28 下午05:01:09
 * @author zhaoyha
 */
public class ApproveFlowUtil {

  /**
   * 得到一组VO中审批通过的VO
   * 
   * @param vos
   * @return
   */
  public static AbstractBill[] filterApprovedVO(AbstractBill[] vos) {
    List<AbstractBill> filterVoLst = new ArrayList<AbstractBill>();
    for (AbstractBill vo : vos) {
      Integer status = ApproveFlowUtil.getBillStatus(vo);
      if (POEnumBillStatus.APPROVE.toInteger().equals(status)) {
        filterVoLst.add(vo);
      }
    }
    if (CollectionUtils.isEmpty(filterVoLst)) {
      return null;
    }
    return new ListToArrayTool<AbstractBill>().convertToArray(filterVoLst);
  }

  /**
   * 过滤出可进行审批中修改（修订）的VO，用于检查或其它业务处理
   * 
   * @param vos
   * @return 可能为null
   */
  public static <E extends AbstractBill> E[] filterApprovingEditReviseVO(E[] vos) {
    List<E> filterVOLst = new ArrayList<E>();
    for (E vo : vos) {
      Integer status = ApproveFlowUtil.getBillStatus(vo);
      String approver = ApproveFlowUtil.getApprover(vo);
      // 审批中且已经有人审批，才需要检查
      if (POEnumBillStatus.APPROVING.toInteger().equals(status)
          && StringUtils.isNotBlank(approver)
          && StringUtils.isNotBlank(vo.getPrimaryKey())) {
        filterVOLst.add(vo);
      }
    }
    if (filterVOLst.size() == 0) {
      return null;
    }
    return new ListToArrayTool<E>().convertToArray(filterVOLst);
  }

  /**
   * 得到一组VO中审批中的VO
   * 
   * @param vos
   * @return
   */
  public static AbstractBill[] filterApprovingVO(AbstractBill[] vos) {
    List<AbstractBill> filterVoLst = new ArrayList<AbstractBill>();
    for (AbstractBill vo : vos) {
      Integer status = ApproveFlowUtil.getBillStatus(vo);
      if (POEnumBillStatus.APPROVING.toInteger().equals(status)) {
        filterVoLst.add(vo);
      }
    }
    if (CollectionUtils.isEmpty(filterVoLst)) {
      return null;
    }
    return new ListToArrayTool<AbstractBill>().convertToArray(filterVoLst);
  }

  /**
   * 过滤出原来是审批过的，当前操作后取消审批的VO<br>
   * 即原始VO为审批通过状态，当前VO为非审批通过状态
   * 
   * @param vos
   * @param origVos
   * @return
   */
  public static AbstractBill[] filterUnApprovedVO(AbstractBill[] vos,
      AbstractBill[] origVos) {
    Map<String, AbstractBill> orgMap = AggVOUtil.createVOMap(origVos);
    List<AbstractBill> filterVoLst = new ArrayList<AbstractBill>();
    for (AbstractBill vo : vos) {
      Integer status = ApproveFlowUtil.getBillStatus(vo);
      Integer orgst =
          ApproveFlowUtil.getBillStatus(orgMap.get(vo.getPrimaryKey()));
      if (POEnumBillStatus.APPROVE.toInteger().equals(orgst)
          && !POEnumBillStatus.APPROVE.toInteger().equals(status)) {
        filterVoLst.add(vo);
      }
    }
    if (CollectionUtils.isEmpty(filterVoLst)) {
      return null;
    }
    return new ListToArrayTool<AbstractBill>().convertToArray(filterVoLst);

  }

  /**
   * 得到VO审批日期的属性名称
   * 
   * @param vo
   * @return
   */
  public static String getApproveDateAttName(AggregatedValueObject vo) {
    return ApproveFlowUtil.getIFlowBizItfMapKey(vo,
        IFlowBizItf.ATTRIBUTE_APPROVEDATE);
  }

  /**
   * 得到审批人
   * 
   * @param vo
   * @return
   */
  public static String getApprover(AggregatedValueObject vo) {
    String approverName = ApproveFlowUtil.getApproverAttName(vo);
    CircularlyAccessibleValueObject headvo = vo.getParentVO();
    return (String) headvo.getAttributeValue(approverName);
  }

  public static String getApproverAttName(AggregatedValueObject vo) {
    return ApproveFlowUtil.getIFlowBizItfMapKey(vo,
        IFlowBizItf.ATTRIBUTE_APPROVER);
  }

  /**
   * 得到单据状态
   * 
   * @param vo
   * @return
   */
  public static Integer getBillStatus(AggregatedValueObject vo) {
    String statusName = ApproveFlowUtil.getStatusAttName(vo);
    CircularlyAccessibleValueObject headvo = vo.getParentVO();
    return (Integer) headvo.getAttributeValue(statusName);
  }

  /**
   * 取得元数据实现的流程接口字段映射信息
   * 
   * @param vo
   * @param key
   * @return
   */
  public static String getIFlowBizItfMapKey(AggregatedValueObject vo, String key) {
    IBusinessEntity entity = null;
    try {
      entity =
          MDBaseQueryFacade.getInstance().getBusinessEntityByFullName(
              ((SuperVO) vo.getParentVO()).getMetaData().getEntityName());
    }
    catch (MetaDataException ex) {
      ExceptionUtils.wrappException(ex);
    }
    if (null != entity) {
      Map<String, String> map =
          entity.getBizInterfaceMapInfo(IFlowBizItf.class.getName());
      return map.get(key);
    }
    return null;
  }

  /**
   * 得到VO单据状态的属性名称
   * 
   * @param vo
   * @return
   */
  public static String getStatusAttName(AggregatedValueObject vo) {
    return ApproveFlowUtil.getIFlowBizItfMapKey(vo,
        IFlowBizItf.ATTRIBUTE_APPROVESTATUS);
  }

  /**
   * 支持审批中修改，判断可修改状态：<br>
   * 自由、审批不通过、审批中的直接可修改，至于审批中是否可修改由后台判断，按钮不处理
   * 
   * @param vo
   * @return
   */
  public static boolean isCanAppovingEdit(AggregatedValueObject vo) {
    Integer status = ApproveFlowUtil.getBillStatus(vo);
    // 自由、审批不通过、审批中的直接可修改，至于审批中是否可修改由后台判断，按钮不处理
    if (POEnumBillStatus.FREE.toInteger().equals(status)
        || POEnumBillStatus.NOPASS.toInteger().equals(status)
        || POEnumBillStatus.APPROVING.toInteger().equals(status)) {
      return true;
    }
    return false;
  }

  /**
   * 支持审批中修订，判断状态：<br>
   * 必须是审批中且已经有审批人的
   * 
   * @param vo
   * @return
   */
  public static boolean isCanAppovingRevise(AggregatedValueObject vo) {
    Integer status = ApproveFlowUtil.getBillStatus(vo);
    String approver = ApproveFlowUtil.getApprover(vo);
    if (POEnumBillStatus.APPROVING.toInteger().equals(status)
        && StringUtils.isNotBlank(approver)) {
      return true;
    }
    return false;
  }

  /**
   * 自由、审批不通过的直接删除
   * 
   * @param vo
   * @return
   */
  public static boolean isCanDel(AggregatedValueObject vo) {
    Integer status = ApproveFlowUtil.getBillStatus(vo);
    // 自由、审批不通过的直接删除
    if (POEnumBillStatus.FREE.toInteger().equals(status)
        || POEnumBillStatus.NOPASS.toInteger().equals(status)) {
      return true;
    }
    return false;
  }

  /**
   * 自由、审批不通过的直接可修改，审批中但还未审批过的也可修改
   * 
   * @param vo
   * @return
   */
  public static boolean isCanEdit(AggregatedValueObject vo) {
    Integer status = ApproveFlowUtil.getBillStatus(vo);
    // 自由、审批不通过的直接可修改，审批中但还未审批过的也可修改
    if (POEnumBillStatus.FREE.toInteger().equals(status)
        || POEnumBillStatus.NOPASS.toInteger().equals(status)
        || POEnumBillStatus.APPROVING.toInteger().equals(status)) {
      return true;
    }
    return false;
  }

  /**
   * 根据VO状态判断是否可提交<br>
   * 只有自由状态的可提交
   * 
   * @param vo
   * @return
   */
  public static boolean isCanSendApprove(AggregatedValueObject vo) {
    Integer status = ApproveFlowUtil.getBillStatus(vo);
    if (POEnumBillStatus.FREE.toInteger().equals(status)) {
      return true;
    }
    return false;
  }

  /**
   * 根据VO状态判断是否可取消审批<br>
   * 审批通过、未通过、审批中均可弃审
   * 
   * @param vo
   * @return
   */
  public static boolean isCanUnApprove(AggregatedValueObject vo) {
    Integer status = ApproveFlowUtil.getBillStatus(vo);
    if (POEnumBillStatus.APPROVE.toInteger().equals(status)
        || POEnumBillStatus.NOPASS.toInteger().equals(status)
        || POEnumBillStatus.APPROVING.toInteger().equals(status)) {
      return true;
    }
    return false;
  }

  /**
   * 根据审批流的要求,单据修改保存时,如果当前状态是审批不通过,则将状态置为自由态<br>
   * 并且清空审批人等
   * 
   * @param billvos
   * @author wangljc
   * @date 2011-6-28
   */
  public static void processBillStatusWhenEdit(AbstractBill[] billvos) {
    if (ArrayUtils.isEmpty(billvos)) {
      return;
    }

    for (int i = 0; i < billvos.length; i++) {
      String statusName = ApproveFlowUtil.getStatusAttName(billvos[i]);
      String approverName = ApproveFlowUtil.getApproverAttName(billvos[i]);
      String appDateName = ApproveFlowUtil.getApproveDateAttName(billvos[i]);
      CircularlyAccessibleValueObject headvo = billvos[i].getParentVO();
      Integer status = (Integer) headvo.getAttributeValue(statusName);
      if (POEnumBillStatus.NOPASS.toInteger().equals(status)) {
        headvo.setAttributeValue(statusName, POEnumBillStatus.FREE.toInteger());
        headvo.setAttributeValue(approverName, null);
        headvo.setAttributeValue(appDateName, null);
        headvo.setStatus(VOStatus.UPDATED);
      }
    }
  }

  /**
   * 设置制单人、制单日期（取业务日期）
   * 
   * @param vos
   */
  public static void setBillMakeInfo(AggregatedValueObject[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (AggregatedValueObject vo : vos) {
      if (vo.getParentVO().getAttributeValue(PUQueryConst.BILLMAKER) == null) {
        vo.getParentVO().setAttributeValue(PUQueryConst.BILLMAKER,
            AppContext.getInstance().getPkUser());
      }
      if (vo.getParentVO().getAttributeValue(PUQueryConst.DMAKEDATE) == null) {
        vo.getParentVO().setAttributeValue(PUQueryConst.DMAKEDATE,
            AppContext.getInstance().getBusiDate());
      }
    }
  }
  /**
   * 审批中单据修改，判断当前操作人是否当前审批人，如果不是，则不允许修改！
   * @param vo 单据VO
   * @param vtrantypecodename 交易类型编码字段名
   */
  public static void userCanEditCheck(AbstractBill vo,String vtrantypecodename){
		Integer status = ApproveFlowUtil.getBillStatus(vo);
		ISuperVO head = vo.getParent(); 
		if(POEnumBillStatus.APPROVING.toInteger().equals(status)){
			String billid = head.getPrimaryKey();
			String vtrantypecode = (String) head.getAttributeValue(vtrantypecodename);
			String user = AppContext.getInstance().getPkUser();
			boolean isApprover = false;
			try {
				isApprover  =  NCLocator.getInstance().lookup(IPFWorkflowQry.class)
           .isCheckman(billid, vtrantypecode, user);
     }
     catch (BusinessException e) {
       ExceptionUtils.wrappException(e);
     }
     if (!isApprover) {
       ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().
      		 getStrByID("4004000_0", "04004000-0165")/*
      		 																					*@res
      		 																					*当前操作人不是单据当前审批人，不具有修改权限！
      		 																					*/);
     }
		}
	}

}
