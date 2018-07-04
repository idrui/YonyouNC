package nc.vo.pu.m21.vochange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bd.userdef.UserDefCheckUtils;
import nc.itf.pu.m21.IOrderSupplierQuery;
import nc.itf.scmpub.reference.uap.bd.psn.PsndocPubService;
import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.itf.scmpub.reference.uap.org.DeptPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.org.DeptVO;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.DirectUtil;
import nc.vo.pu.m21.query.supplier.SupplierInfo;
import nc.vo.pu.m21.rule.CurrencyAndExchangerate;
import nc.vo.pu.m21.rule.FlowStockOrgValue;
import nc.vo.pu.m21.rule.OrganizationDefaultValue;
import nc.vo.pu.m21.rule.PriceQuoter;
import nc.vo.pu.m21.rule.RelationCalculate;
import nc.vo.pu.m21.rule.ReqCorpDefaultValue;
import nc.vo.pu.m21.rule.SupplierDefaultValue;
import nc.vo.pu.m21.rule.vat.OrderVatInfoSrcFillRule;
import nc.vo.pu.pub.enumeration.PriceParam;
import nc.vo.pu.pub.rule.WeightVolumePieceCalc;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SOBillType;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @since 6.0
 * @version 2011-6-22 上午10:27:48
 * @author wuxla
 */

public abstract class AbstractOrderChangeVOAdjust implements IChangeVOAdjust {

  @Override
  public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
      AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    if (null == destVO) {
      return null;
    }
    // 调用批处理方法
    return this.batchAdjustAfterChange(new AggregatedValueObject[] {
      srcVO
    }, new AggregatedValueObject[] {
      destVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    if (null == srcVO) {
      return null;
    }
    // 调用批处理方法
    return this.batchAdjustBeforeChange(new AggregatedValueObject[] {
      srcVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    OrderVO[] vos = ArrayUtil.convertArrayType(destVOs);
    this.setBusiDate(vos);
    // 补全订单来源单据主数量，用于最后一次拉单计算
    this.fillOrderSourceNum(srcVOs, vos);
    // 直运标志补全
    this.setDirectValue(vos);
    // 自定义项检查，上下游不一致
    this.checkUserDef(vos);

    // 补全体积件数等信息
    this.calWeightVolumePiece(vos);

    vos = this.fillInformation(vos, srcVOs);
    // vat信息补全
    this.fillVatInfo(vos);
    // 做数量的尾差处理（倒挤）
    this.processMargin(vos, srcVOs);
    // 处理业务流程字段
    this.fillBusiTypeInfo(vos);
    // 转单或推单打开界面时是否进行自动询价
    this.setOrderPrice(vos);
    return vos;
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    return srcVOs;
  }

  private void checkDeptVisible(MapList<String, OrderHeaderVO> deptMapList) {
    if (0 == deptMapList.size()) {
      return;
    }
    Set<String> deptSet = deptMapList.keySet();
    String[] depts = deptSet.toArray(new String[deptSet.size()]);
    DeptVO[] deptVOs = DeptPubService.queryDeptVOsByPKS(depts);
    if (ArrayUtils.isEmpty(deptVOs)) {
      return;
    }
    for (DeptVO deptVO : deptVOs) {
      String pk_dept = deptVO.getPk_dept();
      String pk_org = deptVO.getPk_org();
      List<OrderHeaderVO> list = deptMapList.get(pk_dept);
      for (OrderHeaderVO headVO : list) {
        if (!StringUtils.equals(headVO.getPk_org(), pk_org)) {
          headVO.setPk_dept(null);
          headVO.setPk_dept_v(null);
        }
      }
    }
  }

  private void checkPsnVisible(MapList<String, OrderHeaderVO> psnMapList) {
    if (psnMapList.size() == 0) {
      return;
    }
    Set<String> psnSet = psnMapList.keySet();
    String[] psns = psnSet.toArray(new String[psnSet.size()]);
    Map<String, List<String>> orgs = PsndocPubService.queryPsndocByPks(psns);
    if (orgs.size() == 0) {
      return;
    }
    for (String psn : psns) {
      List<OrderHeaderVO> headerVO = psnMapList.get(psn);
      List<String> org = orgs.get(psn);
      for (OrderHeaderVO vo : headerVO) {
        if (!org.contains(vo.getPk_org())) {
          vo.setCemployeeid(null);
        }
      }
    }
  }

  private void checkUserDef(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    UserDefCheckUtils.check(vos, new Class[] {
      OrderHeaderVO.class, OrderItemVO.class
    });
  }

  private MapList<String, OrderVO> getOrderMapList(OrderVO[] vos) {
    MapList<String, OrderVO> mapList = new MapList<String, OrderVO>();
    for (OrderVO vo : vos) {
      String cemployeeid = vo.getHVO().getCemployeeid();
      String pk_dept_v = vo.getHVO().getPk_dept_v();
      if (!StringUtils.isEmpty(cemployeeid) && StringUtils.isEmpty(pk_dept_v)) {
        mapList.put(cemployeeid, vo);
      }
    }
    return mapList;
  }

  /**
   * 获取来源或源头请购单id
   * 
   * @param orderVOs
   * @return
   */
  private String[] getPraybillHids(OrderVO[] orderVOs) {
    Set<String> praybillHids = new HashSet<String>();
    for (OrderVO vo : orderVOs) {
      for (OrderItemVO item : vo.getBVO()) {
        //
        if (POBillType.PrayBill.getCode().equals(item.getCsourcetypecode())) {
          praybillHids.add(item.getCsourceid());
        }
        else if (POBillType.PrayBill.getCode().equals(item.getCfirsttypecode())) {
          praybillHids.add(item.getCfirstid());
        }
      }
    }
    if (praybillHids.size() > 0) {
      return praybillHids.toArray(new String[praybillHids.size()]);
    }
    return null;
  }

  private MapList<String, String> getPsnDeptMap(
      MapList<String, OrderVO> mapList, String pk_org) {
    Set<String> keySet = mapList.keySet();
    String[] cemployeeids = keySet.toArray(new String[keySet.size()]);
    MapList<String, String> psndeptMapList =
        PsndocPubService.queryDeptIDByPsndocIDs(cemployeeids, pk_org);
    return psndeptMapList;
  }

  /**
   * 获取来源或源头的销售订单交易类型
   * 
   * @param orderVOs
   * @return
   */
  private String[] getSoTrantyeIds(OrderVO[] orderVOs) {
    Set<String> soTrantypeIds = new HashSet<String>();
    for (OrderVO vo : orderVOs) {
      for (OrderItemVO item : vo.getBVO()) {
        if (SOBillType.Order.getCode().equals(item.getCsourcetypecode())) {
          soTrantypeIds.add(item.getVsourcetrantype());
        }
        else if (SOBillType.Order.getCode().equals(item.getCfirsttypecode())) {
          soTrantypeIds.add(item.getVfirsttrantype());
        }
      }
    }
    if (soTrantypeIds.size() > 0) {
      return soTrantypeIds.toArray(new String[soTrantypeIds.size()]);
    }
    return null;
  }

  private Map<String, UFBoolean> getVisibleMap(String[] depts, String pk_org) {
    Map<String, UFBoolean> visibleMap = new HashMap<String, UFBoolean>();
    DeptVO[] deptVOs = DeptPubService.queryDeptVOsByPKS(depts);
    for (DeptVO vo : deptVOs) {
      if (StringUtils.equals(vo.getPk_org(), pk_org)) {
        visibleMap.put(vo.getPk_dept(), UFBoolean.TRUE);
      }
      else {
        visibleMap.put(vo.getPk_dept(), UFBoolean.FALSE);
      }
    }
    return visibleMap;
  }

  /**
   * 判断采购订单自动询价条件列表中是否有值，有值VO交换后询价
   * 
   * @param pss
   * @param paramKey
   * @return
   */
  private boolean isExistParam(OrderVO[] vos) {
    String pk_org =
        (String) vos[0].getParent().getAttributeValue(OrderHeaderVO.PK_ORG);
    PriceParam[] pss = PUSysParamUtil.getPO16(pk_org);
    if (pss == null || pss.length < 1) {
      return false;
    }
    return true;
  }

  private void setBusiDate(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    UFDate date = AppContext.getInstance().getBusiDate();
    for (OrderVO vo : vos) {
      vo.getHVO().setDbilldate(date);
    }
  }

  /**
   * 根据请购单设置直运标志
   * 
   * @param praybillhids
   * @param orderVOs
   */
  private void setDirectValueByPraybill(String[] praybillhids,
      OrderVO[] orderVOs) {
    Map<String, UFBoolean> result =
        DirectUtil.queryPrayDirectAttrs(praybillhids);
    if (MapUtils.isEmpty(result)) {
      return;
    }
    for (OrderVO vo : orderVOs) {
      for (OrderItemVO item : vo.getBVO()) {
        if (result.get(item.getCsourceid()) != null) {
          vo.getHVO().setBdirect(result.get(item.getCsourceid()));
          break;
        }
        if (result.get(item.getCfirstid()) != null) {
          vo.getHVO().setBdirect(result.get(item.getCfirstid()));
          break;
        }
      }
    }
  }

  /**
   * 根据销售订单交易类型设置直运标志
   * 
   * @param soTrantypeids
   * @param orderVOs
   */
  private void setDirectValueBySOOrder(String[] soTrantypeids,
      OrderVO[] orderVOs) {
    Map<String, UFBoolean> result =
        DirectUtil.querySOTrantypeDirectAttrs(soTrantypeids);
    if (MapUtils.isEmpty(result)) {
      return;
    }
    for (OrderVO vo : orderVOs) {
      for (OrderItemVO item : vo.getBVO()) {
        if (result.get(item.getVsourcetrantype()) != null) {
          vo.getHVO().setBdirect(result.get(item.getVsourcetrantype()));
          break;
        }
        if (result.get(item.getVfirsttrantype()) != null) {
          vo.getHVO().setBdirect(result.get(item.getVfirsttrantype()));
          break;
        }
      }
    }
  }

  /**
   * 设置组织币种
   * <p>
   * 使用场景：
   * <ul>
   * <li>如果币种无值，设置组织本币
   * </ul>
   */
  private void setOrgCurrency(BillHelper<OrderVO> helper) {
    String coirgcurrencyid =
        (String) helper.getHeadValue(OrderHeaderVO.CORIGCURRENCYID);
    if (StringUtils.isEmpty(coirgcurrencyid)) {
      String pk_org = (String) helper.getHeadValue(OrderHeaderVO.PK_ORG);
      coirgcurrencyid = OrgUnitPubService.queryOrgCurrByPk(pk_org);
      helper.setHeadValue(OrderHeaderVO.CORIGCURRENCYID, coirgcurrencyid);
    }
  }

  /**
   * 设置价格
   * 
   * @param vos
   */
  private void setPrice(OrderVO[] vos) {
    RelationCalculate cal = new RelationCalculate();
    for (OrderVO vo : vos) {
      PriceQuoter priceQuoter = new PriceQuoter(new BillHelper<OrderVO>(vo));
      Integer[] rows = new Integer[vo.getBVO().length];
      for (int i = 0; i < rows.length; i++) {
        rows[i] = Integer.valueOf(i);
      }
      Map<Integer, String> changeRow = priceQuoter.setDefaultPrice(rows);
      OrderVO calVo = new OrderVO();
      for (Entry<Integer, String> entry : changeRow.entrySet()) {
        calVo.setHVO(vo.getHVO());
        calVo.setBVO(new OrderItemVO[] {
          vo.getBVO()[entry.getKey().intValue()]
        });
        cal.calculate(calVo, entry.getValue());
      }
    }
  }

  protected void calWeightVolumePiece(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    IKeyValue[] keyValues = BillHelper.declareArray(vos);
    WeightVolumePieceCalc weightVolumePieceCal =
        new WeightVolumePieceCalc(keyValues);
    for (int i = 0; i < vos.length; ++i) {
      int length = vos[i].getBVO().length;
      int[] rows = new int[length];
      for (int j = 0; j < length; ++j) {
        rows[j] = j;
      }
      weightVolumePieceCal.calc(keyValues[i], rows);
    }
  }

  protected void checkVisible(OrderVO[] vos) {
    MapList<String, OrderHeaderVO> psnMapList =
        new MapList<String, OrderHeaderVO>();
    MapList<String, OrderHeaderVO> deptMapList =
        new MapList<String, OrderHeaderVO>();
    for (OrderVO vo : vos) {
      OrderHeaderVO headVO = vo.getHVO();
      String pk_psn = headVO.getCemployeeid();
      if (pk_psn != null) {
        psnMapList.put(pk_psn, headVO);
      }
      String pk_dept = headVO.getPk_dept();
      if (pk_dept != null) {
        deptMapList.put(pk_dept, headVO);
      }
    }
    this.checkDeptVisible(deptMapList);
    this.checkPsnVisible(psnMapList);
  }

  /**
   * mengjian VO交换后如果相应业务流程字段为空，那么在业务流程中配置的单据函数也将失效（即不走配置的单据函数了）
   * 
   * @param vos
   */
  protected void fillBusiTypeInfo(OrderVO[] vos) {
    if (null == vos || 0 == vos.length) {
      return;
    }

    List<OrderVO> list = new ArrayList<OrderVO>();
    for (OrderVO orderVO : vos) {
      if (orderVO.getHVO().getVtrantypecode() != null) {
        list.add(orderVO);
      }
    }
    if (list.size() > 0) {
      // mengjian VO交换后如果相应业务流程字段为空，那么在业务流程中配置的单据函数也将失效（即不走配置的单据函数了）
      PfServiceScmUtil.setBusiTypeNoExcep(
          list.toArray(new OrderVO[list.size()]), POBillType.Order.getCode());
    }
  }

  protected void fillInformation(BillHelper<OrderVO> helper, int[] rows) {
    this.fillOrgSuppValue(helper, rows);
  }

  protected OrderVO[] fillInformation(OrderVO[] vos,
      AggregatedValueObject[] srcVOs) {
    if (ArrayUtils.isEmpty(vos) || ArrayUtils.isEmpty(srcVOs)) {
      return null;
    }
    RelationCalculate cal = new RelationCalculate();
    for (OrderVO vo : vos) {
      BillHelper<OrderVO> helper = new BillHelper<OrderVO>(vo);
      OrderItemVO[] items = vo.getBVO();
      int[] rows = new int[items.length];
      for (int i = 0; i < items.length; i++) {
        rows[i] = i;
      }

      this.fillInformation(helper, rows);
      this.relationCalculate(cal, vo);
    }
    this.fillOtherInfo(vos, srcVOs);
    this.checkVisible(vos);
    return vos;
  }

  /**
   * 根据上游，补全订单表体“来源单据主数量”计算属性字段，用于后续的联动计算。
   * <p>
   * 使用场景：
   * <ul>
   * <li>vo交换后处理类，补全“来源单据主数量”
   * </ul>
   * 
   * @param srcVOs 来源单据vo数组
   * @param vos 订单vo数组
   */
  protected void fillOrderSourceNum(AggregatedValueObject[] srcVOs,
      OrderVO[] vos) {
    Map<String, CircularlyAccessibleValueObject> srcItemMap =
        AggVOUtil.createItemMap(srcVOs);
    String[] numStrs = this.getNumStrs();
    for (OrderVO vo : vos) {
      for (OrderItemVO item : vo.getBVO()) {
        // key:订单表体的来源单据行id
        String key = item.getCsourcebid();
        if (srcItemMap.containsKey(key)) {
          // 来源单据主数量，来源单据主数量字段默认与订单主数量一致
          item.setNsourcenum((UFDouble) srcItemMap.get(key)
              .getAttributeValue(numStrs[0]));
          // sw 20150812 主数量与来源相等，认为是一次拉单，辅数量、报价数量等于来源,避免尾差
          if (item.getNnum()!= null && item.getNsourcenum() != null 
              && item.getNnum().compareTo(item.getNsourcenum()) == 0) {
            item.setNastnum((UFDouble) srcItemMap.get(key)
                .getAttributeValue(numStrs[1]));
            item.setNqtunitnum((UFDouble) srcItemMap.get(key)
                .getAttributeValue(numStrs[2]));
          }
        }
      }
    }
  }
  
  /**
   * 这个Map，主要用来获取不同上有单据对应订单的数量字段，映射关系来源于单据转换
   * 
   * @return
   */
  protected String[] getNumStrs(){
    return new String[]{OrderItemVO.NNUM, OrderItemVO.NASTNUM, OrderItemVO.NASTNUM};
  }

  protected void fillOrgSuppValue(BillHelper<OrderVO> helper, int[] rows) {
    // 补充组织相关的信息
    this.getOrgDefaultValueSetter(helper).setDefaultOrganizationValue(rows);
    // 设置物流组织
    FlowStockOrgValue flowOrgRule = new FlowStockOrgValue(helper);
    flowOrgRule.registerCombineRemoteCall(rows);
    flowOrgRule.setFlowStockOrg(rows);
    // 设置需求公司
    new ReqCorpDefaultValue(helper).setDefaultValue(rows);
    // 获得供应商信息
    SupplierInfo supplier = this.getSupplierInfo(helper);

    // 设置供应商的默认值
    SupplierDefaultValue vendorDefaultValue = new SupplierDefaultValue(helper);
    vendorDefaultValue.setDefaultValueNotClear(supplier);

    this.setOrgCurrency(helper);
    // 补充本位币和汇率的相关信息
    new CurrencyAndExchangerate(helper).setCurrencyAndExchangeRate(rows);
  }

  protected void fillOtherInfo(OrderVO[] vos, AggregatedValueObject[] srcVOs) {
    //
  }

  /**
   * 根据需求：销售订单直运安排采购订单，补充表体直运仓库。
   * 订单自制时不考虑补仓库的问题（用户场景中没有通过自制订单走直运的）。
   * 
   * @param helper
   */
  protected void fillStordocForDirect(BillHelper<OrderVO> helper) {
    Set<String> pk_storeorgs = new HashSet<String>();
    for (int i = 0; i < helper.getItemCount(); i++) {
      String pk_stororg =
          (String) helper.getBodyValue(i, OrderItemVO.PK_ARRVSTOORG);
      String pk_stordoc =
          (String) helper.getBodyValue(i, OrderItemVO.PK_RECVSTORDOC);
      if (StringUtils.isEmpty(pk_stordoc) && !StringUtils.isEmpty(pk_stororg)) {
        pk_storeorgs.add(pk_stororg);
      }
    }
    if (pk_storeorgs.size() > 0) {
      Map<String, String> orgStordocMap =
          StordocPubService.getDirectstore(pk_storeorgs
              .toArray(new String[pk_storeorgs.size()]));
      if (MapUtils.isEmpty(orgStordocMap)) {
        return;
      }
      for (int i = 0; i < helper.getItemCount(); i++) {
        String pk_stororg =
            (String) helper.getBodyValue(i, OrderItemVO.PK_ARRVSTOORG);
        String pk_stordoc =
            (String) helper.getBodyValue(i, OrderItemVO.PK_RECVSTORDOC);
        if (StringUtils.isEmpty(pk_stordoc)) {
          helper.setBodyValue(i, OrderItemVO.PK_RECVSTORDOC,
              orgStordocMap.get(pk_stororg));
        }
      }
    }
  }

  /**
   * vat规则补全信息，如国家等。子类不需要补全的话直接覆写掉该方法。
   * 
   * @param vos
   */
  protected void fillVatInfo(OrderVO[] vos) {
    // 这里默认处理为重新获取vat（ 采购订单来源大部分都需要重新获取vat），子类可以覆写
    this.resetVatInfoByOrderRule(vos);
  }

  /**
   * 组织信息设置器
   * 
   * @param helper
   * @return
   */
  protected OrganizationDefaultValue getOrgDefaultValueSetter(
      BillHelper<OrderVO> helper) {
    return new OrganizationDefaultValue(helper);
  }

  protected SupplierInfo getSupplierInfo(IKeyValue keyValue) {
    String pk_supplier =
        (String) keyValue.getHeadValue(OrderHeaderVO.PK_SUPPLIER);
    if (pk_supplier == null) {
      return null;
    }
    String pk_purchaseorg =
        (String) keyValue.getHeadValue(OrderHeaderVO.PK_ORG);
    SupplierInfo supplier = null;
    try {
      supplier =
          NCLocator.getInstance().lookup(IOrderSupplierQuery.class)
              .querySupplier(pk_supplier, pk_purchaseorg);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return supplier;
  }

  protected void processMargin(OrderVO[] vos, AggregatedValueObject[] srcVos) {
    return;
  }

  protected void relationCalculate(RelationCalculate cal, OrderVO vo) {
    cal.calculate(vo, OrderItemVO.NNUM);
  }

  /**
   * 根据订单的vat规则重新寻找国家税码等
   * 
   * @param vos
   */
  protected void resetVatInfoByOrderRule(OrderVO[] vos) {
    OrderVatInfoSrcFillRule vatRule = new OrderVatInfoSrcFillRule();
    vatRule.setResetVat(true);
    vatRule.process(vos);
  }

  protected void setDept(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    MapList<String, OrderVO> mapList = this.getOrderMapList(vos);
    if (mapList.size() == 0) {
      return;
    }

    String pk_org = vos[0].getHVO().getPk_org();
    MapList<String, String> psndeptMapList =
        this.getPsnDeptMap(mapList, pk_org);
    if (null == psndeptMapList || 0 == psndeptMapList.size()) {
      return;
    }

    Set<String> deptSet = new HashSet<String>();
    for (Entry<String, List<String>> entry : psndeptMapList.entrySet()) {
      deptSet.addAll(entry.getValue());
    }
    String[] depts = deptSet.toArray(new String[deptSet.size()]);
    Map<String, String> deptMap = DeptPubService.getLastVIDSByDeptIDS(depts);
    if (null == deptMap || 0 == deptMap.size()) {
      return;
    }

    Map<String, UFBoolean> visibleMap = this.getVisibleMap(depts, pk_org);

    for (Entry<String, List<OrderVO>> entry : mapList.entrySet()) {
      String cemployeeid = entry.getKey();
      List<String> deptList = psndeptMapList.get(cemployeeid);
      if (deptList != null && deptList.size() == 1) {
        String dept = deptList.get(0);
        if (visibleMap.get(dept).booleanValue()) {
          for (OrderVO vo : entry.getValue()) {
            vo.getHVO().setPk_dept(dept);
            vo.getHVO().setPk_dept_v(deptMap.get(dept));
          }
        }
      }
    }
  }

  /**
   * 根据来源或源头设置订单的交易类型，主要是依据请购单和销售订单
   * 
   * @param orderVOs
   */
  protected void setDirectValue(OrderVO[] orderVOs) {
    // 根据请购单补
    String[] praybillids = this.getPraybillHids(orderVOs);
    if (!ArrayUtils.isEmpty(praybillids)) {
      this.setDirectValueByPraybill(praybillids, orderVOs);
    }
    // 根据销售订单补
    String[] soTrantypeids = this.getSoTrantyeIds(orderVOs);
    if (!ArrayUtils.isEmpty(soTrantypeids)) {
      this.setDirectValueBySOOrder(soTrantypeids, orderVOs);
    }
  }

  /**
   * 子类根据是否需要自动询价实现该方法
   * 
   * @author mengjian
   * @param vos
   */
  protected void setOrderPrice(OrderVO[] vos) {
    // TODO 子类根据需要处理

  }

  /**
   * 根据参数寻单价。
   * 
   * @author mengjian
   * @param vos 没有寻到价格的采购订单聚合VO数组。
   */
  protected void setPriceByParaPO16(OrderVO[] vos) {
    if (!this.isExistParam(vos)) {
      return;
    }
    List<OrderVO> bills = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      List<OrderItemVO> items = new ArrayList<OrderItemVO>();
      for (OrderItemVO item : vo.getBVO()) {
        if (MathTool.isZero(item.getNqtorigtaxprice())) {
          items.add(item);
        }
      }
      if (items.size() > 0) {
        OrderVO bill = new OrderVO();
        bill.setHVO(vo.getHVO());
        bill.setBVO(items.toArray(new OrderItemVO[items.size()]));
        bills.add(bill);
      }
    }
    if (bills.size() > 0) {
      this.setPrice(bills.toArray(new OrderVO[bills.size()]));
//    } else {
//      RelationCalculate cal = new RelationCalculate();
//      for (OrderVO vo : vos) {
//          cal.calculate(vo, OrderItemVO.NQTORIGTAXPRICE);
//      }
    }
  }

}
