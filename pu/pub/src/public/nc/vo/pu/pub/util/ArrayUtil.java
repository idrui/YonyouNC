/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-12-29 上午09:59:31
 */
package nc.vo.pu.pub.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFDouble;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>完成一些数组实用处理
 * <li>判空
 * <li>基本类型和对象转换可以调用org.apache.commons.lang.ArrayUtils的公共方法
 * <li>
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2009-12-29 上午09:59:31
 */
public class ArrayUtil {

  /**
   * 方法功能描述：将多个同类型数组合并为一个新数组。
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param objArrays
   * @return 新数组，可能为null或零长
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2009-12-29 下午03:38:41
   */
  @SuppressWarnings("unchecked")
  public static <E> E[] combinArrays(E[]... objArrays) {
    if (objArrays.length == 0) {
      return null;
    }

    List<E> alCombinResult = new ArrayList<E>();
    E[] firstNotNull = null;
    for (int i = 0; i < objArrays.length; i++) {
      if (ArrayUtils.isEmpty(objArrays[i])) {
        continue;
      }
      if (null == firstNotNull) {
        firstNotNull = objArrays[i];
      }
      for (int j = 0; j < objArrays[i].length; j++) {
        alCombinResult.add(objArrays[i][j]);
      }
    }
    if (null == firstNotNull) {
      return null;
    }
    return alCombinResult.toArray((E[]) Array.newInstance(firstNotNull
        .getClass().getComponentType(), alCombinResult.size()));
  }

  /**
   * 方法功能描述：将Object数组转换为需要类型的数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param <E>
   * @param objs
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-16 下午04:34:37
   */
  @SuppressWarnings("unchecked")
  public static <E> E[] convertArrayType(Object[] objs) {
    if (ArrayUtils.isEmpty(objs)) {
      return null;
    }
    E[] convertArray = (E[]) Array.newInstance(objs[0].getClass(), objs.length);
    System.arraycopy(objs, 0, convertArray, 0, objs.length);
    return convertArray;
  }

  /**
   * 方法功能描述：int数组转Integer数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param list
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-26 上午09:57:30
   */
  // ArrayUtils.toObject
  // public static Integer[] intToInteger(int[] intArray) {
  //
  // if (ArrayUtils.isEmpty(intArray)) {
  // return null;
  // }
  //
  // Integer[] integerArray = new Integer[intArray.length];
  // int len = intArray.length;
  //
  // for (int i = 0; i < len; i++) {
  // integerArray[i] = Integer.valueOf(intArray[i]);
  // }
  //
  // return integerArray;
  // }
  /**
   * 方法功能描述：判断一个整型数组是否为空
   * <p>
   * <b>参数说明</b>
   * 
   * @param array
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-9 下午07:42:19
   */
  // public static boolean isEmpty(int[] array) {
  // if (null == array || 0 == array.length) {
  // return true;
  // }
  //
  // return false;
  // }
  /**
   * 方法功能描述：判断一个数组是否为空。
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param array
   * @return 如果null==array || array.length==0 返回空
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2009-12-29 上午10:02:31
   */
  // public static boolean isEmpty(Object[] array) {
  // return ArrayUtils.isEmpty(array);
  // }
  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param values
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-14 上午11:32:58
   */
  // public static int[] toPrimitive(Integer[] values) {
  // if (ArrayUtils.isEmpty(values)) {
  // return null;
  // }
  // return ArrayUtils.toPrimitive(values);
  // }
  /**
   * 交换数组顺序
   */
  public static void swap(Integer[] array, int i, int j) {
    Integer t = array[i];
    array[i] = array[j];
    array[j] = t;
  }

  /**
   * 交换数组顺序
   */
  public static void swap(String[] array, int i, int j) {
    String t = array[i];
    array[i] = array[j];
    array[j] = t;
  }

  /**
   * 交换数组顺序
   */
  public static void swap(UFDouble[] array, int i, int j) {
    UFDouble t = array[i];
    array[i] = array[j];
    array[j] = t;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param list
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-14 上午11:32:50
   */
  public static int[] toPrimitive(List<Integer> list) {
    if (ListUtil.isEmpty(list)) {
      return null;
    }
    return ArrayUtils.toPrimitive(list.toArray(new Integer[list.size()]));
  }
}
