package nc.pubimpl.pu.m25.ia;

import nc.bs.pu.pub.PUIDQueryBuilder;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.page.IPage;
import nc.impl.pubapp.pattern.page.SecondaryPage;
import nc.impl.pubapp.pattern.page.db.IDDBPage;
import nc.pubift.pu.m25.ia.IInvoiceQueryForIAClosingCheck;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.parameter.ia.QueryParaVO;

/**
 * @ClassName:InvoiceQueryForIAClosingCheckImpl
 * @Description:为存货核算提供关账检查接口的实现类 包括两个实现方法，一个是存货核算未审批采购发票查询接口的实现，
 *                                一个是存货核算未结算采购发票查询接口的实现
 * @author liyjp
 * @date 2014-11-17 下午4:39:19
 */
public class InvoiceQueryForIAClosingCheckImpl implements
    IInvoiceQueryForIAClosingCheck {
  /**
   * @Description:存货核算未审批采购发票查询接口的实现方法
   *                                   表头开票日期在当前会计期间，财务组织=当前业务单元，自由态的采购发票
   * @param queryParaVO[] 查询参数VO
   *          包含pk_financeorg财务组织、startData会计期间开始日期、endpData会计期间结束日期
   * @throws BusinessException 业务异常
   */
  @Override
  public InvoiceHeaderVO[] queryUnapprovedInvoice(QueryParaVO queryParaVO)
      throws BusinessException {
  	
    if (queryParaVO == null) {
      return null;
    }
    String[] pk_financeorgs = queryParaVO.getPk_financeorgs(); // 财务组织
    UFDate startData = queryParaVO.getStartData(); // 会计期间开始日期
    UFDate endData = queryParaVO.getEndData(); // 会计期间结束日期

    /*
     * add by wandl 
     * 一次查询返回5000行，避免出现内存溢出
     */
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select pk_invoice from po_invoice ");
    sql.append(" where dr = 0 ");
    sql.append(" and ");
    sql.append(InvoiceHeaderVO.PK_ORG, pk_financeorgs);
    int [] status = new int[]{
    		POEnumBillStatus.FREE.toIntValue(),POEnumBillStatus.APPROVING.toIntValue(),
    		POEnumBillStatus.NOPASS.toIntValue()
    };
    sql.append(" and ");
    sql.append(InvoiceHeaderVO.FBILLSTATUS, status);
    if (startData != null) {
    	sql.append(" and dbilldate >= '" + startData);
      if (endData != null) {
      	sql.append("' and  dbilldate <= '" + endData);
      }
      else {
        sql.append("' and dbilldate <= '" + startData);
      }
      sql.append("'");
    }
    IDDBPage ds = new IDDBPage(sql.toString(),5000);
    IPage<String> page = new SecondaryPage<String>(ds, 5000);
    String[] ids = null;
    if(page.hasNext()){
    	ids = page.next(); 	
    }
    else{
    	return new InvoiceHeaderVO[0];
    }
    
    sql.reset();
    PUIDQueryBuilder builder = new PUIDQueryBuilder();
    sql.append(" and ");
    sql.append(builder.buildSQL(InvoiceHeaderVO.PK_INVOICE, ids));
    /*
     * end
     */
    VOQuery<InvoiceHeaderVO> voquery =
        new VOQuery<InvoiceHeaderVO>(InvoiceHeaderVO.class);
    InvoiceHeaderVO[] headerVOs = voquery.query(sql.toString(), null);
    return headerVOs;
  }

  /**
   * @Description:存货核算未结算采购发票查询接口的实现方法
   *                                   表头开票日期在当前会计期间，财务组织=当前业务单元，已审批未结算的采购发票
   * @param queryParaVO[] 查询参数VO
   *          包含pk_financeorg财务组织、startData会计期间开始日期、endpData会计期间结束日期
   * @throws BusinessException
   */
  @Override
  public InvoiceHeaderVO[] queryUnsettledInvoice(QueryParaVO queryParaVO)
      throws BusinessException {
    if (queryParaVO == null) {
      return null;
    }
    String[] pk_financeorgs = queryParaVO.getPk_financeorgs(); // 财务组织
    UFDate startData = queryParaVO.getStartData(); // 会计期间开始日期
    UFDate endData = queryParaVO.getEndData(); // 会计期间结束日期
    /*
     * add by wandl 
     * 一次查询返回5000行，避免出现内存溢出
     */
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select pk_invoice from po_invoice po_invoice ");
    sql.append(" where po_invoice.dr = 0 ");
    sql.append(" and ");
    sql.append(InvoiceHeaderVO.PK_ORG, pk_financeorgs);
    sql.append(" and ");
    sql.append(InvoiceHeaderVO.FBILLSTATUS, POEnumBillStatus.APPROVE.toIntValue());
    if (startData != null) {
    	sql.append(" and dbilldate >= '" + startData);
      if (endData != null) {
      	sql.append("' and  dbilldate <= '" + endData);
      }
      else {
        sql.append("' and dbilldate <= '" + startData);
      }
      sql.append("'");
    }
    sql.append(" and po_invoice.pk_invoice in ( ");
    sql.append(" select po_invoice_b.pk_invoice  ");
    sql.append(" from  po_invoice_b po_invoice_b, po_settlebill_b po_settlebill_b ");
    sql.append(" where po_invoice_b.pk_invoice_b = po_settlebill_b.pk_invoice_b ");
    sql.append(" and po_invoice_b.dr = 0 and po_settlebill_b.dr = 0 ");
    sql.append(" and po_invoice_b.nastnum - isnull(po_invoice_b.nreasonwastenum,0) > po_settlebill_b.nsettlenum ");
    sql.append(" ) ");
    /* sql.append(" and po_invoice.pk_invoice in ");
    sql.append(" (select po_invoice_b.pk_invoice from po_invoice_b po_invoice_b ");
    sql.append(" where po_invoice_b.dr = 0 ");
    sql.append(" and (po_invoice_b.naccumsettmny is null or po_invoice_b.naccumsettmny=0) ");
    sql.append(" and (po_invoice_b.naccumsettnum is null or po_invoice_b.naccumsettnum=0) ");
    sql.append(" ) ");*/
    IDDBPage ds = new IDDBPage(sql.toString(),5000);
    IPage<String> page = new SecondaryPage<String>(ds, 5000);
    String[] ids = null;
    if(page.hasNext()){
    	ids = page.next(); 	
    }
    else{
    	return new InvoiceHeaderVO[0];
    }

    sql.reset();
    PUIDQueryBuilder builder = new PUIDQueryBuilder();
    sql.append(" and ");
    sql.append(builder.buildSQL(InvoiceHeaderVO.PK_INVOICE, ids));
    /*
     * end
     */  
    VOQuery<InvoiceHeaderVO> voquery =
        new VOQuery<InvoiceHeaderVO>(InvoiceHeaderVO.class);
    InvoiceHeaderVO[] headerVOs = voquery.query(sql.toString(), null);
    return headerVOs;
  }

}
