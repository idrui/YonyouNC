package nc.vo.pu.pub.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import nc.vo.bd.stordoc.StordocVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;

import nc.pubitf.org.cache.IOrgUnitPubService_C;

import nc.bs.framework.common.NCLocator;

/**
 * 设置利润中心
 * 
 * @since 6.5
 * @version 2014-4-21 下午04:21:02
 * @author mengjian
 */
public class PCCostregionSetter {

  private IKeyValue keyValue;

  public PCCostregionSetter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 根据库存组织、财务组织设置利润中心 如果库存组织是利润中心，则利润中心从库存组织携带
   * 如果库存组织不是利润中心，财务组织是利润中心，则利润中心从财务组织携带
   * 
   * @param event
   */
  /*public void setApliabcenterByOrg() {
    boolean stockFlag = false;
    boolean fiFlag = false;
    String pk_stockorg =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_STOCKORG);
    String pk_org =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG);
    if (StringUtils.isNotEmpty(pk_stockorg)) {
      stockFlag =
          OrgUnitPubService.isTypeOf(pk_stockorg,
              nc.itf.org.IOrgConst.LIACENTERTYPE);
    }
    if (StringUtils.isNotEmpty(pk_org)) {
      fiFlag =
          OrgUnitPubService
              .isTypeOf(pk_org, nc.itf.org.IOrgConst.LIACENTERTYPE);
    }
    int rowCount = this.keyValue.getItemCount();
    for (int i = 0; i < rowCount; i++) {
      Object pk_material =
          this.keyValue.getBodyValue(i, InitialEstItemVO.PK_MATERIAL);
      Object csourcebid =
          this.keyValue.getBodyValue(i, InitialEstItemVO.CSOURCEBID);
      // 表体没有来源的物料行才设置
      if (pk_material != null && csourcebid == null) {
        if (stockFlag) {
          this.keyValue.setBodyValue(i, InitialEstItemVO.PK_APLIABCENTER,
              pk_stockorg);
          continue;
        }
        if (fiFlag) {
          this.keyValue.setBodyValue(i, InitialEstItemVO.PK_APLIABCENTER,
              pk_org);
        }
      }
    }

  }*/

  /**
   * 根据仓库设置利润中心
   * 
   * @param event
   */
  /*public void setApliabcenterByStordoc() {
    String pk_stordoc =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_STOCKORG);
    String[] fields = new String[] {
      StordocVO.PROFITCENTRE
    };
    StordocVO[] stcrdocs = StordocPubService.queryStordocByPks(new String[] {
      pk_stordoc
    }, fields);
    if (stcrdocs == null) {
      return;
    }
    String pk_apliabcenter =
        (String) stcrdocs[0].getAttributeValue(StordocVO.PROFITCENTRE);
    if (StringUtils.isBlank(pk_apliabcenter)) {
      return;
    }
    int rowCount = this.keyValue.getItemCount();
    for (int i = 0; i < rowCount; i++) {
      Object pk_material =
          this.keyValue.getBodyValue(i, InitialEstItemVO.PK_MATERIAL);
      Object csourcebid =
          this.keyValue.getBodyValue(i, InitialEstItemVO.CSOURCEBID);
      // 表体没有来源的物料行才设置
      if (pk_material != null && csourcebid == null) {
        this.keyValue.setBodyValue(i, InitialEstItemVO.PK_APLIABCENTER,
            pk_apliabcenter);
      }
    }
  }*/

  // zj 根据收货仓库设置收货利润中心
  public boolean setArrviCenterByOrg(HashMap<String, UFBoolean> hashfi,
      HashMap<String, UFBoolean> hashstock, int row)

  {

    // pk_psfinanceorg 结算财务组织
    String pk_org =
        (String) this.keyValue.getBodyValue(row, OrderItemVO.PK_PSFINANCEORG);

    String pk_stockorg =
        (String) this.keyValue.getBodyValue(row, OrderItemVO.PK_ARRVSTOORG);

    UFBoolean stockFlag =
        hashstock.containsKey(pk_stockorg) ? (UFBoolean) hashstock
            .get(pk_stockorg) : UFBoolean.FALSE;
    // 采购订单 pk_apliabcenter 结算利润中心 最 新版本pk_arrliabcenter 收货利润中心

    if (stockFlag.booleanValue()) {
      this.keyValue
          .setBodyValue(row, OrderItemVO.PK_ARRLIABCENTER, pk_stockorg);
      return true;
    }

    UFBoolean fiFlag =
        hashfi.containsKey(pk_org) ? (UFBoolean) hashfi.get(pk_org)
            : UFBoolean.FALSE;
    if (fiFlag.booleanValue()) {
      this.keyValue.setBodyValue(row, OrderItemVO.PK_ARRLIABCENTER, pk_org);
      return true;
    }

    return false;

  }

  public void setArrviCentersByOrg(int[] rows) {
    // 准备查询数据
    HashMap<String, UFBoolean> hashstock = this.prePareHashStock(rows);
    HashMap<String, UFBoolean> hashfi = this.prePareHashOrg(rows);

    // 开始设值
    for (int i : rows) {

      if (this.setArrviCenterByOrg(hashfi, hashstock, i)) {
        continue;
      }
      // 若没有则从结算利润中心带出 pk_apliabcenter 结算利润中心最新版本
      // pk_arrliabcenter 收货利润中心
      String pk_apliabcenter =
          (String) this.keyValue.getBodyValue(i, OrderItemVO.PK_APLIABCENTER);

      this.keyValue.setBodyValue(i, "pk_arrliabcenter", pk_apliabcenter);
    }

    // 设置最新版本
    this.setArrviCenterVbyArriv(rows);

  }

  /**
   * 设置利润中心 从仓库对应的利润中心携带； 若没有，则从库存组织携带（若库存组织是利润中心）； 若没有，则从财务组织携带（若财务组织是利润中心）；
   * 若都没有，则为空。
   */
  /*public void setPK_apliabcenter() {
    this.setApliabcenterByOrg();
    this.setApliabcenterByStordoc();
  }*/

  // zj 20141026
  /**
   * 设置收货利润中心 界面
   */
  public void setPK_Arriabcenter(int[] rows) {

    HashMap<String, String> hashstordoc = this.preParerecvstordoc(rows);
    HashMap<String, UFBoolean> hashstock = this.prePareHashStock(rows);
    HashMap<String, UFBoolean> hashfi = this.prePareHashOrg(rows);

    for (int i : rows) {
      // luojw 如果收货利润中心有值，则不进行赋值
      if(this.keyValue.getBodyValue(i, OrderItemVO.PK_ARRLIABCENTER) != null){
        continue;
      }
      // 从收货仓库对应的利润中心带
      if (this.setarrivbystore(hashstordoc, i)) {
        continue;
      }
      else
      // 仓库未带出，则从收货库存组织带，没有则从财务组织带
      if (this.setArrviCenterByOrg(hashfi, hashstock, i)) {
        continue;
      }
      // 若没有则从结算利润中心带出 pk_apliabcenter 结算利润中心最新版本
      // pk_arrliabcenter 收货利润中心
      String pk_apliabcenter =
          (String) this.keyValue.getBodyValue(i, OrderItemVO.PK_APLIABCENTER);

      this.keyValue.setBodyValue(i, OrderItemVO.PK_ARRLIABCENTER,
          pk_apliabcenter);
      // 若没有则为空

    }
    // 设置最新版本
    this.setArrviCenterVbyArriv(rows);
  }

  private HashMap<String, UFBoolean> prePareHashOrg(int[] rows) {
    HashMap<String, UFBoolean> hashfi = new HashMap<String, UFBoolean>();

    for (int i : rows) {
      // 编辑完收货库存组织后，财务组织已经有值
      String pk_org =
          (String) this.keyValue.getBodyValue(i, OrderItemVO.PK_PSFINANCEORG);

      if (StringUtils.isNotEmpty(pk_org) && !hashfi.containsKey(pk_org)) {
        boolean fiFlag =
            OrgUnitPubService.isTypeOf(pk_org,
                nc.itf.org.IOrgConst.LIACENTERTYPE);
        if (fiFlag) {
          hashfi.put(pk_org, UFBoolean.TRUE);
        }
        else {
          hashfi.put(pk_org, UFBoolean.FALSE);
        }

      }
    }
    return hashfi;

  }

  private HashMap<String, UFBoolean> prePareHashStock(int[] rows) {
    HashMap<String, UFBoolean> hashstock = new HashMap<String, UFBoolean>();
    for (int i : rows) {

      // 采购订单 pk_arrvstoorg 收货库存组织
      String pk_stockorg =
          (String) this.keyValue.getBodyValue(i, OrderItemVO.PK_ARRVSTOORG);

      if (StringUtils.isNotEmpty(pk_stockorg)
          && !hashstock.containsKey(pk_stockorg)) {
        boolean stockFlag =
            OrgUnitPubService.isTypeOf(pk_stockorg,
                nc.itf.org.IOrgConst.LIACENTERTYPE);

        if (stockFlag) {

          hashstock.put(pk_stockorg, UFBoolean.TRUE);
        }
        else {
          hashstock.put(pk_stockorg, UFBoolean.FALSE);
        }

      }

    }
    return hashstock;

  }

  private HashMap<String, String> preParerecvstordoc(int[] rows) {
    HashMap<String, String> hashstrdocvo = new HashMap<String, String>();

    //
    ArrayList<String> strlist = new ArrayList<String>();
    for (int i : rows) {
      String PK_RECVSTORDOC =
          (String) this.keyValue.getBodyValue(i, OrderItemVO.PK_RECVSTORDOC);
      if (StringUtils.isBlank(PK_RECVSTORDOC)) {
        continue;
      }

      strlist.add(PK_RECVSTORDOC);
    }
    if (strlist.size() < 1) {
      return hashstrdocvo;
    }
    String[] pks = new String[strlist.size()];
    strlist.toArray(pks);
    //

    String[] fields = new String[] {
      StordocVO.PROFITCENTRE
    };

    StordocVO[] stcrdocs = StordocPubService.queryStordocByPks(pks, fields);
    if (stcrdocs == null) {

    }
    else {
      for (int i = 0; i < stcrdocs.length; i++) {

        hashstrdocvo.put(
            (String) stcrdocs[i].getAttributeValue(StordocVO.PK_STORDOC),
            (String) stcrdocs[i].getAttributeValue(StordocVO.PROFITCENTRE));

      }

    }
    return hashstrdocvo;
  }

  private boolean setarrivbystore(HashMap<String, String> hashstordoc, int row)

  {

    String pk_stordoc =
        (String) this.keyValue.getBodyValue(row, OrderItemVO.PK_RECVSTORDOC);

    String pk_arrliabcenter =
        hashstordoc.containsKey(pk_stordoc) ? (String) hashstordoc
            .get(pk_stordoc) : "";
    if (StringUtils.isBlank(pk_arrliabcenter)) {
      return false;
    }

    this.keyValue.setBodyValue(row, OrderItemVO.PK_ARRLIABCENTER,
        pk_arrliabcenter);
    return true;
    // Object pk_material =
    // this.keyValue.getBodyValue(row, OrderItemVO.PK_MATERIAL);
    // Object csourcebid = this.keyValue.getBodyValue(row,
    // OrderItemVO.CSOURCEBID);
    // // 表体没有来源的物料行才设置
    // if (pk_material != null && csourcebid == null) {
    // // pk_arrliabcenter 收货利润中心
    // this.keyValue.setBodyValue(row, OrderItemVO.PK_ARRLIABCENTER,
    // pk_arrliabcenter);
    // return true;
    // }

    // return false;

  }

  private void setArrviCenterVbyArriv(int[] rows) {
    // 根据PK_ORG 查找最新版本，设置值
    try {
      ArrayList<String> strlist = new ArrayList<String>();
      for (int i : rows) {
        String PK_ARRLIABCENTER =
            (String) this.keyValue
                .getBodyValue(i, OrderItemVO.PK_ARRLIABCENTER);
        if (StringUtils.isBlank(PK_ARRLIABCENTER)) {
          continue;
        }

        strlist.add(PK_ARRLIABCENTER);
      }
      Map<String, String> mapvid = new HashMap<String, String>();
      if (strlist.size() > 0) {

        String[] strs = new String[strlist.size()];
        strlist.toArray(strs);

        mapvid =
            NCLocator.getInstance().lookup(IOrgUnitPubService_C.class)
                .getNewVIDSByOrgIDS(strs);

      }
      for (int i : rows) {
        String PK_ARRLIABCENTER =
            (String) this.keyValue
                .getBodyValue(i, OrderItemVO.PK_ARRLIABCENTER);
        if (StringUtils.isBlank(PK_ARRLIABCENTER)) {

          this.keyValue.setBodyValue(i, OrderItemVO.PK_ARRLIABCENTER_V, null);
          continue;
        }

        String newid = mapvid.get(PK_ARRLIABCENTER);
        this.keyValue.setBodyValue(i, OrderItemVO.PK_ARRLIABCENTER_V, newid);

      }

    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);

    }

  }

}
