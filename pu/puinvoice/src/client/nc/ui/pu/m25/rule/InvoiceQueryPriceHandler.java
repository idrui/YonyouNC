package nc.ui.pu.m25.rule;

import nc.itf.pu.m25.IDefaultPriceQuery;
import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.m25.editor.utils.RelationCalculate;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoicePriceQueryVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.util.remotecallcombination.RemoteCallCombinatorEx;
import nc.vo.util.remotecallcombination.Token;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票询价相关
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-14 上午09:07:28
 */
public class InvoiceQueryPriceHandler implements IPURemoteCallCombinator {

  private CardEditorHelper cardEditorHelper = null;

  private BillCardPanel cardPanel = null;

  private Token hqhptoken;

  /** 原币无税单价 */
  private int[] origPriceChangeRows;

  /** 原币含税单价 */
  private int[] origTaxPriceChangeRows;

  /** 主本币单价 */
  private int[] priceChangeRows;

  private Token pricetoken;

  private int[] setrows;

  /** 主本币含税单价 */
  private int[] taxPriceChangeRows;

  public InvoiceQueryPriceHandler(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
    int rowCount = this.cardPanel.getRowCount();
    this.setrows = new int[rowCount];
    if (rowCount > 0) {
      for (int i = 0; i < rowCount; i++) {
        this.setrows[i] = i;
      }
    }
  }

  /**
   * 
   * @param cardPanel
   * @param setrows 要处理的行
   */
  public InvoiceQueryPriceHandler(BillCardPanel cardPanel, int[] setrows) {
    super();
    this.cardPanel = cardPanel;
    this.setrows = setrows;
  }

  public CardEditorHelper getCardEditorHelper() {
    if (this.cardEditorHelper == null) {
      this.cardEditorHelper = new CardEditorHelper(this.getCardPanel());
    }
    return this.cardEditorHelper;
  }

  /**
   * @return cardPanel
   */
  public BillCardPanel getCardPanel() {
    return this.cardPanel;
  }

  /**
   * @return origPriceChangeRows
   */
  public int[] getOrigPriceChangeRows() {
    if (this.origPriceChangeRows == null) {
      this.origPriceChangeRows = new int[0];
    }
    return this.origPriceChangeRows;
  }

  /**
   * @return origTaxPriceChangeRows
   */
  public int[] getOrigTaxPriceChangeRows() {
    if (this.origTaxPriceChangeRows == null) {
      this.origTaxPriceChangeRows = new int[0];
    }
    return this.origTaxPriceChangeRows;
  }

  /**
   * @return origPriceChangeRows
   */
  public int[] getPriceChangeRows() {
    if (this.priceChangeRows == null) {
      this.priceChangeRows = new int[0];
    }
    return this.priceChangeRows;
  }

  /**
   * @return
   */
  public int[] getTaxPriceChangeRows() {
    if (this.taxPriceChangeRows == null) {
      this.taxPriceChangeRows = new int[0];
    }
    return this.taxPriceChangeRows;
  }

  /**
   * 优质优价处理
   * 
   * @param rows
   */
  public void handleHqHpPrice() {
    InvoicePriceQueryVO[] pqvos = this.getHqHpPriceQueryVO();
    if (ArrayUtils.isEmpty(pqvos)) {
      return;
    }
    try {
      pqvos =
          (InvoicePriceQueryVO[]) RemoteCallCombinatorEx.getInstance()
              .getResult(this.hqhptoken);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    this.matchPrice(pqvos);
  }

  /**
   * @param rows
   */
  public void handleQueryPrice() {
    InvoicePriceQueryVO[] pqvos = this.getPriceQueryVO();
    if (ArrayUtils.isEmpty(pqvos)) {
      return;
    }
    try {
      pqvos =
          (InvoicePriceQueryVO[]) RemoteCallCombinatorEx.getInstance()
              .getResult(this.pricetoken);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    this.matchPrice(pqvos);
  }

  @Override
  public void prepare() {
    this.prepareHqHpPrice();// 优质优价询价准备
    this.prepareQueryPrice();// 采购发票参数询价准备
  }

  public void prepareHqHpPrice() {
    InvoicePriceQueryVO[] pqvos = this.getHqHpPriceQueryVO();
    if (ArrayUtils.isEmpty(pqvos)) {
      return;
    }
    RemoteCallCombinatorEx rcc = RemoteCallCombinatorEx.getInstance();
    IDefaultPriceQuery service = rcc.getService(IDefaultPriceQuery.class);
    if (null != this.hqhptoken) {
      rcc.update(this.hqhptoken);
    }
    try {
      service.queryHqHpPrice(pqvos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (null == this.hqhptoken) {
      this.hqhptoken = rcc.getToken();
    }
  }

  public void prepareQueryPrice() {
    InvoicePriceQueryVO[] pqvos = this.getPriceQueryVO();
    if (ArrayUtils.isEmpty(pqvos)) {
      return;
    }
    RemoteCallCombinatorEx rcc = RemoteCallCombinatorEx.getInstance();
    IDefaultPriceQuery service = rcc.getService(IDefaultPriceQuery.class);
    if (null != this.pricetoken) {
      rcc.update(this.pricetoken);
    }
    try {
      service.queryPriceBySysPara(pqvos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (null == this.pricetoken) {
      this.pricetoken = rcc.getToken();
    }
  }

  @Override
  public void process() {
    // 暂时没有逻辑
  }

  /**
   * @param origPriceChangeRows 要设置的 origPriceChangeRows
   */
  public void setOrigPriceChangeRows(int[] origPriceChangeRows) {
    this.origPriceChangeRows = origPriceChangeRows;
  }

  /**
   * @param origTaxPriceChangeRows 要设置的 origTaxPriceChangeRows
   */
  public void setOrigTaxPriceChangeRows(int[] origTaxPriceChangeRows) {
    this.origTaxPriceChangeRows = origTaxPriceChangeRows;
  }

  /**
   * @param priceChangeRows 要设置的 priceChangeRows
   */
  public void setPriceChangeRows(int[] priceChangeRows) {
    this.priceChangeRows = priceChangeRows;
  }

  public void setTaxPriceChangeRows(int[] taxPriceChangeRows) {
    this.taxPriceChangeRows = taxPriceChangeRows;
  }

  /**
   * 价格联动计算
   */
  private void afterPriceChange() {
    // 无税单价变化
    if (!ArrayUtils.isEmpty(this.getOrigPriceChangeRows())) {
      new RelationCalculate().calculate(this.getCardPanel(),
          this.getOrigPriceChangeRows(), InvoiceItemVO.NORIGPRICE);
    }
    // 含税单价变化
    if (!ArrayUtils.isEmpty(this.getOrigTaxPriceChangeRows())) {
      new RelationCalculate().calculate(this.getCardPanel(),
          this.getOrigTaxPriceChangeRows(), InvoiceItemVO.NORIGTAXPRICE);
    }

    // 主本币单价变化
    if (!ArrayUtils.isEmpty(this.getPriceChangeRows())) {
      new RelationCalculate().calculate(this.getCardPanel(),
          this.getPriceChangeRows(), InvoiceItemVO.NPRICE);
    }

    // 主本币含税单价变化
    if (!ArrayUtils.isEmpty(this.getTaxPriceChangeRows())) {
      new RelationCalculate().calculate(this.getCardPanel(),
          this.getTaxPriceChangeRows(), InvoiceItemVO.NTAXPRICE);
    }

  }

  private boolean calByPrice(InvoicePriceQueryVO queryVO, String priceKey) {
    CardEditorHelper helper = new CardEditorHelper(this.getCardPanel());
    UFDouble curPrice =
        helper.getBodyUFDoubleValue(queryVO.getItemIndex(), priceKey);
    UFDouble queryPrice = (UFDouble) queryVO.getAttributeValue(priceKey);
    // 价格为空或者与就价格相等，不处理
    if (UFDouble.ZERO_DBL.equals(MathTool.nvl(queryPrice))
        || queryPrice.equals(curPrice)) {
      return false;
    }
    // 不可抵扣税率
    if (queryVO.getNNoSubTaxRate() != null) {
      helper.setBodyValue(queryVO.getItemIndex(), InvoiceItemVO.NNOSUBTAXRATE,
          queryVO.getNNoSubTaxRate());
    }
    // 税率
    if (queryVO.getNtaxrate() != null) {
      helper.setBodyValue(queryVO.getItemIndex(), InvoiceItemVO.NTAXRATE,
          queryVO.getNtaxrate());
    }
    // 扣税类别
    if (queryVO.getFtaxtypeflag() != null) {
      helper.setBodyValue(queryVO.getItemIndex(), InvoiceItemVO.FTAXTYPEFLAG,
          queryVO.getFtaxtypeflag());
    }

    helper.setBodyValue(queryVO.getItemIndex(), priceKey, queryPrice);

    if (InvoiceItemVO.NORIGPRICE.equals(priceKey)) {
      this.setOrigPriceChangeRows(ArrayUtils.add(this.getOrigPriceChangeRows(),
          queryVO.getItemIndex()));
    }
    else if (InvoiceItemVO.NORIGTAXPRICE.equals(priceKey)) {
      this.setOrigTaxPriceChangeRows(ArrayUtils.add(
          this.getOrigTaxPriceChangeRows(), queryVO.getItemIndex()));
    }
    else if (InvoiceItemVO.NPRICE.equals(priceKey)) {
      this.setPriceChangeRows(ArrayUtils.add(this.getPriceChangeRows(),
          queryVO.getItemIndex()));
    }
    else if (InvoiceItemVO.NTAXPRICE.equals(priceKey)) {
      this.setTaxPriceChangeRows(ArrayUtils.add(this.getTaxPriceChangeRows(),
          queryVO.getItemIndex()));
    }

    return true;
  }

  private InvoicePriceQueryVO[] getHqHpPriceQueryVO() {
    if (ArrayUtils.isEmpty(this.setrows)) {
      return null;
    }
    InvoiceVO vo = this.getPriceVO(this.setrows);
    // 组合询价vo，找来源入库单的行
    InvoicePriceQueryVO[] queryVOs =
        InvoiceVOUtil.getHqHpQueryVO(vo, this.setrows);
    return queryVOs;
  }

  /**
   * 询价表头vo，只给必要字段赋值
   * 
   * @return
   */
  private InvoiceHeaderVO getPriceHeaderVO() {
    InvoiceHeaderVO header = new InvoiceHeaderVO();
    String[] headerAttr = InvoiceVOUtil.PRICE_QUERY_HEADER_ATTR;
    for (int i = 0; i < headerAttr.length; i++) {
      header.setAttributeValue(headerAttr[i], this.getCardEditorHelper()
          .getHeadValue(headerAttr[i]));
    }
    return header;
  }

  /**
   * 询价表体vo，只给必要字段赋值
   * 
   * @param rows
   * @return
   */
  private InvoiceItemVO[] getPriceItemVOs(int[] rows) {
    InvoiceItemVO[] items = new InvoiceItemVO[rows.length];
    String[] bodyAttr = InvoiceVOUtil.PRICE_QUERY_BODY_ATTR;
    for (int i = 0; i < rows.length; i++) {
      items[i] = new InvoiceItemVO();
      for (String attr : bodyAttr) {
        items[i].setAttributeValue(attr, this.getCardEditorHelper()
            .getBodyValue(rows[i], attr));
      }
    }
    return items;
  }

  /**
   * 获取价格优先参数PO28
   * 
   * @return
   */
  private boolean getPricePriority() {
    CardEditorHelper helper = new CardEditorHelper(this.getCardPanel());
    String pk_purchaseorg =
        helper.getHeadStringValue(InvoiceHeaderVO.PK_PURCHASEORG);
    return PUSysParamUtil.getPO28For25(pk_purchaseorg);
  }

  private InvoicePriceQueryVO[] getPriceQueryVO() {
    if (ArrayUtils.isEmpty(this.setrows)) {
      return null;
    }
    InvoiceVO vo = this.getPriceVO(this.setrows);
    // 组合询价vo，过滤物料为空的行
    InvoicePriceQueryVO[] queryVOs =
        InvoiceVOUtil.getInvoicePriceQuerVO(vo, this.setrows);
    return queryVOs;
  }

  private InvoiceVO getPriceVO(int[] rows) {
    InvoiceVO vo = new InvoiceVO();
    vo.setParentVO(this.getPriceHeaderVO());
    vo.setChildrenVO(this.getPriceItemVOs(rows));
    return vo;
  }

  /**
   * 无税价格优先时的处理
   * 
   * @param queryVOs
   */
  private void handlePricePrior(InvoicePriceQueryVO[] queryVOs) {
    this.HandleTaxPriceChange(queryVOs, false);
  }

  /**
   * 询价处理
   * 
   * @param queryVOs
   * @param isTaxPricePrior 是否含税优先
   */
  private void HandleTaxPriceChange(InvoicePriceQueryVO[] queryVOs,
      boolean isTaxPricePrior) {

    String priceItemKey =
        isTaxPricePrior ? InvoiceItemVO.NORIGTAXPRICE
            : InvoiceItemVO.NORIGPRICE;

    for (int i = 0; i < queryVOs.length; i++) {
      UFDouble queryPrice =
          (UFDouble) queryVOs[i].getAttributeValue(priceItemKey);
      // 查询到的主含税/主无税单价为空，则比较主本币无税单价
      if (UFDouble.ZERO_DBL.equals(MathTool.nvl(queryPrice))) {
        // 先计算主本币无税单价
        if (!this.calByPrice(queryVOs[i], InvoiceItemVO.NPRICE)) {
          // 计算主本币含税单价
          this.calByPrice(queryVOs[i], InvoiceItemVO.NTAXPRICE);
        }
      }
      // 查询到的主含税/主无税单价不为空
      else {
        this.calByPrice(queryVOs[i], priceItemKey);
      }
    }
  }

  /**
   * 含税价格优先时的处理
   * 
   * @param queryVOs
   */
  private void HandleTaxPricePrior(InvoicePriceQueryVO[] queryVOs) {
    this.HandleTaxPriceChange(queryVOs, true);
  }

  /**
   * 价格匹配
   * 
   * @param queryVOs
   */
  private void matchPrice(InvoicePriceQueryVO[] queryVOs) {
    // 含税优先
    if (this.getPricePriority()) {
      this.HandleTaxPricePrior(queryVOs);
    }
    // 无税优先
    else {
      this.handlePricePrior(queryVOs);
    }
    // 含税单价/无税单价变化处理
    this.afterPriceChange();
  }

}
