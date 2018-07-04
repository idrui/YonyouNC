/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 上午10:37:44
 */
package nc.impl.pu.m4t.action;

import nc.bs.pu.est.plugin.InitialEstPluginPoint;
import nc.bs.pu.m4t.maintain.InitialEstDeleteBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m4t.entity.InitialEstContext;
import nc.vo.pu.m4t.entity.InitialEstVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>删除动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-26 上午10:37:44
 */
public class InitialEstDeleteAction {
  public void delete(InitialEstVO[] vos, InitialEstContext context) {

    BillTransferTool<InitialEstVO> tool =
        new BillTransferTool<InitialEstVO>(vos);// 并发控制
    InitialEstVO[] fullVOs = tool.getClientFullInfoBill();

    AroundProcesser<InitialEstVO> processer =
        new AroundProcesser<InitialEstVO>(InitialEstPluginPoint.DELETE_ACTION);
    this.addRule(processer);

    processer.before(fullVOs);
    new InitialEstDeleteBP(context).delete(fullVOs);
    processer.after(fullVOs);
  }

  private void addRule(AroundProcesser<InitialEstVO> processer) {
    //
  }

}
