package nc.impl.pu.ithp2m21;

import nc.bs.pu.m21.maintain.ITHP2OrderSaveBP;
import nc.itf.pu.ithp2m21.IPurchaseOrderMaintain;
import nc.vo.pu.m21.entity.OrderVO;

public class PurchaseOrderMaintain implements IPurchaseOrderMaintain {

	@Override
	public OrderVO[] save(OrderVO[] clientVos, OrderVO[] originVos) {
		ITHP2OrderSaveBP savebp = new ITHP2OrderSaveBP(clientVos);
		OrderVO[] returnedVos = savebp.save(clientVos, originVos);
		return returnedVos;
	}

}
