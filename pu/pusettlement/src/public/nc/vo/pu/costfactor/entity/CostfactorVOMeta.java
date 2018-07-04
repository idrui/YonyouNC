package nc.vo.pu.costfactor.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

/**
 * �ɱ�����Ҫ�ص���VO��Ԫ����
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhaoyha
 * @time 2009-6-1 ����09:51:27
 */
public class CostfactorVOMeta extends AbstractBillMeta {

  /**
   * CostfactorVOMeta �Ĺ�����
   */
  public CostfactorVOMeta() {
    this.init();
  }

  private void init() {
    this.setParent(CostfactorHeaderVO.class);
    this.addChildren(CostfactorItemVO.class);
  }

}
