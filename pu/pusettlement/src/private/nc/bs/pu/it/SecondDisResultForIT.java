package nc.bs.pu.it;

import nc.bs.pu.m27.feesettle.distribute.SecondDisResult;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ڵ�����Ʊ��̯
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.31
 * @since 6.31
 * @author liangchen1
 * @time 2013-11-28 ����01:12:53
 */
public class SecondDisResultForIT extends SecondDisResult {
  // 0:����,1:�ۿ�,2:��������
  private int ndistributetype;

  public int getNdistributetype() {
    return this.ndistributetype;
  }

  public void setNdistributetype(int ndistributetype) {
    this.ndistributetype = ndistributetype;
  }
}
