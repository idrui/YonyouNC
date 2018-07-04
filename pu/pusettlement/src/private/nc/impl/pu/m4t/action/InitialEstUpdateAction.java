/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 上午10:37:03
 */
package nc.impl.pu.m4t.action;

import nc.bs.pu.m4t.maintain.InitialEstSaveBP;
import nc.impl.pu.m4t.action.rule.maintain.ParaValidityChkRule;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m4t.entity.InitialEstContext;
import nc.vo.pu.m4t.entity.InitialEstVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>更新动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-26 上午10:37:03
 */
public class InitialEstUpdateAction {

  public InitialEstVO[] update(InitialEstVO[] vos, InitialEstContext context) {
    BillTransferTool<InitialEstVO> tool =
        new BillTransferTool<InitialEstVO>(vos);
    InitialEstVO[] orgVos = tool.getOriginBills();
    // InitialEstVO[] clientVos = tool.getClientFullInfoBill(); // 前台过来的是修改过的行,
    // 这里补充为完成VO
    // v61 根据协议接口调用者传入的一定是全VO
    CompareAroundProcesser<InitialEstVO> processer =
        new CompareAroundProcesser<InitialEstVO>(null);
    this.addRule(processer);
    processer.before(vos, orgVos);
    InitialEstVO[] savedVos =
        new InitialEstSaveBP(context).save(null, vos, orgVos);
    processer.after(savedVos, orgVos);
    return savedVos;
  }

  /**
   * @param processer
   */
  private void addRule(CompareAroundProcesser<InitialEstVO> processer) {
    processer.addBeforeRule(new ParaValidityChkRule());
  }

}
