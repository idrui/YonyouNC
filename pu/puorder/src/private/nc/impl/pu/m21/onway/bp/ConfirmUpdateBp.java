/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-27 ����10:05:13
 */
package nc.impl.pu.m21.onway.bp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.collections.MapUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����״̬�Է�ȷ�ϸ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-27 ����10:05:13
 */
public class ConfirmUpdateBp {

  /**
   * �����������������¶���״̬�Է�ȷ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVOs
   * @param status
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 ����10:06:11
   */
  public void updateConfirm(OrderVO[] orderVOs) {

    // ������һ״̬�Ĳ���״̬Ϊ0�����ݣ����û�У�����һ��
    Map<String, PoTransTypeVO> tranTypeMap =
        OnwayBpTools.getTransTypeVOs(orderVOs, OrderHeaderVO.VTRANTYPECODE);

    Integer nextStatus =
        OnwayBpTools.getNextStatus(orderVOs[0],
            OnwayStatus.STATUS_CONFIRM.toInt(), tranTypeMap,
            OrderHeaderVO.VTRANTYPECODE);

    OrderItemVO[] bvos = orderVOs[0].getBVO();

    // ���ӱ���������
    Map<String, OrderItemVO> bvoMap =
        OnwayBpTools.groupKeyMap(bvos, OrderItemVO.PK_ORDER_B);

    // ȡ�����ӱ�����״̬Ϊ����������
    // ȡ�õ�ǰ״̬����
    Map<String, StatusOnWayItemVO> currOnwayVOMap =
        this.getOnwayVOs(bvoMap, OnwayStatus.STATUS_CONFIRM.toInteger());

    // ��ѯ����ǰ״̬Ϊ��,˵�����ֲ���,�״�
    if (currOnwayVOMap.size() == 0) {
      String tip = null;
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0",
              "0pubapp-0177")/* @res "���ֲ����������²�ѯ" */;
      ExceptionUtils.wrappBusinessException(message, tip);
    }

    // û����״̬ʱ������״̬����
    if (nextStatus != null) {
      Map<String, StatusOnWayItemVO> nextOnwayVOMap =
          this.getOnwayVOs(bvoMap, nextStatus);

      Map<String, OrderItemVO> notExistBvoMap =
          new HashMap<String, OrderItemVO>();

      // �����Ѵ��ڵ�����
      if (MapUtils.isNotEmpty(nextOnwayVOMap)) {
        notExistBvoMap = this.updateExistNextStatus(bvoMap, nextOnwayVOMap);
      }
      else {
        notExistBvoMap.putAll(bvoMap);
      }

      // ���ڲ�������״̬�����ݣ�����
      if (MapUtils.isNotEmpty(notExistBvoMap)) {
        this.insertNextStatus(currOnwayVOMap, notExistBvoMap, nextStatus);
      }
    }

    // �����������ݣ�����������Ϊ��������
    this.updateCurrentStatus(currOnwayVOMap, bvoMap);

    // �����Զ�����
    BillTransferTool<OrderVO> transferTool =
        new BillTransferTool<OrderVO>(orderVOs);
    BillUpdate<OrderVO> billUpdate = new BillUpdate<OrderVO>();
    OrderVO[] oldVOs = transferTool.getOriginBills();
    billUpdate.update(orderVOs, oldVOs);

  }

  /**
   * �����������������ɲ�����״̬�õ�VO
   * <p>
   * <b>����˵��</b>
   * 
   * @param currOnwayVOMap
   * @param bvoMapEntry
   * @param nextStatus
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 ����03:35:57
   */
  private StatusOnWayItemVO creatInsertStatusVO(
      Map<String, StatusOnWayItemVO> currOnwayVOMap,
      Map.Entry<String, OrderItemVO> bvoMapEntry, Integer nextStatus) {

    String pk_order_b = bvoMapEntry.getKey();
    OrderItemVO bvo = bvoMapEntry.getValue();

    StatusOnWayItemVO currOnwayVO = currOnwayVOMap.get(pk_order_b);
    StatusOnWayItemVO insertOnwayVO = (StatusOnWayItemVO) currOnwayVO.clone();

    // ��;״̬
    insertOnwayVO.setFonwaystatus(nextStatus);
    // ��;����Ϊȷ������
    insertOnwayVO.setNonwaynum(bvo.getNconfirmnum());
    // ���ɲ�������
    insertOnwayVO.setNmaxhandlenum(bvo.getNconfirmnum());
    // �Ƿ��Ѳ���Ϊ��
    insertOnwayVO.setIsoperated(UFBoolean.FALSE);
    // �������
    insertOnwayVO.setPk_order_bb(null);

    return insertOnwayVO;
    // // �������ڼ�Ϊȷ������
    // insertOnwayVO.setDbilldate(bvo.getDconfirmdate());
    // // �ƻ���������
    // insertOnwayVO.setDplanarrvdate(bvo.getDplanarrvdate());
    // // ��;״̬
    // insertOnwayVO.setFonwaystatus(Integer.valueOf(nextStatus));
    // // �Ƿ��Ѳ���Ϊ��
    // insertOnwayVO.setIsoperated(UFBoolean.FALSE);
    // // ��;����Ϊȷ������
    // insertOnwayVO.setNonwaynum(bvo.getNconfirmnum());
    // // ���ݺ�Ϊ�Է�������
    // insertOnwayVO.setVbillcode(bvo.getVvendorordercode());
    // // �Է������к�
    // insertOnwayVO.setVvendororderrow(bvo.getVvendororderrow());

    // StatusOnWayItemVO statusOnWayItemVO = new StatusOnWayItemVO();
    // // �������ڼ�Ϊȷ������
    // statusOnWayItemVO.setDbilldate(bvo.getDconfirmdate());
    // // �ƻ���������
    // statusOnWayItemVO.setDplanarrvdate(bvo.getDplanarrvdate());
    // // ��;״̬
    // statusOnWayItemVO.setFonwaystatus(Integer.valueOf(nextStatus));
    // // �Ƿ��Ѳ���Ϊ��
    // statusOnWayItemVO.setIsoperated(UFBoolean.FALSE);
    // // ��;����Ϊȷ������
    // statusOnWayItemVO.setNonwaynum(bvo.getNconfirmnum());
    // statusOnWayItemVO.setPk_group(bvo.getPk_group());
    // statusOnWayItemVO.setPk_order(bvo.getPk_order());
    // statusOnWayItemVO.setPk_order_b(bvo.getPk_order_b());
    // statusOnWayItemVO.setPk_org(bvo.getPk_org());
    // statusOnWayItemVO.setPk_org_v(bvo.getPk_org_v());
    // // ���ݺ�Ϊ�Է�������
    // statusOnWayItemVO.setVbillcode(bvo.getVvendorordercode());
    // // �Է������к�
    // statusOnWayItemVO.setVvendororderrow(bvo.getVvendororderrow());
  }

  /**
   * �����������������ɸ��µ�ǰ״̬VO
   * <p>
   * <b>����˵��</b>
   * 
   * @param bvoMap
   * @param updateCurrVO
   * @param updateOrigVO
   * @param onwayMapEntry
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 ����03:59:52
   */
  private void creatUpdateCurrOnwayVO(Map<String, OrderItemVO> bvoMap,
      List<StatusOnWayItemVO> updateCurrVO,
      List<StatusOnWayItemVO> updateOrigVO,
      Map.Entry<String, StatusOnWayItemVO> onwayMapEntry) {

    String pk_order_b = onwayMapEntry.getKey();
    StatusOnWayItemVO onwayVO = onwayMapEntry.getValue();

    OrderItemVO bvo = bvoMap.get(pk_order_b);

    StatusOnWayItemVO updateOnwayVO = (StatusOnWayItemVO) onwayVO.clone();

    // �������ڼ�Ϊȷ������
    updateOnwayVO.setDbilldate(bvo.getDconfirmdate());
    // �ƻ���������
    updateOnwayVO.setDplanarrvdate(bvo.getDplanarrvdate());
    // ��;״̬
    // updateOnwayVO.setFonwaystatus(Integer.valueOf(nextStatus));
    // �Ƿ��Ѳ���Ϊ��
    updateOnwayVO.setIsoperated(UFBoolean.TRUE);
    // ��;����Ϊȷ������
    updateOnwayVO.setNonwaynum(bvo.getNconfirmnum());
    // ���ɲ�������
    updateOnwayVO.setNmaxhandlenum(bvo.getNconfirmnum());
    // ���ݺ�Ϊ�Է�������
    updateOnwayVO.setVbillcode(bvo.getVvendorordercode());
    // �Է������к�
    updateOnwayVO.setVvendororderrow(bvo.getVvendororderrow());

    updateCurrVO.add(updateOnwayVO);
    updateOrigVO.add(onwayVO);
  }

  /**
   * ������������������ҳ����Ϣ
   * <p>
   * <b>����˵��</b>
   * 
   * @param onwayVO
   * @param bvo
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 ����02:15:40
   */
  private void fillInputInfo(StatusOnWayItemVO onwayVO, OrderItemVO bvo) {
    // �Է�������
    // onwayVO.setVbillcode(bvo.getVvendorordercode());

    // �Է������к�
    // onwayVO.setVvendororderrow(bvo.getVvendororderrow());

    // ��;������Ϊȷ��������
    onwayVO.setNonwaynum(bvo.getNconfirmnum());

    // ���ɲ�������
    onwayVO.setNmaxhandlenum(bvo.getNconfirmnum());

    // ȷ������
    // onwayVO.setDbilldate(bvo.getDconfirmdate());

    // �ƻ���������
    // onwayVO.setDplanarrvdate(bvo.getDplanarrvdate());
  }

  // /**
  // * ��������������ȡ����һ״̬
  // * <p>
  // * <b>����˵��</b>
  // *
  // * @param orderVOs
  // * @param status
  // * @param tranTypeMap
  // * @return <p>
  // * @since 6.0
  // * @author wanghuid
  // * @time 2010-9-27 ����11:56:07
  // */
  // private int getNextStatus(OrderVO orderVO, int status,
  // Map<String, PoTransTypeVO> tranTypeMap) {
  //
  // PoTransTypeVO tranTypeVO =
  // tranTypeMap.get(orderVO.getHVO().getVtrantypecode());
  //
  // String tranTypeStr = OnwayBpTools.getNextStatusStr(status);
  //
  // int nextStatus =
  // ((Integer) tranTypeVO.getAttributeValue(tranTypeStr)).intValue();
  // return nextStatus;
  // }

  /**
   * ��������������ȡ����;״̬���ӱ��е����ݣ������ÿ�����������У����ӱ��в���״̬ΪN������ֻ��һ������˷���ֵ�����ӱ�pk���ࣩ
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVO
   * @param status
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 ����11:52:04
   */
  private Map<String, StatusOnWayItemVO> getOnwayVOs(
      Map<String, OrderItemVO> bvoMap, Integer status) {

    String[] bvoPkArray = bvoMap.keySet().toArray(new String[bvoMap.size()]);

    SqlBuilder sqlbd = new SqlBuilder();

    sqlbd.append(" and ");
    sqlbd.append(StatusOnWayItemVO.FONWAYSTATUS, status);
    sqlbd.append(" and ");
    sqlbd.append(StatusOnWayItemVO.ISOPERATED, "N");
    sqlbd.append(" and ");
    // ��ʱ��
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_37.name());
    sqlbd.append(builder.buildSQL(StatusOnWayItemVO.PK_ORDER_B, bvoPkArray));

    VOQuery<StatusOnWayItemVO> voquery =
        new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);
    StatusOnWayItemVO[] onwayVOs = voquery.query(sqlbd.toString(), null);

    // �����ӱ�pk����map
    return OnwayBpTools.groupKeyMap(onwayVOs, StatusOnWayItemVO.PK_ORDER_B);
  }

  /**
   * ��������������������״̬����
   * <p>
   * <b>����˵��</b>
   * 
   * @param currOnwayVOMap
   * @param bvoMap
   * @param nextStatus
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 ����03:33:40
   */
  private void insertNextStatus(Map<String, StatusOnWayItemVO> currOnwayVOMap,
      Map<String, OrderItemVO> bvoMap, Integer nextStatus) {

    Set<StatusOnWayItemVO> insertOnwaySet = new HashSet<StatusOnWayItemVO>();

    Set<Map.Entry<String, OrderItemVO>> bvoSet = bvoMap.entrySet();
    for (Map.Entry<String, OrderItemVO> bvoMapEntry : bvoSet) {

      StatusOnWayItemVO insertOnwayVO =
          this.creatInsertStatusVO(currOnwayVOMap, bvoMapEntry, nextStatus);

      insertOnwaySet.add(insertOnwayVO);

    }

    StatusOnWayItemVO[] insertOnwayVOArray =
        insertOnwaySet.toArray(new StatusOnWayItemVO[insertOnwaySet.size()]);

    VOInsert<StatusOnWayItemVO> voinsert = new VOInsert<StatusOnWayItemVO>();
    voinsert.insert(insertOnwayVOArray);
  }

  /**
   * �����������������µ�ǰ״̬������
   * <p>
   * <b>����˵��</b>
   * 
   * @param currOnwayVOMap
   * @param bvoMap
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-28 ����08:57:12
   */
  private void updateCurrentStatus(
      Map<String, StatusOnWayItemVO> currOnwayVOMap,
      Map<String, OrderItemVO> bvoMap) {

    List<StatusOnWayItemVO> updateCurrList = new ArrayList<StatusOnWayItemVO>();
    List<StatusOnWayItemVO> updateOrigList = new ArrayList<StatusOnWayItemVO>();

    Set<Map.Entry<String, StatusOnWayItemVO>> onwaySet =
        currOnwayVOMap.entrySet();
    for (Map.Entry<String, StatusOnWayItemVO> onwayMapEntry : onwaySet) {

      this.creatUpdateCurrOnwayVO(bvoMap, updateCurrList, updateOrigList,
          onwayMapEntry);
    }

    StatusOnWayItemVO[] updateCurrArray =
        updateCurrList.toArray(new StatusOnWayItemVO[updateCurrList.size()]);
    StatusOnWayItemVO[] updateOrigArray =
        updateOrigList.toArray(new StatusOnWayItemVO[updateOrigList.size()]);

    VOUpdate<StatusOnWayItemVO> voupdate = new VOUpdate<StatusOnWayItemVO>();
    voupdate.update(updateCurrArray, updateOrigArray);
  }

  /**
   * �������������������Ѿ����ڵ���״̬����
   * <p>
   * <b>����˵��</b>
   * 
   * @param bvoMap
   * @param onwayVOMap
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 ����02:35:38
   */
  private Map<String, OrderItemVO> updateExistNextStatus(
      Map<String, OrderItemVO> bvoMap, Map<String, StatusOnWayItemVO> onwayVOMap) {

    Set<Map.Entry<String, StatusOnWayItemVO>> onwayVOEntrySet =
        onwayVOMap.entrySet();

    List<StatusOnWayItemVO> updateList = new ArrayList<StatusOnWayItemVO>();
    List<StatusOnWayItemVO> originList = new ArrayList<StatusOnWayItemVO>();

    Map<String, OrderItemVO> notExistBvoMap =
        new HashMap<String, OrderItemVO>();
    notExistBvoMap.putAll(bvoMap);

    for (Map.Entry<String, StatusOnWayItemVO> onwayVOMapEntry : onwayVOEntrySet) {
      String pk_order_b = onwayVOMapEntry.getKey();
      StatusOnWayItemVO onwayVO = onwayVOMapEntry.getValue();

      StatusOnWayItemVO updateVO = (StatusOnWayItemVO) onwayVO.clone();

      OrderItemVO bvo = bvoMap.get(pk_order_b);

      // ��ҳ����Ϣ����
      this.fillInputInfo(updateVO, bvo);

      // ���ɸ�����VO
      updateList.add(updateVO);
      originList.add(onwayVO);

      // ���Ѿ����ڵ�VOȥ��
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
