/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 下午08:11:42
 */
package nc.pubimpl.pu.m21.mm;

import nc.bs.pu.m21.query.price.LatestPriceQueryBP;
import nc.pubitf.pu.m21.mm.IOrderQueryExecForMM;
import nc.pubitf.scmpub.scmpub.mmpps.calc.ISupplyResultForCalc;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pu.mmpps.SupplyResultForCalcVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>生成制造与排程：采购订单供给量
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-6 下午08:11:42
 */
public class OrderQueryExecForMMImpl implements IOrderQueryExecForMM {

  @Override
  public OrderPriceData[] getLatestPrice(String[] pk_arrvstoorgs, String[] moids)
      throws BusinessException {

    try {
      LatestPriceQueryBP latestPriceQueryBP = new LatestPriceQueryBP();
      return latestPriceQueryBP.queryForMM(pk_arrvstoorgs, moids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public ISupplyResultForCalc getSupplyResult(boolean includeRed)
      throws BusinessException {
    try {
      StringBuilder from = new StringBuilder();
      from.append(" po_order_b po_order_b inner join po_order po_order ");
      from.append(" on po_order.pk_order = po_order_b.pk_order ");

      StringBuilder where = new StringBuilder();
      where.append(" po_order.dr = 0 and po_order_b.dr = 0 ");
      where.append(" and po_order.bislatest = 'Y' ");// 最新版本
      where.append(" and po_order_b.bstockclose = 'N' ");// 未入库关闭

      if (includeRed) {
        where
            .append("and ( ( po_order_b.nnum > 0 "
                + "and po_order_b.nnum - isnull(po_order_b.naccumstorenum, 0) - isnull(po_order_b.nsuprsnum, 0) > 0 ) ");
        where
            .append("or ( po_order_b.nnum < 0 "
                + "and  po_order_b.nnum - isnull(po_order_b.naccumstorenum, 0) - isnull(po_order_b.nsuprsnum, 0) < 0 ) ");
        where.append(" )");
      }
      else {
        where
            .append("and po_order_b.nnum - isnull(po_order_b.naccumstorenum, 0) - isnull(po_order_b.nsuprsnum, 0) > 0 ");
        where.append("and po_order.breturn = 'N' ");
      }

      SupplyResultForCalcVO supplyResult = new SupplyResultForCalcVO();
      supplyResult.setFromSql(from.toString());
      supplyResult.setWhereSql(where.toString());
      supplyResult.setCustomerid("po_order_b.casscustid");// 客户
      supplyResult.setFree1("po_order_b.vfree1");
      supplyResult.setFree10("po_order_b.vfree10");
      supplyResult.setFree2("po_order_b.vfree2");
      supplyResult.setFree3("po_order_b.vfree3");
      supplyResult.setFree4("po_order_b.vfree4");
      supplyResult.setFree5("po_order_b.vfree5");
      supplyResult.setFree6("po_order_b.vfree6");
      supplyResult.setFree7("po_order_b.vfree7");
      supplyResult.setFree8("po_order_b.vfree8");
      supplyResult.setFree9("po_order_b.vfree9");
      supplyResult.setMaterialid("po_order_b.pk_srcmaterial");
      supplyResult.setMaterialvid("po_order_b.pk_material");
      supplyResult
          .setNnum("po_order_b.nnum - isnull(po_order_b.naccumstorenum, 0)"); // 供给数量[未执行量]
      supplyResult.setProductorid("po_order_b.cproductorid");
      supplyResult.setProjectid("po_order_b.cprojectid");
      supplyResult.setReservatioNnum("po_order_b.nsuprsnum");// 预留
      supplyResult.setSupplybid("po_order_b.pk_order_b");
      supplyResult.setSupplyCode("po_order.vbillcode");
      supplyResult.setSupplyDate("po_order_b.dplanarrvdate");// 计划到货日期
      supplyResult.setSupplyid("po_order.pk_order");
      supplyResult.setSupplyOrgid("po_order_b.pk_arrvstoorg");// 收货库存组织
      supplyResult.setSupplyOrgvid("po_order_b.pk_arrvstoorg_v");
      supplyResult.setSupplyRowNo("po_order_b.crowno");
      supplyResult.setSupplyTypeCodeValue(POBillType.Order.getCode());
      supplyResult.setSupplyTypeIdValue(POBillType.Order.getCode());
      supplyResult.setVendorid("po_order_b.pk_supplier");// 供应商
      // 来源单据行id
      supplyResult.setCsrcbid("po_order_b." + OrderItemVO.CSOURCEBID);
      // 来源单据id
      supplyResult.setCsrcid("po_order_b." + OrderItemVO.CSOURCEID);
      // 单据状态
      supplyResult.setFstatusflag("po_order." + OrderHeaderVO.FORDERSTATUS);
      // 单据状态枚举元数据id
      supplyResult.setFstatusflagEnumID(PUMDValue.OrderStatus.value());
      // 来源单据号
      supplyResult.setVsrccode("po_order_b." + OrderItemVO.VSOURCECODE);
      // 来源单据行号
      supplyResult.setVsrcrowno("po_order_b." + OrderItemVO.VSOURCEROWNO);
      // 来源单据类型
      supplyResult.setVsrctype("po_order_b." + OrderItemVO.CSOURCETYPECODE);
      // 源头单据
      supplyResult.setFirstId("po_order_b." + OrderItemVO.CFIRSTID);
      // 源头单据明细
      supplyResult.setFirstBid("po_order_b." + OrderItemVO.CFIRSTBID);
      // 源头单据号
      supplyResult.setFirstCode("po_order_b." + OrderItemVO.VFIRSTCODE);
      // 源头单据行号
      supplyResult.setFirstRowNo("po_order_b." + OrderItemVO.VFIRSTROWNO);
      // 源头单据类型
      supplyResult.setFirstType("po_order_b." + OrderItemVO.CFIRSTTYPECODE);

      return supplyResult;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
