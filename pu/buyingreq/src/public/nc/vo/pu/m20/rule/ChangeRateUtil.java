package nc.vo.pu.m20.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.StringUtils;

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
 * @author gaogr
 * @time 2010-8-18 下午04:25:42
 */
public class ChangeRateUtil {

  /**
   * 方法功能描述：设置“是否固定换算率”、“换算率”。
   * <p>
   * <b>参数说明</b>
   * 
   * @param keyValue
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 下午04:25:55
   */
  public void setChgRateAndFixedFlag(IKeyValue keyValue) {
    int[] rows = this.getRows(keyValue);

    this.setChgRateAndFixedFlag(keyValue, rows, true);
  }

  /**
   * 方法功能描述：设置“是否固定换算率”、“换算率”。
   * <p>
   * <b>参数说明</b>
   * 
   * @param keyValue
   * @param rowNos
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-28 下午03:13:30
   */
  public void setChgRateAndFixedFlag(IKeyValue keyValue, int[] rowNos) {
    this.setChgRateAndFixedFlag(keyValue, rowNos, false);
  }

  /**
   * 方法功能描述：设置“是否固定换算率”、“换算率”。
   * <p>
   * <b>参数说明</b>
   * 
   * @param keyValue
   * @param rowNos
   * @param isSetNotnull
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-28 下午03:13:30
   */
  public void setChgRateAndFixedFlag(IKeyValue keyValue, int[] rowNos,
      boolean isSetNotnull) {

    if (null == rowNos || rowNos.length == 0) {
      return;
    }

    ScaleUtils su =
        new ScaleUtils((String) keyValue.getBodyValue(rowNos[0],
            PraybillItemVO.PK_GROUP));   
    /*
     * add by wandl
     * 解决物料编辑事件效率问题，修改查询换算率，固定换算率为批量接口！
     */
    List<String> materiallist = new ArrayList<String>();
    List<String> castunitidlist = new ArrayList<String>();
    for (int rowNo : rowNos){
    	String pk_material =
          (String) keyValue.getBodyValue(rowNo, PraybillItemVO.PK_MATERIAL);
   // 辅单位，用来取固定换算率标志位的
    	String castunitid =
          (String) keyValue.getBodyValue(rowNo, PraybillItemVO.CASTUNITID);
    	if(pk_material == null || castunitid == null){
    		continue;
    	}
      materiallist.add(pk_material);
      castunitidlist.add(castunitid);
    }
    String[] pk_materials = materiallist.toArray(new String[0]);
    String[] castunitids = castunitidlist.toArray(new String[0]);
    Map<String, UFBoolean> isfixedratemap =  new HashMap<String, UFBoolean>();
    if (pk_materials.length > 0 && castunitids.length > 0) {
    	isfixedratemap = MaterialPubService.
    			queryIsFixedRateByMaterialAndMeasdocs(pk_materials, castunitids);
    }
    for (int rowNo : rowNos) {
      if (isSetNotnull
          && null != keyValue.getBodyValue(rowNo, PraybillItemVO.VCHANGERATE)) {
        continue;
      }
      // 物料主键
      String pk_material =
          (String) keyValue.getBodyValue(rowNo, PraybillItemVO.PK_MATERIAL);
      if (StringUtils.isEmpty(pk_material)) {
        continue;
      }

      // 单位、业务单位
      String cunitid =
          (String) keyValue.getBodyValue(rowNo, PraybillItemVO.CUNITID);
      String castunitid =
          (String) keyValue.getBodyValue(rowNo, PraybillItemVO.CASTUNITID);

      if (StringUtils.isEmpty(castunitid)) {
        continue;
      }

      // 是否固定换算率、换算率
      UFBoolean isFixedRate = null;
      String changeRate = null;
      if (cunitid.equals(castunitid)) {
        isFixedRate = UFBoolean.valueOf(true);
        changeRate = "1/1";
      }
      else {
      	isFixedRate = isfixedratemap.get(pk_material + castunitid);
        /*isFixedRate =
            MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(
                pk_material, castunitid);*/

        changeRate =
            MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(
                pk_material, castunitid);
      }
      changeRate = su.adjustHslScale(changeRate);
      keyValue.setBodyValue(rowNo, PraybillItemVO.BFIXEDRATE,isFixedRate);
      keyValue.setBodyValue(rowNo, PraybillItemVO.VCHANGERATE, changeRate);
    }
  }

  private int[] getRows(IKeyValue keyValue) {
    int[] rows = new int[keyValue.getItemCount()];
    for (int i = 0; i < keyValue.getItemCount(); i++) {
      rows[i] = i;
    }
    return rows;
  }
}
