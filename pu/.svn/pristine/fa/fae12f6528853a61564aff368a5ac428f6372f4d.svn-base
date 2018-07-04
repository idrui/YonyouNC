package nc.bs.pu.m21.maintain.rule.delete;

import java.util.HashSet;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

/**
 * @description
 *              采购订单付款计划不全部删除
 * @scene
 *        采购订单付款计划删除
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午3:10:55
 * @author luojw
 */

public class PayPlanNotAllDelRule implements IRule<PayPlanVO> {

  @Override
  public void process(PayPlanVO[] vos) {
    Set<String> hidSet = new HashSet<String>();
    for (PayPlanVO vo : vos) {
      hidSet.add(vo.getPk_order());
    }

    String[] hids = hidSet.toArray(new String[hidSet.size()]);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select h." + OrderHeaderVO.VBILLCODE + " from ");
    sql.append(PUEntity.M21_H_TABLE + " h  left join "
        + PUEntity.M21_PAYPLAN_TABLE + " b ");
    sql.append(" on h." + OrderHeaderVO.PK_ORDER + " = b." + PayPlanVO.PK_ORDER);
    sql.append(" and b." + AbstractPayPlanVO.DR, 0);
    sql.append(" where ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_29.name());
    sql.append(builder.buildSQL(" h." + OrderHeaderVO.PK_ORDER, hids));
    sql.append(" group by h." + OrderHeaderVO.VBILLCODE);
    sql.append(" having count(b." + PayPlanVO.PK_ORDER_PAYPLAN + ") = 0");

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    if (rowset.size() > 0) {
      String[] codes = rowset.toOneDimensionStringArray();
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0229", null, codes)/*
                                                                 * 订单{0}不能删除全部付款计划
                                                                 */);
    }
  }
}
