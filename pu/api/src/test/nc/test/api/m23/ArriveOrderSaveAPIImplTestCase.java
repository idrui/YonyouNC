package nc.test.api.m23;

import org.junit.Test;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.scmpub.AbstractSCMTestCase;
import nc.login.bs.INCUserQueryService;
import nc.pubitf.org.IGroupPubService;
import nc.pubitf.pu.m23.api.IArriveBillMaintainAPI;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sm.UserVO;
/**
 * 
 * @description
 *		到货单持久化API保存测试用例
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *
 * @since 6.5
 * @version 2015-11-4 下午4:07:59
 * @author wandl
 */
public class ArriveOrderSaveAPIImplTestCase extends AbstractSCMTestCase{

	@Test
	public void testInsert(){
		ArriveVO vo = new ArriveVO();
  	ArriveHeaderVO head = new ArriveHeaderVO();
  	ArriveItemVO item1 = new ArriveItemVO();
  	ArriveItemVO item2 = new ArriveItemVO();
  	head.setPk_org("00014910000000002Z8G");
		head.setPk_purchaseorg("00014910000000002Z8G");
		head.setPk_pupsndoc("1001491000000008MFF3");
		head.setVtrantypecode("23-01");
		
		item1.setVsourcecode("GCD151200138");
		item1.setVsourcerowno("10");
		item1.setPk_srcmaterial("1001491000000003AH27");
		UFDouble nnum = new UFDouble(1);
		item1.setNnum(nnum);
		UFDouble nastnum = new UFDouble(1);
		item1.setNastnum(nastnum);
		item1.setCsourcetypecode("21");
		
		//表体2
		item2.setVsourcecode("GCD151200138");
		item2.setVsourcerowno("20");
		item2.setPk_srcmaterial("1001E4100000005MCSKZ");
		item2.setNnum(new UFDouble(4));
		item2.setNastnum(new UFDouble(4));
		item2.setCsourcetypecode("21");
		vo.setHVO(head);
		vo.setBVO(new ArriveItemVO[]{
				item1,item2
		});
		IArriveBillMaintainAPI service = 
				NCLocator.getInstance().lookup(IArriveBillMaintainAPI.class);
		try {
			service.insertBills(new ArriveVO[]{
					vo	
			});
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
  @Override
  protected String getPort() {
    return "1231";
  }
	
	@Override
  protected String getPwd() {
    return "123456a";
  }

  @Override
  protected String getUser() {
    return "ncc222";
  }
	
	@Override
  protected void setUp() throws Exception {
    super.setUp();

    String dataSource = InvocationInfoProxy.getInstance().getUserDataSource();
    String userCode = InvocationInfoProxy.getInstance().getUserCode();
    try {
      UserVO userVO =
          NCLocator.getInstance().lookup(INCUserQueryService.class)
              .findUserVO(dataSource, userCode);
      if (userVO != null) {
        InvocationInfoProxy.getInstance().setGroupId(userVO.getPk_group());
        String groupNo =
            NCLocator.getInstance().lookup(IGroupPubService.class)
                .getGroupNoByPK(userVO.getPk_group());
        InvocationInfoProxy.getInstance().setGroupNumber(groupNo);
        InvocationInfoProxy.getInstance().setUserId(userVO.getPrimaryKey());
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }
	
}
