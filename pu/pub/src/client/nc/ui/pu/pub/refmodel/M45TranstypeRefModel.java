package nc.ui.pu.pub.refmodel;

import nc.ui.pf.pub.TranstypeRefModel;

/**
 * @since 6.0
 * @version 2011-5-16 下午02:09:26
 * @author liugxa
 */

public class M45TranstypeRefModel extends TranstypeRefModel {

  @Override
  public String getWherePart() {
    return super.getWherePart() + this.getFixedWhere();
  }

  /**
   * 增加过滤条件 以适配 单据类型仅为库存采购入库单的交易类型
   * 
   * @return
   */
  private String getFixedWhere() {
    return " and parentbilltype='45' ";
  }

}
