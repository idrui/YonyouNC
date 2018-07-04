package nc.ui.pu.m21.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.pu.m21.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.ClientContext;
import nc.ui.pu.reference.ct.Z2CTUIServices;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.ct.entity.CtRelatingVO;
import nc.vo.ct.purdaily.entity.CtPubillViewVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.NumValueSetter;
import nc.vo.pu.m21.rule.PlanArriveDate;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.uif2.LoginContext;
import nc.vo.util.remotecallcombination.Token;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>主要职责是关联合同
 * <li>关联合同后，需要处理合同相关的字段的可编辑性。
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 下午12:16:08
 *       tianft 这里是弹出界面的形式，不能走远程调用合并
 */
public class ContractLinker {
  // ////

  private boolean isCTEnable;

  private LoginContext loginContext = null;

  private BillCardPanel panel;

  private Token token;

  public ContractLinker(BillCardPanel blp, LoginContext loginContext) {
    this.panel = blp;
    this.loginContext = loginContext;
    this.isCTEnable = SysInitGroupQuery.isCTEnabled();
  }

  public ContractLinker(CardBodyAfterEditEvent event) {
    this.panel = event.getBillCardPanel();
    this.loginContext = event.getContext();
    this.isCTEnable = ((ClientContext) this.loginContext).isStart(NCModule.CT);
  }

  public ContractLinker(CardHeadTailAfterEditEvent event) {
    this.panel = event.getBillCardPanel();
    this.loginContext = event.getContext();
    this.isCTEnable = ((ClientContext) this.loginContext).isStart(NCModule.CT);
  }

  /**
   * 方法功能描述：关联合同，不再控制单价可编辑性，由合同“单价控制方式”回写控制。
   * <p>
   * <b>参数说明</b>
   * 
   * @param irows
   * @param mustSetCnt
   * @param pasteFlag <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-18 下午12:35:29
   */
  public void contractLink(Integer[] irows, boolean mustSetCnt,
      boolean pasteFlag) {
    if (ArrayUtils.isEmpty(irows)) {
      return;
    }
    Integer[] rows = irows;

    if (!this.isCTEnable) {
      return;
    }

    String pk_org =
        (String) this.panel.getHeadItem(OrderHeaderVO.PK_ORG).getValueObject();
    // 赠品不关联合同（手工参照合同除外）
    if (!mustSetCnt) {
      rows = this.setDefaultPriceAndGetLargessLines(rows);
    }
    // 所有赠品行均为赠品，直接返回，不作关联合同处理！
    if (ArrayUtils.isEmpty(rows)) {
      return;
    }

    // 如果是行复制后的粘贴，不询合同
    if (pasteFlag) {
      rows = this.getNoSourceCtLines(rows);
    }
    // 所有行均为Z2参照，直接返回，不作关联合同处理！
    if (ArrayUtils.isEmpty(rows)) {
      return;
    }

    CtRelatingVO[] rVOs = this.getCtRelatingVOs(rows);
    CtPubillViewVO[] ctViewVOs = null;
    try {
      ctViewVOs = Z2CTUIServices.relateCT(rVOs, this.token);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    if (ArrayUtils.isEmpty(ctViewVOs)) {
      this.setEditableWhenCtNull(rows);
    }

    // 重新安排顺序
    ctViewVOs = this.getSerialViewVOs(rows, ctViewVOs);

    // 用来取非合同价的行
    Set<Integer> notCntPriceSet = new HashSet<Integer>();
    PricePriority pricePriority = PUSysParamUtil.getPO28(pk_org);
    // 为效率考虑，外层做个缓存
    int rowcnt = this.panel.getRowCount();
    String[] rowno = new String[rowcnt];
    for (int i = 0; i < rowno.length; i++) {
      rowno[i] = (String) this.panel.getBodyValueAt(i, OrderItemVO.CROWNO);
    }

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < rows.length; ++i) {
      int k = -1;
      for (int j = 0; j < rowcnt; ++j) {
        String rownoOnPanel = rowno[j];
        if (rownoOnPanel != null && ctViewVOs[i] != null
            && rownoOnPanel.equalsIgnoreCase(ctViewVOs[i].getCrowno())) {
          k = j;
          break;
        }
      }
      if (-1 == k) {
        notCntPriceSet.add(rows[i]);
        continue;
      }

      int row = k;

      String pk_material =
          (String) this.panel.getBodyValueAt(row, OrderItemVO.PK_MATERIAL);
      UFDate dbilldate =
          (UFDate) this.panel.getHeadItem(OrderHeaderVO.DBILLDATE)
              .getValueObject();
      if (null == pk_material || null == dbilldate) {
        this.setCellEditableAndCntValueWhenNull(Integer.valueOf(row));
        continue;
      }

      // 合同号始终可编辑
      this.panel.setCellEditable(row, OrderItemVO.CCONTRACTID, this.panel
          .getBodyItem("material", OrderItemVO.CCONTRACTID).isEdit());

      // 合同ID 合同号
      this.setContractValue(ctViewVOs[i], Integer.valueOf(row));

      // 设置物料辅助属性
      this.setVfree(ctViewVOs[i], row);
      
      // 单位、数量
      this.setQtUnit(ctViewVOs[i], row);

      String rowNo = this.setNum(ctViewVOs[i], row);
      if (rowNo != null) {
        builder.append("[");
        builder.append(rowNo);
        builder.append("]");
      }

      // 设置不可抵扣税率
      this.setNosubtaxrate(ctViewVOs[i], row);
      // 优质优价
      this.setCqpbaseschemeid(ctViewVOs[i], Integer.valueOf(row));

      // 币种ID
      this.setOrigcurrencyValue(ctViewVOs[i], Integer.valueOf(row));

      // 价格
      this.setPriceValue(ctViewVOs[i], Integer.valueOf(row), pricePriority,
          notCntPriceSet);
    }

    int[] plandaterows = ArrayUtils.toPrimitive(rows);
    CardEditorHelper card = new CardEditorHelper(this.panel);
    PlanArriveDate daterule = new PlanArriveDate(card);
    daterule.setPlanArriveDate(plandaterows);

    if (builder.length() > 0) {
      ShowStatusBarMsgUtil.showErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("40040400",
              "1400404000059")/* @res "关联合同出错" */,
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("40040400",
              "1400404000060", null, new String[] {
                builder.toString()
              })/* @res "第{0}行合同累计订单数量大于合同数量,订单数量置为0,请手工修改数量." */,
          this.loginContext);
    }
  }

  /**
   * 方法功能描述：寻找某些行的合同
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-18 下午02:45:20
   */
  private CtRelatingVO[] getCtRelatingVOs(Integer[] rows) {
    if (ArrayUtils.isEmpty(rows)) {
      return null;
    }

    // 主组织
    String pk_org =
        (String) this.panel.getHeadItem(OrderHeaderVO.PK_ORG).getValueObject();
    // 供应商
    String cvendor =
        (String) this.panel.getHeadItem(OrderHeaderVO.PK_SUPPLIER)
            .getValueObject();
    // 订单日期
    UFDate dbilldate =
        (UFDate) this.panel.getHeadItem(OrderHeaderVO.DBILLDATE)
            .getValueObject();
    // 币种
    String corigcurrencyid =
        (String) this.panel.getHeadItem(OrderHeaderVO.CORIGCURRENCYID)
            .getValueObject();
    // 运输方式
    String pk_transporttype =
        (String) this.panel.getHeadItem(OrderHeaderVO.PK_TRANSPORTTYPE)
            .getValueObject();

    // 有供应商及日期时才需重新寻找合同
    if (StringUtil.isEmptyWithTrim(pk_org)
        || StringUtil.isEmptyWithTrim(cvendor)
        || ObjectUtil.isEmptyWithTrim(dbilldate)) {
      return null;
    }

    Set<CtRelatingVO> ctRelatingVOSet = new HashSet<CtRelatingVO>();
    for (Integer row : rows) {
      CtRelatingVO ctRelatingVO = new CtRelatingVO();
      ctRelatingVO.setCurrency(corigcurrencyid);
      ctRelatingVO.setCvendor(cvendor);
      ctRelatingVO.setDate(dbilldate);
      String material =
          (String) this.panel.getBodyValueAt(row.intValue(),
              OrderItemVO.PK_MATERIAL);
      if (null == material) {
        continue;
      }
      ctRelatingVO.setMaterial(material);
      // //需求确认，订单关联合同存在一些历史问题，关联合同的时候不用报价单位维度
      // // 报价单位
      // String cqtunit =
      // (String) this.panel.getBodyValueAt(row.intValue(),
      // OrderItemVO.CQTUNITID);
      // ctRelatingVO.setCqtunit(cqtunit);
      ctRelatingVO.setPk_org(pk_org);
      String crowno =
          (String) this.panel
              .getBodyValueAt(row.intValue(), OrderItemVO.CROWNO);
      ctRelatingVO.setSourerowno(crowno);
      String cproductorid =
          (String) this.panel.getBodyValueAt(row.intValue(),
              OrderItemVO.CPRODUCTORID);
      ctRelatingVO.setCproductorid(cproductorid);
      String cqualitylevelid =
          (String) this.panel.getBodyValueAt(row.intValue(),
              OrderItemVO.CQUALITYLEVELID);
      ctRelatingVO.setCqualitylevelid(cqualitylevelid);
      String cdevareaid =
          (String) this.panel.getBodyValueAt(row.intValue(),
              OrderItemVO.CDEVAREAID);
      ctRelatingVO.setReceiveaddress(cdevareaid);
      ctRelatingVO.setCtranspmodeid(pk_transporttype);

      ctRelatingVOSet.add(ctRelatingVO);
    }

    if (!ctRelatingVOSet.isEmpty()) {
      return ctRelatingVOSet.toArray(new CtRelatingVO[ctRelatingVOSet.size()]);
    }

    return null;
  }

  /**
   * 方法功能描述：过滤有源且为合同的行索引,主要考虑复制行复制来源与关联到合同的一致性
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-18 下午01:45:38
   */
  private Integer[] getNoSourceCtLines(Integer[] rows) {
    if (ArrayUtils.isEmpty(rows)) {
      return null;
    }

    String sourceType = null;
    Set<Integer> notCntIndex = new HashSet<Integer>();
    for (Integer row : rows) {
      if (null == row) {
        continue;
      }

      sourceType =
          (String) this.panel.getBodyValueAt(row.intValue(),
              OrderItemVO.CSOURCETYPECODE);
      if (ObjectUtils.equals(CTBillType.PurDaily.getCode(), sourceType)) {
        continue;
      }

      notCntIndex.add(row);
    }

    if (!notCntIndex.isEmpty()) {
      return notCntIndex.toArray(new Integer[0]);
    }

    return null;
  }

  /**
   * 方法功能描述：得到根据采购价格优先规格的单价
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-19 下午01:55:52
   */
  private UFDouble getOrgPriceValueByPricePriority(CtPubillViewVO ctViewVO,
      PricePriority pricePriority) {
    // 含税优先
    if (PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(pricePriority)) {
      return ctViewVO.getNorigtaxprice();
    }
    // 无税优先
    else if (PricePriority.PRICE_PRIOR_TO_TAXPRICE.equals(pricePriority)) {
      return ctViewVO.getNorigprice();
    }

    return null;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param pricePriority
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-19 下午02:00:58
   */
  private String getPriceFieldByPricePriority(PricePriority pricePriority) {
    // 含税优先 主单位
    if (PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(pricePriority)) {
      return OrderItemVO.NORIGTAXPRICE;
    }
    // 无税优先
    else if (PricePriority.PRICE_PRIOR_TO_TAXPRICE.equals(pricePriority)) {
      return OrderItemVO.NORIGPRICE;
    }

    return null;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param pricePriority
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-24 下午02:01:00
   */
  private String getQtPriceFieldByPricePriority(PricePriority pricePriority) {
    // 含税优先 报价
    if (PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(pricePriority)) {
      return OrderItemVO.NQTORIGTAXPRICE;
    }
    // 无税优先
    else if (PricePriority.PRICE_PRIOR_TO_TAXPRICE.equals(pricePriority)) {
      return OrderItemVO.NQTORIGPRICE;
    }

    return null;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param ctViewVO
   * @param pricePriority
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-24 下午01:56:17
   */
  private UFDouble getQtPriceValueByPricePriority(CtPubillViewVO ctViewVO,
      PricePriority pricePriority) {
    // 含税优先
    if (PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(pricePriority)) {
      return ctViewVO.getNqtorigtaxprice();
    }
    // 无税优先
    else if (PricePriority.PRICE_PRIOR_TO_TAXPRICE.equals(pricePriority)) {
      return ctViewVO.getNqtorigprice();
    }

    return null;
  }

  /**
   * 方法功能描述：使选择的合同和行对应
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   * @param ctViewVOs
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-21 上午11:19:03
   */
  private CtPubillViewVO[] getSerialViewVOs(Integer[] rows,
      CtPubillViewVO[] ctViewVOs) {
    CtPubillViewVO[] retViewVOs = new CtPubillViewVO[rows.length];
    if (ArrayUtils.isEmpty(ctViewVOs)) {
      return retViewVOs;
    }
    if (ctViewVOs.length > 1) {
      Map<String, CtPubillViewVO> map = new HashMap<String, CtPubillViewVO>();
      for (CtPubillViewVO ctViewVO : ctViewVOs) {
        map.put(ctViewVO.getCrowno(), ctViewVO);
      }

      for (int i = 0; i < rows.length; ++i) {
        String crowno =
            (String) this.panel.getBodyValueAt(rows[i].intValue(),
                OrderItemVO.CROWNO);
        retViewVOs[i] = map.get(crowno);
      }
    }
    else {
      retViewVOs[0] = ctViewVOs[0];
    }
    return retViewVOs;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-24 下午04:16:08
   */
  // private void relationCalculate(Integer[] rows) {
  // RelationCalculateAfterQuoter tool =
  // new RelationCalculateAfterQuoter(this.panel);
  // tool.relationCalculate(rows);
  // }
  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-6-19 下午02:28:20
   */
  private void setCellEditableAndCntValueWhenNull(Integer row) {
    // 合同号
    this.panel.setCellEditable(row.intValue(), OrderItemVO.VCONTRACTCODE,
        false, "material");
    // 币种
    this.panel.setCellEditable(row.intValue(), OrderItemVO.CORIGCURRENCYID,
        this.panel.getBodyItem("material", OrderItemVO.CORIGCURRENCYID)
            .isEdit(), "material");
    // 合同头ID
    this.panel.setBodyValueAt(null, row.intValue(), OrderItemVO.CCONTRACTID,
        "material");
    // 合同行ID
    this.panel.setBodyValueAt(null, row.intValue(), OrderItemVO.CCONTRACTROWID,
        "material");
    // 合同号
    this.panel.setBodyValueAt(null, row.intValue(), OrderItemVO.VCONTRACTCODE,
        "material");
  }

  /**
   * 方法功能描述：合同
   * <p>
   * <b>参数说明</b>
   * 
   * @param ctViewVO
   * @param row <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-19 下午02:24:43
   */
  private void setContractValue(CtPubillViewVO ctViewVO, Integer row) {
    if (null == ctViewVO) {
      // 合同头ID
      this.panel.setBodyValueAt(null, row.intValue(), OrderItemVO.CCONTRACTID,
          "material");
      // 合同行ID
      this.panel.setBodyValueAt(null, row.intValue(),
          OrderItemVO.CCONTRACTROWID, "material");
      // 合同号
      this.panel.setBodyValueAt(null, row.intValue(),
          OrderItemVO.VCONTRACTCODE, "material");
    }
    else {
      // 合同头ID
      this.panel.setBodyValueAt(ctViewVO.getPk_ct_pu(), row.intValue(),
          OrderItemVO.CCONTRACTID, "material");
      // 合同行ID
      this.panel.setBodyValueAt(ctViewVO.getPk_ct_pu_b(), row.intValue(),
          OrderItemVO.CCONTRACTROWID, "material");
      // 合同号
      this.panel.setBodyValueAt(ctViewVO.getVbillcode(), row.intValue(),
          OrderItemVO.VCONTRACTCODE, "material");
      this.panel.getBillModel().loadLoadRelationItemValue(row.intValue(),
          OrderItemVO.CCONTRACTID);
    }
  }

  private void setCqpbaseschemeid(CtPubillViewVO ctViewVO, Integer row) {
    if (ctViewVO != null) {
      this.panel.setBodyValueAt(ctViewVO.getCqpbaseschemeid(), row.intValue(),
          OrderItemVO.CQPBASESCHEMEID);
    }
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-18 下午01:09:16
   */
  // private void setDefaultPrice(Integer[] rows) {
  // CardEditorHelper editor = new CardEditorHelper(this.panel);
  // PriceQuoter quoter = new PriceQuoter(editor);
  // quoter.setDefaultPrice(rows);
  // }
  /**
   * 方法功能描述：过滤赠品行索引
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-18 下午01:11:56
   */
  private Integer[] setDefaultPriceAndGetLargessLines(Integer[] rows) {
    if (ArrayUtils.isEmpty(rows)) {
      return null;
    }

    UFBoolean blargess = null;
    Set<Integer> notCntIndex = new HashSet<Integer>();

    for (Integer row : rows) {
      if (null == row) {
        continue;
      }

      // 电子商务不关联合同
      String cecbillbid =
          (String) this.panel.getBodyValueAt(row.intValue(),
              OrderItemVO.CECBILLBID);
      if (!StringUtil.isEmptyWithTrim(cecbillbid)) {
        continue;
      }

      blargess =
          (UFBoolean) this.panel.getBodyValueAt(row.intValue(),
              OrderItemVO.BLARGESS);
      // 赠品
      if (blargess != null && blargess.booleanValue()) {
        this.setPriceValueWhenBlargess(row);
      }
      // 非赠品
      else {
        notCntIndex.add(row);
      }
    }

    if (!notCntIndex.isEmpty()) {
      return notCntIndex.toArray(new Integer[0]);
    }

    return null;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-21 上午11:23:19
   */
  private void setEditableWhenCtNull(Integer[] rows) {
    for (Integer row : rows) {
      // 主单位原币含税单价
      this.panel.setCellEditable(row.intValue(), OrderItemVO.NORIGTAXPRICE,
          this.panel.getBodyItem("material", OrderItemVO.NORIGTAXPRICE)
              .isEdit(), "material");
      // 报价原币含税单价
      this.panel.setCellEditable(row.intValue(), OrderItemVO.NQTORIGTAXPRICE,
          this.panel.getBodyItem("material", OrderItemVO.NQTORIGTAXPRICE)
              .isEdit(), "material");
      // 主单位原币无税单价
      this.panel.setCellEditable(row.intValue(), OrderItemVO.NORIGPRICE,
          this.panel.getBodyItem("material", OrderItemVO.NORIGPRICE).isEdit(),
          "material");
      // 报价原币无税单价
      this.panel
          .setCellEditable(row.intValue(), OrderItemVO.NQTORIGPRICE, this.panel
              .getBodyItem("material", OrderItemVO.NQTORIGPRICE).isEdit(),
              "material");
      // 币种
      if (this.panel.getBodyItem("material", OrderItemVO.CORIGCURRENCYID) != null) {
        this.panel.setCellEditable(row.intValue(), OrderItemVO.CORIGCURRENCYID,
            this.panel.getBodyItem("material", OrderItemVO.CORIGCURRENCYID)
                .isEdit(), "material");
      }
      else {
        this.panel.setCellEditable(row.intValue(), OrderItemVO.CORIGCURRENCYID,
            true, "material");
      }
    }
  }

  private void setnexchangerate(BillCardPanel panel, int row) {
    String ccurrencytype =
        (String) panel.getBodyValueAt(row, OrderItemVO.CORIGCURRENCYID);
    UFDate date =
        (UFDate) panel.getHeadItem(OrderItemVO.DBILLDATE).getValueObject();
    if (StringUtil.isEmptyWithTrim(ccurrencytype)) {
      ccurrencytype =
          (String) panel.getHeadItem(OrderHeaderVO.CORIGCURRENCYID)
              .getValueObject();
    }
    UFDouble rate =
        CurrencyRate.getCurrencyRateByOrg(
            (String) panel.getBodyValueAt(row, OrderItemVO.PK_PSFINANCEORG),
            ccurrencytype,
            (String) panel.getBodyValueAt(row, OrderItemVO.CCURRENCYID), date);
    panel.setBodyValueAt(rate, row, OrderItemVO.NEXCHANGERATE, "material");
  }

  /**
   * 设置不可抵扣税率，从合同过来要覆盖已有值
   * 
   * @param ctViewVO
   * @param row
   */
  private void setNosubtaxrate(CtPubillViewVO ctViewVO, int row) {
    if (null == ctViewVO) {
      return;
    }
    UFDouble oldRate =
        (UFDouble) this.panel.getBodyValueAt(row, OrderItemVO.NNOSUBTAXRATE);
    if (ctViewVO.getNnosubtaxrate() != null
        && !ctViewVO.getNnosubtaxrate().equals(oldRate)) {
      this.panel.setBodyValueAt(ctViewVO.getNnosubtaxrate(), row,
          OrderItemVO.NNOSUBTAXRATE, "material");
      // 这里先以"不可抵扣税率"算一遍，后续价格变动会重新算
      RelationCalculate cal = new RelationCalculate();
      cal.calculate(this.panel, new int[] {
        row
      }, OrderItemVO.NNOSUBTAXRATE);
    }

  }

  private String setNum(CtPubillViewVO ctViewVO, int row) {
    if (null == ctViewVO) {
      return null;
    }
    Object norgnum = this.panel.getBodyValueAt(row, OrderItemVO.NNUM);
    // 如果不为空，且不为0，则返回null。即数量为空或者为0，才去修改主数量
    if (norgnum != null && ((UFDouble) norgnum).doubleValue() != 0.0) {
      return null;
    }

    boolean blesszero = false;
    UFDouble nctnum = ctViewVO.getNnum();
    UFDouble nctordernum = ctViewVO.getNordnum();
    UFDouble nnum = MathTool.sub(nctnum, nctordernum);
    if (MathTool.lessThan(nnum, UFDouble.ZERO_DBL)) {
      blesszero = true;
      nnum = UFDouble.ZERO_DBL;
    }
    this.panel.setBodyValueAt(nnum, row, OrderItemVO.NNUM, "material");

    CardEditorHelper helper = new CardEditorHelper(this.panel);
    NumValueSetter numvalue = new NumValueSetter(helper);
    numvalue.setNastnum(new int[] {
      row
    });

    if (blesszero) {
      return (String) this.panel.getBodyValueAt(row, OrderItemVO.CROWNO);
    }
    return null;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param ctViewVO
   * @param row <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-19 下午02:32:03
   */
  private void setOrigcurrencyValue(CtPubillViewVO ctViewVO, Integer row) {
    if (null == ctViewVO) {
      // 币种
      this.panel.setCellEditable(row.intValue(), OrderItemVO.CORIGCURRENCYID,
          this.panel.getBodyItem("material", OrderItemVO.CORIGCURRENCYID)
              .isEdit(), "material");
    }
    else {
      this.panel.setBodyValueAt(ctViewVO.getCorigcurrencyid(), row.intValue(),
          OrderItemVO.CORIGCURRENCYID, "material");
      this.panel.setBodyValueAt(ctViewVO.getNtaxrate(), row.intValue(),
          OrderItemVO.NTAXRATE, "material");
      // 合同币种不可编辑
      this.panel.setCellEditable(row.intValue(), OrderItemVO.CORIGCURRENCYID,
          false, "material");

      this.setnexchangerate(this.panel, row.intValue());
    }
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param ctViewVO
   * @param row
   * @param pricePriority
   * @param notCntPriceSet <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-19 下午02:40:55
   */
  private void setPriceValue(CtPubillViewVO ctViewVO, Integer row,
      PricePriority pricePriority, Set<Integer> notCntPriceSet) {
    UFDouble orgPrice = null;
    UFDouble qtPrice = null;
    String orgChangeKey = null;
    String qtChangeKey = null;
    UFDouble oldOrgPrice = null;
    UFDouble oldQtPrice = null;

    Integer oldFtaxtypeflag =
        (Integer) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.FTAXTYPEFLAG);
    Integer ftaxtypeflag = null;
    if (ctViewVO != null) {
      orgPrice = this.getOrgPriceValueByPricePriority(ctViewVO, pricePriority);
      orgChangeKey = this.getPriceFieldByPricePriority(pricePriority);
      qtPrice = this.getQtPriceValueByPricePriority(ctViewVO, pricePriority);
      qtChangeKey = this.getQtPriceFieldByPricePriority(pricePriority);
      if (orgPrice != null) {
        oldOrgPrice =
            (UFDouble) this.panel.getBodyValueAt(row.intValue(), orgChangeKey);
        this.panel.setBodyValueAt(orgPrice, row.intValue(), orgChangeKey,
            "material");
      }
      if (qtPrice != null) {
        oldQtPrice =
            (UFDouble) this.panel.getBodyValueAt(row.intValue(), qtChangeKey);
        this.panel.setBodyValueAt(qtPrice, row.intValue(), qtChangeKey,
            "material");
      }
      ftaxtypeflag = ctViewVO.getFtaxtypeflag();
      this.panel.setBodyValueAt(ftaxtypeflag, row.intValue(),
          OrderItemVO.FTAXTYPEFLAG, "material");
    }

    if (ctViewVO != null && (orgPrice != null || qtPrice != null)) {
      // 重新计算数量关系：只新值不同于原值时才重新计算
      if (null == oldQtPrice || MathTool.compareTo(qtPrice, oldQtPrice) != 0) {
        RelationCalculate rc = new RelationCalculate();
        rc.calculate(this.panel, new int[] {
          row.intValue()
        }, qtChangeKey);
      }
      else if (null == oldOrgPrice
          || MathTool.compareTo(orgPrice, oldOrgPrice) != 0) {
        RelationCalculate rc = new RelationCalculate();
        rc.calculate(this.panel, new int[] {
          row.intValue()
        }, orgChangeKey);
      }
      else if (!ObjectUtils.equals(ftaxtypeflag, oldFtaxtypeflag)) {
        RelationCalculate rc = new RelationCalculate();
        rc.calculate(this.panel, new int[] {
          row.intValue()
        }, OrderItemVO.FTAXTYPEFLAG);
      }

    }
    else {
      // 币种
      String corigcurrencyid =
          (String) this.panel.getBodyValueAt(row.intValue(),
              OrderItemVO.CORIGCURRENCYID);
      // 折本汇率
      UFDouble nexchangerate =
          (UFDouble) this.panel.getBodyValueAt(row.intValue(),
              OrderItemVO.NEXCHANGERATE);
      if (null == corigcurrencyid || null == nexchangerate) {
        return;
      }
      if (!notCntPriceSet.contains(row)) {
        notCntPriceSet.add(row);
      }
    }
  }

  /**
   * 方法功能描述：赠品行，设置价格
   * <p>
   * <b>参数说明</b>
   * 
   * @param row <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-19 下午02:45:04
   */
  private void setPriceValueWhenBlargess(Integer row) {
    UFDouble price = null;
    // 主单位本币无税净价
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NNETPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NNETPRICE, "material");
    }
    // 主单位原币无税净价
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NORIGNETPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NORIGNETPRICE, "material");
    }
    // 主单位原币无税单价
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NORIGPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NORIGPRICE, "material");
    }
    // 主单位原币含税净价
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NORIGTAXNETPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NORIGTAXNETPRICE, "material");
    }
    // 主单位原币含税单价
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NORIGTAXPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NORIGTAXPRICE, "material");
    }
    // 主单位本币含税净价
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NTAXNETPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NTAXNETPRICE, "material");
    }
    // 报价本币无税净价
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NQTNETPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NQTNETPRICE, "material");
    }
    // 报价原币无税净价
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NQTORIGNETPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NQTORIGNETPRICE, "material");
    }
    // 报价原币无税单价
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NQTORIGPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NQTORIGPRICE, "material");
    }
    // 报价原币含税净价
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NQTORIGTAXNETPRC);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NQTORIGTAXNETPRC, "material");
    }
    // 报价原币含税单价
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NQTORIGTAXPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NQTORIGTAXPRICE, "material");
    }
    // 报价本币含税净价
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NQTTAXNETPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NQTTAXNETPRICE, "material");
    }
  }

  private void setQtUnit(CtPubillViewVO ctViewVO, int row) {
    if (null == ctViewVO) {
      return;
    }

    String cqtunitid = ctViewVO.getCqtunitid();
    if (cqtunitid != null) {
      this.panel.setBodyValueAt(cqtunitid, row, OrderItemVO.CQTUNITID);
    }
    String vqtunitrate = ctViewVO.getVqtunitrate();
    if (cqtunitid != null && vqtunitrate != null) {
      String oldrate =
          (String) this.panel.getBodyValueAt(row, OrderItemVO.VQTUNITRATE);
      UFDouble norgnum =
          (UFDouble) this.panel.getBodyValueAt(row, OrderItemVO.NNUM);
      this.panel.setBodyValueAt(vqtunitrate, row, OrderItemVO.VQTUNITRATE,
          "material");
      if (!vqtunitrate.equals(oldrate) && norgnum != null) {
        CardEditorHelper helper = new CardEditorHelper(this.panel);
        NumValueSetter numvalue = new NumValueSetter(helper);
        numvalue.setNastnum(new int[] {
          row
        });
      }
    }

  }

  private void setVfree(CtPubillViewVO ctViewVO, int row) {
    if (null == ctViewVO) {
//      this.panel.setBodyValueAt(null, row, OrderItemVO.CPROJECTID, "material");
      this.panel.setBodyValueAt(null, row, OrderItemVO.CPRODUCTORID, "material");
      this.panel.setBodyValueAt(null, row, OrderItemVO.CASSCUSTID, "material");
      this.panel.setBodyValueAt(null, row, OrderItemVO.CQUALITYLEVELID, "material");
      this.panel.setBodyValueAt(null, row, OrderItemVO.CDEVAREAID, "material");
      this.panel.setBodyValueAt(null, row, OrderItemVO.VFREE1, "material");
      this.panel.setBodyValueAt(null, row, OrderItemVO.VFREE2, "material");
      this.panel.setBodyValueAt(null, row, OrderItemVO.VFREE3, "material");
      this.panel.setBodyValueAt(null, row, OrderItemVO.VFREE4, "material");
      this.panel.setBodyValueAt(null, row, OrderItemVO.VFREE5, "material");
      this.panel.setBodyValueAt(null, row, OrderItemVO.VFREE6, "material");
      this.panel.setBodyValueAt(null, row, OrderItemVO.VFREE7, "material");
      this.panel.setBodyValueAt(null, row, OrderItemVO.VFREE8, "material");
      this.panel.setBodyValueAt(null, row, OrderItemVO.VFREE9, "material");
      this.panel.setBodyValueAt(null, row, OrderItemVO.VFREE10, "material");
    }
    else {
			Object cffileid = this.panel.getBodyValueAt(row, OrderItemVO.CFFILEID);
			if (cffileid == null || cffileid.toString().isEmpty()) {
				this.panel.setBodyValueAt(ctViewVO.getAttributeValue("cffileid"), row,
						OrderItemVO.CFFILEID, "material");
			}
//      this.panel.setBodyValueAt(ctViewVO.getAttributeValue("cbprojectid"), row,
//          OrderItemVO.CPROJECTID, "material");
      this.panel.setBodyValueAt(ctViewVO.getCproductorid(), row,
          OrderItemVO.CPRODUCTORID, "material");
      this.panel.setBodyValueAt(ctViewVO.getCasscustid(), row,
          OrderItemVO.CASSCUSTID, "material");
      this.panel.setBodyValueAt(ctViewVO.getCqualitylevelid(), row,
          OrderItemVO.CQUALITYLEVELID, "material");
      this.panel.setBodyValueAt(ctViewVO.getCdevareaid(), row,
          OrderItemVO.CDEVAREAID, "material");
      this.panel.setBodyValueAt(ctViewVO.getVfree1(), row, OrderItemVO.VFREE1,
          "material");
      this.panel.setBodyValueAt(ctViewVO.getVfree2(), row, OrderItemVO.VFREE2,
          "material");
      this.panel.setBodyValueAt(ctViewVO.getVfree3(), row, OrderItemVO.VFREE3,
          "material");
      this.panel.setBodyValueAt(ctViewVO.getVfree4(), row, OrderItemVO.VFREE4,
          "material");
      this.panel.setBodyValueAt(ctViewVO.getVfree5(), row, OrderItemVO.VFREE5,
          "material");
      this.panel.setBodyValueAt(ctViewVO.getVfree6(), row, OrderItemVO.VFREE6,
          "material");
      this.panel.setBodyValueAt(ctViewVO.getVfree7(), row, OrderItemVO.VFREE7,
          "material");
      this.panel.setBodyValueAt(ctViewVO.getVfree8(), row, OrderItemVO.VFREE8,
          "material");
      this.panel.setBodyValueAt(ctViewVO.getVfree9(), row, OrderItemVO.VFREE9,
          "material");
      this.panel.setBodyValueAt(ctViewVO.getVfree10(), row,
          OrderItemVO.VFREE10, "material");
      this.panel.getBillModel().loadLoadRelationItemValue(row);
    }
  }

}
