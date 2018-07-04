/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-3 ����08:44:05
 */
package nc.itf.pu.reference.ia;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ia.mi2.po.I2AdjustBackData;
import nc.pubitf.ia.mi2.po.IIAI2ForPOSettle;
import nc.pubitf.ia.mi9.po.I9AdjustBackData;
import nc.pubitf.ia.mi9.po.IIAI9ForPOFeeSettle;
import nc.pubitf.ia.mi9.po.IIAI9ForPOSettle;
import nc.pubitf.ia.mig.po.IIAIGForPOFeeSettle;
import nc.pubitf.ia.mig.po.IIAIGForPOSettle;
import nc.vo.ia.mi2.entity.I2BillVO;
import nc.vo.ia.mi9.entity.I9BillVO;
import nc.vo.ia.mig.entity.IGBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>IA�ṩ���ɹ�����27�ķ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-3 ����08:44:05
 */
public class M27IAServices {

  /**
   * �ɹ�ȡ������ɾ������������������
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param csrcids ��Դ����ID(�ɹ����㵥ID)
   * @param csrcbids ��Դ������ID(�ɹ����㵥����ID)
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-30 ����11:33:05
   */
  public static void cancelSettleI9(String[] csrcids, String[] csrcbids)
      throws BusinessException {
    IIAI9ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI9ForPOSettle.class);
    iaSettle.deleteI9ForPOUnsettle(csrcids);
  }

  /**
   * �ɹ�ȡ������ɾ������������������
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param csrcids ��Դ����ID(�ɹ����㵥ID)
   * @param csrcbids ��Դ������ID(�ɹ����㵥����ID)
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-30 ����11:33:05
   */
  public static void cancelSettleIG(String[] csrcids, String[] csrcbids)
      throws BusinessException {
    IIAIGForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IIAIGForPOSettle.class);
    iaSettle.deleteIGForPOUnsettle(csrcids);
  }

  /**
   * ���������������ɹ����ý������ȡ������ʱɾ������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param csrcids ��Դ����ID(�ɹ����㵥ID)
   * @param csrcbids ��Դ������ID(�ɹ����㵥����ID)
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-8-19 ����01:37:32
   */
  public static void deleteI9ForPOCancelFeeSettle(String[] csrcids,
      String[] csrcbids) {
    if (csrcids == null || csrcbids == null) {
      return;
    }
    IIAI9ForPOFeeSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI9ForPOFeeSettle.class);
    try {
      iaSettle.deleteI9ForPOCancelFeeSettle(csrcids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���������������ɹ�ȡ�����ý���ɾ�������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param csrcids
   * @param csrcbids
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-2 ����03:14:12
   */
  public static void deleteIGForPOUnfeeSettle(String[] csrcids) {
    if (csrcids == null) {
      return;
    }
    IIAIGForPOFeeSettle iaSettle =
        NCLocator.getInstance().lookup(IIAIGForPOFeeSettle.class);
    try {
      iaSettle.deleteIGForPOUnfeeSettle(csrcids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * �ɹ���ͨ�������س��ڳ��ݹ��γɵĴ��������ֲɹ���ⵥ
   * 
   * @param bills �������ɹ���ⵥ����VO
   * @return �����Ĵ������ɹ���ⵥ����VO
   * @throws BusinessException
   */
  public static void insertI2ForPOQCSettleAdjustBack(I2BillVO[] voaIA)
      throws BusinessException {
    IIAI2ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI2ForPOSettle.class);
    iaSettle.insertI2ForPOQCSettleAdjustBack(voaIA);
  }

  /**
   * ���������������ɹ����ý���ʱ��������������������I9��
   * <p>
   * <b>����˵��</b>
   * 
   * @param bills
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-8-19 ����01:34:12
   */
  public static void insertI9ForPOFeeSettle(I9BillVO[] bills) {
    if (bills == null || bills.length == 0) {
      return;
    }
    IIAI9ForPOFeeSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI9ForPOFeeSettle.class);
    try {
      iaSettle.insertI9ForPOFeeSettle(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���������������ɹ����ý���س��ݹ��γɵĴ��������������(���ڷ��ý���ʱ�س�����ݹ���������I9)
   * <p>
   * <b>����˵��</b>
   * 
   * @param datas
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-2 ����03:16:52
   */
  public static void insertI9ForPOFeeSettleAdjustBack(I9AdjustBackData[] datas) {
    if (datas == null || datas.length == 0) {
      return;
    }
    IIAI9ForPOFeeSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI9ForPOFeeSettle.class);
    try {
      iaSettle.insertI9ForPOFeeSettleAdjustBack(datas);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���������������ɹ����ý������ɴ���������������(���ý�������IG)
   * <p>
   * <b>����˵��</b>
   * 
   * @param bills
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-2 ����03:13:22
   */
  public static void insertIGForPOFeeSettle(IGBillVO[] bills) {
    if (bills == null || bills.length == 0) {
      return;
    }
    IIAIGForPOFeeSettle iaSettle =
        NCLocator.getInstance().lookup(IIAIGForPOFeeSettle.class);
    try {
      iaSettle.insertIGForPOFeeSettle(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * �ɹ���ͨ����������ɴ������ɹ���ⵥ
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voaIA
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-30 ����11:32:05
   */
  public static void settleToI2(I2BillVO[] voaIA) throws BusinessException {
    IIAI2ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI2ForPOSettle.class);
    iaSettle.insertI2ForPOSettle(voaIA);
  }

  /**
   * �ɹ�����س��ݹ��γɵĴ������ɹ���ⵥ
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voaIA
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-30 ����11:32:38
   */
  public static void settleToI2ForAdjustBack(I2AdjustBackData[] voaBack)
      throws BusinessException {
    IIAI2ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI2ForPOSettle.class);
    // insertI2ForPOAdjustBack
    iaSettle.insertI2ForPOSettleAdjustBack(voaBack);
  }

  /**
   * �ɹ��������ɴ��������������
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voaIA
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-30 ����11:33:05
   */
  public static void settleToI9(I9BillVO[] bills) throws BusinessException {
    IIAI9ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI9ForPOSettle.class);
    iaSettle.insertI9ForPOSettle(bills);
  }

  /**
   * �ɹ��������ɴ��������������
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voaIA
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-30 ����11:33:05
   */
  public static void settleToIG(IGBillVO[] bills) throws BusinessException {
    IIAIGForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IIAIGForPOSettle.class);
    iaSettle.insertIGForPOSettle(bills);
  }

  /**
   * �ɹ�ȡ����ͨ�������ɾ���������Ĳɹ���ⵥ
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param csrcids ��Դ����ID(�ɹ����㵥ID)
   * @param csrcbids ��Դ������ID(�ɹ����㵥��ID)
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-30 ����11:33:05
   */
  public static void unsettleI2(String[] csrcids, String[] csrcbids)
      throws BusinessException {
    IIAI2ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI2ForPOSettle.class);
    iaSettle.deleteI2ForPOUnsettle(csrcids);
  }
}
