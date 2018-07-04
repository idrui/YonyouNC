/**
 * $�ļ�˵��$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-18 ����10:35:42
 */
package nc.bs.pu.m21.query;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.scmpub.util.SCMDataAccessUtils;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21.pub.SplitOrderVOUtil;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.VOSortUtils;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ�������̨��ѯ������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-18 ����10:35:42
 */
public class OrderQueryUtil {

  /**
   * ��������������Ϊ�ɹ���ⵥ�͵������ṩ�Ĺ�����ѯ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param sql
   * @param isLazy
   * @return <p>
   * @since 6.0
   * @author zhyhang
   * @time 2010-1-19 ����08:35:23
   */
  public static OrderVO[] queryFor45_23(String sql, UFBoolean isLazy) {
    //DataAccessUtils utils = new DataAccessUtils();
  	/*
  	 * change by wandl
  	 * ת���������Ʋ�ѯ������10000�У�����10000�лᱨ����ʾ��С��ѯ��Χ��
  	 */
  	SCMDataAccessUtils utils = new SCMDataAccessUtils(10000);
    // ��ѯ��ͷ�����塢�����ƻ���id
    IRowSet rowset = utils.query(sql);
    Set<String> headids = new HashSet<String>();
    Set<String> itemids = new HashSet<String>();
    Set<String> bb1ids = new HashSet<String>();
    // ��;״̬���ӱ�pk
    Set<String> onwayPks = new HashSet<String>();
    while (rowset.next()) {
      if (!StringUtil.isEmptyWithTrim(rowset.getString(0))) {
        headids.add(rowset.getString(0));
      }
      if (!StringUtil.isEmptyWithTrim(rowset.getString(1))) {
        itemids.add(rowset.getString(1));
      }
      if (!StringUtil.isEmptyWithTrim(rowset.getString(2))) {
        bb1ids.add(rowset.getString(2));
      }
      if (!StringUtil.isEmptyWithTrim(rowset.getString(3))) {
        onwayPks.add(rowset.getString(3));
      }
    }
    if (0 == headids.size() || 0 == itemids.size()) {
      return null;
    }

    if (isLazy.booleanValue()) {
      //
    }
    OrderHeaderVO[] headers =
        new VOQuery<OrderHeaderVO>(OrderHeaderVO.class).query(headids
            .toArray(new String[headids.size()]));
    OrderItemVO[] items =
        new VOQuery<OrderItemVO>(OrderItemVO.class).query(itemids
            .toArray(new String[itemids.size()]));
    // ��;״̬
    StatusOnWayItemVO[] onwayVOs =
        new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class).query(onwayPks
            .toArray(new String[onwayPks.size()]));

    Map<String, OrderItemVO> map = CirVOUtil.createKeyVOMap(items);
    for (StatusOnWayItemVO onwayVO : onwayVOs) {
      String pk_order_b = onwayVO.getPk_order_b();
      OrderItemVO itemVO = map.get(pk_order_b);
      if (null == itemVO) {
        continue;
      }
      UFDouble nonwaynum = onwayVO.getNonwaynum();
      if (OnwayStatus.STATUS_ARRIVE.toInt() == onwayVO.getFonwaystatus()
          .intValue()) {
        itemVO.setNcanarrivenum(MathTool.sub(nonwaynum,
            itemVO.getNaccumarrvnum()));
      }
      if (OnwayStatus.STATUS_STORE.toInt() == onwayVO.getFonwaystatus()
          .intValue()) {
        itemVO
            .setNcaninnum(MathTool.sub(nonwaynum, itemVO.getNaccumstorenum()));
      }
    }

    BillComposite<OrderVO> bc = new BillComposite<OrderVO>(OrderVO.class);
    OrderVO tempVO = new OrderVO();
    bc.append(tempVO.getMetaData().getParent(), headers);
    bc.append(tempVO.getMetaData().getVOMeta(OrderItemVO.class), items);
    OrderVO[] orderVOs = bc.composite();

    if (bb1ids.isEmpty()) {
      OrderQueryUtil.sort(orderVOs);
      return orderVOs;
    }

    // ���ڵ����ƻ�
    OrderReceivePlanVO[] rpVOs =
        new VOQuery<OrderReceivePlanVO>(OrderReceivePlanVO.class).query(bb1ids
            .toArray(new String[bb1ids.size()]));

    // �ṩ��;״̬����������Ҫ������;�����뵽���ƻ����С�
    // ��
    orderVOs =
        SplitOrderVOUtil.getInstance().splitOrderVOByRPVOs(orderVOs, rpVOs);
    OrderQueryUtil.sort(orderVOs);
    return orderVOs;
  }

  private static void sort(OrderVO[] orderVOs) {
    for (OrderVO orderVO : orderVOs) {
      OrderItemVO[] itemVOs = orderVO.getBVO();
      VOSortUtils.ascSort(itemVOs, OrderItemVO.CROWNO);
      orderVO.setBVO(itemVOs);
    }
  }
}
