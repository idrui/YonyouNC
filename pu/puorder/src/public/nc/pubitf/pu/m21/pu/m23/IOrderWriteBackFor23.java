package nc.pubitf.pu.m21.pu.m23;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

public interface IOrderWriteBackFor23 {

  public void writeBackFor23(IOrderWriteBackParaFor23[] wbVos,
      UFBoolean isUserConfirm) throws BusinessException;
}
