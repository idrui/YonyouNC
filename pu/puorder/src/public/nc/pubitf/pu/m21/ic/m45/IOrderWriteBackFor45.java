package nc.pubitf.pu.m21.ic.m45;

import nc.vo.pub.BusinessException;

public interface IOrderWriteBackFor45 {

  void writeBackFor45(IOrderWriteBackPara[] wbVos) throws BusinessException;

}
