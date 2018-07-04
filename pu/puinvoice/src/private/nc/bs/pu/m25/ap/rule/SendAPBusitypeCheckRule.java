/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-27 下午01:58:32
 */
package nc.bs.pu.m25.ap.rule;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.ref.InvoiceUapRefer;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 检查发票传应付脚本是否配置了传应付驱动
 * @scene
 * 传应付
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:40:34
 * @author zhangshqb
 */
public class SendAPBusitypeCheckRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String pk_busitype = vos[0].getParentVO().getPk_busitype();
    if (!InvoiceUapRefer.hasSendAPDriveAction(pk_busitype, BSContext
        .getInstance().getUserID())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004050_0", "04004050-0018")/*
                                                                   * @res
                                                                   * "发票传应付组件未配置驱动应付保存组件，不能传应付！"
                                                                   */);
    }
  }

}
