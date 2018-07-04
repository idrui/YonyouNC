package nc.itf.pu.reference.ic;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.m4d.pu.IMaterialOutForPU;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ���ϳ���4D�ṩ���ɹ��ķ�������
 * 
 * @since 6.0
 * @version 2011-5-8 ����09:09:00
 * @author wuxla
 */

public class M4DPUServices {

  /**
   * ȡ�ù�Ӧ�̲���������
   * 
   * @param paramMap paramMap Key:����oid��value:��Ӧ������
   * @return Key:����oid|��Ӧ��pk��value:������(UFDouble)
   */
  public static Map<String, UFDouble> getVendorQuotas(
      Map<String, String[]> paramMap) {
    IMaterialOutForPU service =
        NCLocator.getInstance().lookup(IMaterialOutForPU.class);
    try {
      return service.getVendorQuotas(paramMap);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
