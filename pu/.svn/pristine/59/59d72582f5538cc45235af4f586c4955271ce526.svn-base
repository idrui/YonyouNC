/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-3 上午10:45:53
 */
package nc.ui.pu.m20.editor.card.pub;

import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>新增单据时设置表体默认值
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-3 上午10:45:53
 */
public class BodyLineDefaultValue {
  private final LoginContext ctx;

  private final IKeyValue keyValue;

  public BodyLineDefaultValue(IKeyValue keyValue, LoginContext ctx) {
    this.keyValue = keyValue;
    this.ctx = ctx;
  }

  /**
   * 方法功能描述：简要描述本方法的功能。
   * <p>
   * <b>参数说明</b>
   * 
   * @param iRow
   *          <p>
   * @since 6.0
   * @author linsf
   * @time 2010-2-3 上午11:10:16
   */
  public void setDefaultValue(int row) {
    if (this.keyValue.getItemCount() == 0) {
      return;
    }
    this.keyValue.setBodyValue(row, PraybillItemVO.PK_GROUP,
        this.ctx.getPk_group());
    this.keyValue
        .setBodyValue(row, PraybillItemVO.PK_ORG, this.ctx.getPk_org());
    this.keyValue.setBodyValue(row, PraybillItemVO.CPROJECTID,
        this.keyValue.getHeadValue(PraybillHeaderVO.CHPROJECTID));
    /*this.keyValue.setBodyValue(row, PraybillItemVO.PK_ORG_V,
        OrgUnitPubService.getOrgVid(this.ctx.getPk_org()));*/
    this.keyValue.setBodyValue(row, PraybillItemVO.BCANPURCHASEORGEDIT,
        UFBoolean.TRUE);
  }
}
