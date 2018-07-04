/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-6 ����10:37:32
 */
package nc.vo.pu.m4202.entity;

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
 * <li>���Ļ��ܲ����ͷ(����)VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-6 ����10:37:32
 */
public class VmiFIHeaderVO extends SuperVO {

  /** ������ */
  public static final String APPROVER = "approver";

  /** Ӱ��ɱ���־ */
  public static final String BAFFECTCOST = "baffectcost";

  /** �Ƶ��� */
  public static final String BILLMAKER = "billmaker";

  /** �Ƿ���ͨ�ɹ� */
  public static final String BNORMPUR = "bnormpur";

  /** ������˰ */
  public static final String BOPPTAXFLAG = "bopptaxflag";

  /** �Ƿ������� */
  public static final String BSETTLEFINISH = "bsettlefinish";

  /** ����ó�� */
  public static final String BTRIATRADEFLAG = "btriatradeflag";

  /** �ͻ� */
  public static final String CASSCUSTID = "casscustid";

  /** ��λ */
  public static final String CASTUNITID = "castunitid";

  /** ���ĵ��ݱ�ͷ */
  public static final String CCONSUMEHID = "cconsumehid";

  /** ��λ�� */
  public static final String CCURRENCYID = "ccurrencyid";

  /** ˰�� */
  public static final String CESTTAXCODEID = "cesttaxcodeid";

  /** ������ **/
  public static final String CFFILEID = "cffileid";

  /** �������� */
  public static final String CPRODUCTORID = "cproductorid";

  /** ��Ŀ */
  public static final String CPROJECTID = "cprojectid";

  /** ����ʱ�� */
  public static final String CREATIONTIME = "creationtime";

  /** ������ */
  public static final String CREATOR = "creator";

  /** �ջ�����/���� */
  public static final String CRECECOUNTRYID = "crececountryid";

  /** �к� */
  public static final String CROWNO = "crowno";

  /** ��������/���� */
  public static final String CSENDCOUNTRYID = "csendcountryid";

  /** ���״̬ */
  public static final String CSTATEID = "cstateid";

  /** ���Ļ��ܹ���ID */
  public static final String CSUMRULEID = "csumruleid";

  /** ��˰����/���� */
  public static final String CTAXCOUNTRYID = "ctaxcountryid";

  /** �������� */
  public static final String CTRANTYPEID = "ctrantypeid";

  /** ����λ */
  public static final String CUNITID = "cunitid";

  /** �������� */
  public static final String DBILLDATE = "dbilldate";

  /** ҵ������ */
  public static final String DBIZDATE = "dbizdate";

  /** dr */
  public static final String DR = "dr";

  /** �ݹ����� */
  public static final String DTOCOSTAPDATE = "dtocostapdate";

  /** �������� */
  public static final String FBUYSELLFLAG = "fbuysellflag";

  /** ��˰��� */
  public static final String FESTTAXTYPE = "festtaxtype";

  /** ��Ӧ����־ */
  public static final String FTOAPFLAG = "ftoapflag";

  /** ���ɱ���־ */
  public static final String FTOIAFLAG = "ftoiaflag";

  /** ����޸�ʱ�� */
  public static final String MODIFIEDTIME = "modifiedtime";

  /** ����޸��� */
  public static final String MODIFIER = "modifier";

  /** �ݹ������ۼƽ������� */
  public static final String NACCESTCOSTSTTLNUM = "naccestcoststtlnum";

  /** �ۼƻس��ݹ��ɱ���� */
  public static final String NACCESTCOSTWASHMNY = "naccestcostwashmny";

  /** �ۼƷ��ý����� */
  public static final String NACCFEESETTLEMNY = "naccfeesettlemny";

  /** �ۼƻ�������� */
  public static final String NACCGOODSSETTLEMNY = "naccgoodssettlemny";

  /** �ݹ�ǰ�ۼƽ����� */
  public static final String NACCPREESTSTTLMNY = "naccpreeststtlmny";

  /** �ۼƽ����� */
  public static final String NACCSETTLEMNY = "naccsettlemny";

  /** �ۼƵ���ȷ��Ӧ��ԭ�Ҽ�˰�ϼ� */
  public static final String NACCTOAPADJSTOTMNY = "nacctoapadjstotmny";

  /** �ۼƵ���ȷ�ϳɱ���� */
  public static final String NACCTOCOSTADJSTMNY = "nacctocostadjstmny";

  /** �ۼƽ������� */
  public static final String NACCUMSETTLENUM = "naccumsettlenum";

  /** �Ƴɱ���� */
  public static final String NESTCALCOSTMNY = "nestcalcostmny";

  /** �ǳɱ����� **/
  public static final String NESTCALCOSTPRICE = "nestcalcostprice";

  /** ��˰��� */
  public static final String NESTCALTAXMNY = "nestcaltaxmny";

  /** �۱����� */
  public static final String NESTEXHGRATE = "nestexhgrate";

  /** ������˰��� */
  public static final String NESTMNY = "nestmny";

  /** ���ɵֿ�˰�� */
  public static final String NESTNOSUBTAX = "nestnosubtax";

  /** ���ɵֿ�˰�� */
  public static final String NESTNOSUBTAXRATE = "nestnosubtaxrate";

  /** �ݹ������� */
  public static final String NESTNUM = "nestnum";

  /** ��˰��� */
  public static final String NESTOMNY = "nestomny";

  /** ����˰���� */
  public static final String NESTOPRICE = "nestoprice";

  /** ����˰���� */
  public static final String NESTOTAXPRICE = "nestotaxprice";

  /** ��˰�ϼ� */
  public static final String NESTOTOTALMNY = "nestototalmny";

  /** �ݹ����� */
  public static final String NESTPRICE = "nestprice";

  /** ����˰�� */
  public static final String NESTTAXMNY = "nesttaxmny";

  /** �ݹ���˰���� */
  public static final String NESTTAXPRICE = "nesttaxprice";

  /** ˰�� */
  public static final String NESTTAXRATE = "nesttaxrate";

  /** ���Ҽ�˰�ϼ� */
  public static final String NESTTOTALMNY = "nesttotalmny";

  /** ���ñ�����˰��� */
  public static final String NFEEMNY = "nfeemny";

  /** ���ñ��Ҽ�˰�ϼ� */
  public static final String NFEETAXMNY = "nfeetaxmny";

  /** �������� */
  public static final String NINASSISTNUM = "ninassistnum";

  /** ���������� */
  public static final String NINNUM = "ninnum";

  /** ��������˰���� */
  public static final String NNETPRICE = "nnetprice";

  /** �����Һ�˰���� */
  public static final String NTAXNETPRICE = "ntaxnetprice";

  /** �ܱ�����˰��� */
  public static final String NTOTALMNY = "ntotalmny";

  /** �ܱ��Ҽ�˰�ϼ� */
  public static final String NTOTALTAXMNY = "ntotaltaxmny";

  /** ���ε��� */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** ҵ������ */
  public static final String PK_BUSITYPE = "pk_busitype";

  /** ���ɱ���Ӧ���� */
  public static final String PK_COSTAPPSN = "pk_costappsn";

  /** �ɱ��� */
  public static final String PK_COSTREGION = "pk_costregion";

  /** ���� */
  public static final String PK_ESTCURRENCY = "pk_estcurrency";

  /** ���������֯���°汾 */
  public static final String PK_FINANCEORG = "pk_financeorg";

  /** ���������֯ */
  public static final String PK_FINANCEORG_V = "pk_financeorg_v";

  /** ���� */
  public static final String PK_GROUP = "pk_group";

  /** ���ϱ��� */
  public static final String PK_MATERIAL = "pk_material";

  /** ��˾���°汾 */
  public static final String PK_ORG = "pk_org";

  /** ��˾ */
  public static final String PK_ORG_V = "pk_org_v";

  /** �������°汾 */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** ���Ļ��ܲ��񸨱�ʶ */
  public static final String PK_STOCKPS = "pk_stockps";

  /** ���Ļ��ܲ�������ʶ */
  public static final String PK_STOCKPS_B = "pk_stockps_b";

  /** �ֿ� */
  public static final String PK_STORDOC = "pk_stordoc";

  /** �����֯���°汾 */
  public static final String PK_STOREORG = "pk_storeorg";

  /** �����֯ */
  public static final String PK_STOREORG_V = "pk_storeorg_v";

  /** ��Ӧ�� */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** ����ʱ�� */
  public static final String TAUDITTIME = "taudittime";

  /** ts */
  public static final String TS = "ts";

  /** ���κ� */
  public static final String VBATCHCODE = "vbatchcode";

  /** ���ݺ� */
  public static final String VBILLCODE = "vbillcode";

  /** ������ */
  public static final String VCHANGERATE = "vchangerate";

  /** ���ĵ��ݺ� */
  public static final String VCONSUMEBILLCODE = "vconsumebillcode";

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

  /** ����ƥ��ɹ����Ĺ��� */
  public static final String VMIMATCHPURINRULE = "vmimatchpurinrule";

  /** �������� */
  public static final String VTRANTYPECODE = "vtrantypecode";

  /**
   * 
   */
  private static final long serialVersionUID = 6646250056893765086L;

  /** ������ getter ���� */
  public String getApprover() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.APPROVER);
  }

  /** Ӱ��ɱ���־ getter ���� */
  public UFBoolean getBaffectcost() {
    return (UFBoolean) this.getAttributeValue(VmiFIHeaderVO.BAFFECTCOST);
  }

  /** �Ƶ��� getter ���� */
  public String getBillmaker() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.BILLMAKER);
  }

  /** �Ƿ���ͨ�ɹ� getter ���� */
  public UFBoolean getBnormpur() {
    return (UFBoolean) this.getAttributeValue(VmiFIHeaderVO.BNORMPUR);
  }

  /** ������˰ getter ���� */
  public UFBoolean getBopptaxflag() {
    return (UFBoolean) this.getAttributeValue(VmiFIHeaderVO.BOPPTAXFLAG);
  }

  /** �Ƿ������� getter ���� */
  public UFBoolean getBsettlefinish() {
    return (UFBoolean) this.getAttributeValue(VmiFIHeaderVO.BSETTLEFINISH);
  }

  /** ����ó�� getter ���� */
  public UFBoolean getBtriatradeflag() {
    return (UFBoolean) this.getAttributeValue(VmiFIHeaderVO.BTRIATRADEFLAG);
  }

  /** �ͻ� getter ���� */
  public String getCasscustid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CASSCUSTID);
  }

  /** ��λ getter ���� */
  public String getCastunitid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CASTUNITID);
  }

  /** ���ĵ��ݱ�ͷ getter ���� */
  public String getCconsumehid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CCONSUMEHID);
  }

  /** ��λ�� getter ���� */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CCURRENCYID);
  }

  /** ˰�� getter ���� */
  public String getCesttaxcodeid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CESTTAXCODEID);
  }

  /** ������ **/
  public String getCffileid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CFFILEID);
  }

  /** �������� getter ���� */
  public String getCproductorid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CPRODUCTORID);
  }

  /** ��Ŀ getter ���� */
  public String getCprojectid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CPROJECTID);
  }

  /** ����ʱ�� getter ���� */
  public UFDateTime getCreationtime() {
    return (UFDateTime) this.getAttributeValue(VmiFIHeaderVO.CREATIONTIME);
  }

  /** ������ getter ���� */
  public String getCreator() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CREATOR);
  }

  /** �ջ�����/���� getter ���� */
  public String getCrececountryid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CRECECOUNTRYID);
  }

  /** �к� getter ���� */
  public String getCrowno() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CROWNO);
  }

  /** ��������/���� getter ���� */
  public String getCsendcountryid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CSENDCOUNTRYID);
  }

  /** ���״̬ getter ���� */
  public String getCstateid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CSTATEID);
  }

  /** ���Ļ��ܹ���ID getter ���� */
  public String getCsumruleid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CSUMRULEID);
  }

  /** ��˰����/���� getter ���� */
  public String getCtaxcountryid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CTAXCOUNTRYID);
  }

  /** �������� getter ���� */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CTRANTYPEID);
  }

  /** ����λ getter ���� */
  public String getCunitid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CUNITID);
  }

  /** �������� getter ���� */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(VmiFIHeaderVO.DBILLDATE);
  }

  /** ҵ������ getter ���� */
  public UFDate getDbizdate() {
    return (UFDate) this.getAttributeValue(VmiFIHeaderVO.DBIZDATE);
  }

  /** dr getter ���� */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(VmiFIHeaderVO.DR);
  }

  /** �ݹ����� getter ���� */
  public UFDate getDtocostapdate() {
    return (UFDate) this.getAttributeValue(VmiFIHeaderVO.DTOCOSTAPDATE);
  }

  /** �������� getter ���� */
  public Integer getFbuysellflag() {
    return (Integer) this.getAttributeValue(VmiFIHeaderVO.FBUYSELLFLAG);
  }

  /** ��˰��� getter ���� */
  public Integer getFesttaxtype() {
    return (Integer) this.getAttributeValue(VmiFIHeaderVO.FESTTAXTYPE);
  }

  /** ��Ӧ����־ getter ���� */
  public Integer getFtoapflag() {
    return (Integer) this.getAttributeValue(VmiFIHeaderVO.FTOAPFLAG);
  }

  /** ���ɱ���־ getter ���� */
  public Integer getFtoiaflag() {
    return (Integer) this.getAttributeValue(VmiFIHeaderVO.FTOIAFLAG);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.VMIFI_H);
  }

  /** ����޸�ʱ�� getter ���� */
  public UFDateTime getModifiedtime() {
    return (UFDateTime) this.getAttributeValue(VmiFIHeaderVO.MODIFIEDTIME);
  }

  /** ����޸��� getter ���� */
  public String getModifier() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.MODIFIER);
  }

  /** �ݹ������ۼƽ������� getter ���� */
  public UFDouble getNaccestcoststtlnum() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCESTCOSTSTTLNUM);
  }

  /** �ۼƻس��ݹ��ɱ���� getter ���� */
  public UFDouble getNaccestcostwashmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCESTCOSTWASHMNY);
  }

  /** �ۼƷ��ý����� getter ���� */
  public UFDouble getNaccfeesettlemny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCFEESETTLEMNY);
  }

  /** �ۼƻ�������� getter ���� */
  public UFDouble getNaccgoodssettlemny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCGOODSSETTLEMNY);
  }

  /** �ݹ�ǰ�ۼƽ����� getter ���� */
  public UFDouble getNaccpreeststtlmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCPREESTSTTLMNY);
  }

  /** �ۼƽ����� getter ���� */
  public UFDouble getNaccsettlemny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCSETTLEMNY);
  }

  /** �ۼƵ���ȷ��Ӧ��ԭ�Ҽ�˰�ϼ� getter ���� */
  public UFDouble getNacctoapadjstotmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCTOAPADJSTOTMNY);
  }

  /** �ۼƵ���ȷ�ϳɱ���� getter ���� */
  public UFDouble getNacctocostadjstmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCTOCOSTADJSTMNY);
  }

  /** �ۼƽ������� getter ���� */
  public UFDouble getNaccumsettlenum() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCUMSETTLENUM);
  }

  /** �Ƴɱ���� getter ���� */
  public UFDouble getNestcalcostmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTCALCOSTMNY);
  }

  /**
   * �ǳɱ�����
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>wuxla v61
   * </ul>
   * 
   * @return
   */
  public UFDouble getNestcalcostprice() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTCALCOSTPRICE);
  }

  /** ��˰��� getter ���� */
  public UFDouble getNestcaltaxmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTCALTAXMNY);
  }

  /** �۱����� getter ���� */
  public UFDouble getNestexhgrate() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTEXHGRATE);
  }

  /** ������˰��� getter ���� */
  public UFDouble getNestmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTMNY);
  }

  /** ���ɵֿ�˰�� getter ���� */
  public UFDouble getNestnosubtax() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTNOSUBTAX);
  }

  /** ���ɵֿ�˰�� getter ���� */
  public UFDouble getNestnosubtaxrate() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTNOSUBTAXRATE);
  }

  /** �ݹ������� getter ���� */
  public UFDouble getNestnum() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTNUM);
  }

  /** ��˰��� getter ���� */
  public UFDouble getNestomny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTOMNY);
  }

  /** ����˰���� getter ���� */
  public UFDouble getNestoprice() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTOPRICE);
  }

  /** ����˰���� getter ���� */
  public UFDouble getNestotaxprice() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTOTAXPRICE);
  }

  /** ��˰�ϼ� getter ���� */
  public UFDouble getNestototalmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTOTOTALMNY);
  }

  /** �ݹ����� getter ���� */
  public UFDouble getNestprice() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTPRICE);
  }

  /** ����˰�� getter ���� */
  public UFDouble getNesttaxmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTTAXMNY);
  }

  /** �ݹ���˰���� getter ���� */
  public UFDouble getNesttaxprice() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTTAXPRICE);
  }

  /** ˰�� getter ���� */
  public UFDouble getNesttaxrate() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTTAXRATE);
  }

  /** ���Ҽ�˰�ϼ� getter ���� */
  public UFDouble getNesttotalmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTTOTALMNY);
  }

  /** ���ñ�����˰��� getter ���� */
  public UFDouble getNfeemny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NFEEMNY);
  }

  /** ���ñ��Ҽ�˰�ϼ� getter ���� */
  public UFDouble getNfeetaxmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NFEETAXMNY);
  }

  /** �������� getter ���� */
  public UFDouble getNinassistnum() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NINASSISTNUM);
  }

  /** ���������� getter ���� */
  public UFDouble getNinnum() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NINNUM);
  }

  /** ��������˰���� getter ���� */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NNETPRICE);
  }

  /** �����Һ�˰���� getter ���� */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NTAXNETPRICE);
  }

  /** �ܱ�����˰��� getter ���� */
  public UFDouble getNtotalmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NTOTALMNY);
  }

  /** �ܱ��Ҽ�˰�ϼ� getter ���� */
  public UFDouble getNtotaltaxmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NTOTALTAXMNY);
  }

  /** ���ε��� getter ���� */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_BATCHCODE);
  }

  /** ҵ������ getter ���� */
  public String getPk_busitype() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_BUSITYPE);
  }

  /** ���ɱ���Ӧ���� getter ���� */
  public String getPk_costappsn() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_COSTAPPSN);
  }

  /** �ɱ��� getter ���� */
  public String getPk_costregion() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_COSTREGION);
  }

  /** ���� getter ���� */
  public String getPk_estcurrency() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_ESTCURRENCY);
  }

  /** ���������֯���°汾 getter ���� */
  public String getPk_financeorg() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_FINANCEORG);
  }

  /** ���������֯ getter ���� */
  public String getPk_financeorg_v() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_FINANCEORG_V);
  }

  /** ���� getter ���� */
  public String getPk_group() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_GROUP);
  }

  /** ���ϱ��� getter ���� */
  public String getPk_material() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_MATERIAL);
  }

  /** ��˾���°汾 getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_ORG);
  }

  /** ��˾ getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_ORG_V);
  }

  /** �������°汾 getter ���� */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_SRCMATERIAL);
  }

  /** ���Ļ��ܲ��񸨱�ʶ getter ���� */
  public String getPk_stockps() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_STOCKPS);
  }

  /** ���Ļ��ܲ�������ʶ getter ���� */
  public String getPk_stockps_b() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_STOCKPS_B);
  }

  /** �ֿ� getter ���� */
  public String getPk_stordoc() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_STORDOC);
  }

  /** �����֯���°汾 getter ���� */
  public String getPk_storeorg() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_STOREORG);
  }

  /** �����֯ getter ���� */
  public String getPk_storeorg_v() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_STOREORG_V);
  }

  /** ��Ӧ�� getter ���� */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_SUPPLIER);
  }

  /** ����ʱ�� getter ���� */
  public UFDateTime getTaudittime() {
    return (UFDateTime) this.getAttributeValue(VmiFIHeaderVO.TAUDITTIME);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(VmiFIHeaderVO.TS);
  }

  /** ���κ� getter ���� */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VBATCHCODE);
  }

  /** ���ݺ� getter ���� */
  public String getVbillcode() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VBILLCODE);
  }

  /** ������ getter ���� */
  public String getVchangerate() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VCHANGERATE);
  }

  /** ���ĵ��ݺ� getter ���� */
  public String getVconsumebillcode() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VCONSUMEBILLCODE);
  }

  /** ���ɸ�������1 getter ���� */
  public String getVfree1() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE1);
  }

  /** ���ɸ�������10 getter ���� */
  public String getVfree10() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE10);
  }

  /** ���ɸ�������2 getter ���� */
  public String getVfree2() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE2);
  }

  /** ���ɸ�������3 getter ���� */
  public String getVfree3() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE3);
  }

  /** ���ɸ�������4 getter ���� */
  public String getVfree4() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE4);
  }

  /** ���ɸ�������5 getter ���� */
  public String getVfree5() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE5);
  }

  /** ���ɸ�������6 getter ���� */
  public String getVfree6() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE6);
  }

  /** ���ɸ�������7 getter ���� */
  public String getVfree7() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE7);
  }

  /** ���ɸ�������8 getter ���� */
  public String getVfree8() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE8);
  }

  /** ���ɸ�������9 getter ���� */
  public String getVfree9() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE9);
  }

  /** ����ƥ��ɹ����Ĺ��� getter ���� */
  public String getVmimatchpurinrule() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VMIMATCHPURINRULE);
  }

  /** �������� getter ���� */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VTRANTYPECODE);
  }

  /** ������ setter ���� */
  public void setApprover(String approver) {
    this.setAttributeValue(VmiFIHeaderVO.APPROVER, approver);
  }

  /** Ӱ��ɱ���־ setter ���� */
  public void setBaffectcost(UFBoolean baffectcost) {
    this.setAttributeValue(VmiFIHeaderVO.BAFFECTCOST, baffectcost);
  }

  /** �Ƶ��� setter ���� */
  public void setBillmaker(String billmaker) {
    this.setAttributeValue(VmiFIHeaderVO.BILLMAKER, billmaker);
  }

  /** �Ƿ���ͨ�ɹ� setter ���� */
  public void setBnormpur(UFBoolean bnormpur) {
    this.setAttributeValue(VmiFIHeaderVO.BNORMPUR, bnormpur);
  }

  /** ������˰ setter ���� */
  public void setBopptaxflag(UFBoolean bopptaxflag) {
    this.setAttributeValue(VmiFIHeaderVO.BOPPTAXFLAG, bopptaxflag);
  }

  /** �Ƿ������� setter ���� */
  public void setBsettlefinish(UFBoolean bsettlefinish) {
    this.setAttributeValue(VmiFIHeaderVO.BSETTLEFINISH, bsettlefinish);
  }

  /** ����ó�� setter ���� */
  public void setBtriatradeflag(UFBoolean btriatradeflag) {
    this.setAttributeValue(VmiFIHeaderVO.BTRIATRADEFLAG, btriatradeflag);
  }

  /** �ͻ� setter ���� */
  public void setCasscustid(String casscustid) {
    this.setAttributeValue(VmiFIHeaderVO.CASSCUSTID, casscustid);
  }

  /** ��λ setter ���� */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(VmiFIHeaderVO.CASTUNITID, castunitid);
  }

  /** ���ĵ��ݱ�ͷsetter ���� */
  public void setCconsumehid(String cconsumehid) {
    this.setAttributeValue(VmiFIHeaderVO.CCONSUMEHID, cconsumehid);
  }

  /** ��λ�� setter ���� */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(VmiFIHeaderVO.CCURRENCYID, ccurrencyid);
  }

  /** ˰�� setter ���� */
  public void setCesttaxcodeid(String cesttaxcodeid) {
    this.setAttributeValue(VmiFIHeaderVO.CESTTAXCODEID, cesttaxcodeid);
  }

  /** ������ **/
  public void setCffileid(String cffileid) {
    this.setAttributeValue(VmiFIHeaderVO.CFFILEID, cffileid);
  }

  /** �������� setter ���� */
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(VmiFIHeaderVO.CPRODUCTORID, cproductorid);
  }

  /** ��Ŀ setter ���� */
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(VmiFIHeaderVO.CPROJECTID, cprojectid);
  }

  /** ����ʱ�� setter ���� */
  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(VmiFIHeaderVO.CREATIONTIME, creationtime);
  }

  /** ������ setter ���� */
  public void setCreator(String creator) {
    this.setAttributeValue(VmiFIHeaderVO.CREATOR, creator);
  }

  /** �ջ�����/���� setter ���� */
  public void setCrececountryid(String crececountryid) {
    this.setAttributeValue(VmiFIHeaderVO.CRECECOUNTRYID, crececountryid);
  }

  /** �к� setter ���� */
  public void setCrowno(String crowno) {
    this.setAttributeValue(VmiFIHeaderVO.CROWNO, crowno);
  }

  /** ��������/���� setter ���� */
  public void setCsendcountryid(String csendcountryid) {
    this.setAttributeValue(VmiFIHeaderVO.CSENDCOUNTRYID, csendcountryid);
  }

  /** ���״̬ setter ���� */
  public void setCstateid(String cstateid) {
    this.setAttributeValue(VmiFIHeaderVO.CSTATEID, cstateid);
  }

  /** ���Ļ��ܹ���ID setter ���� */
  public void setCsumruleid(String csumruleid) {
    this.setAttributeValue(VmiFIHeaderVO.CSUMRULEID, csumruleid);
  }

  /** ��˰����/���� setter ���� */
  public void setCtaxcountryid(String ctaxcountryid) {
    this.setAttributeValue(VmiFIHeaderVO.CTAXCOUNTRYID, ctaxcountryid);
  }

  /** �������� setter ���� */
  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(VmiFIHeaderVO.CTRANTYPEID, ctrantypeid);
  }

  /** ����λ setter ���� */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(VmiFIHeaderVO.CUNITID, cunitid);
  }

  /** �������� setter ���� */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(VmiFIHeaderVO.DBILLDATE, dbilldate);
  }

  /** ҵ������ setter ���� */
  public void setDbizdate(UFDate dbizdate) {
    this.setAttributeValue(VmiFIHeaderVO.DBIZDATE, dbizdate);
  }

  /** dr setter ���� */
  public void setDr(Integer dr) {
    this.setAttributeValue(VmiFIHeaderVO.DR, dr);
  }

  /** �ݹ����� setter ���� */
  public void setDtocostapdate(UFDate dtocostapdate) {
    this.setAttributeValue(VmiFIHeaderVO.DTOCOSTAPDATE, dtocostapdate);
  }

  /** �������� setter ���� */
  public void setFbuysellflag(Integer fbuysellflag) {
    this.setAttributeValue(VmiFIHeaderVO.FBUYSELLFLAG, fbuysellflag);
  }

  /** ��˰��� setter ���� */
  public void setFesttaxtype(Integer festtaxtype) {
    this.setAttributeValue(VmiFIHeaderVO.FESTTAXTYPE, festtaxtype);
  }

  /** ��Ӧ����־ setter ���� */
  public void setFtoapflag(Integer ftoapflag) {
    this.setAttributeValue(VmiFIHeaderVO.FTOAPFLAG, ftoapflag);
  }

  /** ���ɱ���־ setter ���� */
  public void setFtoiaflag(Integer ftoiaflag) {
    this.setAttributeValue(VmiFIHeaderVO.FTOIAFLAG, ftoiaflag);
  }

  /** ����޸�ʱ�� setter ���� */
  public void setModifiedtime(UFDateTime modifiedtime) {
    this.setAttributeValue(VmiFIHeaderVO.MODIFIEDTIME, modifiedtime);
  }

  /** ����޸��� setter ���� */
  public void setModifier(String modifier) {
    this.setAttributeValue(VmiFIHeaderVO.MODIFIER, modifier);
  }

  /** �ݹ������ۼƽ������� setter ���� */
  public void setNaccestcoststtlnum(UFDouble naccestcoststtlnum) {
    this.setAttributeValue(VmiFIHeaderVO.NACCESTCOSTSTTLNUM, naccestcoststtlnum);
  }

  /** �ۼƻس��ݹ��ɱ���� setter ���� */
  public void setNaccestcostwashmny(UFDouble naccestcostwashmny) {
    this.setAttributeValue(VmiFIHeaderVO.NACCESTCOSTWASHMNY, naccestcostwashmny);
  }

  /** �ۼƷ��ý����� setter ���� */
  public void setNaccfeesettlemny(UFDouble naccfeesettlemny) {
    this.setAttributeValue(VmiFIHeaderVO.NACCFEESETTLEMNY, naccfeesettlemny);
  }

  /** �ۼƻ�������� setter ���� */
  public void setNaccgoodssettlemny(UFDouble naccgoodssettlemny) {
    this.setAttributeValue(VmiFIHeaderVO.NACCGOODSSETTLEMNY, naccgoodssettlemny);
  }

  /** �ݹ�ǰ�ۼƽ����� setter ���� */
  public void setNaccpreeststtlmny(UFDouble naccpreeststtlmny) {
    this.setAttributeValue(VmiFIHeaderVO.NACCPREESTSTTLMNY, naccpreeststtlmny);
  }

  /** �ۼƽ����� setter ���� */
  public void setNaccsettlemny(UFDouble naccsettlemny) {
    this.setAttributeValue(VmiFIHeaderVO.NACCSETTLEMNY, naccsettlemny);
  }

  /** �ۼƵ���ȷ��Ӧ��ԭ�Ҽ�˰�ϼ� setter ���� */
  public void setNacctoapadjstotmny(UFDouble nacctoapadjstotmny) {
    this.setAttributeValue(VmiFIHeaderVO.NACCTOAPADJSTOTMNY, nacctoapadjstotmny);
  }

  /** �ۼƵ���ȷ�ϳɱ���� setter ���� */
  public void setNacctocostadjstmny(UFDouble nacctocostadjstmny) {
    this.setAttributeValue(VmiFIHeaderVO.NACCTOCOSTADJSTMNY, nacctocostadjstmny);
  }

  /** �ۼƽ������� setter ���� */
  public void setNaccumsettlenum(UFDouble naccumsettlenum) {
    this.setAttributeValue(VmiFIHeaderVO.NACCUMSETTLENUM, naccumsettlenum);
  }

  /** �Ƴɱ���� setter ���� */
  public void setNestcalcostmny(UFDouble nestcalcostmny) {
    this.setAttributeValue(VmiFIHeaderVO.NESTCALCOSTMNY, nestcalcostmny);
  }

  /**
   * �ǳɱ�����
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>wuxla V61
   * </ul>
   * 
   * @param nestcalcostprice
   */
  public void setNestcalcostprice(UFBoolean nestcalcostprice) {
    this.setAttributeValue(VmiFIHeaderVO.NESTCALCOSTPRICE, nestcalcostprice);
  }

  /** ��˰��� setter ���� */
  public void setNestcaltaxmny(UFDouble nestcaltaxmny) {
    this.setAttributeValue(VmiFIHeaderVO.NESTCALTAXMNY, nestcaltaxmny);
  }

  /** �۱����� setter ���� */
  public void setNestexhgrate(UFDouble nestexhgrate) {
    this.setAttributeValue(VmiFIHeaderVO.NESTEXHGRATE, nestexhgrate);
  }

  /** ������˰��� setter ���� */
  public void setNestmny(UFDouble nestmny) {
    this.setAttributeValue(VmiFIHeaderVO.NESTMNY, nestmny);
  }

  /** ���ɵֿ�˰�� setter ���� */
  public void setNestnosubtax(UFDouble nnosubtax) {
    this.setAttributeValue(VmiFIHeaderVO.NESTNOSUBTAX, nnosubtax);
  }

  /** ���ɵֿ�˰�� setter ���� */
  public void setNestnosubtaxrate(UFDouble nestnosubtaxrate) {
    this.setAttributeValue(VmiFIHeaderVO.NESTNOSUBTAXRATE, nestnosubtaxrate);
  }

  /** �ݹ������� setter ���� */
  public void setNestnum(UFDouble nestnum) {
    this.setAttributeValue(VmiFIHeaderVO.NESTNUM, nestnum);
  }

  /** ��˰��� setter ���� */
  public void setNestomny(UFDouble nestomny) {
    this.setAttributeValue(VmiFIHeaderVO.NESTOMNY, nestomny);
  }

  /** ����˰���� setter ���� */
  public void setNestoprice(UFDouble nestoprice) {
    this.setAttributeValue(VmiFIHeaderVO.NESTOPRICE, nestoprice);
  }

  /** ����˰���� setter ���� */
  public void setNestotaxprice(UFDouble nestotaxprice) {
    this.setAttributeValue(VmiFIHeaderVO.NESTOTAXPRICE, nestotaxprice);
  }

  /** ��˰�ϼ� setter ���� */
  public void setNestototalmny(UFDouble nestototalmny) {
    this.setAttributeValue(VmiFIHeaderVO.NESTOTOTALMNY, nestototalmny);
  }

  /** �ݹ����� setter ���� */
  public void setNestprice(UFDouble nestprice) {
    this.setAttributeValue(VmiFIHeaderVO.NESTPRICE, nestprice);
  }

  /** ����˰�� setter ���� */
  public void setNesttaxmny(UFDouble nesttaxmny) {
    this.setAttributeValue(VmiFIHeaderVO.NESTTAXMNY, nesttaxmny);
  }

  /** �ݹ���˰���� setter ���� */
  public void setNesttaxprice(UFDouble nesttaxprice) {
    this.setAttributeValue(VmiFIHeaderVO.NESTTAXPRICE, nesttaxprice);
  }

  /** ˰�� setter ���� */
  public void setNesttaxrate(UFDouble nesttaxrate) {
    this.setAttributeValue(VmiFIHeaderVO.NESTTAXRATE, nesttaxrate);
  }

  /** ���Ҽ�˰�ϼ� setter ���� */
  public void setNesttotalmny(UFDouble nesttotalmny) {
    this.setAttributeValue(VmiFIHeaderVO.NESTTOTALMNY, nesttotalmny);
  }

  /** ���ñ�����˰��� setter ���� */
  public void setNfeemny(UFDouble nfeemny) {
    this.setAttributeValue(VmiFIHeaderVO.NFEEMNY, nfeemny);
  }

  /** ���ñ��Ҽ�˰�ϼ� setter ���� */
  public void setNfeetaxmny(UFDouble nfeetaxmny) {
    this.setAttributeValue(VmiFIHeaderVO.NFEETAXMNY, nfeetaxmny);
  }

  /** �������� setter ���� */
  public void setNinassistnum(UFDouble ninassistnum) {
    this.setAttributeValue(VmiFIHeaderVO.NINASSISTNUM, ninassistnum);
  }

  /** ���������� setter ���� */
  public void setNinnum(UFDouble ninnum) {
    this.setAttributeValue(VmiFIHeaderVO.NINNUM, ninnum);
  }

  /** ��������˰���� setter ���� */
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(VmiFIHeaderVO.NNETPRICE, nnetprice);
  }

  /** �����Һ�˰���� setter ���� */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(VmiFIHeaderVO.NTAXNETPRICE, ntaxnetprice);
  }

  /** �ܱ�����˰��� setter ���� */
  public void setNtotalmny(UFDouble ntotalmny) {
    this.setAttributeValue(VmiFIHeaderVO.NTOTALMNY, ntotalmny);
  }

  /** �ܱ��Ҽ�˰�ϼ� setter ���� */
  public void setNtotaltaxmny(UFDouble ntotaltaxmny) {
    this.setAttributeValue(VmiFIHeaderVO.NTOTALTAXMNY, ntotaltaxmny);
  }

  /** ���ε��� setter ���� */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(VmiFIHeaderVO.PK_BATCHCODE, pk_batchcode);
  }

  /** ҵ������ setter ���� */
  public void setPk_busitype(String pk_busitype) {
    this.setAttributeValue(VmiFIHeaderVO.PK_BUSITYPE, pk_busitype);
  }

  /** ���ɱ���Ӧ���� setter ���� */
  public void setPk_costappsn(String pk_costappsn) {
    this.setAttributeValue(VmiFIHeaderVO.PK_COSTAPPSN, pk_costappsn);
  }

  /** �ɱ��� setter ���� */
  public void setPk_costregion(String pk_costregion) {
    this.setAttributeValue(VmiFIHeaderVO.PK_COSTREGION, pk_costregion);
  }

  /** ���� setter ���� */
  public void setPk_estcurrency(String pk_estcurrency) {
    this.setAttributeValue(VmiFIHeaderVO.PK_ESTCURRENCY, pk_estcurrency);
  }

  /** ���������֯���°汾 setter ���� */
  public void setPk_financeorg(String pk_financeorg) {
    this.setAttributeValue(VmiFIHeaderVO.PK_FINANCEORG, pk_financeorg);
  }

  /** ���������֯ setter ���� */
  public void setPk_financeorg_v(String pk_financeorg_v) {
    this.setAttributeValue(VmiFIHeaderVO.PK_FINANCEORG_V, pk_financeorg_v);
  }

  /** ���� setter ���� */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(VmiFIHeaderVO.PK_GROUP, pk_group);
  }

  /** ���ϱ��� setter ���� */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(VmiFIHeaderVO.PK_MATERIAL, pk_material);
  }

  /** ��˾���°汾 setter ���� */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(VmiFIHeaderVO.PK_ORG, pk_org);
  }

  /** ��˾ setter ���� */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(VmiFIHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** �������°汾 setter ���� */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(VmiFIHeaderVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** ���Ļ��ܲ��񸨱�ʶ setter ���� */
  public void setPk_stockps(String pk_stockps) {
    this.setAttributeValue(VmiFIHeaderVO.PK_STOCKPS, pk_stockps);
  }

  /** ���Ļ��ܲ�������ʶ setter ���� */
  public void setPk_stockps_b(String pk_stockps_b) {
    this.setAttributeValue(VmiFIHeaderVO.PK_STOCKPS_B, pk_stockps_b);
  }

  /** �ֿ� setter ���� */
  public void setPk_stordoc(String pk_stordoc) {
    this.setAttributeValue(VmiFIHeaderVO.PK_STORDOC, pk_stordoc);
  }

  /** �����֯���°汾 setter ���� */
  public void setPk_storeorg(String pk_storeorg) {
    this.setAttributeValue(VmiFIHeaderVO.PK_STOREORG, pk_storeorg);
  }

  /** �����֯ setter ���� */
  public void setPk_storeorg_v(String pk_storeorg_v) {
    this.setAttributeValue(VmiFIHeaderVO.PK_STOREORG_V, pk_storeorg_v);
  }

  /** ��Ӧ�� setter ���� */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(VmiFIHeaderVO.PK_SUPPLIER, pk_supplier);
  }

  /** ����ʱ�� setter ���� */
  public void setTaudittime(UFDateTime taudittime) {
    this.setAttributeValue(VmiFIHeaderVO.TAUDITTIME, taudittime);
  }

  /** ts setter ���� */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(VmiFIHeaderVO.TS, ts);
  }

  /** ���κ� setter ���� */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(VmiFIHeaderVO.VBATCHCODE, vbatchcode);
  }

  /** ���ݺ� setter ���� */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(VmiFIHeaderVO.VBILLCODE, vbillcode);
  }

  /** ������ setter ���� */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(VmiFIHeaderVO.VCHANGERATE, vchangerate);
  }

  /** ���ĵ��ݺ� setter ���� */
  public void setVconsumebillcode(String vconsumebillcode) {
    this.setAttributeValue(VmiFIHeaderVO.VCONSUMEBILLCODE, vconsumebillcode);
  }

  /** ���ɸ�������1 setter ���� */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE1, vfree1);
  }

  /** ���ɸ�������10 setter ���� */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE10, vfree10);
  }

  /** ���ɸ�������2 setter ���� */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE2, vfree2);
  }

  /** ���ɸ�������3 setter ���� */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE3, vfree3);
  }

  /** ���ɸ�������4 setter ���� */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE4, vfree4);
  }

  /** ���ɸ�������5 setter ���� */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE5, vfree5);
  }

  /** ���ɸ�������6 setter ���� */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE6, vfree6);
  }

  /** ���ɸ�������7 setter ���� */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE7, vfree7);
  }

  /** ���ɸ�������8 setter ���� */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE8, vfree8);
  }

  /** ���ɸ�������9 setter ���� */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(VmiFIHeaderVO.VFREE9, vfree9);
  }

  /** ����ƥ��ɹ����Ĺ��� setter ���� */
  public void setVmimatchpurinrule(String vmimatchpurinrule) {
    this.setAttributeValue(VmiFIHeaderVO.VMIMATCHPURINRULE, vmimatchpurinrule);
  }

  /** �������� setter ���� */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(VmiFIHeaderVO.VTRANTYPECODE, vtrantypecode);
  }

}
