package nc.pubitf.pu.m20.ec.m1;

import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���������д�빺���Ƿ񷢲�����������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-27 ����11:41:23
 */
public interface IReWrite20ForM1 {
  /**
   * �����������������������д�빺���Ƿ񷢲�����������
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_praybill_bs
   *          �빺��������PK
   * @throws BusinessException
   *           ҵ���쳣
   * @since 6.0
   * @author GGR
   * @time 2010-5-27 ����11:42:29
   */
  void backWrite(String[] pk_praybill_bs) throws BusinessException;
}
