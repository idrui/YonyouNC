package nc.vo.pu.report.queryinfo.praybill;

import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.report.enumeration.PrayBillGroupEnum;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;

/**
 * �빺����ѯ���ɱ����ѯ������
 * (1)����ִ��ʱ����Ҫ���ص��ֶΣ�
 * ��Ӧ�� �� ��Ӧ��Դ������ͷ��Ӧ��
 * �������� �� ��Ӧ��Դ������ͷ��������
 * ������ �� ��Ӧ��Դ������ͷ������
 * �ƻ��������� ��Ӧ��Դ��������ƻ���������
 * �������� ��Ӧ��������ͷ��������
 * ������� ��Ӧ��ⵥ����������ڡ�
 * (2)�������ϻ���ʱ����Ҫ���ص��ֶΣ�
 * �빺���� ��Ӧ�빺����ͷ�빺����
 * �����֯ ��Ӧ�빺��������֯
 * �ƻ�Ա ��Ӧ�빺����ͷ�ļƻ�Ա
 * �������� ��Ӧ�빺��������������
 * ���鶩������ ��Ӧ�빺�����鶩������
 * ���鹩Ӧ��
 * �ɹ���֯
 * ��Ӧ�� ��Ӧ��Դ������ͷ��Ӧ��
 * �������� ��Ӧ��Դ������ͷ��������
 * ������ ��Ӧ��Դ������ͷ������
 * ������� ��Ӧ��ⵥ����������ڡ�
 * 
 * @since 6.0
 * @version 2011-2-22 ����09:59:55
 * @author liuchx
 */

public class PrayBillQryInfoPara extends PuQueryInfoPara {

  public static final String CMATERIALOID = PUEntity.M20_B_TABLE + "." + "pk_srcmaterial.code";

  public static final String CMATERIALOID_NAME = PUEntity.M20_B_TABLE + "." + "pk_srcmaterial.name";

  public static final String mainOrg = PUEntity.M20_H_TABLE + "."
      + PraybillHeaderVO.PK_ORG;

  // ��ѯģ�� ���ܿھ�
  public static final String QUERY_GROUP_KEY = "grouptype";

  // Ԫ����·��
  public static final String SRCMATERIAL = "pk_praybill_b."
      + PraybillItemVO.PK_SRCMATERIAL;

  private static final long serialVersionUID = 4553061134405425021L;

  private String bsc;

  public String getBsc() {
    return this.bsc;
  }

  /**
   * ��Ҫ���ص��ֶ�
   */
  @Override
  public String[] getHideKeys() {
    Integer groupType = Integer.valueOf(this.getGroupcondtion());
    /** ���� **/
    if (PrayBillGroupEnum.MAR.value().equals(groupType)) {
      // (2)�������ϻ���ʱ����Ҫ���ص��ֶΣ�
      // * �빺���� ��Ӧ�빺����ͷ�빺����
      // * �����֯ ��Ӧ�빺��������֯
      // * �ƻ�Ա ��Ӧ�빺����ͷ�ļƻ�Ա
      // * �������� ��Ӧ�빺��������������
      // * ���鶩������ ��Ӧ�빺�����鶩������
      // * ���鹩Ӧ��
      // * �ɹ���֯
      // * ��Ӧ�� ��Ӧ��Դ������ͷ��Ӧ��
      // * �������� ��Ӧ��Դ������ͷ��������
      // * ������ ��Ӧ��Դ������ͷ������
      // * ������� ��Ӧ��ⵥ����������ڡ�
      return new String[] {

        PraybillHeaderVO.VBILLCODE,// ���ݺš�
        "this.pk_org_v.name", "this.pk_planpsn.name", PraybillItemVO.DREQDATE,
        PraybillItemVO.DSUGGESTDATE, PraybillItemVO.PK_SUGGESTSUPPLIER,
        "this.pk_purchaseorg_v.name", "this.order_pk_supplier.name",
        "order_dbilldate", "order_vbillcode", "arrive_dbilldate",
        OrderItemVO.DPLANARRVDATE, "PURIN_DBILLDATE"
      };
    }
    // �빺��ִ�л���
    if (PrayBillGroupEnum.EXEGROUP.value().equals(groupType)) {
      return new String[] {
        // * (1)����ִ��ʱ����Ҫ���ص��ֶΣ�
        // * ��Ӧ�� �� ��Ӧ��Դ������ͷ��Ӧ��
        // * �������� �� ��Ӧ��Դ������ͷ��������
        // * ������ �� ��Ӧ��Դ������ͷ������
        // * �ƻ��������� ��Ӧ��Դ��������ƻ���������
        // * �������� ��Ӧ��������ͷ��������
        // * ������� ��Ӧ��ⵥ����������ڡ�

        "order_vbillcode",// ������
        "this.order_pk_supplier.name", "order_dbilldate", "order_vbillcode",
        "arrive_dbilldate", OrderItemVO.DPLANARRVDATE, "PURIN_DBILLDATE"

      };
    }
    /** ��ϸ **/
    return new String[] {};
  }

  public void setBsc(String bsc) {
    this.bsc = bsc;
  }

}
