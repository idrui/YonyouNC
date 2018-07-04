/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-25 ����02:44:17
 */
package nc.itf.pu.reference.ia;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ia.mi2.po.IIAI2ForPOConfirm;
import nc.pubitf.ia.mi2.po.IIAI2ForPOEstimate;
import nc.pubitf.ia.mi2.po.IIAI2ForPOVMIEstimate;
import nc.pubitf.ia.mi9.po.IIAI9ForPOFeeEstimate;
import nc.pubitf.ia.mi9.po.IIAI9ForPOVMIFeeEstimate;
import nc.vo.ia.mi2.entity.I2BillVO;
import nc.vo.ia.mi9.entity.I9BillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>IA�ṩ���ɹ�ȷ�Ϻ��ݹ��ķ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-25 ����02:44:17
 */
public class IAForEstConfirmServices {

  /**
   * ���������������ɹ���ⵥֱ��ȷ�ϳɱ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param bills
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-9 ����11:17:55
   */
  public static I2BillVO[] confirm(I2BillVO[] bills) {
    IIAI2ForPOConfirm service =
        NCLocator.getInstance().lookup(IIAI2ForPOConfirm.class);
    try {
      return service.insertI2ForPOConfirm(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * �����������������ɴ��������ݹ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param bills
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-9 ����10:55:23
   */
  public static I2BillVO[] estimate(I2BillVO[] bills) {
    IIAI2ForPOEstimate service =
        NCLocator.getInstance().lookup(IIAI2ForPOEstimate.class);
    I2BillVO[] retbills = null;
    try {
      retbills = service.insertI2ForPOEstimate(bills);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return retbills;
  }

  /**
   * �������������������ݹ�����I9(���������������)��
   * <p>
   * <b>����˵��</b>
   * 
   * @param bills I9
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-22 ����10:20:42
   */
  public static I9BillVO[] feeEstimate(I9BillVO[] bills) {
    IIAI9ForPOFeeEstimate service =
        NCLocator.getInstance().lookup(IIAI9ForPOFeeEstimate.class);
    try {
      return service.insertI9ForPOFeeEstimate(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * ��������������ȡ�������ݹ�I9���ݡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param stockHIDs ���ͷID
   * @param stockBIDs �����ID
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-22 ����10:23:06
   */
  public static void feeUnEstimate(String[] stockHIDS, String[] stockBIDs) {
    IIAI9ForPOFeeEstimate service =
        NCLocator.getInstance().lookup(IIAI9ForPOFeeEstimate.class);
    try {
      service.deleteI9ForPOCancelFeeEstimate(stockHIDS, stockBIDs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���������������ɹ���ⵥȡ��ֱ��ȷ�ϳɱ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param fiHIDs �ɹ����ͷIDs
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-9 ����11:19:06
   */
  public static void unConfirm(String[] fiHIDs,String[] fiBIDs) {
    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }
    IIAI2ForPOConfirm service =
        NCLocator.getInstance().lookup(IIAI2ForPOConfirm.class);
    try {
      service.deleteI2ForPOUnconfirm(fiHIDs,fiBIDs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ��������������ȡ�����������ݹ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param fiHIDs ���ͷID
   * @param fiBIDs �������ID
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-9 ����10:54:52
   */
  public static void unEstimate(String[] fiHIDs, String[] fiBIDs) {
    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }
    IIAI2ForPOEstimate service =
        NCLocator.getInstance().lookup(IIAI2ForPOEstimate.class);
    try {
      service.deleteI2ForPOUnestimate(fiHIDs, fiBIDs);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ��������������ȡ�����Ļ��ܴ��������ݹ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param fiHIDs VMIͷID
   * @param fiBIDs ��VMI��ID
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-9 ����10:54:52
   */
  public static void unVMIEstimate(String[] fiHIDs, String[] fiBIDs) {
    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }
    IIAI2ForPOVMIEstimate service =
        NCLocator.getInstance().lookup(IIAI2ForPOVMIEstimate.class);
    try {
      service.deleteI2ForPOVMIUnestimate(fiHIDs);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ��������������ȡ�����Ļ��ܷ����ݹ�I9���ݡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param stockHIDs VMIͷID
   * @param stockBIDs VMI��ID
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-22 ����10:23:06
   */
  public static void unVMIFeeEstimate(String[] stockHIDS, String[] stockBIDs) {
    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }
    IIAI9ForPOVMIFeeEstimate service =
        NCLocator.getInstance().lookup(IIAI9ForPOVMIFeeEstimate.class);
    try {
      service.deleteI9ForPOVMIFeeUnestimate(stockHIDS);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * �����������������Ļ����ݹ����ɴ��������ݹ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param bills I2��������
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-19 ����10:55:23
   */
  public static I2BillVO[] VMIEstimate(I2BillVO[] bills) {
    IIAI2ForPOVMIEstimate service =
        NCLocator.getInstance().lookup(IIAI2ForPOVMIEstimate.class);
    I2BillVO[] retbills = null;
    try {
      retbills = service.insertI2ForPOVMIEstimate(bills);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return retbills;
  }

  /**
   * �����������������Ļ��ܷ����ݹ�����I9(���������������)��
   * <p>
   * <b>����˵��</b>
   * 
   * @param bills I9
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-22 ����10:20:42
   */
  public static I9BillVO[] VMIFeeEstimate(I9BillVO[] bills) {
    IIAI9ForPOVMIFeeEstimate service =
        NCLocator.getInstance().lookup(IIAI9ForPOVMIFeeEstimate.class);
    try {
      return service.insertI9ForPOVMIFeeEstimate(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

}
