/**
 * $�ļ�˵��$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-19 ����11:36:16
 */
package nc.impl.pu.costfactor.action;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.pu.costfactor.entity.CostfactorItemVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-19 ����11:36:16
 */
public class UpdateCostfactorItemAction {

  public CostfactorItemVO[] update(CostfactorItemVO[] bills) {
    VOUpdate<CostfactorItemVO> vou = new VOUpdate<CostfactorItemVO>();

    String[] toUpdateStrs = new String[] {
      CostfactorItemVO.BSHOW, CostfactorItemVO.ISHOWORDER
    };

    return vou.update(bills, toUpdateStrs);

  }
}
