/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-21 ����02:40:08
 */
package nc.impl.pu.m21.onway.bp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21.pub.TrantypeUtil;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��;״̬���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-21 ����02:40:08
 */
public class OutputUpdateBp {

  // δ����
  private static final String notOperated = "N";

  public void updateOutput(OrderVO[] orderVOs) {

    List<StatusOnWayItemVO> insList = new ArrayList<StatusOnWayItemVO>();
    List<StatusOnWayItemVO> updList = new ArrayList<StatusOnWayItemVO>();

    // ������һ״̬������.
    // ������ĸ��ŵ��ݰ��������ͷ���
    Map<String, List<OrderVO>> orderMaplist =
        OnwayBpTools.groupByTrantype(orderVOs);

    // ��ͬ�������͵ĵ���,ȡ�����е��ӱ�id
    Set<Map.Entry<String, List<OrderVO>>> mapEntry = orderMaplist.entrySet();

    // ��Բ�ͬ�Ľ�������,��״̬���ܲ���,��˷ֿ�����
    for (Map.Entry<String, List<OrderVO>> entry : mapEntry) {

      String tranType = entry.getKey();
      List<OrderVO> orderList = entry.getValue();
      // ȡ�õ�ǰ״̬��VO
      Map<String, OrderItemVO> bodyPkMap = OnwayBpTools.getBodyPk(orderList);

      String[] bodyPkArray =
          bodyPkMap.keySet().toArray(new String[bodyPkMap.keySet().size()]);

      Map<String, StatusOnWayItemVO> currentStatusVOMap =
          this.getExistVO(bodyPkArray, OnwayStatus.STATUS_OUTPUT);
      // ��ѯ����ǰ״̬Ϊ��,˵�����ֲ���,�״�
      if (currentStatusVOMap.size() == 0) {
        String tip = null;
        String message =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0",
                "0pubapp-0177")/* @res "���ֲ����������²�ѯ" */;
        ExceptionUtils.wrappBusinessException(message, tip);
      }

      Map<String, PoTransTypeVO> tranTypeMap =
          OnwayBpTools.getTransTypeVOs(orderVOs, OrderHeaderVO.VTRANTYPECODE);

      // ������һ״̬
      this.insertNextStatus(bodyPkMap, tranTypeMap, currentStatusVOMap,
          insList, tranType, OnwayStatus.STATUS_OUTPUT);

      // ��������״̬������
      this.updateSelfStatus(currentStatusVOMap, updList);
    }

    // ��������
    VOInsert<StatusOnWayItemVO> insertTool = new VOInsert<StatusOnWayItemVO>();
    insertTool.insert(insList.toArray(new StatusOnWayItemVO[insList.size()]));

    // ��������
    VOUpdate<StatusOnWayItemVO> updateTool = new VOUpdate<StatusOnWayItemVO>();
    updateTool.update(updList.toArray(new StatusOnWayItemVO[updList.size()]),
        new String[] {
          StatusOnWayItemVO.ISOPERATED
        });

    // �����Զ�����
    // BillTransferTool<OrderVO> transferTool =
    // new BillTransferTool<OrderVO>(orderVOs);
    // BillUpdate<OrderVO> billUpdate = new BillUpdate<OrderVO>();
    // OrderVO[] oldVOs = transferTool.getOriginBills();
    // billUpdate.update(orderVOs, oldVOs);
  }

  /**
   * ��������������������Ҫ�����VO
   * <p>
   * <b>����˵��</b>
   * 
   * @param nextStatusVOMap
   * @param pkMap
   * @param nextStatus
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-25 ����11:48:32
   */
  private StatusOnWayItemVO[] creatInsertDataForOutput(
      Map<String, StatusOnWayItemVO> nextStatusVOMap,
      Map<String, OrderItemVO> pkMap, Integer nextStatus) {

    Set<Map.Entry<String, StatusOnWayItemVO>> nextStatusVO =
        nextStatusVOMap.entrySet();

    List<StatusOnWayItemVO> statusVOList = new ArrayList<StatusOnWayItemVO>();

    for (Map.Entry<String, StatusOnWayItemVO> nextStatusEntry : nextStatusVO) {
      String pk_order_b = nextStatusEntry.getKey();
      StatusOnWayItemVO statusVO = nextStatusEntry.getValue();
      StatusOnWayItemVO insertVO = (StatusOnWayItemVO) statusVO.clone();
      // ��;������Ϊ������
      insertVO.setNonwaynum(pkMap.get(pk_order_b).getNnum());
      // ��;״̬Ϊ��һ״̬
      insertVO.setFonwaystatus(nextStatus);
      // ���·���PK
      // this.resetPk(insertVO);
      insertVO.setPk_order_bb(null);

      statusVOList.add(insertVO);
    }

    return statusVOList.toArray(new StatusOnWayItemVO[statusVOList.size()]);
  }

  /**
   * ������������������ÿ������״̬�Ŀɲ�������ֻ�ܴ���һ������˰����ӱ�pkҲ���Խ����ݷֿ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param onwayStatusVOs
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-25 ����10:31:51
   */
  private Map<String, StatusOnWayItemVO> formatReturnData(
      StatusOnWayItemVO[] onwayStatusVOs) {

    Map<String, StatusOnWayItemVO> onwayItemVO =
        new HashMap<String, StatusOnWayItemVO>();
    for (StatusOnWayItemVO onwayStatusVO : onwayStatusVOs) {
      onwayItemVO.put(onwayStatusVO.getPk_order_b(), onwayStatusVO);
    }

    return onwayItemVO;
  }

  /**
   * ��������������ȡ���Ѿ����ڵ�VO
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderList
   * @param nextStatus
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-21 ����04:40:48
   */
  private Map<String, StatusOnWayItemVO> getExistVO(String[] pks,
      OnwayStatus nextStatus) {

    SqlBuilder sqlBd = new SqlBuilder();
    sqlBd.append(" and ");
    sqlBd.append(StatusOnWayItemVO.FONWAYSTATUS, nextStatus.toInt());
    sqlBd.append("and ");
    // ��ʱ��
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_42.name());
    sqlBd.append(builder.buildSQL(StatusOnWayItemVO.PK_ORDER_B, pks));
    sqlBd.append("and ");
    sqlBd.append(StatusOnWayItemVO.ISOPERATED, OutputUpdateBp.notOperated);
    VOQuery<StatusOnWayItemVO> voquery =
        new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);
    StatusOnWayItemVO[] onwayStatusVOs = voquery.query(sqlBd.toString(), null);

    // ���ӱ�pk�����ݷֿ�
    return this.formatReturnData(onwayStatusVOs);
  }

  /**
   * ��������������������һ״̬������
   * <p>
   * <b>����˵��</b>
   * 
   * @param pkMap
   * @param tranTypeMap
   * @param currentStatusVOMap
   * @param insList
   * @param tranType
   * @param status
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-25 ����04:33:15
   */
  private void insertNextStatus(Map<String, OrderItemVO> pkMap,
      Map<String, PoTransTypeVO> tranTypeMap,
      Map<String, StatusOnWayItemVO> currentStatusVOMap,
      List<StatusOnWayItemVO> insList, String tranType, OnwayStatus status) {

    // String nextStr = OnwayBpTools.getNextStatusStr(status);

    // if (StringUtils.isEmpty(nextStr)) {
    // ExceptionUtils.wrappBusinessException("��ǰ״̬������;����״̬֮һ������");
    // }

    // ȡ����һ״̬
    Integer nextStatus =
        TrantypeUtil.getInstance().getNextStatus(status.toInt(),
            tranTypeMap.get(tranType));
    // ((Integer) tranTypeMap.get(tranType).getAttributeValue(nextStr))
    // .intValue();

    // �����һ״̬�����ڣ�����������
    if (nextStatus == null) {
      return;
    }

    // ����Ҫ���������
    StatusOnWayItemVO[] insertVO =
        this.creatInsertDataForOutput(currentStatusVOMap, pkMap, nextStatus);

    insList.addAll(Arrays.asList(insertVO));
  }

  /**
   * �����������������·���pk
   * <p>
   * <b>����˵��</b>
   * 
   * @param bvo
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-3 ����03:32:17
   */
  // private void resetPk(StatusOnWayItemVO bvo) {
  // DBTool dao = new DBTool();
  // String[] ids = dao.getOIDs(1);
  // String pk_order_bb = ids[0];
  // bvo.setPk_order_bb(pk_order_bb);
  // bvo.setPrimaryKey(pk_order_bb);
  // }

  /**
   * ����������������������״̬
   * <p>
   * <b>����˵��</b>
   * 
   * @param currentStatusVO
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-25 ����11:47:48
   */
  private void updateSelfStatus(Map<String, StatusOnWayItemVO> currentStatusVO,
      List<StatusOnWayItemVO> updList) {

    Collection<StatusOnWayItemVO> statusVOs = currentStatusVO.values();

    List<StatusOnWayItemVO> statusList = new ArrayList<StatusOnWayItemVO>();

    for (StatusOnWayItemVO statusVO : statusVOs) {
      StatusOnWayItemVO updateData = (StatusOnWayItemVO) statusVO.clone();
      updateData.setIsoperated(UFBoolean.TRUE);
      statusList.add(updateData);
    }

    updList.addAll(statusList);
  }
}
