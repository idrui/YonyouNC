/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-15 ����09:06:08
 */
package nc.bs.pu.m21.writeback.pu.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ڳ��ݹ���ⵥ��д���ۼ���������������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-15 ����09:06:08
 */
public class AccStoreNumCalcFor4TRule implements IRule<OrderViewVO> {
  private IOrderWriteBackPara[] wbVos;

  public AccStoreNumCalcFor4TRule(IOrderWriteBackPara[] wbVos) {
    this.wbVos = wbVos;
  }

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderViewVO[] views) {
    Map<String, OrderViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    for (IOrderWriteBackPara vo : this.wbVos) {
      OrderViewVO view = viewMap.get(vo.getBID());
      UFDouble diffNum = vo.getDiffNum();
      // ��д�ۼ��������
      UFDouble newAccNum = MathTool.add(view.getNaccumstorenum(), diffNum);
      view.setNaccumstorenum(newAccNum);
      // ����ɹ������Ǹ���������Ҫ��д�ۼ��˿�����
      if (view.getBreturn().booleanValue()) {
        UFDouble newAccBackNum =
            MathTool.add(view.getNbackstorenum(), MathTool.oppose(diffNum));
        view.setNbackstorenum(newAccBackNum);
      }
    }
  }
}
