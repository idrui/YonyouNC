/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-12 下午01:38:00
 */
package nc.pubimpl.pu.m25.pu.settle.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 虚拟发票VO结构检查
 * @scene
 * 生成虚拟发票
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:15:20
 * @author zhangshqb
 */
public class VirtualVOCheckRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0044")/*@res "结算传入的数据不能生成虚拟发票，请检查!"*/);
    }
    for (InvoiceVO vo : vos) {
      InvoiceHeaderVO header = vo.getParentVO();
      if (null == header) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0045")/*@res "结算传入的数据不能生成完整虚拟发票，请检查!"*/);
      }
      InvoiceItemVO[] items = vo.getChildrenVO();
      if (ArrayUtils.isEmpty(items)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0045")/*@res "结算传入的数据不能生成完整虚拟发票，请检查!"*/);
      }
      for (InvoiceItemVO item : items) {
        if (null == item) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0045")/*@res "结算传入的数据不能生成完整虚拟发票，请检查!"*/);
        }
      }
    }
  }

}