package nc.ui.pubapp.plugin.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Action;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.action.ActionContainer;
import nc.funcnode.ui.action.INCAction;
import nc.funcnode.ui.action.SeparatorAction;
import nc.itf.uap.bbd.func.IFuncRegisterService;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ToftPanelAdaptor;
import nc.ui.uif2.actions.IActionContainer;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.sm.funcreg.ButtonRegVO;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;

/**
 * 采购组前台测试用例运行按钮（借用pubapp的壳子）
 * 
 * @since 6.0
 * @version 2011-12-29 下午1:58:40
 * @author zhaoyha
 */
public class ActionAutoRegistAction extends NCAction implements
    BeanFactoryAware {
  private static final long serialVersionUID = 2901455944241062881L;

  private ListableBeanFactory factory;

  public ActionAutoRegistAction() {
    super();
    this.setBtnName("采购前台测试");/* -=notranslate=- */
    this.setCode("brAction");
    this.putValue(Action.SHORT_DESCRIPTION, "采购组前台测试用例运行按钮");/* -=notranslate=- */
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // originProcess(); //pubapp的按钮注册原功能
    // 使用查询方案打开节点的测试
    // OpennodeByQueryschemeTest oqst = new OpennodeByQueryschemeTest();
    // IVendorMaterialQueryForPimTest test = new
    // IVendorMaterialQueryForPimTest();
    // test.testVendorMaterial();
    // 请购单修订节点

    // oqst.testOpen20revise();
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) {
    if (this.isDevelopMode()) {
      this.factory = (ListableBeanFactory) beanFactory;
      this.plugAction();
    }
  }

  private ButtonRegVO createBtnRegVO(Action action) {
    String fun_name = (String) action.getValue(Action.NAME);
    String class_name = (String) action.getValue(INCAction.CODE);
    String btn_des = (String) action.getValue(Action.SHORT_DESCRIPTION);
    LoginContext context = (LoginContext) this.factory.getBean("context");
    ToftPanelAdaptor tpAdaptor = (ToftPanelAdaptor) context.getEntranceUI();
    String funid =
        tpAdaptor.getFuncletContext().getFuncRegisterVO().getCfunid();
    ButtonRegVO tButtonRegVO = new ButtonRegVO();
    tButtonRegVO.setBtnname(fun_name);
    tButtonRegVO.setBtncode(class_name);
    tButtonRegVO.setBtnownertype(Integer.valueOf(0));
    tButtonRegVO.setIsbuttonpower(UFBoolean.FALSE);
    tButtonRegVO.setIsenable(UFBoolean.TRUE);
    tButtonRegVO.setIskeyfunc(UFBoolean.FALSE);
    tButtonRegVO.setParent_id(funid);
    tButtonRegVO.setBtndesc(btn_des);
    return tButtonRegVO;
  }

  private boolean isDevelopMode() {
    return !StringUtils.isBlank(System.getProperty("nc.runMode"));
  }

  private void originProcess() throws BusinessException {
    Map<String, NCAction> ncMap =
        BeanFactoryUtils.beansOfTypeIncludingAncestors(this.factory,
            NCAction.class);
    Collection<NCAction> actions = ncMap.values();
    Map<String, Action> actionMap = new HashMap<String, Action>();

    for (NCAction action : actions) {
      if (action.getClass() == this.getClass()) {
        continue;
      }
      if (!actionMap.containsKey(action.getValue(INCAction.CODE))) {
        actionMap.put((String) action.getValue(INCAction.CODE), action);
      }
    }

    // 针对menubean、groupbean处理
    this.registerActionContner(actionMap);

    // 卡片界面表体扩展按钮
    this.registerExtendAction(actionMap);

    ButtonRegVO[] buttonRegVO = new ButtonRegVO[actionMap.values().size()];
    Collection<Action> ncactions = actionMap.values();
    int i = 0;

    for (Action action : ncactions) {
      buttonRegVO[i++] = this.createBtnRegVO(action);
    }

    IFuncRegisterService tIFuncRegisterService =
        NCLocator.getInstance().lookup(IFuncRegisterService.class);
    tIFuncRegisterService.insertButtonArray(buttonRegVO);

    LoginContext context = (LoginContext) this.factory.getBean("context");
    ToftPanelAdaptor tpAdaptor = (ToftPanelAdaptor) context.getEntranceUI();
    MessageDialog.showHintDlg(tpAdaptor, "注册成功", "共自动注册 "/* -=notranslate=- */
        + buttonRegVO.length + " 个按钮，请到“功能注册”节点确认");/* -=notranslate=- */
  }

  private void plugAction() {
    IActionContainer container =
        (IActionContainer) this.factory.getBean("container");
    List<Action> actions = new ArrayList<Action>(container.getActions());
    actions.add(0, this);
    container.setActions(actions);
  }

  private void registerActionContner(Map<String, Action> actionMap) {
    // Map<String, ActionContainer> containerMap =
    // this.factory.getBeansOfType(ActionContainer.class);
    Map<String, ActionContainer> containerMap =
        BeanFactoryUtils.beansOfTypeIncludingAncestors(this.factory,
            ActionContainer.class);
    Collection<ActionContainer> actions = containerMap.values();
    for (ActionContainer action : actions) {
      Action[] childs = action.getAllChild();
      for (Action child : childs) {
        if (child instanceof SeparatorAction) {
          continue;
        }
        if (!actionMap.containsKey(child.getValue(INCAction.CODE))) {
          actionMap.put((String) child.getValue(INCAction.CODE),
              (NCAction) child);
        }
      }
    }
  }

  private void registerExtendAction(Map<String, Action> actionMap) {
    // Map<String, BillForm> billformMap =
    // this.factory.getBeansOfType(BillForm.class);
    Map<String, BillForm> billformMap =
        BeanFactoryUtils.beansOfTypeIncludingAncestors(this.factory,
            BillForm.class);
    Collection<BillForm> billformc = billformMap.values();
    if (billformc.size() > 0) {
      Object[] objects = billformc.toArray();
      for (Object object : objects) {
        for (Action extendAction : ((BillForm) object).getBodyLineActions()) {
          if (!actionMap.containsKey(extendAction.getValue(INCAction.CODE))) {
            actionMap.put((String) extendAction.getValue(INCAction.CODE),
                extendAction);
          }
        }
      }
    }
  }
}
