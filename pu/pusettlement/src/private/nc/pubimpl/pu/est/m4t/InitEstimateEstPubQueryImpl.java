package nc.pubimpl.pu.est.m4t;

import nc.bs.pu.est.EstVOQueryBP;
import nc.pubitf.pu.est.m4t.IInitEstimateEstPubQuery;
import nc.vo.pu.est.entity.m4t.InitialBillEstItemVO;
import nc.vo.pu.est.entity.m4t.InitialBillEstVO;
import nc.vo.pu.est.entity.m4t.InitialBillGoodsEstVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since 6.0
 * @version 2011-4-12 下午07:10:06
 * @author 田锋涛
 */

public class InitEstimateEstPubQueryImpl implements IInitEstimateEstPubQuery {
  @Override
  public InitialBillEstVO[] query(String[] bids) throws BusinessException {
    InitialBillEstVO[] vos = null;
    try {
      // vos =
      // new EstVOQueryBP<InitialBillEstVO>(InitialBillEstVO.class,
      // InitialBillGoodsEstVO.class, InitialBillEstItemVO.class)
      // .query(bids);

      EstVOQueryBP<InitialBillEstVO> bp =
          new EstVOQueryBP<InitialBillEstVO>(InitialBillEstVO.class,
              InitialBillGoodsEstVO.class, InitialBillEstItemVO.class);
      // 不可少
      bp.setStock_b_key(InitialEstItemVO.PK_INITIALEST_B);
      vos = bp.query(bids);

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return vos;
  }

}
