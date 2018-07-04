/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-11 ����10:35:55
 */
package nc.bs.pu.m25.writeback.source;

import java.util.List;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.pub.writeback.IWriteBackSource;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-11 ����10:35:55
 */
public interface IInvoiceWriteBackSource extends IWriteBackSource {

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
  public void writeback(List<RewritePara> rwParas, InvoiceUIToBSEnv env);

}
