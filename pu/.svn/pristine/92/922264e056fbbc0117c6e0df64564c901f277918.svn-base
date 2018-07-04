/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-4 下午06:03:58
 */
package nc.vo.pu.m422x.rule;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.util.IKeyValue;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>组织默认值
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-4 下午06:03:58
 */
public class OrganizationValue {
  private IKeyValue keyValue;

  public OrganizationValue(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：设置表体库存组织
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-4 下午06:15:59
   */
  public void setStockOrg(int[] rows) {
    String pk_org =
        (String) this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_ORG);
    if (StringUtil.isEmptyWithTrim(pk_org)) {
      return;
    }

    String pk_org_v =
        (String) this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_ORG_V);
    if (StringUtil.isEmptyWithTrim(pk_org_v)) {
      pk_org_v = this.getOrgVid(pk_org);
    }

    String pk_group =
        (String) this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_GROUP);

    for (int row : rows) {
      this.keyValue.setBodyValue(row, StoreReqAppItemVO.PK_ORG, pk_org);
      this.keyValue.setBodyValue(row, StoreReqAppItemVO.PK_ORG_V, pk_org_v);
      this.keyValue.setBodyValue(row, StoreReqAppItemVO.PK_GROUP, pk_group);

      // 物资需求汇总平衡65 wuxla
      // 原始需求库存组织,默认值为主组织,不可空，不可编辑
      this.keyValue.setBodyValue(row, StoreReqAppItemVO.PK_REQSTOORG, pk_org);
      this.keyValue.setBodyValue(row, StoreReqAppItemVO.PK_REQSTOORG_V,
          pk_org_v);

    }
  }

  private String getOrgVid(String orgOid) {
    if (orgOid == null) {
      return null;
    }
    return OrgUnitPubService.getOrgVid(orgOid);
  }
}
