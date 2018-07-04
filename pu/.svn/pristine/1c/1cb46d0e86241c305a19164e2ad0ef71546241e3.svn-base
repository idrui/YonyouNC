package nc.vo.pu.m21.rule.api;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.check.vovalidate.VONullValidate;

/**
 * 
 * @description 订单API保存前校验必输项
 * @scene
 * 
 * @param
 * 
 * @functionName 采购订单API保存
 * @since 6.5
 * @version 2015-11-16 下午4:05:52
 * @author zhangshqb
 */
public class PUOrderVONullValidate extends VONullValidate {

	@Override
	public String[] getHeadNotNullFields() {
		return new String[] { 
				OrderHeaderVO.PK_SUPPLIER, 
				OrderHeaderVO.PK_ORG };
	}

	@Override
	public String[] getBodyNotNullFields() {
		return null;
	}

	@Override
	public MapList<String, String> getMultiBodyNotNullFields() {
		MapList<String, String> mapList = new MapList<String, String>();
		List<String> list = new ArrayList<String>();
		list.add(OrderItemVO.PK_MATERIAL);
		list.add(OrderItemVO.NASTNUM);
		mapList.putAll("pk_order_b", list);
		return mapList;
	}

	@Override
	public void otherCheck(AbstractBill billVO,
			List<ValidationException> exceptions) {
		OrderVO vo = (OrderVO) billVO;
		OrderItemVO[] itemVOs = vo.getBVO();
		for (OrderItemVO item : itemVOs) {
			UFDouble nqtorigprice = item.getNqtorigprice();
			UFDouble nqtorigtaxprice = item.getNqtorigtaxprice();
			UFDouble norigmny = item.getNorigmny();
			UFDouble norigtaxmny = item.getNorigtaxmny();
			if (nqtorigprice == null && nqtorigtaxprice == null && norigmny == null
					&& norigtaxmny == null) {
				new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
						.getStrByID("4004030_0", "04004030-0387")/* @res 无税单价、含税单价、无税金额以及价税合计不能都为空 */);
			}
			String vchangerate = item.getVchangerate();
			if (vchangerate != null && vchangerate.contains("-")) {
				new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
						.getStrByID("4004030_0", "04004030-0386")/* @res "换算率不能为负" */);
			}
		}
	}
}
