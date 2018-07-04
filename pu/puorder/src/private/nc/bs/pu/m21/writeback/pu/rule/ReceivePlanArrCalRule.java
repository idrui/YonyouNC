/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-26 ����06:38:34
 */
package nc.bs.pu.m21.writeback.pu.rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.pu.m23.IOrderWriteBackParaFor23;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ƻ���д
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-26 ����06:38:34
 */
public class ReceivePlanArrCalRule implements IRule<OrderReceivePlanVO> {
  private OrderViewVO[] views;

  private IOrderWriteBackParaFor23[] wbVos;

  public ReceivePlanArrCalRule(IOrderWriteBackParaFor23[] wbVos,
      OrderViewVO[] views) {
    this.wbVos = wbVos;
    this.views = views;
  }

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderReceivePlanVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    Map<String, OrderViewVO> viewMap = CirVOUtil.createKeyVOMap(this.views);
    Map<String, OrderReceivePlanVO> rpMap =
        new HashMap<String, OrderReceivePlanVO>();
    for (OrderReceivePlanVO rpVO : vos) {
      rpMap.put(rpVO.getPk_order_bb1(), rpVO);
    }

    for (IOrderWriteBackParaFor23 vo : this.wbVos) {
      OrderViewVO view = viewMap.get(vo.getBID());
      OrderReceivePlanVO rpVO = rpMap.get(vo.getBBID());
      if (null == rpVO) {
        continue;
      }
      UFDouble diffNum = vo.getDiffNum();
      // ���������ԭ�����˻�
      if (!vo.isReturn()) {
        UFDouble newAccNum = MathTool.add(rpVO.getNaccumarrvnum(), diffNum);
        rpVO.setNaccumarrvnum(newAccNum);
      }
      else {// �˻�
        if (view.getBreturn().booleanValue()
            || view.getBrefwhenreturn().booleanValue()) {
          UFDouble newAccNum = MathTool.add(rpVO.getNaccumarrvnum(), diffNum);
          rpVO.setNaccumarrvnum(newAccNum);
        }
        UFDouble newAccNum =
            MathTool.add(rpVO.getNbackarrvnum(), MathTool.oppose(diffNum));
        rpVO.setNbackarrvnum(newAccNum);
      }

      // �ۼ�;������
      UFDouble diffWasteNum = vo.getDiffWasteNum();
      UFDouble newWasteNum =
          MathTool.add(view.getNaccumwastnum(), diffWasteNum);
      view.setNaccumwastnum(newWasteNum);
    }
  }

}
