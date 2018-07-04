/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-2 ����10:07:35
 */
package nc.ui.pu.m21.service.onway;

import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21.rule.OnwayValidationTool;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����ǰ̨У����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-2 ����10:07:35
 */
public class OrderSendoutValidationService extends
    AbstractOnwayValidationService {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.m21.service.onway.AbstractOnwayValidationService#docheck()
   */
  @Override
  void docheck(PoTransTypeVO transtypeVO, OrderOnwayItemVO[] voitems)
      throws BusinessException {

    OnwayValidationTool.chkEmptyForSendOut(transtypeVO, voitems);
    OnwayValidationTool.chkBusiForSendOut(transtypeVO, voitems);
  }
}
