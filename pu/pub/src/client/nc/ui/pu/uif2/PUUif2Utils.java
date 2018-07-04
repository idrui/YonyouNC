package nc.ui.pu.uif2;

import java.awt.Component;
import java.lang.reflect.Field;
import java.util.Map;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.AbstractFunclet;
import nc.funcnode.ui.FuncletInitData;
import nc.ui.uap.sf.SFClientUtil2;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.uif2.LoginContext;

/**
 * �ɹ����Uif2������
 * 
 * @since 6.0
 * @version 2011-12-9 ����11:23:52
 * @author zhaoyha
 */
@SuppressWarnings("unchecked")
public class PUUif2Utils {

  /**
   * FuncRegisterVO�з����Զ�����������ö��
   */
  public static enum PUFRVOAttName {
    /** �ⲿ�򿪵Ľڵ�� **/
    sourrounding_funcode;

  }

  /**
   * ��context�еõ�FuncRegisterVO
   * 
   * @param context Logincontext
   * @return FuncRegisterVO�����context��û��AbstractFunclet���򷵻�null)
   */
  public static FuncRegisterVO getFuncRegisterVO(LoginContext context) {
    Object ui = context.getEntranceUI();
    if (ui instanceof AbstractFunclet) {
      return ((AbstractFunclet) ui).getFuncletContext().getFuncRegisterVO();
    }
    return null;
  }

  /**
   * ��FuncRegisterVO�õ��û�����ע�⴫��Ķ��������л���Զ��<br>
   * һ�����ڴ��ⲿ�����Դ򿪽ڵ㷽ʽչʾ�Ľ����д�����Ϣ��ƽ̨�Ľ����Ƿ�յģ���<br>
   * ��initdata֮ǰFuncRegisterVO�Ϳ��ã�<br>
   * �������ɴ���һЩ�����ʼ���Ȳ���
   * 
   * @param frvo FuncRegisterVO�ڵ�ע��VO
   * @param name Ҫ������������PUFRVOAttName
   * @return
   * @see ��ʵҲ���������������ʵ�֣���Ҫע���ڴ�й©
   * @see WorkbenchEnvironment#getClientCache(Object)
   */
  public static Object getUsrObjFromFRVO(FuncRegisterVO frvo, String name) {
    try {
      Map<String, Object> valueIndex = PUUif2Utils.getFRVOIndexMap(frvo);
      return valueIndex.get(name);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * ���ݽڵ�ţ��ԶԻ�����ʽ�򿪽ڵ㣬������ڵ��д���initData
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>���ڴ�һ��������ִ��ĳ��ҵ�������������ť����������һ���ڵ����
   * </ul>
   * 
   * @param parent ������
   * @param funcode Ҫ�򿪵Ľڵ��
   * @param initData ���绯����
   */
  public static void openFuncNodeDlg(Component parent, String funcode,
      FuncletInitData initData) {
    SFClientUtil2.openFuncNodeDialog(parent, funcode, initData, null, true,
        true);
  }

  /**
   * ��FuncRegisterVOע��һЩ�û�����ע�⴫��Ķ��������л���Զ��<br>
   * һ�����ڴ��ⲿ�����Դ򿪽ڵ㷽ʽչʾ�Ľ����д�����Ϣ��ƽ̨�Ľ����Ƿ�յģ���<br>
   * ��initdata֮ǰFuncRegisterVO�Ϳ��ã�<br>
   * �������ɴ���һЩ�����ʼ���Ȳ���
   * 
   * @param frvo FuncRegisterVO�ڵ�ע��VO
   * @param name Ҫ������������PUFRVOAttName
   * @param value �û�����
   * @see ��ʵҲ���������������ʵ�֣���Ҫע���ڴ�й©
   * @see WorkbenchEnvironment#putClientCache(Object, Object)
   */
  public static void setUsrObjToFRVO(FuncRegisterVO frvo, String name,
      Object value) {
    Map<String, Object> valueIndex = PUUif2Utils.getFRVOIndexMap(frvo);
    valueIndex.put(name, value);
  }

  private static Map<String, Object> getFRVOIndexMap(FuncRegisterVO frvo) {
    try {
      Field field = SuperVO.class.getDeclaredField("valueIndex");
      field.setAccessible(true);
      Map<String, Object> valueIndex = (Map<String, Object>) field.get(frvo);
      return valueIndex;
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

}
