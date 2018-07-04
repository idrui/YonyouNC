package nc.ui.pu.m25.editor.utils;

import nc.itf.pu.m25.IDefaultPriceQuery;
import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoicePriceQueryVO;
import nc.vo.pu.m25.pub.InvoiceServiceUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.util.remotecallcombination.RemoteCallCombinatorEx;
import nc.vo.util.remotecallcombination.Token;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ͷ�������ֶΣ��ı�����ֶ�Ӱ������ֶ����¼���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-18 ����07:01:15
 */
public class TaxRateAndTypeUtil implements IPURemoteCallCombinator {

  private BillCardPanel cardPanel = null;

  private int[] setrows = null;

  private Token token = null;

  public TaxRateAndTypeUtil(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
  }

  /**
   * 
   * @param cardPanel ��Ƭ
   * @param setrows Ҫ�������
   */
  public TaxRateAndTypeUtil(BillCardPanel cardPanel, int[] setrows) {
    super();
    this.cardPanel = cardPanel;
    this.setrows = setrows;
  }

  /**
   * ����������������ͷ�������ֶΣ��ı�����ֶ�Ӱ������ֶ����¼��㡣Ŀǰ˰�ʣ���˰�������
   * <p>
   * <b>����˵��</b>
   * 
   * @param event
   * @param itemKey
   *          <p>
   * @since 6.0
   * @author tianft
   * @time 2010-3-18 ����07:02:51
   */
  public void changeBodyByHeader(String itemKey, Object newValue) {
    CardEditorHelper util = new CardEditorHelper(this.getCardPanel());
    int[] rows = util.getDifferentRows(itemKey, newValue, true);
    if (rows == null) {
      return;
    }
    // �����ֶα仯����
    new RelationCalculate().calculate(this.getCardPanel(), rows, itemKey);
  }

  /**
   * @return cardPanel
   */
  public BillCardPanel getCardPanel() {
    return this.cardPanel;
  }

  @Override
  public void prepare() {
    RemoteCallCombinatorEx rcc = RemoteCallCombinatorEx.getInstance();
    if (this.token != null) {
      rcc.update(this.token);
      this.doInvoke(rcc);
    }
    else {
      this.doInvoke(rcc);
      this.token = rcc.getToken();
    }
  }

  @Override
  public void process() {
    // ��ʱ�������߼�
  }

  /**
   * @param cardPanel
   *          Ҫ���õ� cardPanel
   */
  public void setCardPanel(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
  }

  /**
   * ��������������Ϊ����ѯ
   * <p>
   * <b>����˵��</b>
   * 
   * @param rows
   *          <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-15 ����09:17:03
   */
  public void setPlanPrice() {
    InvoicePriceQueryVO[] queryVOs = null;
    if (null != this.token) {
      try {
        queryVOs =
            (InvoicePriceQueryVO[]) RemoteCallCombinatorEx.getInstance()
                .getResult(this.token);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    else {
      queryVOs = this.prepareQueryVOs(this.setrows);
      try {
        queryVOs =
            InvoiceServiceUtil.getPriceQuerySerivce().queryPlanPrice(queryVOs);
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }
    if (null == queryVOs) {
      return;
    }
    CardEditorHelper helper = new CardEditorHelper(this.getCardPanel());
    for (InvoicePriceQueryVO priceVO : queryVOs) {
      helper.setBodyValue(priceVO.getItemIndex(), InvoiceItemVO.NPLANPRICE,
          priceVO.getNprice());
    }
  }

  private void doInvoke(RemoteCallCombinatorEx rcc) {
    IDefaultPriceQuery service = rcc.getService(IDefaultPriceQuery.class);
    InvoicePriceQueryVO[] queryVOs = this.prepareQueryVOs(this.setrows);
    try {
      service.queryPlanPrice(queryVOs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private InvoicePriceQueryVO[] prepareQueryVOs(int[] rows) {
    InvoicePriceQueryVO[] queryVOs = new InvoicePriceQueryVO[rows.length];
    CardEditorHelper helper = new CardEditorHelper(this.getCardPanel());
    String pk_org = helper.getHeadStringValue(InvoiceHeaderVO.PK_ORG);
    for (int i = 0; i < rows.length; i++) {
      queryVOs[i] = new InvoicePriceQueryVO();
      queryVOs[i].setPk_material(helper.getBodyStringValue(rows[i],
          InvoiceItemVO.PK_MATERIAL));
      queryVOs[i].setPk_org(pk_org);
      queryVOs[i].setItemIndex(rows[i]);
      // ������һ������Ԫ���ݳ�ʼ����ͷvo
      queryVOs[i].setPk_supplier(helper
          .getHeadStringValue(InvoiceHeaderVO.PK_SUPPLIER));
    }
    return queryVOs;
  }

}
