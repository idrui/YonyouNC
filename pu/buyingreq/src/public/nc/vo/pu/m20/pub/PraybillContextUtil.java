package nc.vo.pu.m20.pub;

import nc.vo.pu.m20.context.PraybillContext;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pflow.PfUserObject;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>从平台的参数VO得到订单的前后台交换的环境信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author yangtian
 * @time 2012-5-22 下午02:54:23
 */
public class PraybillContextUtil {
  /**
   * 方法功能描述：从平台的参数VO得到订单的前后台交换的环境信息数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param pfvo
   * @return <p>
   * @since 6.0
   * @author yangtian
   * @time 2012-5-22 下午02:57:52
   */
  public static PraybillContext[] getPtxs(PfParameterVO pfvo) {
    if (pfvo.m_userObj == null) {
      return null;
    }

    if (pfvo.m_userObj instanceof PfUserObject) {
      PfUserObject userObj = (PfUserObject) pfvo.m_userObj;
      if (userObj.getUserObject() instanceof PraybillContext) {
        return new PraybillContext[] {
          (PraybillContext) userObj.getUserObject()
        };
      }
    }
    return null;
  }
}
