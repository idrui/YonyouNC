package nc.vo.pu.m422x.rule.api;

import java.io.Serializable;
/**
 * 
 * @description
 *		�����������뵥VO
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		�����������뵥VO
 * @since 6.5
 * @version 2015-11-10 ����7:51:31
 * @author wandl
 */
public interface IStoreReqAppVO extends Serializable {
  
  /**
   * �����������뵥
   */
  public static final String PK_STOREREQ = "pk_storereq";
  /**
   * ����
   */
  public static final String PK_GROUP = "pk_group";
  /**
   * �����֯
   */
  public static final String PK_ORG = "pk_org";
  /**
   * �����֯
   */
  public static final String PK_ORG_V = "pk_org_v";
  /**
   * ���뵥��
   */
  public static final String VBILLCODE = "vbillcode";
  /**
   * ��������
   */
  public static final String DBILLDATE = "dbilldate";
  /**
   * ���벿��
   */
  public static final String PK_APPDEPTH = "pk_appdepth";
  /**
   * ���벿��
   */
  public static final String PK_APPDEPTH_V = "pk_appdepth_v";
  /**
   * ������
   */
  public static final String PK_APPPSNH = "pk_apppsnh";
  /**
   * �������ͱ������
   */
  public static final String VTRANTYPECODE = "vtrantypecode";
  /**
   * ����״̬
   */
  public static final String FBILLSTATUS = "fbillstatus";
  /**
   * ����
   */
  public static final String BURGENCY = "burgency";
  /**
   * ������
   */
  public static final String NTOTALASTNUM = "ntotalastnum";
  /**
   * ��˰�ϼ�
   */
  public static final String NTOTALORIGMNY = "ntotalorigmny";
  /**
   * ��Ŀ
   */
  public static final String PK_PROJECT = "pk_project";
  /**
   * ������
   */
  public static final String CREATOR = "creator";
  /**
   * ����ʱ��
   */
  public static final String CREATIONTIME = "creationtime";
  /**
   * �Ƶ���
   */
  public static final String BILLMAKER = "billmaker";
  /**
   * �Ƶ�����
   */
  public static final String DMAKEDATE = "dmakedate";
  /**
   * ������
   */
  public static final String APPROVER = "approver";
  /**
   * ��������
   */
  public static final String TAUDITTIME = "taudittime";
  /**
   * ����޸���
   */
  public static final String MODIFIER = "modifier";
  /**
   * ����޸�ʱ��
   */
  public static final String MODIFIEDTIME = "modifiedtime";
  /**
   * ��ӡ����
   */
  public static final String IPRINTCOUNT = "iprintcount";
  /**
   * �Զ�����1
   */
  public static final String VDEF1 = "vdef1";
  /**
   * �Զ�����2
   */
  public static final String VDEF2 = "vdef2";
  /**
   * �Զ�����3
   */
  public static final String VDEF3 = "vdef3";
  /**
   * �Զ�����4
   */
  public static final String VDEF4 = "vdef4";
  /**
   * �Զ�����5
   */
  public static final String VDEF5 = "vdef5";
  /**
   * �Զ�����6
   */
  public static final String VDEF6 = "vdef6";
  /**
   * �Զ�����7
   */
  public static final String VDEF7 = "vdef7";
  /**
   * �Զ�����8
   */
  public static final String VDEF8 = "vdef8";
  /**
   * �Զ�����9
   */
  public static final String VDEF9 = "vdef9";
  /**
   * �Զ�����10
   */
  public static final String VDEF10 = "vdef10";
  /**
   * �Զ�����11
   */
  public static final String VDEF11 = "vdef11";
  /**
   * �Զ�����12
   */
  public static final String VDEF12 = "vdef12";
  /**
   * �Զ�����13
   */
  public static final String VDEF13 = "vdef13";
  /**
   * �Զ�����14
   */
  public static final String VDEF14 = "vdef14";
  /**
   * �Զ�����15
   */
  public static final String VDEF15 = "vdef15";
  /**
   * �Զ�����16
   */
  public static final String VDEF16 = "vdef16";
  /**
   * �Զ�����17
   */
  public static final String VDEF17 = "vdef17";
  /**
   * �Զ�����18
   */
  public static final String VDEF18 = "vdef18";
  /**
   * �Զ�����19
   */
  public static final String VDEF19 = "vdef19";
  /**
   * �Զ�����20
   */
  public static final String VDEF20 = "vdef20";
  /**
   * ��ע
   */
  public static final String VMEMO = "vmemo";
  /**
   * ��������
   */
  public static final String FREQTYPEFLAG = "freqtypeflag";
  /**
   * ����������������
   */
  public static final String CTRANTYPEID = "ctrantypeid";
  /**
   * vostatus
   */
  public static final String STATUS = "status";
  /**
   * dr
   */
  public static final String DR = "dr";
  /**
   * ts
   */
  public static final String TS = "ts";
  /**
   * �����������뵥��ϸ.�����������뵥��ϸ
   */
  public static final String PK_STOREREQ_B_PK_STOREREQ_B = "pk_storereq_b.pk_storereq_b";
  /**
   * �����������뵥��ϸ.����
   */
  public static final String PK_STOREREQ_B_PK_GROUP = "pk_storereq_b.pk_group";
  /**
   * �����������뵥��ϸ.�����֯
   */
  public static final String PK_STOREREQ_B_PK_ORG = "pk_storereq_b.pk_org";
  /**
   * �����������뵥��ϸ.�����֯
   */
  public static final String PK_STOREREQ_B_PK_ORG_V = "pk_storereq_b.pk_org_v";
  /**
   * �����������뵥��ϸ.�к�
   */
  public static final String PK_STOREREQ_B_CROWNO = "pk_storereq_b.crowno";
  /**
   * �����������뵥��ϸ.���ϰ汾��Ϣ
   */
  public static final String PK_STOREREQ_B_PK_MATERIAL = "pk_storereq_b.pk_material";
  /**
   * �����������뵥��ϸ.������Ϣ
   */
  public static final String PK_STOREREQ_B_PK_SRCMATERIAL = "pk_storereq_b.pk_srcmaterial";
  /**
   * �����������뵥��ϸ.����λ
   */
  public static final String PK_STOREREQ_B_CUNITID = "pk_storereq_b.cunitid";
  /**
   * �����������뵥��ϸ.������
   */
  public static final String PK_STOREREQ_B_NNUM = "pk_storereq_b.nnum";
  /**
   * �����������뵥��ϸ.��λ
   */
  public static final String PK_STOREREQ_B_CASTUNITID = "pk_storereq_b.castunitid";
  /**
   * �����������뵥��ϸ.����
   */
  public static final String PK_STOREREQ_B_NASTNUM = "pk_storereq_b.nastnum";
  /**
   * �����������뵥��ϸ.������
   */
  public static final String PK_STOREREQ_B_VCHANGERATE = "pk_storereq_b.vchangerate";
  /**
   * �����������뵥��ϸ.�����Һ�˰����
   */
  public static final String PK_STOREREQ_B_NTAXPRICE = "pk_storereq_b.ntaxprice";
  /**
   * �����������뵥��ϸ.���Ҽ�˰�ϼ�
   */
  public static final String PK_STOREREQ_B_NTAXMNY = "pk_storereq_b.ntaxmny";
  /**
   * �����������뵥��ϸ.����ֿ�
   */
  public static final String PK_STOREREQ_B_PK_REQSTORDOC = "pk_storereq_b.pk_reqstordoc";
  /**
   * �����������뵥��ϸ.��������
   */
  public static final String PK_STOREREQ_B_DREQDATE = "pk_storereq_b.dreqdate";
  /**
   * �����������뵥��ϸ.������
   */
  public static final String PK_STOREREQ_B_PK_APPPSN = "pk_storereq_b.pk_apppsn";
  /**
   * �����������뵥��ϸ.���벿��
   */
  public static final String PK_STOREREQ_B_PK_APPDEPT_V = "pk_storereq_b.pk_appdept_v";
  /**
   * �����������뵥��ϸ.���벿��
   */
  public static final String PK_STOREREQ_B_PK_APPDEPT = "pk_storereq_b.pk_appdept";
  /**
   * �����������뵥��ϸ.�ջ���ַ
   */
  public static final String PK_STOREREQ_B_PK_RECEIVEADDRESS = "pk_storereq_b.pk_receiveaddress";
  /**
   * �����������뵥��ϸ.�ջ�����
   */
  public static final String PK_STOREREQ_B_CDEVAREAID = "pk_storereq_b.cdevareaid";
  /**
   * �����������뵥��ϸ.�ջ��ص�
   */
  public static final String PK_STOREREQ_B_CDEVADDRID = "pk_storereq_b.cdevaddrid";
  /**
   * �����������뵥��ϸ.��Դ��������
   */
  public static final String PK_STOREREQ_B_CSOURCETYPECODE = "pk_storereq_b.csourcetypecode";
  /**
   * �����������뵥��ϸ.��Դ����
   */
  public static final String PK_STOREREQ_B_CSOURCEID = "pk_storereq_b.csourceid";
  /**
   * �����������뵥��ϸ.��Դ������ϸ
   */
  public static final String PK_STOREREQ_B_CSOURCEBID = "pk_storereq_b.csourcebid";
  /**
   * �����������뵥��ϸ.��Դ���ݺ�
   */
  public static final String PK_STOREREQ_B_VSOURCECODE = "pk_storereq_b.vsourcecode";
  /**
   * �����������뵥��ϸ.��Դ��������
   */
  public static final String PK_STOREREQ_B_VSOURCETRANTYPE = "pk_storereq_b.vsourcetrantype";
  /**
   * �����������뵥��ϸ.��Դ�����к�
   */
  public static final String PK_STOREREQ_B_VSOURCEROWNO = "pk_storereq_b.vsourcerowno";
  /**
   * �����������뵥��ϸ.��Դ����ʱ���
   */
  public static final String PK_STOREREQ_B_SOURCETS = "pk_storereq_b.sourcets";
  /**
   * �����������뵥��ϸ.��Դ������ʱ���
   */
  public static final String PK_STOREREQ_B_SOURCEBTS = "pk_storereq_b.sourcebts";
  /**
   * �����������뵥��ϸ.Դͷ��������
   */
  public static final String PK_STOREREQ_B_CFIRSTTYPECODE = "pk_storereq_b.cfirsttypecode";
  /**
   * �����������뵥��ϸ.Դͷ����
   */
  public static final String PK_STOREREQ_B_CFIRSTID = "pk_storereq_b.cfirstid";
  /**
   * �����������뵥��ϸ.Դͷ������ϸ
   */
  public static final String PK_STOREREQ_B_CFIRSTBID = "pk_storereq_b.cfirstbid";
  /**
   * �����������뵥��ϸ.Դͷ���ݺ�
   */
  public static final String PK_STOREREQ_B_VFIRSTCODE = "pk_storereq_b.vfirstcode";
  /**
   * �����������뵥��ϸ.Դͷ��������
   */
  public static final String PK_STOREREQ_B_VFIRSTTRANTYPE = "pk_storereq_b.vfirsttrantype";
  /**
   * �����������뵥��ϸ.Դͷ�����к�
   */
  public static final String PK_STOREREQ_B_VFIRSTROWNO = "pk_storereq_b.vfirstrowno";
  /**
   * �����������뵥��ϸ.�ۼƳ���������
   */
  public static final String PK_STOREREQ_B_NACCUOUTNUM = "pk_storereq_b.naccuoutnum";
  /**
   * �����������뵥��ϸ.�ۼ��������������
   */
  public static final String PK_STOREREQ_B_NACCUOUTREQNUM = "pk_storereq_b.naccuoutreqnum";
  /**
   * �����������뵥��ϸ.���������������
   */
  public static final String PK_STOREREQ_B_NCANOUTREQNUM = "pk_storereq_b.ncanoutreqnum";
  /**
   * �����������뵥��ϸ.�Ƿ�ر�
   */
  public static final String PK_STOREREQ_B_BCLOSE = "pk_storereq_b.bclose";
  /**
   * �����������뵥��ϸ.���κ�����
   */
  public static final String PK_STOREREQ_B_PK_BATCHCODE = "pk_storereq_b.pk_batchcode";
  /**
   * �����������뵥��ϸ.���κ�
   */
  public static final String PK_STOREREQ_B_VBATCHCODE = "pk_storereq_b.vbatchcode";
  /**
   * �����������뵥��ϸ.��Ӧ��
   */
  public static final String PK_STOREREQ_B_CVENDORID = "pk_storereq_b.cvendorid";
  /**
   * �����������뵥��ϸ.��������
   */
  public static final String PK_STOREREQ_B_CPRODUCTORID = "pk_storereq_b.cproductorid";
  /**
   * �����������뵥��ϸ.��Ŀ
   */
  public static final String PK_STOREREQ_B_CPROJECTID = "pk_storereq_b.cprojectid";
  /**
   * �����������뵥��ϸ.��Ŀ����
   */
  public static final String PK_STOREREQ_B_CPROJECTTASKID = "pk_storereq_b.cprojecttaskid";
  /**
   * �����������뵥��ϸ.�ɱ�Ҫ��
   */
  public static final String PK_STOREREQ_B_CCOSTELEMENTID = "pk_storereq_b.ccostelementid";
  /**
   * �����������뵥��ϸ.���ɸ�������1
   */
  public static final String PK_STOREREQ_B_VFREE1 = "pk_storereq_b.vfree1";
  /**
   * �����������뵥��ϸ.���ɸ�������2
   */
  public static final String PK_STOREREQ_B_VFREE2 = "pk_storereq_b.vfree2";
  /**
   * �����������뵥��ϸ.���ɸ�������3
   */
  public static final String PK_STOREREQ_B_VFREE3 = "pk_storereq_b.vfree3";
  /**
   * �����������뵥��ϸ.���ɸ�������4
   */
  public static final String PK_STOREREQ_B_VFREE4 = "pk_storereq_b.vfree4";
  /**
   * �����������뵥��ϸ.���ɸ�������5
   */
  public static final String PK_STOREREQ_B_VFREE5 = "pk_storereq_b.vfree5";
  /**
   * �����������뵥��ϸ.���ɸ�������6
   */
  public static final String PK_STOREREQ_B_VFREE6 = "pk_storereq_b.vfree6";
  /**
   * �����������뵥��ϸ.���ɸ�������7
   */
  public static final String PK_STOREREQ_B_VFREE7 = "pk_storereq_b.vfree7";
  /**
   * �����������뵥��ϸ.���ɸ�������8
   */
  public static final String PK_STOREREQ_B_VFREE8 = "pk_storereq_b.vfree8";
  /**
   * �����������뵥��ϸ.���ɸ�������9
   */
  public static final String PK_STOREREQ_B_VFREE9 = "pk_storereq_b.vfree9";
  /**
   * �����������뵥��ϸ.���ɸ�������10
   */
  public static final String PK_STOREREQ_B_VFREE10 = "pk_storereq_b.vfree10";
  /**
   * �����������뵥��ϸ.�Զ�����1
   */
  public static final String PK_STOREREQ_B_VBDEF1 = "pk_storereq_b.vbdef1";
  /**
   * �����������뵥��ϸ.�Զ�����2
   */
  public static final String PK_STOREREQ_B_VBDEF2 = "pk_storereq_b.vbdef2";
  /**
   * �����������뵥��ϸ.�Զ�����3
   */
  public static final String PK_STOREREQ_B_VBDEF3 = "pk_storereq_b.vbdef3";
  /**
   * �����������뵥��ϸ.�Զ�����4
   */
  public static final String PK_STOREREQ_B_VBDEF4 = "pk_storereq_b.vbdef4";
  /**
   * �����������뵥��ϸ.�Զ�����5
   */
  public static final String PK_STOREREQ_B_VBDEF5 = "pk_storereq_b.vbdef5";
  /**
   * �����������뵥��ϸ.�Զ�����6
   */
  public static final String PK_STOREREQ_B_VBDEF6 = "pk_storereq_b.vbdef6";
  /**
   * �����������뵥��ϸ.�Զ�����7
   */
  public static final String PK_STOREREQ_B_VBDEF7 = "pk_storereq_b.vbdef7";
  /**
   * �����������뵥��ϸ.�Զ�����8
   */
  public static final String PK_STOREREQ_B_VBDEF8 = "pk_storereq_b.vbdef8";
  /**
   * �����������뵥��ϸ.�Զ�����9
   */
  public static final String PK_STOREREQ_B_VBDEF9 = "pk_storereq_b.vbdef9";
  /**
   * �����������뵥��ϸ.�Զ�����10
   */
  public static final String PK_STOREREQ_B_VBDEF10 = "pk_storereq_b.vbdef10";
  /**
   * �����������뵥��ϸ.�Զ�����11
   */
  public static final String PK_STOREREQ_B_VBDEF11 = "pk_storereq_b.vbdef11";
  /**
   * �����������뵥��ϸ.�Զ�����12
   */
  public static final String PK_STOREREQ_B_VBDEF12 = "pk_storereq_b.vbdef12";
  /**
   * �����������뵥��ϸ.�Զ�����13
   */
  public static final String PK_STOREREQ_B_VBDEF13 = "pk_storereq_b.vbdef13";
  /**
   * �����������뵥��ϸ.�Զ�����14
   */
  public static final String PK_STOREREQ_B_VBDEF14 = "pk_storereq_b.vbdef14";
  /**
   * �����������뵥��ϸ.�Զ�����15
   */
  public static final String PK_STOREREQ_B_VBDEF15 = "pk_storereq_b.vbdef15";
  /**
   * �����������뵥��ϸ.�Զ�����16
   */
  public static final String PK_STOREREQ_B_VBDEF16 = "pk_storereq_b.vbdef16";
  /**
   * �����������뵥��ϸ.�Զ�����17
   */
  public static final String PK_STOREREQ_B_VBDEF17 = "pk_storereq_b.vbdef17";
  /**
   * �����������뵥��ϸ.�Զ�����18
   */
  public static final String PK_STOREREQ_B_VBDEF18 = "pk_storereq_b.vbdef18";
  /**
   * �����������뵥��ϸ.�Զ�����19
   */
  public static final String PK_STOREREQ_B_VBDEF19 = "pk_storereq_b.vbdef19";
  /**
   * �����������뵥��ϸ.�Զ�����20
   */
  public static final String PK_STOREREQ_B_VBDEF20 = "pk_storereq_b.vbdef20";
  /**
   * �����������뵥��ϸ.��ע
   */
  public static final String PK_STOREREQ_B_VBMEMO = "pk_storereq_b.vbmemo";
  /**
   * �����������뵥��ϸ.��������
   */
  public static final String PK_STOREREQ_B_DBILLDATE = "pk_storereq_b.dbilldate";
  /**
   * �����������뵥��ϸ.���ұ���
   */
  public static final String PK_STOREREQ_B_CCURRENCYID = "pk_storereq_b.ccurrencyid";
  /**
   * �����������뵥��ϸ.�ۼ��빺������
   */
  public static final String PK_STOREREQ_B_NACCUMBUYREQNUM = "pk_storereq_b.naccumbuyreqnum";
  /**
   * �����������뵥��ϸ.���빺������
   */
  public static final String PK_STOREREQ_B_NCANBUYREQNNUM = "pk_storereq_b.ncanbuyreqnnum";
  /**
   * �����������뵥��ϸ.ԭʼ��������֯
   */
  public static final String PK_STOREREQ_B_PK_REQSTOORG_V = "pk_storereq_b.pk_reqstoorg_v";
  /**
   * �����������뵥��ϸ.ԭʼ��������֯���°汾
   */
  public static final String PK_STOREREQ_B_PK_REQSTOORG = "pk_storereq_b.pk_reqstoorg";
  /**
   * �����������뵥��ϸ.�´�ƽ������֯
   */
  public static final String PK_STOREREQ_B_PK_NEXTBALANCEORG_V = "pk_storereq_b.pk_nextbalanceorg_v";
  /**
   * �����������뵥��ϸ.�´�ƽ������֯���°汾
   */
  public static final String PK_STOREREQ_B_PK_NEXTBALANCEORG = "pk_storereq_b.pk_nextbalanceorg";
  /**
   * �����������뵥��ϸ.��ƽ��
   */
  public static final String PK_STOREREQ_B_BENDGATHER = "pk_storereq_b.bendgather";
  /**
   * �����������뵥��ϸ.�������������
   */
  public static final String PK_STOREREQ_B_NACCUSTORNUM = "pk_storereq_b.naccustornum";
  /**
   * �����������뵥��ϸ.ת������������
   */
  public static final String PK_STOREREQ_B_NNETNUM = "pk_storereq_b.nnetnum";
  /**
   * �����������뵥��ϸ.���ε���
   */
  public static final String PK_STOREREQ_B_CSOURCEID2 = "pk_storereq_b.csourceid2";
  /**
   * �����������뵥��ϸ.���ε�����
   */
  public static final String PK_STOREREQ_B_CSOURCEBID2 = "pk_storereq_b.csourcebid2";
  /**
   * �����������뵥��ϸ.���ε��ݺ�
   */
  public static final String PK_STOREREQ_B_VSOURCECODE2 = "pk_storereq_b.vsourcecode2";
  /**
   * �����������뵥��ϸ.���ε����к�
   */
  public static final String PK_STOREREQ_B_VSOURCEROWNO2 = "pk_storereq_b.vsourcerowno2";
  /**
   * �����������뵥��ϸ.���ε�������
   */
  public static final String PK_STOREREQ_B_CSOURCETYPECODE2 = "pk_storereq_b.csourcetypecode2";
  /**
   * �����������뵥��ϸ.���ε��ݽ�������
   */
  public static final String PK_STOREREQ_B_VSOURCETRANTYPE2 = "pk_storereq_b.vsourcetrantype2";
  /**
   * �����������뵥��ϸ.��������
   */
  public static final String PK_STOREREQ_B_CFIRSTID2 = "pk_storereq_b.cfirstid2";
  /**
   * �����������뵥��ϸ.����������ϸ
   */
  public static final String PK_STOREREQ_B_CFIRSTBID2 = "pk_storereq_b.cfirstbid2";
  /**
   * �����������뵥��ϸ.�������ݺ�
   */
  public static final String PK_STOREREQ_B_VFIRSTCODE2 = "pk_storereq_b.vfirstcode2";
  /**
   * �����������뵥��ϸ.���������к�
   */
  public static final String PK_STOREREQ_B_VFIRSTROWNO2 = "pk_storereq_b.vfirstrowno2";
  /**
   * �����������뵥��ϸ.������������
   */
  public static final String PK_STOREREQ_B_CFIRSTTYPECODE2 = "pk_storereq_b.cfirsttypecode2";
  /**
   * �����������뵥��ϸ.�������ݽ�������
   */
  public static final String PK_STOREREQ_B_VFIRSTTRANTYPE2 = "pk_storereq_b.vfirsttrantype2";
  /**
   * �����������뵥��ϸ.����ʱ��
   */
  public static final String PK_STOREREQ_B_TGATHERTIME = "pk_storereq_b.tgathertime";
  /**
   * �����������뵥��ϸ.������
   */
  public static final String PK_STOREREQ_B_CGATHERPSNID = "pk_storereq_b.cgatherpsnid";
  /**
   * �����������뵥��ϸ.����ID
   */
  public static final String PK_STOREREQ_B_CGATHERID = "pk_storereq_b.cgatherid";
  /**
   * �����������뵥��ϸ.����ƽ��ת�빺������
   */
  public static final String PK_STOREREQ_B_NACCUMMINUSNUM = "pk_storereq_b.naccumminusnum";
  /**
   * �����������뵥��ϸ.CBS
   */
  public static final String PK_STOREREQ_B_CBS = "pk_storereq_b.cbs";
  /**
   * �����������뵥��ϸ.vostatus
   */
  public static final String PK_STOREREQ_B_STATUS = "pk_storereq_b.status";
  /**
   * �����������뵥��ϸ.dr
   */
  public static final String PK_STOREREQ_B_DR = "pk_storereq_b.dr";
  /**
   * �����������뵥��ϸ.ts
   */
  public static final String PK_STOREREQ_B_TS = "pk_storereq_b.ts";
}


