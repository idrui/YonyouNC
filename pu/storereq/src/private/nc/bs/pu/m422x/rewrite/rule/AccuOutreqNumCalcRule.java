package nc.bs.pu.m422x.rewrite.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pu.m422x.entity.WriteBack422XVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 *            回写物资需求申请单时，计算累计申请出库主数量
 * @scene
 *      回写物资需求申请单
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-12-16 下午03:09:22
 * @author wuxla
 */
public class AccuOutreqNumCalcRule implements IRule<StoreReqAppViewVO> {
  private WriteBack422XVO[] wbVos;

  public AccuOutreqNumCalcRule(WriteBack422XVO[] wbVos) {
    this.wbVos = wbVos;
  }

  @Override
  public void process(StoreReqAppViewVO[] views) {
    Map<String, StoreReqAppViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    for (WriteBack422XVO vo : this.wbVos) {
      StoreReqAppViewVO view = viewMap.get(vo.getPk_storereq_b());
      UFDouble newAccNum =
          MathTool.add(view.getNaccuoutreqnum(), vo.getDiffNum());
      view.setNaccuoutreqnum(newAccNum);
    }
  }

}
