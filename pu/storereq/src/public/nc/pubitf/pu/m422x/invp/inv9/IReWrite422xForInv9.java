package nc.pubitf.pu.m422x.invp.inv9;

import java.util.Map;

import nc.vo.pu.m422x.entity.WriteBack422XForInv9ParamVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * �����������뵥Ϊ ���ƻ� �ṩ�Ļ�д�ӿ�
 * 
 * @since 6.3
 * @version 2014-4-29 ����03:18:00
 * @author zhangyhh
 */
public interface IReWrite422xForInv9 {

  /**
   * ���ƽ����д�����������뵥�ӿ�
   * 
   * @param WriteBack422XVO[]
   * @throws BusinessException
   */
  void reWriteReq(WriteBack422XForInv9ParamVO[] param) throws BusinessException;

  /**
   * ȡ�����ƽ����д�����������뵥�ӿ�--���������Ϣ
   * 
   * @param pk_reqline
   * @throws BusinessException
   */
  void reWriteReqForClear(WriteBack422XForInv9ParamVO[] vos)
      throws BusinessException;

  /**
   * ɾ���빺����д��Ӧ�����������뵥�����ӿ�
   * 
   * @param pk_line
   * @param billtype ��������
   * @throws BusinessException
   */
  void reWriteReqForDelete(Map<String, UFDouble> returnNums, String billtype)
      throws BusinessException;

}
