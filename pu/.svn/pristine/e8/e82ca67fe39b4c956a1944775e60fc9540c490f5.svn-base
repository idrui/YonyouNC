package nc.vo.pu.pub.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.logging.Logger;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

@SuppressWarnings({
  "unchecked", "rawtypes"
})
public class AggVOUtil {

  /**
   * 方法功能描述：将主子VO数组，组合成AGGVO。<br>
   * 这里一般用于非元数据聚合VO的合并，也可以处理元数据的<br>
   * 但元数据的合并最好使用钟经理的工具类nc.vo.pubapp.pattern.model.tool.BillComposite
   * <p>
   * <b>参数说明</b>
   * 
   * @param <T>
   * @param headers 头
   * @param items 　体
   * @param billClass 　aggvo的类
   * @param headIdName 　头体相关联的外键
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-17 下午02:24:05
   */
  public static <T extends AggregatedValueObject> T[] createAggVO(
      CircularlyAccessibleValueObject[] headers,
      CircularlyAccessibleValueObject[] items, Class<T> billClass,
      String headIdName) {
    Map<String, T> newBillMap = new HashMap<String, T>();
    Set<String> dealtIds = new HashSet<String>();
    try {
      for (CircularlyAccessibleValueObject head : headers) {
        String hid = head.getPrimaryKey();
        if (newBillMap.containsKey(hid)) {
          continue;
        }
        // 建新的BILLVO
        T newBill = Constructor.construct(billClass);
        newBill.setParentVO(head);
        newBillMap.put(hid, newBill);
        if (null == items) {
          continue;
        }
        List<CircularlyAccessibleValueObject> children =
            new ArrayList<CircularlyAccessibleValueObject>();
        // 找表体VO
        for (CircularlyAccessibleValueObject item : items) {
          if (!dealtIds.contains(item.getPrimaryKey())
              && hid.equals(item.getAttributeValue(headIdName))) {
            children.add(item);
            dealtIds.add(item.getPrimaryKey());
          }
        }
        if (0 < children.size()) {
          CircularlyAccessibleValueObject[] childrenAry =
              new ListToArrayTool<CircularlyAccessibleValueObject>()
                  .convertToArray(children);
          newBill.setChildrenVO(childrenAry);
        }
      }
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    T[] bills = Constructor.construct(billClass, newBillMap.size());
    return newBillMap.values().toArray(bills);
  }

  /**
   * 方法功能描述：将聚合VO的表体生成［表头主键，表头］的Map
   * <p>
   * <b>参数说明</b>
   * 
   * @param <E>
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-2 下午09:21:36
   */
  public static <E extends CircularlyAccessibleValueObject> Map<String, E> createHeadMap(
      AggregatedValueObject[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    Map<String, E> retMap = new HashMap<String, E>();
    for (AggregatedValueObject vo : vos) {
      if (null == vo || null == vo.getParentVO()) {
        continue;
      }
      String id = null;
      try {
        id = vo.getParentVO().getPrimaryKey();
      }
      catch (BusinessException e) {
        // 不抛出异常
      }

      if (!StringUtil.isEmptyWithTrim(id)) {
        retMap.put(id, (E) vo.getParentVO());
      }
    }
    return retMap;
  }

  /**
   * 方法功能描述：将聚合VO的表体生成［表体主键，表体］的Map。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2009-12-31 下午03:15:17
   */
  public static <E extends CircularlyAccessibleValueObject> Map<String, E> createItemMap(
      AggregatedValueObject[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    Map<String, E> retMap = new HashMap<String, E>();
    for (AggregatedValueObject vo : vos) {
      if (null == vo || ArrayUtils.isEmpty(vo.getChildrenVO())) {
        continue;
      }
      for (CircularlyAccessibleValueObject item : vo.getChildrenVO()) {
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
          retMap.put(bid, (E) item);
        }
      }
    }
    return retMap;
  }

  /**
   * 方法功能描述：将聚合VO生成[表头主键，VO]的Map
   * <p>
   * <b>参数说明</b>
   * 
   * @param <E>
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-6 上午10:02:57
   */
  public static <E extends AggregatedValueObject> Map<String, E> createVOMap(
      AggregatedValueObject[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    Map<String, E> retMap = new HashMap<String, E>();
    for (AggregatedValueObject vo : vos) {
      if (null == vo || null == vo.getParentVO()) {
        continue;
      }

      String id = null;
      try {
        id = vo.getParentVO().getPrimaryKey();
      }
      catch (BusinessException e) {
        // 不抛出异常
      }

      if (!StringUtil.isEmptyWithTrim(id)) {
        retMap.put(id, (E) vo);
      }
    }

    return retMap;
  }

  /**
   * 方法功能描述：获取全vo中的itemvo的组合
   * <p>
   * <b>参数说明</b>
   * 
   * @param <T>
   * @param VOs
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-11 下午01:54:44
   */
  public static <T extends CircularlyAccessibleValueObject> T[] getCombinItemVOs(
      AggregatedValueObject[] VOs) {
    if (ArrayUtils.isEmpty(VOs)) {
      return null;
    }
    T[] itemVOs = null;
    for (AggregatedValueObject vo : VOs) {
      itemVOs = ArrayUtil.combinArrays(itemVOs, (T[]) vo.getChildrenVO());
    }
    return itemVOs;
  }

  public static <T extends CircularlyAccessibleValueObject> T[] getCombinItemVOs(
      AggregatedValueObject[] VOs, T[] itemVOs) {
    if (ArrayUtils.isEmpty(VOs) && ArrayUtils.isEmpty(itemVOs)) {
      return null;
    }
    T[] resutlItemVOs =
        (T[]) ArrayUtil.combinArrays(itemVOs, AggVOUtil.getCombinItemVOs(VOs));

    return resutlItemVOs;
  }

  /**
   * 方法功能描述：得到VO数组中按某个域得到的值数组 返回的数组中不含NULL或空串，并过滤重复值
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaPlan VO数组
   * @param sField 域名称
   * @param sFieldClass 域类型
   * @return <p>
   * @since 6.0
   * @author 王印芬
   * @time 2010-4-13 下午03:39:53
   */
  public static Object[] getDistinctFieldArray(
      CircularlyAccessibleValueObject[] voaPlan, String sField,
      Class sFieldClass) {
    if (null == voaPlan || null == sField) {
      return null;
    }

    HashMap mapValue = new HashMap();
    Object oValue = null;
    int iLen = voaPlan.length;
    for (int j = 0; j < iLen; j++) {

      oValue = voaPlan[j].getAttributeValue(sField);

      // 不含空及重复键
      if (!ObjectUtil.isEmptyWithTrim(oValue) && !mapValue.containsKey(oValue)) {
        mapValue.put(oValue, "");
      }
    }

    iLen = mapValue.size();
    if (iLen == 0) {
      return null;
    }

    Object[] oaRet = null;
    try {
      Class classRet = Class.forName(sFieldClass.getName());
      oaRet = (Object[]) java.lang.reflect.Array.newInstance(classRet, iLen);
    }
    catch (Exception e) {
      Logger
          .error("nc.vo.pu.pub.util.AggVOUtil.getDistinctFieldArray(CircularlyAccessibleValueObject [], String, Class)类型转换错误");
      return null;
    }

    mapValue.keySet().toArray(oaRet);
    return oaRet;
  }

  /**
   * 得到VO数组的头中按某个域的值数组 返回的数组中不含NULL或空串，并过滤重复值
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaCheck 用于检查的VO数组.每个VO的头体均饱满，无空值
   * @param sField 域
   * @param sFieldClass 域的类型
   * @param bNullAndDuplicateIncluded 是否包括空值及重复值
   * @return <p>
   * @author wangyf
   * @time 2009-6-29 下午03:26:59
   */
  public static Object[] getDistinctHeadFieldArray(
      AggregatedValueObject[] voaCheck, String sField, Class sFieldClass) {

    return AggVOUtil.getFieldArray(voaCheck, true, sField, sFieldClass, false);

  }

  /**
   * 得到VO数组的体中按某个域的值数组 返回的数组中不含NULL或空串，并过滤重复值
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaCheck 用于检查的VO数组.每个VO的头体均饱满，无空值
   * @param sField 域
   * @param sFieldClass 域的类型
   * @param bNullAndDuplicateIncluded 是否包括空值及重复值
   * @return <p>
   * @author wangyf
   * @time 2009-6-29 下午03:26:59
   */

  public static Object[] getDistinctItemFieldArray(
      AggregatedValueObject[] voaCheck, String sField, Class sFieldClass) {

    return AggVOUtil.getFieldArray(voaCheck, false, sField, sFieldClass, false);

  }

  /**
   * 方法功能描述：得到单据的表头主键数组。
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   * @return bills的表头主键数组 可能为null
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2009-12-29 上午11:23:45
   */
  public static String[] getPrimaryKeys(IBill[] bills) {
    if (ArrayUtils.isEmpty(bills)) {
      return null;
    }
    List<String> ids = new ArrayList<String>();
    for (IBill bill : bills) {
      ids.add(bill.getPrimaryKey());
    }
    return ids.toArray(new String[ids.size()]);
  }

  /**
   * 得到VO的主键
   * 
   * @param vos vo数组
   * @return
   */
  public static String[] getPrimaryKeys(ISuperVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    Set<String> ids = new HashSet<String>();
    for (ISuperVO vo : vos) {
      ids.add(vo.getPrimaryKey());
    }
    return ids.toArray(new String[ids.size()]);
  }

  /**
   * 判断一个单据是否自制
   * 
   * @param bill
   * @param srcIDName 来源单据ID字段名称
   * @return
   */
  public static boolean isSelfMade(IKeyValue bill, String srcIDName) {
    if (null == bill) {
      return false;
    }
    for (int i = 0, rows = bill.getItemCount(); i < rows; i++) {
      if (StringUtils.isNotBlank((String) bill.getBodyValue(i, srcIDName))) {
        return false;
      }
    }
    return true;

  }

  /**
   * 方法功能描述：筛选出聚合VO数组中为新增状态的VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author linsf
   * @time 2010-2-24 下午04:16:20
   */
  public static IBill[] pickupInsertVO(IBill[] vos) {
    List<IBill> newVos = new ArrayList<IBill>();
    for (IBill vo : vos) {
      if (VOStatus.NEW == vo.getParent().getStatus()) {
        newVos.add(vo);
      }
    }
    if (0 == newVos.size()) {
      return null;
    }
    return new ListToArrayTool<IBill>().convertToArray(newVos);
  }

  /**
   * 方法功能描述：筛选出聚合VO数组中为修改状态的VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author linsf
   * @time 2010-2-24 下午04:18:14
   */
  public static IBill[] pickupUpdateVO(IBill[] vos) {
    List<IBill> newVos = new ArrayList<IBill>();
    for (IBill vo : vos) {
      if (VOStatus.UPDATED == vo.getParent().getStatus()) {
        newVos.add(vo);
      }
    }
    if (0 == newVos.size()) {
      return null;
    }
    return new ListToArrayTool<IBill>().convertToArray(newVos);
  }

  /**
   * 方法功能描述：调整指定VO数组，按照参照的VO数组顺序排列。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos 　要排序的VO数组 两个VO数组必须有相同长度，且相同主键
   * @param referVos 　参照的VO数组
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-28 上午08:56:46
   */
  public static <T extends AggregatedValueObject> void reSortVO(T[] vos,
      T[] referVos) {
    Map<String, T> vomap = AggVOUtil.createVOMap(vos);
    for (int i = 0; i < referVos.length; i++) {
      String pk = null;
      try {
        pk = referVos[i].getParentVO().getPrimaryKey();
      }
      catch (BusinessException e) {
        Logger.error("Find the primary key error:" + e.getMessage());

      }
      vos[i] = vomap.get(pk);
    }
  }

  /**
   * 根据vo更新itemvo，要求都是具有主键的vo
   * 
   * @param vo
   * @param updateItemVOs
   */
  public static void updateItemVOs(AggregatedValueObject vo,
      CircularlyAccessibleValueObject[] updateItemVOs) {
    if (vo == null || ArrayUtils.isEmpty(updateItemVOs)) {
      return;
    }
    Map<String, CircularlyAccessibleValueObject> itemvoMap =
        new HashMap<String, CircularlyAccessibleValueObject>();
    try {
      for (CircularlyAccessibleValueObject item : vo.getChildrenVO()) {
        itemvoMap.put(item.getPrimaryKey(), item);
      }
      for (int i = 0; i < updateItemVOs.length; i++) {
        String itemPk = updateItemVOs[i].getPrimaryKey();
        if (itemvoMap.containsKey(itemPk)) {
          updateItemVOs[i] = itemvoMap.get(itemPk);
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 得到VO数组中按某个域得到的值数组 返回的数组中不含NULL或空串，并过滤重复值
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaCheck 用于检查的VO数组.每个VO的头体均饱满，无空值
   * @param bHead 如果是从头VO中取数组，则传true；如果是体，传false.
   * @param sField 域
   * @param sFieldClass 域的类型
   * @param bNullAndDuplicateIncluded 是否包括空值及重复值
   * @return <p>
   * @author wangyf
   * @time 2009-6-29 下午03:26:59
   */

  private static Object[] getFieldArray(AggregatedValueObject[] voaCheck,
      boolean bHead, String sField, Class sFieldClass,
      boolean bNullAndDuplicateIncluded) {

    // 参数正确性检查
    if (voaCheck == null || sField == null) {
      return null;
    }

    int iLen = voaCheck.length;
    Object oValue = null;
    CircularlyAccessibleValueObject[] voaItem = null;
    int iBodyLen = 0;
    ArrayList listValue = new ArrayList();
    for (int i = 0; i < iLen; i++) {
      if (voaCheck[i] == null) {
        continue;
      }
      if (bHead) {

        oValue = voaCheck[i].getParentVO().getAttributeValue(sField);

        if (bNullAndDuplicateIncluded) {
          listValue.add(oValue);
        }
        else {
          // 含空及重复键
          if (!ObjectUtil.isEmptyWithTrim(oValue)
              && !listValue.contains(oValue)) {
            listValue.add(oValue);
          }
        }
      }
      else {
        voaItem = voaCheck[i].getChildrenVO();
        if (voaItem != null) {
          iBodyLen = voaItem.length;
          for (int j = 0; j < iBodyLen; j++) {

            oValue = voaItem[j].getAttributeValue(sField);

            if (bNullAndDuplicateIncluded) {
              listValue.add(oValue);
            }
            else {
              // 含空及重复键
              if (!ObjectUtil.isEmptyWithTrim(oValue)
                  && !listValue.contains(oValue)) {
                listValue.add(oValue);
              }
            }
          }
        }
      }
    }

    iLen = listValue.size();
    if (iLen == 0) {
      return null;
    }

    Object[] oaRet = null;
    try {
      Class classRet = Class.forName(sFieldClass.getName());
      oaRet = (Object[]) java.lang.reflect.Array.newInstance(classRet, iLen);
    }
    catch (Exception e) {
      Logger
          .error("nc.vo.po.pub.OrderPubVO.getAllFieldArray(OrderVO [], Class, String, Class, boolean)类型转换错误");/*
                                                                                                               * -=
                                                                                                               * notranslate
                                                                                                               * =
                                                                                                               * -
                                                                                                               */
      return null;
    }

    listValue.toArray(oaRet);
    return oaRet;

  }
}
