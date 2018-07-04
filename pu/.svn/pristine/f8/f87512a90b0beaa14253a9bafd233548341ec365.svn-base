package nc.bs.pu.m27.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

import org.apache.commons.lang.ArrayUtils;

/**
 * 根据结算财务组织与库存组织所属财务组织是否相同，设置是否普通采购， 确定是否普通采购还是集采
 * 
 * @since 6.0
 * @version 2011-1-28 下午12:02:10
 * @author yinfy
 */

public class FillNormalPurFlagRule<E extends AbstractBill> implements IRule<E> {

  @Override
  public void process(E[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    Map<String, String> storeFiMap = this.getstore_fiorgMap(vos);
    for (E vo : vos) {
      CircularlyAccessibleValueObject head = vo.getParentVO();
      String instoreorg = this.getpk_storeorg(vo);
      head.setAttributeValue(PurchaseinFIHeaderVO.BNORMPUR, UFBoolean.TRUE);

      if (null == storeFiMap || !storeFiMap.containsKey(instoreorg)) {
        continue;
      }
      String outfiorg = this.getpk_fiorg(vo);
      String infiorg = storeFiMap.get(instoreorg);
      if (null != outfiorg && !outfiorg.equals(infiorg)) {
        head.setAttributeValue(PurchaseinFIHeaderVO.BNORMPUR, UFBoolean.FALSE);
      }
    }
  }

  private Map<String, String> getstore_fiorgMap(E[] vos) {
    Set<String> store_orgs = new HashSet<String>();
    for (E vo : vos) {
      store_orgs.add(this.getpk_storeorg(vo));
    }
    String[] storeorgs = store_orgs.toArray(new String[store_orgs.size()]);
    Map<String, String> storeFiMap =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(storeorgs);
    return storeFiMap;
  }

  protected String getpk_fiorg(E vo) {
    String outfiorg = null;
    CircularlyAccessibleValueObject[] items = vo.getChildrenVO();
    if (!ArrayUtils.isEmpty(items)) {
      // 假设所有表体行的结算财务组织都是同一个
      CircularlyAccessibleValueObject item = items[0];
      outfiorg =
          (String) item.getAttributeValue(PurchaseinFIItemVO.PK_FINANCEORG);
    }
    return outfiorg;
  }

  protected String getpk_storeorg(E vo) {
    return (String) vo.getParentVO().getAttributeValue(
        PurchaseinFIHeaderVO.PK_ORG);
  }
}
