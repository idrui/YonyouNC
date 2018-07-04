package nc.vo.pu.m20.rule;

import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.vo.bd.supplier.stock.SupStockVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.ObjectUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购员为空时设置采购员为供应商专管业务员
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-19 上午10:39:28
 */
public class SetEmployeeBySupplier {
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

    for (int i = 0, len = rows.length; i < len; i++) {
      if (ObjectUtil.isEmptyWithTrim(keyValue.getBodyValue(rows[i],
          PraybillItemVO.PK_EMPLOYEE))) {

        String pk_org =
            (String) keyValue.getBodyValue(rows[i],
                PraybillItemVO.PK_PURCHASEORG);
        String pk_supplier =
            (String) keyValue.getBodyValue(rows[i],
                PraybillItemVO.PK_SUGGESTSUPPLIER);

        SupStockVO[] vos;
        vos = SupplierPubService.getSupStockVO(new String[] {
          pk_supplier
        }, pk_org, new String[] {
          SupStockVO.PK_SUPPLIER, SupStockVO.RESPPERSON
        });
        if (!ArrayUtils.isEmpty(vos)) {
          keyValue.setBodyValue(rows[i], PraybillItemVO.PK_EMPLOYEE,
              vos[0].getRespperson());
        }
      }

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
