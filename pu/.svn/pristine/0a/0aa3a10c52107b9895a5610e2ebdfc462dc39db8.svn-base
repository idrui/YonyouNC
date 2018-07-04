/**
 * $文件说明$
 *
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-15 下午04:48:11
 */
package nc.bs.pu.m21.writeback.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pu.m21.onway.bp.OnwayBpTools;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.AssertUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.collections.MapUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货入库回写在途状态表
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-10-15 下午04:48:11
 */
public class OnwayStatusWriteBackRule implements IRule<OrderViewVO> {

  /** 未操作 */
  private static final String notOperated = "N";

  /** 已操作 */
  private static final String operated = "Y";

  /** 已操作 */
  private String opreraStr = null;

  /** 状态 */
  private int status = 0;

  public OnwayStatusWriteBackRule(int status, String opreraStr) {
    this.status = status;
    this.opreraStr = opreraStr;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderViewVO[] vos) {
    Map<String, OrderViewVO> viewVOMap = this.getBodyPkMap(vos);

    String[] keyArray =
        viewVOMap.keySet().toArray(new String[viewVOMap.size()]);

    // 检索所有未操作的项
    Map<String, StatusOnWayItemVO> onwayNotOperMap =
        this.queryOnwayStatusVO(keyArray, OnwayStatusWriteBackRule.notOperated,
            this.status);

    // 更新未操作的数据
    this.updateNotOperVO(onwayNotOperMap, viewVOMap);

    // 查询已经存在的操作后数据
    Map<String, StatusOnWayItemVO> onwayMap =
        this.queryOnwayStatusVO(keyArray, OnwayStatusWriteBackRule.operated,
            this.status);

    // 检索已经操作的项，有更新，没有插入
    Map<String, OrderViewVO> notExistViewBvoMap =
        new HashMap<String, OrderViewVO>();

    if (MapUtils.isNotEmpty(onwayMap)) {
      notExistViewBvoMap = this.updateExistVO(onwayMap, viewVOMap);
    }
    else {
      notExistViewBvoMap.putAll(viewVOMap);
    }

    // 插入不存在的项
    if (MapUtils.isNotEmpty(notExistViewBvoMap)) {
      this.insertNotExistVO(notExistViewBvoMap, onwayNotOperMap);
    }
  }

  /**
   * 方法功能描述：取得所有子表pk
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-15 下午05:04:08
   */
  private Map<String, OrderViewVO> getBodyPkMap(OrderViewVO[] vos) {
    Map<String, OrderViewVO> bvoPkMap = new HashMap<String, OrderViewVO>();

    for (OrderViewVO viewVO : vos) {
      bvoPkMap.put(viewVO.getPk_order_b(), viewVO);
    }

    return bvoPkMap;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param notExistViewBvoMap
   * @param onwayNotOperMap
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-18 上午10:22:57
   */
  private void insertNotExistVO(Map<String, OrderViewVO> notExistViewBvoMap,
      Map<String, StatusOnWayItemVO> onwayNotOperMap) {

    Set<Map.Entry<String, OrderViewVO>> notExistSet =
        notExistViewBvoMap.entrySet();
    Set<StatusOnWayItemVO> onwaySet = new HashSet<StatusOnWayItemVO>();

    for (Map.Entry<String, OrderViewVO> notExistMapEntry : notExistSet) {
      String pk_order_b = notExistMapEntry.getKey();
      OrderViewVO viewVO = notExistMapEntry.getValue();

      StatusOnWayItemVO onwayVO = onwayNotOperMap.get(pk_order_b);
      StatusOnWayItemVO updateOnwayVO = (StatusOnWayItemVO) onwayVO.clone();

      // 累计到货数量
      // updateOnwayVO.setNonwaynum(viewVO.getNaccumarrvnum());
      Object numObj = viewVO.getAttributeValue(this.opreraStr);
      UFDouble num = null;
      if (numObj != null) {
        num = new UFDouble(numObj.toString());
      }
      updateOnwayVO.setNonwaynum(num);

      // 设置已操作
      updateOnwayVO.setIsoperated(UFBoolean
          .valueOf(OnwayStatusWriteBackRule.operated));
      onwaySet.add(updateOnwayVO);
    }

    StatusOnWayItemVO[] updateVOs =
        onwaySet.toArray(new StatusOnWayItemVO[onwaySet.size()]);

    VOInsert<StatusOnWayItemVO> voInsert = new VOInsert<StatusOnWayItemVO>();
    voInsert.insert(updateVOs);

  }

  /**
   * 方法功能描述：检索在途状态VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param bvoPks
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-18 上午08:49:06
   */
  private Map<String, StatusOnWayItemVO> queryOnwayStatusVO(String[] bvoPks,
      String isoperated, int onwayStatus) {
    SqlBuilder sqlbd = new SqlBuilder();
    sqlbd.append(" and ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_33.name());
    sqlbd.append(builder.buildSQL(StatusOnWayItemVO.PK_ORDER_B, bvoPks));
    sqlbd.append(" and ");
    sqlbd.append(StatusOnWayItemVO.ISOPERATED, isoperated);
    sqlbd.append(" and ");
    sqlbd.append(StatusOnWayItemVO.FONWAYSTATUS, onwayStatus);

    VOQuery<StatusOnWayItemVO> voquery =
        new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);
    StatusOnWayItemVO[] onwayVOs = voquery.query(sqlbd.toString(), null);

    // 按照子表pk生成map
    return OnwayBpTools.groupKeyMap(onwayVOs, StatusOnWayItemVO.PK_ORDER_B);
  }

  /**
   * 方法功能描述：更新已经存在的VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param onwayMap
   * @param viewVOMap
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-18 上午09:47:25
   */
  private Map<String, OrderViewVO> updateExistVO(
      Map<String, StatusOnWayItemVO> onwayMap,
      Map<String, OrderViewVO> viewVOMap) {

    Set<Map.Entry<String, StatusOnWayItemVO>> onwaySet = onwayMap.entrySet();

    List<StatusOnWayItemVO> onwayList = new ArrayList<StatusOnWayItemVO>();
    Map<String, OrderViewVO> notExistBvoMap =
        new HashMap<String, OrderViewVO>();
    notExistBvoMap.putAll(viewVOMap);

    for (Map.Entry<String, StatusOnWayItemVO> onwayEntrySet : onwaySet) {
      String pk_order_b = onwayEntrySet.getKey();
      StatusOnWayItemVO onwayVO = onwayEntrySet.getValue();
      StatusOnWayItemVO updateOnwayVO = (StatusOnWayItemVO) onwayVO.clone();

      Object numObj =
          viewVOMap.get(pk_order_b).getAttributeValue(this.opreraStr);
      UFDouble num = null;
      if (numObj != null) {
        num = new UFDouble(numObj.toString());
      }
      // 累计到货数量
      updateOnwayVO.setNonwaynum(num);
      onwayList.add(updateOnwayVO);

      // 将存在的VO去除
      notExistBvoMap.remove(pk_order_b);
    }

    VOUpdate<StatusOnWayItemVO> voupdate = new VOUpdate<StatusOnWayItemVO>();
    StatusOnWayItemVO[] updateVOs =
        onwayList.toArray(new StatusOnWayItemVO[onwayList.size()]);

    voupdate.update(updateVOs, new String[] {
      StatusOnWayItemVO.NONWAYNUM
    });

    return notExistBvoMap;
  }

  /**
   * 方法功能描述：维护未操作状态的VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param onwayNotOperMap
   * @param viewVOMap
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-18 上午10:45:31
   */
  private void updateNotOperVO(Map<String, StatusOnWayItemVO> onwayNotOperMap,
      Map<String, OrderViewVO> viewVOMap) {

    Set<Map.Entry<String, OrderViewVO>> viewVOSet = viewVOMap.entrySet();
    Set<StatusOnWayItemVO> updateOnwaySet = new HashSet<StatusOnWayItemVO>();

    for (Map.Entry<String, OrderViewVO> viewMapEntry : viewVOSet) {
      String pk_order_b = viewMapEntry.getKey();
      OrderViewVO viewVO = viewMapEntry.getValue();

      StatusOnWayItemVO onwayVO = onwayNotOperMap.get(pk_order_b);
      AssertUtils.assertValue(onwayVO != null, nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0150")/*
                                                                   * @res
                                                                   * "到货的物料在途状态没有走到到货状态!"
                                                                   */);

      if (onwayVO != null) {
        StatusOnWayItemVO updateOnwayVO = (StatusOnWayItemVO) onwayVO.clone();
        // 只更新累计到货数量
        Object numObj = viewVO.getAttributeValue(this.opreraStr);
        UFDouble num = null;
        if (numObj != null) {
          num = new UFDouble(numObj.toString());
        }
        UFDouble onwayNum = updateOnwayVO.getNonwaynum();
        UFDouble notArriveNum = MathTool.sub(onwayNum, num);
        // 如果实际到货或入库数量大于在途状态的到货、入库数量，抛异常
        if (MathTool.compareTo(notArriveNum, UFDouble.ZERO_DBL) < 0) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004030_0", "04004030-0151")/*
                                                                       * @res
                                                                       * "实际的到货、入库数量不能大于在途状态的到货、入库数量!"
                                                                       */);
        }

        updateOnwayVO.setNonwaynum(notArriveNum);

        updateOnwaySet.add(updateOnwayVO);
      }
    }

    StatusOnWayItemVO[] updateVOArray =
        updateOnwaySet.toArray(new StatusOnWayItemVO[updateOnwaySet.size()]);
    VOUpdate<StatusOnWayItemVO> voupdate = new VOUpdate<StatusOnWayItemVO>();
    // 只更新在途数量字段
    voupdate.update(updateVOArray, new String[] {
      StatusOnWayItemVO.NONWAYNUM
    });
  }
}
