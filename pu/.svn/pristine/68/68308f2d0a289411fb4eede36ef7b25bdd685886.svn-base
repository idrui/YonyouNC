/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 上午11:45:14
 */
package nc.vo.pu.m21.rule;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置采购组织值
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 上午11:45:14
 */
public class PurchaseOrgValue {
  private IKeyValue keyValue;

  public PurchaseOrgValue(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：设置表头和表体采购组织值
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-9-28 上午11:49:41
   */
  public void setPurchaseOrgValue() {
    String pk_org = (String) this.keyValue.getHeadValue(OrderHeaderVO.PK_ORG);
    String pk_org_v = this.getOrgVid(pk_org);

    this.keyValue.setHeadValue(OrderHeaderVO.PK_ORG_V, pk_org_v);

    int rowcount = this.keyValue.getItemCount();
    for (int i = 0; i < rowcount; ++i) {
      this.keyValue.setBodyValue(i, OrderItemVO.PK_ORG, pk_org);
      this.keyValue.setBodyValue(i, OrderItemVO.PK_ORG_V, pk_org_v);
    }
  }

  /**
   * 方法功能描述：得到组织VID
   * <p>
   * <b>参数说明</b>
   * 
   * @param orgOid
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-28 上午11:49:22
   */
  private String getOrgVid(String orgOid) {
    if (orgOid == null) {
      return null;
    }

    String orgVid = OrgUnitPubService.getOrgVid(orgOid);
    return orgVid;
  }

}
