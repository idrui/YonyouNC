/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 ����01:09:51
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ѯ�ɹ���ⵥ���ݹ���Ϣ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-16 ����01:09:51
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
