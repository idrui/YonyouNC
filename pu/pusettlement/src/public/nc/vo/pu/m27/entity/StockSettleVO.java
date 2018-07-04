package nc.vo.pu.m27.entity;

import nc.vo.pu.m25.settle.ICostfactorDiscount;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * �˴���������˵���� �������ڣ�(2001-7-5)
 * 
 * @author��
 */
public abstract class StockSettleVO extends AbstractDataView implements
    ICostfactorDiscount {
  /** �Ƿ���ͨ�ɹ� */
  public static final String BNORMPUR = "bnormpur";

  public static final String BSETTLEFINISH = "bsettlefinish";

  /** ��λ */
  public static final String CASTUNITID = "castunitid";

  public static final String CBILLTYPECODE = "cbilltypecode";

  /** ���ұ��� */
  public static final String CCURRENCYID = "ccurrencyid";

  /** Դͷ���ݷ�¼��ʶ */
  public static final String CFIRSTBID = "cfirstbid";

  /** Դͷ���ݱ�ʶ */
  public static final String CFIRSTID = "cfirstid";

  /** Դͷ�������� */
  public static final String CFIRSTTYPECODE = "cfirsttypecode";

  public static final String CORIGCURRENCYID = "corigcurrencyid";

  public static final String CPRODUCTORID = "cproductorid";

  public static final String CPROJECTID = "cprojectid";

  /** ��Դ���ݷ�¼��ʶ */
  public static final String CSOURCEBID = "csourcebid";

  /** ��Դ���ݱ�ʶ */
  public static final String CSOURCEID = "csourceid";

  /** ��Դ�������� */
  public static final String CSOURCETYPECODE = "csourcetypecode";

  /**
   * ��������
   */
  public static final String CTRANTYPEID = "ctrantypeid";

  /** ����λ */
  public static final String CUNITID = "cunitid";

  /** ������� **/
  public static final String DBILLDATE = "dbilldate";

  /** �ݹ�Ӧ�����ۼƳ������ */
  public static final String NACCESTAPWASHNUM = "naccestapwashnum";

  /** �ݹ������ۼƽ������� */
  public static final String NACCESTCOSTSTTLNUM = "naccestcoststtlnum";

  /** �ۼ��ݹ�������� */
  public static final String NACCESTCOSTWASHMNY = "naccestcostwashmny";

  /** �ۼƻ�������� */
  public static final String NACCGOODSSETTLEMNY = "naccgoodssettlemny";

  /** �ۼ�ȷ�ϳ������ */
  public static final String NACCTOCOSTADJSTMNY = "nacctocostadjstmny";

  /** �ۼƽ������� */
  public static final String NACCUMSETTLENUM = "naccumsettlenum";

  /** ����ƽ���� */
  public final static String NAVGSETTLEPRICE = "navgsettleprice";

  /** ��ⵥ�ϵļƳɱ���� */
  public static final String NCALCOSTMNY = "ncalcostmny";

  // wuxla v61 �Ƴɱ�����
  /** ��ⵥȷ�ϳɱ����� */
  public static final String NCALCOSTPRICE = "ncalcostprice";

  /** δ������ */
  public static final String NCANSETTLEMNY = "ncansettlemny";

  /** δ�������� */
  public static final String NCANSETTLENUM = "ncansettlenum";

  /** ��ⵥ�ϵĳɱ���� */
  public final static String NCOSTMNY = "ncostmny";

  /** ��ⵥ�ϵĳɱ����� */
  public final static String NCOSTPRICE = "ncostprice";

  /** ���ν������� */
  public static final String NCURRENTSETTLENUM = "ncurrentsettlenum";

  /** �Ƴɱ���� */
  public static final String NESTCALCOSTMNY = "nestcalcostmny";

  // wuxla v61 �ݹ��ǳɱ�����
  public static final String NESTCALCOSTPRICE = "nestcalcostprice";

  /** �ݹ���� */
  public static final String NESTMNY = "nestmny";

  /** �ݹ����� */
  public static final String NESTNUM = "nestnum";

  /** �ݹ����� */
  public static final String NESTPRICE = "nestprice";

  /**
   * ʵ��ҵ������
   */
  public static final String NINASSISTNUM = "ninassistnum";

  /**
   * ʵ���������
   */
  public static final String NINNUM = "ninnum";

  /** ������˰��ȷ�ϳɱ�ʱ�Ľ�� */
  public static final String NMNY = "nmny";

  /** ��������˰���ۣ�ȷ�ϳɱ�ʱ�ĵ��� */
  public static final String NNETPRICE = "nnetprice";

  /** ����˰���� */
  public static final String NORIGNETPRICE = "norignetprice";

  public static final String NORIGPRICE = "norigprice";

  /** δ�ݹ��ѽ������������ */
  public final static String NPURESETTLEMNY = "npuresettlemny";

  /** δ�ݹ��ѽ������������������� */
  public final static String NPURESETTLENUM = "npuresettlenum";

  /** ʣ��������� */
  public static final String NRESIDUALSETTLENUM = "nresidualsettlenum";

  public static final String PK_BATCHCODE = "pk_batchcode";

  public static final String PK_COSTREGION = "pk_costregion";

  public static final String PK_DEPT = "pk_dept";

  public static final String PK_DEPT_V = "pk_dept_v";

  public static final String PK_FINANCEORG = "pk_financeorg";

  public static final String PK_GROUP = "pk_group";

  public static final String PK_MATERIAL = "pk_material";

  public static final String PK_ORDER = "pk_order";

  public static final String PK_ORDER_B = "pk_order_b";

  public static final String PK_ORG = "pk_org";

  public static final String PK_ORG_V = "pk_org_v";

  public static final String PK_PSNDOC = "pk_psndoc";

  /** ����OID */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  public static final String PK_STOCKPS = "pk_stockps";

  public static final String PK_STOCKPS_B = "pk_stockps_b";

  public static final String PK_SUPPLIER = "pk_supplier";

  public static final String VBATCHCODE = "vbatchcode";

  public static final String VBILLCODE = "vbillcode";

  public static final String VCHANGERATE = "vchangerate";

  /** Դͷ���ݺ� */
  public static final String VFIRSTCODE = "vfirstcode";

  /** Դͷ�����к� */
  public static final String VFIRSTROWNO = "vfirstrowno";

  /** Դͷ�������� */
  public static final String VFIRSTTRANTYPE = "vfirsttrantype";

  public static final String VFREE1 = "vfree1";

  public static final String VFREE10 = "vfree10";

  public static final String VFREE2 = "vfree2";

  public static final String VFREE3 = "vfree3";

  public static final String VFREE4 = "vfree4";

  public static final String VFREE5 = "vfree5";

  public static final String VFREE6 = "vfree6";

  public static final String VFREE7 = "vfree7";

  public static final String VFREE8 = "vfree8";

  public static final String VFREE9 = "vfree9";

  public static final String VNOTEBODY = "vnotebody";

  public static final String VORDERCODE = "vordercode";

  public static final String VORDERTRANTYPECODE = "vordertrantypecode";

  /** ��Դ���ݺ� */
  public static final String VSOURCECODE = "vsourcecode";

  /** ��Դ�����к� */
  public static final String VSOURCEROWNO = "vsourcerowno";

  /** ��Դ�������� */
  public static final String VSOURCETRANTYPE = "vsourcetrantype";

  public static final String VTRANTYPECODE = "vtrantypecode";

  private static final long serialVersionUID = 1L;

  // add by liangchen1 631�����ڵ�����Ʊ
  /** �������� */
  private UFDouble m_nadjustmny = null;

  /** ���η�̯���ݽ����ý���ʱ�� */
  private UFDouble m_nallotmoney = null;

  /** ���η�̯�������������ý���ʱ�� */
  private UFDouble m_nallotnum = null;

  /** ����ƽ������ */
  private UFDouble m_navgsettleprice = null;

  /** δ������ */
  private UFDouble m_ncansettlemny = null;

  /** δ�������� */
  private UFDouble m_ncansettlenum = null;

  /** �ɱ�Ҫ��1-8 */
  private UFDouble m_ncostfactor1 = null;

  private UFDouble m_ncostfactor2 = null;

  private UFDouble m_ncostfactor3 = null;

  private UFDouble m_ncostfactor4 = null;

  private UFDouble m_ncostfactor5 = null;

  private UFDouble m_ncostfactor6 = null;

  private UFDouble m_ncostfactor7 = null;

  private UFDouble m_ncostfactor8 = null;

  /** �ۼƱ��ν������� */
  private UFDouble m_ncurrentaccumsettlenum = null;

  /** ���η�Ʊ������ (���������Ϻ��޷�Ʊ����) */
  private UFDouble m_ncurrentinvoicesettlemny = null;

  /** ���ν������� */
  private UFDouble m_ncurrentsettlenum = null;

  /** �����ܷ�̯�����ý���ʱ�� */
  private UFDouble m_ncurrenttotalsettlemoney = null;

  /** �ۿ� */
  private UFDouble m_ndiscount = null;

  /** δ�ݹ��ѽ������������ */
  private UFDouble m_npuresettlemny = null;

  /** δ�ݹ��ѽ������� */
  private UFDouble m_npuresettlenum = null;

  /** ����ʣ�����������ʵ�ʽ���ʱ���Դ�����Ϊ׼�����������С���� */
  private transient UFDouble m_nresidualsettlenum = null;

  protected transient DataViewMeta m_viewMeta = null;

  @Override
  public void addtoCurrenttotalsettlemoney(UFDouble dValue) {
    this.setNcurrenttotalsettlemoney(MathTool.add(
        this.getNcurrenttotalsettlemoney(), dValue));
  }

  /**
   * ���෽����д,���ĳЩû��Ԫ���ݵ��ֶ��Լ�����
   * 
   * @see nc.vo.pubapp.pattern.model.entity.view.AbstractDataView#getAttributeValue(java.lang.String)
   */
  @Override
  public Object getAttributeValue(String name) {
    // ����ļ���ֵ
    if (StockSettleVO.NRESIDUALSETTLENUM.equals(name)) {
      return this.getNresidualsettlenum();
    }
    else if (StockSettleVO.NCANSETTLENUM.equals(name)) {
      return this.getNcansettlenum();
    }
    else if (StockSettleVO.NCANSETTLEMNY.equals(name)) {
      return this.getNcansettlemny();
    }
    else if (StockSettleVO.NCURRENTSETTLENUM.equals(name)) {
      return this.getNcurrentsettlenum();
    }
    return super.getAttributeValue(name);
  }

  /** �õ��Ƿ�Ӱ��ɱ���־ **/
  public abstract UFBoolean getBaffectcost();

  /** �õ��Ƿ�Ӱ���������ĳɱ���־ **/
  public abstract UFBoolean getBaffectpciacost();

  /** ����ͨ�ɹ������Ǽ��� */
  public abstract UFBoolean getBnormpur();

  public abstract UFBoolean getBsettlefinish();

  /**
   * �����������������ҵ��λ
   * <p>
   * <b>����˵��</b>
   * 
   * @return ҵ��λ��PK
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-9-14 ����08:53:51
   */
  public abstract String getCastunitid();

  public abstract String getCbilltypecode();

  /** ���ұ��� getter ���� */
  public abstract String getCcurrencyid();

  /**
   * ��������������ȡ��<b>Դͷ����������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getCfirstbid();

  public abstract String getCfirstid();

  /**
   * ��������������ȡ��<b>Դͷ��������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getCfirsttypecode();

  /**
   * ��������������ȡ��<b>ԭ�ұ���</b>���ԡ�<br>
   * �����Զ���������ƥ�䣨���û���ѡ����˰������ͬʱ��
   * 
   * @param
   * @return String
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public abstract String getCorigcurrencyid();

  /**
   * ��������������ȡ��<b>��������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getCproductorid();

  /**
   * ��������������ȡ��<b>��Ŀ</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getCprojectid();

  /**
   * ��������������ȡ��<b>�ϲ㵥��������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getCsourcebid();

  /**
   * ��������������ȡ��<b>�ϲ㵥������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getCsourceid();

  /**
   * ��������������ȡ��<b>�ϲ㵥������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getCsourcetypecode();

  /**
   * ��������PK
   * 
   * @return
   */
  public abstract String getCtrantypeid();

  /**
   * ��������������ȡ��<b>��������λ</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getCunitid();

  /**
   * �������
   * 
   * @return
   */
  public abstract UFDate getDbilldate();

  /**
   * ������������������<b>��Ӧ��</b>���ԡ�
   * 
   * @param UFBoolean bconfirmap
   * @return
   * @author wangyf
   * @time 2009-6-30 13:38:31
   */
  public abstract Integer getFdirtoaptype();

  /**
   * ������������������<b>��Ӧ��</b>���ԡ�
   * 
   * @param UFBoolean bconfirmap
   * @return
   * @author wangyf
   * @time 2009-6-30 13:38:31
   */
  public abstract Integer getFdirtoiatype();

  /** �ݹ������ۼƽ������� getter ���� */
  public abstract UFDouble getNaccestcoststtlnum();

  /**
   * �ۼ��ݹ��������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-6-10 ����11:19:02
   */
  public abstract UFDouble getNaccestcostwashmny();

  /**
   * ���������������ۼƽ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @return �ۼƽ�����
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-9-16 ����04:07:53
   */
  public abstract UFDouble getNaccgoodssettlemny();

  /**
   * �������������� �ݹ�ǰ�ۼƽ�������÷�̯ʱʹ�ã���û���ݹ�ֻ�н���ĵ��ݷ����ۼƽ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-13 ����11:52:21
   */
  public abstract UFDouble getNaccpreeststtlmny();

  /**
   * ���������������ۼƵ���ȷ��Ӧ��ԭ�Ҽ�˰�ϼơ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-27 ����06:22:06
   */
  public UFDouble getNacctoapadjstotmny() {
    return UFDouble.ZERO_DBL;
  }

  /**
   * �ۼ�ȷ�ϳɱ�������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-6-10 ����11:19:08
   */
  public abstract UFDouble getNacctocostadjstmny();

  /**
   * ���������������ۼƽ�������
   * <p>
   * <b>����˵��</b>
   * 
   * @return �ۼƽ�������
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-9-16 ����04:08:18
   */
  public abstract UFDouble getNaccumsettlenum();

  public abstract UFDouble getNaccurevcostnum();

  @Override
  public UFDouble getNadjustmny() {
    return this.m_nadjustmny;
  }

  @Override
  public UFDouble getNallotmoney() {
    return this.m_nallotmoney;
  }

  @Override
  public UFDouble getNallotnum() {
    return this.m_nallotnum;
  }

  public UFDouble getNavgsettleprice() {
    return this.m_navgsettleprice;
  }

  /**
   * �ǳɱ����
   * <p>
   * ʹ�ó�����wuxla V61����
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public abstract UFDouble getNcalcostmny();

  public abstract UFDouble getNcalcostprice();

  public UFDouble getNcansettlemny() {
    return this.m_ncansettlemny;
  }

  public UFDouble getNcansettlenum() {
    return this.m_ncansettlenum;
  }

  @Override
  public UFDouble getNcostfactor1() {
    return this.m_ncostfactor1;
  }

  @Override
  public UFDouble getNcostfactor2() {
    return this.m_ncostfactor2;
  }

  @Override
  public UFDouble getNcostfactor3() {
    return this.m_ncostfactor3;
  }

  @Override
  public UFDouble getNcostfactor4() {
    return this.m_ncostfactor4;
  }

  @Override
  public UFDouble getNcostfactor5() {
    return this.m_ncostfactor5;
  }

  @Override
  public UFDouble getNcostfactor6() {
    return this.m_ncostfactor6;
  }

  @Override
  public UFDouble getNcostfactor7() {
    return this.m_ncostfactor7;
  }

  @Override
  public UFDouble getNcostfactor8() {
    return this.m_ncostfactor8;
  }

  public UFDouble getNcurrentaccumsettlenum() {
    return this.m_ncurrentaccumsettlenum;
  }

  public UFDouble getNcurrentinvoicesettlemny() {
    return this.m_ncurrentinvoicesettlemny;
  }

  public UFDouble getNcurrentsettlenum() {
    return this.m_ncurrentsettlenum;
  }

  public UFDouble getNcurrenttotalsettlemoney() {
    return this.m_ncurrenttotalsettlemoney;
  }

  @Override
  public UFDouble getNdiscount() {
    return this.m_ndiscount;
  }

  /**
   * �ݹ��ǳɱ����
   * <p>
   * ʹ�ó�����wuxla V61����
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public abstract UFDouble getNestcalcostmny();

  /**
   * �ݹ��ǳɱ�����
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>wuxla v61
   * </ul>
   * 
   * @return
   */
  public abstract UFDouble getNestcalcostprice();

  public abstract UFDouble getNestmny();

  /**
   * �ݹ��ɱ�����
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @author wangyf
   * @time 2010-4-2 ����02:13:12
   */
  public abstract UFDouble getNestnum();

  public abstract UFDouble getNestprice();

  public abstract UFDouble getNinassistnum();

  public abstract UFDouble getNinnum();

  /**
   * ������˰��� <br>
   * ���ȷ�Ϲ��ɱ�����Ϊȷ�ϳɱ��Ľ��
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-6-8 ����01:11:07
   */
  public abstract UFDouble getNmny();

  /**
   * ��������˰���ۣ�ȷ�ϳɱ��ĵ���<br>
   * Ҳ���ڷ�Ʊ��ⵥ�Զ���������ƥ�䣨���û���ѡ����˰������ͬʱ��
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-6-8 ����01:10:37
   */
  public abstract UFDouble getNnetprice();

  /**
   * ��ⵥ����ԭ����˰���ۣ�������ң��򷵻���������˰���ۣ�<br>
   * ���ں�����ⵥ�Զ���������ƥ�䣨���û���ѡ����˰������ͬʱ��
   * 
   * @return
   */
  public abstract UFDouble getNorignetprice();

  /**
   * ����������������ⵥȷ��Ӧ����˰�ϼơ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-27 ����06:47:12
   */
  public UFDouble getNorigtaxmny() {
    return UFDouble.ZERO_DBL;
  }

  /**
   * ��������������ȷ��Ӧ����ԭ�Ҽۺ�˰����(Ϊ��ⵥ��)��
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-27 ����06:21:00
   */
  public UFDouble getNorigtaxnetprice() {
    return UFDouble.ZERO_DBL;
  }

  public UFDouble getNpuresettlemny() {
    return this.m_npuresettlemny;
  }

  public UFDouble getNpuresettlenum() {
    return this.m_npuresettlenum;
  }

  public UFDouble getNresidualsettlenum() {
    return this.m_nresidualsettlenum;
  }

  public abstract String getPk_batchcode();

  public abstract String getPk_costregion();

  /**
   * ��������������ȡ��<b>����</b>���ԡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-27 ����09:41:46
   */
  public abstract String getPk_dept();

  public abstract String getPk_dept_v();

  /**
   * ��������������ȡ��<b>ֱ�����۶�����</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getPk_dtransalebid();

  /**
   * ��������������ȡ��<b>ֱ�����۶���</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getPk_dtransaleid();

  public abstract String getPk_financeorg();

  public abstract String getPk_group();

  public abstract String getPk_material();

  public abstract String getPk_order();

  public abstract String getPk_order_b();

  public abstract String getPk_org();

  public abstract String getPk_org_v();

  /**
   * ��������������ȡ��<b>ҵ��Ա</b>���ԡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-27 ����09:41:46
   */
  public abstract String getPk_psndoc();

  @Override
  public abstract String getPk_srcmaterial();

  public abstract String getPk_stockps();

  /**
   * ��������������ȡ��<b>�����ȼ�</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  // public abstract String getCqualitylevelid();
  public abstract String getPk_stockps_b();

  public abstract String getPk_supplier();

  public abstract String getVbatchcode();

  public abstract String getVbillcode();

  public abstract String getVchangerate();

  /** �ɹ���ͬ�� getter ���� */
  public abstract String getVctcode();

  /**
   * ��������������ȡ��<b>Դͷ���ݺ�</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getVfirstcode();

  public abstract String getVfirstrowno();

  /**
   * ��������������ȡ��<b>Դͷ��������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getVfirsttrantype();

  public abstract String getVfree1();

  public abstract String getVfree10();

  public abstract String getVfree2();

  public abstract String getVfree3();

  public abstract String getVfree4();

  public abstract String getVfree5();

  public abstract String getVfree6();

  public abstract String getVfree7();

  public abstract String getVfree8();

  public abstract String getVfree9();

  public abstract String getVnotebody();

  public abstract String getVordercode();

  public abstract String getVordertrantypecode();

  /**
   * ��������������ȡ��<b>�ϲ㵥�ݺ�</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getVsourcecode();

  /**
   * ��������������ȡ��<b>�ϲ㵥���к�</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getVsourcerowno();

  /**
   * ��������������ȡ��<b>�ϲ㽻������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getVsourcetrantype();

  public abstract String getVtrantypecode();

  /** �����Ƿ�Ӱ��ɱ���־ **/
  public abstract void setBaffectcost(UFBoolean flag);

  /** �����Ƿ�Ӱ���������ĳɱ���־ **/
  public abstract void setBaffectpciacost(UFBoolean flag);

  /**
   * �������
   * 
   * @param dbilldate
   */
  public abstract void setDbilldate(UFDate dbilldate);

  /**
   * �ۼ��ݹ��������
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wangyf
   * @time 2010-6-10 ����02:04:36
   */
  public abstract void setNaccestcostwashmny(UFDouble value);

  /**
   * ���������������ۼƵ���ȷ��Ӧ��ԭ�Ҽ�˰�ϼơ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param nacctoapadjstotmny <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-27 ����06:22:18
   */
  public void setNacctoapadjstotmny(UFDouble nacctoapadjstotmny) {
    if (nacctoapadjstotmny == null) {
      return;
      //
    }
  }

  /**
   * �ۼ�ȷ�ϳɱ�������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-6-10 ����02:04:08
   */
  public abstract void setNacctocostadjstmny(UFDouble value);

  @Override
  public void setNadjustmny(UFDouble nadjustmny) {
    this.m_nadjustmny = nadjustmny;
  }

  /**
   * @param nallotmoney Ҫ���õ� nallotmoney
   */
  public void setNallotmoney(UFDouble nallotmoney) {
    this.m_nallotmoney = nallotmoney;
  }

  /**
   * @param nallotnum Ҫ���õ� nallotnum
   */
  public void setNallotnum(UFDouble nallotnum) {
    this.m_nallotnum = nallotnum;
  }

  public void setNavgsettleprice(UFDouble navgsettleprice) {
    this.m_navgsettleprice = navgsettleprice;
  }

  /**
   * �Ƴɱ��ɽ�����
   * 
   * @param ncansettlenmny
   */
  public void setNcansettlemny(UFDouble ncansettlenmny) {
    this.m_ncansettlemny = ncansettlenmny;
  }

  public void setNcansettlenum(UFDouble ncansettlenum) {
    this.m_ncansettlenum = ncansettlenum;
  }

  @Override
  public void setNcostfactor1(UFDouble ncostfactor1) {
    this.m_ncostfactor1 = ncostfactor1;
  }

  @Override
  public void setNcostfactor2(UFDouble ncostfactor2) {
    this.m_ncostfactor2 = ncostfactor2;
  }

  @Override
  public void setNcostfactor3(UFDouble ncostfactor3) {
    this.m_ncostfactor3 = ncostfactor3;
  }

  @Override
  public void setNcostfactor4(UFDouble ncostfactor4) {
    this.m_ncostfactor4 = ncostfactor4;
  }

  @Override
  public void setNcostfactor5(UFDouble ncostfactor5) {
    this.m_ncostfactor5 = ncostfactor5;
  }

  @Override
  public void setNcostfactor6(UFDouble ncostfactor6) {
    this.m_ncostfactor6 = ncostfactor6;
  }

  @Override
  public void setNcostfactor7(UFDouble ncostfactor7) {
    this.m_ncostfactor7 = ncostfactor7;
  }

  @Override
  public void setNcostfactor8(UFDouble ncostfactor8) {
    this.m_ncostfactor8 = ncostfactor8;
  }

  public void setNcurrentaccumsettlenum(UFDouble ncurrentaccumsettlenum) {
    this.m_ncurrentaccumsettlenum = ncurrentaccumsettlenum;
  }

  public void setNcurrentinvoicesettlemny(UFDouble ncurrentsettlemny) {
    this.m_ncurrentinvoicesettlemny = ncurrentsettlemny;
  }

  public void setNcurrentsettlenum(UFDouble ncurrentsettlenum) {
    this.m_ncurrentsettlenum = ncurrentsettlenum;
  }

  public void setNcurrenttotalsettlemoney(UFDouble ncurrentotalsettlemny) {
    this.m_ncurrenttotalsettlemoney = ncurrentotalsettlemny;
  }

  @Override
  public void setNdiscount(UFDouble ndiscount) {
    this.m_ndiscount = ndiscount;
  }

  /**
   * ����������������ⵥȷ��Ӧ����˰�ϼơ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param norigtaxmny <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-27 ����06:47:32
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {//
    if (norigtaxmny == null) {
      return;
    }
  }

  /**
   * ��������������ȷ��Ӧ����ԭ�Ҽۺ�˰���ۡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param norigtaxnetprice <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-27 ����06:21:35
   */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {//
    if (norigtaxnetprice == null) {
      return;
    }
  }

  public void setNpuresettlemny(UFDouble npuresettlemny) {
    this.m_npuresettlemny = npuresettlemny;
  }

  public void setNpuresettlenum(UFDouble npuresettlenum) {
    this.m_npuresettlenum = npuresettlenum;
  }

  public void setNresidualsettlenum(UFDouble nresidualsettlenum) {
    this.m_nresidualsettlenum = nresidualsettlenum;
  }

}
