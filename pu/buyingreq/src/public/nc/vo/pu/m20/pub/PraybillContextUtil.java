package nc.vo.pu.m20.pub;

import nc.vo.pu.m20.context.PraybillContext;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pflow.PfUserObject;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ƽ̨�Ĳ���VO�õ�������ǰ��̨�����Ļ�����Ϣ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author yangtian
 * @time 2012-5-22 ����02:54:23
 */
public class PraybillContextUtil {
  /**
   * ����������������ƽ̨�Ĳ���VO�õ�������ǰ��̨�����Ļ�����Ϣ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param pfvo
   * @return <p>
   * @since 6.0
   * @author yangtian
   * @time 2012-5-22 ����02:57:52
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
