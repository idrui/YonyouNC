package nc.vo.pu.pub.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * 
 * @description
 * 表体冗余表头的信息,主组织，主组织版本，集团
 * @scene
 * 期初暂估单保存
 * @param
 * 无
 *
 * @since 6.3
 * @version 2011-6-1 下午05:19:12
 * @author zhaoyha
 */

public class ItemDupHeaderInfoRule<E extends AbstractBill> implements IRule<E> {
  private static final String PK_GROUP = "pk_group";

  private static final String PK_ORG = "pk_org";

  private static final String PK_ORG_V = "pk_org_v";

  @Override
  public void process(E[] vos) {
    for (E vo : vos) {
      ISuperVO head = vo.getParent();
      String pk_org =
          (String) head.getAttributeValue(ItemDupHeaderInfoRule.PK_ORG);
      String pk_org_v =
          (String) head.getAttributeValue(ItemDupHeaderInfoRule.PK_ORG_V);
      String pk_group =
          (String) head.getAttributeValue(ItemDupHeaderInfoRule.PK_GROUP);
      for (CircularlyAccessibleValueObject item : vo.getChildrenVO()) {
        item.setAttributeValue(ItemDupHeaderInfoRule.PK_ORG, pk_org);
        item.setAttributeValue(ItemDupHeaderInfoRule.PK_ORG_V, pk_org_v);
        item.setAttributeValue(ItemDupHeaderInfoRule.PK_GROUP, pk_group);
      }
    }
  }

}
