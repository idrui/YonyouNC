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
 *            ��д�����������뵥ʱ�������ۼƳ�������
 * @scene
 *       ��д�����������뵥
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-9-21 ����09:18:12
 * @author gaogr
 */
public class AccOrderNumCalcRule implements IRule<StoreReqAppViewVO> {

  /** ��д����VO **/
  private final WriteBack422XVO[] wbVos;

  public AccOrderNumCalcRule(WriteBack422XVO[] wbVos) {
    this.wbVos = wbVos;
  }

  @Override
  public void process(StoreReqAppViewVO[] views) {
    Map<String, StoreReqAppViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    for (WriteBack422XVO vo : this.wbVos) {
      StoreReqAppViewVO view = viewMap.get(vo.getPk_storereq_b());
      UFDouble diffNum = vo.getDiffNum();
      UFDouble newAccNum = MathTool.add(view.getNaccuoutnum(), diffNum);
      view.setNaccuoutnum(newAccNum);
    }
  }

}
