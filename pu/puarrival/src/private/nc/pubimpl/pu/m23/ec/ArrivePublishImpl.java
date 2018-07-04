package nc.pubimpl.pu.m23.ec;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.pubimpl.pu.m23.ec.rule.PublishValidateRule;
import nc.pubitf.pu.m23.ec.IArrivePublish;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;

public class ArrivePublishImpl implements IArrivePublish {

  @Override
  public ArriveVO[] publish(ArriveVO[] vos) throws BusinessException {
    // �������� + ���TS
    BillTransferTool<ArriveVO> tool = new BillTransferTool<ArriveVO>(vos);

    // ����޸�ǰVO
    ArriveVO[] originVos = tool.getOriginBills();

    CompareAroundProcesser<ArriveVO> processer =
        new CompareAroundProcesser<ArriveVO>(null);
    this.addBeforeRule(processer);
    this.addAfterRule(processer);
    processer.before(vos, originVos);

    BillUpdate<ArriveVO> bo = new BillUpdate<ArriveVO>();
    ArriveVO[] rets = bo.update(vos, originVos);

    processer.after(vos, originVos);
    return rets;
  }

  private void addAfterRule(CompareAroundProcesser<ArriveVO> processer) {
    // do nothing.
  }

  private void addBeforeRule(CompareAroundProcesser<ArriveVO> processer) {
    // ������������顣
    processer.addBeforeRule(new PublishValidateRule());
  }
}
