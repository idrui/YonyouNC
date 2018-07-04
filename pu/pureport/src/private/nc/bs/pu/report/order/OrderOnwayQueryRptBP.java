/**
 * �ñ��������ѵ����������� = ��ǰ״̬������֮�� - ��һ״̬������֮�ͣ�����ʵ��˼·���£�
 * �������;�Ķ�����������union��;���ݣ���ɽ����a��
 * ��ѯʱ��ʹ�÷�������lead��ȡ���������a����һ�У���a������ - ��һ�е�����������������Ҫ����ֵ��
 *  
 *  
 *  ʾ��SQL���£������ο�����ʾ��ʹ�õ�Oracle�﷨��
select po_order.pk_order, po_order.pk_org_v, po_order.vbillcode, po_order.dbilldate,po_order.pk_supplier,
    po_order.cemployeeid, po_order.nversion, po_order.bisreplenish, po_order.breturn, po_order_b.crowno, po_order_b.blargess, 
    po_order_b.pk_material, po_order_b.nqttaxnetprice, po_order_b.dplanarrvdate, po_order_b.cunitid, po_order_b.ccurrencyid,
    b.nnum, b.fonwaystatus, po_order_b.nqttaxnetprice * b.nnum ntaxmny from (
        select a.pk_order_b, a.fonwaystatus, a.nnum - nvl(lead(a.nnum, 1) over (partition by a.pk_order_b order by a.fonwaystatus), 0) nnum from (
            -- ��ѯ����;״̬�ġ�����̬�ģ����ҽ������͵���;��ʼ״̬�������Ķ�������
            select po_order_b.pk_order_b, 0 fonwaystatus, po_order_b.nnum nnum from po_order_b po_order_b
            inner join po_order po_order on po_order.pk_order = po_order_b.pk_order and po_order.dr = 0 and po_order.bislatest = 'Y' and forderstatus = 3
            inner join po_potrantype on po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> 0
            where po_order_b.dr = 0 and po_potrantype.ionwaybegin = 0 and po_order.pk_org = '0001A510000000003Z19'
            union all
            -- ��ѯ��;��ȡ�ñ������ڣ��Ѿ�ִ�е���;����
            select po_order_bb.pk_order_b, po_order_bb.fonwaystatus, sum(nonwaynum) nnum from po_order_bb
            inner join po_order_b po_order_b on po_order_bb.pk_order_b = po_order_b.pk_order_b
            inner join po_order po_order on po_order.pk_order = po_order_b.pk_order and po_order.bislatest='Y'
            inner join po_potrantype on po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> 0
            where isoperated = 'Y' and po_order_b.dr = 0 and po_order_bb.dr = 0 and po_order_bb.pk_org='0001A510000000003Z19'
            group by po_order_bb.pk_order_b, po_order_bb.fonwaystatus
            union all
            -- ���ϵ�������Ϊ�����������д��;��
            select po_arriveorder_b.pk_order_b, 7 fonwaystatus, sum(po_arriveorder_b.nnum) nnum from po_arriveorder_b
            inner join po_arriveorder on po_arriveorder_b.pk_arriveorder = po_arriveorder.pk_arriveorder
            inner join po_order po_order on po_order.pk_order = po_arriveorder_b.pk_order and po_order.bislatest='Y'
            inner join po_potrantype on po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> 0
            where po_arriveorder_b.dr = 0 and po_arriveorder.fbillstatus = 3 and po_order.pk_org='0001A510000000003Z19'
            group by po_arriveorder_b.pk_order_b
            union all
            -- ����������Ϊ�ɹ���ⵥ�����д��;��
            select ic_purchasein_b.cfirstbillbid, 8 fonwaystatus, sum(nnum) from ic_purchasein_b
            inner join ic_purchasein_h on ic_purchasein_b.cgeneralhid = ic_purchasein_h.cgeneralhid
            inner join po_order po_order on po_order.pk_order = ic_purchasein_b.cfirstbillhid and po_order.bislatest='Y'
            inner join po_potrantype on po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> 0
            where ic_purchasein_b.dr = 0 and ic_purchasein_h.fbillflag = 3 and po_order.pk_org='0001A510000000003Z19'
            group by ic_purchasein_b.cfirstbillbid
        ) a
    ) b
inner join po_order_b po_order_b on b.pk_order_b = po_order_b.pk_order_b
inner join po_order po_order on po_order.pk_order = po_order_b.pk_order and po_order.bislatest='Y'
inner join po_potrantype on po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> 0

-- ����Ϊ��ѡ��һЩ����
--inner join bd_supplier on po_order.pk_supplier = bd_supplier.pk_supplier
--inner join bd_areacl on bd_supplier.pk_areacl = bd_areacl.pk_areacl
--inner join bd_material_v on po_order_b.pk_srcmaterial = bd_material_v.pk_source
--inner join bd_marbasclass on bd_material_v.pk_marbasclass = bd_marbasclass.pk_marbasclass
--inner join bd_materialstock on bd_materialstock.pk_material = bd_material_v.pk_material
--inner join bd_marpuclass on bd_marpuclass.pk_marpuclass = bd_marpuclass.pk_marpuclass

where b.nnum <> 0 and fonwaystatus >= po_potrantype.ionwaybegin and fonwaystatus < po_potrantype.ionwayend -- ��ʼ״̬֮ǰ�ĺͽ���״̬���Ժ�Ĳ���ʾ
and not exists (
    select 1 from po_order_b ib
    where ib.pk_order_b = po_order_b.pk_order_b and (
        (po_potrantype.ionwayend = 7 and po_order_b.barriveclose = 'Y') or  
        (po_potrantype.ionwayend = 8 and po_order_b.bstockclose = 'Y') or  
        (po_potrantype.ionwayend = 8 and po_order_b.barriveclose = 'Y' and b.fonwaystatus <> 7)
    )
)

-- ������ƴ�ӽ����ϵĲ�ѯ����
-- and ......
 */

package nc.bs.pu.report.order;

import nc.pub.smart.context.SmartContext;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.report.enumeration.OrderOnwayQueryFieldCode;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.order.OrderOnwayQueryView;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.StringUtils;

/**
 * ƴ�Ӷ�����;״̬��ѯSQL��BP�ࡣ
 * 
 * @since 6.3
 * @version 2012-8-23 ����06:49:06
 * @author lixyp
 */
public class OrderOnwayQueryRptBP {

  private String orgOperCode = null;

  private String pk_org = null;

  private SqlBuilder sqlBuilder = new SqlBuilder();

  /**
   * ��ȡSQL�������γɵ�SQL�ɲο���������ļ�ע�͡�
   * 
   * @param context ����ģ��������
   * @return ��ѯSQL
   */
  public String getQuerySql(SmartContext context) {
    this.pk_org = (String) context.getAttribute("#pk_org#");
    this.orgOperCode = (String) context.getAttribute("#orgOperCode#");
    ConditionVO[] conds= (ConditionVO[])context.getAttribute(PuQueryInfoPara.QUERY_CONDS);
    OrderOnwayQueryView view = new OrderOnwayQueryView(conds, context);
    this.sqlBuilder.append("select ");
    this.appendField("head", OrderHeaderVO.PK_ORDER);
    this.appendField("head", OrderHeaderVO.PK_ORG);
    this.appendField("head", OrderHeaderVO.PK_ORG_V);
    this.appendField("head", OrderHeaderVO.VBILLCODE);
    this.appendField("head", OrderHeaderVO.DBILLDATE);
    this.appendField("head", OrderHeaderVO.PK_SUPPLIER);
    this.appendField("head", OrderHeaderVO.CEMPLOYEEID);
    this.appendField("head", OrderHeaderVO.NVERSION);
    this.appendField("head", OrderHeaderVO.BISREPLENISH);
    this.appendField("head", OrderHeaderVO.BRETURN);
    this.appendField("head", OrderItemVO.CROWNO);
    this.appendField("head", OrderItemVO.BLARGESS);
    this.appendField("head", OrderItemVO.PK_MATERIAL);
    this.appendField("head", OrderItemVO.NQTTAXNETPRICE);
    this.appendField("head", OrderItemVO.DPLANARRVDATE);
    this.appendField("head", OrderItemVO.CUNITID);
    this.appendField("head", OrderItemVO.CCURRENCYID);
    this.appendField("b", OrderItemVO.NNUM);
    this.appendField("b", OrderOnwayItemVO.FONWAYSTATUS);
    this.sqlBuilder.append(" head.nqttaxnetprice * b.nnum ntaxmny ");
    this.sqlBuilder.append(" from (");
    this.buildSubQueryB();
    this.sqlBuilder.append(") b ");
    String headSql = view.getViewSqlName();
    this.processJoin(headSql);
    this.processWhere();

    return this.sqlBuilder.toString();
  }

  /**
   * ��Ӳ�ѯ�ֶΡ�
   * 
   * @param table ����
   * @param field �ֶ���
   */
  private void appendField(String table, String field) {
    this.appendField(table, field, null);
  }

  /**
   * ��Ӳ�ѯ�ֶΡ�
   * 
   * @param table ����
   * @param field �ֶ���
   * @param alias ����
   */
  private void appendField(String table, String field, String alias) {
    if (table != null) {
      this.sqlBuilder.append(table);
      this.sqlBuilder.append(".");
    }
    this.sqlBuilder.append(field);

    if (alias != null) {
      this.sqlBuilder.append(" ");
      this.sqlBuilder.append(alias);
    }

    this.sqlBuilder.append(",");
  }

  /**
   * ƴ����֯�ֶε�SQLƬ�Ρ�
   * ��Ϊǰ̨�õ�����֯�ֶε�ѡ�Ͷ�ѡ����ʽ��һ�����������һ������ͳһ����
   * 
   * @param field ��֯�ֶ�SQL
   */
  private void appendOrgField(String field) {
    // ������ģ�Ͷ���ʱ����Ϊ��
    if (this.pk_org != null) {
      this.sqlBuilder.append(field);
      if ("=".equals(this.orgOperCode)) {
        this.sqlBuilder.append(this.orgOperCode);
        this.sqlBuilder.append("'");
        this.sqlBuilder.append(this.pk_org);
        this.sqlBuilder.append("'");
      }
      else {
        this.sqlBuilder.append(this.orgOperCode);
        this.sqlBuilder.append(this.pk_org);
      }
    }
  }

  /**
   * ���쵽������ѯ����ѯ������;�������͵ġ��Ѿ������Ķ������ε�������
   */
  private void buildArriveQuery() {
    this.sqlBuilder.append("select ");
    this.appendField(PUEntity.M23_B_TABLE, ArriveItemVO.PK_ORDER_B);
    this.appendField(null, "7", "fonwaystatus");
    this.appendField(null, "sum(po_arriveorder_b.nnum)", "nnum");
    this.sqlBuilder.deleteLastChar(); // ɾ�����һ������

    this.sqlBuilder.append(" from ");
    this.sqlBuilder.append(PUEntity.M23_B_TABLE);
    this.sqlBuilder
        .append(" inner join po_arriveorder on po_arriveorder_b.pk_arriveorder = po_arriveorder.pk_arriveorder");
    this.sqlBuilder
        .append(" inner join po_order po_order on po_order.pk_order = po_arriveorder_b.pk_order and po_order.bislatest='Y' ");
    this.sqlBuilder
        .append(" inner join po_potrantype on po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> 0 ");
    this.sqlBuilder
        .append(" where po_arriveorder_b.dr = 0 and po_arriveorder.fbillstatus = ");
    this.sqlBuilder.append(POEnumBillStatus.APPROVE.toInteger());
    this.appendOrgField(" and po_order.pk_org ");

    this.sqlBuilder.append(" group by po_arriveorder_b.pk_order_b");
  }

  /**
   * ����;���в�ѯ�Ѿ�ִ�е���;���ݡ�
   */
  private void buildOnwayQuery() {
    this.sqlBuilder.append("select ");
    this.appendField(PUEntity.M21_BB_TABLE, OrderOnwayItemVO.PK_ORDER_B);
    this.appendField(PUEntity.M21_BB_TABLE, OrderOnwayItemVO.FONWAYSTATUS);
    this.appendField(null, "sum(nonwaynum)", "nnum");
    this.sqlBuilder.deleteLastChar();

    this.sqlBuilder.append(" from ");
    this.sqlBuilder.append(PUEntity.M21_BB_TABLE);
    this.sqlBuilder.append(" inner join po_order_b po_order_b on");
    this.sqlBuilder.append(" po_order_bb.pk_order_b = po_order_b.pk_order_b");
    this.sqlBuilder.append(" inner join po_order po_order on");
    this.sqlBuilder
        .append(" po_order.pk_order = po_order_b.pk_order and po_order.bislatest='Y' ");
    this.sqlBuilder.append(" inner join po_potrantype on");
    this.sqlBuilder
        .append(" po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> ");
    this.sqlBuilder.append(OnwayStatus.STATUS_AUDIT.toInteger());
    this.sqlBuilder.append(" where isoperated = 'Y' and po_order_b.dr = 0 ");
    this.sqlBuilder.append(" and po_order_bb.dr = 0 ");
    this.appendOrgField(" and po_order_bb.pk_org ");

    this.sqlBuilder
        .append(" group by po_order_bb.pk_order_b, po_order_bb.fonwaystatus");
  }

  /**
   * ���충����ѯ����ѯ������;�������͵ġ��Ѿ������ġ��ҽ������͵���;��ʼ״̬�������Ķ������ݡ�
   */
  private void buildOrderQuery() {
    this.sqlBuilder.append("select ");
    this.appendField(PUEntity.M21_B_TABLE, OrderItemVO.PK_ORDER_B);
    this.appendField(null, "0", "fonwaystatus");
    this.appendField(PUEntity.M21_B_TABLE, OrderItemVO.NNUM, "nnum");
    this.sqlBuilder.deleteLastChar(); // ɾ�����һ������

    this.sqlBuilder.append(" from ");
    this.sqlBuilder.append(PUEntity.M21_B_TABLE);
    this.sqlBuilder
        .append(" po_order_b inner join po_order po_order on po_order.pk_order = po_order_b.pk_order and po_order.dr = 0 and po_order.bislatest='Y' and ");
    this.sqlBuilder.append(OrderHeaderVO.FORDERSTATUS,
        POEnumBillStatus.APPROVE.toInt());
    this.sqlBuilder
        .append(" inner join po_potrantype on po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> 0 ");
    this.sqlBuilder
        .append(" where po_order_b.dr = 0 and po_potrantype.ionwaybegin = 0 ");
    this.appendOrgField(" and po_order.pk_org ");
  }

  /**
   * ���쵽������ѯ����ѯ������;�������͵ġ��Ѿ������Ķ������βɹ���ⵥ��
   */
  private void buildPurchaseinQuery() {
    this.sqlBuilder.append("select ic_purchasein_b.cfirstbillbid, ");
    this.sqlBuilder.append("8 fonwaystatus, sum(nnum) nnum ");
    this.sqlBuilder.append(" from ic_purchasein_b ");
    this.sqlBuilder
        .append(" inner join ic_purchasein_h on ic_purchasein_b.cgeneralhid = ic_purchasein_h.cgeneralhid");
    this.sqlBuilder
        .append(" inner join po_order po_order on po_order.pk_order = ic_purchasein_b.cfirstbillhid and po_order.bislatest='Y' ");
    this.sqlBuilder
        .append(" inner join po_potrantype on po_potrantype.vtrantypecode = po_order.vtrantypecode and ionwayend <> 0 ");
    this.sqlBuilder
        .append(" where ic_purchasein_b.dr = 0 and ic_purchasein_h.fbillflag = 3 ");
    this.appendOrgField(" and po_order.pk_org ");

    this.sqlBuilder.append(" group by ic_purchasein_b.cfirstbillbid ");
  }

  /**
   * �����Ӳ�ѯb��<br />
   * ����a,b�����Ӳ�ѯ��SQLʵ��˼·����ο���������ļ�ע�͡�
   */
  private void buildSubQueryB() {
    this.sqlBuilder.append(" select a.pk_order_b,a.fonwaystatus, ");
    this.sqlBuilder
        .append("a.nnum - isnull(lead(a.nnum, 1) over (partition by a.pk_order_b order by a.fonwaystatus), 0) nnum ");

    // �����Ӳ�ѯa
    this.sqlBuilder.append("from (");
    this.buildOrderQuery();
    this.sqlBuilder.append(" union all ");
    this.buildOnwayQuery();
    this.sqlBuilder.append(" union all ");
    this.buildArriveQuery();
    this.sqlBuilder.append(" union all ");
    this.buildPurchaseinQuery();
    this.sqlBuilder.append(") a");
  }

  /**
   * �����Ӳ�ѯb�����ӣ������ǰ̨�Ĳ�ѯ������ء�
   * @param headSql 
   */
  private void processJoin(String headSql) {
    // ��������ӡ�
    this.sqlBuilder
        .append(" inner join (" + headSql + ") head on b.pk_order_b = head.pk_order_b");


  }

  /**
   * �����Ӳ�ѯb�������������ǰ̨�Ĳ�ѯ������ء�
   */
  private void processWhere() {
    this.sqlBuilder.append(" where ");
    this.sqlBuilder.append(" b.nnum <> 0 ");
    this.sqlBuilder.append(" and b.fonwaystatus >= head.ionwaybegin ");
    this.sqlBuilder.append(" and b.fonwaystatus < head.ionwayend ");
    this.sqlBuilder.append(" and not exists (");
    this.sqlBuilder.append(" select 1 from po_order_b ib ");
    this.sqlBuilder.append(" where ib.pk_order_b = head.pk_order_b and(");
    this.sqlBuilder.append(" (head.ionwayend = ");
    this.sqlBuilder.append(OnwayStatus.STATUS_ARRIVE.toInteger());
    this.sqlBuilder.append(" and head.barriveclose = 'Y') or ");
    this.sqlBuilder.append(" (head.ionwayend = ");
    this.sqlBuilder.append(OnwayStatus.STATUS_STORE.toInteger());
    this.sqlBuilder.append(" and head.bstockclose = 'Y') or ");
    this.sqlBuilder.append(" (head.ionwayend = ");
    this.sqlBuilder.append(OnwayStatus.STATUS_STORE.toInteger());
    this.sqlBuilder.append(" and head.barriveclose = 'Y' ");
    this.sqlBuilder.append(" and b.fonwaystatus <> ");
    this.sqlBuilder.append(OnwayStatus.STATUS_ARRIVE.toInteger());
    this.sqlBuilder.append(")))");

//    if (!StringUtils.isEmpty(this.whereClause)) {
//      this.sqlBuilder.append(" and ");
//      this.sqlBuilder.append(this.whereClause);
//    }
  }
}
