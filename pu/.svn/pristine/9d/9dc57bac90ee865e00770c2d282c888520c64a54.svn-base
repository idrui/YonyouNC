/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-7 下午04:14:54
 */
package nc.ui.pu.est.action.m45;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.est.m45.IPurchaseInEstMaintain;
import nc.ui.pu.est.action.CancelEstAction;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstHeaderVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购入库单取消暂估动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-7 下午04:14:54
 */
@SuppressWarnings("serial")
public class PurchsInCancelEstAction extends CancelEstAction {

  @Override
  protected void cancelEst(EstVO[] vos, UFBoolean onlyCancelFee)
      throws BusinessException {
    IPurchaseInEstMaintain srv =
        NCLocator.getInstance().lookup(IPurchaseInEstMaintain.class);
    srv.puchaseInUnEst((PurchaseInEstVO[]) vos, onlyCancelFee);
  }

  @Override
  protected void setHeadInfo(GoodsEstVO head, GoodsEstVO ohead) {
    super.setHeadInfo(head, ohead);
    ((PurchaseInEstHeaderVO) head).setBautotofi(((PurchaseInEstHeaderVO) ohead)
        .getBautotofi());
  }

}
