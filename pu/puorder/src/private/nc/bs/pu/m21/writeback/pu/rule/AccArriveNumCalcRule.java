/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-1 ����01:22:54
 */
package nc.bs.pu.m21.writeback.pu.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.pu.m23.IOrderWriteBackParaFor23;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ۼƵ���(��)��;������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-1 ����01:22:54
 */
public class AccArriveNumCalcRule implements IRule<OrderViewVO> {

  private IOrderWriteBackParaFor23[] wbVos;

  public AccArriveNumCalcRule(IOrderWriteBackParaFor23[] wbVos) {
    this.wbVos = wbVos;
  }

  @Override
  public void process(OrderViewVO[] views) {

    Map<String, OrderViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    for (IOrderWriteBackParaFor23 vo : this.wbVos) {
      OrderViewVO view = viewMap.get(vo.getBID());
      UFDouble diffNum = vo.getDiffNum();
      // ���������ԭ�����˻�
      if (!vo.isReturn()) {
        UFDouble newAccNum = MathTool.add(view.getNaccumarrvnum(), diffNum);
        view.setNaccumarrvnum(newAccNum);
      }
      else {// �˻�
        if (view.getBreturn().booleanValue()
            || view.getBrefwhenreturn().booleanValue()) {
          UFDouble newAccNum = MathTool.add(view.getNaccumarrvnum(), diffNum);
          view.setNaccumarrvnum(newAccNum);
        }
        UFDouble newAccBackNum =
            MathTool.add(view.getNbackarrvnum(), MathTool.oppose(diffNum));
        view.setNbackarrvnum(newAccBackNum);
      }
      // �ۼ�;������
      UFDouble diffWasteNum = vo.getDiffWasteNum();
      UFDouble newWasteNum =
          MathTool.add(view.getNaccumwastnum(), diffWasteNum);
      view.setNaccumwastnum(newWasteNum);
    }
  }

}
