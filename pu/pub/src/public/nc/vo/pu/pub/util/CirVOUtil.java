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
   * 方法功能描述：返回CircularlyAccessibleValueObjectVO数组KEY到VO的MAP。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author zhyhang
   * @time 2010-1-16 下午05:18:52
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
   * 方法功能描述：当前headvo与来源聚合vo的headvo比较，判断非空字段是否一致
   * <p>
   * <b>参数说明</b>
   * 
   * @param VOs - 聚合vo
   * @param curHeadVO - 表头vo
   * @param itemKeys - 非空字段key
   * @param includeDate - 是否包括日期型字段
   * @return
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-8 下午09:03:17
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
   * 方法功能描述：当前headvo与来源headvo比较，判断非空字段是否一致
   * 目前用于发票参照增行时候的表头vo比较
   * <p>
   * <b>参数说明</b>
   * 
   * @param srcHeadVO 来源headervo
   * @param curHeadVO 当前headervo
   * @param itemKeys - 非空字段key
   * @param includeDate - 是否处理日期型
   * @return
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-8 下午01:26:40
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
   * 得到VO数组中按某个域得到的MAP,取MAP的所有KEY得到所有此KEY的值
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaPlan
   *          VO数组
   * @param sField
   *          域名称
   * @param T
   *          域类型
   * @return 按某个域得到的MAP。HashMap<T,T>。T为该字段的类型。使用时只取此MAP的KEY即可。
   *         <p>
   * @author wangyf
   * @param <E>
   * @time 2009-6-29 下午03:09:18
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

      // 不含空及重复键
      if (!ObjectUtil.isEmptyWithTrim(oValue) && !hsetValue.contains(oValue)) {
        hsetValue.add(oValue);
      }
    }

    return hsetValue;

  }

  /**
   * 从VO数组中提取按KEY的MAP结构
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaCircu
   *          VO[]
   * @param sFieldName
   *          KEY名称
   * @return HashMap<>
   *         KEY：参数KEY的各个值，VALUE：ArrayList<CircularlyAccessibleValueObject
   *         >，此KEY下的所有VO。
   * @author wangyf
   * @time 2009-7-2 上午11:26:26
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
