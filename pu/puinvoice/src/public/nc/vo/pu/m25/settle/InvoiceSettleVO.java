package nc.vo.pu.m25.settle;

import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.pub.MathTool;

public class InvoiceSettleVO extends AbstractDataView implements
    ICostfactorDiscount {

  public static final String BAPFLAG = InvoiceHeaderVO.BAPFLAG;

  public static final String BFEE = InvoiceHeaderVO.BFEE;

  /** ���ұ��� **/
  public static final String CCURRENCYID = InvoiceHeaderVO.CCURRENCYID;
  
  /** ��Ʊ���� **/
  public static final String DBILLDATE = InvoiceHeaderVO.DBILLDATE;
  /**
   * ��Ʊ����
   * 
   * @param dbilldate
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(InvoiceSettleVO.DBILLDATE, dbilldate);
  }
  /**
   * ��Ʊ����
   * 
   * @return
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(InvoiceSettleVO.DBILLDATE);
  }

  /** Դͷ���ݷ�¼��ʶ */
  public static final String CFIRSTBID = InvoiceItemVO.CFIRSTBID;

  /** Դͷ���ݱ�ʶ */
  public static final String CFIRSTID = InvoiceItemVO.CFIRSTID;

  /** Դͷ�������� */
  public static final String CFIRSTTYPECODE = InvoiceItemVO.CFIRSTTYPECODE;

  /** ԭ�ұ��� **/
  public static final String CORIGCURRENCYID = InvoiceHeaderVO.CORIGCURRENCYID;

  public static final String CPRODUCTORID = InvoiceItemVO.CPRODUCTORID;

  public static final String CPROJECTID = InvoiceItemVO.CPROJECTID;

  /** �к� **/
  public static final String CROWNO = InvoiceItemVO.CROWNO;

  /** ��Դ���ݷ�¼��ʶ */
  public static final String CSOURCEBID = InvoiceItemVO.CSOURCEBID;

  /** ��Դ���ݱ�ʶ */
  public static final String CSOURCEID = InvoiceItemVO.CSOURCEID;

  /** ��Դ�������� */
  public static final String CSOURCETYPECODE = InvoiceItemVO.CSOURCETYPECODE;

  /**
   * ��������
   */
  public static final String CTRANTYPEID = "ctrantypeid";

  public static final String CUNITID = InvoiceItemVO.CUNITID;

  /** ��Ʊ���� **/
  public static final String FINVOICETYPE = "finvoicetype";

  // ��Ʊ�����ͣ���Ӧö����InvoiceRowType(0:�����У�1���ۿ��У�2:�����У�3����������)
  public static final String FROWTYPE = InvoiceItemVO.FROWTYPE;

  public static final String NACCUMSETTMNY = InvoiceItemVO.NACCUMSETTMNY;

  public static final String NACCUMSETTNUM = InvoiceItemVO.NACCUMSETTNUM;

  /** �ɽ����� */
  public static final String NCANSETTLEMNY = "ncansettlemny";

  /** �ɽ������� */
  public static final String NCANSETTLENUM = "ncansettlenum";

  /** ���η�Ʊ������ */
  public static final String NCURRENTINVOICESETTLEMNY =
      "ncurrentinvoicesettlemny";

  /** ���ν������� */
  public static final String NCURRENTSETTLENUM = "ncurrentsettlenum";

  public static final String NMNY = InvoiceItemVO.NMNY;

  public static final String NNUM = InvoiceItemVO.NNUM;

  /** ��ԭ����˰������ **/
  public static final String NORIGPRICE = InvoiceItemVO.NORIGPRICE;

  public static final String NPRICE = InvoiceItemVO.NPRICE;

  public static final String NREASONWASTENUM = InvoiceItemVO.NREASONWASTENUM;

  /** ʣ��������� */
  public static final String NRESIDUALSETTLENUM = "nresidualsettlenum";

  public static final String PK_BIZPSN = InvoiceHeaderVO.PK_BIZPSN;

  public static final String PK_DEPT = InvoiceHeaderVO.PK_DEPT;

  public static final String PK_DEPT_V = InvoiceHeaderVO.PK_DEPT_V;

  public static final String PK_GROUP = InvoiceHeaderVO.PK_GROUP;

  public static final String PK_INVOICE = InvoiceHeaderVO.PK_INVOICE;

  public static final String PK_INVOICE_B = InvoiceItemVO.PK_INVOICE_B;

  public static final String PK_MATERIAL = InvoiceItemVO.PK_MATERIAL;

  public static final String PK_ORDER = InvoiceItemVO.PK_ORDER;

  public static final String PK_ORDER_B = InvoiceItemVO.PK_ORDER_B;

  public static final String PK_ORG = InvoiceHeaderVO.PK_ORG;

  public static final String PK_ORG_V = InvoiceHeaderVO.PK_ORG_V;

  public static final String PK_PARENTINVOICE =
      InvoiceHeaderVO.PK_PARENTINVOICE;

  public static final String PK_SRCMATERIAL = InvoiceItemVO.PK_SRCMATERIAL;

  /** �����֯ **/
  public static final String PK_STOCKORG = InvoiceHeaderVO.PK_STOCKORG;

  /** �����֯�汾��Ϣ **/
  public static final String PK_STOCKORG_V = InvoiceHeaderVO.PK_STOCKORG_V;

  public static final String PK_SUPPLIER = InvoiceHeaderVO.PK_SUPPLIER;

  public static final String VBATCHCODE = InvoiceItemVO.VBATCHCODE;

  public static final String VBILLCODE = InvoiceHeaderVO.VBILLCODE;

  /** Դͷ���ݺ� */
  public static final String VFIRSTCODE = InvoiceItemVO.VFIRSTCODE;

  /** Դͷ�����к� */
  public static final String VFIRSTROWNO = InvoiceItemVO.VFIRSTROWNO;

  /** Դͷ�������� */
  public static final String VFIRSTTRANTYPE = InvoiceItemVO.VFIRSTTRANTYPE;

  public static final String VFREE1 = InvoiceItemVO.VFREE1;

  public static final String VFREE10 = InvoiceItemVO.VFREE10;

  public static final String VFREE2 = InvoiceItemVO.VFREE2;

  public static final String VFREE3 = InvoiceItemVO.VFREE3;

  public static final String VFREE4 = InvoiceItemVO.VFREE4;

  public static final String VFREE5 = InvoiceItemVO.VFREE5;

  public static final String VFREE6 = InvoiceItemVO.VFREE6;

  public static final String VFREE7 = InvoiceItemVO.VFREE7;

  public static final String VFREE8 = InvoiceItemVO.VFREE8;

  public static final String VFREE9 = InvoiceItemVO.VFREE9;

  /** ���屸ע */
  public static final String VMEMOB = InvoiceItemVO.VMEMOB;

  /** ��Դ���ݺ� */
  public static final String VSOURCECODE = InvoiceItemVO.VSOURCECODE;

  /** ��Դ�����к� */
  public static final String VSOURCEROWNO = InvoiceItemVO.VSOURCEROWNO;

  /** ��Դ�������� */
  public static final String VSOURCETRANTYPE = InvoiceItemVO.VSOURCETRANTYPE;

  // public static final String CQUALITYLEVELID = InvoiceItemVO.CQUALITYLEVELID;

  public static final String VTRANTYPECODE = InvoiceHeaderVO.VTRANTYPECODE;

  private static final long serialVersionUID = -7340683516996512866L;

  private UFDouble m_costfactor7 = null;

  private UFDouble m_costfactor8 = null;

  // add by liangchen1 631�����ڵ�����̯
  /** ���ڵ������� */
  private UFDouble m_nadjustmny = null;

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

  /** �ۼƱ��η���������ۼƽ������� */
  private UFDouble m_ncurrentaccreasonwastenum = null;

  /** �ۼƱ��η�Ʊ������ */
  private UFDouble m_ncurrentaccuminvoicesettlemny = null;

  /** �ۼƱ��ν������� */
  private UFDouble m_ncurrentaccumsettlenum = null;

  /** �ۼƱ����ܽ����� */
  private UFDouble m_ncurrentaccumtotalsettlemny = null;

  /** ���η�Ʊ������ */
  private UFDouble m_ncurrentinvoicesettlemny = null;

  /** �����ܽ�����=���η�Ʊ������+�ۿ�+�ɱ�Ҫ��1-8 */
  private UFDouble m_ncurrentotalsettlemny = null;

  /** ���ν������� */
  private UFDouble m_ncurrentsettlenum = null;

  /** �ۿ� */
  private UFDouble m_ndiscount = null;

  /** ����ʣ�����������ʵ�ʽ���ʱ���Դ�����Ϊ׼�����������С���� */
  private transient UFDouble m_nresidualsettlenum = null;

  /** �ɱ��� */
  private String m_pk_costregion = null;

  // private transient DataViewMeta m_viewMeta = null;

  @Override
  public void addtoCurrenttotalsettlemoney(UFDouble dValue) {
    this.setNcurrentotalsettlemny(MathTool.add(this.getNcurrentotalsettlemny(),
        dValue));
  }

  /**
   * ���෽����д,���ĳЩû��Ԫ���ݵ��ֶ��Լ�����
   * 
   * @see nc.vo.pubapp.pattern.model.entity.view.AbstractDataView#getAttributeValue(java.lang.String)
   */
  @Override
  public Object getAttributeValue(String name) {
    if (InvoiceSettleVO.NCANSETTLENUM.equals(name)) {// δ��������
      return this.getNcansettlenum();
    }
    else if (InvoiceSettleVO.NCANSETTLEMNY.equals(name)) {// δ������
      return this.getNcansettlemny();
    }
    else if (InvoiceSettleVO.NCURRENTSETTLENUM.equals(name)) {// ���ν�������
      return this.getNcurrentsettlenum();
    }
    else if (InvoiceSettleVO.NRESIDUALSETTLENUM.equals(name)) {// ʣ���������
      return this.getNresidualsettlenum();
    }
    else if (InvoiceSettleVO.NCURRENTINVOICESETTLEMNY.equals(name)) {
      return this.getNcurrentinvoicesettlemny();// ���η�Ʊ������
    }

    return super.getAttributeValue(name);
  }

  /**
   * ��������������ȡ��<b>�Ѵ�Ӧ����־</b>���ԡ�
   * 
   * @param
   * @return UFBoolean
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public UFBoolean getBapflag() {
    return (UFBoolean) this.getAttributeValue(InvoiceSettleVO.BAPFLAG);
  }

  /**
   * ��������������ȡ��<b>�Ƿ���÷�Ʊ</b>���ԡ�
   * 
   * @param
   * @return UFBoolean
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public UFBoolean getBfee() {
    return (UFBoolean) this.getAttributeValue(InvoiceSettleVO.BFEE);
  }

  /** ���ⷢƱ��־ getter ���� */
  public UFBoolean getBvirtual() {
    return (UFBoolean) this.getAttributeValue(InvoiceHeaderVO.BVIRTUAL);
  }

  /**
   * ��������������ȡ��<b>���ұ���</b>���ԡ�
   * 
   * @param
   * @return String
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CCURRENCYID);
  }

  /**
   * ��������������ȡ��<b>Դͷ����������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CFIRSTBID);
  }

  /**
   * ��������������ȡ��<b>Դͷ��������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCfirstid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CFIRSTID);
  }

  /**
   * ��������������ȡ��<b>Դͷ��������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCfirsttypecode() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CFIRSTTYPECODE);
  }

  /**
   * ��������������ȡ��<b>ԭ�ұ���</b>���ԡ�
   * 
   * @param
   * @return String
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CORIGCURRENCYID);
  }

  /**
   * ��������������ȡ��<b>��������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCproductorid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CPRODUCTORID);
  }

  /**
   * ��������������ȡ��<b>��Ŀ</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCprojectid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CPROJECTID);
  }

  /**
   * ��������������ȡ��<b>�к�</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCrowno() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CROWNO);
  }

  /**
   * ��������������ȡ��<b>�ϲ㵥��������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCsourcebid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CSOURCEBID);
  }

  /**
   * ��������������ȡ��<b>�ϲ㵥������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCsourceid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CSOURCEID);
  }

  /**
   * ��������������ȡ��<b>�ϲ㵥������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CSOURCETYPECODE);
  }

  /**
   * ��������PK
   * 
   * @return
   */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CTRANTYPEID);
  }

  /**
   * ��������������ȡ��<b>��������λ</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCunitid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CUNITID);
  }

  /** ��ȡ��Ʊ���� getter ���� */
  public Integer getFinvoicetype() {
    return (Integer) this.getAttributeValue(InvoiceHeaderVO.FINVOICETYPE);
  }

  /** ������ getter ���� */
  public Integer getFrowtype() {
    return (Integer) this.getAttributeValue(InvoiceSettleVO.FROWTYPE);
  }

  @Override
  public IDataViewMeta getMetaData() {
    // if (this.m_viewMeta == null) {
    // Class<? extends ISuperVO> headClazz = InvoiceHeaderVO.class;
    // Class<? extends ISuperVO> itemClazz = InvoiceItemVO.class;
    // // ��ͼ����voΪ�ӱ�vo
    // this.m_viewMeta =
    // new DataViewMeta(itemClazz, new String[] {
    // InvoiceItemVO.VFREE1, InvoiceItemVO.VFREE10, InvoiceItemVO.VFREE2,
    // InvoiceItemVO.VFREE3, InvoiceItemVO.VFREE4, InvoiceItemVO.VFREE5,
    // InvoiceItemVO.VFREE6, InvoiceItemVO.VFREE7, InvoiceItemVO.VFREE8,
    // InvoiceItemVO.VFREE9, InvoiceItemVO.VBATCHCODE,
    // InvoiceItemVO.CROWNO, InvoiceItemVO.PK_COSTSUBJ,
    // InvoiceItemVO.CSOURCEBID, InvoiceItemVO.CSOURCETYPECODE,
    // InvoiceItemVO.VSOURCECODE, InvoiceItemVO.VSOURCEROWNO,
    // InvoiceItemVO.VSOURCETRANTYPE, InvoiceItemVO.CSOURCEID,
    // InvoiceItemVO.VFIRSTROWNO, InvoiceItemVO.VFIRSTTRANTYPE,
    // InvoiceItemVO.CFIRSTID, InvoiceItemVO.CFIRSTBID,
    // InvoiceItemVO.CFIRSTTYPECODE, InvoiceItemVO.VFIRSTCODE,
    // InvoiceItemVO.NACCUMSETTMNY, InvoiceItemVO.NACCUMSETTNUM,
    // InvoiceItemVO.VCHANGERATE, InvoiceItemVO.NNUM, InvoiceItemVO.NMNY,
    // InvoiceItemVO.NPRICE, InvoiceItemVO.NREASONWASTENUM,
    // InvoiceItemVO.PK_INVOICE_B, InvoiceItemVO.PK_MATERIAL,
    // InvoiceItemVO.PK_SRCMATERIAL, InvoiceItemVO.CUNITID,
    // InvoiceItemVO.PK_ORDER, InvoiceItemVO.PK_ORDER_B,
    // InvoiceItemVO.CPRODUCTORID, InvoiceItemVO.CPROJECTID,
    // InvoiceItemVO.PK_STORDOC, InvoiceItemVO.VMEMOB,
    // InvoiceItemVO.NORIGPRICE, InvoiceItemVO.FROWTYPE, InvoiceItemVO.TS,
    // InvoiceItemVO.NCALCOSTMNY
    // });
    //
    // // ��ͼvo��Ҫ��������vo
    // this.m_viewMeta.add(headClazz, new String[] {
    // InvoiceHeaderVO.PK_BIZPSN, InvoiceHeaderVO.BAPFLAG,
    // InvoiceHeaderVO.BVIRTUAL, InvoiceHeaderVO.DBILLDATE,
    // InvoiceHeaderVO.PK_GROUP, InvoiceHeaderVO.PK_DEPT,
    // InvoiceHeaderVO.PK_DEPT_V, InvoiceHeaderVO.BFEE,
    // InvoiceHeaderVO.PK_ORG, InvoiceHeaderVO.PK_ORG_V,
    // InvoiceHeaderVO.PK_INVOICE, InvoiceHeaderVO.PK_PARENTINVOICE,
    // InvoiceHeaderVO.PK_PURCHASEORG, InvoiceHeaderVO.PK_STOCKORG,
    // InvoiceHeaderVO.PK_STOCKORG_V, InvoiceHeaderVO.VBILLCODE,
    // InvoiceHeaderVO.VTRANTYPECODE, InvoiceHeaderVO.CTRANTYPEID,
    // InvoiceHeaderVO.CCURRENCYID, InvoiceHeaderVO.CORIGCURRENCYID,
    // InvoiceHeaderVO.PK_SUPPLIER, InvoiceHeaderVO.TS
    //
    // });
    // // ���ù����ֶ�
    //
    // this.m_viewMeta.addRelation(itemClazz, InvoiceItemVO.PK_INVOICE,
    // headClazz, InvoiceHeaderVO.PK_INVOICE);
    //
    // this.m_viewMeta.setAttributeAliasName(InvoiceHeaderVO.class,
    // InvoiceHeaderVO.TS, "hts");
    // }
    // return this.m_viewMeta;

    return DataViewMetaFactory.getInstance().getDataViewMeta(
        InvoiceSettleVOMeta.class);
  }

  /**
   * ��������������ȡ��<b>�ۼƱ��ҽ�����</b>���ԡ�
   * 
   * @param
   * @return UFDouble
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public UFDouble getNaccumsettmny() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleVO.NACCUMSETTMNY);
  }

  /**
   * ��������������ȡ��<b>�ۼƽ�������</b>���ԡ�
   * 
   * @param
   * @return UFDouble
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public UFDouble getNaccumsettnum() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleVO.NACCUMSETTNUM);
  }

  @Override
  public UFDouble getNadjustmny() {
    return this.m_nadjustmny;
  }

  @Override
  public UFDouble getNallotmoney() {
    return this.getNcurrentinvoicesettlemny();
  }

  @Override
  public UFDouble getNallotnum() {
    return this.getNcurrentsettlenum();
  }

  /** �Ƴɱ���� getter ���� */
  public UFDouble getNcalcostmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NCALCOSTMNY);
  }

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
    return this.m_costfactor7;
  }

  @Override
  public UFDouble getNcostfactor8() {
    return this.m_costfactor8;
  }

  /**
   * �����ۼƺ�����Ľ�������
   * 
   * @return
   */
  public UFDouble getNcurrentaccreasonwastenum() {
    return this.m_ncurrentaccreasonwastenum;
  }

  public UFDouble getNcurrentaccuminvoicesettlemny() {
    return this.m_ncurrentaccuminvoicesettlemny;
  }

  public UFDouble getNcurrentaccumsettlenum() {
    return this.m_ncurrentaccumsettlenum;
  }

  public UFDouble getNcurrentaccumtotalsettlemny() {
    return this.m_ncurrentaccumtotalsettlemny;
  }

  public UFDouble getNcurrentinvoicesettlemny() {
    return this.m_ncurrentinvoicesettlemny;
  }

  public UFDouble getNcurrentotalsettlemny() {
    return this.m_ncurrentotalsettlemny;
  }

  public UFDouble getNcurrentsettlenum() {
    return this.m_ncurrentsettlenum;
  }

  @Override
  public UFDouble getNdiscount() {
    return this.m_ndiscount;
  }

  /**
   * ��������������ȡ��<b>������˰���</b>���ԡ�
   * 
   * @param
   * @return UFDouble
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleVO.NMNY);
  }

  /**
   * ��������������ȡ��<b>��Ʊ����</b>���ԡ�
   * 
   * @param
   * @return UFDouble
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleVO.NNUM);
  }

  /**
   * ��������������ȡ��<b>��ԭ����˰������</b>���ԡ�
   * 
   * @param
   * @return UFDouble
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleVO.NORIGPRICE);
  }

  /**
   * ��������������ȡ��<b>������˰����</b>���ԡ�
   * 
   * @param
   * @return UFDouble
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleVO.NPRICE);
  }

  /**
   * ��������������ȡ��<b>�����������</b>���ԡ�
   * 
   * @param
   * @return UFDouble
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public UFDouble getNreasonwastenum() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleVO.NREASONWASTENUM);
  }

  public UFDouble getNresidualsettlenum() {
    return this.m_nresidualsettlenum;
  }

  /**
   * ��������������ȡ��<b>ҵ��Ա</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public String getPk_bizpsn() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_BIZPSN);
  }

  public String getPk_costregion() {
    return this.m_pk_costregion;
  }

  /**
   * ��������������ȡ��<b>ʹ�ò���</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getPk_dept() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_DEPT);
  }

  public String getPk_dept_v() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_DEPT_V);
  }

  /**
   * ��������������ȡ��<b>��������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_GROUP);
  }

  /**
   * ��������������ȡ��<b>�ɹ���Ʊ����_����</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getPk_invoice() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_INVOICE);
  }

  /**
   * ��������������ȡ��<b>��Ʊ��ʵ������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getPk_invoice_b() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_INVOICE_B);
  }

  /**
   * ��������������ȡ��<b>����</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getPk_material() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_MATERIAL);
  }

  /**
   * ��������������ȡ��<b>�ɹ���������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getPk_order() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_ORDER);
  }

  /**
   * ��������������ȡ��<b>�ɹ�����������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_ORDER_B);
  }

  /**
   * ��������������ȡ��<b>����֯-������֯</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_ORG);
    // return (String)
    // getVO(InvoiceHeaderVO.class).getAttributeValue(PK_FINANCEORG);
  }

  public String getPk_org_v() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_ORG_V);
    // return (String)
    // getVO(InvoiceHeaderVO.class).getAttributeValue(PK_FINANCEORG);
  }

  /**
   * ��������������ȡ��<b>���÷�Ʊ��Ӧ���﷢Ʊ</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public String getPk_parentinvoice() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_PARENTINVOICE);
  }

  @Override
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_SRCMATERIAL);
  }

  /**
   * ��������������ȡ��<b>�����֯</b>���ԡ�
   * 
   * @param
   * @return String
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public String getPk_stockorg() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_STOCKORG);
  }

  /**
   * ��������������ȡ��<b>�����֯�汾</b>���ԡ�
   * 
   * @param
   * @return String
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public String getPk_stockorg_v() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_STOCKORG_V);
  }

  /** �ֿ� getter ���� */
  public String getPk_stordoc() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_STORDOC);
  }

  /**
   * ��������������ȡ��<b>��Ӧ��</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_SUPPLIER);
  }

  /**
   * ��������������ȡ��<b>��Ʊ��</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public String getVbillcode() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VBILLCODE);
  }

  /** ��ͬ�� getter ���� */
  public String getVctcode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VCTCODE);
  }

  /**
   * ��������������ȡ��<b>Դͷ���ݺ�</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFIRSTCODE);
  }

  /**
   * ��������������ȡ��<b>�����ȼ�</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  // public String getCqualitylevelid() {
  // return (String) this.getAttributeValue(CQUALITYLEVELID);
  // }
  /**
   * ��������������ȡ��<b>Դͷ�����к�</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFIRSTROWNO);
  }

  /**
   * ��������������ȡ��<b>Դͷ��������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFIRSTTRANTYPE);
  }

  /**
   * ��������������ȡ��<b>������1</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree1() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE1);
  }

  /**
   * ������������������<b>�����ȼ�</b>���ԡ�
   * 
   * @param String vfree1
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  // public void setCqualitylevelid(String value) {
  // this.setAttributeValue(CQUALITYLEVELID, value);
  // }
  /**
   * ��������������ȡ��<b>������10</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree10() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE10);
  }

  /**
   * ��������������ȡ��<b>������2</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree2() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE2);
  }

  /**
   * ��������������ȡ��<b>������3</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree3() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE3);
  }

  /**
   * ��������������ȡ��<b>������4</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree4() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE4);
  }

  /**
   * ��������������ȡ��<b>������5</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree5() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE5);
  }

  /**
   * ��������������ȡ��<b>������6</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree6() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE6);
  }

  /**
   * ��������������ȡ��<b>������7</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree7() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE7);
  }

  /**
   * ��������������ȡ��<b>������8</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree8() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE8);
  }

  /**
   * ��������������ȡ��<b>������9</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree9() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE9);
  }

  /**
   * ��������������ȡ��<b>�б�ע</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVmemo() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VMEMOB);
  }

  /** ������ getter ���� */
  public String getVordercode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VORDERCODE);
  }

  /**
   * ��������������ȡ��<b>���κ�</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVproducenum() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VBATCHCODE);
  }

  /**
   * ��������������ȡ��<b>�ϲ㵥�ݺ�</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVsourcecode() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VSOURCECODE);
  }

  /**
   * ��������������ȡ��<b>�ϲ㵥���к�</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VSOURCEROWNO);
  }

  /**
   * ��������������ȡ��<b>�ϲ㽻������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVsourcetrantype() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VSOURCETRANTYPE);
  }

  /**
   * ��������������ȡ��<b>��������</b>���ԡ�
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VTRANTYPECODE);
  }

  @Override
  public void setAttributeValue(String name, Object value) {
    if (InvoiceSettleVO.NCURRENTINVOICESETTLEMNY.equals(name)) {
      // ������Ʊ��������÷�Ʊ���ģ����ȡֵ
      this.setNcurrentinvoicesettlemny((UFDouble) value);
    }
    super.setAttributeValue(name, value);
  }

  /**
   * ������������������<b>�Ѵ�Ӧ����־</b>���ԡ�
   * 
   * @param UFBoolean bapflag
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public void setBapflag(UFBoolean bapflag) {
    this.setAttributeValue(InvoiceSettleVO.BAPFLAG, bapflag);
  }

  /**
   * ������������������<b>�Ƿ���÷�Ʊ</b>���ԡ�
   * 
   * @param UFBoolean bchargeinvoice
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public void setBfee(UFBoolean bchargeinvoice) {
    this.setAttributeValue(InvoiceSettleVO.BFEE, bchargeinvoice);
  }

  /** ���ⷢƱ��־ setter ���� */
  public void setBvirtual(UFBoolean bvirtual) {
    this.setAttributeValue(InvoiceHeaderVO.BVIRTUAL, bvirtual);
  }

  /**
   * ������������������<b>ԭ�ұ���</b>���ԡ�
   * 
   * @param String pk_currtype
   * @return
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public void setCcurrencyid(String pk_currtype) {
    this.setAttributeValue(InvoiceSettleVO.CCURRENCYID, pk_currtype);
  }

  /**
   * ������������������<b>Դͷ����������</b>���ԡ�
   * 
   * @param String csourcebillrowid
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(InvoiceSettleVO.CFIRSTBID, cfirstbid);
  }

  /**
   * ������������������<b>Դͷ��������</b>���ԡ�
   * 
   * @param String csourcebillid
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(InvoiceSettleVO.CFIRSTID, cfirstid);
  }

  /**
   * ������������������<b>Դͷ��������</b>���ԡ�
   * 
   * @param String csourcebilltype
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCfirsttypecode(String cfirsttypecode) {
    this.setAttributeValue(InvoiceSettleVO.CFIRSTTYPECODE, cfirsttypecode);
  }

  /**
   * ������������������<b>ԭ�ұ���</b>���ԡ�
   * 
   * @param String pk_currtype
   * @return
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public void setCorigcurrencyid(String pk_currtype) {
    this.setAttributeValue(InvoiceSettleVO.CORIGCURRENCYID, pk_currtype);
  }

  /**
   * ������������������<b>��������</b>���ԡ�
   * 
   * @param String vfree1
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCproductorid(String value) {
    this.setAttributeValue(InvoiceSettleVO.CPRODUCTORID, value);
  }

  /**
   * ������������������<b>��Ŀ</b>���ԡ�
   * 
   * @param String vfree1
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCprojectid(String value) {
    this.setAttributeValue(InvoiceSettleVO.CPROJECTID, value);
  }

  /**
   * ������������������<b>�к�</b>���ԡ�
   * 
   * @param String vrowno
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCrowno(String vrowno) {
    this.setAttributeValue(InvoiceSettleVO.CROWNO, vrowno);
  }

  /**
   * ������������������<b>�ϲ㵥��������</b>���ԡ�
   * 
   * @param String cupsourcebillrowid
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCsourcebid(String csourcebid) {
    this.setAttributeValue(InvoiceSettleVO.CSOURCEBID, csourcebid);
  }

  /**
   * ������������������<b>�ϲ㵥������</b>���ԡ�
   * 
   * @param String cupsourcebillid
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCsourceid(String csourceid) {
    this.setAttributeValue(InvoiceSettleVO.CSOURCEID, csourceid);
  }

  /**
   * ������������������<b>�ϲ㵥������</b>���ԡ�
   * 
   * @param String cupsourcebilltype
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCsourcetypecode(String csourcetypecode) {
    this.setAttributeValue(InvoiceSettleVO.CSOURCETYPECODE, csourcetypecode);
  }

  /**
   * ������������������<b>��������λ</b>���ԡ�
   * 
   * @param String pk_measdoc
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCunitid(String pk_measdoc) {
    this.setAttributeValue(InvoiceSettleVO.CUNITID, pk_measdoc);
  }

  /** ���÷�Ʊ���� setter ���� */
  public void setFinvoicetype(Integer finvoicetype) {
    this.setAttributeValue(InvoiceHeaderVO.FINVOICETYPE, finvoicetype);
  }

  /**
   * ������������������<b>�ۼƱ��ҽ�����</b>���ԡ�
   * 
   * @param UFDouble naccumsettmny
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setNaccumsettmny(UFDouble naccumsettmny) {
    this.setAttributeValue(InvoiceSettleVO.NACCUMSETTMNY, naccumsettmny);
  }

  /**
   * ������������������<b>�ۼƽ�������</b>���ԡ�
   * 
   * @param UFDouble naccumsettnum
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setNaccumsettnum(UFDouble naccumsettnum) {
    this.setAttributeValue(InvoiceSettleVO.NACCUMSETTNUM, naccumsettnum);
  }

  @Override
  public void setNadjustmny(UFDouble nadjustmny) {
    this.m_nadjustmny = nadjustmny;
  }

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
    this.m_costfactor7 = ncostfactor7;
  }

  @Override
  public void setNcostfactor8(UFDouble ncostfactor8) {
    this.m_costfactor8 = ncostfactor8;
  }

  /**
   * �����ۼƺ�����Ľ�������
   * 
   * @param ncurrentaccreasonwastenum
   */
  public void setNcurrentaccreasonwastenum(UFDouble ncurrentaccreasonwastenum) {
    this.m_ncurrentaccreasonwastenum = ncurrentaccreasonwastenum;
  }

  public void setNcurrentaccuminvoicesettlemny(
      UFDouble ncurrentaccuminvoicesettlemny) {
    this.m_ncurrentaccuminvoicesettlemny = ncurrentaccuminvoicesettlemny;
  }

  public void setNcurrentaccumsettlenum(UFDouble ncurrentaccumsettlenum) {
    this.m_ncurrentaccumsettlenum = ncurrentaccumsettlenum;
  }

  public void setNcurrentaccumtotalsettlemny(
      UFDouble ncurrentaccumtotalsettlemny) {
    this.m_ncurrentaccumtotalsettlemny = ncurrentaccumtotalsettlemny;
  }

  public void setNcurrentinvoicesettlemny(UFDouble ncurrentsettlemny) {
    this.m_ncurrentinvoicesettlemny = ncurrentsettlemny;
  }

  public void setNcurrentotalsettlemny(UFDouble ncurrentotalsettlemny) {
    this.m_ncurrentotalsettlemny = ncurrentotalsettlemny;
  }

  public void setNcurrentsettlenum(UFDouble ncurrentsettlenum) {
    this.m_ncurrentsettlenum = ncurrentsettlenum;
  }

  @Override
  public void setNdiscount(UFDouble ndiscount) {
    this.m_ndiscount = ndiscount;
  }

  /**
   * ������������������<b>������˰���</b>���ԡ�
   * 
   * @param UFDouble nmoney
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setNmny(UFDouble nmoney) {
    this.setAttributeValue(InvoiceSettleVO.NMNY, nmoney);
  }

  /**
   * ������������������<b>��Ʊ����</b>���ԡ�
   * 
   * @param UFDouble ninvoicenum
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setNnum(UFDouble ninvoicenum) {
    this.setAttributeValue(InvoiceSettleVO.NNUM, ninvoicenum);
  }

  /**
   * ������������������<b>��ԭ����˰������</b>���ԡ�
   * 
   * @param UFDouble ninvoicenum
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(InvoiceSettleVO.NORIGPRICE, norigprice);
  }

  /**
   * ������������������<b>������˰����</b>���ԡ�
   * 
   * @param UFDouble nnetprice
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setNprice(UFDouble nnetprice) {
    this.setAttributeValue(InvoiceSettleVO.NPRICE, nnetprice);
  }

  /**
   * ������������������<b>�����������</b>���ԡ�
   * 
   * @param UFDouble nreasonwastenum
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setNreasonwastenum(UFDouble nreasonwastenum) {
    this.setAttributeValue(InvoiceSettleVO.NREASONWASTENUM, nreasonwastenum);
  }

  public void setNresidualsettlenum(UFDouble nresidualsettlenum) {
    this.m_nresidualsettlenum = nresidualsettlenum;
  }

  /**
   * ������������������<b>ҵ��Ա</b>���ԡ�
   * 
   * @param String cbizpsn
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public void setPk_bizpsn(String cbizpsn) {
    this.setAttributeValue(InvoiceSettleVO.PK_BIZPSN, cbizpsn);
  }

  public void setPk_costregion(String pk_costregion) {
    this.m_pk_costregion = pk_costregion;
  }

  /**
   * ������������������<b>ʹ�ò���</b>���ԡ�
   * 
   * @param String pk_dept
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(InvoiceSettleVO.PK_DEPT, pk_dept);
  }

  /**
   * ������������������<b>��������</b>���ԡ�
   * 
   * @param String pk_group
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(InvoiceSettleVO.PK_GROUP, pk_group);
  }

  /**
   * ������������������<b>�ɹ���Ʊ����_����</b>���ԡ�
   * 
   * @param String pk_invoice
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setPk_invoice(String pk_invoice) {
    this.setAttributeValue(InvoiceSettleVO.PK_INVOICE, pk_invoice);
  }

  /**
   * ������������������<b>��Ʊ��ʵ������</b>���ԡ�
   * 
   * @param String pk_invoice_b
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setPk_invoice_b(String pk_invoice_b) {
    this.setAttributeValue(InvoiceSettleVO.PK_INVOICE_B, pk_invoice_b);
  }

  /**
   * ������������������<b>����</b>���ԡ�
   * 
   * @param String pk_material
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(InvoiceSettleVO.PK_MATERIAL, pk_material);
  }

  /**
   * ������������������<b>�ɹ���������</b>���ԡ�
   * 
   * @param String pk_order
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(InvoiceSettleVO.PK_ORDER, pk_order);
  }

  /**
   * ������������������<b>�ɹ�����������</b>���ԡ�
   * 
   * @param String pk_order_b
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setPk_order_b(String pk_order_b) {
    this.setAttributeValue(InvoiceSettleVO.PK_ORDER_B, pk_order_b);
  }

  /**
   * ������������������<b>����֯-������֯</b>���ԡ�
   * 
   * @param String pk_financeorg
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(InvoiceSettleVO.PK_ORG, pk_org);
  }

  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(InvoiceSettleVO.PK_ORG_V, pk_org_v);
  }

  /**
   * ������������������<b>���÷�Ʊ��Ӧ���﷢Ʊ</b>���ԡ�
   * 
   * @param String pk_parentinvoice
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public void setPk_parentinvoice(String pk_parentinvoice) {
    this.setAttributeValue(InvoiceSettleVO.PK_PARENTINVOICE, pk_parentinvoice);
  }

  public void setPk_srcmaterial(String pk_material) {
    this.setAttributeValue(InvoiceSettleVO.PK_SRCMATERIAL, pk_material);
  }

  /**
   * ������������������<b>�����֯</b>���ԡ�
   * 
   * @param String pk_currtype
   * @return
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public void setPk_stockorg(String pk_stock) {
    this.setAttributeValue(InvoiceSettleVO.PK_STOCKORG, pk_stock);
  }

  /**
   * ������������������<b>�����֯</b>���ԡ�
   * 
   * @param String pk_currtype
   * @return
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public void setPk_stockorg_v(String pk_stock_v) {
    this.setAttributeValue(InvoiceSettleVO.PK_STOCKORG_V, pk_stock_v);
  }

  /** �ֿ� setter ���� */
  public void setPk_stordoc(String pk_stordoc) {
    this.setAttributeValue(InvoiceItemVO.PK_STORDOC, pk_stordoc);
  }

  /**
   * ������������������<b>��Ӧ��</b>���ԡ�
   * 
   * @param String pk_supplier
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(InvoiceSettleVO.PK_SUPPLIER, pk_supplier);
  }

  /**
   * ������������������<b>��Ʊ��</b>���ԡ�
   * 
   * @param String vinvoicecode
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public void setVbillcode(String vinvoicecode) {
    this.setAttributeValue(InvoiceSettleVO.VBILLCODE, vinvoicecode);
  }

  /**
   * ������������������<b>Դͷ���ݺ�</b>���ԡ�
   * 
   * @param String vsourcebillcode
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(InvoiceSettleVO.VFIRSTCODE, vfirstcode);
  }

  /**
   * ������������������<b>Դͷ�����к�</b>���ԡ�
   * 
   * @param String vsourcebillrowno
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfirstrowno(String vsourcebillrowno) {
    this.setAttributeValue(InvoiceSettleVO.VFIRSTROWNO, vsourcebillrowno);
  }

  /**
   * ������������������<b>Դͷ��������</b>���ԡ�
   * 
   * @param String vsourcetrantype
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(InvoiceSettleVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /**
   * ������������������<b>������1</b>���ԡ�
   * 
   * @param String vfree1
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(InvoiceSettleVO.VFREE1, vfree1);
  }

  /**
   * ������������������<b>������10</b>���ԡ�
   * 
   * @param String vfree10
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(InvoiceSettleVO.VFREE10, vfree10);
  }

  /**
   * ������������������<b>������2</b>���ԡ�
   * 
   * @param String vfree2
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(InvoiceSettleVO.VFREE2, vfree2);
  }

  /**
   * ������������������<b>������3</b>���ԡ�
   * 
   * @param String vfree3
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(InvoiceSettleVO.VFREE3, vfree3);
  }

  /**
   * ������������������<b>������4</b>���ԡ�
   * 
   * @param String vfree4
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(InvoiceSettleVO.VFREE4, vfree4);
  }

  /**
   * ������������������<b>������5</b>���ԡ�
   * 
   * @param String vfree5
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(InvoiceSettleVO.VFREE5, vfree5);
  }

  /**
   * ������������������<b>������6</b>���ԡ�
   * 
   * @param String vfree6
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(InvoiceSettleVO.VFREE6, vfree6);
  }

  /**
   * ������������������<b>������7</b>���ԡ�
   * 
   * @param String vfree7
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(InvoiceSettleVO.VFREE7, vfree7);
  }

  /**
   * ������������������<b>������8</b>���ԡ�
   * 
   * @param String vfree8
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(InvoiceSettleVO.VFREE8, vfree8);
  }

  /**
   * ������������������<b>������9</b>���ԡ�
   * 
   * @param String vfree9
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(InvoiceSettleVO.VFREE9, vfree9);
  }

  /**
   * ������������������<b>�б�ע��</b>���ԡ�
   * 
   * @param String vupsourcetrantype
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVmemo(String vbodymemo) {
    this.setAttributeValue(InvoiceSettleVO.VMEMOB, vbodymemo);
  }

  /**
   * ������������������<b>���κ�</b>���ԡ�
   * 
   * @param String vproducenum
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVproducenum(String vproducenum) {
    this.setAttributeValue(InvoiceSettleVO.VBATCHCODE, vproducenum);
  }

  /**
   * ������������������<b>�ϲ㵥�ݺ�</b>���ԡ�
   * 
   * @param String vupsourcebillcode
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVsourcecode(String vsourcecode) {
    this.setAttributeValue(InvoiceSettleVO.VSOURCECODE, vsourcecode);
  }

  /**
   * ������������������<b>�ϲ㵥���к�</b>���ԡ�
   * 
   * @param String vupsourcebillrowno
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVsourcerowno(String vsourcerowno) {
    this.setAttributeValue(InvoiceSettleVO.VSOURCEROWNO, vsourcerowno);
  }

  /**
   * ������������������<b>�ϲ㽻������</b>���ԡ�
   * 
   * @param String vupsourcetrantype
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVsourcetrantype(String vsourcetrantype) {
    this.setAttributeValue(InvoiceSettleVO.VSOURCETRANTYPE, vsourcetrantype);
  }

  /**
   * ������������������<b>��������</b>���ԡ�
   * 
   * @param String vtrantypecode
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(InvoiceSettleVO.VTRANTYPECODE, vtrantypecode);
  }
}
