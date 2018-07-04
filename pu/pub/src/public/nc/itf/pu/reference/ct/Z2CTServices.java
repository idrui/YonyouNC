/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-26 ����02:44:31
 */
package nc.itf.pu.reference.ct;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.ct.purdaily.ICtPayPlanQuery;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ct.purdaily.ICtQueryForOrder;
import nc.pubitf.ct.purdaily.IOrderToCtRefWhereSql;
import nc.pubitf.ct.purdaily.IOrderToCtRelating;
import nc.pubitf.ct.purdaily.puorder.IReWriteZ2For21;
import nc.vo.ct.entity.CtBusinessVO;
import nc.vo.ct.entity.CtRelatingVO;
import nc.vo.ct.entity.CtWriteBackForOrderVO;
import nc.vo.ct.purdaily.entity.CtPuVO;
import nc.vo.ct.purdaily.entity.CtPubillViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ͬ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-26 ����02:44:31
 */
public class Z2CTServices {
  /**
   * ������
   * 
   * @param pk_org
   * @return
   */
  public static boolean checkPO88Para(String pk_org) {
    if (!SysInitGroupQuery.isCTEnabled()) {
      return true;
    }
    try {

      ICtPayPlanQuery service =
          NCLocator.getInstance().lookup(ICtPayPlanQuery.class);
      return service.checkPO88Para(pk_org);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return true;
  }

  /**
   * ����������������ͬ�Ų���
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_org
   * @param pk_supplier
   * @param pk_material
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-30 ����02:40:28
   */
  public static String getRefWherePart(String pk_org, String pk_supplier,
      String pk_material, String corigcurrencyid, String dbilldate)
      throws BusinessException {
    IOrderToCtRefWhereSql purefct =
        NCLocator.getInstance().lookup(IOrderToCtRefWhereSql.class);
    return purefct.getWherePart(pk_org, pk_supplier, pk_material,
        corigcurrencyid, dbilldate, UFBoolean.FALSE);
  }

  /**
   * �����������������ݺ�ͬbid�����ؽ������ͼ����Ʒ�ʽ
   * <p>
   * <b>����˵��</b>
   * 
   * @param pks
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-3 ����10:08:55
   */
  public static Map<String, CtBusinessVO> queryCtBusinessByPks(String[] pks)
      throws BusinessException {
    if (!SysInitGroupQuery.isCTEnabled()) {
      return new HashMap<String, CtBusinessVO>();
    }
    ICtQueryForOrder service =
        NCLocator.getInstance().lookup(ICtQueryForOrder.class);
    return service.queryCtBusinessByPks(pks);
  }

  /**
   * Ϊ���ɿ����ṩ�Ĳ�ѯ����ͬ�����������ģ���Ҫ���������������ĺ�ͬ��
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   * 
   * @param pks
   * @return
   */
  public static Map<String, CtPubillViewVO> queryCtInfoForCenpuByBid(
      String[] pks) {
    if (!SysInitGroupQuery.isCTEnabled()) {
      return null;
    }
    ICtQueryForOrder service =
        NCLocator.getInstance().lookup(ICtQueryForOrder.class);
    try {
      return service.queryCtInfoForCenpuByBid(pks);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  public static Map<String, CtPuVO> queryOrgTypeByPks(String[] pks) {
    if (!SysInitGroupQuery.isCTEnabled()) {
      return null;
    }
    ICtQueryForOrder service =
        NCLocator.getInstance().lookup(ICtQueryForOrder.class);
    try {
      return service.queryOrgTypeByPks(pks);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  public static Map<String, CtPubillViewVO> queryViewVOsByBPks(String[] pks) {
    if (!SysInitGroupQuery.isCTEnabled()) {
      return null;
    }
    ICtQueryForOrder service =
        NCLocator.getInstance().lookup(ICtQueryForOrder.class);
    try {
      return service.queryViewVOsByBPks(pks);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  // /**
  // * ��������������ͨ����ͬ�����鶩�����Ʒ�ʽ�����ں�ͬ���Ƽ��
  // * <p>
  // * <b>����˵��</b>
  // *
  // * @param value
  // * @return
  // * @throws BusinessException <p>
  // * @since 6.0
  // * @author wuxla
  // * @time 2010-5-30 ����10:07:59
  // */
  // public static Integer queryCtltypebypk(String value) throws
  // BusinessException {
  // if (!SysInitGroupQuery.isCTEnabled()) {
  // return -1;
  // }
  // IBusinessTypeService service =
  // NCLocator.getInstance().lookup(IBusinessTypeService.class);
  // return service.queryCtltypebypk(value);
  // }

  //
  // /**
  // * �����������������Ͽ��Ʒ�ʽ
  // * <p>
  // * <b>����˵��</b>
  // *
  // * @param value
  // * @return
  // * @throws BusinessException <p>
  // * @since 6.0
  // * @author wuxla
  // * @time 2010-8-2 ����11:23:57
  // */
  // public static Integer queryMaterial(String value, String pk_group)
  // throws BusinessException {
  // IBusinessTypeService service =
  // NCLocator.getInstance().lookup(IBusinessTypeService.class);
  // return service.queryMaterial(value, pk_group);
  // }

  // /**
  // * �����������������ݺ�ͬ���������õ���Ӧԭ�Һ�˰���ۣ����ں�ͬ���Ƽ��
  // * <p>
  // * <b>����˵��</b>
  // *
  // * @param pks
  // * @return
  // * @throws BusinessException <p>
  // * @since 6.0
  // * @author wuxla
  // * @time 2010-5-30 ����10:01:48
  // */
  // public static Map<String, UFDouble> queryNoriTaxPriceByPks(String[] pks)
  // throws BusinessException {
  // ICtQueryForOrder service =
  // NCLocator.getInstance().lookup(ICtQueryForOrder.class);
  // return service.queryNoriTaxPriceByPks(pks);
  // }

  // /**
  // * �����������������ݱ������������ѯ��ͬ����
  // * <p>
  // * <b>����˵��</b>
  // *
  // * @param ids
  // * @return <p>
  // * @since 6.0
  // * @author wuxla
  // * @time 2010-8-2 ����04:24:53
  // */
  // public static CtPuBVO[] queryPurdailyBVO(String[] ids)
  // throws BusinessException {
  // IPurdailyMaintain service =
  // NCLocator.getInstance().lookup(IPurdailyMaintain.class);
  // return service.queryPurdailyBVO(ids);
  // }

  // /**
  // * ��������������ͨ����ͬ���� ���ؽ�������VO��MAP
  // * <p>
  // * <b>����˵��</b>
  // *
  // * @param ids
  // * @return <p>
  // * @since 6.0
  // * @author wuxla
  // * @time 2010-8-2 ����04:40:37
  // */
  // public static Map<String, BusinessSetVO> querySetVOsByPk(String[] ids)
  // throws BusinessException {
  // IBusinessTypeService service =
  // NCLocator.getInstance().lookup(IBusinessTypeService.class);
  // return service.querySetVOsByPk(ids);
  // }

  /**
   * ���������������ɹ�����������ͬ����
   * <p>
   * <b>����˵��</b>
   * 
   * @throws BusinessException <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-22 ����02:37:42
   */
  public static CtPubillViewVO[] relatingCT(CtRelatingVO[] queryVOs)
      throws BusinessException {
    IOrderToCtRelating service =
        NCLocator.getInstance().lookup(IOrderToCtRelating.class);
    return service.queryForRelating(queryVOs, UFBoolean.FALSE);
  }

  /**
   * ���������������ɹ�������д�ɹ���ͬ
   * <p>
   * <b>����˵��</b>
   * 
   * @param backVO
   * @throws BusinessException <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-30 ����10:01:21
   */
  public static void rewriteBackZ2(CtWriteBackForOrderVO[] backVO)
      throws BusinessException {
    if (!SysInitGroupQuery.isCTEnabled()) {
      return;
    }
    IReWriteZ2For21 service =
        NCLocator.getInstance().lookup(IReWriteZ2For21.class);
    service.rewriteBack(backVO);
  }
}
