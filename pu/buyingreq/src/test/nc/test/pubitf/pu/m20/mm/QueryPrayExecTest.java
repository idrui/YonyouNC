package nc.test.pubitf.pu.m20.mm;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m20.mm.IQueryPrayExec;
import nc.test.pu.mm.AbstractSupplyForMMTestCase;
import nc.vo.pu.mmpps.SupplyResultForCalcVO;

public class QueryPrayExecTest extends AbstractSupplyForMMTestCase {

  @Override
  public SupplyResultForCalcVO getSupplyResultVO() {
    try {
      IQueryPrayExec srv = NCLocator.getInstance().lookup(IQueryPrayExec.class);
      SupplyResultForCalcVO rst = (SupplyResultForCalcVO) srv.getSupplyResult();
      return rst;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void test() {
    System.out.println(this.getSupplySql());
  }

  // public void test() {
  // ISupplyResultForCalc supplyResultForCalc;
  // try {
  // supplyResultForCalc = new QueryPrayExecImpl().getSupplyResult();
  // StringBuilder stringBuilder = new StringBuilder();
  // stringBuilder.append(" select ");
  // stringBuilder.append(supplyResultForCalc.getCustomerid() + ",");
  // stringBuilder.append(supplyResultForCalc.getFree1() + ",");
  // stringBuilder.append(supplyResultForCalc.getFree10() + ",");
  // stringBuilder.append(supplyResultForCalc.getFree2() + ",");
  // stringBuilder.append(supplyResultForCalc.getFree3() + ",");
  // stringBuilder.append(supplyResultForCalc.getFree4() + ",");
  // stringBuilder.append(supplyResultForCalc.getFree5() + ",");
  // stringBuilder.append(supplyResultForCalc.getFree6() + ",");
  // stringBuilder.append(supplyResultForCalc.getFree7() + ",");
  // stringBuilder.append(supplyResultForCalc.getFree8() + ",");
  // stringBuilder.append(supplyResultForCalc.getFree9() + ",");
  // stringBuilder.append(supplyResultForCalc.getMaterialid() + ",");
  // stringBuilder.append(supplyResultForCalc.getMaterialvid() + ",");
  // stringBuilder.append(supplyResultForCalc.getNnum() + ",");
  // stringBuilder.append(supplyResultForCalc.getProductorid() + ",");
  // stringBuilder.append(supplyResultForCalc.getProjectid() + ",");
  // // stringBuilder.append(supplyResultForCalc.getReservatioNnum() + ",");
  // stringBuilder.append(supplyResultForCalc.getSupplybid() + ",");
  // stringBuilder.append(supplyResultForCalc.getSupplyCode() + ",");
  // stringBuilder.append(supplyResultForCalc.getSupplyDate() + ",");
  // stringBuilder.append(supplyResultForCalc.getSupplyid() + ",");
  // stringBuilder.append(supplyResultForCalc.getSupplyOrgid() + ",");
  // stringBuilder.append(supplyResultForCalc.getSupplyOrgvid() + ",");
  // stringBuilder.append(supplyResultForCalc.getSupplyRowNo());
  // // stringBuilder.append(supplyResultForCalc.getSupplyTypeCodeValue() +
  // // ",");
  // // stringBuilder.append(supplyResultForCalc.getSupplyTypeIdValue() + ",");
  // // stringBuilder.append(supplyResultForCalc.getVendorid());
  // stringBuilder.append(" from ");
  // stringBuilder.append(supplyResultForCalc.getFrom());
  // stringBuilder.append(" where ");
  // stringBuilder.append(supplyResultForCalc.getWhere());
  //
  // System.out.println(stringBuilder.toString());
  // }
  // catch (BusinessException e) {
  // ExceptionUtils.wrappException(e);
  // }
  //
  // }
}
