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
 * @Description:Ϊ��������ṩ���˼��ӿڵ�ʵ���� ��������ʵ�ַ�����һ���Ǵ������δ�����ɹ���Ʊ��ѯ�ӿڵ�ʵ�֣�
 *                                һ���Ǵ������δ����ɹ���Ʊ��ѯ�ӿڵ�ʵ��
 * @author liyjp
 * @date 2014-11-17 ����4:39:19
 */
public class InvoiceQueryForIAClosingCheckImpl implements
    IInvoiceQueryForIAClosingCheck {
  /**
   * @Description:�������δ�����ɹ���Ʊ��ѯ�ӿڵ�ʵ�ַ���
   *                                   ��ͷ��Ʊ�����ڵ�ǰ����ڼ䣬������֯=��ǰҵ��Ԫ������̬�Ĳɹ���Ʊ
   * @param queryParaVO[] ��ѯ����VO
   *          ����pk_financeorg������֯��startData����ڼ俪ʼ���ڡ�endpData����ڼ��������
   * @throws BusinessException ҵ���쳣
   */
  @Override
  public InvoiceHeaderVO[] queryUnapprovedInvoice(QueryParaVO queryParaVO)
      throws BusinessException {
  	
    if (queryParaVO == null) {
      return null;
    }
    String[] pk_financeorgs = queryParaVO.getPk_financeorgs(); // ������֯
    UFDate startData = queryParaVO.getStartData(); // ����ڼ俪ʼ����
    UFDate endData = queryParaVO.getEndData(); // ����ڼ��������

    /*
     * add by wandl 
     * һ�β�ѯ����5000�У���������ڴ����
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
   * @Description:�������δ����ɹ���Ʊ��ѯ�ӿڵ�ʵ�ַ���
   *                                   ��ͷ��Ʊ�����ڵ�ǰ����ڼ䣬������֯=��ǰҵ��Ԫ��������δ����Ĳɹ���Ʊ
   * @param queryParaVO[] ��ѯ����VO
   *          ����pk_financeorg������֯��startData����ڼ俪ʼ���ڡ�endpData����ڼ��������
   * @throws BusinessException
   */
  @Override
  public InvoiceHeaderVO[] queryUnsettledInvoice(QueryParaVO queryParaVO)
      throws BusinessException {
    if (queryParaVO == null) {
      return null;
    }
    String[] pk_financeorgs = queryParaVO.getPk_financeorgs(); // ������֯
    UFDate startData = queryParaVO.getStartData(); // ����ڼ俪ʼ����
    UFDate endData = queryParaVO.getEndData(); // ����ڼ��������
    /*
     * add by wandl 
     * һ�β�ѯ����5000�У���������ڴ����
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
