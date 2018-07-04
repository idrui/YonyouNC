package nc.test.api.m422x;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.scmpub.AbstractSCMTestCase;
import nc.login.bs.INCUserQueryService;
import nc.pubitf.org.IGroupPubService;
import nc.pubitf.pu.m422x.api.IStoreReqAppQueryAPI;
import nc.ui.querytemplate.operator.EqOperator;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.rule.api.IStoreReqAppVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.util.QuerySchemeBuilder;
import nc.vo.sm.UserVO;

/**
 * 
 * @description
 *		                    物资需求申请单查询API测试用例
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		
 * @since 6.5
 * @version 2015-11-10 下午7:58:57
 * @author luojw
 */
public class StoreReqAppQueryAPIImplTestCase extends AbstractSCMTestCase{
	
  public void test(){
    this.queryVOByScheme();
    this.queryVOBySchemeWithFields();
  }
  
	public void queryVOByScheme(){
		IQueryScheme queryscheme = this.getQueryScheme();
    IStoreReqAppQueryAPI service = this.getService();
    StoreReqAppVO[] vos = null;
    try {
      vos = service.queryVOByScheme(queryscheme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
  public void queryVOBySchemeWithFields(){
    IQueryScheme queryscheme = this.getQueryScheme();
    IStoreReqAppQueryAPI service = this.getService();
    String[] fields = this.getQueryFields();
    StoreReqAppVO[] vos = null;
    try {
      vos = service.queryVOByScheme(queryscheme, fields);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private String[] getQueryFields(){
    return new String[]{IStoreReqAppVO.VBILLCODE, IStoreReqAppVO.CREATOR,
        IStoreReqAppVO.PK_STOREREQ_B_PK_MATERIAL};
  }

  private IStoreReqAppQueryAPI getService() {
    return NCLocator.getInstance().lookup(IStoreReqAppQueryAPI.class);
  }

  private IQueryScheme getQueryScheme() {
    QuerySchemeBuilder builder =
        QuerySchemeBuilder.buildByFullClassName(StoreReqAppHeaderVO.class.getName());
    builder.append(IStoreReqAppVO.DR, EqOperator.getInstance(),
        new Integer[] {
          Integer.valueOf(0)
        });
    builder.append(IStoreReqAppVO.PK_STOREREQ_B_DR, EqOperator.getInstance(),
        new Integer[] {
          Integer.valueOf(0)
        });
    builder.append(IStoreReqAppVO.VBILLCODE, EqOperator.getInstance(),
        new String[] {
          "MR2015112600000007"
        });
    builder.append(IStoreReqAppVO.PK_ORG, EqOperator.getInstance(),
        new String[] {
          "0001DD1000000000O6MM"
        });
    IQueryScheme queryscheme = builder.create();
    return queryscheme;
  }
	
	
	
	@Override
  protected String getPort() {
    return "9821";
  }
	
	@Override
  protected String getPwd() {
    return "123456a";
  }

  @Override
  protected String getUser() {
    return "luojw";
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
