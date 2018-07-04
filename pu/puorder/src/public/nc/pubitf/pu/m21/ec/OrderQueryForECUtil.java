package nc.pubitf.pu.m21.ec;

import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubift.pu.m25.ec.InvoicePublishedQueryCondVO;
import nc.pubift.pu.m25.ec.InvoiceSupplyDetailQueryCondVO;
import nc.pubitf.pu.m23.ec.ArrivePublishedQueryCondVO;
import nc.pubitf.pu.m23.ec.BackArriveQueryCondVO;
import nc.pubitf.pu.m23.ec.SupplyDetailQueryCondVO;
import nc.vo.bd.address.AddressVO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.org.DeptVO;
import nc.vo.org.OrgVO;
import nc.vo.pu.ec.QueryForECUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.vorg.PurchaseOrgVersionVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-5-17 ����04:41:00
 * @author wuxla
 */

public class OrderQueryForECUtil {

  public static String getApprovedArriveWherePart(
      QuerySchemeProcessor qrySchemeProcessor, ArrivePublishedQueryCondVO condVO) {
    String hname = qrySchemeProcessor.getMainTableAlias();
    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(QueryForECUtil.getPubWherePart(hname, condVO));
    // ��Ʒ����
    QueryCondition matnameCond = condVO.getMatnameCond();
    if (matnameCond != null) {
      String matAlias =
          qrySchemeProcessor
              .getTableAliasOfAttribute(ArriveItemVO.PK_ARRIVEORDER_B + "."
                  + ArriveItemVO.PK_MATERIAL + "." + MaterialVO.PK_ORG);
      wherePart.append(" and " + matAlias + "." + matnameCond.getFieldCode(),
          " " + matnameCond.getOperator() + " ",
          matnameCond.getValues()[0].toString());
    }
    // ��������
    QueryCondition arriveCodeCond = condVO.getArriveCodeCond();
    if (arriveCodeCond != null) {
      wherePart.append(" and " + hname + "." + ArriveHeaderVO.VBILLCODE, " "
          + arriveCodeCond.getOperator() + " ",
          arriveCodeCond.getValues()[0].toString());
    }
    // �ɹ���֯name
    QueryCondition purorgNameCond = condVO.getPurorgNameCond();
    if (purorgNameCond != null) {
      String puorgAlias =
          qrySchemeProcessor
              .getTableAliasOfAttribute(ArriveHeaderVO.PK_PURCHASEORG + "."
                  + PurchaseOrgVersionVO.PK_ORG);
      wherePart.append(
          " and " + puorgAlias + "." + purorgNameCond.getFieldCode(), " "
              + purorgNameCond.getOperator() + " ",
          purorgNameCond.getValues()[0].toString());
    }
    // �����֯pk
    QueryCondition pk_org = condVO.getPk_org();
    if (pk_org != null) {
      wherePart.append(OrderQueryForECUtil.getCondWherePart(qrySchemeProcessor,
          pk_org, OrderHeaderVO.PK_ORG));
    }
    // �ɹ�����
    QueryCondition departNameCond = condVO.getDeptCond();
    if (departNameCond != null) {
      wherePart.append(OrderQueryForECUtil.getCondWherePart(qrySchemeProcessor,
          departNameCond, ArriveHeaderVO.PK_DEPT));
    }
    // �ջ���
    QueryCondition recPsnCond = condVO.getRecPsnCond();
    if (recPsnCond != null) {
      wherePart.append(OrderQueryForECUtil.getCondWherePart(qrySchemeProcessor,
          recPsnCond, ArriveHeaderVO.PK_RECEIVEPSNDOC));
    }
    // �Ƿ��˻�
    QueryCondition backCond = condVO.getBackCond();
    if (backCond != null) {
      wherePart.append(OrderQueryForECUtil.getCondWherePart(qrySchemeProcessor,
          backCond, ArriveHeaderVO.BISBACK));
    }

    // ������
    QueryCondition orderCodeCond = condVO.getOrderCodeCond();
    if (orderCodeCond != null) {
      String bTableAlias =
          qrySchemeProcessor
              .getTableAliasOfAttribute("pk_arriveorder_b.pk_org");
      qrySchemeProcessor
          .appendFrom("inner join po_order on po_order.pk_order = "
              + bTableAlias + ".pk_order ");
      wherePart.append(" and po_order." + OrderHeaderVO.VBILLCODE, " "
          + orderCodeCond.getOperator() + " ",
          orderCodeCond.getValues()[0].toString());
    }
    return wherePart.toString();
  }

  public static String getApprovedInvoiceWherePart(
      QuerySchemeProcessor qrySchemeProcessor,
      InvoicePublishedQueryCondVO condVO) {
    String hname = qrySchemeProcessor.getMainTableAlias();
    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(QueryForECUtil.getPubWherePart(hname, condVO));
    // ��Ʊ��
    QueryCondition invoiceCodeCond = condVO.getInvoiceCodeCond();
    if (invoiceCodeCond != null) {
      wherePart.append(" and " + hname + "." + InvoiceHeaderVO.VBILLCODE, " "
          + invoiceCodeCond.getOperator() + " ",
          invoiceCodeCond.getValues()[0].toString());
    }
    String hecname = null;
    // ��Ʊ����
    QueryCondition arriveDateCond = condVO.getArriveDateCond();
    if (arriveDateCond != null) {
      hecname =
          qrySchemeProcessor
              .getTableAliasOfAttribute(InvoiceHeaderVO.DARRIVEDATE);
      wherePart.append(QueryForECUtil.getDateWherePart(hecname + "."
          + InvoiceHeaderVO.DARRIVEDATE, arriveDateCond));
    }
    // �ɹ���֯
    QueryCondition purorgNameCond = condVO.getPurorgNameCond();
    if (purorgNameCond != null) {
      wherePart.append(OrderQueryForECUtil.getPuorgNameWherePart(
          qrySchemeProcessor, purorgNameCond));
    }

    // ������֯
    QueryCondition pk_org = condVO.getPk_org();
    if (pk_org != null) {
      wherePart.append(OrderQueryForECUtil.getCondWherePart(qrySchemeProcessor,
          pk_org, InvoiceHeaderVO.PK_ORG));
    }
    // ������
    QueryCondition orderCodeCond = condVO.getOrderCodeCond();
    if (orderCodeCond != null) {
      qrySchemeProcessor
          .appendFrom(" inner join po_order po_order on po_invoice_b.pk_order = po_order.pk_order ");
      wherePart.append(" and po_order." + OrderHeaderVO.VBILLCODE, " "
          + orderCodeCond.getOperator() + " ",
          orderCodeCond.getValues()[0].toString());
    }
    return wherePart.toString();
  }

  public static String getApprovedWherePart(
      QuerySchemeProcessor qrySchemeProcessor, OrderApprovedQueryCondVO condVO) {
    String hname = qrySchemeProcessor.getMainTableAlias();
    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(QueryForECUtil.getPubWherePart(hname, condVO));
    OrderQueryForECUtil.appendHeadCloseWherePart(wherePart);
    // ������
    QueryCondition orderCodeCond = condVO.getOrderCodeCond();
    if (orderCodeCond != null) {
      wherePart.append(" and " + hname + "." + OrderHeaderVO.VBILLCODE, " "
          + orderCodeCond.getOperator() + " ",
          orderCodeCond.getValues()[0].toString());
    }
    String hecname = null;
    // ȷ������
    QueryCondition confirmDateCond = condVO.getConfirmDateCond();
    if (confirmDateCond != null) {
      hecname =
          qrySchemeProcessor.getTableAliasOfAttribute(OrderHeaderVO.TRESPTIME);
      wherePart.append(QueryForECUtil.getDateWherePart(hecname + "."
          + OrderHeaderVO.TRESPTIME, confirmDateCond));
    }
    // ȷ��״̬
    QueryCondition statusCond = condVO.getStatusCond();
    if (statusCond != null) {
      if (null == hecname) {
        hecname =
            qrySchemeProcessor
                .getTableAliasOfAttribute(OrderHeaderVO.IRESPSTATUS);
      }
      wherePart.append(" and " + hecname + "." + OrderHeaderVO.IRESPSTATUS, " "
          + statusCond.getOperator() + " ",
          statusCond.getValues()[0].toString());
    }
    // ��Ʒ����
    QueryCondition matnameCond = condVO.getMatnameCond();
    if (matnameCond != null) {
      // ֻ��ƴ������ʱ�Ų���壬��ʱ�żӱ���Ĺر�����
      OrderQueryForECUtil.appendBodyCloseWherePart(wherePart);
      wherePart.append(OrderQueryForECUtil.getMatNameWherePart(
          qrySchemeProcessor, matnameCond));
    }
    // �ɹ���֯
    QueryCondition purorgNameCond = condVO.getPurorgNameCond();
    if (purorgNameCond != null) {
      wherePart.append(OrderQueryForECUtil.getPuorgNameWherePart(
          qrySchemeProcessor, purorgNameCond));
    }
    return wherePart.toString();
  }

  public static String getBackArriveWherePart(
      QuerySchemeProcessor qrySchemeProcessor, BackArriveQueryCondVO condVO) {
    String hname = qrySchemeProcessor.getMainTableAlias();
    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(QueryForECUtil.getPubWherePart(hname, condVO));

    // �˻�����
    QueryCondition arriveCodeCond = condVO.getBillCodeCond();
    if (arriveCodeCond != null) {
      wherePart.append(" and " + hname + "." + ArriveHeaderVO.VBILLCODE, " "
          + arriveCodeCond.getOperator() + " ",
          arriveCodeCond.getValues()[0].toString());
    }
    // �ɹ���֯
    QueryCondition purorgNameCond = condVO.getPurorgNameCond();
    if (purorgNameCond != null) {
      String puorgAlias =
          qrySchemeProcessor
              .getTableAliasOfAttribute(ArriveHeaderVO.PK_PURCHASEORG + "."
                  + PurchaseOrgVersionVO.PK_ORG);
      wherePart.append(
          " and " + puorgAlias + "." + purorgNameCond.getFieldCode(), " "
              + purorgNameCond.getOperator() + " ",
          purorgNameCond.getValues()[0].toString());
    }

    // �����֯
    QueryCondition pk_org = condVO.getPk_org();
    if (pk_org != null) {
      wherePart.append(OrderQueryForECUtil.getCondWherePart(qrySchemeProcessor,
          pk_org, OrderHeaderVO.PK_ORG));
    }

    // ��Ӧ״̬
    QueryCondition respStatusCond = condVO.getRespStatusCond();
    if (respStatusCond != null) {
      String[] values = respStatusCond.getValues();
      if (values.length > 1) {
        Integer[] vis = new Integer[values.length];
        for (int i = 0; i < values.length; i++) {
          vis[i] = Integer.valueOf(values[i]);
        }
        wherePart.append(" and " + hname + "." + ArriveHeaderVO.IRESPSTATUS,
            vis);
      }
      else {
        wherePart.append(" and " + hname + "." + ArriveHeaderVO.IRESPSTATUS,
            " " + respStatusCond.getOperator() + " ", values[0]);
      }
    }
    // ��Ʒ����
    QueryCondition matnameCond = condVO.getMatnameCond();
    if (matnameCond != null) {
      String matAlias =
          qrySchemeProcessor
              .getTableAliasOfAttribute(ArriveItemVO.PK_ARRIVEORDER_B + "."
                  + ArriveItemVO.PK_MATERIAL + "." + MaterialVO.PK_ORG);
      wherePart.append(" and " + matAlias + "." + matnameCond.getFieldCode(),
          " " + matnameCond.getOperator() + " ",
          matnameCond.getValues()[0].toString());
    }

    return wherePart.toString();
  }

  public static String getExecDetailWherePart(
      QuerySchemeProcessor qrySchemeProcessor, OrderExecDetailQueryCondVO condVO) {
    String hname = qrySchemeProcessor.getMainTableAlias();
    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(QueryForECUtil.getPubWherePart(hname, condVO));

    // wuxla �� ȥ������͹ر�����
    // wherePart.append(" and po_order." + OrderHeaderVO.BFROZEN,
    // UFBoolean.FALSE);
    // OrderQueryForECUtil.appendHeadCloseWherePart(wherePart);
    // OrderQueryForECUtil.appendBodyCloseWherePart(wherePart);
    // ������
    QueryCondition orderCodeCond = condVO.getOrderCodeCond();
    if (orderCodeCond != null) {
      wherePart.append(" and " + hname + "." + OrderHeaderVO.VBILLCODE, " "
          + orderCodeCond.getOperator() + " ",
          orderCodeCond.getValues()[0].toString());
    }
    // ��ͬ��
    QueryCondition cntCodeCond = condVO.getCntCodeCond();
    if (cntCodeCond != null) {
      String bname =
          qrySchemeProcessor.getTableAliasOfAttribute(OrderItemVO.PK_ORDER_B
              + "." + OrderItemVO.PK_ORG);
      wherePart.append(" and " + bname + "." + OrderItemVO.VCONTRACTCODE, " "
          + cntCodeCond.getOperator() + " ",
          cntCodeCond.getValues()[0].toString());
    }
    // �ɹ���֯
    QueryCondition purorgNameCond = condVO.getPurorgNameCond();
    if (purorgNameCond != null) {
      wherePart.append(OrderQueryForECUtil.getPuorgNameWherePart(
          qrySchemeProcessor, purorgNameCond));
    }
    // ��Ʊ��Ӧ��
    QueryCondition invcsupllierNameCond = condVO.getInvcsupllierNameCond();
    if (invcsupllierNameCond != null) {
      String invcsupllierAlias =
          qrySchemeProcessor
              .getTableAliasOfAttribute(OrderHeaderVO.PK_INVCSUPLLIER + "."
                  + SupplierVO.PK_ORG);
      wherePart.append(
          " and " + invcsupllierAlias + "."
              + invcsupllierNameCond.getFieldCode(),
          " " + invcsupllierNameCond.getOperator() + " ",
          invcsupllierNameCond.getValues()[0].toString());
    }
    // ��Ʒ����
    QueryCondition matnameCond = condVO.getMatnameCond();
    if (matnameCond != null) {
      qrySchemeProcessor
          .appendFrom(" inner join bd_material bd_material on bd_material.pk_material = po_order_b.pk_material ");
      wherePart.append(" and bd_material." + matnameCond.getFieldCode(), " "
          + matnameCond.getOperator() + " ",
          matnameCond.getValues()[0].toString());
    }

    // mengjian by 20150124 ���������֯���ջ���֯���ջ��ֿ⡢�ջ���ַ���ɹ����š������š���Ʒ���ƣ��ı���ģ����ѯ����
    QueryCondition psfinanceorgCond = condVO.getPsfinanceorgCond();
    if (psfinanceorgCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          psfinanceorgCond, OrderItemVO.PK_ORDER_B + "."
              + OrderItemVO.PK_PSFINANCEORG, OrgVO.PK_ORG));
    }
    QueryCondition arrvstoorgCond = condVO.getArrvstoorgCond();
    if (arrvstoorgCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          arrvstoorgCond, OrderItemVO.PK_ORDER_B + "."
              + OrderItemVO.PK_ARRVSTOORG, OrgVO.PK_ORG));
    }
    QueryCondition recvstordocCond = condVO.getRecvstordocCond();
    if (recvstordocCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          recvstordocCond, OrderItemVO.PK_ORDER_B + "."
              + OrderItemVO.PK_RECVSTORDOC, StordocVO.PK_STORDOC));
    }
    QueryCondition receiveaddressCond = condVO.getReceiveaddressCond();
    if (receiveaddressCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          receiveaddressCond, OrderItemVO.PK_ORDER_B + "."
              + OrderItemVO.PK_RECEIVEADDRESS, AddressVO.PK_ADDRESS));
    }
    QueryCondition reqdeptCond = condVO.getReqdeptCond();
    if (reqdeptCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          reqdeptCond, OrderItemVO.PK_ORDER_B + "." + OrderItemVO.PK_REQDEPT,
          DeptVO.PK_DEPT));
    }
    QueryCondition deptCond = condVO.getDeptCond();
    if (deptCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          deptCond, OrderHeaderVO.PK_DEPT, DeptVO.PK_DEPT));
    }

    return wherePart.toString();
  }

  public static String getLinkQueryByCondWherePart(
      QuerySchemeProcessor qrySchemeProcessor, LinkQueryCondVO condVO) {
    String hname = qrySchemeProcessor.getMainTableAlias();
    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(QueryForECUtil.getPubWherePart(hname, condVO));

    // �ƻ���������
    QueryCondition planArriveDate = condVO.getPlanArriveDate();
    if (planArriveDate != null) {
      String bname =
          qrySchemeProcessor.getTableAliasOfAttribute("pk_order_b.pk_order_b");
      wherePart.append(QueryForECUtil.getDateWherePart(bname + "."
          + OrderItemVO.DPLANARRVDATE, planArriveDate));
    }
    // �ɹ���֯
    QueryCondition purchasePk = condVO.getPurchasePk();
    if (purchasePk != null) {
      String[] values = purchasePk.getValues();
      if (!ArrayUtils.isEmpty(values)) {
        wherePart.append(" and " + hname + "." + OrderHeaderVO.PK_ORG, values);
      }
    }
    // ����PK����
    QueryCondition materialPks = condVO.getMaterialPks();
    if (materialPks != null) {
      String[] values = materialPks.getValues();
      if (!ArrayUtils.isEmpty(values)) {
        String bname =
            qrySchemeProcessor
                .getTableAliasOfAttribute("pk_order_b.pk_order_b");
        wherePart.append(" and " + bname + "." + OrderItemVO.PK_MATERIAL,
            values);
      }
    }
    return wherePart.toString();
  }

  public static String getMatNameWherePart(
      QuerySchemeProcessor qrySchemeProcessor, QueryCondition matnameCond) {
    SqlBuilder wherePart = new SqlBuilder();
    String matAlias =
        qrySchemeProcessor.getTableAliasOfAttribute(OrderItemVO.PK_ORDER_B
            + "." + OrderItemVO.PK_MATERIAL + "." + MaterialVO.PK_ORG);
    wherePart.append(" and " + matAlias + "." + matnameCond.getFieldCode(), " "
        + matnameCond.getOperator() + " ",
        matnameCond.getValues()[0].toString());
    return wherePart.toString();
  }

  public static String getOnConfirmOrderQueryWherePart(
      QuerySchemeProcessor qrySchemeProcessor, OnConfirmOrderQueryCondVO condVO) {
    String hname = qrySchemeProcessor.getMainTableAlias();
    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(QueryForECUtil.getPubWherePart(hname, condVO));
    // wuxla �� ȥ���ر�����
    // OrderQueryForECUtil.appendHeadCloseWherePart(wherePart);

    // �ƻ���������
    QueryCondition planArriveDate = condVO.getPlanArriveDate();
    if (planArriveDate != null) {
      String bname =
          qrySchemeProcessor.getTableAliasOfAttribute(OrderItemVO.PK_ORDER_B);
      wherePart.append(QueryForECUtil.getDateWherePart(bname + "."
          + OrderItemVO.DPLANARRVDATE, planArriveDate));
    }

    // �ɹ���֯
    QueryCondition purchasePkCond = condVO.getPurchasePkCond();
    if (purchasePkCond != null) {
      String[] values = purchasePkCond.getValues();
      wherePart.append(" and " + hname + "." + OrderHeaderVO.PK_ORG, values);
    }

    return wherePart.toString();
  }

  public static String getPubedWherePart(
      QuerySchemeProcessor qrySchemeProcessor, OrderPublishedQueryCondVO condVO) {
    String hname = qrySchemeProcessor.getMainTableAlias();
    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(QueryForECUtil.getPubWherePart(hname, condVO));
    // wuxla ֣���ס���ΰ��ȥ���ر�����
    // OrderQueryForECUtil.appendHeadCloseWherePart(wherePart);

    // ������
    QueryCondition orderCodeCond = condVO.getOrderCodeCond();
    if (orderCodeCond != null) {
      wherePart.append(" and " + hname + "." + OrderHeaderVO.VBILLCODE, " "
          + orderCodeCond.getOperator() + " ",
          orderCodeCond.getValues()[0].toString());
    }

    String hecname = null;
    // ȷ������
    QueryCondition confirmDateCond = condVO.getConfirmDateCond();
    if (confirmDateCond != null) {
      hecname =
          qrySchemeProcessor.getTableAliasOfAttribute(OrderHeaderVO.TRESPTIME);
      wherePart.append(QueryForECUtil.getDateWherePart(hecname + "."
          + OrderHeaderVO.TRESPTIME, confirmDateCond));
    }
    // ȷ��״̬
    QueryCondition statusCond = condVO.getStatusCond();
    if (statusCond != null) {
      if (null == hecname) {
        hecname =
            qrySchemeProcessor
                .getTableAliasOfAttribute(OrderHeaderVO.IRESPSTATUS);
      }
      wherePart.append(" and " + hecname + "." + OrderHeaderVO.IRESPSTATUS, " "
          + statusCond.getOperator() + " ",
          statusCond.getValues()[0].toString());
    }
    // ��Ʒ����
    QueryCondition matnameCond = condVO.getMatnameCond();
    if (matnameCond != null) {
      // ֻ��ƴ������ʱ�Ų���壬��ʱ�żӱ���Ĺر�����
      OrderQueryForECUtil.appendBodyCloseWherePart(wherePart);
      wherePart.append(OrderQueryForECUtil.getMatNameWherePart(
          qrySchemeProcessor, matnameCond));
    }
    // �ɹ���֯
    QueryCondition purorgNameCond = condVO.getPurorgNameCond();
    if (purorgNameCond != null) {
      wherePart.append(OrderQueryForECUtil.getPuorgNameWherePart(
          qrySchemeProcessor, purorgNameCond));
    }

    // mengjian by 20150124 ���������֯���ջ���֯���ջ��ֿ⡢�ջ���ַ���ɹ����š������š���Ʒ���ƣ��ı���ģ����ѯ����
    QueryCondition psfinanceorgCond = condVO.getPsfinanceorgCond();
    if (psfinanceorgCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          psfinanceorgCond, OrderItemVO.PK_ORDER_B + "."
              + OrderItemVO.PK_PSFINANCEORG, OrgVO.PK_ORG));
    }
    QueryCondition arrvstoorgCond = condVO.getArrvstoorgCond();
    if (arrvstoorgCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          arrvstoorgCond, OrderItemVO.PK_ORDER_B + "."
              + OrderItemVO.PK_ARRVSTOORG, OrgVO.PK_ORG));
    }
    QueryCondition recvstordocCond = condVO.getRecvstordocCond();
    if (recvstordocCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          recvstordocCond, OrderItemVO.PK_ORDER_B + "."
              + OrderItemVO.PK_RECVSTORDOC, StordocVO.PK_STORDOC));
    }
    QueryCondition receiveaddressCond = condVO.getReceiveaddressCond();
    if (receiveaddressCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          receiveaddressCond, OrderItemVO.PK_ORDER_B + "."
              + OrderItemVO.PK_RECEIVEADDRESS, AddressVO.PK_ADDRESS));
    }
    QueryCondition reqdeptCond = condVO.getReqdeptCond();
    if (reqdeptCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          reqdeptCond, OrderItemVO.PK_ORDER_B + "." + OrderItemVO.PK_REQDEPT,
          DeptVO.PK_DEPT));
    }
    QueryCondition deptCond = condVO.getDeptCond();
    if (deptCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          deptCond, OrderHeaderVO.PK_DEPT, DeptVO.PK_DEPT));
    }
    return wherePart.toString();
  }

  public static String getPuorgNameWherePart(
      QuerySchemeProcessor qrySchemeProcessor, QueryCondition purorgNameCond) {
    SqlBuilder wherePart = new SqlBuilder();
    String puorgAlias =
        qrySchemeProcessor.getTableAliasOfAttribute(OrderHeaderVO.PK_ORG + "."
            + OrgVO.PK_ORG);
    wherePart.append(
        " and " + puorgAlias + "." + purorgNameCond.getFieldCode(), " "
            + purorgNameCond.getOperator() + " ",
        purorgNameCond.getValues()[0].toString());
    return wherePart.toString();
  }

  public static String getRPSCMMaintainWherePart(
      QuerySchemeProcessor qrySchemeProcessor,
      OrderReceivePlanPubedQueryCondVO condVO) {
    String hname = qrySchemeProcessor.getMainTableAlias();
    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(QueryForECUtil.getPubWherePart(hname, condVO));
    OrderQueryForECUtil.appendHeadCloseWherePart(wherePart);
    OrderQueryForECUtil.appendBodyCloseWherePart(wherePart);

    wherePart.append(" and po_order." + OrderHeaderVO.BFROZEN, UFBoolean.FALSE);
    wherePart.append(" and " + hname + "." + OrderHeaderVO.FORDERSTATUS,
        POEnumBillStatus.APPROVE.toInt());
    String bb1Alias =
        qrySchemeProcessor
            .getTableAliasOfAttribute("pk_order_b.pk_order_bb1.pk_org");
    wherePart.append(" and " + bb1Alias + "." + OrderReceivePlanVO.DR, 0);
    // ������
    QueryCondition orderCodeCond = condVO.getOrderCodeCond();
    if (orderCodeCond != null) {
      wherePart.append(" and " + hname + "." + OrderHeaderVO.VBILLCODE, " "
          + orderCodeCond.getOperator() + " ",
          orderCodeCond.getValues()[0].toString());
    }

    SqlBuilder fromPart = new SqlBuilder();
    fromPart.append(" inner join po_potrantype po_potrantype on ");
    fromPart.append(hname + ".vtrantypecode = po_potrantype.vtrantypecode");
    wherePart.append(" and po_potrantype." + PoTransTypeVO.BRECEIVEPLAN,
        UFBoolean.TRUE);

    qrySchemeProcessor.appendFrom(fromPart.toString());
    // ��Ʒ����
    QueryCondition matnameCond = condVO.getMatnameCond();
    if (matnameCond != null) {
      String matAlias =
          qrySchemeProcessor.getTableAliasOfAttribute(OrderItemVO.PK_ORDER_B
              + "." + OrderReceivePlanVO.PK_ORDER_BB1 + "."
              + OrderItemVO.PK_MATERIAL + "." + MaterialVO.PK_ORG);
      wherePart.append(" and " + matAlias + "." + matnameCond.getFieldCode(),
          " " + matnameCond.getOperator() + " ",
          matnameCond.getValues()[0].toString());
    }

    QueryCondition planArrDateCond = condVO.getPlanArrDateCond();
    if (planArrDateCond != null) {
      wherePart.append(QueryForECUtil.getDateWherePart(bb1Alias + "."
          + OrderReceivePlanVO.DPLANARRVDATE, planArrDateCond));
    }

    QueryCondition sendDate = condVO.getSendDate();
    if (sendDate != null) {
      wherePart.append(QueryForECUtil.getDateWherePart(bb1Alias + "."
          + OrderReceivePlanVO.DSENDDATE, sendDate));
    }

    // �ɹ���֯
    QueryCondition pk_org = condVO.getPk_org();
    if (pk_org != null) {
      // wherePart.append(OrderQueryForECUtil.getCondWherePart(qrySchemeProcessor,
      // pk_org, OrderHeaderVO.PK_ORG));
      wherePart.append(" and " + hname + "." + OrderHeaderVO.PK_ORG,
          pk_org.getValues());

    }
    // mengjian by 20150124 ���������֯���ջ���֯���ջ��ֿ⡢�ջ���ַ���ɹ����š������š���Ʒ���ƣ��ı���ģ����ѯ����
    QueryCondition psfinanceorgCond = condVO.getPsfinanceorgCond();
    if (psfinanceorgCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          psfinanceorgCond, OrderItemVO.PK_ORDER_B + "."
              + OrderItemVO.PK_PSFINANCEORG, OrgVO.PK_ORG));
    }
    QueryCondition arrvstoorgCond = condVO.getArrvstoorgCond();
    if (arrvstoorgCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          arrvstoorgCond, OrderItemVO.PK_ORDER_B + "."
              + OrderItemVO.PK_ARRVSTOORG, OrgVO.PK_ORG));
    }
    QueryCondition recvstordocCond = condVO.getRecvstordocCond();
    if (recvstordocCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          recvstordocCond, OrderItemVO.PK_ORDER_B + "."
              + OrderItemVO.PK_RECVSTORDOC, StordocVO.PK_STORDOC));
    }
    QueryCondition receiveaddressCond = condVO.getReceiveaddressCond();
    if (receiveaddressCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          receiveaddressCond, OrderItemVO.PK_ORDER_B + "."
              + OrderItemVO.PK_RECEIVEADDRESS, AddressVO.PK_ADDRESS));
    }
    QueryCondition reqdeptCond = condVO.getReqdeptCond();
    if (reqdeptCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          reqdeptCond, OrderItemVO.PK_ORDER_B + "." + OrderItemVO.PK_REQDEPT,
          DeptVO.PK_DEPT));
    }
    QueryCondition deptCond = condVO.getDeptCond();
    if (deptCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          deptCond, OrderHeaderVO.PK_DEPT, DeptVO.PK_DEPT));
    }
    return wherePart.toString();
  }

  public static String getRPSupplierMaintainWherePart(
      QuerySchemeProcessor qrySchemeProcessor,
      OrderReceivePlanQueryCondVO condVO) {
    String hname = qrySchemeProcessor.getMainTableAlias();
    SqlBuilder wherePart = new SqlBuilder();
    OrderQueryForECUtil.appendHeadCloseWherePart(wherePart);
    OrderQueryForECUtil.appendBodyCloseWherePart(wherePart);
    wherePart.append(QueryForECUtil.getPubWherePart(hname, condVO));
    wherePart.append(" and po_order." + OrderHeaderVO.BFROZEN, UFBoolean.FALSE);
    wherePart.append(" and " + hname + "." + OrderHeaderVO.FORDERSTATUS,
        POEnumBillStatus.APPROVE.toInt());
    qrySchemeProcessor.getTableAliasOfAttribute("pk_order_b.pk_org");
    // ������
    QueryCondition orderCodeCond = condVO.getOrderCodeCond();
    if (orderCodeCond != null) {
      wherePart.append(" and " + hname + "." + OrderHeaderVO.VBILLCODE, " "
          + orderCodeCond.getOperator() + " ",
          orderCodeCond.getValues()[0].toString());
    }

    SqlBuilder fromPart = new SqlBuilder();
    fromPart.append(" inner join po_potrantype po_potrantype on ");
    fromPart.append(hname + ".ctrantypeid = po_potrantype.ctrantypeid");
    wherePart.append(" and po_potrantype." + PoTransTypeVO.BRECEIVEPLAN,
        UFBoolean.TRUE);
    qrySchemeProcessor.appendFrom(fromPart.toString());

    // ��Ʒ����
    QueryCondition matnameCond = condVO.getMatnameCond();
    String matAlias =
        qrySchemeProcessor.getTableAliasOfAttribute(OrderItemVO.PK_ORDER_B
            + "." + OrderItemVO.PK_MATERIAL + "." + MaterialVO.PK_ORG);
    wherePart.append(
        " and isnull(" + matAlias + "." + MaterialVO.FEE + ",'N')",
        UFBoolean.FALSE);
    wherePart.append(" and isnull(" + matAlias + "." + MaterialVO.DISCOUNTFLAG
        + ",'N')", UFBoolean.FALSE);
    if (matnameCond != null) {
      wherePart.append(" and " + matAlias + "." + matnameCond.getFieldCode(),
          " " + matnameCond.getOperator() + " ",
          matnameCond.getValues()[0].toString());
    }

    // �ɹ���֯
    QueryCondition pk_org = condVO.getPk_org();
    if (pk_org != null) {
      wherePart.append(" and " + hname + "." + OrderHeaderVO.PK_ORG,
          pk_org.getValues());
    }
    // mengjian by 20150124 ���������֯���ջ���֯���ջ��ֿ⡢�ջ���ַ���ɹ����š������š���Ʒ���ƣ��ı���ģ����ѯ����
    QueryCondition psfinanceorgCond = condVO.getPsfinanceorgCond();
    if (psfinanceorgCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          psfinanceorgCond, OrderItemVO.PK_ORDER_B + "."
              + OrderItemVO.PK_PSFINANCEORG, OrgVO.PK_ORG));
    }
    QueryCondition arrvstoorgCond = condVO.getArrvstoorgCond();
    if (arrvstoorgCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          arrvstoorgCond, OrderItemVO.PK_ORDER_B + "."
              + OrderItemVO.PK_ARRVSTOORG, OrgVO.PK_ORG));
    }
    QueryCondition recvstordocCond = condVO.getRecvstordocCond();
    if (recvstordocCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          recvstordocCond, OrderItemVO.PK_ORDER_B + "."
              + OrderItemVO.PK_RECVSTORDOC, StordocVO.PK_STORDOC));
    }
    QueryCondition receiveaddressCond = condVO.getReceiveaddressCond();
    if (receiveaddressCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          receiveaddressCond, OrderItemVO.PK_ORDER_B + "."
              + OrderItemVO.PK_RECEIVEADDRESS, AddressVO.PK_ADDRESS));
    }
    QueryCondition reqdeptCond = condVO.getReqdeptCond();
    if (reqdeptCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          reqdeptCond, OrderItemVO.PK_ORDER_B + "." + OrderItemVO.PK_REQDEPT,
          DeptVO.PK_DEPT));
    }
    QueryCondition deptCond = condVO.getDeptCond();
    if (deptCond != null) {
      wherePart.append(OrderQueryForECUtil.getNameWherePart(qrySchemeProcessor,
          deptCond, OrderHeaderVO.PK_DEPT, DeptVO.PK_DEPT));
    }
    return wherePart.toString();
  }

  public static String getSupplyDetailQueryWherePart(
      QuerySchemeProcessor qrySchemeProcessor,
      InvoiceSupplyDetailQueryCondVO condVO) {
    String hname = qrySchemeProcessor.getMainTableAlias();
    SqlBuilder wherePart = new SqlBuilder();
    // ��Ӧ��,��������
    wherePart.append(QueryForECUtil.getPubWherePart(hname, condVO));
    // ��������
    QueryCondition arriveDateCond = condVO.getArriveDateCond();
    // �˻�����
    QueryCondition backarriveDateCond = condVO.getBackArriveDateCond();
    // ��Ʊ����
    QueryCondition invoiceDateCond = condVO.getInvoiceDateCond();
    // �������
    QueryCondition inDateCond = condVO.getInDateCond();
    // �˿�����
    QueryCondition outDateCond = condVO.getOutDateCond();
    if (invoiceDateCond != null || inDateCond != null || outDateCond != null) {
      qrySchemeProcessor
          .appendFrom(" inner join po_order_b on po_order_b.pk_order_b=po_invoice_b.pk_order_b ");

      if (arriveDateCond != null || backarriveDateCond != null) {
        qrySchemeProcessor
            .appendFrom(" inner join po_arriveorder_b on po_arriveorder_b.pk_order_b=po_order_b.pk_order_b ");

        if (arriveDateCond != null) {
          wherePart.append(QueryForECUtil.getDateWherePart(" po_arriveorder."
              + ArriveHeaderVO.BISBACK + "='N' and po_arriveorder."
              + ArriveHeaderVO.DBILLDATE, arriveDateCond));
        }
        if (backarriveDateCond != null) {
          wherePart.append(QueryForECUtil.getDateWherePart(" po_arriveorder."
              + ArriveHeaderVO.BISBACK + "='Y' and po_arriveorder."
              + ArriveHeaderVO.DBILLDATE, backarriveDateCond));
        }
      }
      if (invoiceDateCond != null) {
        wherePart.append(QueryForECUtil.getDateWherePart(" po_invoice."
            + InvoiceHeaderVO.DBILLDATE, invoiceDateCond));
      }

      if (inDateCond != null) {
        qrySchemeProcessor
            .appendFrom(" inner join ic_purchasein_b on ic_purchasein_b.cfirstbillbid=po_order_b.pk_order_b inner join ic_purchasein_h on ic_purchasein_h.cgeneralhid=ic_purchasein_b.cgeneralhid and ic_purchasein_b.csourcetype='21'");
        wherePart.append(QueryForECUtil.getDateWherePart(
            "ic_purchasein_h.freplenishflag='N' and ic_purchasein_h.dbilldate",
            inDateCond));
      }

      if (outDateCond != null) {
        if (inDateCond == null) {
          qrySchemeProcessor
              .appendFrom(" inner join ic_purchasein_b on ic_purchasein_b.cfirstbillbid=po_order_b.pk_order_b inner join ic_purchasein_h on ic_purchasein_h.cgeneralhid=ic_purchasein_b.cgeneralhid and ic_purchasein_b.csourcetype='21'");
        }
        wherePart.append(QueryForECUtil.getDateWherePart(
            "ic_purchasein_h.freplenishflag='Y' and ic_purchasein_h.dbilldate",
            outDateCond));
      }
    }

    // ��Ʒ����
    QueryCondition matnameCond = condVO.getMatnameCond();
    if (matnameCond != null) {
      wherePart.append(OrderQueryForECUtil.getMatNameWherePart(
          qrySchemeProcessor, matnameCond));
    }
    return wherePart.toString();
  }

  public static String getSupplyDetailQueryWherePart(
      QuerySchemeProcessor qrySchemeProcessor, SupplyDetailQueryCondVO condVO) {
    String hname = qrySchemeProcessor.getMainTableAlias();
    SqlBuilder wherePart = new SqlBuilder();
    // ��Ӧ��,��������
    wherePart.append(QueryForECUtil.getPubWherePart(hname, condVO));
    // ��������
    QueryCondition arriveDateCond = condVO.getArriveDateCond();
    if (arriveDateCond != null) {
      wherePart.append(QueryForECUtil.getDateWherePart(" po_arriveorder."
          + ArriveHeaderVO.BISBACK + "='N' and po_arriveorder."
          + ArriveHeaderVO.DBILLDATE, arriveDateCond));
    }
    // �˻�����
    QueryCondition backarriveDateCond = condVO.getBackArriveDateCond();
    if (backarriveDateCond != null) {
      wherePart.append(QueryForECUtil.getDateWherePart(" po_arriveorder."
          + ArriveHeaderVO.BISBACK + "='Y' and po_arriveorder."
          + ArriveHeaderVO.DBILLDATE, backarriveDateCond));
    }
    // ��Ʊ����
    QueryCondition invoiceDateCond = condVO.getInvoiceDateCond();
    // �������
    QueryCondition inDateCond = condVO.getInDateCond();
    // �˿�����
    QueryCondition outDateCond = condVO.getOutDateCond();
    if (invoiceDateCond != null || inDateCond != null || outDateCond != null) {
      qrySchemeProcessor
          .appendFrom(" inner join po_order_b on po_order_b.pk_order_b=po_arriveorder_b.pk_order_b ");

      if (invoiceDateCond != null) {
        qrySchemeProcessor
            .appendFrom(" inner join po_invoice_b on po_invoice_b.pk_order=po_order_b.pk_order inner join po_invoice on po_invoice.pk_invoice=po_invoice_b.pk_invoice");
        wherePart.append(QueryForECUtil.getDateWherePart(" po_invoice."
            + InvoiceHeaderVO.DBILLDATE, invoiceDateCond));
      }

      if (inDateCond != null) {
        qrySchemeProcessor
            .appendFrom(" inner join ic_purchasein_b on ic_purchasein_b.cfirstbillbid=po_order_b.pk_order_b inner join ic_purchasein_h on ic_purchasein_h.cgeneralhid=ic_purchasein_b.cgeneralhid and ic_purchasein_b.csourcetype='21'");
        wherePart.append(QueryForECUtil.getDateWherePart(
            "ic_purchasein_h.freplenishflag='N' and ic_purchasein_h.dbilldate",
            inDateCond));
      }

      if (outDateCond != null) {
        if (inDateCond == null) {
          qrySchemeProcessor
              .appendFrom(" inner join ic_purchasein_b on ic_purchasein_b.cfirstbillbid=po_order_b.pk_order_b inner join ic_purchasein_h on ic_purchasein_h.cgeneralhid=ic_purchasein_b.cgeneralhid and ic_purchasein_b.csourcetype='21'");
        }
        wherePart.append(QueryForECUtil.getDateWherePart(
            "ic_purchasein_h.freplenishflag='Y' and ic_purchasein_h.dbilldate",
            outDateCond));
      }
    }

    // ��Ʒ����
    QueryCondition matnameCond = condVO.getMatnameCond();
    if (matnameCond != null) {
      wherePart.append(OrderQueryForECUtil.getMatNameWherePart(
          qrySchemeProcessor, matnameCond));
    }
    return wherePart.toString();
  }

  public static String getSupplyDetailWherePart(
      QuerySchemeProcessor qrySchemeProcessor, SupplyDetailQueryVO condVO) {
    String hname = qrySchemeProcessor.getMainTableAlias();
    // �����ӱ�
    qrySchemeProcessor.getTableAliasOfAttribute("pk_order_b.pk_org");
    SqlBuilder wherePart = new SqlBuilder();
    // ��Ӧ��,��������
    wherePart.append(QueryForECUtil.getPubWherePart(hname, condVO));

    // wuxla �� ȥ������͹ر�����
    // wherePart.append(" and po_order." + OrderHeaderVO.BFROZEN,
    // UFBoolean.FALSE);
    // wherePart.append(" and po_order." + OrderHeaderVO.BFINALCLOSE,
    // UFBoolean.FALSE);
    // wherePart.append(" and po_order_b." + OrderItemVO.FISACTIVE,
    // EnumActive.ACTIVE.toInt());
    // // ��������
    // QueryCondition arriveDateCond = condVO.getArriveDateCond();
    // if (arriveDateCond != null) {
    // qrySchemeProcessor
    // .appendFrom(" inner join  po_arriveorder_b on po_arriveorder_b.pk_order=po_order.pk_order inner join po_arriveorder on po_arriveorder.pk_arriveorder=po_arriveorder_b.pk_arriveorder");
    // wherePart.append(QueryForECUtil.getDateWherePart(" po_arriveorder."
    // + ArriveHeaderVO.BISBACK + "='N' and po_arriveorder."
    // + ArriveHeaderVO.DBILLDATE, arriveDateCond));
    // }
    // // �˻�����
    // QueryCondition backarriveDateCond = condVO.getBackArriveDateCond();
    // if (backarriveDateCond != null) {
    // if (arriveDateCond == null) {
    // qrySchemeProcessor
    // .appendFrom(" inner join  po_arriveorder_b on po_arriveorder_b.pk_order=po_order.pk_order inner join po_arriveorder on po_arriveorder.pk_arriveorder=po_arriveorder_b.pk_arriveorder");
    // }
    // wherePart.append(QueryForECUtil.getDateWherePart(" po_arriveorder."
    // + ArriveHeaderVO.BISBACK + "='Y' and po_arriveorder."
    // + ArriveHeaderVO.DBILLDATE, backarriveDateCond));
    // }
    // // ��Ʊ����
    // QueryCondition invoiceDateCond = condVO.getInvoiceDateCond();
    // if (invoiceDateCond != null) {
    // qrySchemeProcessor
    // .appendFrom(" inner join  po_invoice_b on po_invoice_b.pk_order=po_order_b.pk_order inner join po_invoice on po_invoice.pk_invoice=po_invoice_b.pk_invoice");
    // wherePart.append(QueryForECUtil.getDateWherePart(" po_invoice."
    // + InvoiceHeaderVO.DBILLDATE, invoiceDateCond));
    // }
    // // �������
    // QueryCondition inDateCond = condVO.getInDateCond();
    // if (inDateCond != null) {
    // qrySchemeProcessor
    // .appendFrom(" inner join  ic_purchasein_b on ic_purchasein_b.cfirstbillbid=po_order_b.pk_order_b inner join ic_purchasein_h on ic_purchasein_h.cgeneralhid=ic_purchasein_b.cgeneralhid and ic_purchasein_b.csourcetype='21'");
    // wherePart.append(QueryForECUtil.getDateWherePart(
    // "ic_purchasein_h.freplenishflag='N' and ic_purchasein_h.dbilldate",
    // inDateCond));
    // }
    // // �˿�����
    // QueryCondition outDateCond = condVO.getOutDateCond();
    // if (outDateCond != null) {
    // if (inDateCond == null) {
    // qrySchemeProcessor
    // .appendFrom(" inner join  ic_purchasein_b on ic_purchasein_b.cfirstbillbid=po_order_b.pk_order_b inner join ic_purchasein_h on ic_purchasein_h.cgeneralhid=ic_purchasein_b.cgeneralhid and ic_purchasein_b.csourcetype='21'");
    // }
    // wherePart.append(QueryForECUtil.getDateWherePart(
    // "ic_purchasein_h.freplenishflag='Y' and ic_purchasein_h.dbilldate",
    // outDateCond));
    // }

    // ��Ʒ����
    QueryCondition matnameCond = condVO.getMatnameCond();
    if (matnameCond != null) {
      wherePart.append(OrderQueryForECUtil.getMatNameWherePart(
          qrySchemeProcessor, matnameCond));
    }
    // mengjian
    // ���
    QueryCondition materialspec = condVO.getMaterialspec();
    if (materialspec != null) {
      String matAlias =
          qrySchemeProcessor.getTableAliasOfAttribute(OrderItemVO.PK_ORDER_B
              + "." + OrderItemVO.PK_MATERIAL + "." + MaterialVO.PK_ORG);
      wherePart.append(" and " + matAlias + "." + materialspec.getFieldCode(),
          " " + materialspec.getOperator() + " ",
          materialspec.getValues()[0].toString());
    }
    // �ͺ�
    QueryCondition materialtype = condVO.getMaterialtype();
    if (materialtype != null) {
      String matAlias =
          qrySchemeProcessor.getTableAliasOfAttribute(OrderItemVO.PK_ORDER_B
              + "." + OrderItemVO.PK_MATERIAL + "." + MaterialVO.PK_ORG);
      wherePart.append(" and " + matAlias + "." + materialtype.getFieldCode(),
          " " + materialtype.getOperator() + " ",
          materialtype.getValues()[0].toString());
    }
    // ���Ϸ�������
    QueryCondition marbasclassName = condVO.getMarbasclassNameCond();
    if (marbasclassName != null) {
      String matAlias =
          qrySchemeProcessor.getTableAliasOfAttribute(OrderItemVO.PK_ORDER_B
              + "." + OrderItemVO.PK_MATERIAL + "." + MaterialVO.PK_MARBASCLASS
              + "." + MarBasClassVO.PK_ORG);
      wherePart.append(
          " and " + matAlias + "." + marbasclassName.getFieldCode(), " "
              + marbasclassName.getOperator() + " ",
          marbasclassName.getValues()[0].toString());
    }

    // �ɹ���֯
    QueryCondition pk_org = condVO.getPk_org();
    if (pk_org != null) {
      // wherePart.append(OrderQueryForECUtil.getCondWherePart(qrySchemeProcessor,
      // pk_org, OrderHeaderVO.PK_ORG));
      String[] pk_orgsvalue = pk_org.getValues();
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_21_65.name());
      String orgcondsql =
          builder.buildSQL(" and " + hname + "." + OrderHeaderVO.PK_ORG,
              pk_orgsvalue);
      wherePart.append(orgcondsql);

    }
    return wherePart.toString();
  }

  private static void appendBodyCloseWherePart(SqlBuilder sqlBuilder) {
    sqlBuilder.append(" and ");
    sqlBuilder.append(PUEntity.M21_B_TABLE);
    sqlBuilder.append(".");
    sqlBuilder.append(OrderItemVO.FISACTIVE, EnumActive.ACTIVE.toInt());
  }

  private static void appendHeadCloseWherePart(SqlBuilder sqlBuilder) {
    sqlBuilder.append(" and ");
    sqlBuilder.append(PUEntity.M21_H_TABLE);
    sqlBuilder.append(".");
    sqlBuilder.append(OrderHeaderVO.BFINALCLOSE, UFBoolean.FALSE);
  }

  private static String getCondWherePart(
      QuerySchemeProcessor qrySchemeProcessor, QueryCondition cond,
      String strFiled) {
    SqlBuilder wherePart = new SqlBuilder();
    String puorgAlias =
        qrySchemeProcessor.getTableAliasOfAttribute(strFiled + "." + strFiled);
    wherePart.append(" and " + puorgAlias + "." + cond.getFieldCode(), " "
        + cond.getOperator() + " ", cond.getValues()[0].toString());
    return wherePart.toString();
  }

  /**
   * @param qrySchemeProcessor
   * @param nameCond
   * @param currStrFiled ��ǰ�����ڴ�Ԫ�����е�����
   * @param strFiled ��ǰ�����е�ĳ���ֶ�����
   * @return
   */
  private static String getNameWherePart(
      QuerySchemeProcessor qrySchemeProcessor, QueryCondition nameCond,
      String currStrFiled, String strFiled) {
    SqlBuilder wherePart = new SqlBuilder();
    String tableAlias =
        qrySchemeProcessor.getTableAliasOfAttribute(currStrFiled + "."
            + strFiled);
    wherePart.append(" and " + tableAlias + "." + nameCond.getFieldCode(), " "
        + nameCond.getOperator() + " ", nameCond.getValues()[0].toString());
    return wherePart.toString();
  }
}
