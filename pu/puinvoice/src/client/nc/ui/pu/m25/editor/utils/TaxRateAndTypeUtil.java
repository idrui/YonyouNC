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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表头表体有字段，改变表体字段影响表体字段重新计算
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-18 下午07:01:15
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
   * @param cardPanel 卡片
   * @param setrows 要处理的行
   */
  public TaxRateAndTypeUtil(BillCardPanel cardPanel, int[] setrows) {
    super();
    this.cardPanel = cardPanel;
    this.setrows = setrows;
  }

  /**
   * 方法功能描述：表头表体有字段，改变表体字段影响表体字段重新计算。目前税率，扣税类别在用
   * <p>
   * <b>参数说明</b>
   * 
   * @param event
   * @param itemKey
   *          <p>
   * @since 6.0
   * @author tianft
   * @time 2010-3-18 下午07:02:51
   */
  public void changeBodyByHeader(String itemKey, Object newValue) {
    CardEditorHelper util = new CardEditorHelper(this.getCardPanel());
    int[] rows = util.getDifferentRows(itemKey, newValue, true);
    if (rows == null) {
      return;
    }
    // 表体字段变化后处理
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
    // 暂时不处理逻辑
  }

  /**
   * @param cardPanel
   *          要设置的 cardPanel
   */
  public void setCardPanel(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
  }

  /**
   * 方法功能描述：为表体询
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   *          <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-15 上午09:17:03
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
      // 多设置一个便于元数据初始化表头vo
      queryVOs[i].setPk_supplier(helper
          .getHeadStringValue(InvoiceHeaderVO.PK_SUPPLIER));
    }
    return queryVOs;
  }

}
