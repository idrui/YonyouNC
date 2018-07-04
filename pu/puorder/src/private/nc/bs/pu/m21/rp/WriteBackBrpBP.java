/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-20 ����08:10:39
 */
package nc.bs.pu.m21.rp;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-20 ����08:10:39
 */
public class WriteBackBrpBP {

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param batchVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-20 ����08:14:13
   */
  public void writebackBRP(BatchOperateVO batchVO, OrderVO orderVO) {
    // ɾ����VO
    Object[] delVOs = batchVO.getDelObjs();
    // ����ɾ��VO��Ӧ�ı��嵽���ƻ���־
    if (!ArrayUtils.isEmpty(delVOs)) {
      this.deleteUpdateBRP(delVOs, orderVO);
    }

    // ������VO
    Object[] addVOs = batchVO.getAddObjs();
    // ��������VO��Ӧ�Ķ�������ĵ����ƻ���־
    if (!ArrayUtils.isEmpty(addVOs)) {
      this.addUpdateBRP(addVOs, orderVO);
    }
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param addVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-20 ����08:20:30
   */
  private void addUpdateBRP(Object[] addVOs, OrderVO orderVO) {
    VOUpdate<OrderItemVO> update = new VOUpdate<OrderItemVO>();
    OrderItemVO[] addItemVOs = this.getOrderItemVOs(addVOs, orderVO);

    if (ArrayUtils.isEmpty(addItemVOs)) {
      return;
    }
    // �������ƻ���־����ΪY
    for (OrderItemVO itemVO : addItemVOs) {
      itemVO.setBreceiveplan(UFBoolean.TRUE);
    }
    update.update(addItemVOs, new String[] {
      OrderItemVO.BRECEIVEPLAN
    });
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param delVOs
   * @param orderVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-20 ����08:19:57
   */
  private void deleteUpdateBRP(Object[] delVOs, OrderVO orderVO) {
    VOUpdate<OrderItemVO> update = new VOUpdate<OrderItemVO>();
    // �����ƻ����б��β����Ķ��������еı���
    Set<String> pkOrderBSet = this.getItemPks(orderVO);

    Set<String> del = new HashSet<String>();
    for (int i = 0; i < delVOs.length; ++i) {
      if (null == delVOs[i]) {
        continue;
      }
      OrderReceivePlanVO vo = (OrderReceivePlanVO) delVOs[i];
      String pkOrderB = vo.getPk_order_b();
      // �������pkOrderBSet�У�˵�����Խ����Ӧ����ĵ����ƻ�����ΪN
      if ((null == pkOrderBSet) || !pkOrderBSet.contains(pkOrderB)) {
        del.add(pkOrderB);
      }
    }

    if (0 == del.size()) {
      return;
    }

    // ���ñ��嵽���ƻ�ΪN
    String[] delBIds = del.toArray(new String[0]);
    // Map<String, OrderItemVO> map = AggVOUtil.createItemMap(new OrderVO[] {
    // orderVO
    // });
    BillIndex index = new BillIndex(new OrderVO[] {
      orderVO
    });
    IVOMeta meta = orderVO.getMetaData().getVOMeta(OrderItemVO.class);
    OrderItemVO[] delItemVOs = new OrderItemVO[delBIds.length];
    for (int i = 0; i < delBIds.length; ++i) {
      // delItemVOs[i] = map.get(delBIds[i]);
      delItemVOs[i] = (OrderItemVO) index.get(meta, delBIds[i]);
      delItemVOs[i].setBreceiveplan(UFBoolean.FALSE);
    }
    update.update(delItemVOs, new String[] {
      OrderItemVO.BRECEIVEPLAN
    });
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVO
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-20 ����08:23:15
   */
  private Set<String> getItemPks(OrderVO orderVO) {
    if (null == orderVO) {
      return null;
    }

    String pkOrder = orderVO.getHVO().getPk_order();
    if (null == pkOrder) {
      return null;
    }

    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct pk_order_b from po_order_bb1 where pk_order='"
        + pkOrder + "' and dr=0 ");
    // VOQuery<OrderReceivePlanVO> query = new VOQuery<OrderReceivePlanVO>(
    // OrderReceivePlanVO.class);
    // OrderReceivePlanVO[] rpVOs = query.query(" and pk_order='" + pkOrder
    // + "' and dr=0 ", null);
    // if (!ArrayUtil.isEmpty(rpVOs)) {
    // Set<String> set = new HashSet<String>();
    // for (OrderReceivePlanVO rpVO : rpVOs) {
    // set.add(rpVO.getPk_order_b());
    // }
    //
    // return set;
    // }
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    String[] pks = rowset.toOneDimensionStringArray();
    if (ArrayUtils.isEmpty(pks)) {
      return null;
    }
    Set<String> set = new HashSet<String>();
    for (String pk : pks) {
      set.add(pk);
    }
    return set;
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param addVOs
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-20 ����09:28:01
   */
  private OrderItemVO[] getOrderItemVOs(Object[] addVOs, OrderVO orderVO) {
    Set<String> setPKOrderB = new HashSet<String>();
    for (int i = 0; i < addVOs.length; ++i) {
      if (null == addVOs[i]) {
        continue;
      }
      OrderReceivePlanVO rpVO = (OrderReceivePlanVO) addVOs[i];
      String pkOrderB = rpVO.getPk_order_b();
      setPKOrderB.add(pkOrderB);
    }

    if (setPKOrderB.isEmpty()) {
      return null;
    }

    Set<OrderItemVO> setItemVO = new HashSet<OrderItemVO>();
    // Map<String, OrderItemVO> map = AggVOUtil.createItemMap(new OrderVO[] {
    // orderVO
    // });
    BillIndex index = new BillIndex(new OrderVO[] {
      orderVO
    });
    IVOMeta meta = orderVO.getMetaData().getVOMeta(OrderItemVO.class);
    Iterator<String> iter = setPKOrderB.iterator();
    while (iter.hasNext()) {
      String pkOrderB = iter.next();
      // OrderItemVO itemVO = map.get(pkOrderB);
      OrderItemVO itemVO = (OrderItemVO) index.get(meta, pkOrderB);
      UFBoolean breceiveplan = itemVO.getBreceiveplan();
      if ((null == breceiveplan) || !breceiveplan.booleanValue()) {
        setItemVO.add(itemVO);
      }
    }

    if (setItemVO.size() > 0) {
      return setItemVO.toArray(new OrderItemVO[0]);
    }

    return null;
  }

}
