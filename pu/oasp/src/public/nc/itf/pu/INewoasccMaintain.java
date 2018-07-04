package nc.itf.pu;

import nc.itf.pubapp.pub.smart.ISmartService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pu.qst.newoascc.NewoasccHeadVO;

public interface INewoasccMaintain extends ISmartService{

	 public NewoasccHeadVO[] query(IQueryScheme queryScheme)
      throws BusinessException, Exception;
}