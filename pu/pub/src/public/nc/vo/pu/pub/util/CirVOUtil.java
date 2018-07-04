package nc.vo.pu.pub.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFTime;
import nc.vo.pubapp.pattern.log.Log;

import org.apache.commons.lang.ArrayUtils;

public class CirVOUtil {
  /**
   * ������������������CircularlyAccessibleValueObjectVO����KEY��VO��MAP��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author zhyhang
   * @time 2010-1-16 ����05:18:52
   */
  public static <T extends CircularlyAccessibleValueObject> Map<String, T> createKeyVOMap(
      T[] vos) {
    Map<String, T> retMap = new HashMap<String, T>();
    for (T item : vos) {
      if (null == item) {
        continue;
      }
      String bid = null;
      try {
        bid = item.getPrimaryKey();
      }
      catch (BusinessException e) {
        // ignore
      }
      if (!StringUtil.isEmptyWithTrim(bid)) {
        retMap.put(bid, item);
      }
    }
    return retMap;
  }

  /**
   * ����������������ǰheadvo����Դ�ۺ�vo��headvo�Ƚϣ��жϷǿ��ֶ��Ƿ�һ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param VOs - �ۺ�vo
   * @param curHeadVO - ��ͷvo
   * @param itemKeys - �ǿ��ֶ�key
   * @param includeDate - �Ƿ�����������ֶ�
   * @return
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-8 ����09:03:17
   */
  public static boolean existDifferNotNullItems(AggregatedValueObject[] VOs,
      CircularlyAccessibleValueObject curHeadVO, String[] itemKeys,
      boolean includeDate) {
    if (ArrayUtils.isEmpty(VOs) || curHeadVO == null
        || ArrayUtils.isEmpty(itemKeys)) {
      return false;
    }
    boolean exist = false;
    for (AggregatedValueObject vo : VOs) {
      exist =
          CirVOUtil.existDifferNotNullItems(vo.getParentVO(), curHeadVO,
              itemKeys, includeDate);
      if (exist) {
        return true;
      }
    }
    return false;
  }

  /**
   * ����������������ǰheadvo����Դheadvo�Ƚϣ��жϷǿ��ֶ��Ƿ�һ��
   * Ŀǰ���ڷ�Ʊ��������ʱ��ı�ͷvo�Ƚ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param srcHeadVO ��Դheadervo
   * @param curHeadVO ��ǰheadervo
   * @param itemKeys - �ǿ��ֶ�key
   * @param includeDate - �Ƿ���������
   * @return
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-8 ����01:26:40
   */
  public static boolean existDifferNotNullItems(
      CircularlyAccessibleValueObject srcHeadVO,
      CircularlyAccessibleValueObject curHeadVO, String[] itemKeys,
      boolean includeDate) {
    if (srcHeadVO == null || curHeadVO == null || ArrayUtils.isEmpty(itemKeys)) {
      return false;
    }
    for (String key : itemKeys) {
      Object srcAttr = srcHeadVO.getAttributeValue(key);
      Object curAttr = curHeadVO.getAttributeValue(key);
      if (srcAttr != null && curAttr != null && !srcAttr.equals(curAttr)) {
        if (!includeDate
            && (srcAttr instanceof UFDate || srcAttr instanceof UFDateTime || srcAttr instanceof UFTime)) {
          continue;
        }
        Log.debug(" different key:" + key + " srcValue=" + srcAttr.toString()
            + " curValue=" + curAttr.toString());
        return true;
      }
    }

    return false;
  }

  /**
   * �õ�VO�����а�ĳ����õ���MAP,ȡMAP������KEY�õ����д�KEY��ֵ
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voaPlan
   *          VO����
   * @param sField
   *          ������
   * @param T
   *          ������
   * @return ��ĳ����õ���MAP��HashMap<T,T>��TΪ���ֶε����͡�ʹ��ʱֻȡ��MAP��KEY���ɡ�
   *         <p>
   * @author wangyf
   * @param <E>
   * @time 2009-6-29 ����03:09:18
   */
  @SuppressWarnings("unchecked")
  public static <E> HashSet<E> getDistinctFieldSet(
      CircularlyAccessibleValueObject[] voaPlan, String sField) {
    HashSet<E> hsetValue = new HashSet<E>();
    if (voaPlan == null) {
      return hsetValue;
    }

    E oValue = null;
    int iLen = voaPlan.length;
    for (int j = 0; j < iLen; j++) {

      oValue = (E) voaPlan[j].getAttributeValue(sField);

      // �����ռ��ظ���
      if (!ObjectUtil.isEmptyWithTrim(oValue) && !hsetValue.contains(oValue)) {
        hsetValue.add(oValue);
      }
    }

    return hsetValue;

  }

  /**
   * ��VO��������ȡ��KEY��MAP�ṹ
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voaCircu
   *          VO[]
   * @param sFieldName
   *          KEY����
   * @return HashMap<>
   *         KEY������KEY�ĸ���ֵ��VALUE��ArrayList<CircularlyAccessibleValueObject
   *         >����KEY�µ�����VO��
   * @author wangyf
   * @time 2009-7-2 ����11:26:26
   */
  public static <T extends CircularlyAccessibleValueObject> HashMap<String, ArrayList<T>> getFieldVOList(
      T[] voaCircu, String sFieldName) {
    HashMap<String, ArrayList<T>> hmapRet = new HashMap<String, ArrayList<T>>();
    for (int i = 0; i < voaCircu.length; i++) {
      String key = (String) voaCircu[i].getAttributeValue(sFieldName);
      if (null == key || 0 == key.trim().length()) {
        continue;
      }
      ArrayList<T> list = null;
      if (!hmapRet.containsKey(key)) {
        list = new ArrayList<T>();
      }
      else {
        list = hmapRet.get(key);
      }
      list.add(voaCircu[i]);
      hmapRet.put(key, list);
    }
    return hmapRet;
  }
}
