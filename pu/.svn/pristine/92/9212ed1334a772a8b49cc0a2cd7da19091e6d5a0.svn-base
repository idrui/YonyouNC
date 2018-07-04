package nc.vo.pu.m23.rule.api.fill;

import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.fill.billfill.IBillValueFill;
import nc.vo.scmpub.fill.util.VOValueUtil;
import nc.vo.scmpub.util.ArrayUtil;

/**
 * 
 * @description
 *		到货单采购组织Vid填充规则
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-24 上午11:31:38
 * @author wandl
 */
public class FillPurchaseorgVidRule implements IBillValueFill{

	private ISuperVO[] fillValue(ISuperVO[] vos) throws BusinessException {
		Set<String> purchaseorgs = VOValueUtil.getVOsValueSet(vos, ArriveHeaderVO.PK_PURCHASEORG);
		Map<String, String> orgmap =
        NCLocator.getInstance().lookup(IOrgUnitPubService_C.class)
            .getNewVIDSByOrgIDS(purchaseorgs.toArray(new String[0]));
		String purchaseorg = null;
		String purchaseorgvid = null;
		for(ISuperVO vo : vos){
			purchaseorg = (String) vo.getAttributeValue(ArriveHeaderVO.PK_PURCHASEORG);
			if(purchaseorg == null){
				continue;
			}
			purchaseorgvid = orgmap.get(purchaseorg);
			if(purchaseorgvid == null){
				continue;
			}
			vo.setAttributeValue(ArriveHeaderVO.PK_PURCHASEORG_V, purchaseorgvid);
		}
		return vos;
	}

	@Override
	public AbstractBill[] fillValue(AbstractBill[] billVOs)
			throws BusinessException {
		ISuperVO[] headVOs = VOValueUtil.getHeadVOs(billVOs);
		if (ArrayUtil.isEmpty(headVOs)) {
      return null;
    }
    this.fillValue(headVOs);
    return billVOs;
	}
}
