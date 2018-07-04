/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-12-29 下午02:41:14
 */
package nc.bs.pu.m21.maintain.rule.save;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.util.VOFieldLengthChecker;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description 采购订单参数VO的正确性校验、VO中的最大值检查
 * @scene 采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:20:18
 * @author luojw
 */
public class SaveVOValidateRule implements IRule<OrderVO> {

	@Override
	public void process(OrderVO[] vos) {
		// 参数VO的正确性校验
		this.VoValidate(vos);
		// VO中的最大值检查
		VOFieldLengthChecker.checkVOFieldsLength(vos);

	}

	private void VoValidate(OrderVO[] vos) {
		for (OrderVO vo : vos) {
			if (null == vo) {
				ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
						.getNCLangRes().getStrByID("4004030_0", "04004030-0123")/*
																																		 * @res
																																		 * "传入的订单中有空值 ！"
																																		 */);
			} else if (null == vo.getHVO() || ArrayUtils.isEmpty(vo.getBVO())) {
				ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
						.getNCLangRes().getStrByID("4004030_0", "04004030-0124")/*
																																		 * @res
																																		 * "传入的订单中存在不完整的订单数据！"
																																		 */);
			} else {
				OrderItemVO[] itemVOs = vo.getBVO();
				List<OrderItemVO> list = new ArrayList<OrderItemVO>();
				for (OrderItemVO itemVO : itemVOs) {
					if (itemVO != null && VOStatus.DELETED != itemVO.getStatus()) {
						list.add(itemVO);
					}
				}

				if (list.size() == 0) {
					ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4004030_0", "04004030-0124")/*
																																			 * @res
																																			 * "传入的订单中存在不完整的订单数据！"
																																			 */);
				}
				OrderPaymentVO[] pvos = (OrderPaymentVO[]) vo
						.getChildren(OrderPaymentVO.class);
				UFBoolean bisreplenish = vo.getHVO().getBisreplenish();
				if (bisreplenish != null && bisreplenish.booleanValue() && pvos != null) {
					for (OrderPaymentVO pvo : pvos) {
						pvo.setPk_payment(null);
					}
				}
			}
		}
	}
}
