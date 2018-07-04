package nc.vo.pu.m4t.util;

import nc.vo.pu.m4t.entity.InitialEstContext;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pflow.PfUserObject;

/**
 * @since 6.0
 * @version 2011-3-11 ÏÂÎç06:35:29
 * @author wuxla
 */

public class InitialContextUtil {
  public static InitialEstContext[] getCtxs(PfParameterVO pfvo) {
    Object[] userObjs = pfvo.m_userObjs;
    if (userObjs instanceof InitialEstContext[]) {
      return (InitialEstContext[]) userObjs;
    }

    if (null == userObjs || 0 == userObjs.length) {
      Object userObj = pfvo.m_userObj;
      if (userObj instanceof InitialEstContext) {
        return new InitialEstContext[] {
          (InitialEstContext) userObj
        };
      }
      if (userObj instanceof PfUserObject) {
        userObjs = new PfUserObject[] {
          (PfUserObject) userObj
        };
      }
    }
    InitialEstContext[] envs = null;
    if (userObjs instanceof PfUserObject[]) {
      envs = new InitialEstContext[userObjs.length];
      for (int i = 0; i < userObjs.length; ++i) {
        if (((PfUserObject[]) userObjs)[i].getUserObject() instanceof InitialEstContext) {
          envs[i] =
              (InitialEstContext) ((PfUserObject[]) userObjs)[i]
                  .getUserObject();
        }
        else {
          envs[i] = new InitialEstContext();
        }
      }
    }
    return envs;
  }
}
