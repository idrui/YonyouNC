/**
 * 
 */
package nc.vo.pu.m21.rule;

import java.util.HashMap;
import java.util.Map;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.StringUtils;

/**
 * 需求公司默认规则
 * 
 * @since 6.0
 * @version 2012-5-21 下午01:45:04
 * @author tianft
 */
public class ReqCorpDefaultValue {

  private IKeyValue keyValue;

  public ReqCorpDefaultValue(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 自制采购订单时可以编辑，默认值规则：如果需求库存组织勾选了公司属性，则直接取需求库存组织作为默认值
   * 
   * @param rows
   */
  public void setDefaultValue(int[] rows) {
    // by 20141105 mengjian 缓存数据用
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    for (int i = 0; i < rows.length; ++i) {
      String pk_storeorg =
          (String) this.keyValue
              .getBodyValue(rows[i], OrderItemVO.PK_REQSTOORG);
      String pk_reqcorp =
          (String) this.keyValue.getBodyValue(rows[i], OrderItemVO.PK_REQCORP);
      if (StringUtils.isEmpty(pk_storeorg) || !StringUtils.isEmpty(pk_reqcorp)) {
        continue;
      }
      // 如果需求库存组织勾选了公司属性
      if (!map.containsKey(pk_storeorg)) {
        boolean isCorpType =
            OrgUnitPubService.isTypeOf(pk_storeorg, IOrgConst.CORPORGTYPE);
        map.put(pk_storeorg, UFBoolean.valueOf(isCorpType));
      }
      if (UFBoolean.TRUE.equals(map.get(pk_storeorg))) {
        this.keyValue
            .setBodyValue(rows[i], OrderItemVO.PK_REQCORP, pk_storeorg);
      }

    }
  }

}
