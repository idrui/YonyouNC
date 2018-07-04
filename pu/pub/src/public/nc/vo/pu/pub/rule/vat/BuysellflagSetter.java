package nc.vo.pu.pub.rule.vat;

import java.util.ArrayList;
import java.util.List;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.StringUtils;

/**
 * ���ù�������
 * ����ʱ�����˰����/����==��������/��������������Ϊ�����ڲɹ���������Ϊ�����ڲɹ���
 * ���ֱ�ͷ�ͱ���
 * 
 * @since 6.0
 * @version 2012-2-14 ����11:24:12
 * @author wuxla
 */
public class BuysellflagSetter {

  /**
   * ���ñ��幺������
   * <p>
   * ʹ�ó�����ֻ�ʺϵ���KeyValue����
   * <ul>
   * <li>
   * </ul>
   * 
   * @param rows
   */
  public List<Integer> setBodyBuysellFlag(IKeyValue keyValue, int[] rows) {
    List<Integer> changRows = new ArrayList<Integer>();
    for (int row : rows) {
      if (this.setBodyBuysellFlag(keyValue, row)) {
        changRows.add(Integer.valueOf(row));
      }
    }
    return changRows;
  }

  /**
   * <p>
   * ʹ�ó��������ñ��幺������
   * <ul>
   * <li>
   * </ul>
   */
  public MapList<Integer, Integer> setBodyBuysellFlag(IKeyValue[] keyValues) {
    MapList<Integer, Integer> changeBills = new MapList<Integer, Integer>();
    for (int billCount = 0; billCount < keyValues.length; billCount++) {
      IKeyValue keyValue = keyValues[billCount];
      for (int i = 0; i < keyValue.getItemCount(); ++i) {
        if (this.setBodyBuysellFlag(keyValue, i)) {
          changeBills.put(Integer.valueOf(billCount), Integer.valueOf(i));
        }
      }
    }
    return changeBills;
  }

  /**
   * <p>
   * ʹ�ó��������ñ�ͷ��������
   * <ul>
   * <li>
   * </ul>
   */
  public List<Integer> setHeadBuysellFlag(IKeyValue[] keyValues) {
    List<Integer> changeBills = new ArrayList<Integer>();
    for (int i = 0; i < keyValues.length; i++) {
      IKeyValue keyValue = keyValues[i];
      String ctaxcountryid =
          (String) keyValue.getHeadValue(PuAttrNameEnum.ctaxcountryid.name());
      String csendcountryid =
          (String) keyValue.getHeadValue(PuAttrNameEnum.csendcountryid.name());
      Integer oldValue =
          (Integer) keyValue.getHeadValue(PuAttrNameEnum.fbuysellflag.name());
      Integer newValue = this.getBuySellFlag(ctaxcountryid, csendcountryid);
      if (newValue.equals(oldValue)) {
        continue;
      }
      keyValue.setHeadValue(PuAttrNameEnum.fbuysellflag.name(), newValue);
      changeBills.add(Integer.valueOf(i));
    }
    return changeBills;
  }

  /**
   * <p>
   * ʹ�ó����������˰����/����==��������/��������������Ϊ�����ڲɹ���������Ϊ�����ڲɹ���
   * <ul>
   * <li>
   * </ul>
   * 
   * @param ctaxcountryid ��˰����/����
   * @param csendcountryid ��������/����
   * @return ��������
   */
  private Integer getBuySellFlag(String ctaxcountryid, String csendcountryid) {
    // ������һ��Ϊ�գ�Ĭ�ϴ���Ϊ���ڲɹ�����Ӱ���������ó�׵ı�־
    if (StringUtils.isEmpty(csendcountryid)
        || StringUtils.isEmpty(ctaxcountryid)) {
      return BuySellFlagEnum.NATIONAL_BUY.value();
    }
    else if (StringUtils.equals(ctaxcountryid, csendcountryid)) {
      return BuySellFlagEnum.NATIONAL_BUY.value();
    }
    return BuySellFlagEnum.IMPORT.value();
  }

  /**
   * ���ù������ͣ���������ֵ���ֵ�Ƿ�һ��
   * 
   * @param keyValue
   * @param row
   * @return ֵ�Ƿ�仯��true-ֵ�б仯��false-ֵδ��
   */
  private boolean setBodyBuysellFlag(IKeyValue keyValue, int row) {
    String ctaxcountryid =
        (String) keyValue
            .getBodyValue(row, PuAttrNameEnum.ctaxcountryid.name());
    String csendcountryid =
        (String) keyValue.getBodyValue(row,
            PuAttrNameEnum.csendcountryid.name());
    Integer oldValue =
        (Integer) keyValue
            .getBodyValue(row, PuAttrNameEnum.fbuysellflag.name());
    Integer newValue = this.getBuySellFlag(ctaxcountryid, csendcountryid);
    // ǰ��ֵһ��
    if (newValue.equals(oldValue)) {
      return false;
    }
    keyValue.setBodyValue(row, PuAttrNameEnum.fbuysellflag.name(), newValue);
    return true;

  }
}
