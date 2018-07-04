package nc.ui.pu.m25.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPaytermFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.ui.scmpub.query.refregion.QueryDlgInitialUtils;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.enumeration.InvoiceQryFieldCode;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * ��Ʊ��ѯ��ʼ����
 * 
 * @since 6.0
 * @version 2011-1-11 ����02:07:58
 * @author �����
 */
public class InvoiceQryCondDlgInitializer implements
    IQueryConditionDLGInitializer {

  /** �ɹ�Ա���� */
  public static final String BIZPSN_NAME = "pk_bizpsn";

  /** ���ϱ��� */
  public static final String MATERIAL_CODE = "invoicebody.pk_srcmaterial.code";

  /** �������� */
  public static final String MATERIAL_NAME = "invoicebody.pk_srcmaterial.name";

  /** ���� Э�� */
  public static final String PAYTERM_CODE = "pk_payterm.code";

  /** ���λ */
  public static final String PAYTOSUPPLIER = "pk_paytosupplier";

  /** ʹ�ò��� */
  public static final String PK_USEDEPT = "invoicebody.pk_usedept";

  /** �����֯���� */
  public static final String STOCK_CODE = "invoicebody.pk_stordoc.pk_org.code";

  /** �ֿ���� */
  public static final String STORDOC_CODE = "invoicebody.pk_stordoc.code";

  /** ��Ӧ�̱��� */
  public static final String SUPPLIER_CODE = "pk_supplier.code";

  /** ��Ӧ������ */
  public static final String SUPPLIER_NAME = "pk_supplier.name";
  
  /** ��Ŀ���� */
  public static final String PROJECT_CODE = "pk_project.project_code";
  
  /** ��Ŀ���� */
  public static final String PK_PROJECT = "invoicebody.cprojectid";

  private AbstractUIAppModel model = null;

  public AbstractUIAppModel getModel() {
    return this.model;
  }

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.initPermission(condDLGDelegator);
    this.filterRef(condDLGDelegator);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    QueryDlgInitialUtils.filterBuySellFlagForBuy(condDLGDelegator,
        InvoiceHeaderVO.FBUYSELLFLAG);
  }

  public void setModel(AbstractUIAppModel model) {
    this.model = model;
  }

  private void filterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 1.��Ӧ�̱��룺 ������������֯ѡֵΨһ������ո���֯�ɼ��Ĺ�Ӧ�̵���¼�룬������ռ��ŷ�Χ�ĵ���¼�롣
    // 2.��Ӧ������
    // 3.���λ ��ͬ��Ӧ��
    // 4.����Э����룺 ���ո���Э��¼�룬������������֯ѡֵΨһ������ո���֯�ɼ��ĸ���Э��¼�룬������ռ��ŷ�Χ�ĸ���Э��
    // 5.�ֿ���룺 ����������֯���롱ѡֵΨһ������ոÿ����֯�¼��ɴ����ÿ����֯�Ĳֿ⵵��¼��(??)��������ոü����µ����вֿ⵵��¼�롣
    // 6.���ý������Ͳ��շ�Χ
    new QTransTypeFilter(condDLGDelegator, POBillType.Invoice.getCode())
        .filter();
    // �ɹ�Ա(��Ա) -- ��������鿴���ŵ�������Ա
    QPsndocFilter qpsnfilter =
        QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator,
            InvoiceQryCondDlgInitializer.BIZPSN_NAME);
    qpsnfilter.setPk_orgCode(null);
    qpsnfilter.addEditorListener();

    // ���� -- ��������鿴���ŵ����в���
    QDeptFilter qdeptfilter =
        QDeptFilter.createQDeptFilterOfPU(condDLGDelegator,
            InvoiceQryFieldCode.deptcode.code());
    qdeptfilter.setPk_orgCode(null);
    qdeptfilter.addEditorListener();
    qdeptfilter =
        QDeptFilter.createQDeptFilterOfPU(condDLGDelegator,
            InvoiceHeaderVO.PK_DEPT);
    qdeptfilter.setPk_orgCode(null);
    qdeptfilter.addEditorListener();

    // ʹ�ò��� -- ��������֯����
    QDeptFilter qusedeptfilter =
        QDeptFilter.createQDeptFilterOfPU(condDLGDelegator,
            InvoiceQryCondDlgInitializer.PK_USEDEPT);
    qusedeptfilter.addEditorListener();

    // ��Ӧ�̱���
    new QSupplierFilter(condDLGDelegator,
        InvoiceQryCondDlgInitializer.SUPPLIER_CODE).addEditorListener();
    // ��Ӧ������
    new QSupplierFilter(condDLGDelegator,
        InvoiceQryCondDlgInitializer.SUPPLIER_NAME).addEditorListener();
    // ���λ
    new QSupplierFilter(condDLGDelegator,
        InvoiceQryCondDlgInitializer.PAYTOSUPPLIER).addEditorListener();
    // �ֿ����
    new QWareHouseFilter(condDLGDelegator,
        InvoiceQryCondDlgInitializer.STOCK_CODE,
        InvoiceQryCondDlgInitializer.STORDOC_CODE).addEditorListener();
    // ����Э�����
    new QPaytermFilter(condDLGDelegator,
        InvoiceQryCondDlgInitializer.PAYTERM_CODE).addEditorListener();
    // ������֯���˿ͻ�
    new QCustomerFilter(condDLGDelegator, "invoicebody."
        + PuAttrNameEnum.casscustid.name()).addEditorListener();
    
    // �ϲ�ͨ�油��NCdp205450398 zhangshqb 2015-09-23 �����Ŀ��ع���
		QProjectFilter qprojfilter = new QProjectFilter(condDLGDelegator,
				InvoiceHeaderVO.PK_ORG, InvoiceQryCondDlgInitializer.PROJECT_CODE);
		qprojfilter.addEditorListener();
		QProjectFilter qcprojfilter = new QProjectFilter(condDLGDelegator,
				InvoiceHeaderVO.PK_ORG, InvoiceQryCondDlgInitializer.PK_PROJECT);
		qcprojfilter.addEditorListener();

    // ������֯��������
    new QMarterialFilter(condDLGDelegator, InvoiceHeaderVO.PK_ORG,
        InvoiceQryCondDlgInitializer.MATERIAL_CODE).addEditorListener();
    new QMarterialFilter(condDLGDelegator, InvoiceHeaderVO.PK_ORG,
        InvoiceQryCondDlgInitializer.MATERIAL_NAME).addEditorListener();
    
    // �����Ϲ���������
    new QFfileFilterByMaterCode(condDLGDelegator, "invoicebody.pk_srcmaterial.code",
        "invoicebody.cffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "invoicebody.pk_srcmaterial.code",
        "invoicebody.cffileid.vskucode").addEditorListener();
  }

  private void initPermission(QueryConditionDLGDelegator condDLGDelegator) {
    // ����֯Ȩ��
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      InvoiceHeaderVO.PK_ORG
    });
    condDLGDelegator.setPowerEnable(true);
  }

}
