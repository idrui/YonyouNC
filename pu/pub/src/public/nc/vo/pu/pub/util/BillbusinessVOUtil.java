package nc.vo.pu.pub.util;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.pfflow01.BillbusinessVO;

public class BillbusinessVOUtil {

  /**
   * ����������������Ҫ�����������Ĺ��ܡ�
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          ����VO����
   * @param sBillType
   *          ��������
   * @return <p>
   * @author wangyf
   * @time 2009-7-14 ����10:36:31
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
   * ����������������ǰ����������ָ��ҵ���������Ƿ����ָ�������ε�������
   * <p>
   * <b>����˵��</b>
   * 
   * @param groupId ����pk
   * @param curBillType ��ǰ��������
   * @param srcBillType ���ε�������
   * @param businessType ҵ������pk
   * @return
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-7 ����11:10:26
   */
  public static boolean isExistSourceBillType(String groupId,
      String curBillType, String srcBillType, String businessType) {
    if (StringUtil.isEmptyWithTrim(groupId)
        || StringUtil.isEmptyWithTrim(curBillType)
        || StringUtil.isEmptyWithTrim(srcBillType)
        || StringUtil.isEmptyWithTrim(businessType)) {
      return false;
    }
    // ���ҵ�ǰ��������+ҵ�����̶�Ӧ�����ε�����Ϣ
    BillbusinessVO[] busiVOs =
        PfServiceScmUtil.querybillSource(groupId, curBillType, businessType,
            false);

    return BillbusinessVOUtil.isExistBillType(busiVOs, srcBillType);

  }
}
