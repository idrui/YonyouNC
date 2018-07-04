package nc.vo.pu.m23.rule.api.fill;

import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.fill.billfill.IBillValueFill;

/**
 * 
 * @description
 *		如果到货单来源单据是赠品，填补赠品数量、赠品主数量
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-28 下午9:41:03
 * @author wandl
 */
public class FillPresentNum implements IBillValueFill{

	@Override
	public AbstractBill[] fillValue(AbstractBill[] billVOs)
			throws BusinessException {
		 ArriveItemVO[] itemVO = null;
	    for (AbstractBill vo : billVOs) {
	      itemVO = ((ArriveVO) vo).getBVO();
	      for (int i = 0, len = itemVO.length; i < len; i++) {
	        if ((itemVO[i].getBpresentsource() == null)
	            || !itemVO[i].getBpresentsource().booleanValue()) {
	          continue;
	        }
	        UFDouble nnum = itemVO[i].getNnum();
	        UFDouble nastnum = itemVO[i].getNastnum();
	        itemVO[i].setNpresentnum(nnum);
	        itemVO[i].setNpresentastnum(nastnum);
	      }
	    }
		return billVOs;
	}

}
