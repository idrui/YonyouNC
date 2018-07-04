/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-29 上午11:15:46
 */
package nc.impl.pu.m21.onway.bp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21.entity.OrderOnwayVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.collections.MapUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>在途状态正向操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-29 上午11:15:46
 */
public class OnwayUpdateBpMy {

  /**
   * 方法功能描述：在途状态正向操作BP
   * <p>
   * <b>参数说明</b>
   * 
   * @param onwayVOs
   * @param status
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-29 下午02:46:30
   */
  public StatusOnWayItemVO[] updateOnway(OrderOnwayVO[] onwayVOs, int status) {

    // 取得采购订单主表的pk
    OnwayDAO dao = new OnwayDAO();
    String pk_order = onwayVOs[0].getHVO().getPk_order();
    dao.clockByPk(pk_order);

    Map<String, PoTransTypeVO> tranTypeMap =
        OnwayBpTools.getTransTypeVOs(onwayVOs, OrderHeaderVO.VTRANTYPECODE);

    Integer nextStatus =
        OnwayBpTools.getNextStatus(onwayVOs[0], status, tranTypeMap,
            OrderHeaderVO.VTRANTYPECODE);

    OrderOnwayItemVO[] viewBVOs = onwayVOs[0].getBVO();

    // 按子表主键分组
    Map<String, OrderOnwayItemVO> viewBVOMap =
        OnwayBpTools.groupKeyMap(viewBVOs, OrderItemVO.PK_ORDER_B);

    // 取得当前状态数据,即孙表自身状态的数据
    Map<String, StatusOnWayItemVO> currOnwayVOMap =
        this.getOnwayVOs(viewBVOMap, Integer.valueOf(status));

    if (nextStatus != null) {
      // 取得子子表中下状态为操作的数据
      Map<String, StatusOnWayItemVO> nextOnwayVOMap =
          this.getOnwayVOs(viewBVOMap, nextStatus);

      Map<String, OrderOnwayItemVO> notExistViewBvoMap =
          new HashMap<String, OrderOnwayItemVO>();

      // 更新已存在的数据
      if (MapUtils.isNotEmpty(nextOnwayVOMap)) {
        notExistViewBvoMap =
            this.updateExistNextStatus(viewBVOMap, nextOnwayVOMap, status);
      }
      else {
        notExistViewBvoMap.putAll(viewBVOMap);
      }

      // 对于不存在下状态的数据，插入
      if (MapUtils.isNotEmpty(notExistViewBvoMap)) {
        this.insertNextStatus(currOnwayVOMap, notExistViewBvoMap, nextStatus,
            status);
      }
    }

    // 算出操作后剩余数据插入
    this.insertRemainNum(currOnwayVOMap, viewBVOMap, status);

    // 更新当前状态数据
    this.updateCurrStatus(currOnwayVOMap, viewBVOMap, status);

    // 除了插入发货的数据之外，还要更新相同单据的表头自定义项信息。
    dao.updateVhdef(pk_order, onwayVOs[0], status);

    return null;
  }

  /**
   * 方法功能描述：生成插入下状态用的VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param currOnwayVOMap
   * @param bvoMapEntry
   * @param nextStatus
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 下午03:35:57
   */
  private StatusOnWayItemVO creatInsertStatusVO(
      Map<String, StatusOnWayItemVO> currOnwayVOMap,
      Map.Entry<String, OrderOnwayItemVO> bvoMapEntry, Integer nextStatus,
      int status) {

    String pk_order_b = bvoMapEntry.getKey();
    OrderOnwayItemVO bvo = bvoMapEntry.getValue();

    StatusOnWayItemVO currOnwayVO = currOnwayVOMap.get(pk_order_b);
    StatusOnWayItemVO insertOnwayVO = (StatusOnWayItemVO) currOnwayVO.clone();

    // 在途状态
    insertOnwayVO.setFonwaystatus(nextStatus);
    // 在途数量
    UFDouble operNum = bvo.getNonwaynum();
    // if (status == ((Integer) OnwayStatus.STATUS_SENDOUT.value()).intValue())
    // {
    if (status == OnwayStatus.STATUS_SENDOUT.toInt()) {
      operNum = bvo.getNsendoutnum();
    }
    insertOnwayVO.setNonwaynum(operNum);
    // 是否已操作为否
    insertOnwayVO.setIsoperated(UFBoolean.FALSE);
    // 清空主键
    insertOnwayVO.setPk_order_bb(null);

    return insertOnwayVO;

  }

  /**
   * 方法功能描述：生成更新当前状态VO,此处先不考虑表头自定义项，之后统一更新。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bvoMap
   * @param updateCurrVO
   * @param updateOrigVO
   * @param onwayMapEntry
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 下午03:59:52
   */
  private void creatUpdateCurrOnwayVO(Map<String, OrderOnwayItemVO> bvoMap,
      List<StatusOnWayItemVO> updateCurrVO,
      List<StatusOnWayItemVO> updateOrigVO,
      Map.Entry<String, StatusOnWayItemVO> onwayMapEntry, int status) {

    String pk_order_b = onwayMapEntry.getKey();
    StatusOnWayItemVO onwayVO = onwayMapEntry.getValue();

    OrderOnwayItemVO viewBVO = bvoMap.get(pk_order_b);
    if (viewBVO.getTs() == null || !viewBVO.getTs().equals(onwayVO.getTs())) {
      String tip = null;
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0",
              "0pubapp-0177")/* @res "出现并发，请重新查询" */;
      ExceptionUtils.wrappBusinessException(message, tip);
    }

    StatusOnWayItemVO updateOnwayVO = (StatusOnWayItemVO) onwayVO.clone();

    this.fillInputInfoForCurrStatus(status, viewBVO, updateOnwayVO);

    updateCurrVO.add(updateOnwayVO);
    updateOrigVO.add(onwayVO);
  }

  /**
   * 方法功能描述：填充页面输入信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param status
   * @param viewBVO
   * @param updateOnwayVO
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-29 下午02:28:03
   */
  private void fillInputInfoForCurrStatus(int status, OrderOnwayItemVO viewBVO,
      StatusOnWayItemVO updateOnwayVO) {

    // 单据号为
    updateOnwayVO.setVbillcode(viewBVO.getVbillcode());
    // 单据日期
    updateOnwayVO.setDbilldate(viewBVO.getDbilldate());
    // 在途数量为确认数量
    // if (status == ((Integer) OnwayStatus.STATUS_SENDOUT.value()).intValue())
    // {
    if (status == OnwayStatus.STATUS_SENDOUT.toInt()) {
      updateOnwayVO.setNonwaynum(viewBVO.getNsendoutnum());
    }
    else {
      updateOnwayVO.setNonwaynum(viewBVO.getNonwaynum());
    }
    // 计划到货日期
    updateOnwayVO.setDplanarrvdate(viewBVO.getDplanarrvdate());
    // 是否已操作为是
    updateOnwayVO.setIsoperated(UFBoolean.TRUE);

    // 承运商
    updateOnwayVO.setCcarrier(viewBVO.getCcarrier());

    // 货柜号
    updateOnwayVO.setCcasecode(viewBVO.getCcasecode());

    // 到货港口
    updateOnwayVO.setClandport(viewBVO.getClandport());

    // 装船港口
    updateOnwayVO.setCloadport(viewBVO.getCloadport());

    // 船次号
    updateOnwayVO.setCshipline(viewBVO.getCshipline());

    // 船名
    updateOnwayVO.setCshipname(viewBVO.getCshipname());

    // 计划到港日期
    updateOnwayVO.setDplanfreightdate(viewBVO.getDplanfreightdate());

    // 自定义项
    this.mappingVbdef(updateOnwayVO, viewBVO);

    // onwayItemVO.setStatus(status);
  }

  /**
   * 方法功能描述：填入页面信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param onwayVO
   * @param bvo
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 下午02:15:40
   */
  private void fillInputInfoForNextStatus(StatusOnWayItemVO onwayVO,
      OrderOnwayItemVO bvo, int status) {
    // 对方订单号
    // onwayVO.setVbillcode(bvo.getVvendorordercode());

    // 对方订单行号
    // onwayVO.setVvendororderrow(bvo.getVvendororderrow());

    // 计算更新后在途数量
    UFDouble existNum = onwayVO.getNonwaynum();
    UFDouble operaNum = bvo.getNonwaynum();
    // if (status == ((Integer) OnwayStatus.STATUS_SENDOUT.value()).intValue())
    // {
    if (status == OnwayStatus.STATUS_SENDOUT.toInt()) {
      operaNum = bvo.getNsendoutnum();
    }

    UFDouble afterEditNum = MathTool.add(existNum, operaNum);
    onwayVO.setNonwaynum(afterEditNum);
    // 确认日期
    // onwayVO.setDbilldate(bvo.getDconfirmdate());

    // 计划到货日期
    // onwayVO.setDplanarrvdate(bvo.getDplanarrvdate());
  }

  /**
   * 方法功能描述：取得在途状态子子表中的数据（针对于每个订单表体行，子子表中操作状态为N的数据只有一条，因此返回值按照子表pk分类）
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVO
   * @param status
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 上午11:52:04
   */
  private Map<String, StatusOnWayItemVO> getOnwayVOs(
      Map<String, OrderOnwayItemVO> bvoMap, Integer status) {

    String[] bvoPkArray = bvoMap.keySet().toArray(new String[bvoMap.size()]);

    SqlBuilder sqlbd = new SqlBuilder();

    sqlbd.append(" and ");
    sqlbd.append(StatusOnWayItemVO.FONWAYSTATUS, status);
    sqlbd.append(" and ");
    sqlbd.append(StatusOnWayItemVO.ISOPERATED, "N");
    sqlbd.append(" and ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_40.name());
    sqlbd.append(builder.buildSQL(StatusOnWayItemVO.PK_ORDER_B, bvoPkArray));

    VOQuery<StatusOnWayItemVO> voquery =
        new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);
    StatusOnWayItemVO[] onwayVOs = voquery.query(sqlbd.toString(), null);

    // 按照子表pk生成map
    return OnwayBpTools.groupKeyMap(onwayVOs, StatusOnWayItemVO.PK_ORDER_B);
  }

  /**
   * 方法功能描述：插入下状态数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param currOnwayVOMap
   * @param bvoMap
   * @param nextStatus
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 下午03:33:40
   */
  private void insertNextStatus(Map<String, StatusOnWayItemVO> currOnwayVOMap,
      Map<String, OrderOnwayItemVO> bvoMap, Integer nextStatus, int status) {

    Set<StatusOnWayItemVO> insertOnwaySet = new HashSet<StatusOnWayItemVO>();

    Set<Map.Entry<String, OrderOnwayItemVO>> bvoSet = bvoMap.entrySet();
    for (Map.Entry<String, OrderOnwayItemVO> bvoMapEntry : bvoSet) {

      StatusOnWayItemVO insertOnwayVO =
          this.creatInsertStatusVO(currOnwayVOMap, bvoMapEntry, nextStatus,
              status);

      insertOnwaySet.add(insertOnwayVO);

    }

    StatusOnWayItemVO[] insertOnwayVOArray =
        insertOnwaySet.toArray(new StatusOnWayItemVO[insertOnwaySet.size()]);

    VOInsert<StatusOnWayItemVO> voinsert = new VOInsert<StatusOnWayItemVO>();
    voinsert.insert(insertOnwayVOArray);
  }

  /**
   * 方法功能描述：插入当前状态的剩余数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param currOnwayVOMap
   * @param viewBVOMap
   * @param status
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-29 下午02:06:10
   */
  private void insertRemainNum(Map<String, StatusOnWayItemVO> currOnwayVOMap,
      Map<String, OrderOnwayItemVO> viewBVOMap, int status) {

    Set<StatusOnWayItemVO> insertSet = new HashSet<StatusOnWayItemVO>();

    Set<Map.Entry<String, StatusOnWayItemVO>> currOnwayVOSet =
        currOnwayVOMap.entrySet();

    for (Map.Entry<String, StatusOnWayItemVO> currOnwayVOEntry : currOnwayVOSet) {
      String pk_order_b = currOnwayVOEntry.getKey();
      StatusOnWayItemVO onwayBVO = currOnwayVOEntry.getValue();

      StatusOnWayItemVO insertVO = (StatusOnWayItemVO) onwayBVO.clone();

      // 计算剩余数量
      UFDouble currNum = onwayBVO.getNonwaynum();
      UFDouble operNum = viewBVOMap.get(pk_order_b).getNonwaynum();
      // if (status == ((Integer)
      // OnwayStatus.STATUS_SENDOUT.value()).intValue()) {
      // 发货状态取本次发货数量
      if (status == OnwayStatus.STATUS_SENDOUT.toInt()) {
        operNum = viewBVOMap.get(pk_order_b).getNsendoutnum();
      }
      UFDouble remainNum = MathTool.sub(currNum, operNum);

      insertVO.setNonwaynum(remainNum);
      insertVO.setPk_order_bb(null);

      insertSet.add(insertVO);
    }

    VOInsert<StatusOnWayItemVO> voinsert = new VOInsert<StatusOnWayItemVO>();
    voinsert.insert(insertSet.toArray(new StatusOnWayItemVO[insertSet.size()]));

  }

  /**
   * 方法功能描述：40个自定义项之间的映射
   * <p>
   * <b>参数说明</b>
   * 
   * @param onwayItemVO
   * @param onwayVO
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-13 下午02:00:07
   */
  private void mappingVbdef(StatusOnWayItemVO onwayItemVO,
      OrderOnwayItemVO onwayVO) {
    for (int i = 0; i < 40; i++) {
      String vbdef = "vbdef" + String.valueOf(i + 1);
      onwayItemVO.setAttributeValue(vbdef, onwayVO.getAttributeValue(vbdef));
    }
  }

  /**
   * 方法功能描述：更新当前状态数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param currOnwayVOMap
   * @param viewBVOMap
   * @param status
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-29 下午02:10:40
   */
  private void updateCurrStatus(Map<String, StatusOnWayItemVO> currOnwayVOMap,
      Map<String, OrderOnwayItemVO> viewBVOMap, int status) {

    List<StatusOnWayItemVO> updateList = new ArrayList<StatusOnWayItemVO>();
    List<StatusOnWayItemVO> origList = new ArrayList<StatusOnWayItemVO>();

    Set<Map.Entry<String, StatusOnWayItemVO>> currOnwayVOSet =
        currOnwayVOMap.entrySet();
    for (Map.Entry<String, StatusOnWayItemVO> currOnwayVOEntry : currOnwayVOSet) {
      // String pk_order_b = currOnwayVOEntry.getKey();
      // StatusOnWayItemVO onwayBVO = currOnwayVOEntry.getValue();
      //
      // StatusOnWayItemVO insertVO = (StatusOnWayItemVO) onwayBVO.clone();
      //
      // // 更新操作数量和状态
      //
      // insertVO.setNonwaynum(remainNum);
      //
      // updateList.add(insertVO);
      // origList.add(onwayBVO);

      this.creatUpdateCurrOnwayVO(viewBVOMap, updateList, origList,
          currOnwayVOEntry, status);

    }

    VOUpdate<StatusOnWayItemVO> voupdate = new VOUpdate<StatusOnWayItemVO>();

    StatusOnWayItemVO[] updateArray =
        updateList.toArray(new StatusOnWayItemVO[updateList.size()]);
    StatusOnWayItemVO[] origArray =
        origList.toArray(new StatusOnWayItemVO[origList.size()]);
    voupdate.update(updateArray, origArray);
  }

  /**
   * 方法功能描述：更新已经存在的下状态数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param bvoMap
   * @param onwayVOMap
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 下午02:35:38
   */
  private Map<String, OrderOnwayItemVO> updateExistNextStatus(
      Map<String, OrderOnwayItemVO> bvoMap,
      Map<String, StatusOnWayItemVO> onwayVOMap, int status) {

    Set<Map.Entry<String, StatusOnWayItemVO>> onwayVOEntrySet =
        onwayVOMap.entrySet();

    List<StatusOnWayItemVO> updateList = new ArrayList<StatusOnWayItemVO>();
    List<StatusOnWayItemVO> originList = new ArrayList<StatusOnWayItemVO>();

    Map<String, OrderOnwayItemVO> notExistBvoMap =
        new HashMap<String, OrderOnwayItemVO>();
    notExistBvoMap.putAll(bvoMap);

    for (Map.Entry<String, StatusOnWayItemVO> onwayVOMapEntry : onwayVOEntrySet) {
      String pk_order_b = onwayVOMapEntry.getKey();
      StatusOnWayItemVO onwayVO = onwayVOMapEntry.getValue();

      StatusOnWayItemVO updateVO = (StatusOnWayItemVO) onwayVO.clone();

      OrderOnwayItemVO bvo = bvoMap.get(pk_order_b);

      // 将页面信息填入
      this.fillInputInfoForNextStatus(updateVO, bvo, status);

      // 生成更新用VO
      updateList.add(updateVO);
      originList.add(onwayVO);

      // 将已经存在的VO去除
      notExistBvoMap.remove(pk_order_b);
    }

    VOUpdate<StatusOnWayItemVO> voupdate = new VOUpdate<StatusOnWayItemVO>();

    StatusOnWayItemVO[] updateVOs =
        updateList.toArray(new StatusOnWayItemVO[updateList.size()]);
    StatusOnWayItemVO[] originVOs =
        originList.toArray(new StatusOnWayItemVO[originList.size()]);

    voupdate.update(updateVOs, originVOs);

    return notExistBvoMap;
  }

}
