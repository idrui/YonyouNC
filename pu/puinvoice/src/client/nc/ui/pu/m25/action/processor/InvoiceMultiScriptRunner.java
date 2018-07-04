/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-25 下午09:32:39
 */
package nc.ui.pu.m25.action.processor;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.ml.NCLangRes;
import nc.ui.pu.pub.util.BillManageModelUtil;
import nc.ui.pubapp.pub.common.context.PFlowContext;
import nc.ui.pubapp.uif2app.actions.pflow.AbstractScriptExcAction;
import nc.ui.pubapp.uif2app.actions.pflow.MultiBillScriptRunner;
import nc.ui.pubapp.uif2app.actions.pflow.ScriptPFlowAction;
import nc.vo.pu.m25.entity.InvoiceVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-4-25 下午09:32:39
 */
public class InvoiceMultiScriptRunner extends MultiBillScriptRunner {

  public InvoiceMultiScriptRunner(PFlowContext flowContext,
      AbstractScriptExcAction action) {
    super(flowContext);
    this.setScriptAction(action);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.MultiBillScriptRunner#createShowMsg(int,
   *      nc.vo.pubapp.pattern.model.entity.bill.IBill, java.lang.Throwable)
   */
  @Override
  protected String createShowMsg(int procIndex, Object bill, Throwable error) {
    ScriptPFlowAction action = (ScriptPFlowAction) this.getScriptAction();
    int index = BillManageModelUtil.getIndex(action.getModel(), bill);
    String billcode = ((InvoiceVO) bill).getParentVO().getVbillcode();
    int indexno = index + 1;

    if (this.isTaskSuccessful()) {
      return NCLangRes.getInstance().getStrByID("4004050_0", "04004050-0102",
          null, new String[] {
            String.valueOf(indexno), billcode
          })/* 序号[{0}]编号[{1}]的发票，操作成功。 */;
    }
    if (!(error instanceof IResumeException)) {
      return NCLangRes.getInstance().getStrByID("4004050_0", "04004050-0103",
          null, new String[] {
            String.valueOf(indexno), billcode, error.getMessage()
          })/* 序号[{0}]编号[{1}]的发票，操作失败，原因：{2} */;
    }
    return NCLangRes.getInstance().getStrByID("4004050_0", "04004050-0104",
        null, new String[] {
          String.valueOf(indexno), billcode, error.getMessage()
        })/* 序号[{0}]编号[{1}]的发票，提示[{2}]用户取消操作。 */;
  }
}
