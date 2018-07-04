package nc.pubimpl.pu.m422x.invp;

import nc.pubitf.invp.plan.IReqResultForInvp;
import nc.pubitf.invp.plan.ReqResultForInvpVO;
import nc.pubitf.pu.m422x.invp.IStorereqQueryForInvp;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.enumeration.ReqTypesEnum;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 物资需求申请单作为库存计划的需求而提供的查询服务实现类
 * 
 * @since 6.0
 * @version 2010-12-14 下午06:21:00
 * @author duy
 */
public class StorereqQueryForInvpImpl implements IStorereqQueryForInvp {

  @Override
  public IReqResultForInvp getReq(String pk_org, String tempTable)
      throws BusinessException {
    try {
      // from部分
      StringBuilder from = new StringBuilder();
      from.append(" po_storereq_b b ");
      from.append(" inner join po_storereq h on h.pk_storereq = b.pk_storereq ");
      from.append(" inner join ");
      from.append(tempTable);
      from.append(" t on t.pk_material = b.pk_srcmaterial ");

      // where部分
      SqlBuilder where = new SqlBuilder();
      where.append(" h.dr = 0 and b.dr = 0 ");
      where.append(" and h.pk_org", pk_org);
      where.append(" and b.pk_org", pk_org);
      where.append(" and b.dreqdate >= t.dstart ");
      where.append(" and b.dreqdate <= t.dend ");
      where.append(" and b.bclose = 'N' ");
      // add by fanly3 2014-01-24
      // 若物资需求申请单参与库存计划时，则参与库存计划的数量，是物资需求申请单的主数量-累计出库主数量。
      where.append(" and b.nnum - isnull(b.naccuoutnum, 0) > 0 ");
      where.append(" and h." + StoreReqAppHeaderVO.FBILLSTATUS,
          POEnumBillStatus.APPROVE.toInt());
      // mengjian by 20150407
      where.append(" and h." + StoreReqAppHeaderVO.FREQTYPEFLAG,
          ReqTypesEnum.GROSS_REQUIREMENT);

      // sql封装类。
      ReqResultForInvpVO result = new ReqResultForInvpVO();
      result.setBillDate("h." + StoreReqAppHeaderVO.DBILLDATE);
      result.setCunitid("b." + StoreReqAppItemVO.CUNITID);
      result.setFrom(from.toString());
      result.setMaterialid("b." + StoreReqAppItemVO.PK_SRCMATERIAL);
      result.setMaterialvid("b." + StoreReqAppItemVO.PK_MATERIAL);
      result.setNallocnum("0");
      // add by fanly3 2014-01-24
      // 若物资需求申请单参与库存计划时，则参与库存计划的数量，是物资需求申请单的主数量-累计出库主数量。
      result.setNnum(" b.nnum - isnull(b.naccuoutnum, 0) ");
      result.setReqbid("b." + StoreReqAppItemVO.PK_STOREREQ_B);
      result.setReqCode("h." + StoreReqAppHeaderVO.VBILLCODE);
      result.setReqDate("b." + StoreReqAppItemVO.DREQDATE);
      result.setReqid("h." + StoreReqAppHeaderVO.PK_STOREREQ);
      result.setReqOrgid("h." + StoreReqAppHeaderVO.PK_ORG);
      result.setReqOrgvid("h." + StoreReqAppHeaderVO.PK_ORG_V);
      result.setReqRowno("b." + StoreReqAppItemVO.CROWNO);
      result.setReqTypecode(POBillType.MRBill.getCode());
      result.setReqTypeid(POBillType.MRBill.getCode());
      result.setWhere(where.toString());
      return result;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
