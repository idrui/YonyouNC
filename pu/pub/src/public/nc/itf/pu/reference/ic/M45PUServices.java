/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-3 ����08:44:05
 */
package nc.itf.pu.reference.ic;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.general.IICGenRewritePara;
import nc.pubitf.ic.m45.m21.IPurchaseInQueryFor21;
import nc.pubitf.ic.m45.m21.IRewrite45For21;
import nc.pubitf.ic.m45.m23.IPurchaseInMaintainserviceFor23;
import nc.pubitf.ic.m45.m25.IParameter45For25;
import nc.pubitf.ic.m45.m25.IRewrite45For25;
import nc.pubitf.ic.transtype.IInOutTransTypeQueryService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���ⵥ45�ṩ���ɹ��ķ�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-3 ����08:44:05
 */
public class M45PUServices {

  /**
   * ���ݵ����������ѯ�Ƿ���������ⵥ
   * 
   * @param bids
   * @return
   */
  public static Map<String, UFBoolean> getMapBysrcBid(String[] bids) {
    IPurchaseInMaintainserviceFor23 purchaseIn =
        NCLocator.getInstance().lookup(IPurchaseInMaintainserviceFor23.class);
    try {
      return purchaseIn.queryPurchaseVOsFor23(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
      return null;
    }
  }

  public static Map<String, UFBoolean> getMapBysrcHid(String[] hids) {
    IPurchaseInQueryFor21 purchaseIn =
        NCLocator.getInstance().lookup(IPurchaseInQueryFor21.class);
    try {
      return purchaseIn.getMapBysrcHid(hids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
      return null;
    }
  }

  /**
   * ���������������õ���ⵥ���������Ƿ�Ӱ��ɱ���־��
   * <p>
   * <b>����˵��</b>
   * 
   * @param trantypes ��������ID���飨V61������
   * @return <p>
   * @since 6.0
   * @author zhyhang
   * @time 2010-5-22 ����10:18:16
   */
  public static Map<String, UFBoolean> getStockTrantypeAffectCostFlag(
      String[] trantypes) {
    IInOutTransTypeQueryService service =
        NCLocator.getInstance().lookup(IInOutTransTypeQueryService.class);
    Map<String, UFBoolean> retMap =
        new HashMap<String, UFBoolean>(trantypes.length);
    for (int i = 0; i < trantypes.length; i++) {
      String type = trantypes[i];
      try {
        retMap.put(type, service.isAffectCostById(type) ? UFBoolean.TRUE
            : UFBoolean.FALSE);
      }
      catch (BusinessException e) {
        // ��־�쳣
        ExceptionUtils.wrappException(e);
      }
    }
    return retMap;
  }

  /**
   * ���ݶ����Ų�ѯ��ⵥ����id
   * 
   * @param codes
   * @return key:�ɹ������� value����ⵥ����id
   */
  public static MapList<String, String> queryBidByOrderCode(String[] codes) {
    try {
      IPurchaseInQueryFor21 service =
          NCLocator.getInstance().lookup(IPurchaseInQueryFor21.class);
      return service.queryBidByOrderCode(codes);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return null;
  }
  
  /**
   * ���ݶ����Ų�ѯ��ⵥ����id
   * 
   * @param bids
   * @return key:�ɹ������� value����ⵥ����id
   */
  public static MapList<String, String> queryBidByOrderBid(String[] bids) {
    try {
      IPurchaseInQueryFor21 service =
          NCLocator.getInstance().lookup(IPurchaseInQueryFor21.class);
      return service.queryPurchaseInBIDsByFirstBIDs(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return null;
  }

  /**
   * ��ѯ��������Ӧ����ⵥ�Ƿ��Ѿ�ת��
   * 
   * @param bids����������ID
   * @return key������������ value���Ѿ�ת��ΪY������ΪN
   */
  public static Map<String, UFBoolean> queryIsFixedAssetFor23(String[] bids) {
    try {
      IPurchaseInMaintainserviceFor23 service =
          NCLocator.getInstance().lookup(IPurchaseInMaintainserviceFor23.class);
      return service.queryIsFixedAssetFor23(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * ������ⵥid�ҵ�������
   * 
   * @param bids
   * @return
   */
  public static Map<String, String> queryOrderCodeByPurchaseInBid(String[] bids) {
    IPurchaseInQueryFor21 service =
        NCLocator.getInstance().lookup(IPurchaseInQueryFor21.class);
    try {
      return service.queryOrderCodeByPurchaseInBid(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * ���������������ɹ�������д�˿ⵥ45
   * <p>
   * <b>����˵��</b>
   * 
   * @param paras
   *          ��д����
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-4 ����11:32:18
   */
  public static void writeback45For21(IICGenRewritePara[] paras) {
    IRewrite45For21 service =
        NCLocator.getInstance().lookup(IRewrite45For21.class);
    try {
      service.rewrite45For21Rep(paras);
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���������������ɹ���Ʊ��д�ɹ���ⵥ45��
   * <p>
   * <b>����˵��</b>
   * 
   * @param wbVos
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-2-3 ����08:48:23
   */
  public static void writeback45For25(IParameter45For25[] wbVos) {
    IRewrite45For25 service =
        NCLocator.getInstance().lookup(IRewrite45For25.class);
    try {
      service.rewrite45AccInNumFor25(wbVos);
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
  }

}
