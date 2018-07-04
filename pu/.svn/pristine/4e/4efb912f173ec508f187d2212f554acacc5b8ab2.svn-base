/**
 *
 */
package nc.vo.pu.m25.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 表头日期检查:票到日期不能早于发票日期！期初发票的发票日期不能晚于系统启用日期
 * @scene
 * 保存的BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:23:37
 * @author zhangshqb
 */
public class InvoiceDateChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    StringBuffer buffer = new StringBuffer();
    for (InvoiceVO vo : vos) {
      InvoiceHeaderVO headerVO = vo.getParentVO();
      if (headerVO.getDarrivedate().beforeDate(headerVO.getDbilldate())) {
        buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004050_0", "04004050-0057")/* @res "票到日期不早于发票日期" */);
        ExceptionUtils.wrappBusinessException(buffer.toString());
      }
      // 检查期初发票的发票日期
      this.checkInitialInvcDate(headerVO);
    }
  }

  private void checkInitialInvcDate(InvoiceHeaderVO headerVO) {
    if (!UFBoolean.TRUE.equals(headerVO.getBinitial())) {
      return;
    }
    UFDate billdate = headerVO.getDbilldate();

    UFDate limitedDate =
        PUSysParamUtil.getINI02BeforeDate(headerVO.getPk_org());
    if (limitedDate == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0122")/*
                                                                   * @res
                                                                   * "采购期初日期（参数INI02）未设置！"
                                                                   */);
    }
    else if (billdate.after(limitedDate.asLocalEnd())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004050_0", "04004050-0111")/*
                                                    * 期初发票的发票日期不能晚于采购期初日期的前一天！
                                                    */);
    }
  }

}
