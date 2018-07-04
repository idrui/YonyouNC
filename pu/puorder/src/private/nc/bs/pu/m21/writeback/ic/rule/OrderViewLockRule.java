package nc.bs.pu.m21.writeback.ic.rule;

import nc.impl.pubapp.pattern.data.view.tool.ViewConcurrentTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderViewVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���Ԥ����д�ɹ���������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-10 ����08:47:32
 */
public class OrderViewLockRule implements IRule<OrderViewVO> {

  @Override
  public void process(OrderViewVO[] vos) {
    new ViewConcurrentTool().lock(vos);
  }

}
