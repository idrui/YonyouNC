/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 下午03:40:25
 */
package nc.impl.pu.m21;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.et.m5730.IM5730Maintain;
import nc.itf.pu.m21.IMsgXSAGHT0001Save;
import nc.itf.so.m4331.IDeliveryMaintain;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.so.m30.so.m4331.IRewrite30For4331;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.et.m5730.entity.DetailVO;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.bs.pu.m21.maintain.OrderSaveJKBP;



/**
 * 2017-03-06 攀枝花生产订单接口接口实现类 

 */
public class MsgXSAGHT0001SaveImpl implements IMsgXSAGHT0001Save {
	/**
	   * 缓存回写销售订单信息
	   */
	  List<Rewrite4331Para> saleorderList;
	
	@Override
	public String mXSAGHT0001Head_RequiresNew(NCObject[] objs,OrderVO orderVos,DeliveryVO aggDeliveryVO,DetailVO aggDetailVO,SaleOrderVO aggsaleordervo,String expFlag) throws Exception {
		// TODO 自动生成的方法存根	
		try {
			
			
			OrderContext ctx = new OrderContext();
			BillTransferTool<OrderVO> tool = new BillTransferTool<OrderVO>(new OrderVO[]{orderVos});
		    OrderVO[] originVos = tool.getOriginBills();
		    new OrderSaveJKBP(ctx).save(new OrderVO[]{orderVos}, originVos);
            //MDPersistenceService.lookupPersistenceService().saveBill(objs[0]);
		    //InsertDeliveryBP is = NCLocator.getInstance().lookup(InsertDeliveryBP.class);
		    
		    /*
		    BillTransferTool<DeliveryVO> transferTool =
		            new BillTransferTool<DeliveryVO>(new DeliveryVO[]{aggDeliveryVO});
		    AroundProcesser<DeliveryVO> processer =
		            new AroundProcesser<DeliveryVO>(Action4331PlugInPoint.InsertAction);
		    processer.before(new DeliveryVO[]{aggDeliveryVO});
		    */
		    //new PFRewrite30BeforeRule().process(rewriteObjs);
		    
		    
		    if(expFlag.equals("N")){
		    	IDeliveryMaintain is = NCLocator.getInstance().lookup(IDeliveryMaintain.class);
		    	if(aggDeliveryVO.getParent().getStatus()==1){
		    		is.updateDelivery(new DeliveryVO[]{aggDeliveryVO}, new DeliveryVO[]{aggDeliveryVO});	
		    	}else{
		    		is.insertDelivery(new DeliveryVO[]{aggDeliveryVO});
		    	}
		    	/****** 回写来源单据 ***********/
			    this.saleorderList = new ArrayList<Rewrite4331Para>();
			     UFDouble reValue = aggDeliveryVO.getChildrenVO()[0].getNnum();
			      if (reValue.compareTo(UFDouble.ZERO_DBL) != 0) {
			      
			       String srcBid = aggDeliveryVO.getChildrenVO()[0].getCsrcbid();
			      // 是否关闭上游单据
			      UFBoolean bclosesrcflag = aggDeliveryVO.getChildrenVO()[0].getBclosesrcflag();
			      if (SOBillType.Order.getCode().equals("30")) {
			        Rewrite4331Para para =
			            new Rewrite4331Para(srcBid, reValue, bclosesrcflag, UFBoolean.TRUE);
			        this.saleorderList.add(para);
			      }
			      this.rewriteSaleOrder();
			      }
		    }else if(expFlag.equals("C")){
		    	IM5730Maintain is = NCLocator.getInstance().lookup(IM5730Maintain.class);
		    	if(aggDetailVO.getParent().getStatus()==1){
		    		aggDetailVO.getDetailBVO()[0].setAttributeValue("srcbts", this.FindDirtyTs(aggDetailVO));
		    		BillTransferTool<DetailVO> tools = new BillTransferTool<DetailVO>(new DetailVO[]{aggDetailVO});
		    		DetailVO[] originBills = tools.getOriginBills();
		    		is.updateM5730(new DetailVO[]{aggDetailVO}, originBills);
		    	}else{//srcbts
		    		aggDetailVO.getDetailBVO()[0].setAttributeValue("srcbts", this.FindDirtyTs(aggDetailVO));
		    		is.insertM5730(new DetailVO[]{aggDetailVO});
		    	}
		    	/*NCObject obj = NCObject.newInstance(aggDetailVO);
		    	MDPersistenceService.lookupPersistenceService().saveBill(obj);*/
		    }
		    
		      
		    //processer.after(vos);
		    
		    //is.insert(new DeliveryVO[]{aggDeliveryVO});
		    //MDPersistenceService.lookupPersistenceService().saveBill(objs[1]);
		    
		    
			MDPersistenceService.lookupPersistenceService().saveBill(objs[2]);
			
		} catch (MetaDataException e1) {
			// TODO 自动生成的 catch 块
			ExceptionUtils.getInstance().wrappBusinessException("接口数据保存失败！");
		}
		  return "ok";
  }
	
	@Override
	public String mXSAGHT0001Head_RequiresNew(NCObject objs) {
		// TODO 自动生成的方法存根	
		try {
            MDPersistenceService.lookupPersistenceService().saveBill(objs);
		} catch (MetaDataException e1) {
			// TODO 自动生成的 catch 块
			ExceptionUtils.getInstance().wrappBusinessException("回写失败！");
		}
		  return "ok";
  }
	
	/**
	   * 回写销售订单
	   */
	  private void rewriteSaleOrder() {
	    if (this.saleorderList.size() == 0) {
	      return;
	    }
	    Rewrite4331Para[] paras = new Rewrite4331Para[this.saleorderList.size()];
	    paras = this.saleorderList.toArray(paras);
	    IRewrite30For4331 api =
	        NCLocator.getInstance().lookup(IRewrite30For4331.class);
	    try {
	      api.rewrite30SendNumFor4331(paras);
	    }
	    catch (BusinessException ex) {
	      ExceptionUtils.wrappException(ex);
	    }
	  }
	  
	  public String FindDirtyTs(DetailVO aggDetailVO) {
			//ordernumpf//mdorderno
			BaseDAO baseDao = new BaseDAO();
			String sqlStr = "";
			SQLParameter sp = new SQLParameter();
			sqlStr = " select et_contract_b.ts from et_contract_b  where et_contract_b.pk_contract_b=?  and et_contract_b.dr=0 ;";	
			sp.addParam(aggDetailVO.getDetailBVO()[0].getCsrcbid());
			
			String li = "0";
			try {
				li = (String) baseDao.executeQuery(sqlStr, sp,
						new ResultSetProcessor() {
							@Override
							public String handleResultSet(ResultSet rs)
									throws SQLException {
								rs.next();
								return rs.getString(1);
							}
						});
			} catch (Exception e) {
				ExceptionUtils.wrappBusinessException("执行失败!");
			}
			return li;
		}
	
}

