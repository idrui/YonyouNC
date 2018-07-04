package nc.vo.pu.pub.util;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.pfflow01.BillbusinessVO;

public class BillbusinessVOUtil {

  /**
   * 方法功能描述：简要描述本方法的功能。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          流程VO数组
   * @param sBillType
   *          单据类型
   * @return <p>
   * @author wangyf
   * @time 2009-7-14 上午10:36:31
   */
  public static boolean isExistBillType(BillbusinessVO[] vosBusi,
      String sBillType) {

    if ((vosBusi == null) || (vosBusi.length == 0) || (sBillType == null)) {
      return false;
    }

    for (int i = 0; i < vosBusi.length; i++) {
      if (sBillType.equals(vosBusi[i].getPk_billtype())) {
        return true;
      }
    }

    return false;
  }

  /**
   * 方法功能描述：当前单据类型在指定业务流程下是否存在指定的上游单据类型
   * <p>
   * <b>参数说明</b>
   * 
   * @param groupId 集团pk
   * @param curBillType 当前单据类型
   * @param srcBillType 上游单据类型
   * @param businessType 业务流程pk
   * @return
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-7 下午11:10:26
   */
  public static boolean isExistSourceBillType(String groupId,
      String curBillType, String srcBillType, String businessType) {
    if (StringUtil.isEmptyWithTrim(groupId)
        || StringUtil.isEmptyWithTrim(curBillType)
        || StringUtil.isEmptyWithTrim(srcBillType)
        || StringUtil.isEmptyWithTrim(businessType)) {
      return false;
    }
    // 查找当前单据类型+业务流程对应的上游单据信息
    BillbusinessVO[] busiVOs =
        PfServiceScmUtil.querybillSource(groupId, curBillType, businessType,
            false);

    return BillbusinessVOUtil.isExistBillType(busiVOs, srcBillType);

  }
}
