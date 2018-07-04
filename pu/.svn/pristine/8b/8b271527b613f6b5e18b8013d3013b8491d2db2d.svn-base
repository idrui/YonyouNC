package nc.impl.pu.m21.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.impl.pu.m21.action.rule.maintain.ApDataValueSetter;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.pu.reference.sc.M61PUServices;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pu.m21.entity.ApDataVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.m21.enumeration.EnumBillStatus;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MapSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-3-8 下午04:31:34
 * @author wuxla
 */

public class ApDataAction {

  private ApDataVO[] vos;

  public ApDataVO[] getApData(String pk_org, String pk_supplier) {
    Map<String, UFDouble[]> orderApDataMap =
        this.getOrderApData(pk_org, pk_supplier);
    if (ArrayUtils.isEmpty(this.vos)) {
      return null;
    }

    String[] pk_apfinanceorgs = new String[this.vos.length];
    for (int i = 0; i < this.vos.length; ++i) {
      pk_apfinanceorgs[i] = this.vos[i].getPk_apfinanceorg();
    }

    Map<String, String> orgMap =
        OrgUnitPubService.getNewVIDSByOrgIDS(pk_apfinanceorgs);
    for (ApDataVO vo : this.vos) {
      vo.setPk_apfinanceorg_v(orgMap.get(vo.getPk_apfinanceorg()));
    }

    List<String> invoiceBidList =
        this.getInvoiceBIDsFromOrder(pk_org, pk_supplier, pk_apfinanceorgs);
    Map<String, UFDouble[]> scApDataMap =
        M61PUServices.queryMnyBySupply(pk_org, pk_supplier, pk_apfinanceorgs);
    List<String> scInvoiceBidList =
        M61PUServices.queryM25pkBySupply(pk_org, pk_supplier, pk_apfinanceorgs);

    // 查询订单对应的采购发票对应的应付单的已核销本币金额
    if (!CollectionUtils.isEmpty(scInvoiceBidList)) {
      invoiceBidList.addAll(scInvoiceBidList);
    }
    Map<String, UFDouble> verifyMnyMap = null;
    if (!CollectionUtils.isEmpty(invoiceBidList)) {
      String[] pk_invoice_bs =
          invoiceBidList.toArray(new String[invoiceBidList.size()]);
      verifyMnyMap =
          ArapServicesForPUUtil.getVerfiyMoney(pk_supplier, pk_apfinanceorgs,
              pk_invoice_bs);
    }
    Map<String, UFDouble> unPayMnyMap =
        ArapServicesForPUUtil.getUnpayedMoney(pk_supplier, pk_apfinanceorgs);
    // 累计付款本币金额
    Map<String, UFDouble> orderPayMnyMap =
        this.getOrderPayMny(pk_org, pk_supplier, pk_apfinanceorgs);

    Map<String, UFDouble> creditboundMap =
        SupplierPubService.getCreditboundByOrg(pk_supplier, pk_apfinanceorgs);

    Map<String, UFDouble> payVerifyMap =
        this.getPayVerifyMny(pk_org, pk_supplier);

    for (ApDataVO vo : this.vos) {
      ApDataValueSetter valueSetter = new ApDataValueSetter();
      valueSetter.setOrderPayMnyMap(orderPayMnyMap);
      valueSetter.setScApDataMap(scApDataMap);
      valueSetter.setCreditboundMap(creditboundMap);
      valueSetter.setUnPayMnyMap(unPayMnyMap);
      valueSetter.setVerifyMnyMap(verifyMnyMap);
      valueSetter.setOrderApDataMap(orderApDataMap);
      valueSetter.setPayVerifyMap(payVerifyMap);
      valueSetter.setApData(vo);
    }

    return this.vos;
  }

  /**
   * 表体id
   * 
   * @param pk_org
   * @param pk_supplier
   * @param pk_apfinanceorgs
   * @return
   */
  private List<String> getInvoiceBIDsFromOrder(String pk_org,
      String pk_supplier, String[] pk_apfinanceorgs) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct invb." + InvoiceItemVO.PK_INVOICE_B);
    sql.append(" from " + PUEntity.M21_H_TABLE + " h, ");
    sql.append(PUEntity.M21_B_TABLE + " b, ");
    sql.append(PUEntity.M25_B_TAB + " invb ");
    sql.append(" where h." + OrderHeaderVO.PK_ORDER + " = b."
        + OrderItemVO.PK_ORDER);
    sql.append(" and b." + OrderItemVO.PK_ORDER_B + " = invb."
        + InvoiceItemVO.PK_ORDER_B);
    sql.append(" and h." + OrderHeaderVO.DR, 0);
    sql.append(" and h." + OrderHeaderVO.PK_ORG, pk_org);
    sql.append(" and h." + OrderHeaderVO.PK_SUPPLIER, pk_supplier);
    sql.append(" and h." + OrderHeaderVO.BISLATEST, UFBoolean.TRUE);
    sql.append(" and h." + OrderHeaderVO.FORDERSTATUS, new int[] {
      POEnumBillStatus.APPROVE.toInt(), EnumBillStatus.EXPORT.toInt()
    });
    sql.append(" and h." + OrderHeaderVO.BFROZEN, UFBoolean.FALSE);
    sql.append(" and b." + OrderItemVO.DR, 0);
    sql.append(" and b." + OrderItemVO.BPAYCLOSE, UFBoolean.FALSE);
    sql.append(" and invb." + InvoiceItemVO.DR, 0);
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_35.name());
    sql.append(builder.buildSQL(" and invb." + InvoiceItemVO.PK_APFINANCEORG,
        pk_apfinanceorgs));

    String[] values =
        new DataAccessUtils().query(sql.toString()).toOneDimensionStringArray();
    if (!ArrayUtils.isEmpty(values)) {
      List<String> list = new ArrayList<String>();
      for (String value : values) {
        list.add(value);
      }
      return list;
    }
    return new ArrayList<String>();
  }

  /**
   * @param pk_org
   * @param pk_supplier
   * @param pk_apfinanceorgs
   * @return 组织，本币价税合计，累计入库数量*单价，累计核销金额
   */
  private Map<String, UFDouble[]> getOrderApData(String pk_org,
      String pk_supplier) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct b." + OrderItemVO.PK_APFINANCEORG);
    sql.append(",b." + OrderItemVO.CCURRENCYID);
    sql.append(" from " + PUEntity.M21_B_TABLE + " b ");
    sql.append(" where ");
    sql.append(" b." + OrderItemVO.PK_ORG, pk_org);
    sql.append(" and b." + OrderItemVO.PK_SUPPLIER, pk_supplier);
    sql.append(" and b.dr", 0);
    sql.append(" and b." + OrderItemVO.FISACTIVE, new int[] {
      EnumActive.ACTIVE.toIntValue(), EnumActive.CLOSE.toIntValue()
    });
    sql.append(" and b." + OrderItemVO.BLARGESS, UFBoolean.FALSE);
    String[][] values =
        new DataAccessUtils().query(sql.toString()).toTwoDimensionStringArray();
    if (ArrayUtils.isEmpty(values)) {
      return null;
    }

    this.vos = new ApDataVO[values.length];
    Map<String, UFDouble[]> map = new HashMap<String, UFDouble[]>();
    for (int i = 0; i < values.length; ++i) {
      String[] value = values[i];
      this.vos[i] = new ApDataVO();
      this.vos[i].setPk_apfinanceorg(value[0]);
      this.vos[i].setCcurrencyid(value[1]);
      map.put(value[0], new UFDouble[] {
        UFDouble.ZERO_DBL, UFDouble.ZERO_DBL
      });
    }

    sql = new SqlBuilder();
    sql.append("select distinct b." + OrderItemVO.PK_APFINANCEORG);
    sql.append(",b." + OrderItemVO.CCURRENCYID);
    sql.append(",sum(" + OrderItemVO.NTAXMNY + ") ");
    sql.append(",sum(" + OrderItemVO.NACCUMSTORENUM + "*"
        + OrderItemVO.NTAXPRICE + ") ");
    // sql.append(",sum(" + OrderItemVO.NACCCANCELINVMNY + ") ");
    sql.append("from " + PUEntity.M21_H_TABLE + " h, ");
    sql.append(PUEntity.M21_B_TABLE + " b ");
    sql.append("where h." + OrderHeaderVO.PK_ORDER + " = b."
        + OrderItemVO.PK_ORDER);
    sql.append(" and h." + OrderHeaderVO.DR, 0);
    sql.append(" and h." + OrderHeaderVO.PK_ORG, pk_org);
    sql.append(" and h." + OrderHeaderVO.PK_SUPPLIER, pk_supplier);
    sql.append(" and h." + OrderHeaderVO.BISLATEST, UFBoolean.TRUE);
    sql.append(" and h." + OrderHeaderVO.FORDERSTATUS, new int[] {
      POEnumBillStatus.APPROVE.toInt(), EnumBillStatus.EXPORT.toInt()
    });
    sql.append(" and h." + OrderHeaderVO.BFROZEN, UFBoolean.FALSE);
    sql.append(" and b." + OrderItemVO.DR, 0);
    // sql.append(" and b." + OrderItemVO.PK_APFINANCEORG, pk_apfinanceorgs);
    sql.append(" and b." + OrderItemVO.BLARGESS, UFBoolean.FALSE);
    sql.append(" and b." + OrderItemVO.BPAYCLOSE, UFBoolean.FALSE);
    sql.append(" group by b." + OrderItemVO.PK_APFINANCEORG + ",b."
        + OrderItemVO.CCURRENCYID);

    values =
        new DataAccessUtils().query(sql.toString()).toTwoDimensionStringArray();
    if (ArrayUtils.isEmpty(values)) {
      return map;
    }

    for (int i = 0; i < values.length; ++i) {
      String[] value = values[i];
      map.put(value[0], new UFDouble[] {
        new UFDouble(value[2]), new UFDouble(value[3])
      });
    }

    return map;
  }

  private Map<String, UFDouble> getOrderPayMny(String pk_org,
      String pk_supplier, String[] pk_apfinanceorgs) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct b." + AbstractPayPlanVO.PK_FINANCEORG);
    sql.append(",sum(" + AbstractPayPlanVO.NACCUMPAYMNY + ") ");
    sql.append("from " + PUEntity.M21_H_TABLE + " h, ");
    sql.append(PUEntity.M21_PAYPLAN_TABLE + " b ");
    sql.append("where h." + OrderHeaderVO.PK_ORDER + " = b."
        + PayPlanVO.PK_ORDER);
    sql.append(" and h." + OrderHeaderVO.DR, 0);
    sql.append(" and h." + OrderHeaderVO.PK_ORG, pk_org);
    sql.append(" and h." + OrderHeaderVO.PK_SUPPLIER, pk_supplier);
    sql.append(" and h." + OrderHeaderVO.BISLATEST, UFBoolean.TRUE);
    sql.append(" and h." + OrderHeaderVO.FORDERSTATUS, new int[] {
      POEnumBillStatus.APPROVE.toInt(), EnumBillStatus.EXPORT.toInt()
    });
    sql.append(" and h." + OrderHeaderVO.BFROZEN, UFBoolean.FALSE);
    sql.append(" and b." + AbstractPayPlanVO.DR, 0);
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_25_12.name());
    sql.append(builder.buildSQL(" and b." + AbstractPayPlanVO.PK_FINANCEORG,
        pk_apfinanceorgs));
    sql.append(" group by b." + AbstractPayPlanVO.PK_FINANCEORG);

    String[][] values =
        new DataAccessUtils().query(sql.toString()).toTwoDimensionStringArray();
    if (ArrayUtils.isEmpty(values)) {
      return null;
    }

    Map<String, UFDouble> map = new HashMap<String, UFDouble>();
    for (String[] value : values) {
      map.put(value[0], new UFDouble(value[1]));
    }
    return map;
  }

  private Map<String, UFDouble> getPayVerifyMny(String pk_org,
      String pk_supplier) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct b." + OrderItemVO.PK_APFINANCEORG + ",b."
        + OrderItemVO.PK_ORDER);
    sql.append(" from " + PUEntity.M21_H_TABLE + " h, ");
    sql.append(PUEntity.M21_B_TABLE + " b ");
    sql.append("where h." + OrderHeaderVO.PK_ORDER + " = b."
        + OrderItemVO.PK_ORDER);
    sql.append(" and h." + OrderHeaderVO.DR, 0);
    sql.append(" and h." + OrderHeaderVO.PK_ORG, pk_org);
    sql.append(" and h." + OrderHeaderVO.PK_SUPPLIER, pk_supplier);
    sql.append(" and h." + OrderHeaderVO.BISLATEST, UFBoolean.TRUE);
    sql.append(" and h." + OrderHeaderVO.FORDERSTATUS, new int[] {
      POEnumBillStatus.APPROVE.toInt(), EnumBillStatus.EXPORT.toInt()
    });
    sql.append(" and h." + OrderHeaderVO.BFROZEN, UFBoolean.FALSE);
    sql.append(" and b." + OrderItemVO.DR, 0);
    sql.append(" and b." + OrderItemVO.BLARGESS, UFBoolean.FALSE);

    String[][] values =
        new DataAccessUtils().query(sql.toString()).toTwoDimensionStringArray();
    if (ArrayUtils.isEmpty(values)) {
      return null;
    }

    MapSet<String, String> mapSet = new MapSet<String, String>();
    for (String[] value : values) {
      mapSet.put(value[0], value[1]);
    }

    Map<String, UFDouble> map = new HashMap<String, UFDouble>();
    for (Entry<String, Set<String>> entry : mapSet.entrySet()) {
      Set<String> set = entry.getValue();
      String[] hids = set.toArray(new String[set.size()]);
      UFDouble mny = ArapServicesForPUUtil.queryVerifyLocalMoneyByOrderID(hids);
      map.put(entry.getKey(), mny);
    }

    return map;
  }

}
