/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-27 ����02:16:09
 */
package nc.impl.pu.m20;

import nc.impl.pu.m20.action.PraybillPublishToECAction;
import nc.itf.pu.m20.IPraybillPublishToEC;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺����������������ʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-27 ����02:16:09
 */
public class PraybillPublishToECImpl implements IPraybillPublishToEC {

  @Override
  public PraybillVO[] publishToEC(PraybillVO[] vos) throws BusinessException {
    try {
      return new PraybillPublishToECAction().publishToEC(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public PraybillVO[] unPublishToEC(PraybillVO[] vos) throws BusinessException {
    try {
      return new PraybillPublishToECAction().unPublishToEC(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

}
