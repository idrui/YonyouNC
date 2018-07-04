/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-2 下午01:40:31
 */
package nc.vo.pu.m4t.entity;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单的视图VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-2 下午01:40:31
 */
public class InitialEstViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = 6706181690653666406L;

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.pattern.model.entity.view.AbstractDataView#getMetaData()
   */
  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance()
        .getBillViewMeta(InitialEstVO.class);
  }

  /** 累计开票数量 getter 方法 */
  public UFDouble getNaccinvoicenum() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NACCINVOICENUM);
  }

  /** 累计结算数量 getter 方法 */
  public UFDouble getNaccsettlenum() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NACCSETTLENUM);
  }

  /** 累计冲减暂估金额 getter 方法 */
  public UFDouble getNaccwashmny() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NACCWASHMNY);
  }

  /** 主数量 getter 方法 */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NNUM);
  }

  /** 累计开票数量 setter 方法 */
  public void setNaccinvoicenum(UFDouble naccinvoicenum) {
    this.setAttributeValue(InitialEstItemVO.NACCINVOICENUM, naccinvoicenum);
  }

  /** 累计结算数量 setter 方法 */
  public void setNaccsettlenum(UFDouble naccsettlenum) {
    this.setAttributeValue(InitialEstItemVO.NACCSETTLENUM, naccsettlenum);
  }

  /** 累计冲减暂估金额 setter 方法 */
  public void setNaccwashmny(UFDouble naccwashmny) {
    this.setAttributeValue(InitialEstItemVO.NACCWASHMNY, naccwashmny);
  }

  /** 主数量 setter 方法 */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(InitialEstItemVO.NNUM, nnum);
  }

}
