/**
 * $�ļ�˵��$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-10 ����08:34:03
 */
package nc.vo.pu.pub.writeback;

import java.util.List;

import nc.impl.pubapp.bill.rewrite.RewritePara;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���д���ε�ͳһ�ӿ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-2-10 ����08:34:03
 */
public interface IWriteBackSource {
  /**
   * �������������������ε��ݽ��������Ļ�д��
   * <p>
   * <b>����˵��</b>
   * 
   * @param rwParas �����ϰ������ɵĻ�д�����б�,������ת��Ϊ���εĻ�дVO
   *          <p>
   *          ��ֻ���һ�����ε�������
   *          <p>
   * @since 6.0
   * @author zhyhang
   * @time 2010-2-10 ����08:35:27
   */
  public void writeback(List<RewritePara> rwParas);
}
