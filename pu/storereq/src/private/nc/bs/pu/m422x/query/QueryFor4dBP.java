package nc.bs.pu.m422x.query;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.enumeration.PuRefBillTypeIdEnum;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������������ṩ�����ϳ��ⵥ�Ĳ�ѯBP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-15 ����11:43:54
 */
public class QueryFor4dBP extends Abstract422XRefQueryBP{
  
  public QueryFor4dBP(IQueryScheme queryScheme) {
    super(queryScheme);
  }

  @Override
  public StringBuilder makeGetPKSql() {
    String mainTableAlias = this.getHeadtb();
    String itemTableAlias = this.getItemtb();

    SqlBuilder partWhr = new SqlBuilder();
    partWhr.append(" and " + mainTableAlias + ".fbillstatus = "
        + POEnumBillStatus.APPROVE.value());
    partWhr.append(" and " + mainTableAlias + ".dr = 0");
    partWhr.append(" and " + mainTableAlias + ".pk_storereq = "
        + itemTableAlias + ".pk_storereq");
    partWhr.append(" and " + itemTableAlias + ".nnum > isnull("
        + itemTableAlias + ".naccuoutnum, 0)");
    partWhr.append(" and " + itemTableAlias + ".nnum is not null");
    partWhr.append(" and " + itemTableAlias + ".dr = 0");
    partWhr.append(" and isnull(" + itemTableAlias + ".bclose,'N') = 'N' ");
    // fanly3 add 2014-01-23 ���˵���Դ��ά�޼ƻ��������������뵥��
    partWhr.append(" and ");
    partWhr.append(itemTableAlias + ".csourcetypecode", "<>",
        PuRefBillTypeIdEnum.M4B32.getBillTypeId());
    
    // ���ƻ��ڹ�������
    FilterForPlanPosUtil.filterMaterialByPos(this.psor, partWhr);
    
    this.psor.appendWhere(partWhr.toString());

    StringBuilder wholeSql = new StringBuilder();
    wholeSql.append(" select distinct " + mainTableAlias + ".pk_storereq,");
    wholeSql.append(itemTableAlias + ".pk_storereq_b ");
    wholeSql.append(this.psor.getFinalFromWhere());

    return wholeSql;
  }

}
