/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-7 下午04:34:46
 */
package nc.bs.pu.m25.maintain.rule.freeze;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>冻结时填充一些必要信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-7 下午04:34:46
 */
public class InvoiceFreezeInfoFillRule implements ICompareRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos, InvoiceVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (InvoiceVO vo : vos) {
      InvoiceHeaderVO header = vo.getParentVO();
      // 冻结标志
      header.setBfrozen(UFBoolean.TRUE);
      // 冻结时间
      header.setTfrozentime(AppContext.getInstance().getBusiDate());
      // 冻结人
      header.setPk_frozenuser(BSContext.getInstance().getUserID());

      header.setStatus(VOStatus.UPDATED);
    }
  }
}
