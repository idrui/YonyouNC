package nc.ui.pu.qst.newoaspwh.action;

import java.awt.event.ActionEvent;

import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.pubapp.uif2app.actions.pflow.ScriptPFlowAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.query2.model.ModelDataManager;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pp.m28.entity.PriceAuditHeaderVO;
import nc.vo.pp.m28.entity.PriceAuditVO;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.cgfa.Cgfa;
import nc.vo.pu.qst.newoaspwh.AggNewoaspwhaHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;


/**
 * @author adminstrator
 *
 *取消结算按钮
 */
public class CancelOaBzAction extends ScriptPFlowAction {

	private ModelDataManager mdManager;
	
	public CancelOaBzAction() {
		super.setBtnName("清除发送OA审批标识");
		this.setCode("CancelOaBz");
	}
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
        
		BillManageModel model = (BillManageModel)getModel();
		ShowUpableBillForm myeditor = (ShowUpableBillForm)editor;	

		Object[]  obj =  this.getModel().getSelectedOperaDatas();
		
		if (null == obj ) {
			ExceptionUtils.wrappBusinessException("请选择数据！"); 
			return;
		}
		
		for(int j=0 ; j<obj.length; j++){
			AggNewoaspwhaHeadVO aggVo = (AggNewoaspwhaHeadVO)obj[j];
			
			if("1".equals(aggVo.getParent().getAttributeValue("splx"))){//方案审批
				if(aggVo.getParent().getAttributeValue("spfah")==null) return;
				String fahString=aggVo.getParent().getAttributeValue("spfah").toString();
				AggCgfa[] CgfaTempVos=getCgfaVOByCond(fahString);
				if(CgfaTempVos !=null){
					AggCgfa cgfaVo=getCgfaVOByPk(CgfaTempVos[0].getPrimaryKey());
					cgfaVo.getParent().setAttributeValue("hdef1", "0");
					cgfaVo.getParent().setStatus(VOStatus.UPDATED);
					AggCgfa[] cgfaVos=new AggCgfa[]{cgfaVo};
					try {
						NCObject[] objs = new NCObject[cgfaVos.length];
						for(int i = 0; i<cgfaVos.length; i++){
							objs[i] = NCObject.newInstance(cgfaVos[i]);
						}
						MDPersistenceService.lookupPersistenceService().saveBill(objs);
			
					} catch (BusinessException ee) {
						throw new BusinessRuntimeException(ee.getMessage(), ee);
					}
				}
			}else  if("2".equals(aggVo.getParent().getAttributeValue("splx"))){//价格审批
				if(aggVo.getParent().getAttributeValue("spfah")==null) return;
				String fahString=aggVo.getParent().getAttributeValue("spfah").toString();
				PriceAuditVO[] PriceTempVos=getPriceVOByCond(fahString);
				if(PriceTempVos !=null){
					PriceAuditVO PriceVo=getPriceVOByPk(PriceTempVos[0].getPrimaryKey());
					PriceVo.getParent().setAttributeValue("vdef16", "0");
					PriceVo.getParent().setStatus(VOStatus.UPDATED);
					PriceAuditVO[] priceVos=new PriceAuditVO[]{PriceVo};
					try {
						NCObject[] objs = new NCObject[priceVos.length];
						for(int i = 0; i<priceVos.length; i++){
							objs[i] = NCObject.newInstance(priceVos[i]);
						}
						MDPersistenceService.lookupPersistenceService().saveBill(objs);
			
					} catch (BusinessException eer1) {
						throw new BusinessRuntimeException(eer1.getMessage(), eer1);
					}
				}
			}
			
			//清空本单据主表采购方案号清空
			aggVo.getParent().setAttributeValue("spfah", null);
			aggVo.getParent().setAttributeValue("spfamc", null);
			aggVo.getParent().setStatus(VOStatus.UPDATED);
			AggNewoaspwhaHeadVO[] aggVos=new AggNewoaspwhaHeadVO[]{aggVo};
			try {
				NCObject[] objs = new NCObject[aggVos.length];
				for(int i = 0; i<aggVos.length; i++){
					objs[i] = NCObject.newInstance(aggVos[i]);
				}
				MDPersistenceService.lookupPersistenceService().saveBill(objs);
			} catch (BusinessException ee) {
				throw new BusinessRuntimeException(ee.getMessage(), ee);
			}
			
		}
			
			//刷新
		mdManager.refresh();
		ShowStatusBarMsgUtil.showStatusBarMsg("操作完成!", getModel().getContext());

	}



	
	@Override
	protected boolean isActionEnable() {
		boolean isEnable = super.isActionEnable();
		AggNewoaspwhaHeadVO aggVO=(AggNewoaspwhaHeadVO) model.getSelectedData();
		if (isEnable && this.getModel().getSelectedData() != null) {
			return true;
		}
		return false;
	}

	
	public ModelDataManager getMdManager() {
		return mdManager;
	}


	public void setMdManager(ModelDataManager mdManager) {
		this.mdManager = mdManager;
	}
	

	
	private IMDPersistenceQueryService getMDQueryService() {
		return MDPersistenceService.lookupPersistenceQueryService();
	}
	
	private AggCgfa[] getCgfaVOByCond(String billno) {
		String sql;

		sql=" PU_CGFA.dr =0 and billno = '" + billno + "'";
		
		try {
			NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(Cgfa.class, sql, false);
			if (ncObjects == null) { return null; }
			AggCgfa[] aggCgfaVOs = new AggCgfa[ncObjects.length];
			for (int i = 0; i < ncObjects.length; i++) {
				aggCgfaVOs[i] = (AggCgfa) ncObjects[i].getContainmentObject();
			}
			return aggCgfaVOs;
		} catch (MetaDataException e) {
			throw new BusinessRuntimeException(e.getMessage(), e);
		}
	}
	
	private AggCgfa getCgfaVOByPk(String pk_cgfa) {

		try {
			NCObject ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByPKWithDR(Cgfa.class, pk_cgfa, true);
			if (ncObjects == null) { return null; }
			AggCgfa CgfaHeadVOs= (AggCgfa) ncObjects.getContainmentObject();
			return CgfaHeadVOs;
		} catch (MetaDataException e) {
			throw new BusinessRuntimeException(e.getMessage(), e);
		}
	}
	
	private PriceAuditVO[] getPriceVOByCond(String billno) {
		String sql;

		sql=" PURP_PRICEAUDIT.dr =0 and vbillcode = '" + billno + "'";
		
		try {
			NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(PriceAuditHeaderVO.class, sql, false);
			if (ncObjects == null) { return null; }
			PriceAuditVO[] aggPriceVOs = new PriceAuditVO[ncObjects.length];
			for (int i = 0; i < ncObjects.length; i++) {
				aggPriceVOs[i] = (PriceAuditVO) ncObjects[i].getContainmentObject();
			}
			return aggPriceVOs;
		} catch (MetaDataException e) {
			throw new BusinessRuntimeException(e.getMessage(), e);
		}
	}
	
	private PriceAuditVO getPriceVOByPk(String pk_priceaudit) {

		try {
			NCObject ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByPKWithDR(PriceAuditHeaderVO.class, pk_priceaudit, true);
			if (ncObjects == null) { return null; }
			PriceAuditVO PriceHeadVOs= (PriceAuditVO) ncObjects.getContainmentObject();
			return PriceHeadVOs;
		} catch (MetaDataException e) {
			throw new BusinessRuntimeException(e.getMessage(), e);
		}
	}

}
