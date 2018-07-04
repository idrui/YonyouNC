package nc.vo.pu.m23.rule;

import java.util.ArrayList;
import java.util.List;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>用于保存时，检查是否存在结算利润中心有值，收货利润中心为空
 * </ul>
 * <p>
 * <p>
 * 
 * @since 6.0
 * @version 2014-9-11 上午9:50:57
 * @author luojw
 */

public class ChkLiabcenterWhenSave {

  public void chkTwoCenter(ArriveVO vo) {
    ArriveItemVO[] items = vo.getBVO();
    List<String> rows = new ArrayList<String>();
    for (ArriveItemVO item : items) {
      Object apliabcenter =
          item.getAttributeValue(ArriveItemVO.PK_APLIABCENTER_V);
      Object arrliabcenter =
          item.getAttributeValue(ArriveItemVO.PK_ARRLIABCENTER_V);
      if (apliabcenter != null & arrliabcenter == null) {
        rows.add(item.getCrowno());
      }
    }
    if (rows.size() > 0) {
      StringBuilder builder = new StringBuilder();
      for (String row : rows) {
        String message =
            NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0211", null, new String[] {
                  row
                })/* 第{0}行不允许结算利润中心有值，收货利润中心为空！ */;
        builder.append(message);
      }
      ExceptionUtils.wrappBusinessException(builder.toString());
    }
  }

}
