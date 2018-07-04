/**
 * $�ļ�˵��$
 *
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-22 ����09:39:06
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
 * �ɹ������ۼƿ�Ʊ����飬ֻ���ڷ����м����
 * 
 * @since 6.0
 * @version 2011-10-17 ����10:17:00
 * @author �����
 */
public class AccInvoiceMnyChkRule implements IRule<OrderViewVO> {

  @Override
  public void process(OrderViewVO[] vos) {
    for (OrderViewVO view : vos) {
      UFDouble feeMny = view.getNfeemny();
      // ��Ϊ�Ƿ�����
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
        // �ۼƷ��ý����Ҽ�˰�ϼ�
        if (MathTool.greaterThan(feeMny, taxMny)) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004030_0", "04004030-0350")/*
                                                                       * @res
                                                                       * "���ڷ�Ʊ��Ʊ�����ڶ������Ҽ�˰�ϼƵķ����У����飡"
                                                                       */);
        }

      }
    }
  }

}
