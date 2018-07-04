/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-6-26 ����02:56:11
 */
package nc.vo.pu.m25.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhaoyha
 * @time 2009-6-26 ����02:56:11
 */
public class InvoiceItemVO extends SuperVO {

  // add by zhangyhh for �����ڷ�Ʊ
  /** ��������Ʊ���� */
  public static final String CADJUSTEDINVID = "cadjustedinvid";

  /** ��������Ʊ������ */
  public static final String CADJUSTEDROWID = "cadjustedrowid";

  /** �ͻ� */
  public static final String CASSCUSTID = "casscustid";

  /** ��λ */
  public static final String CASTUNITID = "castunitid";

  /** ��ͬ���� */
  public static final String CCONTRACTID = "ccontractid";

  /** ��ͬ������ */
  public static final String CCONTRACTROWID = "ccontractrowid";

  /** ������ **/
  public static final String CFFILEID = "cffileid";

  /** Դͷ���������� */
  public static final String CFIRSTBID = "cfirstbid";

  /** Դͷ�������� */
  public static final String CFIRSTID = "cfirstid";

  /** Դͷ�������� */
  public static final String CFIRSTTYPECODE = "cfirsttypecode";

  /** �������� */
  public static final String CPRODUCTORID = "cproductorid";

  /** ��Ŀ */
  public static final String CPROJECTID = "cprojectid";

  /** ��Ŀ���� */
  public static final String CPROJECTTASKID = "cprojecttaskid";

  /** �����ȼ� */
  public static final String CQUALITYLEVELID = "cqualitylevelid";

  /** �к� */
  public static final String CROWNO = "crowno";

  /** ��Դ���������� */
  public static final String CSOURCEBID = "csourcebid";

  /** ��Դ�������� */
  public static final String CSOURCEID = "csourceid";

  /** ��Դ�������� */
  public static final String CSOURCETYPECODE = "csourcetypecode";

  /** ˰�� */
  public static final String CTAXCODEID = "ctaxcodeid";

  /** ����λ */
  public static final String CUNITID = "cunitid";

  /** ��Ʊ���� */
  public static final String DBILLDATE = "dbilldate";

  /** dr */
  public static final String DR = "dr";

  /** ��Դ�������� */
  public static final String DSOURCEDATE = "dsourcedate";

  /** Դͷ������TS */
  public static final String FIRSTBTS = "firstbts";

  /** Դͷ����TS */
  public static final String FIRSTTS = "firstts";

  /** ������ */
  public static final String FROWTYPE = "frowtype";

  /** ��˰��� */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /** �ۼƱ��ҽ����� */
  public static final String NACCUMSETTMNY = "naccumsettmny";

  /** �ۼƽ��������� */
  public static final String NACCUMSETTNUM = "naccumsettnum";

  /** �������� */
  public static final String NADJUSTORGPRICE = "nadjustorgprice";

  /** ���� */
  public static final String NASTNUM = "nastnum";

  /** ��˰���� */
  public static final String NASTORIGPRICE = "nastorigprice";

  /** ��˰���� */
  public static final String NASTORIGTAXPRICE = "nastorigtaxprice";

  /** ������˰���� */
  public static final String NASTPRICE = "nastprice";

  /** ���Һ�˰���� */
  public static final String NASTTAXPRICE = "nasttaxprice";

  /** �Ƴɱ���� */
  public static final String NCALCOSTMNY = "ncalcostmny";

  /** ��˰��� */
  public static final String NCALTAXMNY = "ncaltaxmny";

  /** ȫ�ֱ�����˰��� */
  public static final String NGLOBALMNY = "nglobalmny";

  /** ȫ�ֱ��Ҽ�˰�ϼ� */
  public static final String NGLOBALTAXMNY = "nglobaltaxmny";

  /** ���ű�����˰��� */
  public static final String NGROUPMNY = "ngroupmny";

  /** ���ű��Ҽ�˰�ϼ� */
  public static final String NGROUPTAXMNY = "ngrouptaxmny";

  /** ������˰��� */
  public static final String NMNY = "nmny";

  /** ���ɵֿ�˰�� */
  public static final String NNOSUBTAX = "nnosubtax";

  /** ���ɵֿ�˰�� */
  public static final String NNOSUBTAXRATE = "nnosubtaxrate";

  /** ������ */
  public static final String NNUM = "nnum";

  /** �������ԣ����ڻ�д��Դ=������������������� */
  public static final String NNUMWRBCK = "nnumwrbck";

  /** ��˰��� */
  public static final String NORIGMNY = "norigmny";

  /** ����˰���� */
  public static final String NORIGPRICE = "norigprice";

  /** ��˰�ϼ� */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /** ����˰���� */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /** �ƻ��� */
  public static final String NPLANPRICE = "nplanprice";

  /** ��������˰���� */
  public static final String NPRICE = "nprice";

  /** ������������� */
  public static final String NREASONWASTENUM = "nreasonwastenum";

  /** ��Դ���������� */
  public static final String NSOURCENUM = "nsourcenum";

  /** ����˰�� */
  public static final String NTAX = "ntax";

  /** ���Ҽ�˰�ϼ� */
  public static final String NTAXMNY = "ntaxmny";

  /** �����Һ�˰���� */
  public static final String NTAXPRICE = "ntaxprice";

  /** ˰�� */
  public static final String NTAXRATE = "ntaxrate";

  /** Ӧ��������֯(OID) */
  public static final String PK_APFINANCEORG = "pk_apfinanceorg";

  /** Ӧ��������֯(VID) */
  public static final String PK_APFINANCEORG_V = "pk_apfinanceorg_v";

  /** ��������(OID) */
  public static final String PK_APLIABCENTER = "pk_apliabcenter";

  /** ��������(VID) */
  public static final String PK_APLIABCENTER_V = "pk_apliabcenter_v";

  /** ���ε��� */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** ��֧��Ŀ */
  public static final String PK_COSTSUBJ = "pk_costsubj";

  /** �������� */
  public static final String PK_GROUP = "pk_group";

  /** ��Ʊ��ʵ�� */
  public static final String PK_INVOICE = "pk_invoice";

  /** �ɹ���Ʊ��ϸ */
  public static final String PK_INVOICE_B = "pk_invoice_b";

  /** ����(VID) */
  public static final String PK_MATERIAL = "pk_material";

  /** �ɹ��������� */
  public static final String PK_ORDER = "pk_order";

  /** �ɹ����������� */
  public static final String PK_ORDER_B = "pk_order_b";

  /** ������֯(OID) */
  public static final String PK_ORG = "pk_org";

  /** ������֯(VID) */
  public static final String PK_ORG_V = "pk_org_v";

  /** ����(OID) */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** ��ⵥ��ID */
  public static final String PK_STOCKPS_B = "pk_stockps_b";

  /** �ֿ� */
  public static final String PK_STORDOC = "pk_stordoc";

  /** ��Ӧ�� */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** ʹ�ò���(OID) */
  public static final String PK_USEDEPT = "pk_usedept";

  /** ʹ�ò���(VID) */
  public static final String PK_USEDEPT_V = "pk_usedept_v";

  /** ��Դ������TS */
  public static final String SOURCEBTS = "sourcebts";

  /** ��Դ����TS */
  public static final String SOURCETS = "sourcets";

  /** ts */
  public static final String TS = "ts";

  /** ��������Ʊ�� */
  public static final String VADJEDBILLCODE = "vadjedbillcode";

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

  /** ������ */
  public static final String VCHANGERATE = "vchangerate";

  /** ��ͬ�� */
  public static final String VCTCODE = "vctcode";

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

  /** ��ע */
  public static final String VMEMOB = "vmemob";

  /** ������ */
  public static final String VORDERCODE = "vordercode";

  /** ������������ */
  public static final String VORDERTRANTYPE = "vordertrantype";

  /** ��Դ���ݺ� */
  public static final String VSOURCECODE = "vsourcecode";

  /** ��Դ�����к� */
  public static final String VSOURCEROWNO = "vsourcerowno";

  /** ��Դ�������� */
  public static final String VSOURCETRANTYPE = "vsourcetrantype";

  private static final long serialVersionUID = 1L;

  /** ��ȡ��������Ʊ���� getter ���� */
  public String getCadjustedinvid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CADJUSTEDINVID);
  }

  /** ��ȡ��������Ʊ������ getter ���� */
  public String getCadjustedrowid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CADJUSTEDROWID);
  }

  /** �ͻ� getter ���� */
  public String getCasscustid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CASSCUSTID);
  }

  /** ��λ getter ���� */
  public String getCastunitid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CASTUNITID);
  }

  /** ��ȡ��ͬ���� getter ���� */
  public String getCcontractid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CCONTRACTID);
  }

  /** ��ȡ��ͬ������ getter ���� */
  public String getCcontractrowid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CCONTRACTROWID);
  }

  /** ������ **/
  public String getCffileid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CFFILEID);
  }

  /** Դͷ���������� getter ���� */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CFIRSTBID);
  }

  /** Դͷ�������� getter ���� */
  public String getCfirstid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CFIRSTID);
  }

  /** Դͷ�������� getter ���� */
  public String getCfirsttypecode() {
    return (String) this.getAttributeValue(InvoiceItemVO.CFIRSTTYPECODE);
  }

  /** �������� getter ���� */
  public String getCproductorid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CPRODUCTORID);
  }

  /** ��Ŀ getter ���� */
  public String getCprojectid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CPROJECTID);
  }

  /** ��Ŀ���� getter ���� */
  public String getCprojecttaskid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CPROJECTTASKID);
  }

  /** �����ȼ� getter ���� */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CQUALITYLEVELID);
  }

  /** �к� getter ���� */
  public String getCrowno() {
    return (String) this.getAttributeValue(InvoiceItemVO.CROWNO);
  }

  /** ��Դ���������� getter ���� */
  public String getCsourcebid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CSOURCEBID);
  }

  /** ��Դ�������� getter ���� */
  public String getCsourceid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CSOURCEID);
  }

  /** ��Դ�������� getter ���� */
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(InvoiceItemVO.CSOURCETYPECODE);
  }

  /** ˰�� getter ���� */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CTAXCODEID);
  }

  /** ����λ getter ���� */
  public String getCunitid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CUNITID);
  }

  /** ��Ʊ���� getter ���� */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(InvoiceItemVO.DBILLDATE);
  }

  /** dr getter ���� */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(InvoiceItemVO.DR);
  }

  /** ��Դ�������� getter ���� */
  public UFDate getDsourcedate() {
    return (UFDate) this.getAttributeValue(InvoiceItemVO.DSOURCEDATE);
  }

  /** Դͷ������TS getter ���� */
  public UFDateTime getFirstbts() {
    return (UFDateTime) this.getAttributeValue(InvoiceItemVO.FIRSTBTS);
  }

  /** Դͷ����TS getter ���� */
  public UFDateTime getFirstts() {
    return (UFDateTime) this.getAttributeValue(InvoiceItemVO.FIRSTTS);
  }

  /** ������ getter ���� */
  public Integer getFrowtype() {
    return (Integer) this.getAttributeValue(InvoiceItemVO.FROWTYPE);
  }

  /** ��˰��� getter ���� */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(InvoiceItemVO.FTAXTYPEFLAG);
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pub.ISuperVO#getMetaData()
   */
  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.M25_B);
    return meta;
  }

  /** �ۼƱ��ҽ����� getter ���� */
  public UFDouble getNaccumsettmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NACCUMSETTMNY);
  }

  /** �ۼƽ��������� getter ���� */
  public UFDouble getNaccumsettnum() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NACCUMSETTNUM);
  }

  /** ��ȡ�������� getter ���� */
  public UFDouble getNadjustorgprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NADJUSTORGPRICE);
  }

  /** ���� getter ���� */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NASTNUM);
  }

  /** ��˰���� getter ���� */
  public UFDouble getNastorigprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NASTORIGPRICE);
  }

  /** ��˰���� getter ���� */
  public UFDouble getNastorigtaxprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NASTORIGTAXPRICE);
  }

  /** ������˰���� getter ���� */
  public UFDouble getNastprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NASTPRICE);
  }

  /** ���Һ�˰���� getter ���� */
  public UFDouble getNasttaxprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NASTTAXPRICE);
  }

  /** �Ƴɱ���� getter ���� */
  public UFDouble getNcalcostmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NCALCOSTMNY);
  }

  /** ��˰��� getter ���� */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NCALTAXMNY);
  }

  /** ȫ�ֱ�����˰��� getter ���� */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NGLOBALMNY);
  }

  /** ȫ�ֱ��Ҽ�˰�ϼ� getter ���� */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NGLOBALTAXMNY);
  }

  /** ���ű�����˰��� getter ���� */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NGROUPMNY);
  }

  /** ���ű��Ҽ�˰�ϼ� getter ���� */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NGROUPTAXMNY);
  }

  /** ������˰��� getter ���� */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NMNY);
  }

  /** ���ɵֿ�˰�� getter ���� */
  public UFDouble getNnosubtax() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NNOSUBTAX);
  }

  /** ���ɵֿ�˰�� getter ���� */
  public UFDouble getNnosubtaxrate() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NNOSUBTAXRATE);
  }

  /** ������ getter ���� */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NNUM);
  }

  /** �������ԣ����ڻ�д��Դ=������������������� */
  public UFDouble getNnumwrbck() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NNUMWRBCK);
  }

  /** ��˰��� getter ���� */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NORIGMNY);
  }

  /** ����˰���� getter ���� */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NORIGPRICE);
  }

  /** ��˰�ϼ� getter ���� */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NORIGTAXMNY);
  }

  /** ����˰���� getter ���� */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NORIGTAXPRICE);
  }

  /** �ƻ��� getter ���� */
  public UFDouble getNplanprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NPLANPRICE);
  }

  /** ��������˰���� getter ���� */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NPRICE);
  }

  /** ������������� getter ���� */
  public UFDouble getNreasonwastenum() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NREASONWASTENUM);
  }

  /** ��Դ���������� getter ���� */
  public UFDouble getNsourcenum() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NSOURCENUM);
  }

  /** ����˰�� getter ���� */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NTAX);
  }

  /** ���Ҽ�˰�ϼ� getter ���� */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NTAXMNY);
  }

  /** �����Һ�˰���� getter ���� */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NTAXPRICE);
  }

  /** ˰�� getter ���� */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NTAXRATE);
  }

  /** Ӧ��������֯(OID) getter ���� */
  public String getPk_apfinanceorg() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_APFINANCEORG);
  }

  /** Ӧ��������֯(VID) getter ���� */
  public String getPk_apfinanceorg_v() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_APFINANCEORG_V);
  }

  /** ��������(OID) getter ���� */
  public String getPk_apliabcenter() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_APLIABCENTER);
  }

  /** ��������(VID) getter ���� */
  public String getPk_apliabcenter_v() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_APLIABCENTER_V);
  }

  /** ���ε��� getter ���� */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_BATCHCODE);
  }

  /** ��֧��Ŀ getter ���� */
  public String getPk_costsubj() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_COSTSUBJ);
  }

  /** �������� getter ���� */
  public String getPk_group() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_GROUP);
  }

  /** ��Ʊ��ʵ�� getter ���� */
  public String getPk_invoice() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_INVOICE);
  }

  /** �ɹ���Ʊ��ϸ getter ���� */
  public String getPk_invoice_b() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_INVOICE_B);
  }

  /** ����(VID) getter ���� */
  public String getPk_material() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_MATERIAL);
  }

  /** �ɹ��������� getter ���� */
  public String getPk_order() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_ORDER);
  }

  /** �ɹ����������� getter ���� */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_ORDER_B);
  }

  /** ������֯(OID) getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_ORG);
  }

  /** ������֯(VID) getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_ORG_V);
  }

  /** ����(OID) getter ���� */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_SRCMATERIAL);
  }

  /** ��ⵥ��ID getter ���� */
  public String getPk_stockps_b() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_STOCKPS_B);
  }

  /** �ֿ� getter ���� */
  public String getPk_stordoc() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_STORDOC);
  }

  /** ��Ӧ�� getter ���� */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_SUPPLIER);
  }

  /** ʹ�ò���(OID) getter ���� */
  public String getPk_usedept() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_USEDEPT);
  }

  /** ʹ�ò���(VID) getter ���� */
  public String getPk_usedept_v() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_USEDEPT_V);
  }

  /** ��Դ������TS getter ���� */
  public UFDateTime getSourcebts() {
    return (UFDateTime) this.getAttributeValue(InvoiceItemVO.SOURCEBTS);
  }

  /** ��Դ����TS getter ���� */
  public UFDateTime getSourcets() {
    return (UFDateTime) this.getAttributeValue(InvoiceItemVO.SOURCETS);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(InvoiceItemVO.TS);
  }

  /** ��ȡ��������Ʊ�� getter ���� */
  public String getVadjedbillcode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VADJEDBILLCODE);
  }

  /** ���κ� getter ���� */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBATCHCODE);
  }

  /** �Զ�����1 getter ���� */
  public String getVbdef1() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF1);
  }

  /** �Զ�����10 getter ���� */
  public String getVbdef10() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF10);
  }

  /** �Զ�����11 getter ���� */
  public String getVbdef11() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF11);
  }

  /** �Զ�����12 getter ���� */
  public String getVbdef12() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF12);
  }

  /** �Զ�����13 getter ���� */
  public String getVbdef13() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF13);
  }

  /** �Զ�����14 getter ���� */
  public String getVbdef14() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF14);
  }

  /** �Զ�����15 getter ���� */
  public String getVbdef15() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF15);
  }

  /** �Զ�����16 getter ���� */
  public String getVbdef16() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF16);
  }

  /** �Զ�����17 getter ���� */
  public String getVbdef17() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF17);
  }

  /** �Զ�����18 getter ���� */
  public String getVbdef18() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF18);
  }

  /** �Զ�����19 getter ���� */
  public String getVbdef19() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF19);
  }

  /** �Զ�����2 getter ���� */
  public String getVbdef2() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF2);
  }

  /** �Զ�����20 getter ���� */
  public String getVbdef20() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF20);
  }

  /** �Զ�����3 getter ���� */
  public String getVbdef3() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF3);
  }

  /** �Զ�����4 getter ���� */
  public String getVbdef4() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF4);
  }

  /** �Զ�����5 getter ���� */
  public String getVbdef5() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF5);
  }

  /** �Զ�����6 getter ���� */
  public String getVbdef6() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF6);
  }

  /** �Զ�����7 getter ���� */
  public String getVbdef7() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF7);
  }

  /** �Զ�����8 getter ���� */
  public String getVbdef8() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF8);
  }

  /** �Զ�����9 getter ���� */
  public String getVbdef9() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBDEF9);
  }

  /** ������ getter ���� */
  public String getVchangerate() {
    return (String) this.getAttributeValue(InvoiceItemVO.VCHANGERATE);
  }

  /** ��ͬ�� getter ���� */
  public String getVctcode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VCTCODE);
  }

  /** Դͷ���ݺ� getter ���� */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFIRSTCODE);
  }

  /** Դͷ�����к� getter ���� */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFIRSTROWNO);
  }

  /** Դͷ�������� getter ���� */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFIRSTTRANTYPE);
  }

  /** ���ɸ�������1 getter ���� */
  public String getVfree1() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE1);
  }

  /** ���ɸ�������10 getter ���� */
  public String getVfree10() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE10);
  }

  /** ���ɸ�������2 getter ���� */
  public String getVfree2() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE2);
  }

  /** ���ɸ�������3 getter ���� */
  public String getVfree3() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE3);
  }

  /** ���ɸ�������4 getter ���� */
  public String getVfree4() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE4);
  }

  /** ���ɸ�������5 getter ���� */
  public String getVfree5() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE5);
  }

  /** ���ɸ�������6 getter ���� */
  public String getVfree6() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE6);
  }

  /** ���ɸ�������7 getter ���� */
  public String getVfree7() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE7);
  }

  /** ���ɸ�������8 getter ���� */
  public String getVfree8() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE8);
  }

  /** ���ɸ�������9 getter ���� */
  public String getVfree9() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE9);
  }

  /** ��ע getter ���� */
  public String getVmemob() {
    return (String) this.getAttributeValue(InvoiceItemVO.VMEMOB);
  }

  /** ������ getter ���� */
  public String getVordercode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VORDERCODE);
  }

  /** ������������ getter ���� */
  public String getVordertrantype() {
    return (String) this.getAttributeValue(InvoiceItemVO.VORDERTRANTYPE);
  }

  /** ��Դ���ݺ� getter ���� */
  public String getVsourcecode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VSOURCECODE);
  }

  /** ��Դ�����к� getter ���� */
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(InvoiceItemVO.VSOURCEROWNO);
  }

  /** ��Դ�������� getter ���� */
  public String getVsourcetrantype() {
    return (String) this.getAttributeValue(InvoiceItemVO.VSOURCETRANTYPE);
  }

  /** ���ñ�������Ʊ���� setter ���� */
  public void setCadjustedinvid(String cadjustedinvid) {
    this.setAttributeValue(InvoiceItemVO.CADJUSTEDINVID, cadjustedinvid);
  }

  /** ���ñ�������Ʊ������ setter ���� */
  public void setCadjustedrowid(String cadjustedrowid) {
    this.setAttributeValue(InvoiceItemVO.CADJUSTEDROWID, cadjustedrowid);
  }

  /** �ͻ� setter ���� */
  public void setCasscustid(String casscustid) {
    this.setAttributeValue(InvoiceItemVO.CASSCUSTID, casscustid);
  }

  /** ��λ setter ���� */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(InvoiceItemVO.CASTUNITID, castunitid);
  }

  /** ���ú�ͬ���� setter ���� */
  public void setCcontractid(String ccontractid) {
    this.setAttributeValue(InvoiceItemVO.CCONTRACTID, ccontractid);
  }

  /** ���ú�ͬ������ setter ���� */
  public void setCcontractrowid(String ccontractrowid) {
    this.setAttributeValue(InvoiceItemVO.CCONTRACTROWID, ccontractrowid);
  }

  /** ������ **/
  public void setCffileid(String cffileid) {
    this.setAttributeValue(InvoiceItemVO.CFFILEID, cffileid);
  }

  /** Դͷ���������� setter ���� */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(InvoiceItemVO.CFIRSTBID, cfirstbid);
  }

  /** Դͷ�������� setter ���� */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(InvoiceItemVO.CFIRSTID, cfirstid);
  }

  /** Դͷ�������� setter ���� */
  public void setCfirsttypecode(String cfirsttypecode) {
    this.setAttributeValue(InvoiceItemVO.CFIRSTTYPECODE, cfirsttypecode);
  }

  /** �������� setter ���� */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(InvoiceItemVO.CPRODUCTORID, cproductorid);
  }

  /** ��Ŀ setter ���� */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(InvoiceItemVO.CPROJECTID, cprojectid);
  }

  /** ��Ŀ���� setter ���� */
  public void setCprojecttaskid(String cprojecttaskid) {
    this.setAttributeValue(InvoiceItemVO.CPROJECTTASKID, cprojecttaskid);
  }

  /** �����ȼ� setter ���� */
  public void setCqualitylevelid(String cqualitylevelid) {
    this.setAttributeValue(InvoiceItemVO.CQUALITYLEVELID, cqualitylevelid);
  }

  /** �к� setter ���� */
  public void setCrowno(String crowno) {
    this.setAttributeValue(InvoiceItemVO.CROWNO, crowno);
  }

  /** ��Դ���������� setter ���� */
  public void setCsourcebid(String csourcebid) {
    this.setAttributeValue(InvoiceItemVO.CSOURCEBID, csourcebid);
  }

  /** ��Դ�������� setter ���� */
  public void setCsourceid(String csourceid) {
    this.setAttributeValue(InvoiceItemVO.CSOURCEID, csourceid);
  }

  /** ��Դ�������� setter ���� */
  public void setCsourcetypecode(String csourcetypecode) {
    this.setAttributeValue(InvoiceItemVO.CSOURCETYPECODE, csourcetypecode);
  }

  /** ˰�� setter ���� */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(InvoiceItemVO.CTAXCODEID, ctaxcodeid);
  }

  /** ����λ setter ���� */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(InvoiceItemVO.CUNITID, cunitid);
  }

  /** ��Ʊ���� setter ���� */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(InvoiceItemVO.DBILLDATE, dbilldate);
  }

  /** dr setter ���� */
  public void setDr(Integer dr) {
    this.setAttributeValue(InvoiceItemVO.DR, dr);
  }

  /** ��Դ�������� setter ���� */
  public void setDsourcedate(UFDate dsourcedate) {
    this.setAttributeValue(InvoiceItemVO.DSOURCEDATE, dsourcedate);
  }

  /** Դͷ������TS setter ���� */
  public void setFirstbts(UFDateTime firstbts) {
    this.setAttributeValue(InvoiceItemVO.FIRSTBTS, firstbts);
  }

  /** Դͷ����TS setter ���� */
  public void setFirstts(UFDateTime firstts) {
    this.setAttributeValue(InvoiceItemVO.FIRSTTS, firstts);
  }

  /** ������ setter ���� */
  public void setFrowtype(Integer frowtype) {
    this.setAttributeValue(InvoiceItemVO.FROWTYPE, frowtype);
  }

  /** ��˰��� setter ���� */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(InvoiceItemVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /** �ۼƱ��ҽ����� setter ���� */
  public void setNaccumsettmny(UFDouble naccumsettmny) {
    this.setAttributeValue(InvoiceItemVO.NACCUMSETTMNY, naccumsettmny);
  }

  /** �ۼƽ��������� setter ���� */
  public void setNaccumsettnum(UFDouble naccumsettnum) {
    this.setAttributeValue(InvoiceItemVO.NACCUMSETTNUM, naccumsettnum);
  }

  /** ���õ������� setter ���� */
  public void setNadjustorgprice(UFDouble nadjustorgprice) {
    this.setAttributeValue(InvoiceItemVO.NADJUSTORGPRICE, nadjustorgprice);
  }

  /** ���� setter ���� */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(InvoiceItemVO.NASTNUM, nastnum);
  }

  /** ��˰���� setter ���� */
  public void setNastorigprice(UFDouble nastorigprice) {
    this.setAttributeValue(InvoiceItemVO.NASTORIGPRICE, nastorigprice);
  }

  /** ��˰���� setter ���� */
  public void setNastorigtaxprice(UFDouble nastorigtaxprice) {
    this.setAttributeValue(InvoiceItemVO.NASTORIGTAXPRICE, nastorigtaxprice);
  }

  /** ������˰���� setter ���� */
  public void setNastprice(UFDouble nastprice) {
    this.setAttributeValue(InvoiceItemVO.NASTPRICE, nastprice);
  }

  /** ���Һ�˰���� setter ���� */
  public void setNasttaxprice(UFDouble nasttaxprice) {
    this.setAttributeValue(InvoiceItemVO.NASTTAXPRICE, nasttaxprice);
  }

  /** �Ƴɱ���� setter ���� */
  public void setNcalcostmny(UFDouble ncalcostmny) {
    this.setAttributeValue(InvoiceItemVO.NCALCOSTMNY, ncalcostmny);
  }

  /** ��˰��� setter ���� */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(InvoiceItemVO.NCALTAXMNY, ncaltaxmny);
  }

  /** ȫ�ֱ�����˰��� setter ���� */
  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(InvoiceItemVO.NGLOBALMNY, nglobalmny);
  }

  /** ȫ�ֱ��Ҽ�˰�ϼ� setter ���� */
  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(InvoiceItemVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  /** ���ű�����˰��� setter ���� */
  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(InvoiceItemVO.NGROUPMNY, ngroupmny);
  }

  /** ���ű��Ҽ�˰�ϼ� setter ���� */
  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(InvoiceItemVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  /** ������˰��� setter ���� */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(InvoiceItemVO.NMNY, nmny);
  }

  /** ���ɵֿ�˰�� setter ���� */
  public void setNnosubtax(UFDouble nnosubtax) {
    this.setAttributeValue(InvoiceItemVO.NNOSUBTAX, nnosubtax);
  }

  /** ���ɵֿ�˰�� setter ���� */
  public void setNnosubtaxrate(UFDouble nnosubtaxrate) {
    this.setAttributeValue(InvoiceItemVO.NNOSUBTAXRATE, nnosubtaxrate);
  }

  /** ������ setter ���� */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(InvoiceItemVO.NNUM, nnum);
  }

  /** �������ԣ����ڻ�д��Դ=������������������� */
  public void setNnumwrbck(UFDouble nnumwrbck) {
    this.setAttributeValue(InvoiceItemVO.NNUMWRBCK, nnumwrbck);
  }

  /** ��˰��� setter ���� */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(InvoiceItemVO.NORIGMNY, norigmny);
  }

  /** ����˰���� setter ���� */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(InvoiceItemVO.NORIGPRICE, norigprice);
  }

  /** ��˰�ϼ� setter ���� */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(InvoiceItemVO.NORIGTAXMNY, norigtaxmny);
  }

  /** ����˰���� setter ���� */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(InvoiceItemVO.NORIGTAXPRICE, norigtaxprice);
  }

  /** �ƻ��� setter ���� */
  public void setNplanprice(UFDouble nplanprice) {
    this.setAttributeValue(InvoiceItemVO.NPLANPRICE, nplanprice);
  }

  /** ��������˰���� setter ���� */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(InvoiceItemVO.NPRICE, nprice);
  }

  /** ������������� setter ���� */
  public void setNreasonwastenum(UFDouble nreasonwastenum) {
    this.setAttributeValue(InvoiceItemVO.NREASONWASTENUM, nreasonwastenum);
  }

  /** ��Դ���������� setter ���� */
  public void setNsourcenum(UFDouble nsourcenum) {
    this.setAttributeValue(InvoiceItemVO.NSOURCENUM, nsourcenum);
  }

  /** ����˰�� setter ���� */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(InvoiceItemVO.NTAX, ntax);
  }

  /** ���Ҽ�˰�ϼ� setter ���� */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(InvoiceItemVO.NTAXMNY, ntaxmny);
  }

  /** �����Һ�˰���� setter ���� */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(InvoiceItemVO.NTAXPRICE, ntaxprice);
  }

  /** ˰�� setter ���� */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(InvoiceItemVO.NTAXRATE, ntaxrate);
  }

  /** Ӧ��������֯(OID) setter ���� */
  public void setPk_apfinanceorg(String pk_apfinanceorg) {
    this.setAttributeValue(InvoiceItemVO.PK_APFINANCEORG, pk_apfinanceorg);
  }

  /** Ӧ��������֯(VID) setter ���� */
  public void setPk_apfinanceorg_v(String pk_apfinanceorg_v) {
    this.setAttributeValue(InvoiceItemVO.PK_APFINANCEORG_V, pk_apfinanceorg_v);
  }

  /** ��������(OID) setter ���� */
  public void setPk_apliabcenter(String pk_apliabcenter) {
    this.setAttributeValue(InvoiceItemVO.PK_APLIABCENTER, pk_apliabcenter);
  }

  /** ��������(VID) setter ���� */
  public void setPk_apliabcenter_v(String pk_apliabcenter_v) {
    this.setAttributeValue(InvoiceItemVO.PK_APLIABCENTER_V, pk_apliabcenter_v);
  }

  /** ���ε��� setter ���� */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(InvoiceItemVO.PK_BATCHCODE, pk_batchcode);
  }

  /** ��֧��Ŀ setter ���� */
  public void setPk_costsubj(String pk_costsubj) {
    this.setAttributeValue(InvoiceItemVO.PK_COSTSUBJ, pk_costsubj);
  }

  /** �������� setter ���� */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(InvoiceItemVO.PK_GROUP, pk_group);
  }

  /** ��Ʊ��ʵ�� setter ���� */
  public void setPk_invoice(String pk_invoice) {
    this.setAttributeValue(InvoiceItemVO.PK_INVOICE, pk_invoice);
  }

  /** �ɹ���Ʊ��ϸ setter ���� */
  public void setPk_invoice_b(String pk_invoice_b) {
    this.setAttributeValue(InvoiceItemVO.PK_INVOICE_B, pk_invoice_b);
  }

  /** ����(VID) setter ���� */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(InvoiceItemVO.PK_MATERIAL, pk_material);
  }

  /** �ɹ��������� setter ���� */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(InvoiceItemVO.PK_ORDER, pk_order);
  }

  /** �ɹ����������� setter ���� */
  public void setPk_order_b(String pk_order_b) {
    this.setAttributeValue(InvoiceItemVO.PK_ORDER_B, pk_order_b);
  }

  /** ������֯(OID) setter ���� */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(InvoiceItemVO.PK_ORG, pk_org);
  }

  /** ������֯(VID) setter ���� */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(InvoiceItemVO.PK_ORG_V, pk_org_v);
  }

  /** ����(OID) setter ���� */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(InvoiceItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** ��ⵥ��ID setter ���� */
  public void setPk_stockps_b(String pk_stockps_b) {
    this.setAttributeValue(InvoiceItemVO.PK_STOCKPS_B, pk_stockps_b);
  }

  /** �ֿ� setter ���� */
  public void setPk_stordoc(String pk_stordoc) {
    this.setAttributeValue(InvoiceItemVO.PK_STORDOC, pk_stordoc);
  }

  /** ��Ӧ�� setter ���� */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(InvoiceItemVO.PK_SUPPLIER, pk_supplier);
  }

  /** ʹ�ò���(OID) setter ���� */
  public void setPk_usedept(String pk_usedept) {
    this.setAttributeValue(InvoiceItemVO.PK_USEDEPT, pk_usedept);
  }

  /** ʹ�ò���(VID) setter ���� */
  public void setPk_usedept_v(String pk_usedept_v) {
    this.setAttributeValue(InvoiceItemVO.PK_USEDEPT_V, pk_usedept_v);
  }

  /** ��Դ������TS setter ���� */
  public void setSourcebts(UFDateTime sourcebts) {
    this.setAttributeValue(InvoiceItemVO.SOURCEBTS, sourcebts);
  }

  /** ��Դ����TS setter ���� */
  public void setSourcets(UFDateTime sourcets) {
    this.setAttributeValue(InvoiceItemVO.SOURCETS, sourcets);
  }

  /** ts setter ���� */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(InvoiceItemVO.TS, ts);
  }

  /** ���ñ�������Ʊ�� setter ���� */
  public void setVadjedbillcode(String vadjedbillcode) {
    this.setAttributeValue(InvoiceItemVO.VADJEDBILLCODE, vadjedbillcode);
  }

  /** ���κ� setter ���� */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(InvoiceItemVO.VBATCHCODE, vbatchcode);
  }

  /** �Զ�����1 setter ���� */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(InvoiceItemVO.VBDEF1, vbdef1);
  }

  /** �Զ�����10 setter ���� */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(InvoiceItemVO.VBDEF10, vbdef10);
  }

  /** �Զ�����11 setter ���� */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(InvoiceItemVO.VBDEF11, vbdef11);
  }

  /** �Զ�����12 setter ���� */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(InvoiceItemVO.VBDEF12, vbdef12);
  }

  /** �Զ�����13 setter ���� */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(InvoiceItemVO.VBDEF13, vbdef13);
  }

  /** �Զ�����14 setter ���� */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(InvoiceItemVO.VBDEF14, vbdef14);
  }

  /** �Զ�����15 setter ���� */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(InvoiceItemVO.VBDEF15, vbdef15);
  }

  /** �Զ�����16 setter ���� */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(InvoiceItemVO.VBDEF16, vbdef16);
  }

  /** �Զ�����17 setter ���� */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(InvoiceItemVO.VBDEF17, vbdef17);
  }

  /** �Զ�����18 setter ���� */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(InvoiceItemVO.VBDEF18, vbdef18);
  }

  /** �Զ�����19 setter ���� */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(InvoiceItemVO.VBDEF19, vbdef19);
  }

  /** �Զ�����2 setter ���� */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(InvoiceItemVO.VBDEF2, vbdef2);
  }

  /** �Զ�����20 setter ���� */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(InvoiceItemVO.VBDEF20, vbdef20);
  }

  /** �Զ�����3 setter ���� */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(InvoiceItemVO.VBDEF3, vbdef3);
  }

  /** �Զ�����4 setter ���� */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(InvoiceItemVO.VBDEF4, vbdef4);
  }

  /** �Զ�����5 setter ���� */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(InvoiceItemVO.VBDEF5, vbdef5);
  }

  /** �Զ�����6 setter ���� */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(InvoiceItemVO.VBDEF6, vbdef6);
  }

  /** �Զ�����7 setter ���� */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(InvoiceItemVO.VBDEF7, vbdef7);
  }

  /** �Զ�����8 setter ���� */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(InvoiceItemVO.VBDEF8, vbdef8);
  }

  /** �Զ�����9 setter ���� */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(InvoiceItemVO.VBDEF9, vbdef9);
  }

  /** ������ setter ���� */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(InvoiceItemVO.VCHANGERATE, vchangerate);
  }

  /** ��ͬ�� setter ���� */
  public void setVctcode(String vctcode) {
    this.setAttributeValue(InvoiceItemVO.VCTCODE, vctcode);
  }

  /** Դͷ���ݺ� setter ���� */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(InvoiceItemVO.VFIRSTCODE, vfirstcode);
  }

  /** Դͷ�����к� setter ���� */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(InvoiceItemVO.VFIRSTROWNO, vfirstrowno);
  }

  /** Դͷ�������� setter ���� */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(InvoiceItemVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /** ���ɸ�������1 setter ���� */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(InvoiceItemVO.VFREE1, vfree1);
  }

  /** ���ɸ�������10 setter ���� */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(InvoiceItemVO.VFREE10, vfree10);
  }

  /** ���ɸ�������2 setter ���� */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(InvoiceItemVO.VFREE2, vfree2);
  }

  /** ���ɸ�������3 setter ���� */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(InvoiceItemVO.VFREE3, vfree3);
  }

  /** ���ɸ�������4 setter ���� */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(InvoiceItemVO.VFREE4, vfree4);
  }

  /** ���ɸ�������5 setter ���� */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(InvoiceItemVO.VFREE5, vfree5);
  }

  /** ���ɸ�������6 setter ���� */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(InvoiceItemVO.VFREE6, vfree6);
  }

  /** ���ɸ�������7 setter ���� */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(InvoiceItemVO.VFREE7, vfree7);
  }

  /** ���ɸ�������8 setter ���� */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(InvoiceItemVO.VFREE8, vfree8);
  }

  /** ���ɸ�������9 setter ���� */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(InvoiceItemVO.VFREE9, vfree9);
  }

  /** ��ע setter ���� */
  public void setVmemob(String vmemob) {
    this.setAttributeValue(InvoiceItemVO.VMEMOB, vmemob);
  }

  /** ������ setter ���� */
  public void setVordercode(String vordercode) {
    this.setAttributeValue(InvoiceItemVO.VORDERCODE, vordercode);
  }

  /** ������������ setter ���� */
  public void setVordertrantype(String vordertrantype) {
    this.setAttributeValue(InvoiceItemVO.VORDERTRANTYPE, vordertrantype);
  }

  /** ��Դ���ݺ� setter ���� */
  public void setVsourcecode(String vsourcecode) {
    this.setAttributeValue(InvoiceItemVO.VSOURCECODE, vsourcecode);
  }

  /** ��Դ�����к� setter ���� */
  public void setVsourcerowno(String vsourcerowno) {
    this.setAttributeValue(InvoiceItemVO.VSOURCEROWNO, vsourcerowno);
  }

  /** ��Դ�������� setter ���� */
  public void setVsourcetrantype(String vsourcetrantype) {
    this.setAttributeValue(InvoiceItemVO.VSOURCETRANTYPE, vsourcetrantype);
  }

}
