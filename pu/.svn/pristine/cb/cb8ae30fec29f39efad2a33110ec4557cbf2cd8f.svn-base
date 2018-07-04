package nc.vo.pu.m21.rule;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pubapp.AppContext;
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
 * @author duy
 * @time 2010-4-8 下午03:32:21
 */
public class UnitAndChangeRate {
  private IKeyValue keyValue;

  private ScaleUtils scale;

  public UnitAndChangeRate(IKeyValue keyValue) {
    this.keyValue = keyValue;
    this.scale = new ScaleUtils(AppContext.getInstance().getPkGroup());
  }

  /**
   * 方法功能描述：设置业务单位换算率和报价单位、报价单位换算率。如果业务单位变化，需要同时修改报价单位
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   *          要设置的单据行数组
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-4-8 下午03:35:04
   */
  public void setChangeRate(int[] rows) {
    for (int row : rows) {
      Object assistUnit =
          this.keyValue.getBodyValue(row, OrderItemVO.CASTUNITID);

      // 清空换算率
      if (assistUnit == null) {
        this.keyValue.setBodyValue(row, OrderItemVO.VCHANGERATE, null);
        // 清空报价单位和报价单位换算率
        this.keyValue.setBodyValue(row, OrderItemVO.CQTUNITID, null);
        this.keyValue.setBodyValue(row, OrderItemVO.VQTUNITRATE, null);
        return;
      }

      // 查询换算率
      String changeRate = this.getChangeRate(row, (String) assistUnit);
      this.keyValue.setBodyValue(row, OrderItemVO.VCHANGERATE, changeRate);

      // 同步报价单位和报价单位换算率
      this.keyValue.setBodyValue(row, OrderItemVO.CQTUNITID, assistUnit);
      this.keyValue.setBodyValue(row, OrderItemVO.VQTUNITRATE, changeRate);
    }
  }

  /**
   * 不清空报价换算率
   * 
   * @param rows
   */
  public void setChangeRateNotChangeQt(int[] rows) {
    for (int row : rows) {
      Object assistUnit =
          this.keyValue.getBodyValue(row, OrderItemVO.CASTUNITID);

      // 清空换算率
      if (assistUnit == null) {
        this.keyValue.setBodyValue(row, OrderItemVO.VCHANGERATE, null);
        return;
      }

      // 查询换算率
      String changeRate = this.getChangeRate(row, (String) assistUnit);
      this.keyValue.setBodyValue(row, OrderItemVO.VCHANGERATE, changeRate);
    }
  }

  /**
   * 方法功能描述：设置报价单位换算率。报价单位变化，不会修改业务单位。
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   *          要设置的单据行数组
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-4-8 下午03:35:28
   */
  public void setQtChangeRate(int[] rows) {
    for (int row : rows) {
      Object assistUnit =
          this.keyValue.getBodyValue(row, OrderItemVO.CQTUNITID);

      // 清空换算率
      if (assistUnit == null) {
        this.keyValue.setBodyValue(row, OrderItemVO.VQTUNITRATE, null);
        return;
      }

      // 查询换算率
      String changeRate = this.getChangeRate(row, (String) assistUnit);
      this.keyValue.setBodyValue(row, OrderItemVO.VQTUNITRATE, changeRate);

    }
  }

  private String getChangeRate(int row, String unit) {
    Object mainunit = this.keyValue.getBodyValue(row, OrderItemVO.CUNITID);
    String changeRate = "1/1";
    if (!unit.equals(mainunit)) {
      Object pk_material =
          this.keyValue.getBodyValue(row, OrderItemVO.PK_MATERIAL);
      changeRate =
          MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(
              (String) pk_material, unit);
    }
    return this.scale.adjustHslScale(changeRate);
  }
}
