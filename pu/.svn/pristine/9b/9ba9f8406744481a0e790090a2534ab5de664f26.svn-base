package nc.pubimpl.pu.m21.dm.m4804;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.pubitf.pu.m21.dm.m4804.I21DeliveryStatusOperater;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-5-25 下午06:50:00
 * @author wuxla
 */

public class M21DeliveryStatusOperaterImpl implements I21DeliveryStatusOperater {

  @Override
  public void setTransStatus(String[] bids, String[] bb1ids, UFBoolean status)
      throws BusinessException {
    try {
      if (!ArrayUtils.isEmpty(bb1ids)) {
        this.updateRPs(bb1ids, status);
      }
      this.updateItems(bids, status);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

  private void updateItems(String[] bids, UFBoolean status) {
    new VOConcurrentTool().lock(OrderItemVO.class, bids);
    VOQuery<OrderItemVO> query = new VOQuery<OrderItemVO>(OrderItemVO.class);
    OrderItemVO[] vos = query.query(bids);
    if (ArrayUtils.isEmpty(vos) || vos.length != bids.length) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4004030_0", "04004030-0173")/* @res "出现并发" */);
    }
    for (OrderItemVO vo : vos) {
      vo.setBtransclosed(status);
    }

    VOUpdate<OrderItemVO> update = new VOUpdate<OrderItemVO>();
    update.update(vos, new String[] {
      OrderItemVO.BTRANSCLOSED
    });
  }

  private void updateRPs(String[] bb1ids, UFBoolean status) {
    new VOConcurrentTool().lock(OrderReceivePlanVO.class, bb1ids);
    VOQuery<OrderReceivePlanVO> query =
        new VOQuery<OrderReceivePlanVO>(OrderReceivePlanVO.class);
    OrderReceivePlanVO[] vos = query.query(bb1ids);
    if (ArrayUtils.isEmpty(vos) || vos.length != bb1ids.length) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4004030_0", "04004030-0173")/* @res "出现并发" */);
    }
    for (OrderReceivePlanVO vo : vos) {
      vo.setBtransclosed(status);
    }

    VOUpdate<OrderReceivePlanVO> update = new VOUpdate<OrderReceivePlanVO>();
    update.update(vos, new String[] {
      OrderReceivePlanVO.BTRANSCLOSED
    });
  }

}
