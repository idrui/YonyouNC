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
 * Ԥ������Դ(֧����Ϣģ�����)
 * 
 * @since 6.0
 * @version 2011-10-20 ����01:32:06
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
   * �����ڲ�ѯ��idȻ���ٷ���ֵ
   * ֱ�ӳ�ʼ��data����Ϊ�󲿷����ݶ����ò�ѯ���������Գ�ʼ������put��Ӧ�ĵ���ֵ
   * 
   * @param values ˳������getAllDataItemExpress()һ��
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
   * ������sql��ѯ�����
   * sql����Ҫ��������name
   * 
   * @param values ˳��Ҫһ�£����ư���6������
   * @param multiLangItem ��Ҫ���������ֶ�
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
          // �Ƕ����ֶ�ҲҪ��ֵ�Ž�ȥ��ֻ����ֵ����һ����
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
   * ������1���������
   * 
   * @param key
   * @param value
   * @param serial ��0��ʼ
   */
  public void putData(String key, String value, int serial) {
    this.putDataByLangSeqNo(Integer.valueOf(1), key, value, serial);
  }

  /**
   * ������2���������
   * 
   * @param key
   * @param value
   * @param serial ��0��ʼ
   */
  public void putData2(String key, String value, int serial) {
    this.putDataByLangSeqNo(Integer.valueOf(2), key, value, serial);
  }

  /**
   * ������3���������
   * 
   * @param key
   * @param value
   * @param serial ��0��ʼ
   */
  public void putData3(String key, String value, int serial) {
    this.putDataByLangSeqNo(Integer.valueOf(3), key, value, serial);
  }

  /**
   * ������4���������
   * 
   * @param key
   * @param value
   * @param serial ��0��ʼ
   */
  public void putData4(String key, String value, int serial) {
    this.putDataByLangSeqNo(Integer.valueOf(4), key, value, serial);
  }

  /**
   * ������5���������
   * 
   * @param key
   * @param value
   * @param serial ��0��ʼ
   */
  public void putData5(String key, String value, int serial) {
    this.putDataByLangSeqNo(Integer.valueOf(5), key, value, serial);
  }

  /**
   * ������6���������
   * 
   * @param key
   * @param value
   * @param serial ��0��ʼ
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
   * ������sql��ѯ�����
   * sql����Ҫ��������name
   * 
   * @param values ˳��Ҫһ�£����ư���6������
   * @param multiLangItem ��Ҫ���������ֶ�
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
          // �Ƕ����ֶ�ҲҪ��ֵ�Ž�ȥ��ֻ����ֵ����һ����
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
   * ����������Ż�ȡ�洢����Ϣ
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
