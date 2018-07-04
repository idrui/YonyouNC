/**
 * 
 */
package nc.vo.pu.m25.rule.maintain;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.scmpub.util.VOFieldLengthChecker;

/**
 * 
 * @description
 * 发票VO的极限值检查<br>
 * 是否超最大长度<br>
 * 数值型属性极限值检查,各个数量金额字段是否超最大值.
 * @scene
 * 保存的BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:25:56
 * @author zhangshqb
 */
public class InvoiceLimitCheckRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    // 调用公共处理方法，针对所有字段而非只有数字字段
    VOFieldLengthChecker.checkVOFieldsLength(vos);
  }

}
