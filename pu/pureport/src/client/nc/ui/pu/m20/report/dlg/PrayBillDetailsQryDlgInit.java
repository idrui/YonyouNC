package nc.ui.pu.m20.report.dlg;

import nc.ui.pu.report.pub.action.PuReportDlgInit;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.pubapp.uif2app.query2.totalvo.UserDefPropDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pu.report.queryinfo.praybill.PrayBillQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * �ɹ�����ִ�в�ѯģ����չ�����
 * 
 * @since 6.0
 * @version 2011-9-6 ����9:56:39
 * @author zhaoyha
 */
public class PrayBillDetailsQryDlgInit extends PuReportDlgInit implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {

    // �빺���ţ����տ����֯�µĲ��ŵ������롣
    QDeptFilter deptFilter =
        QDeptFilter.createDeptFilterOfIC(condDLGDelegator, PUEntity.M20_H_TABLE
            + "." + PraybillHeaderVO.PK_PLANDEPT);
    deptFilter.setPk_orgCode(PrayBillQryInfoPara.mainOrg);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();
    // �빺Ա�������빺��֯�µ���Ա��������
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfIC(condDLGDelegator,
            PUEntity.M20_H_TABLE + "." + PraybillHeaderVO.PK_PLANPSN);
    psnFilter.setPk_orgCode(PrayBillQryInfoPara.mainOrg);
    psnFilter.addEditorListener();
    // ���ϱ���(OID)
    new QMarterialoidFilter(condDLGDelegator, PrayBillQryInfoPara.mainOrg,
        "po_praybill_b.pk_srcmaterial.code").addEditorListener();
    // ��������
    new QMarterialoidFilter(condDLGDelegator, PrayBillQryInfoPara.mainOrg,
        "po_praybill_b.pk_srcmaterial.name").addEditorListener();

    // ������֯�������ϻ�������
    new QMarbasclassFilter(condDLGDelegator, PrayBillQryInfoPara.mainOrg,
        "po_praybill_b.pk_material.pk_marbasclass").addEditorListener();
    // ����ֿ�
    QWareHouseFilter whfilter =
        new QWareHouseFilter(condDLGDelegator, PrayBillQryInfoPara.mainOrg,
            "po_praybill_b.pk_reqstor");
    // ���������������˲ֿ�
    whfilter.filter();
    whfilter.addEditorListener();
    // ��Ӧ�̵������ࣨӦ���ǻ��������б仯������Ҫ���ˣ�
    // ��Ŀ
    new QProjectFilter(condDLGDelegator, PrayBillQryInfoPara.mainOrg,
        "po_praybill_b.cprojectid").addEditorListener();
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // �Զ�������
    condDLGDelegator.addQueryCondVODealer(new UserDefPropDealer(null, this
        .getQueryParams(new PraybillHeaderVO(), new PraybillItemVO())));
    // ������֯���˿ͻ�
    QCustomerFilter customerFilter =
        new QCustomerFilter(condDLGDelegator, PUEntity.M20_B_TABLE
            + PUQueryConst.DOT + PuAttrNameEnum.casscustid.name());
    customerFilter.setPk_orgCode(PrayBillQryInfoPara.mainOrg);
    customerFilter.addEditorListener();
    
    // �����Ϲ���������
    new QFfileFilter(condDLGDelegator, PrayBillQryInfoPara.CMATERIALOID,
        "po_praybill_b.cffileid").addEditorListener();
    new QFfileFilter(condDLGDelegator, PrayBillQryInfoPara.CMATERIALOID,
        "po_praybill_b.cffileid.vskucode").addEditorListener();
    
    // �����빺����
    QTransTypeFilter filter = new QTransTypeFilter(condDLGDelegator, POBillType.PrayBill.getCode());
    filter.setTransTypeCode("po_praybill.ctrantypeid");
    filter.filter();
  }
}
