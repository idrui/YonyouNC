package nc.pubitf.pu.tbb;

import java.awt.Container;

import nc.vo.pub.BusinessException;
import nc.vo.tb.dailyexe.NtbLinkQueryData;

/**
 * �ɹ�����ȡ������ɹ�����
 * 
 * @since 6.0
 * @version 2011-6-22 ����03:08:49
 * @author wuxla
 */

public interface IPULinkQueryForTbb {
  /**
   * �ɹ�����ȡ������ɹ�����
   * 
   * @param data ȡ������
   * @param parent ȡ���Ĳ�������
   * @throws BusinessException
   */

  void linkQueryForTbb(NtbLinkQueryData data, Container parent)
      throws BusinessException;
}
