package nc.pubitf.pu.m422x.invp.inv9;

import nc.vo.pub.BusinessException;

/**
 * ���ƻ�ɾ�������������뵥�ӿ�
 * 
 * @author zhangyhh
 * @since 6.30
 * @time 2014-04-29 15:37:23
 * 
 */
public interface IDelete422xForInv9 {

  /**
   * ȡ��ƽ���ɾ�����������������뵥�ӿ�
   * @param pk_reqLine
   * @throws BusinessException
   */
  void deleteReq(String[] pk_reqLine) throws BusinessException;

}
