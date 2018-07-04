package nc.vo.pu.m422x.context;

import java.io.Serializable;

import nc.bs.framework.common.IAttributeManager;
import nc.bs.framework.common.NCLocator;

/**
 * 物资需求申请单环境变量
 * 
 * @since 6.36
 * @version 2015-1-25 下午3:10:01
 * @author mengjian
 */
public class StorereqContext implements Serializable {

  private static StorereqContext context = new StorereqContext();

  private static final long serialVersionUID = 6360877810475110714L;

  // 预算控制数量回写检查标志
  private boolean bBudgetControlCheckFlag = false;

  private StorereqContext() {
  }

  public static StorereqContext getInstance() {
    return StorereqContext.context;
  }

  public Object getSession(String name) {
    return this.getAttributeManager().getAttribute(name);
  }

  public boolean isbBudgetControlCheckFlag() {
    return this.bBudgetControlCheckFlag;
  }

  public void setbBudgetControlCheckFlag(boolean bBudgetControlCheckFlag) {
    this.bBudgetControlCheckFlag = bBudgetControlCheckFlag;
  }

  public void setSession(String name, Object value) {
    this.getAttributeManager().setAttribute(name, value);
  }

  private IAttributeManager getAttributeManager() {
    String env = "nc.bs.framework.core.service.RequestAttributeManager";
    IAttributeManager manager =
        (IAttributeManager) NCLocator.getInstance().lookup(env);
    return manager;
  }
}
