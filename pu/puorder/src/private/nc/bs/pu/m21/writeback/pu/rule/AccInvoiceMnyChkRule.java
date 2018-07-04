/**
 * $文件说明$
 *
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-22 下午09:39:06
 */
package nc.bs.pu.m21.writeback.pu.rule;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 采购订单累计开票金额检查，只对于费用行检查金额
 * 
 * @since 6.0
 * @version 2011-10-17 上午10:17:00
 * @author 田锋涛
 */
public class AccInvoiceMnyChkRule implements IRule<OrderViewVO> {

  @Override
  public void process(OrderViewVO[] vos) {
    for (OrderViewVO view : vos) {
      UFDouble feeMny = view.getNfeemny();
      // 认为是费用行
      if (!MathTool.isZero(feeMny)) {
      	 StringBuffer sql = new StringBuffer(" select isnull(sum(pib.norigtaxmny),0.0) from po_invoice_b pib where pib.dr = 0 and pib.cfirsttypecode = '21' ");
      	 sql.append(" and pib.cfirstbid='"+view.getPk_order_b()+"' ");
      	 DataAccessUtils utils = new DataAccessUtils();
      	 IRowSet rowset = utils.query(sql.toString());
      	 String[][] nsumfeemny = rowset.toTwoDimensionStringArray();
      	 if(nsumfeemny!=null&&nsumfeemny.length>0){
      		 feeMny = new UFDouble(nsumfeemny[0][0]);
      	 }else{
      		 feeMny = new UFDouble(0.0);
      	 }    	 
      	 
          //UFDouble taxMny = view.getNtaxmny();
          UFDouble taxMny = view.getNorigtaxmny();
        // 累计费用金额超本币价税合计
        if (MathTool.greaterThan(feeMny, taxMny)) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004030_0", "04004030-0350")/*
                                                                       * @res
                                                                       * "存在发票开票金额大于订单本币价税合计的费用行，请检查！"
                                                                       */);
        }

      }
    }
  }

}
