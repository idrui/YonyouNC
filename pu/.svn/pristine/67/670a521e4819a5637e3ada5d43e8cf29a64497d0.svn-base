/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-7 上午08:44:54
 */
package nc.vo.pu.m4t.rule;

import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.pub.enumeration.EnumDiscounttaxtype;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置表体默认值
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-7 上午08:44:54
 */
public class LineDefaultValue {
  private LoginContext ctx;

  private IKeyValue keyValue;

  public LineDefaultValue(IKeyValue keyValue, LoginContext ctx) {
    this.keyValue = keyValue;
    this.ctx = ctx;
  }

  /**
   * 方法功能描述：设置表体默认值
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-7 上午09:19:52
   */
  public void setDefaultValue(int row) {
    if (this.keyValue.getItemCount() == 0) {
      return;
    }
    // 集团
    if (this.keyValue.getBodyValue(row, InitialEstItemVO.PK_GROUP) == null) {
      this.keyValue.setBodyValue(row, InitialEstItemVO.PK_GROUP,
          this.ctx.getPk_group());
    }
    // 财务组织
    if (this.keyValue.getBodyValue(row, InitialEstItemVO.PK_ORG) == null) {
      this.keyValue.setBodyValue(row, InitialEstItemVO.PK_ORG,
          this.ctx.getPk_org());
    }
    // 换算率
    if (this.keyValue.getBodyValue(row, InitialEstItemVO.VCHANGERATE) == null) {
      this.keyValue.setBodyValue(row, InitialEstItemVO.VCHANGERATE,
          "1.0000/1.0000");
    }

    // 扣税类别
    if (this.keyValue.getBodyValue(row, InitialEstItemVO.FTAXTYPEFLAG) == null) {
      this.keyValue.setBodyValue(row, InitialEstItemVO.FTAXTYPEFLAG,
          EnumDiscounttaxtype.TAXOUT.value());
    }
  }
}
