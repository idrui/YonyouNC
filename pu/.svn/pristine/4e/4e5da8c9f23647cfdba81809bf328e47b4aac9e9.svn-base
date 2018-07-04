/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-8 下午08:01:55
 */
package nc.vo.pu.m4t.rule;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
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
 * @time 2010-9-8 下午08:01:55
 */
public class OrganizationValue {
  private IKeyValue keyValue;

  public OrganizationValue(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：设置表体财务组织
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-4 下午06:15:59
   */
  public void setFinanceOrg(int[] rows) {
    String pk_org =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG);
    if (StringUtil.isEmptyWithTrim(pk_org)) {
      return;
    }

    String pk_org_v =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG_V);
    if (StringUtil.isEmptyWithTrim(pk_org_v)) {
      pk_org_v = this.getOrgVid(pk_org);
    }

    String pk_group =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_GROUP);

    for (int row : rows) {
      this.keyValue.setBodyValue(row, InitialEstItemVO.PK_ORG, pk_org);
      this.keyValue.setBodyValue(row, InitialEstItemVO.PK_ORG_V, pk_org_v);
      this.keyValue.setBodyValue(row, InitialEstItemVO.PK_GROUP, pk_group);
    }
  }

  private String getOrgVid(String orgOid) {
    if (orgOid == null) {
      return null;
    }

    String orgVid = null;
    orgVid = OrgUnitPubService.getOrgVid(orgOid);
    return orgVid;
  }
}
