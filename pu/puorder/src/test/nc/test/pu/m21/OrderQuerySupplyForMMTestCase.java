package nc.test.pu.m21;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21.mm.IOrderQueryExecForMM;
import nc.test.pu.mm.AbstractSupplyForMMTestCase;
import nc.vo.pu.mmpps.SupplyResultForCalcVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 采购订单给mrp提供的供给查询测试
 * 
 * @since 6.0
 * @version 2011-12-21 上午11:42:37
 * @author tianft
 */
public class OrderQuerySupplyForMMTestCase extends AbstractSupplyForMMTestCase {

  @Override
  public SupplyResultForCalcVO getSupplyResultVO() {
    IOrderQueryExecForMM srv =
        NCLocator.getInstance().lookup(IOrderQueryExecForMM.class);
    SupplyResultForCalcVO rst = null;
    try {
      rst = (SupplyResultForCalcVO) srv.getSupplyResult(false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return rst;
  }

  public void test() {
    System.out.println(this.getSupplySql());
  }

}
