package nc.vo.pu.m21.rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import nc.itf.org.IOrgConst;
import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.pu.reference.uap.org.ArrliabCenterPubService;
import nc.itf.pu.reference.uap.org.parameter.IFinanceOrgForArrliabCenter;
import nc.itf.pu.reference.uap.org.parameter.IStoreOrganization;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.PurchaseOrgPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.itf.scmpub.reference.uap.org.parameter.IFinanceOrganization;
import nc.itf.scmpub.reference.uap.org.parameter.IMaterialStoreOrganization;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.rule.PCCostregionSetter;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.util.remotecallcombination.RemoteCallCombinatorEx;
import nc.vo.util.remotecallcombination.Token;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>1、 带出默认结算财务组织（如果需求库存组织不为空，根据表体的需求库存组织+物料采购分类+表头采购组织匹配采购业务委托关系，找到对应值；
 * 如果需求库存组织为空则按照收货库存组织+物料采购分类+表头采购组织匹配采购业务委托关系；如果仍为空，则判断采购组织是否勾选了财务组织的组织属性，
 * 如果勾选了则可以将采购组织的组织ID作为默认值带入）
 * <li>2、 带出默认应付财务组织（如果需求库存组织不为空，根据表体的需求库存组织+物料采购分类+表头采购组织匹配采购业务委托关系，找到对应的应付组织；
 * 如果为空则按照收货库存组织+物料采购分类+表头采购组织匹配采购业务委托关系；如果仍为空，则将结算财务组织作为默认值带入）
 * <li>3、
 * 带出默认利润中心（根据需求库存组织+物料采购分类+表头采购组织匹配采购业务委托关系，找到对应的利润中心；如果为空则按照收货库存组织+物料采购分类
 * +表头采购组织匹配采购业务委托关系；如果仍为空，则如果应付组织同时是利润中心的属性则将应付组织作为默认值带入）
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 下午12:26:28
 */
public class OrganizationDefaultValue implements IPURemoteCallCombinator {
  private static class MaterialStoreOrganization implements
      IMaterialStoreOrganization {
    private String materialVersionId;

    private String storeOrganizationId;

    public MaterialStoreOrganization() {
      return;
    }

    @Override
    public String getMaterialVersionId() {
      return this.materialVersionId;
    }

    @Override
    public String getStoreOrganizationId() {
      return this.storeOrganizationId;
    }

    public void setMaterialId(String materialId) {
      this.materialVersionId = materialId;
    }

    public void setStoreOrganizationId(String storeOrganizationId) {
      this.storeOrganizationId = storeOrganizationId;
    }
  }

  // 收货仓库 、库存组织对象 by guoyk 2014年9月26日
  private static class StoreOrganization implements IStoreOrganization {
    // 收货仓库ID
    private String recvstorageId;

    // 库存组织ID
    private String storeOrganizationId;

    public StoreOrganization() {
      return;
    }

    @Override
    public String getRecvstorageId() {
      return this.recvstorageId;
    }

    @Override
    public String getStoreOrganizationId() {
      return this.storeOrganizationId;
    }

    public void setRecvstorageId(String recvstorageId) {
      this.recvstorageId = recvstorageId;
    }

    public void setStoreOrganizationId(String storeOrganizationId) {
      this.storeOrganizationId = storeOrganizationId;
    }
  }

  private boolean clear = true;

  private Map<String, UFBoolean> enableMap;

  private IKeyValue keyValue;

  private HashMap<String, String> orgVidBuf;

  private String pk_org;

  private String pk_org_v;

  private Token token_arr;

  // 收货利润中心的token by guoyk 2014年9月26日
  private Token token_arrliab;

  private Token token_req;

  private Map<String, UFBoolean> typeMap;

  public OrganizationDefaultValue(IKeyValue keyValue) {
    this.keyValue = keyValue;
    this.initialOrgInfo();
  }

  @Override
  public void prepare() {
    //
  }

  @Override
  public void process() {
    //
  }

  public void registerCombineRemoteCall(int[] rows) {

    // 设置默认需求库存组织和默认收货库存组织
    this.setDefaultStoreOrganization(rows);
    Map<Integer, IMaterialStoreOrganization> map1 =
        this.getMaterialAndStoreOrgs(rows, OrderItemVO.PK_REQSTOORG);
    Map<Integer, IMaterialStoreOrganization> map2 =
        this.getMaterialAndStoreOrgs(rows, OrderItemVO.PK_ARRVSTOORG);

    PurchaseOrgPubService service = new PurchaseOrgPubService();
    try {
      if (map1.size() != 0) {
        if (this.token_req != null) {
          RemoteCallCombinatorEx.getInstance().update(this.token_req);
        }
        this.token_req =
            service
                .register_findFinanceOrganization(this.pk_org, map1.values());
      }
      if (map2.size() != 0) {
        if (this.token_arr != null) {
          RemoteCallCombinatorEx.getInstance().update(this.token_arr);
        }
        this.token_arr =
            service
                .register_findFinanceOrganization(this.pk_org, map2.values());
      }
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    // 收货利润中心远程调用注册 by guoyk 2014年9月29日
    this.registerCombineRemoteCallForArrCenter(rows);
  }

  // 收货利润中心远程调用注册 by guoyk 2014年9月29日
  public void registerCombineRemoteCallForArrCenter(int[] rows) {
    Map<Integer, IStoreOrganization> map =
        this.getRecvstorageAndStoreOrgs(rows);
    ArrliabCenterPubService arrService = new ArrliabCenterPubService();
    try {
      if (map.size() != 0) {
        if (this.token_arrliab != null) {
          RemoteCallCombinatorEx.getInstance().update(this.token_arrliab);
        }
        this.token_arrliab =
            arrService.register_findArrliabCenter(map.values());
      }
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }

  }

  public void setClear(boolean clear) {
    this.clear = clear;
  }

  // 设置收货利润中心 by guoyk 2014年9月29日
  public void setDefaultArrliabCenterValue(int[] rows) {
    // 根据收货仓库和收货库存组织设置利润中心
    this.setArrliabCenterValueByStor(rows);
    // 收货仓库和收货库存组织以及收货库存组织所属财务组织都没指定收货利润中心,从结算利润中心带入,都没有则为空
    this.setArrliabCenterValueByApliab(rows);
  }

  /**
   * 方法功能描述：根据业务委托关系设置默认的组织的值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   *          行
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-1-28 下午02:39:26
   */
  public void setDefaultOrganizationValue(int[] rows) {
    if (this.pk_org == null || this.pk_org_v == null) {
      return;
    }

    // 设置采购组织VID以及表体行的采购组织
    this.setPurchaseOrg(rows);

    // 清除财务组织
    this.clearFinanceOrganization(rows);

    // 设置默认需求库存组织和默认收货库存组织
    if (this.token_arr == null) {
      this.setDefaultStoreOrganization(rows);
    }

    // 根据需求库存组织进行匹配
    this.findAndLoadFinanceOrganization(rows, OrderItemVO.PK_REQSTOORG);

    // 根据收货库存组织进行匹配
    this.findAndLoadFinanceOrganization(rows, OrderItemVO.PK_ARRVSTOORG);

    // 如果仍然没有找到财务组织，如果采购组织同时又是财务组织，则用采购组织的作为财务组织的ID
    this.setDefaultPsFinance(rows);
    // 如果仍然没有应付财务组织，则将结算财务组织作为默认值带入
    this.setDefaultApFinance(rows);
    // 如果仍然没有利润中心，如果应付组织是利润中心，则取应付组织的ID作为利润中心
    this.setDefaultAbFinance(rows);
    // 设置收货利润中心 by guoyk
    // this.setDefaultArrliabCenterValue(rows);
    // 设置收货利润中心默认值
    new PCCostregionSetter(this.keyValue).setPK_Arriabcenter(rows);
  }

  public void setDefaultOrganizationValueByArrOrg(int[] rows) {
    if (this.pk_org == null || this.pk_org_v == null) {
      return;
    }

    // 设置采购组织VID以及表体行的采购组织
    this.setPurchaseOrg(rows);

    // 清除财务组织
    this.clearFinanceOrganization(rows);

    // 根据需求库存组织进行匹配
    this.findAndLoadFinanceOrganization(rows, OrderItemVO.PK_REQSTOORG);

    // 根据收货库存组织进行匹配
    this.findAndLoadFinanceOrganization(rows, OrderItemVO.PK_ARRVSTOORG);

    // 如果仍然没有找到财务组织，如果采购组织同时又是财务组织，则用采购组织的作为财务组织的ID
    this.setDefaultPsFinance(rows);
    // 如果仍然没有应付财务组织，则将结算财务组织作为默认值带入
    this.setDefaultApFinance(rows);
    // 如果仍然没有利润中心，如果应付组织是利润中心，则取应付组织的ID作为利润中心
    this.setDefaultAbFinance(rows);
    // 20141026 zj编辑收货库存组织，收货利润中心重新取值 必须放在设置财务组织后
    new nc.vo.pu.pub.rule.PCCostregionSetter(this.keyValue)
        .setArrviCentersByOrg(rows);
  }

  private void clearFinanceOrganization(int[] rows) {
    if (this.clear) {
      for (int row : rows) {
        this.keyValue.setBodyValue(row, OrderItemVO.PK_PSFINANCEORG_V, null);
        this.keyValue.setBodyValue(row, OrderItemVO.PK_APFINANCEORG_V, null);
        this.keyValue.setBodyValue(row, OrderItemVO.PK_APLIABCENTER_V, null);
      }
    }
  }

  private void findAndLoadFinanceOrganization(int[] rows,
      String storeOrganizationItemKey) {
    Map<Integer, IMaterialStoreOrganization> map =
        this.getMaterialAndStoreOrgs(rows, storeOrganizationItemKey);
    if (map.size() == 0) {
      return;
    }

    PurchaseOrgPubService service = new PurchaseOrgPubService();
    Collection<IFinanceOrganization> financeOrganizations =
        service
            .findFinanceOrganization(
                this.pk_org,
                map.values(),
                storeOrganizationItemKey.equals(OrderItemVO.PK_REQSTOORG) ? this.token_req
                    : this.token_arr);
    this.loadOrganizationsToPanel(map.keySet(), financeOrganizations);
  }

  /**
   * 方法功能描述：从界面上获得物料和库存组织的信息。
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   *          要操作的行
   * @return 行和物料库存组织信息的对照
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-1-29 上午10:50:45
   */
  private Map<Integer, IMaterialStoreOrganization> getMaterialAndStoreOrgs(
      int[] rows, String storeOrganizationItemKey) {
    Map<Integer, IMaterialStoreOrganization> map =
        new LinkedHashMap<Integer, IMaterialStoreOrganization>();

    for (int i = 0; i < rows.length; i++) {
      String pk_material =
          (String) this.keyValue.getBodyValue(rows[i], OrderItemVO.PK_MATERIAL);
      String pk_storeOrg =
          (String) this.keyValue
              .getBodyValue(rows[i], storeOrganizationItemKey);
      String pk_psfinOrg =
          (String) this.keyValue.getBodyValue(rows[i],
              OrderItemVO.PK_PSFINANCEORG_V);
      String pk_apfinOrg =
          (String) this.keyValue.getBodyValue(rows[i],
              OrderItemVO.PK_APFINANCEORG_V);
      String pk_apliabcenter =
          (String) this.keyValue.getBodyValue(rows[i],
              OrderItemVO.PK_APLIABCENTER_V);
      if (pk_material != null
          && pk_storeOrg != null
          && (pk_psfinOrg == null || pk_apfinOrg == null || pk_apliabcenter == null)) {
        MaterialStoreOrganization param = new MaterialStoreOrganization();
        param.setMaterialId(pk_material);
        param.setStoreOrganizationId(pk_storeOrg);
        map.put(Integer.valueOf(rows[i]), param);
      }
    }
    return map;
  }

  private UFBoolean getOrgEnable(String pk_org) {
    if (pk_org == null) {
      return UFBoolean.FALSE;
    }
    if (this.enableMap == null) {
      this.enableMap = new HashMap<String, UFBoolean>();
    }
    else if (this.enableMap.containsKey(pk_org)) {
      return this.enableMap.get(pk_org);
    }

    UFBoolean enable = StockOrgPubService.isEnable(pk_org);
    this.enableMap.put(pk_org, enable);
    return enable;
  }

  private boolean getOrgType(String pk_org, String type) {
    if (pk_org == null) {
      return false;
    }
    // 将组织和类型联合作为key，组织是否属于类型作为value
    String key = pk_org + type;
    if (this.typeMap == null) {
      this.typeMap = new HashMap<String, UFBoolean>();
    }
    else if (this.typeMap.containsKey(key)) {
      return this.typeMap.get(key).booleanValue();
    }

    boolean istype = OrgUnitPubService.isTypeOf(pk_org, type);
    this.typeMap.put(key, UFBoolean.valueOf(istype));
    return istype;
  }

  private String getOrgVid(String orgOid) {
    if (orgOid == null) {
      return null;
    }
    if (this.orgVidBuf == null) {
      this.orgVidBuf = new HashMap<String, String>();
    }
    else if (this.orgVidBuf.containsKey(orgOid)) {
      return this.orgVidBuf.get(orgOid);
    }

    String orgVid = null;
    orgVid = OrgUnitPubService.getOrgVid(orgOid);
    this.orgVidBuf.put(orgOid, orgVid);
    return orgVid;
  }

  // 设置收货仓库和库存组织对象 by guoyk 2014年9月26日
  private Map<Integer, IStoreOrganization> getRecvstorageAndStoreOrgs(int[] rows) {
    Map<Integer, IStoreOrganization> map =
        new LinkedHashMap<Integer, IStoreOrganization>();
    for (int row : rows) {
      StoreOrganization param = new StoreOrganization();
      String storeOrganizationId =
          (String) this.keyValue.getBodyValue(row, OrderItemVO.PK_ARRVSTOORG);
      String recvstorageId =
          (String) this.keyValue.getBodyValue(row, OrderItemVO.PK_RECVSTORDOC);
      if (!(storeOrganizationId == null && recvstorageId == null)) {
        param.setStoreOrganizationId(storeOrganizationId);
        param.setRecvstorageId(recvstorageId);
        map.put(Integer.valueOf(row), param);
      }
    }
    return map;
  }

  private void initialOrgInfo() {
    // 采购组织OID
    this.pk_org = (String) this.keyValue.getHeadValue(OrderHeaderVO.PK_ORG);
    if (this.pk_org == null) {
      return;
    }

    // 采购组织VID
    this.pk_org_v = this.getOrgVid(this.pk_org);
  }

  /**
   * 方法功能描述：收货利润中心填写到界面上。
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   *          行
   * @param arrliabCenters
   *          对应的收货利润中心
   *          <p>
   * @since 6.35
   * @author guoyk
   * @time 2014年9月17日
   */
  private void loadArrliabCenterToPanel(Collection<Integer> rows,
      Collection<IFinanceOrgForArrliabCenter> arrliabCenters) {
    // 没有查询到收货利润中心直接退出
    if (arrliabCenters == null || arrliabCenters.size() == 0) {
      return;
    }
    int[] indexes = new int[rows.size()];
    int i = 0;
    // 转换行号
    for (Integer row : rows) {
      indexes[i] = row.intValue();
      i++;
    }
    i = 0;
    for (IFinanceOrgForArrliabCenter arrliabCenter : arrliabCenters) {
      String pk_arrliabcenter =
          (String) this.keyValue.getBodyValue(indexes[i],
              OrderItemVO.PK_ARRLIABCENTER);
      // 如果收货利润中心为空，则为其赋值
      if (pk_arrliabcenter == null) {
        this.keyValue.setBodyValue(indexes[i], OrderItemVO.PK_ARRLIABCENTER,
            arrliabCenter.getArrliabcenter());
        this.keyValue.setBodyValue(indexes[i], OrderItemVO.PK_ARRLIABCENTER_V,
            arrliabCenter.getArrliabcenter());
      }
      i++;
    }
  }

  /**
   * 方法功能描述：把结算财务组织和应付财务组织填写到界面上。
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   *          行
   * @param orgs
   *          对应的组织
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-1-29 上午10:49:32
   */
  private void loadOrganizationsToPanel(Collection<Integer> rows,
      Collection<IFinanceOrganization> orgs) {
    int[] indexes = new int[rows.size()];
    int i = 0;
    for (Integer row : rows) {
      indexes[i] = row.intValue();
      i++;
    }
    i = 0;
    for (IFinanceOrganization org : orgs) {
      if (org != null) {
        // 如果结算财务组织为空，则为其赋值
        String finance =
            (String) this.keyValue.getBodyValue(indexes[i],
                OrderItemVO.PK_PSFINANCEORG_V);
        if (finance == null) {
          this.keyValue.setBodyValue(indexes[i], OrderItemVO.PK_PSFINANCEORG,
              org.getSettleFinanceOrganization());
          // 设置VID
          this.keyValue.setBodyValue(indexes[i], OrderItemVO.PK_PSFINANCEORG_V,
              this.getOrgVid(org.getSettleFinanceOrganization()));
        }
        // 如果应付财务组织为空，则为其赋值
        finance =
            (String) this.keyValue.getBodyValue(indexes[i],
                OrderItemVO.PK_APFINANCEORG_V);
        if (finance == null) {
          this.keyValue.setBodyValue(indexes[i], OrderItemVO.PK_APFINANCEORG,
              org.getApFinanceOrganization());
          this.keyValue.setBodyValue(indexes[i], OrderItemVO.PK_APFINANCEORG_V,
              this.getOrgVid(org.getApFinanceOrganization()));
        }
        // 如果利润中心为空，则为其赋值
        finance =
            (String) this.keyValue.getBodyValue(indexes[i],
                OrderItemVO.PK_APLIABCENTER_V);
        if (finance == null) {
          this.keyValue.setBodyValue(indexes[i], OrderItemVO.PK_APLIABCENTER,
              org.getApliabCenter());
          this.keyValue.setBodyValue(indexes[i], OrderItemVO.PK_APLIABCENTER_V,
              this.getOrgVid(org.getApliabCenter()));
        }
      }
      i++;
    }
  }

  /*
   * 收货仓库和收货库存组织以及收货库存组织所属财务组织都没指定收货利润中心, 从结算利润中心带入, 都没有则为空
   */
  private void setArrliabCenterValueByApliab(int[] rows) {
    for (int row : rows) {
      String arrliabcenter =
          (String) this.keyValue
              .getBodyValue(row, OrderItemVO.PK_ARRLIABCENTER);
      if (arrliabcenter == null) {
        // 获取结算利润中心最新版本
        String apliabcenter =
            (String) this.keyValue.getBodyValue(row,
                OrderItemVO.PK_APLIABCENTER);
        // 获取结算利润中心
        String apliabcenterV =
            (String) this.keyValue.getBodyValue(row,
                OrderItemVO.PK_APLIABCENTER_V);
        if (apliabcenter != null) {
          this.keyValue.setBodyValue(row, OrderItemVO.PK_ARRLIABCENTER,
              apliabcenter);
          this.keyValue.setBodyValue(row, OrderItemVO.PK_ARRLIABCENTER_V,
              apliabcenterV);
        }
      }
    }
  }

  /*
   * 根据收货仓库和收货库存组织设置利润中心
   */
  private void setArrliabCenterValueByStor(int[] rows) {
    Map<Integer, IStoreOrganization> map =
        this.getRecvstorageAndStoreOrgs(rows);
    if (map.size() == 0) {
      return;
    }
    ArrliabCenterPubService service = new ArrliabCenterPubService();
    Collection<IFinanceOrgForArrliabCenter> financeOrgForArrliabCenters =
        service.findArrliabCenter(map.values(), this.token_arrliab);
    this.loadArrliabCenterToPanel(map.keySet(), financeOrgForArrliabCenters);
  }

  /**
   * 方法功能描述：设置默认利润中心。
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   *          行
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-2-1 上午10:23:01
   */
  private void setDefaultAbFinance(int[] rows) {
    try {
      // 此参数用来判断组织是否利润中心
      Map<String, Boolean> isLiacenter = new HashMap<String, Boolean>();
      for (int row : rows) {
        if (this.keyValue.getBodyValue(row, OrderItemVO.PK_REQSTOORG_V) == null
            && this.keyValue.getBodyValue(row, OrderItemVO.PK_ARRVSTOORG_V) == null) {
          continue;
        }

        String liorg =
            (String) this.keyValue.getBodyValue(row,
                OrderItemVO.PK_APLIABCENTER_V);
        // 如果结算利润中心为空，则取结算财务组织
        if (liorg == null) {
          String psforg =
              (String) this.keyValue.getBodyValue(row,
                  OrderItemVO.PK_PSFINANCEORG);
          String psforgv =
              (String) this.keyValue.getBodyValue(row,
                  OrderItemVO.PK_PSFINANCEORG_V);
          if (psforg == null) {
            continue;
          }
          
          Boolean flag = isLiacenter.get(psforg);
          if(flag == null){
            flag = Boolean.valueOf(this.getOrgType(psforg, nc.itf.org.IOrgConst.LIACENTERTYPE));
            isLiacenter.put(psforg, flag);
          }
          if (flag.booleanValue()) {
            this.keyValue.setBodyValue(row, OrderItemVO.PK_APLIABCENTER, psforg);
            this.keyValue.setBodyValue(row, OrderItemVO.PK_APLIABCENTER_V,
                psforgv);
          }
        }
      }
    }
    catch (Exception e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：设置默认应付财务组织。
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   *          行
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-2-1 上午10:21:34
   */
  private void setDefaultApFinance(int[] rows) {
    for (int row : rows) {
      if (this.keyValue.getBodyValue(row, OrderItemVO.PK_REQSTOORG_V) == null
          && this.keyValue.getBodyValue(row, OrderItemVO.PK_ARRVSTOORG_V) == null) {
        continue;
      }

      String aporg =
          (String) this.keyValue.getBodyValue(row,
              OrderItemVO.PK_APFINANCEORG_V);
      if (aporg == null) {
        String psorg =
            (String) this.keyValue.getBodyValue(row,
                OrderItemVO.PK_PSFINANCEORG);
        String psorgv =
            (String) this.keyValue.getBodyValue(row,
                OrderItemVO.PK_PSFINANCEORG_V);
        this.keyValue.setBodyValue(row, OrderItemVO.PK_APFINANCEORG, psorg);
        this.keyValue.setBodyValue(row, OrderItemVO.PK_APFINANCEORG_V, psorgv);
      }
    }
  }

  /**
   * 方法功能描述：设置默认结算财务组织。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_purchaseOrg
   *          采购组织
   * @param rows
   *          行
   *          <p>
   * @since 6.0
   * @author duy
   * @throws BusinessException
   * @time 2010-2-1 上午10:17:13
   */
  private void setDefaultPsFinance(int[] rows) {
    // 当前采购组织是否属于财务组织
    boolean flag =
        this.getOrgType(this.pk_org, nc.itf.org.IOrgConst.FINANCEORGTYPE);
    if (!flag) {
      return;
    }

    for (int row : rows) {
      if (this.keyValue.getBodyValue(row, OrderItemVO.PK_REQSTOORG_V) == null
          && this.keyValue.getBodyValue(row, OrderItemVO.PK_ARRVSTOORG_V) == null) {
        continue;
      }
      String org =
          (String) this.keyValue.getBodyValue(row,
              OrderItemVO.PK_PSFINANCEORG_V);
      if (org == null) {
        this.keyValue.setBodyValue(row, OrderItemVO.PK_PSFINANCEORG,
            this.pk_org);
        this.keyValue.setBodyValue(row, OrderItemVO.PK_PSFINANCEORG_V,
            this.pk_org_v);
      }
    }
  }

  private void setDefaultStoreOrganization(int[] rows) {
    String pk_storeorg_v = null;
    String pk_storeorg = null;
    // 默认需求库存组织
    for (int row : rows) {
      if (this.keyValue.getBodyValue(row, OrderItemVO.PK_REQSTOORG_V) != null
          && this.keyValue.getBodyValue(row, OrderItemVO.PK_ARRVSTOORG_V) != null) {
        continue;
      }
      if (pk_storeorg_v == null) {
        if (this.getOrgEnable(this.pk_org).booleanValue()
            && this.getOrgType(this.pk_org, IOrgConst.STOCKORGTYPE)) {
          pk_storeorg_v = this.pk_org_v;
          pk_storeorg = this.pk_org;
        }
      }
      if (this.keyValue.getBodyValue(row, OrderItemVO.PK_REQSTOORG_V) == null) {
        this.keyValue.setBodyValue(row, OrderItemVO.PK_REQSTOORG_V,
            pk_storeorg_v);
        this.keyValue.setBodyValue(row, OrderItemVO.PK_REQSTOORG, pk_storeorg);
      }

      String pk_arrvstoorg_v =
          (String) this.keyValue.getBodyValue(row, OrderItemVO.PK_REQSTOORG_V);
      String pk_arrvstoorg =
          (String) this.keyValue.getBodyValue(row, OrderItemVO.PK_REQSTOORG);
      if (this.keyValue.getBodyValue(row, OrderItemVO.PK_ARRVSTOORG_V) == null) {
        this.keyValue.setBodyValue(row, OrderItemVO.PK_ARRVSTOORG_V,
            pk_arrvstoorg_v);
        this.keyValue.setBodyValue(row, OrderItemVO.PK_ARRVSTOORG,
            pk_arrvstoorg);
      }
    }
  }

  /**
   * 方法功能描述：设置采购组织VID以及表体行的采购组织。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_purchaseOrg
   *          采购组织主健(OID)
   * @param pk_purchaseOrgVID
   *          采购组织主键（VID）
   * @param rows
   *          要设置的行
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-3 上午09:55:11
   */
  private void setPurchaseOrg(int[] rows) {
    // 活动采购组织的最新VID
    this.keyValue.setHeadValue(OrderHeaderVO.PK_ORG_V, this.pk_org_v);
    for (int row : rows) {
      this.keyValue.setBodyValue(row, OrderItemVO.PK_ORG, this.pk_org);
      this.keyValue.setBodyValue(row, OrderItemVO.PK_ORG_V, this.pk_org_v);
    }
  }
}
