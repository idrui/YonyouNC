package nc.vo.pu.pf;

import nc.vo.pubapp.pflow.PfUserObject;

/**
 * @since 6.36
 * @version 2015-1-25 下午10:05:16
 * @author mengjian
 */
public class PUPFParameter extends PfUserObject {

  private static final long serialVersionUID = -9220665987003023788L;

  // 预算控制预警检查标志
  private boolean bBudgetCheckkFlag = false;

  public boolean isbBudgetCheckkFlag() {
    return this.bBudgetCheckkFlag;
  }

  public void setbBudgetCheckkFlag(boolean bBudgetCheckkFlag) {
    this.bBudgetCheckkFlag = bBudgetCheckkFlag;
  }
}
