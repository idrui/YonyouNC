package nc.pubitf.pu.m20.invp.inv9;

import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

/**
 * ���ƻ���ʽ�����빺��
 * 
 * @version 6.3
 * @author zhangyhh
 * @time 2014-04-29  15:00:37
 */
public interface IPushSave20ForInv9 {

  PraybillVO[] pushSaveBills(PraybillVO[] praybills) throws BusinessException;
}
