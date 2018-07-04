/**
 * $�ļ�˵��$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 ����08:57:09
 */
package nc.bs.pu.m21.maintain.rule.save;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.scmpub.reference.uap.bd.accesor.MaterialAccessor;
import nc.itf.scmpub.reference.uap.bd.accesor.PurchaseOrgAccessor;
import nc.itf.scmpub.reference.uap.bd.material.MaterialStockClassPubService;
import nc.itf.scmpub.reference.uap.org.PurchaseOrgPubService;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.bd.accessor.IBDData;
import nc.vo.bd.material.IMaterialEnumConst;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.DirectUtil;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * @description
 *              �ɹ���������������ؼ��
 * @scene
 *        �ɹ����������޸�
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����2:59:03
 * @author luojw
 */
public class TranTypeChkRule implements ICompareRule<OrderVO> {
  /**
   * �ָ���
   */
  private String separator = "|";

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.ICompareRule#process(E[], E[])
   */
  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    this.checkReceivePlan(vos, originVOs);
    this.checkDirect(vos);
    this.checkCenpur(vos);
  }

  /**
   * ���ѡ�����ǣ������Ʋɹ���������ʱ������б��������������¼��
   * ������ϵ����С��ƻ����͡�Ϊ�����������߹����������������ɲɹ�������
   * ���Ϊ�ɹ��������������˳���ȡ�ɹ������������������϶�Ӧ�Ĳɹ���֯��
   * ���ϵ����ж�Ӧ�Ĳɹ���֯��>
   * �ɹ�����������������֯���������ƥ��ɹ�ҵ��ί�й�ϵ��ö�Ӧ�ɹ���֯��>
   * �ɹ�����������������֯ƥ����֯������Ĭ�ϵĲɹ���֯��
   * ��������˳��ƥ��Ľ����Ϊ�գ���òɹ��������Ա��档
   * ����ǿգ����жϻ�ȡ�Ĳɹ���֯�Ƿ���ڲɹ�������ͷ�ɹ���֯�������ȣ���������Ϸ��ϸü�飬
   * ����ö������ܱ��棬����ʾ���������ϲ����ɸòɹ���֯�ɹ��������кš����ϱ��롢��������ʾ����
   * 
   * @param voList
   */
  private void checkCenpur(List<OrderItemVO> voList) {
    this.checkMatType(voList);
    this.checkPuorg(voList);
  }

  private void checkCenpur(MapList<String, OrderItemVO> mapList,
      Map<String, PoTransTypeVO> trantypeMap) {
    List<OrderItemVO> list = new ArrayList<OrderItemVO>();
    for (Entry<String, List<OrderItemVO>> entry : mapList.entrySet()) {
      String ctrantypeid = entry.getKey();
      PoTransTypeVO typevo = trantypeMap.get(ctrantypeid);
      if (typevo != null && UFBoolean.TRUE.equals(typevo.getBcheckcenpur())) {
        list.addAll(entry.getValue());
      }
    }
    if (0 == list.size()) {
      return;
    }
    this.checkCenpur(list);
  }

  /**
   * ���Ʋɹ������Ƿ���ɹ�ҵ��ί�й�ϵ����ѡѡ��Ĭ��Ϊ��
   * ���ѡ�����ǣ������Ʋɹ���������ʱ������б��������������¼��
   * ������ϵ����С��ƻ����͡�Ϊ�����������߹����������������ɲɹ�������
   * ���Ϊ�ɹ��������������˳���ȡ�ɹ������������������϶�Ӧ�Ĳɹ���֯��
   * ���ϵ����ж�Ӧ�Ĳɹ���֯��>
   * �ɹ�����������������֯���������ƥ��ɹ�ҵ��ί�й�ϵ��ö�Ӧ�ɹ���֯��>
   * �ɹ�����������������֯ƥ����֯������Ĭ�ϵĲɹ���֯��
   * ��������˳��ƥ��Ľ����Ϊ�գ���òɹ��������Ա��档
   * ����ǿգ����жϻ�ȡ�Ĳɹ���֯�Ƿ���ڲɹ�������ͷ�ɹ���֯�������ȣ���������Ϸ��ϸü�飬
   * ����ö������ܱ��棬����ʾ���������ϲ����ɸòɹ���֯�ɹ��������кš����ϱ��롢��������ʾ����
   * 
   * @param vos
   */
  private void checkCenpur(OrderVO[] vos) {
    MapList<String, OrderItemVO> mapList = new MapList<String, OrderItemVO>();
    for (OrderVO vo : vos) {
      // ��Դ���빺���Ĳ����
      // ��������֯Ϊ�յĲ����
      for (OrderItemVO itemVO : vo.getBVO()) {
        if (this.needCheckCenpur(itemVO)) {
          mapList.put(vo.getHVO().getCtrantypeid(), itemVO);
        }
      }
    }
    if (0 == mapList.size()) {
      return;
    }
    Map<String, PoTransTypeVO> trantypeMap = this.getTrantypeMap(mapList);
    if (null == trantypeMap || 0 == trantypeMap.size()) {
      return;
    }

    this.checkCenpur(mapList, trantypeMap);
  }

  private void checkDirect(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      OrderItemVO itemVO = vo.getBVO()[0];
      if (StringUtil.isEmptyWithTrim(itemVO.getCsourcetypecode())
          && StringUtil.isEmptyWithTrim(itemVO.getCfirsttypecode())) {
        continue;
      }

      BillHelper<OrderVO> helper = new BillHelper<OrderVO>(vo);
      boolean isdirect = DirectUtil.isDirect(helper, 0);
      if (!isdirect) {
        continue;
      }

      String ctrantypeid = vo.getHVO().getCtrantypeid();
      boolean trantypedirect = this.isDirect(ctrantypeid);
      if (!trantypedirect) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0125")/*
                                                                     * @res
                                                                     * "ֱ�˲ɹ����������������Ͳ���ֱ�ˣ����飡"
                                                                     */);
      }
    }
  }

  /**
   * ������ϵ����С��ƻ����͡�Ϊ�����������߹����������������ɲɹ�����
   * 
   * @param voList
   */
  private void checkMatType(List<OrderItemVO> voList) {
    MapList<String, String> mapList = new MapList<String, String>();
    for (OrderItemVO vo : voList) {
      mapList.put(vo.getPk_reqstoorg(), vo.getPk_material());
    }

    StringBuilder sb = new StringBuilder();
    for (Entry<String, List<String>> entry : mapList.entrySet()) {
      String key = entry.getKey();
      List<String> value = entry.getValue();
      String[] mats = value.toArray(new String[value.size()]);
      this.checkMatTypeByMatOrg(key, mats, sb);
    }
    if (sb.length() > 0) {
      sb.deleteCharAt(sb.length() - 1);
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004030_0", "04004030-0340", null, new String[] {
            sb.toString()
          })/*
             * @res
             * "����Ϊ�����������߹����������������ɲɹ�����"
             */);
    }
  }

  private void checkMatTypeByMatOrg(String key, String[] mats, StringBuilder sb) {
    Map<String, MaterialStockVO> map =
        MaterialStockClassPubService.queryMaterialStockInfoByPks(mats, key,
            new String[] {
              MaterialStockVO.MARTYPE
            });
    if (null == map) {
      return;
    }
    for (String mat : mats) {
      MaterialStockVO matVO = map.get(mat);
      if (null == matVO) {
        continue;
      }
      String mattype = matVO.getMartype();
      if (IMaterialEnumConst.MATERTYPE_DR.equals(mattype)
          || IMaterialEnumConst.MATERTYPE_FR.equals(mattype)) {
        String name = MaterialAccessor.getDocByPk(mat).getName().toString();
        sb.append(name
            + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                "4004030_0", "04004030-0341")/*
                                              * @res
                                              * "��"
                                              */);
      }
    }
  }

  /**
   * ���Ϊ�ɹ��������������˳���ȡ�ɹ������������������϶�Ӧ�Ĳɹ���֯��
   * ���ϵ����ж�Ӧ�Ĳɹ���֯��>
   * �ɹ�����������������֯���������ƥ��ɹ�ҵ��ί�й�ϵ��ö�Ӧ�ɹ���֯��>
   * �ɹ�����������������֯ƥ����֯������Ĭ�ϵĲɹ���֯��
   * ��������˳��ƥ��Ľ����Ϊ�գ���òɹ��������Ա��档
   * ����ǿգ����жϻ�ȡ�Ĳɹ���֯�Ƿ���ڲɹ�������ͷ�ɹ���֯�������ȣ���������Ϸ��ϸü�飬
   * ����ö������ܱ��棬����ʾ���������ϲ����ɸòɹ���֯�ɹ��������кš����ϱ��롢��������ʾ����
   * 
   * @param voList
   */
  private void checkPuorg(List<OrderItemVO> voList) {
    List<String> matList = new ArrayList<String>();
    List<String> orgList = new ArrayList<String>();
    for (OrderItemVO vo : voList) {
      matList.add(vo.getPk_material());
      orgList.add(vo.getPk_reqstoorg());
    }
    String[] mats = matList.toArray(new String[matList.size()]);
    String[] orgs = orgList.toArray(new String[orgList.size()]);
    Map<String, String> puorgMap =
        PurchaseOrgPubService.queryMaterialStockInfoByPk(orgs, mats);
    if (null == puorgMap) {
      return;
    }

    MapList<String, OrderItemVO> mapList = new MapList<String, OrderItemVO>();
    for (OrderItemVO itemVO : voList) {
      mapList.put(itemVO.getPk_org(), itemVO);
    }
    StringBuilder sb = new StringBuilder();
    for (Entry<String, List<OrderItemVO>> entry : mapList.entrySet()) {
      String error =
          this.checkPuorg(entry.getKey(), entry.getValue(), puorgMap);
      if (error != null) {
        sb.append(error);
      }
    }
    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

  private String checkPuorg(String pk_org, List<OrderItemVO> list,
      Map<String, String> puorgMap) {
    StringBuilder sb = new StringBuilder();
    for (OrderItemVO itemVO : list) {
      String pk_material = itemVO.getPk_material();
      String pk_reqstoorg = itemVO.getPk_reqstoorg();
      String pk_puorg =
          puorgMap.get(pk_reqstoorg + this.separator + pk_material);
      if (!StringUtil.isEmptyWithTrim(pk_puorg) && !pk_puorg.equals(pk_org)) {
        IBDData data = MaterialAccessor.getDocByPk(pk_material);
        sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
            "04004030-0342", null, new String[] {
              itemVO.getCrowno(), data.getCode(), data.getName().toString()
            })/*
               * @res
               * "��{0}�С�{1}��{2}\n"
               */);

      }
    }

    if (sb.length() > 0) {
      IBDData orgData = PurchaseOrgAccessor.getDocByPk(pk_org);
      String orgname = orgData.getName().toString();
      sb.insert(
          0,
          NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
              "04004030-0343", null, new String[] {
                orgname
              })/*
                 * @res
                 * "�������ϲ����ɸòɹ���֯{0}�ɹ���\n"
                 */);

      return sb.toString();
    }
    return null;
  }

  private void checkReceivePlan(OrderVO[] vos, OrderVO[] originVOs) {
    Set<String> haveReceivePlanSet = this.getHaveReceivePlanSet(vos);
    if (null == haveReceivePlanSet) {
      return;
    }

    Map<String, OrderVO> originVOMap = AggVOUtil.createVOMap(originVOs);
    StringBuilder sb = new StringBuilder();

    for (OrderVO vo : vos) {
      String pk_order = vo.getHVO().getPk_order();
      OrderVO originVO = originVOMap.get(pk_order);
      String trantype = vo.getHVO().getVtrantypecode();
      String orginTrantype = originVO.getHVO().getVtrantypecode();
      if (ObjectUtils.equals(trantype, orginTrantype)) {
        continue;
      }

      if (haveReceivePlanSet.contains(pk_order)) {
        sb.append(vo.getHVO().getVbillcode() + ", ");
      }
    }

    if (sb.length() > 0) {
      sb.deleteCharAt(sb.length() - 1);
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0241", null, new String[] {
            sb.toString()
          })/* ����{0}���е����ƻ����������޸Ķ������ͣ�\n */);
    }
  }

  /**
   * ���������������е����ƻ��Ķ���ID
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVOs
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-30 ����06:07:05
   */
  private Set<String> getHaveReceivePlanSet(OrderVO[] orderVOs) {
    String[] orderIds = new String[orderVOs.length];
    for (int i = 0; i < orderIds.length; ++i) {
      orderIds[i] = orderVOs[i].getHVO().getPk_order();
    }

    VOQuery<OrderReceivePlanVO> query =
        new VOQuery<OrderReceivePlanVO>(OrderReceivePlanVO.class, new String[] {
          OrderReceivePlanVO.PK_ORDER
        });
    SqlBuilder sql = new SqlBuilder();
    // ��ʱ��
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_30.name());
    sql.append(builder.buildSQL(OrderReceivePlanVO.PK_ORDER, orderIds));
    OrderReceivePlanVO[] rpVOs = query.query(" and " + sql.toString(), null);

    if (ArrayUtils.isEmpty(rpVOs)) {
      return null;
    }

    Set<String> haveRPIdSet = new HashSet<String>();
    for (OrderReceivePlanVO rpVO : rpVOs) {
      haveRPIdSet.add(rpVO.getPk_order());
    }
    return haveRPIdSet;
  }

  private Map<String, PoTransTypeVO> getTrantypeMap(
      MapList<String, OrderItemVO> mapList) {
    Set<String> ctrantypeidSet = mapList.keySet();
    String[] ctrantypeids =
        ctrantypeidSet.toArray(new String[ctrantypeidSet.size()]);
    IPoTransTypeQuery service =
        NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
    try {
      return service.queryAttrByIDs(ctrantypeids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private boolean isDirect(String ctrantypeid) {
    IPoTransTypeQuery service =
        NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
    try {
      Map<String, PoTransTypeVO> map = service.queryAttrByIDs(new String[] {
        ctrantypeid
      });
      if (null == map || null == map.get(ctrantypeid)) {
        return false;
      }
      return UFBoolean.TRUE.equals(map.get(ctrantypeid).getBdirect());
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * �Ƿ���Դ���빺��
   * �Ƿ���������֯Ϊ��
   * 
   * @param vo ����VO
   * @return �����Դ���빺������false�����򷵻�true
   */
  private boolean needCheckCenpur(OrderItemVO itemVO) {
    String csourcetypecode = itemVO.getCsourcetypecode();
    if (POBillType.PrayBill.getCode().equals(csourcetypecode)) {
      return false;
    }
    if (StringUtil.isEmptyWithTrim(itemVO.getPk_reqstoorg())) {
      return false;
    }
    return true;
  }

}
