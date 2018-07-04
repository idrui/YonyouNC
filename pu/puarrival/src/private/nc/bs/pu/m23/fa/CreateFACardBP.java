package nc.bs.pu.m23.fa;

import nc.bs.pu.m23.fa.rule.ChkCanCrtFARule;
import nc.bs.pu.m23.fa.rule.FilterBySelectedRule;
import nc.bs.pu.m23.fa.rule.InvokeCrtFAServiceRule;
import nc.bs.pu.m23.fa.rule.SendMsgForFARule;
import nc.bs.pu.m23.plugin.ArriveBPPlugInPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.bdlayer.msg.ScmBuziMsgResEnum;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.ArriveATPUpdateRule;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>完成到货单的生成资产卡片
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-13 上午10:52:12
 */
public class CreateFACardBP {

  public ArriveVO[] createFACard(ArriveVO[] aggVO, ArriveVO[] originBills) {
    AroundProcesser<ArriveVO> processer =
        new AroundProcesser<ArriveVO>(ArriveBPPlugInPoint.CreateFACardBP);
    this.addBeforeRule(processer, aggVO);
    this.addAfterRule(processer);

    ArriveVO[] newFullVOs = processer.before(originBills);
    processer.after(newFullVOs);
    return newFullVOs;
  }

  private void addAfterRule(AroundProcesser<ArriveVO> processer) {
    // 可用量变更后操作
    processer.addAfterRule(new ArriveATPUpdateRule(false));
    // 发送业务消息
    processer.addAfterRule(new SendMsgForFARule(
        ScmBuziMsgResEnum.ARRIVAL_GENEQUIP.code()));
  }

  private void addBeforeRule(AroundProcesser<ArriveVO> processer, ArriveVO[] vos) {
    processer.addBeforeRule(new FilterBySelectedRule(vos));
    // 可用量变更前操作
    processer.addBeforeRule(new ArriveATPUpdateRule(true));
    // 检查是否可生成资产卡片
    processer.addBeforeRule(new ChkCanCrtFARule());
    // TODO 根据检验结果（考虑分批检验）的合格数量控制生成卡片的数量
    // 调用资产服务
    processer.addBeforeRule(new InvokeCrtFAServiceRule());
  }
}
