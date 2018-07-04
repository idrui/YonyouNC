package nc.vo.pu.pub.rule.upgrade;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

import org.apache.commons.lang.ArrayUtils;

public class VOStatusUpdateRule<E extends AbstractBill> implements IRule<E> {

  @Override
  public void process(E[] vos) {
    // 升级时更新vo状态
    for (E vo : vos) {
      if (vo.getParent() != null) {
        vo.getParent().setStatus(VOStatus.UPDATED);
      }
      if (ArrayUtils.isEmpty(vo.getChildrenVO())) {
        continue;
      }
      for (CircularlyAccessibleValueObject item : vo.getChildrenVO()) {
        if (null == item) {
          continue;
        }
        item.setStatus(VOStatus.UPDATED);
      }
    }
  }

}
