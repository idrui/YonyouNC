<<<<<<< .mine
///**
// * $�ļ�˵��$
// * 
// * @author zhaoyha
// * @version 6.0
// * @see
// * @since 6.0
// * @time 2010-1-13 ����03:40:25
// */
//package nc.impl.pu.m21;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import nc.bs.dao.BaseDAO;
//import nc.bs.dao.DAOException;
//import nc.itf.pu.m21.IMsgXSAGHT0001;
//import nc.jdbc.framework.SQLParameter;
//import nc.jdbc.framework.processor.ResultSetProcessor;
//import nc.md.data.access.NCObject;
//import nc.md.persist.framework.IMDPersistenceQueryService;
//import nc.md.persist.framework.MDPersistenceService;
//import nc.vo.am.status.StatusVO;
//import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
//import nc.vo.et.m5730.entity.DetailBVO;
//import nc.vo.et.m5730.entity.DetailHVO;
//import nc.vo.et.m5730.entity.DetailVO;
//import nc.vo.pu.m21.entity.MsgXSAGHT0001Head;
//import nc.vo.pu.m21.entity.OrderHeaderVO;
//import nc.vo.pu.m21.entity.OrderItemVO;
//import nc.vo.pu.m21.entity.OrderVO;
//import nc.vo.pu.m21.entity.MsgXSAGHT0001;
//import nc.vo.pub.AggregatedValueObject;
//import nc.vo.pub.VOStatus;
//import nc.vo.pub.lang.UFDate;
//import nc.vo.pubapp.AppContext;
//import nc.vo.so.m4331.entity.DeliveryBVO;
//import nc.vo.so.m4331.entity.DeliveryHVO;
//import nc.vo.so.m4331.entity.DeliveryVO;
//import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
//import nc.vo.lm.intertable.AggLmpgjkHVO;
//import nc.vo.lm.intertable.LmpgjkHVO;
//import nc.vo.lm.intertable.LmpgjkaBVO;
//import nc.vo.lm.intertable.LmpgjkbBVO;
//import nc.vo.lm.intertable.LmpgjkcBVO;
//
//
///**
// * 2017-03-06 ��֦�����������ӿڽӿ�ʵ���� 
//
// */
//public class MsgXSAGHT0001Impl implements IMsgXSAGHT0001 {
//	
//	@Override
//	public String MXSAGHT0001Head(MsgXSAGHT0001Head msgxsaght0001head) {
//		// TODO �Զ����ɵķ������		
//		Map<String, Object> map = new HashMap<>();
////		  boolean flag = this.zjbList(list);
//		  //ҵ����
//		  String flag="2";
//		try {
//			flag = this.MsgXSAGHT0001(msgxsaght0001head);
//		} catch (Exception e) {
//			// TODO �Զ����ɵ� catch ��
//			e.printStackTrace();
//		}finally{
//			//�����м��
//			AggLmpgjkHVO agglmpgjkhvo =this.TranszjbVOMainVO(msgxsaght0001head,flag);
//			  try {
//					NCObject objs = NCObject.newInstance(agglmpgjkhvo);//AGGVO����
//					MDPersistenceService.lookupPersistenceService().saveBill(objs); 
//				} catch (Exception e) {
//					// TODO �Զ����ɵ� catch ��
//					e.printStackTrace();
//				}
//		}
//		  /*map.put("flag", flag);
//		  if(flag){
//			  map.put("reason", "����ɹ�"); 
//		  }else{
//			  map.put("reason", "����ʧ��");
//		  }*/
//		  return "1";
////		return null;
//	}
//	
//	public String MsgXSAGHT0001(MsgXSAGHT0001Head msgxsaght0001head) {
//		// TODO �Զ����ɵķ������
//		//�����м��
//		AggLmpgjkHVO agglmpgjkhvo = this.TranszjbVOMainVO(msgxsaght0001head,"1");
//		BaseDAO baseDao = new BaseDAO();
//		Map<String, Object> map = new HashMap<>();
//		for (int i = 0; i < msgxsaght0001head.getMsgBody().size(); i++) {
//			MsgXSAGHT0001 msgXSAGHT0001=msgxsaght0001head.getMsgBody().get(i);
//			String expFlag = msgXSAGHT0001.getExpFlag();//��ó������ҵ���ʶ
//			/*
//			 * ��óʱ�������ɹ���ͬ�ͷ�����
//			 */
//			if(expFlag.equals("N")){				
//			    int lengthOrder = this.FindSaleOrderNo(msgXSAGHT0001,"reqOrder");
//				if(lengthOrder==0){
//					//���ɹ�������������ʱ����ɹ����������Ͳɹ������ӱ��ڲ�������
//					/*OrderVO aggOrderVo = new OrderVO();
//					OrderHeaderVO orderHeaderVO = TransMainVO(msgXSAGHT0001);
//					OrderItemVO[] orderItemVO = TransSubVO(msgXSAGHT0001);					
//					aggOrderVo.setParentVO(orderHeaderVO);				
//					aggOrderVo.setChildrenVO(orderItemVO);
////					List<OrderVO> lsOrderVO= new ArrayList<>();
////					lsOrderVO.add(aggOrderVo);					
//					try {
//						//String[] ids = baseDao.insertVOList(lsOrderVO);
//						NCObject objs = NCObject.newInstance(aggOrderVo);//AGGVO����
//						MDPersistenceService.lookupPersistenceService().saveBill(objs); 
//					} catch (Exception e) {
//						// TODO �Զ����ɵ� catch ��
//						e.printStackTrace();
//					}	*/	
//					AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
//							agglmpgjkhvo //�м��
// 						    };
//					OrderVO[] aggOrderVo = PfServiceScmUtil.executeVOChange("30-Cxx-pgscdd", "21", srcVosAfterFilter);//30-Cxx-pgscdd�����ε��ݽ������ͣ�21�����ε�������
//					try {
//						NCObject objs = NCObject.newInstance(aggOrderVo);//AGGVO����
//						MDPersistenceService.lookupPersistenceService().saveBill(objs); 
//					} catch (Exception e) {
//						// TODO �Զ����ɵ� catch ��
//						e.printStackTrace();
//					}
//				}else{
//					//���ɹ�������������ʱ��ֻ��ɹ������ӱ��ڲ�������
//					OrderVO aggOrderVo = null;
//					OrderHeaderVO orderHeaderVO = null;				
//					OrderItemVO[] orderItemVO = null;	
//					int lengthSub = this.FindSubOrderNo(msgXSAGHT0001,"reqOrder");
//					if(lengthSub==0){
//						String pasorderNumPf = msgXSAGHT0001.getOrderNumPf();
//						try{
//							NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(OrderHeaderVO.class , "saleorderno = "+pasorderNumPf, false);
//							aggOrderVo = (OrderVO)ncObjects[0].getContainmentObject();
//						} catch(Exception e){
//							
//							return "��ѯ�����ɹ������ں�ͬ��Ϊ"+pasorderNumPf+"������Ϣʧ�ܣ�";				
//						}
//						AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
//								agglmpgjkhvo //�м��
//	 						    };
//						OrderVO[] aggordervo = PfServiceScmUtil.executeVOChange("", "21", srcVosAfterFilter);
//						aggordervo[0].setParentVO(aggOrderVo.getParentVO());
//						try {
//							NCObject objs = NCObject.newInstance(aggordervo[0]);//AGGVO����
//							MDPersistenceService.lookupPersistenceService().saveBill(objs); 
//						} catch (Exception e) {
//							// TODO �Զ����ɵ� catch ��
//							e.printStackTrace();
//						}
//						//orderItemVO[0].setStatus(VOStatus.NEW);
//					}else{
//						//orderItemVO = null;
//					}				
//				}
//				/*
//				 * ����������
//				 */
//			    int lengthSend = this.FindSaleOrderNo(msgXSAGHT0001,"sendDetails");
//				if(lengthSend==0){
//					//���ɹ�������������ʱ����ɹ����������Ͳɹ������ӱ��ڲ�������
//					DeliveryVO aggDeliveryVO = new DeliveryVO();
//					DeliveryHVO deliveryHVO = TransSendMainVO(msgXSAGHT0001);
//					DeliveryBVO[] deliveryBVO = TransSendSubVO(msgXSAGHT0001);					
//					aggDeliveryVO.setParentVO(deliveryHVO);
//					aggDeliveryVO.setChildrenVO(deliveryBVO);				
//					try {
//						NCObject objs = NCObject.newInstance(aggDeliveryVO);//AGGVO����
//						MDPersistenceService.lookupPersistenceService().saveBill(objs);
//					} catch (Exception e) {
//						// TODO �Զ����ɵ� catch ��
//						e.printStackTrace();
//					}					
//				}else{
//					//���ɹ�������������ʱ��ֻ��ɹ������ӱ��ڲ�������
//					DeliveryVO aggDeliveryVO = new DeliveryVO();
//					DeliveryHVO deliveryHVO = null;
//					DeliveryBVO[] deliveryBVO = null;
//					int lengthSub = this.FindSubOrderNo(msgXSAGHT0001,"sendDetails");
//					if(lengthSub==0){
//						deliveryBVO = TransSendSubVO(msgXSAGHT0001);
//					}else{
//						deliveryBVO = null;
//					}
//					String pasorderNumPf = msgXSAGHT0001.getOrderNumPf();
//					try{
//						NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(DeliveryHVO.class , "saleorderno = "+pasorderNumPf, false);
//						aggDeliveryVO = (DeliveryVO)ncObjects[0].getContainmentObject();
//						deliveryHVO = (DeliveryHVO)aggDeliveryVO.getParentVO();
//					} catch(Exception e){
//						
//						return "��ѯ���������ʸ����۶�����Ϊ"+pasorderNumPf+"������Ϣʧ�ܣ�";				
//					}
//					aggDeliveryVO.setParentVO(deliveryHVO);
//					aggDeliveryVO.setChildrenVO(deliveryBVO);					
//					try {
//						NCObject objs = NCObject.newInstance(aggDeliveryVO);//AGGVO����
//						MDPersistenceService.lookupPersistenceService().saveBill(objs);
//					} catch (Exception e) {
//						// TODO �Զ����ɵ� catch ��
//						e.printStackTrace();
//					}					
//				}			
//			}
//			/*
//			 * 
//			 */
//			if(expFlag.equals("C")){				
//			    int length = this.FindSaleOrderNo(msgXSAGHT0001,"reqOrder");
//				if(length==0){
//				OrderVO aggOrderVo = new OrderVO();
//				OrderHeaderVO orderHeaderVO = TransMainVO(msgXSAGHT0001);
//				OrderItemVO[] orderItemVO = TransSubVO(msgXSAGHT0001);					
//				aggOrderVo.setParentVO(orderHeaderVO);	
//				aggOrderVo.setChildrenVO(orderItemVO);				
//				try {
//					NCObject objs = NCObject.newInstance(aggOrderVo);//AGGVO����
//					MDPersistenceService.lookupPersistenceService().saveBill(objs);
//				} catch (Exception e) {
//					// TODO �Զ����ɵ� catch ��
//					e.printStackTrace();
//				}				
//				}else{
//					OrderVO aggOrderVo = new OrderVO();
//					OrderHeaderVO orderHeaderVO = null;			
//					OrderItemVO[] orderItemVO = null;	
//					int lengthSub = this.FindSubOrderNo(msgXSAGHT0001,"reqOrder");
//					if(lengthSub==0){
//						orderItemVO = TransSubVO(msgXSAGHT0001);
//						orderItemVO[0].setStatus(VOStatus.NEW);
//					}else{
//						orderItemVO = null;
//					}				
//					String pasorderNumPf = msgXSAGHT0001.getOrderNumPf();
//					try{
//						NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(OrderHeaderVO.class , "saleorderno = "+pasorderNumPf, false);
//						aggOrderVo = (OrderVO)ncObjects[0].getContainmentObject();
//						orderHeaderVO = (OrderHeaderVO)aggOrderVo.getParentVO();
//					} catch(Exception e){						
//						return "��ѯ�����ɹ������ں�ͬ��Ϊ"+pasorderNumPf+"������Ϣʧ�ܣ�";				
//					}
//					aggOrderVo.setParentVO(orderHeaderVO);			
//					aggOrderVo.setChildrenVO(orderItemVO);
//					try {
//						NCObject objs = NCObject.newInstance(aggOrderVo);//AGGVO����
//						MDPersistenceService.lookupPersistenceService().saveBill(objs);
//					} catch (Exception e) {
//						// TODO �Զ����ɵ� catch ��
//						e.printStackTrace();
//					}
//				}
//				/*
//				 * ����������ϸ
//				 */
//			    int lengthDetail = this.FindSaleOrderNo(msgXSAGHT0001,"details");
//				if(lengthDetail==0){
//					//���ɹ�������������ʱ����ɹ����������Ͳɹ������ӱ��ڲ�������
//					DetailVO aggDetailVO = new DetailVO();
//					DetailHVO detailHVO = TransDetailHVOMainVO(msgXSAGHT0001);
//					DetailBVO[] detailBVO = TransDetailBVOSubVO(msgXSAGHT0001);					
//					aggDetailVO.setParentVO(detailHVO);
//					aggDetailVO.setChildrenVO(detailBVO);					
//					try {
//						NCObject objs = NCObject.newInstance(aggDetailVO);//AGGVO����
//						MDPersistenceService.lookupPersistenceService().saveBill(objs);
//					} catch (Exception e) {
//						// TODO �Զ����ɵ� catch ��
//						e.printStackTrace();
//					}					
//				}else{
//					//���ɹ�������������ʱ��ֻ��ɹ������ӱ��ڲ�������
//					DetailVO aggDetailVO = new DetailVO();
//					DetailHVO detailHVO = null;	
//					DetailBVO[] detailBVO = null;
//					int lengthSubDetail = this.FindSubOrderNo(msgXSAGHT0001,"details");
//					if(lengthSubDetail==0){
//						detailBVO = TransDetailBVOSubVO(msgXSAGHT0001);
//					}else{
//						detailBVO = null;
//					}
//					String pasorderNumPf = msgXSAGHT0001.getOrderNumPf();
//					try{
//						NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(DetailHVO.class , "saleorderno = "+pasorderNumPf, false);
//						aggDetailVO = (DetailVO)ncObjects[0].getContainmentObject();
//						detailHVO = (DetailHVO)aggDetailVO.getParentVO();
//					} catch(Exception e){
//						
//						return "��ѯ������ϸ�ж�����Ϊ"+pasorderNumPf+"������Ϣʧ�ܣ�";				
//					}
//					aggDetailVO.setParentVO(detailHVO);
//					aggDetailVO.setChildrenVO(detailBVO);
////					List<DetailVO> lsDetailVO= new ArrayList<>();
////					lsDetailVO.add(aggDetailVO);					
//					try {
////						String[] ids = baseDao.insertVOList(lsDetailVO);
//						NCObject objs = NCObject.newInstance(aggDetailVO);//AGGVO����
//						MDPersistenceService.lookupPersistenceService().saveBill(objs);
//					} catch (Exception e) {
//						// TODO �Զ����ɵ� catch ��
//						e.printStackTrace();
//					}						
//				}		
//			}
//			/**
//			 * ���ɶ��ձ�
//			 */
//			int length = this.FindSaleOrderNo(msgXSAGHT0001, "pgpdtorderrelation");
//			if(length==0){
//				
//			}else{
//				return "�ӿ����ݴ���ʧ�ܣ�";
//			}
//			
//		}
//		return "OK";
//}
//	/**
//	 * ���ݶ����Ų�ѯ��Ӧ���������Ƿ��иö���������
//	 * Parma msgXSAGHT0001�ӿڴ��ݵĲɹ���������
//	 * type��ѯ�������ͣ�reqOrder-�ɹ�������sendDetails-��������details-������ϸ
//	 */
//	public int FindSaleOrderNo(MsgXSAGHT0001 msgXSAGHT0001,String type) {
//		String expFlag = msgXSAGHT0001.getExpFlag();//��ó������ҵ���ʶ
//		BaseDAO baseDao = new BaseDAO();
//		String sqlStr = "";
//		SQLParameter sp = new SQLParameter();
//		if(type.equals("reqOrder")){
//			sqlStr = "select count(*) from po_order where saleorderno=?";	
//			sp.addParam(msgXSAGHT0001.getOrderNumPf());
//		}else if(type.equals("sendDetails")){
//			sqlStr = "select count(*) from so_delivery where saleorderno=?";
//			sp.addParam(msgXSAGHT0001.getOrderNumPf());
//		}else if(type.equals("details")){
//			sqlStr = "select count(*) from et_detail where saleorderno=?";
//			sp.addParam(msgXSAGHT0001.getOrderNumPf());
//		}else if(type.equals("pgpdtorderrelation")){
//			sqlStr = "select count(*) from lm_pgpdtorderrelation where saleorderno=? and productorderrowno=?";
//			sp.addParam(msgXSAGHT0001.getOrderNumPf());
//			sp.addParam(msgXSAGHT0001.getMdOrderNo());
//		}
//		String li = "0";
//		try {
//			li = (String) baseDao.executeQuery(sqlStr, sp,
//					new ResultSetProcessor() {
//						@Override
//						public String handleResultSet(ResultSet rs)
//								throws SQLException {
//							rs.next();
//							return rs.getString(1);
//						}
//					});
//		} catch (Exception e) {
//			ExceptionUtils.wrappBusinessException("ִ��ʧ��!");
//		}
//		return Integer.parseInt(li);
//	}	
//	/**
//	 * ���ݶ����Ų�ѯ��Ӧ���ӱ����Ƿ��иö���������
//	 * Parma msgXSAGHT0001�ӿڴ��ݵĲɹ���������
//	 * type��ѯ�������ͣ�reqOrder-�ɹ�������sendDetails-��������details-���� ��ϸ
//	 */
//	public int FindSubOrderNo(MsgXSAGHT0001 msgXSAGHT0001,String type) {
//		String expFlag = msgXSAGHT0001.getExpFlag();//��ó������ҵ���ʶ
//		BaseDAO baseDao = new BaseDAO();
//		String sqlStr = "";
//		SQLParameter sp = new SQLParameter();
//		if(type.equals("reqOrder")){
//			sqlStr = "select count(*) from po_order_b where productorderrowno=?";	
//			sp.addParam(msgXSAGHT0001.getMdOrderNo());
//		}else if(type.equals("sendDetails")){
//			sqlStr = "select count(*) from so_delivery_b where productorderrowno=?";
//			sp.addParam(msgXSAGHT0001.getMdOrderNo());
//		}else if(type.equals("details")){
//			sqlStr = "select count(*) from et_detail_b where productorderrowno=?";
//			sp.addParam(msgXSAGHT0001.getMdOrderNo());
//		}
//		String li = "0";
//		try {
//			li = (String) baseDao.executeQuery(sqlStr, sp,
//					new ResultSetProcessor() {
//						@Override
//						public String handleResultSet(ResultSet rs)
//								throws SQLException {
//							rs.next();
//							return rs.getString(1);
//						}
//					});
//		} catch (Exception e) {
//			ExceptionUtils.wrappBusinessException("ִ��ʧ��!");
//		}
//		return Integer.parseInt(li);
//	}
//	/*
//	 * ���ݽӿ��м�㴫�ݵĲ�����ת���ɹ�������Vo
//	 */
//	public OrderHeaderVO TransMainVO(MsgXSAGHT0001 msgXSAGHT0001){		
//		OrderHeaderVO orderHeaderVO = new OrderHeaderVO();
//		//orderHeaderVO.setAttributeValue("vbillcode", );//�������
//		orderHeaderVO.setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//�ʸ����۶�����
//		orderHeaderVO.setAttributeValue("productorderno", msgXSAGHT0001.getMdOrderNumPf());//�ʸ�����������
//		orderHeaderVO.setAttributeValue("contractno", msgXSAGHT0001.getErpOrderNumPf());//NC��ͬ��
//		orderHeaderVO.setAttributeValue("dbilldate", AppContext.getInstance().getServerTime());//��������
////		orderHeaderVO.setAttributeValue("pk_supplier", AppContext.getInstance().getServerTime());//��Ӧ��
////		orderHeaderVO.setAttributeValue("pk_invcsupllier", AppContext.getInstance().getServerTime());//��Ʊ��Ӧ��
//		orderHeaderVO.setAttributeValue("billstatus ", "1");//����״̬
////		orderHeaderVO.setAttributeValue("pk_group ", "1");//��������
////		orderHeaderVO.setAttributeValue("pk_org ", "1");//�ɹ���֯�汾��Ϣ
////		orderHeaderVO.setAttributeValue("pk_org_v ", "1");//�ɹ���֯
//		return orderHeaderVO;
//	}
//	/*
//	 * ���ݽӿ��м�㴫�ݵĲ�����ת���ɹ�������ϸVo
//	 */
//	public OrderItemVO[] TransSubVO(MsgXSAGHT0001 msgXSAGHT0001){		
//		OrderItemVO[] orderItemVO = new OrderItemVO[1];
//		orderItemVO[0].setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//�ʸ����۶�����
//		orderItemVO[0].setAttributeValue("saleorderrowno", msgXSAGHT0001.getOrderNo());//�ʸ����۶��������
//		orderItemVO[0].setAttributeValue("productorderrowno", msgXSAGHT0001.getMdOrderNo());//�ʸ��������������
////		orderItemVO[0].setAttributeValue("pk_material", msgXSAGHT0001.getMdOrderNo());//���ϰ汾��Ϣ
//		String expFlag = msgXSAGHT0001.getExpFlag();//��ó������ҵ���ʶ
//		if(expFlag.equals("N")){
//			orderItemVO[0].setAttributeValue("cfirsttypecode ", "30");//Դͷ��������		
//		}else{
//			orderItemVO[0].setAttributeValue("cfirsttypecode ", "5720");//Դͷ��������	
//		}
////		orderItemVO[0].setAttributeValue("cfirstid ", msgXSAGHT0001.getErpOrderNo());//Դͷ����
//		orderItemVO[0].setAttributeValue("cfirstbid  ", msgXSAGHT0001.getErpOrderNo());//Դͷ������ϸ
//		orderItemVO[0].setAttributeValue("vfirstcode", msgXSAGHT0001.getErpOrderNumPf());//Դͷ���ݺ�
//		orderItemVO[0].setAttributeValue("vsourcecode", msgXSAGHT0001.getErpOrderNumPf());//��Դ���ݺ�
//		if(expFlag.equals("N")){
//			orderItemVO[0].setAttributeValue("csourcetypecode", "30");//Դͷ��������		
//		}else{
//			orderItemVO[0].setAttributeValue("csourcetypecode", "5720");//Դͷ��������	
//		}
////		orderItemVO[0].setAttributeValue("csourcebid ", msgXSAGHT0001.getErpOrderNo());//��Դ����
//		orderItemVO[0].setAttributeValue("csourcebid ", msgXSAGHT0001.getErpOrderNo());//��Դ������ϸ
//		orderItemVO[0].setAttributeValue("nastnum", msgXSAGHT0001.getContOrderNum());//����
//		orderItemVO[0].setAttributeValue("mainamount", msgXSAGHT0001.getContOrderNum());//������
//		return orderItemVO;
//	}	
//	/*
//	 * ���ݽӿ��м�㴫�ݵĲ�����ת����������Vo
//	 */
//	public DeliveryHVO TransSendMainVO(MsgXSAGHT0001 msgXSAGHT0001){		
//		DeliveryHVO deliveryHVO = new DeliveryHVO();		
////		deliveryHVO.setAttributeValue("vbillcode", msgXSAGHT0001.getOrderNumPf());//���ݺ�		
//		deliveryHVO.setAttributeValue("dbilldate", AppContext.getInstance().getServerTime());//��������
//		deliveryHVO.setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//�ʸ����۶�����
//		deliveryHVO.setAttributeValue("productorderno", msgXSAGHT0001.getMdOrderNumPf());//�ʸ�����������
//		deliveryHVO.setAttributeValue("contractno", msgXSAGHT0001.getErpOrderNumPf());//NC��ͬ��
//		deliveryHVO.setAttributeValue("fstatusflag", "2");//����״̬
////		deliveryHVO.setAttributeValue("pk_group", "2");//����״̬
////		deliveryHVO.setAttributeValue("pk_org", "2");//����״̬
////		deliveryHVO.setAttributeValue("pk_org_v", "2");//����״̬
//		return deliveryHVO;
//	}
//	/*
//	 * ���ݽӿ��м�㴫�ݵĲ�����ת����������ϸVo
//	 */
//	public DeliveryBVO[] TransSendSubVO(MsgXSAGHT0001 msgXSAGHT0001){		
//		DeliveryBVO[] deliveryBVO = new DeliveryBVO[1];
//		deliveryBVO[0].setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//�ʸ����۶�����
//		deliveryBVO[0].setAttributeValue("saleorderrowno", msgXSAGHT0001.getOrderNo());//�ʸ����۶��������
//		deliveryBVO[0].setAttributeValue("productorderrowno", msgXSAGHT0001.getMdOrderNo());//�ʸ��������������
//		deliveryBVO[0].setAttributeValue("vsrctype", "30");//Դ��������
//		deliveryBVO[0].setAttributeValue("vsourcecode", msgXSAGHT0001.getErpOrderNumPf());//��Դ���ݺ�
//		//deliveryBVO[0].setAttributeValue("csrcid", msgXSAGHT0001.getErpOrderNumPf());//��Դ���ݱ�ͷID
//		deliveryBVO[0].setAttributeValue("csrcbid", msgXSAGHT0001.getErpOrderNo());//��Դ���ݱ���ID
//		deliveryBVO[0].setAttributeValue("vfirstcode", msgXSAGHT0001.getErpOrderNumPf());//Դͷ���ݺ�
//		deliveryBVO[0].setAttributeValue("vfirsttype", "30");//Դͷ��������
//		deliveryBVO[0].setAttributeValue("cfirstbid", msgXSAGHT0001.getErpOrderNo());//Դͷ���ݱ���ID
////		deliveryBVO[0].setAttributeValue("cfirstid", msgXSAGHT0001.getErpOrderNo());//Դͷ���ݱ�ͷID
//		deliveryBVO[0].setAttributeValue("cmaterialid", msgXSAGHT0001.getErpOrderNo());//���ϵ���
//		deliveryBVO[0].setAttributeValue("cmaterialvid", msgXSAGHT0001.getErpOrderNo());//���ϱ���
////		deliveryBVO[0].setAttributeValue("cordercustid", msgXSAGHT0001.getErpOrderNo());//�ͻ�����
////		deliveryBVO[0].setAttributeValue("cinvoicecustid", msgXSAGHT0001.getErpOrderNo());//��Ʊ�ͻ�
//		deliveryBVO[0].setAttributeValue("csaleorgvid", msgXSAGHT0001.getErpOrderNo());//������֯
//		deliveryBVO[0].setAttributeValue("csettleorgid", msgXSAGHT0001.getErpOrderNo());//���������֯���°汾
//		deliveryBVO[0].setAttributeValue("csettleorgvid", msgXSAGHT0001.getErpOrderNo());//���������֯
//		deliveryBVO[0].setAttributeValue("nastnum", msgXSAGHT0001.getContOrderNum());//����
//		deliveryBVO[0].setAttributeValue("nnum", msgXSAGHT0001.getContOrderNum());//������
//		return deliveryBVO;
//	}
//	/*
//	 * ���ݽӿ��м�㴫�ݵĲ�����ת��������ϸ��Vo
//	 */
//	public DetailHVO TransDetailHVOMainVO(MsgXSAGHT0001 msgXSAGHT0001){		
//		DetailHVO detailHVO = new DetailHVO();
//		detailHVO.setAttributeValue("dbilldate", AppContext.getInstance().getServerTime());//��������
//		detailHVO.setAttributeValue("dmakedate", AppContext.getInstance().getServerTime());//�Ƶ�����
////		detailHVO.setAttributeValue("vbillcode", msgXSAGHT0001.getOrderNumPf());//������ϸ����
//		detailHVO.setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//�ʸ����۶�����
//		detailHVO.setAttributeValue("productorderno", msgXSAGHT0001.getMdOrderNumPf());//�ʸ�����������
////		detailHVO.setAttributeValue("carorgid", msgXSAGHT0001.getMdOrderNumPf());//Ӧ����֯���°汾
////		detailHVO.setAttributeValue("carorgvid", msgXSAGHT0001.getMdOrderNumPf());//Ӧ����֯
////		detailHVO.setAttributeValue("csettleorgid", msgXSAGHT0001.getMdOrderNumPf());//���������֯���°汾
////		detailHVO.setAttributeValue("csettleorgvid", msgXSAGHT0001.getMdOrderNumPf());//���������֯
////		detailHVO.setAttributeValue("ctradewordid", msgXSAGHT0001.getMdOrderNumPf());//ó������
////		detailHVO.setAttributeValue("ctransporttypeid", msgXSAGHT0001.getMdOrderNumPf());//���䷽ʽ
////		detailHVO.setAttributeValue("ccustomerid", msgXSAGHT0001.getMdOrderNumPf());//�ͻ�
////		detailHVO.setAttributeValue("cinvoicecustid", msgXSAGHT0001.getMdOrderNumPf());//��Ʊ�ͻ�
////		detailHVO.setAttributeValue("cdeptid", msgXSAGHT0001.getMdOrderNumPf());//�������°汾
////		detailHVO.setAttributeValue("cdeptvid", msgXSAGHT0001.getMdOrderNumPf());//����
////		detailHVO.setAttributeValue("cloadportid", msgXSAGHT0001.getMdOrderNumPf());//���˸�
////		detailHVO.setAttributeValue("cdestportid", msgXSAGHT0001.getMdOrderNumPf());//Ŀ�ĸ�
////		detailHVO.setAttributeValue("corigcurrencyid", msgXSAGHT0001.getMdOrderNumPf());//����
////		detailHVO.setAttributeValue("pk_group", msgXSAGHT0001.getMdOrderNumPf());//����
////		detailHVO.setAttributeValue("pk_org", msgXSAGHT0001.getMdOrderNumPf());//������֯
////		detailHVO.setAttributeValue("pk_org_v", msgXSAGHT0001.getMdOrderNumPf());//������֯�汾
//		detailHVO.setAttributeValue("fpfstatusflag", "1");//������״̬
//		detailHVO.setAttributeValue("fstatusflag", "3");//����״̬
//		return detailHVO;
//	}
//	/*
//	 * ���ݽӿ��м�㴫�ݵĲ�����ת��������ϸ��Vo
//	 */
//	public DetailBVO[] TransDetailBVOSubVO(MsgXSAGHT0001 msgXSAGHT0001){		
//		DetailBVO[] detailBVO = new DetailBVO[1];	
//		detailBVO[0].setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//�ʸ����۶�����
//		detailBVO[0].setAttributeValue("saleorderrowno", msgXSAGHT0001.getOrderNo());//�ʸ����۶��������
//		detailBVO[0].setAttributeValue("productorderrowno", msgXSAGHT0001.getMdOrderNo());//�ʸ��������������		
////		detailBVO[0].setAttributeValue("csrcid", msgXSAGHT0001.getErpOrderNo());//��Դ��������
//		detailBVO[0].setAttributeValue("csrcbid", msgXSAGHT0001.getErpOrderNo());//��Դ���ݸ���
//		detailBVO[0].setAttributeValue("vsourcecode", msgXSAGHT0001.getErpOrderNumPf());//��Դ���ݺ�
//		detailBVO[0].setAttributeValue("vsrctype", "5720");//��Դ��������
////		detailBVO[0].setAttributeValue("cfirstbid", );//Դͷ�����ӱ�
////		detailBVO[0].setAttributeValue("cfirstid", );//Դͷ��������
//		detailBVO[0].setAttributeValue("vfirstcode", msgXSAGHT0001.getErpOrderNumPf());//Դͷ���ݺ�
//		detailBVO[0].setAttributeValue("vfirsttype", "5720");//��Դ��������
////		detailBVO[0].setAttributeValue("cmaterialid", "5720");//�������°汾
////		detailBVO[0].setAttributeValue("cmaterialvid", "5720");//���ϱ���
//		detailBVO[0].setAttributeValue("nastnum", msgXSAGHT0001.getContOrderNum());//����
//		detailBVO[0].setAttributeValue("nnum", msgXSAGHT0001.getContOrderNum());//������	
////		detailBVO[0].setAttributeValue("nqtorigprice", msgXSAGHT0001.getContOrderNum());//����
////		detailBVO[0].setAttributeValue("norigprice", msgXSAGHT0001.getContOrderNum());//������	
//		return detailBVO;
//	}
//	
//	/*
//	 * ���ݽӿ��м�㴫�ݵĲ�����ת�����ձ�VO
//	 */
//	public DetailBVO[] TransdzbVOSubVO(MsgXSAGHT0001 msgXSAGHT0001){		
//		DetailBVO[] detailBVO = new DetailBVO[1];	
//		detailBVO[0].setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//�ʸ����۶�����
//		detailBVO[0].setAttributeValue("saleorderrowno", msgXSAGHT0001.getOrderNo());//�ʸ����۶��������
//		detailBVO[0].setAttributeValue("productorderrowno", msgXSAGHT0001.getMdOrderNo());//�ʸ��������������		
////		detailBVO[0].setAttributeValue("csrcid", msgXSAGHT0001.getErpOrderNo());//��Դ��������
//		detailBVO[0].setAttributeValue("csrcbid", msgXSAGHT0001.getErpOrderNo());//��Դ���ݸ���
//		detailBVO[0].setAttributeValue("vsourcecode", msgXSAGHT0001.getErpOrderNumPf());//��Դ���ݺ�
//		detailBVO[0].setAttributeValue("vsrctype", "5720");//��Դ��������
////		detailBVO[0].setAttributeValue("cfirstbid", );//Դͷ�����ӱ�
////		detailBVO[0].setAttributeValue("cfirstid", );//Դͷ��������
//		detailBVO[0].setAttributeValue("vfirstcode", msgXSAGHT0001.getErpOrderNumPf());//Դͷ���ݺ�
//		detailBVO[0].setAttributeValue("vfirsttype", "5720");//��Դ��������
////		detailBVO[0].setAttributeValue("cmaterialid", "5720");//�������°汾
////		detailBVO[0].setAttributeValue("cmaterialvid", "5720");//���ϱ���
//		detailBVO[0].setAttributeValue("nastnum", msgXSAGHT0001.getContOrderNum());//����
//		detailBVO[0].setAttributeValue("nnum", msgXSAGHT0001.getContOrderNum());//������	
////		detailBVO[0].setAttributeValue("nqtorigprice", msgXSAGHT0001.getContOrderNum());//����
////		detailBVO[0].setAttributeValue("norigprice", msgXSAGHT0001.getContOrderNum());//������	
//		return detailBVO;
//	}
//	
//	/*
//	 * ���ݽӿ��м�㴫�ݵĲ�����ת���м��VO
//	 */
//	public AggLmpgjkHVO TranszjbVOMainVO(MsgXSAGHT0001Head msgxsaght0001head,String flag){
//		
//		AggLmpgjkHVO agglmpgjkhvo = new AggLmpgjkHVO();
//		
//		LmpgjkHVO lmpgjkHVO = new LmpgjkHVO();	
//		lmpgjkHVO.setAttributeValue("dwid", msgxsaght0001head.getMsgId());//����ID
//		lmpgjkHVO.setAttributeValue("sendtype", "1");//�������� 1.����
//		lmpgjkHVO.setAttributeValue("interfacetype", "2");//�ӿ����� 2.�ʸֻ������۶���
//		lmpgjkHVO.setAttributeValue("flag", flag);//������ʶ 1.�ɹ� 2.ʧ��
//		lmpgjkHVO.setAttributeValue("receivetime", AppContext.getInstance().getServerTime());//����ʱ�� 
//		lmpgjkHVO.setAttributeValue("attrname0", msgxsaght0001head.getMsgBody().get(0).getOrderNumPf());//�ʸ����۶�����
//		lmpgjkHVO.setAttributeValue("attrname1", msgxsaght0001head.getMsgBody().get(0).getMdOrderNumPf());//��϶�����
//		lmpgjkHVO.setAttributeValue("attrname2", msgxsaght0001head.getMsgBody().get(0).getErpOrderNumPf());//ERP��ͬ��
//		lmpgjkHVO.setAttributeValue("attrname3", msgxsaght0001head.getMsgBody().get(0).getOrderStatusPf());//����״̬_PF
//		lmpgjkHVO.setAttributeValue("attrname4", msgxsaght0001head.getMsgBody().get(0).getOrderCertNo());//����ƾ��
//		lmpgjkHVO.setAttributeValue("attrname5", msgxsaght0001head.getMsgBody().get(0).getOrderTypeCode());//�������ʴ���
//		lmpgjkHVO.setAttributeValue("attrname6", msgxsaght0001head.getMsgBody().get(0).getExpFlag());//��������־ 
//		lmpgjkHVO.setAttributeValue("attrname7", msgxsaght0001head.getMsgBody().get(0).getOrderPeriodPf());//�����ڱ�_PF
//		lmpgjkHVO.setAttributeValue("attrname8", msgxsaght0001head.getMsgBody().get(0).getResoPeriodPf());//��Դ�ڱ�_PF
//		lmpgjkHVO.setAttributeValue("attrname9", msgxsaght0001head.getMsgBody().get(0).getSaleNetworkPf());//��������
//		lmpgjkHVO.setAttributeValue("attrname10", msgxsaght0001head.getMsgBody().get(0).getProdClassCode());//��Ʒ�������
//		lmpgjkHVO.setAttributeValue("attrname11", msgxsaght0001head.getMsgBody().get(0).getCompanyCodePf());//��˾��_PF
//		lmpgjkHVO.setAttributeValue("attrname12", msgxsaght0001head.getMsgBody().get(0).getTradeMode());//ó�׷�ʽ
//		lmpgjkHVO.setAttributeValue("attrname13", msgxsaght0001head.getMsgBody().get(0).getSettleMode());//���㷽ʽ����
//		lmpgjkHVO.setAttributeValue("attrname14", msgxsaght0001head.getMsgBody().get(0).getSignSitePf());//��Լ�ص����_PF
//		lmpgjkHVO.setAttributeValue("attrname15", msgxsaght0001head.getMsgBody().get(0).getPriceFormulaMode());//�Ƽ۷�ʽ
//		lmpgjkHVO.setAttributeValue("attrname16", msgxsaght0001head.getMsgBody().get(0).getRmaNoPf());//�����_PF
//		lmpgjkHVO.setAttributeValue("attrname17", msgxsaght0001head.getMsgBody().get(0).getContInDate());//��Լ¼������
//		lmpgjkHVO.setAttributeValue("attrname18", msgxsaght0001head.getMsgBody().get(0).getContInPerson());//��Լ¼����
//		lmpgjkHVO.setAttributeValue("attrname19", msgxsaght0001head.getMsgBody().get(0).getOrderModiMark());//��������б�־
//		lmpgjkHVO.setAttributeValue("attrname20", msgxsaght0001head.getMsgBody().get(0).getOrderUserCodePf());//�����û�����_PF
//		lmpgjkHVO.setAttributeValue("attrname21", msgxsaght0001head.getMsgBody().get(0).getOrderUserCnamePf());//�����û���������_PF
//		lmpgjkHVO.setAttributeValue("attrname22", msgxsaght0001head.getMsgBody().get(0).getOrderUserAddrNum().toString());//�����û���ַ�� 
//		lmpgjkHVO.setAttributeValue("attrname23", msgxsaght0001head.getMsgBody().get(0).getOrderUserAddrNamePf());//�����û���ַ_PF
//		lmpgjkHVO.setAttributeValue("attrname24", msgxsaght0001head.getMsgBody().get(0).getSettleUserNum());//�跽�û�����
//		lmpgjkHVO.setAttributeValue("attrname25", msgxsaght0001head.getMsgBody().get(0).getSettleUserNamePf());//�跽�û�����_PF
//		lmpgjkHVO.setAttributeValue("attrname26", msgxsaght0001head.getMsgBody().get(0).getSettUserAddrNum().toString());//�跽�û���ַ��
//		lmpgjkHVO.setAttributeValue("attrname27", msgxsaght0001head.getMsgBody().get(0).getSettUserAddrPf());//�跽�û���ַ_PF
//		lmpgjkHVO.setAttributeValue("attrname28", msgxsaght0001head.getMsgBody().get(0).getSettUserAcctNum().toString());//�跽�û��ʻ����
//		lmpgjkHVO.setAttributeValue("attrname29", msgxsaght0001head.getMsgBody().get(0).getSettUserAcctPf());//�跽�û��ʻ�_PF
//		lmpgjkHVO.setAttributeValue("attrname30", msgxsaght0001head.getMsgBody().get(0).getContOrderNum().toString());//��Լ�������� 
//		lmpgjkHVO.setAttributeValue("attrname31", msgxsaght0001head.getMsgBody().get(0).getContTotWt().toString());//��Լ������
//		lmpgjkHVO.setAttributeValue("attrname32", msgxsaght0001head.getMsgBody().get(0).getContTotAmt().toString());//��Լ�ܽ�� 
//		lmpgjkHVO.setAttributeValue("attrname33", msgxsaght0001head.getMsgBody().get(0).getContTrnpTotAmt().toString());//��Լ�˷��ܽ�� 
//		lmpgjkHVO.setAttributeValue("attrname34", msgxsaght0001head.getMsgBody().get(0).getContDepositNum().toString());//��ԼѺ����
//		lmpgjkHVO.setAttributeValue("attrname35", msgxsaght0001head.getMsgBody().get(0).getContDepositAmt().toString());//��ԼѺ���� 
//		lmpgjkHVO.setAttributeValue("attrname36", msgxsaght0001head.getMsgBody().get(0).getContPrepayRate().toString());//��Լ������� 
//		lmpgjkHVO.setAttributeValue("attrname37", msgxsaght0001head.getMsgBody().get(0).getContPrepayLackAmt().toString());//��ԼӦ�ն����
//		lmpgjkHVO.setAttributeValue("attrname38", msgxsaght0001head.getMsgBody().get(0).getTaxRatePf().toString());//˰��_PF 
//		lmpgjkHVO.setAttributeValue("attrname39", msgxsaght0001head.getMsgBody().get(0).getOrderNo());//��ͬ��
//		lmpgjkHVO.setAttributeValue("attrname40", msgxsaght0001head.getMsgBody().get(0).getMdOrderNo());//��϶�������� 
//		lmpgjkHVO.setAttributeValue("attrname41", msgxsaght0001head.getMsgBody().get(0).getErpOrderNo());//ERP��ͬ�����
//		lmpgjkHVO.setAttributeValue("attrname42", msgxsaght0001head.getMsgBody().get(0).getOrderModiNum().toString());//��ͬ�������
//		lmpgjkHVO.setAttributeValue("attrname43", msgxsaght0001head.getMsgBody().get(0).getOrderLastSeqNo());//��ͬ�������
//		lmpgjkHVO.setAttributeValue("attrname44", msgxsaght0001head.getMsgBody().get(0).getSaleId());//����ҵ��Ա����
//		lmpgjkHVO.setAttributeValue("attrname45", msgxsaght0001head.getMsgBody().get(0).getRecCreateTime());//��¼����ʱ��  
//		lmpgjkHVO.setAttributeValue("attrname46", msgxsaght0001head.getMsgBody().get(0).getPartOrderFlag());//�ײö�����־
//		lmpgjkHVO.setAttributeValue("attrname47", msgxsaght0001head.getMsgBody().get(0).getCutNum().toString());//�������� 
//		lmpgjkHVO.setAttributeValue("attrname48", msgxsaght0001head.getMsgBody().get(0).getOrderItemStatusCode());//���۶�������״̬
//		lmpgjkHVO.setAttributeValue("attrname49", msgxsaght0001head.getMsgBody().get(0).getOrderItemModiMark());//�����������б��
//		lmpgjkHVO.setAttributeValue("attrname50", msgxsaght0001head.getMsgBody().get(0).getModiCause());//���ԭ�� 
//		lmpgjkHVO.setStatus(VOStatus.NEW);
//		
//		LmpgjkaBVO[] lmpgjkabvos = new LmpgjkaBVO[1];
//		LmpgjkaBVO lmpgjkabvo = new LmpgjkaBVO();
//		lmpgjkabvo.setAttributeValue("attrname0", msgxsaght0001head.getMsgBody().get(0).getModiDate());//�������
//		lmpgjkabvo.setAttributeValue("attrname1", msgxsaght0001head.getMsgBody().get(0).getModiOperPerson());//��ͬ���������
//		lmpgjkabvo.setAttributeValue("attrname2", msgxsaght0001head.getMsgBody().get(0).getModiType());//������� 
//		lmpgjkabvo.setAttributeValue("attrname3", msgxsaght0001head.getMsgBody().get(0).getFinUserNum());//�����û�����
//		lmpgjkabvo.setAttributeValue("attrname4", msgxsaght0001head.getMsgBody().get(0).getFinUserName());//�����û�����
//		lmpgjkabvo.setAttributeValue("attrname5", msgxsaght0001head.getMsgBody().get(0).getFinUserTrade());//�����û�����ҵ����
//		lmpgjkabvo.setAttributeValue("attrname6", msgxsaght0001head.getMsgBody().get(0).getUserCredit());//�û�������
//		lmpgjkabvo.setAttributeValue("attrname7", msgxsaght0001head.getMsgBody().get(0).getConsignNum());//�ջ��û�����
//		lmpgjkabvo.setAttributeValue("attrname8", msgxsaght0001head.getMsgBody().get(0).getConsignNamePf());//�ջ��û�����_PF
//		lmpgjkabvo.setAttributeValue("attrname9", msgxsaght0001head.getMsgBody().get(0).getCnsgAddressNum().toString());//�ջ��û���ַ��
//		lmpgjkabvo.setAttributeValue("attrname10", msgxsaght0001head.getMsgBody().get(0).getCnsgAddressPf());//�ջ��û���ַ_PF
//		lmpgjkabvo.setAttributeValue("attrname11", msgxsaght0001head.getMsgBody().get(0).getConsignNum1());//�ջ��û�����1
//		lmpgjkabvo.setAttributeValue("attrname12", msgxsaght0001head.getMsgBody().get(0).getConsignName1Pf());//�ջ��û�����1_PF
//		lmpgjkabvo.setAttributeValue("attrname13", msgxsaght0001head.getMsgBody().get(0).getCnsgAddressNum1().toString());//�ջ��û���ַ��1
//		lmpgjkabvo.setAttributeValue("attrname14", msgxsaght0001head.getMsgBody().get(0).getCnsgAddress1Pf());//�ջ��û���ַ1_PF
//		lmpgjkabvo.setAttributeValue("attrname15", msgxsaght0001head.getMsgBody().get(0).getTrnpTitleCode());//�˷ѷ�Ʊ̧ͷ����
//		lmpgjkabvo.setAttributeValue("attrname16", msgxsaght0001head.getMsgBody().get(0).getTrnpTitleText());//�˷ѷ�Ʊ̧ͷ
//		lmpgjkabvo.setAttributeValue("attrname17", msgxsaght0001head.getMsgBody().get(0).getPsr());//��Ʒ�淶�� 
//		lmpgjkabvo.setAttributeValue("attrname18", msgxsaght0001head.getMsgBody().get(0).getApn());//������; 
//		lmpgjkabvo.setAttributeValue("attrname19", msgxsaght0001head.getMsgBody().get(0).getMsc());//ұ��淶�� 
//		lmpgjkabvo.setAttributeValue("attrname20", msgxsaght0001head.getMsgBody().get(0).getNewProductFlag());//���Բ�Ʒ��־
//		lmpgjkabvo.setAttributeValue("attrname21", msgxsaght0001head.getMsgBody().get(0).getProductDscr());//��Ʒ����
//		lmpgjkabvo.setAttributeValue("attrname22", msgxsaght0001head.getMsgBody().get(0).getShopsignPf());//�ƺ�_PF
//		lmpgjkabvo.setAttributeValue("attrname23", msgxsaght0001head.getMsgBody().get(0).getSteelStd());//��Ʒ��׼
//		lmpgjkabvo.setAttributeValue("attrname24", msgxsaght0001head.getMsgBody().get(0).getCapabYnFlag());//�����Ƿ�Ҫ���־ 
//		lmpgjkabvo.setAttributeValue("attrname25", msgxsaght0001head.getMsgBody().get(0).getRainCoatFlag());//�Ӹ��겼��־
//		lmpgjkabvo.setAttributeValue("attrname26", msgxsaght0001head.getMsgBody().get(0).getQcType());//�ʱ�������_PF
//		lmpgjkabvo.setAttributeValue("attrname27", msgxsaght0001head.getMsgBody().get(0).getCertiNum().toString());//�ʱ������
//		lmpgjkabvo.setAttributeValue("attrname28", msgxsaght0001head.getMsgBody().get(0).getPackingCode());//��װ����_PF
//		lmpgjkabvo.setAttributeValue("attrname29", msgxsaght0001head.getMsgBody().get(0).getOrderPriority());//�������ȼ� 
//		lmpgjkabvo.setAttributeValue("attrname30", msgxsaght0001head.getMsgBody().get(0).getMetFeetFlag());//��Ӣ�Ʊ�־
//		lmpgjkabvo.setAttributeValue("attrname31", msgxsaght0001head.getMsgBody().get(0).getOrderThick().toString());//������ 
//		lmpgjkabvo.setAttributeValue("attrname32", msgxsaght0001head.getMsgBody().get(0).getOrderWidthPf().toString());//������
//		lmpgjkabvo.setAttributeValue("attrname33", msgxsaght0001head.getMsgBody().get(0).getOrderLenLow().toString());//������������ 
//		lmpgjkabvo.setAttributeValue("attrname34", msgxsaght0001head.getMsgBody().get(0).getOrderLenUp().toString());//������������
//		lmpgjkabvo.setAttributeValue("attrname35", msgxsaght0001head.getMsgBody().get(0).getThickEng());//Ӣ�ƺ��
//		lmpgjkabvo.setAttributeValue("attrname36", msgxsaght0001head.getMsgBody().get(0).getWidthEng());//Ӣ�ƿ���
//		lmpgjkabvo.setAttributeValue("attrname37", msgxsaght0001head.getMsgBody().get(0).getLengthMinEng());//Ӣ�Ƴ�������
//		lmpgjkabvo.setAttributeValue("attrname38", msgxsaght0001head.getMsgBody().get(0).getLengthMaxEng());//Ӣ�Ƴ�������
//		lmpgjkabvo.setAttributeValue("attrname39", msgxsaght0001head.getMsgBody().get(0).getWeightMethod());//���ط�ʽ
//		lmpgjkabvo.setAttributeValue("attrname40", msgxsaght0001head.getMsgBody().get(0).getOrderUnit());//����������λ
//		lmpgjkabvo.setAttributeValue("attrname41", msgxsaght0001head.getMsgBody().get(0).getOrderWtPf().toString());//��������_PF
//		lmpgjkabvo.setAttributeValue("attrname42", msgxsaght0001head.getMsgBody().get(0).getPreAlcaWtPf().toString());//Ԥ��ͬ������_PF
//		lmpgjkabvo.setAttributeValue("attrname43", msgxsaght0001head.getMsgBody().get(0).getOrderPiece().toString());//�������� 
//		lmpgjkabvo.setAttributeValue("attrname44", msgxsaght0001head.getMsgBody().get(0).getUnitWtUpPf().toString());//�����������_PF
//		lmpgjkabvo.setAttributeValue("attrname45", msgxsaght0001head.getMsgBody().get(0).getUnitWtLowPf().toString());//������С����_PF
//		lmpgjkabvo.setAttributeValue("attrname46", msgxsaght0001head.getMsgBody().get(0).getDelivyTolUpper().toString());//��������
//		lmpgjkabvo.setAttributeValue("attrname47", msgxsaght0001head.getMsgBody().get(0).getDelivyTolLower().toString());//�������� 
//		lmpgjkabvo.setAttributeValue("attrname48", msgxsaght0001head.getMsgBody().get(0).getCoilInsideDimPf().toString());//�ھ�_PF
//		lmpgjkabvo.setAttributeValue("attrname49", msgxsaght0001head.getMsgBody().get(0).getShortSizeRatePf().toString());//�����̳���_PF
//		lmpgjkabvo.setAttributeValue("attrname50", msgxsaght0001head.getMsgBody().get(0).getShortSizeLowPf().toString());//�����̳�����_PF
//		lmpgjkabvo.setAttributeValue("attrname51", msgxsaght0001head.getMsgBody().get(0).getShortSizeUpPf().toString());//�����̳�����_PF
//		lmpgjkabvo.setAttributeValue("attrname52", msgxsaght0001head.getMsgBody().get(0).getShortSize1().toString());//�̳߳���1 
//		lmpgjkabvo.setAttributeValue("attrname53", msgxsaght0001head.getMsgBody().get(0).getShortSize2().toString());//�̳߳���2 
//		lmpgjkabvo.setAttributeValue("attrname54", msgxsaght0001head.getMsgBody().get(0).getShortSize3().toString());//�̳߳���3 
//		lmpgjkabvo.setAttributeValue("attrname55", msgxsaght0001head.getMsgBody().get(0).getShortSize4().toString());//�̳߳���4 
//		lmpgjkabvo.setStatus(VOStatus.NEW);
//		
//		lmpgjkabvos[0]=lmpgjkabvo;
//		
//		LmpgjkbBVO[] lmpgjkbbvos = new LmpgjkbBVO[1];
//		LmpgjkbBVO lmpgjkbbvo = new LmpgjkbBVO();
//		lmpgjkbbvo.setAttributeValue("attrname0", msgxsaght0001head.getMsgBody().get(0).getShortSize5().toString());//�̳߳���5
//		lmpgjkbbvo.setAttributeValue("attrname1", msgxsaght0001head.getMsgBody().get(0).getDeliveryDateChr());//Լ����������
//		lmpgjkbbvo.setAttributeValue("attrname2", msgxsaght0001head.getMsgBody().get(0).getDelivyPeriodPf());//�����ڱ�_PF
//		lmpgjkbbvo.setAttributeValue("attrname3", msgxsaght0001head.getMsgBody().get(0).getDeliveryWeekMark());//���ܽ�����־
//		lmpgjkbbvo.setAttributeValue("attrname4", msgxsaght0001head.getMsgBody().get(0).getOrderReadyDate());//������������ 
//		lmpgjkbbvo.setAttributeValue("attrname5", msgxsaght0001head.getMsgBody().get(0).getLaunchTime());//�·�ʱ�� 
//		lmpgjkbbvo.setAttributeValue("attrname6", msgxsaght0001head.getMsgBody().get(0).getLaunchId());//�·��˹���
//		lmpgjkbbvo.setAttributeValue("attrname7", msgxsaght0001head.getMsgBody().get(0).getLaunchFirstTime());//�����·�ʱ�� 
//		lmpgjkbbvo.setAttributeValue("attrname8", msgxsaght0001head.getMsgBody().get(0).getLaunchFirstId());//�����·��˹���
//		lmpgjkbbvo.setAttributeValue("attrname9", msgxsaght0001head.getMsgBody().get(0).getVersionNumPf().toString());//�汾��_PF
//		lmpgjkbbvo.setAttributeValue("attrname10", msgxsaght0001head.getMsgBody().get(0).getOrderPriceMode());//��ͬ�Ƽ۷�ʽ 
//		lmpgjkbbvo.setAttributeValue("attrname11", msgxsaght0001head.getMsgBody().get(0).getPriceBaseDate());//�۸�ִ�л�׼��
//		lmpgjkbbvo.setAttributeValue("attrname12", msgxsaght0001head.getMsgBody().get(0).getSaleUnitPrice().toString());//���� ���� 
//		lmpgjkbbvo.setAttributeValue("attrname13", msgxsaght0001head.getMsgBody().get(0).getAgreemtCode());//Э��۸񵥺�
//		lmpgjkbbvo.setAttributeValue("attrname14", msgxsaght0001head.getMsgBody().get(0).getAgreePrice().toString());//Э��۸� 
//		lmpgjkbbvo.setAttributeValue("attrname15", msgxsaght0001head.getMsgBody().get(0).getOrderAmtPf().toString());//�������_PF
//		lmpgjkbbvo.setAttributeValue("attrname16", msgxsaght0001head.getMsgBody().get(0).getSaleRemark());//���۱�ע
//		lmpgjkbbvo.setAttributeValue("attrname17", msgxsaght0001head.getMsgBody().get(0).getA_flag());//A���־  
//		lmpgjkbbvo.setAttributeValue("attrname18", msgxsaght0001head.getMsgBody().get(0).getUserSpecDesc());//�û���������Ҫ��
//		lmpgjkbbvo.setAttributeValue("attrname19", msgxsaght0001head.getMsgBody().get(0).getDrewMode());//��Ʊ��ʽ
//		lmpgjkbbvo.setAttributeValue("attrname20", msgxsaght0001head.getMsgBody().get(0).getDepositN().toString());//Ѻ����
//		lmpgjkbbvo.setAttributeValue("attrname21", msgxsaght0001head.getMsgBody().get(0).getDepositAmtPf().toString());//Ѻ����_PF
//		lmpgjkbbvo.setAttributeValue("attrname22", msgxsaght0001head.getMsgBody().get(0).getTransType());//�������� 
//		lmpgjkbbvo.setAttributeValue("attrname23", msgxsaght0001head.getMsgBody().get(0).getTrnpModeCodePf());//���䷽ʽ����_PF
//		lmpgjkbbvo.setAttributeValue("attrname24", msgxsaght0001head.getMsgBody().get(0).getDeliveryPlaceCodePf());//�յ�վ�۴���_PF
//		lmpgjkbbvo.setAttributeValue("attrname25", msgxsaght0001head.getMsgBody().get(0).getDeliveryPlaceName());//�յ�վ������
//		lmpgjkbbvo.setAttributeValue("attrname26", msgxsaght0001head.getMsgBody().get(0).getDeliveryPlaceCode1Pf());//�յ�վ�۴���1_PF
//		lmpgjkbbvo.setAttributeValue("attrname27", msgxsaght0001head.getMsgBody().get(0).getDeliveryPlaceName1());//�յ�վ������1
//		lmpgjkbbvo.setAttributeValue("attrname28", msgxsaght0001head.getMsgBody().get(0).getPrivateRouteCodePf());//ר���ߴ���_PF
//		lmpgjkbbvo.setAttributeValue("attrname29", msgxsaght0001head.getMsgBody().get(0).getPrivateRouteNamePf());//ר��������_PF
//		lmpgjkbbvo.setAttributeValue("attrname30", msgxsaght0001head.getMsgBody().get(0).getTrnpPricePf().toString());//�˷ѵ���_PF
//		lmpgjkbbvo.setAttributeValue("attrname31", msgxsaght0001head.getMsgBody().get(0).getTrnpAmtPf().toString());//�˷ѽ��_PF
//		lmpgjkbbvo.setAttributeValue("attrname32", msgxsaght0001head.getMsgBody().get(0).getGuideMeasureAq());//�Ʋ��ȼ�����ʽ
//		lmpgjkbbvo.setAttributeValue("attrname33", msgxsaght0001head.getMsgBody().get(0).getEdgeType());//�б���̬
//		lmpgjkbbvo.setAttributeValue("attrname34", msgxsaght0001head.getMsgBody().get(0).getOrderTechnicDes());//��ͬ��������������
//		lmpgjkbbvo.setAttributeValue("attrname35", msgxsaght0001head.getMsgBody().get(0).getOrderByprodDegreeCode());//��ͬ����Ʒ�ȼ�����
//		lmpgjkbbvo.setAttributeValue("attrname36", msgxsaght0001head.getMsgBody().get(0).getWeightPlate());//п������
//		lmpgjkbbvo.setAttributeValue("attrname37", msgxsaght0001head.getMsgBody().get(0).getGrainCode());//���ȴ���
//		lmpgjkbbvo.setAttributeValue("attrname38", msgxsaght0001head.getMsgBody().get(0).getPatternSalient().toString());//�Ƹ�
//		lmpgjkbbvo.setAttributeValue("attrname39", msgxsaght0001head.getMsgBody().get(0).getSectionCode());//�������
//		lmpgjkbbvo.setAttributeValue("attrname40", msgxsaght0001head.getMsgBody().get(0).getSectionDesc());//��������
//		lmpgjkbbvo.setAttributeValue("attrname41", msgxsaght0001head.getMsgBody().get(0).getRecognCode());//��Ʒ��ʶ����
//		lmpgjkbbvo.setAttributeValue("attrname42", msgxsaght0001head.getMsgBody().get(0).getEndLineCode());//�յ�վʡ�� 
//		lmpgjkbbvo.setAttributeValue("attrname43", msgxsaght0001head.getMsgBody().get(0).getEndLineName());//�յ�վ������
//		lmpgjkbbvo.setAttributeValue("attrname44", msgxsaght0001head.getMsgBody().get(0).getTransProdCode());//�����Ʒ����
//		lmpgjkbbvo.setAttributeValue("attrname45", msgxsaght0001head.getMsgBody().get(0).getUnloadPoint());//ж���ص�
//		lmpgjkbbvo.setAttributeValue("attrname46", msgxsaght0001head.getMsgBody().get(0).getPriceMode());//����ģʽ
//		lmpgjkbbvo.setAttributeValue("attrname47", msgxsaght0001head.getMsgBody().get(0).getProdCode());//����Ʒ��
//		lmpgjkbbvo.setAttributeValue("attrname48", msgxsaght0001head.getMsgBody().get(0).getStraragemMark());//���̺�����ϵ�ȼ�
//		lmpgjkbbvo.setAttributeValue("attrname49", msgxsaght0001head.getMsgBody().get(0).getPriceModeCname());//����ģʽ���� ����:10
//		lmpgjkbbvo.setAttributeValue("attrname50", msgxsaght0001head.getMsgBody().get(0).getProdCodeCname());//����ģʽ����  ����:30 
//		lmpgjkbbvo.setAttributeValue("attrname51", msgxsaght0001head.getMsgBody().get(0).getStraragemMarkCname());//���̺�����ϵ�ȼ�����
//		lmpgjkbbvo.setAttributeValue("attrname52", msgxsaght0001head.getMsgBody().get(0).getInvoiceType());//Ӫ������Ʊ����
//		lmpgjkbbvo.setAttributeValue("attrname53", msgxsaght0001head.getMsgBody().get(0).getInvoiceTypeName());//��Ʊ����˵�� 
//		lmpgjkbbvo.setAttributeValue("attrname54", msgxsaght0001head.getMsgBody().get(0).getUserChineseName());//��Ʊ������ 
//		lmpgjkbbvo.setAttributeValue("attrname55", msgxsaght0001head.getMsgBody().get(0).getTaxNum());//��˰��ʶ���
//		lmpgjkbbvo.setStatus(VOStatus.NEW);
//		
//		lmpgjkbbvos[0] = lmpgjkbbvo;
//		
//		LmpgjkcBVO[] lmpgjkcbvos = new LmpgjkcBVO[1];
//		LmpgjkcBVO lmpgjkcbvo = new LmpgjkcBVO();
//		lmpgjkcbvo.setAttributeValue("attrname0", msgxsaght0001head.getMsgBody().get(0).getAddressLocationc());//��ַ
//		lmpgjkcbvo.setAttributeValue("attrname1", msgxsaght0001head.getMsgBody().get(0).getBankBranchName());//�������� 
//		lmpgjkcbvo.setAttributeValue("attrname2", msgxsaght0001head.getMsgBody().get(0).getAccountNum());//�˺�
//		lmpgjkcbvo.setAttributeValue("attrname3", msgxsaght0001head.getMsgBody().get(0).getTelNum());//�绰
//		lmpgjkcbvo.setStatus(VOStatus.NEW);
//		
//		lmpgjkcbvos[0] = lmpgjkcbvo;
//		
//		agglmpgjkhvo.setParentVO(lmpgjkHVO);
//		agglmpgjkhvo.setChildren(LmpgjkaBVO.class, lmpgjkabvos);	
//		agglmpgjkhvo.setChildren(LmpgjkbBVO.class, lmpgjkbbvos);	
//		agglmpgjkhvo.setChildren(LmpgjkcBVO.class, lmpgjkcbvos);	
//		return agglmpgjkhvo;
//	}
//
//	
//}
//
=======
/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 ����03:40:25
 */
package nc.impl.pu.m21;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IMsgXSAGHT0001;
import nc.itf.pu.m21.IMsgXSAGHT0001Save;
import nc.itf.uif.pub.IUifService;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.pub.billcode.itf.IBillcodeManage;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.et.m5720.entity.ContractBVO;
import nc.vo.et.m5720.entity.ContractHVO;
import nc.vo.et.m5720.entity.ContractVO;
import nc.vo.et.m5730.entity.DetailBVO;
import nc.vo.et.m5730.entity.DetailCLVO;
import nc.vo.et.m5730.entity.DetailCMSNVO;
import nc.vo.et.m5730.entity.DetailDOCVO;
import nc.vo.et.m5730.entity.DetailFEEVO;
import nc.vo.et.m5730.entity.DetailHVO;
import nc.vo.et.m5730.entity.DetailInfoVO;
import nc.vo.et.m5730.entity.DetailMatchVO;
import nc.vo.et.m5730.entity.DetailPTVO;
import nc.vo.et.m5730.entity.DetailPayVO;
import nc.vo.et.m5730.entity.DetailVO;
import nc.vo.et.m5730.entity.DetailBBBVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.lm.pgdzb.Pgpdtorderrelation;
import nc.vo.lm.pgnxscddxx.AggXsaght0001HVO;
import nc.vo.lm.pgnxscddxx.Xsaght0001HVO;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pf.PfUtilTools;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;


/**
 * 2017-03-06 ��֦�����������ӿڽӿ�ʵ���� 

 */
public class MsgXSAGHT0001Impl implements IBackgroundWorkPlugin,IMsgXSAGHT0001 {
	
	@Override
	public String mXSAGHT0001Head_RequiresNew() {
		// TODO �Զ����ɵķ������	
		try {

			NCObject[] ncobjects = 
					MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(Xsaght0001HVO.class, " msgflag='0' and dr=0", false);
			if(ncobjects==null){
				return "��δ��������";
			}
			for (int i = 0; i < ncobjects.length; i++) {
				AggXsaght0001HVO  aggvo =(AggXsaght0001HVO) ncobjects[i].getContainmentObject();
				this.MsgXSAGHT0001(aggvo);
				 }
		} catch (MetaDataException e1) {
			// TODO �Զ����ɵ� catch ��
			/*Logger.debug("==========================================");
			Logger.error(e1.getMessage(), e1);*/
			e1.printStackTrace();
		}
		  return null;
  }
	
	public String MsgXSAGHT0001(AggXsaght0001HVO aggvo) {
		// TODO �Զ����ɵķ������    erpordernumpf   erporderno
		String expFlag = (String) aggvo.getParentVO().getAttributeValue("expflag");//��ó������ҵ���ʶ
		String erpordernumpf = (String) aggvo.getParentVO().getAttributeValue("erpordernumpf");//erp��ͬ��
		String erporderno = (String) aggvo.getParentVO().getAttributeValue("erporderno");//erp��ͬ�����
		int contordernum =(int) aggvo.getParentVO().getAttributeValue("contordernum");//��Լ��������
		String ordernumpf =(String) aggvo.getParentVO().getAttributeValue("ordernumpf");//����������
		//InvocationInfoProxy.getInstance().setGroupId("0001N610000000000IT0");
			/*
			 * ��óʱ�������ɹ���ͬ�ͷ�����
			 */
		NCObject objsDZB=null;
		DetailVO aggDetailVO = new DetailVO();
		SaleOrderVO aggsaleordervo= new SaleOrderVO();
		OrderVO aggOrderVos = new OrderVO();
		DeliveryVO aggDeliveryVO = new DeliveryVO();
		NCObject[] ncobject = new NCObject[3];
		
		IUifService queryService = NCLocator.getInstance().lookup(IUifService.class);
		
			if(expFlag.equals("N")){				
			    
				try {
			    NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(SaleOrderHVO.class , "dr=0 and csaleorderid = '"+erpordernumpf+"'", false);
			    SaleOrderVO saleordervo = (SaleOrderVO) ncObjects[0].getContainmentObject();
				String id = saleordervo.getParent().getPrimaryKey();
				
				SaleOrderBVO[] saleorderbvo = (SaleOrderBVO[]) queryService.queryByCondition(SaleOrderBVO.class, "csaleorderid = '" + id
						+ "'"+" and csaleorderbid = '"+erporderno+"' and dr=0");
				
				int count = this.findCount(ordernumpf);
                if(contordernum!=count){
                	aggvo.getParent().setAttributeValue("msgflag", "2");
		        	aggvo.getParent().setAttributeValue("msginfo", "��Լ����������ɹ���ͬ��������");
					this.writeBack(aggvo);
                	return "";
                }
				aggsaleordervo.setParent(saleordervo.getParent());
				aggsaleordervo.setChildrenVO(saleorderbvo);
			        }catch (Exception e) {
			        	aggvo.getParent().setAttributeValue("msgflag", "2");
			        	aggvo.getParent().setAttributeValue("msginfo", "��ѯ��ó��ͬ��Ϣʧ��");
						this.writeBack(aggvo);
						return "";
					}
				int lengthOrder = this.FindSaleOrderNo(aggvo,"reqOrder");
				if(lengthOrder==0){
					AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
							aggsaleordervo //�м��
 						    };
					try {
						java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
						aggOrderVos = (OrderVO) PfUtilTools.runChangeData("30-Cxx-pgscdd", "21", srcVosAfterFilter[0]);//30-Cxx-pgscdd�����ε��ݽ������ͣ�21�����ε�������
						aggOrderVos.getParentVO().setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//�ʸ����۶�����
						aggOrderVos.getParentVO().setAttributeValue("productorderno", aggvo.getParent().getAttributeValue("mdordernumpf"));//�ʸ�����������
						//aggOrderVo[0].getParentVO().setAttributeValue("contractno", arg1);//
						aggOrderVos.getChildrenVO()[0].setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//�ʸ����۶�����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("saleorderrowno", aggvo.getParent().getAttributeValue("orderno"));//�ʸ����۶��������
						aggOrderVos.getChildrenVO()[0].setAttributeValue("productorderrowno", aggvo.getParent().getAttributeValue("mdorderno"));//�ʸ��������������
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nastnum", df.format(aggvo.getParent().getAttributeValue("orderwtpf")));//����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nnum", df.format(aggvo.getParent().getAttributeValue("orderwtpf")));//������
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//���Һ�˰����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxnetprice", aggvo.getParent().getAttributeValue("agreeprice"));//���Һ�˰����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtorigtaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//��˰����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtorigtaxnetprc", aggvo.getParent().getAttributeValue("agreeprice"));//��˰����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//����˰����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("csourceid", aggvo.getParent().getAttributeValue("erpordernumpf"));//��Դ����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("csourcebid", aggvo.getParent().getAttributeValue("erporderno"));//��Դ������ϸ
						UFDouble nastnum = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("nastnum");
						UFDouble nqtprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtprice");
						UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
						UFDouble nqtorigtaxprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigtaxprice");
						UFDouble nqttaxnetprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqttaxnetprice");
						//UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
						 
						//df.format(��Ҫ��ʽ��������);
						double nmny = nqtprice.doubleValue()*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nmny", df.format(nmny));//������˰���
						double norigmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("norigmny", df.format(norigmny));//��˰���
						double norigtaxmny =nqtorigtaxprice.doubleValue()*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxmny", df.format(norigtaxmny));//��˰�ϼ�
						double ntaxmny =nqttaxnetprice.doubleValue()*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxmny", df.format(ntaxmny));//���Ҽ�˰�ϼ�
						//double ncalcostmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("ncalcostmny", df.format(norigmny));//�Ƴɱ����
						//===== ���ɵ��ݱ��� ====
						IBillcodeManage codemanage = (IBillcodeManage) NCLocator.getInstance().lookup(IBillcodeManage.class.getName());
						String[] vbillcodes = codemanage.getBatchBillCodesByVOArray("21", aggOrderVos.getParentVO().getAttributeValue("pk_group").toString(), aggOrderVos.getParentVO().getAttributeValue("pk_org").toString(), new Object[]{aggOrderVos.getParent()});
						aggOrderVos.getParent().setAttributeValue("vbillcode", vbillcodes[0]);
						//===== ���ɵ��ݱ��� ====
						VORowNoUtils.setVOsRowNoByRule(new OrderVO[] { aggOrderVos });
						/*objsNPO = NCObject.newInstance(aggOrderVo);//AGGVO����
						ncobject[0]=objsNPO;*/
						//MDPersistenceService.lookupPersistenceService().saveBill(objs); 
					} catch (Exception e) {
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "�����ɹ�������Ϣת��ʧ��");
						this.writeBack(aggvo);
						return "";
					}
				}/*else{
					//��д�м�� 
					aggvo.getParent().setAttributeValue("msgflag", "2");
					aggvo.getParent().setAttributeValue("msginfo", "�����ɹ�������Ϣ�Ѵ���");
					this.writeBack(aggvo);
					return "";
				}*/	
				else{
					OrderVO aggordervo = null;
					int lengthSub = this.FindSubOrderNo(aggvo,"reqOrder");
					if(lengthSub==0){
						String pasorderNumPf = (String) aggvo.getParent().getAttributeValue("ordernumpf");
						String id="";
						try{
							NCObject[] ncObjects1 = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(OrderHeaderVO.class , "po_order.dr=0 and saleorderno = '"+pasorderNumPf+"'", false);
							aggordervo = (OrderVO)ncObjects1[0].getContainmentObject();
							id = aggordervo.getParent().getPrimaryKey();
						} catch(Exception e){
							aggvo.getParent().setAttributeValue("msgflag", "2");
							aggvo.getParent().setAttributeValue("msginfo", "��ѯ�����ɹ������ں�ͬ��Ϊ"+pasorderNumPf+"������Ϣʧ�ܣ�");
							this.writeBack(aggvo);
							return "";
						}
						AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
								aggsaleordervo //�м��
	 						    };
						//OrderVO[] aggordervo = PfServiceScmUtil.executeVOChange("30-Cxx-pgscdd_b", "21", srcVosAfterFilter);
						try {
							java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
							aggOrderVos= (OrderVO) PfUtilTools.runChangeData("30-Cxx-pgscdd_b", "21", srcVosAfterFilter[0]);
							aggOrderVos.setParentVO(aggordervo.getParentVO());
							aggOrderVos.getParent().setStatus(VOStatus.UPDATED);
							aggOrderVos.getChildrenVO()[0].setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//�ʸ����۶�����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("saleorderrowno", aggvo.getParent().getAttributeValue("orderno"));//�ʸ����۶��������
							aggOrderVos.getChildrenVO()[0].setAttributeValue("productorderrowno", aggvo.getParent().getAttributeValue("mdorderno"));//�ʸ��������������
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nastnum", df.format(aggvo.getParent().getAttributeValue("orderwtpf")));//����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nnum", df.format(aggvo.getParent().getAttributeValue("orderwtpf")));//������
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//���Һ�˰����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxnetprice", aggvo.getParent().getAttributeValue("agreeprice"));//���Һ�˰����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtorigtaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//��˰����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtorigtaxnetprc", aggvo.getParent().getAttributeValue("agreeprice"));//��˰����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//����˰����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("csourceid", aggvo.getParent().getAttributeValue("erpordernumpf"));//��Դ����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("csourcebid", aggvo.getParent().getAttributeValue("erporderno"));//��Դ������ϸ
							aggOrderVos.getChildrenVO()[0].setAttributeValue("pk_order", aggOrderVos.getParent().getAttributeValue("pk_order"));//�ϲ㵥������
							UFDouble nastnum = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("nastnum");
							UFDouble nqtprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtprice");
							UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
							UFDouble nqtorigtaxprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigtaxprice");
							UFDouble nqttaxnetprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqttaxnetprice");
							//UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
							double nmny = nqtprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nmny", nmny);//������˰���
							double norigmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("norigmny", norigmny);//��˰���
							double norigtaxmny =nqtorigtaxprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxmny", norigtaxmny);//��˰�ϼ�
							double ntaxmny =nqttaxnetprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxmny", ntaxmny);//���Ҽ�˰�ϼ�
							//double ncalcostmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("ncalcostmny", norigmny);//�Ƴɱ����
							aggOrderVos.getChildrenVO()[0].setStatus(VOStatus.NEW);
							VORowNoUtils.setVOsRowNoByRule(new OrderVO[] { aggOrderVos });

                            OrderItemVO[] itemvo  = (OrderItemVO[])queryService.queryByCondition(OrderItemVO.class, "pk_order = '" + id
									+ "' and dr=0");
							
							OrderItemVO[] orderitemvo = new OrderItemVO[itemvo.length+1];
							
							orderitemvo[0] = (OrderItemVO)aggOrderVos.getChildren(OrderItemVO.class)[0];
							for(int i=0;i<itemvo.length;i++){
								orderitemvo[i+1]=itemvo[i];
							}
							
							aggOrderVos.setChildren(OrderItemVO.class, orderitemvo);
							
						} catch (Exception e) {
							//��д�м�� 
							aggvo.getParent().setAttributeValue("msgflag", "2");
							aggvo.getParent().setAttributeValue("msginfo", "�����ɹ�������Ϣת��ʧ��");
							this.writeBack(aggvo);
							return "";
							}
					}else{
						//��д�м�� 
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "�����ɹ�������Ϣ�Ѵ���");
						this.writeBack(aggvo);
						return "";
					}				
				}
				/*
				 * ����������
				 */
				int lengthSend = this.FindSaleOrderNo(aggvo,"sendDetails");
				if(lengthSend==0){
					AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
							aggsaleordervo //�м��
 						    };
					//DeliveryVO[] aggDeliveryVO = PfServiceScmUtil.executeVOChange("5720-Cxx-pgscdd", "4331", srcVosAfterFilter);//30-Cxx-pgscdd�����ε��ݽ������ͣ�4331�����ε�������
					try {
						aggDeliveryVO= (DeliveryVO) PfUtilTools.runChangeData("30-Cxx-pgscdd", "4331", srcVosAfterFilter[0]);
						aggDeliveryVO.getParentVO().setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//�ʸ����۶�����
						aggDeliveryVO.getParentVO().setAttributeValue("productorderno", aggvo.getParent().getAttributeValue("mdordernumpf"));//�ʸ�����������
						aggDeliveryVO.getParent().setStatus(VOStatus.NEW);
						aggDeliveryVO.getChildrenVO()[0].setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//�ʸ����۶�����
						aggDeliveryVO.getChildrenVO()[0].setAttributeValue("saleorderrowno", aggvo.getParent().getAttributeValue("orderno"));//�ʸ����۶��������
						aggDeliveryVO.getChildrenVO()[0].setAttributeValue("productorderrowno", aggvo.getParent().getAttributeValue("mdorderno"));//�ʸ��������������
						aggDeliveryVO.getChildrenVO()[0].setAttributeValue("nastnum", aggvo.getParent().getAttributeValue("orderwtpf"));//����
						aggDeliveryVO.getChildrenVO()[0].setAttributeValue("nnum", aggvo.getParent().getAttributeValue("orderwtpf"));//������
						aggDeliveryVO.getChildrenVO()[0].setStatus(VOStatus.NEW);
						//===== ���ɵ��ݱ��� ====
						IBillcodeManage codemanage = (IBillcodeManage) NCLocator.getInstance().lookup(IBillcodeManage.class.getName());
						String[] vbillcodes = codemanage.getBatchBillCodesByVOArray("4331", aggDeliveryVO.getParentVO().getAttributeValue("pk_group").toString(), aggDeliveryVO.getParentVO().getAttributeValue("pk_org").toString(), new Object[]{aggDeliveryVO.getParent()});
						aggDeliveryVO.getParent().setAttributeValue("vbillcode", vbillcodes[0]);
						//===== ���ɵ��ݱ��� ====
						VORowNoUtils.setVOsRowNoByRule(new DeliveryVO[] { aggDeliveryVO });
						
						//objsNPD = NCObject.newInstance(aggDeliveryVO);//AGGVO����
						//ncobject[1]=objsNPD;
					} catch (Exception e) {
						//��д�м��
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "������ת��ʧ��");
						this.writeBack(aggvo);
						return "";
					}				
				}/*else{
					//��д�м��
					aggvo.getParent().setAttributeValue("msgflag", "2");
					aggvo.getParent().setAttributeValue("msginfo", "��������Ϣ�Ѵ���");
					this.writeBack(aggvo);
					return "";
				}*/
				else{
					//���ɹ�������������ʱ��ֻ��ɹ������ӱ��ڲ�������
					DeliveryVO deliveryvo = new DeliveryVO();
					int lengthSubD = this.FindSubOrderNo(aggvo,"sendDetails");
					if(lengthSubD==0){
						String pasorderNumPf = (String) aggvo.getParent().getAttributeValue("ordernumpf");
						try{
							NCObject[] ncObject = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(DeliveryHVO.class , "dr=0 and saleorderno = '"+pasorderNumPf+"'", false);
							deliveryvo = (DeliveryVO)ncObject[0].getContainmentObject();
						} catch(Exception e){
							//��д�м��
							aggvo.getParent().setAttributeValue("msgflag", "2");
							aggvo.getParent().setAttributeValue("msginfo", "��ѯ���������ʸ����۶�����Ϊ"+pasorderNumPf+"������Ϣʧ�ܣ�");
							this.writeBack(aggvo);
							return "";
						}
						AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
								aggsaleordervo //�м��
	 						    };
						//DeliveryVO[] aggordervo = PfServiceScmUtil.executeVOChange("30-Cxx-pgscdd_b", "4331", srcVosAfterFilter);
						try {
							aggDeliveryVO= (DeliveryVO) PfUtilTools.runChangeData("30-Cxx-pgscdd_b", "4331", srcVosAfterFilter[0]);
							aggDeliveryVO.setParentVO(deliveryvo.getParentVO());
							aggDeliveryVO.getParent().setStatus(VOStatus.UPDATED);
							aggDeliveryVO.getChildrenVO()[0].setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//�ʸ����۶�����
							aggDeliveryVO.getChildrenVO()[0].setAttributeValue("saleorderrowno", aggvo.getParent().getAttributeValue("orderno"));//�ʸ����۶��������
							aggDeliveryVO.getChildrenVO()[0].setAttributeValue("productorderrowno", aggvo.getParent().getAttributeValue("mdorderno"));//�ʸ��������������
							aggDeliveryVO.getChildrenVO()[0].setAttributeValue("nastnum", aggvo.getParent().getAttributeValue("orderwtpf"));//����
							aggDeliveryVO.getChildrenVO()[0].setAttributeValue("nnum", aggvo.getParent().getAttributeValue("orderwtpf"));//������
							aggDeliveryVO.getChildrenVO()[0].setAttributeValue("cdeliveryid", aggDeliveryVO.getParent().getAttributeValue("cdeliveryid"));//�ϲ㵥������
							aggDeliveryVO.getChildrenVO()[0].setStatus(VOStatus.NEW);
							VORowNoUtils.setVOsRowNoByRule(new DeliveryVO[] { aggDeliveryVO });
							//objsNPD= NCObject.newInstance(aggordervo);//AGGVO����
							//ncobject[1]=objsNPD;
						} catch (Exception e) {
							//��д�м��
							aggvo.getParent().setAttributeValue("msgflag", "2");
							aggvo.getParent().setAttributeValue("msginfo", "��������Ϣת��ʧ��");
							this.writeBack(aggvo);
							return "";
							}
					}else{
						//��д�м��
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "��������Ϣ�Ѵ���");
						this.writeBack(aggvo);
						return "";
					}				
				}			
			}
			/*
			 * 
			 */
			if(expFlag.equals("C")){		
				ContractVO contractvoz= new ContractVO();
				try {
			    NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(ContractHVO.class , "dr=0 and pk_contract = '"+erpordernumpf+"'", false);
			    ContractVO contractvo = (ContractVO) ncObjects[0].getContainmentObject();
				String id = contractvo.getParent().getPrimaryKey();
				
				ContractBVO[] contractbvo  = (ContractBVO[])queryService.queryByCondition(ContractBVO.class, "pk_contract = '" + id
						+ "'"+" and pk_contract_b = '"+erporderno+"' and dr=0");
				
				int count = this.findCount(ordernumpf);
				if(contordernum!=count){
					aggvo.getParent().setAttributeValue("msgflag", "2");
		        	aggvo.getParent().setAttributeValue("msginfo", "��Լ������������ں�ͬ��������");
					this.writeBack(aggvo);
					return "";
				}
				contractvoz.setParent(contractvo.getParent());
				contractvoz.setChildrenVO(contractbvo);
			        }catch (Exception e) {
			        	aggvo.getParent().setAttributeValue("msgflag", "2");
			        	aggvo.getParent().setAttributeValue("msginfo", "��ѯ���ں�ͬ��Ϣʧ�ܣ�");
						this.writeBack(aggvo);
						return "";
						
			        }
				int lengthC = this.FindSaleOrderNo(aggvo,"reqOrder");
				if(lengthC==0){
					AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
							contractvoz //�м��
 						    };
					//OrderVO[] aggordervo = PfServiceScmUtil.executeVOChange("5720-Cxx-pgscdd", "21", srcVosAfterFilter);
					try {
						java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
						aggOrderVos= (OrderVO) PfUtilTools.runChangeData("5720-Cxx-pgscdd", "21", srcVosAfterFilter[0]);
						aggOrderVos.getParentVO().setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//�ʸ����۶�����
						aggOrderVos.getParentVO().setAttributeValue("productorderno", aggvo.getParent().getAttributeValue("mdordernumpf"));//�ʸ�����������
						aggOrderVos.getParent().setStatus(VOStatus.NEW);
						aggOrderVos.getChildrenVO()[0].setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//�ʸ����۶�����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("saleorderrowno", aggvo.getParent().getAttributeValue("orderno"));//�ʸ����۶��������
						aggOrderVos.getChildrenVO()[0].setAttributeValue("productorderrowno", aggvo.getParent().getAttributeValue("mdorderno"));//�ʸ��������������
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nastnum", df.format(aggvo.getParent().getAttributeValue("orderwtpf")));//����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nnum", df.format(aggvo.getParent().getAttributeValue("orderwtpf")));//������
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//���Һ�˰����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxnetprice", aggvo.getParent().getAttributeValue("agreeprice"));//���Һ�˰����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtorigtaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//��˰����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtorigtaxnetprc", aggvo.getParent().getAttributeValue("agreeprice"));//��˰����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//����˰����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("csourceid", aggvo.getParent().getAttributeValue("erpordernumpf"));//��Դ����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("csourcebid", aggvo.getParent().getAttributeValue("erporderno"));//��Դ������ϸ
						aggOrderVos.getChildrenVO()[0].setStatus(VOStatus.NEW);
						//nqttaxnetprice=nexchangerate*norigprice       nqtprice=(nqtorigprice*nexchangerate)/1.17
						UFDouble nexchangerate = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("nexchangerate");
						UFDouble norigprice = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("norigprice");
						UFDouble nastnum = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("nastnum");
						//UFDouble nqtprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtprice");
						UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
						UFDouble nqtorigtaxprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigtaxprice");
						
						double nqtprice =(nqtorigprice.doubleValue()*nexchangerate.doubleValue())/1.17;
						double nqttaxnetprice =nexchangerate.doubleValue()*norigprice.doubleValue();
						//UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
						double nmny = nqtprice*nastnum.doubleValue();
						 
						//df.format(��Ҫ��ʽ��������);
						
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nmny", df.format(nmny));//������˰���
						double norigmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("norigmny", df.format(norigmny));//��˰���
						double norigtaxmny =nqtorigtaxprice.doubleValue()*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxmny", df.format(norigtaxmny));//��˰�ϼ�
						double ntaxmny =nqttaxnetprice*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxmny", df.format(ntaxmny));//���Ҽ�˰�ϼ�
						//double ncalcostmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
						aggOrderVos.getChildrenVO()[0].setAttributeValue("ncalcostmny", df.format(norigmny));//�Ƴɱ����
						//===== ���ɵ��ݱ��� ====
						IBillcodeManage codemanage = (IBillcodeManage) NCLocator.getInstance().lookup(IBillcodeManage.class.getName());
						String[] vbillcodes = codemanage.getBatchBillCodesByVOArray("21", aggOrderVos.getParentVO().getAttributeValue("pk_group").toString(), aggOrderVos.getParentVO().getAttributeValue("pk_org").toString(), new Object[]{aggOrderVos.getParent()});
						aggOrderVos.getParent().setAttributeValue("vbillcode", vbillcodes[0]);
						//===== ���ɵ��ݱ��� ====
						VORowNoUtils.setVOsRowNoByRule(new OrderVO[] { aggOrderVos });
						//===================������ʱ����=====================
						/*
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nnetprice", 1.0);//��������˰����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxnetprice", 1.0);//�����Һ�˰����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nprice", 1.0);//��������˰����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxprice", 1.0);//�����Һ�˰����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtnetprice", 1.0);//������˰����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxnetprice", 1.0);//���Һ�˰����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtprice", 1.0);//������˰����
						aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxprice", 1.0);//���Һ�˰����
						*/
						//========================================
						//NCObject objsCPO = NCObject.newInstance(aggordervo);//AGGVO����
						//ncobject[0]=objsCPO;
						//MDPersistenceService.lookupPersistenceService().saveBill(objs); 
					} catch (Exception e) {
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "�����ɹ�������Ϣת��ʧ�ܣ�");
						this.writeBack(aggvo);
						return "";
						}		
				}/*else{
					//��д�м��
					aggvo.getParent().setAttributeValue("msgflag", "2");
					aggvo.getParent().setAttributeValue("msginfo", "�����ɹ�������Ϣ�Ѵ���");
					this.writeBack(aggvo);
					return "";
				}*/
				else{
					OrderVO aggordervo = new OrderVO();
					int lengthSubO = this.FindSubOrderNo(aggvo,"reqOrder");
					if(lengthSubO==0){
						String pasorderNumPf = (String) aggvo.getParent().getAttributeValue("ordernumpf");
						String id ="";
						try{
							NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(OrderHeaderVO.class , "po_order.dr=0 and saleorderno = '"+pasorderNumPf+"' ", false);
							aggordervo = (OrderVO)ncObjects[0].getContainmentObject();
							id = aggordervo.getParent().getPrimaryKey();
						} catch(Exception e){
							aggvo.getParent().setAttributeValue("msgflag", "2");
							aggvo.getParent().setAttributeValue("msginfo", "��ѯ�����ɹ������ں�ͬ��Ϊ"+pasorderNumPf+"������Ϣʧ�ܣ�");
							this.writeBack(aggvo);
							return "";
						}
						AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
								contractvoz //�м��
	 						    };
						//OrderVO[] aggordervo = PfServiceScmUtil.executeVOChange("5720-Cxx-pgscdd_b", "21", srcVosAfterFilter);
						try {
							java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00"); 
							aggOrderVos= (OrderVO) PfUtilTools.runChangeData("5720-Cxx-pgscdd_b", "21", srcVosAfterFilter[0]);
							aggOrderVos.setParentVO(aggordervo.getParentVO());
							aggOrderVos.getParent().setStatus(VOStatus.UPDATED);
							aggOrderVos.getChildrenVO()[0].setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//�ʸ����۶�����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("saleorderrowno", aggvo.getParent().getAttributeValue("orderno"));//�ʸ����۶��������
							aggOrderVos.getChildrenVO()[0].setAttributeValue("productorderrowno", aggvo.getParent().getAttributeValue("mdorderno"));//�ʸ��������������
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nastnum", df.format(aggvo.getParent().getAttributeValue("orderwtpf")));//����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nnum", df.format(aggvo.getParent().getAttributeValue("orderwtpf")));//������
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//���Һ�˰����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxnetprice", aggvo.getParent().getAttributeValue("agreeprice"));//���Һ�˰����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtorigtaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//��˰����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtorigtaxnetprc", aggvo.getParent().getAttributeValue("agreeprice"));//��˰����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxprice", aggvo.getParent().getAttributeValue("agreeprice"));//����˰����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("csourceid", aggvo.getParent().getAttributeValue("erpordernumpf"));//��Դ����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("csourcebid", aggvo.getParent().getAttributeValue("erporderno"));//��Դ������ϸ
							aggOrderVos.getChildrenVO()[0].setAttributeValue("pk_order", aggOrderVos.getParent().getAttributeValue("pk_order"));//�ϲ㵥������
							aggOrderVos.getChildrenVO()[0].setStatus(VOStatus.NEW);
							/*UFDouble nastnum = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("nastnum");
							UFDouble nqtprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtprice");
							UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
							UFDouble nqtorigtaxprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigtaxprice");
							UFDouble nqttaxnetprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqttaxnetprice");
							//UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
							double nmny = nqtprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nmny", nmny);//������˰���
							double norigmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("norigmny", norigmny);//��˰���
							double norigtaxmny =nqtorigtaxprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxmny", norigtaxmny);//��˰�ϼ�
							double ntaxmny =nqttaxnetprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxmny", ntaxmny);//���Ҽ�˰�ϼ�
							//double ncalcostmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("ncalcostmny", norigmny);//�Ƴɱ����*/

							UFDouble nexchangerate = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("nexchangerate");
							UFDouble norigprice = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("norigprice");
							UFDouble nastnum = (UFDouble) aggOrderVos.getChildrenVO()[0].getAttributeValue("nastnum");
							//UFDouble nqtprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtprice");
							UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
							UFDouble nqtorigtaxprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigtaxprice");
							
							double nqtprice =(nqtorigprice.doubleValue()*nexchangerate.doubleValue())/1.17;
							double nqttaxnetprice =nexchangerate.doubleValue()*norigprice.doubleValue();
							//UFDouble nqtorigprice =(UFDouble)aggOrderVos.getChildrenVO()[0].getAttributeValue("nqtorigprice");
							double nmny = nqtprice*nastnum.doubleValue();
							
							//df.format(��Ҫ��ʽ��������);
							
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nmny", df.format(nmny));//������˰���
							double norigmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("norigmny", df.format(norigmny));//��˰���
							double norigtaxmny =nqtorigtaxprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("norigtaxmny", df.format(norigtaxmny));//��˰�ϼ�
							double ntaxmny =nqttaxnetprice*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxmny", df.format(ntaxmny));//���Ҽ�˰�ϼ�
							//double ncalcostmny =nqtorigprice.doubleValue()*nastnum.doubleValue();
							aggOrderVos.getChildrenVO()[0].setAttributeValue("ncalcostmny", df.format(norigmny));//�Ƴɱ����
							VORowNoUtils.setVOsRowNoByRule(new OrderVO[] { aggOrderVos });
							
							OrderItemVO[] itemvo  = (OrderItemVO[])queryService.queryByCondition(OrderItemVO.class, "pk_order = '" + id
									+ "' and dr=0");
							
							OrderItemVO[] orderitemvo = new OrderItemVO[itemvo.length+1];
							
							orderitemvo[0] = (OrderItemVO)aggOrderVos.getChildren(OrderItemVO.class)[0];
							for(int i=0;i<itemvo.length;i++){
								orderitemvo[i+1]=itemvo[i];
							}
							
							aggOrderVos.setChildren(OrderItemVO.class, orderitemvo);
							
							//===================������ʱ����=====================
							/*aggOrderVos.getChildrenVO()[0].setAttributeValue("nnetprice", 1.0);//��������˰����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxnetprice", 1.0);//�����Һ�˰����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nprice", 1.0);//��������˰����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("ntaxprice", 1.0);//�����Һ�˰����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtnetprice", 1.0);//������˰����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxnetprice", 1.0);//���Һ�˰����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqtprice", 1.0);//������˰����
							aggOrderVos.getChildrenVO()[0].setAttributeValue("nqttaxprice", 1.0);//���Һ�˰����*/
							//========================================
							//NCObject objsCCO = NCObject.newInstance(aggordervo);//AGGVO����
							//ncobject[0]=objsCCO;
							//MDPersistenceService.lookupPersistenceService().saveBill(objs);
						} catch (Exception e) {
							aggvo.getParent().setAttributeValue("msgflag", "2");
							aggvo.getParent().setAttributeValue("msginfo", "�����ɹ�������Ϣת��ʧ��");
							this.writeBack(aggvo);
							return "";
							}
					}else{
						//��д�м��
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "�����ɹ�������Ϣ�Ѵ���");
						this.writeBack(aggvo);
						return "";
					}				
				}
				/*
				 * ����������ϸ
				 */
				int lengthDetail = this.FindSaleOrderNo(aggvo,"details");
				if(lengthDetail==0){
					
					AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
							contractvoz //�м��DetailHVO
 						    };
					
					//DetailVO[] aggDetailVO = PfServiceScmUtil.executeVOChange("5720-Cxx-pgscdd", "5730", srcVosAfterFilter);//30-Cxx-pgscdd�����ε��ݽ������ͣ�4331�����ε�������
					try {
						aggDetailVO = (DetailVO) PfUtilTools.runChangeData("5720-Cxx-pgscdd", "5730", srcVosAfterFilter[0]);
						aggDetailVO.getParentVO().setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//�ʸ����۶�����
						aggDetailVO.getParentVO().setAttributeValue("productorderno", aggvo.getParent().getAttributeValue("mdordernumpf"));//�ʸ�����������
						aggDetailVO.getParentVO().setStatus(VOStatus.NEW);
						aggDetailVO.getChildrenVO()[0].setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//�ʸ����۶�����
						aggDetailVO.getChildrenVO()[0].setAttributeValue("saleorderrowno", aggvo.getParent().getAttributeValue("orderno"));//�ʸ����۶��������
						aggDetailVO.getChildrenVO()[0].setAttributeValue("productorderrowno", aggvo.getParent().getAttributeValue("mdorderno"));//�ʸ��������������
						aggDetailVO.getChildrenVO()[0].setAttributeValue("nastnum", aggvo.getParent().getAttributeValue("orderwtpf"));//����
						aggDetailVO.getChildrenVO()[0].setAttributeValue("nnum", aggvo.getParent().getAttributeValue("orderwtpf"));//������
						aggDetailVO.getChildrenVO()[0].setStatus(VOStatus.NEW);//
						
						aggDetailVO.setChildren(DetailCLVO.class, new DetailCLVO[]{});
						aggDetailVO.setChildren(DetailCMSNVO.class, new DetailCMSNVO[]{});
						aggDetailVO.setChildren(DetailDOCVO.class, new DetailDOCVO[]{});
						aggDetailVO.setChildren(DetailFEEVO.class, new DetailFEEVO[]{});
						aggDetailVO.setChildren(DetailInfoVO.class, new DetailInfoVO[]{});
						aggDetailVO.setChildren(DetailMatchVO.class, new DetailMatchVO[]{});
						aggDetailVO.setChildren(DetailPayVO.class, new DetailPayVO[]{});
						
						//===== ���ɵ��ݱ��� ====
						IBillcodeManage codemanage = (IBillcodeManage) NCLocator.getInstance().lookup(IBillcodeManage.class.getName());
						String[] vbillcodes = codemanage.getBatchBillCodesByVOArray("5730", aggDetailVO.getParentVO().getAttributeValue("pk_group").toString(), aggDetailVO.getParentVO().getAttributeValue("pk_org").toString(), new Object[]{aggDetailVO.getParent()});
						aggDetailVO.getParent().setAttributeValue("vbillcode", vbillcodes[0]);
						//===== ���ɵ��ݱ��� ====
						VORowNoUtils.setVOsRowNoByRule(new DetailVO[] { aggDetailVO });

						//NCObject objsCPD = NCObject.newInstance(aggDetailVO);//AGGVO����
						//ncobject[1]=objsCPD;
					} catch (Exception e) {
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "������ϸ��Ϣת��ʧ��");
						this.writeBack(aggvo);
						return "";
						}
					}/*else{
						//��д�м��
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "������ϸ��Ϣ�Ѵ���");
						this.writeBack(aggvo);
						return "";
					}*/
				else{
					//���ɹ�������������ʱ��ֻ��ɹ������ӱ��ڲ�������
					DetailVO detailvo = new DetailVO();
					int lengthSubDetail = this.FindSubOrderNo(aggvo,"details");
					if(lengthSubDetail==0){
						String pasorderNumPf = (String) aggvo.getParent().getAttributeValue("ordernumpf");
						String id="";
						try{
							NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(DetailHVO.class , "dr=0 and saleorderno = '"+pasorderNumPf+"'", false);
							detailvo = (DetailVO)ncObjects[0].getContainmentObject();
							id = detailvo.getParent().getPrimaryKey();
						} catch(Exception e){
							aggvo.getParent().setAttributeValue("msgflag", "2");
							aggvo.getParent().setAttributeValue("msginfo", "��ѯ������ϸ�ж�����Ϊ"+pasorderNumPf+"������Ϣʧ��");
							this.writeBack(aggvo);
							return "";
						}
						AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
								contractvoz //�м��
	 						    };
						//DetailVO[] aggordervo = PfServiceScmUtil.executeVOChange("5720-Cxx-pgscdd_b", "5730", srcVosAfterFilter);
						try {
							aggDetailVO = (DetailVO) PfUtilTools.runChangeData("5720-Cxx-pgscdd_b", "5730", srcVosAfterFilter[0]);
							aggDetailVO.setParentVO(detailvo.getParentVO());
							aggDetailVO.getParent().setStatus(VOStatus.UPDATED);
							aggDetailVO.getChildrenVO()[0].setAttributeValue("saleorderno", aggvo.getParent().getAttributeValue("ordernumpf"));//�ʸ����۶�����
							aggDetailVO.getChildrenVO()[0].setAttributeValue("saleorderrowno", aggvo.getParent().getAttributeValue("orderno"));//�ʸ����۶��������
							aggDetailVO.getChildrenVO()[0].setAttributeValue("productorderrowno", aggvo.getParent().getAttributeValue("mdorderno"));//�ʸ��������������
							aggDetailVO.getChildrenVO()[0].setAttributeValue("nastnum", aggvo.getParent().getAttributeValue("orderwtpf"));//����
							aggDetailVO.getChildrenVO()[0].setAttributeValue("nnum", aggvo.getParent().getAttributeValue("orderwtpf"));//������
							aggDetailVO.getChildrenVO()[0].setAttributeValue("pk_detail", id);//�ϲ㵥������
							aggDetailVO.getChildrenVO()[0].setStatus(VOStatus.NEW);
							

							DetailBVO[] detailbvo  = (DetailBVO[])queryService.queryByCondition(DetailBVO.class, "pk_detail = '" + id
									+ "' and dr=0");
							
							DetailBVO[] detailbvos = new DetailBVO[detailbvo.length+1];
							
							detailbvos[0] = (DetailBVO)aggDetailVO.getChildren(DetailBVO.class)[0];
							for(int i=0;i<detailbvo.length;i++){
								detailbvos[i+1]=detailbvo[i];
							}
							
							aggDetailVO.setChildren(DetailBVO.class, detailbvos);
							VORowNoUtils.setVOsRowNoByRule(new DetailVO[] { aggDetailVO });
							/*DetailCLVO detailclvo=new DetailCLVO();
							detailclvo.setStatus(VOStatus.UNCHANGED);*/
							aggDetailVO.setChildren(DetailCLVO.class, new DetailCLVO[]{});
							aggDetailVO.setChildren(DetailCMSNVO.class, new DetailCMSNVO[]{});
							aggDetailVO.setChildren(DetailDOCVO.class, new DetailDOCVO[]{});
							aggDetailVO.setChildren(DetailFEEVO.class, new DetailFEEVO[]{});
							aggDetailVO.setChildren(DetailInfoVO.class, new DetailInfoVO[]{});
							aggDetailVO.setChildren(DetailMatchVO.class, new DetailMatchVO[]{});
							aggDetailVO.setChildren(DetailPTVO.class, new DetailPTVO[]{});
							aggDetailVO.setChildren(DetailBBBVO.class, new DetailBBBVO[]{});
							aggDetailVO.setChildren(DetailPayVO.class, new DetailPayVO[]{});
							
							
						} catch (Exception e) {
							aggvo.getParent().setAttributeValue("msgflag", "2");
							aggvo.getParent().setAttributeValue("msginfo", "������ϸ��Ϣת��ʧ��");
							this.writeBack(aggvo);
							return "";
						}	
					}else{
						//��д�м��
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "������ϸ��Ϣ�Ѵ���");
						this.writeBack(aggvo);
						return "";
					}
										
				}		
			}
			/**
			 * ���ɶ��ձ�
			 */
			int length = this.FindSaleOrderNo(aggvo, "pgpdtorderrelation");
			if(length==0){
				Pgpdtorderrelation pgpdtorderrelation = new Pgpdtorderrelation();
				
				if(expFlag.equals("N")){
					pgpdtorderrelation.setBusinesstype("30");
					String vbillcode =this.Findvbillcode(aggvo, "reqOrder");
					pgpdtorderrelation.setVbillcode(vbillcode);
				}else if(expFlag.equals("C")){
					pgpdtorderrelation.setBusinesstype("5720");
					String vbillcode =this.Findvbillcode(aggvo, "sendDetails");
					pgpdtorderrelation.setVbillcode(vbillcode);
				}
				try{
				pgpdtorderrelation.setExpflag(expFlag);
				pgpdtorderrelation.setPk_contract(erpordernumpf);
				pgpdtorderrelation.setPk_contract_b(erporderno);
				pgpdtorderrelation.setSaleorderno(aggvo.getParent().getAttributeValue("ordernumpf").toString());
				pgpdtorderrelation.setSaleorderrowno(aggvo.getParent().getAttributeValue("orderno").toString());
				pgpdtorderrelation.setProductorderno(aggvo.getParent().getAttributeValue("mdordernumpf").toString());
				pgpdtorderrelation.setProductorderrowno(aggvo.getParent().getAttributeValue("mdorderno").toString());
				pgpdtorderrelation.setIs_interface(aggvo.getParent().getAttributeValue("directdelivyflag").equals("N")?UFBoolean.FALSE:UFBoolean.TRUE);
				Date date = new Date();  
				UFDateTime systime= new UFDateTime(date.getTime());
				pgpdtorderrelation.setCreationtime(systime);
				pgpdtorderrelation.setStatus(VOStatus.NEW);
				objsDZB = NCObject.newInstance(pgpdtorderrelation);//AGGVO����
				ncobject[2]=objsDZB;
				}catch(Exception e){
					aggvo.getParent().setAttributeValue("msgflag", "2");
					aggvo.getParent().setAttributeValue("msginfo", "���ձ���Ϣת��ʧ��");
					this.writeBack(aggvo);
					return "";
				}
			}else{
				aggvo.getParent().setAttributeValue("msgflag", "2");
				aggvo.getParent().setAttributeValue("msginfo", "���ձ���Ϣ�Ѵ���");
				this.writeBack(aggvo);
				return "";
			}
			
		
			
			String flag="";
					try {//SaleOrderVO aggsaleordervo= new SaleOrderVO();
						IMsgXSAGHT0001Save is = NCLocator.getInstance().lookup(IMsgXSAGHT0001Save.class);
						flag = is.mXSAGHT0001Head_RequiresNew(ncobject,aggOrderVos,aggDeliveryVO,aggDetailVO,aggsaleordervo,expFlag);
					}catch(Exception e){
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", e.getMessage());
						this.writeBack(aggvo);
						return "";
					}
					if(flag.equals("ok")){
						aggvo.getParent().setAttributeValue("msgflag", "1");
						aggvo.getParent().setAttributeValue("msginfo", "�ӿ����ݴ����ɹ�");
					}else{
						aggvo.getParent().setAttributeValue("msgflag", "2");
						aggvo.getParent().setAttributeValue("msginfo", "�ӿ����ݴ���ʧ��");
					}
					this.writeBack(aggvo);
					
			
		
		return "OK";
}
	
	public void writeBack(AggXsaght0001HVO aggvo){
		
			Date date = new Date();  
			UFDateTime systime= new UFDateTime(date.getTime());
			aggvo.getParent().setAttributeValue("modifiedtime", systime);
			aggvo.getParent().setStatus(VOStatus.UPDATED);
			NCObject objs = NCObject.newInstance(aggvo);
			IMsgXSAGHT0001Save is = NCLocator.getInstance().lookup(IMsgXSAGHT0001Save.class);
			is.mXSAGHT0001Head_RequiresNew(objs);
	}
	/**
	 * ���ݶ����Ų�ѯ��Ӧ���������Ƿ��иö���������
	 * Parma msgXSAGHT0001�ӿڴ��ݵĲɹ���������
	 * type��ѯ�������ͣ�reqOrder-�ɹ�������sendDetails-��������details-������ϸ
	 */
	public int FindSaleOrderNo(AggXsaght0001HVO aggvo,String type) {
		//ordernumpf//mdorderno
		BaseDAO baseDao = new BaseDAO();
		String sqlStr = "";
		SQLParameter sp = new SQLParameter();
		if(type.equals("reqOrder")){
			sqlStr = "select count(*) from po_order where saleorderno=? and dr=0";	
			sp.addParam(aggvo.getParent().getAttributeValue("ordernumpf"));
		}else if(type.equals("sendDetails")){
			sqlStr = "select count(*) from so_delivery where saleorderno=? and dr=0";
			sp.addParam(aggvo.getParent().getAttributeValue("ordernumpf"));
		}else if(type.equals("details")){
			sqlStr = "select count(*) from et_detail where saleorderno=? and dr=0";
			sp.addParam(aggvo.getParent().getAttributeValue("ordernumpf"));
		}else if(type.equals("pgpdtorderrelation")){
			sqlStr = "select count(*) from lm_pgpdtorderrelation where saleorderno=? and productorderrowno=? and dr=0";
			sp.addParam(aggvo.getParent().getAttributeValue("ordernumpf"));
			sp.addParam(aggvo.getParent().getAttributeValue("mdorderno"));
		}
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
			ExceptionUtils.wrappBusinessException("ִ��ʧ��!");
		}
		return Integer.parseInt(li);
	}	
	/**
	 * ���ݶ����Ų�ѯ��Ӧ���ӱ����Ƿ��иö���������
	 * Parma msgXSAGHT0001�ӿڴ��ݵĲɹ���������
	 * type��ѯ�������ͣ�reqOrder-�ɹ�������sendDetails-��������details-���� ��ϸ
	 */
	public int FindSubOrderNo(AggXsaght0001HVO aggvo,String type) {
		//String expFlag = msgXSAGHT0001.getExpFlag();//��ó������ҵ���ʶ
		BaseDAO baseDao = new BaseDAO();
		String sqlStr = "";
		SQLParameter sp = new SQLParameter();
		if(type.equals("reqOrder")){
			sqlStr = "select count(*) from po_order_b where productorderrowno=? and dr=0";	
			sp.addParam(aggvo.getParent().getAttributeValue("mdorderno"));
		}else if(type.equals("sendDetails")){
			sqlStr = "select count(*) from so_delivery_b where productorderrowno=? and dr=0";
			sp.addParam(aggvo.getParent().getAttributeValue("mdorderno"));
		}else if(type.equals("details")){
			sqlStr = "select count(*) from et_detail_b where productorderrowno=? and dr=0";
			sp.addParam(aggvo.getParent().getAttributeValue("mdorderno"));
		}
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
			ExceptionUtils.wrappBusinessException("ִ��ʧ��!");
		}
		return Integer.parseInt(li);
	}
	public String Findvbillcode(AggXsaght0001HVO aggvo,String type) {
		//ordernumpf//mdorderno
		BaseDAO baseDao = new BaseDAO();
		String sqlStr = "";
		SQLParameter sp = new SQLParameter();
		if(type.equals("reqOrder")){
			sqlStr = "select vbillcode from so_saleorder where csaleorderid=?";	
			sp.addParam(aggvo.getParent().getAttributeValue("erpordernumpf"));
		}else if(type.equals("sendDetails")){
			sqlStr = "select vbillcode from et_contract where pk_contract=?";
			sp.addParam(aggvo.getParent().getAttributeValue("erpordernumpf"));
		}
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
			ExceptionUtils.wrappBusinessException("ִ��ʧ��!");
		}
		return li;
	}
	

	public int findCount(String ordernumpf) {
		//ordernumpf//mdorderno
		BaseDAO baseDao = new BaseDAO();
		String sqlStr = "select count(*) from msg_xsaght0001 where ordernumpf=? and dr=0";
		SQLParameter sp = new SQLParameter();
		sp.addParam(ordernumpf);
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
			ExceptionUtils.wrappBusinessException("ִ��ʧ��!");
		}
		return Integer.parseInt(li);
	}
	/*
	 * ���ݽӿ��м�㴫�ݵĲ�����ת���ɹ�������Vo
	 */
	/*public OrderHeaderVO TransMainVO(MsgXSAGHT0001 msgXSAGHT0001){		
		OrderHeaderVO orderHeaderVO = new OrderHeaderVO();
		//orderHeaderVO.setAttributeValue("vbillcode", );//�������
		orderHeaderVO.setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//�ʸ����۶�����
		orderHeaderVO.setAttributeValue("productorderno", msgXSAGHT0001.getMdOrderNumPf());//�ʸ�����������
		orderHeaderVO.setAttributeValue("contractno", msgXSAGHT0001.getErpOrderNumPf());//NC��ͬ��
		orderHeaderVO.setAttributeValue("dbilldate", AppContext.getInstance().getServerTime());//��������
//		orderHeaderVO.setAttributeValue("pk_supplier", AppContext.getInstance().getServerTime());//��Ӧ��
//		orderHeaderVO.setAttributeValue("pk_invcsupllier", AppContext.getInstance().getServerTime());//��Ʊ��Ӧ��
		orderHeaderVO.setAttributeValue("billstatus ", "1");//����״̬
//		orderHeaderVO.setAttributeValue("pk_group ", "1");//��������
//		orderHeaderVO.setAttributeValue("pk_org ", "1");//�ɹ���֯�汾��Ϣ
//		orderHeaderVO.setAttributeValue("pk_org_v ", "1");//�ɹ���֯
		return orderHeaderVO;
	}*/
	/*
	 * ���ݽӿ��м�㴫�ݵĲ�����ת���ɹ�������ϸVo
	 */
	/*public OrderItemVO[] TransSubVO(MsgXSAGHT0001 msgXSAGHT0001){		
		OrderItemVO[] orderItemVO = new OrderItemVO[1];
		orderItemVO[0].setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//�ʸ����۶�����
		orderItemVO[0].setAttributeValue("saleorderrowno", msgXSAGHT0001.getOrderNo());//�ʸ����۶��������
		orderItemVO[0].setAttributeValue("productorderrowno", msgXSAGHT0001.getMdOrderNo());//�ʸ��������������
//		orderItemVO[0].setAttributeValue("pk_material", msgXSAGHT0001.getMdOrderNo());//���ϰ汾��Ϣ
		String expFlag = msgXSAGHT0001.getExpFlag();//��ó������ҵ���ʶ
		if(expFlag.equals("N")){
			orderItemVO[0].setAttributeValue("cfirsttypecode ", "30");//Դͷ��������		
		}else{
			orderItemVO[0].setAttributeValue("cfirsttypecode ", "5720");//Դͷ��������	
		}
//		orderItemVO[0].setAttributeValue("cfirstid ", msgXSAGHT0001.getErpOrderNo());//Դͷ����
		orderItemVO[0].setAttributeValue("cfirstbid  ", msgXSAGHT0001.getErpOrderNo());//Դͷ������ϸ
		orderItemVO[0].setAttributeValue("vfirstcode", msgXSAGHT0001.getErpOrderNumPf());//Դͷ���ݺ�
		orderItemVO[0].setAttributeValue("vsourcecode", msgXSAGHT0001.getErpOrderNumPf());//��Դ���ݺ�
		if(expFlag.equals("N")){
			orderItemVO[0].setAttributeValue("csourcetypecode", "30");//Դͷ��������		
		}else{
			orderItemVO[0].setAttributeValue("csourcetypecode", "5720");//Դͷ��������	
		}
//		orderItemVO[0].setAttributeValue("csourcebid ", msgXSAGHT0001.getErpOrderNo());//��Դ����
		orderItemVO[0].setAttributeValue("csourcebid ", msgXSAGHT0001.getErpOrderNo());//��Դ������ϸ
		orderItemVO[0].setAttributeValue("nastnum", msgXSAGHT0001.getContOrderNum());//����
		orderItemVO[0].setAttributeValue("mainamount", msgXSAGHT0001.getContOrderNum());//������
		return orderItemVO;
	}*/	
	/*
	 * ���ݽӿ��м�㴫�ݵĲ�����ת����������Vo
	 */
	/*public DeliveryHVO TransSendMainVO(MsgXSAGHT0001 msgXSAGHT0001){		
		DeliveryHVO deliveryHVO = new DeliveryHVO();		
//		deliveryHVO.setAttributeValue("vbillcode", msgXSAGHT0001.getOrderNumPf());//���ݺ�		
		deliveryHVO.setAttributeValue("dbilldate", AppContext.getInstance().getServerTime());//��������
		deliveryHVO.setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//�ʸ����۶�����
		deliveryHVO.setAttributeValue("productorderno", msgXSAGHT0001.getMdOrderNumPf());//�ʸ�����������
		deliveryHVO.setAttributeValue("contractno", msgXSAGHT0001.getErpOrderNumPf());//NC��ͬ��
		deliveryHVO.setAttributeValue("fstatusflag", "2");//����״̬
//		deliveryHVO.setAttributeValue("pk_group", "2");//����״̬
//		deliveryHVO.setAttributeValue("pk_org", "2");//����״̬
//		deliveryHVO.setAttributeValue("pk_org_v", "2");//����״̬
		return deliveryHVO;
	}*/
	/*
	 * ���ݽӿ��м�㴫�ݵĲ�����ת����������ϸVo
	 */
	/*public DeliveryBVO[] TransSendSubVO(MsgXSAGHT0001 msgXSAGHT0001){		
		DeliveryBVO[] deliveryBVO = new DeliveryBVO[1];
		deliveryBVO[0].setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//�ʸ����۶�����
		deliveryBVO[0].setAttributeValue("saleorderrowno", msgXSAGHT0001.getOrderNo());//�ʸ����۶��������
		deliveryBVO[0].setAttributeValue("productorderrowno", msgXSAGHT0001.getMdOrderNo());//�ʸ��������������
		deliveryBVO[0].setAttributeValue("vsrctype", "30");//Դ��������
		deliveryBVO[0].setAttributeValue("vsourcecode", msgXSAGHT0001.getErpOrderNumPf());//��Դ���ݺ�
		//deliveryBVO[0].setAttributeValue("csrcid", msgXSAGHT0001.getErpOrderNumPf());//��Դ���ݱ�ͷID
		deliveryBVO[0].setAttributeValue("csrcbid", msgXSAGHT0001.getErpOrderNo());//��Դ���ݱ���ID
		deliveryBVO[0].setAttributeValue("vfirstcode", msgXSAGHT0001.getErpOrderNumPf());//Դͷ���ݺ�
		deliveryBVO[0].setAttributeValue("vfirsttype", "30");//Դͷ��������
		deliveryBVO[0].setAttributeValue("cfirstbid", msgXSAGHT0001.getErpOrderNo());//Դͷ���ݱ���ID
//		deliveryBVO[0].setAttributeValue("cfirstid", msgXSAGHT0001.getErpOrderNo());//Դͷ���ݱ�ͷID
		deliveryBVO[0].setAttributeValue("cmaterialid", msgXSAGHT0001.getErpOrderNo());//���ϵ���
		deliveryBVO[0].setAttributeValue("cmaterialvid", msgXSAGHT0001.getErpOrderNo());//���ϱ���
//		deliveryBVO[0].setAttributeValue("cordercustid", msgXSAGHT0001.getErpOrderNo());//�ͻ�����
//		deliveryBVO[0].setAttributeValue("cinvoicecustid", msgXSAGHT0001.getErpOrderNo());//��Ʊ�ͻ�
		deliveryBVO[0].setAttributeValue("csaleorgvid", msgXSAGHT0001.getErpOrderNo());//������֯
		deliveryBVO[0].setAttributeValue("csettleorgid", msgXSAGHT0001.getErpOrderNo());//���������֯���°汾
		deliveryBVO[0].setAttributeValue("csettleorgvid", msgXSAGHT0001.getErpOrderNo());//���������֯
		deliveryBVO[0].setAttributeValue("nastnum", msgXSAGHT0001.getContOrderNum());//����
		deliveryBVO[0].setAttributeValue("nnum", msgXSAGHT0001.getContOrderNum());//������
		return deliveryBVO;
	}*/
	/*
	 * ���ݽӿ��м�㴫�ݵĲ�����ת��������ϸ��Vo
	 */
	/*public DetailHVO TransDetailHVOMainVO(MsgXSAGHT0001 msgXSAGHT0001){		
		DetailHVO detailHVO = new DetailHVO();
		detailHVO.setAttributeValue("dbilldate", AppContext.getInstance().getServerTime());//��������
		detailHVO.setAttributeValue("dmakedate", AppContext.getInstance().getServerTime());//�Ƶ�����
//		detailHVO.setAttributeValue("vbillcode", msgXSAGHT0001.getOrderNumPf());//������ϸ����
		detailHVO.setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//�ʸ����۶�����
		detailHVO.setAttributeValue("productorderno", msgXSAGHT0001.getMdOrderNumPf());//�ʸ�����������
//		detailHVO.setAttributeValue("carorgid", msgXSAGHT0001.getMdOrderNumPf());//Ӧ����֯���°汾
//		detailHVO.setAttributeValue("carorgvid", msgXSAGHT0001.getMdOrderNumPf());//Ӧ����֯
//		detailHVO.setAttributeValue("csettleorgid", msgXSAGHT0001.getMdOrderNumPf());//���������֯���°汾
//		detailHVO.setAttributeValue("csettleorgvid", msgXSAGHT0001.getMdOrderNumPf());//���������֯
//		detailHVO.setAttributeValue("ctradewordid", msgXSAGHT0001.getMdOrderNumPf());//ó������
//		detailHVO.setAttributeValue("ctransporttypeid", msgXSAGHT0001.getMdOrderNumPf());//���䷽ʽ
//		detailHVO.setAttributeValue("ccustomerid", msgXSAGHT0001.getMdOrderNumPf());//�ͻ�
//		detailHVO.setAttributeValue("cinvoicecustid", msgXSAGHT0001.getMdOrderNumPf());//��Ʊ�ͻ�
//		detailHVO.setAttributeValue("cdeptid", msgXSAGHT0001.getMdOrderNumPf());//�������°汾
//		detailHVO.setAttributeValue("cdeptvid", msgXSAGHT0001.getMdOrderNumPf());//����
//		detailHVO.setAttributeValue("cloadportid", msgXSAGHT0001.getMdOrderNumPf());//���˸�
//		detailHVO.setAttributeValue("cdestportid", msgXSAGHT0001.getMdOrderNumPf());//Ŀ�ĸ�
//		detailHVO.setAttributeValue("corigcurrencyid", msgXSAGHT0001.getMdOrderNumPf());//����
//		detailHVO.setAttributeValue("pk_group", msgXSAGHT0001.getMdOrderNumPf());//����
//		detailHVO.setAttributeValue("pk_org", msgXSAGHT0001.getMdOrderNumPf());//������֯
//		detailHVO.setAttributeValue("pk_org_v", msgXSAGHT0001.getMdOrderNumPf());//������֯�汾
		detailHVO.setAttributeValue("fpfstatusflag", "1");//������״̬
		detailHVO.setAttributeValue("fstatusflag", "3");//����״̬
		return detailHVO;
	}*/
	/*
	 * ���ݽӿ��м�㴫�ݵĲ�����ת��������ϸ��Vo
	 */
	/*public DetailBVO[] TransDetailBVOSubVO(MsgXSAGHT0001 msgXSAGHT0001){		
		DetailBVO[] detailBVO = new DetailBVO[1];	
		detailBVO[0].setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//�ʸ����۶�����
		detailBVO[0].setAttributeValue("saleorderrowno", msgXSAGHT0001.getOrderNo());//�ʸ����۶��������
		detailBVO[0].setAttributeValue("productorderrowno", msgXSAGHT0001.getMdOrderNo());//�ʸ��������������		
//		detailBVO[0].setAttributeValue("csrcid", msgXSAGHT0001.getErpOrderNo());//��Դ��������
		detailBVO[0].setAttributeValue("csrcbid", msgXSAGHT0001.getErpOrderNo());//��Դ���ݸ���
		detailBVO[0].setAttributeValue("vsourcecode", msgXSAGHT0001.getErpOrderNumPf());//��Դ���ݺ�
		detailBVO[0].setAttributeValue("vsrctype", "5720");//��Դ��������
//		detailBVO[0].setAttributeValue("cfirstbid", );//Դͷ�����ӱ�
//		detailBVO[0].setAttributeValue("cfirstid", );//Դͷ��������
		detailBVO[0].setAttributeValue("vfirstcode", msgXSAGHT0001.getErpOrderNumPf());//Դͷ���ݺ�
		detailBVO[0].setAttributeValue("vfirsttype", "5720");//��Դ��������
//		detailBVO[0].setAttributeValue("cmaterialid", "5720");//�������°汾
//		detailBVO[0].setAttributeValue("cmaterialvid", "5720");//���ϱ���
		detailBVO[0].setAttributeValue("nastnum", msgXSAGHT0001.getContOrderNum());//����
		detailBVO[0].setAttributeValue("nnum", msgXSAGHT0001.getContOrderNum());//������	
//		detailBVO[0].setAttributeValue("nqtorigprice", msgXSAGHT0001.getContOrderNum());//����
//		detailBVO[0].setAttributeValue("norigprice", msgXSAGHT0001.getContOrderNum());//������	
		return detailBVO;
	}*/
	
	/*
	 * ���ݽӿ��м�㴫�ݵĲ�����ת�����ձ�VO
	 */
	/*public DetailBVO[] TransdzbVOSubVO(MsgXSAGHT0001 msgXSAGHT0001){		
		DetailBVO[] detailBVO = new DetailBVO[1];	
		detailBVO[0].setAttributeValue("saleorderno", msgXSAGHT0001.getOrderNumPf());//�ʸ����۶�����
		detailBVO[0].setAttributeValue("saleorderrowno", msgXSAGHT0001.getOrderNo());//�ʸ����۶��������
		detailBVO[0].setAttributeValue("productorderrowno", msgXSAGHT0001.getMdOrderNo());//�ʸ��������������		
//		detailBVO[0].setAttributeValue("csrcid", msgXSAGHT0001.getErpOrderNo());//��Դ��������
		detailBVO[0].setAttributeValue("csrcbid", msgXSAGHT0001.getErpOrderNo());//��Դ���ݸ���
		detailBVO[0].setAttributeValue("vsourcecode", msgXSAGHT0001.getErpOrderNumPf());//��Դ���ݺ�
		detailBVO[0].setAttributeValue("vsrctype", "5720");//��Դ��������
//		detailBVO[0].setAttributeValue("cfirstbid", );//Դͷ�����ӱ�
//		detailBVO[0].setAttributeValue("cfirstid", );//Դͷ��������
		detailBVO[0].setAttributeValue("vfirstcode", msgXSAGHT0001.getErpOrderNumPf());//Դͷ���ݺ�
		detailBVO[0].setAttributeValue("vfirsttype", "5720");//��Դ��������
//		detailBVO[0].setAttributeValue("cmaterialid", "5720");//�������°汾
//		detailBVO[0].setAttributeValue("cmaterialvid", "5720");//���ϱ���
		detailBVO[0].setAttributeValue("nastnum", msgXSAGHT0001.getContOrderNum());//����
		detailBVO[0].setAttributeValue("nnum", msgXSAGHT0001.getContOrderNum());//������	
//		detailBVO[0].setAttributeValue("nqtorigprice", msgXSAGHT0001.getContOrderNum());//����
//		detailBVO[0].setAttributeValue("norigprice", msgXSAGHT0001.getContOrderNum());//������	
		return detailBVO;
	}*/
	
	/*
	 * ���ݽӿ��м�㴫�ݵĲ�����ת���м��VO
	 */
	/*public AggLmpgjkHVO TranszjbVOMainVO(MsgXSAGHT0001Head msgxsaght0001head,String flag){
		
		AggLmpgjkHVO agglmpgjkhvo = new AggLmpgjkHVO();
		
		LmpgjkHVO lmpgjkHVO = new LmpgjkHVO();	
		lmpgjkHVO.setAttributeValue("dwid", msgxsaght0001head.getMsgId());//����ID
		lmpgjkHVO.setAttributeValue("sendtype", "1");//�������� 1.����
		lmpgjkHVO.setAttributeValue("interfacetype", "2");//�ӿ����� 2.�ʸֻ������۶���
		lmpgjkHVO.setAttributeValue("flag", flag);//������ʶ 1.�ɹ� 2.ʧ��
		lmpgjkHVO.setAttributeValue("receivetime", AppContext.getInstance().getServerTime());//����ʱ�� 
		lmpgjkHVO.setAttributeValue("attrname0", msgxsaght0001head.getMsgBody().get(0).getOrderNumPf());//�ʸ����۶�����
		lmpgjkHVO.setAttributeValue("attrname1", msgxsaght0001head.getMsgBody().get(0).getMdOrderNumPf());//��϶�����
		lmpgjkHVO.setAttributeValue("attrname2", msgxsaght0001head.getMsgBody().get(0).getErpOrderNumPf());//ERP��ͬ��
		lmpgjkHVO.setAttributeValue("attrname3", msgxsaght0001head.getMsgBody().get(0).getOrderStatusPf());//����״̬_PF
		lmpgjkHVO.setAttributeValue("attrname4", msgxsaght0001head.getMsgBody().get(0).getOrderCertNo());//����ƾ��
		lmpgjkHVO.setAttributeValue("attrname5", msgxsaght0001head.getMsgBody().get(0).getOrderTypeCode());//�������ʴ���
		lmpgjkHVO.setAttributeValue("attrname6", msgxsaght0001head.getMsgBody().get(0).getExpFlag());//��������־ 
		lmpgjkHVO.setAttributeValue("attrname7", msgxsaght0001head.getMsgBody().get(0).getOrderPeriodPf());//�����ڱ�_PF
		lmpgjkHVO.setAttributeValue("attrname8", msgxsaght0001head.getMsgBody().get(0).getResoPeriodPf());//��Դ�ڱ�_PF
		lmpgjkHVO.setAttributeValue("attrname9", msgxsaght0001head.getMsgBody().get(0).getSaleNetworkPf());//��������
		lmpgjkHVO.setAttributeValue("attrname10", msgxsaght0001head.getMsgBody().get(0).getProdClassCode());//��Ʒ�������
		lmpgjkHVO.setAttributeValue("attrname11", msgxsaght0001head.getMsgBody().get(0).getCompanyCodePf());//��˾��_PF
		lmpgjkHVO.setAttributeValue("attrname12", msgxsaght0001head.getMsgBody().get(0).getTradeMode());//ó�׷�ʽ
		lmpgjkHVO.setAttributeValue("attrname13", msgxsaght0001head.getMsgBody().get(0).getSettleMode());//���㷽ʽ����
		lmpgjkHVO.setAttributeValue("attrname14", msgxsaght0001head.getMsgBody().get(0).getSignSitePf());//��Լ�ص����_PF
		lmpgjkHVO.setAttributeValue("attrname15", msgxsaght0001head.getMsgBody().get(0).getPriceFormulaMode());//�Ƽ۷�ʽ
		lmpgjkHVO.setAttributeValue("attrname16", msgxsaght0001head.getMsgBody().get(0).getRmaNoPf());//�����_PF
		lmpgjkHVO.setAttributeValue("attrname17", msgxsaght0001head.getMsgBody().get(0).getContInDate());//��Լ¼������
		lmpgjkHVO.setAttributeValue("attrname18", msgxsaght0001head.getMsgBody().get(0).getContInPerson());//��Լ¼����
		lmpgjkHVO.setAttributeValue("attrname19", msgxsaght0001head.getMsgBody().get(0).getOrderModiMark());//��������б�־
		lmpgjkHVO.setAttributeValue("attrname20", msgxsaght0001head.getMsgBody().get(0).getOrderUserCodePf());//�����û�����_PF
		lmpgjkHVO.setAttributeValue("attrname21", msgxsaght0001head.getMsgBody().get(0).getOrderUserCnamePf());//�����û���������_PF
		lmpgjkHVO.setAttributeValue("attrname22", null == msgxsaght0001head.getMsgBody().get(0).getOrderUserAddrNum()? "":msgxsaght0001head.getMsgBody().get(0).getOrderUserAddrNum().toString());//�����û���ַ�� 
		lmpgjkHVO.setAttributeValue("attrname23", msgxsaght0001head.getMsgBody().get(0).getOrderUserAddrNamePf());//�����û���ַ_PF
		lmpgjkHVO.setAttributeValue("attrname24", msgxsaght0001head.getMsgBody().get(0).getSettleUserNum());//�跽�û�����
		lmpgjkHVO.setAttributeValue("attrname25", msgxsaght0001head.getMsgBody().get(0).getSettleUserNamePf());//�跽�û�����_PF
		lmpgjkHVO.setAttributeValue("attrname26", null == msgxsaght0001head.getMsgBody().get(0).getSettUserAddrNum()? "":msgxsaght0001head.getMsgBody().get(0).getSettUserAddrNum().toString());//�跽�û���ַ��
		lmpgjkHVO.setAttributeValue("attrname27", msgxsaght0001head.getMsgBody().get(0).getSettUserAddrPf());//�跽�û���ַ_PF
		lmpgjkHVO.setAttributeValue("attrname28", msgxsaght0001head.getMsgBody().get(0).getSettUserAcctNum().toString());//�跽�û��ʻ����
		lmpgjkHVO.setAttributeValue("attrname29", msgxsaght0001head.getMsgBody().get(0).getSettUserAcctPf());//�跽�û��ʻ�_PF
		lmpgjkHVO.setAttributeValue("attrname30", msgxsaght0001head.getMsgBody().get(0).getContOrderNum().toString());//��Լ�������� 
		lmpgjkHVO.setAttributeValue("attrname31", msgxsaght0001head.getMsgBody().get(0).getContTotWt().toString());//��Լ������
		lmpgjkHVO.setAttributeValue("attrname32", msgxsaght0001head.getMsgBody().get(0).getContTotAmt().toString());//��Լ�ܽ�� 
		lmpgjkHVO.setAttributeValue("attrname33", msgxsaght0001head.getMsgBody().get(0).getContTrnpTotAmt().toString());//��Լ�˷��ܽ�� 
		lmpgjkHVO.setAttributeValue("attrname34", msgxsaght0001head.getMsgBody().get(0).getContDepositNum().toString());//��ԼѺ����
		lmpgjkHVO.setAttributeValue("attrname35", msgxsaght0001head.getMsgBody().get(0).getContDepositAmt().toString());//��ԼѺ���� 
		lmpgjkHVO.setAttributeValue("attrname36", msgxsaght0001head.getMsgBody().get(0).getContPrepayRate().toString());//��Լ������� 
		lmpgjkHVO.setAttributeValue("attrname37", msgxsaght0001head.getMsgBody().get(0).getContPrepayLackAmt().toString());//��ԼӦ�ն����
		lmpgjkHVO.setAttributeValue("attrname38", msgxsaght0001head.getMsgBody().get(0).getTaxRatePf().toString());//˰��_PF 
		lmpgjkHVO.setAttributeValue("attrname39", msgxsaght0001head.getMsgBody().get(0).getOrderNo());//��ͬ��
		lmpgjkHVO.setAttributeValue("attrname40", msgxsaght0001head.getMsgBody().get(0).getMdOrderNo());//��϶�������� 
		lmpgjkHVO.setAttributeValue("attrname41", msgxsaght0001head.getMsgBody().get(0).getErpOrderNo());//ERP��ͬ�����
		lmpgjkHVO.setAttributeValue("attrname42", msgxsaght0001head.getMsgBody().get(0).getOrderModiNum().toString());//��ͬ�������
		lmpgjkHVO.setAttributeValue("attrname43", msgxsaght0001head.getMsgBody().get(0).getOrderLastSeqNo());//��ͬ�������
		lmpgjkHVO.setAttributeValue("attrname44", msgxsaght0001head.getMsgBody().get(0).getSaleId());//����ҵ��Ա����
		lmpgjkHVO.setAttributeValue("attrname45", msgxsaght0001head.getMsgBody().get(0).getRecCreateTime());//��¼����ʱ��  
		lmpgjkHVO.setAttributeValue("attrname46", msgxsaght0001head.getMsgBody().get(0).getPartOrderFlag());//�ײö�����־
		lmpgjkHVO.setAttributeValue("attrname47", msgxsaght0001head.getMsgBody().get(0).getCutNum().toString());//�������� 
		lmpgjkHVO.setAttributeValue("attrname48", msgxsaght0001head.getMsgBody().get(0).getOrderItemStatusCode());//���۶�������״̬
		lmpgjkHVO.setAttributeValue("attrname49", msgxsaght0001head.getMsgBody().get(0).getOrderItemModiMark());//�����������б��
		lmpgjkHVO.setAttributeValue("attrname50", msgxsaght0001head.getMsgBody().get(0).getModiCause());//���ԭ�� 
		lmpgjkHVO.setStatus(VOStatus.NEW);
		
		LmpgjkaBVO[] lmpgjkabvos = new LmpgjkaBVO[1];
		LmpgjkaBVO lmpgjkabvo = new LmpgjkaBVO();
		lmpgjkabvo.setAttributeValue("attrname0", msgxsaght0001head.getMsgBody().get(0).getModiDate());//�������
		lmpgjkabvo.setAttributeValue("attrname1", msgxsaght0001head.getMsgBody().get(0).getModiOperPerson());//��ͬ���������
		lmpgjkabvo.setAttributeValue("attrname2", msgxsaght0001head.getMsgBody().get(0).getModiType());//������� 
		lmpgjkabvo.setAttributeValue("attrname3", msgxsaght0001head.getMsgBody().get(0).getFinUserNum());//�����û�����
		lmpgjkabvo.setAttributeValue("attrname4", msgxsaght0001head.getMsgBody().get(0).getFinUserName());//�����û�����
		lmpgjkabvo.setAttributeValue("attrname5", msgxsaght0001head.getMsgBody().get(0).getFinUserTrade());//�����û�����ҵ����
		lmpgjkabvo.setAttributeValue("attrname6", msgxsaght0001head.getMsgBody().get(0).getUserCredit());//�û�������
		lmpgjkabvo.setAttributeValue("attrname7", msgxsaght0001head.getMsgBody().get(0).getConsignNum());//�ջ��û�����
		lmpgjkabvo.setAttributeValue("attrname8", msgxsaght0001head.getMsgBody().get(0).getConsignNamePf());//�ջ��û�����_PF
		lmpgjkabvo.setAttributeValue("attrname9", msgxsaght0001head.getMsgBody().get(0).getCnsgAddressNum().toString());//�ջ��û���ַ��
		lmpgjkabvo.setAttributeValue("attrname10", msgxsaght0001head.getMsgBody().get(0).getCnsgAddressPf());//�ջ��û���ַ_PF
		lmpgjkabvo.setAttributeValue("attrname11", msgxsaght0001head.getMsgBody().get(0).getConsignNum1());//�ջ��û�����1
		lmpgjkabvo.setAttributeValue("attrname12", msgxsaght0001head.getMsgBody().get(0).getConsignName1Pf());//�ջ��û�����1_PF
		lmpgjkabvo.setAttributeValue("attrname13", msgxsaght0001head.getMsgBody().get(0).getCnsgAddressNum1().toString());//�ջ��û���ַ��1
		lmpgjkabvo.setAttributeValue("attrname14", msgxsaght0001head.getMsgBody().get(0).getCnsgAddress1Pf());//�ջ��û���ַ1_PF
		lmpgjkabvo.setAttributeValue("attrname15", msgxsaght0001head.getMsgBody().get(0).getTrnpTitleCode());//�˷ѷ�Ʊ̧ͷ����
		lmpgjkabvo.setAttributeValue("attrname16", msgxsaght0001head.getMsgBody().get(0).getTrnpTitleText());//�˷ѷ�Ʊ̧ͷ
		lmpgjkabvo.setAttributeValue("attrname17", msgxsaght0001head.getMsgBody().get(0).getPsr());//��Ʒ�淶�� 
		lmpgjkabvo.setAttributeValue("attrname18", msgxsaght0001head.getMsgBody().get(0).getApn());//������; 
		lmpgjkabvo.setAttributeValue("attrname19", msgxsaght0001head.getMsgBody().get(0).getMsc());//ұ��淶�� 
		lmpgjkabvo.setAttributeValue("attrname20", msgxsaght0001head.getMsgBody().get(0).getNewProductFlag());//���Բ�Ʒ��־
		lmpgjkabvo.setAttributeValue("attrname21", msgxsaght0001head.getMsgBody().get(0).getProductDscr());//��Ʒ����
		lmpgjkabvo.setAttributeValue("attrname22", msgxsaght0001head.getMsgBody().get(0).getShopsignPf());//�ƺ�_PF
		lmpgjkabvo.setAttributeValue("attrname23", msgxsaght0001head.getMsgBody().get(0).getSteelStd());//��Ʒ��׼
		lmpgjkabvo.setAttributeValue("attrname24", msgxsaght0001head.getMsgBody().get(0).getCapabYnFlag());//�����Ƿ�Ҫ���־ 
		lmpgjkabvo.setAttributeValue("attrname25", msgxsaght0001head.getMsgBody().get(0).getRainCoatFlag());//�Ӹ��겼��־
		lmpgjkabvo.setAttributeValue("attrname26", msgxsaght0001head.getMsgBody().get(0).getQcType());//�ʱ�������_PF
		lmpgjkabvo.setAttributeValue("attrname27", msgxsaght0001head.getMsgBody().get(0).getCertiNum().toString());//�ʱ������
		lmpgjkabvo.setAttributeValue("attrname28", msgxsaght0001head.getMsgBody().get(0).getPackingCode());//��װ����_PF
		lmpgjkabvo.setAttributeValue("attrname29", msgxsaght0001head.getMsgBody().get(0).getOrderPriority());//�������ȼ� 
		lmpgjkabvo.setAttributeValue("attrname30", msgxsaght0001head.getMsgBody().get(0).getMetFeetFlag());//��Ӣ�Ʊ�־
		lmpgjkabvo.setAttributeValue("attrname31", msgxsaght0001head.getMsgBody().get(0).getOrderThick().toString());//������ 
		lmpgjkabvo.setAttributeValue("attrname32", msgxsaght0001head.getMsgBody().get(0).getOrderWidthPf().toString());//������
		lmpgjkabvo.setAttributeValue("attrname33", msgxsaght0001head.getMsgBody().get(0).getOrderLenLow().toString());//������������ 
		lmpgjkabvo.setAttributeValue("attrname34", msgxsaght0001head.getMsgBody().get(0).getOrderLenUp().toString());//������������
		lmpgjkabvo.setAttributeValue("attrname35", msgxsaght0001head.getMsgBody().get(0).getThickEng());//Ӣ�ƺ��
		lmpgjkabvo.setAttributeValue("attrname36", msgxsaght0001head.getMsgBody().get(0).getWidthEng());//Ӣ�ƿ���
		lmpgjkabvo.setAttributeValue("attrname37", msgxsaght0001head.getMsgBody().get(0).getLengthMinEng());//Ӣ�Ƴ�������
		lmpgjkabvo.setAttributeValue("attrname38", msgxsaght0001head.getMsgBody().get(0).getLengthMaxEng());//Ӣ�Ƴ�������
		lmpgjkabvo.setAttributeValue("attrname39", msgxsaght0001head.getMsgBody().get(0).getWeightMethod());//���ط�ʽ
		lmpgjkabvo.setAttributeValue("attrname40", msgxsaght0001head.getMsgBody().get(0).getOrderUnit());//����������λ
		lmpgjkabvo.setAttributeValue("attrname41", msgxsaght0001head.getMsgBody().get(0).getOrderWtPf().toString());//��������_PF
		lmpgjkabvo.setAttributeValue("attrname42", msgxsaght0001head.getMsgBody().get(0).getPreAlcaWtPf().toString());//Ԥ��ͬ������_PF
		lmpgjkabvo.setAttributeValue("attrname43", msgxsaght0001head.getMsgBody().get(0).getOrderPiece().toString());//�������� 
		lmpgjkabvo.setAttributeValue("attrname44", msgxsaght0001head.getMsgBody().get(0).getUnitWtUpPf().toString());//�����������_PF
		lmpgjkabvo.setAttributeValue("attrname45", msgxsaght0001head.getMsgBody().get(0).getUnitWtLowPf().toString());//������С����_PF
		lmpgjkabvo.setAttributeValue("attrname46", msgxsaght0001head.getMsgBody().get(0).getDelivyTolUpper().toString());//��������
		lmpgjkabvo.setAttributeValue("attrname47", msgxsaght0001head.getMsgBody().get(0).getDelivyTolLower().toString());//�������� 
		//lmpgjkabvo.setAttributeValue("attrname48", msgxsaght0001head.getMsgBody().get(0).getCoilInsideDimPf().toString());//�ھ�_PF
		lmpgjkabvo.setAttributeValue("attrname48",0);//�ھ�_PF
		lmpgjkabvo.setAttributeValue("attrname49", msgxsaght0001head.getMsgBody().get(0).getShortSizeRatePf().toString());//�����̳���_PF
		lmpgjkabvo.setAttributeValue("attrname50", msgxsaght0001head.getMsgBody().get(0).getShortSizeLowPf().toString());//�����̳�����_PF
		lmpgjkabvo.setAttributeValue("attrname51", msgxsaght0001head.getMsgBody().get(0).getShortSizeUpPf().toString());//�����̳�����_PF
		lmpgjkabvo.setAttributeValue("attrname52", msgxsaght0001head.getMsgBody().get(0).getShortSize1().toString());//�̳߳���1 
		lmpgjkabvo.setAttributeValue("attrname53", msgxsaght0001head.getMsgBody().get(0).getShortSize2().toString());//�̳߳���2 
		lmpgjkabvo.setAttributeValue("attrname54", msgxsaght0001head.getMsgBody().get(0).getShortSize3().toString());//�̳߳���3 
		lmpgjkabvo.setAttributeValue("attrname55", msgxsaght0001head.getMsgBody().get(0).getShortSize4().toString());//�̳߳���4 
		lmpgjkabvo.setStatus(VOStatus.NEW);
		
		lmpgjkabvos[0]=lmpgjkabvo;
		
		LmpgjkbBVO[] lmpgjkbbvos = new LmpgjkbBVO[1];
		LmpgjkbBVO lmpgjkbbvo = new LmpgjkbBVO();
		lmpgjkbbvo.setAttributeValue("attrname0", msgxsaght0001head.getMsgBody().get(0).getShortSize5().toString());//�̳߳���5
		lmpgjkbbvo.setAttributeValue("attrname1", msgxsaght0001head.getMsgBody().get(0).getDeliveryDateChr());//Լ����������
		lmpgjkbbvo.setAttributeValue("attrname2", msgxsaght0001head.getMsgBody().get(0).getDelivyPeriodPf());//�����ڱ�_PF
		lmpgjkbbvo.setAttributeValue("attrname3", msgxsaght0001head.getMsgBody().get(0).getDeliveryWeekMark());//���ܽ�����־
		lmpgjkbbvo.setAttributeValue("attrname4", msgxsaght0001head.getMsgBody().get(0).getOrderReadyDate());//������������ 
		lmpgjkbbvo.setAttributeValue("attrname5", msgxsaght0001head.getMsgBody().get(0).getLaunchTime());//�·�ʱ�� 
		lmpgjkbbvo.setAttributeValue("attrname6", msgxsaght0001head.getMsgBody().get(0).getLaunchId());//�·��˹���
		lmpgjkbbvo.setAttributeValue("attrname7", msgxsaght0001head.getMsgBody().get(0).getLaunchFirstTime());//�����·�ʱ�� 
		lmpgjkbbvo.setAttributeValue("attrname8", msgxsaght0001head.getMsgBody().get(0).getLaunchFirstId());//�����·��˹���
		lmpgjkbbvo.setAttributeValue("attrname9", msgxsaght0001head.getMsgBody().get(0).getVersionNumPf().toString());//�汾��_PF
		lmpgjkbbvo.setAttributeValue("attrname10", msgxsaght0001head.getMsgBody().get(0).getOrderPriceMode());//��ͬ�Ƽ۷�ʽ 
		lmpgjkbbvo.setAttributeValue("attrname11", msgxsaght0001head.getMsgBody().get(0).getPriceBaseDate());//�۸�ִ�л�׼��
		lmpgjkbbvo.setAttributeValue("attrname12", msgxsaght0001head.getMsgBody().get(0).getSaleUnitPrice().toString());//���� ���� 
		lmpgjkbbvo.setAttributeValue("attrname13", msgxsaght0001head.getMsgBody().get(0).getAgreemtCode());//Э��۸񵥺�
		lmpgjkbbvo.setAttributeValue("attrname14", msgxsaght0001head.getMsgBody().get(0).getAgreePrice().toString());//Э��۸� 
		lmpgjkbbvo.setAttributeValue("attrname15", msgxsaght0001head.getMsgBody().get(0).getOrderAmtPf().toString());//�������_PF
		lmpgjkbbvo.setAttributeValue("attrname16", msgxsaght0001head.getMsgBody().get(0).getSaleRemark());//���۱�ע
		lmpgjkbbvo.setAttributeValue("attrname17", msgxsaght0001head.getMsgBody().get(0).getA_flag());//A���־  
		lmpgjkbbvo.setAttributeValue("attrname18", msgxsaght0001head.getMsgBody().get(0).getUserSpecDesc());//�û���������Ҫ��
		lmpgjkbbvo.setAttributeValue("attrname19", msgxsaght0001head.getMsgBody().get(0).getDrewMode());//��Ʊ��ʽ
		lmpgjkbbvo.setAttributeValue("attrname20", msgxsaght0001head.getMsgBody().get(0).getDepositN().toString());//Ѻ����
		lmpgjkbbvo.setAttributeValue("attrname21", msgxsaght0001head.getMsgBody().get(0).getDepositAmtPf().toString());//Ѻ����_PF
		lmpgjkbbvo.setAttributeValue("attrname22", msgxsaght0001head.getMsgBody().get(0).getTransType());//�������� 
		lmpgjkbbvo.setAttributeValue("attrname23", msgxsaght0001head.getMsgBody().get(0).getTrnpModeCodePf());//���䷽ʽ����_PF
		lmpgjkbbvo.setAttributeValue("attrname24", msgxsaght0001head.getMsgBody().get(0).getDeliveryPlaceCodePf());//�յ�վ�۴���_PF
		lmpgjkbbvo.setAttributeValue("attrname25", msgxsaght0001head.getMsgBody().get(0).getDeliveryPlaceName());//�յ�վ������
		lmpgjkbbvo.setAttributeValue("attrname26", msgxsaght0001head.getMsgBody().get(0).getDeliveryPlaceCode1Pf());//�յ�վ�۴���1_PF
		lmpgjkbbvo.setAttributeValue("attrname27", msgxsaght0001head.getMsgBody().get(0).getDeliveryPlaceName1());//�յ�վ������1
		lmpgjkbbvo.setAttributeValue("attrname28", msgxsaght0001head.getMsgBody().get(0).getPrivateRouteCodePf());//ר���ߴ���_PF
		lmpgjkbbvo.setAttributeValue("attrname29", msgxsaght0001head.getMsgBody().get(0).getPrivateRouteNamePf());//ר��������_PF
		lmpgjkbbvo.setAttributeValue("attrname30", msgxsaght0001head.getMsgBody().get(0).getTrnpPricePf().toString());//�˷ѵ���_PF
		lmpgjkbbvo.setAttributeValue("attrname31", msgxsaght0001head.getMsgBody().get(0).getTrnpAmtPf().toString());//�˷ѽ��_PF
		lmpgjkbbvo.setAttributeValue("attrname32", msgxsaght0001head.getMsgBody().get(0).getGuideMeasureAq());//�Ʋ��ȼ�����ʽ
		lmpgjkbbvo.setAttributeValue("attrname33", msgxsaght0001head.getMsgBody().get(0).getEdgeType());//�б���̬
		lmpgjkbbvo.setAttributeValue("attrname34", msgxsaght0001head.getMsgBody().get(0).getOrderTechnicDes());//��ͬ��������������
		lmpgjkbbvo.setAttributeValue("attrname35", msgxsaght0001head.getMsgBody().get(0).getOrderByprodDegreeCode());//��ͬ����Ʒ�ȼ�����
		lmpgjkbbvo.setAttributeValue("attrname36", msgxsaght0001head.getMsgBody().get(0).getWeightPlate());//п������
		lmpgjkbbvo.setAttributeValue("attrname37", msgxsaght0001head.getMsgBody().get(0).getGrainCode());//���ȴ���
		lmpgjkbbvo.setAttributeValue("attrname38", msgxsaght0001head.getMsgBody().get(0).getPatternSalient().toString());//�Ƹ�
		lmpgjkbbvo.setAttributeValue("attrname39", msgxsaght0001head.getMsgBody().get(0).getSectionCode());//�������
		lmpgjkbbvo.setAttributeValue("attrname40", msgxsaght0001head.getMsgBody().get(0).getSectionDesc());//��������
		lmpgjkbbvo.setAttributeValue("attrname41", msgxsaght0001head.getMsgBody().get(0).getRecognCode());//��Ʒ��ʶ����
		lmpgjkbbvo.setAttributeValue("attrname42", msgxsaght0001head.getMsgBody().get(0).getEndLineCode());//�յ�վʡ�� 
		lmpgjkbbvo.setAttributeValue("attrname43", msgxsaght0001head.getMsgBody().get(0).getEndLineName());//�յ�վ������
		lmpgjkbbvo.setAttributeValue("attrname44", msgxsaght0001head.getMsgBody().get(0).getTransProdCode());//�����Ʒ����
		lmpgjkbbvo.setAttributeValue("attrname45", msgxsaght0001head.getMsgBody().get(0).getUnloadPoint());//ж���ص�
		lmpgjkbbvo.setAttributeValue("attrname46", msgxsaght0001head.getMsgBody().get(0).getPriceMode());//����ģʽ
		lmpgjkbbvo.setAttributeValue("attrname47", msgxsaght0001head.getMsgBody().get(0).getProdCode());//����Ʒ��
		lmpgjkbbvo.setAttributeValue("attrname48", msgxsaght0001head.getMsgBody().get(0).getStraragemMark());//���̺�����ϵ�ȼ�
		lmpgjkbbvo.setAttributeValue("attrname49", msgxsaght0001head.getMsgBody().get(0).getPriceModeCname());//����ģʽ���� ����:10
		lmpgjkbbvo.setAttributeValue("attrname50", msgxsaght0001head.getMsgBody().get(0).getProdCodeCname());//����ģʽ����  ����:30 
		lmpgjkbbvo.setAttributeValue("attrname51", msgxsaght0001head.getMsgBody().get(0).getStraragemMarkCname());//���̺�����ϵ�ȼ�����
		lmpgjkbbvo.setAttributeValue("attrname52", msgxsaght0001head.getMsgBody().get(0).getInvoiceType());//Ӫ������Ʊ����
		lmpgjkbbvo.setAttributeValue("attrname53", msgxsaght0001head.getMsgBody().get(0).getInvoiceTypeName());//��Ʊ����˵�� 
		lmpgjkbbvo.setAttributeValue("attrname54", msgxsaght0001head.getMsgBody().get(0).getUserChineseName());//��Ʊ������ 
		lmpgjkbbvo.setAttributeValue("attrname55", msgxsaght0001head.getMsgBody().get(0).getTaxNum());//��˰��ʶ���
		lmpgjkbbvo.setStatus(VOStatus.NEW);
		
		lmpgjkbbvos[0] = lmpgjkbbvo;
		
		LmpgjkcBVO[] lmpgjkcbvos = new LmpgjkcBVO[1];
		LmpgjkcBVO lmpgjkcbvo = new LmpgjkcBVO();
		lmpgjkcbvo.setAttributeValue("attrname0", msgxsaght0001head.getMsgBody().get(0).getAddressLocationc());//��ַ
		lmpgjkcbvo.setAttributeValue("attrname1", msgxsaght0001head.getMsgBody().get(0).getBankBranchName());//�������� 
		lmpgjkcbvo.setAttributeValue("attrname2", msgxsaght0001head.getMsgBody().get(0).getAccountNum());//�˺�
		lmpgjkcbvo.setAttributeValue("attrname3", msgxsaght0001head.getMsgBody().get(0).getTelNum());//�绰
		lmpgjkcbvo.setStatus(VOStatus.NEW);
		
		lmpgjkcbvos[0] = lmpgjkcbvo;
		
		agglmpgjkhvo.setParentVO(lmpgjkHVO);
		agglmpgjkhvo.setChildren(LmpgjkaBVO.class, lmpgjkabvos);	
		agglmpgjkhvo.setChildren(LmpgjkbBVO.class, lmpgjkbbvos);	
		agglmpgjkhvo.setChildren(LmpgjkcBVO.class, lmpgjkcbvos);	
		return agglmpgjkhvo;
	}*/

	@Override
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {
		this.mXSAGHT0001Head_RequiresNew();
		// TODO �Զ����ɵķ������
		return null;
	}
	
	/*public void loadBodyData(int row) {
		try {
			// �������ID
			//��ѯ�ӱ�VO���飬�м�����VO,��д����VO���飬queryByCondition(vo�������ӱ����+��������)
			//ѯ�۵��ӱ�vo
			XjdzbBodyVO[] xjdzbBodyVO = (XjdzbBodyVO[]) HYPubBO_Client
					.queryByCondition(XjdzbBodyVO.class, "PK_WLXJD = '" + id
							+ "'"+" and dr=0");
			//ѯ�۵���װ�嵥vo
			WlxjdBzqdVO[] wlxjdBzqdVO = (WlxjdBzqdVO[]) HYPubBO_Client
					.queryByCondition(WlxjdBzqdVO.class, "PK_WLXJD = '" + id
							+ "'"+" and dr=0");
			getbillListPanel().getBillListData().setBodyValueVO("id_xjdzb", xjdzbBodyVO);
			getbillListPanel().getBillListData().setBodyValueVO("id_wlxjdbzqd", wlxjdBzqdVO);
			getbillListPanel().getBodyBillModel().execLoadFormula();
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
		}
	}
*/
	
}

>>>>>>> .r4829