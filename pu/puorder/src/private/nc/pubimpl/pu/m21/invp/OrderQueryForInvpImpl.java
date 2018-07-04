package nc.pubimpl.pu.m21.invp;

import nc.pubitf.invp.plan.ISupplyResultForInvp;
import nc.pubitf.invp.plan.SupplyResultForInvpVO;
import nc.pubitf.pu.m21.invp.IOrderQueryForInvp;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * �ɹ�������Ϊ���ƻ��Ĺ������ṩ�Ĳ�ѯ����ʵ����
 * 
 * @since 6.0
 * @version 2010-12-14 ����01:49:37
 * @author duy
 */
public class OrderQueryForInvpImpl implements IOrderQueryForInvp {

  private static final String numSql =
      " b.nnum - isnull(b.naccumstorenum, 0) - isnull(nsuprsnum, 0) ";

  @Override
  public ISupplyResultForInvp getSupply(String pk_org, String tempTable,
      boolean includeRed) throws BusinessException {
    try {
      // from����
      StringBuilder from = new StringBuilder();
      from.append(" po_order_b b ");
      from.append(" inner join po_order h on h.pk_order = b.pk_order ");
      from.append(" inner join ");
      from.append(tempTable);
      from.append(" t on t.pk_material = b.pk_srcmaterial ");

      // where����
      SqlBuilder where = new SqlBuilder();
      where.append(" h.dr = 0 and b.dr = 0 ");
      where.append(" and h.bislatest = 'Y' ");

      // ֱ��ҵ��Ĳɹ��������������������۶�������Ӧ����Ϊ���������󵥾ݽ��м��㡣
      // 2013-5-28 ϯ���ڡ��߹��١���С��

      where.append(" and h.bdirect='N' ");
      where.append(" and b.pk_arrvstoorg", pk_org);
      where.append(" and b.dplanarrvdate >= t.dstart ");
      where.append(" and b.dplanarrvdate <= t.dend ");
      where.append(" and b.bstockclose = 'N' ");

      if (includeRed) {
        where.append(" and ((b.nnum > 0 and ");
        where.append(OrderQueryForInvpImpl.numSql);
        where.append(" > 0) or (b.nnum < 0 and ");
        where.append(OrderQueryForInvpImpl.numSql);
        where.append(" < 0))");
      }
      else {
        where.append(" and ");
        where.append(OrderQueryForInvpImpl.numSql);
        where.append(" > 0 and h.breturn = 'N'");
      }

      SupplyResultForInvpVO result = new SupplyResultForInvpVO();
      result.setBillDate("h." + OrderHeaderVO.DBILLDATE);
      result.setCunitid("b." + OrderItemVO.CUNITID);
      result.setFrom(from.toString());
      result.setMaterialid("b." + OrderItemVO.PK_SRCMATERIAL);
      result.setMaterialvid("b." + OrderItemVO.PK_MATERIAL);
      result.setNnum(OrderQueryForInvpImpl.numSql);
      result.setSupplybid("b." + OrderItemVO.PK_ORDER_B);
      result.setSupplyCode("h." + OrderHeaderVO.VBILLCODE);
      result.setSupplyDate("b." + OrderItemVO.DPLANARRVDATE);
      result.setSupplyid("h." + OrderHeaderVO.PK_ORDER);
      result.setSupplyOrgid("b." + OrderItemVO.PK_ARRVSTOORG);
      result.setSupplyOrgvid("b." + OrderItemVO.PK_ARRVSTOORG_V);
      result.setSupplyRowno("b." + OrderItemVO.CROWNO);
      result.setSupplyTrantypecode("h." + OrderHeaderVO.VTRANTYPECODE);
      result.setSupplyTrantypeid("h." + OrderHeaderVO.CTRANTYPEID);
      result.setSupplyTypecode(POBillType.Order.getCode());
      result.setSupplyTypeid(POBillType.Order.getCode());
      result.setWhere(where.toString());
      return result;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
