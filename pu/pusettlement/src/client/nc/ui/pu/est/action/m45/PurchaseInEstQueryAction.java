/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-17 ����11:10:18
 */
package nc.ui.pu.est.action.m45;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.est.m45.IPurchaseInEstQuery;
import nc.ui.pu.est.model.EstUIContext;
import nc.ui.pu.est.util.EstRefFilterUtil;
import nc.ui.pu.est.view.EstEditor;
import nc.ui.pu.est.view.EstEditorInitializer;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.ModelDataDescriptor;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.est.enumeration.QueryNonMetaFieldCode;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.pub.util.VOSortUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���ⵥ�ݹ���ȡ���ݹ�����ѯaction
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-17 ����11:10:18
 */
public class PurchaseInEstQueryAction extends DefaultQueryAction {

  private static final long serialVersionUID = -8310797053078005685L;

  private EstEditor editor;

  protected IQueryScheme queryScheme;

  /**
   * @return editor
   */
  public EstEditor getEditor() {
    return this.editor;
  }

  public IQueryScheme getQueryScheme() {
    return this.queryScheme;
  }

  /**
   * @param editor Ҫ���õ� editor
   */
  public void setEditor(EstEditor editor) {
    this.editor = editor;
  }

  /** ��ѯʱ���õ��ݹ����� */
  private QueryEstType getEstQryType(QuerySchemeProcessor qrySchemeProcessor) {

    QueryCondition vo =
        qrySchemeProcessor.getQueryCondition(QueryNonMetaFieldCode.festtype
            .name());
    // ���û�д������� ��������û��ֵ��Ĭ��Ϊ�����ݹ�
    if (vo == null || ArrayUtils.isEmpty(vo.getValues())) {
      return QueryEstType.GOODS_EST;
    }
    return QueryEstType.valueOf(vo.getValues()[0]);
  }

  private void initAfterQuery(PurchaseInEstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // ����ϼ����
    EstVOUtil.calculateTotal(vos);
    this.initContext(vos);
    this.initRef();
  }

  private void initBeforeQuery(QueryEstType queryEstType) {
    this.initContext(queryEstType);
    this.initUI();
  }

  private void initContext(QueryEstType queryEstType) {
    EstUIContext context = this.getContext();
    context.setEsttype(queryEstType);
  }

  private void initRef() {
    EstRefFilterUtil util =
        new EstRefFilterUtil(this.editor.getBillCardPanel());
    String pk_fiorg = this.getContext().getPk_fiorg();
    // ���幩Ӧ��
    util.filterSupplier(FeeEstVO.PK_SUPPLIER, pk_fiorg);
  }

  /** ��ѯ�Ƿ�����Ѿ����й������ݹ������� **/
  private UFBoolean isQryFeeEstimatedData(
      QuerySchemeProcessor qrySchemeProcessor) {
    QueryCondition vo =
        qrySchemeProcessor.getQueryCondition(QueryNonMetaFieldCode.bestfee
            .name());
    // �����ڸò�ѯ����
    if (vo == null || ArrayUtils.isEmpty(vo.getValues())) {
      return UFBoolean.FALSE;
    }
    return ValueUtils.getUFBoolean(vo.getValues()[0]);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.actions.QueryAction#executeQuery(java.lang.String)
   */
  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    this.queryScheme = queryScheme;
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    QueryEstType queryEstType = this.getEstQryType(qrySchemeProcessor);
    this.initBeforeQuery(queryEstType);
    UFBoolean includeFeeEstimated =
        this.isQryFeeEstimatedData(qrySchemeProcessor);
    IPurchaseInEstQuery srv =
        NCLocator.getInstance().lookup(IPurchaseInEstQuery.class);
    PurchaseInEstVO[] vos = null;
    try {
      if (QueryEstType.GOODS_EST == this.getContext().getEsttype()) {
        vos = srv.goodsEstQuery(queryScheme);
      }
      else {
        vos = srv.feeEstQuery(queryScheme, includeFeeEstimated);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    VOSortUtils.sortVOs(vos, PurchaseinFIHeaderVO.VBILLCODE);
    this.initAfterQuery(vos);

    String schemeName = queryScheme.getName();
    ModelDataDescriptor modelDataDescriptor = null;
    if (!StringUtil.isEmptyWithTrim(schemeName)) {
      modelDataDescriptor = new ModelDataDescriptor(schemeName);
    }
    else {
      modelDataDescriptor =
          new ModelDataDescriptor(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("pubapp_0", "0pubapp-0144")/* @res "��ѯ���" */);
    }

    this.getModel().initModel(vos, modelDataDescriptor);
  }

  protected EstUIContext getContext() {
    EstUIContext context = (EstUIContext) this.getModel().getContext();
    return context;
  }

  protected void initContext(PurchaseInEstVO[] vos) {
    EstUIContext context = this.getContext();
    // ��ǰ������֯
    context.setPk_fiorg(vos[0].getParentVO().getPk_financeorg());
    Map<String, String> purMap = new HashMap<String, String>();
    context.setIdPurOrgMap(purMap);
    for (PurchaseInEstVO vo : vos) {
      purMap.put(vo.getParentVO().getPk_stockps_b(), vo.getParentVO()
          .getPk_purchaseOrg());
    }
  }

  protected void initUI() {
    EstEditorInitializer initializer = new EstEditorInitializer(this.editor);
    // ��ʼ��ģ��һЩ����
    initializer.setEditable();
  }
}
