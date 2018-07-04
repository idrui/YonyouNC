package nc.test.api.m23;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.scmpub.AbstractSCMTestCase;
import nc.login.bs.INCUserQueryService;
import nc.pubitf.org.IGroupPubService;
import nc.pubitf.pu.m23.api.IArriveBillQueryAPI;
import nc.ui.querytemplate.operator.EqOperator;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.api.IArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.util.QuerySchemeBuilder;
import nc.vo.sm.UserVO;

/**
 * 
 * @description
 *		到货单查询API测试用例，根据查询方案查询到货单整单VO
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		到货单查询API测试用例
 * @since 6.5
 * @version 2015-11-10 下午7:58:57
 * @author wandl
 */
public class ArriveBillQueryAPIImplTestCase extends AbstractSCMTestCase{
	
	public void testQueryByScheme() throws BusinessException{
		QuerySchemeBuilder builder =
        QuerySchemeBuilder.buildByFullClassName(ArriveHeaderVO.class.getName());
    builder.append(IArriveVO.PK_ARRIVEORDER,
        EqOperator.getInstance(), new String[] {
          "1001111000000001K5WM", "1001111000000001K6U7"
        });
    builder.append(IArriveVO.DR, EqOperator.getInstance(),
        new Integer[] {
          0
        });
    builder.append(IArriveVO.VBILLCODE, EqOperator.getInstance(),
        new String[] {
          "DH2015110900000001", "DH2015110900000002"
        });
    builder.append(IArriveVO.PK_ORG, EqOperator.getInstance(),
        new String[] {
          "00011110000000003D51"
        });
    IQueryScheme queryscheme = builder.create();
    
    IArriveBillQueryAPI service = 
    		NCLocator.getInstance().lookup(IArriveBillQueryAPI.class);
    ArriveVO[] vos = null;
    try {
			vos = service.queryVOByScheme(queryscheme);
		} catch (Exception e) {
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
    return "ncc230";
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
