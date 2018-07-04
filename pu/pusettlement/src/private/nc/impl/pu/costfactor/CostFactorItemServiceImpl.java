/**
 * $�ļ�˵��$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-19 ����11:23:58
 */
package nc.impl.pu.costfactor;

import nc.impl.pu.costfactor.action.UpdateCostfactorItemAction;
import nc.itf.pu.costfactor.ICostFactorItemService;
import nc.vo.pu.costfactor.entity.CostfactorItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

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
 * @time 2010-6-19 ����11:23:58
 */
public class CostFactorItemServiceImpl implements ICostFactorItemService {

  @Override
  public CostfactorItemVO update(CostfactorItemVO newVo)
      throws BusinessException {
    try {
      CostfactorItemVO[] costVOs = new CostfactorItemVO[] {
        newVo
      };
      return this.update(costVOs)[0];
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public CostfactorItemVO[] update(CostfactorItemVO[] newVos)
      throws BusinessException {

    try {
      // ���Ƿ���ʾ����ʾ˳����µ����ݿ���
      return new UpdateCostfactorItemAction().update(newVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

}
