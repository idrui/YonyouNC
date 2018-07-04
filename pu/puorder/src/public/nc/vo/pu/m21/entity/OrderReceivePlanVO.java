/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-12 ����02:18:55
 */
package nc.vo.pu.m21.entity;

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
 * <li>�����ƻ�VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-12 ����02:18:55
 */
public class OrderReceivePlanVO extends SuperVO {

  /** �Ƿ���Ʒ */
  public static final String BLARGESS = "blargess";

  /** �Ƿ�����ر� */
  public static final String BTRANSCLOSED = "btransclosed";

  /** ��λ */
  public static final String CASTUNITID = "castunitid";

  /** �ջ��ص� */
  public static final String CDEVADDRID = "cdevaddrid";

  /** �ջ����� */
  public static final String CDEVAREAID = "cdevareaid";

  /** ���۵�λ */
  public static final String CQTUNITID = "cqtunitid";

  /** �����к� */
  public static final String CROWNOBB1 = "crownobb1";

  /** ����λ */
  public static final String CUNITID = "cunitid";

  /** ��Ӧ�̷����ص� */
  public static final String CVENDDEVADDRID = "cvenddevaddrid";

  /** ��Ӧ�̷������� */
  public static final String CVENDDEVAREAID = "cvenddevareaid";

  /** �������� */
  public static final String CVENDORID = "cvendorid";

  /** �ƻ��������� */
  public static final String DPLANARRVDATE = "dplanarrvdate";

  /** dr */
  public static final String DR = "dr";

  /**
   * ��������
   */
  public static final String DSENDDATE = "dsenddate";

  /** ����״̬ */
  public static final String FISACTIVE = "fisactive";

  /** �ۼƵ��������� */
  public static final String NACCUMARRVNUM = "naccumarrvnum";

  /** �ۼ����������� */
  public static final String NACCUMDEVNUM = "naccumdevnum";

  /** ���ջ����� **/
  public static final String NACCUMRECEIVENUM = "naccumreceivenum";

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

  /** ������ */
  public static final String NNUM = "nnum";

  /** ���� */
  public static final String NPACKNUM = "npacknum";

  /** �������� */
  public static final String NQTUNITNUM = "nqtunitnum";

  /** ��� */
  public static final String NVOLUMN = "nvolumn";

  /** ���� */
  public static final String NWEIGHT = "nweight";

  /** �ջ������֯ */
  public static final String PK_ARRVSTOORG = "pk_arrvstoorg";

  /** �ջ������֯�汾��Ϣ */
  public static final String PK_ARRVSTOORG_V = "pk_arrvstoorg_v";

  /** ���κ����� */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** ������֯ */
  public static final String PK_FLOWSTOCKORG = "pk_flowstockorg";

  /** ������֯�汾��Ϣ */
  public static final String PK_FLOWSTOCKORG_V = "pk_flowstockorg_v";

  /** �������� */
  public static final String PK_GROUP = "pk_group";

  /** ���ϰ汾��Ϣ */
  public static final String PK_MATERIAL = "pk_material";

  /** �ɹ����� */
  public static final String PK_ORDER = "pk_order";

  /** �����ƻ����ӱ� */
  public static final String PK_ORDER_B = "pk_order_b";

  /** �����ƻ� */
  public static final String PK_ORDER_BB1 = "pk_order_bb1";

  /** �ɹ���֯ԭʼ��Ϣ */
  public static final String PK_ORG = "pk_org";

  /** �ɹ���֯ */
  public static final String PK_ORG_V = "pk_org_v";

  /** �ջ���ַ */
  public static final String PK_RECEIVEADDRESS = "pk_receiveaddress";

  /** �ջ��ֿ� */
  public static final String PK_RECVSTORDOC = "pk_recvstordoc";

  /** ������Ϣ */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

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

  /** �����ƻ��� */
  public static final String VBILLCODE = "vbillcode";

  /** ������ */
  public static final String VCHANGERATE = "vchangerate";

  /**
   * EC��������
   */
  public static final String VECBILLCODE = "vecbillcode";

  /**
   * ���ƺ�
   */
  public static final String VEHICLELICENSE = "vehiclelicense";

  /**
   * ���乤��
   */
  public static final String VEHICLETYPE = "vehicletype";

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

  /**
   * ��ϵ��
   */
  public static final String VLINKPSN = "vlinkpsn";

  /**
   * ��ϵ��ʽ
   */
  public static final String VLINKTYPE = "vlinktype";

  /** ��ע */
  public static final String VMEMO = "vmemo";

  /** ���۵�λ������ */
  public static final String VQTUNITRATE = "vqtunitrate";

  /** ��Ӧ�̷�����ַ */
  public static final String VVENDDEVADDR = "vvenddevaddr";

  private static final long serialVersionUID = -6034282102163011124L;

  /** �Ƿ���Ʒ getter ���� */
  public UFBoolean getBlargess() {
    return (UFBoolean) this.getAttributeValue(OrderReceivePlanVO.BLARGESS);
  }

  /** �Ƿ�����ر� getter ���� */
  public UFBoolean getBtransclosed() {
    return (UFBoolean) this.getAttributeValue(OrderReceivePlanVO.BTRANSCLOSED);
  }

  /** ��λ getter ���� */
  public String getCastunitid() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CASTUNITID);
  }

  /** �ջ��ص� getter ���� */
  public String getCdevaddrid() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CDEVADDRID);
  }

  /** �ջ����� getter ���� */
  public String getCdevareaid() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CDEVAREAID);
  }

  /** ���۵�λ getter ���� */
  public String getCqtunitid() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CQTUNITID);
  }

  /** �����к� getter ���� */
  public String getCrownobb1() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CROWNOBB1);
  }

  /** ����λ getter ���� */
  public String getCunitid() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CUNITID);
  }

  /** ��Ӧ�̷����ص� getter ���� */
  public String getCvenddevaddrid() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CVENDDEVADDRID);
  }

  /** ��Ӧ�̷������� getter ���� */
  public String getCvenddevareaid() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CVENDDEVAREAID);
  }

  /** �������� getter ���� */
  public String getCvendorid() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CVENDORID);
  }

  /** �ƻ��������� getter ���� */
  public UFDate getDplanarrvdate() {
    return (UFDate) this.getAttributeValue(OrderReceivePlanVO.DPLANARRVDATE);
  }

  /** dr getter ���� */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(OrderReceivePlanVO.DR);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public UFDate getDsenddate() {
    return (UFDate) this.getAttributeValue(OrderReceivePlanVO.DSENDDATE);
  }

  /** ����״̬ getter ���� */
  public Integer getFisactive() {
    return (Integer) this.getAttributeValue(OrderReceivePlanVO.FISACTIVE);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M21_BB1);
  }

  /** �ۼƵ��������� getter ���� */
  public UFDouble getNaccumarrvnum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NACCUMARRVNUM);
  }

  /** �ۼ����������� getter ���� */
  public UFDouble getNaccumdevnum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NACCUMDEVNUM);
  }

  /** ���ջ����� **/
  public UFDouble getNaccumreceivenum() {
    return (UFDouble) this
        .getAttributeValue(OrderReceivePlanVO.NACCUMRECEIVENUM);
  }

  /** �ۼ���������� getter ���� */
  public UFDouble getNaccumstorenum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NACCUMSTORENUM);
  }

  /** �ۼ�;�������� getter ���� */
  public UFDouble getNaccumwastnum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NACCUMWASTNUM);
  }

  /** ���� getter ���� */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NASTNUM);
  }

  /** �ۼ��˻������� getter ���� */
  public UFDouble getNbackarrvnum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NBACKARRVNUM);
  }

  /** �ۼ��˿������� getter ���� */
  public UFDouble getNbackstorenum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NBACKSTORENUM);
  }

  /** ������ getter ���� */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NNUM);
  }

  /** ���� getter ���� */
  public UFDouble getNpacknum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NPACKNUM);
  }

  /** �������� getter ���� */
  public UFDouble getNqtunitnum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NQTUNITNUM);
  }

  /** ��� getter ���� */
  public UFDouble getNvolumn() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NVOLUMN);
  }

  /** ���� getter ���� */
  public UFDouble getNweight() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NWEIGHT);
  }

  /** �ջ������֯ getter ���� */
  public String getPk_arrvstoorg() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ARRVSTOORG);
  }

  /** �ջ������֯�汾��Ϣ getter ���� */
  public String getPk_arrvstoorg_v() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ARRVSTOORG_V);
  }

  /** ���κ����� getter ���� */
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_BATCHCODE);
  }

  /** ������֯ getter ���� */
  public String getPk_flowstockorg() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_FLOWSTOCKORG);
  }

  /** ������֯�汾��Ϣ getter ���� */
  public String getPk_flowstockorg_v() {
    return (String) this
        .getAttributeValue(OrderReceivePlanVO.PK_FLOWSTOCKORG_V);
  }

  /** �������� getter ���� */
  public String getPk_group() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_GROUP);
  }

  /** ���ϰ汾��Ϣ getter ���� */
  public String getPk_material() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_MATERIAL);
  }

  /** �ɹ����� getter ���� */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORDER);
  }

  /** �����ƻ����ӱ� getter ���� */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORDER_B);
  }

  /** �����ƻ� getter ���� */
  public String getPk_order_bb1() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORDER_BB1);
  }

  /** �ɹ���֯ԭʼ��Ϣ getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORG);
  }

  /** �ɹ���֯ getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORG_V);
  }

  /** �ջ���ַ getter ���� */
  public String getPk_receiveaddress() {
    return (String) this
        .getAttributeValue(OrderReceivePlanVO.PK_RECEIVEADDRESS);
  }

  /** �ջ��ֿ� getter ���� */
  public String getPk_recvstordoc() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_RECVSTORDOC);
  }

  /** ������Ϣ getter ���� */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_SRCMATERIAL);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(OrderReceivePlanVO.TS);
  }

  /** ���κ� getter ���� */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBATCHCODE);
  }

  /** �Զ�����1 getter ���� */
  public String getVbdef1() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF1);
  }

  /** �Զ�����10 getter ���� */
  public String getVbdef10() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF10);
  }

  /** �Զ�����11 getter ���� */
  public String getVbdef11() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF11);
  }

  /** �Զ�����12 getter ���� */
  public String getVbdef12() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF12);
  }

  /** �Զ�����13 getter ���� */
  public String getVbdef13() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF13);
  }

  /** �Զ�����14 getter ���� */
  public String getVbdef14() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF14);
  }

  /** �Զ�����15 getter ���� */
  public String getVbdef15() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF15);
  }

  /** �Զ�����16 getter ���� */
  public String getVbdef16() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF16);
  }

  /** �Զ�����17 getter ���� */
  public String getVbdef17() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF17);
  }

  /** �Զ�����18 getter ���� */
  public String getVbdef18() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF18);
  }

  /** �Զ�����19 getter ���� */
  public String getVbdef19() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF19);
  }

  /** �Զ�����2 getter ���� */
  public String getVbdef2() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF2);
  }

  /** �Զ�����20 getter ���� */
  public String getVbdef20() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF20);
  }

  /** �Զ�����3 getter ���� */
  public String getVbdef3() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF3);
  }

  /** �Զ�����4 getter ���� */
  public String getVbdef4() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF4);
  }

  /** �Զ�����5 getter ���� */
  public String getVbdef5() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF5);
  }

  /** �Զ�����6 getter ���� */
  public String getVbdef6() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF6);
  }

  /** �Զ�����7 getter ���� */
  public String getVbdef7() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF7);
  }

  /** �Զ�����8 getter ���� */
  public String getVbdef8() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF8);
  }

  /** �Զ�����9 getter ���� */
  public String getVbdef9() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBDEF9);
  }

  /** �����ƻ��� getter ���� */
  public String getVbillcode() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBILLCODE);
  }

  /** ������ getter ���� */
  public String getVchangerate() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VCHANGERATE);
  }

  /**
   * ��ȡEC��������
   * 
   * @return EC��������
   */
  public String getVecbillcode() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VECBILLCODE);
  }

  /**
   * ��ȡ���ƺ�
   * 
   * @return ���ƺ�
   */
  public String getVehiclelicense() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VEHICLELICENSE);
  }

  /**
   * ��ȡ���乤��
   * 
   * @return ���乤��
   */
  public String getVehicletype() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VEHICLETYPE);
  }

  /** ���ɸ�������1 getter ���� */
  public String getVfree1() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE1);
  }

  /** ���ɸ�������10 getter ���� */
  public String getVfree10() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE10);
  }

  /** ���ɸ�������2 getter ���� */
  public String getVfree2() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE2);
  }

  /** ���ɸ�������3 getter ���� */
  public String getVfree3() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE3);
  }

  /** ���ɸ�������4 getter ���� */
  public String getVfree4() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE4);
  }

  /** ���ɸ�������5 getter ���� */
  public String getVfree5() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE5);
  }

  /** ���ɸ�������6 getter ���� */
  public String getVfree6() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE6);
  }

  /** ���ɸ�������7 getter ���� */
  public String getVfree7() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE7);
  }

  /** ���ɸ�������8 getter ���� */
  public String getVfree8() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE8);
  }

  /** ���ɸ�������9 getter ���� */
  public String getVfree9() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE9);
  }

  /**
   * ��ȡ��ϵ��
   * 
   * @return ��ϵ��
   */
  public String getVlinkpsn() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VLINKPSN);
  }

  /**
   * ��ȡ��ϵ��ʽ
   * 
   * @return ��ϵ��ʽ
   */
  public String getVlinktype() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VLINKTYPE);
  }

  /** ��ע getter ���� */
  public String getVmemo() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VMEMO);
  }

  /** ���۵�λ������ getter ���� */
  public String getVqtunitrate() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VQTUNITRATE);
  }

  /** ��Ӧ�̷�����ַ getter ���� */
  public String getVvenddevaddr() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VVENDDEVADDR);
  }

  /** �Ƿ���Ʒ setter ���� */
  public void setBlargess(UFBoolean blargess) {
    this.setAttributeValue(OrderReceivePlanVO.BLARGESS, blargess);
  }

  /** �Ƿ�����ر� setter ���� */
  public void setBtransclosed(UFBoolean btransclosed) {
    this.setAttributeValue(OrderReceivePlanVO.BTRANSCLOSED, btransclosed);
  }

  /** ��λ setter ���� */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(OrderReceivePlanVO.CASTUNITID, castunitid);
  }

  /** �ջ��ص� setter ���� */
  public void setCdevaddrid(String cdevaddrid) {
    this.setAttributeValue(OrderReceivePlanVO.CDEVADDRID, cdevaddrid);
  }

  /** �ջ����� setter ���� */
  public void setCdevareaid(String cdevareaid) {
    this.setAttributeValue(OrderReceivePlanVO.CDEVAREAID, cdevareaid);
  }

  /** ���۵�λ setter ���� */
  public void setCqtunitid(String cqtunitid) {
    this.setAttributeValue(OrderReceivePlanVO.CQTUNITID, cqtunitid);
  }

  /** �����к� setter ���� */
  public void setCrownobb1(String crownobb1) {
    this.setAttributeValue(OrderReceivePlanVO.CROWNOBB1, crownobb1);
  }

  /** ����λ setter ���� */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(OrderReceivePlanVO.CUNITID, cunitid);
  }

  /** ��Ӧ�̷����ص� setter ���� */
  public void setCvenddevaddrid(String cvenddevaddrid) {
    this.setAttributeValue(OrderReceivePlanVO.CVENDDEVADDRID, cvenddevaddrid);
  }

  /** ��Ӧ�̷������� setter ���� */
  public void setCvenddevareaid(String cvenddevareaid) {
    this.setAttributeValue(OrderReceivePlanVO.CVENDDEVAREAID, cvenddevareaid);
  }

  /** �������� setter ���� */
  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(OrderReceivePlanVO.CVENDORID, cvendorid);
  }

  /** �ƻ��������� setter ���� */
  public void setDplanarrvdate(UFDate dplanarrvdate) {
    this.setAttributeValue(OrderReceivePlanVO.DPLANARRVDATE, dplanarrvdate);
  }

  /** dr setter ���� */
  public void setDr(Integer dr) {
    this.setAttributeValue(OrderReceivePlanVO.DR, dr);
  }

  /**
   * ���÷�������
   * 
   * @param dsenddate ��������
   */
  public void setDsenddate(UFDate dsenddate) {
    this.setAttributeValue(OrderReceivePlanVO.DSENDDATE, dsenddate);
  }

  /** ����״̬ setter ���� */
  public void setFisactive(Integer fisactive) {
    this.setAttributeValue(OrderReceivePlanVO.FISACTIVE, fisactive);
  }

  /** �ۼƵ��������� setter ���� */
  public void setNaccumarrvnum(UFDouble naccumarrvnum) {
    this.setAttributeValue(OrderReceivePlanVO.NACCUMARRVNUM, naccumarrvnum);
  }

  /** �ۼ����������� setter ���� */
  public void setNaccumdevnum(UFDouble naccumdevnum) {
    this.setAttributeValue(OrderReceivePlanVO.NACCUMDEVNUM, naccumdevnum);
  }

  /** ���ջ����� **/
  public void setNaccumreceivenum(UFDouble naccumreceivenum) {
    this.setAttributeValue(OrderReceivePlanVO.NACCUMRECEIVENUM,
        naccumreceivenum);
  }

  /** �ۼ���������� setter ���� */
  public void setNaccumstorenum(UFDouble naccumstorenum) {
    this.setAttributeValue(OrderReceivePlanVO.NACCUMSTORENUM, naccumstorenum);
  }

  /** �ۼ�;�������� setter ���� */
  public void setNaccumwastnum(UFDouble naccumwastnum) {
    this.setAttributeValue(OrderReceivePlanVO.NACCUMWASTNUM, naccumwastnum);
  }

  /** ���� setter ���� */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(OrderReceivePlanVO.NASTNUM, nastnum);
  }

  /** �ۼ��˻������� setter ���� */
  public void setNbackarrvnum(UFDouble nbackarrvnum) {
    this.setAttributeValue(OrderReceivePlanVO.NBACKARRVNUM, nbackarrvnum);
  }

  /** �ۼ��˿������� setter ���� */
  public void setNbackstorenum(UFDouble nbackstorenum) {
    this.setAttributeValue(OrderReceivePlanVO.NBACKSTORENUM, nbackstorenum);
  }

  /** ������ setter ���� */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(OrderReceivePlanVO.NNUM, nnum);
  }

  /** ���� setter ���� */
  public void setNpacknum(UFDouble npacknum) {
    this.setAttributeValue(OrderReceivePlanVO.NPACKNUM, npacknum);
  }

  /** �������� setter ���� */
  public void setNqtunitnum(UFDouble nqtunitnum) {
    this.setAttributeValue(OrderReceivePlanVO.NQTUNITNUM, nqtunitnum);
  }

  /** ��� setter ���� */
  public void setNvolumn(UFDouble nvolumn) {
    this.setAttributeValue(OrderReceivePlanVO.NVOLUMN, nvolumn);
  }

  /** ���� setter ���� */
  public void setNweight(UFDouble nweight) {
    this.setAttributeValue(OrderReceivePlanVO.NWEIGHT, nweight);
  }

  /** �ջ������֯ setter ���� */
  public void setPk_arrvstoorg(String pk_arrvstoorg) {
    this.setAttributeValue(OrderReceivePlanVO.PK_ARRVSTOORG, pk_arrvstoorg);
  }

  /** �ջ������֯�汾��Ϣ setter ���� */
  public void setPk_arrvstoorg_v(String pk_arrvstoorg_v) {
    this.setAttributeValue(OrderReceivePlanVO.PK_ARRVSTOORG_V, pk_arrvstoorg_v);
  }

  /** ���κ����� setter ���� */
  public void setPk_batchcode(String pk_batchcode) {
    this.setAttributeValue(OrderReceivePlanVO.PK_BATCHCODE, pk_batchcode);
  }

  /** ������֯ setter ���� */
  public void setPk_flowstockorg(String pk_flowstockorg) {
    this.setAttributeValue(OrderReceivePlanVO.PK_FLOWSTOCKORG, pk_flowstockorg);
  }

  /** ������֯�汾��Ϣ setter ���� */
  public void setPk_flowstockorg_v(String pk_flowstockorg_v) {
    this.setAttributeValue(OrderReceivePlanVO.PK_FLOWSTOCKORG_V,
        pk_flowstockorg_v);
  }

  /** �������� setter ���� */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(OrderReceivePlanVO.PK_GROUP, pk_group);
  }

  /** ���ϰ汾��Ϣ setter ���� */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(OrderReceivePlanVO.PK_MATERIAL, pk_material);
  }

  /** �ɹ����� setter ���� */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(OrderReceivePlanVO.PK_ORDER, pk_order);
  }

  /** �����ƻ����ӱ� setter ���� */
  public void setPk_order_b(String pk_order_b) {
    this.setAttributeValue(OrderReceivePlanVO.PK_ORDER_B, pk_order_b);
  }

  /** �����ƻ� setter ���� */
  public void setPk_order_bb1(String pk_order_bb1) {
    this.setAttributeValue(OrderReceivePlanVO.PK_ORDER_BB1, pk_order_bb1);
  }

  /** �ɹ���֯ԭʼ��Ϣ setter ���� */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(OrderReceivePlanVO.PK_ORG, pk_org);
  }

  /** �ɹ���֯ setter ���� */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(OrderReceivePlanVO.PK_ORG_V, pk_org_v);
  }

  /** �ջ���ַ setter ���� */
  public void setPk_receiveaddress(String pk_receiveaddress) {
    this.setAttributeValue(OrderReceivePlanVO.PK_RECEIVEADDRESS,
        pk_receiveaddress);
  }

  /** �ջ��ֿ� setter ���� */
  public void setPk_recvstordoc(String pk_recvstordoc) {
    this.setAttributeValue(OrderReceivePlanVO.PK_RECVSTORDOC, pk_recvstordoc);
  }

  /** ������Ϣ setter ���� */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(OrderReceivePlanVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** ts setter ���� */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(OrderReceivePlanVO.TS, ts);
  }

  /** ���κ� setter ���� */
  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(OrderReceivePlanVO.VBATCHCODE, vbatchcode);
  }

  /** �Զ�����1 setter ���� */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF1, vbdef1);
  }

  /** �Զ�����10 setter ���� */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF10, vbdef10);
  }

  /** �Զ�����11 setter ���� */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF11, vbdef11);
  }

  /** �Զ�����12 setter ���� */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF12, vbdef12);
  }

  /** �Զ�����13 setter ���� */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF13, vbdef13);
  }

  /** �Զ�����14 setter ���� */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF14, vbdef14);
  }

  /** �Զ�����15 setter ���� */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF15, vbdef15);
  }

  /** �Զ�����16 setter ���� */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF16, vbdef16);
  }

  /** �Զ�����17 setter ���� */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF17, vbdef17);
  }

  /** �Զ�����18 setter ���� */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF18, vbdef18);
  }

  /** �Զ�����19 setter ���� */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF19, vbdef19);
  }

  /** �Զ�����2 setter ���� */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF2, vbdef2);
  }

  /** �Զ�����20 setter ���� */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF20, vbdef20);
  }

  /** �Զ�����3 setter ���� */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF3, vbdef3);
  }

  /** �Զ�����4 setter ���� */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF4, vbdef4);
  }

  /** �Զ�����5 setter ���� */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF5, vbdef5);
  }

  /** �Զ�����6 setter ���� */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF6, vbdef6);
  }

  /** �Զ�����7 setter ���� */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF7, vbdef7);
  }

  /** �Զ�����8 setter ���� */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF8, vbdef8);
  }

  /** �Զ�����9 setter ���� */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(OrderReceivePlanVO.VBDEF9, vbdef9);
  }

  /** �����ƻ��� setter ���� */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(OrderReceivePlanVO.VBILLCODE, vbillcode);
  }

  /** ������ setter ���� */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(OrderReceivePlanVO.VCHANGERATE, vchangerate);
  }

  /**
   * ����EC��������
   * 
   * @param vecbillcode EC��������
   */
  public void setVecbillcode(String vecbillcode) {
    this.setAttributeValue(OrderReceivePlanVO.VECBILLCODE, vecbillcode);
  }

  /**
   * ���ó��ƺ�
   * 
   * @param vehiclelicense ���ƺ�
   */
  public void setVehiclelicense(String vehiclelicense) {
    this.setAttributeValue(OrderReceivePlanVO.VEHICLELICENSE, vehiclelicense);
  }

  /**
   * �������乤��
   * 
   * @param vehicletype ���乤��
   */
  public void setVehicletype(String vehicletype) {
    this.setAttributeValue(OrderReceivePlanVO.VEHICLETYPE, vehicletype);
  }

  /** ���ɸ�������1 setter ���� */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(OrderReceivePlanVO.VFREE1, vfree1);
  }

  /** ���ɸ�������10 setter ���� */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(OrderReceivePlanVO.VFREE10, vfree10);
  }

  /** ���ɸ�������2 setter ���� */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(OrderReceivePlanVO.VFREE2, vfree2);
  }

  /** ���ɸ�������3 setter ���� */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(OrderReceivePlanVO.VFREE3, vfree3);
  }

  /** ���ɸ�������4 setter ���� */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(OrderReceivePlanVO.VFREE4, vfree4);
  }

  /** ���ɸ�������5 setter ���� */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(OrderReceivePlanVO.VFREE5, vfree5);
  }

  /** ���ɸ�������6 setter ���� */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(OrderReceivePlanVO.VFREE6, vfree6);
  }

  /** ���ɸ�������7 setter ���� */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(OrderReceivePlanVO.VFREE7, vfree7);
  }

  /** ���ɸ�������8 setter ���� */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(OrderReceivePlanVO.VFREE8, vfree8);
  }

  /** ���ɸ�������9 setter ���� */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(OrderReceivePlanVO.VFREE9, vfree9);
  }

  /**
   * ������ϵ��
   * 
   * @param vlinkpsn ��ϵ��
   */
  public void setVlinkpsn(String vlinkpsn) {
    this.setAttributeValue(OrderReceivePlanVO.VLINKPSN, vlinkpsn);
  }

  /**
   * ������ϵ��ʽ
   * 
   * @param vlinktype ��ϵ��ʽ
   */
  public void setVlinktype(String vlinktype) {
    this.setAttributeValue(OrderReceivePlanVO.VLINKTYPE, vlinktype);
  }

  /** ��ע setter ���� */
  public void setVmemo(String vmemo) {
    this.setAttributeValue(OrderReceivePlanVO.VMEMO, vmemo);
  }

  /** ���۵�λ������ setter ���� */
  public void setVqtunitrate(String vqtunitrate) {
    this.setAttributeValue(OrderReceivePlanVO.VQTUNITRATE, vqtunitrate);
  }

  /** ��Ӧ�̷�����ַ setter ���� */
  public void setVvenddevaddr(String vvenddevaddr) {
    this.setAttributeValue(OrderReceivePlanVO.VVENDDEVADDR, vvenddevaddr);
  }
}
