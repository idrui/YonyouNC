/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-19 上午10:39:28
 */
package nc.vo.pu.m20.rule;

import java.util.ArrayList;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置采购员
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-19 上午10:39:28
 */
public class SetEmployee {
  /**
   * 方法功能描述：设置采购员。
   * <p>
   * <b>参数说明</b>
   * 
   * @param keyValue
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 下午04:28:14
   */
  public void setEmployee(IKeyValue keyValue) {
    int[] rows = this.getRows(keyValue);

    this.setEmployee(keyValue, rows);
  }

  /**
   * 询采购岗设置采购员。
   * <p>
   * <b>参数说明</b>
   * 
   * @param keyValue
   * @param rows
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-19 上午10:41:09
   */
  public void setEmployee(IKeyValue keyValue, int[] rows) {

    Map<String, String> employees = this.getPurchaser(keyValue, rows);
    if (null == employees || employees.size() == 0) {
      return;
    }

    for (int i = 0, len = rows.length; i < len; i++) {
      String pk_org =
          (String) keyValue
              .getBodyValue(rows[i], PraybillItemVO.PK_PURCHASEORG);
      String pk_material =
          (String) keyValue.getBodyValue(rows[i], PraybillItemVO.PK_MATERIAL);

      if (null != pk_org && null != pk_material) {
        keyValue.setBodyValue(rows[i], PraybillItemVO.PK_EMPLOYEE,
            employees.get(pk_org + pk_material));
      }
    }

  }

  private Map<String, String> getPurchaser(IKeyValue keyValue, int[] rows) {

    ArrayList<String> pk_orgs = new ArrayList<String>();
    ArrayList<String> pk_materials = new ArrayList<String>();
    String pk_stock = (String) keyValue.getHeadValue(PraybillItemVO.PK_ORG);
    for (int i = 0, len = rows.length; i < len; i++) {
      String pk_org =
          (String) keyValue
              .getBodyValue(rows[i], PraybillItemVO.PK_PURCHASEORG);
      String pk_material =
          (String) keyValue.getBodyValue(rows[i], PraybillItemVO.PK_MATERIAL);

      if (null != pk_org && null != pk_material) {
        pk_orgs.add(pk_org);
        pk_materials.add(pk_material);
      }
    }

    try {
      return NCLocator
          .getInstance()
          .lookup(nc.pubitf.pu.position.IQueryPosition.class)
          .getPurchaser(pk_orgs.toArray(new String[pk_orgs.size()]),
              new String[] {
                pk_stock
              }, pk_materials.toArray(new String[pk_materials.size()]));
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
      return null;
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
