package nc.vo.pu.m23.pub;

import nc.vo.pu.m23.env.ArrivalUIToBSEnv;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pflow.PfUserObject;

/**
 * ��ƽ̨�Ĳ���VO�õ���������ǰ��̨�����Ļ�����Ϣ
 * 
 * @since 6.0
 * @version 2011-3-17 ����08:07:16
 * @author yinfy
 */

public class ArrivalEnvExtractUtil {
  /** ��ƽ̨�Ĳ���VO�õ���Ʊ��ǰ��̨�����Ļ�����Ϣ **/
  public static ArrivalUIToBSEnv getEnv(PfParameterVO pfvo) {
    ArrivalUIToBSEnv[] envs = ArrivalEnvExtractUtil.getEnvs(pfvo);
    if (null == envs) {
      return new ArrivalUIToBSEnv();
    }
    return envs[0];
  }

  /** ��ƽ̨�Ĳ���VO�õ���Ʊ��ǰ��̨�����Ļ�����Ϣ���� **/
  public static ArrivalUIToBSEnv[] getEnvs(PfParameterVO pfvo) {
    Object[] userObjs = pfvo.m_userObjs;
    if (userObjs instanceof ArrivalUIToBSEnv[]) {
      return (ArrivalUIToBSEnv[]) userObjs;
    }

    if (null == userObjs || 0 == userObjs.length) {
      Object userObj = pfvo.m_userObj;
      if (userObj instanceof ArrivalUIToBSEnv) {
        return new ArrivalUIToBSEnv[] {
          (ArrivalUIToBSEnv) userObj
        };
      }
      if (userObj instanceof PfUserObject) {
        userObjs = new PfUserObject[] {
          (PfUserObject) userObj
        };
      }
    }
    ArrivalUIToBSEnv[] envs = null;
    if (userObjs instanceof PfUserObject[]) {
      envs = new ArrivalUIToBSEnv[userObjs.length];
      for (int i = 0; i < userObjs.length; ++i) {
        if (((PfUserObject[]) userObjs)[i].getUserObject() instanceof ArrivalUIToBSEnv) {
          envs[i] =
              (ArrivalUIToBSEnv) ((PfUserObject[]) userObjs)[i].getUserObject();
        }
        else {
          envs[i] = new ArrivalUIToBSEnv();
        }
      }
    }
    return envs;
  }
}
