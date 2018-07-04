/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-25 ����07:57:52
 */
package nc.vo.pu.m4t.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ڳ��ݹ����ۺ�VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-25 ����07:57:52
 */
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.pu.m4t.entity.InitialEstHeaderVO")
public class InitialEstVO extends AbstractBill {

  /**
   * 
   */
  private static final long serialVersionUID = 5829122842941708564L;

  public InitialEstHeaderVO getHeader() {
    return (InitialEstHeaderVO) super.getParentVO();
  }

  public InitialEstItemVO[] getItems() {
    return (InitialEstItemVO[]) super.getChildrenVO();
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pubapp.pattern.model.entity.bill.IBill#getBillMeta()
   */
  @Override
  public IBillMeta getMetaData() {
    return BillMetaFactory.getInstance().getBillMeta(InitialEstVOMeta.class);
  }

}
