/**
 *
 */
package nc.vo.pu.m25.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 参数正确性检查,数组元素（VO）是否为空；VO是否有表体
 * @scene
 * 保存的BP,前台新增保存,前台更新保存
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:18:27
 * @author zhangshqb
 */
public class ParaValidityChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0098")/*@res "要操作的发票数据为空，请检查！"*/);
    }
    else {
      for (InvoiceVO vo : vos) {
        if (null == vo) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0098")/*@res "要操作的发票数据为空，请检查！"*/);
        }
        else if (null == vo.getParentVO()) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0099")/*@res "要操作的发票数据不完整，请检查！"*/);
        }
        else if (ArrayUtils.isEmpty(vo.getChildrenVO())) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0099")/*@res "要操作的发票数据不完整，请检查！"*/);
        }
        else {
          for (InvoiceItemVO item : vo.getChildrenVO()) {
            if (null == item) {
              ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0100")/*@res "要操作的发票存在空数据行，请检查！"*/);
            }
          }
        }
      }

    }
  }

}