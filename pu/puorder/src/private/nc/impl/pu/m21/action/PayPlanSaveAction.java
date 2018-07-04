package nc.impl.pu.m21.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.pu.m21.maintain.PayPlanDeleteBP;
import nc.bs.pu.m21.maintain.PayPlanInsertBP;
import nc.bs.pu.m21.maintain.PayPlanUpdateBP;
import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.query.PayPlanQueryBP;
import nc.impl.pu.m21.action.rule.maintain.OrderStatusChkRule;
import nc.impl.pu.m21.action.rule.maintain.OverPayChkRule;
import nc.impl.pu.m21.action.rule.maintain.PayLimitRule;
import nc.impl.pu.m21.action.rule.maintain.PayPlanRowNoRule;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.scmf.payplan.rule.PayPlanViewTransferTool;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * 付款计划更新保存
 * 
 * @since 6.0
 * @version 2011-1-7 上午10:25:12
 * @author wuxla
 */

public class PayPlanSaveAction {

  public BatchOperateVO batchSave(BatchOperateVO batchVO) {
    PayPlanViewVO[] addObjs = ArrayUtil.convertArrayType(batchVO.getAddObjs());
    PayPlanViewVO[] updObjs = ArrayUtil.convertArrayType(batchVO.getUpdObjs());
    PayPlanViewVO[] delObjs = ArrayUtil.convertArrayType(batchVO.getDelObjs());

    PayPlanViewTransferTool<PayPlanViewVO, OrderHeaderVO> tool =
        new PayPlanViewTransferTool<PayPlanViewVO, OrderHeaderVO>(updObjs,
            addObjs, delObjs, OrderHeaderVO.class);
    PayPlanViewVO[] originUpdObjs = tool.getOriginUpdObjs();
    PayPlanViewVO[] originDelObjs = tool.getOriginDelObjs();
    OrderHeaderVO[] originAddHeadVOs = tool.getOriginAddHeadVOs();

    new OrderStatusChkRule(originAddHeadVOs).process(ArrayUtil.combinArrays(
        originUpdObjs, originDelObjs));

    /*
     *  add by wandl 
     *  付款计划增行保存付款比例之和等于100校验
     */
    this.checkNrate(addObjs, updObjs, delObjs, originUpdObjs);
    
    if (!ArrayUtils.isEmpty(updObjs)) {
      updObjs = new PayPlanUpdateBP().update(updObjs, originUpdObjs);
      batchVO.setUpdObjs(updObjs);
    }

    if (!ArrayUtils.isEmpty(addObjs)) {
      addObjs = new PayPlanInsertBP().insert(addObjs, originAddHeadVOs);
      batchVO.setAddObjs(addObjs);
    }

    if (!ArrayUtils.isEmpty(delObjs)) {
      new PayPlanDeleteBP().delete(delObjs);
    }

    this.check(tool.getHids());

    return batchVO;
  }

  /*
   *  add by wandl 
   *  付款计划增行保存付款比例之和等于100校验
   */
  private void checkNrate(PayPlanViewVO[] addObjs,PayPlanViewVO[] updObjs,
  		PayPlanViewVO[] delObjs,PayPlanViewVO[] originUpdObjs){
    Map<String,List<UFDouble>> map = new HashMap<String,List<UFDouble>>();
    
    if(addObjs == null){
    	addObjs = new PayPlanViewVO[0];
    }
    if(delObjs == null){
    	delObjs = new PayPlanViewVO[0];
    }
    if(updObjs == null){
    	updObjs = new PayPlanViewVO[0];
    }
    if(originUpdObjs == null){
    	originUpdObjs = new PayPlanViewVO[0];
    }
    PayPlanVO[] vos = new PayPlanVO[addObjs.length];
    PayPlanVO[] delvos = new PayPlanVO[delObjs.length];
    PayPlanVO[] upvos = new PayPlanVO[updObjs.length];
    PayPlanVO[] oriupvos = new PayPlanVO[originUpdObjs.length];
    
    for (int i = 0; i < addObjs.length; ++i) {
      vos[i] = (PayPlanVO) addObjs[i].getVO(PayPlanVO.class);
    }
    for (int i = 0; i < delObjs.length; ++i) {
    	delvos[i] = (PayPlanVO) delObjs[i].getVO(PayPlanVO.class);
    }
    for (int i = 0; i < updObjs.length; ++i) {
    	upvos[i] = (PayPlanVO) updObjs[i].getVO(PayPlanVO.class);
    }
    for (int i = 0; i < originUpdObjs.length; ++i) {
    	oriupvos[i] = (PayPlanVO) originUpdObjs[i].getVO(PayPlanVO.class);
    }
    String pk_order = null;
    List<UFDouble> templist = null;
    for (PayPlanVO addvo : vos){
    	pk_order = addvo.getPk_order();
    	if(map.containsKey(pk_order)){
    		templist = map.get(pk_order);
    	}
    	else {
    		templist = new ArrayList<UFDouble>();
    	}
    	templist.add(addvo.getNrate());
    	map.put(pk_order, templist);
    }
    for (PayPlanVO delvo : delvos){
    	pk_order = delvo.getPk_order();
    	if(map.containsKey(pk_order)){
    		templist = map.get(pk_order);
    	}
    	else {
    		templist = new ArrayList<UFDouble>();
    	}
    	templist.add(UFDouble.ZERO_DBL.sub(delvo.getNrate()));
    	map.put(pk_order, templist);
    }
    for (PayPlanVO upvo : upvos){
    	pk_order = upvo.getPk_order();
    	if(map.containsKey(pk_order)){
    		templist = map.get(pk_order);
    	}
    	else {
    		templist = new ArrayList<UFDouble>();
    	}
    	templist.add(upvo.getNrate());
    	map.put(pk_order, templist);
    }
    for (PayPlanVO oriupvo : oriupvos){
    	pk_order = oriupvo.getPk_order();
    	if(map.containsKey(pk_order)){
    		templist = map.get(pk_order);
    	}
    	else {
    		templist = new ArrayList<UFDouble>();
    	}
    	templist.add(UFDouble.ZERO_DBL.sub(oriupvo.getNrate()));
    	map.put(pk_order, templist);
    }
    
    List<String> pk_order_list = new ArrayList<String>();

    for (Entry<String, List<UFDouble>> entry : map.entrySet()) {
			pk_order = entry.getKey();
			List<UFDouble> rates = entry.getValue();
			UFDouble sum = UFDouble.ZERO_DBL;
			for (UFDouble rate : rates) {
				sum = sum.add(rate);
			}
			if (!sum.equals(UFDouble.ZERO_DBL)) {
				pk_order_list.add(pk_order);
			}
		}
    if(pk_order_list.size()>0){
    	String[] pk_orders = pk_order_list.toArray(new String[0]);
    	DataAccessUtils utils = new DataAccessUtils();
    	SqlBuilder sql = new SqlBuilder();
    	sql.append(" select vbillcode from po_order ");
    	sql.append(" where ");
    	sql.append(" pk_order ", pk_orders);
    	IRowSet rowset = utils.query(sql.toString());
    	List<String> errbillcode = new ArrayList<String>();
    	while(rowset.next()){
    		errbillcode.add(rowset.getString(0));
    	}
    	String[] errbillcodes = errbillcode.toArray(new String[0]);
    	ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("4004030_0", "04004030-0383", null,
							errbillcodes)/*
																				 * @res "订单{0}的付款计划比率之和不等于100！"
																				 */);
    }
  }
  
  private void addRule(AroundProcesser<AggPayPlanVO> processer) {
    processer.addAfterRule(new PayPlanRowNoRule());
    // processer.addAfterRule(new PayPlanTotalRateChkRule());
    processer.addAfterRule(new PayLimitRule());
    processer.addAfterRule(new OverPayChkRule());
  }

  private void check(String[] hids) {
    if (ArrayUtils.isEmpty(hids)) {
      return;
    }
//    AggPayPlanVO[] vos =
//        new BillQuery<AggPayPlanVO>(AggPayPlanVO.class).query(hids);
    AggPayPlanVO[] vos = new PayPlanQueryBP().queryPayPlanVOs(hids);
    AroundProcesser<AggPayPlanVO> processer =
        new AroundProcesser<AggPayPlanVO>(OrderPluginPoint.PAYPLAN_UPDATE);
    this.addRule(processer);
    processer.after(vos);
  }
}
