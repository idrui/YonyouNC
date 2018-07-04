package nc.impl.pu.m23.qc.action.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.pu.m23.maintain.rule.insert.InsertSendMsgRule;
import nc.vo.pu.m23.entity.ArriveVO;

/**
 * 
 * @description
 * 到货单质检时，通知库管员
 * @scene
 * 到货单质检
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-5-13 下午3:24:43
 * @author luojw
 */
public class QualityCheckSendMsgRule extends InsertSendMsgRule {

  public QualityCheckSendMsgRule(String msgResCode) {
    super(msgResCode);
  }

  @Override
  protected Map<String, List<ArriveVO>> sortVos(ArriveVO[] vos) {
    Map<String, List<ArriveVO>> uservos = new HashMap<>();
    for (ArriveVO vo : vos) {
      String maker = vo.getHVO().getBillmaker();
      List<ArriveVO> volist = uservos.get(maker);
      if(volist == null){
        volist = new ArrayList<>();
        uservos.put(maker, volist);
      }
      volist.add(vo);
    }
    return uservos;
  }

}
