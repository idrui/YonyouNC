/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 下午03:38:29
 */
package nc.pubimpl.pu.est.m45;

import nc.pubimpl.pu.est.m45.action.PurchsInAutoEstAction;
import nc.pubimpl.pu.est.m45.action.PurchsInTOCostAPAction;
import nc.pubitf.pu.est.m45.IPurchaseInTOCostAP;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-24 下午03:38:29
 */
public class PurchaseInTOCostAPImpl implements IPurchaseInTOCostAP {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.est.m45.IPurchaseInTOCostAP#confirm(nc.vo.ic.m45.entity.PurchaseInVO[])
   */
  @Override
  public void confirm(PurchaseInVO[] vos) throws BusinessException {
    try {
      new PurchsInTOCostAPAction().confirm(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.est.m45.IPurchaseInTOCostAP#estimate(nc.vo.ic.m45.entity.PurchaseInVO[])
   */
  @Override
  public void estimate(PurchaseInVO[] vos) throws BusinessException {
    try {
      new PurchsInAutoEstAction().estimate(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }

  }

}
