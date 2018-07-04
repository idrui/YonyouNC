/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-29 下午07:42:51
 */
package nc.vo.pu.m25.rule.maintain;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.rule.RowNoCheckRule;

/**
 * 
 * @description
 * 发票行号合法性检查
 * @scene
 * 保存的BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:24:49
 * @author zhangshqb
 */
public class InvoiceRowNoCheckRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    RowNoCheckRule rule = new RowNoCheckRule();
    rule.checkRowNo(vos, InvoiceItemVO.CROWNO);
  }

}
