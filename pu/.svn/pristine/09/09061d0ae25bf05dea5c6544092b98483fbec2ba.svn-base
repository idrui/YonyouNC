package nc.vo.pu.m25.rule.maintain;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.scmmm.vo.scmpub.scale.BillVOScaleCheckProcessor;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceScaleProcessor;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 发票精度检查
 * @scene
 * 保存的BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:24:23
 * @author zhangshqb
 */
public class InvoiceScaleCheckRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    BillVOScaleCheckProcessor scaleChecker =
        new BillVOScaleCheckProcessor(vos[0].getParentVO().getPk_group(), vos);
    new InvoiceScaleProcessor().setScaleForCheck(scaleChecker);
  }

}
