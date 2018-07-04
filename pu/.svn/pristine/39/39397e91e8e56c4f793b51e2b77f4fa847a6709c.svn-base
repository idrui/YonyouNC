/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-8 下午01:52:10
 */
package nc.bs.pu.m25.maintain.rule.freeze;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>解冻时清除相关信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-8 下午01:52:10
 */
public class InvoiceUnFreezeClearInfoRule implements ICompareRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos, InvoiceVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (InvoiceVO vo : vos) {
      InvoiceHeaderVO header = vo.getParentVO();
      // 冻结标志
      header.setBfrozen(UFBoolean.FALSE);
      // 冻结时间
      header.setTfrozentime(null);
      // 冻结人
      header.setPk_frozenuser(null);
      // 冻结原因
      header.setVfrozenreason(null);
      header.setStatus(VOStatus.UPDATED);
    }

  }

}
