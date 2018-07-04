package nc.itf.pu.ithp2m21;

import nc.vo.pu.m21.entity.OrderVO;

public interface IPurchaseOrderMaintain {
	/**
	 * 方法功能描述：订单保存BP（前台 保存、推式保存）。
	 */
	OrderVO[] save(OrderVO[] clientVos, OrderVO[] originVos);
	
}
