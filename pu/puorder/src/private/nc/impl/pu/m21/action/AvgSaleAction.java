/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-23 下午02:15:28
 */
package nc.impl.pu.m21.action;

import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.itf.pu.reference.ic.M4CPUServices;
import nc.itf.pu.reference.so.M30SOServices;
import nc.itf.pu.reference.so.M32SOServices;
import nc.vo.pu.m21.entity.AvgSaleQueryVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销量查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-23 下午02:15:28
 */
public class AvgSaleAction {

  /**
   * 销量查询
   * 
   * @param vos
   * @return
   */
  public AvgSaleQueryVO[] querySaleData(AvgSaleQueryVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    String[] cmaterialoid = new String[vos.length];
    for (int i = 0; i < vos.length; ++i) {
      cmaterialoid[i] = vos[i].getPk_srcmaterial();
    }
    UFDate queryDate = vos[0].getDquerydate();
    Integer queryDay = vos[0].getIqueryday();
    String pk_group = BSContext.getInstance().getGroupID();
    String pk_org = vos[0].getPk_org();

    Map<String, UFDouble> invoutMap =
        M4CPUServices.getInvOutNumber(cmaterialoid, queryDate, queryDay,
            pk_group, pk_org);
    Map<String, UFDouble> saleOrderMap =
        M30SOServices.getSaleOrderNumber(cmaterialoid, queryDate, queryDay,
            pk_group, pk_org);
    Map<String, UFDouble> saleInvoiceMap =
        M32SOServices.getInvInvoiceNumber(cmaterialoid, queryDate, queryDay,
            pk_group, pk_org);

    AvgSaleQueryVO[] results = new AvgSaleQueryVO[vos.length];
    for (int i = 0; i < vos.length; ++i) {
      String pk_srcmaterial = vos[i].getPk_srcmaterial();
      results[i] = new AvgSaleQueryVO();
      results[i].setPk_material(vos[i].getPk_material());
      results[i].setPk_srcmaterial(pk_srcmaterial);
      results[i].setDquerydate(vos[i].getDquerydate());
      results[i].setIqueryday(vos[i].getIqueryday());
      results[i].setCunitid(vos[i].getCunitid());
      if (saleInvoiceMap != null) {
        results[i].setNinvoicenum(saleInvoiceMap.get(pk_srcmaterial));
      }
      if (saleOrderMap != null) {
        results[i].setNordernum(saleOrderMap.get(pk_srcmaterial));
      }
      if (invoutMap != null) {
        results[i].setNoutnum(invoutMap.get(pk_srcmaterial));
      }
    }

    return results;
  }
}
