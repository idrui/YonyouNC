/**
 * $�ļ�˵��$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-22 ����09:41:09
 */
package nc.bs.pu.m21.writeback.pu.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ʊ�رռ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-22 ����09:41:09
 */
public class InvoiceCloseChkRule implements IRule<OrderViewVO> {

  private OrderViewVO[] orgViews;

  public InvoiceCloseChkRule(OrderViewVO[] orgViews) {
    this.orgViews = orgViews;
  }

  @Override
  public void process(OrderViewVO[] views) {
    // �������
    Map<String, OrderViewVO> orgMap = CirVOUtil.createKeyVOMap(this.orgViews);
    for (OrderViewVO view : views) {
      // ��Ҫȷ�ϸ��������˿�����
      if (view.getBfinalclose().booleanValue()) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4004030_0", "04004030-0258", null, new String[]{view.getVordercode()})/*���ݺţ�{0}�Ѿ����չرգ��ɹ���Ʊ���������޸ģ�*/);
      }
      // "���ݺţ�{0}�Ѿ����չرգ��ɹ���Ʊ���������޸ģ�");
      OrderViewVO orgView = orgMap.get(view.getPk_order_b());
      UFDouble oldAccNum = orgView.getNaccuminvoicenum();
      UFDouble newAccNum = view.getNaccuminvoicenum();

      // ����
      if (MathTool.absCompareTo(newAccNum, oldAccNum) > 0
          && view.getBinvoiceclose().booleanValue()) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4004030_0", "04004030-0259", null, new String[]{view.getVordercode(),view.getCrowno()})/*���ݺţ�{0}�кţ�{1}�Ѿ���Ʊ�رգ��ɹ���Ʊ���������޸ģ�*/);
        // "���ݺţ�{0}�кţ�{0}�Ѿ���Ʊ�رգ��ɹ���Ʊ���������޸ģ�");
      }
    }
  }
}
