package nc.pubitf.pu.position;

import java.io.Serializable;

/**
 * �ɹ��ڲ�ѯ����VO
 * 
 * @since 6.0
 * @version 2012-10-31 ����04:35:52
 * @author wuxla
 */
public class PositionQueryVO implements Serializable {
  private static final long serialVersionUID = -5426404702928672356L;

  /**
   * ���ϻ������࣬������ĩ�����࣬ĿǰֻӦ���ں�ͬ
   */
  private String pk_marbaseclass;

  /**
   * �ɹ�������
   */
  private String pk_position;

  /**
   * �ɹ���֯������
   */
  private String pk_purchaseorg;

  /**
   * ����OID�������ϻ������ࣨĿǰֻ���ں�ͬ�����ʣ�����һ������
   */
  private String pk_srcmaterial;

  /**
   * �����֯��VO����ʱ�����빺������֯���߲ɹ�������������֯�����û�в�������ֵ
   */
  private String pk_stockorg;

  /**
   * @return ���ϻ�������
   */
  public String getPk_marbaseclass() {
    return this.pk_marbaseclass;
  }

  /**
   * @return �ɹ�������
   */
  public String getPk_position() {
    return this.pk_position;
  }

  /**
   * @return �ɹ���֯
   */
  public String getPk_purchaseorg() {
    return this.pk_purchaseorg;
  }

  /**
   * @return ����OID
   */
  public String getPk_srcmaterial() {
    return this.pk_srcmaterial;
  }

  /**
   * @return �����֯
   */
  public String getPk_stockorg() {
    return this.pk_stockorg;
  }

  /**
   * �������ϻ������࣬������ĩ�����࣬Ŀǰֻ�к�ͬʹ��
   * 
   * @param pk_marbaseclass
   */
  public void setPk_marbaseclass(String pk_marbaseclass) {
    this.pk_marbaseclass = pk_marbaseclass;
  }

  /**
   * ���òɹ�������
   * 
   * @param pk_position
   */
  public void setPk_position(String pk_position) {
    this.pk_position = pk_position;
  }

  /**
   * ���òɹ���֯
   * 
   * @param pk_purchaseorg
   */
  public void setPk_purchaseorg(String pk_purchaseorg) {
    this.pk_purchaseorg = pk_purchaseorg;
  }

  /**
   * ��������OID
   * �����ϻ������໥�⣬ֻ��һ����ֵ
   * ���ϻ�������Ŀǰֻ�к�ͬ�ã���������ģ�������ֵ
   * 
   * @param pk_srcmaterial
   */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.pk_srcmaterial = pk_srcmaterial;
  }

  /**
   * �����֯
   * VO����ʱ�����빺������֯���߲ɹ�������������֯�����û�ж��գ������ͬ���۸�����������������ֵ
   * 
   * @param pk_stockorg
   */
  public void setPk_stockorg(String pk_stockorg) {
    this.pk_stockorg = pk_stockorg;
  }
}
