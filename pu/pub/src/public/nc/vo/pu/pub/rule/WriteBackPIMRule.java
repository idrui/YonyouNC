package nc.vo.pu.pub.rule;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.pub.iadapter.PimForPUInterfaceAdapter;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              ����ɾ��ʱ ��д��Ŀ����ģ��
 * @scene
 *        �빺��ɾ�����޸ġ�ɾ���������������뵥����
 * @param String pk_billtype ��������
 * @since 6.3
 * @version 2014-10-21 ����9:22:39
 * @author yanxm5
 */
public class WriteBackPIMRule<E extends AbstractBill> implements
    ICompareRule<E> {
  private String pk_billtype;

  public WriteBackPIMRule(String pk_billtype) {
    this.pk_billtype = pk_billtype;
  }

  @Override
  public void process(E[] vos, E[] originVOs) {
    boolean isPIMEnable = SysInitGroupQuery.isPIMEnabled();
    if (!isPIMEnable) {
      return;
    }
    if (ArrayUtils.isEmpty(originVOs)) {
      // ����
      new PimForPUInterfaceAdapter<AbstractBill>(this.pk_billtype)
          .writebackWhenInsert(vos);
    }
    else if (ArrayUtils.isEmpty(vos)
        || VOStatus.DELETED == vos[0].getParent().getStatus()) {
      // ɾ��
      // �빺��vos���ǿգ�����ֻ�ܸ���״̬�ж�
      new PimForPUInterfaceAdapter<AbstractBill>(this.pk_billtype)
          .writebackWhenDelete(originVOs);
    }
    else {
      // �޸�
      new PimForPUInterfaceAdapter<AbstractBill>(this.pk_billtype)
          .writebackWhenUpdate(vos, originVOs);
    }
  }
}
