/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-27 ����11:16:02
 */
package nc.vo.pu.m25.ref;

import nc.bs.arap.util.IArapBillTypeCons;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pf.change.PfUtilBaseTools;
import nc.vo.pu.m25.enumeration.InvoiceBillAction;
import nc.vo.pub.pf.IPFConfigInfo;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���Ʊ��UAP��̨���ߵ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-27 ����11:16:02
 */
public class InvoiceUapRefer {
  /**
   * ���������������жϵ�ǰ�������û��Ƿ������˲ɹ���Ʊ����->ȡ����Ӧ����������
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_busitype
   * @param operator
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-28 ����04:35:41
   */
  public static boolean hasCancelSendAPDriveAction(String pk_busitype,
      String operator) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select pk_sourcebilltype,pk_billtype,actiontype");
    sql.append(" from pub_messagedrive where ");
    sql.append("pk_sourcebusinesstype", pk_busitype);
    sql.append(" and ");
    sql.append("sourceaction", InvoiceBillAction.UNAPPROVE.name());
    sql.append(" and (");
    sql.append("configflag", IPFConfigInfo.UserNoRelation);// �Ͳ���Ա�޹�
    sql.append(" or (");
    sql.append("configflag", IPFConfigInfo.UserRelation);
    sql.append(" and ");
    sql.append("operator", operator);// �Ͳ���Ա�й�
    sql.append(") or (");
    sql.append("configflag", IPFConfigInfo.RoleRelation);// �ͽ�ɫ�й�,��Ҫ�����û��ڹ�˾�н�ɫ��ѯ
    sql.append(" and operator in(select pk_role from sm_user_role where ");
    sql.append("cuserid", operator);
    sql.append("))) ");
    // ���õ�����
    // sql.append("and exists (select 1 from bd_busitype where bd_busitype.pk_busitype = pub_messagedrive.pk_businesstype and validity  = "
    // + WorkflowDefStatusEnum.Valid.getIntValue() + ")");
    DataAccessUtils util = new DataAccessUtils();
    IRowSet result = util.query(sql.toString());
    if (null == result || 0 == result.size()) {
      return false;
    }
    while (result.next()) {
      String sourcetype = result.getString(0);
      String tartype = result.getString(1);
      String taraction = result.getString(2);
      if (!POBillType.Invoice.getCode().equals(
          PfUtilBaseTools.getRealBilltype(sourcetype))) {
        continue;
      }
      if (!POBillType.Invoice.getCode().equals(
          PfUtilBaseTools.getRealBilltype(tartype))) {
        continue;
      }
      if (InvoiceBillAction.CANCELSENDAP.name().equals(taraction)) {
        return true;
      }

    }
    return false;
  }

  /**
   * ����������������ǰ�������Ƿ������˲ɹ���Ʊ��Ӧ����������
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_busitype
   * @param operator
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-27 ����01:53:21
   */
  public static boolean hasSendAPDriveAction(String pk_busitype, String operator) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select pk_sourcebilltype,pk_billtype");
    sql.append(" from pub_messagedrive where ");
    sql.append("pk_sourcebusinesstype", pk_busitype);
    sql.append(" and ");
    sql.append("sourceaction", InvoiceBillAction.SENDAP.name());
    sql.append(" and (");
    sql.append("configflag", IPFConfigInfo.UserNoRelation);// �Ͳ���Ա�޹�
    sql.append(" or (");
    sql.append("configflag", IPFConfigInfo.UserRelation);
    sql.append(" and ");
    sql.append("operator", operator);// �Ͳ���Ա�й�
    sql.append(") or (");
    sql.append("configflag", IPFConfigInfo.RoleRelation);// �ͽ�ɫ�й�,��Ҫ�����û��ڹ�˾�н�ɫ��ѯ
    sql.append(" and operator in(select pk_role from sm_user_role where ");
    sql.append("cuserid", operator);
    sql.append("))) ");
    // ���õ�����
    // sql.append("and exists (select 1 from bd_busitype where bd_busitype.pk_busitype = pub_messagedrive.pk_businesstype and validity = "
    // + WorkflowDefStatusEnum.Valid.getIntValue() + ")");
    DataAccessUtils util = new DataAccessUtils();
    IRowSet result = util.query(sql.toString());
    if (null == result || 0 == result.size()) {
      return false;
    }
    while (result.next()) {
      String sourcetype = result.getString(0);
      String tartype = result.getString(1);
      if (!POBillType.Invoice.getCode().equals(
          PfUtilBaseTools.getRealBilltype(sourcetype))) {
        continue;
      }
      if (IArapBillTypeCons.F1.equals(PfUtilBaseTools.getRealBilltype(tartype))) {
        return true;
      }
    }
    return false;
  }

}
