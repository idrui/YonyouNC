package nc.impl.pu;

import nc.impl.pub.ace.AceNewoaryxxPubServiceImpl;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.vo.bd.meta.BatchOperateVO;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pu.newoaryxx.NewoaryxxHeadVO;
import nc.vo.pu.qst.newoascc.NewoasccHeadVO;
import nc.itf.pu.INewoaryxxMaintain;

public class NewoaryxxMaintainImpl extends AceNewoaryxxPubServiceImpl
		implements INewoaryxxMaintain {

	@Override
	public NewoaryxxHeadVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybasedoc(queryScheme);
	}

	@Override
	public BatchOperateVO batchSave(BatchOperateVO batchVO) throws BusinessException {
		BatchSaveAction<NewoaryxxHeadVO> saveAction = new BatchSaveAction<NewoaryxxHeadVO>();
		Object[] nvo = batchVO.getUpdObjs();
		if(nvo != null){
			for(int i = 0; i<nvo.length; i++){
				((NewoaryxxHeadVO)nvo[i]).setAttributeValue("modifier", AppContext.getInstance().getPkUser());
				((NewoaryxxHeadVO)nvo[i]).setAttributeValue("modifiedtime", AppContext.getInstance().getServerTime());
			}
			batchVO.setUpdObjs(nvo);
		}
		Object[] nvi = batchVO.getAddObjs();
		if(nvi != null){
			for(int i = 0; i<nvi.length; i++){
				((NewoaryxxHeadVO)nvi[i]).setAttributeValue("creator", AppContext.getInstance().getPkUser());
				((NewoaryxxHeadVO)nvi[i]).setAttributeValue("creationtime", AppContext.getInstance().getServerTime());
			}
			batchVO.setAddObjs(nvi);
		}
		BatchOperateVO retData = saveAction.batchSave(batchVO);
		return retData;
	}
}
