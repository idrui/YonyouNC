package nc.impl.pu.m422x.action.rule.approve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.ic.m4d.m422x.IMaterialQueryServiceFor422X;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 *            物资需求申请单取消审批时，检查是否有后续单据
 * @scene
 *      物资需求申请单取消审批
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-7-28 上午07:25:22
 * @author wuxla
 */
public class FollowupBillChkRule implements IRule<StoreReqAppVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(StoreReqAppVO[] vos) {
    /*
     *  add by wandl
     *  检查有无下游材料出库单
     *  应用场景: 材料出库单拉物资需求申请单
     */
  	List<String> hids = new ArrayList<String>();
  	IMaterialQueryServiceFor422X services = (IMaterialQueryServiceFor422X) NCLocator.getInstance().lookup(
  			IMaterialQueryServiceFor422X.class.getName());
  	Map<String,String> bcodemap = new HashMap<String,String>();
    for (StoreReqAppVO vo : vos) {
    	hids.add(vo.getPrimaryKey());
    	bcodemap.put(vo.getPrimaryKey(), vo.getHVO().getVbillcode());
    }
    String[] srchids = hids.toArray(new String[0]);
    Map<String, UFBoolean> map = new HashMap<String,UFBoolean>();
		try {
			map = services.queryMaterialBySourceHID(srchids);
		} 
		catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
    for(String srchid : srchids){
    	if(map != null && map.get(srchid) == UFBoolean.TRUE){
    		ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().
    				getStrByID("4004010_0", "04004010-0020", null, new String[]{bcodemap.get(srchid)})/*单据{0}存在有后续单据，请检查*/);
    	}
    }
  }
}
