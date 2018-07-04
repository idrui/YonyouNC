package nc.itf.pu.reference.qc;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.qc.checkitem.IQueryCheckItem;
import nc.vo.pub.BusinessException;
import nc.vo.qc.checkitem.entity.CheckItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>检验项目提供的服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-8-13 上午10:38:22
 */
public class CheckItemServices {

  /**
   * 方法功能描述：根据检验项目pk查询检验项目VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_checkitems
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-13 上午10:38:22
   */
  public static Map<String, CheckItemVO> queryCheckItem(String[] pk_checkitems)
      throws BusinessException {
    if (!SysInitGroupQuery.isQCEnabled()) {
      return new HashMap<String, CheckItemVO>();
    }
    IQueryCheckItem service =
        NCLocator.getInstance().lookup(IQueryCheckItem.class);
    return service.queryCheckItem(pk_checkitems);

  }
}
