package nc.ui.pu.pub.refmodel;

import nc.ui.pf.pub.TranstypeRefModel;

/**
 * @since 6.0
 * @version 2011-5-16 ����02:09:26
 * @author liugxa
 */

public class M45TranstypeRefModel extends TranstypeRefModel {

  @Override
  public String getWherePart() {
    return super.getWherePart() + this.getFixedWhere();
  }

  /**
   * ���ӹ������� ������ �������ͽ�Ϊ���ɹ���ⵥ�Ľ�������
   * 
   * @return
   */
  private String getFixedWhere() {
    return " and parentbilltype='45' ";
  }

}
