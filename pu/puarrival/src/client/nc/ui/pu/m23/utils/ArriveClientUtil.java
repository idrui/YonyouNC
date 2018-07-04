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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单客户端的工具类
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-2-26 上午09:51:38
 */
public class ArriveClientUtil {

  /**
   * 方法功能描述：清空指定的itemKey数组的值
   * <p>
   * <b>参数说明</b>
   *
   * @param card 卡片面板
   * @param itemKeys 需要进行清空的itemKey数组
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-25 上午10:00:10
   */
  public static void clearBodyCellValues(BillCardPanel card, int rowIndex,
      String[] itemKeys) {

    for (int j = 0, itemLength = itemKeys.length; j < itemLength; j++) {
      card.setBodyValueAt(null, rowIndex, itemKeys[j]);
    }
  }

  /**
   * 方法功能描述：清空表体中指定的itemKey数组的值
   * <p>
   * <b>参数说明</b>
   *
   * @param card 卡片面板
   * @param itemKeys 需要进行清空的itemKey数组
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-25 上午10:00:10
   */
  public static void clearBodyColoumValues(BillCardPanel card, String[] itemKeys) {

    for (int i = 0, rowCount = card.getRowCount(); i < rowCount; i++) {
      for (int j = 0, itemLength = itemKeys.length; j < itemLength; j++) {
        card.setBodyValueAt(null, i, itemKeys[j]);
      }
    }
  }

  /**
   * 方法功能描述：清空表体自由项的值
   * <p>
   * <b>参数说明</b>
   *
   * @param card <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-25 上午10:22:49
   */
  public static void clearFreeCellValues(BillCardPanel card, int rowIndwx) {

    // 自由项数组
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
   * 方法功能描述：清空指定的表头字段数组的值
   * <p>
   * <b>参数说明</b>
   *
   * @param card
   * @param itemKeys <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-29 上午09:47:15
   */
  public static void clearHeaderItemValues(BillCardPanel card, String[] itemKeys) {

    for (int i = 0, len = itemKeys.length; i < len; i++) {
      card.getHeadItem(itemKeys[i]).setValue(null);
    }
  }

  /**
   * 方法功能描述：得到表体指定单元格的boolean类型的值
   * <p>
   * <b>参数说明</b>
   *
   * @param card
   * @param rowIndex
   * @param itemKey
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-25 上午11:17:10
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
   * 方法功能描述：得到表头指定字段的String类型的值
   * <p>
   * <b>参数说明</b>
   *
   * @param card
   * @param itemKey
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-29 上午09:09:45
   */
  public static boolean getBooleanHeaderValue(BillCardPanel card, String itemKey) {
    Object headerValue = card.getHeadItem(itemKey).getValueObject();
    if (headerValue == null) {
      return false;
    }

    return ((Boolean) headerValue).booleanValue();
  }

  /**
   * 方法功能描述：得到表体指定单元格的Integer类型的值
   * <p>
   * <b>参数说明</b>
   *
   * @param card
   * @param rowIndex
   * @param itemKey
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-25 上午11:17:10
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
   * 方法功能描述：得到表体指定单元格的String类型的值
   * <p>
   * <b>参数说明</b>
   *
   * @param card
   * @param rowIndex
   * @param itemKey
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-25 上午11:17:10
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
   * 方法功能描述：得到表头指定字段的String类型的值
   * <p>
   * <b>参数说明</b>
   *
   * @param card
   * @param itemKey
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-29 上午09:09:45
   */
  public static String getStringHeaderValue(BillCardPanel card, String itemKey) {
    Object headerValue = card.getHeadItem(itemKey).getValueObject();
    if (headerValue == null) {
      return null;
    }

    return (String) headerValue;
  }

  /**
   * 方法功能描述：得到表体指定单元格的UFDate类型的值
   * <p>
   * <b>参数说明</b>
   *
   * @param card
   * @param rowIndex
   * @param itemKey
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-25 上午11:17:10
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
   * 方法功能描述：得到表体指定单元格的UFDouble类型的值
   * <p>
   * <b>参数说明</b>
   *
   * @param card
   * @param rowIndex
   * @param itemKey
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-25 上午11:17:10
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
   * 到货单上维护采购订单的到货关闭状态－隐藏计算属性字段
   *
   * @param bcp
   */
  public static void hideCloseOrderItem(BillCardPanel bcp) {
    bcp.getBodyPanel().hideTableCol(ArriveItemVO.BCLOSEORDER);
    bcp.getBodyPanel().hideTableCol(ArriveItemVO.NCANARRIVENUM);
  }

  /**
   * 方法功能描述：根据采购订单的交易类型ID来读取其属性“是否直运”，用于“收货仓库”是否过滤直运类仓库
   * <p>
   * <b>参数说明</b>
   *
   * @param pk_group
   * @param orderTransType
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-4-19 上午11:18:44
   */
  public static boolean isPODirectPurchase(String orderTransType) {
    boolean isDirect = false;
    try {
      // 属性“是否直运”
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
      throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0061")/*@res "查询订单交易类型时发生异常"*/, e);
    }
    return isDirect;
  }

  /**
   * 到货单上维护采购订单的到货关闭状态－显示计算属性字段
   *
   * @param bcp
   */
  public static void showCloseOrderItem(BillCardPanel bcp) {
    bcp.getBodyPanel().showTableCol(ArriveItemVO.BCLOSEORDER);
    bcp.getBodyPanel().showTableCol(ArriveItemVO.NCANARRIVENUM);
  }

}