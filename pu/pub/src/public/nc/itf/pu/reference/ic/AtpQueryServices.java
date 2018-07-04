/**
 * $�ļ�˵��$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-23 ����01:51:58
 */
package nc.itf.pu.reference.ic;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.atp.IAtpQuery;
import nc.pubitf.ic.onhand.IOnhandQry;
import nc.vo.ic.atp.entity.AtpVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ATP��ѯ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-10-23 ����01:51:58
 */
public class AtpQueryServices {

  /**
   * �����������������ƻ����ڲ�ѯAtp������Ϣ �����ƻ�����Ϊ�գ������ѯ��������������Ϣһ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_calbodyoids
   * @param cmaterialoids
   * @param ddates
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-10-23 ����01:53:09
   */
  public static AtpVO[] queryAtpVOs(String[] pk_calbodyoids,
      String[] cmaterialoids, UFDate[] ddates) {

    IAtpQuery service = NCLocator.getInstance().lookup(IAtpQuery.class);
    try {
      return service.queryAtpVOs(pk_calbodyoids, cmaterialoids, ddates);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return null;
  }

  /**
   * �������ڿ����֯���ִ����������Ƕ��ᣬԤ�������״̬�Ƿ���ã��ֿ��Ƿ�ƻ�����
   * 
   * @param pk_org �����֯���飬�����ϲ�����Ҫ��һһ��Ӧ
   * @param cmaterialvid������VID����
   * @return key:pk_org+cmaterialvid;value:nonhandnum
   */
  public static Map<String, UFDouble> queryInvOnhandnum(String[] pk_org,
      String[] cmaterialvid) {
    try {
      return NCLocator.getInstance().lookup(IOnhandQry.class)
          .queryInvOnhandnum(pk_org, cmaterialvid);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  public static Map<String, UFDouble> queryVendorStoreDatasByOid(
      String[] cvendorid, String cmaterialoid) {
    IOnhandQry service = NCLocator.getInstance().lookup(IOnhandQry.class);
    try {
      return service.queryVendorStoreDatasByOid(cvendorid, cmaterialoid);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
