/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-11 上午10:14:39
 */
package nc.ui.pu.m4t.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置币种相关的可编辑性
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-11 上午10:14:39
 */
public class EditableByCurrency {
  private BillCardPanel panel;

  public EditableByCurrency(BillCardPanel panel) {
    this.panel = panel;
  }

  /**
   * 方法功能描述：设置可编辑性
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-9-11 上午10:16:17
   */
  public void setEditable() {
    this.panel.getHeadItem(InitialEstHeaderVO.CCURRENCYID).setEnabled(false);

    // 本币
    String ccurrencyid =
        (String) this.panel.getHeadItem(InitialEstHeaderVO.CCURRENCYID)
            .getValueObject();
    // 原币
    String corigcurrencyid =
        (String) this.panel.getHeadItem(InitialEstHeaderVO.CORIGCURRENCYID)
            .getValueObject();

    // 原币为空不让编辑
    // 原币、本币相同不允许编辑
    if (StringUtil.isEmptyWithTrim(corigcurrencyid)
        || corigcurrencyid.equals(ccurrencyid)) {
      this.panel.getHeadItem(InitialEstHeaderVO.NEXCHANGERATE)
          .setEnabled(false);
    }
    else {
      this.panel.getHeadItem(InitialEstHeaderVO.NEXCHANGERATE).setEnabled(true);
    }
  }
}
