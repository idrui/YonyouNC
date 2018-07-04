/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-1 ����06:03:10
 */
package nc.pubimpl.pu.m21.pu.m20;

import nc.pubitf.pu.m21.pu.m20.IOrderQueryFor20ToMM;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺��Ϊ���������ṩ��ѯʱ�����ɹ������Ĳ�ѯ���ʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-1 ����06:03:10
 */
public class OrderQueryFor20ToMMImpl implements IOrderQueryFor20ToMM {

  /**
   * ���෽����д
   * 
   * @see nc.pubitf.pu.m21.pu.m20.IOrderQueryFor20ToMM#getSqlFor23ToMM()
   */
  @Override
  public String getSqlFor23ToMM() {
    SqlBuilder sql = new SqlBuilder();
    sql
        .append("select ob.nnum, ob.csourcebid from po_order_b ob left outer join po_order o on ");
    sql.append("o.pk_order = ob.pk_order and o.dr = 0 and ");
    sql.append("o.forderstatus", (Integer) POEnumBillStatus.APPROVE.value());
    sql.append(" where ");
    sql.append("ob.csourcetypecode", POBillType.Arrive.getCode());
    sql.append(" and ");
    sql
        .append("ob.fisactive", "!=", (Integer) EnumActive.REVISEHISTORY
            .value());
    sql.append(" and ob.dr=0");

    return sql.toString();
  }

}
