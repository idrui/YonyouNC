package nc.pubitf.pu.m422x.ewm.m4b32;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;

public interface IQuery422XFor4b32 {

	  public StoreReqAppVO[] queryStoreReqAppsFor4b32(IQueryScheme queryScheme)
		      throws BusinessException ;
	
}
