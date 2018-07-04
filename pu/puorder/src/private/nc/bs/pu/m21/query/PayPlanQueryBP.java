package nc.bs.pu.m21.query;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pubapp.pattern.model.tool.BillComposite;

import org.apache.commons.lang.ArrayUtils;

/**
 * 付款计划查询BP
 * 
 * @since 6.0
 * @version 2011-1-7 上午08:17:50
 * @author wuxla
 */

public class PayPlanQueryBP {

  public AggPayPlanVO[] queryPayPlanVOs(String[] hids) {
    if (ArrayUtils.isEmpty(hids)) {
      return null;
    }

    VOQuery<OrderHeaderVO> headQuery =
        new VOQuery<OrderHeaderVO>(OrderHeaderVO.class);
    OrderHeaderVO[] heads = headQuery.query(hids);
    if (ArrayUtils.isEmpty(heads)) {
      return null;
    }

    VOQuery<PayPlanVO> payplanQuery = new VOQuery<PayPlanVO>(PayPlanVO.class);
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_28.name());
    String cond = builder.buildSQL(PayPlanVO.PK_ORDER, hids);
    PayPlanVO[] payplans = payplanQuery.query(" and " + cond, null);
    if (ArrayUtils.isEmpty(payplans)) {
      return null;
    }

    BillComposite<AggPayPlanVO> bc =
        new BillComposite<AggPayPlanVO>(AggPayPlanVO.class);
    AggPayPlanVO vo = new AggPayPlanVO();
    bc.append(vo.getMetaData().getParent(), heads);
    bc.append(vo.getMetaData().getVOMeta(PayPlanVO.class), payplans);
    AggPayPlanVO[] vos = bc.composite();
    // AroundProcesser<AggPayPlanVO> processer =
    // new AroundProcesser<AggPayPlanVO>(OrderPluginPoint.PAYPLAN_QUERY);
    // this.addRule(processer);
    // processer.after(vos);
    return vos;
  }

  // private void addRule(AroundProcesser<AggPayPlanVO> processer) {
  // }
}
