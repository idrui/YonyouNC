package nc.vo.pu.m20.rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.so.M30SOServices;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.SOBillType;

/**
 * 1：直运处理，销售直运安排处理
 * 销售订单交易类型 属性为 直运时，请购单 直运才变为 直运。
 * 2：其他都为 否。
 * 
 * @since 6.0
 * @version 2011-7-7 上午11:16:17
 * @author liuchx
 */
public class BDirectRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    for (PraybillVO bill : vos) {
      if (null == bill.getHVO().getBdirecttransit()) {
        bill.getHVO().setBdirecttransit(UFBoolean.FALSE);
      }
      String csourcetype = bill.getBVO()[0].getCsourcetypecode();

      if (!SOBillType.Order.getCode().equals(csourcetype)) {
        continue;
      }
      UFBoolean direct = null;
      String vsrctrantypecode = bill.getBVO()[0].getVsrctrantypecode();
      if (map.containsKey(vsrctrantypecode)) {
        direct = map.get(vsrctrantypecode);
      }
      else {
        direct = this.getDirecttype(vsrctrantypecode);
        map.put(vsrctrantypecode, direct);
      }

      if (UFBoolean.TRUE.equals(direct)) {
        this.setBdirecttransit(bill);
      }
    }
  }

  private UFBoolean getDirecttype(String vtrantypecode) {
    return M30SOServices.queryIsDirectPO(vtrantypecode);
  }

  private void setBdirecttransit(PraybillVO vo) {
    vo.getHVO().setBdirecttransit(UFBoolean.TRUE);
  }

}
