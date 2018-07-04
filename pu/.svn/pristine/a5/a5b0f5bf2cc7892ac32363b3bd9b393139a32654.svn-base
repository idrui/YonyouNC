package nc.pubimpl.pu.m21.api;

import nc.impl.pu.m21.action.OrderDeleteAction;
import nc.impl.pu.m21.action.OrderSaveAction;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.pubitf.pu.m21.api.IOrderMaintainAPI;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.api.FillOrderVOInfo;
import nc.vo.pub.BusinessException;

/**
 * 
 * @description
 *	订单API持久化服务实现
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-11-16 下午4:08:41
 * @author zhangshqb
 */
public class OrderMaintainAPIImpl implements IOrderMaintainAPI {

	@Override
	public OrderVO[] insertBills(OrderVO[] bills) throws BusinessException {
		FillOrderVOInfo fillOrderClientVO = new FillOrderVOInfo();
		OrderVO[] orderVOs = fillOrderClientVO.fillInfo(bills);
		OrderSaveAction action = new OrderSaveAction();
		OrderVO[] vos = action.save(orderVOs, new OrderContext());
		return vos;
	}

	@Override
	public void deleteBillsByIDs(String[] ids) throws BusinessException {
		BillQuery<OrderVO> query = new BillQuery<OrderVO>(OrderVO.class);
		OrderVO[] vos = query.query(ids);
		OrderDeleteAction action = new OrderDeleteAction();
		action.delete(vos, new OrderContext());
	}

	@Override
	public void deleteBillsBySourceIDs(String[] srcids) throws BusinessException {

	}

	@Override
	public OrderVO[] approve(OrderVO[] bills) throws BusinessException {

		return null;
	}

	@Override
	public OrderVO[] unApprove(OrderVO[] bills) throws BusinessException {
		return null;
	}

}
