/**
 *
 */
package nc.vo.pu.m25.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 *	发票状态检查,发票状态检查，必须是非审批态；冻结标志=N；（前后台）虚拟发票不能审核；
 * @scene
 * 审批
 * @param
 * 
 *
 * @since 6.3
 * @version 2014-10-22 下午2:39:27
 * @author zhangshqb
 */
public class ApproveStatusChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    StringBuffer buffer = new StringBuffer();
    for (InvoiceVO vo : vos) {
      buffer.append(this.checkStatus(vo));
    }
    if (buffer.length() > 0) {
      ExceptionUtils.wrappBusinessException(buffer.toString());
    }
  }

  /**
   * 检测表体中是否有主原币无税金额大于0的记录 冻结状态且发票主原币金额>0时不能审核
   *
   * @author xiebo
   * @time 2010-1-27 上午11:49:23
   * @param
   * @return
   * @throws
   */
  private StringBuffer checkNorigmnys(InvoiceItemVO[] itemVOs) {
    StringBuffer buffer = new StringBuffer();
    for (InvoiceItemVO itemVO : itemVOs) {
      if (MathTool.compareTo(itemVO.getNorigmny(), UFDouble.ZERO_DBL) > 0) {
        return buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0050")/*@res "冻结状态的正数发票不能审批！"*/);
      }
    }
    return buffer;
  }

  /**
   * 检测发票状态
   *
   * @author xiebo
   * @time 2010-1-27 上午11:59:36
   * @param
   * @return
   * @throws
   */
  private StringBuffer checkStatus(InvoiceVO vo) {
    StringBuffer buffer = new StringBuffer();
    InvoiceHeaderVO headerVO = vo.getParentVO();
    if (null == headerVO) {
      buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0029")/*@res "表头不能为空\r\n"*/);
    }
    else {
      if (POEnumBillStatus.APPROVE.toInt() == headerVO.getFbillstatus()
          .intValue()) {
        buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0051")/*@res "发票已经审批不能再审批；"*/);
      }
      if (ValueUtils.getBoolean(headerVO.getBvirtual())) {
        buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0052")/*@res "虚拟发票不能手工审批\r\n"*/);
      }
      if (headerVO.getBfrozen() != null && headerVO.getBfrozen().booleanValue()) {
        buffer.append(this.checkNorigmnys(vo.getChildrenVO()));
      }
    }
    return buffer;
  }

}