package nc.vo.pu.m23.rule.api.fill;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.fill.billfill.IBillValueFill;

/**
 * 
 * @description
 *		联动计算后，将数量设置到应到数量上，在退货场景下，主数量取反，联动数量，应到应与数量保持一致。
 *		如果单据日期为空，填充当前业务日期
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *
 * @since 6.5
 * @version 2015-11-3 下午10:29:26
 * @author wandl
 */
public class FillNplanAstNum implements IRule<ArriveVO>{
	@Override
	public void process(ArriveVO[] vos) {
		UFDate busidate = AppContext.getInstance().getBusiDate();
		for (ArriveVO vo : vos) {
			if(vo.getHVO().getDbilldate() == null){
				vo.getHVO().setDbilldate(busidate);
			}
      // 联动计算后，将数量设置到应到数量上，在退货场景下，主数量取反，联动数量，应到应与数量保持一致。
      // 订单退货与原到货退货都需要此步骤。
      for (ArriveItemVO itemVo : vo.getBVO()) {
        itemVo.setNplanastnum(itemVo.getNastnum());
      }
    }
	}

}
