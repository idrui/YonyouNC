/**
 * 
 */
package nc.ui.pu.mtrantype;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.ISmartService;
import nc.vo.pub.ISuperVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version
 * @since
 * @author luojw
 * @time 2014-6-18 下午8:26:45
 */
public class TranTypeService {

  private static ISmartService service = NCLocator.getInstance().lookup(
      ISmartService.class);

  @SuppressWarnings("unchecked")
  public static <T extends ISuperVO> T[] queryTranstypeExtProp(Class<T> clazz,
      String wherePart) throws Exception {
    return (T[]) TranTypeService.service.selectByWhereSql(wherePart, clazz);
  }

}
