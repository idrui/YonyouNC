package nc.vo.pu.pub.rule;

import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.IKeyValue.RowStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @since 6.0
 * @version 2011-6-3 下午01:41:48
 * @author wuxla
 */

public class WeightVolumePieceSum {
  /** 总件数 */
  private String headerPieceField = "ntotalpiece";

  /** 总体积 */
  private String headerVolumeField = "ntotalvolume";

  /** 总重量 */
  private String headerWeightField = "ntotalweight";

  /** 件数 */
  private String itemPackField = "npacknum";

  /** 体积 */
  private String itemVolumeField = "nvolumn";

  /** 重量 */
  private String itemWeightField = "nweight";

  private IKeyValue keyValue;

  public WeightVolumePieceSum(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public boolean excludeRow(int row) {
    if (row >= 0) {
      return false;
    }
    return true;
  }

  public String getItemPackField() {
    return this.itemPackField;
  }

  public void setHeaderPieceField(String headerPieceField) {
    this.headerPieceField = headerPieceField;
  }

  public void setHeaderVolumeField(String headerVolumeField) {
    this.headerVolumeField = headerVolumeField;
  }

  public void setHeaderWeightField(String headerWeightField) {
    this.headerWeightField = headerWeightField;
  }

  public void setItemPackField(String itemPackField) {
    this.itemPackField = itemPackField;
  }

  public void setItemVolumeField(String itemVolumeField) {
    this.itemVolumeField = itemVolumeField;
  }

  public void setItemWeightField(String itemWeightField) {
    this.itemWeightField = itemWeightField;
  }

  public void sum() {
    int rows = this.keyValue.getItemCount();
    UFDouble ntotalpiece = UFDouble.ZERO_DBL;
    UFDouble ntotalvolume = UFDouble.ZERO_DBL;
    UFDouble ntotalweight = UFDouble.ZERO_DBL;
    for (int i = 0; i < rows; i++) {
      // 删除行不计算
      if (this.keyValue.getRowStatus(i) == RowStatus.DELETED) {
        continue;
      }
      // 其他排除的行不计算
      if (this.excludeRow(i)) {
        continue;
      }

      ntotalpiece =
          MathTool.add(ntotalpiece,
              (UFDouble) this.keyValue.getBodyValue(i, this.itemPackField));
      ntotalvolume =
          MathTool.add(ntotalvolume,
              (UFDouble) this.keyValue.getBodyValue(i, this.itemVolumeField));
      ntotalweight =
          MathTool.add(ntotalweight,
              (UFDouble) this.keyValue.getBodyValue(i, this.itemWeightField));
    }

    this.keyValue.setHeadValue(this.headerWeightField, ntotalweight);
    this.keyValue.setHeadValue(this.headerVolumeField, ntotalvolume);
    this.keyValue.setHeadValue(this.headerPieceField, ntotalpiece);
  }
}
