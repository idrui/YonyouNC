/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-8 上午09:21:05
 */
package nc.impl.pu.m21.onway.bp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderOnwayHeaderVO;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21.entity.OrderOnwayVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21.pub.TrantypeUtil;
import nc.vo.pu.m21.rule.OnwayValidationTool;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>在途状态工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-8 上午09:21:05
 */
public class OnwayBpTools {

  /**
   * 方法功能描述：生成页面显示用表体VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param statusOnwayVO
   * @param orderBVOMap
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-29 上午09:04:12
   */
  public static OrderOnwayItemVO creatOnwayBVO(StatusOnWayItemVO statusOnwayVO,
      Map<String, OrderItemVO> orderBVOMap, Map<String, UFDouble> accumNumMap,
      Map<String, UFDouble> nextStatusAccNumMap) {
    OrderOnwayItemVO onwayBVO = new OrderOnwayItemVO();
    String pk_order_b = statusOnwayVO.getPk_order_b();
    OrderItemVO orderBVO = orderBVOMap.get(pk_order_b);

    UFDouble onwayNum = MathTool.nvl(statusOnwayVO.getNonwaynum());

    // 过滤在途数量小于0的项,还要过滤累计操作数量大于确认数量（无确认数量时取主数量为确认数量）的项
    if (MathTool.compareTo(onwayNum, UFDouble.ZERO_DBL) <= 0) {
      return null;
    }
    // 过滤掉下一状态的操作累计数量大于确认数量的项
    UFDouble nextStatusAccNum =
        MathTool.nvl(nextStatusAccNumMap.get(pk_order_b));
    UFDouble nmaxhandlenum = MathTool.nvl(statusOnwayVO.getNmaxhandlenum());
    if (MathTool.compareTo(nmaxhandlenum, nextStatusAccNum) <= 0) {
      return null;
    }

    // 累计数量
    UFDouble accumNum = MathTool.nvl(accumNumMap.get(pk_order_b));
    onwayBVO.setNaccunum(accumNum);
    // 在途数量
    onwayBVO.setNonwaynum(onwayNum);
    // 本次发货金额
    onwayBVO.setNsendoutnum(onwayNum);
    // 在途状态
    onwayBVO.setFonwaystatus(statusOnwayVO.getFonwaystatus());
    // 单据编号
    onwayBVO.setVbillcode(statusOnwayVO.getVbillcode());
    // 单据日期
    onwayBVO.setDbilldate(statusOnwayVO.getDbilldate());
    // 计划到货日期
    onwayBVO.setDplanarrvdate(statusOnwayVO.getDplanarrvdate());
    // 装船港口
    onwayBVO.setCloadport(statusOnwayVO.getCloadport());
    // 到货港口
    onwayBVO.setClandport(statusOnwayVO.getClandport());
    // 船名
    onwayBVO.setCshipname(statusOnwayVO.getCshipname());
    // 船次号
    onwayBVO.setCshipline(statusOnwayVO.getCshipline());
    // 计划到港日期
    onwayBVO.setDplanfreightdate(statusOnwayVO.getDplanfreightdate());
    // 货柜号
    onwayBVO.setCcasecode(statusOnwayVO.getCcasecode());
    // 承运商
    onwayBVO.setCcarrier(statusOnwayVO.getCcarrier());

    // 采购订单在途
    // 此处不将子子表主键清空
    // String pk_order_bb = onwayBVO.getPk_order_bb();
    // if (pk_order_bb == null) {
    // DBTool dao = new DBTool();
    // String[] ids = dao.getOIDs(1);
    // pk_order_bb = ids[0];
    // }
    // onwayBVO.setPk_order_bb(pk_order_bb);
    // onwayBVO.setPrimaryKey(pk_order_bb);
    onwayBVO.setPk_order_bb(statusOnwayVO.getPk_order_bb());

    // 采购订单明细
    onwayBVO.setPk_order_b(orderBVO.getPk_order_b());
    // 采购订单
    onwayBVO.setPk_order(orderBVO.getPk_order());
    // 采购组织版本
    onwayBVO.setPk_org(orderBVO.getPk_org());
    // 采购组织
    onwayBVO.setPk_org_v(orderBVO.getPk_org_v());
    // 所属集团
    onwayBVO.setPk_group(orderBVO.getPk_group());
    // 行状态
    // onwayBVO.setForderrowstatus(orderBVO.getForderrowstatus());
    // 物料版本信息
    onwayBVO.setPk_material(orderBVO.getPk_material());
    // 主单位
    onwayBVO.setCunitid(orderBVO.getCunitid());
    // 主数量
    onwayBVO.setNnum(orderBVO.getNnum());
    // 主无税净价
    onwayBVO.setNorignetprice(orderBVO.getNorignetprice());
    // 行号
    onwayBVO.setCrowno(orderBVO.getCrowno());
    // 原币无税金额
    onwayBVO.setNorigmny(orderBVO.getNorigmny());
    // 确认数量
    // onwayBVO.setNconfirmnum(rowset.getUFDouble(25));
    onwayBVO.setTs(statusOnwayVO.getTs());
    // 表头自定义项
    OnwayBpTools.getBodyVbdef(onwayBVO, statusOnwayVO);

    return onwayBVO;
  }

  /**
   * 方法功能描述：生成页面显示用HVOMap
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-28 下午03:30:09
   */
  public static Map<String, OrderOnwayHeaderVO> creatOnwayHVOMap(
      OrderVO[] orderVOs) {
    Map<String, OrderOnwayHeaderVO> onwayHVOMap =
        new HashMap<String, OrderOnwayHeaderVO>();

    for (OrderVO orderVO : orderVOs) {
      OrderHeaderVO hvo = orderVO.getHVO();
      String pk_order = hvo.getPk_order();

      OrderOnwayHeaderVO onwayHVO = OnwayBpTools.orderHVOToOnwayHVO(hvo);

      onwayHVOMap.put(pk_order, onwayHVO);
    }

    return onwayHVOMap;
  }

  /**
   * 方法功能描述：取得子表VO的Map
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-28 下午03:40:01
   */
  public static Map<String, OrderItemVO> creatOrderBVOMap(OrderVO[] orderVOs) {

    Map<String, OrderItemVO> orderBVOMap = new HashMap<String, OrderItemVO>();

    for (OrderVO orderVO : orderVOs) {
      OrderItemVO[] bvos = orderVO.getBVO();
      for (OrderItemVO bvo : bvos) {
        orderBVOMap.put(bvo.getPk_order_b(), bvo);
      }
    }

    return orderBVOMap;
  }

  /**
   * 方法功能描述：生成页面显示用AggVO
   * <p>
   * <b>参数说明</b>
   * 
   * @param onwayHVOMap
   * @param onwayBVOMapList
   * @param statusItemVOMap
   * @param orderOnwayVOList
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-28 下午04:53:36
   */
  public static OrderOnwayVO[] doCreatOnwayAggVO(
      Map<String, OrderOnwayHeaderVO> onwayHVOMap,
      MapList<String, OrderOnwayItemVO> onwayBVOMapList,
      Map<String, StatusOnWayItemVO> statusItemVOMap) {

    List<OrderOnwayVO> orderOnwayVOList = new ArrayList<OrderOnwayVO>();

    Set<Map.Entry<String, List<OrderOnwayItemVO>>> onwayBVOSet =
        onwayBVOMapList.toMap().entrySet();

    for (Map.Entry<String, List<OrderOnwayItemVO>> onwayBVOMapEntry : onwayBVOSet) {
      String pk_order = onwayBVOMapEntry.getKey();
      List<OrderOnwayItemVO> onwayBVOList = onwayBVOMapEntry.getValue();

      // 过滤表体为空的订单
      if (!ListUtil.isEmpty(onwayBVOList)) {
        // 设置表头自定义项
        OrderOnwayHeaderVO onwayHVO = onwayHVOMap.get(pk_order);
        OnwayBpTools.getHeadVbdef(onwayHVO, statusItemVOMap.get(pk_order));

        OrderOnwayVO orderOnwayVO = new OrderOnwayVO();

        // 设置表头vo
        orderOnwayVO.setHVO(onwayHVO);
        OrderOnwayItemVO[] onwayBVOArray =
            onwayBVOList.toArray(new OrderOnwayItemVO[onwayBVOList.size()]);
        // 设置标题vo
        orderOnwayVO.setBVO(onwayBVOArray);

        orderOnwayVOList.add(orderOnwayVO);
      }
    }

    return orderOnwayVOList.toArray(new OrderOnwayVO[orderOnwayVOList.size()]);
  }

  /**
   * 方法功能描述：取得所有子表pk,返回以主键为key,vo为value的map
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVOs
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-21 下午03:01:26
   */
  public static Map<String, OrderItemVO> getBodyPk(List<OrderVO> orderVOs) {

    if (orderVOs == null) {
      return new HashMap<String, OrderItemVO>();
    }

    Map<String, OrderItemVO> pkMap = new HashMap<String, OrderItemVO>();

    for (OrderVO orderVO : orderVOs) {
      OrderItemVO[] itemVOs = orderVO.getBVO();
      for (OrderItemVO itemVO : itemVOs) {
        pkMap.put(itemVO.getPk_order_b(), itemVO);
      }
    }
    return pkMap;
  }

  /**
   * 方法功能描述：取得子表pk,传入的数组为pk_order,pk_order_b,pk_order_bb的二维数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param pks
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-26 下午03:24:38
   */
  public static String[] getBodyPksByTwoDimStr(String[][] pks) {

    int firstDimLen = pks.length;

    Set<String> subPkSet = new HashSet<String>();

    for (int firstDim = 0; firstDim < firstDimLen; firstDim++) {
      subPkSet.add(pks[firstDim][1]);
    }
    return subPkSet.toArray(new String[subPkSet.size()]);
  }

  /**
   * 方法功能描述：取得表体自定义项
   * <p>
   * <b>参数说明</b>
   * 
   * @param onwayBVO
   * @param rowset
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-13 下午01:31:02
   */
  public static void getHeadVbdef(OrderOnwayHeaderVO onwayHVO,
      StatusOnWayItemVO onwayVO) {
    for (int i = 0; i < 40; i++) {
      String vhdef = "vhdef" + String.valueOf(i + 1);
      onwayHVO.setAttributeValue(vhdef, onwayVO.getAttributeValue(vhdef));
    }
  }

  /**
   * 方法功能描述：取得下一状态
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @param status
   * @param tranTypeMap
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 上午11:56:07
   */
  public static <E extends AggregatedValueObject> Integer getNextStatus(
      E aggVO, int status, Map<String, PoTransTypeVO> tranTypeMap,
      String tranTypeKey) {

    PoTransTypeVO tranTypeVO =
        tranTypeMap.get(aggVO.getParentVO().getAttributeValue(tranTypeKey));

    // AssertUtils.assertValue(tranTypeVO != null,
    // "交易类型VO不应该为空!");

    // String tranTypeStr = OnwayBpTools.getNextStatusStr(status);

    Integer nextStatus = null;

    if (tranTypeVO != null) {
      nextStatus = TrantypeUtil.getInstance().getNextStatus(status, tranTypeVO);
      // ((Integer) tranTypeVO.getAttributeValue(tranTypeStr)).intValue();

    }

    return nextStatus;
  }

  /**
   * 方法功能描述：取得下一状态的字符串
   * <p>
   * <b>参数说明</b>
   * 
   * @param status
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-25 上午09:43:48
   */
  public static String getNextStatusStr(int status) {

    if (((Integer) OnwayStatus.STATUS_OUTPUT.value()).intValue() == status) {
      return PoTransTypeVO.IOUTPUTAFT;
    }
    else if (((Integer) OnwayStatus.STATUS_CONFIRM.value()).intValue() == status) {
      return PoTransTypeVO.ICONFIRMAFT;
    }
    else if (((Integer) OnwayStatus.STATUS_SENDOUT.value()).intValue() == status) {
      return PoTransTypeVO.ICONSIGNAFT;
    }
    else if (((Integer) OnwayStatus.STATUS_SHIP.value()).intValue() == status) {
      return PoTransTypeVO.ILOADAFT;
    }
    else if (((Integer) OnwayStatus.STATUS_COMEIN.value()).intValue() == status) {
      return PoTransTypeVO.ICUSTOMAFT;
    }
    else if (((Integer) OnwayStatus.STATUS_GETOUT.value()).intValue() == status) {
      return PoTransTypeVO.IOUTCUSTOMAFT;
    }
    return null;
  }

  /**
   * 方法功能描述：取得主表主键
   * <p>
   * <b>参数说明</b>
   * 
   * @param pks
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-26 下午07:45:05
   */
  public static String[] getPkorder(String[][] pks) {
    if (pks == null || pks.length == 0) {
      return new String[0];
    }
    int firstDimLen = pks.length;

    Set<String> pkOrderSet = new HashSet<String>();

    for (int firstDim = 0; firstDim < firstDimLen; firstDim++) {
      pkOrderSet.add(pks[firstDim][0]);
    }

    return pkOrderSet.toArray(new String[pkOrderSet.size()]);
  }

  /**
   * 方法功能描述：取得子子表pk，由于对方确认状态每个子表数据只能对应于一条子子表中的数据，因此每个子表pk对应一个子子表pk
   * 传入的数组为pk_order,pk_order_b,pk_order_bb的二维数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param pks
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-26 下午03:24:38
   */
  public static String[] getSubBodyPks(String[][] pks) {

    int firstDimLen = pks.length;

    List<String> subPkList = new ArrayList<String>();

    for (int firstDim = 0; firstDim < firstDimLen; firstDim++) {
      subPkList.add(pks[firstDim][2]);
    }
    return subPkList.toArray(new String[subPkList.size()]);
  }

  /**
   * 方法功能描述：批量取得订单类型Map
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-21 下午03:21:24
   */
  public static <E extends AggregatedValueObject> Map<String, PoTransTypeVO> getTransTypeVOs(
      E[] orderVOs, String tranTypeName) {

    Set<String> tranTypeSet = new HashSet<String>();
    Map<String, PoTransTypeVO> tranTypeMap =
        new HashMap<String, PoTransTypeVO>();

    if (ArrayUtils.isEmpty(orderVOs)) {
      return tranTypeMap;
    }

    for (E orderVO : orderVOs) {

      Object tranTypeNameObj =
          orderVO.getParentVO().getAttributeValue(tranTypeName);

      if (tranTypeNameObj != null) {
        String tranType = tranTypeNameObj.toString();
        tranTypeSet.add(tranType);
      }
    }

    tranTypeMap =
        OnwayValidationTool.getTransTypeVO(tranTypeSet
            .toArray(new String[tranTypeSet.size()]));

    return tranTypeMap;
  }

  /**
   * 方法功能描述：按照订单类型将订单分组
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-21 下午03:58:38
   */
  public static Map<String, List<OrderVO>> groupByTrantype(OrderVO[] orderVOs) {
    MapList<String, OrderVO> orderMaplist = new MapList<String, OrderVO>();

    for (OrderVO orderVO : orderVOs) {
      orderMaplist.put(orderVO.getHVO().getVtrantypecode(), orderVO);
    }

    return orderMaplist.toMap();
  }

  /**
   * 方法功能描述：将传入的vo按指定的字段生成map(只对应一对一的情况)
   * <p>
   * <b>参数说明</b>
   * 
   * @param supervos
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 上午11:24:45
   */
  public static <E extends ISuperVO> Map<String, E> groupKeyMap(E[] supervos,
      String key) {

    Map<String, E> supervoMap = new HashMap<String, E>();
    for (E supervo : supervos) {
      Object keyObj = supervo.getAttributeValue(key);
      if (keyObj != null) {
        supervoMap.put(supervo.getAttributeValue(key).toString(), supervo);
      }
    }

    return supervoMap;
  }

  /**
   * 方法功能描述：判断是否可操作
   * <p>
   * <b>参数说明</b>
   * 
   * @param status
   *          当前状态
   * @param comStatus
   *          比较状态
   * @param accuNum
   *          当前累状态计数量
   * @param operNum
   *          当前操作数量
   * @param comAccuNum
   *          比较状态累计数量
   * @param row
   *          行号
   * @param errMsg
   *          错误信息
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-8 上午11:00:19
   */
  public static void isCanOperate(int status, int comStatus, UFDouble accuNum,
      UFDouble operNum, UFDouble comAccuNum, int row, StringBuilder errMsg) {

    // 正操作转i，反操作转ii。 i.
    // * 如果ABS（已累计数量+本次数量）>ABS（比较状态累计数量）， 提示用户如"订单行N：本次可装运数量范围为（0，50）。" ii.
    // * 如果ABS（已累计数量-本次数量）<ABS（下一状态累计数量）， 提示用户如"订单行N：本次可反装运数量范围为（0，40］。"

    // ==========判断是否需要提示
    // 可输入数量范围
    UFDouble dNumRange = UFDouble.ZERO_DBL;
    // 是否需要提示
    boolean bNeedHint = false;
    // ==============正操作
    if (status > comStatus) {
      // ABS（已累计数量+本次数量）>ABS（比较状态累计数量）
      dNumRange = MathTool.add(accuNum, operNum);
      if (MathTool.absCompareTo(dNumRange, comAccuNum) > 0) {
        // 状态可操作数量=上一状态数量-已累计数量
        dNumRange = MathTool.sub(comAccuNum, accuNum);
        bNeedHint = true;
      }
    }
    // ==============反操作
    else {
      // ABS（已累计数量-本次数量）<ABS（下一状态累计数量）
      dNumRange = MathTool.sub(accuNum, operNum);
      if (MathTool.absCompareTo(dNumRange, comAccuNum) < 0) {
        // 状态可操作数量=已累计数量－下一状态累计数量
        dNumRange = MathTool.sub(accuNum, comAccuNum);
        bNeedHint = true;
      }
    }

    String sNumRange = "";
    if (bNeedHint) {
      if (MathTool.absCompareTo(dNumRange, UFDouble.ZERO_DBL) < 0) {
        sNumRange = "[" + dNumRange + ",0)";
      }
      else {
        sNumRange = "(0," + dNumRange + "]";
      }
      // errMsg.append(nc.bs.ml.NCLangResOnserver.getInstance().getStrByID(
      // "4004020501", "UPP4004020501-000084")/* @res "行" */
      // + row + ":" + sNumRange + "\n");
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
          "04004030-0294", null, new String[] {
            String.valueOf(row), sNumRange
          })/* 选择行的第{0}行{1}\n */);
    }
  }

  /**
   * 方法功能描述：替换是否已操作
   * <p>
   * <b>参数说明</b>
   * 
   * @param sql
   * @param onwayStatus
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-17 下午02:57:44
   */
  // public static String replaceStatus(String sql, int onwayStatus) {
  // String whereSql = sql;
  // String[] strs = OnwayBpTools.getReplaceStr(onwayStatus);
  // for (String str : strs) {
  // whereSql = whereSql.replaceAll(str, " 1 = 1 ");
  // }
  // return whereSql;
  // }

  /**
   * 方法功能描述：取得表体自定义项
   * <p>
   * <b>参数说明</b>
   * 
   * @param onwayBVO
   * @param rowset
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-13 下午01:31:02
   */
  private static void getBodyVbdef(OrderOnwayItemVO onwayBVO,
      StatusOnWayItemVO onwayVO) {
    for (int i = 0; i < 40; i++) {
      String vbdef = "vbdef" + String.valueOf(i + 1);
      onwayBVO.setAttributeValue(vbdef, onwayVO.getAttributeValue(vbdef));
    }
  }

  // private static int getInt(Object obj) {
  // return ValueUtils.getInt(obj);
  // }

  /**
   * 方法功能描述：根据情况取得替换内容
   * <p>
   * <b>参数说明</b>
   * 
   * @param onwayStatus
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-17 下午02:51:22
   */
  // private static String[] getReplaceStr(int onwayStatus) {
  //
  // String[] returnStrs = null;
  //
  // // 发货
  // if (onwayStatus == OnwayStatus.STATUS_SENDOUT.toInt()) {
  // returnStrs = new String[] {
  // "bissendout = 'N'", "bissendout = 'Y'"
  // };
  // }
  // // 装运
  // else if (onwayStatus == OnwayStatus.STATUS_SHIP.toInt()) {
  // returnStrs = new String[] {
  // "bisload = 'N'", "bisload = 'Y'"
  // };
  // }
  // // 报关
  // else if (onwayStatus == OnwayStatus.STATUS_COMEIN.toInt()) {
  // returnStrs = new String[] {
  // "biscustom = 'N'", "biscustom = 'Y'"
  // };
  // }
  // // 出关
  // else if (onwayStatus == OnwayStatus.STATUS_GETOUT.toInt()) {
  // returnStrs = new String[] {
  // "bisoutcustom = 'N'", "bisoutcustom = 'Y'"
  // };
  // }
  //
  // return returnStrs;
  // }

  /**
   * 方法功能描述：根据订单的HVO对应生成页面显示用HVO
   * <p>
   * <b>参数说明</b>
   * 
   * @param hvo
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-28 下午03:30:34
   */
  private static OrderOnwayHeaderVO orderHVOToOnwayHVO(OrderHeaderVO hvo) {
    OrderOnwayHeaderVO onwayHVO = new OrderOnwayHeaderVO();
    // 采购组织
    onwayHVO.setPk_org_v(hvo.getPk_org_v());
    // 供应商
    onwayHVO.setPk_supplier(hvo.getPk_supplier());
    // 订单编号
    onwayHVO.setVbillcode(hvo.getVbillcode());

    onwayHVO.setPk_order(hvo.getPk_order());
    // 交易类型
    onwayHVO.setVtrantypecode(hvo.getVtrantypecode());
    // 币种
    onwayHVO.setCorigcurrencyid(hvo.getCorigcurrencyid());
    // onwayHVO.setTs(hvo.getTs());

    return onwayHVO;
  }

}
