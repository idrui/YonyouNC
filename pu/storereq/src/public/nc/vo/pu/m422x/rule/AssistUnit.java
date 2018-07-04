/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-13 下午02:25:23
 */
package nc.vo.pu.m422x.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

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
 * @time 2010-8-13 下午02:25:23
 */
public class AssistUnit {
  private IKeyValue keyValue;

  public AssistUnit(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-13 下午02:26:44
   */
  public void setAssistUnit(int[] rows) {
    String[] materials = this.getMaterials(rows);
    if (ArrayUtils.isEmpty(materials)) {
      return;
    }

    Map<String, String> assistunitMap =
        MaterialPubService.queryPuMeasdocIDByPks(materials);

    this.setAssistunit(rows, assistunitMap);
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-13 下午02:26:24
   */
  private String[] getMaterials(int[] rows) {
    Set<String> materialSet = new HashSet<String>();
    for (int row : rows) {
      String material =
          (String) this.keyValue.getBodyValue(row,
              StoreReqAppItemVO.PK_MATERIAL);
      if (!StringUtil.isEmptyWithTrim(material)) {
        materialSet.add(material);
      }
    }

    if (materialSet.size() > 0) {
      return materialSet.toArray(new String[materialSet.size()]);
    }

    return null;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   * @param assistunitMap <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-13 下午02:27:25
   */
  private void setAssistunit(int[] rows, Map<String, String> assistunitMap) {
    for (int row : rows) {
      String material =
          (String) this.keyValue.getBodyValue(row,
              StoreReqAppItemVO.PK_MATERIAL);
      String assistunit = assistunitMap.get(material);
      if (null == assistunit) {
        String unit =
            (String) this.keyValue.getBodyValue(row, StoreReqAppItemVO.CUNITID);
        this.keyValue.setBodyValue(row, StoreReqAppItemVO.CASTUNITID, unit);
      }
      else {
        this.keyValue.setBodyValue(row, StoreReqAppItemVO.CASTUNITID,
            assistunit);
      }
    }
  }
}
