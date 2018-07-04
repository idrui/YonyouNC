package nc.itf.pu;

import nc.itf.pubapp.pub.smart.ISmartService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pu.newoaryxx.NewoaryxxHeadVO;

public interface INewoaryxxMaintain extends ISmartService{

	 public NewoaryxxHeadVO[] query(IQueryScheme queryScheme)
      throws BusinessException, Exception;
}