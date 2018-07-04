package nc.bs.pu.m23.pfxx;

import nc.bs.pu.m23.maintain.ArriveUpdateBP;
import nc.impl.pu.m23.maintain.action.ArriveDeleteAction;
import nc.impl.pu.m23.maintain.action.ArriveInsertAction;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.env.ArrivalUIToBSEnv;
import nc.vo.pu.pfxx.plugins.AbstractPuPfxxPlugin;
import nc.vo.pub.AggregatedValueObject;

/**
 * 到货单外部平台导入插件类 (此类可删除,到货不支持)
 * 
 * @since 6.0
 * @version 2011-4-29 下午05:06:07
 * @author 田锋涛
 */
@Deprecated
public class M23PfxxPlugin extends AbstractPuPfxxPlugin {

  @Override
  protected void deleteVO(AggregatedValueObject vo) {
    new ArriveDeleteAction().deleteArrive(new ArriveVO[] {
      (ArriveVO) vo
    });
  }

  @Override
  protected String getChildrenPkFiled() {
    return ArriveItemVO.PK_ARRIVEORDER_B;
  }

  @Override
  protected String getParentPkFiled() {
    return ArriveHeaderVO.PK_ARRIVEORDER;
  }

  @Override
  protected AggregatedValueObject insert(AggregatedValueObject vo) {
    ArriveVO[] returnVOs =
        new ArriveInsertAction().insertArrive(new ArriveVO[] {
          (ArriveVO) vo
        }, new ArrivalUIToBSEnv());
    return returnVOs[0];

  }

  @Override
  protected AggregatedValueObject queryVOByPk(String voPk) {
    BillQuery<ArriveVO> billquery = new BillQuery<ArriveVO>(ArriveVO.class);
    return billquery.query(new String[] {
      voPk
    })[0];
  }

  @Override
  protected AggregatedValueObject update(AggregatedValueObject updatevo,
      AggregatedValueObject origVO) {
    ArriveVO[] returnVOs =
        new ArriveUpdateBP(new ArrivalUIToBSEnv()).updateArrive(new ArriveVO[] {
          (ArriveVO) updatevo
        }, new ArriveVO[] {
          (ArriveVO) origVO
        });
    return returnVOs[0];
  }

}
