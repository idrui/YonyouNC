/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-6-1 下午03:37:07
 */
package nc.ui.pu.m25trantype;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.ISmartService;
import nc.vo.pu.m25trantype.entity.InvcTranTypeVO;

/**
 * 采购发票交易类型扩展属性服务类
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>查询采购发票交易类型扩展属性
 * </ul>
 * <p>
 * <p>
 * 
 * @version v60
 * @since v60
 * @author zhaoyha
 * @time 2009-6-1 下午03:37:07
 */
public class InvcTranTypeService {
  private static ISmartService service = (ISmartService) NCLocator
      .getInstance().lookup(ISmartService.class.getName());

  /**
   * 方法功能描述：查询采购发票交易类型扩展属性。
   * <p>
   * <b>参数说明</b>
   * 
   * @param wherePart
   *          查询条件
   * @return 采购发票交易类型扩展属性
   * @throws Exception
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 上午11:00:16
   */
  public static InvcTranTypeVO[] queryTranstypeExtProp(String wherePart)
      throws Exception {
    return (InvcTranTypeVO[]) InvcTranTypeService.service.selectByWhereSql(
        wherePart, InvcTranTypeVO.class);
  }
}
