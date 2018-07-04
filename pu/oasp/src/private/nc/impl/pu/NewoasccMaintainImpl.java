package nc.impl.pu;

import nc.impl.pub.ace.AceNewoasccPubServiceImpl;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.vo.bd.meta.BatchOperateVO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pu.qst.newoascc.NewoasccHeadVO;
import nc.itf.pu.INewoasccMaintain;

public class NewoasccMaintainImpl extends AceNewoasccPubServiceImpl
		implements INewoasccMaintain {

	@Override
	public NewoasccHeadVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybasedoc(queryScheme);
	}

	@Override
	public BatchOperateVO batchSave(BatchOperateVO batchVO) throws BusinessException {
		BatchSaveAction<NewoasccHeadVO> saveAction = new BatchSaveAction<NewoasccHeadVO>();
		Object[] nvo = batchVO.getUpdObjs();
		if(nvo != null){
			for(int i = 0; i<nvo.length; i++){
				((NewoasccHeadVO)nvo[i]).setAttributeValue("modifier", AppContext.getInstance().getPkUser());
				((NewoasccHeadVO)nvo[i]).setAttributeValue("modifiedtime", AppContext.getInstance().getServerTime());
			}
			batchVO.setUpdObjs(nvo);
		}
		Object[] nvi = batchVO.getAddObjs();
		if(nvi != null){
			for(int i = 0; i<nvi.length; i++){
				((NewoasccHeadVO)nvi[i]).setAttributeValue("creator", AppContext.getInstance().getPkUser());
				((NewoasccHeadVO)nvi[i]).setAttributeValue("creationtime", AppContext.getInstance().getServerTime());
			}
			batchVO.setAddObjs(nvi);
		}
		BatchOperateVO retData = saveAction.batchSave(batchVO);
		return retData;
	}
}
