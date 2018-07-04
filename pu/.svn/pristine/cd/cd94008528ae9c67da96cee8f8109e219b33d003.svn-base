package nc.vo.pu.pub.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.IKeyValue.RowStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 计算体积重量
 * 
 * @since 6.0
 * @version 2011-5-8 下午01:29:06
 * @author wuxla
 */

public class WeightVolumePieceCalc {
  /** 主单位 */
  private String itemCunitidField = "cunitid";

  /** 物料 **/
  private String itemMaterialField = "pk_material";

  /** 主数量 **/
  private String itemNumField = "nnum";

  /** 件数 */
  private String itemPackField = "npacknum";

  /** 体积 */
  private String itemVolumeField = "nvolumn";

  /** 重量 */
  private String itemWeightField = "nweight";

  private IKeyValue[] keyValues;

  private Map<String, MaterialVO> matMap;

  private Map<String, String> measMap;

  private ScaleUtils scale;
  
  private int[] rows;

  public WeightVolumePieceCalc(IKeyValue keyValue) {
    this.keyValues = Constructor.declareArray(keyValue.getClass(), 1);
    this.keyValues[0] = keyValue;
    this.init();
  }

  public WeightVolumePieceCalc(IKeyValue keyValue, int[] rows) {
    this.keyValues = Constructor.declareArray(keyValue.getClass(), 1);
    this.keyValues[0] = keyValue;
    this.rows = rows;
    this.init();
  }
  
  public WeightVolumePieceCalc(IKeyValue[] keyValues) {
    this.keyValues = keyValues;
    this.init();
  }

  /**
   * 对一个单据所有行进行计算。
   * 
   * @param keyValue 单据。构造函数中传入的。
   */
  public void calc(IKeyValue keyValue) {
    int[] rows = new int[keyValue.getItemCount()];
    for (int i = 0; i < rows.length; i++) {
      rows[i] = i;
    }
    this.calc(keyValue, rows);
  }

  /**
   * 针对指定的KeyValue进行计算
   * 
   * @param keyValue 要计算的单据
   * @param rows 要计算的行
   */
  public void calc(IKeyValue keyValue, int[] rows) {
    if (null == this.matMap) {
      return;
    }
    for (int row : rows) {
      UFDouble num = (UFDouble) keyValue.getBodyValue(row, this.itemNumField);
      if (0 == MathTool.compareTo(num, UFDouble.ZERO_DBL)) {
        keyValue.setBodyValue(row, this.itemWeightField, null);
        keyValue.setBodyValue(row, this.itemVolumeField, null);
        continue;
      }

      String pk_material =
          (String) keyValue.getBodyValue(row, this.itemMaterialField);
      if (StringUtil.isEmptyWithTrim(pk_material)) {
        continue;
      }

      this.setWeight(pk_material, num, row, keyValue);
      this.setVolume(pk_material, num, row, keyValue);
      this.setPiece(pk_material, num, row, keyValue);
    }

  }

  /**
   * 只针对构造时传入的第1个KeyValue进行计算
   * 
   * @param rows 要计算的行
   */
  public void calc(int[] rows) {
    this.calc(this.keyValues[0], rows);
  }

  public void setItemMaterialField(String itemMaterialField) {
    this.itemMaterialField = itemMaterialField;
  }

  public void setItemNumField(String itemNumField) {
    this.itemNumField = itemNumField;
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

  private String getChangeRate(String pk_material, String pieceMeasdoc,
      String cunitid) {
    String changeRate = "1/1";
    if (!cunitid.equals(pieceMeasdoc)) {
      changeRate =
          MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(pk_material,
              pieceMeasdoc);
    }
    return this.scale.adjustHslScale(changeRate);
  }

  private Map<String, MaterialVO> getMatMap(String[] mats) {
    return MaterialPubService.queryMaterialBaseInfo(mats, new String[] {
      MaterialVO.UNITWEIGHT, MaterialVO.UNITVOLUME
    });
  }

  private String[] getMats() {
    List<String> matList = new ArrayList<>();
    if(this.rows != null){
      for(int row : this.rows){
        Object pk_material = this.keyValues[0].getBodyValue(row, this.itemMaterialField);
        if(pk_material == null){
          continue;
        }
        matList.add(pk_material.toString());
      }
    } else {
      for (IKeyValue keyValue : this.keyValues) {
        for (int i = 0; i < keyValue.getItemCount(); ++i) {
          // 删除行不计算
          if (keyValue.getRowStatus(i) == RowStatus.DELETED) {
            continue;
          }
          String pk_material =
              (String) keyValue.getBodyValue(i, this.itemMaterialField);
          if (StringUtil.isEmptyWithTrim(pk_material)) {
            continue;
          }
          matList.add(pk_material);
        }
      }
    }
    if (0 == matList.size()) {
      return null;
    }

    String[] mats = matList.toArray(new String[matList.size()]);
    return mats;
  }

  private Map<String, String> getMeasMap(String[] mats) {
    return MaterialPubService.queryPieceMeasdocIDByPks(mats);
  }

  private void init() {
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    this.scale = new ScaleUtils(pk_group);
    String[] mats = this.getMats();
    if (ArrayUtils.isEmpty(mats)) {
      return;
    }
    this.matMap = this.getMatMap(mats);
    this.measMap = this.getMeasMap(mats);
  }

  private void setPiece(String pk_material, UFDouble num, int i,
      IKeyValue keyValue) {
    String pieceMeasdoc =
        null == this.measMap ? null : this.measMap.get(pk_material);
    if (null == pieceMeasdoc) {
      keyValue.setBodyValue(i, this.itemPackField, null);
      return;
    }
    String cunitid = (String) keyValue.getBodyValue(i, this.itemCunitidField);
    String vchangerate = this.getChangeRate(pk_material, pieceMeasdoc, cunitid);
    UFDouble npacknum =
        this.scale.adjustUnitScale(
            HslParseUtil.hslDivUFDouble(vchangerate, num), pk_material);
    keyValue.setBodyValue(i, this.itemPackField, npacknum);
  }

  private void setVolume(String pk_material, UFDouble num, int i,
      IKeyValue keyValue) {
    if (null == this.matMap.get(pk_material)) {
      return;
    }
    UFDouble unitvolume =
        MathTool.nvl(this.matMap.get(pk_material).getUnitvolume());
    UFDouble nvolume =
        this.scale.adjustStandardVolumnScale(num.multiply(unitvolume));

    keyValue.setBodyValue(i, this.itemVolumeField, nvolume);
  }

  private void setWeight(String pk_material, UFDouble num, int i,
      IKeyValue keyValue) {
    if (null == this.matMap.get(pk_material)) {
      return;
    }
    UFDouble unitweight =
        MathTool.nvl(this.matMap.get(pk_material).getUnitweight());
    UFDouble nweight =
        this.scale.adjustStandardWeightScale(num.multiply(unitweight));
    keyValue.setBodyValue(i, this.itemWeightField, nweight);
  }
}
