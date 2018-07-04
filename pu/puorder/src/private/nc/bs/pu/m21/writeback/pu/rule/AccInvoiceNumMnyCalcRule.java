/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-25 ����10:12:46
 */
package nc.bs.pu.m21.writeback.pu.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.pu.m25.IOrderWriteBackParaFor25;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����ɹ��������ۼ�����������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-25 ����10:12:46
 */
public class AccInvoiceNumMnyCalcRule implements IRule<OrderViewVO> {

  /** ��д����VO **/
  private IOrderWriteBackParaFor25[] wbVos;

  public AccInvoiceNumMnyCalcRule(IOrderWriteBackParaFor25[] wbVos) {
    this.wbVos = wbVos;
  }

  @Override
  public void process(OrderViewVO[] views) {
    Map<String, OrderViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    for (IOrderWriteBackParaFor25 vo : this.wbVos) {
      OrderViewVO view = viewMap.get(vo.getBID());
      if (vo.isFee()) {
        UFDouble diffMny = vo.getDiffMny();
        UFDouble newFeeMny = MathTool.add(view.getNfeemny(), diffMny);
        view.setNfeemny(newFeeMny);
        view.setNaccuminvoicemny(newFeeMny);
        UFDouble diffNum = vo.getDiffNum();
        UFDouble newAccNum = MathTool.add(view.getNaccuminvoicenum(), diffNum);
        view.setNaccuminvoicenum(newAccNum);
      }
      else {
        UFDouble diffNum = vo.getDiffNum();
        UFDouble newAccNum = MathTool.add(view.getNaccuminvoicenum(), diffNum);
        view.setNaccuminvoicenum(newAccNum);
        UFDouble diffMny = vo.getDiffMny();
        UFDouble newAccMny = MathTool.add(view.getNaccuminvoicemny(), diffMny);
        view.setNaccuminvoicemny(newAccMny);
      }
    }
  }

}
