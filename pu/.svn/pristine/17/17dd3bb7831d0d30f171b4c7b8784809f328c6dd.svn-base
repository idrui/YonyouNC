/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-8 上午10:44:39
 */
package nc.bs.pu.m4t.writeback.pu.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4t.entity.InitialEstViewVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>累计开票数量检查：
 * <li>if 主数量 < 累计开票数量 then 抛出异常(累计开票数量不能超期初暂估单可开票数量)
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-8 上午10:44:39
 */
public class AccInvoiceNumChkRule implements IRule<InitialEstViewVO> {

  /**
   * 父类方法重写
   *
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(InitialEstViewVO[] views) {
    for (InitialEstViewVO view : views) {
      UFDouble nnum = view.getNnum();
      UFDouble naccinvoicenum = view.getNaccinvoicenum();
      if (MathTool.isDiffSign(nnum, naccinvoicenum)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0091")/*@res "存在累计开票数量与期初暂估单数量正负不一致 的行，请检查！"*/);
      }
      if (MathTool.compareTo(MathTool.abs(nnum), MathTool.abs(naccinvoicenum)) < 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0092")/*@res "累计开票数量不能超期初暂估单可开票数量"*/);
      }
    }
  }

}