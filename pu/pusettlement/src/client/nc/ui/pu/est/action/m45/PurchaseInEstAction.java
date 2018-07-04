/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-20 ����06:01:20
 */
package nc.ui.pu.est.action.m45;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.est.m45.IPurchaseInEstMaintain;
import nc.ui.pu.est.action.AbstractEstAction;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ⵥ�ݹ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-20 ����06:01:20
 */
public class PurchaseInEstAction extends AbstractEstAction {

  private static final long serialVersionUID = 8285210542196706304L;

  @Override
  protected void feeEstInvokeBS(EstVO[] vos) throws Exception {
    IPurchaseInEstMaintain srv =
        NCLocator.getInstance().lookup(IPurchaseInEstMaintain.class);
    srv.purchaseInFeeEst((PurchaseInEstVO[]) vos);
  }

  @Override
  protected void goodsEstInvokeBS(EstVO[] vos) throws Exception {
    IPurchaseInEstMaintain srv =
        NCLocator.getInstance().lookup(IPurchaseInEstMaintain.class);
    srv.purchaseInEst((PurchaseInEstVO[]) vos);

  }
}
