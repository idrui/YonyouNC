package nc.bs.pu.m27.feesettle.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m27.feesettle.util.FeeSettleDataContext;
import nc.bs.pu.m27.feesettle.util.FeeSettlePrivateUtil;
import nc.bs.pu.m27.feesettle.util.FeeSettleQueryPara;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.costfactor.IFactorOrdByOrgMaterial;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.m45.PurchaseinFIFDVO;
import nc.vo.pu.est.entity.m50.VmiFIFDVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.m4201.entity.PurchaseinFIFeeVO;
import nc.vo.pu.m4202.entity.VmiFIFeeVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 * 查询费用暂估明细、查询结算费用分摊明细(把数据放到FeeSettleDataContext中去)
 * 1、回写费用暂估表(暂估费用物料第一次结算的结算单ID、行ID)、是否第一次费用结算
 * 2、费用暂估回冲、调差
 * 3、查询结算费用分摊明细
 * @scene
 * 费用结算
 * @param
 * bdosettle 结算，还是其他操作(取消结算、传IA、取消传IA)
 * bneedFeeDistributeDetail 是否需要查询结算分摊明细
 * datactx 数据上下文
 * 
 * @since 6.3
 * @version 2014-10-22 下午4:14:21
 * @author zhangshqb
 */
public class PrepareFeeSettleDataRule implements IRule<SettleBillVO> {

  // 结算，还是其他操作(取消结算、传IA、取消传IA)
  private boolean bdosettle;

  // 是否需要查询结算分摊明细
  private boolean bneedFeeDistributeDetail;

  // 数据上下文
  private FeeSettleDataContext datactx;

  // 是消耗汇总还是采购结算
  private boolean isVmi = false;

  public PrepareFeeSettleDataRule(boolean bdosettle,
      boolean bneedFeeDistributeDetail, FeeSettleDataContext datactx) {
    this.bdosettle = bdosettle;
    this.bneedFeeDistributeDetail = bneedFeeDistributeDetail;
    this.datactx = datactx;
  }

  public PrepareFeeSettleDataRule(boolean bdosettle,
      FeeSettleDataContext datactx) {
    this.bdosettle = bdosettle;
    this.datactx = datactx;
  }

  @Override
  public void process(SettleBillVO[] vos) {
    List<SettleBillVO> feevos = FeeSettlePrivateUtil.findNeedFeeSettleVO(vos);
    if (this.datactx == null || feevos == null || feevos.size() == 0) {
      return;
    }
    // 设置此次操作是采购还是消耗汇总结算
    this.initSettleBusitype(vos[0]);
    if (this.bneedFeeDistributeDetail) {
      // 缓存费用结算分摊明细
      this.queryFeeAllotDetails(vos);
    }
    // 费用暂估明细、费用暂估分摊明细
    if (this.bdosettle) {
      this.prepareForDoFeeSettle(feevos);
    }
    else {
      this.queryEstDataBySettleHid(feevos);
    }
  }

  private void fillupCostFactor(SettleFeeAllotDetailVO detail, String pk_org) {
    // 补充成本要素，暂时这么处理
    CostfactorViewVO[] costFactors = this.queryCostFactor(pk_org, new String[] {
      detail.getPk_srcmaterial()
    });
    if (!ArrayUtils.isEmpty(costFactors)) {
      detail.setCostfactorvo(costFactors[0]);
    }
  }

  private SqlBuilder getDosettleEstQrySql(String tempTname) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select bb.pk_stockps_fee,bbb.pk_stockps_fd ");
    sql.append(" from ");
    sql.append(this.getFIItemTable());
    sql.append(" b  inner join ");
    sql.append(this.getFeeEstTable());
    sql.append(" bb ");
    sql.append(" on b.pk_stockps_b = bb.pk_stockps_b ");// 关联费用暂估明细
    sql.append(" inner join ");
    sql.append(this.getFeeEstDistTable());
    sql.append(" bbb");// 关联费用暂估分摊明细
    sql.append(" on bb.pk_stockps_fee = bbb.pk_stockps_fee ");
    sql.append(" inner join " + tempTname + " t");
    sql.append(" on b.pk_stockps_b = t.id1");
    sql.append(" and bb.pk_srcfeematerial = t.id2");
    sql.append(" where b.dr = 0 and bb.dr =0 and bbb.dr = 0");
    // 暂估费用物料第一次结算的结算单ID为空
    sql.appendIDIsNull(" and bb.pk_firstsettle");
    sql.appendIDIsNull(" and bb.pk_firstsettle_b");
    return sql;
  }

  private SqlBuilder getEstQueryBySettleHidSql(String[] hids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select bb.pk_stockps_fee,bbb.pk_stockps_fd ");
    sql.append(" from ");
    sql.append(this.getFeeEstTable());
    sql.append(" bb inner join ");
    sql.append(this.getFeeEstDistTable());
    sql.append(" bbb");// 关联费用暂估分摊明细
    sql.append(" on bb.pk_stockps_fee = bbb.pk_stockps_fee ");
    sql.append(" where bb.dr = 0 and bbb.dr =0");
    sql.append(new IDExQueryBuilder(PUTempTable.tmp_po_27_01.name()).buildSQL(
        " and bb.pk_firstsettle", hids));
    return sql;
  }

  /**
   * 得到费用暂估分摊名称表名称（消耗汇总，还是采购入）
   * 
   * @return
   */
  private String getFeeEstDistTable() {
    if (this.isVmi) {
      return PUEntity.VMIFI_FD_TAB;
    }
    return PUEntity.PurchaseinFI_FD_TAB;
  }

  private VOQuery<? extends FeeEstDistVO> getFeeEstDistVOQuery() {
    if (this.isVmi) {
      return new VOQuery<VmiFIFDVO>(VmiFIFDVO.class);
    }
    return new VOQuery<PurchaseinFIFDVO>(PurchaseinFIFDVO.class);
  }

  /**
   * 得到费用暂估分摊明细表名称（消耗汇总，还是采购入）
   * 
   * @return
   */
  private String getFeeEstTable() {
    if (this.isVmi) {
      return PUEntity.VMIFI_Fee_TAB;
    }
    return PUEntity.PurchaseinFI_Fee_TAB;
  }

  private VOQuery<? extends FeeEstVO> getFeeEstVOQuery() {
    if (this.isVmi) {
      return new VOQuery<VmiFIFeeVO>(VmiFIFeeVO.class);
    }
    return new VOQuery<PurchaseinFIFeeVO>(PurchaseinFIFeeVO.class);
  }

  /**
   * 得到财务副本子表名称（消耗汇总，还是采购入）
   * 
   * @return
   */
  private String getFIItemTable() {
    if (this.isVmi) {
      return PUEntity.VMIFI_H_TAB;
    }
    return PUEntity.PurchaseinFI_B_TAB;
  }

  private void initSettleBusitype(SettleBillVO vo) {
    for (SettleBillItemVO item : vo.getChildrenVO()) {
      if (StringUtils.isNotBlank(item.getPk_voiconsume())) {
        // 因前台消耗汇总和采购结算是分开的，所以只要有一条是消耗汇总，即可判断此操作为消耗汇总结算，否则为采购结算
        this.isVmi = true;
        return;
      }
    }
  }

  private void prepareForDoFeeSettle(List<SettleBillVO> feevos) {
    if (feevos == null || feevos.size() == 0) {
      return;
    }
    // 说明是采购结算，已经查出数据，消耗汇总不用再查询
    if (null != this.datactx.getEstBbItems()) {
      return;
    }
    // 从结算单中抽取劳务类、折扣类发票对应的物料VID
    FeeSettleQueryPara[] queryParas =
        FeeSettlePrivateUtil.buildFeeSettleQueryPara(feevos);
    if (ArrayUtils.isEmpty(queryParas)) {
      return;
    }
    // 构造查询采购财务入的费用暂估明细临时表
    String tempTname = FeeSettlePrivateUtil.buldTempTable(queryParas);
    // 得到查询条件
    SqlBuilder sql = this.getDosettleEstQrySql(tempTname);
    IRowSet rowset = new DataAccessUtils().query(sql.toString());
    if (rowset == null || rowset.size() == 0) {
      return;
    }
    Set<String> bbidset = new HashSet<String>();
    Set<String> bbbidset = new HashSet<String>();
    while (rowset.next()) {
      bbidset.add(rowset.getString(0));
      bbbidset.add(rowset.getString(1));
    }
    if (bbidset.size() == 0 || bbbidset.size() == 0) {
      return;
    }
    // 费用暂估明细
    String[] bbids = bbidset.toArray(new String[0]);
    FeeEstVO[] bbItems = this.getFeeEstVOQuery().query(bbids);
    // 费用暂估分摊明细
    String[] bbbids = bbbidset.toArray(new String[0]);
    FeeEstDistVO[] bbbItems = this.getFeeEstDistVOQuery().query(bbbids);

    // 存储暂估数据
    this.datactx.setEstBbItems(bbItems);
    this.datactx.setEstBbbItems(bbbItems);
  }

  private CostfactorViewVO[] queryCostFactor(String pk_fiorg, String[] pk_mrls) {
    CostfactorViewVO[] totalFactors = null;
    try {
      IFactorOrdByOrgMaterial cfSrv =
          NCLocator.getInstance().lookup(IFactorOrdByOrgMaterial.class);
      totalFactors = cfSrv.queryFactorViewVOByOrgMaterial(pk_fiorg, pk_mrls);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return totalFactors;
  }

  private void queryEstDataBySettleHid(List<SettleBillVO> feevos) {
    // 根据暂估费用物料第一次结算 == 结算单ID来查询暂估数据
    if (feevos == null || feevos.size() == 0) {
      return;
    }
    // 说明是采购结算，已经查出数据，消耗汇总不用再查询
    if (null != this.datactx.getEstBbItems()) {
      return;
    }
    Set<String> settlehids = new HashSet<String>();
    for (SettleBillVO vo : feevos) {
      settlehids.add(vo.getParentVO().getPk_settlebill());
    }
    String[] hids = settlehids.toArray(new String[0]);
    // 得到查询条件
    SqlBuilder sql = this.getEstQueryBySettleHidSql(hids);
    IRowSet rowset = new DataAccessUtils().query(sql.toString());
    if (rowset == null || rowset.size() == 0) {
      return;
    }
    Set<String> bbidset = new HashSet<String>();
    Set<String> bbbidset = new HashSet<String>();
    while (rowset.next()) {
      bbidset.add(rowset.getString(0));
      bbbidset.add(rowset.getString(1));
    }
    if (bbidset.size() == 0 || bbbidset.size() == 0) {
      return;
    }
    // 费用暂估明细
    String[] bbids = bbidset.toArray(new String[0]);
    FeeEstVO[] bbItems = this.getFeeEstVOQuery().query(bbids);
    // 费用暂估分摊明细
    String[] bbbids = bbbidset.toArray(new String[0]);
    FeeEstDistVO[] bbbItems = this.getFeeEstDistVOQuery().query(bbbids);
    // 存储暂估数据
    this.datactx.setEstBbItems(bbItems);
    this.datactx.setEstBbbItems(bbbItems);
  }

  private void queryFeeAllotDetails(SettleBillVO[] vos) {
    if (vos == null || vos.length != 1) {
      return;
    }
    // 记录费用结算之入库单
    List<String> feesettlebids = new ArrayList<String>();
    for (SettleBillVO vo : vos) {
      SettleBillItemVO[] items = vo.getChildrenVO();
      for (SettleBillItemVO item : items) {
        feesettlebids.add(item.getPk_settlebill_b());
      }
    }
    if (feesettlebids.size() == 0) {
      return;
    }
    // 查询对应的费用分摊明细
    String[] querybids = feesettlebids.toArray(new String[0]);
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select b.pk_stock_b,bb.pk_settle_feeallot,b.pk_org");
    sql.append(" from po_settlebill_b b inner join po_settle_feeallot bb");
    sql.append(" on b.pk_settlebill_b=bb.pk_settlebill_b");
    sql.append(" where b.dr=0 and bb.dr=0 and ");
    sql.append(new IDExQueryBuilder(PUTempTable.tmp_po_27_02.name()).buildSQL(
        " bb.pk_settlebill_b", querybids));
    IRowSet rowset = new DataAccessUtils().query(sql.toString());
    // <分摊明细主键,入库单行主键>
    Map<String, String> bbid_stockbid = new HashMap<String, String>();
    Map<String, String> bbid_org = new HashMap<String, String>();
    while (rowset.next()) {
      String stockbid = rowset.getString(0);
      String bbid = rowset.getString(1);
      bbid_stockbid.put(bbid, stockbid);
      bbid_org.put(bbid, rowset.getString(2));
    }
    if (bbid_stockbid.size() == 0) {
      return;
    }
    String[] bbids = bbid_stockbid.keySet().toArray(new String[0]);
    Class<SettleFeeAllotDetailVO> c = SettleFeeAllotDetailVO.class;
    SettleFeeAllotDetailVO[] allotDetails = null;
    allotDetails = new VOQuery<SettleFeeAllotDetailVO>(c).query(bbids);

    MapList<String, SettleFeeAllotDetailVO> details = null;
    details = new MapList<String, SettleFeeAllotDetailVO>();
    for (SettleFeeAllotDetailVO detail : allotDetails) {
      String bbid = detail.getPk_settle_feeallot();
      String stockbid = bbid_stockbid.get(bbid);
      if (detail.getCostfactorvo() == null) {
        String pk_org = bbid_org.get(bbid);
        this.fillupCostFactor(detail, pk_org);
      }

      details.put(stockbid, detail);
    }
    this.datactx.setBeenSavedAllotDetails(details);
  }

}
