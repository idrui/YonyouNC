/**
 * $文件说明$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-23 下午01:51:58
 */
package nc.itf.pu.reference.ic;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.atp.IAtpQuery;
import nc.pubitf.ic.onhand.IOnhandQry;
import nc.vo.ic.atp.entity.AtpVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>ATP查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-10-23 下午01:51:58
 */
public class AtpQueryServices {

  /**
   * 方法功能描述：按计划日期查询Atp完整信息 ，若计划日期为空，则与查询可用量报表返回信息一致
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_calbodyoids
   * @param cmaterialoids
   * @param ddates
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-10-23 下午01:53:09
   */
  public static AtpVO[] queryAtpVOs(String[] pk_calbodyoids,
      String[] cmaterialoids, UFDate[] ddates) {

    IAtpQuery service = NCLocator.getInstance().lookup(IAtpQuery.class);
    try {
      return service.queryAtpVOs(pk_calbodyoids, cmaterialoids, ddates);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return null;
  }

  /**
   * 物料所在库存组织的现存量，不考虑冻结，预留，库存状态是否可用，仓库是否计划可用
   * 
   * @param pk_org 库存组织数组，和物料参数不要求一一对应
   * @param cmaterialvid　物料VID数组
   * @return key:pk_org+cmaterialvid;value:nonhandnum
   */
  public static Map<String, UFDouble> queryInvOnhandnum(String[] pk_org,
      String[] cmaterialvid) {
    try {
      return NCLocator.getInstance().lookup(IOnhandQry.class)
          .queryInvOnhandnum(pk_org, cmaterialvid);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  public static Map<String, UFDouble> queryVendorStoreDatasByOid(
      String[] cvendorid, String cmaterialoid) {
    IOnhandQry service = NCLocator.getInstance().lookup(IOnhandQry.class);
    try {
      return service.queryVendorStoreDatasByOid(cvendorid, cmaterialoid);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
