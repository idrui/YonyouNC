package nc.pubitf.pu.m422x.pu.m20;

import nc.vo.pu.m422x.entity.WriteBack422XVO;
import nc.vo.pub.BusinessException;

/**
 * �����������뵥Ϊ�빺���ṩ�Ļ�д����ӿ�
 * 
 * @since 6.0
 * @version 2012-5-30 ����03:36:58
 * @author lixyp
 */
public interface IReWrite422xFor20 {

  /**
   * �빺����д�ۼ��빺��������
   * 
   * @param paraVos ��дvo
   * @throws BusinessException
   */
  void backWriteNum(WriteBack422XVO[] paraVos) throws BusinessException;
}
