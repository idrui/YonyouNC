/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-5 ����08:51:05
 */
package nc.pubitf.pu.m21.pu.m25;

import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.vo.pub.lang.UFDouble;

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
 * @time 2010-2-5 ����08:51:05
 */
public interface IOrderWriteBackParaFor25 extends IOrderWriteBackPara {
  /**
   * ��д�ۼƽ���
   * <p>
   * ��Ʊ��д����ʱ���ۼƿ�Ʊ���
   **/
  public UFDouble getDiffMny();

  /** ��Ʊ�ĵ��� **/
  public UFDouble getPrice();

  /** �Ƿ�ɾ����Ʊ **/
  public boolean isDiscard();

  /** �Ƿ���÷�Ʊ **/
  public boolean isFee();

  /** �Ƿ��û�ȷ���˵��۳��ݲ����ʾ **/
  public boolean isPriceUserConfirm();
}
