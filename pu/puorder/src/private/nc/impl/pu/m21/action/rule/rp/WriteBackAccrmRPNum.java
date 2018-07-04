/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-15 下午06:42:49
 */
package nc.impl.pu.m21.action.rule.rp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.data.vo.VOUpdateTS;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>更新订单子表的累计到货计划数量
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-15 下午06:42:49
 */
public class WriteBackAccrmRPNum implements ICompareRule<BatchOperateVO> {

  private OrderVO orderVO;

  public WriteBackAccrmRPNum(OrderVO orderVO) {
    this.orderVO = orderVO;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.ICompareRule#process(E[], E[])
   */
  @Override
  public void process(BatchOperateVO[] vos, BatchOperateVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos) || ArrayUtils.isEmpty(originVOs)) {
      return;
    }

    if (null == vos[0] || null == originVOs[0] || null == this.orderVO) {
      return;
    }

    this.writebackAccrpnum(vos[0], originVOs[0], this.orderVO);
  }

  /**
   * 方法功能描述：新增回写
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param itemMap
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 下午03:22:02
   */
  private void addWriteBack(BatchOperateVO vo, Map<String, OrderItemVO> itemMap) {
    // 新增的VO
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
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 下午02:00:40
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
   * 方法功能描述：删除回写
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param itemMap
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 下午03:22:56
   */
  private void deleteWriteBack(BatchOperateVO originVO,
      Map<String, OrderItemVO> itemMap) {
    // 删除的VO
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
   * 方法功能描述：根据传入的OrderReceivePlanVO数组得到相对应的OrderItemVO的主键数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param set
   * @param objs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-16 下午06:22:24
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
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param batchVO
   * @param vo
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-13 下午06:11:14
   */
  private OrderItemVO[] getOrderItemVOs(BatchOperateVO batchVO, OrderVO vo) {
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
    // vo
    // });
    BillIndex index = new BillIndex(new OrderVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(OrderItemVO.class);
    Set<OrderItemVO> setItemVO = new HashSet<OrderItemVO>();
    Iterator<String> iter = set.iterator();
    while (iter.hasNext()) {
      // setItemVO.add(map.get(iter.next()));
      setItemVO.add((OrderItemVO) index.get(meta, iter.next()));
    }

    return setItemVO.toArray(new OrderItemVO[0]);
  }

  /**
   * 方法功能描述：刷新表头Ts
   * <p>
   * <b>参数说明</b>
   * 
   * @param batchVO
   * @param updateItemVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 下午02:43:26
   */
  private void refreshTs(OrderVO vo) {
    // 刷新表头Ts
    VOUpdateTS<OrderHeaderVO> updateHeaderVOTs =
        new VOUpdateTS<OrderHeaderVO>();
    updateHeaderVOTs.update(new OrderHeaderVO[] {
      vo.getHVO()
    });
  }

  /**
   * 方法功能描述：更新回写
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param originVO
   * @param itemMap
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 下午03:23:00
   */
  private void updateWriteBack(BatchOperateVO vo, BatchOperateVO originVO,
      Map<String, OrderItemVO> itemMap) {
    // 更新后的VO
    Object[] upVOs = vo.getUpdObjs();
    if (ArrayUtils.isEmpty(upVOs)) {
      return;
    }

    // 更新前的VO
    Object[] originUpVOs = originVO.getUpdObjs();
    Map<String, OrderReceivePlanVO> upMap = this.createRPMap(upVOs);
    Map<String, OrderReceivePlanVO> originUpMap = this.createRPMap(originUpVOs);

    // 更新
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
   * 方法功能描述：回写
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param originVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 下午03:00:59
   */
  private void writeBackAccrmRPNum(BatchOperateVO vo, BatchOperateVO originVO,
      OrderVO orderVo) {
    // 需要操作的表体
    OrderItemVO[] itemVOs = this.getOrderItemVOs(vo, orderVo);

    if (ArrayUtils.isEmpty(itemVOs)) {
      return;
    }

    Map<String, OrderItemVO> itemMap = new HashMap<String, OrderItemVO>();
    for (OrderItemVO itemVO : itemVOs) {
      itemMap.put(itemVO.getPk_order_b(), itemVO);
    }

    // 新增
    this.addWriteBack(vo, itemMap);
    // 删除
    this.deleteWriteBack(originVO, itemMap);
    // 更新
    this.updateWriteBack(vo, originVO, itemMap);

    for (OrderItemVO itemVO : itemVOs) {
      if (MathTool.compareTo(itemVO.getNaccumrpnum(), UFDouble.ZERO_DBL) > 0) {
        itemVO.setBreceiveplan(UFBoolean.TRUE);
      }
      else {
        itemVO.setBreceiveplan(UFBoolean.FALSE);
      }
    }

    VOUpdate<OrderItemVO> update = new VOUpdate<OrderItemVO>();
    update.update(itemVOs, new String[] {
      OrderItemVO.NACCUMRPNUM, OrderItemVO.BRECEIVEPLAN
    });
  }

  private void writebackAccrpnum(BatchOperateVO vo, BatchOperateVO originVO,
      OrderVO orderVo) {
    // 回写累计到货计划数量
    this.writeBackAccrmRPNum(vo, originVO, orderVo);

    // 刷新Ts
    this.refreshTs(orderVo);
  }
}
