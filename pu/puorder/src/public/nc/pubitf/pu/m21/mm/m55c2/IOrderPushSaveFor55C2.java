package nc.pubitf.pu.m21.mm.m55c2;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmf.sourcing.entity.SourceReturnVO;

/**
 * Ϊ��ɢ�����ṩ�Ľӿڣ������������ܷ�����ʽ�����ɹ�����
 * 
 * @since 6.0
 * @version 2012-10-26 ����10:37:20
 * @author liuyand
 */
public interface IOrderPushSaveFor55C2 {

  /**
   * �����������ܷ�����ʽ�����ɹ�����
   * 
   * @param orderVOs �ɹ������ۺ�VO����
   * @param sourceReturnVOs ����ѰԴ����VO����
   * @return
   * @throws BusinessException
   */
  public OrderVO[] pushSave(OrderVO[] orderVOs, SourceReturnVO[] SourceReturnVOs)
      throws BusinessException;

}
