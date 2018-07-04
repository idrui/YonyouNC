/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-27 ����01:49:37
 */
package nc.vo.pu.pub.entity;

import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>PO27������ֵVO
 * <li>�����������ƻ����Ƿ񰴼ƻ����ݹ�ȥ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-27 ����01:49:37
 */
public class PO27VO {
  private UFBoolean bplanprior = UFBoolean.FALSE;

  private PriceSource[] ps;

  /**
   * PO27VO �Ĺ�����
   * 
   * @param bplanprior
   * @param ps
   */
  public PO27VO(UFBoolean bplanprior, PriceSource[] ps) {
    this.bplanprior = bplanprior;
    this.ps = ps;
  }

  /**
   * @return bplanprior
   */
  public UFBoolean getBplanprior() {
    return this.bplanprior;
  }

  /**
   * @return ps
   */
  public PriceSource[] getPs() {
    return this.ps;
  }

  /**
   * @param bplanprior Ҫ���õ� bplanprior
   */
  public void setBplanprior(UFBoolean bplanprior) {
    this.bplanprior = bplanprior;
  }

  /**
   * @param ps Ҫ���õ� ps
   */
  public void setPs(PriceSource[] ps) {
    this.ps = ps;
  }

}
