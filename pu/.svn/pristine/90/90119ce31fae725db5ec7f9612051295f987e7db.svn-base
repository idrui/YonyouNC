/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-16 下午03:54:17
 */
package nc.bs.pu.est.rule.billfactory;

import java.util.Map;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;

import org.apache.commons.lang.ArrayUtils;

public abstract class AbstractEstBillGenerator<T extends AggregatedValueObject> {

  private String FIBilltype;

  private String srcBilltype;

  private T[] srcVos;

  public AbstractEstBillGenerator(String srcBilltype, String FIBilltype,
      T[] srcVos) {
    this.srcBilltype = srcBilltype;
    this.FIBilltype = FIBilltype;
    this.srcVos = srcVos;
  }

  public AggregatedValueObject[] genBill() {
    if (ArrayUtils.isEmpty(this.srcVos)
        || StringUtil.isEmptyWithTrim(this.srcBilltype)) {
      return null;
    }
    AggregatedValueObject[] vos = this.execVOChange();
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    for (AggregatedValueObject vo : vos) {
      this.fillHeadInfo(vo.getParentVO());
      this.fillItemInfo(vo.getChildrenVO());
    }
    return vos;
  }

  protected AggregatedValueObject[] execVOChange() {
    this.processSrcVO();
    AggregatedValueObject[] destVos =
        PfServiceScmUtil.executeVOChange(this.srcBilltype, this.FIBilltype,
            this.srcVos);
    return destVos;
  }

  /**
   * 填充VO交换完成后，目的单据的表头信息
   * 
   * @param head
   *          表头VO
   **/
  protected void fillHeadInfo(CircularlyAccessibleValueObject head) {
    // 子类实现
  }

  /**
   * 填充VO交换完成后，目的单据的表体信息
   * 
   * @param items
   *          目标表体VO数组
   **/
  protected void fillItemInfo(CircularlyAccessibleValueObject[] items) {
    // 子类实现
  }

  /** 财务行主键到VO的Map **/
  protected <F extends CircularlyAccessibleValueObject> Map<String, F> getItemMap() {
    return AggVOUtil.createItemMap(this.srcVos);
  }

  protected T[] getSrcVos() {
    return this.srcVos;
  }

  /**
   * 方法功能描述：交换前对财务VO进行加工。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author zhyhang
   * @time 2010-6-19 下午08:04:22
   */
  protected void processSrcVO() {
    // 子类实现
  }

  protected void setSrcVos(T[] srcVos) {
    this.srcVos = srcVos;
  }
}
