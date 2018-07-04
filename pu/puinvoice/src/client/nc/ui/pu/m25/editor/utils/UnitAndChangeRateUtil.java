/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-23 下午03:34:09
 */
package nc.ui.pu.m25.editor.utils;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>单位的工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-26
 */
public class UnitAndChangeRateUtil {

  public static String getChangeRate(int row, CardEditorHelper util) {
    String unit = util.getBodyStringValue(row, InvoiceItemVO.CUNITID);
    String astunit = util.getBodyStringValue(row, InvoiceItemVO.CASTUNITID);
    ScaleUtils su = new ScaleUtils(AppContext.getInstance().getPkGroup());
    String changeRate = su.adjustHslScale("1/1");
    if (unit.equals(astunit)) {
      return changeRate;
    }
    String pk_material =
        util.getBodyStringValue(row, InvoiceItemVO.PK_MATERIAL);
    String newChangeRate =
        MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(pk_material,
            astunit);
    return null != newChangeRate ? su.adjustHslScale(newChangeRate)
        : changeRate;
  }

  public static boolean isFixedChangeRate(String pk_material, String astUnit) {
    boolean isFixedChangeRate = true;
    isFixedChangeRate =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(pk_material,
            astUnit);
    return isFixedChangeRate;
  }

  public static void setChangeRate(CardEditorHelper util, int[] rows) {
    if (ArrayUtils.isEmpty(rows)) {
      return;
    }
    String oldChangeRate = null;
    String newChangeRate = null;
    for (int i = 0; i < rows.length; i++) {
      oldChangeRate =
          util.getBodyStringValue(rows[i], InvoiceItemVO.VCHANGERATE);
      newChangeRate = UnitAndChangeRateUtil.getChangeRate(rows[i], util);
      UFDouble oldnum =
          util.getBodyUFDoubleValue(rows[i], InvoiceItemVO.NASTNUM);
      util.setBodyValue(rows[i], InvoiceItemVO.NASTNUM, oldnum);
      if (newChangeRate.equals(oldChangeRate)) {
        // 为了处理精度，也要重算，根据优先策略，从金额发起联动计算
        String pk_purorg =
            util.getHeadStringValue(InvoiceHeaderVO.PK_PURCHASEORG);
        if (PUSysParamUtil.getPO28For25(pk_purorg)) {
          new RelationCalculate().calculate(util.getEditor(), new int[] {
            rows[i]
          }, InvoiceItemVO.NORIGTAXMNY);
        }
        else {
          new RelationCalculate().calculate(util.getEditor(), new int[] {
            rows[i]
          }, InvoiceItemVO.NORIGMNY);
        }
      }
      else {
        util.setBodyValue(rows[i], InvoiceItemVO.VCHANGERATE, newChangeRate);
        new RelationCalculate().calculate(util.getEditor(), new int[] {
          rows[i]
        }, InvoiceItemVO.VCHANGERATE);
      }
    }
  }
}
