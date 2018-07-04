/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 下午01:09:51
 */
package nc.pubimpl.pu.est.m45;

import nc.bs.pu.est.EstVOQueryBP;
import nc.pubitf.pu.est.m45.IPurchaseInEstPubQuery;
import nc.vo.pu.est.entity.m45.PurchaseInEstHeaderVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.m4201.entity.PurchaseinFIFeeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>查询采购入库单的暂估信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-16 下午01:09:51
 */
public class PurchaseInEstPubQueryImpl implements IPurchaseInEstPubQuery {

  @Override
  public PurchaseInEstVO[] query(String[] bids) throws BusinessException {
    PurchaseInEstVO[] vos = null;
    try {

      vos =
          new EstVOQueryBP<PurchaseInEstVO>(PurchaseInEstVO.class,
              PurchaseInEstHeaderVO.class, PurchaseinFIFeeVO.class).query(bids);

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return vos;
  }

}
