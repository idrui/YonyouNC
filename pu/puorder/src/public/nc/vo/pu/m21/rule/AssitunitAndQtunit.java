/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-5 下午09:36:04
 */
package nc.vo.pu.m21.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置单位和报价单位
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-5 下午09:36:04
 */
public class AssitunitAndQtunit {

  private IKeyValue keyValue;

  public AssitunitAndQtunit(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setAssistunitAndQtunit(int[] rows) {
    String[] materials = this.getMaterials(rows);
    if (ArrayUtils.isEmpty(materials)) {
      return;
    }

    Map<String, String> assistunitMap = null;
    assistunitMap = MaterialPubService.queryPuMeasdocIDByPks(materials);

    this.setAssistunitAndQtunit(rows, assistunitMap);
  }

  /**
   * 方法功能描述：取得表体物料pk
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-10 下午02:53:59
   */
  private String[] getMaterials(int[] rows) {
    Set<String> materialSet = new HashSet<String>();
    for (int row : rows) {
      String material =
          (String) this.keyValue.getBodyValue(row, OrderItemVO.PK_MATERIAL);
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
   * 方法功能描述：设置单位和报价单位。如果无辅单位，单位和报价单位取主单位，如果有辅单位，单位和报价单位取辅单位。
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   * @param assistunitMap
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-10 下午03:04:11
   */
  private void setAssistunitAndQtunit(int[] rows,
      Map<String, String> assistunitMap) {
    for (int row : rows) {
      String material =
          (String) this.keyValue.getBodyValue(row, OrderItemVO.PK_MATERIAL);
      String assistunit = assistunitMap.get(material);
      if (null == assistunit) {
        String unit =
            (String) this.keyValue.getBodyValue(row, OrderItemVO.CUNITID);
        this.keyValue.setBodyValue(row, OrderItemVO.CASTUNITID, unit);
        this.keyValue.setBodyValue(row, OrderItemVO.CQTUNITID, unit);
      }
      else {
        this.keyValue.setBodyValue(row, OrderItemVO.CASTUNITID, assistunit);
        this.keyValue.setBodyValue(row, OrderItemVO.CQTUNITID, assistunit);
      }
    }
  }

}
