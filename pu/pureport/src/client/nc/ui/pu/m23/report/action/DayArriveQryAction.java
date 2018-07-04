package nc.ui.pu.m23.report.action;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.bs.ic.icreport.pub.ICReportConditionUtils;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.ui.pu.m23.report.dlg.DayArriveQryDlgInit;
import nc.ui.pu.report.pub.action.PURptDefaultQueryAction;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.arrival.PuArrivalQryInfoPara;
import nc.vo.pu.report.scale.m23.DayArriveScaleStrategy;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.query.ConditionVO;
import nc.vo.querytemplate.QueryTemplateConvertor;

import org.apache.commons.lang.ArrayUtils;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * �ɹ�����-�յ��������ѯ
 * 
 * @since 6.3
 * @version 2012-8-11 ����10:38:15
 * @author fanly3
 */
public class DayArriveQryAction extends PURptDefaultQueryAction {
  /** �ƻ�����ʱ�� */
  public static final String DPLANARRVDATE = "dplanarrvdate";

  /** �����֯ */
  public static final String PK_STOCKORG = "pk_stockorg";

  /** ���ϱ��� **/
  private static final String BD_MATERIAL_CODE = "bd_material_v.code";

  /** �������� **/
  private static final String BD_MATERIAL_NAME = "bd_material_v.name";

  /** ��Ӧ�̱��� **/
  private static final String BD_SUPPLIER_CODE = "bd_supplier.code";

  /** ��Ӧ������ **/
  private static final String BD_SUPPLIER_NAME = "bd_supplier.name";

  /** ����Ԫ����ȫ·�� */
  private static final String MATERIAL_META_PATH = "pk_order_b.pk_srcmaterial";

  @Override
  public IQueryCondition doQueryByScheme(Container parent, IContext context,
      AbsAnaReportModel reportModel, IQueryScheme queryScheme) {
    if (queryScheme == null) {
      return new BaseQueryCondition(false);
    }
    Object obj = queryScheme.get(IQueryScheme.KEY_FILTERS);
    if (obj != null) {
      IFilter[] filters = (IFilter[]) obj;
      List<IFilter> list = Arrays.asList(filters);
      String wherePart = queryScheme.getWhereSQLOnly();
      ConditionVO[] generalConds =
          QueryTemplateConvertor.convertIFilter2ConditionVO(list);
      this.processQueryPara(context, generalConds, wherePart);
    }
    ICReportConditionUtils.setDescription(context, queryScheme);
    IQueryCondition condition = this.createQueryCondition(context);
    this.setCondOrgAttribute(context, queryScheme);
    return condition;
  }

  @Override
  public IQueryConditionDLGInitializer getQryCondDLGInitializer() {
    return new DayArriveQryDlgInit();
  }

  /**
   * �����ѯ����
   * 
   * @param context ������context����
   * @param conds ��ѯ�Ի�����ѡ���˵���������
   * @param wherePart ��ѯ�Ի�����where����
   */
  private void processQueryPara(IContext context, ConditionVO[] conds,
      String wherePart) {
    if (!ArrayUtils.isEmpty(conds)) {
      List<ConditionVO> generalList = new ArrayList<ConditionVO>();
      List<ConditionVO> orderBConds = new ArrayList<ConditionVO>();
      List<ConditionVO> orderBB1Conds = new ArrayList<ConditionVO>();
      for (ConditionVO cond : conds) {
        String fieldCode = cond.getFieldCode();
        // �Զ�������֯����
        if (DayArriveQryAction.PK_STOCKORG.equals(fieldCode)) {
          // û�е����ƻ�ƥ��ɹ��������ϵ��ջ������֯
          cond.setFieldCode(PUEntity.M21_B_TABLE + "."
              + OrderItemVO.PK_ARRVSTOORG);
          orderBConds.add(cond);

          // �е����ƻ�ƥ�䵽���ƻ����ջ������֯
          ConditionVO orderBOrg = (ConditionVO) cond.clone();
          orderBOrg.setFieldCode(PUEntity.M21_BB1_TABLE + "."
              + OrderItemVO.PK_ARRVSTOORG);
          orderBB1Conds.add(orderBOrg);
          continue;
        }
        // �ƻ���������
        if (DayArriveQryAction.DPLANARRVDATE.equals(fieldCode)) {
          // û�е����ƻ�ƥ��ɹ��������ϵļƻ���������
          cond.setFieldCode(PUEntity.M21_B_TABLE + "."
              + OrderItemVO.DPLANARRVDATE);
          orderBConds.add(cond);
          // �е����ƻ�ƥ�䵽���ƻ��ļƻ���������
          ConditionVO orderBOrg = (ConditionVO) cond.clone();
          orderBOrg.setFieldCode(PUEntity.M21_BB1_TABLE + "."
              + OrderReceivePlanVO.DPLANARRVDATE);
          orderBB1Conds.add(orderBOrg);
          continue;
        }
        generalList.add(cond);
      }
      context.setAttribute(PuQueryInfoPara.QUERY_CONDS,
          generalList.toArray(new ConditionVO[0]));
      context.setAttribute(PuArrivalQryInfoPara.ORDER_B_COND,
          orderBConds.toArray(new ConditionVO[0]));
      context.setAttribute(PuArrivalQryInfoPara.ORDER_BB1_COND,
          orderBB1Conds.toArray(new ConditionVO[0]));
    }
  }

  @Override
  protected Map<String, String> getColumnMapping() {
    // key=��ѯģ�� value=Ԫ����ȫ·��(��ͷ�ֶβ��üӱ�ͷ����ǰ׺)
    Map<String, String> columnMapping = new HashMap<String, String>();
    columnMapping.put(DayArriveQryAction.BD_MATERIAL_CODE,
        DayArriveQryAction.MATERIAL_META_PATH);
    columnMapping.put(DayArriveQryAction.BD_MATERIAL_NAME,
        DayArriveQryAction.MATERIAL_META_PATH);
    columnMapping.put(DayArriveQryAction.BD_SUPPLIER_CODE,
        OrderHeaderVO.PK_SUPPLIER);
    columnMapping.put(DayArriveQryAction.BD_SUPPLIER_NAME,
        OrderHeaderVO.PK_SUPPLIER);

    return columnMapping;
  }

  @Override
  protected Class<? extends ISuperVO> getMainClass() {
    return OrderHeaderVO.class;
  }

  @Override
  protected String getMainOrgKey() {
    // ��ѯģ��������֯�ֶΣ�ƽ̨�ᰴ�ո��ֶ����ù���
    return DayArriveQryAction.PK_STOCKORG;
  }

  @Override
  protected ICRptBaseScalePrcStrategy getScaleStrategy() {
    return new DayArriveScaleStrategy();
  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {
    if (condition == null || !(condition instanceof BaseQueryCondition)) {
      return condition;
    }
    String wherePart = queryDlg.getWhereSQL();
    ConditionVO[] generalConds = this.getDelegator().getGeneralCondtionVOs();
    this.processQueryPara(context, generalConds, wherePart);
    IQueryCondition condition1 = this.createQueryCondition(context);
    this.setCondOrgAttribute(context);
    return condition1;// super.setQueryResult(condition, queryDlg, reportModel,
                      // context);
  }
}
