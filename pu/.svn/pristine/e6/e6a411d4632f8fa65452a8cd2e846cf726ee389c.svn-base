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
 * 采购组的Uif2工具类
 * 
 * @since 6.0
 * @version 2011-12-9 上午11:23:52
 * @author zhaoyha
 */
@SuppressWarnings("unchecked")
public class PUUif2Utils {

  /**
   * FuncRegisterVO中放置自定义属性名称枚举
   */
  public static enum PUFRVOAttName {
    /** 外部打开的节点号 **/
    sourrounding_funcode;

  }

  /**
   * 从context中得到FuncRegisterVO
   * 
   * @param context Logincontext
   * @return FuncRegisterVO（如果context中没有AbstractFunclet，则返回null)
   */
  public static FuncRegisterVO getFuncRegisterVO(LoginContext context) {
    Object ui = context.getEntranceUI();
    if (ui instanceof AbstractFunclet) {
      return ((AbstractFunclet) ui).getFuncletContext().getFuncRegisterVO();
    }
    return null;
  }

  /**
   * 从FuncRegisterVO得到用户对象，注意传入的对象不能序列化到远程<br>
   * 一般用于从外部，向以打开节点方式展示的界面中传递信息（平台的界面是封闭的），<br>
   * 在initdata之前FuncRegisterVO就可用，<br>
   * 基于它可处理一些界面初始化等操作
   * 
   * @param frvo FuncRegisterVO节点注册VO
   * @param name 要传递属性名称PUFRVOAttName
   * @return
   * @see 其实也可以用这个方法来实现，但要注意内存泄漏
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
   * 根据节点号，以对话框形式打开节点，并且向节点中传入initData
   * <p>
   * 使用场景：
   * <ul>
   * <li>用于从一个界面中执行某项业务操作（如点击按钮），打开另外一个节点界面
   * </ul>
   * 
   * @param parent 父窗口
   * @param funcode 要打开的节点号
   * @param initData 初如化数据
   */
  public static void openFuncNodeDlg(Component parent, String funcode,
      FuncletInitData initData) {
    SFClientUtil2.openFuncNodeDialog(parent, funcode, initData, null, true,
        true);
  }

  /**
   * 向FuncRegisterVO注入一些用户对象，注意传入的对象不能序列化到远程<br>
   * 一般用于从外部，向以打开节点方式展示的界面中传递信息（平台的界面是封闭的），<br>
   * 在initdata之前FuncRegisterVO就可用，<br>
   * 基于它可处理一些界面初始化等操作
   * 
   * @param frvo FuncRegisterVO节点注册VO
   * @param name 要传递属性名称PUFRVOAttName
   * @param value 用户对象
   * @see 其实也可以用这个方法来实现，但要注意内存泄漏
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
