/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 ����11:35:50
 */
package nc.vo.pu.m20.entity.writeback;

import nc.vo.pu.m20.enumeration.EnumOperate;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b> �۸�������Ѱ���۵����ɹ���ͬ��д���ɴ�������
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-4 ����11:35:50
 */
public class GenNumWriteBackVO extends BaseWriteBackVO {

  private static final long serialVersionUID = -2628939182581274025L;

  // ������ʶ�������ġ�ɾ
  private EnumOperate operateFlag;

  // �Ƿ�����������
  private UFBoolean isSaOrder;

  // �����������к�
  private String crowno;

  /**
   * �����������к�
   * 
   * @return
   */
  public String getCrowno() {
    return this.crowno;
  }

  /**
   * �����������к�
   * 
   * @param crowno
   */
  public void setCrowno(String crowno) {
    this.crowno = crowno;
  }

  /**
   * �Ƿ�����������
   * 
   * @return
   */
  public UFBoolean getIsSaOrder() {
    return this.isSaOrder;
  }

  /**
   * �Ƿ�����������
   * 
   * @param isSaOrder
   */
  public void setIsSaOrder(UFBoolean isSaOrder) {
    this.isSaOrder = isSaOrder;
  }

  /**
   * ������ʶ�������ġ�ɾ
   * 
   * @return opeFlag
   */
  public EnumOperate getOperateFlag() {
    return this.operateFlag;
  }

  /**
   * @param opeFlag
   *          ������ʶ�������ġ�ɾ
   */
  public void setOperateFlag(EnumOperate opeFlag) {
    this.operateFlag = opeFlag;
  }

}
