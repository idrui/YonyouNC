/**
 * $文件说明$
 * 
 * @author Administrator
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-10 上午11:27:48
 */
package nc.bs.pu.est;

import nc.impl.pubapp.pattern.data.view.tool.ViewConcurrentTool;
import nc.impl.pubapp.pattern.data.view.tool.ViewTransferTool;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.pub.util.AggVOUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估VO单据补全和并发控制工具
 * <li><b>此工具保证只查一次原始VO</b>
 * <li><b>对于暂估，只对货物暂估进行并发控制，对相应的费用暂估不用并发控制也不会有问题，提高并发性</b>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author Administrator
 * @time 2010-7-10 上午11:27:48
 */
@SuppressWarnings("unchecked")
public class EstVOTransferTool<T extends EstVO> {
  private T[] clientVos;

  private T[] origVos;

  private EstVOQueryBP<T> voQryBP;

  public EstVOTransferTool(QueryEstType estType, T[] clientVos,
      Class<? extends FeeEstVO> feeVoClass) {
    this.clientVos = clientVos;
    Class<T> estVoClass = (Class<T>) clientVos[0].getClass();
    Class<? extends GoodsEstVO> goodsVoClass =
        clientVos[0].getParentVO().getClass();
    this.voQryBP = new EstVOQueryBP<T>(estVoClass, goodsVoClass, feeVoClass);
    if (QueryEstType.UN_EST == estType) {
      this.initUnEst();
    }
    else if (QueryEstType.GOODS_EST == estType) {
      // 暂估时，只做货物的并发，费用不用考虑并发，因一个费用项只能暂估一次，BP中有此规则检查，类似并发处理
      this.initEst(null);
    }
    else {
      String[] bids =
          (String[]) AggVOUtil.getDistinctHeadFieldArray(clientVos,
              GoodsEstVO.PK_STOCKPS_B, String.class);
      this.initEst(bids);
    }
  }

  public T[] getClientFullVos() {
    return this.clientVos;
  }

  public T[] getOrigVos() {
    return this.origVos;
  }

  private void initEst(String[] bids) {
    GoodsEstVO[] heads = EstVOUtil.getGoodsEstVos(this.clientVos);
    ViewTransferTool<GoodsEstVO> tool = new ViewTransferTool<GoodsEstVO>(heads);
    GoodsEstVO[] origHeads = tool.getOriginViews();
    FeeEstVO[] fees = null;
    if (!ArrayUtils.isEmpty(bids)) {
      fees = this.voQryBP.queryItems(bids);
    }
    this.origVos = this.voQryBP.combineVO(origHeads, fees);
  }

  private void initUnEst() {
    GoodsEstVO[] heads = EstVOUtil.getGoodsEstVos(this.clientVos);
    // 锁货物暂估主键（财务表头和表体）
    new ViewConcurrentTool().lock(heads);
    String[] bids =
        (String[]) AggVOUtil.getDistinctHeadFieldArray(this.clientVos,
            GoodsEstVO.PK_STOCKPS_B, String.class);
    // 锁费用暂估主键
    FeeEstVO[] fees = (FeeEstVO[]) EstVOUtil.getFeeEstVOs(this.clientVos);
    if (!ArrayUtils.isEmpty(fees)) {
      new VOConcurrentTool().lock(fees);
    }
    else {
      fees = new FeeEstVO[0];
    }
    // 货物暂估checkts
    GoodsEstVO[] origHeads = this.voQryBP.queryHead(bids);
    new ViewConcurrentTool().checkTS(heads, origHeads);
    // 费用暂估checkts
    FeeEstVO[] origFees = this.voQryBP.queryItems(bids);
    new VOConcurrentTool().checkTS(fees, origFees);
    this.origVos = this.voQryBP.combineVO(origHeads, origFees);
  }

}
