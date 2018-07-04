package nc.vo.pu.pub.rule;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.pub.iadapter.PimForPUInterfaceAdapter;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              单据删除时 回写项目管理模块
 * @scene
 *        请购单删除、修改、删除、物资需求申请单保存
 * @param String pk_billtype 单据类型
 * @since 6.3
 * @version 2014-10-21 上午9:22:39
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
      // 新增
      new PimForPUInterfaceAdapter<AbstractBill>(this.pk_billtype)
          .writebackWhenInsert(vos);
    }
    else if (ArrayUtils.isEmpty(vos)
        || VOStatus.DELETED == vos[0].getParent().getStatus()) {
      // 删除
      // 请购单vos不是空，所以只能根据状态判断
      new PimForPUInterfaceAdapter<AbstractBill>(this.pk_billtype)
          .writebackWhenDelete(originVOs);
    }
    else {
      // 修改
      new PimForPUInterfaceAdapter<AbstractBill>(this.pk_billtype)
          .writebackWhenUpdate(vos, originVOs);
    }
  }
}
