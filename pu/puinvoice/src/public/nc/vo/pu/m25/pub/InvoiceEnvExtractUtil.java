/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-11 ����09:45:53
 */
package nc.vo.pu.m25.pub;

import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pflow.PfUserObject;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ƽ̨�Ĳ���VO�õ���Ʊ��ǰ��̨�����Ļ�����Ϣ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-11 ����09:45:53
 */
public class InvoiceEnvExtractUtil {

  /** ��ƽ̨�Ĳ���VO�õ���Ʊ��ǰ��̨�����Ļ�����Ϣ **/
  public static InvoiceUIToBSEnv getEnv(PfParameterVO pfvo) {
    InvoiceUIToBSEnv[] envs = InvoiceEnvExtractUtil.getEnvs(pfvo);
    if (null == envs) {
      return new InvoiceUIToBSEnv();
    }
    return envs[0];
  }

  /** ��ƽ̨�Ĳ���VO�õ���Ʊ��ǰ��̨�����Ļ�����Ϣ���� **/
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
