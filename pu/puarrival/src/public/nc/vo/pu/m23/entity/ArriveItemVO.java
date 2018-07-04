package nc.vo.pu.m23.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ı���VO��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 ����03:44:40
 */
public class ArriveItemVO extends SuperVO {

  /** �˻�����ԭ�������� */
  public static final String BBACKREFORDER = "bbackreforder";

  /** �����Ƿ��� */
  public static final String BC_BSEAL = "bc_bseal";

  /** ���������ȼ� */
  public static final String BC_CQUALITYLEVELID = "bc_cqualitylevelid";

  /** ���ο��״̬ */
  public static final String BC_CSTATEID = "bc_cstateid";

  /** �����γ�ʱ�� */
  public static final String BC_TBATCHTIME = "bc_tbatchtime";

  /** ����ʱ�� */
  public static final String BC_TCHECKTIME = "bc_tchecktime";

  /** ����ʱ��� */
  public static final String BC_TS = "bc_ts";

  /** �����Զ�����1 */
  public static final String BC_VDEF1 = "bc_vdef1";

  /** �����Զ�����10 */
  public static final String BC_VDEF10 = "bc_vdef10";

  /** �����Զ�����11 */
  public static final String BC_VDEF11 = "bc_vdef11";

  /** �����Զ�����12 */
  public static final String BC_VDEF12 = "bc_vdef12";

  /** �����Զ�����13 */
  public static final String BC_VDEF13 = "bc_vdef13";

  /** �����Զ�����14 */
  public static final String BC_VDEF14 = "bc_vdef14";

  /** �����Զ�����15 */
  public static final String BC_VDEF15 = "bc_vdef15";

  /** �����Զ�����16 */
  public static final String BC_VDEF16 = "bc_vdef16";

  /** �����Զ�����17 */
  public static final String BC_VDEF17 = "bc_vdef17";

  /** �����Զ�����18 */
  public static final String BC_VDEF18 = "bc_vdef18";

  /** �����Զ�����19 */
  public static final String BC_VDEF19 = "bc_vdef19";

  /** �����Զ�����2 */
  public static final String BC_VDEF2 = "bc_vdef2";

  /** �����Զ�����20 */
  public static final String BC_VDEF20 = "bc_vdef20";

  /** �����Զ�����3 */
  public static final String BC_VDEF3 = "bc_vdef3";

  /** �����Զ�����4 */
  public static final String BC_VDEF4 = "bc_vdef4";

  /** �����Զ�����5 */
  public static final String BC_VDEF5 = "bc_vdef5";

  /** �����Զ�����6 */
  public static final String BC_VDEF6 = "bc_vdef6";

  /** �����Զ�����7 */
  public static final String BC_VDEF7 = "bc_vdef7";

  /** �����Զ�����8 */
  public static final String BC_VDEF8 = "bc_vdef8";

  /** �����Զ�����9 */
  public static final String BC_VDEF9 = "bc_vdef9";

  /** ���ΰ汾 */
  public static final String BC_VERSION = "bc_version";

  /** ����ɢ���� */
  public static final String BC_VHASHCODE = "bc_vhashcode";

  /** ���α�ע */
  public static final String BC_VNOTE = "bc_vnote";

  /** ��Ӧ�����κ� */
  public static final String BC_VVENDBATCHCODE = "bc_vvendbatchcode";

  /**
   * �����رգ��ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   */
  public static final String BCLOSEORDER = "bcloseorder";

  /** �������ʲ�Ƭ */
  public static final String BFAFLAG = "bfaflag";

  /** �̶������� */
  public static final String BFIXEDRATE = "bfixedrate";

  /** �Ƿ��������״̬ */
  public static final String BLETGOSTATE = "bletgostate";

  /** ��Ʒ */
  public static final String BPRESENT = "bpresent";

  /** ��Դ�������Ƿ���Ʒ */
  public static final String BPRESENTSOURCE = "bpresentsource";

  /** ������ת�̵� */
  public static final String BTRANSASSET = "btransasset";

  /** ����ó�ף�Ϊ���ɹ���ֵ����ӵ��ֶΣ� */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /** �ͻ� */
  public static final String CASSCUSTID = "casscustid";

  /** ��λ */
  public static final String CASTUNITID = "castunitid";

  /** ���ұ��� */
  public static final String CCURRENCYID = "ccurrencyid";

  /** ������ **/
  public static final String CFFILEID = "cffileid";

  /** Դͷ������ϸ */
  public static final String CFIRSTBID = "cfirstbid";

  /** Դͷ���� */
  public static final String CFIRSTID = "cfirstid";

  /** Դͷ�������� */
  public static final String CFIRSTTYPECODE = "cfirsttypecode";

  /** ԭ�ұ��� */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** �������е��к� */
  public static final String CPASSBOLLROWNO = "cpassbollrowno";

  /** �������� */
  public static final String CPRODUCTORID = "cproductorid";

  /** ��Ŀ */
  public static final String CPROJECTID = "cprojectid";

  /** ��Ŀ���� */
  public static final String CPROJECTTASKID = "cprojecttaskid";

  /** �����ȼ� */
  public static final String CQUALITYLEVELID = "cqualitylevelid";

  /** �ջ�����/������Ϊ���ɹ���ֵ����ӵ��ֶΣ� */
  public static final String CRECECOUNTRYID = "crececountryid";

  /** ������ */
  public static final String CREPORTERID = "creporterid";

  /** �к� */
  public static final String CROWNO = "crowno";

  /** ��������/������Ϊ���ɹ���ֵ����ӵ��ֶΣ� */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /** ��Դ��������ϸ */
  public static final String CSOURCEARRIVEBID = "csourcearrivebid";

  /** ��Դ������ */
  public static final String CSOURCEARRIVEID = "csourcearriveid";

  /** ��Դ������ϸ */
  public static final String CSOURCEBID = "csourcebid";

  /** ��Դ���� */
  public static final String CSOURCEID = "csourceid";

  /** ��Դ�������� */
  public static final String CSOURCETYPECODE = "csourcetypecode";

  /** ��˰����/������Ϊ���ɹ���ֵ����ӵ��ֶΣ� */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /** ����λ */
  public static final String CUNITID = "cunitid";

  /** �������� */
  public static final String DBILLDATE = "dbilldate";

  /** ʧЧ���� */
  public static final String DINVALIDDATE = "dinvaliddate";

  /** �ƻ��������� */
  public static final String DPLANRECEIVEDATE = "dplanreceivedate";

  /** �������� */
  public static final String DPRODUCEDATE = "dproducedate";

  /** dr */
  public static final String DR = "dr";

  /** �������� */
  public static final String DREPORTDATE = "dreportdate";

  /** �������ͣ�Ϊ���ɹ���ֵ����ӵ��ֶΣ� */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /** ��Ʒ���� ֧��ί������ */
  public static final String FPRODUCTCLASS = "fproductclass";

  /** ��˰��� */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /** ���������� */
  public static final String IVALIDDAY = "ivalidday";

  /** �ۼ��˻������� */
  public static final String NACCUMBACKNUM = "naccumbacknum";

  /** �ۼƱ��������� */
  public static final String NACCUMCHECKNUM = "naccumchecknum";

  /** �ۼƽ���������������� */
  public static final String NACCUMLETGOINNUM = "naccumletgoinnum";

  /** �ۼƽ������������� */
  public static final String NACCUMLETGONUM = "naccumletgonum";

  /** �ۼƲ��������� */
  public static final String NACCUMREPLNUM = "naccumreplnum";

  /** �ۼ���������� */
  public static final String NACCUMSTORENUM = "naccumstorenum";

  /** �������� */
  public static final String NASTNUM = "nastnum";

  /**
   * �ɵ������������ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   */
  public static final String NCANARRIVENUM = "ncanarrivenum";

  /** �ɲ��������� */
  public static final String NCANREPLNUM = "ncanreplnum";

  /** ����������� */
  public static final String NCANSTORENUM = "ncanstorenum";

  /** ���α������� */
  public static final String NCHECKNUM = "nchecknum";

  /** �ۼƺϸ������� */
  public static final String NELIGNUM = "nelignum";

  /** �۱����� */
  public static final String NEXCHANGERATE = "nexchangerate";

  /** ������˰��� */
  public static final String NMNY = "nmny";

  /** �ۼƲ��ϸ������� */
  public static final String NNOTELIGNUM = "nnotelignum";

  /** ���������� */
  public static final String NNUM = "nnum";

  /** ԭ����˰��� */
  public static final String NORIGMNY = "norigmny";

  /** ��ԭ����˰���� */
  public static final String NORIGPRICE = "norigprice";

  /** ԭ�Һ�˰��� */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /** ��ԭ�Һ�˰���� */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /** Ӧ������ */
  public static final String NPLANASTNUM = "nplanastnum";

  /** Ӧ�������� */
  public static final String NPLANNUM = "nplannum";

  /** ��Ʒ���� */
  public static final String NPRESENTASTNUM = "npresentastnum";

  /** ��Ʒ������ */
  public static final String NPRESENTNUM = "npresentnum";

  /** ��������˰���� */
  public static final String NPRICE = "nprice";

  /** ��Դ���������� */
  public static final String NSOURCENUM = "nsourcenum";

  /** ����˰�� */
  public static final String NTAX = "ntax";

  /** ���Һ�˰��� */
  public static final String NTAXMNY = "ntaxmny";

  /** �����Һ�˰���� */
  public static final String NTAXPRICE = "ntaxprice";

  /** ˰�� */
  public static final String NTAXRATE = "ntaxrate";

  /** ;������ */
  public static final String NWASTASTNUM = "nwastastnum";

  /** ;�������� */
  public static final String NWASTNUM = "nwastnum";

  /** �ϸ������� */
  public static final String NWILLELIGNUM = "nwillelignum";

  /** ���ϸ������� */
  public static final String NWILLNOTELIGNUM = "nwillnotelignum";

  /** Ӧ����֯���°汾 */
  public static final String PK_APFINANCEORG = "pk_apfinanceorg";

  /** Ӧ����֯ */
  public static final String PK_APFINANCEORG_V = "pk_apfinanceorg_v";

  /** ���������������°汾 */
  public static final String PK_APLIABCENTER = "pk_apliabcenter";

  /** ������������ */
  public static final String PK_APLIABCENTER_V = "pk_apliabcenter_v";

  /** ��������ϸ */
  public static final String PK_ARRIVEORDER = "pk_arriveorder";

  /** ��������ϸ */
  public static final String PK_ARRIVEORDER_B = "pk_arriveorder_b";

  /** �ջ������������°汾 */
  public static final String PK_ARRLIABCENTER = "pk_arrliabcenter";

  /** �ջ��������� */
  public static final String PK_ARRLIABCENTER_V = "pk_arrliabcenter_v";

  /** ���κ����� */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** ������ϸ���� */
  public static final String PK_CHECKDETAIL = "pk_checkdetail";

  /** ���� */
  public static final String PK_GROUP = "pk_group";

  /** ����VID */
  public static final String PK_MATERIAL = "pk_material";

  /** �ɹ����� */
  public static final String PK_ORDER = "pk_order";

  /** �ɹ�������ϸ */
  public static final String PK_ORDER_B = "pk_order_b";

  /** ���������ƻ� */
  public static final String PK_ORDER_BB1 = "pk_order_bb1";

  /** �����֯���°汾 */
  public static final String PK_ORG = "pk_org";

  /** �����֯ */
  public static final String PK_ORG_V = "pk_org_v";

  /** �������е����� */
  public static final String PK_PASSBILL = "pk_passbill";

  /** �������е��������� */
  public static final String PK_PASSBILL_B = "pk_passbill_b";

  /** ���������֯���°汾 */
  public static final String PK_PSFINANCEORG = "pk_psfinanceorg";

  /** ���������֯ */
  public static final String PK_PSFINANCEORG_V = "pk_psfinanceorg_v";

  /** �ʼ����� */
  public static final String PK_QCCENTER = "pk_qccenter";

  /** ��λ */
  public static final String PK_RACK = "pk_rack";

  /** �ջ��ֿ� */
  public static final String PK_RECEIVESTORE = "pk_receivestore";

  /** ��������֯���°汾 */
  public static final String PK_REQSTOORG = "pk_reqstoorg";

  /** ��������֯ */
  public static final String PK_REQSTOORG_V = "pk_reqstoorg_v";

  /** ����ֿ� */
  public static final String PK_REQSTORE = "pk_reqstore";

  /** ����OID */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** ��Դ������TS */
  public static final String SOURCEBTS = "sourcebts";

  /** ��Դ����TS */
  public static final String SOURCETS = "sourcets";

  /** ts */
  public static final String TS = "ts";

  /** �˻����� */
  public static final String VBACKREASONB = "vbackreasonb";

  /** ���κ�*/
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

  /** �������е��� */
  public static final String VPASSBILLCODE = "vpassbillcode";

  /** �������κ� */
  public static final String VPRODBATCHCODE = "vprodbatchcode";

  /** ��Դ���ݺ� */
  public static final String VSOURCECODE = "vsourcecode";

  /** ��Դ�����к� */
  public static final String VSOURCEROWNO = "vsourcerowno";

  /** ��Դ�������� */
  public static final String VSOURCETRANTYPE = "vsourcetrantype";

  private static final long serialVersionUID = 1L;

  /** �˻�����ԭ�������� getter ���� */
  public UFBoolean getBbackreforder() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BBACKREFORDER);
  }

  /** �����Ƿ��� getter ���� */
  public UFBoolean getBc_bseal() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BC_BSEAL);
  }

  /** ���������ȼ� getter ���� */
  public String getBc_cqualitylevelid() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_CQUALITYLEVELID);
  }

  /** ���ο��״̬ getter ���� */
  public String getBc_cstateid() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_CSTATEID);
  }

  /** �����γ�ʱ�� getter ���� */
  public UFDateTime getBc_tbatchtime() {
    return (UFDateTime) this.getAttributeValue(ArriveItemVO.BC_TBATCHTIME);
  }

  /** ����ʱ�� getter ���� */
  public UFDateTime getBc_tchecktime() {
    return (UFDateTime) this.getAttributeValue(ArriveItemVO.BC_TCHECKTIME);
  }

  /** ����ʱ��� getter ���� */
  public UFDateTime getBc_ts() {
    return (UFDateTime) this.getAttributeValue(ArriveItemVO.BC_TS);
  }

  /** �����Զ�����1 getter ���� */
  public String getBc_vdef1() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF1);
  }

  /** �����Զ�����10 getter ���� */
  public String getBc_vdef10() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF10);
  }

  /** �����Զ�����11 getter ���� */
  public String getBc_vdef11() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF11);
  }

  /** �����Զ�����12 getter ���� */
  public String getBc_vdef12() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF12);
  }

  /** �����Զ�����13 getter ���� */
  public String getBc_vdef13() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF13);
  }

  /** �����Զ�����14 getter ���� */
  public String getBc_vdef14() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF14);
  }

  /** �����Զ�����15 getter ���� */
  public String getBc_vdef15() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF15);
  }

  /** �����Զ�����16 getter ���� */
  public String getBc_vdef16() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF16);
  }

  /** �����Զ�����17 getter ���� */
  public String getBc_vdef17() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF17);
  }

  /** �����Զ�����18 getter ���� */
  public String getBc_vdef18() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF18);
  }

  /** �����Զ�����19 getter ���� */
  public String getBc_vdef19() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF19);
  }

  /** �����Զ�����2 getter ���� */
  public String getBc_vdef2() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF2);
  }

  /** �����Զ�����20 getter ���� */
  public String getBc_vdef20() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF20);
  }

  /** �����Զ�����3 getter ���� */
  public String getBc_vdef3() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF3);
  }

  /** �����Զ�����4 getter ���� */
  public String getBc_vdef4() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF4);
  }

  /** �����Զ�����5 getter ���� */
  public String getBc_vdef5() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF5);
  }

  /** �����Զ�����6 getter ���� */
  public String getBc_vdef6() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF6);
  }

  /** �����Զ�����7 getter ���� */
  public String getBc_vdef7() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF7);
  }

  /** �����Զ�����8 getter ���� */
  public String getBc_vdef8() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF8);
  }

  /** �����Զ�����9 getter ���� */
  public String getBc_vdef9() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VDEF9);
  }

  /** ���ΰ汾 getter ���� */
  public Integer getBc_version() {
    return (Integer) this.getAttributeValue(ArriveItemVO.BC_VERSION);
  }

  /** ����ɢ���� getter ���� */
  public String getBc_vhashcode() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VHASHCODE);
  }

  /** ���α�ע getter ���� */
  public String getBc_vnote() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VNOTE);
  }

  /** ��Ӧ�����κ� getter ���� */
  public String getBc_vvendbatchcode() {
    return (String) this.getAttributeValue(ArriveItemVO.BC_VVENDBATCHCODE);
  }

  /**
   * ��ȡ�����رգ��ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @return �����ر�
   */
  public UFBoolean getBcloseorder() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BCLOSEORDER);
  }

  /** �������ʲ�Ƭ getter ���� */
  public UFBoolean getBfaflag() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BFAFLAG);
  }

  /** �̶������� getter ���� */
  public UFBoolean getBfixedrate() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BFIXEDRATE);
  }

  /** �Ƿ��������״̬ getter ���� */
  public UFBoolean getBletgostate() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BLETGOSTATE);
  }

  /** ��Ʒ getter ���� */
  public UFBoolean getBpresent() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BPRESENT);
  }

  /** ��Դ�������Ƿ���Ʒ getter ���� */
  public UFBoolean getBpresentsource() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BPRESENTSOURCE);
  }

  /** ������ת�̵� getter ���� */
  public UFBoolean getBtransasset() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BTRANSASSET);
  }

  /** ����ó�� getter ���� */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(ArriveItemVO.BTRIATRADEFLAG);
  }

  /** �ͻ� getter ���� */
  public String getCasscustid() {
    return (String) this.getAttributeValue(ArriveItemVO.CASSCUSTID);
  }

  /** ��λ getter ���� */
  public String getCastunitid() {
    return (String) this.getAttributeValue(ArriveItemVO.CASTUNITID);
  }

  /** ���ұ��� getter ���� */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(ArriveItemVO.CCURRENCYID);
  }

  /** ������ **/
  public String getCffileid() {
    return (String) this.getAttributeValue(ArriveItemVO.CFFILEID);
  }

  /** Դͷ������ϸ getter ���� */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(ArriveItemVO.CFIRSTBID);
  }

  /** Դͷ���� getter ���� */
  public String getCfirstid() {
    return (String) this.getAttributeValue(ArriveItemVO.CFIRSTID);
  }

  /** Դͷ�������� getter ���� */
  public String getCfirsttypecode() {
    return (String) this.getAttributeValue(ArriveItemVO.CFIRSTTYPECODE);
  }

  /** ԭ�ұ��� getter ���� */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(ArriveItemVO.CORIGCURRENCYID);
  }

  /** �������е��к� getter ���� */
  public String getCpassbollrowno() {
    return (String) this.getAttributeValue(ArriveItemVO.CPASSBOLLROWNO);
  }

  /** �������� getter ���� */
  public String getCproductorid() {
    return (String) this.getAttributeValue(ArriveItemVO.CPRODUCTORID);
  }

  /** ��Ŀ getter ���� */
  public String getCprojectid() {
    return (String) this.getAttributeValue(ArriveItemVO.CPROJECTID);
  }

  /** ��Ŀ���� getter ���� */
  public String getCprojecttaskid() {
    return (String) this.getAttributeValue(ArriveItemVO.CPROJECTTASKID);
  }

  /** �����ȼ� getter ���� */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(ArriveItemVO.CQUALITYLEVELID);
  }

  /** �ջ�����/���� getter ���� */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(ArriveItemVO.CRECECOUNTRYID);
  }

  /** ������ getter ���� */
  public String getCreporterid() {
    return (String) this.getAttributeValue(ArriveItemVO.CREPORTERID);
  }

  /** �к� getter ���� */
  public String getCrowno() {
    return (String) this.getAttributeValue(ArriveItemVO.CROWNO);
  }

  /** ��������/���� getter ���� */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(ArriveItemVO.CSENDCOUNTRYID);
  }

  /** ��Դ��������ϸ getter���� */
  public String getCsourcearrivebid() {
    return (String) this.getAttributeValue(ArriveItemVO.CSOURCEARRIVEBID);
  }

  /** ��Դ������ getter���� */
  public String getCsourcearriveid() {
    return (String) this.getAttributeValue(ArriveItemVO.CSOURCEARRIVEID);
  }

  /** ��Դ������ϸ getter ���� */
  public String getCsourcebid() {
    return (String) this.getAttributeValue(ArriveItemVO.CSOURCEBID);
  }

  /** ��Դ���� getter ���� */
  public String getCsourceid() {
    return (String) this.getAttributeValue(ArriveItemVO.CSOURCEID);
  }

  /** ��Դ�������� getter ���� */
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(ArriveItemVO.CSOURCETYPECODE);
  }

  /** ��˰����/���� getter ���� */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(ArriveItemVO.CTAXCOUNTRYID);
  }

  /** ����λ getter ���� */
  public String getCunitid() {
    return (String) this.getAttributeValue(ArriveItemVO.CUNITID);
  }

  /** �������� getter ���� */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(ArriveItemVO.DBILLDATE);
  }

  /** ʧЧ���� getter ���� */
  public UFDate getDinvaliddate() {
    return (UFDate) this.getAttributeValue(ArriveItemVO.DINVALIDDATE);
  }

  /** �ƻ��������� getter ���� */
  public UFDate getDplanreceivedate() {
    return (UFDate) this.getAttributeValue(ArriveItemVO.DPLANRECEIVEDATE);
  }

  /** �������� getter ���� */
  public UFDate getDproducedate() {
    return (UFDate) this.getAttributeValue(ArriveItemVO.DPRODUCEDATE);
  }

  /** dr getter ���� */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(ArriveItemVO.DR);
  }

  /** �������� getter ���� */
  public UFDate getDreportdate() {
    return (UFDate) this.getAttributeValue(ArriveItemVO.DREPORTDATE);
  }

  /** �������� getter ���� */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(ArriveItemVO.FBUYSELLFLAG);
  }

  /** ��Ʒ���� getter ���� */
  public Integer getFproductclass() {
    return (Integer) this.getAttributeValue(ArriveItemVO.FPRODUCTCLASS);
  }

  /** ��˰��� getter ���� */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(ArriveItemVO.FTAXTYPEFLAG);
  }

  /** ���������� getter ���� */
  public Integer getIvalidday() {
    return (Integer) this.getAttributeValue(ArriveItemVO.IVALIDDAY);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M23_B);
  }

  /** �ۼ��˻������� getter���� */
  public UFDouble getNaccumbacknum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NACCUMBACKNUM);
  }

  /** �ۼƱ��������� getter ���� */
  public UFDouble getNaccumchecknum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NACCUMCHECKNUM);
  }

  /** �ۼƽ���������������� getter ���� */
  public UFDouble getNaccumletgoinnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NACCUMLETGOINNUM);
  }

  /** �ۼƽ������������� getter ���� */
  public UFDouble getNaccumletgonum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NACCUMLETGONUM);
  }

  /** �ۼƲ��������� getter ���� */
  public UFDouble getNaccumreplnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NACCUMREPLNUM);
  }

  /** �ۼ���������� getter ���� */
  public UFDouble getNaccumstorenum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NACCUMSTORENUM);
  }

  /** �������� getter ���� */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NASTNUM);
  }

  /**
   * ��ȡ�ɵ������������ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @return �ɵ���������
   */
  public UFDouble getNcanarrivenum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NCANARRIVENUM);
  }

  /** �ɲ��������� getter ���� */
  public UFDouble getNcanreplnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NCANREPLNUM);
  }

  /** ����������� getter ���� */
  public UFDouble getNcanstorenum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NCANSTORENUM);
  }

  /** ���α������� getter ���� */
  public UFDouble getNchecknum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NCHECKNUM);
  }

  /** �ۼƺϸ������� getter ���� */
  public UFDouble getNelignum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NELIGNUM);
  }

  /** �۱����� getter ���� */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NEXCHANGERATE);
  }

  /** ������˰��� getter ���� */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NMNY);
  }

  /** �ۼƲ��ϸ������� getter ���� */
  public UFDouble getNnotelignum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NNOTELIGNUM);
  }

  /** ���������� getter ���� */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NNUM);
  }

  /** ԭ����˰��� getter ���� */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NORIGMNY);
  }

  /** ��ԭ����˰���� getter ���� */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NORIGPRICE);
  }

  /** ԭ�Һ�˰��� getter ���� */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NORIGTAXMNY);
  }

  /** ��ԭ�Һ�˰���� getter ���� */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NORIGTAXPRICE);
  }

  /** Ӧ������ getter ���� */
  public UFDouble getNplanastnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NPLANASTNUM);
  }

  /** Ӧ�������� getter ���� */
  public UFDouble getNplannum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NPLANNUM);
  }

  /** ��Ʒ���� getter ���� */
  public UFDouble getNpresentastnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NPRESENTASTNUM);
  }

  /** ��Ʒ������ getter ���� */
  public UFDouble getNpresentnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NPRESENTNUM);
  }

  /** ��������˰���� getter ���� */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NPRICE);
  }

  /** ��Դ���������� getter ���� */
  public UFDouble getNsourcenum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NSOURCENUM);
  }

  /** ����˰�� getter ���� */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NTAX);
  }

  /** ���Һ�˰��� getter ���� */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NTAXMNY);
  }

  /** �����Һ�˰���� getter ���� */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NTAXPRICE);
  }

  /** ˰�� getter ���� */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NTAXRATE);
  }

  /** ;������ getter ���� */
  public UFDouble getNwastastnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NWASTASTNUM);
  }

  /** ;�������� getter ���� */
  public UFDouble getNwastnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NWASTNUM);
  }

  /** �ϸ������� getter ���� */
  public UFDouble getNwillelignum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NWILLELIGNUM);
  }

  /** ���ϸ������� getter ���� */
  public UFDouble getNwillnotelignum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NWILLNOTELIGNUM);
  }

  /** Ӧ����֯���°汾 getter ���� */
  public String getPk_apfinanceorg() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_APFINANCEORG);
  }

  /** Ӧ����֯ getter ���� */
  public String getPk_apfinanceorg_v() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_APFINANCEORG_V);
  }

  /** �ջ������������°汾getter ���� */
  public String getPk_apliabcenter() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ARRLIABCENTER);
  }

  /** �ջ���������getter ���� */
  public String getPk_apliabcenter_v() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ARRLIABCENTER_V);
  }

  /** ��������ϸ getter ���� */
  public String getPk_arriveorder() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ARRIVEORDER);
  }

  /** ��������ϸ getter ���� */
  public String getPk_arriveorder_b() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ARRIVEORDER_B);
  }

  /** ���������������°汾getter ���� */
  public String getPk_arrliabcenter() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_APLIABCENTER);
  }

  /** ������������getter ���� */
  public String getPk_arrliabcenter_v() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_APLIABCENTER_V);
  }

  /** ���κ����� getter ���� */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_BATCHCODE);
  }

  /** ������ϸ���� getter ���� */
  public String getPk_checkdetail() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_CHECKDETAIL);
  }

  /** ���� getter ���� */
  public String getPk_group() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_GROUP);
  }

  /** ����VID getter ���� */
  public String getPk_material() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_MATERIAL);
  }

  /** �ɹ����� getter ���� */
  public String getPk_order() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ORDER);
  }

  /** �ɹ�������ϸ getter ���� */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ORDER_B);
  }

  /** ���������ƻ� getter ���� */
  public String getPk_order_bb1() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ORDER_BB1);
  }

  /** �����֯���°汾 getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ORG);
  }

  /** �����֯ getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_ORG_V);
  }

  /** �������е����� getter ���� */
  public String getPk_passbill() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_PASSBILL);
  }

  /** �������е��������� getter ���� */
  public String getPk_passbill_b() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_PASSBILL_B);
  }

  /** ���������֯���°汾 getter ���� */
  public String getPk_psfinanceorg() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_PSFINANCEORG);
  }

  /** ���������֯ getter ���� */
  public String getPk_psfinanceorg_v() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_PSFINANCEORG_V);
  }

  /** �ʼ����� getter ���� */
  public String getPk_qccenter() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_QCCENTER);
  }

  /** ��λ getter ���� */
  public String getPk_rack() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_RACK);
  }

  /** �ջ��ֿ� getter ���� */
  public String getPk_receivestore() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_RECEIVESTORE);
  }

  /** ��������֯���°汾 getter ���� */
  public String getPk_reqstoorg() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_REQSTOORG);
  }

  /** ��������֯ getter ���� */
  public String getPk_reqstoorg_v() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_REQSTOORG_V);
  }

  /** ����ֿ� getter ���� */
  public String getPk_reqstore() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_REQSTORE);
  }

  /** ����OID getter ���� */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_SRCMATERIAL);
  }

  /** ��Դ������TS getter ���� */
  public UFDateTime getSourcebts() {
    return (UFDateTime) this.getAttributeValue(ArriveItemVO.SOURCEBTS);
  }

  /** ��Դ����TS getter ���� */
  public UFDateTime getSourcets() {
    return (UFDateTime) this.getAttributeValue(ArriveItemVO.SOURCETS);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(ArriveItemVO.TS);
  }

  /** �˻����� getter ���� */
  public String getVbackreasonb() {
    return (String) this.getAttributeValue(ArriveItemVO.VBACKREASONB);
  }

  /** ���κ� getter ���� */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(ArriveItemVO.VBATCHCODE);
  }

  /** �Զ�����1 getter ���� */
  public String getVbdef1() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF1);
  }

  /** �Զ�����10 getter ���� */
  public String getVbdef10() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF10);
  }

  /** �Զ�����11 getter ���� */
  public String getVbdef11() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF11);
  }

  /** �Զ�����12 getter ���� */
  public String getVbdef12() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF12);
  }

  /** �Զ�����13 getter ���� */
  public String getVbdef13() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF13);
  }

  /** �Զ�����14 getter ���� */
  public String getVbdef14() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF14);
  }

  /** �Զ�����15 getter ���� */
  public String getVbdef15() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF15);
  }

  /** �Զ�����16 getter ���� */
  public String getVbdef16() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF16);
  }

  /** �Զ�����17 getter ���� */
  public String getVbdef17() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF17);
  }

  /** �Զ�����18 getter ���� */
  public String getVbdef18() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF18);
  }

  /** �Զ�����19 getter ���� */
  public String getVbdef19() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF19);
  }

  /** �Զ�����2 getter ���� */
  public String getVbdef2() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF2);
  }

  /** �Զ�����20 getter ���� */
  public String getVbdef20() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF20);
  }

  /** �Զ�����3 getter ���� */
  public String getVbdef3() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF3);
  }

  /** �Զ�����4 getter ���� */
  public String getVbdef4() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF4);
  }

  /** �Զ�����5 getter ���� */
  public String getVbdef5() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF5);
  }

  /** �Զ�����6 getter ���� */
  public String getVbdef6() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF6);
  }

  /** �Զ�����7 getter ���� */
  public String getVbdef7() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF7);
  }

  /** �Զ�����8 getter ���� */
  public String getVbdef8() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF8);
  }

  /** �Զ�����9 getter ���� */
  public String getVbdef9() {
    return (String) this.getAttributeValue(ArriveItemVO.VBDEF9);
  }

  /** ������ getter ���� */
  public String getVchangerate() {
    return (String) this.getAttributeValue(ArriveItemVO.VCHANGERATE);
  }

  /** Դͷ���ݺ� getter ���� */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(ArriveItemVO.VFIRSTCODE);
  }

  /** Դͷ�����к� getter ���� */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(ArriveItemVO.VFIRSTROWNO);
  }

  /** Դͷ�������� getter ���� */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(ArriveItemVO.VFIRSTTRANTYPE);
  }

  /** ���ɸ�������1 getter ���� */
  public String getVfree1() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE1);
  }

  /** ���ɸ�������10 getter ���� */
  public String getVfree10() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE10);
  }

  /** ���ɸ�������2 getter ���� */
  public String getVfree2() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE2);
  }

  /** ���ɸ�������3 getter ���� */
  public String getVfree3() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE3);
  }

  /** ���ɸ�������4 getter ���� */
  public String getVfree4() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE4);
  }

  /** ���ɸ�������5 getter ���� */
  public String getVfree5() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE5);
  }

  /** ���ɸ�������6 getter ���� */
  public String getVfree6() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE6);
  }

  /** ���ɸ�������7 getter ���� */
  public String getVfree7() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE7);
  }

  /** ���ɸ�������8 getter ���� */
  public String getVfree8() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE8);
  }

  /** ���ɸ�������9 getter ���� */
  public String getVfree9() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE9);
  }

  /** ��ע getter ���� */
  public String getVmemob() {
    return (String) this.getAttributeValue(ArriveItemVO.VMEMOB);
  }

  /** �������е��� getter ���� */
  public String getVpassbillcode() {
    return (String) this.getAttributeValue(ArriveItemVO.VPASSBILLCODE);
  }

  /** �������κ� getter ���� */
  public String getVprodbatchcode() {
    return (String) this.getAttributeValue(ArriveItemVO.VPRODBATCHCODE);
  }

  /** ��Դ���ݺ� getter ���� */
  public String getVsourcecode() {
    return (String) this.getAttributeValue(ArriveItemVO.VSOURCECODE);
  }

  /** ��Դ�����к� getter ���� */
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(ArriveItemVO.VSOURCEROWNO);
  }

  /** ��Դ�������� getter ���� */
  public String getVsourcetrantype() {
    return (String) this.getAttributeValue(ArriveItemVO.VSOURCETRANTYPE);
  }

  /** �˻�����ԭ�������� setter ���� */
  public void setBbackreforder(UFBoolean bbackreforder) {
    this.setAttributeValue(ArriveItemVO.BBACKREFORDER, bbackreforder);
  }

  /** �����Ƿ��� setter ���� */
  public void setBc_bseal(UFBoolean bc_bseal) {
    this.setAttributeValue(ArriveItemVO.BC_BSEAL, bc_bseal);
  }

  /** ���������ȼ� setter ���� */
  public void setBc_cqualitylevelid(String bc_cqualitylevelid) {
    this.setAttributeValue(ArriveItemVO.BC_CQUALITYLEVELID, bc_cqualitylevelid);
  }

  /** ���ο��״̬ setter ���� */
  public void setBc_cstateid(String bc_cstateid) {
    this.setAttributeValue(ArriveItemVO.BC_CSTATEID, bc_cstateid);
  }

  /** �����γ�ʱ�� setter ���� */
  public void setBc_tbatchtime(UFDateTime bc_tbatchtime) {
    this.setAttributeValue(ArriveItemVO.BC_TBATCHTIME, bc_tbatchtime);
  }

  /** ����ʱ�� setter ���� */
  public void setBc_tchecktime(UFDateTime bc_tchecktime) {
    this.setAttributeValue(ArriveItemVO.BC_TCHECKTIME, bc_tchecktime);
  }

  /** ����ʱ��� setter ���� */
  public void setBc_ts(UFDateTime bc_ts) {
    this.setAttributeValue(ArriveItemVO.BC_TS, bc_ts);
  }

  /** �����Զ�����1 setter ���� */
  public void setBc_vdef1(String bc_vdef1) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF1, bc_vdef1);
  }

  /** �����Զ�����10 setter ���� */
  public void setBc_vdef10(String bc_vdef10) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF10, bc_vdef10);
  }

  /** �����Զ�����11 setter ���� */
  public void setBc_vdef11(String bc_vdef11) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF11, bc_vdef11);
  }

  /** �����Զ�����12 setter ���� */
  public void setBc_vdef12(String bc_vdef12) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF12, bc_vdef12);
  }

  /** �����Զ�����13 setter ���� */
  public void setBc_vdef13(String bc_vdef13) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF13, bc_vdef13);
  }

  /** �����Զ�����14 setter ���� */
  public void setBc_vdef14(String bc_vdef14) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF14, bc_vdef14);
  }

  /** �����Զ�����15 setter ���� */
  public void setBc_vdef15(String bc_vdef15) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF15, bc_vdef15);
  }

  /** �����Զ�����16 setter ���� */
  public void setBc_vdef16(String bc_vdef16) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF16, bc_vdef16);
  }

  /** �����Զ�����17 setter ���� */
  public void setBc_vdef17(String bc_vdef17) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF17, bc_vdef17);
  }

  /** �����Զ�����18 setter ���� */
  public void setBc_vdef18(String bc_vdef18) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF18, bc_vdef18);
  }

  /** �����Զ�����19 setter ���� */
  public void setBc_vdef19(String bc_vdef19) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF19, bc_vdef19);
  }

  /** �����Զ�����2 setter ���� */
  public void setBc_vdef2(String bc_vdef2) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF2, bc_vdef2);
  }

  /** �����Զ�����20 setter ���� */
  public void setBc_vdef20(String bc_vdef20) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF20, bc_vdef20);
  }

  /** �����Զ�����3 setter ���� */
  public void setBc_vdef3(String bc_vdef3) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF3, bc_vdef3);
  }

  /** �����Զ�����4 setter ���� */
  public void setBc_vdef4(String bc_vdef4) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF4, bc_vdef4);
  }

  /** �����Զ�����5 setter ���� */
  public void setBc_vdef5(String bc_vdef5) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF5, bc_vdef5);
  }

  /** �����Զ�����6 setter ���� */
  public void setBc_vdef6(String bc_vdef6) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF6, bc_vdef6);
  }

  /** �����Զ�����7 setter ���� */
  public void setBc_vdef7(String bc_vdef7) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF7, bc_vdef7);
  }

  /** �����Զ�����8 setter ���� */
  public void setBc_vdef8(String bc_vdef8) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF8, bc_vdef8);
  }

  /** �����Զ�����9 setter ���� */
  public void setBc_vdef9(String bc_vdef9) {
    this.setAttributeValue(ArriveItemVO.BC_VDEF9, bc_vdef9);
  }

  /** ���ΰ汾 setter ���� */
  public void setBc_version(Integer bc_version) {
    this.setAttributeValue(ArriveItemVO.BC_VERSION, bc_version);
  }

  /** ����ɢ���� setter ���� */
  public void setBc_vhashcode(String bc_vhashcode) {
    this.setAttributeValue(ArriveItemVO.BC_VHASHCODE, bc_vhashcode);
  }

  /** ���α�ע setter ���� */
  public void setBc_vnote(String bc_vnote) {
    this.setAttributeValue(ArriveItemVO.BC_VNOTE, bc_vnote);
  }

  /** ��Ӧ�����κ� setter ���� */
  public void setBc_vvendbatchcode(String bc_vvendbatchcode) {
    this.setAttributeValue(ArriveItemVO.BC_VVENDBATCHCODE, bc_vvendbatchcode);
  }

  /**
   * ���õ����رգ��ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @param bcloseorder �����ر�
   */
  public void setBcloseorder(UFBoolean bcloseorder) {
    this.setAttributeValue(ArriveItemVO.BCLOSEORDER, bcloseorder);
  }

  /** �������ʲ�Ƭ setter ���� */
  public void setBfaflag(UFBoolean bfaflag) {
    this.setAttributeValue(ArriveItemVO.BFAFLAG, bfaflag);
  }

  /** �̶������� setter ���� */
  public void setBfixedrate(UFBoolean bfixedrate) {
    this.setAttributeValue(ArriveItemVO.BFIXEDRATE, bfixedrate);
  }

  /** �Ƿ��������״̬ setter ���� */
  public void setBletgostate(UFBoolean bletgostate) {
    this.setAttributeValue(ArriveItemVO.BLETGOSTATE, bletgostate);
  }

  /** ��Ʒ setter ���� */
  public void setBpresent(UFBoolean bpresent) {
    this.setAttributeValue(ArriveItemVO.BPRESENT, bpresent);
  }

  /** ��Դ�������Ƿ���Ʒ setter ���� */
  public void setBpresentsource(UFBoolean bpresentsource) {
    this.setAttributeValue(ArriveItemVO.BPRESENTSOURCE, bpresentsource);
  }

  /** ������ת�̵� setter ���� */
  public void setBtransasset(UFBoolean btransasset) {
    this.setAttributeValue(ArriveItemVO.BTRANSASSET, btransasset);
  }

  /** ����ó�� setter ���� */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(ArriveItemVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /** �ͻ� setter ���� */
  public void setCasscustid(String casscustid) {
    this.setAttributeValue(ArriveItemVO.CASSCUSTID, casscustid);
  }

  /** ��λ setter ���� */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(ArriveItemVO.CASTUNITID, castunitid);
  }

  /** ���ұ��� setter ���� */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(ArriveItemVO.CCURRENCYID, ccurrencyid);
  }

  /** ������ **/
  public void setCffileid(String cffileid) {
    this.setAttributeValue(ArriveItemVO.CFFILEID, cffileid);
  }

  /** Դͷ������ϸ setter ���� */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(ArriveItemVO.CFIRSTBID, cfirstbid);
  }

  /** Դͷ���� setter ���� */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(ArriveItemVO.CFIRSTID, cfirstid);
  }

  /** Դͷ�������� setter ���� */
  public void setCfirsttypecode(String cfirsttypecode) {
    this.setAttributeValue(ArriveItemVO.CFIRSTTYPECODE, cfirsttypecode);
  }

  /** ԭ�ұ��� setter ���� */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(ArriveItemVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /** �������е��к� setter ���� */
  public void setCpassbollrowno(String cpassbollrowno) {
    this.setAttributeValue(ArriveItemVO.CPASSBOLLROWNO, cpassbollrowno);
  }

  /** �������� setter ���� */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(ArriveItemVO.CPRODUCTORID, cproductorid);
  }

  /** ��Ŀ setter ���� */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(ArriveItemVO.CPROJECTID, cprojectid);
  }

  /** ��Ŀ���� setter ���� */
  public void setCprojecttaskid(String cprojecttaskid) {
    this.setAttributeValue(ArriveItemVO.CPROJECTTASKID, cprojecttaskid);
  }

  /** �����ȼ� setter ���� */
  public void setCqualitylevelid(String cqualitylevelid) {
    this.setAttributeValue(ArriveItemVO.CQUALITYLEVELID, cqualitylevelid);
  }

  /** �ջ�����/���� setter ���� */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(ArriveItemVO.CRECECOUNTRYID, crececountryid);
  }

  /** ������ setter ���� */
  public void setCreporterid(String creporterid) {
    this.setAttributeValue(ArriveItemVO.CREPORTERID, creporterid);
  }

  /** �к� setter ���� */
  public void setCrowno(String crowno) {
    this.setAttributeValue(ArriveItemVO.CROWNO, crowno);
  }

  /** ��������/���� setter ���� */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(ArriveItemVO.CSENDCOUNTRYID, csendcountryid);
  }

  /** ��Դ��������ϸ setter���� */
  public void setCsourcearrivebid(String csourcearrivebid) {
    this.setAttributeValue(ArriveItemVO.CSOURCEARRIVEBID, csourcearrivebid);
  }

  /** ��Դ������ setter���� */
  public void setCsourcearriveid(String csourcearriveid) {
    this.setAttributeValue(ArriveItemVO.CSOURCEARRIVEID, csourcearriveid);
  }

  /** ��Դ������ϸ setter ���� */
  public void setCsourcebid(String csourcebid) {
    this.setAttributeValue(ArriveItemVO.CSOURCEBID, csourcebid);
  }

  /** ��Դ���� setter ���� */
  public void setCsourceid(String csourceid) {
    this.setAttributeValue(ArriveItemVO.CSOURCEID, csourceid);
  }

  /** ��Դ�������� setter ���� */
  public void setCsourcetypecode(String csourcetypecode) {
    this.setAttributeValue(ArriveItemVO.CSOURCETYPECODE, csourcetypecode);
  }

  /** ��˰����/���� setter ���� */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(ArriveItemVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /** ����λ setter ���� */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(ArriveItemVO.CUNITID, cunitid);
  }

  /** �������� setter ���� */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(ArriveItemVO.DBILLDATE, dbilldate);
  }

  /** ʧЧ���� setter ���� */
  public void setDinvaliddate(UFDate dinvaliddate) {
    this.setAttributeValue(ArriveItemVO.DINVALIDDATE, dinvaliddate);
  }

  /** �ƻ��������� setter ���� */
  public void setDplanreceivedate(UFDate dplanreceivedate) {
    this.setAttributeValue(ArriveItemVO.DPLANRECEIVEDATE, dplanreceivedate);
  }

  /** �������� setter ���� */
  public void setDproducedate(UFDate dproducedate) {
    this.setAttributeValue(ArriveItemVO.DPRODUCEDATE, dproducedate);
  }

  /** dr setter ���� */
  public void setDr(Integer dr) {
    this.setAttributeValue(ArriveItemVO.DR, dr);
  }

  /** �������� setter ���� */
  public void setDreportdate(UFDate dreportdate) {
    this.setAttributeValue(ArriveItemVO.DREPORTDATE, dreportdate);
  }

  /** �������� setter ���� */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(ArriveItemVO.FBUYSELLFLAG, fbuysellflag);
  }

  /** ��Ʒ���� setter ���� */
  public void setFproductclass(Integer fproductclass) {
    this.setAttributeValue(ArriveItemVO.FPRODUCTCLASS, fproductclass);
  }

  /** ��˰��� setter ���� */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(ArriveItemVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /** ���������� setter ���� */
  public void setIvalidday(Integer ivalidday) {
    this.setAttributeValue(ArriveItemVO.IVALIDDAY, ivalidday);
  }

  /** �ۼ��˻������� setter���� */
  public void setNaccumbacknum(UFDouble naccumbacknum) {
    this.setAttributeValue(ArriveItemVO.NACCUMBACKNUM, naccumbacknum);
  }

  /** �ۼƱ��������� setter ���� */
  public void setNaccumchecknum(UFDouble naccumchecknum) {
    this.setAttributeValue(ArriveItemVO.NACCUMCHECKNUM, naccumchecknum);
  }

  /** �ۼƽ���������������� setter ���� */
  public void setNaccumletgoinnum(UFDouble naccumletgoinnum) {
    this.setAttributeValue(ArriveItemVO.NACCUMLETGOINNUM, naccumletgoinnum);
  }

  /** �ۼƽ������������� setter ���� */
  public void setNaccumletgonum(UFDouble naccumletgonum) {
    this.setAttributeValue(ArriveItemVO.NACCUMLETGONUM, naccumletgonum);
  }

  /** �ۼƲ��������� setter ���� */
  public void setNaccumreplnum(UFDouble naccumreplnum) {
    this.setAttributeValue(ArriveItemVO.NACCUMREPLNUM, naccumreplnum);
  }

  /** �ۼ���������� setter ���� */
  public void setNaccumstorenum(UFDouble naccumstorenum) {
    this.setAttributeValue(ArriveItemVO.NACCUMSTORENUM, naccumstorenum);
  }

  /** �������� setter ���� */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(ArriveItemVO.NASTNUM, nastnum);
  }

  /**
   * ���ÿɵ������������ɳ������ֶΣ�����Զ�̴���ֵ�������ܱ��浽���ݿ⣩
   * 
   * @param ncanarrivenum �ɵ���������
   */
  public void setNcanarrivenum(UFDouble ncanarrivenum) {
    this.setAttributeValue(ArriveItemVO.NCANARRIVENUM, ncanarrivenum);
  }

  /** �ɲ��������� setter ���� */
  public void setNcanreplnum(UFDouble ncanreplnum) {
    this.setAttributeValue(ArriveItemVO.NCANREPLNUM, ncanreplnum);
  }

  /** ����������� setter ���� */
  public void setNcanstorenum(UFDouble ncanstorenum) {
    this.setAttributeValue(ArriveItemVO.NCANSTORENUM, ncanstorenum);
  }

  /** ���α������� setter ���� */
  public void setNchecknum(UFDouble nchecknum) {
    this.setAttributeValue(ArriveItemVO.NCHECKNUM, nchecknum);
  }

  /** �ۼƺϸ������� setter ���� */
  public void setNelignum(UFDouble nelignum) {
    this.setAttributeValue(ArriveItemVO.NELIGNUM, nelignum);
  }

  /** �۱����� setter ���� */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(ArriveItemVO.NEXCHANGERATE, nexchangerate);
  }

  /** ������˰��� setter ���� */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(ArriveItemVO.NMNY, nmny);
  }

  /** �ۼƲ��ϸ������� setter ���� */
  public void setNnotelignum(UFDouble nnotelignum) {
    this.setAttributeValue(ArriveItemVO.NNOTELIGNUM, nnotelignum);
  }

  /** ���������� setter ���� */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(ArriveItemVO.NNUM, nnum);
  }

  /** ԭ����˰��� setter ���� */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(ArriveItemVO.NORIGMNY, norigmny);
  }

  /** ��ԭ����˰���� setter ���� */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(ArriveItemVO.NORIGPRICE, norigprice);
  }

  /** ԭ�Һ�˰��� setter ���� */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(ArriveItemVO.NORIGTAXMNY, norigtaxmny);
  }

  /** ��ԭ�Һ�˰���� setter ���� */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(ArriveItemVO.NORIGTAXPRICE, norigtaxprice);
  }

  /** Ӧ������ setter ���� */
  public void setNplanastnum(UFDouble nplanastnum) {
    this.setAttributeValue(ArriveItemVO.NPLANASTNUM, nplanastnum);
  }

  /** Ӧ�������� setter ���� */
  public void setNplannum(UFDouble nplannum) {
    this.setAttributeValue(ArriveItemVO.NPLANNUM, nplannum);
  }

  /** ��Ʒ���� setter ���� */
  public void setNpresentastnum(UFDouble npresentastnum) {
    this.setAttributeValue(ArriveItemVO.NPRESENTASTNUM, npresentastnum);
  }

  /** ��Ʒ������ setter ���� */
  public void setNpresentnum(UFDouble npresentnum) {
    this.setAttributeValue(ArriveItemVO.NPRESENTNUM, npresentnum);
  }

  /** ��������˰���� setter ���� */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(ArriveItemVO.NPRICE, nprice);
  }

  /** ��Դ���������� setter ���� */
  public void setNsourcenum(UFDouble nsourcenum) {
    this.setAttributeValue(ArriveItemVO.NSOURCENUM, nsourcenum);
  }

  /** ����˰�� setter ���� */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(ArriveItemVO.NTAX, ntax);
  }

  /** ���Һ�˰��� setter ���� */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(ArriveItemVO.NTAXMNY, ntaxmny);
  }

  /** �����Һ�˰���� setter ���� */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(ArriveItemVO.NTAXPRICE, ntaxprice);
  }

  /** ˰�� setter ���� */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(ArriveItemVO.NTAXRATE, ntaxrate);
  }

  /** ;������ setter ���� */
  public void setNwastastnum(UFDouble nwastastnum) {
    this.setAttributeValue(ArriveItemVO.NWASTASTNUM, nwastastnum);
  }

  /** ;�������� setter ���� */
  public void setNwastnum(UFDouble nwastnum) {
    this.setAttributeValue(ArriveItemVO.NWASTNUM, nwastnum);
  }

  /** �ϸ������� setter ���� */
  public void setNwillelignum(UFDouble nwillelignum) {
    this.setAttributeValue(ArriveItemVO.NWILLELIGNUM, nwillelignum);
  }

  /** ���ϸ������� setter ���� */
  public void setNwillnotelignum(UFDouble nwillnotelignum) {
    this.setAttributeValue(ArriveItemVO.NWILLNOTELIGNUM, nwillnotelignum);
  }

  /** Ӧ����֯���°汾 setter ���� */
  public void setPk_apfinanceorg(String pk_apfinanceorg) {
    this.setAttributeValue(ArriveItemVO.PK_APFINANCEORG, pk_apfinanceorg);
  }

  /** Ӧ����֯ setter ���� */
  public void setPk_apfinanceorg_v(String pk_apfinanceorg_v) {
    this.setAttributeValue(ArriveItemVO.PK_APFINANCEORG_V, pk_apfinanceorg_v);
  }

  /** ���������������°汾 setter ���� */
  public void setPk_apliabcenter(String pk_apliabcenter) {
    this.setAttributeValue(ArriveItemVO.PK_APLIABCENTER, pk_apliabcenter);
  }

  /** ������������ setter ���� */
  public void setPk_apliabcenter_v(String pk_apliabcenter_v) {
    this.setAttributeValue(ArriveItemVO.PK_APLIABCENTER_V, pk_apliabcenter_v);
  }

  /** ��������ϸ setter ���� */
  public void setPk_arriveorder(String pk_arriveorder) {
    this.setAttributeValue(ArriveItemVO.PK_ARRIVEORDER, pk_arriveorder);
  }

  /** ��������ϸ setter ���� */
  public void setPk_arriveorder_b(String pk_arriveorder_b) {
    this.setAttributeValue(ArriveItemVO.PK_ARRIVEORDER_B, pk_arriveorder_b);
  }

  /** �ջ������������°汾 setter ���� */
  public void setPk_arrliabcenter(String pk_arrliabcenter) {
    this.setAttributeValue(ArriveItemVO.PK_ARRLIABCENTER, pk_arrliabcenter);
  }

  /** �ջ��������� setter ���� */
  public void setPk_arrliabcenter_v(String pk_arrliabcenter_v) {
    this.setAttributeValue(ArriveItemVO.PK_ARRLIABCENTER_V, pk_arrliabcenter_v);
  }

  /** ���κ����� setter ���� */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(ArriveItemVO.PK_BATCHCODE, pk_batchcode);
  }

  /** ������ϸ���� setter ���� */
  public void setPk_checkdetail(String pk_checkdetail) {
    this.setAttributeValue(ArriveItemVO.PK_CHECKDETAIL, pk_checkdetail);
  }

  /** ���� setter ���� */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(ArriveItemVO.PK_GROUP, pk_group);
  }

  /** ����VID setter ���� */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(ArriveItemVO.PK_MATERIAL, pk_material);
  }

  /** �ɹ����� setter ���� */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(ArriveItemVO.PK_ORDER, pk_order);
  }

  /** �ɹ�������ϸ setter ���� */
  public void setPk_order_b(String pk_order_b) {
    this.setAttributeValue(ArriveItemVO.PK_ORDER_B, pk_order_b);
  }

  /** ���������ƻ� setter ���� */
  public void setPk_order_bb1(String pk_order_bb1) {
    this.setAttributeValue(ArriveItemVO.PK_ORDER_BB1, pk_order_bb1);
  }

  /** �����֯���°汾 setter ���� */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(ArriveItemVO.PK_ORG, pk_org);
  }

  /** �����֯ setter ���� */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(ArriveItemVO.PK_ORG_V, pk_org_v);
  }

  /** �������е����� setter ���� */
  public void setPk_passbill(String pk_passbill) {
    this.setAttributeValue(ArriveItemVO.PK_PASSBILL, pk_passbill);
  }

  /** �������е��������� setter ���� */
  public void setPk_passbill_b(String pk_passbill_b) {
    this.setAttributeValue(ArriveItemVO.PK_PASSBILL_B, pk_passbill_b);
  }

  /** ���������֯���°汾 setter ���� */
  public void setPk_psfinanceorg(String pk_psfinanceorg) {
    this.setAttributeValue(ArriveItemVO.PK_PSFINANCEORG, pk_psfinanceorg);
  }

  /** ���������֯ setter ���� */
  public void setPk_psfinanceorg_v(String pk_psfinanceorg_v) {
    this.setAttributeValue(ArriveItemVO.PK_PSFINANCEORG_V, pk_psfinanceorg_v);
  }

  /** �ʼ����� setter ���� */
  public void setPk_qccenter(String pk_qccenter) {
    this.setAttributeValue(ArriveItemVO.PK_QCCENTER, pk_qccenter);
  }

  /** ��λ setter ���� */
  public void setPk_rack(String pk_rack) {
    this.setAttributeValue(ArriveItemVO.PK_RACK, pk_rack);
  }

  /** �ջ��ֿ� setter ���� */
  public void setPk_receivestore(String pk_receivestore) {
    this.setAttributeValue(ArriveItemVO.PK_RECEIVESTORE, pk_receivestore);
  }

  /** ��������֯���°汾 setter ���� */
  public void setPk_reqstoorg(String pk_reqstoorg) {
    this.setAttributeValue(ArriveItemVO.PK_REQSTOORG, pk_reqstoorg);
  }

  /** ��������֯ setter ���� */
  public void setPk_reqstoorg_v(String pk_reqstoorg_v) {
    this.setAttributeValue(ArriveItemVO.PK_REQSTOORG_V, pk_reqstoorg_v);
  }

  /** ����ֿ� setter ���� */
  public void setPk_reqstore(String pk_reqstore) {
    this.setAttributeValue(ArriveItemVO.PK_REQSTORE, pk_reqstore);
  }

  /** ����OID setter ���� */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(ArriveItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** ��Դ������TS setter ���� */
  public void setSourcebts(UFDateTime sourcebts) {
    this.setAttributeValue(ArriveItemVO.SOURCEBTS, sourcebts);
  }

  /** ��Դ����TS setter ���� */
  public void setSourcets(UFDateTime sourcets) {
    this.setAttributeValue(ArriveItemVO.SOURCETS, sourcets);
  }

  /** ts setter ���� */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(ArriveItemVO.TS, ts);
  }

  /** �˻����� setter ���� */
  public void setVbackreasonb(String vbackreasonb) {
    this.setAttributeValue(ArriveItemVO.VBACKREASONB, vbackreasonb);
  }

  /** ���κ� setter ���� */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(ArriveItemVO.VBATCHCODE, vbatchcode);
  }

  /** �Զ�����1 setter ���� */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(ArriveItemVO.VBDEF1, vbdef1);
  }

  /** �Զ�����10 setter ���� */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(ArriveItemVO.VBDEF10, vbdef10);
  }

  /** �Զ�����11 setter ���� */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(ArriveItemVO.VBDEF11, vbdef11);
  }

  /** �Զ�����12 setter ���� */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(ArriveItemVO.VBDEF12, vbdef12);
  }

  /** �Զ�����13 setter ���� */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(ArriveItemVO.VBDEF13, vbdef13);
  }

  /** �Զ�����14 setter ���� */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(ArriveItemVO.VBDEF14, vbdef14);
  }

  /** �Զ�����15 setter ���� */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(ArriveItemVO.VBDEF15, vbdef15);
  }

  /** �Զ�����16 setter ���� */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(ArriveItemVO.VBDEF16, vbdef16);
  }

  /** �Զ�����17 setter ���� */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(ArriveItemVO.VBDEF17, vbdef17);
  }

  /** �Զ�����18 setter ���� */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(ArriveItemVO.VBDEF18, vbdef18);
  }

  /** �Զ�����19 setter ���� */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(ArriveItemVO.VBDEF19, vbdef19);
  }

  /** �Զ�����2 setter ���� */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(ArriveItemVO.VBDEF2, vbdef2);
  }

  /** �Զ�����20 setter ���� */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(ArriveItemVO.VBDEF20, vbdef20);
  }

  /** �Զ�����3 setter ���� */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(ArriveItemVO.VBDEF3, vbdef3);
  }

  /** �Զ�����4 setter ���� */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(ArriveItemVO.VBDEF4, vbdef4);
  }

  /** �Զ�����5 setter ���� */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(ArriveItemVO.VBDEF5, vbdef5);
  }

  /** �Զ�����6 setter ���� */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(ArriveItemVO.VBDEF6, vbdef6);
  }

  /** �Զ�����7 setter ���� */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(ArriveItemVO.VBDEF7, vbdef7);
  }

  /** �Զ�����8 setter ���� */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(ArriveItemVO.VBDEF8, vbdef8);
  }

  /** �Զ�����9 setter ���� */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(ArriveItemVO.VBDEF9, vbdef9);
  }

  /** ������ setter ���� */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(ArriveItemVO.VCHANGERATE, vchangerate);
  }

  /** Դͷ���ݺ� setter ���� */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(ArriveItemVO.VFIRSTCODE, vfirstcode);
  }

  /** Դͷ�����к� setter ���� */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(ArriveItemVO.VFIRSTROWNO, vfirstrowno);
  }

  /** Դͷ�������� setter ���� */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(ArriveItemVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /** ���ɸ�������1 setter ���� */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(ArriveItemVO.VFREE1, vfree1);
  }

  /** ���ɸ�������10 setter ���� */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(ArriveItemVO.VFREE10, vfree10);
  }

  /** ���ɸ�������2 setter ���� */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(ArriveItemVO.VFREE2, vfree2);
  }

  /** ���ɸ�������3 setter ���� */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(ArriveItemVO.VFREE3, vfree3);
  }

  /** ���ɸ�������4 setter ���� */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(ArriveItemVO.VFREE4, vfree4);
  }

  /** ���ɸ�������5 setter ���� */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(ArriveItemVO.VFREE5, vfree5);
  }

  /** ���ɸ�������6 setter ���� */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(ArriveItemVO.VFREE6, vfree6);
  }

  /** ���ɸ�������7 setter ���� */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(ArriveItemVO.VFREE7, vfree7);
  }

  /** ���ɸ�������8 setter ���� */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(ArriveItemVO.VFREE8, vfree8);
  }

  /** ���ɸ�������9 setter ���� */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(ArriveItemVO.VFREE9, vfree9);
  }

  /** ��ע setter ���� */
  public void setVmemob(String vmemob) {
    this.setAttributeValue(ArriveItemVO.VMEMOB, vmemob);
  }

  /** �������е��� setter ���� */
  public void setVpassbillcode(String vpassbillcode) {
    this.setAttributeValue(ArriveItemVO.VPASSBILLCODE, vpassbillcode);
  }

  /** �������κ� setter ���� */
  public void setVprodbatchcode(String vprodbatchcode) {
    this.setAttributeValue(ArriveItemVO.VPRODBATCHCODE, vprodbatchcode);
  }

  /** ��Դ���ݺ� setter ���� */
  public void setVsourcecode(String vsourcecode) {
    this.setAttributeValue(ArriveItemVO.VSOURCECODE, vsourcecode);
  }

  /** ��Դ�����к� setter ���� */
  public void setVsourcerowno(String vsourcerowno) {
    this.setAttributeValue(ArriveItemVO.VSOURCEROWNO, vsourcerowno);
  }

  /** ��Դ�������� setter ���� */
  public void setVsourcetrantype(String vsourcetrantype) {
    this.setAttributeValue(ArriveItemVO.VSOURCETRANTYPE, vsourcetrantype);
  }
}
