package nc.impl.pu.m24.action;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.pu.m24.maintain.PricestlInsertBP;
import nc.bs.pu.m24.maintain.rule.FillCreateInfoRule;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pubapp.pattern.log.TimeLog;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单新增Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-7-30 下午02:45:00
 */
public class PricestlInsertAction {

  public PricestlVO[] insert(PricestlVO[] PricestlVOs) {
    if (ArrayUtils.isEmpty(PricestlVOs)) {
      return null;
    }
    TimeLog.logStart();

    // 获取差异VO
    BillTransferTool<PricestlVO> transferTool =
        new BillTransferTool<PricestlVO>(PricestlVOs);
    PricestlVO[] insertVOs = transferTool.getClientFullInfoBill();

    AroundProcesser<PricestlVO> processer =
        new AroundProcesser<PricestlVO>(null);
    // 加入Action中的执行前/后规则
    this.addBeforeRule(processer);

    processer.before(insertVOs);

    PricestlInsertBP action = new PricestlInsertBP();
    PricestlVO[] vos = action.insert(insertVOs);

    processer.after(vos);

    // 获取返回前台的差异轻量VO
    vos = transferTool.getBillForToClient(vos);

    TimeLog.info(NCLangResOnserver.getInstance().getStrByID("4004070_0",
        "04004070-0006")/* 业务日志:后台插入单据数据 */);
    return vos;

  }

  /**
   * 方法功能描述：更新前规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 下午01:23:42
   */
  private void addBeforeRule(AroundProcesser<PricestlVO> processer) {

    // 设置审计信息
    processer.addBeforeRule(new FillCreateInfoRule());
  }

}
