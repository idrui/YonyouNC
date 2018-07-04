package nc.ui.pu.est.query;

import nc.ui.pu.est.view.EstQueryComboEditorCreator;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.refedit.IFieldValueElementEditorCreator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarPuClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.enumeration.QueryNonMetaFieldCode;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * @since 6.0
 * @version 2011-1-17 ����04:33:20
 * @author yinfy
 */

public class VMIEstQueryDLGInitializer implements IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.init(condDLGDelegator);
    // ����֯Ȩ��
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      VmiFIHeaderVO.PK_FINANCEORG
    });
    // ��֧������Ȩ��
    condDLGDelegator.setPowerEnable(false);
    this.filterRef(condDLGDelegator);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  protected void filterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // ���ý������Ͳ��շ�Χ
    new QTransTypeFilter(condDLGDelegator, ICBillType.VmiSum.getCode())
        .filter();
    String fiorgcode = GoodsEstVO.PK_FINANCEORG;
    String matrlcode = GoodsEstVO.PK_MATERIAL;
    String srcmatrlcode = GoodsEstVO.PK_SRCMATERIAL;
    // ��������֯�������ϻ�������
    String filtercode = matrlcode + ".pk_marbasclass";
    QMarbasclassFilter mbclf =
        new QMarbasclassFilter(condDLGDelegator, filtercode);
    mbclf.setPk_orgCode(fiorgcode);
    mbclf.addEditorListener();
    // ��������֯�������ϲɹ�����
    filtercode = matrlcode + ".materialstock.pk_marpuclass";
    QMarPuClassFilter mpclf =
        new QMarPuClassFilter(condDLGDelegator, filtercode);
    mpclf.setPk_orgCode(fiorgcode);
    mpclf.addEditorListener();
    // ������֯��������
    filtercode = matrlcode;
    new QMarterialFilter(condDLGDelegator, fiorgcode, filtercode)
        .addEditorListener();
    filtercode = srcmatrlcode;
    new QMarterialFilter(condDLGDelegator, fiorgcode, filtercode)
        .addEditorListener();
    // ��Ŀ
    filtercode =
        PurchaseinFIItemVO.PK_STOCKPS_B + "." + PurchaseinFIItemVO.CPROJECTID;
    new QProjectFilter(condDLGDelegator, fiorgcode, filtercode)
        .addEditorListener();
    // ��Ӧ��
    filtercode = GoodsEstVO.PK_SUPPLIER;
    QSupplierFilter supf = new QSupplierFilter(condDLGDelegator, filtercode);
    supf.setPk_orgCode(fiorgcode);
    supf.addEditorListener();
    // �������֯���˲ֿ�
    filtercode = VmiFIHeaderVO.PK_STORDOC;
    new QWareHouseFilter(condDLGDelegator, VmiFIHeaderVO.PK_STOREORG,
        filtercode).addEditorListener();
    
    // �����Ϲ���������
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_srcmaterial.code",
        "cffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_srcmaterial.code",
        "cffileid.vskucode").addEditorListener();
  }

  protected void init(QueryConditionDLGDelegator condDLGDelegator) {
    // �༭��
    IFieldValueElementEditorCreator comboCrt = new EstQueryComboEditorCreator();
    condDLGDelegator.setFieldValueElementEditor(null,
        QueryNonMetaFieldCode.bestfee.name(), comboCrt);
    condDLGDelegator.setFieldValueElementEditor(null,
        QueryNonMetaFieldCode.festtype.name(), comboCrt);
  }
}
