package nc.vo.pu.m25.settle;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>����ʱ�õĳɱ�Ҫ�ء��ۿ۽ӿڣ�</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author wangyf
 * @time 2010-3-25 ����11:59:06
 */
public interface ICostfactorDiscount {
  /**
   * ��һ���������ۼƽ�������
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param dValue
   *          <p>
   * @author wangyf
   * @time 2010-3-26 ����01:43:25
   */
  public void addtoCurrenttotalsettlemoney(UFDouble dValue);

  public UFDouble getNadjustmny();

  /**
   * �õ���̯���
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2010-3-26 ����01:06:37
   */
  public UFDouble getNallotmoney();

  /**
   * �õ���̯����
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2010-3-26 ����01:06:37
   */
  public UFDouble getNallotnum();

  public UFDouble getNcostfactor1();

  public UFDouble getNcostfactor2();

  public UFDouble getNcostfactor3();

  public UFDouble getNcostfactor4();

  public UFDouble getNcostfactor5();

  public UFDouble getNcostfactor6();

  public UFDouble getNcostfactor7();

  public UFDouble getNcostfactor8();

  public UFDouble getNdiscount();

  /**
   * �õ�����SrcID
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2010-3-26 ����01:09:22
   */
  public String getPk_srcmaterial();

  public void setNadjustmny(UFDouble nadjustmny);

  public void setNcostfactor1(UFDouble ncostfactor1);

  public void setNcostfactor2(UFDouble ncostfactor2);

  public void setNcostfactor3(UFDouble ncostfactor3);

  public void setNcostfactor4(UFDouble ncostfactor4);

  public void setNcostfactor5(UFDouble ncostfactor5);

  public void setNcostfactor6(UFDouble ncostfactor6);

  public void setNcostfactor7(UFDouble ncostfactor7);

  public void setNcostfactor8(UFDouble ncostfactor8);

  public void setNdiscount(UFDouble ndiscount);

}
