package nc.bs.pu.m422x.rewrite.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pu.m422x.entity.WriteBack422XVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

public class AccBuyingReqNumCalcRule implements IRule<StoreReqAppViewVO> {

  private WriteBack422XVO[] wbVos;

  public AccBuyingReqNumCalcRule(WriteBack422XVO[] wbVos) {
    this.wbVos = wbVos;
  }

  @Override
  public void process(StoreReqAppViewVO[] views) {
    Map<String, StoreReqAppViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    for (WriteBack422XVO vo : this.wbVos) {
      StoreReqAppViewVO view = viewMap.get(vo.getPk_storereq_b());
      UFDouble newAccNum =
          MathTool.add(view.getNaccumbuyreqnum(), vo.getDiffNum());
      view.setNaccumbuyreqnum(newAccNum);
    }
  }
}
