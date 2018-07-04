/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 ����10:14:24
 */
package nc.vo.pu.m20.pub;

import java.util.ArrayList;

import nc.vo.pu.m20.entity.writeback.OrderWriteBackVO;

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
 * @author linsf
 * @time 2010-2-4 ����10:14:24
 */
public class PraybillVOUtil {

  private static PraybillVOUtil instance = new PraybillVOUtil();

  private PraybillVOUtil() {
    // ȱʡ���췽��
  }

  /**
   * ����������������ʵ��ģʽ��
   * <p>
   * <b>����˵��</b>
   * 
   * @return ��ʵ��
   *         <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 ����04:24:07
   */
  public static PraybillVOUtil getInstance() {
    return PraybillVOUtil.instance;
  }

  /**
   * ���������������Ӷ�����дVO�л�ȡ�빺��bids��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          ��дvo
   * @return �빺��bids
   *         <p>
   * @since 6.0
   * @author linsf
   * @time 2010-2-4 ����10:15:22
   */
  public String[] getBidsFromWBVos(OrderWriteBackVO[] vos) {
    ArrayList<String> retVal = new ArrayList<String>();
    for (OrderWriteBackVO vo : vos) {
      retVal.add(vo.getPk_praybill_b());
    }
    return retVal.toArray(new String[retVal.size()]);
  }
}
