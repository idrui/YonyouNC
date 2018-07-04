package nc.vo.pu.m21.rule.api.fill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.payment.PaymentService;
import nc.vo.bd.payment.PaymentChVO;
import nc.vo.bd.payment.PaymentVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.fill.billfill.IBillValueFill;

/**
 * 
 * @description 根据表头付款协议内容填充订单表体付款协议页签的内容 如果表体存在付款协议内容，则不做任何操作。
 * @scene
 * 
 * @param
 * 
 * @functionName 采购订单API保存
 * @since 6.5
 * @version 2015-11-16 下午4:05:04
 * @author zhangshqb
 */
public class FillPaymentInfo implements IBillValueFill {
	
	public OrderVO[] fill(OrderVO[] vos) {
		// Map<pk_payment,List<OrderVO>>
		// 推送过来的vos中没有主键
		Map<String, List<OrderVO>> map = new HashMap<String, List<OrderVO>>();
		for (OrderVO vo : vos) {
			String pk_payterm = vo.getHVO().getPk_payterm();
			if (pk_payterm == null || pk_payterm.isEmpty()) {
				continue;
			} else {
				ISuperVO[] paymentVOs = vo.getChildren(OrderPaymentVO.class);
				if (paymentVOs == null || paymentVOs.length == 0) {
					List<OrderVO> list = map.get(pk_payterm);
					if (list == null) {
						list = new ArrayList<OrderVO>();
						map.put(pk_payterm, list);
					}
					list.add(vo);
				}
			}
		}
		if (map.isEmpty()) {
			return vos;
		}
		SuperVO[] payterms = PaymentService.queryPaymentByIds(
				map.keySet().toArray(new String[map.size()]), PaymentVO.class);
		for (SuperVO payterm : payterms) {
			PaymentChVO[] chvos = ((PaymentVO) payterm).getPaymentch();
			OrderPaymentVO[] paymentVOs = this.convertPaytermToPaymentVO(chvos);
			List<OrderVO> list = map.get(payterm.getPrimaryKey());
			for (OrderVO orderVO : list) {
				orderVO.setChildren(OrderPaymentVO.class, paymentVOs);
			}
		}
		return vos;
	}

	/**
	 * 将平台的付款协议内容转换为订单付款协议内容
	 * 
	 * @author zhangshqb
	 * @param chvos
	 * @return
	 */
	private OrderPaymentVO[] convertPaytermToPaymentVO(PaymentChVO[] chvos) {
		OrderPaymentVO[] paymentVOs = new OrderPaymentVO[chvos.length];
		for (int i = 0; i < chvos.length; i++) {
			PaymentChVO vo = chvos[i];
			paymentVOs[i] = new OrderPaymentVO();
			paymentVOs[i].setAttributeValue(OrderPaymentVO.SHOWORDER,
					vo.getAttributeValue(OrderPaymentVO.SHOWORDER));
			paymentVOs[i].setAttributeValue(OrderPaymentVO.ACCRATE,
					vo.getAttributeValue(OrderPaymentVO.ACCRATE));
			paymentVOs[i].setAttributeValue(OrderPaymentVO.PREPAYMENT,
					vo.getAttributeValue(OrderPaymentVO.PREPAYMENT));
			paymentVOs[i].setAttributeValue(OrderPaymentVO.EFFECTADDMONTH,
					vo.getAttributeValue(OrderPaymentVO.EFFECTADDMONTH));
			paymentVOs[i].setAttributeValue(OrderPaymentVO.EFFECTMONTH,
					vo.getAttributeValue(OrderPaymentVO.EFFECTMONTH));
			paymentVOs[i].setAttributeValue(OrderPaymentVO.EFFECTDATEADDDATE,
					vo.getAttributeValue(OrderPaymentVO.EFFECTDATEADDDATE));
			paymentVOs[i].setAttributeValue(OrderPaymentVO.PK_PAYPERIOD,
					vo.getAttributeValue(OrderPaymentVO.PK_PAYPERIOD));
			paymentVOs[i].setAttributeValue(OrderPaymentVO.PK_BALATYPE,
					vo.getAttributeValue(OrderPaymentVO.PK_BALATYPE));
			paymentVOs[i].setAttributeValue(OrderPaymentVO.PK_RATE,
					vo.getAttributeValue(OrderPaymentVO.PK_RATE));
			paymentVOs[i].setAttributeValue(OrderPaymentVO.PAYMENTDAY,
					vo.getAttributeValue(OrderPaymentVO.PAYMENTDAY));
			paymentVOs[i].setAttributeValue(OrderPaymentVO.CHECKDATA,
					vo.getAttributeValue(OrderPaymentVO.CHECKDATA));
			paymentVOs[i].setAttributeValue(OrderPaymentVO.ISDEPOSIT,
					vo.getAttributeValue(OrderPaymentVO.ISDEPOSIT));
			paymentVOs[i].setAttributeValue(OrderPaymentVO.ACCOUNTDAY,
					vo.getAttributeValue(OrderPaymentVO.ACCOUNTDAY));
		}
		return paymentVOs;
	}

	@Override
	public AbstractBill[] fillValue(AbstractBill[] billVOs)
			throws BusinessException {
		return this.fill((OrderVO[]) billVOs);
	}

}
