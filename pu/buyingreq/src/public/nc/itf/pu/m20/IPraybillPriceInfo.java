package nc.itf.pu.m20;

import nc.vo.pu.m20.entity.PrayPriceInfoVO;
import nc.vo.pub.BusinessException;

/**
 * @since 6.0
 * @version 2011-3-22 ����08:59:15
 * @author �����
 */

public interface IPraybillPriceInfo {
  /**
   * <ul>
   * <b>Ϊ�۸���֤���ѯ�۸�</b>
   * <li>��ѯѯ���۵���ͱ���;
   * <li>��ѯ�ɹ����¼�
   * </ul>
   * 
   * @param priceInfoVOs
   * @param dateWhere
   * @return
   * @throws BusinessException
   */
  public PrayPriceInfoVO[] queryPriceInfo(PrayPriceInfoVO[] priceInfoVOs,
      String dateWhere) throws BusinessException;
}
