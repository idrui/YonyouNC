package nc.test.api.m21;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.scmpub.AbstractSCMTestCase;
import nc.login.bs.INCUserQueryService;
import nc.pubitf.org.IGroupPubService;
import nc.pubitf.pu.m21.api.IOrderMaintainAPI;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sm.UserVO;

public class OrderSaveAPIImplTestCase extends AbstractSCMTestCase {

	/**
	 * 最小集,必输项测试。
	 */
	public void testInsert() {
		OrderItemVO vo = new OrderItemVO();
		vo.setPk_material("1001DD10000000000CQR");
		vo.setNastnum(new UFDouble(5));
		// vo.setNtaxrate(new UFDouble(17));
		vo.setNorigmny(new UFDouble(10));

		OrderHeaderVO headerVO = new OrderHeaderVO();
		headerVO.setPk_org("0001DD1000000000O6MM");
		headerVO.setPk_dept_v("0001DD1000000000O6OJ");
		headerVO.setPk_supplier("1001DD10000000000PPA");
		headerVO.setPk_payterm("1001Z810000000004L4X");

		OrderVO orderVO = new OrderVO();
		orderVO.setHVO(headerVO);
		orderVO.setBVO(new OrderItemVO[] { vo });

		IOrderMaintainAPI api = NCLocator.getInstance().lookup(
				IOrderMaintainAPI.class);
		try {
			api.insertBills(new OrderVO[] { orderVO });
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 界面默认放出字段测试
	 */
	public void testInsert1() {
		OrderItemVO vo = new OrderItemVO();
		vo.setPk_reqstoorg_v("0001DD1000000000O6ML");
		vo.setPk_apfinanceorg_v("0001DD1000000000O6ML");
		vo.setPk_material("1001DD10000000000CQR");
		vo.setCastunitid("0001Z0100000000000XT");
		vo.setNastnum(new UFDouble(5));
		vo.setNtaxrate(new UFDouble(17));
		vo.setVchangerate("1.00/1.00");
		vo.setFtaxtypeflag(new Integer(2));
		vo.setNqtorigprice(new UFDouble(10));
		vo.setPk_psfinanceorg_v("0001DD1000000000O6ML");
		vo.setCdevaddrid("");
		vo.setCdevareaid("");
		vo.setCqpbaseschemeid("");
		
		OrderItemVO vo1 = new OrderItemVO();
		vo1.setPk_reqstoorg_v("0001DD1000000000O6ML");
		vo1.setPk_apfinanceorg_v("0001DD1000000000O6ML");
		vo1.setPk_material("1001DD10000000000CQR");
		vo1.setCastunitid("0001Z0100000000000XT");
		vo1.setNastnum(new UFDouble(5));
		vo1.setNtaxrate(new UFDouble(17));
		vo1.setVchangerate("1.00/1.00");
		vo1.setFtaxtypeflag(new Integer(2));
		vo1.setNqtorigprice(new UFDouble(10));
		vo1.setPk_psfinanceorg_v("0001DD1000000000O6ML");
		vo1.setCdevaddrid("");
		vo1.setCdevareaid("");
		vo1.setCqpbaseschemeid("");
		

		OrderHeaderVO headerVO = new OrderHeaderVO();
		headerVO.setPk_org("0001DD1000000000O6MM");
		headerVO.setPk_dept_v("0001DD1000000000O6OJ");
		headerVO.setCorigcurrencyid("1002Z0100000000001K1");
		headerVO.setDbilldate(new UFDate());
		headerVO.setPk_supplier("1001DD10000000000PPA");
		headerVO.setFhtaxtypeflag(new Integer(1));
		headerVO.setBrefwhenreturn(UFBoolean.TRUE);
		headerVO.setCemployeeid("1001DD10000000002JTO");
		headerVO.setPk_balatype("0001Z0100000000000Y2");
//		headerVO.setPk_bankdoc("1001Z8100000000A4NBM");
		headerVO.setPk_payterm("1001Z810000000004L4X");
		// headerVO.setPk_project("");
		headerVO.setPk_recvcustomer("1001DD1000000000091W");
		headerVO.setBrefwhenreturn(UFBoolean.TRUE);

		OrderVO orderVO = new OrderVO();
		orderVO.setHVO(headerVO);
		orderVO.setBVO(new OrderItemVO[] { vo,vo1 });

		IOrderMaintainAPI api = NCLocator.getInstance().lookup(
				IOrderMaintainAPI.class);
		try {
			api.insertBills(new OrderVO[] { orderVO });
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	//
	// /**
	// * 允许用户输入的最大值测试
	// */
	// public void testInsert2() {
	// OrderItemVO vo = new OrderItemVO();
	// vo.setPk_reqstoorg_v("00011110000000003D50");
	// vo.setPk_apfinanceorg_v("00011110000000003D50");
	// vo.setPk_material("1001111000000006I6SW");
	// vo.setCastunitid("1001111000000002016A");
	// vo.setNastnum(new UFDouble(5));
	// vo.setNtaxrate(new UFDouble(17));
	// vo.setVchangerate("1.00/1.00");
	// vo.setFtaxtypeflag(new Integer(2));
	// vo.setNqtorigprice(new UFDouble(10));
	// vo.setPk_psfinanceorg_v("00011110000000003D50");
	//
	// OrderHeaderVO headerVO = new OrderHeaderVO();
	// headerVO.setPk_org("00011110000000003D51");
	// headerVO.setPk_dept_v("00011110000000003DEC");
	// headerVO.setCorigcurrencyid("1002Z0100000000001K1");
	// headerVO.setPk_supplier("10011110000000000S65");
	// headerVO.setFhtaxtypeflag(new Integer(1));
	//
	// OrderVO orderVO = new OrderVO();
	// orderVO.setHVO(headerVO);
	// orderVO.setBVO(new OrderItemVO[] { vo });
	//
	// IOrderMaintainAPI api = NCLocator.getInstance().lookup(
	// IOrderMaintainAPI.class);
	// try {
	// api.insertBills(new OrderVO[] { orderVO });
	// } catch (BusinessException e) {
	// e.printStackTrace();
	// }
	// }

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
		return "luojw1";
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
