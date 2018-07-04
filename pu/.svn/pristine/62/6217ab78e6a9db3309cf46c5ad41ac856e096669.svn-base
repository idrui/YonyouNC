package nc.impl.pu.m20.action;

import nc.bs.pu.m20.plugin.PraybillPluginPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m20.rule.CheckUnSendApproveRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单收回操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-10-12 上午11:13:17
 */
public class PraybillUnSendApproveAction {
  /**
   * 方法功能描述：收回。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param script
   * @return <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 上午11:01:49
   */
  public PraybillVO[] unSendapprove(PraybillVO[] vos, AbstractCompiler2 script) {
    PfParameterUtil<PraybillVO> util =
        new PfParameterUtil<PraybillVO>(script == null ? null
            : script.getPfParameterVO(), vos);
    PraybillVO[] originBills = util.getOrginBills();
    PraybillVO[] clientBills = util.getClientFullInfoBill();
    AroundProcesser<PraybillVO> processer =
        new AroundProcesser<PraybillVO>(PraybillPluginPoint.UNSENDAPPROVE);
    this.addBeforeRule(processer);

    // 前规则
    processer.before(clientBills);

    // 调用平台脚本进行审批
    if (null != script) {
      try {
        script.procRecallFlow(script.getPfParameterVO());
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }

    // 把VO持久化到数据库中
    BillUpdate<PraybillVO> update = new BillUpdate<PraybillVO>();
    PraybillVO[] returnVos = update.update(clientBills, originBills);

    // 后规则
    processer.after(returnVos);
    return returnVos;
  }

  /**
   * 方法功能描述：增加规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 上午11:01:34
   */
  private void addBeforeRule(AroundProcesser<PraybillVO> processer) {
    processer.addBeforeFinalRule(new CheckUnSendApproveRule());// 是否可以收回

  }
}
