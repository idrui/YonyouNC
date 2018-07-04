package nc.pubitf.pu.mpp;

import java.awt.Container;

import nc.vo.pub.BusinessException;
import nc.vo.tb.obj.NtbParamVO;

public interface ILinkQueryForMpp {
  /**
   * ִ��������
   * 
   * @param param �������
   * @param parent ����Ĳ�������
   * @throws BusinessException
   */
  void execLinkQueryForMpp(NtbParamVO param, Container parent)
      throws BusinessException;

  /**
   * Ԥռ������
   * 
   * @param param �������
   * @param parent ����Ĳ�������
   * @throws BusinessException
   */
  void readyLinkQueryForMpp(NtbParamVO param, Container parent)
      throws BusinessException;
}
