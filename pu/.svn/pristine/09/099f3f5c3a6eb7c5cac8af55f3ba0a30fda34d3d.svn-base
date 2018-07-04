package nc.vo.pu.pub.alert;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nc.ui.pub.print.IDataSource;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.Language;
import nc.vo.ml.LanguageVO;
import nc.vo.ml.MultiLangContext;

/**
 * 预警数据源(支持消息模板多语)
 * 
 * @since 6.0
 * @version 2011-10-20 下午01:32:06
 * @author wuxla
 */

public class PuAlertDataSource implements IDataSource {

  private static final long serialVersionUID = -1610496120703363787L;

  private int itemsize;

  private Map<String, Map<String, String[]>> mlData =
      new HashMap<String, Map<String, String[]>>();

  private int valuesize;

  public PuAlertDataSource() {
    this.putAll();
    String[] allitems = this.getAllDataItemExpress();
    if (allitems != null) {
      this.itemsize = allitems.length;
    }
  }

  /**
   * 适用于查询出id然后再放置值
   * 直接初始化data，因为大部分数据都不用查询档案，所以初始化后再put相应的档案值
   * 
   * @param values 顺序必须和getAllDataItemExpress()一致
   */
  public PuAlertDataSource(String[][] values) {
    this.putAll();
    String[] allitems = this.getAllDataItemExpress();
    if (allitems == null) {
      return;
    }
    this.itemsize = allitems.length;

    if (null == values || 0 == values.length) {
      return;
    }
    this.valuesize = values.length;
    for (int i = 0; i < this.valuesize; ++i) {
      for (int j = 0; j < this.itemsize; ++j) {
        this.putData(allitems[j], values[i][j], i);
        this.putData2(allitems[j], values[i][j], i);
        this.putData3(allitems[j], values[i][j], i);
        this.putData4(allitems[j], values[i][j], i);
        this.putData5(allitems[j], values[i][j], i);
        this.putData6(allitems[j], values[i][j], i);
      }
    }

  }

  /**
   * 适用于sql查询出结果
   * sql中需要加上所有name
   * 
   * @param values 顺序要一致，名称包含6个多语
   * @param multiLangItem 需要处理多语的字段
   */
  public PuAlertDataSource(String[][] values, Set<String> multiLangItem) {
    this.putAll();
    String[] allitems = this.getAllDataItemExpress();
    if (allitems == null) {
      return;
    }
    this.itemsize = allitems.length;
    if (null == values || 0 == values.length) {
      return;
    }
    if (null == multiLangItem || 0 == multiLangItem.size()) {
      for (int i = 0; i < this.valuesize; ++i) {
        for (int j = 0; j < this.itemsize; ++j) {
          this.putData(allitems[j], values[i][j], i);
          this.putData2(allitems[j], values[i][j], i);
          this.putData3(allitems[j], values[i][j], i);
          this.putData4(allitems[j], values[i][j], i);
          this.putData5(allitems[j], values[i][j], i);
          this.putData6(allitems[j], values[i][j], i);
        }
      }
      return;
    }

    for (int i = 0; i < this.valuesize; ++i) {
      int length = values[i].length;
      int k = 0;
      for (int j = 0; j < length;) {
        if (multiLangItem.contains(allitems[k])) {
          this.putData(allitems[k], values[i][j++], i);
          this.putData2(allitems[k], values[i][j++], i);
          this.putData3(allitems[k], values[i][j++], i);
          this.putData4(allitems[k], values[i][j++], i);
          this.putData5(allitems[k], values[i][j++], i);
          this.putData6(allitems[k], values[i][j++], i);
        }
        else {
          // 非多语字段也要把值放进去，只不过值都是一样的
          this.putData(allitems[k], values[i][j], i);
          this.putData2(allitems[k], values[i][j], i);
          this.putData3(allitems[k], values[i][j], i);
          this.putData4(allitems[k], values[i][j], i);
          this.putData5(allitems[k], values[i][j], i);
          this.putData6(allitems[k], values[i][j++], i);
        }
        ++k;
      }
    }
  }

  @Override
  public String[] getAllDataItemExpress() {
    return null;
  }

  @Override
  public String[] getAllDataItemNames() {
    return null;
  }

  @Override
  public String[] getDependentItemExpressByExpress(String itemExpress) {
    return null;
  }

  @Override
  public String[] getItemValuesByExpress(String itemExpress) {
    String langcode = this.getCurrLangCode();
    Map<String, String[]> values = this.mlData.get(langcode);

    return values.get(itemExpress);
  }

  @Override
  public String getModuleName() {
    return null;
  }

  @Override
  public boolean isNumber(String itemExpress) {
    return false;
  }

  /**
   * 往语言1中添加数据
   * 
   * @param key
   * @param value
   * @param serial 从0开始
   */
  public void putData(String key, String value, int serial) {
    this.putDataByLangSeqNo(Integer.valueOf(1), key, value, serial);
  }

  /**
   * 往语言2中添加数据
   * 
   * @param key
   * @param value
   * @param serial 从0开始
   */
  public void putData2(String key, String value, int serial) {
    this.putDataByLangSeqNo(Integer.valueOf(2), key, value, serial);
  }

  /**
   * 往语言3中添加数据
   * 
   * @param key
   * @param value
   * @param serial 从0开始
   */
  public void putData3(String key, String value, int serial) {
    this.putDataByLangSeqNo(Integer.valueOf(3), key, value, serial);
  }

  /**
   * 往语言4中添加数据
   * 
   * @param key
   * @param value
   * @param serial 从0开始
   */
  public void putData4(String key, String value, int serial) {
    this.putDataByLangSeqNo(Integer.valueOf(4), key, value, serial);
  }

  /**
   * 往语言5中添加数据
   * 
   * @param key
   * @param value
   * @param serial 从0开始
   */
  public void putData5(String key, String value, int serial) {
    this.putDataByLangSeqNo(Integer.valueOf(5), key, value, serial);
  }

  /**
   * 往语言6中添加数据
   * 
   * @param key
   * @param value
   * @param serial 从0开始
   */
  public void putData6(String key, String value, int serial) {
    this.putDataByLangSeqNo(Integer.valueOf(6), key, value, serial);
  }

  public void putDataByLangSeqNo(Integer langSeqNo, String key, String value,
      int serial) {
    Map<String, String[]> data = this.getMultLangDataBySeqNo(langSeqNo);
    String[] values = data.get(key);
    if (null == values) {
      values = new String[this.valuesize];
      data.put(key, values);
    }
    values[serial] = value;
  }

  /**
   * 适用于sql查询出结果
   * sql中需要加上所有name
   * 
   * @param values 顺序要一致，名称包含6个多语
   * @param multiLangItem 需要处理多语的字段
   */
  public void putDataByMuttiLangItems(String[][] values) {
    if (null == values || 0 == values.length) {
      return;
    }
    if (this.itemsize == 0) {
      return;
    }
    this.valuesize = values.length;
    Set<String> multiLangItem = this.getMultiLangItems();
    String[] allitems = this.getAllDataItemExpress();
    if (null == multiLangItem || 0 == multiLangItem.size()) {
      for (int i = 0; i < this.valuesize; ++i) {
        for (int j = 0; j < this.itemsize; ++j) {
          this.putData(allitems[j], values[i][j], i);
          this.putData2(allitems[j], values[i][j], i);
          this.putData3(allitems[j], values[i][j], i);
          this.putData4(allitems[j], values[i][j], i);
          this.putData5(allitems[j], values[i][j], i);
          this.putData6(allitems[j], values[i][j], i);
        }
      }
      return;
    }

    for (int i = 0; i < this.valuesize; ++i) {
      int length = values[i].length;
      int k = 0;
      for (int j = 0; j < length;) {
        if (multiLangItem.contains(allitems[k])) {
          this.putData(allitems[k], values[i][j++], i);
          this.putData2(allitems[k], values[i][j++], i);
          this.putData3(allitems[k], values[i][j++], i);
          this.putData4(allitems[k], values[i][j++], i);
          this.putData5(allitems[k], values[i][j++], i);
          this.putData6(allitems[k], values[i][j++], i);
        }
        else {
          // 非多语字段也要把值放进去，只不过值都是一样的
          this.putData(allitems[k], values[i][j], i);
          this.putData2(allitems[k], values[i][j], i);
          this.putData3(allitems[k], values[i][j], i);
          this.putData4(allitems[k], values[i][j], i);
          this.putData5(allitems[k], values[i][j], i);
          this.putData6(allitems[k], values[i][j++], i);
        }
        ++k;
      }
    }
  }

  private String getCurrLangCode() {
    // String langcode = InvocationInfoProxy.getInstance().getLangCode();
    String langcode =
        MultiLangContext.getInstance().getCurrentLangVO().getLangcode();
    if (StringUtil.isEmptyWithTrim(langcode)
        || !this.mlData.containsKey(langcode)) {
      langcode = Language.SIMPLE_CHINESE_CODE;
    }
    return langcode;
  }

  /**
   * 根据语种序号获取存储的信息
   * 
   * @param seqNo
   * @return
   */
  private Map<String, String[]> getMultLangDataBySeqNo(Integer seqNo) {
    LanguageVO[] langvos = MultiLangContext.getInstance().getEnableLangVOs();
    for (LanguageVO vo : langvos) {
      if (vo.getLangseq() != null && vo.getLangseq().equals(seqNo)) {
        return this.mlData.get(vo.getLangcode());
      }
    }
    return new HashMap<String, String[]>();
  }

  private void putAll() {
    LanguageVO[] langvos = MultiLangContext.getInstance().getEnableLangVOs();
    for (LanguageVO vo : langvos) {
      this.mlData.put(vo.getLangcode(), new HashMap<String, String[]>());
    }
  }

  protected Set<String> getMultiLangItems() {
    return null;
  }

}
