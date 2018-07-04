package nc.pubimpl.pu.m20.ecn.eco;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.mmf.busi.eco.IBill4ECAResult;
import nc.pubitf.mmf.busi.eco.IECAItemPara;
import nc.pubitf.mmf.busi.eco.MaterialAssFieldConst;
import nc.pubitf.pu.m20.ecn.eco.IQueryPrayBillForECA;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.enumeration.EnumBillStatue;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.CommonProcessForECA;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * ��ɢ���칤�̱������ʱ����ѯ�빺����Ϣ�ӿ�ʵ����
 * 
 * @since 6.3.1
 * @version 2013-8-13 ����03:53:45
 * @author fanly3
 */
public class QueryPrayBillForECAImpl implements IQueryPrayBillForECA {

  @Override
  public void qryECAResult(IECAItemPara[] paras, IBill4ECAResult temptable)
      throws BusinessException {
    try {
      CommonProcessForECA utils = new CommonProcessForECA(paras, temptable);
      // ��ʱ��
      String tempTableName =
          utils.constructParasTempTable(PUTempTable.tmp_po_20_15.name());
      String finalSql = this.constructFinalSql(tempTableName, utils, paras);
      DataAccessUtils dao = new DataAccessUtils();
      dao.update(finalSql);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * �����빺��select���
   * 
   * @return �빺��select���
   */
  private String constructSelectSql() {
    SqlBuilder selectSql = new SqlBuilder();
    selectSql.append("select '" + POBillType.PrayBill.getCode() + "'");
    selectSql.append(",po_praybill." + PraybillHeaderVO.PK_ORG);
    selectSql.append(",po_praybill." + PraybillHeaderVO.PK_ORG_V);
    selectSql.append(",po_praybill." + PraybillHeaderVO.PK_PRAYBILL);
    selectSql.append(",po_praybill." + PraybillHeaderVO.VBILLCODE);
    selectSql.append(",po_praybill_b." + PraybillItemVO.PK_PRAYBILL_B);
    selectSql.append(",po_praybill_b." + PraybillItemVO.CROWNO);
    selectSql.append(",po_praybill." + PraybillHeaderVO.DBILLDATE);
    // �������� = ��������
    selectSql.append(",po_praybill_b." + PraybillItemVO.DREQDATE);
    // �ֿ�
    selectSql.append(",null");
    selectSql.append(",po_praybill_b." + PraybillItemVO.PK_SRCMATERIAL);
    selectSql.append(",po_praybill_b." + PraybillItemVO.PK_MATERIAL);
    // ��Ӧ��
    selectSql.append(",null");
    selectSql.append(",po_praybill_b." + PraybillItemVO.CPRODUCTORID);
    selectSql.append(",po_praybill_b." + PraybillItemVO.CPROJECTID);
    selectSql.append(",po_praybill_b." + PraybillItemVO.CASSCUSTID);
    selectSql.append(",po_praybill_b." + PraybillItemVO.VFREE1);
    selectSql.append(",po_praybill_b." + PraybillItemVO.VFREE2);
    selectSql.append(",po_praybill_b." + PraybillItemVO.VFREE3);
    selectSql.append(",po_praybill_b." + PraybillItemVO.VFREE4);
    selectSql.append(",po_praybill_b." + PraybillItemVO.VFREE5);
    selectSql.append(",po_praybill_b." + PraybillItemVO.VFREE6);
    selectSql.append(",po_praybill_b." + PraybillItemVO.VFREE7);
    selectSql.append(",po_praybill_b." + PraybillItemVO.VFREE8);
    selectSql.append(",po_praybill_b." + PraybillItemVO.VFREE9);
    selectSql.append(",po_praybill_b." + PraybillItemVO.VFREE10);
    // ����BOMID
    selectSql.append(",null");
    // ��װBOMID
    selectSql.append(",null");
    selectSql.append(",po_praybill_b." + PraybillItemVO.CUNITID);
    selectSql.append(",po_praybill_b." + PraybillItemVO.CASTUNITID);
    selectSql.append(",po_praybill_b." + PraybillItemVO.VCHANGERATE);
    selectSql.append(",po_praybill_b." + PraybillItemVO.NASTNUM);
    selectSql.append(",po_praybill_b." + PraybillItemVO.NNUM);
    // δִ��������=������-�ۼƶ�������
    selectSql.append(",po_praybill_b." + PraybillItemVO.NNUM
        + "- isnull(po_praybill_b." + PraybillItemVO.NACCUMULATENUM + ",0)");
    // Ԥ��������
    selectSql.append(",null");
    return selectSql.toString();
  }

  /**
   * ����From��Where���
   * 
   * @param tempTableName ��������ʱ����
   * @param paras ECA�������
   * @return From��Where����SQL
   */
  private String constructFromWhereSql(String tempTableName,
      IECAItemPara[] paras) {
    SqlBuilder fromSql = new SqlBuilder();
    fromSql
        .append(" from po_praybill_b po_praybill_b inner join po_praybill po_praybill ");
    fromSql.append(" on po_praybill_b.pk_praybill = po_praybill.pk_praybill ");
    fromSql.append(" inner join " + tempTableName + " " + tempTableName);
    // �����֯��oid�飬���������ȷ�Ϲ�
    fromSql.append(" on " + tempTableName + "."
        + CommonProcessForECA.ANALYZEORG + " = po_praybill.pk_org ");

    SqlBuilder whereSql = new SqlBuilder();
    whereSql.append(" where po_praybill.dr", 0);
    whereSql.append(" and po_praybill_b.dr", 0);
    // ���°汾
    whereSql.append(" and po_praybill.bislatest", UFBoolean.TRUE);
    // �빺��δ�ر�
    whereSql.append(" and po_praybill.fbillstatus", "<>",
        EnumBillStatue.CLOSE.toInteger());
    // ��δ�ر�
    whereSql.append(" and po_praybill_b.browclose", UFBoolean.FALSE);
    // δִ��������0
    whereSql
        .append(" and (po_praybill_b.nnum - isnull(po_praybill_b.naccumulatenum,0)) > 0");
    // ��������
    whereSql.append(" and po_praybill_b.dreqdate between " + tempTableName
        + "." + CommonProcessForECA.ANALYZEDATEFROM + " and " + tempTableName
        + "." + CommonProcessForECA.ANALYZEDATETO);
    // ��������
    whereSql.append(" and po_praybill_b.pk_material = " + tempTableName + "."
        + CommonProcessForECA.CMATERIALVID);

    String assWhere = this.constructAssWhereSql(tempTableName, paras);
    if (assWhere != null && assWhere.length() > 0) {
      whereSql.append(" and (");
      whereSql.append(assWhere);
      whereSql.append(")");
    }

    SqlBuilder fromWhereSql = new SqlBuilder();
    fromWhereSql.append(fromSql.toString());
    fromWhereSql.append(whereSql.toString());
    return fromWhereSql.toString();
  }

  /**
   * �������������Ĳ�ѯ����SQL
   * 
   * @param tempTableName �ɹ�����½�����ʱ����
   * @param paras ECA�������
   * @return �����Ĳ�ѯ�빺����ϢSQL
   */
  private String constructQuerySql(String tempTableName, IECAItemPara[] paras) {
    SqlBuilder finalSql = new SqlBuilder();
    finalSql.append(this.constructSelectSql());
    finalSql.append(this.constructFromWhereSql(tempTableName, paras));
    return finalSql.toString();
  }

  /**
   * ��������SQL
   * 
   * @param tempTableName �ɹ�����½�����ʱ����
   * @param utils �ɹ���ECA�������������
   * @param paras ECA�������
   * @return ���յ�SQL
   */
  private String constructFinalSql(String tempTableName,
      CommonProcessForECA utils, IECAItemPara[] paras) {
    SqlBuilder finalSql = new SqlBuilder();
    finalSql.append(utils.constructInsertSql());
    finalSql.append(this.constructQuerySql(tempTableName, paras));
    return finalSql.toString();
  }

  /**
   * �����빺����������where����
   * 
   * @param tempTableName ��������ʱ����
   * @param paras ECA�������
   * @return ECA��ѯ�빺��ʱ���������Թ��ɵĴ���or�ؼ��ֵ�where����
   */
  private String constructAssWhereSql(String tempTableName, IECAItemPara[] paras) {
    SqlBuilder sql = new SqlBuilder();
    for (IECAItemPara para : paras) {
      String[] asslist = para.getAsslist();
      if (asslist != null && asslist.length == 1
          && CommonProcessForECA.CVENDORID.equals(asslist[0])) {
        continue;
      }
      if (asslist != null && asslist.length > 0) {
        sql.append("(");
        sql.append(this.constructAssPropSql(asslist, tempTableName));
        sql.append(") or ");
      }
    }
    String assWhereSql = sql.toString();
    if (assWhereSql != null && assWhereSql.length() > 0) {
      assWhereSql = assWhereSql.substring(0, assWhereSql.lastIndexOf("or"));
    }

    return assWhereSql;
  }

  /**
   * �����빺���ĸ�������SQL
   * 
   * @param asslist ���������ڲ�ѯ�ĸ��������ֶ�����
   * @param tempTableName ��������ʱ����
   * @return �������Թ��ɵ�where����
   */
  private String constructAssPropSql(String[] asslist, String tempTableName) {
    SqlBuilder sql = new SqlBuilder();
    for (String str : asslist) {
      // �ͻ�
      if (str.equals(MaterialAssFieldConst.getCcustomerid())) {
        sql.append(tempTableName + "." + CommonProcessForECA.CCUSTOMERID
            + " = " + "po_praybill_b." + PraybillItemVO.CASSCUSTID);
      }
      // ��������
      else if (str.equals(MaterialAssFieldConst.getCproductorid())) {
        sql.append(" and " + tempTableName + "."
            + CommonProcessForECA.CPRODUCTORID + " = " + "po_praybill_b."
            + PraybillItemVO.CPRODUCTORID);
      }
      // ��Ŀ
      else if (str.equals(MaterialAssFieldConst.getCprojectid())) {
        sql.append(" and " + tempTableName + "."
            + CommonProcessForECA.CPROJECTID + " = " + "po_praybill_b."
            + PraybillItemVO.CPROJECTID);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree1())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE1
            + " = " + "po_praybill_b." + PraybillItemVO.VFREE1);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree2())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE2
            + " = " + "po_praybill_b." + PraybillItemVO.VFREE2);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree3())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE3
            + " = " + "po_praybill_b." + PraybillItemVO.VFREE3);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree4())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE4
            + " = " + "po_praybill_b." + PraybillItemVO.VFREE4);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree5())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE5
            + " = " + "po_praybill_b." + PraybillItemVO.VFREE5);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree6())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE6
            + " = " + "po_praybill_b." + PraybillItemVO.VFREE6);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree7())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE7
            + " = " + "po_praybill_b." + PraybillItemVO.VFREE7);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree8())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE8
            + " = " + "po_praybill_b." + PraybillItemVO.VFREE8);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree9())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE9
            + " = " + "po_praybill_b." + PraybillItemVO.VFREE9);
      }
      else if (str.equals(MaterialAssFieldConst.getVfree10())) {
        sql.append(" and " + tempTableName + "." + CommonProcessForECA.VFREE10
            + " = " + "po_praybill_b." + PraybillItemVO.VFREE10);
      }
    }
    String sqlStr = sql.toString();
    if (sqlStr.startsWith(" and ")) {
      sqlStr = sqlStr.replaceFirst(" and ", "");
    }
    return sqlStr;
  }
}
