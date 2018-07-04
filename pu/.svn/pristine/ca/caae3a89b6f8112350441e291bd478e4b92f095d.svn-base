package nc.pubimpl.pu.m25.pu.settle.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.VORelationCalculate;
import nc.vo.pub.lang.UFDouble;

/**
 * @description
 *              虚拟发票VO联动计算，结算关心金额，以金额再次发起联动计算，以保证发票数据的完整性
 * @scene
 *        生成虚拟发票
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午3:16:52
 * @author zhangshqb
 */
public class VirtualVORelaCalcRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    VORelationCalculate vocalc = new VORelationCalculate();
    for (InvoiceVO invoiceVO : vos) {
      InvoiceHeaderVO head = invoiceVO.getParentVO();
      for (InvoiceItemVO item : invoiceVO.getChildrenVO()) {
        // 无发票结算时，会根据记成本金额反算本币无税金额。此时以本币无税金额驱动时，不再改变记成本金额和不可抵扣税额
        UFDouble ncalcostmny = item.getNcalcostmny();
        UFDouble nmny = item.getNmny();
        UFDouble nosubtax = ncalcostmny.sub(nmny);
        // wuxla v61
        vocalc.calculate(head, item, InvoiceItemVO.NMNY);
        item.setNcalcostmny(ncalcostmny);
        item.setNnosubtax(nosubtax);
      }
    }
  }

}
