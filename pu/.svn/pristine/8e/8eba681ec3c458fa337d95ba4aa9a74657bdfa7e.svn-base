/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-9 上午11:42:24
 */
package nc.vo.pu.m4t.rule;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>单位和换算率
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-9 上午11:42:24
 */
public class UnitAndChangeRate {
  private IKeyValue keyValue;

  public UnitAndChangeRate(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setChangeRate(int[] rows) {
    for (int row : rows) {
      Object assistUnit =
          this.keyValue.getBodyValue(row, InitialEstItemVO.CASTUNITID);
      if (assistUnit == null) {
        this.keyValue.setBodyValue(row, InitialEstItemVO.VCHANGERATE, null);
        return;
      }

      String changeRate = this.getChangeRate(row, (String) assistUnit);
      this.keyValue.setBodyValue(row, InitialEstItemVO.VCHANGERATE, changeRate);
    }
  }

  private String getChangeRate(int row, String unit) {
    Object mainunit = this.keyValue.getBodyValue(row, InitialEstItemVO.CUNITID);
    String changeRate = "1/1";
    if (!unit.equals(mainunit)) {
      Object pk_material =
          this.keyValue.getBodyValue(row, InitialEstItemVO.PK_MATERIAL);
      changeRate =
          MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(
              (String) pk_material, unit);
    }
    ScaleUtils scale = new ScaleUtils(AppContext.getInstance().getPkGroup());
    return scale.adjustHslScale(changeRate);
  }
}
