/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-4 ����04:12:44
 */
package nc.pubitf.pu.m21.ia.mif;

import java.util.Map;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>Ϊ��������ṩ�Ľӿ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-4 ����04:12:44
 */
public interface IOrderQueryForIf {

  /**
   * �����������������ݲ�����֯+����ȡ�����м�
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_financeorg ������֯
   * @param pk_srcmaterials ����OID[]
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-4 ����04:15:56
   */
  Map<String, UFDouble> getNewPriceForIA(String pk_financeorg,
      String[] pk_srcmaterials);
}
