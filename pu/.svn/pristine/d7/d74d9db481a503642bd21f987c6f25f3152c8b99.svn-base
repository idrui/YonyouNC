/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-26 下午08:29:44
 */
package nc.vo.pu.m422x.rule;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pubapp.scale.ScaleUtils;

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
 * @author wuxla
 * @time 2010-7-26 下午08:29:44
 */
public class UnitAndChangeRate {

  private IKeyValue keyValue;

  private ScaleUtils scale;

  public UnitAndChangeRate(IKeyValue keyValue) {
    this.keyValue = keyValue;
    this.scale =
        new ScaleUtils(
            (String) this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_GROUP));
  }

  public void setChangeRate(int[] rows) {
    for (int row : rows) {
      Object assistUnit =
          this.keyValue.getBodyValue(row, StoreReqAppItemVO.CASTUNITID);
      if (assistUnit == null) {
        this.keyValue.setBodyValue(row, StoreReqAppItemVO.VCHANGERATE, null);
        return;
      }

      String changeRate = this.getChangeRate(row, (String) assistUnit);
      this.keyValue
          .setBodyValue(row, StoreReqAppItemVO.VCHANGERATE, changeRate);
    }
  }

  private String getChangeRate(int row, String unit) {
    Object mainunit =
        this.keyValue.getBodyValue(row, StoreReqAppItemVO.CUNITID);
    String changeRate = this.scale.adjustHslScale("1/1");
    if (!unit.equals(mainunit)) {
      Object pk_material =
          this.keyValue.getBodyValue(row, StoreReqAppItemVO.PK_MATERIAL);
      changeRate =
          MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(
              (String) pk_material, unit);
    }
    return changeRate;
  }
}
