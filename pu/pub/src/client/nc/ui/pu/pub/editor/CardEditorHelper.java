package nc.ui.pu.pub.editor;

import java.util.ArrayList;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>该类用来操作IBillCardEditor，主要用来向卡片界面上设置值，取值等操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-29 下午01:36:15
 */
public class CardEditorHelper implements IKeyValue {
  private BillCardPanel editor;

  public CardEditorHelper(BillCardPanel editor) {
    this.editor = editor;
  }

  public void clearBodyValue(int row, String[] keys) {
    if (ArrayUtils.isEmpty(keys)) {
      return;
    }
    for (String key : keys) {
      this.setBodyValue(row, key, null);
    }
  }

  public void clearBodyValue(int[] rows, String[] keys) {
    if (ArrayUtils.isEmpty(rows) || ArrayUtils.isEmpty(keys)) {
      return;
    }
    for (int row : rows) {
      this.clearBodyValue(row, keys);
    }
  }

  // 清空表体所有行对应的item
  public void clearBodyValue(String key) {
    if (StringUtil.isEmptyWithTrim(key)) {
      return;
    }
    int rows = this.getItemCount();
    for (int row = 0; row < rows; row++) {
      this.setBodyValue(row, key, null);
    }
  }

  public void clearHeadValue(String key) {
    if (StringUtil.isEmptyWithTrim(key)) {
      return;
    }
    this.setHeadValue(key, null);
  }

  public void clearHeadValue(String[] keys) {
    if (ArrayUtils.isEmpty(keys)) {
      return;
    }
    for (String key : keys) {
      this.clearHeadValue(key);
    }
  }

  public String getBodyStringValue(int row, String itemKey) {
    String itemValue =
        ValueUtils.getString(this.editor.getBodyValueAt(row, itemKey));
    return itemValue;
  }

  /**
   * 方法功能描述：取表体 ufboolean类型的值
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   * @param key
   * @return <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-8-13 上午11:09:24
   */
  public UFBoolean getBodyUFBooleanValue(int row, String key) {
    UFBoolean ret =
        ValueUtils.getUFBoolean(this.editor.getBodyValueAt(row, key));
    return ret;
  }

  public UFDate getBodyUFDateValue(int row, String itemKey) {
    UFDate itemValue =
        ValueUtils.getUFDate(this.editor.getBodyValueAt(row, itemKey));
    return itemValue;
  }

  public UFDouble getBodyUFDoubleValue(int row, String itemKey) {
    UFDouble itemValue =
        ValueUtils.getUFDouble(this.editor.getBodyValueAt(row, itemKey));
    return itemValue;
  }

  @Override
  public Object getBodyValue(int row, String itemKey) {
    return this.editor.getBodyValueAt(row, itemKey);
  }

  public int[] getDifferentRows(String itemKey, Object itemValue) {
    return this.getDifferentRows(itemKey, itemValue, false);
  }

  /**
   * 方法功能描述：获取与指定字段值不一致的行
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemKey
   *          字段名
   * @param itemValue
   *          字段值
   * @param changeRow
   *          是否置换字段值
   * @return 行数
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-3-31 下午06:28:47
   */
  public int[] getDifferentRows(String itemKey, Object itemValue,
      boolean changeRow) {

    String itemStringValue = ValueUtils.getString(itemValue);
    if (StringUtil.isEmptyWithTrim(itemStringValue)) {
      return null;
    }

    // 表体字段不存在
    if ((null == this.editor.getBodyItem(itemKey))
        || (this.editor.getRowCount() < 1)) {
      return null;
    }

    ArrayList<Integer> alRows = new ArrayList<Integer>();
    String value = null;
    for (int i = 0; i < this.editor.getRowCount(); i++) {
      value = this.getBodyStringValue(i, itemKey);
      if (itemStringValue.equals(value)) {
        continue;
      }
      alRows.add(Integer.valueOf(i));
      // 表体字段重新赋值
      if (changeRow) {
        this.editor.setBodyValueAt(itemValue, i, itemKey);
      }
    }
    if (alRows.size() == 0) {
      return null;
    }

    int[] rows =
        ArrayUtils.toPrimitive(alRows.toArray(new Integer[alRows.size()]));
    return rows;
  }

  /**
   * @return editor
   */
  public BillCardPanel getEditor() {
    return this.editor;
  }

  /**
   * 方法功能描述：获取表头可空/不可空字段key
   * <p>
   * <b>参数说明</b>
   * 
   * @param isNull
   *          是否可空 true - 不可空 false - 可空
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-8 上午11:38:07
   */
  public String[] getHeadItemKeys(boolean isNull) {
    BillItem[] headItems = this.getEditor().getHeadShowItems();
    ArrayList<String> filterItems = new ArrayList<String>();
    for (BillItem item : headItems) {
      if (item.isNull() == isNull) {
        filterItems.add(item.getKey());
      }
    }
    if (filterItems.size() == 0) {
      return null;
    }
    return filterItems.toArray(new String[filterItems.size()]);
  }

  public BillItem[] getHeadItems(boolean isNull) {
    BillItem[] headItems = this.getEditor().getHeadShowItems();
    ArrayList<BillItem> filterItems = new ArrayList<BillItem>();
    for (BillItem item : headItems) {
      if (item.isNull() == isNull) {
        filterItems.add(item);
      }
    }
    if (filterItems.size() == 0) {
      return null;
    }
    return filterItems.toArray(new BillItem[filterItems.size()]);
  }

  public String getHeadStringValue(String itemKey) {

    String itemValue =
        ValueUtils.getString(this.editor.getHeadItem(itemKey).getValueObject());
    return itemValue;
  }

  public UFDate getHeadUFDateValue(String itemKey) {

    UFDate itemValue =
        ValueUtils.getUFDate(this.editor.getHeadItem(itemKey).getValueObject());
    return itemValue;
  }

  public UFDouble getHeadUFDoubleValue(String itemKey) {

    UFDouble itemValue =
        ValueUtils.getUFDouble(this.editor.getHeadItem(itemKey)
            .getValueObject());
    return itemValue;
  }

  @Override
  public Object getHeadValue(String itemKey) {
    return this.editor.getHeadTailItem(itemKey).getValueObject();
  }

  @Override
  public int getItemCount() {
    return this.editor.getRowCount();
  }

  @Override
  public int getItemCount(String tableCode) {
    return this.editor.getBillData().getBillModel(tableCode).getRowCount();
  }

  /**
   * 方法功能描述：获取表头不可空字段key
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-8 上午11:41:04
   */
  public String[] getNotNullHeadItemKeys() {
    return this.getHeadItemKeys(true);
  }

  /**
   * 方法功能描述：获取表头可空字段key
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-8 上午11:41:17
   */
  public String[] getNullHeadItemKeys() {
    return this.getHeadItemKeys(false);
  }

  public int[] getRows(int rows) {
    int[] row = new int[rows];
    for (int i = 0; i < rows; i++) {
      row[i] = i;
    }
    return row;
  }

  @Override
  public RowStatus getRowStatus(int row) {
    int rowstate = this.editor.getBillModel().getRowState(row);
    if (rowstate == BillModel.NORMAL) {
      return RowStatus.UNCHANGED;
    }
    else if (rowstate == BillModel.MODIFICATION) {
      return RowStatus.UPDATED;
    }
    else if (rowstate == BillModel.ADD) {
      return RowStatus.NEW;
    }
    else if (rowstate == BillModel.DELETE) {
      return RowStatus.DELETED;
    }
    return RowStatus.UNCHANGED;
  }

  @Override
  public void setBodyValue(int row, String itemKey, Object value) {
    this.setBodyValue(row, itemKey, value,
        this.editor.getCurrentBodyTableCode());
  }

  @Override
  public void setBodyValue(int row, String itemKey, Object value,
      String tableCode) {
    this.editor.setBodyValueAt(value, row, itemKey, tableCode);
  }

  @Override
  public void setHeadValue(String itemKey, Object value) {
    this.editor.getHeadTailItem(itemKey).setValue(value);
  }

}
