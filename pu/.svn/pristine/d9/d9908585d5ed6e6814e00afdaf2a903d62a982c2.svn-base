/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-15 下午02:14:48
 */
package nc.impl.pu.m21.action.rule.rp;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写订单子表的到货计划标志
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-15 下午02:14:48
 */
public class WriteBackOrderItemBRP implements IRule<BatchOperateVO> {

  private OrderVO orderVO;

  public WriteBackOrderItemBRP(OrderVO orderVO) {
    this.orderVO = orderVO;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(BatchOperateVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    this.writebackBRP(this.orderVO);
  }

  /**
   * 方法功能描述：回写到货计划标志
   * <p>
   * <b>参数说明</b>
   * 
   * @param batchVO
   * @param vo
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-20 上午08:14:13
   */
  public void writebackBRP(OrderVO vo) {
    DataAccessUtils util = new DataAccessUtils();
    String pk_order = vo.getHVO().getPk_order();

    SqlBuilder sql = new SqlBuilder();
    sql.append("update po_order_b set breceiveplan = 'N' ");
    sql.append("where ");
    sql.append(OrderItemVO.PK_ORDER, pk_order);
    sql.append(" and dr = 0");
    sql.append(" and ");
    sql.append(OrderItemVO.FISACTIVE, "<>",
        (Integer) EnumActive.REVISEHISTORY.value());
    util.update(sql.toString());

    sql.reset();
    sql.append("update po_order_b po_order_b set po_order_b.breceiveplan = 'Y' ");
    sql.append("where ");
    sql.append("po_order_b." + OrderItemVO.PK_ORDER, pk_order);
    sql.append(" and exists ( select 1 from po_order_bb1 po_order_bb1 ");
    sql.append(" where po_order_bb1.pk_order_b = po_order_b.pk_order_b ");
    sql.append(" and po_order_bb1.dr=0 ");
    sql.append(" and po_order_bb1.pk_order", pk_order);
    sql.append(" ) ");
    sql.append(" and po_order_b.dr =0 ");
    sql.append(" and po_order_b." + OrderItemVO.FISACTIVE, "<>",
        (Integer) EnumActive.REVISEHISTORY.value());
    util.update(sql.toString());
  }
}
