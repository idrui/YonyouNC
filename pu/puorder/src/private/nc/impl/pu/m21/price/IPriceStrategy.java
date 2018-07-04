package nc.impl.pu.m21.price;

import java.util.Map;

import nc.vo.bd.material.pu.MaterialPuVO;
import nc.vo.pu.m21.query.price.OrderPriceQueryParam;
import nc.vo.pu.pub.enumeration.PricePriority;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ѯ��ʱ�ļ۸����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 ����03:38:58
 */
public interface IPriceStrategy {

  /**
   * ��������������ѯ�ۡ�
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author duy
   * @time 2010-3-21 ����03:49:38
   */
  public void queryPrice();

  /**
   * �����������������ϵ����Ĳɹ���֯ҳǩ�����ԡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param puvoMap ���ϵ����Ĳɹ���֯ҳǩ�����ԣ�key-���ϵ�PK��value-�ɹ���֯ҳǩ���Զ���
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 ����10:36:57
   */
  public void setMaterialPuVO(Map<String, MaterialPuVO> puvoMap);

  /**
   * �����������������ü۸����Ȳ��ԡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param pp
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-24 ����09:28:39
   */
  public void setPricePiority(PricePriority pp);

  /**
   * ������������������ѯ�۲������顣
   * <p>
   * <b>����˵��</b>
   * 
   * @param params ѯ�۲�������
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 ����04:02:36
   */
  public void setQueryParameter(OrderPriceQueryParam param);
}
