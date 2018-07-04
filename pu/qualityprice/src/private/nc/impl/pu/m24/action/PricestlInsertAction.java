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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���㵥����Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-7-30 ����02:45:00
 */
public class PricestlInsertAction {

  public PricestlVO[] insert(PricestlVO[] PricestlVOs) {
    if (ArrayUtils.isEmpty(PricestlVOs)) {
      return null;
    }
    TimeLog.logStart();

    // ��ȡ����VO
    BillTransferTool<PricestlVO> transferTool =
        new BillTransferTool<PricestlVO>(PricestlVOs);
    PricestlVO[] insertVOs = transferTool.getClientFullInfoBill();

    AroundProcesser<PricestlVO> processer =
        new AroundProcesser<PricestlVO>(null);
    // ����Action�е�ִ��ǰ/�����
    this.addBeforeRule(processer);

    processer.before(insertVOs);

    PricestlInsertBP action = new PricestlInsertBP();
    PricestlVO[] vos = action.insert(insertVOs);

    processer.after(vos);

    // ��ȡ����ǰ̨�Ĳ�������VO
    vos = transferTool.getBillForToClient(vos);

    TimeLog.info(NCLangResOnserver.getInstance().getStrByID("4004070_0",
        "04004070-0006")/* ҵ����־:��̨���뵥������ */);
    return vos;

  }

  /**
   * ������������������ǰ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 ����01:23:42
   */
  private void addBeforeRule(AroundProcesser<PricestlVO> processer) {

    // ���������Ϣ
    processer.addBeforeRule(new FillCreateInfoRule());
  }

}
