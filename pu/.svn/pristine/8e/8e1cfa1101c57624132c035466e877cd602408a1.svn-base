/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 上午08:57:09
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
 *              采购订单交易类型相关检查
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午2:59:03
 * @author luojw
 */
public class TranTypeChkRule implements ICompareRule<OrderVO> {
  /**
   * 分隔符
   */
  private String separator = "|";

  /**
   * 父类方法重写
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
   * 如果选择了是，则自制采购订单保存时针对所有表体行物料作以下检查
   * 如果物料档案中“计划类型”为分销补货或者工厂补货，则不能生成采购订单；
   * 如果为采购件，则根据以下顺序获取采购订单表体所有行物料对应的采购组织：
   * 物料档案中对应的采购组织－>
   * 采购订单表体需求库存组织＋存货分类匹配采购业务委托关系获得对应采购组织－>
   * 采购订单表体需求库存组织匹配组织档案中默认的采购组织。
   * 按照以上顺序匹配的结果仍为空，则该采购订单可以保存。
   * 如果非空，则判断获取的采购组织是否等于采购订单表头采购组织，如果相等，则该行物料符合该检查，
   * 否则该订单不能保存，并提示“以下物料不能由该采购组织采购：单据行号、物料编码、名称来显示”。
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
   * 自制采购订单是否检查采购业务委托关系：勾选选择，默认为否。
   * 如果选择了是，则自制采购订单保存时针对所有表体行物料作以下检查
   * 如果物料档案中“计划类型”为分销补货或者工厂补货，则不能生成采购订单；
   * 如果为采购件，则根据以下顺序获取采购订单表体所有行物料对应的采购组织：
   * 物料档案中对应的采购组织－>
   * 采购订单表体需求库存组织＋存货分类匹配采购业务委托关系获得对应采购组织－>
   * 采购订单表体需求库存组织匹配组织档案中默认的采购组织。
   * 按照以上顺序匹配的结果仍为空，则该采购订单可以保存。
   * 如果非空，则判断获取的采购组织是否等于采购订单表头采购组织，如果相等，则该行物料符合该检查，
   * 否则该订单不能保存，并提示“以下物料不能由该采购组织采购：单据行号、物料编码、名称来显示”。
   * 
   * @param vos
   */
  private void checkCenpur(OrderVO[] vos) {
    MapList<String, OrderItemVO> mapList = new MapList<String, OrderItemVO>();
    for (OrderVO vo : vos) {
      // 来源于请购单的不检查
      // 需求库存组织为空的不检查
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
                                                                     * "直运采购订单，但交易类型不是直运，请检查！"
                                                                     */);
      }
    }
  }

  /**
   * 如果物料档案中“计划类型”为分销补货或者工厂补货，则不能生成采购订单
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
             * "物料为分销补货或者工厂补货，不能生成采购订单"
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
                                              * "、"
                                              */);
      }
    }
  }

  /**
   * 如果为采购件，则根据以下顺序获取采购订单表体所有行物料对应的采购组织：
   * 物料档案中对应的采购组织－>
   * 采购订单表体需求库存组织＋存货分类匹配采购业务委托关系获得对应采购组织－>
   * 采购订单表体需求库存组织匹配组织档案中默认的采购组织。
   * 按照以上顺序匹配的结果仍为空，则该采购订单可以保存。
   * 如果非空，则判断获取的采购组织是否等于采购订单表头采购组织，如果相等，则该行物料符合该检查，
   * 否则该订单不能保存，并提示“以下物料不能由该采购组织采购：单据行号、物料编码、名称来显示”。
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
               * "第{0}行、{1}、{2}\n"
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
                 * "以下物料不能由该采购组织{0}采购：\n"
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
          })/* 订单{0}已有到货计划，不允许修改订单类型！\n */);
    }
  }

  /**
   * 方法功能描述：有到货计划的订单ID
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-30 下午06:07:05
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
    // 临时表
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
   * 是否来源于请购单
   * 是否需求库存组织为空
   * 
   * @param vo 订单VO
   * @return 如果来源于请购单返回false，否则返回true
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
