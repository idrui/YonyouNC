package nc.pubimpl.pu.m20.api;

import nc.impl.pu.m20.action.PraybillInsertAction;
import nc.pubitf.pu.m20.api.IPrayBillMaintainAPI;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.api.FillPrayBillInfo;
import nc.vo.pub.BusinessException;

/**
 * 
 * @description 请购单持久化服务实现
 * @scene
 * 
 * @param
 * 
 * @since 6.5
 * @version 2015-10-23 上午9:07:39
 * @author wandl
 */
public class PrayBillMaintainAPIImpl implements IPrayBillMaintainAPI {

	@Override
	public PraybillVO[] insertBills(PraybillVO[] bills) throws BusinessException {
		FillPrayBillInfo fillPrayBillInfo = new FillPrayBillInfo();
		PraybillVO[] vos = fillPrayBillInfo.fill(bills);
		PraybillInsertAction action = new PraybillInsertAction();
		PraybillVO[] praybillVos = action.insert(vos);
		return praybillVos;
	}

	@Override
	public void deleteBillsByIDs(String[] ids) throws BusinessException {
	}

	@Override
	public void deleteBillsBySourceIDs(String[] srcids) throws BusinessException {
	}

	@Override
	public PraybillVO[] approve(PraybillVO[] bills) throws BusinessException {
		return null;
	}

	@Override
	public PraybillVO[] unApprove(PraybillVO[] bills) throws BusinessException {
		return null;
	}

}
