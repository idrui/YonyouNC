package nc.vo.pu.m27.entity;

import java.util.HashSet;
import java.util.Set;

import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pu.m4203.entity.SubcontinFIHeaderVO;
import nc.vo.pu.m4203.entity.SubcontinFIItemVO;
import nc.vo.pub.BeanHelper;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * ί�мӹ���Ľ���VO
 * 
 * @since 6.0
 * @version 2011-1-21 ����03:17:23
 * @author zhaoyha
 */
public class SubcontInSettleVO extends StockSettleVO {

  /** ����get����ȡֵ **/
  public static Set<String> beanAttrSet = new HashSet<String>();

  private static final long serialVersionUID = 3963387912098305169L;

  static {
    SubcontInSettleVO.beanAttrSet.add(StockSettleVO.NESTMNY);
    SubcontInSettleVO.beanAttrSet.add(StockSettleVO.NESTNUM);
    SubcontInSettleVO.beanAttrSet.add(StockSettleVO.NESTPRICE);
    SubcontInSettleVO.beanAttrSet.add(StockSettleVO.NACCESTCOSTSTTLNUM);
    SubcontInSettleVO.beanAttrSet.add(StockSettleVO.NNETPRICE);
    SubcontInSettleVO.beanAttrSet.add(StockSettleVO.NORIGPRICE);
  }

  @Override
  public Object getAttributeValue(String name) {
    if (SubcontInSettleVO.beanAttrSet.contains(name)) {
      return BeanHelper.getProperty(this, name);
    }
    return super.getAttributeValue(name);
  }

  @Override
  public UFBoolean getBaffectcost() {
    return (UFBoolean) this.getAttributeValue(SubcontinFIItemVO.BAFFECTCOST);
  }

  @Override
  public UFBoolean getBaffectpciacost() {
    // ���ί����ⵥ��Ӱ����������
    return UFBoolean.FALSE;
  }

  @Override
  public UFBoolean getBnormpur() {
    return UFBoolean.TRUE;
  }

  /** �Ƿ������� getter ���� */
  @Override
  public UFBoolean getBsettlefinish() {
    return (UFBoolean) this.getAttributeValue(SubcontinFIItemVO.BSETTLEFINISH);
  }

  /** ��λ getter ���� */
  @Override
  public String getCastunitid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CASTUNITID);
  }

  @Override
  public String getCbilltypecode() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.CBILLTYPECODE);
  }

  /** ��λ�� getter ���� */
  @Override
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CCURRENCYID);
  }

  /** Դͷ���ݱ��� getter ���� */
  @Override
  public String getCfirstbid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CFIRSTBID);
  }

  /** Դͷ���ݱ�ͷ getter ���� */
  @Override
  public String getCfirstid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CFIRSTID);
  }

  /** Դͷ�������� getter ���� */
  @Override
  public String getCfirsttypecode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CFIRSTTYPECODE);
  }

  /** ί�мӹ��������ҵ�񣬷��ر�λ�� */
  @Override
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CCURRENCYID);
  }

  /** �������� getter ���� */
  @Override
  public String getCproductorid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CPRODUCTORID);
  }

  /** ��Ŀ getter ���� */
  @Override
  public String getCprojectid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CPROJECTID);
  }

  /** ��Դ���ݱ��� getter ���� */
  @Override
  public String getCsourcebid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CSOURCEBID);
  }

  /** ��Դ���ݱ�ͷ getter ���� */
  @Override
  public String getCsourceid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CSOURCEID);
  }

  /** ��Դ�������� getter ���� */
  @Override
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CSOURCETYPECODE);
  }

  @Override
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(StockSettleVO.CTRANTYPEID);
  }

  /** ����λ getter ���� */
  @Override
  public String getCunitid() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.CUNITID);
  }

  /**
   * �������
   * 
   * @return
   */
  @Override
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SubcontinFIItemVO.DBIZDATE);
  }

  @Override
  public Integer getFdirtoaptype() {
    return EnumToAPFlag.NotToAP.toInteger();
  }

  @Override
  public Integer getFdirtoiatype() {
    return (Integer) this.getAttributeValue(SubcontinFIItemVO.FTOIAFLAG);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        SubcontInSettleVOMeta.class);
    // if (this.m_viewMeta == null) {
    // Class<? extends ISuperVO> headClazz = SubcontinFIHeaderVO.class;
    // Class<? extends ISuperVO> itemClazz = SubcontinFIItemVO.class;
    // // ��ͼ����voΪ�ӱ�vo
    // this.m_viewMeta =
    // new DataViewMeta(itemClazz, new String[] {
    // SubcontinFIItemVO.BSETTLEFINISH, SubcontinFIItemVO.CFIRSTBID,
    // SubcontinFIItemVO.CFIRSTID, SubcontinFIItemVO.CFIRSTTYPECODE,
    // SubcontinFIItemVO.PK_STOCKPS, SubcontinFIItemVO.CSOURCEBID,
    // SubcontinFIItemVO.CSOURCEID, SubcontinFIItemVO.CSOURCETYPECODE,
    // SubcontinFIItemVO.FTOIAFLAG, SubcontinFIItemVO.BAFFECTCOST,
    // SubcontinFIItemVO.NACCUMSETTLENUM, SubcontinFIItemVO.NINNUM,
    // SubcontinFIItemVO.NINASSISTNUM,
    // SubcontinFIItemVO.NACCESTCOSTWASHMNY,
    // SubcontinFIItemVO.PK_COSTREGION, SubcontinFIItemVO.CCURRENCYID,
    // SubcontinFIItemVO.PK_GROUP, SubcontinFIItemVO.PK_MATERIAL,
    // SubcontinFIItemVO.CASTUNITID, SubcontinFIItemVO.CUNITID,
    // SubcontinFIItemVO.PK_SRCMATERIAL, SubcontinFIItemVO.PK_ORDER,
    // SubcontinFIItemVO.PK_ORDER_B, SubcontinFIItemVO.PK_STOCKPS_B,
    // SubcontinFIItemVO.VSOURCECODE, SubcontinFIItemVO.VSOURCEROWNO,
    // SubcontinFIItemVO.VSOURCETRANTYPE, SubcontinFIItemVO.VFIRSTROWNO,
    // SubcontinFIItemVO.VFIRSTTRANTYPE, SubcontinFIItemVO.VFIRSTCODE,
    // SubcontinFIItemVO.PK_SUPPLIER, SubcontinFIItemVO.VFREE1,
    // SubcontinFIItemVO.VFREE2, SubcontinFIItemVO.VFREE3,
    // SubcontinFIItemVO.VFREE4, SubcontinFIItemVO.VFREE5,
    // SubcontinFIItemVO.VFREE6, SubcontinFIItemVO.VFREE7,
    // SubcontinFIItemVO.VFREE8, SubcontinFIItemVO.VFREE9,
    // SubcontinFIItemVO.VFREE10, SubcontinFIItemVO.VNOTEBODY,
    // SubcontinFIItemVO.VBATCHCODE, SubcontinFIItemVO.VCHANGERATE,
    // SubcontinFIItemVO.NCOSTMNY, SubcontinFIItemVO.NCOSTPRICE,
    // SubcontinFIItemVO.PK_FINANCEORG, SubcontinFIItemVO.PK_FINANCEORG_V,
    // SubcontinFIItemVO.PK_APFINANCEORG,
    // SubcontinFIItemVO.PK_APFINANCEORG_V, SubcontinFIItemVO.TS,
    // SubcontinFIItemVO.CPROJECTID, SubcontinFIItemVO.CPRODUCTORID,
    // SubcontinFIItemVO.NACCGOODSSETTLEMNY
    // });
    // // ��ͼvo��Ҫ��������vo
    // this.m_viewMeta.add(headClazz, new String[] {
    // SubcontinFIHeaderVO.PK_BUSITYPE, SubcontinFIHeaderVO.PK_GROUP,
    // SubcontinFIHeaderVO.PK_ORG, SubcontinFIHeaderVO.PK_ORG_V,
    // SubcontinFIHeaderVO.PK_STOCKPS, SubcontinFIHeaderVO.PK_STORDOC,
    // SubcontinFIHeaderVO.VBILLCODE, SubcontinFIHeaderVO.CBILLTYPECODE,
    // SubcontinFIHeaderVO.VTRANTYPECODE, SubcontinFIHeaderVO.CTRANTYPEID,
    // SubcontinFIHeaderVO.PK_DEPT, SubcontinFIHeaderVO.PK_DEPT_V,
    // SubcontinFIHeaderVO.PK_PSNDOC, SubcontinFIHeaderVO.TS
    //
    // });
    // // ���ù����ֶ�
    // this.m_viewMeta.addRelation(itemClazz, SubcontinFIItemVO.PK_STOCKPS,
    // headClazz, SubcontinFIHeaderVO.PK_STOCKPS);
    // }
    // return this.m_viewMeta;

  }

  /**
   * ί�мӹ��뼴Ϊ�ۼƽ������������ݹ�����㣩
   */
  @Override
  public UFDouble getNaccestcoststtlnum() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NACCUMSETTLENUM);
  }

  @Override
  public UFDouble getNaccestcostwashmny() {
    return (UFDouble) this
        .getAttributeValue(SubcontinFIItemVO.NACCESTCOSTWASHMNY);
  }

  @Override
  public UFDouble getNaccgoodssettlemny() {
    return (UFDouble) this.getAttributeValue(StockSettleVO.NACCGOODSSETTLEMNY);
  }

  @Override
  public UFDouble getNaccpreeststtlmny() {
    return UFDouble.ZERO_DBL;
  }

  @Override
  public UFDouble getNacctocostadjstmny() {
    return UFDouble.ZERO_DBL;
  }

  @Override
  public UFDouble getNaccumsettlenum() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NACCUMSETTLENUM);
  }

  @Override
  public UFDouble getNaccurevcostnum() {
    return UFDouble.ZERO_DBL;
  }

  @Override
  public UFDouble getNcalcostmny() {
    // wuxla v61
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NCOSTMNY);
  }

  @Override
  public UFDouble getNcalcostprice() {
    // wuxla v61
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NCOSTPRICE);
  }

  @Override
  public UFDouble getNestcalcostmny() {
    // wuxla v61
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NCOSTMNY);
  }

  @Override
  public UFDouble getNestcalcostprice() {
    // wuxla v61
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NCOSTPRICE);
  }

  /**
   * ί�мӹ��뼴Ϊ�ݹ��ӹ���
   */
  @Override
  public UFDouble getNestmny() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NCOSTMNY);
  }

  /**
   * ί�мӹ��뼴Ϊʵ��������
   */
  @Override
  public UFDouble getNestnum() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NINNUM);
  }

  /**
   * ί�мӹ��뼴Ϊ���ɱ���
   */
  @Override
  public UFDouble getNestprice() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NCOSTPRICE);
  }

  @Override
  public UFDouble getNinassistnum() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NINASSISTNUM);
  }

  @Override
  public UFDouble getNinnum() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NINNUM);
  }

  @Override
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NCOSTMNY);
  }

  @Override
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NCOSTPRICE);
  }

  @Override
  public UFDouble getNorignetprice() {
    // wuxla v61
    return (UFDouble) this.getAttributeValue(SubcontinFIItemVO.NCOSTPRICE);
  }

  @Override
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_BATCHCODE);
  }

  @Override
  public String getPk_costregion() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_COSTREGION);
  }

  @Override
  public String getPk_dept() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_DEPT);
  }

  @Override
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_DEPT_V);
  }

  @Override
  public String getPk_dtransalebid() {
    return null;
  }

  @Override
  public String getPk_dtransaleid() {
    return null;
  }

  @Override
  public String getPk_financeorg() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_FINANCEORG);
  }

  @Override
  public String getPk_group() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_GROUP);
  }

  @Override
  public String getPk_material() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_MATERIAL);
  }

  @Override
  public String getPk_order() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_ORDER);
  }

  @Override
  public String getPk_order_b() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_ORDER_B);
  }

  @Override
  public String getPk_org() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_ORG);
  }

  @Override
  public String getPk_org_v() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_ORG_V);
  }

  @Override
  public String getPk_psndoc() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_PSNDOC);
  }

  @Override
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_SRCMATERIAL);
  }

  @Override
  public String getPk_stockps() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.PK_STOCKPS);
  }

  @Override
  public String getPk_stockps_b() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_STOCKPS_B);
  }

  @Override
  public String getPk_supplier() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.PK_SUPPLIER);
  }

  @Override
  public String getVbatchcode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VBATCHCODE);
  }

  @Override
  public String getVbillcode() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VBILLCODE);
  }

  @Override
  public String getVchangerate() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VCHANGERATE);
  }

  /** �ɹ���ͬ�� getter ���� */
  @Override
  public String getVctcode() {
    return null;
  }

  @Override
  public String getVfirstcode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFIRSTCODE);
  }

  @Override
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFIRSTROWNO);
  }

  @Override
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFIRSTTRANTYPE);
  }

  /** ���ɸ�������1 getter ���� */
  @Override
  public String getVfree1() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE1);
  }

  /** ���ɸ�������10 getter ���� */
  @Override
  public String getVfree10() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE10);
  }

  /** ���ɸ�������2 getter ���� */
  @Override
  public String getVfree2() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE2);
  }

  /** ���ɸ�������3 getter ���� */
  @Override
  public String getVfree3() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE3);
  }

  /** ���ɸ�������4 getter ���� */
  @Override
  public String getVfree4() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE4);
  }

  /** ���ɸ�������5 getter ���� */
  @Override
  public String getVfree5() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE5);
  }

  /** ���ɸ�������6 getter ���� */
  @Override
  public String getVfree6() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE6);
  }

  /** ���ɸ�������7 getter ���� */
  @Override
  public String getVfree7() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE7);
  }

  /** ���ɸ�������8 getter ���� */
  @Override
  public String getVfree8() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE8);
  }

  /** ���ɸ�������9 getter ���� */
  @Override
  public String getVfree9() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VFREE9);
  }

  /** �б�ע getter ���� */
  @Override
  public String getVnotebody() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VNOTEBODY);
  }

  /** ������ getter ���� */
  @Override
  public String getVordercode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VORDERCODE);
  }

  /** ������������ getter ���� */
  @Override
  public String getVordertrantypecode() {
    return (String) this
        .getAttributeValue(SubcontinFIItemVO.VORDERTRANTYPECODE);
  }

  /** ��Դ���ݺ� getter ���� */
  @Override
  public String getVsourcecode() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VSOURCECODE);
  }

  /** ��Դ�����к� getter ���� */
  @Override
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VSOURCEROWNO);
  }

  /** ��Դ�������� getter ���� */
  @Override
  public String getVsourcetrantype() {
    return (String) this.getAttributeValue(SubcontinFIItemVO.VSOURCETRANTYPE);
  }

  @Override
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(SubcontinFIHeaderVO.VTRANTYPECODE);
  }

  @Override
  public void setBaffectcost(UFBoolean flag) {
    this.setAttributeValue(SubcontinFIItemVO.BAFFECTCOST, flag);
  }

  @Override
  public void setBaffectpciacost(UFBoolean flag) {
  }

  /**
   * �������
   * 
   * @param dbilldate
   */
  @Override
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SubcontinFIItemVO.DBIZDATE, dbilldate);
  }

  @Override
  public void setNaccestcostwashmny(UFDouble value) {
    this.setAttributeValue(SubcontinFIItemVO.NACCESTCOSTWASHMNY, value);

  }

  @Override
  public void setNacctocostadjstmny(UFDouble value) {
    // ί�мӹ���û��ֱ��ȷ�ϳɱ���Ӧ��
  }

}
