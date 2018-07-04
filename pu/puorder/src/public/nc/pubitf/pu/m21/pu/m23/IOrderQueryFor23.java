package nc.pubitf.pu.m21.pu.m23;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

public interface IOrderQueryFor23 {

  OrderVO[] queryFor23(IQueryScheme queryScheme) throws BusinessException;

  OrderVO[] queryFor23(String condition, UFBoolean isLazy)
      throws BusinessException;

  OrderVO[] queryFor23QuickArrive(String condition) throws BusinessException;

  OrderVO[] queryFor23Return(IQueryScheme queryScheme) throws BusinessException;
}
