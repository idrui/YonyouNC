package nc.ui.pu.m23.billref.ic.m45;

import java.awt.Container;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarPuClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ṩ���ɹ���ⵥ�Ĳ�ѯ������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-27 ����11:13:15
 */
public class BillReferQueryFor45 extends DefaultBillReferQuery {

  public BillReferQueryFor45(Container c, TemplateInfo info) {
    super(c, info);
  }

  public String getDefaultPk_OrgValue() {
    String pk_org;
    try {
      pk_org = DefaultDataSettingAccessor.getDefaultOrgUnit();
      if (StringUtil.isEmptyWithTrim(pk_org)) {
        return null;
      }
      if (OrgUnitPubService.isTypeOf(pk_org, IOrgConst.STOCKORGTYPE)) {
        return pk_org;
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private void filterOrgPower(QueryConditionDLGDelegator dlgDelegator) {
    // ����֯Ȩ��
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      ArriveHeaderVO.PK_ORG
    });
    // ����ʹ��Ȩ��
    dlgDelegator.setPowerEnable(true);
  }

  private void setFilter(QueryConditionDLGDelegator dlgDelegator) {
    // ���ñ���
    // this.setTitle("�ɹ���ⵥ���յ������Ĳ�ѯ�Ի���");
    // SCMQueryDlgConfig config = new SCMQueryDlgConfig();
    // config.setMustJoinBTname("po_arriveorder_b");
    // config.setRedunDbilldateBTname("po_arriveorder_b");
    // config.setRedunPk_OrgBTname("po_arriveorder_b");
    // this.setSCMQueryDlgConfig(config);

    // ���ý������Ͳ��շ�Χ
    new QTransTypeFilter(dlgDelegator, POBillType.Arrive.getCode()).filter();

    // �������֯���˲ֿ�
    new QWareHouseFilter(dlgDelegator, ArriveItemVO.PK_ORG,
        "pk_arriveorder_b.pk_receivestore").addEditorListener();

    // ������֯�������ϻ�������
    String marbascalss = "pk_arriveorder_b.pk_material.pk_marbasclass.code";
    new QMarbasclassFilter(dlgDelegator, marbascalss).addEditorListener();

    // ������֯�������ϲɹ�����
    String marpuclass =
        "pk_arriveorder_b.pk_material.materialstock.pk_marpuclass.code";
    new QMarPuClassFilter(dlgDelegator, marpuclass).addEditorListener();
    // ������֯��������
    new QMarterialFilter(dlgDelegator, ArriveItemVO.PK_ORG,
        "pk_arriveorder_b.pk_material.code").addEditorListener();
    new QMarterialFilter(dlgDelegator, ArriveItemVO.PK_ORG,
        "pk_arriveorder_b.pk_material.name").addEditorListener();
    new QMarterialFilter(dlgDelegator, ArriveItemVO.PK_ORG,
        "pk_arriveorder_b.pk_srcmaterial.code").addEditorListener();
    new QMarterialFilter(dlgDelegator, ArriveItemVO.PK_ORG,
        "pk_arriveorder_b.pk_srcmaterial.name").addEditorListener();

    // ��Ŀ
    new QProjectFilter(dlgDelegator, ArriveItemVO.PK_ORG,
        "pk_arriveorder_b.cprojectid").addEditorListener();

    // ��Ӧ��
    new QSupplierFilter(dlgDelegator, "pk_supplier.code").addEditorListener();
    new QSupplierFilter(dlgDelegator, "pk_supplier.name").addEditorListener();

    // �ɹ����ţ����ղɹ���֯�µĲ��ŵ�������
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(dlgDelegator, "pk_dept");
    deptFilter.setPk_orgCode("pk_purchaseorg");
    deptFilter.addEditorListener();

    // �ɹ�Ա�����ղɹ���֯�µ���Ա��������
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfPU(dlgDelegator, "pk_pupsndoc");
    psnFilter.setPk_orgCode("pk_purchaseorg");
    psnFilter.addEditorListener();
    // ������֯���˿ͻ�
    new QCustomerFilter(dlgDelegator, ArriveItemVO.PK_ARRIVEORDER_B
        + PUQueryConst.DOT + PuAttrNameEnum.casscustid.name())
        .addEditorListener();
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // ��֯Ȩ�޹���
    this.filterOrgPower(dlgDelegator);
    this.setFilter(dlgDelegator);
    dlgDelegator.setDefaultValue(ArriveItemVO.PK_ORG,
        this.getDefaultPk_OrgValue());
    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }
}
