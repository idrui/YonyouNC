/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-26 ����06:38:43
 */
package nc.vo.pu.m20.entity;

import nc.vo.pu.m21.entity.OrderItemVO;
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
 * <b>�빺������VO</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-26 ����06:38:43
 */
public class PraybillItemVO extends SuperVO {
  /** �ɹ���֯�ɱ༭ **/
  public static final String BCANPURCHASEORGEDIT = "bcanpurchaseorgedit";

  /** �Ƿ�̶������� **/
  public static final String BFIXEDRATE = "bfixedrate";

  /** �Ѱ��� **/
  public static final String BISARRANGE = "bisarrange";

  /** �������������� */
  public static final String BISGENSAORDER = "bisgensaorder";

  /** �������������� **/
  public static final String BPUBLISHTOEC = "bpublishtoec";

  /** �йر� **/
  public static final String BROWCLOSE = "browclose";

  /** �ͻ� */
  public static final String CASSCUSTID = "casscustid";

  /** ��λ **/
  public static final String CASTUNITID = "castunitid";

  /** ������ **/
  public static final String CFFILEID = "cffileid";

  /** Դͷ���ݷ�¼��ʶ **/
  public static final String CFIRSTBID = "cfirstbid";

  /** Դͷ���ݱ�ʶ **/
  public static final String CFIRSTID = "cfirstid";

  /** Դͷ�������� **/
  public static final String CFIRSTTYPECODE = "cfirsttypecode";

  /** �������� **/
  public static final String CORDERTRANTYPECODE = "cordertrantypecode";

  /** �������� **/
  public static final String CPRODUCTORID = "cproductorid";

  /** ��Ŀ **/
  public static final String CPROJECTID = "cprojectid";

  /** ��Ŀ���� */
  public static final String CPROJECTTASKID = "cprojecttaskid";

  /** �к� **/
  public static final String CROWNO = "crowno";

  /** ��Դ���ݷ�¼��ʶ **/
  public static final String CSOURCEBID = "csourcebid";

  /** ��Դ���ݱ�ʶ **/
  public static final String CSOURCEID = "csourceid";

  /** ��Դ�������� **/
  public static final String CSOURCETYPECODE = "csourcetypecode";

  /** ��������λ **/
  public static final String CUNITID = "cunitid";

  /** �빺���� **/
  public static final String DBILLDATE = "dbilldate";

  /** �������� **/
  public static final String DREQDATE = "dreqdate";

  /** ���鶩������ **/
  public static final String DSUGGESTDATE = "dsuggestdate";

  /** �ۼƶ������� **/
  public static final String NACCUMULATENUM = "naccumulatenum";

  /** ���� **/
  public static final String NASTNUM = "nastnum";

  /** ���ɺ�ͬ���� **/
  public static final String NGENCT = "ngenct";

  /** ������ **/
  public static final String NNUM = "nnum";

  /** ���ɼ۸����������� **/
  public static final String NPRICEAUDITBILL = "npriceauditbill";

  /** ����ѯ���۵����� **/
  public static final String NQUOTEBILL = "nquotebill";

  /** ���Ҽ�˰�ϼ� **/
  public static final String NTAXMNY = "ntaxmny";

  /** �����Һ�˰���� **/
  public static final String NTAXPRICE = "ntaxprice";

  /** ���ε��� **/
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** �ɹ�Ա **/
  public static final String PK_EMPLOYEE = "pk_employee";

  /** �������� **/
  public static final String PK_GROUP = "pk_group";

  /** ����(VID) **/
  public static final String PK_MATERIAL = "pk_material";

  /** �����֯ **/
  public static final String PK_ORG = "pk_org";

  /** �����֯�汾 **/
  public static final String PK_ORG_V = "pk_org_v";

  /** �빺������_���� **/
  public static final String PK_PRAYBILL = "pk_praybill";

  /** �빺����ID **/
  public static final String PK_PRAYBILL_B = "pk_praybill_b";

  /** ��Ʒ **/
  public static final String PK_PRODUCT = "pk_product";

  /** ��Ʒ�汾 **/
  public static final String PK_PRODUCT_V = "pk_product_v";

  /** �ɹ���֯ **/
  public static final String PK_PURCHASEORG = "pk_purchaseorg";

  /** �ɹ���֯�汾 **/
  public static final String PK_PURCHASEORG_V = "pk_purchaseorg_v";

  /** ������ **/
  public static final String PK_REQDEPT = "pk_reqdept";

  /** �����Ű汾 **/
  public static final String PK_REQDEPT_V = "pk_reqdept_v";

  /** ԭʼ��������֯���°汾 */
  public static final String PK_REQSTOORG = "pk_reqstoorg";

  /** ԭʼ��������֯ */
  public static final String PK_REQSTOORG_V = "pk_reqstoorg_v";

  /** ����ֿ� **/
  public static final String PK_REQSTOR = "pk_reqstor";

  /** ����(OID) **/
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** ���鹩Ӧ�� **/
  public static final String PK_SUGGESTSUPPLIER = "pk_suggestsupplier";

  /** ��Դ������TS */
  public static final String SOURCEBTS = "sourcebts";

  /** ��Դ����TS */
  public static final String SOURCETS = "sourcets";

  /** ʱ��� **/
  public static final String TS = "ts";

  /** ���κ� **/
  public static final String VBATCHCODE = "vbatchcode";

  /** �Զ�����1 **/
  public static final String VBDEF1 = "vbdef1";

  /** �Զ�����10 **/
  public static final String VBDEF10 = "vbdef10";

  /** �Զ�����11 **/
  public static final String VBDEF11 = "vbdef11";

  /** �Զ�����12 **/
  public static final String VBDEF12 = "vbdef12";

  /** �Զ�����13 **/
  public static final String VBDEF13 = "vbdef13";

  /** �Զ�����14 **/
  public static final String VBDEF14 = "vbdef14";

  /** �Զ�����15 **/
  public static final String VBDEF15 = "vbdef15";

  /** �Զ�����16 **/
  public static final String VBDEF16 = "vbdef16";

  /** �Զ�����17 **/
  public static final String VBDEF17 = "vbdef17";

  /** �Զ�����18 **/
  public static final String VBDEF18 = "vbdef18";

  /** �Զ�����19 **/
  public static final String VBDEF19 = "vbdef19";

  /** �Զ�����2 **/
  public static final String VBDEF2 = "vbdef2";

  /** �Զ�����20 **/
  public static final String VBDEF20 = "vbdef20";

  /** �Զ�����3 **/
  public static final String VBDEF3 = "vbdef3";

  /** �Զ�����4 **/
  public static final String VBDEF4 = "vbdef4";

  /** �Զ�����5 **/
  public static final String VBDEF5 = "vbdef5";

  /** �Զ�����6 **/
  public static final String VBDEF6 = "vbdef6";

  /** �Զ�����7 **/
  public static final String VBDEF7 = "vbdef7";

  /** �Զ�����8 **/
  public static final String VBDEF8 = "vbdef8";

  /** �Զ�����9 **/
  public static final String VBDEF9 = "vbdef9";

  /** ��ע **/
  public static final String VBMEMO = "vbmemo";

  /** ������ **/
  public static final String VCHANGERATE = "vchangerate";

  /** Դͷ���ݺ� **/
  public static final String VFIRSTCODE = "vfirstcode";

  /** Դͷ�����к� **/
  public static final String VFIRSTROWNO = "vfirstrowno";

  /** Դͷ�������� **/
  public static final String VFIRSTTRANTYPE = "vfirsttrantype";

  /** ���ɸ�������1 **/
  public static final String VFREE1 = "vfree1";

  /** ���ɸ�������10 **/
  public static final String VFREE10 = "vfree10";

  /** ���ɸ�������2 **/
  public static final String VFREE2 = "vfree2";

  /** ���ɸ�������3 **/
  public static final String VFREE3 = "vfree3";

  /** ���ɸ�������4 **/
  public static final String VFREE4 = "vfree4";

  /** ���ɸ�������5 **/
  public static final String VFREE5 = "vfree5";

  /** ���ɸ�������6 **/
  public static final String VFREE6 = "vfree6";

  /** ���ɸ�������7 **/
  public static final String VFREE7 = "vfree7";

  /** ���ɸ�������8 **/
  public static final String VFREE8 = "vfree8";

  /** ���ɸ�������9 **/
  public static final String VFREE9 = "vfree9";

  /** ��Դ���ݺ� **/
  public static final String VSOURCECODE = "vsourcecode";

  /** ��Դ�����к� **/
  public static final String VSOURCEROWNO = "vsourcerowno";

  /** ��Դ�������� **/
  public static final String VSRCTRANTYPECODE = "vsrctrantypecode";

  private static final long serialVersionUID = -3432997440833423530L;
  /**
   * �������������ֶ�  modified by wangzym
   */
  public static final String STS_REQ = "sts_req";
  public static final String TMSTP_DISPATCH = "tmstp_dispatch";
  /**
   * ����һ���ֶ� modify by zjf transtime ����ʱ��
   */
  public static final String TRANSTIME="transtime";
  private UFDateTime transtime;
  
  public UFDateTime getTranstime() {
	return transtime;
}

public void setTranstime(UFDateTime transtime) {
	this.transtime = transtime;
}

/** ��������_�������� **/
public static final String MATERIALNAME = "materialname";

/** ����ͺŻ�ͼ�� **/
public static final String MATERIALSPEC = "materialspec";

/** ԭ����/���� **/
public static final String YCG = "ycg";

/**  ��������_�������� **/
public String getMaterialname() {
  return (String) this.getAttributeValue(PraybillItemVO.MATERIALNAME);
}

/**  ��������_�������� **/
public void setMaterialname(String materialname) {
  this.setAttributeValue(PraybillItemVO.MATERIALNAME, materialname);
}

/**  ����ͺŻ�ͼ�� **/
public String getMaterialspec() {
  return (String) this.getAttributeValue(PraybillItemVO.MATERIALSPEC);
}

/**  ����ͺŻ�ͼ�� **/
public void setMaterialspec(String materialspec) {
  this.setAttributeValue(PraybillItemVO.MATERIALSPEC, materialspec);
}

/**  ԭ����/���� **/
public String getYcg() {
  return (String) this.getAttributeValue(PraybillItemVO.YCG);
}

/**  ԭ����/����**/
public void setYcg(String ycg) {
  this.setAttributeValue(PraybillItemVO.YCG, ycg);
}



/** 2016-12-22������չ�ֶ�  **/
  
  public String sts_req;              //�ƻ�״̬                  
  public String req_document;         //�����ĺ�                  
  public String code_proj_cst;        //�ͻ����̱��              
  public String name_proj_cst;        //�ͻ���������              
  public String name_machine;         //��������                  
  public String material_quality;     //����                      
  public Integer rating_life;          //��������죩            
  public String cde_manufac;          //ԭʼ�����̱���            
  public String name_manufac;         //���������ƣ�ԭʼ�����̣�  
  public UFDouble pric_req;             //�ƻ��۸񣨼ƻ����۲���˰��
  public UFDouble pric_req_rat;         //�ƻ��۸񣨼ƻ����ۺ�˰��  
  public UFDouble tot_pric_req;         //�ƻ��ܼ۲���˰            
  public UFDouble tot_pric_req_rat;     //�ƻ��ܼۺ�˰              
  public String seq_fac_plan;         //�ͻ��ƻ���-�����ƻ���     
  public String seq_fac_no;           //�����ƻ��к�              
  public String cde_fact;             //�ͻ����-��������         
  public String name_fact;            //�ͻ�����-��������         
  public String remark_plan;          //�ƻ�˵��                  
  public String banfn;                //�ɹ������                
  public String bnfpo;                //�ɹ������к�              
  public String req_group;            //�ɹ���                    
  public String req_group_b;          //�ɹ���֯                  
  public String code_facty;           //��˾����                  
  public String cde_equipment;        //������Ż����            
  public String checker_fac;          //���Ա                    
  public UFDateTime tmstp_dispatch;       //�ƻ�����ʱ��
  
  public Integer unit_pric;          //�۸�λ                    
  public String group_matl;       //������        

  
  /*****************************************************************/

  /** �ɹ���֯�ɱ༭ **/
  public UFBoolean getBcanpurchaseorgedit() {
    return (UFBoolean) this
        .getAttributeValue(PraybillItemVO.BCANPURCHASEORGEDIT);
  }

  /** �Ƿ�̶������� **/
  public UFBoolean getBfixedrate() {
    return (UFBoolean) this.getAttributeValue(PraybillItemVO.BFIXEDRATE);
  }

  /** �Ѱ��� **/
  public UFBoolean getBisarrange() {
    return (UFBoolean) this.getAttributeValue(PraybillItemVO.BISARRANGE);
  }

  /** �������������� **/
  public UFBoolean getBisgensaorder() {
    return (UFBoolean) this.getAttributeValue(PraybillItemVO.BISGENSAORDER);
  }

  /** �������������� **/
  public UFBoolean getBpublishtoec() {
    return (UFBoolean) this.getAttributeValue(PraybillItemVO.BPUBLISHTOEC);
  }

  /** �йر� **/
  public UFBoolean getBrowclose() {
    return (UFBoolean) this.getAttributeValue(PraybillItemVO.BROWCLOSE);
  }

  /** �ͻ� getter ���� */
  public String getCasscustid() {
    return (String) this.getAttributeValue(PraybillItemVO.CASSCUSTID);
  }

  /** ��λ **/
  public String getCastunitid() {
    return (String) this.getAttributeValue(PraybillItemVO.CASTUNITID);
  }

  /** ������ **/
  public String getCffileid() {
    return (String) this.getAttributeValue(PraybillItemVO.CFFILEID);
  }

  /** Դͷ���ݷ�¼��ʶ **/
  public String getCfirstbid() {
    return (String) this.getAttributeValue(PraybillItemVO.CFIRSTBID);
  }

  /** Դͷ���ݱ�ʶ **/
  public String getCfirstid() {
    return (String) this.getAttributeValue(PraybillItemVO.CFIRSTID);
  }

  /** Դͷ�������� **/
  public String getCfirsttypecode() {
    return (String) this.getAttributeValue(PraybillItemVO.CFIRSTTYPECODE);
  }

  /** �������� **/
  public String getCordertrantypecode() {
    return (String) this.getAttributeValue(PraybillItemVO.CORDERTRANTYPECODE);
  }

  /** �������� **/
  public String getCproductorid() {
    return (String) this.getAttributeValue(PraybillItemVO.CPRODUCTORID);
  }

  /** ��Ŀ **/
  public String getCprojectid() {
    return (String) this.getAttributeValue(PraybillItemVO.CPROJECTID);
  }

  /** ��Ŀ���� getter ���� */
  public String getCprojecttaskid() {
    return (String) this.getAttributeValue(PraybillItemVO.CPROJECTTASKID);
  }

  /** �к� **/
  public String getCrowno() {
    return (String) this.getAttributeValue(PraybillItemVO.CROWNO);
  }

  /** ��Դ���ݷ�¼��ʶ **/
  public String getCsourcebid() {
    return (String) this.getAttributeValue(PraybillItemVO.CSOURCEBID);
  }

  /** ��Դ���ݱ�ʶ **/
  public String getCsourceid() {
    return (String) this.getAttributeValue(PraybillItemVO.CSOURCEID);
  }

  /** ��Դ�������� **/
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(PraybillItemVO.CSOURCETYPECODE);
  }

  /** ��������λ **/
  public String getCunitid() {
    return (String) this.getAttributeValue(PraybillItemVO.CUNITID);
  }

  /** �빺���� **/
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(PraybillHeaderVO.DBILLDATE);
  }

  /** �������� **/
  public UFDate getDreqdate() {
    return (UFDate) this.getAttributeValue(PraybillItemVO.DREQDATE);
  }

  /** ���鶩������ **/
  public UFDate getDsuggestdate() {
    return (UFDate) this.getAttributeValue(PraybillItemVO.DSUGGESTDATE);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M20_B);
  }

  /** �ۼƶ������� **/
  public UFDouble getNaccumulatenum() {
    return (UFDouble) this.getAttributeValue(PraybillItemVO.NACCUMULATENUM);
  }

  /** ���� **/
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(PraybillItemVO.NASTNUM);
  }

  /** ���ɺ�ͬ���� **/
  public Integer getNgenct() {
    return (Integer) this.getAttributeValue(PraybillItemVO.NGENCT);
  }

  /** ������ **/
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(PraybillItemVO.NNUM);
  }

  /** ���ɼ۸����������� **/
  public Integer getNpriceauditbill() {
    return (Integer) this.getAttributeValue(PraybillItemVO.NPRICEAUDITBILL);
  }

  /** ����ѯ���۵����� **/
  public Integer getNquotebill() {
    return (Integer) this.getAttributeValue(PraybillItemVO.NQUOTEBILL);
  }

  /** ���Ҽ�˰�ϼ� **/
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(PraybillItemVO.NTAXMNY);
  }

  /** �����Һ�˰���� **/
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(PraybillItemVO.NTAXPRICE);
  }

  /** ���ε��� **/
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_BATCHCODE);
  }

  /** �ɹ�Ա **/
  public String getPk_employee() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_EMPLOYEE);
  }

  /** �������� **/
  public String getPk_group() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_GROUP);
  }

  /** ����(VID) **/
  public String getPk_material() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_MATERIAL);
  }

  /** �����֯ **/
  public String getPk_org() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_ORG);
  }

  /** �����֯�汾 **/
  public String getPk_org_v() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_ORG_V);
  }

  /** �빺������_���� **/
  public String getPk_praybill() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_PRAYBILL);
  }

  /** �빺����ID **/
  public String getPk_praybill_b() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_PRAYBILL_B);
  }

  /** ��Ʒ **/
  public String getPk_product() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_PRODUCT);
  }

  /** ��Ʒ�汾 **/
  public String getPk_product_v() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_PRODUCT_V);
  }

  /** �ɹ���֯ **/
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_PURCHASEORG);
  }

  /** �ɹ���֯�汾 **/
  public String getPk_purchaseorg_v() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_PURCHASEORG_V);
  }

  /** ������ **/
  public String getPk_reqdept() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_REQDEPT);
  }

  /** �����Ű汾 **/
  public String getPk_reqdept_v() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_REQDEPT_V);
  }

  /** ԭʼ��������֯���°汾 **/
  public String getPk_reqstoorg() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_REQSTOORG);
  }

  /** ԭʼ��������֯ **/
  public String getPk_reqstoorg_v() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_REQSTOORG_V);
  }

  /** ����ֿ� **/
  public String getPk_reqstor() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_REQSTOR);
  }

  /** ����(OID) **/
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_SRCMATERIAL);
  }

  /** ���鹩Ӧ�� **/
  public String getPk_suggestsupplier() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_SUGGESTSUPPLIER);
  }

  /** ��Դ������TS getter ���� */
  public UFDateTime getSourcebts() {
    return (UFDateTime) this.getAttributeValue(OrderItemVO.SOURCEBTS);
  }

  /** ��Դ����TS getter ���� */
  public UFDateTime getSourcets() {
    return (UFDateTime) this.getAttributeValue(OrderItemVO.SOURCETS);
  }

  /** ʱ��� **/
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(PraybillItemVO.TS);
  }

  /** ���κ� **/
  public String getVbatchcode() {
    return (String) this.getAttributeValue(PraybillItemVO.VBATCHCODE);
  }

  /** �Զ�����1 **/
  public String getVbdef1() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF1);
  }

  /** �Զ�����10 **/
  public String getVbdef10() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF10);
  }

  /** �Զ�����11 **/
  public String getVbdef11() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF11);
  }

  /** �Զ�����12 **/
  public String getVbdef12() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF12);
  }

  /** �Զ�����13 **/
  public String getVbdef13() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF13);
  }

  /** �Զ�����14 **/
  public String getVbdef14() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF14);
  }

  /** �Զ�����15 **/
  public String getVbdef15() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF15);
  }

  /** �Զ�����16 **/
  public String getVbdef16() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF16);
  }

  /** �Զ�����17 **/
  public String getVbdef17() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF17);
  }

  /** �Զ�����18 **/
  public String getVbdef18() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF18);
  }

  /** �Զ�����19 **/
  public String getVbdef19() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF19);
  }

  /** �Զ�����2 **/
  public String getVbdef2() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF2);
  }

  /** �Զ�����20 **/
  public String getVbdef20() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF20);
  }

  /** �Զ�����3 **/
  public String getVbdef3() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF3);
  }

  /** �Զ�����4 **/
  public String getVbdef4() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF4);
  }

  /** �Զ�����5 **/
  public String getVbdef5() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF5);
  }

  /** �Զ�����6 **/
  public String getVbdef6() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF6);
  }

  /** �Զ�����7 **/
  public String getVbdef7() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF7);
  }

  /** �Զ�����8 **/
  public String getVbdef8() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF8);
  }

  /** �Զ�����9 **/
  public String getVbdef9() {
    return (String) this.getAttributeValue(PraybillItemVO.VBDEF9);
  }

  /** ��ע **/
  public String getVbmemo() {
    return (String) this.getAttributeValue(PraybillItemVO.VBMEMO);
  }

  /** ������ **/
  public String getVchangerate() {
    return (String) this.getAttributeValue(PraybillItemVO.VCHANGERATE);
  }

  /** Դͷ���ݺ� **/
  public String getVfirstcode() {
    return (String) this.getAttributeValue(PraybillItemVO.VFIRSTCODE);
  }

  /** Դͷ�����к� **/
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(PraybillItemVO.VFIRSTROWNO);
  }

  /** Դͷ�������� **/
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(PraybillItemVO.VFIRSTTRANTYPE);
  }

  /** ���ɸ�������1 **/
  public String getVfree1() {
    return (String) this.getAttributeValue(PraybillItemVO.VFREE1);
  }

  /** ���ɸ�������10 **/
  public String getVfree10() {
    return (String) this.getAttributeValue(PraybillItemVO.VFREE10);
  }

  /** ���ɸ�������2 **/
  public String getVfree2() {
    return (String) this.getAttributeValue(PraybillItemVO.VFREE2);
  }

  /** ���ɸ�������3 **/
  public String getVfree3() {
    return (String) this.getAttributeValue(PraybillItemVO.VFREE3);
  }

  /** ���ɸ�������4 **/
  public String getVfree4() {
    return (String) this.getAttributeValue(PraybillItemVO.VFREE4);
  }

  /** ���ɸ�������5 **/
  public String getVfree5() {
    return (String) this.getAttributeValue(PraybillItemVO.VFREE5);
  }

  /** ���ɸ�������6 **/
  public String getVfree6() {
    return (String) this.getAttributeValue(PraybillItemVO.VFREE6);
  }

  /** ���ɸ�������7 **/
  public String getVfree7() {
    return (String) this.getAttributeValue(PraybillItemVO.VFREE7);
  }

  /** ���ɸ�������8 **/
  public String getVfree8() {
    return (String) this.getAttributeValue(PraybillItemVO.VFREE8);
  }

  /** ���ɸ�������9 **/
  public String getVfree9() {
    return (String) this.getAttributeValue(PraybillItemVO.VFREE9);
  }

  /** ��Դ���ݺ� **/
  public String getVsourcecode() {
    return (String) this.getAttributeValue(PraybillItemVO.VSOURCECODE);
  }

  /** ��Դ�����к� **/
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(PraybillItemVO.VSOURCEROWNO);
  }

  /** ��Դ�������� **/
  public String getVsrctrantypecode() {
    return (String) this.getAttributeValue(PraybillItemVO.VSRCTRANTYPECODE);
  }

  /** �ɹ���֯�ɱ༭ **/
  public void setBcanpurchaseorgedit(UFBoolean bcanpurchaseorgedit) {
    this.setAttributeValue(PraybillItemVO.BCANPURCHASEORGEDIT,
        bcanpurchaseorgedit);
  }

  /** �Ƿ�̶������� **/
  public void setBfixedrate(UFBoolean bfixedrate) {
    this.setAttributeValue(PraybillItemVO.BFIXEDRATE, bfixedrate);
  }

  /** �Ѱ��� **/
  public void setBisarrange(UFBoolean bisarrange) {
    this.setAttributeValue(PraybillItemVO.BISARRANGE, bisarrange);
  }

  /** �������������� **/
  public void setBisgensaorder(UFBoolean bisgensaorder) {
    this.setAttributeValue(PraybillItemVO.BISGENSAORDER, bisgensaorder);
  }

  /** �������������� **/
  public void setBpublishtoec(UFBoolean bpublishtoec) {
    this.setAttributeValue(PraybillItemVO.BPUBLISHTOEC, bpublishtoec);
  }

  /** �йر� **/
  public void setBrowclose(UFBoolean browclose) {
    this.setAttributeValue(PraybillItemVO.BROWCLOSE, browclose);
  }

  /** �ͻ� setter ���� */
  public void setCasscustid(String casscustid) {
    this.setAttributeValue(PraybillItemVO.CASSCUSTID, casscustid);
  }

  /** ��λ **/
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(PraybillItemVO.CASTUNITID, castunitid);
  }

  /** ������ **/
  public void setCffileid(String cffileid) {
    this.setAttributeValue(PraybillItemVO.CFFILEID, cffileid);
  }

  /** Դͷ���ݷ�¼��ʶ **/
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(PraybillItemVO.CFIRSTBID, cfirstbid);
  }

  /** Դͷ���ݱ�ʶ **/
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(PraybillItemVO.CFIRSTID, cfirstid);
  }

  /** Դͷ�������� **/
  public void setCfirsttypecode(String cfirsttypecode) {
    this.setAttributeValue(PraybillItemVO.CFIRSTTYPECODE, cfirsttypecode);
  }

  /** �������� **/
  public void setCordertrantypecode(String cordertrantypecode) {
    this.setAttributeValue(PraybillItemVO.CORDERTRANTYPECODE,
        cordertrantypecode);
  }

  /** �������� **/
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(PraybillItemVO.CPRODUCTORID, cproductorid);
  }

  /** ��Ŀ **/
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(PraybillItemVO.CPROJECTID, cprojectid);
  }

  /** ��Ŀ���� setter ���� */
  public void setCprojecttaskid(String cprojecttaskid) {
    this.setAttributeValue(PraybillItemVO.CPROJECTTASKID, cprojecttaskid);
  }

  /** �к� **/
  public void setCrowno(String crowno) {
    this.setAttributeValue(PraybillItemVO.CROWNO, crowno);
  }

  /** ��Դ���ݷ�¼��ʶ **/
  public void setCsourcebid(String csourcebid) {
    this.setAttributeValue(PraybillItemVO.CSOURCEBID, csourcebid);
  }

  /** ��Դ���ݱ�ʶ **/
  public void setCsourceid(String csourceid) {
    this.setAttributeValue(PraybillItemVO.CSOURCEID, csourceid);
  }

  /** ��Դ�������� **/
  public void setCsourcetypecode(String csourcetypecode) {
    this.setAttributeValue(PraybillItemVO.CSOURCETYPECODE, csourcetypecode);
  }

  /** ��������λ **/
  public void setCunitid(String cunitid) {
    this.setAttributeValue(PraybillItemVO.CUNITID, cunitid);
  }

  /** �빺���� **/
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(PraybillHeaderVO.DBILLDATE, dbilldate);
  }

  /** �������� **/
  public void setDreqdate(UFDate dreqdate) {
    this.setAttributeValue(PraybillItemVO.DREQDATE, dreqdate);
  }

  /** ���鶩������ **/
  public void setDsuggestdate(UFDate dsuggestdate) {
    this.setAttributeValue(PraybillItemVO.DSUGGESTDATE, dsuggestdate);
  }

  /** �ۼƶ������� **/
  public void setNaccumulatenum(UFDouble naccumulatenum) {
    this.setAttributeValue(PraybillItemVO.NACCUMULATENUM, naccumulatenum);
  }

  /** ���� **/
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(PraybillItemVO.NASTNUM, nastnum);
  }

  /** ���ɺ�ͬ���� **/
  public void setNgenct(Integer ngenct) {
    this.setAttributeValue(PraybillItemVO.NGENCT, ngenct);
  }

  /** ������ **/
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(PraybillItemVO.NNUM, nnum);
  }

  /** ���ɼ۸����������� **/
  public void setNpriceauditbill(Integer npriceauditbill) {
    this.setAttributeValue(PraybillItemVO.NPRICEAUDITBILL, npriceauditbill);
  }

  /** ����ѯ���۵����� **/
  public void setNquotebill(Integer nquotebill) {
    this.setAttributeValue(PraybillItemVO.NQUOTEBILL, nquotebill);
  }

  /** ���Ҽ�˰�ϼ� **/
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(PraybillItemVO.NTAXMNY, ntaxmny);
  }

  /** �����Һ�˰���� **/
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(PraybillItemVO.NTAXPRICE, ntaxprice);
  }

  /** ���ε��� **/
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(PraybillItemVO.PK_BATCHCODE, pk_batchcode);
  }

  /** �ɹ�Ա **/
  public void setPk_employee(String pk_employee) {
    this.setAttributeValue(PraybillItemVO.PK_EMPLOYEE, pk_employee);
  }

  /** �������� **/
  public void setPk_group(String pk_group) {
    this.setAttributeValue(PraybillItemVO.PK_GROUP, pk_group);
  }

  /** ����(VID) **/
  public void setPk_material(String pk_material) {
    this.setAttributeValue(PraybillItemVO.PK_MATERIAL, pk_material);
  }

  /** �����֯ **/
  public void setPk_org(String pk_org) {
    this.setAttributeValue(PraybillItemVO.PK_ORG, pk_org);
  }

  /** �����֯�汾 **/
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(PraybillItemVO.PK_ORG_V, pk_org_v);
  }

  /** �빺������_���� **/
  public void setPk_praybill(String pk_praybill) {
    this.setAttributeValue(PraybillItemVO.PK_PRAYBILL, pk_praybill);
  }

  /** �빺����ID **/
  public void setPk_praybill_b(String pk_praybill_b) {
    this.setAttributeValue(PraybillItemVO.PK_PRAYBILL_B, pk_praybill_b);
  }

  /** ��Ʒ **/
  public void setPk_product(String pk_product) {
    this.setAttributeValue(PraybillItemVO.PK_PRODUCT, pk_product);
  }

  /** ��Ʒ�汾 **/
  public void setPk_product_v(String pk_product_v) {
    this.setAttributeValue(PraybillItemVO.PK_PRODUCT_V, pk_product_v);
  }

  /** �ɹ���֯ **/
  public void setPk_purchaseorg(String pk_purchaseorg) {
    this.setAttributeValue(PraybillItemVO.PK_PURCHASEORG, pk_purchaseorg);
  }

  /** �ɹ���֯�汾 **/
  public void setPk_purchaseorg_v(String pk_purchaseorg_v) {
    this.setAttributeValue(PraybillItemVO.PK_PURCHASEORG_V, pk_purchaseorg_v);
  }

  /** ������ **/
  public void setPk_reqdept(String pk_reqdept) {
    this.setAttributeValue(PraybillItemVO.PK_REQDEPT, pk_reqdept);
  }

  /** �����Ű汾 **/
  public void setPk_reqdept_v(String pk_reqdept_v) {
    this.setAttributeValue(PraybillItemVO.PK_REQDEPT_V, pk_reqdept_v);
  }

  /** ԭʼ��������֯���°汾 **/
  public void setPk_reqstoorg(String pk_reqstoorg) {
    this.setAttributeValue(PraybillItemVO.PK_REQSTOORG, pk_reqstoorg);
  }

  /** ԭʼ��������֯ **/
  public void setPk_reqstoorg_v(String pk_reqstoorg_v) {
    this.setAttributeValue(PraybillItemVO.PK_REQSTOORG_V, pk_reqstoorg_v);
  }

  /** ����ֿ� **/
  public void setPk_reqstor(String pk_reqstor) {
    this.setAttributeValue(PraybillItemVO.PK_REQSTOR, pk_reqstor);
  }

  /** ����(OID) **/
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(PraybillItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** ���鹩Ӧ�� **/
  public void setPk_suggestsupplier(String pk_suggestsupplier) {
    this.setAttributeValue(PraybillItemVO.PK_SUGGESTSUPPLIER,
        pk_suggestsupplier);
  }

  /** ��Դ������TS setter ���� */
  public void setSourcebts(UFDateTime sourcebts) {
    this.setAttributeValue(OrderItemVO.SOURCEBTS, sourcebts);
  }

  /** ��Դ����TS setter ���� */
  public void setSourcets(UFDateTime sourcets) {
    this.setAttributeValue(OrderItemVO.SOURCETS, sourcets);
  }

  /** ʱ��� **/
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(PraybillItemVO.TS, ts);
  }

  /** ���κ� **/
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(PraybillItemVO.VBATCHCODE, vbatchcode);
  }

  /** �Զ�����1 **/
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(PraybillItemVO.VBDEF1, vbdef1);
  }

  /** �Զ�����10 **/
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(PraybillItemVO.VBDEF10, vbdef10);
  }

  /** �Զ�����11 **/
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(PraybillItemVO.VBDEF11, vbdef11);
  }

  /** �Զ�����12 **/
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(PraybillItemVO.VBDEF12, vbdef12);
  }

  /** �Զ�����13 **/
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(PraybillItemVO.VBDEF13, vbdef13);
  }

  /** �Զ�����14 **/
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(PraybillItemVO.VBDEF14, vbdef14);
  }

  /** �Զ�����15 **/
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(PraybillItemVO.VBDEF15, vbdef15);
  }

  /** �Զ�����16 **/
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(PraybillItemVO.VBDEF16, vbdef16);
  }

  /** �Զ�����17 **/
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(PraybillItemVO.VBDEF17, vbdef17);
  }

  /** �Զ�����18 **/
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(PraybillItemVO.VBDEF18, vbdef18);
  }

  /** �Զ�����19 **/
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(PraybillItemVO.VBDEF19, vbdef19);
  }

  /** �Զ�����2 **/
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(PraybillItemVO.VBDEF2, vbdef2);
  }

  /** �Զ�����20 **/
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(PraybillItemVO.VBDEF20, vbdef20);
  }

  /** �Զ�����3 **/
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(PraybillItemVO.VBDEF3, vbdef3);
  }

  /** �Զ�����4 **/
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(PraybillItemVO.VBDEF4, vbdef4);
  }

  /** �Զ�����5 **/
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(PraybillItemVO.VBDEF5, vbdef5);
  }

  /** �Զ�����6 **/
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(PraybillItemVO.VBDEF6, vbdef6);
  }

  /** �Զ�����7 **/
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(PraybillItemVO.VBDEF7, vbdef7);
  }

  /** �Զ�����8 **/
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(PraybillItemVO.VBDEF8, vbdef8);
  }

  /** �Զ�����9 **/
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(PraybillItemVO.VBDEF9, vbdef9);
  }

  /** ��ע **/
  public void setVbmemo(String vmemo) {
    this.setAttributeValue(PraybillItemVO.VBMEMO, vmemo);
  }

  /** ������ **/
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(PraybillItemVO.VCHANGERATE, vchangerate);
  }

  /** Դͷ���ݺ� **/
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(PraybillItemVO.VFIRSTCODE, vfirstcode);
  }

  /** Դͷ�����к� **/
  public void setVfirstrowno(String vfirstrowno) {
    this.setAttributeValue(PraybillItemVO.VFIRSTROWNO, vfirstrowno);
  }

  /** Դͷ�������� **/
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(PraybillItemVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /** ���ɸ�������1 **/
  public void setVfree1(String vfree1) {
    this.setAttributeValue(PraybillItemVO.VFREE1, vfree1);
  }

  /** ���ɸ�������10 **/
  public void setVfree10(String vfree10) {
    this.setAttributeValue(PraybillItemVO.VFREE10, vfree10);
  }

  /** ���ɸ�������2 **/
  public void setVfree2(String vfree2) {
    this.setAttributeValue(PraybillItemVO.VFREE2, vfree2);
  }

  /** ���ɸ�������3 **/
  public void setVfree3(String vfree3) {
    this.setAttributeValue(PraybillItemVO.VFREE3, vfree3);
  }

  /** ���ɸ�������4 **/
  public void setVfree4(String vfree4) {
    this.setAttributeValue(PraybillItemVO.VFREE4, vfree4);
  }

  /** ���ɸ�������5 **/
  public void setVfree5(String vfree5) {
    this.setAttributeValue(PraybillItemVO.VFREE5, vfree5);
  }

  /** ���ɸ�������6 **/
  public void setVfree6(String vfree6) {
    this.setAttributeValue(PraybillItemVO.VFREE6, vfree6);
  }

  /** ���ɸ�������7 **/
  public void setVfree7(String vfree7) {
    this.setAttributeValue(PraybillItemVO.VFREE7, vfree7);
  }

  /** ���ɸ�������8 **/
  public void setVfree8(String vfree8) {
    this.setAttributeValue(PraybillItemVO.VFREE8, vfree8);
  }

  /** ���ɸ�������9 **/
  public void setVfree9(String vfree9) {
    this.setAttributeValue(PraybillItemVO.VFREE9, vfree9);
  }

  /** ��Դ���ݺ� **/
  public void setVsourcecode(String vsourcecode) {
    this.setAttributeValue(PraybillItemVO.VSOURCECODE, vsourcecode);
  }

  /** ��Դ�����к� **/
  public void setVsourcerowno(String vsourcerowno) {
    this.setAttributeValue(PraybillItemVO.VSOURCEROWNO, vsourcerowno);
  }

  /** ��Դ�������� **/
  public void setVsrctrantypecode(String vsrctrantypecode) {
    this.setAttributeValue(PraybillItemVO.VSRCTRANTYPECODE, vsrctrantypecode);
  }
  /********************************************************/
  /***16-12-22������չ�ֶ�*/

/**
 * @return sts_req
 */
public String getSts_req() {
	return sts_req;
}

/**
 * @param sts_req Ҫ���õ� sts_req
 */
public void setSts_req(String sts_req) {
	this.sts_req = sts_req;
}

/**
 * @return req_document
 */
public String getReq_document() {
	return req_document;
}

/**
 * @param req_document Ҫ���õ� req_document
 */
public void setReq_document(String req_document) {
	this.req_document = req_document;
}

/**
 * @return code_proj_cst
 */
public String getCode_proj_cst() {
	return code_proj_cst;
}

/**
 * @param code_proj_cst Ҫ���õ� code_proj_cst
 */
public void setCode_proj_cst(String code_proj_cst) {
	this.code_proj_cst = code_proj_cst;
}

/**
 * @return name_proj_cst
 */
public String getName_proj_cst() {
	return name_proj_cst;
}

/**
 * @param name_proj_cst Ҫ���õ� name_proj_cst
 */
public void setName_proj_cst(String name_proj_cst) {
	this.name_proj_cst = name_proj_cst;
}

/**
 * @return name_machine
 */
public String getName_machine() {
	return name_machine;
}

/**
 * @param name_machine Ҫ���õ� name_machine
 */
public void setName_machine(String name_machine) {
	this.name_machine = name_machine;
}

/**
 * @return material_quality
 */
public String getMaterial_quality() {
	return material_quality;
}

/**
 * @param material_quality Ҫ���õ� material_quality
 */
public void setMaterial_quality(String material_quality) {
	this.material_quality = material_quality;
}

/**
 * @return rating_life
 */
public Integer getRating_life() {
	return rating_life;
}

/**
 * @param rating_life Ҫ���õ� rating_life
 */
public void setRating_life(Integer rating_life) {
	this.rating_life = rating_life;
}

/**
 * @return cde_manufac
 */
public String getCde_manufac() {
	return cde_manufac;
}

/**
 * @param cde_manufac Ҫ���õ� cde_manufac
 */
public void setCde_manufac(String cde_manufac) {
	this.cde_manufac = cde_manufac;
}

/**
 * @return name_manufac
 */
public String getName_manufac() {
	return name_manufac;
}

/**
 * @param name_manufac Ҫ���õ� name_manufac
 */
public void setName_manufac(String name_manufac) {
	this.name_manufac = name_manufac;
}

/**
 * @return pric_req
 */
public UFDouble getPric_req() {
	return pric_req;
}

/**
 * @param pric_req Ҫ���õ� pric_req
 */
public void setPric_req(UFDouble pric_req) {
	this.pric_req = pric_req;
}

/**
 * @return pric_req_rat
 */
public UFDouble getPric_req_rat() {
	return pric_req_rat;
}

/**
 * @param pric_req_rat Ҫ���õ� pric_req_rat
 */
public void setPric_req_rat(UFDouble pric_req_rat) {
	this.pric_req_rat = pric_req_rat;
}

/**
 * @return tot_pric_req
 */
public UFDouble getTot_pric_req() {
	return tot_pric_req;
}

/**
 * @param tot_pric_req Ҫ���õ� tot_pric_req
 */
public void setTot_pric_req(UFDouble tot_pric_req) {
	this.tot_pric_req = tot_pric_req;
}

/**
 * @return tot_pric_req_rat
 */
public UFDouble getTot_pric_req_rat() {
	return tot_pric_req_rat;
}

/**
 * @param tot_pric_req_rat Ҫ���õ� tot_pric_req_rat
 */
public void setTot_pric_req_rat(UFDouble tot_pric_req_rat) {
	this.tot_pric_req_rat = tot_pric_req_rat;
}

/**
 * @return seq_fac_plan
 */
public String getSeq_fac_plan() {
	return seq_fac_plan;
}

/**
 * @param seq_fac_plan Ҫ���õ� seq_fac_plan
 */
public void setSeq_fac_plan(String seq_fac_plan) {
	this.seq_fac_plan = seq_fac_plan;
}

/**
 * @return seq_fac_no
 */
public String getSeq_fac_no() {
	return seq_fac_no;
}

/**
 * @param seq_fac_no Ҫ���õ� seq_fac_no
 */
public void setSeq_fac_no(String seq_fac_no) {
	this.seq_fac_no = seq_fac_no;
}

/**
 * @return cde_fact
 */
public String getCde_fact() {
	return cde_fact;
}

/**
 * @param cde_fact Ҫ���õ� cde_fact
 */
public void setCde_fact(String cde_fact) {
	this.cde_fact = cde_fact;
}

/**
 * @return name_fact
 */
public String getName_fact() {
	return name_fact;
}

/**
 * @param name_fact Ҫ���õ� name_fact
 */
public void setName_fact(String name_fact) {
	this.name_fact = name_fact;
}

/**
 * @return remark_plan
 */
public String getRemark_plan() {
	return remark_plan;
}

/**
 * @param remark_plan Ҫ���õ� remark_plan
 */
public void setRemark_plan(String remark_plan) {
	this.remark_plan = remark_plan;
}

/**
 * @return banfn
 */
public String getBanfn() {
	return banfn;
}

/**
 * @param banfn Ҫ���õ� banfn
 */
public void setBanfn(String banfn) {
	this.banfn = banfn;
}

/**
 * @return bnfpo
 */
public String getBnfpo() {
	return bnfpo;
}

/**
 * @param bnfpo Ҫ���õ� bnfpo
 */
public void setBnfpo(String bnfpo) {
	this.bnfpo = bnfpo;
}

/**
 * @return req_group
 */
public String getReq_group() {
	return req_group;
}

/**
 * @param req_group Ҫ���õ� req_group
 */
public void setReq_group(String req_group) {
	this.req_group = req_group;
}

/**
 * @return req_group_b
 */
public String getReq_group_b() {
	return req_group_b;
}

/**
 * @param req_group_b Ҫ���õ� req_group_b
 */
public void setReq_group_b(String req_group_b) {
	this.req_group_b = req_group_b;
}

/**
 * @return code_facty
 */
public String getCode_facty() {
	return code_facty;
}

/**
 * @param code_facty Ҫ���õ� code_facty
 */
public void setCode_facty(String code_facty) {
	this.code_facty = code_facty;
}

/**
 * @return cde_equipment
 */
public String getCde_equipment() {
	return cde_equipment;
}

/**
 * @param cde_equipment Ҫ���õ� cde_equipment
 */
public void setCde_equipment(String cde_equipment) {
	this.cde_equipment = cde_equipment;
}

/**
 * @return checker_fac
 */
public String getChecker_fac() {
	return checker_fac;
}

/**
 * @param checker_fac Ҫ���õ� checker_fac
 */
public void setChecker_fac(String checker_fac) {
	this.checker_fac = checker_fac;
}

/**
 * @return tmstp_dispatch
 */
public UFDateTime getTmstp_dispatch() {
	return tmstp_dispatch;
}

/**
 * @param tmstp_dispatch Ҫ���õ� tmstp_dispatch
 */
public void setTmstp_dispatch(UFDateTime tmstp_dispatch) {
	this.tmstp_dispatch = tmstp_dispatch;
}

/**
 * @return unit_pric
 */
public Integer getUnit_pric() {
	return unit_pric;
}

/**
 * @param unit_pric Ҫ���õ� unit_pric
 */
public void setUnit_pric(Integer unit_pric) {
	this.unit_pric = unit_pric;
}

/**
 * @return group_matl
 */
public String getGroup_matl() {
	return group_matl;
}

/**
 * @param group_matl Ҫ���õ� group_matl
 */
public void setGroup_matl(String group_matl) {
	this.group_matl = group_matl;
}


  
  
}
