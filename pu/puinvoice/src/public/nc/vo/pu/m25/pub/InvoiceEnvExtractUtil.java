/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-11 上午09:45:53
 */
package nc.vo.pu.m25.pub;

import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pflow.PfUserObject;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>从平台的参数VO得到发票的前后台交换的环境信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-11 上午09:45:53
 */
public class InvoiceEnvExtractUtil {

  /** 从平台的参数VO得到发票的前后台交换的环境信息 **/
  public static InvoiceUIToBSEnv getEnv(PfParameterVO pfvo) {
    InvoiceUIToBSEnv[] envs = InvoiceEnvExtractUtil.getEnvs(pfvo);
    if (null == envs) {
      return new InvoiceUIToBSEnv();
    }
    return envs[0];
  }

  /** 从平台的参数VO得到发票的前后台交换的环境信息数组 **/
  public static InvoiceUIToBSEnv[] getEnvs(PfParameterVO pfvo) {
    Object[] userObjs = pfvo.m_userObjs;
    if (userObjs instanceof InvoiceUIToBSEnv[]) {
      return (InvoiceUIToBSEnv[]) userObjs;
    }

    if ((null == userObjs) || (0 == userObjs.length)) {
      Object userObj = pfvo.m_userObj;
      if (userObj instanceof InvoiceUIToBSEnv) {
        return new InvoiceUIToBSEnv[] {
          (InvoiceUIToBSEnv) userObj
        };
      }
      if (userObj instanceof PfUserObject) {
        userObjs = new PfUserObject[] {
          (PfUserObject) userObj
        };
      }
    }
    InvoiceUIToBSEnv[] envs = null;
    if (userObjs instanceof PfUserObject[]) {
      envs = new InvoiceUIToBSEnv[userObjs.length];
      for (int i = 0; i < userObjs.length; ++i) {
        if (((PfUserObject[]) userObjs)[i].getUserObject() instanceof InvoiceUIToBSEnv) {
          envs[i] =
              (InvoiceUIToBSEnv) ((PfUserObject[]) userObjs)[i].getUserObject();
        }
        else {
          envs[i] = new InvoiceUIToBSEnv();
        }
      }
    }
    return envs;
  }
}
