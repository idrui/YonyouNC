package nc.vo.pu.m21.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>�ɹ������ӱ�VO</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author donggq
 * @time 2009-6-16 ����07:02:42
 */
public class OrderItemVO extends nc.vo.pub.SuperVO {

  /** �����ر� */
  public static final String BARRIVECLOSE = "barriveclose";

  /** ����ת�ɹ� */
  public static final String BBORROWPUR = "bborrowpur";

  /** ��Ʊ�ر� */
  public static final String BINVOICECLOSE = "binvoiceclose";

  /** �Ƿ���Ʒ */
  public static final String BLARGESS = "blargess";

  /** ����ر� */
  public static final String BPAYCLOSE = "bpayclose";

  /** ���ڵ����ƻ� */
  public static final String BRECEIVEPLAN = "breceiveplan";

  /** ���ر� */
  public static final String BSTOCKCLOSE = "bstockclose";

  /** �Ƿ�����ر� */
  public static final String BTRANSCLOSED = "btransclosed";

  /** ����ó�� */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /** �ͻ� */
  public static final String CASSCUSTID = "casscustid";

  /** ��λ */
  public static final String CASTUNITID = "castunitid";

  /** ��ͬ��Ϣ */
  public static final String CCONTRACTID = "ccontractid";

  /** ��ͬ��ϸ */
  public static final String CCONTRACTROWID = "ccontractrowid";

  /** ���ұ��� */
  public static final String CCURRENCYID = "ccurrencyid";

  /** Ŀ�ĵص��� */
  public static final String CDESTIAREAID = "cdestiareaid";

  /** Ŀ�ĵع� */
  public static final String CDESTICOUNTRYID = "cdesticountryid";

  /** �ջ��ص� */
  public static final String CDEVADDRID = "cdevaddrid";

  /** �ջ����� */
  public static final String CDEVAREAID = "cdevareaid";

  /** �������񵥾���ϸ */
  public static final String CECBILLBID = "cecbillbid";

  /** �������񵥾� */
  public static final String CECBILLID = "cecbillid";

  /** �������񵥾����� */
  public static final String CECTYPECODE = "cectypecode";

  /** ������ **/
  public static final String CFFILEID = "cffileid";

  /** Դͷ������ϸ */
  public static final String CFIRSTBID = "cfirstbid";

  /** Դͷ���� */
  public static final String CFIRSTID = "cfirstid";

  /** Դͷ�������� */
  public static final String CFIRSTTYPECODE = "cfirsttypecode";

  /** ����Ա */
  public static final String CHANDLER = "chandler";

  /** ԭ������ */
  public static final String CORIGAREAID = "corigareaid";

  /** ԭ���� */
  public static final String CORIGCOUNTRYID = "corigcountryid";

  /** ���� */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** �빺���������� */
  public static final String CPRAYBILLBID = "cpraybillbid";

  /** �빺���� */
  public static final String CPRAYBILLCODE = "cpraybillcode";

  /** �빺����ͷ���� */
  public static final String CPRAYBILLHID = "cpraybillhid";

  /** �빺���к� */
  public static final String CPRAYBILLROWNO = "cpraybillrowno";

  /** �빺���������� */
  public static final String CPRAYTYPECODE = "cpraytypecode";

  /** �۸�������������ӱ� */
  public static final String CPRICEAUDIT_BB1ID = "cpriceaudit_bb1id";

  /** �۸������������ϸ */
  public static final String CPRICEAUDIT_BID = "cpriceaudit_bid";

  /** �۸������� */
  public static final String CPRICEAUDITID = "cpriceauditid";

  /** �������� */
  public static final String CPRODUCTORID = "cproductorid";

  /** ��Ŀ */
  public static final String CPROJECTID = "cprojectid";

  /** ��Ŀ���� */
  public static final String CPROJECTTASKID = "cprojecttaskid";

  /** �����ż۷��� */
  public static final String CQPBASESCHEMEID = "cqpbaseschemeid";

  /** ���۵�λ */
  public static final String CQTUNITID = "cqtunitid";

  /** �����ȼ� */
  public static final String CQUALITYLEVELID = "cqualitylevelid";

  /** �ջ�����/���� */
  public static final String CRECECOUNTRYID = "crececountryid";

  /** �к� */
  public static final String CROWNO = "crowno";

  /** ������/���� */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /** ��Դ������ϸ */
  public static final String CSOURCEBID = "csourcebid";

  /** ��Դ���� */
  public static final String CSOURCEID = "csourceid";

  /** ��Դ�������� */
  public static final String CSOURCETYPECODE = "csourcetypecode";

  /** ˰�� */
  public static final String CTAXCODEID = "ctaxcodeid";

  /** ��˰��/���� */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /** ����λ */
  public static final String CUNITID = "cunitid";

  /** ��Ӧ�̷����ص� */
  public static final String CVENDDEVADDRID = "cvenddevaddrid";

  /** ��Ӧ�̷������� */
  public static final String CVENDDEVAREAID = "cvenddevareaid";

  /** �������� */
  public static final String DBILLDATE = "dbilldate";

  /** ȷ������ */
  public static final String DCONFIRMDATE = "dconfirmdate";

  /** �������� */
  public static final String DCORRECTDATE = "dcorrectdate";

  /** ԭʼ�ƻ��������� */
  public static final String DORIGPLANARRVDATE = "dorigplanarrvdate";

  /** �ƻ��������� */
  public static final String DPLANARRVDATE = "dplanarrvdate";

  /** dr */
  public static final String DR = "dr";

  /** ʵִ�ɹ����� */
  public static final String FACTPURTYPE = "factpurtype";

  /** �������� */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /** ���� */
  public static final String FISACTIVE = "fisactive";

  /** Ӧִ�ɹ����� */
  public static final String FNEEDPURTYPE = "fneedpurtype";

  /** ��˰��� */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /** ��Ӧ�̽���״̬ */
  public static final String ISTORESTATUS = "istorestatus";

  /** �ۼ��Ѻ������ҿ�Ʊ��� */
  public static final String NACCCANCELINVMNY = "nacccancelinvmny";

  /** �ۼƵ��������� */
  public static final String NACCUMARRVNUM = "naccumarrvnum";

  /** �ۼ����������� */
  public static final String NACCUMDEVNUM = "naccumdevnum";

  /** �ۼƱ��ҿ�Ʊ��� */
  public static final String NACCUMINVOICEMNY = "naccuminvoicemny";

  /** �ۼƿ�Ʊ������ */
  public static final String NACCUMINVOICENUM = "naccuminvoicenum";

  /** �ۼƼ�������� */
  public static final String NACCUMPICKUPNUM = "naccumpickupnum";

  /** �ۼƵ����ƻ������� */
  public static final String NACCUMRPNUM = "naccumrpnum";

  /** �ۼ���������� */
  public static final String NACCUMSTORENUM = "naccumstorenum";

  /** �ۼ�;�������� */
  public static final String NACCUMWASTNUM = "naccumwastnum";

  /** ���� */
  public static final String NASTNUM = "nastnum";

  /** �ۼ��˻������� */
  public static final String NBACKARRVNUM = "nbackarrvnum";

  /** �ۼ��˿������� */
  public static final String NBACKSTORENUM = "nbackstorenum";

  /** �Ƴɱ���� */
  public static final String NCALCOSTMNY = "ncalcostmny";

  /** ��˰��� */
  public static final String NCALTAXMNY = "ncaltaxmny";

  /** �ɵ������� */
  public static final String NCANARRIVENUM = "ncanarrivenum";

  /** ��������� */
  public static final String NCANINNUM = "ncaninnum";

  /** �ɿ�Ʊ���� */
  public static final String NCANINVOICENUM = "ncaninvoicenum";

  /** ȷ�Ͻ�� */
  public static final String NCONFIRMMNY = "nconfirmmny";

  /** ȷ������ */
  public static final String NCONFIRMNUM = "nconfirmnum";

  /** �۱����� */
  public static final String NEXCHANGERATE = "nexchangerate";

  /** �����ۼƿ�Ʊ��� */
  public static final String NFEEMNY = "nfeemny";

  /** ȫ�ֱ�λ�һ��� */
  public static final String NGLOBALEXCHGRATE = "nglobalexchgrate";

  /** ȫ�ֱ�����˰��� */
  public static final String NGLOBALMNY = "nglobalmny";

  /** ȫ�ֱ��Ҽ�˰�ϼ� */
  public static final String NGLOBALTAXMNY = "nglobaltaxmny";

  /** ���ű�λ�һ��� */
  public static final String NGROUPEXCHGRATE = "ngroupexchgrate";

  /** ���ű�����˰��� */
  public static final String NGROUPMNY = "ngroupmny";

  /** ���ű��Ҽ�˰�ϼ� */
  public static final String NGROUPTAXMNY = "ngrouptaxmny";

  /** �ۿ� */
  public static final String NITEMDISCOUNTRATE = "nitemdiscountrate";

  /** ����޼� */
  public static final String NMAXPRICE = "nmaxprice";

  /** ������˰��� */
  public static final String NMNY = "nmny";

  /** ��������˰���� */
  public static final String NNETPRICE = "nnetprice";

  /** δ������ */
  public static final String NNOPAYORGMNY = "nnopayorgmny";

  /** ���ɵֿ�˰�� */
  public static final String NNOSUBTAX = "nnosubtax";

  /** ���ɵֿ�˰�� */
  public static final String NNOSUBTAXRATE = "nnosubtaxrate";

  /** ������ */
  public static final String NNUM = "nnum";

  /** ��˰��� */
  public static final String NORIGMNY = "norigmny";

  /** ����˰���� */
  public static final String NORIGNETPRICE = "norignetprice";

  /** ԭʼ�������� */
  public static final String NORIGORDERNUM = "norigordernum";

  /** ԭʼ��������˰���� */
  public static final String NORIGORDERPRICE = "norigorderprice";

  /** ����˰���� */
  public static final String NORIGPRICE = "norigprice";

  /** ��˰�ϼ� */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /** ����˰���� */
  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";

  /** ����˰���� */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /** ���� */
  public static final String NPACKNUM = "npacknum";

  /** ��������˰���� */
  public static final String NPRICE = "nprice";

  /** ������˰���� */
  public static final String NQTNETPRICE = "nqtnetprice";

  /** ��˰���� */
  public static final String NQTORIGNETPRICE = "nqtorignetprice";

  /** ��˰���� */
  public static final String NQTORIGPRICE = "nqtorigprice";

  /** ��˰���� */
  public static final String NQTORIGTAXNETPRC = "nqtorigtaxnetprc";

  /** ��˰���� */
  public static final String NQTORIGTAXPRICE = "nqtorigtaxprice";

  // /** ˰�� */ 2012-02-08 ����ȥ�����ֶΣ�����˰���Ϊ˰��
  // public static final String NORIGTAX = "norigtax";

  /** ���۱�����˰���� */
  public static final String NQTPRICE = "nqtprice";

  /** ���Һ�˰���� */
  public static final String NQTTAXNETPRICE = "nqttaxnetprice";

  /** ���۱��Һ�˰���� */
  public static final String NQTTAXPRICE = "nqttaxprice";

  /** �������� */
  public static final String NQTUNITNUM = "nqtunitnum";

  // modify by liangchen1 �ۻ����̲���
  /** �ͻ��ƻ����� */
  public static final String NSENDPLANNUM = "nsendplannum";

  /** ��Դ���������� */
  public static final String NSOURCENUM = "nsourcenum";

  /** ��Ԥ������ */
  public static final String NSUPRSNUM = "nsuprsnum";

  /** ����˰�� */
  public static final String NTAX = "ntax";

  /** ���Ҽ�˰�ϼ� */
  public static final String NTAXMNY = "ntaxmny";

  /** �����Һ�˰���� */
  public static final String NTAXNETPRICE = "ntaxnetprice";

  /** �����Һ�˰���� */
  public static final String NTAXPRICE = "ntaxprice";

  /** ˰�� */
  public static final String NTAXRATE = "ntaxrate";

  /** ��� */
  public static final String NVOLUMN = "nvolumn";

  /** ���� */
  public static final String NWEIGHT = "nweight";

  /** Ӧ����֯���°汾 */
  public static final String PK_APFINANCEORG = "pk_apfinanceorg";

  /** Ӧ����֯ */
  public static final String PK_APFINANCEORG_V = "pk_apfinanceorg_v";

  /** ���������������°汾 */
  public static final String PK_APLIABCENTER = "pk_apliabcenter";

  /** ������������ */
  public static final String PK_APLIABCENTER_V = "pk_apliabcenter_v";

  /** �ջ������������°汾 */
  public static final String PK_ARRLIABCENTER = "pk_arrliabcenter";

  /** �ջ��������� */
  public static final String PK_ARRLIABCENTER_V = "pk_arrliabcenter_v";

  /** �ջ������֯���°汾 */
  public static final String PK_ARRVSTOORG = "pk_arrvstoorg";

  /** �ջ������֯ */
  public static final String PK_ARRVSTOORG_V = "pk_arrvstoorg_v";

  /** ���κ����� */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** ���ɿ��ƹ����ӱ� */
  public static final String PK_CENPURULE_B = "pk_cenpurule_b";

  /** �ۿ۹������ */
  public static final String PK_DISCOUNT = "pk_discount";

  /** ������֯���°汾 */
  public static final String PK_FLOWSTOCKORG = "pk_flowstockorg";

  /** ������֯ */
  public static final String PK_FLOWSTOCKORG_V = "pk_flowstockorg_v";

  /** �������� */
  public static final String PK_GROUP = "pk_group";

  /** ���ϰ汾��Ϣ */
  public static final String PK_MATERIAL = "pk_material";

  /** �ɹ�������ϸ */
  public static final String PK_ORDER = "pk_order";

  /** �ɹ�������ϸ */
  public static final String PK_ORDER_B = "pk_order_b";

  /** �ɹ���֯�汾��Ϣ */
  public static final String PK_ORG = "pk_org";

  /** �ɹ���֯ */
  public static final String PK_ORG_V = "pk_org_v";

  /** ���������֯���°汾 */
  public static final String PK_PSFINANCEORG = "pk_psfinanceorg";

  /** ���������֯ */
  public static final String PK_PSFINANCEORG_V = "pk_psfinanceorg_v";

  /** �ջ���ַ */
  public static final String PK_RECEIVEADDRESS = "pk_receiveaddress";

  /** �����ƻ����� */
  public static final String PK_RECEIVEPLAN = "pk_receiveplan";

  /** �ջ��ֿ� */
  public static final String PK_RECVSTORDOC = "pk_recvstordoc";

  /** ����˾ */
  public static final String PK_REQCORP = "pk_reqcorp";

  /** ���������°汾 */
  public static final String PK_REQDEPT = "pk_reqdept";

  /** ������ */
  public static final String PK_REQDEPT_V = "pk_reqdept_v";

  /** ��������֯���°汾 */
  public static final String PK_REQSTOORG = "pk_reqstoorg";

  /** ��������֯ */
  public static final String PK_REQSTOORG_V = "pk_reqstoorg_v";

  /** ����ֿ� */
  public static final String PK_REQSTORDOC = "pk_reqstordoc";

  /** �ų̼ƻ� */
  public static final String PK_SCHEDULE = "pk_schedule";

  /** �ų̼ƻ���ϸ */
  public static final String PK_SCHEDULE_B = "pk_schedule_b";

  /** ������Ϣ */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** �޶���Դ������ϸ */
  public static final String PK_SRCORDER_B = "pk_srcorder_b";

  /** ��Ӧ�� */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** ��Դ������TS */
  public static final String SOURCEBTS = "sourcebts";

  /** ��Դ����TS */
  public static final String SOURCETS = "sourcets";

  /** ts */
  public static final String TS = "ts";

  /** ���κ� */
  public static final String VBATCHCODE = "vbatchcode";

  /** �Զ�����1 */
  public static final String VBDEF1 = "vbdef1";

  /** �Զ�����10 */
  public static final String VBDEF10 = "vbdef10";

  /** �Զ�����11 */
  public static final String VBDEF11 = "vbdef11";

  /** �Զ�����12 */
  public static final String VBDEF12 = "vbdef12";

  /** �Զ�����13 */
  public static final String VBDEF13 = "vbdef13";

  /** �Զ�����14 */
  public static final String VBDEF14 = "vbdef14";

  /** �Զ�����15 */
  public static final String VBDEF15 = "vbdef15";

  /** �Զ�����16 */
  public static final String VBDEF16 = "vbdef16";

  /** �Զ�����17 */
  public static final String VBDEF17 = "vbdef17";

  /** �Զ�����18 */
  public static final String VBDEF18 = "vbdef18";

  /** �Զ�����19 */
  public static final String VBDEF19 = "vbdef19";

  /** �Զ�����2 */
  public static final String VBDEF2 = "vbdef2";

  /** �Զ�����20 */
  public static final String VBDEF20 = "vbdef20";

  /** �Զ�����3 */
  public static final String VBDEF3 = "vbdef3";

  /** �Զ�����4 */
  public static final String VBDEF4 = "vbdef4";

  /** �Զ�����5 */
  public static final String VBDEF5 = "vbdef5";

  /** �Զ�����6 */
  public static final String VBDEF6 = "vbdef6";

  /** �Զ�����7 */
  public static final String VBDEF7 = "vbdef7";

  /** �Զ�����8 */
  public static final String VBDEF8 = "vbdef8";

  /** �Զ�����9 */
  public static final String VBDEF9 = "vbdef9";

  /** ��ע */
  public static final String VBMEMO = "vbmemo";

  /** ������ */
  public static final String VCHANGERATE = "vchangerate";

  /** ��ͬ�� */
  public static final String VCONTRACTCODE = "vcontractcode";

  /** �������񵥾ݺ� */
  public static final String VECBILLCODE = "vecbillcode";

  /** Դͷ���ݺ� */
  public static final String VFIRSTCODE = "vfirstcode";

  /** Դͷ�����к� */
  public static final String VFIRSTROWNO = "vfirstrowno";

  /** Դͷ�������� */
  public static final String VFIRSTTRANTYPE = "vfirsttrantype";

  /** ���ɸ�������1 */
  public static final String VFREE1 = "vfree1";

  /** ���ɸ�������10 */
  public static final String VFREE10 = "vfree10";

  /** ���ɸ�������2 */
  public static final String VFREE2 = "vfree2";

  /** ���ɸ�������3 */
  public static final String VFREE3 = "vfree3";

  /** ���ɸ�������4 */
  public static final String VFREE4 = "vfree4";

  /** ���ɸ�������5 */
  public static final String VFREE5 = "vfree5";

  /** ���ɸ�������6 */
  public static final String VFREE6 = "vfree6";

  /** ���ɸ�������7 */
  public static final String VFREE7 = "vfree7";

  /** ���ɸ�������8 */
  public static final String VFREE8 = "vfree8";

  /** ���ɸ�������9 */
  public static final String VFREE9 = "vfree9";

  /** �۸��������� */
  public static final String VPRICEAUDITCODE = "vpriceauditcode";

  /** ���ۻ����� */
  public static final String VQTUNITRATE = "vqtunitrate";

  /** ��Դ���ݺ� */
  public static final String VSOURCECODE = "vsourcecode";

  /** ��Դ�����к� */
  public static final String VSOURCEROWNO = "vsourcerowno";

  /** ��Դ�������� */
  public static final String VSOURCETRANTYPE = "vsourcetrantype";

  /** ��Ӧ�̷�����ַ */
  public static final String VVENDDEVADDR = "vvenddevaddr";

  /** ��Ӧ���ϱ��� */
  public static final String VVENDINVENTORYCODE = "vvendinventorycode";

  /** ��Ӧ�������� */
  public static final String VVENDINVENTORYNAME = "vvendinventoryname";

  /** �Է������� */
  public static final String VVENDORORDERCODE = "vvendorordercode";

  /** �Է������к� */
  public static final String VVENDORORDERROW = "vvendororderrow";

  private static final long serialVersionUID = -8877575473271036743L;

  /** �����ر� getter ���� */
  public UFBoolean getBarriveclose() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BARRIVECLOSE);
  }

  /** ����ת�ɹ� getter ���� */
  public UFBoolean getBborrowpur() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BBORROWPUR);
  }

  /** ��Ʊ�ر� getter ���� */
  public UFBoolean getBinvoiceclose() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BINVOICECLOSE);
  }

  /** �Ƿ���Ʒ getter ���� */
  public UFBoolean getBlargess() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BLARGESS);
  }

  /** ����ر� getter ���� */
  public UFBoolean getBpayclose() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BPAYCLOSE);
  }

  /** ���ڵ����ƻ� getter ���� */
  public UFBoolean getBreceiveplan() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BRECEIVEPLAN);
  }

  /** ���ر� getter ���� */
  public UFBoolean getBstockclose() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BSTOCKCLOSE);
  }

  /** �Ƿ�����ر� getter ���� */
  public UFBoolean getBtransclosed() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BTRANSCLOSED);
  }

  /** ����ó�� getter ���� */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BTRIATRADEFLAG);
  }

  /** �ͻ� getter ���� */
  public String getCasscustid() {
    return (String) this.getAttributeValue(OrderItemVO.CASSCUSTID);
  }

  /** ��λ getter ���� */
  public String getCastunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CASTUNITID);
  }

  /** ��ͬ��Ϣ getter ���� */
  public String getCcontractid() {
    return (String) this.getAttributeValue(OrderItemVO.CCONTRACTID);
  }

  /** ��ͬ��ϸ getter ���� */
  public String getCcontractrowid() {
    return (String) this.getAttributeValue(OrderItemVO.CCONTRACTROWID);
  }

  /** ���ұ��� getter ���� */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(OrderItemVO.CCURRENCYID);
  }

  /** Ŀ�ĵص��� getter ���� */
  public String getCdestiareaid() {
    return (String) this.getAttributeValue(OrderItemVO.CDESTIAREAID);
  }

  /** Ŀ�ĵع� getter ���� */
  public String getCdesticountryid() {
    return (String) this.getAttributeValue(OrderItemVO.CDESTICOUNTRYID);
  }

  /** �ջ��ص� getter ���� */
  public String getCdevaddrid() {
    return (String) this.getAttributeValue(OrderItemVO.CDEVADDRID);
  }

  /** �ջ����� getter ���� */
  public String getCdevareaid() {
    return (String) this.getAttributeValue(OrderItemVO.CDEVAREAID);
  }

  /** �������񵥾���ϸ getter ���� */
  public String getCecbillbid() {
    return (String) this.getAttributeValue(OrderItemVO.CECBILLBID);
  }

  /** �������񵥾� getter ���� */
  public String getCecbillid() {
    return (String) this.getAttributeValue(OrderItemVO.CECBILLID);
  }

  /** �������񵥾����� getter ���� */
  public String getCectypecode() {
    return (String) this.getAttributeValue(OrderItemVO.CECTYPECODE);
  }

  /** ������ **/
  public String getCffileid() {
    return (String) this.getAttributeValue(OrderItemVO.CFFILEID);
  }

  /** Դͷ������ϸ getter ���� */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(OrderItemVO.CFIRSTBID);
  }

  /** Դͷ���� getter ���� */
  public String getCfirstid() {
    return (String) this.getAttributeValue(OrderItemVO.CFIRSTID);
  }

  /** Դͷ�������� getter ���� */
  public String getCfirsttypecode() {
    return (String) this.getAttributeValue(OrderItemVO.CFIRSTTYPECODE);
  }

  /** ����Ա getter ���� */
  public String getChandler() {
    return (String) this.getAttributeValue(OrderItemVO.CHANDLER);
  }

  /** ԭ������ getter ���� */
  public String getCorigareaid() {
    return (String) this.getAttributeValue(OrderItemVO.CORIGAREAID);
  }

  /** ԭ���� getter ���� */
  public String getCorigcountryid() {
    return (String) this.getAttributeValue(OrderItemVO.CORIGCOUNTRYID);
  }

  /** ���� getter ���� */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(OrderItemVO.CORIGCURRENCYID);
  }

  /** �빺���������� getter ���� */
  public String getCpraybillbid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRAYBILLBID);
  }

  /** �빺���� getter ���� */
  public String getCpraybillcode() {
    return (String) this.getAttributeValue(OrderItemVO.CPRAYBILLCODE);
  }

  /** �빺����ͷ���� getter ���� */
  public String getCpraybillhid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRAYBILLHID);
  }

  /** �빺���к� getter ���� */
  public String getCpraybillrowno() {
    return (String) this.getAttributeValue(OrderItemVO.CPRAYBILLROWNO);
  }

  /** �빺���������� */
  public String getCpraytypecode() {
    return (String) this.getAttributeValue(OrderItemVO.CPRAYTYPECODE);
  }

  /** �۸�������������ӱ� getter ���� */
  public String getCpriceaudit_bb1id() {
    return (String) this.getAttributeValue(OrderItemVO.CPRICEAUDIT_BB1ID);
  }

  /** �۸������������ϸ getter ���� */
  public String getCpriceaudit_bid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRICEAUDIT_BID);
  }

  /** �۸������� getter ���� */
  public String getCpriceauditid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRICEAUDITID);
  }

  /** �������� getter ���� */
  public String getCproductorid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRODUCTORID);
  }

  /** ��Ŀ getter ���� */
  public String getCprojectid() {
    return (String) this.getAttributeValue(OrderItemVO.CPROJECTID);
  }

  /** ��Ŀ���� getter ���� */
  public String getCprojecttaskid() {
    return (String) this.getAttributeValue(OrderItemVO.CPROJECTTASKID);
  }

  /** �����ż۷��� getter ���� */
  public String getCqpbaseschemeid() {
    return (String) this.getAttributeValue(OrderItemVO.CQPBASESCHEMEID);
  }

  /** ���۵�λ getter ���� */
  public String getCqtunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CQTUNITID);
  }

  /** �����ȼ� getter ���� */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(OrderItemVO.CQUALITYLEVELID);
  }

  /** �ջ�����/���� getter ���� */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(OrderItemVO.CRECECOUNTRYID);
  }

  /** �к� getter ���� */
  public String getCrowno() {
    return (String) this.getAttributeValue(OrderItemVO.CROWNO);
  }

  /** ������/���� getter ���� */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(OrderItemVO.CSENDCOUNTRYID);
  }

  /** ��Դ������ϸ getter ���� */
  public String getCsourcebid() {
    return (String) this.getAttributeValue(OrderItemVO.CSOURCEBID);
  }

  /** ��Դ���� getter ���� */
  public String getCsourceid() {
    return (String) this.getAttributeValue(OrderItemVO.CSOURCEID);
  }

  /** ��Դ�������� getter ���� */
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(OrderItemVO.CSOURCETYPECODE);
  }

  /** ˰�� getter ���� */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(OrderItemVO.CTAXCODEID);
  }

  /** ��˰��/���� getter ���� */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(OrderItemVO.CTAXCOUNTRYID);
  }

  /** ����λ getter ���� */
  public String getCunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CUNITID);
  }

  /** ��Ӧ�̷����ص� getter ���� */
  public String getCvenddevaddrid() {
    return (String) this.getAttributeValue(OrderItemVO.CVENDDEVADDRID);
  }

  /** ��Ӧ�̷������� getter ���� */
  public String getCvenddevareaid() {
    return (String) this.getAttributeValue(OrderItemVO.CVENDDEVAREAID);
  }

  /** �������� getter ���� */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(OrderItemVO.DBILLDATE);
  }

  /** ȷ������ getter ���� */
  public UFDate getDconfirmdate() {
    return (UFDate) this.getAttributeValue(OrderItemVO.DCONFIRMDATE);
  }

  /** �������� getter ���� */
  public UFDate getDcorrectdate() {
    return (UFDate) this.getAttributeValue(OrderItemVO.DCORRECTDATE);
  }

  /** ԭʼ�ƻ��������� getter ���� */
  public UFDate getDorigplanarrvdate() {
    return (UFDate) this.getAttributeValue(OrderItemVO.DORIGPLANARRVDATE);
  }

  /** �ƻ��������� getter ���� */
  public UFDate getDplanarrvdate() {
    return (UFDate) this.getAttributeValue(OrderItemVO.DPLANARRVDATE);
  }

  /** dr getter ���� */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(OrderItemVO.DR);
  }

  /** ʵִ�ɹ����� getter ���� */
  public Integer getFactpurtype() {
    return (Integer) this.getAttributeValue(OrderItemVO.FACTPURTYPE);
  }

  /** �������� getter ���� */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(OrderItemVO.FBUYSELLFLAG);
  }

  /** ���� getter ���� */
  public Integer getFisactive() {
    return (Integer) this.getAttributeValue(OrderItemVO.FISACTIVE);
  }

  /** Ӧִ�ɹ����� getter���� */
  public Integer getFneedpurtype() {
    return (Integer) this.getAttributeValue(OrderItemVO.FNEEDPURTYPE);
  }

  /** ��˰��� getter ���� */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(OrderItemVO.FTAXTYPEFLAG);
  }

  /** ��Ӧ�̽���״̬ getter ���� */
  public Integer getIstorestatus() {
    return (Integer) this.getAttributeValue(OrderItemVO.ISTORESTATUS);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M21_B);
  }

  /** �ۼ��Ѻ������ҿ�Ʊ��� getter ���� */
  public UFDouble getNacccancelinvmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCCANCELINVMNY);
  }

  /** �ۼƵ��������� getter ���� */
  public UFDouble getNaccumarrvnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMARRVNUM);
  }

  /** �ۼ����������� getter ���� */
  public UFDouble getNaccumdevnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMDEVNUM);
  }

  /** �ۼƱ��ҿ�Ʊ��� getter ���� */
  public UFDouble getNaccuminvoicemny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMINVOICEMNY);
  }

  /** �ۼƿ�Ʊ������ getter ���� */
  public UFDouble getNaccuminvoicenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMINVOICENUM);
  }

  /** �ۼƼ�������� getter ���� */
  public UFDouble getNaccumpickupnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMPICKUPNUM);
  }

  /** �ۼƵ����ƻ������� getter ���� */
  public UFDouble getNaccumrpnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMRPNUM);
  }

  /** �ۼ���������� getter ���� */
  public UFDouble getNaccumstorenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMSTORENUM);
  }

  /** �ۼ�;�������� getter ���� */
  public UFDouble getNaccumwastnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMWASTNUM);
  }

  /** ���� getter ���� */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NASTNUM);
  }

  /** �ۼ��˻������� getter ���� */
  public UFDouble getNbackarrvnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NBACKARRVNUM);
  }

  /** �ۼ��˿������� getter ���� */
  public UFDouble getNbackstorenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NBACKSTORENUM);
  }

  /** �Ƴɱ���� getter ���� */
  public UFDouble getNcalcostmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCALCOSTMNY);
  }

  /** ��˰��� getter ���� */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCALTAXMNY);
  }

  /** �ɵ������� getter ���� */
  public UFDouble getNcanarrivenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCANARRIVENUM);
  }

  /** ��������� getter ���� */
  public UFDouble getNcaninnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCANINNUM);
  }

  /** �ɿ�Ʊ���� getter ���� */
  public UFDouble getNcaninvoicenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCANINVOICENUM);
  }

  /** ȷ�Ͻ�� getter ���� */
  public UFDouble getNconfirmmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCONFIRMMNY);
  }

  /** ȷ������ getter ���� */
  public UFDouble getNconfirmnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCONFIRMNUM);
  }

  /** �۱����� getter ���� */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NEXCHANGERATE);
  }

  /** �����ۼƿ�Ʊ��� getter ���� */
  public UFDouble getNfeemny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NFEEMNY);
  }

  /** ȫ�ֱ�λ�һ��� getter ���� */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NGLOBALEXCHGRATE);
  }

  /** ȫ�ֱ�����˰��� getter ���� */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NGLOBALMNY);
  }

  /** ȫ�ֱ��Ҽ�˰�ϼ� getter ���� */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NGLOBALTAXMNY);
  }

  /** ���ű�λ�һ��� getter ���� */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NGROUPEXCHGRATE);
  }

  /** ���ű�����˰��� getter ���� */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NGROUPMNY);
  }

  /** ���ű��Ҽ�˰�ϼ� getter ���� */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NGROUPTAXMNY);
  }

  /** �ۿ� getter ���� */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NITEMDISCOUNTRATE);
  }

  /** ����޼� getter ���� */
  public UFDouble getNmaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NMAXPRICE);
  }

  /** ������˰��� getter ���� */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NMNY);
  }

  /** ��������˰���� getter ���� */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNETPRICE);
  }

  /** δ������ getter ���� */
  public UFDouble getNnopayorgmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNOPAYORGMNY);
  }

  /** ���ɵֿ�˰�� getter ���� */
  public UFDouble getNnosubtax() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNOSUBTAX);
  }

  /** ���ɵֿ�˰�� getter ���� */
  public UFDouble getNnosubtaxrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNOSUBTAXRATE);
  }

  /** ������ getter ���� */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNUM);
  }

  /** ��˰��� getter ���� */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGMNY);
  }

  /** ����˰���� getter ���� */
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGNETPRICE);
  }

  /** ԭʼ�������� getter ���� */
  public UFDouble getNorigordernum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGORDERNUM);
  }

  /** ԭʼ��������˰���� getter ���� */
  public UFDouble getNorigorderprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGORDERPRICE);
  }

  /** ����˰���� getter ���� */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGPRICE);
  }

  /** ��˰�ϼ� getter ���� */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAXMNY);
  }

  /** ����˰���� getter ���� */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAXNETPRICE);
  }

  /** ����˰���� getter ���� */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAXPRICE);
  }

  /** ���� getter ���� */
  public UFDouble getNpacknum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NPACKNUM);
  }

  /** ��������˰���� getter ���� */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NPRICE);
  }

  /** ������˰���� getter ���� */
  public UFDouble getNqtnetprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTNETPRICE);
  }

  /** ��˰���� getter ���� */
  public UFDouble getNqtorignetprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTORIGNETPRICE);
  }

  // /** ˰�� getter ���� */
  // public UFDouble getNorigtax() {
  // return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAX);
  // }

  /** ��˰���� getter ���� */
  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTORIGPRICE);
  }

  /** ��˰���� getter ���� */
  public UFDouble getNqtorigtaxnetprc() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTORIGTAXNETPRC);
  }

  /** ��˰���� getter ���� */
  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTORIGTAXPRICE);
  }

  /** ���۱�����˰���� getter ���� */
  public UFDouble getNqtprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTPRICE);
  }

  /** ���Һ�˰���� getter ���� */
  public UFDouble getNqttaxnetprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTTAXNETPRICE);
  }

  /** ���۱��Һ�˰���� getter ���� */
  public UFDouble getNqttaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTTAXPRICE);
  }

  /** �������� getter ���� */
  public UFDouble getNqtunitnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTUNITNUM);
  }

  // modify by liangchen1 �ۻ����̲���
  /** �ͻ��ƻ�����getter ���� */
  public UFDouble getNsendplannum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NSENDPLANNUM);
  }

  /** ��Դ���������� getter ���� */
  public UFDouble getNsourcenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NSOURCENUM);
  }

  /** ��Ԥ������ getter ���� */
  public UFDouble getNsuprsnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NSUPRSNUM);
  }

  /** ����˰�� getter ���� */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAX);
  }

  /** ���Ҽ�˰�ϼ� getter ���� */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAXMNY);
  }

  /** �����Һ�˰���� getter ���� */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAXNETPRICE);
  }

  /** �����Һ�˰���� getter ���� */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAXPRICE);
  }

  /** ˰�� getter ���� */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAXRATE);
  }

  /** ��� getter ���� */
  public UFDouble getNvolumn() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NVOLUMN);
  }

  /** ���� getter ���� */
  public UFDouble getNweight() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NWEIGHT);
  }

  /** Ӧ����֯���°汾 getter ���� */
  public String getPk_apfinanceorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_APFINANCEORG);
  }

  /** Ӧ����֯ getter ���� */
  public String getPk_apfinanceorg_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_APFINANCEORG_V);
  }

  /** ���������������°汾 getter ���� */
  public String getPk_apliabcenter() {
    return (String) this.getAttributeValue(OrderItemVO.PK_APLIABCENTER);
  }

  /** ������������ getter ���� */
  public String getPk_apliabcenter_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_APLIABCENTER_V);
  }

  /** �ջ������������°汾 getter ���� */
  public String getPk_arrliabcenter() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRLIABCENTER);
  }

  /** �ջ��������� getter ���� */
  public String getPk_arrliabcenter_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRLIABCENTER_V);
  }

  /** �ջ������֯���°汾 getter ���� */
  public String getPk_arrvstoorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRVSTOORG);
  }

  /** �ջ������֯ getter ���� */
  public String getPk_arrvstoorg_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRVSTOORG_V);
  }

  /** ���κ����� getter ���� */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(OrderItemVO.PK_BATCHCODE);
  }

  /** ���ɿ��ƹ����ӱ� getter ���� */
  public String getPk_cenpurule_b() {
    return (String) this.getAttributeValue(OrderItemVO.PK_CENPURULE_B);
  }

  /** �ۿ۹������ getter ���� */
  public String getPk_discount() {
    return (String) this.getAttributeValue(OrderItemVO.PK_DISCOUNT);
  }

  /** ������֯���°汾 getter ���� */
  public String getPk_flowstockorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_FLOWSTOCKORG);
  }

  /** ������֯ getter ���� */
  public String getPk_flowstockorg_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_FLOWSTOCKORG_V);
  }

  /** �������� getter ���� */
  public String getPk_group() {
    return (String) this.getAttributeValue(OrderItemVO.PK_GROUP);
  }

  /** ���ϰ汾��Ϣ getter ���� */
  public String getPk_material() {
    return (String) this.getAttributeValue(OrderItemVO.PK_MATERIAL);
  }

  /** �ɹ�������ϸ getter ���� */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER);
  }

  /** �ɹ�������ϸ getter ���� */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER_B);
  }

  /** �ɹ���֯�汾��Ϣ getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORG);
  }

  /** �ɹ���֯ getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORG_V);
  }

  /** ���������֯���°汾 getter ���� */
  public String getPk_psfinanceorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_PSFINANCEORG);
  }

  /** ���������֯ getter ���� */
  public String getPk_psfinanceorg_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_PSFINANCEORG_V);
  }

  /** �ջ���ַ getter ���� */
  public String getPk_receiveaddress() {
    return (String) this.getAttributeValue(OrderItemVO.PK_RECEIVEADDRESS);
  }

  /** �����ƻ����� getter ���� */
  public String getPk_receiveplan() {
    return (String) this.getAttributeValue(OrderItemVO.PK_RECEIVEPLAN);
  }

  /** �ջ��ֿ� getter ���� */
  public String getPk_recvstordoc() {
    return (String) this.getAttributeValue(OrderItemVO.PK_RECVSTORDOC);
  }

  /** ����˾ getter ���� */
  public String getPk_reqcorp() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQCORP);
  }

  /** ���������°汾 getter ���� */
  public String getPk_reqdept() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQDEPT);
  }

  /** ������ getter ���� */
  public String getPk_reqdept_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQDEPT_V);
  }

  /** ��������֯���°汾 getter ���� */
  public String getPk_reqstoorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQSTOORG);
  }

  /** ��������֯ getter ���� */
  public String getPk_reqstoorg_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQSTOORG_V);
  }

  /** ����ֿ� getter ���� */
  public String getPk_reqstordoc() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQSTORDOC);
  }

  /** �ų̼ƻ� getter ���� */
  public String getPk_schedule() {
    return (String) this.getAttributeValue(OrderItemVO.PK_SCHEDULE);
  }

  /** �ų̼ƻ���ϸ getter ���� */
  public String getPk_schedule_b() {
    return (String) this.getAttributeValue(OrderItemVO.PK_SCHEDULE_B);
  }

  /** ������Ϣ getter ���� */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(OrderItemVO.PK_SRCMATERIAL);
  }

  /** �޶���Դ������ϸ getter ���� */
  public String getPk_srcorder_b() {
    return (String) this.getAttributeValue(OrderItemVO.PK_SRCORDER_B);
  }

  /** ��Ӧ�� getter ���� */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(OrderItemVO.PK_SUPPLIER);
  }

  /** ��Դ������TS getter ���� */
  public UFDateTime getSourcebts() {
    return (UFDateTime) this.getAttributeValue(OrderItemVO.SOURCEBTS);
  }

  /** ��Դ����TS getter ���� */
  public UFDateTime getSourcets() {
    return (UFDateTime) this.getAttributeValue(OrderItemVO.SOURCETS);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(OrderItemVO.TS);
  }

  /** ���κ� getter ���� */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(OrderItemVO.VBATCHCODE);
  }

  /** �Զ�����1 getter ���� */
  public String getVbdef1() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF1);
  }

  /** �Զ�����10 getter ���� */
  public String getVbdef10() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF10);
  }

  /** �Զ�����11 getter ���� */
  public String getVbdef11() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF11);
  }

  /** �Զ�����12 getter ���� */
  public String getVbdef12() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF12);
  }

  /** �Զ�����13 getter ���� */
  public String getVbdef13() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF13);
  }

  /** �Զ�����14 getter ���� */
  public String getVbdef14() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF14);
  }

  /** �Զ�����15 getter ���� */
  public String getVbdef15() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF15);
  }

  /** �Զ�����16 getter ���� */
  public String getVbdef16() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF16);
  }

  /** �Զ�����17 getter ���� */
  public String getVbdef17() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF17);
  }

  /** �Զ�����18 getter ���� */
  public String getVbdef18() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF18);
  }

  /** �Զ�����19 getter ���� */
  public String getVbdef19() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF19);
  }

  /** �Զ�����2 getter ���� */
  public String getVbdef2() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF2);
  }

  /** �Զ�����20 getter ���� */
  public String getVbdef20() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF20);
  }

  /** �Զ�����3 getter ���� */
  public String getVbdef3() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF3);
  }

  /** �Զ�����4 getter ���� */
  public String getVbdef4() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF4);
  }

  /** �Զ�����5 getter ���� */
  public String getVbdef5() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF5);
  }

  /** �Զ�����6 getter ���� */
  public String getVbdef6() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF6);
  }

  /** �Զ�����7 getter ���� */
  public String getVbdef7() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF7);
  }

  /** �Զ�����8 getter ���� */
  public String getVbdef8() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF8);
  }

  /** �Զ�����9 getter ���� */
  public String getVbdef9() {
    return (String) this.getAttributeValue(OrderItemVO.VBDEF9);
  }

  /** ��ע getter ���� */
  public String getVbmemo() {
    return (String) this.getAttributeValue(OrderItemVO.VBMEMO);
  }

  /** ������ getter ���� */
  public String getVchangerate() {
    return (String) this.getAttributeValue(OrderItemVO.VCHANGERATE);
  }

  /** ��ͬ�� getter ���� */
  public String getVcontractcode() {
    return (String) this.getAttributeValue(OrderItemVO.VCONTRACTCODE);
  }

  /** �������񵥾ݺ� getter ���� */
  public String getVecbillcode() {
    return (String) this.getAttributeValue(OrderItemVO.VECBILLCODE);
  }

  /** Դͷ���ݺ� getter ���� */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(OrderItemVO.VFIRSTCODE);
  }

  /** Դͷ�����к� getter ���� */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(OrderItemVO.VFIRSTROWNO);
  }

  /** Դͷ�������� getter ���� */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(OrderItemVO.VFIRSTTRANTYPE);
  }

  /** ���ɸ�������1 getter ���� */
  public String getVfree1() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE1);
  }

  /** ���ɸ�������10 getter ���� */
  public String getVfree10() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE10);
  }

  /** ���ɸ�������2 getter ���� */
  public String getVfree2() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE2);
  }

  /** ���ɸ�������3 getter ���� */
  public String getVfree3() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE3);
  }

  /** ���ɸ�������4 getter ���� */
  public String getVfree4() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE4);
  }

  /** ���ɸ�������5 getter ���� */
  public String getVfree5() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE5);
  }

  /** ���ɸ�������6 getter ���� */
  public String getVfree6() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE6);
  }

  /** ���ɸ�������7 getter ���� */
  public String getVfree7() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE7);
  }

  /** ���ɸ�������8 getter ���� */
  public String getVfree8() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE8);
  }

  /** ���ɸ�������9 getter ���� */
  public String getVfree9() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE9);
  }

  /** �۸��������� getter ���� */
  public String getVpriceauditcode() {
    return (String) this.getAttributeValue(OrderItemVO.VPRICEAUDITCODE);
  }

  /** ���ۻ����� getter ���� */
  public String getVqtunitrate() {
    return (String) this.getAttributeValue(OrderItemVO.VQTUNITRATE);
  }

  /** ��Դ���ݺ� getter ���� */
  public String getVsourcecode() {
    return (String) this.getAttributeValue(OrderItemVO.VSOURCECODE);
  }

  /** ��Դ�����к� getter ���� */
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(OrderItemVO.VSOURCEROWNO);
  }

  /** ��Դ�������� getter ���� */
  public String getVsourcetrantype() {
    return (String) this.getAttributeValue(OrderItemVO.VSOURCETRANTYPE);
  }

  /** ��Ӧ�̷�����ַ getter ���� */
  public String getVvenddevaddr() {
    return (String) this.getAttributeValue(OrderItemVO.VVENDDEVADDR);
  }

  /** ��Ӧ���ϱ��� getter ���� */
  public String getVvendinventorycode() {
    return (String) this.getAttributeValue(OrderItemVO.VVENDINVENTORYCODE);
  }

  /** ��Ӧ�������� getter ���� */
  public String getVvendinventoryname() {
    return (String) this.getAttributeValue(OrderItemVO.VVENDINVENTORYNAME);
  }

  /** �Է������� getter ���� */
  public String getVvendorordercode() {
    return (String) this.getAttributeValue(OrderItemVO.VVENDORORDERCODE);
  }

  /** �Է������к� getter ���� */
  public String getVvendororderrow() {
    return (String) this.getAttributeValue(OrderItemVO.VVENDORORDERROW);
  }

  /** �����ر� setter ���� */
  public void setBarriveclose(UFBoolean barriveclose) {
    this.setAttributeValue(OrderItemVO.BARRIVECLOSE, barriveclose);
  }

  /** ����ת�ɹ� setter ���� */
  public void setBborrowpur(UFBoolean bborrowpur) {
    this.setAttributeValue(OrderItemVO.BBORROWPUR, bborrowpur);
  }

  /** ��Ʊ�ر� setter ���� */
  public void setBinvoiceclose(UFBoolean binvoiceclose) {
    this.setAttributeValue(OrderItemVO.BINVOICECLOSE, binvoiceclose);
  }

  /** �Ƿ���Ʒ setter ���� */
  public void setBlargess(UFBoolean blargess) {
    this.setAttributeValue(OrderItemVO.BLARGESS, blargess);
  }

  /** ����ر� setter ���� */
  public void setBpayclose(UFBoolean bpayclose) {
    this.setAttributeValue(OrderItemVO.BPAYCLOSE, bpayclose);
  }

  /** ���ڵ����ƻ� setter ���� */
  public void setBreceiveplan(UFBoolean breceiveplan) {
    this.setAttributeValue(OrderItemVO.BRECEIVEPLAN, breceiveplan);
  }

  /** ���ر� setter ���� */
  public void setBstockclose(UFBoolean bstockclose) {
    this.setAttributeValue(OrderItemVO.BSTOCKCLOSE, bstockclose);
  }

  /** �Ƿ�����ر� setter ���� */
  public void setBtransclosed(UFBoolean btransclosed) {
    this.setAttributeValue(OrderItemVO.BTRANSCLOSED, btransclosed);
  }

  /** ����ó�� setter ���� */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(OrderItemVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /** �ͻ� setter ���� */
  public void setCasscustid(String casscustid) {
    this.setAttributeValue(OrderItemVO.CASSCUSTID, casscustid);
  }

  /** ��λ setter ���� */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(OrderItemVO.CASTUNITID, castunitid);
  }

  /** ��ͬ��Ϣ setter ���� */
  public void setCcontractid(String ccontractid) {
    this.setAttributeValue(OrderItemVO.CCONTRACTID, ccontractid);
  }

  /** ��ͬ��ϸ setter ���� */
  public void setCcontractrowid(String ccontractrowid) {
    this.setAttributeValue(OrderItemVO.CCONTRACTROWID, ccontractrowid);
  }

  /** ���ұ��� setter ���� */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(OrderItemVO.CCURRENCYID, ccurrencyid);
  }

  /** Ŀ�ĵص��� setter ���� */
  public void setCdestiareaid(String cdestiareaid) {
    this.setAttributeValue(OrderItemVO.CDESTIAREAID, cdestiareaid);
  }

  /** Ŀ�ĵع� setter ���� */
  public void setCdesticountryid(String cdesticountryid) {
    this.setAttributeValue(OrderItemVO.CDESTICOUNTRYID, cdesticountryid);
  }

  /** �ջ��ص� setter ���� */
  public void setCdevaddrid(String cdevaddrid) {
    this.setAttributeValue(OrderItemVO.CDEVADDRID, cdevaddrid);
  }

  /** �ջ����� setter ���� */
  public void setCdevareaid(String cdevareaid) {
    this.setAttributeValue(OrderItemVO.CDEVAREAID, cdevareaid);
  }

  /** �������񵥾���ϸ setter ���� */
  public void setCecbillbid(String cecbillbid) {
    this.setAttributeValue(OrderItemVO.CECBILLBID, cecbillbid);
  }

  /** �������񵥾� setter ���� */
  public void setCecbillid(String cecbillid) {
    this.setAttributeValue(OrderItemVO.CECBILLID, cecbillid);
  }

  /** �������񵥾����� setter ���� */
  public void setCectypecode(String cectypecode) {
    this.setAttributeValue(OrderItemVO.CECTYPECODE, cectypecode);
  }

  /** ������ **/
  public void setCffileid(String cffileid) {
    this.setAttributeValue(OrderItemVO.CFFILEID, cffileid);
  }

  /** Դͷ������ϸ setter ���� */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(OrderItemVO.CFIRSTBID, cfirstbid);
  }

  /** Դͷ���� setter ���� */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(OrderItemVO.CFIRSTID, cfirstid);
  }

  /** Դͷ�������� setter ���� */
  public void setCfirsttypecode(String cfirsttypecode) {
    this.setAttributeValue(OrderItemVO.CFIRSTTYPECODE, cfirsttypecode);
  }

  /** ����Ա setter ���� */
  public void setChandler(String chandler) {
    this.setAttributeValue(OrderItemVO.CHANDLER, chandler);
  }

  /** ԭ������ setter ���� */
  public void setCorigareaid(String corigareaid) {
    this.setAttributeValue(OrderItemVO.CORIGAREAID, corigareaid);
  }

  /** ԭ���� setter ���� */
  public void setCorigcountryid(String corigcountryid) {
    this.setAttributeValue(OrderItemVO.CORIGCOUNTRYID, corigcountryid);
  }

  /** ���� setter ���� */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(OrderItemVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /** �빺���������� setter ���� */
  public void setCpraybillbid(String cpraybillbid) {
    this.setAttributeValue(OrderItemVO.CPRAYBILLBID, cpraybillbid);
  }

  /** �빺���� setter ���� */
  public void setCpraybillcode(String cpraybillcode) {
    this.setAttributeValue(OrderItemVO.CPRAYBILLCODE, cpraybillcode);
  }

  /** �빺����ͷ���� setter ���� */
  public void setCpraybillhid(String cpraybillhid) {
    this.setAttributeValue(OrderItemVO.CPRAYBILLHID, cpraybillhid);
  }

  /** �빺���к� setter ���� */
  public void setCpraybillrowno(String cpraybillrowno) {
    this.setAttributeValue(OrderItemVO.CPRAYBILLROWNO, cpraybillrowno);
  }

  /** �빺���������� */
  public void setCpraytypecode(String cpraytypecode) {
    this.setAttributeValue(OrderItemVO.CPRAYTYPECODE, cpraytypecode);
  }

  /** �۸�������������ӱ� setter ���� */
  public void setCpriceaudit_bb1id(String cpriceaudit_bb1id) {
    this.setAttributeValue(OrderItemVO.CPRICEAUDIT_BB1ID, cpriceaudit_bb1id);
  }

  /** �۸������������ϸ setter ���� */
  public void setCpriceaudit_bid(String cpriceaudit_bid) {
    this.setAttributeValue(OrderItemVO.CPRICEAUDIT_BID, cpriceaudit_bid);
  }

  /** �۸������� setter ���� */
  public void setCpriceauditid(String cpriceauditid) {
    this.setAttributeValue(OrderItemVO.CPRICEAUDITID, cpriceauditid);
  }

  /** �������� setter ���� */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(OrderItemVO.CPRODUCTORID, cproductorid);
  }

  /** ��Ŀ setter ���� */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(OrderItemVO.CPROJECTID, cprojectid);
  }

  /** ��Ŀ���� setter ���� */
  public void setCprojecttaskid(String cprojecttaskid) {
    this.setAttributeValue(OrderItemVO.CPROJECTTASKID, cprojecttaskid);
  }

  /** �����ż۷��� setter ���� */
  public void setCqpbaseschemeid(String cqpbaseschemeid) {
    this.setAttributeValue(OrderItemVO.CQPBASESCHEMEID, cqpbaseschemeid);
  }

  /** ���۵�λ setter ���� */
  public void setCqtunitid(String cqtunitid) {
    this.setAttributeValue(OrderItemVO.CQTUNITID, cqtunitid);
  }

  /** �����ȼ� setter ���� */
  public void setCqualitylevelid(String cqualitylevelid) {
    this.setAttributeValue(OrderItemVO.CQUALITYLEVELID, cqualitylevelid);
  }

  /** �ջ�����/���� setter ���� */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(OrderItemVO.CRECECOUNTRYID, crececountryid);
  }

  /** �к� setter ���� */
  public void setCrowno(String crowno) {
    this.setAttributeValue(OrderItemVO.CROWNO, crowno);
  }

  /** ������/���� setter ���� */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(OrderItemVO.CSENDCOUNTRYID, csendcountryid);
  }

  /** ��Դ������ϸ setter ���� */
  public void setCsourcebid(String csourcebid) {
    this.setAttributeValue(OrderItemVO.CSOURCEBID, csourcebid);
  }

  /** ��Դ���� setter ���� */
  public void setCsourceid(String csourceid) {
    this.setAttributeValue(OrderItemVO.CSOURCEID, csourceid);
  }

  /** ��Դ�������� setter ���� */
  public void setCsourcetypecode(String csourcetypecode) {
    this.setAttributeValue(OrderItemVO.CSOURCETYPECODE, csourcetypecode);
  }

  /** ˰�� setter ���� */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(OrderItemVO.CTAXCODEID, ctaxcodeid);
  }

  /** ��˰��/���� setter ���� */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(OrderItemVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /** ����λ setter ���� */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(OrderItemVO.CUNITID, cunitid);
  }

  /** ��Ӧ�̷����ص� setter ���� */
  public void setCvenddevaddrid(String cvenddevaddrid) {
    this.setAttributeValue(OrderItemVO.CVENDDEVADDRID, cvenddevaddrid);
  }

  /** ��Ӧ�̷������� setter ���� */
  public void setCvenddevareaid(String cvenddevareaid) {
    this.setAttributeValue(OrderItemVO.CVENDDEVAREAID, cvenddevareaid);
  }

  /** �������� setter ���� */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(OrderItemVO.DBILLDATE, dbilldate);
  }

  /** ȷ������ setter ���� */
  public void setDconfirmdate(UFDate dconfirmdate) {
    this.setAttributeValue(OrderItemVO.DCONFIRMDATE, dconfirmdate);
  }

  /** �������� setter ���� */
  public void setDcorrectdate(UFDate dcorrectdate) {
    this.setAttributeValue(OrderItemVO.DCORRECTDATE, dcorrectdate);
  }

  /** ԭʼ�ƻ��������� setter ���� */
  public void setDorigplanarrvdate(UFDate dorigplanarrvdate) {
    this.setAttributeValue(OrderItemVO.DORIGPLANARRVDATE, dorigplanarrvdate);
  }

  /** �ƻ��������� setter ���� */
  public void setDplanarrvdate(UFDate dplanarrvdate) {
    this.setAttributeValue(OrderItemVO.DPLANARRVDATE, dplanarrvdate);
  }

  /** dr setter ���� */
  public void setDr(Integer dr) {
    this.setAttributeValue(OrderItemVO.DR, dr);
  }

  /** ʵִ�ɹ����� setter ���� */
  public void setFactpurtype(Integer factpurtype) {
    this.setAttributeValue(OrderItemVO.FACTPURTYPE, factpurtype);
  }

  /** �������� setter ���� */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(OrderItemVO.FBUYSELLFLAG, fbuysellflag);
  }

  /** ���� setter ���� */
  public void setFisactive(Integer fisactive) {
    this.setAttributeValue(OrderItemVO.FISACTIVE, fisactive);
  }

  /** Ӧִ�ɹ����� setter ���� */
  public void setFneedpurtype(Integer fneedpurtype) {
    this.setAttributeValue(OrderItemVO.FNEEDPURTYPE, fneedpurtype);
  }

  /** ��˰��� setter ���� */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(OrderItemVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /** ��Ӧ�̽���״̬ setter ���� */
  public void setIstorestatus(Integer istorestatus) {
    this.setAttributeValue(OrderItemVO.ISTORESTATUS, istorestatus);
  }

  /** �ۼ��Ѻ������ҿ�Ʊ��� setter ���� */
  public void setNacccancelinvmny(UFDouble nacccancelinvmny) {
    this.setAttributeValue(OrderItemVO.NACCCANCELINVMNY, nacccancelinvmny);
  }

  /** �ۼƵ��������� setter ���� */
  public void setNaccumarrvnum(UFDouble naccumarrvnum) {
    this.setAttributeValue(OrderItemVO.NACCUMARRVNUM, naccumarrvnum);
  }

  /** �ۼ����������� setter ���� */
  public void setNaccumdevnum(UFDouble naccumdevnum) {
    this.setAttributeValue(OrderItemVO.NACCUMDEVNUM, naccumdevnum);
  }

  /** �ۼƱ��ҿ�Ʊ��� setter ���� */
  public void setNaccuminvoicemny(UFDouble naccuminvoicemny) {
    this.setAttributeValue(OrderItemVO.NACCUMINVOICEMNY, naccuminvoicemny);
  }

  /** �ۼƿ�Ʊ������ setter ���� */
  public void setNaccuminvoicenum(UFDouble naccuminvoicenum) {
    this.setAttributeValue(OrderItemVO.NACCUMINVOICENUM, naccuminvoicenum);
  }

  /** �ۼƼ�������� setter ���� */
  public void setNaccumpickupnum(UFDouble naccumpickupnum) {
    this.setAttributeValue(OrderItemVO.NACCUMPICKUPNUM, naccumpickupnum);
  }

  /** �ۼƵ����ƻ������� setter ���� */
  public void setNaccumrpnum(UFDouble naccumrpnum) {
    this.setAttributeValue(OrderItemVO.NACCUMRPNUM, naccumrpnum);
  }

  /** �ۼ���������� setter ���� */
  public void setNaccumstorenum(UFDouble naccumstorenum) {
    this.setAttributeValue(OrderItemVO.NACCUMSTORENUM, naccumstorenum);
  }

  /** �ۼ�;�������� setter ���� */
  public void setNaccumwastnum(UFDouble naccumwastnum) {
    this.setAttributeValue(OrderItemVO.NACCUMWASTNUM, naccumwastnum);
  }

  /** ���� setter ���� */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(OrderItemVO.NASTNUM, nastnum);
  }

  /** �ۼ��˻������� setter ���� */
  public void setNbackarrvnum(UFDouble nbackarrvnum) {
    this.setAttributeValue(OrderItemVO.NBACKARRVNUM, nbackarrvnum);
  }

  /** �ۼ��˿������� setter ���� */
  public void setNbackstorenum(UFDouble nbackstorenum) {
    this.setAttributeValue(OrderItemVO.NBACKSTORENUM, nbackstorenum);
  }

  /** �Ƴɱ���� setter ���� */
  public void setNcalcostmny(UFDouble ncalcostmny) {
    this.setAttributeValue(OrderItemVO.NCALCOSTMNY, ncalcostmny);
  }

  /** ��˰��� setter ���� */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(OrderItemVO.NCALTAXMNY, ncaltaxmny);
  }

  /** �ɵ������� setter ���� */
  public void setNcanarrivenum(UFDouble ncanarrivenum) {
    this.setAttributeValue(OrderItemVO.NCANARRIVENUM, ncanarrivenum);
  }

  /** ��������� setter ���� */
  public void setNcaninnum(UFDouble ncaninnum) {
    this.setAttributeValue(OrderItemVO.NCANINNUM, ncaninnum);
  }

  /** �ɿ�Ʊ���� setter ���� */
  public void setNcaninvoicenum(UFDouble ncaninvoicenum) {
    this.setAttributeValue(OrderItemVO.NCANINVOICENUM, ncaninvoicenum);
  }

  /** ȷ�Ͻ�� setter ���� */
  public void setNconfirmmny(UFDouble nconfirmmny) {
    this.setAttributeValue(OrderItemVO.NCONFIRMMNY, nconfirmmny);
  }

  /** ȷ������ setter ���� */
  public void setNconfirmnum(UFDouble nconfirmnum) {
    this.setAttributeValue(OrderItemVO.NCONFIRMNUM, nconfirmnum);
  }

  /** �۱����� setter ���� */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(OrderItemVO.NEXCHANGERATE, nexchangerate);
  }

  /** �����ۼƿ�Ʊ��� setter ���� */
  public void setNfeemny(UFDouble nfeemny) {
    this.setAttributeValue(OrderItemVO.NFEEMNY, nfeemny);
  }

  /** ȫ�ֱ�λ�һ��� setter ���� */
  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(OrderItemVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  /** ȫ�ֱ�����˰��� setter ���� */
  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(OrderItemVO.NGLOBALMNY, nglobalmny);
  }

  /** ȫ�ֱ��Ҽ�˰�ϼ� setter ���� */
  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(OrderItemVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  /** ���ű�λ�һ��� setter ���� */
  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(OrderItemVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  /** ���ű�����˰��� setter ���� */
  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(OrderItemVO.NGROUPMNY, ngroupmny);
  }

  /** ���ű��Ҽ�˰�ϼ� setter ���� */
  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(OrderItemVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  /** �ۿ� setter ���� */
  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(OrderItemVO.NITEMDISCOUNTRATE, nitemdiscountrate);
  }

  /** ����޼� setter ���� */
  public void setNmaxprice(UFDouble nmaxprice) {
    this.setAttributeValue(OrderItemVO.NMAXPRICE, nmaxprice);
  }

  /** ������˰��� setter ���� */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(OrderItemVO.NMNY, nmny);
  }

  /** ��������˰���� setter ���� */
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(OrderItemVO.NNETPRICE, nnetprice);
  }

  /** δ������ setter ���� */
  public void setNnopayorgmny(UFDouble nnopayorgmny) {
    this.setAttributeValue(OrderItemVO.NNOPAYORGMNY, nnopayorgmny);
  }

  /** ���ɵֿ�˰�� setter ���� */
  public void setNnosubtax(UFDouble nnosubtax) {
    this.setAttributeValue(OrderItemVO.NNOSUBTAX, nnosubtax);
  }

  /** ���ɵֿ�˰�� setter ���� */
  public void setNnosubtaxrate(UFDouble nnosubtaxrate) {
    this.setAttributeValue(OrderItemVO.NNOSUBTAXRATE, nnosubtaxrate);
  }

  /** ������ setter ���� */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(OrderItemVO.NNUM, nnum);
  }

  /** ��˰��� setter ���� */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(OrderItemVO.NORIGMNY, norigmny);
  }

  /** ����˰���� setter ���� */
  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(OrderItemVO.NORIGNETPRICE, norignetprice);
  }

  /** ԭʼ�������� setter ���� */
  public void setNorigordernum(UFDouble norigordernum) {
    this.setAttributeValue(OrderItemVO.NORIGORDERNUM, norigordernum);
  }

  /** ԭʼ��������˰���� setter ���� */
  public void setNorigorderprice(UFDouble norigorderprice) {
    this.setAttributeValue(OrderItemVO.NORIGORDERPRICE, norigorderprice);
  }

  /** ����˰���� setter ���� */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(OrderItemVO.NORIGPRICE, norigprice);
  }

  /** ��˰�ϼ� setter ���� */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(OrderItemVO.NORIGTAXMNY, norigtaxmny);
  }

  // /** ˰�� setter ���� */
  // public void setNorigtax(UFDouble norigtax) {
  // this.setAttributeValue(OrderItemVO.NORIGTAX, norigtax);
  // }

  /** ����˰���� setter ���� */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(OrderItemVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  /** ����˰���� setter ���� */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(OrderItemVO.NORIGTAXPRICE, norigtaxprice);
  }

  /** ���� setter ���� */
  public void setNpacknum(UFDouble npacknum) {
    this.setAttributeValue(OrderItemVO.NPACKNUM, npacknum);
  }

  /** ��������˰���� setter ���� */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(OrderItemVO.NPRICE, nprice);
  }

  /** ������˰���� setter ���� */
  public void setNqtnetprice(UFDouble nqtnetprice) {
    this.setAttributeValue(OrderItemVO.NQTNETPRICE, nqtnetprice);
  }

  /** ��˰���� setter ���� */
  public void setNqtorignetprice(UFDouble nqtorignetprice) {
    this.setAttributeValue(OrderItemVO.NQTORIGNETPRICE, nqtorignetprice);
  }

  /** ��˰���� setter ���� */
  public void setNqtorigprice(UFDouble nqtorigprice) {
    this.setAttributeValue(OrderItemVO.NQTORIGPRICE, nqtorigprice);
  }

  /** ��˰���� setter ���� */
  public void setNqtorigtaxnetprc(UFDouble nqtorigtaxnetprc) {
    this.setAttributeValue(OrderItemVO.NQTORIGTAXNETPRC, nqtorigtaxnetprc);
  }

  /** ��˰���� setter ���� */
  public void setNqtorigtaxprice(UFDouble nqtorigtaxprice) {
    this.setAttributeValue(OrderItemVO.NQTORIGTAXPRICE, nqtorigtaxprice);
  }

  /** ���۱�����˰���� setter ���� */
  public void setNqtprice(UFDouble nqtprice) {
    this.setAttributeValue(OrderItemVO.NQTPRICE, nqtprice);
  }

  /** ���Һ�˰���� setter ���� */
  public void setNqttaxnetprice(UFDouble nqttaxnetprice) {
    this.setAttributeValue(OrderItemVO.NQTTAXNETPRICE, nqttaxnetprice);
  }

  /** ���۱��Һ�˰���� setter ���� */
  public void setNqttaxprice(UFDouble nqttaxprice) {
    this.setAttributeValue(OrderItemVO.NQTTAXPRICE, nqttaxprice);
  }

  /** �������� setter ���� */
  public void setNqtunitnum(UFDouble nqtunitnum) {
    this.setAttributeValue(OrderItemVO.NQTUNITNUM, nqtunitnum);
  }

  /** �ͻ��ƻ����� setter ���� */
  public void setNsendplannum(UFDouble nsendplannum) {
    this.setAttributeValue(OrderItemVO.NSENDPLANNUM, nsendplannum);
  }

  /** ��Դ���������� setter ���� */
  public void setNsourcenum(UFDouble nsourcenum) {
    this.setAttributeValue(OrderItemVO.NSOURCENUM, nsourcenum);
  }

  /** ��Ԥ������ setter ���� */
  public void setNsuprsnum(UFDouble nsuprsnum) {
    this.setAttributeValue(OrderItemVO.NSUPRSNUM, nsuprsnum);
  }

  /** ����˰�� setter ���� */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(OrderItemVO.NTAX, ntax);
  }

  /** ���Ҽ�˰�ϼ� setter ���� */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(OrderItemVO.NTAXMNY, ntaxmny);
  }

  /** �����Һ�˰���� setter ���� */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(OrderItemVO.NTAXNETPRICE, ntaxnetprice);
  }

  /** �����Һ�˰���� setter ���� */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(OrderItemVO.NTAXPRICE, ntaxprice);
  }

  /** ˰�� setter ���� */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(OrderItemVO.NTAXRATE, ntaxrate);
  }

  /** ��� setter ���� */
  public void setNvolumn(UFDouble nvolumn) {
    this.setAttributeValue(OrderItemVO.NVOLUMN, nvolumn);
  }

  /** ���� setter ���� */
  public void setNweight(UFDouble nweight) {
    this.setAttributeValue(OrderItemVO.NWEIGHT, nweight);
  }

  /** Ӧ����֯���°汾 setter ���� */
  public void setPk_apfinanceorg(String pk_apfinanceorg) {
    this.setAttributeValue(OrderItemVO.PK_APFINANCEORG, pk_apfinanceorg);
  }

  /** Ӧ����֯ setter ���� */
  public void setPk_apfinanceorg_v(String pk_apfinanceorg_v) {
    this.setAttributeValue(OrderItemVO.PK_APFINANCEORG_V, pk_apfinanceorg_v);
  }

  /** ���������������°汾 setter ���� */
  public void setPk_apliabcenter(String pk_apliabcenter) {
    this.setAttributeValue(OrderItemVO.PK_APLIABCENTER, pk_apliabcenter);
  }

  /** ������������ setter ���� */
  public void setPk_apliabcenter_v(String pk_apliabcenter_v) {
    this.setAttributeValue(OrderItemVO.PK_APLIABCENTER_V, pk_apliabcenter_v);
  }

  /** �ջ������������°汾 setter ���� */
  public void setPk_arrliabcenter(String pk_arrliabcenter) {
    this.setAttributeValue(OrderItemVO.PK_ARRLIABCENTER, pk_arrliabcenter);
  }

  /** �ջ��������� setter ���� */
  public void setPk_arrliabcenter_v(String pk_arrliabcenter_v) {
    this.setAttributeValue(OrderItemVO.PK_ARRLIABCENTER_V, pk_arrliabcenter_v);
  }

  /** �ջ������֯���°汾 setter ���� */
  public void setPk_arrvstoorg(String pk_arrvstoorg) {
    this.setAttributeValue(OrderItemVO.PK_ARRVSTOORG, pk_arrvstoorg);
  }

  /** �ջ������֯ setter ���� */
  public void setPk_arrvstoorg_v(String pk_arrvstoorg_v) {
    this.setAttributeValue(OrderItemVO.PK_ARRVSTOORG_V, pk_arrvstoorg_v);
  }

  /** ���κ����� setter ���� */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(OrderItemVO.PK_BATCHCODE, pk_batchcode);
  }

  /** ���ɿ��ƹ����ӱ� setter ���� */
  public void setPk_cenpurule_b(String pk_cenpurule_b) {
    this.setAttributeValue(OrderItemVO.PK_CENPURULE_B, pk_cenpurule_b);
  }

  /** �ۿ۹������ setter ���� */
  public void setPk_discount(String pk_discount) {
    this.setAttributeValue(OrderItemVO.PK_DISCOUNT, pk_discount);
  }

  /** ������֯���°汾 setter ���� */
  public void setPk_flowstockorg(String pk_flowstockorg) {
    this.setAttributeValue(OrderItemVO.PK_FLOWSTOCKORG, pk_flowstockorg);
  }

  /** ������֯ setter ���� */
  public void setPk_flowstockorg_v(String pk_flowstockorg_v) {
    this.setAttributeValue(OrderItemVO.PK_FLOWSTOCKORG_V, pk_flowstockorg_v);
  }

  /** �������� setter ���� */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(OrderItemVO.PK_GROUP, pk_group);
  }

  /** ���ϰ汾��Ϣ setter ���� */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(OrderItemVO.PK_MATERIAL, pk_material);
  }

  /** �ɹ�������ϸ setter ���� */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(OrderItemVO.PK_ORDER, pk_order);
  }

  /** �ɹ�������ϸ setter ���� */
  public void setPk_order_b(String pk_order_b) {
    this.setAttributeValue(OrderItemVO.PK_ORDER_B, pk_order_b);
  }

  /** �ɹ���֯�汾��Ϣ setter ���� */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(OrderItemVO.PK_ORG, pk_org);
  }

  /** �ɹ���֯ setter ���� */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(OrderItemVO.PK_ORG_V, pk_org_v);
  }

  /** ���������֯���°汾 setter ���� */
  public void setPk_psfinanceorg(String pk_psfinanceorg) {
    this.setAttributeValue(OrderItemVO.PK_PSFINANCEORG, pk_psfinanceorg);
  }

  /** ���������֯ setter ���� */
  public void setPk_psfinanceorg_v(String pk_psfinanceorg_v) {
    this.setAttributeValue(OrderItemVO.PK_PSFINANCEORG_V, pk_psfinanceorg_v);
  }

  /** �ջ���ַ setter ���� */
  public void setPk_receiveaddress(String pk_receiveaddress) {
    this.setAttributeValue(OrderItemVO.PK_RECEIVEADDRESS, pk_receiveaddress);
  }

  /** �����ƻ����� setter ���� */
  public void setPk_receiveplan(String pk_receiveplan) {
    this.setAttributeValue(OrderItemVO.PK_RECEIVEPLAN, pk_receiveplan);
  }

  /** �ջ��ֿ� setter ���� */
  public void setPk_recvstordoc(String pk_recvstordoc) {
    this.setAttributeValue(OrderItemVO.PK_RECVSTORDOC, pk_recvstordoc);
  }

  /** ����˾ setter ���� */
  public void setPk_reqcorp(String pk_reqcorp) {
    this.setAttributeValue(OrderItemVO.PK_REQCORP, pk_reqcorp);
  }

  /** ���������°汾 setter ���� */
  public void setPk_reqdept(String pk_reqdept) {
    this.setAttributeValue(OrderItemVO.PK_REQDEPT, pk_reqdept);
  }

  /** ������ setter ���� */
  public void setPk_reqdept_v(String pk_reqdept_v) {
    this.setAttributeValue(OrderItemVO.PK_REQDEPT_V, pk_reqdept_v);
  }

  /** ��������֯���°汾 setter ���� */
  public void setPk_reqstoorg(String pk_reqstoorg) {
    this.setAttributeValue(OrderItemVO.PK_REQSTOORG, pk_reqstoorg);
  }

  /** ��������֯ setter ���� */
  public void setPk_reqstoorg_v(String pk_reqstoorg_v) {
    this.setAttributeValue(OrderItemVO.PK_REQSTOORG_V, pk_reqstoorg_v);
  }

  /** ����ֿ� setter ���� */
  public void setPk_reqstordoc(String pk_reqstordoc) {
    this.setAttributeValue(OrderItemVO.PK_REQSTORDOC, pk_reqstordoc);
  }

  /** �ų̼ƻ� setter ���� */
  public void setPk_schedule(String pk_schedule) {
    this.setAttributeValue(OrderItemVO.PK_SCHEDULE, pk_schedule);
  }

  /** �ų̼ƻ���ϸ setter ���� */
  public void setPk_schedule_b(String pk_schedule_b) {
    this.setAttributeValue(OrderItemVO.PK_SCHEDULE_B, pk_schedule_b);
  }

  /** ������Ϣ setter ���� */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(OrderItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** �޶���Դ������ϸ setter ���� */
  public void setPk_srcorder_b(String pk_srcorder_b) {
    this.setAttributeValue(OrderItemVO.PK_SRCORDER_B, pk_srcorder_b);
  }

  /** ��Ӧ�� setter ���� */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(OrderItemVO.PK_SUPPLIER, pk_supplier);
  }

  /** ��Դ������TS setter ���� */
  public void setSourcebts(UFDateTime sourcebts) {
    this.setAttributeValue(OrderItemVO.SOURCEBTS, sourcebts);
  }

  /** ��Դ����TS setter ���� */
  public void setSourcets(UFDateTime sourcets) {
    this.setAttributeValue(OrderItemVO.SOURCETS, sourcets);
  }

  /** ts setter ���� */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(OrderItemVO.TS, ts);
  }

  /** ���κ� setter ���� */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(OrderItemVO.VBATCHCODE, vbatchcode);
  }

  /** �Զ�����1 setter ���� */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(OrderItemVO.VBDEF1, vbdef1);
  }

  /** �Զ�����10 setter ���� */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(OrderItemVO.VBDEF10, vbdef10);
  }

  /** �Զ�����11 setter ���� */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(OrderItemVO.VBDEF11, vbdef11);
  }

  /** �Զ�����12 setter ���� */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(OrderItemVO.VBDEF12, vbdef12);
  }

  /** �Զ�����13 setter ���� */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(OrderItemVO.VBDEF13, vbdef13);
  }

  /** �Զ�����14 setter ���� */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(OrderItemVO.VBDEF14, vbdef14);
  }

  /** �Զ�����15 setter ���� */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(OrderItemVO.VBDEF15, vbdef15);
  }

  /** �Զ�����16 setter ���� */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(OrderItemVO.VBDEF16, vbdef16);
  }

  /** �Զ�����17 setter ���� */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(OrderItemVO.VBDEF17, vbdef17);
  }

  /** �Զ�����18 setter ���� */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(OrderItemVO.VBDEF18, vbdef18);
  }

  /** �Զ�����19 setter ���� */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(OrderItemVO.VBDEF19, vbdef19);
  }

  /** �Զ�����2 setter ���� */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(OrderItemVO.VBDEF2, vbdef2);
  }

  /** �Զ�����20 setter ���� */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(OrderItemVO.VBDEF20, vbdef20);
  }

  /** �Զ�����3 setter ���� */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(OrderItemVO.VBDEF3, vbdef3);
  }

  /** �Զ�����4 setter ���� */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(OrderItemVO.VBDEF4, vbdef4);
  }

  /** �Զ�����5 setter ���� */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(OrderItemVO.VBDEF5, vbdef5);
  }

  /** �Զ�����6 setter ���� */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(OrderItemVO.VBDEF6, vbdef6);
  }

  /** �Զ�����7 setter ���� */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(OrderItemVO.VBDEF7, vbdef7);
  }

  /** �Զ�����8 setter ���� */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(OrderItemVO.VBDEF8, vbdef8);
  }

  /** �Զ�����9 setter ���� */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(OrderItemVO.VBDEF9, vbdef9);
  }

  /** ��ע setter ���� */
  public void setVbmemo(String vbmemo) {
    this.setAttributeValue(OrderItemVO.VBMEMO, vbmemo);
  }

  /** ������ setter ���� */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(OrderItemVO.VCHANGERATE, vchangerate);
  }

  /** ��ͬ�� setter ���� */
  public void setVcontractcode(String vcontractcode) {
    this.setAttributeValue(OrderItemVO.VCONTRACTCODE, vcontractcode);
  }

  /** �������񵥾ݺ� setter ���� */
  public void setVecbillcode(String vecbillcode) {
    this.setAttributeValue(OrderItemVO.VECBILLCODE, vecbillcode);
  }

  /** Դͷ���ݺ� setter ���� */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(OrderItemVO.VFIRSTCODE, vfirstcode);
  }

  /** Դͷ�����к� setter ���� */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(OrderItemVO.VFIRSTROWNO, vfirstrowno);
  }

  /** Դͷ�������� setter ���� */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(OrderItemVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /** ���ɸ�������1 setter ���� */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(OrderItemVO.VFREE1, vfree1);
  }

  /** ���ɸ�������10 setter ���� */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(OrderItemVO.VFREE10, vfree10);
  }

  /** ���ɸ�������2 setter ���� */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(OrderItemVO.VFREE2, vfree2);
  }

  /** ���ɸ�������3 setter ���� */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(OrderItemVO.VFREE3, vfree3);
  }

  /** ���ɸ�������4 setter ���� */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(OrderItemVO.VFREE4, vfree4);
  }

  /** ���ɸ�������5 setter ���� */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(OrderItemVO.VFREE5, vfree5);
  }

  /** ���ɸ�������6 setter ���� */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(OrderItemVO.VFREE6, vfree6);
  }

  /** ���ɸ�������7 setter ���� */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(OrderItemVO.VFREE7, vfree7);
  }

  /** ���ɸ�������8 setter ���� */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(OrderItemVO.VFREE8, vfree8);
  }

  /** ���ɸ�������9 setter ���� */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(OrderItemVO.VFREE9, vfree9);
  }

  /** �۸��������� setter ���� */
  public void setVpriceauditcode(String vpriceauditcode) {
    this.setAttributeValue(OrderItemVO.VPRICEAUDITCODE, vpriceauditcode);
  }

  /** ���ۻ����� setter ���� */
  public void setVqtunitrate(String vqtunitrate) {
    this.setAttributeValue(OrderItemVO.VQTUNITRATE, vqtunitrate);
  }

  /** ��Դ���ݺ� setter ���� */
  public void setVsourcecode(String vsourcecode) {
    this.setAttributeValue(OrderItemVO.VSOURCECODE, vsourcecode);
  }

  /** ��Դ�����к� setter ���� */
  public void setVsourcerowno(String vsourcerowno) {
    this.setAttributeValue(OrderItemVO.VSOURCEROWNO, vsourcerowno);
  }

  /** ��Դ�������� setter ���� */
  public void setVsourcetrantype(String vsourcetrantype) {
    this.setAttributeValue(OrderItemVO.VSOURCETRANTYPE, vsourcetrantype);
  }

  /** ��Ӧ�̷�����ַ setter ���� */
  public void setVvenddevaddr(String vvenddevaddr) {
    this.setAttributeValue(OrderItemVO.VVENDDEVADDR, vvenddevaddr);
  }

  /** ��Ӧ���ϱ��� setter ���� */
  public void setVvendinventorycode(String vvendinventorycode) {
    this.setAttributeValue(OrderItemVO.VVENDINVENTORYCODE, vvendinventorycode);
  }

  /** ��Ӧ�������� setter ���� */
  public void setVvendinventoryname(String vvendinventoryname) {
    this.setAttributeValue(OrderItemVO.VVENDINVENTORYNAME, vvendinventoryname);
  }

  /** �Է������� setter ���� */
  public void setVvendorordercode(String vvendorordercode) {
    this.setAttributeValue(OrderItemVO.VVENDORORDERCODE, vvendorordercode);
  }

  /** �Է������к� setter ���� */
  public void setVvendororderrow(String vvendororderrow) {
    this.setAttributeValue(OrderItemVO.VVENDORORDERROW, vvendororderrow);
  }

}
