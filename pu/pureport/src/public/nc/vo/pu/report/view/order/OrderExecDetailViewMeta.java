package nc.vo.pu.report.view.order;

import java.sql.Types;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.Attribute;

/**
 * ����ִ����ϸDataViewMeta
 * 
 * @since 6.0
 * @version 2011-9-14 ����07:31:00
 * @author wuxla
 */

public class OrderExecDetailViewMeta extends DataViewMeta {
  public static final String ARRIVE_BLARGESSNUM = "arrive_blargessnum";

  public static final String ARRIVE_DBILLDATE = "arrive_dbilldate";

  public static final String ARRIVE_NNUM = "arrive_nnum";

  public static final String ARRIVE_VBILLCODE = "arrive_vbillcode";

  public static final String ARRVSTOORG = "arrvstoorg";

  public static final String ARRVSTOORG_V = "arrvstoorg_v";

  public static final String CASSCUSTID = "casscustid";

  public static final String CONFIRM_NNUM = "confirm_nnum";

  public static final String CUSTOM_DBILLDATE = "custom_dbilldate";

  public static final String CUSTOM_VBILLCODE = "custom_vbillcode";

  public static final String CWAREHOUSEID = "cwarehouseid";

  /**
   * �ɹ���֯��������š��Է������š��汾�š��Ƿ񲹻����������֡���Ӧ�̡��ɹ�Ա���ɹ����š���������
   */
  public static final String[] EXEHEAD21 = new String[] {
    OrderHeaderVO.PK_ORG_V, OrderHeaderVO.PK_ORG, OrderHeaderVO.VBILLCODE,
    OrderHeaderVO.VCOOPORDERCODE, OrderHeaderVO.NVERSION,
    OrderHeaderVO.BISREPLENISH, OrderHeaderVO.CORIGCURRENCYID,
    OrderHeaderVO.PK_SUPPLIER, OrderHeaderVO.CEMPLOYEEID,
    OrderHeaderVO.PK_DEPT_V, OrderHeaderVO.PK_DEPT, OrderHeaderVO.DBILLDATE
  };

  /**
   * �����������кš��Ƿ���Ʒ�����ϱ��롢�������ơ�����ͺš����ء����ɸ������ԡ����κš�����λ��������
   * ��˰���ۡ���˰���ۡ�˰�ʡ��ۿۡ���˰���ۡ���˰���ۡ�����˰���ۡ�����˰���ۡ����ұ��֡���������˰����
   * �����Һ�˰���ۡ���˰����˰�ϼơ�˰�������˰�����Ҽ�˰�ϼơ�����˰���˰��𡢼ƻ���������
   * ��Ŀ,�ͻ�
   */
  public static final String[] EXEITEM21 = new String[] {
    OrderItemVO.PK_ORDER_B, OrderItemVO.CROWNO, OrderItemVO.BLARGESS,
    OrderItemVO.PK_MATERIAL, OrderItemVO.PK_SRCMATERIAL, OrderItemVO.VFREE1,
    OrderItemVO.VFREE2, OrderItemVO.VFREE3, OrderItemVO.VFREE4,
    OrderItemVO.VFREE5, OrderItemVO.VFREE6, OrderItemVO.VFREE7,
    OrderItemVO.VFREE8, OrderItemVO.VFREE9, OrderItemVO.VFREE10,
    OrderItemVO.VBATCHCODE, OrderItemVO.CUNITID, OrderItemVO.NNUM,
    OrderItemVO.NQTORIGPRICE, OrderItemVO.NQTORIGTAXPRICE,
    OrderItemVO.NTAXRATE, OrderItemVO.NITEMDISCOUNTRATE,
    OrderItemVO.NQTORIGNETPRICE, OrderItemVO.NQTORIGTAXNETPRC,
    OrderItemVO.NORIGNETPRICE, OrderItemVO.NORIGTAXNETPRICE,
    OrderItemVO.CCURRENCYID, OrderItemVO.NNETPRICE, OrderItemVO.NTAXNETPRICE,
    OrderItemVO.NORIGMNY, OrderItemVO.NORIGTAXMNY, OrderItemVO.NMNY,
    OrderItemVO.NTAXMNY, OrderItemVO.NTAX, OrderItemVO.FTAXTYPEFLAG,
    OrderItemVO.DPLANARRVDATE, OrderItemVO.CPROJECTID, OrderItemVO.CASSCUSTID
  };

  public static final String IC_BLARGESSNUM = "ic_blargessnum";

  public static final String IC_CMATERIALOID = "ic_cmaterialoid";

  public static final String IC_CMATERIALVID = "ic_cmaterialvid";

  public static final String IC_CUNITID = "ic_cunitid";

  public static final String IC_DBILLDATE = "ic_dbilldate";

  public static final String IC_NNUM = "ic_nnum";

  public static final String IC_NORIGMNY = "ic_norigmny";

  public static final String IC_NORIGPRICE = "ic_norigprice";

  public static final String IC_VBILLCODE = "ic_vbillcode";

  public static final String INVOICE_CORIGCURRENCYID =
      "invoice_corigcurrencyid";

  public static final String INVOICE_DBILLDATE = "invoice_dbilldate";

  public static final String INVOICE_NNUM = "invoice_nnum";

  public static final String INVOICE_NTAXMNY = "invoice_ntaxmny";

  public static final String INVOICE_VBILLCODE = "invoice_vbillcode";

  public static final String LOAD_DBILLDATE = "load_dbilldate";

  public static final String LOAD_VBILLCODE = "load_vbillcode";

  public static final String NACCCANCELINVMNY = "nacccancelinvmny";

  public static final String NBACKARRVNUM = "nbackarrvnum";

  public static final String NBACKSTORENUM = "nbackstorenum";

  public static final String NELIGNUM = "nelignum";

  /**
   * ��ʱ����
   */
  public static final String ORDEREXECDETAILTABLE = "t_pu_orderexecdetail";

  public static final String OUTCUSTOM_DBILLDATE = "outcustom_dbilldate";

  public static final String OUTCUSTOM_VBILLCODE = "outcustom_vbillcode";

  public static final String OUTPUT_DBILLDATE = "output_dbilldate";

  public static final String OUTPUT_NNUM = "output_nnum";

  public static final String OUTPUT_VBILLCODE = "output_vbillcode";

  public static final String PK_ARRIVE_B = "pk_arrive_b";

  public static final String PK_IC_B = "pk_ic_b";

  public static final String PK_INVOICE_B = "pk_invoice_b";

  public static final String PK_MARBASCLASS = "pk_marbasclass";

  public static final String QC_NNUM = "qc_nnum";

  private RptExtAttribute[] extattrs;

  public OrderExecDetailViewMeta() {
    this.init();
  }

  public JavaType[] getJavaType() {
    JavaType[] javaTypes = new JavaType[this.extattrs.length];
    for (int i = 0; i < this.extattrs.length; ++i) {
      javaTypes[i] = this.extattrs[i].getJavaType();
    }
    return javaTypes;
  }

  private void addExtAttribute() {
    for (RptExtAttribute attr : this.extattrs) {
      Attribute attribute = new Attribute(attr.getName(), null, null);
      attribute.setJavaType(attr.getJavaType());
      attribute.setCustom(false);
      attribute.setStatic(false);
      attribute.setPersistence(false);
      attribute.setSerializable(true);
      this.add(attribute);
    }
  }

  private void init() {
    this.add(OrderItemVO.class, OrderExecDetailViewMeta.EXEITEM21);
    this.add(OrderHeaderVO.class, OrderExecDetailViewMeta.EXEHEAD21);
    this.addRelation(OrderItemVO.class, OrderItemVO.PK_ORDER,
        OrderHeaderVO.class, OrderHeaderVO.PK_ORDER);
    this.initExtAttrs();
    this.addExtAttribute();
  }

  private void initExtAttrs() {
    this.extattrs = new RptExtAttribute[40];
    // ���ϻ�������
    this.extattrs[0] =
        new RptExtAttribute(OrderExecDetailViewMeta.PK_MARBASCLASS,
            Types.VARCHAR, JavaType.String);
    // ȷ���������� �������š� �������ڡ� ������������ װ�˵��š� װ�����ڡ� ���ص���
    // �������ڡ� ���ص��š� ��������
    this.extattrs[1] =
        new RptExtAttribute(OrderExecDetailViewMeta.CONFIRM_NNUM,
            Types.DECIMAL, JavaType.UFDouble);
    this.extattrs[2] =
        new RptExtAttribute(OrderExecDetailViewMeta.OUTPUT_VBILLCODE,
            Types.VARCHAR, JavaType.String);
    this.extattrs[3] =
        new RptExtAttribute(OrderExecDetailViewMeta.OUTPUT_DBILLDATE,
            Types.VARCHAR, JavaType.UFDate);
    this.extattrs[4] =
        new RptExtAttribute(OrderExecDetailViewMeta.OUTPUT_NNUM, Types.DECIMAL,
            JavaType.UFDouble);
    this.extattrs[5] =
        new RptExtAttribute(OrderExecDetailViewMeta.LOAD_VBILLCODE,
            Types.VARCHAR, JavaType.String);
    this.extattrs[6] =
        new RptExtAttribute(OrderExecDetailViewMeta.LOAD_DBILLDATE,
            Types.VARCHAR, JavaType.UFDate);
    this.extattrs[7] =
        new RptExtAttribute(OrderExecDetailViewMeta.CUSTOM_VBILLCODE,
            Types.VARCHAR, JavaType.String);
    this.extattrs[8] =
        new RptExtAttribute(OrderExecDetailViewMeta.CUSTOM_DBILLDATE,
            Types.VARCHAR, JavaType.UFDate);
    this.extattrs[9] =
        new RptExtAttribute(OrderExecDetailViewMeta.OUTCUSTOM_VBILLCODE,
            Types.VARCHAR, JavaType.String);
    this.extattrs[10] =
        new RptExtAttribute(OrderExecDetailViewMeta.OUTCUSTOM_DBILLDATE,
            Types.VARCHAR, JavaType.UFDate);

    // �������š� �������ڡ��������������˻����������ʼ���������������Ʒ�������ϸ�Ʒ����
    this.extattrs[11] =
        new RptExtAttribute(OrderExecDetailViewMeta.ARRIVE_VBILLCODE,
            Types.VARCHAR, JavaType.String);
    this.extattrs[12] =
        new RptExtAttribute(OrderExecDetailViewMeta.ARRIVE_DBILLDATE,
            Types.VARCHAR, JavaType.UFDate);
    this.extattrs[13] =
        new RptExtAttribute(OrderExecDetailViewMeta.ARRIVE_NNUM, Types.DECIMAL,
            JavaType.UFDouble);
    this.extattrs[14] =
        new RptExtAttribute(OrderExecDetailViewMeta.NBACKARRVNUM,
            Types.DECIMAL, JavaType.UFDouble);
    this.extattrs[15] =
        new RptExtAttribute(OrderExecDetailViewMeta.QC_NNUM, Types.DECIMAL,
            JavaType.UFDouble);
    this.extattrs[16] =
        new RptExtAttribute(OrderExecDetailViewMeta.ARRIVE_BLARGESSNUM,
            Types.DECIMAL, JavaType.UFDouble);
    this.extattrs[17] =
        new RptExtAttribute(OrderExecDetailViewMeta.NELIGNUM, Types.DECIMAL,
            JavaType.UFDouble);
    this.extattrs[18] =
        new RptExtAttribute(OrderExecDetailViewMeta.PK_ARRIVE_B, Types.VARCHAR,
            JavaType.String);

    // �ջ������֯�� ���ֿ⡢ ��ⵥ�š� ������ڡ� ������ϱ��롢 ����������ơ� �������λ�� �����Ʒ������
    // ����������� ��ⵥ�ۡ� ���� �˿�������
    this.extattrs[19] =
        new RptExtAttribute(OrderExecDetailViewMeta.ARRVSTOORG_V,
            Types.VARCHAR, JavaType.String);
    this.extattrs[20] =
        new RptExtAttribute(OrderExecDetailViewMeta.ARRVSTOORG, Types.VARCHAR,
            JavaType.String);
    this.extattrs[21] =
        new RptExtAttribute(OrderExecDetailViewMeta.CWAREHOUSEID,
            Types.VARCHAR, JavaType.String);
    this.extattrs[22] =
        new RptExtAttribute(OrderExecDetailViewMeta.IC_VBILLCODE,
            Types.VARCHAR, JavaType.String);
    this.extattrs[23] =
        new RptExtAttribute(OrderExecDetailViewMeta.IC_DBILLDATE,
            Types.VARCHAR, JavaType.UFDate);
    this.extattrs[24] =
        new RptExtAttribute(OrderExecDetailViewMeta.IC_CMATERIALVID,
            Types.VARCHAR, JavaType.String);
    this.extattrs[25] =
        new RptExtAttribute(OrderExecDetailViewMeta.IC_CMATERIALOID,
            Types.VARCHAR, JavaType.String);
    this.extattrs[26] =
        new RptExtAttribute(OrderExecDetailViewMeta.IC_CUNITID, Types.VARCHAR,
            JavaType.String);
    this.extattrs[27] =
        new RptExtAttribute(OrderExecDetailViewMeta.IC_BLARGESSNUM,
            Types.DECIMAL, JavaType.UFDouble);
    this.extattrs[28] =
        new RptExtAttribute(OrderExecDetailViewMeta.IC_NNUM, Types.DECIMAL,
            JavaType.UFDouble);
    this.extattrs[29] =
        new RptExtAttribute(OrderExecDetailViewMeta.IC_NORIGPRICE,
            Types.DECIMAL, JavaType.UFDouble);
    this.extattrs[30] =
        new RptExtAttribute(OrderExecDetailViewMeta.IC_NORIGMNY, Types.DECIMAL,
            JavaType.UFDouble);
    this.extattrs[31] =
        new RptExtAttribute(OrderExecDetailViewMeta.NBACKSTORENUM,
            Types.DECIMAL, JavaType.UFDouble);
    this.extattrs[32] =
        new RptExtAttribute(OrderExecDetailViewMeta.PK_IC_B, Types.VARCHAR,
            JavaType.String);

    // ��Ʊ�š���Ʊ���ڡ� ��Ʊ���֡� ��Ʊ�������� ��Ʊ���Ҽ�˰�ϼơ� ��Ʊ���Ҹ�����
    this.extattrs[33] =
        new RptExtAttribute(OrderExecDetailViewMeta.INVOICE_VBILLCODE,
            Types.VARCHAR, JavaType.String);
    this.extattrs[34] =
        new RptExtAttribute(OrderExecDetailViewMeta.INVOICE_DBILLDATE,
            Types.VARCHAR, JavaType.UFDate);
    this.extattrs[35] =
        new RptExtAttribute(OrderExecDetailViewMeta.INVOICE_CORIGCURRENCYID,
            Types.VARCHAR, JavaType.String);
    this.extattrs[36] =
        new RptExtAttribute(OrderExecDetailViewMeta.INVOICE_NNUM,
            Types.DECIMAL, JavaType.UFDouble);
    this.extattrs[37] =
        new RptExtAttribute(OrderExecDetailViewMeta.INVOICE_NTAXMNY,
            Types.DECIMAL, JavaType.UFDouble);
    this.extattrs[38] =
        new RptExtAttribute(OrderExecDetailViewMeta.NACCCANCELINVMNY,
            Types.DECIMAL, JavaType.UFDouble);
    this.extattrs[39] =
        new RptExtAttribute(OrderExecDetailViewMeta.PK_INVOICE_B,
            Types.VARCHAR, JavaType.String);
  }
}
