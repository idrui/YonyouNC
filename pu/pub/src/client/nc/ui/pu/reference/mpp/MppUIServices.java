package nc.ui.pu.reference.mpp;

import nc.itf.tb.control.IAccessableBusiVO;
import nc.ui.mpp.reference.tbb.TbbLinkServices;

/**
 * @since 6.0
 * @version 2011-6-14 ����07:58:43
 * @author wuxla
 */

public class MppUIServices {

  /**
   * �����ദ�����Ƿ����òɹ��ƻ�
   * �����ò�����У�����װ�ɹ��ƻ����ڵ�ʹ򲻿��ˡ�
   * 
   * @param busiVOs
   */
  public static void linkQueryMpp(IAccessableBusiVO[] busiVOs) {
    TbbLinkServices.linkQueryMpp(busiVOs);
  }
}
