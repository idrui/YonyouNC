package nc.impl.pu.m21.price;

import nc.vo.bd.material.fi.MaterialFiVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ƻ���ѯ�۲���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 ����03:57:58
 */
public class PlanPriceStrategory extends BaseDocPriceStrategy {
  @Override
  protected String getPriceField() {
    return MaterialFiVO.PLANPRICE;
  }
}
