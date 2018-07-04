package nc.test.api.m422x;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.scmpub.AbstractSCMTestCase;
import nc.login.bs.INCUserQueryService;
import nc.pubitf.org.IGroupPubService;
import nc.pubitf.pu.m422x.api.IStoreReqAppMaintainAPI;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sm.UserVO;
/**
 * 
 * @description
 *		                    物资需求申请单持久化API测试用例
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *
 * @since 6.5
 * @version 2015-11-4 下午4:07:59
 * @author luojw
 */
public class StoreReqAppAPIImplTestCase extends AbstractSCMTestCase{

  public void test(){
    this.insertOfMax();
//    this.delete();
  }
  
  // 最大集合
  public void insertOfMax(){
    StoreReqAppHeaderVO header = new StoreReqAppHeaderVO(); 
    // 组织
    header.setPk_org("0001DD1000000000O6MM");
    // 交易类型编码
    header.setVtrantypecode("422X-01");
    // 申请人
    header.setPk_apppsnh("1001DD1000000000096W");
    // 申请部门
    header.setPk_appdepth("1001DD1000000000096Q");
    // 需求类型
    header.setFreqtypeflag(Integer.valueOf(0));
    StoreReqAppItemVO item = new StoreReqAppItemVO();
    // 物料
    item.setPk_material("1001DD10000000000FZB");
    // 供应商
    item.setCvendorid("1001DD1000000000091W");
    // 需求仓库
    item.setPk_reqstordoc("1001DD10000000000CRP");
    // 数量
    item.setNastnum(new UFDouble(2));
    // 主本币含税单价
    item.setNtaxprice(new UFDouble(20));
    StoreReqAppVO vo = new StoreReqAppVO();
    vo.setHVO(header);
    vo.setBVO(new StoreReqAppItemVO[]{item});
    IStoreReqAppMaintainAPI service = NCLocator.getInstance().lookup(IStoreReqAppMaintainAPI.class);
    try {
      service.insertVO(new StoreReqAppVO[]{vo});
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
  
  // 最小集合
	public void insertOfMin(){
    StoreReqAppHeaderVO header = new StoreReqAppHeaderVO(); 
    header.setPk_org("0001DD1000000000O6MM");
    header.setVtrantypecode("422X-01");
    header.setFreqtypeflag(Integer.valueOf(1));
    StoreReqAppItemVO item = new StoreReqAppItemVO();
    item.setPk_material("1001DD10000000000CQR");
    item.setNastnum(new UFDouble(2));
    StoreReqAppVO vo = new StoreReqAppVO();
    vo.setHVO(header);
    vo.setBVO(new StoreReqAppItemVO[]{item});
    IStoreReqAppMaintainAPI service = NCLocator.getInstance().lookup(IStoreReqAppMaintainAPI.class);
    try {
      service.insertVO(new StoreReqAppVO[]{vo});
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
	}
	
  public void delete() {
    IStoreReqAppMaintainAPI service = NCLocator.getInstance().lookup(IStoreReqAppMaintainAPI.class);
    try {
      service.deleteVOByIDs(new String[]{"1001Z810000000004I25"});
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
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
    return "luojw1";
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
