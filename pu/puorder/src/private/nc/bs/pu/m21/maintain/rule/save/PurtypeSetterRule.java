package nc.bs.pu.m21.maintain.rule.save;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ct.Z2CTServices;
import nc.pubitf.scmbd.vrm.vm.IVendorMaterialQuery;
import nc.pubitf.scmf.pu.cenpurule.pu.ICenPuRuleQueryForPu;
import nc.vo.ct.purdaily.entity.CtPuVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmf.pu.cenpurule.entity.CenPuRuleItemVO;
import nc.vo.scmf.pu.cenpurule.entity.ControlType;

/**
 * @description
 *              设置订单表体的采购类型、保存前规则
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午2:48:32
 * @author luojw
 */
public class PurtypeSetterRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    Map<String, CenPuRuleItemVO> cenpuMap = this.getCenpuRuleMap(vos);
    if (null == cenpuMap || cenpuMap.size() == 0) {
      for (OrderVO vo : vos) {
        for (OrderItemVO itemVO : vo.getBVO()) {
          itemVO.setFneedpurtype(null);
          itemVO.setFactpurtype(null);
          itemVO.setPk_cenpurule_b(null);
        }
      }
      return;
    }

    this.setPurtype(vos, cenpuMap);
  }

  /**
   * 根据采购订单表体VO的合同信息过滤采购订单表体VO列表
   * 若表体合同为空，则设置实执采购类型为空值；否则将订单表体VO加入待处理集合中返回。
   * 
   * @param ctList 待处理采购订单表体VO。
   * @return key-合同ID，value-list中存采购订单表体VO
   */
  private MapList<String, OrderItemVO> filterOrderItemVOByCt(
      List<OrderItemVO> ctList) {
    MapList<String, OrderItemVO> maplist = new MapList<String, OrderItemVO>();
    for (OrderItemVO itemVO : ctList) {
      String ccontractid = itemVO.getCcontractid();
      if (null == ccontractid) {
        itemVO.setFactpurtype(null);
      }
      else {
        maplist.put(ccontractid, itemVO);
      }
    }
    return maplist;
  }

  /**
   * 获取集采控制规则集合
   * 根据采购组织、物料OID、需求库存组织按照明细优先原则查询集采控制规则
   * 
   * @param vos 采购订单数组
   * @return
   */
  private Map<String, CenPuRuleItemVO> getCenpuRuleMap(OrderVO[] vos) {
    Set<String> puorgSet = new HashSet<String>();
    List<String> matList = new ArrayList<String>();
    List<String> stockorgList = new ArrayList<String>();
    for (OrderVO vo : vos) {
      String pk_org = vo.getHVO().getPk_org();
      for (OrderItemVO itemVO : vo.getBVO()) {
        String pk_reqstoorg = itemVO.getPk_reqstoorg();
        // 子表需求库存组织为空不处理
        if (null == pk_reqstoorg) {
          continue;
        }
        stockorgList.add(pk_reqstoorg);
        puorgSet.add(pk_org);
        matList.add(itemVO.getPk_srcmaterial());
      }
    }
    // 所有采购订单需求库存组织为空直接返回
    if (stockorgList.size() == 0) {
      return null;
    }

    String[] puorgs = puorgSet.toArray(new String[puorgSet.size()]);
    String[] mats = matList.toArray(new String[matList.size()]);
    String[] stockorgs = stockorgList.toArray(new String[stockorgList.size()]);
    ICenPuRuleQueryForPu service =
        NCLocator.getInstance().lookup(ICenPuRuleQueryForPu.class);
    try {
      return service.queryCenpuruleItem(puorgs, mats, stockorgs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * 获取供应商物料关系
   * 
   * @param vrmList 应执采购类型是供应商名录的采购订单表体VO列表
   * @param cenpuMap 集采控制规则明细集合。key-集采控制规则明细主键，value-集采控制规则明细VO
   * @return 供应商物料关系集合，value-采购组织、物料、供应商
   */
  private Set<String> getvrmset(List<OrderItemVO> vrmList,
      Map<String, CenPuRuleItemVO> cenpuMap) {
    // 供应商
    Set<String> supset = new HashSet<String>();
    // 采购组织
    Set<String> orgset = new HashSet<String>();
    // 物料
    Set<String> matset = new HashSet<String>();
    for (OrderItemVO itemVO : vrmList) {
      supset.add(itemVO.getPk_supplier());
      CenPuRuleItemVO ruleitemvo = cenpuMap.get(itemVO.getPk_cenpurule_b());
      if (ruleitemvo != null) {
        orgset.add(ruleitemvo.getPk_org());
      }
      matset.add(itemVO.getPk_srcmaterial());
    }
    String[] sups = supset.toArray(new String[supset.size()]);
    String[] orgs = orgset.toArray(new String[orgset.size()]);
    String[] mats = matset.toArray(new String[matset.size()]);
    // 根据供应商id查询供应商物料关系。若关系为空，直接返回
    MapList<String, String> vrmMap = null;
    IVendorMaterialQuery service =
        NCLocator.getInstance().lookup(IVendorMaterialQuery.class);
    try {
      vrmMap = service.queryByVendorMaterialOrgIds(sups, mats, orgs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (vrmMap == null || vrmMap.toMap().isEmpty()) {
      return null;
    }
    Set<String> set = new HashSet<String>();
    for (Entry<String, List<String>> en : vrmMap.entrySet()) {
      for (String pk_supplier : en.getValue()) {
        set.add(en.getKey() + pk_supplier);
      }
    }
    return set;
  }

  /**
   * 设置应执采购类型是集采合同的采购订单表体的实执采购类型
   * 若合同的采购组织和集采控制规则对应的采购组织相等，并且集采控制规则的合同类型为空或与合同的交易类型相等，
   * 则设置实执采购类型为集采合同；否则为空。
   * 
   * @param ctList 应执采购类型是集采合同的采购订单表体VO列表
   * @param ruleitemmap 集采控制规则明细集合。key-集采控制规则明细主键，value-集采控制规则明细VO
   */
  private void setCtActtype(List<OrderItemVO> ctList,
      Map<String, CenPuRuleItemVO> ruleitemmap) {
    if (ctList.size() == 0) {
      return;
    }
    // 获取根据采购订单表体VO的合同信息过滤采购订单表体VO列表
    MapList<String, OrderItemVO> maplist = this.filterOrderItemVOByCt(ctList);
    if (maplist.size() == 0) {
      return;
    }

    // 获取采购合同列表
    String[] ccontractids =
        maplist.keySet().toArray(new String[maplist.keySet().size()]);
    Map<String, CtPuVO> ctmap = Z2CTServices.queryOrgTypeByPks(ccontractids);

    for (Entry<String, List<OrderItemVO>> entry : maplist.entrySet()) {
      // 合同ID
      String key = entry.getKey();
      List<OrderItemVO> valuelist = entry.getValue();
      // 合同VO
      CtPuVO ctpuvo = ctmap.get(key);
      // 合同中的采购组织
      String pk_org = ctpuvo.getPk_org();
      // 合同中的交易类型
      String ctrantypeid = ctpuvo.getCtrantypeid();
      for (OrderItemVO orderitemvo : valuelist) {
        CenPuRuleItemVO ruleitemvo =
            ruleitemmap.get(orderitemvo.getPk_cenpurule_b());
        // 集采控制规则中的采购组织
        String rulepkorg = ruleitemvo.getPk_org();
        // 集采控制规则中的合同类型
        String ruletrantypeid = ruleitemvo.getCtrantypeid();
        if (pk_org.equals(rulepkorg)
            && (ruletrantypeid == null || ctrantypeid.equals(ruletrantypeid))) {

          orderitemvo.setFactpurtype(ControlType.CONTRACT.toInteger());
        }
        else {
          orderitemvo.setFactpurtype(null);
        }
      }

    }
  }

  /**
   * 设置采购订单表体VO的采购类型
   * 
   * @param vos
   * @param cenpuMap
   */
  private void setPurtype(OrderVO[] vos, Map<String, CenPuRuleItemVO> cenpuMap) {
    // 采购订单明细列表-应执采购类型-集采合同
    List<OrderItemVO> ctList = new ArrayList<OrderItemVO>();
    // 采购订单明细列表-应执采购类型-供应商名录
    List<OrderItemVO> vrmList = new ArrayList<OrderItemVO>();
    for (OrderVO vo : vos) {
      String pk_org = vo.getHVO().getPk_org();
      for (OrderItemVO itemVO : vo.getBVO()) {
        String pk_reqstoorg = itemVO.getPk_reqstoorg();
        // 子表需求库存组织为空不处理
        if (null == pk_reqstoorg) {
          continue;
        }
        String pk_srcmaterial = itemVO.getPk_srcmaterial();
        // 取当前采购子表记录对应的集采控制规则。若没有对应规则，不处理
        CenPuRuleItemVO cenpuvo =
            cenpuMap.get(pk_org + pk_srcmaterial + pk_reqstoorg);
        if (null == cenpuvo) {
          itemVO.setFneedpurtype(null);
          itemVO.setFactpurtype(null);
          itemVO.setPk_cenpurule_b(null);
          continue;
        }
        Integer fctrltype = cenpuvo.getFctrltype();
        // 设置应执采购类型
        itemVO.setFneedpurtype(fctrltype);
        // 采购订单子表记录对应的集采控制规则主键
        itemVO.setPk_cenpurule_b(cenpuvo.getPk_cenpurule_b());

        if (ControlType.CONTRACT.toInt() == fctrltype.intValue()) {
          ctList.add(itemVO);
        }
        else {
          vrmList.add(itemVO);
        }
      }
    }

    // 构造集采控制规则明细集合。key-集采控制规则明细主键，value-集采控制规则明细VO
    CenPuRuleItemVO[] ruleitemvos =
        cenpuMap.values()
            .toArray(new CenPuRuleItemVO[cenpuMap.values().size()]);
    Map<String, CenPuRuleItemVO> ruleitemmap =
        new HashMap<String, CenPuRuleItemVO>();
    for (CenPuRuleItemVO ruleitemvo : ruleitemvos) {
      ruleitemmap.put(ruleitemvo.getPk_cenpurule_b(), ruleitemvo);
    }
    this.setCtActtype(ctList, ruleitemmap);
    this.setVrmActtype(vrmList, ruleitemmap);
  }

  /**
   * 设置应执采购类型是供应商名录的采购订单表体的实执采购类型
   * 如果采购订单表体VO和供应商物料关系相匹配，则设置实执采购类型为供应商；否则，设置为空。
   * 
   * @param vrmList 应执采购类型是供应商名录的采购订单表体VO列表
   * @param cenpuMap 集采控制规则明细集合。key-集采控制规则明细主键，value-集采控制规则明细VO
   */
  private void setVrmActtype(List<OrderItemVO> vrmList,
      Map<String, CenPuRuleItemVO> cenpuMap) {
    if (vrmList.size() == 0) {
      return;
    }
    Set<String> set = this.getvrmset(vrmList, cenpuMap);
    if (null == set || set.size() == 0) {
      for (OrderItemVO itemVO : vrmList) {
        itemVO.setFactpurtype(null);
      }
      return;
    }
    // 迭代采购订单表体VO。如果和供应商物料关系相匹配，则设置实执采购类型为供应商；否则，设置为空
    for (OrderItemVO itemVO : vrmList) {
      CenPuRuleItemVO ruleitemvo = cenpuMap.get(itemVO.getPk_cenpurule_b());
      String pk_org = ruleitemvo != null ? ruleitemvo.getPk_org() : null;
      String pk_srcmaterial = itemVO.getPk_srcmaterial();
      String pk_supplier = itemVO.getPk_supplier();
      if (set.contains(pk_srcmaterial + pk_org + pk_supplier)) {
        itemVO.setFactpurtype(ControlType.VENDORLIST.toInteger());
      }
      else {
        itemVO.setFactpurtype(null);
      }
    }
  }

}
