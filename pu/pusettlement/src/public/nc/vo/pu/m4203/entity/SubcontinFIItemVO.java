package nc.vo.pu.m4203.entity;

import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * ί�мӹ�������VO
 * 
 * @since 6.0
 * @version 2011-1-19 ����02:25:49
 * @author zhaoyha
 */
public class SubcontinFIItemVO extends SuperVO {
  /** Ӱ��ɱ���־ */
  public static final String BAFFECTCOST = "baffectcost";

  /** �Ƿ������� */
  public static final String BSETTLEFINISH = "bsettlefinish";

  /** �ͻ� */
  public static final String CASSCUSTID = "casscustid";

  /** ��λ */
  public static final String CASTUNITID = "castunitid";

  /** ��λ�� */
  public static final String CCURRENCYID = "ccurrencyid";

  /** ������ **/
  public static final String CFFILEID = "cffileid";

  /** Դͷ���ݱ��� */
  public static final String CFIRSTBID = "cfirstbid";

  /** Դͷ���ݱ�ͷ */
  public static final String CFIRSTID = "cfirstid";

  /** Դͷ�������� */
  public static final String CFIRSTTYPECODE = "cfirsttypecode";

  /** �������� */
  public static final String CPRODUCTORID = "cproductorid";

  /** ��Ŀ */
  public static final String CPROJECTID = "cprojectid";

  /** �����ȼ� */
  public static final String CQUALITYLEVELID = "cqualitylevelid";

  /** �к� */
  public static final String CROWNO = "crowno";

  /** ��Դ���ݱ��� */
  public static final String CSOURCEBID = "csourcebid";

  /** ��Դ���ݱ�ͷ */
  public static final String CSOURCEID = "csourceid";

  /** ��Դ�������� */
  public static final String CSOURCETYPECODE = "csourcetypecode";

  /** ���״̬ */
  public static final String CSTATEID = "cstateid";

  /** ����λ */
  public static final String CUNITID = "cunitid";

  /** �������� */
  public static final String CWORKCENTERID = "cworkcenterid";

  /** ҵ������ */
  public static final String DBIZDATE = "dbizdate";

  /** Դͷ�����Ƶ����� */
  public static final String DFIRSTBILLDATE = "dfirstbilldate";

  /** dr */
  public static final String DR = "dr";

  /** �ݹ����� */
  public static final String DTOCOSTAPDATE = "dtocostapdate";

  /** ���ɱ���־ */
  public static final String FTOIAFLAG = "ftoiaflag";
  
  /** ��Ʒ���� */
  public static final String FOUTPUTTYPE = "foutputtype";

  /** �ۼƻس��ݹ��ɱ���� */
  public static final String NACCESTCOSTWASHMNY = "naccestcostwashmny";

  /** �ۼƷ��ý����� */
  public static final String NACCFEESETTLEMNY = "naccfeesettlemny";

  /** �ۼƻ�������� */
  public static final String NACCGOODSSETTLEMNY = "naccgoodssettlemny";

  /** �ۼƽ����� */
  public static final String NACCSETTLEMNY = "naccsettlemny";

  /** �ۼƽ������� */
  public static final String NACCUMSETTLENUM = "naccumsettlenum";

  /** ��� */
  public static final String NCOSTMNY = "ncostmny";

  /** ���� */
  public static final String NCOSTPRICE = "ncostprice";

  /** ʵ�븨���� */
  public static final String NINASSISTNUM = "ninassistnum";

  /** ��������� */
  public static final String NINNUM = "ninnum";

  /** �ƻ���� */
  public static final String NPLANNEDMNY = "nplannedmny";

  /** �ƻ����� */
  public static final String NPLANNEDPRICE = "nplannedprice";

  /** Ӧ��������֯ */
  public static final String PK_APFINANCEORG = "pk_apfinanceorg";

  /** Ӧ��������֯�汾 */
  public static final String PK_APFINANCEORG_V = "pk_apfinanceorg_v";

  /** �������� */
  public static final String PK_APLIABCENTER = "pk_apliabcenter";

  /** �������İ汾 */
  public static final String PK_APLIABCENTER_V = "pk_apliabcenter_v";

  /** ���ε��� */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** ��˾���°汾 */
  public static final String PK_CORP = "pk_corp";

  /** ��˾ */
  public static final String PK_CORP_V = "pk_corp_v";

  /** �ɱ��� */
  public static final String PK_COSTREGION = "pk_costregion";

  /** ���������֯ */
  public static final String PK_FINANCEORG = "pk_financeorg";

  /** ���������֯�汾 */
  public static final String PK_FINANCEORG_V = "pk_financeorg_v";

  /** ���� */
  public static final String PK_GROUP = "pk_group";

  /** ��Ʊ��Ӧ�� */
  public static final String PK_INVCSUPLLIER = "pk_invcsupllier";

  /** ���ϱ��� */
  public static final String PK_MATERIAL = "pk_material";

  /** ���� */
  public static final String PK_ORDER = "pk_order";

  /** ������ϸ */
  public static final String PK_ORDER_B = "pk_order_b";

  /** �����֯ */
  public static final String PK_ORG = "pk_org";

  /** �����֯�汾 */
  public static final String PK_ORG_V = "pk_org_v";

  /** �ɹ���֯ */
  public static final String PK_PURCHASEORG = "pk_purchaseorg";

  /** �ɹ���֯�汾 */
  public static final String PK_PURCHASEORG_V = "pk_purchaseorg_v";

  /** ���� */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** ί�мӹ��������ϸ */
  public static final String PK_STOCKPS = "pk_stockps";

  /** ί�мӹ�����ϸ���� */
  public static final String PK_STOCKPS_B = "pk_stockps_b";

  /** ��Ӧ�� */
  public static final String PK_SUPPLIER = "pk_supplier";

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

  /** ������ */
  public static final String VCHANGERATE = "vchangerate";

  /** �ɹ���ͬ�� */
  public static final String VCTCODE = "vctcode";

  /** Դͷ���ݺ� */
  public static final String VFIRSTCODE = "vfirstcode";

  /** Դͷ�����к� */
  public static final String VFIRSTROWNO = "vfirstrowno";

  /** Դͷ���ݽ������� */
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

  /** �б�ע */
  public static final String VNOTEBODY = "vnotebody";

  /** ������ */
  public static final String VORDERCODE = "vordercode";

  /** ������������ */
  public static final String VORDERTRANTYPECODE = "vordertrantypecode";

  /** �������� */
  public static final String VPRODUCTBATCH = "vproductbatch";

  /** ���������� */
  public static final String VPRODUCTBILLCODE = "vproductbillcode";

  /** ��Դ���ݺ� */
  public static final String VSOURCECODE = "vsourcecode";

  /** ��Դ�����к� */
  public static final String VSOURCEROWNO = "vsourcerowno";

  /** ��Դ�������� */
  public static final String VSOURCETRANTYPE = "vsourcetrantype";

  private static final long serialVersionUID = -2235857194115893338L;

  /** Ӱ��ɱ���־ getter ���� */
  public UFBoolean getBaffectcost() {
    return (UFBoolean) this.getAttributeValue(SubcontinFIItemVO.BAFFECTCOST);
  }

  /** �Ƿ������� getter ���� */
  public UFBoolean getBsettlefinish() {
    return (UFBoolean) this.getAttributeValue(SubcontinFIItemVO.BSETTLEFINISH);
  }

  /** �ͻ� getter ���� */
  public String getCasscustid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CASSCUSTID);
  }

  /** ��λ getter ���� */
  public String getCastunitid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CASTUNITID);
  }

  /** ��λ�� getter ���� */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CCURRENCYID);
  }

  /** ������ **/
  public String getCffileid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CFFILEID);
  }

  /** Դͷ���ݱ��� getter ���� */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CFIRSTBID);
  }

  /** Դͷ���ݱ�ͷ getter ���� */
  public String getCfirstid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CFIRSTID);
  }

  /** Դͷ�������� getter ���� */
  public String getCfirsttypecode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CFIRSTTYPECODE);
  }

  /** �������� getter ���� */
  public String getCproductorid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CPRODUCTORID);
  }

  /** ��Ŀ getter ���� */
  public String getCprojectid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CPROJECTID);
  }

  /** �����ȼ� getter ���� */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CQUALITYLEVELID);
  }

  /** �к� getter ���� */
  public String getCrowno() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CROWNO);
  }

  /** ��Դ���ݱ��� getter ���� */
  public String getCsourcebid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CSOURCEBID);
  }

  /** ��Դ���ݱ�ͷ getter ���� */
  public String getCsourceid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CSOURCEID);
  }

  /** ��Դ�������� getter ���� */
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CSOURCETYPECODE);
  }

  /** ���״̬ getter ���� */
  public String getCstateid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CSTATEID);
  }

  /** ����λ getter ���� */
  public String getCunitid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CUNITID);
  }

  /** �������� getter ���� */
  public String getCworkcenterid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CWORKCENTERID);
  }

  /** ҵ������ getter ���� */
  public UFDate getDbizdate() {
    return (UFDate) this.getAttributeValue(SubcontinFIItemVO.DBIZDATE);
  }

  /** Դͷ�����Ƶ����� getter ���� */
  public String getDfirstbilldate() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.DFIRSTBILLDATE);
  }

  /** dr getter ���� */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(SubcontinFIItemVO.DR);
  }

  /** �ݹ����� getter ���� */
  public UFDateTime getDtocostapdate() {
    return (UFDateTime) this.getAttributeValue(SubcontinFIItemVO.DTOCOSTAPDATE);
  }

  /** ���ɱ���־ getter ���� */
  public Integer getFtoiaflag() {
    return (Integer) this.getAttributeValue(SubcontinFIItemVO.FTOIAFLAG);
  }
  
  /** ��Ʒ���� getter ���� */
  public Integer getFoutputtype() {
    return (Integer) this.getAttributeValue(SubcontinFIItemVO.FOUTPUTTYPE);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.SUBCONTIN_B);
    return meta;
  }

  /** �ۼƻس��ݹ��ɱ���� getter ���� */
  public UFDouble getNaccestcostwashmny() {
    return (UFDouble) this
        .getAttributeValue(SubcontinFIItemVO.NACCESTCOSTWASHMNY);
  }

  /** �ۼƷ��ý����� getter ���� */
  public UFDouble getNaccfeesettlemny() {
    return (UFDouble) this
        .getAttributeValue(SubcontinFIItemVO.NACCFEESETTLEMNY);
  }

  /** �ۼƻ�������� getter ���� */
  public UFDouble getNaccgoodssettlemny() {
    return (UFDouble) this
        .getAttributeValue(SubcontinFIItemVO.NACCGOODSSETTLEMNY);
  }

  /** �ۼƽ����� getter ���� */
  public UFDouble getNaccsettlemny() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NACCSETTLEMNY);
  }

  /** �ۼƽ������� getter ���� */
  public UFDouble getNaccumsettlenum() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NACCUMSETTLENUM);
  }

  /** ��� getter ���� */
  public UFDouble getNcostmny() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NCOSTMNY);
  }

  /** ���� getter ���� */
  public UFDouble getNcostprice() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NCOSTPRICE);
  }

  /** ʵ�븨���� getter ���� */
  public UFDouble getNinassistnum() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NINASSISTNUM);
  }

  /** ��������� getter ���� */
  public UFDouble getNinnum() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NINNUM);
  }

  /** �ƻ���� getter ���� */
  public UFDouble getNplannedmny() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NPLANNEDMNY);
  }

  /** �ƻ����� getter ���� */
  public UFDouble getNplannedprice() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NPLANNEDPRICE);
  }

  /** Ӧ��������֯ getter ���� */
  public String getPk_apfinanceorg() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_APFINANCEORG);
  }

  /** Ӧ��������֯�汾 getter ���� */
  public String getPk_apfinanceorg_v() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_APFINANCEORG_V);
  }

  /** �������� getter ���� */
  public String getPk_apliabcenter() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_APLIABCENTER);
  }

  /** �������İ汾 getter ���� */
  public String getPk_apliabcenter_v() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_APLIABCENTER_V);
  }

  /** ���ε��� getter ���� */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_BATCHCODE);
  }

  /** ��˾���°汾 getter ���� */
  public String getPk_corp() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_CORP);
  }

  /** ��˾ getter ���� */
  public String getPk_corp_v() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_CORP_V);
  }

  /** �ɱ��� getter ���� */
  public String getPk_costregion() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_COSTREGION);
  }

  /** ���������֯ getter ���� */
  public String getPk_financeorg() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_FINANCEORG);
  }

  /** ���������֯�汾 getter ���� */
  public String getPk_financeorg_v() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_FINANCEORG_V);
  }

  /** ���� getter ���� */
  public String getPk_group() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_GROUP);
  }

  /** ��Ʊ��Ӧ�� getter ���� */
  public String getPk_invcsupllier() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_INVCSUPLLIER);
  }

  /** ���ϱ��� getter ���� */
  public String getPk_material() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_MATERIAL);
  }

  /** ���� getter ���� */
  public String getPk_order() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_ORDER);
  }

  /** ������ϸ getter ���� */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_ORDER_B);
  }

  /** �����֯ getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_ORG);
  }

  /** �����֯�汾 getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_ORG_V);
  }

  /** �ɹ���֯ getter ���� */
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_PURCHASEORG);
  }

  /** �ɹ���֯�汾 getter ���� */
  public String getPk_purchaseorg_v() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_PURCHASEORG_V);
  }

  /** ���� getter ���� */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_SRCMATERIAL);
  }

  /** ί�мӹ��������ϸ getter ���� */
  public String getPk_stockps() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_STOCKPS);
  }

  /** ί�мӹ�����ϸ���� getter ���� */
  public String getPk_stockps_b() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_STOCKPS_B);
  }

  /** ��Ӧ�� getter ���� */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_SUPPLIER);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SubcontinFIItemVO.TS);
  }

  /** ���κ� getter ���� */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBATCHCODE);
  }

  /** �Զ�����1 getter ���� */
  public String getVbdef1() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF1);
  }

  /** �Զ�����10 getter ���� */
  public String getVbdef10() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF10);
  }

  /** �Զ�����11 getter ���� */
  public String getVbdef11() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF11);
  }

  /** �Զ�����12 getter ���� */
  public String getVbdef12() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF12);
  }

  /** �Զ�����13 getter ���� */
  public String getVbdef13() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF13);
  }

  /** �Զ�����14 getter ���� */
  public String getVbdef14() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF14);
  }

  /** �Զ�����15 getter ���� */
  public String getVbdef15() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF15);
  }

  /** �Զ�����16 getter ���� */
  public String getVbdef16() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF16);
  }

  /** �Զ�����17 getter ���� */
  public String getVbdef17() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF17);
  }

  /** �Զ�����18 getter ���� */
  public String getVbdef18() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF18);
  }

  /** �Զ�����19 getter ���� */
  public String getVbdef19() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF19);
  }

  /** �Զ�����2 getter ���� */
  public String getVbdef2() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF2);
  }

  /** �Զ�����20 getter ���� */
  public String getVbdef20() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF20);
  }

  /** �Զ�����3 getter ���� */
  public String getVbdef3() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF3);
  }

  /** �Զ�����4 getter ���� */
  public String getVbdef4() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF4);
  }

  /** �Զ�����5 getter ���� */
  public String getVbdef5() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF5);
  }

  /** �Զ�����6 getter ���� */
  public String getVbdef6() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF6);
  }

  /** �Զ�����7 getter ���� */
  public String getVbdef7() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF7);
  }

  /** �Զ�����8 getter ���� */
  public String getVbdef8() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF8);
  }

  /** �Զ�����9 getter ���� */
  public String getVbdef9() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBDEF9);
  }

  /** ������ getter ���� */
  public String getVchangerate() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VCHANGERATE);
  }

  /** �ɹ���ͬ�� getter ���� */
  public String getVctcode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VCTCODE);
  }

  /** Դͷ���ݺ� getter ���� */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFIRSTCODE);
  }

  /** Դͷ�����к� getter ���� */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFIRSTROWNO);
  }

  /** Դͷ���ݽ������� getter ���� */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFIRSTTRANTYPE);
  }

  /** ���ɸ�������1 getter ���� */
  public String getVfree1() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE1);
  }

  /** ���ɸ�������10 getter ���� */
  public String getVfree10() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE10);
  }

  /** ���ɸ�������2 getter ���� */
  public String getVfree2() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE2);
  }

  /** ���ɸ�������3 getter ���� */
  public String getVfree3() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE3);
  }

  /** ���ɸ�������4 getter ���� */
  public String getVfree4() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE4);
  }

  /** ���ɸ�������5 getter ���� */
  public String getVfree5() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE5);
  }

  /** ���ɸ�������6 getter ���� */
  public String getVfree6() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE6);
  }

  /** ���ɸ�������7 getter ���� */
  public String getVfree7() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE7);
  }

  /** ���ɸ�������8 getter ���� */
  public String getVfree8() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE8);
  }

  /** ���ɸ�������9 getter ���� */
  public String getVfree9() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE9);
  }

  /** �б�ע getter ���� */
  public String getVnotebody() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VNOTEBODY);
  }

  /** ������ getter ���� */
  public String getVordercode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VORDERCODE);
  }

  /** ������������ getter ���� */
  public String getVordertrantypecode() {
    return (String) this
        .getAttributeValue(SubcontinFIItemVO.VORDERTRANTYPECODE);
  }

  /** �������� getter ���� */
  public String getVproductbatch() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VPRODUCTBATCH);
  }

  /** ���������� getter ���� */
  public String getVproductbillcode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VPRODUCTBILLCODE);
  }

  /** ��Դ���ݺ� getter ���� */
  public String getVsourcecode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VSOURCECODE);
  }

  /** ��Դ�����к� getter ���� */
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VSOURCEROWNO);
  }

  /** ��Դ�������� getter ���� */
  public String getVsourcetrantype() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VSOURCETRANTYPE);
  }

  /** Ӱ��ɱ���־ setter ���� */
  public void setBaffectcost(UFBoolean baffectcost) {
    this.setAttributeValue(SubcontinFIItemVO.BAFFECTCOST, baffectcost);
  }

  /** �Ƿ������� setter ���� */
  public void setBsettlefinish(UFBoolean bsettlefinish) {
    this.setAttributeValue(SubcontinFIItemVO.BSETTLEFINISH, bsettlefinish);
  }

  /** �ͻ� setter ���� */
  public void setCasscustid(String casscustid) {
    this.setAttributeValue(SubcontinFIItemVO.CASSCUSTID, casscustid);
  }

  /** ��λ setter ���� */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SubcontinFIItemVO.CASTUNITID, castunitid);
  }

  /** ��λ�� setter ���� */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SubcontinFIItemVO.CCURRENCYID, ccurrencyid);
  }

  /** ������ **/
  public void setCffileid(String cffileid) {
    this.setAttributeValue(VmiFIHeaderVO.CFFILEID, cffileid);
  }

  /** Դͷ���ݱ��� setter ���� */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(SubcontinFIItemVO.CFIRSTBID, cfirstbid);
  }

  /** Դͷ���ݱ�ͷ setter ���� */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(SubcontinFIItemVO.CFIRSTID, cfirstid);
  }

  /** Դͷ�������� setter ���� */
  public void setCfirsttypecode(String cfirsttypecode) {
    this.setAttributeValue(SubcontinFIItemVO.CFIRSTTYPECODE, cfirsttypecode);
  }

  /** �������� setter ���� */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(SubcontinFIItemVO.CPRODUCTORID, cproductorid);
  }

  /** ��Ŀ setter ���� */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(SubcontinFIItemVO.CPROJECTID, cprojectid);
  }

  /** �����ȼ� setter ���� */
  public void setCqualitylevelid(String cqualitylevelid) {
    this.setAttributeValue(SubcontinFIItemVO.CQUALITYLEVELID, cqualitylevelid);
  }

  /** �к� setter ���� */
  public void setCrowno(String crowno) {
    this.setAttributeValue(SubcontinFIItemVO.CROWNO, crowno);
  }

  /** ��Դ���ݱ��� setter ���� */
  public void setCsourcebid(String csourcebid) {
    this.setAttributeValue(SubcontinFIItemVO.CSOURCEBID, csourcebid);
  }

  /** ��Դ���ݱ�ͷ setter ���� */
  public void setCsourceid(String csourceid) {
    this.setAttributeValue(SubcontinFIItemVO.CSOURCEID, csourceid);
  }

  /** ��Դ�������� setter ���� */
  public void setCsourcetypecode(String csourcetypecode) {
    this.setAttributeValue(SubcontinFIItemVO.CSOURCETYPECODE, csourcetypecode);
  }

  /** ���״̬ setter ���� */
  public void setCstateid(String cstateid) {
    this.setAttributeValue(SubcontinFIItemVO.CSTATEID, cstateid);
  }

  /** ����λ setter ���� */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(SubcontinFIItemVO.CUNITID, cunitid);
  }

  /** �������� setter ���� */
  public void setCworkcenterid(String cworkcenterid) {
    this.setAttributeValue(SubcontinFIItemVO.CWORKCENTERID, cworkcenterid);
  }

  /** ҵ������ setter ���� */
  public void setDbizdate(UFDate dbizdate) {
    this.setAttributeValue(SubcontinFIItemVO.DBIZDATE, dbizdate);
  }

  /** Դͷ�����Ƶ����� setter ���� */
  public void setDfirstbilldate(String dfirstbilldate) {
    this.setAttributeValue(SubcontinFIItemVO.DFIRSTBILLDATE, dfirstbilldate);
  }

  /** dr setter ���� */
  public void setDr(Integer dr) {
    this.setAttributeValue(SubcontinFIItemVO.DR, dr);
  }

  /** �ݹ����� setter ���� */
  public void setDtocostapdate(UFDateTime dtocostapdate) {
    this.setAttributeValue(SubcontinFIItemVO.DTOCOSTAPDATE, dtocostapdate);
  }

  /** ���ɱ���־ setter ���� */
  public void setFtoiaflag(Integer ftoiaflag) {
    this.setAttributeValue(SubcontinFIItemVO.FTOIAFLAG, ftoiaflag);
  }
  
  /** ��Ʒ���� setter ���� */
  public void setFoutputtype(Integer foutputtype) {
    this.setAttributeValue(SubcontinFIItemVO.FOUTPUTTYPE, foutputtype);
  }

  /** �ۼƻس��ݹ��ɱ���� setter ���� */
  public void setNaccestcostwashmny(UFDouble naccestcostwashmny) {
    this.setAttributeValue(SubcontinFIItemVO.NACCESTCOSTWASHMNY,
        naccestcostwashmny);
  }

  /** �ۼƷ��ý����� setter ���� */
  public void setNaccfeesettlemny(UFDouble naccfeesettlemny) {
    this.setAttributeValue(SubcontinFIItemVO.NACCFEESETTLEMNY, naccfeesettlemny);
  }

  /** �ۼƻ�������� setter ���� */
  public void setNaccgoodssettlemny(UFDouble naccgoodssettlemny) {
    this.setAttributeValue(SubcontinFIItemVO.NACCGOODSSETTLEMNY,
        naccgoodssettlemny);
  }

  /** �ۼƽ����� setter ���� */
  public void setNaccsettlemny(UFDouble naccsettlemny) {
    this.setAttributeValue(SubcontinFIItemVO.NACCSETTLEMNY, naccsettlemny);
  }

  /** �ۼƽ������� setter ���� */
  public void setNaccumsettlenum(UFDouble naccumsettlenum) {
    this.setAttributeValue(SubcontinFIItemVO.NACCUMSETTLENUM, naccumsettlenum);
  }

  /** ��� setter ���� */
  public void setNcostmny(UFDouble ncostmny) {
    this.setAttributeValue(SubcontinFIItemVO.NCOSTMNY, ncostmny);
  }

  /** ���� setter ���� */
  public void setNcostprice(UFDouble ncostprice) {
    this.setAttributeValue(SubcontinFIItemVO.NCOSTPRICE, ncostprice);
  }

  /** ʵ�븨���� setter ���� */
  public void setNinassistnum(UFDouble ninassistnum) {
    this.setAttributeValue(SubcontinFIItemVO.NINASSISTNUM, ninassistnum);
  }

  /** ��������� setter ���� */
  public void setNinnum(UFDouble ninnum) {
    this.setAttributeValue(SubcontinFIItemVO.NINNUM, ninnum);
  }

  /** �ƻ���� setter ���� */
  public void setNplannedmny(UFDouble nplannedmny) {
    this.setAttributeValue(SubcontinFIItemVO.NPLANNEDMNY, nplannedmny);
  }

  /** �ƻ����� setter ���� */
  public void setNplannedprice(UFDouble nplannedprice) {
    this.setAttributeValue(SubcontinFIItemVO.NPLANNEDPRICE, nplannedprice);
  }

  /** Ӧ��������֯ setter ���� */
  public void setPk_apfinanceorg(String pk_apfinanceorg) {
    this.setAttributeValue(SubcontinFIItemVO.PK_APFINANCEORG, pk_apfinanceorg);
  }

  /** Ӧ��������֯�汾 setter ���� */
  public void setPk_apfinanceorg_v(String pk_apfinanceorg_v) {
    this.setAttributeValue(SubcontinFIItemVO.PK_APFINANCEORG_V,
        pk_apfinanceorg_v);
  }

  /** �������� setter ���� */
  public void setPk_apliabcenter(String pk_apliabcenter) {
    this.setAttributeValue(SubcontinFIItemVO.PK_APLIABCENTER, pk_apliabcenter);
  }

  /** �������İ汾 setter ���� */
  public void setPk_apliabcenter_v(String pk_apliabcenter_v) {
    this.setAttributeValue(SubcontinFIItemVO.PK_APLIABCENTER_V,
        pk_apliabcenter_v);
  }

  /** ���ε��� setter ���� */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(SubcontinFIItemVO.PK_BATCHCODE, pk_batchcode);
  }

  /** ��˾���°汾 setter ���� */
  public void setPk_corp(String pk_corp) {
    this.setAttributeValue(SubcontinFIItemVO.PK_CORP, pk_corp);
  }

  /** ��˾ setter ���� */
  public void setPk_corp_v(String pk_corp_v) {
    this.setAttributeValue(SubcontinFIItemVO.PK_CORP_V, pk_corp_v);
  }

  /** �ɱ��� setter ���� */
  public void setPk_costregion(String pk_costregion) {
    this.setAttributeValue(SubcontinFIItemVO.PK_COSTREGION, pk_costregion);
  }

  /** ���������֯ setter ���� */
  public void setPk_financeorg(String pk_financeorg) {
    this.setAttributeValue(SubcontinFIItemVO.PK_FINANCEORG, pk_financeorg);
  }

  /** ���������֯�汾 setter ���� */
  public void setPk_financeorg_v(String pk_financeorg_v) {
    this.setAttributeValue(SubcontinFIItemVO.PK_FINANCEORG_V, pk_financeorg_v);
  }

  /** ���� setter ���� */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SubcontinFIItemVO.PK_GROUP, pk_group);
  }

  /** ��Ʊ��Ӧ�� setter ���� */
  public void setPk_invcsupllier(String pk_invcsupllier) {
    this.setAttributeValue(SubcontinFIItemVO.PK_INVCSUPLLIER, pk_invcsupllier);
  }

  /** ���ϱ��� setter ���� */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(SubcontinFIItemVO.PK_MATERIAL, pk_material);
  }

  /** ���� setter ���� */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(SubcontinFIItemVO.PK_ORDER, pk_order);
  }

  /** ������ϸ setter ���� */
  public void setPk_order_b(String pk_order_b) {
    this.setAttributeValue(SubcontinFIItemVO.PK_ORDER_B, pk_order_b);
  }

  /** �����֯ setter ���� */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SubcontinFIItemVO.PK_ORG, pk_org);
  }

  /** �����֯�汾 setter ���� */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(SubcontinFIItemVO.PK_ORG_V, pk_org_v);
  }

  /** �ɹ���֯ setter ���� */
  public void setPk_purchaseorg(String pk_purchaseorg) {
    this.setAttributeValue(SubcontinFIItemVO.PK_PURCHASEORG, pk_purchaseorg);
  }

  /** �ɹ���֯�汾 setter ���� */
  public void setPk_purchaseorg_v(String pk_purchaseorg_v) {
    this.setAttributeValue(SubcontinFIItemVO.PK_PURCHASEORG_V, pk_purchaseorg_v);
  }

  /** ���� setter ���� */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(SubcontinFIItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** ί�мӹ��������ϸ setter ���� */
  public void setPk_stockps(String pk_stockps) {
    this.setAttributeValue(SubcontinFIItemVO.PK_STOCKPS, pk_stockps);
  }

  /** ί�мӹ�����ϸ���� setter ���� */
  public void setPk_stockps_b(String pk_stockps_b) {
    this.setAttributeValue(SubcontinFIItemVO.PK_STOCKPS_B, pk_stockps_b);
  }

  /** ��Ӧ�� setter ���� */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(SubcontinFIItemVO.PK_SUPPLIER, pk_supplier);
  }

  /** ts setter ���� */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SubcontinFIItemVO.TS, ts);
  }

  /** ���κ� setter ���� */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(SubcontinFIItemVO.VBATCHCODE, vbatchcode);
  }

  /** �Զ�����1 setter ���� */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF1, vbdef1);
  }

  /** �Զ�����10 setter ���� */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF10, vbdef10);
  }

  /** �Զ�����11 setter ���� */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF11, vbdef11);
  }

  /** �Զ�����12 setter ���� */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF12, vbdef12);
  }

  /** �Զ�����13 setter ���� */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF13, vbdef13);
  }

  /** �Զ�����14 setter ���� */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF14, vbdef14);
  }

  /** �Զ�����15 setter ���� */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF15, vbdef15);
  }

  /** �Զ�����16 setter ���� */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF16, vbdef16);
  }

  /** �Զ�����17 setter ���� */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF17, vbdef17);
  }

  /** �Զ�����18 setter ���� */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF18, vbdef18);
  }

  /** �Զ�����19 setter ���� */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF19, vbdef19);
  }

  /** �Զ�����2 setter ���� */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF2, vbdef2);
  }

  /** �Զ�����20 setter ���� */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF20, vbdef20);
  }

  /** �Զ�����3 setter ���� */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF3, vbdef3);
  }

  /** �Զ�����4 setter ���� */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF4, vbdef4);
  }

  /** �Զ�����5 setter ���� */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF5, vbdef5);
  }

  /** �Զ�����6 setter ���� */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF6, vbdef6);
  }

  /** �Զ�����7 setter ���� */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF7, vbdef7);
  }

  /** �Զ�����8 setter ���� */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF8, vbdef8);
  }

  /** �Զ�����9 setter ���� */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(SubcontinFIItemVO.VBDEF9, vbdef9);
  }

  /** ������ setter ���� */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(SubcontinFIItemVO.VCHANGERATE, vchangerate);
  }

  /** �ɹ���ͬ�� setter ���� */
  public void setVctcode(String vctcode) {
    this.setAttributeValue(SubcontinFIItemVO.VCTCODE, vctcode);
  }

  /** Դͷ���ݺ� setter ���� */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(SubcontinFIItemVO.VFIRSTCODE, vfirstcode);
  }

  /** Դͷ�����к� setter ���� */
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(SubcontinFIItemVO.VFIRSTROWNO, vfirstrowno);
  }

  /** Դͷ���ݽ������� setter ���� */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(SubcontinFIItemVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /** ���ɸ�������1 setter ���� */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE1, vfree1);
  }

  /** ���ɸ�������10 setter ���� */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE10, vfree10);
  }

  /** ���ɸ�������2 setter ���� */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE2, vfree2);
  }

  /** ���ɸ�������3 setter ���� */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE3, vfree3);
  }

  /** ���ɸ�������4 setter ���� */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE4, vfree4);
  }

  /** ���ɸ�������5 setter ���� */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE5, vfree5);
  }

  /** ���ɸ�������6 setter ���� */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE6, vfree6);
  }

  /** ���ɸ�������7 setter ���� */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE7, vfree7);
  }

  /** ���ɸ�������8 setter ���� */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE8, vfree8);
  }

  /** ���ɸ�������9 setter ���� */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(SubcontinFIItemVO.VFREE9, vfree9);
  }

  /** �б�ע setter ���� */
  public void setVnotebody(String vnotebody) {
    this.setAttributeValue(SubcontinFIItemVO.VNOTEBODY, vnotebody);
  }

  /** ������ setter ���� */
  public void setVordercode(String vordercode) {
    this.setAttributeValue(SubcontinFIItemVO.VORDERCODE, vordercode);
  }

  /** ������������ setter ���� */
  public void setVordertrantypecode(String vordertrantypecode) {
    this.setAttributeValue(SubcontinFIItemVO.VORDERTRANTYPECODE,
        vordertrantypecode);
  }

  /** �������� setter ���� */
  public void setVproductbatch(String vproductbatch) {
    this.setAttributeValue(SubcontinFIItemVO.VPRODUCTBATCH, vproductbatch);
  }

  /** ���������� setter ���� */
  public void setVproductbillcode(String vproductbillcode) {
    this.setAttributeValue(SubcontinFIItemVO.VPRODUCTBILLCODE, vproductbillcode);
  }

  /** ��Դ���ݺ� setter ���� */
  public void setVsourcecode(String vsourcecode) {
    this.setAttributeValue(SubcontinFIItemVO.VSOURCECODE, vsourcecode);
  }

  /** ��Դ�����к� setter ���� */
  public void setVsourcerowno(String vsourcerowno) {
    this.setAttributeValue(SubcontinFIItemVO.VSOURCEROWNO, vsourcerowno);
  }

  /** ��Դ�������� setter ���� */
  public void setVsourcetrantype(String vsourcetrantype) {
    this.setAttributeValue(SubcontinFIItemVO.VSOURCETRANTYPE, vsourcetrantype);
  }

}
