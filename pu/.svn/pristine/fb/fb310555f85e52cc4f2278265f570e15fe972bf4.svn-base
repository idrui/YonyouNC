package nc.vo.pu.m20.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>1: 设置单位以关于单位取值，
 * <li>如果物料有默认库存默认单位， 那么单位就取库存默认单位 如果没有库存默认单位，那么单位取主单位值
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author liuchx
 * @time 2010-7-29 下午12:00:49
 */
public class CastunitidUtil {

  // 通过构造子把CardEditorHelper传到util里面使用，其中
  // CardEditorHelper是IKeyValue的实现类
  private IKeyValue keyValue;

  public CastunitidUtil(IKeyValue cardEditorHelper) {
    this.keyValue = cardEditorHelper;
  }

  /**
   * 方法功能描述：设置单位和单位与主单位换算率
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author liuchx
   * @time 2010-7-29 下午12:06:05
   */
  public void setCastUnit(int[] rows) {
    // 物料pk数组
    String[] materials = this.getMaterialPks(rows);
    if (ArrayUtils.isEmpty(materials)) {
      return;
    }
    // 批量取库存默认计量单位
    Map<String, String> assistunitMap = null;

    // bugID:NCdp203826724根据61需求这样改，57也是这么做的
    assistunitMap = MaterialPubService.queryPuMeasdocIDByPks(materials);
    // 把单位到界面
    this.setAssistunitAndQtunit(rows, assistunitMap);
  }

  private String[] getMaterialPks(int[] rows) {
    // 物料pk
    Set<String> materialSet = new HashSet<String>();
    for (int i : rows) {
      String pk =
          (String) this.keyValue.getBodyValue(i, PraybillItemVO.PK_MATERIAL);
      if (!StringUtil.isEmptyWithTrim(pk)) {
        materialSet.add(pk);
      }
    }
    if (materialSet.size() > 0) {
      return materialSet.toArray(new String[materialSet.size()]);
    }
    return null;
  }

  /**
   * 方法功能描述：设置单位
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   * @param assistunitMap
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-7-29 下午12:55:22
   */
  private void setAssistunitAndQtunit(int[] rows,
      Map<String, String> assistunitMap) {
    for (int row : rows) {
      Object material =
          this.keyValue.getBodyValue(row, PraybillItemVO.PK_MATERIAL);
      String assistunit = assistunitMap.get(material);
      if (null == assistunit) {
        String unit =
            (String) this.keyValue.getBodyValue(row, PraybillItemVO.CUNITID);
        this.keyValue.setBodyValue(row, PraybillItemVO.CASTUNITID, unit);
      }
      else {
        this.keyValue.setBodyValue(row, PraybillItemVO.CASTUNITID, assistunit);
      }

    }

  }

}
