/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-27 上午11:55:57
 */
package nc.bs.pu.m25.ap.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 过滤掉未传过应付的VO
 * @scene
 * 取消传应付
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午2:57:50
 * @author zhangshqb
 */
public class InvoiceCancelAPFilterRule implements IFilterRule<InvoiceVO> {

  @Override
  public InvoiceVO[] process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    List<InvoiceVO> apVos = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : vos) {
      UFBoolean apFlag = vo.getParentVO().getBapflag();
      if (ValueUtils.getBoolean(apFlag)) {
        apVos.add(vo);
      }
    }
    return apVos.toArray(new InvoiceVO[apVos.size()]);
  }

}
