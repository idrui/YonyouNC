/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-18 下午06:39:00
 */
package nc.ui.pu.reference.ct;

import nc.pubitf.ct.purdaily.puorder.IRelatePurCT;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.vo.ct.entity.CtRelatingVO;
import nc.vo.ct.purdaily.entity.CtPubillViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.res.NCModule;
import nc.vo.util.remotecallcombination.RemoteCallCombinatorEx;
import nc.vo.util.remotecallcombination.Token;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>合同
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-18 下午06:39:00
 */
public class Z2CTUIServices {

  /**
   * 方法功能描述：关联合同
   * <p>
   * <b>参数说明</b>
   * 
   * @param rVOs
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-17 下午08:58:09
   */
  public static Token register_relateCT(CtRelatingVO[] rVOs)
      throws BusinessException {

    RemoteCallCombinatorEx rcc = RemoteCallCombinatorEx.getInstance();
    IRelatePurCT service = rcc.getService(IRelatePurCT.class);
    service.relateCT(rVOs);

    return rcc.getToken();
  }

  /**
   * 方法功能描述：关联合同
   * <p>
   * <b>参数说明</b>
   * 
   * @param rVOs
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-17 下午08:58:09
   */
  public static CtPubillViewVO[] relateCT(CtRelatingVO[] rVOs)
      throws BusinessException {
    return Z2CTUIServices.relateCT(rVOs, null);
  }

  /**
   * 方法功能描述：关联合同
   * <p>
   * <b>参数说明</b>
   * 
   * @param rVOs
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-17 下午08:58:09
   */
  public static CtPubillViewVO[] relateCT(CtRelatingVO[] rVOs, Token t)
      throws BusinessException {
    if (t != null) {
      return (CtPubillViewVO[]) RemoteCallCombinatorEx.getInstance().getResult(
          t);
    }
    IRelatePurCT service =
        NCUILocator.getInstance().lookup(IRelatePurCT.class,
            NCModule.CT.getName());
    return service.relateCT(rVOs);
  }
}
