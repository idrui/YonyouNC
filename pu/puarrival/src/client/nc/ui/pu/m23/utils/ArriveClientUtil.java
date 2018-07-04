package nc.ui.pu.m23.utils;

import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ͻ��˵Ĺ�����
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-2-26 ����09:51:38
 */
public class ArriveClientUtil {

  /**
   * �����������������ָ����itemKey�����ֵ
   * <p>
   * <b>����˵��</b>
   *
   * @param card ��Ƭ���
   * @param itemKeys ��Ҫ������յ�itemKey����
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-25 ����10:00:10
   */
  public static void clearBodyCellValues(BillCardPanel card, int rowIndex,
      String[] itemKeys) {

    for (int j = 0, itemLength = itemKeys.length; j < itemLength; j++) {
      card.setBodyValueAt(null, rowIndex, itemKeys[j]);
    }
  }

  /**
   * ����������������ձ�����ָ����itemKey�����ֵ
   * <p>
   * <b>����˵��</b>
   *
   * @param card ��Ƭ���
   * @param itemKeys ��Ҫ������յ�itemKey����
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-25 ����10:00:10
   */
  public static void clearBodyColoumValues(BillCardPanel card, String[] itemKeys) {

    for (int i = 0, rowCount = card.getRowCount(); i < rowCount; i++) {
      for (int j = 0, itemLength = itemKeys.length; j < itemLength; j++) {
        card.setBodyValueAt(null, i, itemKeys[j]);
      }
    }
  }

  /**
   * ����������������ձ����������ֵ
   * <p>
   * <b>����˵��</b>
   *
   * @param card <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-25 ����10:22:49
   */
  public static void clearFreeCellValues(BillCardPanel card, int rowIndwx) {

    // ����������
    String[] freeItemArray = new String[10];
    freeItemArray[0] = ArriveItemVO.VFREE1;
    freeItemArray[1] = ArriveItemVO.VFREE2;
    freeItemArray[2] = ArriveItemVO.VFREE3;
    freeItemArray[3] = ArriveItemVO.VFREE4;
    freeItemArray[4] = ArriveItemVO.VFREE5;
    freeItemArray[5] = ArriveItemVO.VFREE6;
    freeItemArray[6] = ArriveItemVO.VFREE7;
    freeItemArray[7] = ArriveItemVO.VFREE8;
    freeItemArray[8] = ArriveItemVO.VFREE9;
    freeItemArray[9] = ArriveItemVO.VFREE10;

    ArriveClientUtil.clearBodyCellValues(card, rowIndwx, freeItemArray);
  }

  /**
   * �����������������ָ���ı�ͷ�ֶ������ֵ
   * <p>
   * <b>����˵��</b>
   *
   * @param card
   * @param itemKeys <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-29 ����09:47:15
   */
  public static void clearHeaderItemValues(BillCardPanel card, String[] itemKeys) {

    for (int i = 0, len = itemKeys.length; i < len; i++) {
      card.getHeadItem(itemKeys[i]).setValue(null);
    }
  }

  /**
   * ���������������õ�����ָ����Ԫ���boolean���͵�ֵ
   * <p>
   * <b>����˵��</b>
   *
   * @param card
   * @param rowIndex
   * @param itemKey
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-25 ����11:17:10
   */
  public static boolean getBooleanCellValue(BillCardPanel card, int rowIndex,
      String itemKey) {
    Object cellValue = card.getBodyValueAt(rowIndex, itemKey);
    if (cellValue == null) {
      return false;
    }

    return ((UFBoolean) cellValue).booleanValue();
  }

  /**
   * ���������������õ���ͷָ���ֶε�String���͵�ֵ
   * <p>
   * <b>����˵��</b>
   *
   * @param card
   * @param itemKey
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-29 ����09:09:45
   */
  public static boolean getBooleanHeaderValue(BillCardPanel card, String itemKey) {
    Object headerValue = card.getHeadItem(itemKey).getValueObject();
    if (headerValue == null) {
      return false;
    }

    return ((Boolean) headerValue).booleanValue();
  }

  /**
   * ���������������õ�����ָ����Ԫ���Integer���͵�ֵ
   * <p>
   * <b>����˵��</b>
   *
   * @param card
   * @param rowIndex
   * @param itemKey
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-25 ����11:17:10
   */
  public static Integer getIntegerCellValue(BillCardPanel card, int rowIndex,
      String itemKey) {
    Object cellValue = card.getBodyValueAt(rowIndex, itemKey);
    if (cellValue == null) {
      return null;
    }

    return (Integer) cellValue;
  }

  /**
   * ���������������õ�����ָ����Ԫ���String���͵�ֵ
   * <p>
   * <b>����˵��</b>
   *
   * @param card
   * @param rowIndex
   * @param itemKey
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-25 ����11:17:10
   */
  public static String getStringCellValue(BillCardPanel card, int rowIndex,
      String itemKey) {
    Object cellValue = card.getBodyValueAt(rowIndex, itemKey);
    if (cellValue == null) {
      return null;
    }

    return (String) cellValue;
  }

  /**
   * ���������������õ���ͷָ���ֶε�String���͵�ֵ
   * <p>
   * <b>����˵��</b>
   *
   * @param card
   * @param itemKey
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-29 ����09:09:45
   */
  public static String getStringHeaderValue(BillCardPanel card, String itemKey) {
    Object headerValue = card.getHeadItem(itemKey).getValueObject();
    if (headerValue == null) {
      return null;
    }

    return (String) headerValue;
  }

  /**
   * ���������������õ�����ָ����Ԫ���UFDate���͵�ֵ
   * <p>
   * <b>����˵��</b>
   *
   * @param card
   * @param rowIndex
   * @param itemKey
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-25 ����11:17:10
   */
  public static UFDate getUFDateCellValue(BillCardPanel card, int rowIndex,
      String itemKey) {
    Object cellValue = card.getBodyValueAt(rowIndex, itemKey);
    if (cellValue == null) {
      return null;
    }

    return (UFDate) cellValue;
  }

  /**
   * ���������������õ�����ָ����Ԫ���UFDouble���͵�ֵ
   * <p>
   * <b>����˵��</b>
   *
   * @param card
   * @param rowIndex
   * @param itemKey
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-25 ����11:17:10
   */
  public static UFDouble getUFDoubleCellValue(BillCardPanel card, int rowIndex,
      String itemKey) {
    Object cellValue = card.getBodyValueAt(rowIndex, itemKey);
    if (cellValue == null) {
      return null;
    }

    return (UFDouble) cellValue;
  }

  /**
   * ��������ά���ɹ������ĵ����ر�״̬�����ؼ��������ֶ�
   *
   * @param bcp
   */
  public static void hideCloseOrderItem(BillCardPanel bcp) {
    bcp.getBodyPanel().hideTableCol(ArriveItemVO.BCLOSEORDER);
    bcp.getBodyPanel().hideTableCol(ArriveItemVO.NCANARRIVENUM);
  }

  /**
   * �����������������ݲɹ������Ľ�������ID����ȡ�����ԡ��Ƿ�ֱ�ˡ������ڡ��ջ��ֿ⡱�Ƿ����ֱ����ֿ�
   * <p>
   * <b>����˵��</b>
   *
   * @param pk_group
   * @param orderTransType
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-4-19 ����11:18:44
   */
  public static boolean isPODirectPurchase(String orderTransType) {
    boolean isDirect = false;
    try {
      // ���ԡ��Ƿ�ֱ�ˡ�
      String[] trans = new String[1];
      trans[0] = PfServiceScmUtil.getTrantypecodeByid(orderTransType);
      String[] fields = new String[1];
      fields[0] = PoTransTypeVO.BDIRECT;
      IPoTransTypeQuery serv =
          NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
      HashMap<String, PoTransTypeVO> map = serv.queryAttrByTypes(trans, fields);
      if (map.get(orderTransType) != null) {
        PoTransTypeVO typeVO = map.get(orderTransType);
        isDirect = ValueUtils.getBoolean(typeVO.getBdirect());
      }
    }
    catch (Exception e) {
      throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0061")/*@res "��ѯ������������ʱ�����쳣"*/, e);
    }
    return isDirect;
  }

  /**
   * ��������ά���ɹ������ĵ����ر�״̬����ʾ���������ֶ�
   *
   * @param bcp
   */
  public static void showCloseOrderItem(BillCardPanel bcp) {
    bcp.getBodyPanel().showTableCol(ArriveItemVO.BCLOSEORDER);
    bcp.getBodyPanel().showTableCol(ArriveItemVO.NCANARRIVENUM);
  }

}