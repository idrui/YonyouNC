package nc.vo.pu.report.view.m422x;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.scmmm.vo.scmpub.report.pub.ISCMReportContext;
import nc.scmmm.vo.scmpub.report.viewfactory.sql.PermissionTableInfo;
import nc.scmmm.vo.scmpub.report.viewfactory.sql.SCMPermissionBeanSqlView;
import nc.vo.ic.pub.sql.BeanPath;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

public class StoreReqSummaryRptView extends SCMPermissionBeanSqlView {

  private final static String BODYALIAS = "po_storereq_b";

  private final static String PK_STOREREQ_B = "pk_storereq_b";

  private static final long serialVersionUID = -7881470505032131939L;

  // ��Ҫ��SQL�г��ֵı����ֶΣ�ʹ��List�����ռ���ͳһת��Ϊ���飬�����ڸ���new���飬ͬʱҲ�������һЩ�жϺͲ���
  private List<String> bodyFields = new ArrayList<String>();

  // ��Ҫ��SQL�г��ֵı�ͷ�ֶΣ�ʹ��List�����ռ���ͳһת��Ϊ���飬�����ڸ���new���飬ͬʱҲ�������һЩ�жϺͲ���
  private List<String> headerFields = new ArrayList<String>();

  public StoreReqSummaryRptView(ISCMReportContext context) {
    super(new StoreReqAppHeaderVO(), context);
  }

  /**
   * �����ܷ�ʽΪ�������ϻ���ʱ�������Ӧ���ֶκͷ��鷽ʽ��
   */
  public void addFieldsByMaterial() {
    this.bodyFields.add(StoreReqAppItemVO.PK_MATERIAL);

    this.commonProcess();
  }

  /**
   * �����ܷ�ʽΪ���ݿ����֯ + ���ϻ���ʱ�������Ӧ���ֶκͷ��鷽ʽ��
   */
  public void addFieldsByOrgMaterial() {
    this.headerFields.add(StoreReqAppHeaderVO.PK_ORG_V);
    this.bodyFields.add(StoreReqAppItemVO.PK_MATERIAL);

    this.commonProcess();
  }

  /**
   * �����ܷ�ʽΪ���ݿ����֯ + �������� + ���ϻ���ʱ�������Ӧ���ֶκͷ��鷽ʽ��
   */
  public void addFieldsByOrgReqDateMaterial() {
    this.headerFields.add(StoreReqAppHeaderVO.PK_ORG_V);
    this.bodyFields.add(StoreReqAppItemVO.DREQDATE);
    this.bodyFields.add(StoreReqAppItemVO.PK_MATERIAL);

    this.commonProcess();
  }

  /**
   * �����ܷ�ʽΪ������������ + ���ϻ���ʱ�������Ӧ���ֶκͷ��鷽ʽ��
   */
  public void addFieldsByReqDateMaterial() {
    this.bodyFields.add(StoreReqAppItemVO.DREQDATE);
    this.bodyFields.add(StoreReqAppItemVO.PK_MATERIAL);

    this.commonProcess();
  }

  /**
   * �����ܷ�ʽΪ��������ֿ� + ���ϻ���ʱ�������Ӧ���ֶκͷ��鷽ʽ��
   */
  public void addFieldsByReqStorDocMaterial() {
    this.bodyFields.add(StoreReqAppItemVO.PK_REQSTORDOC);
    this.bodyFields.add(StoreReqAppItemVO.PK_MATERIAL);

    this.commonProcess();
  }

  /**
   * ������л��ܷ�ʽ����ʾ���ֶ�
   */
  private void commonProcess() {
    // ����ͨ���ֶ�
    if (!this.bodyFields.contains(StoreReqAppItemVO.PK_MATERIAL)) {
      this.bodyFields.add(StoreReqAppItemVO.PK_MATERIAL);
    }
    this.bodyFields.add(StoreReqAppItemVO.CUNITID);

    // ��֮ǰ�ռ����ֶ�ת��Ϊ����
    String[] headerFieldArray = this.headerFields.toArray(new String[] {});
    String[] bodyFieldArray = this.bodyFields.toArray(new String[] {});

    // ��ӷ����ֶ�
    this.addSelectFields(headerFieldArray, headerFieldArray);
    this.addSelectFields(StoreReqSummaryRptView.PK_STOREREQ_B, bodyFieldArray,
        bodyFieldArray);

    // ��Ӻϼ��ֶ�
    this.addViewSumFields(StoreReqSummaryRptView.PK_STOREREQ_B, new String[] {
      StoreReqAppItemVO.NNUM, StoreReqAppItemVO.NASTNUM,
      StoreReqAppItemVO.NTAXMNY, StoreReqAppItemVO.NACCUOUTNUM
    });
  }

  /**
   * ��Ҫ������Ȩ��
   */
  @Override
  protected Set<PermissionTableInfo> initFixTableAlias() {
    return new HashSet<PermissionTableInfo>();
  }

  @Override
  protected void initFixWhere() {
    // ���˵���ɾ��������
    this.addWhereByAttrPathExp(BeanPath.DOT + "dr = 0");
    this.addWhereByAttrPathExp(StoreReqSummaryRptView.BODYALIAS + BeanPath.DOT
        + "dr = 0");
  }
}
