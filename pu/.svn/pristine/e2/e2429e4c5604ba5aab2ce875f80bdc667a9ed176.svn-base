/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-26 下午06:45:20
 */
package nc.vo.pu.m422x.rule;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.context.IContext;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-26 下午06:45:20
 */
public class ItemDefaultValue {
  private IContext ctx;

  private IKeyValue keyValue;

  public ItemDefaultValue(IKeyValue keyValue, IContext ctx) {
    this.keyValue = keyValue;
    this.ctx = ctx;
  }

  public void setDefaultValue(int[] rows) {
    if (this.keyValue.getItemCount() == 0 || ArrayUtils.isEmpty(rows)) {
      return;
    }

    String vid = this.getOrgVid();

    for (int row : rows) {
      // 集团
      if (this.keyValue.getBodyValue(row, StoreReqAppItemVO.PK_GROUP) == null) {
        this.keyValue.setBodyValue(row, StoreReqAppItemVO.PK_GROUP, this.ctx
            .getPk_group());
      }
      // 采购组织
      if (this.keyValue.getBodyValue(row, StoreReqAppItemVO.PK_ORG) == null) {
        this.keyValue.setBodyValue(row, StoreReqAppItemVO.PK_ORG, this.ctx
            .getPk_org());
      }

      if (this.keyValue.getHeadValue(StoreReqAppItemVO.PK_ORG_V) == null) {
        this.keyValue.setHeadValue(StoreReqAppItemVO.PK_ORG_V, vid);
      }
    }
  }

  private String getOrgVid() {
    String vid = null;
    if (!StringUtil.isEmptyWithTrim(this.ctx.getPk_org())) {
      vid = OrgUnitPubService.getOrgVid(this.ctx.getPk_org());
    }
    return vid;
  }
}
