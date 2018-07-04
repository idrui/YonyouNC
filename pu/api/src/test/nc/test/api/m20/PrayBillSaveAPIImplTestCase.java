package nc.test.api.m20;

import org.junit.Test;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.scmpub.AbstractSCMTestCase;
import nc.login.bs.INCUserQueryService;
import nc.pubitf.org.IGroupPubService;
import nc.pubitf.pu.m20.api.IPrayBillMaintainAPI;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sm.UserVO;

public class PrayBillSaveAPIImplTestCase extends AbstractSCMTestCase {
	
	@Test
	public void testInsert() {
		PraybillItemVO itemVO = new PraybillItemVO();
		itemVO.setPk_material("1001DD10000000000CQR");
		itemVO.setNastnum(new UFDouble(50));
		PraybillItemVO item = new PraybillItemVO();
		item.setPk_material("1001DD10000000000CQR");
		item.setNastnum(new UFDouble(30));
		PraybillHeaderVO headerVO = new PraybillHeaderVO();
		headerVO.setPk_org("0001DD1000000000O6MM");
		PraybillVO praybillVO = new PraybillVO();
		praybillVO.setHVO(headerVO);
		praybillVO.setBVO(new PraybillItemVO[]{itemVO,item});
		IPrayBillMaintainAPI api = NCLocator.getInstance().lookup(IPrayBillMaintainAPI.class);
		try {
			api.insertBills(new PraybillVO[]{praybillVO});
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected String getPort() {
		return "5616";
	}

	@Override
	protected String getPwd() {
		return "123456a";
	}

	@Override
	protected String getUser() {
		return "zhangshqb";
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		String dataSource = InvocationInfoProxy.getInstance().getUserDataSource();
		String userCode = InvocationInfoProxy.getInstance().getUserCode();
		try {
			UserVO userVO = NCLocator.getInstance().lookup(INCUserQueryService.class)
					.findUserVO(dataSource, userCode);
			if (userVO != null) {
				InvocationInfoProxy.getInstance().setGroupId(userVO.getPk_group());
				String groupNo = NCLocator.getInstance().lookup(IGroupPubService.class)
						.getGroupNoByPK(userVO.getPk_group());
				InvocationInfoProxy.getInstance().setGroupNumber(groupNo);
				InvocationInfoProxy.getInstance().setUserId(userVO.getPrimaryKey());
			}
		} catch (Exception e) {
			ExceptionUtils.wrappException(e);
		}
	}
}
