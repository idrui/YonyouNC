/**
 * $�ļ�˵��$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-19 ����04:15:22
 */
package nc.pubimpl.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.OrganizationDefaultValue;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������֯Ĭ��ֵ�����������֯��Ӧ��������֯����������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-19 ����04:20:25
 */
public class OrganizationValueRule implements IRule<OrderVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    this.setOrganizationValues(vos);
  }

  /**
   * ��������������������֯Ĭ��ֵ
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVOs ����vo����
   *          <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-19 ����04:26:21
   */
  private void setOrganizationValues(OrderVO[] orderVOs) {
    for (OrderVO orderVO : orderVOs) {
      if (null == orderVO || null == orderVO.getHVO()) {
        continue;
      }
      IKeyValue keyValue = new BillHelper<OrderVO>(orderVO);
      int[] rows = new int[keyValue.getItemCount()];
      for (int i = 0; i < rows.length; ++i) {
        rows[i] = i;
      }
      OrganizationDefaultValue value = new OrganizationDefaultValue(keyValue);
      value.setDefaultOrganizationValue(rows);
    }
  }
}
