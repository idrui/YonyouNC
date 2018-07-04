/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-20 ����08:10:21
 */
package nc.bs.pu.m21.rp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.data.vo.VOUpdateTS;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.MathTool;

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
 * @time 2010-4-20 ����08:10:21
 */
public class WriteBackAccrpnumBP {

  public void writebackAccrpnum(BatchOperateVO vo, BatchOperateVO originVO,
      OrderVO orderVO) {
    if ((null == vo) || (null == originVO) || (null == orderVO)) {
      return;
    }

    // ��д
    this.writeBackAccrmRPNum(vo, originVO, orderVO);

    // ˢ��Ts
    this.refreshTs(orderVO);
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @param itemMap
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 ����03:22:02
   */
  private void addWriteBack(BatchOperateVO vo, Map<String, OrderItemVO> itemMap) {
    // ������VO
    Object[] addVOs = vo.getAddObjs();
    if (ArrayUtils.isEmpty(addVOs)) {
      return;
    }

    for (int i = 0; i < addVOs.length; ++i) {
      OrderReceivePlanVO rpVO = (OrderReceivePlanVO) addVOs[i];
      OrderItemVO itemVO = itemMap.get(rpVO.getPk_order_b());
      UFDouble naccumrpnum =
          MathTool.add(itemVO.getNaccumrpnum(), rpVO.getNnum());
      itemVO.setNaccumrpnum(naccumrpnum);
    }
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 ����02:00:40
   */
  private Map<String, OrderReceivePlanVO> createRPMap(Object[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    Map<String, OrderReceivePlanVO> map =
        new HashMap<String, OrderReceivePlanVO>();
    for (int i = 0; i < vos.length; ++i) {
      OrderReceivePlanVO vo = (OrderReceivePlanVO) vos[i];
      String pkOrderBB1 = vo.getPk_order_bb1();
      map.put(pkOrderBB1, vo);
    }

    return map;
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @param itemMap
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 ����03:22:56
   */
  private void deleteWriteBack(BatchOperateVO originVO,
      Map<String, OrderItemVO> itemMap) {
    // ɾ����VO
    Object[] originDelVOs = originVO.getDelObjs();
    if (ArrayUtils.isEmpty(originDelVOs)) {
      return;
    }

    for (int i = 0; i < originDelVOs.length; ++i) {
      OrderReceivePlanVO rpVO = (OrderReceivePlanVO) originDelVOs[i];
      OrderItemVO itemVO = itemMap.get(rpVO.getPk_order_b());
      UFDouble naccumrpnum =
          MathTool.sub(itemVO.getNaccumrpnum(), rpVO.getNnum());
      itemVO.setNaccumrpnum(naccumrpnum);
    }
  }

  /**
   * �����������������ݴ����OrderReceivePlanVO����õ����Ӧ��OrderItemVO����������
   * <p>
   * <b>����˵��</b>
   * 
   * @param set
   * @param objs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-16 ����06:22:24
   */
  private void getOrderItemVOIds(Set<String> set, Object[] objs) {
    for (int i = 0; i < objs.length; ++i) {
      if (null == objs[i]) {
        continue;
      }
      OrderReceivePlanVO rpVO = (OrderReceivePlanVO) objs[i];
      String pkOrderB = rpVO.getPk_order_b();
      if (!StringUtil.isEmptyWithTrim(pkOrderB)) {
        set.add(pkOrderB);
      }
    }
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @param orderVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-20 ����10:01:01
   */
  private OrderItemVO[] getOrderItemVOs(BatchOperateVO batchVO, OrderVO orderVO) {
    if (null == batchVO) {
      return null;
    }

    Set<String> set = new HashSet<String>();

    Object[] addVOs = batchVO.getAddObjs();
    if (!ArrayUtils.isEmpty(addVOs)) {
      this.getOrderItemVOIds(set, addVOs);
    }

    Object[] delVOs = batchVO.getDelObjs();
    if (!ArrayUtils.isEmpty(delVOs)) {
      this.getOrderItemVOIds(set, delVOs);
    }

    Object[] upVOs = batchVO.getUpdObjs();
    if (!ArrayUtils.isEmpty(upVOs)) {
      this.getOrderItemVOIds(set, upVOs);
    }

    if (set.isEmpty()) {
      return null;
    }

    // Map<String, OrderItemVO> map = AggVOUtil.createItemMap(new OrderVO[] {
    // orderVO
    // });
    BillIndex index = new BillIndex(new OrderVO[] {
      orderVO
    });
    IVOMeta meta = orderVO.getMetaData().getVOMeta(OrderItemVO.class);
    Set<OrderItemVO> setItemVO = new HashSet<OrderItemVO>();
    Iterator<String> iter = set.iterator();
    while (iter.hasNext()) {
      // setItemVO.add(map.get(iter.next()));
      setItemVO.add((OrderItemVO) index.get(meta, iter.next()));
    }

    return setItemVO.toArray(new OrderItemVO[0]);
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param batchVO
   * @param updateItemVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 ����02:43:26
   */
  private void refreshTs(OrderVO orderVO) {
    // ˢ�±�ͷTs
    VOUpdateTS<OrderHeaderVO> updateHeaderVOTs =
        new VOUpdateTS<OrderHeaderVO>();
    updateHeaderVOTs.update(new OrderHeaderVO[] {
      orderVO.getHVO()
    });
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @param originVO
   * @param itemMap
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 ����03:23:00
   */
  private void updateWriteBack(BatchOperateVO vo, BatchOperateVO originVO,
      Map<String, OrderItemVO> itemMap) {
    // ���º��VO
    Object[] upVOs = vo.getUpdObjs();
    if (ArrayUtils.isEmpty(upVOs)) {
      return;
    }

    // ����ǰ��VO
    Object[] originUpVOs = originVO.getUpdObjs();
    Map<String, OrderReceivePlanVO> upMap = this.createRPMap(upVOs);
    Map<String, OrderReceivePlanVO> originUpMap = this.createRPMap(originUpVOs);

    // ����
    if (upMap != null) {
      for (Map.Entry<String, OrderReceivePlanVO> upEntry : upMap.entrySet()) {
        OrderReceivePlanVO rpVO = upEntry.getValue();
        OrderItemVO itemVO = itemMap.get(rpVO.getPk_order_b());
        UFDouble upNum =
            MathTool.sub(rpVO.getNnum(), originUpMap.get(upEntry.getKey())
                .getNnum());
        UFDouble naccumrpnum = MathTool.add(itemVO.getNaccumrpnum(), upNum);
        itemVO.setNaccumrpnum(naccumrpnum);
      }
    }
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @param originVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 ����03:00:59
   */
  private OrderItemVO[] writeBackAccrmRPNum(BatchOperateVO vo,
      BatchOperateVO originVO, OrderVO orderVO) {
    // ��Ҫ�����ı���
    OrderItemVO[] itemVOs = this.getOrderItemVOs(vo, orderVO);
    // = ReceivePlanUtils.getInstance().getOrderItemVOs(vo);

    Map<String, OrderItemVO> itemMap = new HashMap<String, OrderItemVO>();
    for (OrderItemVO itemVO : itemVOs) {
      itemMap.put(itemVO.getPk_order_b(), itemVO);
    }

    // ����
    this.addWriteBack(vo, itemMap);
    // ɾ��
    this.deleteWriteBack(originVO, itemMap);
    // ����
    this.updateWriteBack(vo, originVO, itemMap);

    VOUpdate<OrderItemVO> update = new VOUpdate<OrderItemVO>();
    OrderItemVO[] updateItemVOs = update.update(itemVOs, new String[] {
      OrderItemVO.NACCUMRPNUM
    });

    return updateItemVOs;
  }
}
