/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-26 上午08:49:04
 */
package nc.impl.pu.m21.action.rule.approve;

import java.util.HashSet;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.pub.constant.PUTempTable;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单弃审时删除在途状态表中的数据
 * @scene
 *        采购订单取消审核
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:12:58
 * @author luojw
 */
public class DeleteStatusOnWayRule implements IRule<OrderVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    Set<String> pkSet = new HashSet<String>();
    for (OrderVO vo : vos) {
      pkSet.add(vo.getHVO().getPk_order());
    }

    VOQuery<StatusOnWayItemVO> query =
        new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);
    StatusOnWayItemVO[] itemVOs =
        query.query(
            " and "
                + new IDExQueryBuilder(PUTempTable.tmp_po_21_16.name())
                    .buildSQL("pk_order",
                        pkSet.toArray(new String[pkSet.size()])), null);
    if (ArrayUtils.isEmpty(itemVOs)) {
      return;
    }

    VODelete<StatusOnWayItemVO> delete = new VODelete<StatusOnWayItemVO>();
    delete.delete(itemVOs);
  }
}
