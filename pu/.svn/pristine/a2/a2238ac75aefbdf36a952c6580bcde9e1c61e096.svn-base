package nc.test.api.m21;


import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.scmpub.AbstractSCMTestCase;
import nc.login.bs.INCUserQueryService;
import nc.pubitf.org.IGroupPubService;
import nc.pubitf.pu.m21.api.IOrderQueryAPI;
import nc.ui.querytemplate.operator.EqOperator;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.rule.api.IOrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.util.QuerySchemeBuilder;
import nc.vo.sm.UserVO;

public class OrderBillQueryAPIImplTestCase extends AbstractSCMTestCase {
	
	public void testQueryByIDs() throws BusinessException{
    IOrderQueryAPI service = 
    		NCLocator.getInstance().lookup(IOrderQueryAPI.class);
    OrderVO[] vos = null;
    try {
			vos = service.queryVOByIDs( new String[] {
          "1001Z81000000007OQYS", "1001Z81000000007OQYV"
        });
			int a = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testQueryByScheme() throws BusinessException{
		QuerySchemeBuilder builder =
        QuerySchemeBuilder.buildByFullClassName(OrderHeaderVO.class.getName());
    builder.append(IOrderVO.PK_ORDER,
        EqOperator.getInstance(), new String[] {
          "1001Z81000000007OQYS", "1001Z81000000007OQYV"
        });
    builder.append(IOrderVO.DR, EqOperator.getInstance(),
        new Integer[] {
          0
        });
    builder.append(IOrderVO.VBILLCODE, EqOperator.getInstance(),
        new String[] {
          "CD2015111100000040", "CD2015111100000041"
        });
    IQueryScheme queryscheme = builder.create();
    
    IOrderQueryAPI service = 
    		NCLocator.getInstance().lookup(IOrderQueryAPI.class);
    OrderVO[] vos = null;
    try {
			vos = service.queryVOByScheme(queryscheme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testQueryViewVOsByScheme() throws BusinessException{
		QuerySchemeBuilder builder =
        QuerySchemeBuilder.buildByFullClassName(OrderHeaderVO.class.getName());
    builder.append(IOrderVO.PK_ORDER,
        EqOperator.getInstance(), new String[] {
          "1001Z81000000007OQYS", "1001Z81000000007OQYV"
        });
    builder.append(IOrderVO.DR, EqOperator.getInstance(),
        new Integer[] {
          0
        });
    builder.append(IOrderVO.VBILLCODE, EqOperator.getInstance(),
        new String[] {
          "CD2015111100000040", "CD2015111100000041"
        });
    IQueryScheme queryscheme = builder.create();
    
    IOrderQueryAPI service = 
    		NCLocator.getInstance().lookup(IOrderQueryAPI.class);
    OrderViewVO[] vos = null;
    try {
			vos = service.queryViewVOByScheme(queryscheme);
		} catch (Exception e) {
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
    return "ncc220";
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
