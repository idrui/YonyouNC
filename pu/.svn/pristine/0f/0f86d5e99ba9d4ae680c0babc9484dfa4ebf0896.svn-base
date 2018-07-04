/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-30 下午02:54:23
 */
package nc.vo.pu.m21.pub;

import nc.vo.pu.m21.context.OrderContext;
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
 * @author wuxla
 * @time 2010-5-30 下午02:54:23
 */
public class OrderContextUtil {
  /**
   * 方法功能描述：从平台的参数VO得到订单的前后台交换的环境信息数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param pfvo
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-30 下午02:57:52
   */
  public static OrderContext[] getCtxs(PfParameterVO pfvo) {
    Object[] userObjs = pfvo.m_userObjs;
    if (userObjs instanceof OrderContext[]) {
      return (OrderContext[]) userObjs;
    }

    if (null == userObjs || 0 == userObjs.length) {
      Object userObj = pfvo.m_userObj;
      if (userObj instanceof OrderContext) {
        return new OrderContext[] {
          (OrderContext) userObj
        };
      }
      if (userObj instanceof PfUserObject) {
        userObjs = new PfUserObject[] {
          (PfUserObject) userObj
        };
      }
    }
    OrderContext[] envs = null;
    if (userObjs instanceof PfUserObject[]) {
      envs = new OrderContext[userObjs.length];
      for (int i = 0; i < userObjs.length; ++i) {
        if (((PfUserObject[]) userObjs)[i].getUserObject() instanceof OrderContext) {
          envs[i] =
              (OrderContext) ((PfUserObject[]) userObjs)[i].getUserObject();
        }
        else {
          envs[i] = new OrderContext();
        }
      }
    }
    return envs;
  }
}
