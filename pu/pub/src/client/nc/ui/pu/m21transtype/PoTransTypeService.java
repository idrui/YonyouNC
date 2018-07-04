package nc.ui.pu.m21transtype;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.ISmartService;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;

/**
 * 采购订单交易类型服务类
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>查询采购订单交易类型
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 上午10:48:51
 */
public class PoTransTypeService {
  private static ISmartService service = (ISmartService) NCLocator
      .getInstance().lookup(ISmartService.class.getName());

  /**
   * 方法功能描述：查询采购订单交易类型。
   * <p>
   * <b>参数说明</b>
   * 
   * @param wherePart
   *          查询条件
   * @return 采购订单交易类型
   * @throws Exception
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 上午10:48:55
   */
  public static PoTransTypeVO[] queryTranstypeExtProp(String wherePart)
      throws Exception {
    return (PoTransTypeVO[]) PoTransTypeService.service.selectByWhereSql(
        wherePart, PoTransTypeVO.class);
  }
}
