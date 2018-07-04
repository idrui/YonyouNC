package nc.vo.pu.m21.rule.api.fill;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.scmpub.fill.billfill.TrantypeIDBillFill;

/**
 * 
 * @description 订单交易类型id填充处理
 * @scene
 * 
 * @param
 * 
 * @functionName 采购订单API保存
 * @since 6.5
 * @version 2015-11-16 下午4:04:35
 * @author zhangshqb
 */
public class Ctrantperid extends TrantypeIDBillFill {

	@Override
	public String getTranTypeIDFieldName() {
		return OrderHeaderVO.CTRANTYPEID;
	}

	@Override
	public String getTransTypeCodeFieldName() {
		return OrderHeaderVO.VTRANTYPECODE;
	}

}
