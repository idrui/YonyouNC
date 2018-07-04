package nc.ui.pu.m23.editor.card.utils;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.StringUtils;

public class ChangeRateUtil {

  /**
   * 方法功能描述：设置“是否固定换算率”、“换算率”
   * <p>
   * <b>参数说明</b>
   * 
   * @param card
   * @param rowNo
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-2-5 上午11:31:39
   */
  public static void setChgRateAndFixedFlag(BillCardPanel card, int rowNo) {
    // 物料主键
    String key = ArriveItemVO.PK_MATERIAL;
    String pk_material = ArriveClientUtil.getStringCellValue(card, rowNo, key);
    if (StringUtils.isEmpty(pk_material)) {
      return;
    }

    // 单位、业务单位
    String cunitid =
        ArriveClientUtil.getStringCellValue(card, rowNo, ArriveItemVO.CUNITID);
    String key2 = ArriveItemVO.CASTUNITID;
    String castunitid = ArriveClientUtil.getStringCellValue(card, rowNo, key2);

    if (StringUtils.isEmpty(castunitid)) {
      return;
    }

    // 是否固定换算率、换算率
    boolean bfixed = false;
    String changeRate = null;
    if (cunitid.equals(castunitid)) {
      bfixed = true;
      changeRate = "1/1";
    }
    else {
      bfixed =
          MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(pk_material,
              castunitid);

      changeRate =
          MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(pk_material,
              castunitid);
    }
    card.setBodyValueAt(UFBoolean.valueOf(bfixed), rowNo,
        ArriveItemVO.BFIXEDRATE);
    card.setBodyValueAt(changeRate, rowNo, ArriveItemVO.VCHANGERATE);
  }
}
