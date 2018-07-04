package nc.pubitf.pu.m27;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>Ϊ��������ṩ�Ĳ�ѯ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-8 ����10:47:00
 */
public interface ISettleBillQueryForIA {
  /**
   * ����������������ѯ���½����
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_costregion
   *          �ɱ���
   * @param pk_materials
   *          ���ϵ�OID����
   * @return ���½���۵�MAP<����OID�����½����>
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author duy
   * @time 2010-9-8 ����10:48:48
   */
  public Map<String, UFDouble> queryLatestSettlePrice(String pk_costregion,
      String[] pk_materials) throws BusinessException;
}
