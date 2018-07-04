package nc.vo.pu.m23.rule.transfer;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>转单后，处理“换算率”、“是否固定换算率”
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-4-29 下午06:29:21
 */
public class DealUnitAndChangeRate {
  private ArriveVO[] aggVOArray;

  public DealUnitAndChangeRate(ArriveVO[] retVOArray) {
    this.aggVOArray = retVOArray;
  }

  public ArriveVO[] deal() {
    ArriveItemVO[] itemVO = null;
    ScaleUtils scale = new ScaleUtils(AppContext.getInstance().getPkGroup());
    for (ArriveVO vo : this.aggVOArray) {
      itemVO = vo.getBVO();
      for (int i = 0, len = itemVO.length; i < len; i++) {
        String pk_material = itemVO[i].getPk_material();
        // 单位、业务单位
        String cunitid = itemVO[i].getCunitid();
        String castunitid = itemVO[i].getCastunitid();

        // 是否固定换算率、换算率
        // 换算率走vo对照,不用查,固定换算率为计算属性需要查,modify by wangljc at 2011-7-5 18:47:46
        boolean isFixedRate = false;
        if (cunitid.equals(castunitid)) {
          isFixedRate = true;
          itemVO[i].setVchangerate(scale.adjustHslScale("1/1"));
        }
        else {
          isFixedRate =
              MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(
                  pk_material, castunitid);
          // changeRate =
          // MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(
          // pk_material, castunitid);
        }
        itemVO[i].setBfixedrate(UFBoolean.valueOf(isFixedRate));
      }
    }
    return this.aggVOArray;
  }
}
