/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-16 下午04:48:01
 */
package nc.vo.pu.m20.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import nc.bs.framework.common.RuntimeEnv;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.plan.MaterialPlanVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.pub.AdDaysUtil;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据提前期设置表体行的需求日期和建议订货日期
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-16 下午04:48:01
 */
public class CalculateBodyDays {

  /**
   * 建议订货日期是否需要重新设置，用于建议订货日期已经有值的情况
   */
  private boolean resetSuggestDate = true;

  /**
   * 构造子
   */
  public CalculateBodyDays() {
    //
  }

  /**
   * 构造子
   * 
   * @param resetSuggestDate
   */
  public CalculateBodyDays(boolean resetSuggestDate) {
    this.resetSuggestDate = resetSuggestDate;
  }

  /**
   * 取得物料计划信息中的提前期信息。
   * <p>
   * <b>参数说明</b>
   * 
   * @param keyValue
   * @param rows
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-20 上午09:04:41
   */
  public Map<String, MaterialPlanVO> getAdDaysInfo(IKeyValue keyValue,
      int[] rows) {
    HashSet<String> pk_materials = new HashSet<String>();
    for (int i = 0, len = rows.length; i < len; i++) {
      String pk_material =
          (String) keyValue.getBodyValue(rows[i], PraybillItemVO.PK_MATERIAL);
      if (!StringUtil.isEmptyWithTrim(pk_material)) {
        pk_materials.add(pk_material);
      }
    }
    /*
     * add by wandl
     * 如果资产配置申请传过来的物料都为空，则不去查询物料计划信息中的提前期信息，直接返回空map
     */
    if(pk_materials.size()<=0){
    	return new HashMap<String, MaterialPlanVO>();
    }
    String pk_org = (String) keyValue.getHeadValue(PraybillHeaderVO.PK_ORG);

    return MaterialPubService.queryMaterialPlanMapInfoByPks(
        pk_materials.toArray(new String[pk_materials.size()]), pk_org,
        new String[] {
          MaterialPlanVO.AHEADBATCH, MaterialPlanVO.AHEADCOFF,
          MaterialPlanVO.FIXEDAHEAD, MaterialPlanVO.PK_MATERIAL
        });
  }

  public boolean isResetSuggestDate() {
    return this.resetSuggestDate;
  }

  /**
   * 根据提前期设置全部行需求日期和建议订货日期。
   * <p>
   * <b>参数说明</b>
   * 
   * @param keyValue <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-16 下午04:51:03
   */
  public void setAdvDays(IKeyValue keyValue) {
    int[] rows = this.getRows(keyValue);
    this.setAdvDays(keyValue, rows);
  }

  /**
   * 根据提前期设置需求日期和建议订货日期。
   * <p>
   * <b>参数说明</b>
   * 
   * @param keyValue
   * @param rows 需要计算的行
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-16 下午04:51:03
   */
  public void setAdvDays(IKeyValue keyValue, int[] rows) {

    Map<String, MaterialPlanVO> adDaysInfo = this.getAdDaysInfo(keyValue, rows);
    if (null == adDaysInfo || adDaysInfo.size() == 0) {
      this.setComDays(keyValue, rows);
      return;
    }

    this.setAdDays(keyValue, rows, adDaysInfo);

  }

  public void setResetSuggestDate(boolean resetSuggestDate) {
    this.resetSuggestDate = resetSuggestDate;
  }

  private int[] getRows(IKeyValue keyValue) {
    int[] rows = new int[keyValue.getItemCount()];
    for (int i = 0; i < keyValue.getItemCount(); i++) {
      rows[i] = i;
    }
    return rows;
  }

  /**
   * 根据计划信息计算提前期设置到需求日期和建议请购日期。<br>
   * 1) 需求日期为空<br>
   * i. 需求日期=当前日期向后退nDays天<br>
   * ii. 建议订货日期=当前日期<br>
   * 2) 需求日期不为空<br>
   * i. 需求日期不变<br>
   * ii. 建议订货日期=需求日期向前退 nDays 天 < 当前日期？当前日期：需求日期向前退 nDays 天<br>
   * <p>
   * <b>参数说明</b>
   * 
   * @param keyValue
   * @param rows
   * @param adDaysInfo <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-20 上午09:46:05
   */
  private void setAdDays(IKeyValue keyValue, int[] rows,
      Map<String, MaterialPlanVO> adDaysInfo) {
    UFDate billDate = AppContext.getInstance().getBusiDate();
    UFDate billDate_b = billDate;
    UFDate billDate_e = billDate;
    if (!RuntimeEnv.getInstance().isRunningInServer()) {
      billDate_b = billDate_b.asLocalBegin();
      billDate_e = billDate_e.asLocalEnd();
    }

    for (int i = 0, len = rows.length; i < len; i++) {
      String pk_material =
          (String) keyValue.getBodyValue(rows[i], PraybillItemVO.PK_MATERIAL);

      if (!StringUtil.isEmptyWithTrim(pk_material)) {
        MaterialPlanVO vo = adDaysInfo.get(pk_material);
        UFDouble num =
            (UFDouble) keyValue.getBodyValue(rows[i], PraybillItemVO.NNUM);
        if (null == vo || null == num) {
          if (null == keyValue.getBodyValue(rows[i], PraybillItemVO.DREQDATE)) {
            keyValue.setBodyValue(rows[i], PraybillItemVO.DREQDATE, billDate_e);
          }

          if (null == keyValue.getBodyValue(rows[i],
              PraybillItemVO.DSUGGESTDATE)) {
            keyValue.setBodyValue(rows[i], PraybillItemVO.DSUGGESTDATE,
                billDate_b);
          }
        }
        else {
          int nDays =
              AdDaysUtil.getAdDaysArith(num, vo.getFixedahead(),
                  vo.getAheadcoff(), vo.getAheadbatch());

          // 需求日期为空
          if (null == keyValue.getBodyValue(rows[i], PraybillItemVO.DREQDATE)) {
            UFDate demdate = billDate.getDateAfter(nDays);
            if (!AdDaysUtil.isDateOverflow(demdate)) {
              if (!RuntimeEnv.getInstance().isRunningInServer()) {
                demdate = demdate.asLocalEnd();
              }
              keyValue.setBodyValue(rows[i], PraybillItemVO.DREQDATE, demdate);
            }
            keyValue.setBodyValue(rows[i], PraybillItemVO.DSUGGESTDATE,
                billDate_b);
          }
          // 需求日期不为空
          else {
            // 已经有值且不需要重新设置,则跳过（用于上下游转单时已经有值的情况）
            if (null != keyValue.getBodyValue(rows[i],
                PraybillItemVO.DSUGGESTDATE) && !this.isResetSuggestDate()) {
              continue;
            }
            UFDate demdate =
                (UFDate) keyValue
                    .getBodyValue(rows[i], PraybillItemVO.DREQDATE);
            UFDate dsuggestdate = demdate.getDateBefore(nDays);
            if (billDate_b.after(dsuggestdate)) {
              keyValue.setBodyValue(rows[i], PraybillItemVO.DSUGGESTDATE,
                  billDate_b);
            }
            else {
              if (!RuntimeEnv.getInstance().isRunningInServer()) {
                dsuggestdate = dsuggestdate.asLocalBegin();
              }
              keyValue.setBodyValue(rows[i], PraybillItemVO.DSUGGESTDATE,
                  dsuggestdate);
            }
          }

        }
      }
    }
  }

  /**
   * 设置需求日期和建议订货日期为当前日期 <b>参数说明</b>
   * 
   * @param keyValue
   * @param rows <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-20 上午09:46:05
   */
  private void setComDays(IKeyValue keyValue, int[] rows) {
    UFDate billDate = AppContext.getInstance().getBusiDate();
    UFDate billDate_e = billDate;
    if (!RuntimeEnv.getInstance().isRunningInServer()) {
      billDate_e = billDate_e.asLocalEnd();
    }
    for (int i = 0, len = rows.length; i < len; i++) {
      if (null == keyValue.getBodyValue(rows[i], PraybillItemVO.DREQDATE)) {
        keyValue.setBodyValue(rows[i], PraybillItemVO.DREQDATE, billDate_e);
      }

      if (null == keyValue.getBodyValue(rows[i], PraybillItemVO.DSUGGESTDATE)) {
        keyValue.setBodyValue(rows[i], PraybillItemVO.DSUGGESTDATE, billDate);
      }
    }
  }
}
