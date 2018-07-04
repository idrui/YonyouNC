/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-31 下午01:59:01
 */
package nc.bs.pu.m21.writeback.source;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.itf.pu.reference.pp.PPServices;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pp.m28.entity.GenNumWriteBackVO;
import nc.vo.pp.m28.enumeration.EnumOperate;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.bill.BillRowCompare;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写价格审批单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-31 下午01:59:01
 */
public class WriteBack28 {

  public void writeback(OrderVO[] vos, OrderVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos)) {
      // 删除
      this.writebackWhenDelete(originVOs);
    }
    else if (ArrayUtils.isEmpty(originVOs)) {
      // 新增
      this.writebackWhenInsert(vos);
    }
    else {
      // 修改
      this.writebackWhenUpdate(vos, originVOs);
    }
  }

  /**
   * 方法功能描述：处理删除行
   * <p>
   * <b>参数说明</b>
   * 
   * @param deleteSet
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-1 下午02:09:04
   */
  private void processDeleteRow(Set<GenNumWriteBackVO> deleteSet,
      OrderItemVO itemVO) {
    String cpriceaudit_bid = itemVO.getCpriceaudit_bid();
    if (StringUtil.isEmptyWithTrim(cpriceaudit_bid)) {
      return;
    }
    GenNumWriteBackVO vo = new GenNumWriteBackVO();
    vo.setPk_priceaudit(itemVO.getCpriceauditid());
    vo.setPk_priceaudit_b(cpriceaudit_bid);
    vo.setOperateFlag(EnumOperate.DELETE);

    deleteSet.add(vo);
  }

  /**
   * 方法功能描述：处理新增行
   * <p>
   * <b>参数说明</b>
   * 
   * @param insetSet
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-1 下午02:09:15
   */
  private void processInsertRow(Set<GenNumWriteBackVO> insetSet,
      OrderItemVO itemVO) {
    String cpriceaudit_bid = itemVO.getCpriceaudit_bid();
    if (StringUtil.isEmptyWithTrim(cpriceaudit_bid)) {
      return;
    }
    GenNumWriteBackVO vo = new GenNumWriteBackVO();
    vo.setPk_priceaudit(itemVO.getCpriceauditid());
    vo.setPk_priceaudit_b(cpriceaudit_bid);
    if (itemVO.getSourcets() != null) {
      vo.setStrHTS(itemVO.getSourcets().toString());
    }
    if (itemVO.getSourcebts() != null) {
      vo.setStrBTS(itemVO.getSourcebts().toString());
    }
    vo.setOperateFlag(EnumOperate.ADD);

    insetSet.add(vo);
  }

  /**
   * 方法功能描述：删除回写
   * <p>
   * <b>参数说明</b>
   * 
   * @param originVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-1 下午02:09:33
   */
  private void writebackWhenDelete(OrderVO[] originVOs) {
    Set<GenNumWriteBackVO> deleteSet = new HashSet<GenNumWriteBackVO>();
    for (OrderVO originVO : originVOs) {
      OrderItemVO[] itemVOs = originVO.getBVO();
      if (ArrayUtils.isEmpty(itemVOs)) {
        continue;
      }

      for (OrderItemVO itemVO : itemVOs) {
        this.processDeleteRow(deleteSet, itemVO);
      }
    }

    if (deleteSet.size() > 0) {
      PPServices.writeback28For21(deleteSet
          .toArray(new GenNumWriteBackVO[deleteSet.size()]));
    }
  }

  /**
   * 方法功能描述：新增回写
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-1 下午02:09:45
   */
  private void writebackWhenInsert(OrderVO[] vos) {
    Set<GenNumWriteBackVO> insertSet = new HashSet<GenNumWriteBackVO>();
    for (OrderVO vo : vos) {
      OrderItemVO[] itemVOs = vo.getBVO();
      if (ArrayUtils.isEmpty(itemVOs)) {
        continue;
      }

      for (OrderItemVO itemVO : itemVOs) {
        this.processInsertRow(insertSet, itemVO);
      }
    }

    if (insertSet.size() > 0) {
      PPServices.writeback28For21(insertSet
          .toArray(new GenNumWriteBackVO[insertSet.size()]));
    }
  }

  /**
   * 方法功能描述：修改回写
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param originVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-1 下午02:09:57
   */
  private void writebackWhenUpdate(OrderVO[] vos, OrderVO[] originVOs) {
    BillRowCompare tool = new BillRowCompare(vos, originVOs);
    List<ISuperVO> insertList = tool.getInsertList();
    List<ISuperVO> deleteList = tool.getDeleteList();

    Set<GenNumWriteBackVO> updateSet = new HashSet<GenNumWriteBackVO>();

    if (insertList.size() > 0) {
      for (ISuperVO vo : insertList) {
        this.processInsertRow(updateSet, (OrderItemVO) vo);
      }
    }

    if (deleteList.size() > 0) {
      for (ISuperVO vo : deleteList) {
        this.processDeleteRow(updateSet, (OrderItemVO) vo);
      }
    }

    if (updateSet.size() > 0) {
      PPServices.writeback28For21(updateSet
          .toArray(new GenNumWriteBackVO[updateSet.size()]));
    }
  }
}
