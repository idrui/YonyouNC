/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-25 ����07:59:47
 */
package nc.vo.pu.m4t.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ڳ��ݹ�����Ԫ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-25 ����07:59:47
 */
public class InitialEstVOMeta extends AbstractBillMeta {

  public InitialEstVOMeta() {
    this.setParent(InitialEstHeaderVO.class);
    this.addChildren(InitialEstItemVO.class);
  }

}
