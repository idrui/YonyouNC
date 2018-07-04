package nc.pubimpl.pu.m20.invp;

import nc.pubitf.invp.plan.ISupplyResultForInvp;
import nc.pubitf.invp.plan.SupplyResultForInvpVO;
import nc.pubitf.pu.m20.invp.IBuyingreqQueryForInvp;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 请购单作为库存计划的供给而提供的查询服务实现类
 * 
 * @since 6.0
 * @version 2010-12-14 下午06:21:00
 * @author duy
 */
public class BuyingreqQueryForInvpImpl implements IBuyingreqQueryForInvp {

  @Override
  public ISupplyResultForInvp getSupply(String pk_org, String tempTable)
      throws BusinessException {
    try {
      // from部分。
      StringBuilder from = new StringBuilder();
      from.append(" po_praybill_b b ");
      from.append(" inner join po_praybill h on h.pk_praybill = b.pk_praybill ");
      from.append(" inner join ");
      from.append(tempTable);
      from.append(" t on t.pk_material = b.pk_srcmaterial ");

      // where部分。
      SqlBuilder where = new SqlBuilder();
      where.append(" h.dr = 0 and b.dr = 0 ");
      where.append(" and h.bislatest = 'Y' ");

      // 直运业务的采购订单、调拨订单、销售订单，不应该作为供给或需求单据进行计算。
      // 2013-5-28 席宏钰、高光荣、吴小亮
      where.append(" and h.bdirecttransit = 'N' ");

      where.append(" and h.pk_org", pk_org);
      where.append(" and b.pk_org", pk_org);
      where.append(" and b.dreqdate >= t.dstart ");
      where.append(" and b.dreqdate <= t.dend ");
      where.append(" and b.browclose = 'N' ");
      where.append(" and b.nnum - isnull(b.naccumulatenum, 0) > 0 ");

      // sql封装类。
      SupplyResultForInvpVO result = new SupplyResultForInvpVO();
      result.setBillDate("h." + PraybillHeaderVO.DBILLDATE);
      result.setCunitid("b." + PraybillItemVO.CUNITID);
      result.setFrom(from.toString());
      result.setMaterialid("b." + PraybillItemVO.PK_SRCMATERIAL);
      result.setMaterialvid("b." + PraybillItemVO.PK_MATERIAL);
      result.setNnum(" b.nnum - isnull(b.naccumulatenum, 0) ");
      result.setSupplybid("b." + PraybillItemVO.PK_PRAYBILL_B);
      result.setSupplyCode("h." + PraybillHeaderVO.VBILLCODE);
      result.setSupplyDate("b." + PraybillItemVO.DREQDATE);
      result.setSupplyid("h." + PraybillHeaderVO.PK_PRAYBILL);
      result.setSupplyOrgid("h." + PraybillHeaderVO.PK_ORG);
      result.setSupplyOrgvid("h." + PraybillHeaderVO.PK_ORG_V);
      result.setSupplyRowno("b." + PraybillItemVO.CROWNO);
      result.setSupplyTrantypecode("h." + PraybillHeaderVO.VTRANTYPECODE);
      result.setSupplyTrantypeid("h." + PraybillHeaderVO.CTRANTYPEID);
      result.setSupplyTypecode(POBillType.PrayBill.getCode());
      result.setSupplyTypeid(POBillType.PrayBill.getCode());
      result.setWhere(where.toString());
      return result;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
