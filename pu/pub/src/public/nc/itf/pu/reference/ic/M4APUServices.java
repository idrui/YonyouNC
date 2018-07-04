package nc.itf.pu.reference.ic;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.m4a.IGeneralInServiceForPuFeeSettle;
import nc.pubitf.ic.m4a.IParameter4AForFeeSettle;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������ⵥ�ṩ���ɹ��ķ���ӿ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-9-8 ����10:20:06
 */
public class M4APUServices {

  /**
   * �������������������ۼƷ��ý�������������������ġ��磺���ý�����+1��ȡ������ʱ-1
   * <p>
   * <b>����˵��</b>
   * 
   * @param paras
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-8 ����10:25:06
   */
  public static void rewriteNaccumfeesettlecount(
      IParameter4AForFeeSettle[] paras) {
    try {
      IGeneralInServiceForPuFeeSettle service =
          NCLocator.getInstance().lookup(IGeneralInServiceForPuFeeSettle.class);
      service.rewriteNaccumfeesettlecount(paras);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }
}
