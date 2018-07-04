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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ҫְ���ǹ�����ͬ
 * <li>������ͬ����Ҫ�����ͬ��ص��ֶεĿɱ༭�ԡ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 ����12:16:08
 *       tianft �����ǵ����������ʽ��������Զ�̵��úϲ�
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
   * ��������������������ͬ�����ٿ��Ƶ��ۿɱ༭�ԣ��ɺ�ͬ�����ۿ��Ʒ�ʽ����д���ơ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param irows
   * @param mustSetCnt
   * @param pasteFlag <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-18 ����12:35:29
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
    // ��Ʒ��������ͬ���ֹ����պ�ͬ���⣩
    if (!mustSetCnt) {
      rows = this.setDefaultPriceAndGetLargessLines(rows);
    }
    // ������Ʒ�о�Ϊ��Ʒ��ֱ�ӷ��أ�����������ͬ����
    if (ArrayUtils.isEmpty(rows)) {
      return;
    }

    // ������и��ƺ��ճ������ѯ��ͬ
    if (pasteFlag) {
      rows = this.getNoSourceCtLines(rows);
    }
    // �����о�ΪZ2���գ�ֱ�ӷ��أ�����������ͬ����
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

    // ���°���˳��
    ctViewVOs = this.getSerialViewVOs(rows, ctViewVOs);

    // ����ȡ�Ǻ�ͬ�۵���
    Set<Integer> notCntPriceSet = new HashSet<Integer>();
    PricePriority pricePriority = PUSysParamUtil.getPO28(pk_org);
    // ΪЧ�ʿ��ǣ������������
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

      // ��ͬ��ʼ�տɱ༭
      this.panel.setCellEditable(row, OrderItemVO.CCONTRACTID, this.panel
          .getBodyItem("material", OrderItemVO.CCONTRACTID).isEdit());

      // ��ͬID ��ͬ��
      this.setContractValue(ctViewVOs[i], Integer.valueOf(row));

      // �������ϸ�������
      this.setVfree(ctViewVOs[i], row);
      
      // ��λ������
      this.setQtUnit(ctViewVOs[i], row);

      String rowNo = this.setNum(ctViewVOs[i], row);
      if (rowNo != null) {
        builder.append("[");
        builder.append(rowNo);
        builder.append("]");
      }

      // ���ò��ɵֿ�˰��
      this.setNosubtaxrate(ctViewVOs[i], row);
      // �����ż�
      this.setCqpbaseschemeid(ctViewVOs[i], Integer.valueOf(row));

      // ����ID
      this.setOrigcurrencyValue(ctViewVOs[i], Integer.valueOf(row));

      // �۸�
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
              "1400404000059")/* @res "������ͬ����" */,
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("40040400",
              "1400404000060", null, new String[] {
                builder.toString()
              })/* @res "��{0}�к�ͬ�ۼƶ����������ں�ͬ����,����������Ϊ0,���ֹ��޸�����." */,
          this.loginContext);
    }
  }

  /**
   * ��������������Ѱ��ĳЩ�еĺ�ͬ
   * <p>
   * <b>����˵��</b>
   * 
   * @param rows
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-18 ����02:45:20
   */
  private CtRelatingVO[] getCtRelatingVOs(Integer[] rows) {
    if (ArrayUtils.isEmpty(rows)) {
      return null;
    }

    // ����֯
    String pk_org =
        (String) this.panel.getHeadItem(OrderHeaderVO.PK_ORG).getValueObject();
    // ��Ӧ��
    String cvendor =
        (String) this.panel.getHeadItem(OrderHeaderVO.PK_SUPPLIER)
            .getValueObject();
    // ��������
    UFDate dbilldate =
        (UFDate) this.panel.getHeadItem(OrderHeaderVO.DBILLDATE)
            .getValueObject();
    // ����
    String corigcurrencyid =
        (String) this.panel.getHeadItem(OrderHeaderVO.CORIGCURRENCYID)
            .getValueObject();
    // ���䷽ʽ
    String pk_transporttype =
        (String) this.panel.getHeadItem(OrderHeaderVO.PK_TRANSPORTTYPE)
            .getValueObject();

    // �й�Ӧ�̼�����ʱ��������Ѱ�Һ�ͬ
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
      // //����ȷ�ϣ�����������ͬ����һЩ��ʷ���⣬������ͬ��ʱ���ñ��۵�λά��
      // // ���۵�λ
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
   * ��������������������Դ��Ϊ��ͬ��������,��Ҫ���Ǹ����и�����Դ���������ͬ��һ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param rows
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-18 ����01:45:38
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
   * ���������������õ����ݲɹ��۸����ȹ��ĵ���
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-19 ����01:55:52
   */
  private UFDouble getOrgPriceValueByPricePriority(CtPubillViewVO ctViewVO,
      PricePriority pricePriority) {
    // ��˰����
    if (PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(pricePriority)) {
      return ctViewVO.getNorigtaxprice();
    }
    // ��˰����
    else if (PricePriority.PRICE_PRIOR_TO_TAXPRICE.equals(pricePriority)) {
      return ctViewVO.getNorigprice();
    }

    return null;
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param pricePriority
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-19 ����02:00:58
   */
  private String getPriceFieldByPricePriority(PricePriority pricePriority) {
    // ��˰���� ����λ
    if (PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(pricePriority)) {
      return OrderItemVO.NORIGTAXPRICE;
    }
    // ��˰����
    else if (PricePriority.PRICE_PRIOR_TO_TAXPRICE.equals(pricePriority)) {
      return OrderItemVO.NORIGPRICE;
    }

    return null;
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param pricePriority
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-24 ����02:01:00
   */
  private String getQtPriceFieldByPricePriority(PricePriority pricePriority) {
    // ��˰���� ����
    if (PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(pricePriority)) {
      return OrderItemVO.NQTORIGTAXPRICE;
    }
    // ��˰����
    else if (PricePriority.PRICE_PRIOR_TO_TAXPRICE.equals(pricePriority)) {
      return OrderItemVO.NQTORIGPRICE;
    }

    return null;
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param ctViewVO
   * @param pricePriority
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-24 ����01:56:17
   */
  private UFDouble getQtPriceValueByPricePriority(CtPubillViewVO ctViewVO,
      PricePriority pricePriority) {
    // ��˰����
    if (PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(pricePriority)) {
      return ctViewVO.getNqtorigtaxprice();
    }
    // ��˰����
    else if (PricePriority.PRICE_PRIOR_TO_TAXPRICE.equals(pricePriority)) {
      return ctViewVO.getNqtorigprice();
    }

    return null;
  }

  /**
   * ��������������ʹѡ��ĺ�ͬ���ж�Ӧ
   * <p>
   * <b>����˵��</b>
   * 
   * @param rows
   * @param ctViewVOs
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-21 ����11:19:03
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
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param rows <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-24 ����04:16:08
   */
  // private void relationCalculate(Integer[] rows) {
  // RelationCalculateAfterQuoter tool =
  // new RelationCalculateAfterQuoter(this.panel);
  // tool.relationCalculate(rows);
  // }
  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-6-19 ����02:28:20
   */
  private void setCellEditableAndCntValueWhenNull(Integer row) {
    // ��ͬ��
    this.panel.setCellEditable(row.intValue(), OrderItemVO.VCONTRACTCODE,
        false, "material");
    // ����
    this.panel.setCellEditable(row.intValue(), OrderItemVO.CORIGCURRENCYID,
        this.panel.getBodyItem("material", OrderItemVO.CORIGCURRENCYID)
            .isEdit(), "material");
    // ��ͬͷID
    this.panel.setBodyValueAt(null, row.intValue(), OrderItemVO.CCONTRACTID,
        "material");
    // ��ͬ��ID
    this.panel.setBodyValueAt(null, row.intValue(), OrderItemVO.CCONTRACTROWID,
        "material");
    // ��ͬ��
    this.panel.setBodyValueAt(null, row.intValue(), OrderItemVO.VCONTRACTCODE,
        "material");
  }

  /**
   * ����������������ͬ
   * <p>
   * <b>����˵��</b>
   * 
   * @param ctViewVO
   * @param row <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-19 ����02:24:43
   */
  private void setContractValue(CtPubillViewVO ctViewVO, Integer row) {
    if (null == ctViewVO) {
      // ��ͬͷID
      this.panel.setBodyValueAt(null, row.intValue(), OrderItemVO.CCONTRACTID,
          "material");
      // ��ͬ��ID
      this.panel.setBodyValueAt(null, row.intValue(),
          OrderItemVO.CCONTRACTROWID, "material");
      // ��ͬ��
      this.panel.setBodyValueAt(null, row.intValue(),
          OrderItemVO.VCONTRACTCODE, "material");
    }
    else {
      // ��ͬͷID
      this.panel.setBodyValueAt(ctViewVO.getPk_ct_pu(), row.intValue(),
          OrderItemVO.CCONTRACTID, "material");
      // ��ͬ��ID
      this.panel.setBodyValueAt(ctViewVO.getPk_ct_pu_b(), row.intValue(),
          OrderItemVO.CCONTRACTROWID, "material");
      // ��ͬ��
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
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param rows <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-18 ����01:09:16
   */
  // private void setDefaultPrice(Integer[] rows) {
  // CardEditorHelper editor = new CardEditorHelper(this.panel);
  // PriceQuoter quoter = new PriceQuoter(editor);
  // quoter.setDefaultPrice(rows);
  // }
  /**
   * ��������������������Ʒ������
   * <p>
   * <b>����˵��</b>
   * 
   * @param rows
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-18 ����01:11:56
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

      // �������񲻹�����ͬ
      String cecbillbid =
          (String) this.panel.getBodyValueAt(row.intValue(),
              OrderItemVO.CECBILLBID);
      if (!StringUtil.isEmptyWithTrim(cecbillbid)) {
        continue;
      }

      blargess =
          (UFBoolean) this.panel.getBodyValueAt(row.intValue(),
              OrderItemVO.BLARGESS);
      // ��Ʒ
      if (blargess != null && blargess.booleanValue()) {
        this.setPriceValueWhenBlargess(row);
      }
      // ����Ʒ
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
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param rows <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-21 ����11:23:19
   */
  private void setEditableWhenCtNull(Integer[] rows) {
    for (Integer row : rows) {
      // ����λԭ�Һ�˰����
      this.panel.setCellEditable(row.intValue(), OrderItemVO.NORIGTAXPRICE,
          this.panel.getBodyItem("material", OrderItemVO.NORIGTAXPRICE)
              .isEdit(), "material");
      // ����ԭ�Һ�˰����
      this.panel.setCellEditable(row.intValue(), OrderItemVO.NQTORIGTAXPRICE,
          this.panel.getBodyItem("material", OrderItemVO.NQTORIGTAXPRICE)
              .isEdit(), "material");
      // ����λԭ����˰����
      this.panel.setCellEditable(row.intValue(), OrderItemVO.NORIGPRICE,
          this.panel.getBodyItem("material", OrderItemVO.NORIGPRICE).isEdit(),
          "material");
      // ����ԭ����˰����
      this.panel
          .setCellEditable(row.intValue(), OrderItemVO.NQTORIGPRICE, this.panel
              .getBodyItem("material", OrderItemVO.NQTORIGPRICE).isEdit(),
              "material");
      // ����
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
   * ���ò��ɵֿ�˰�ʣ��Ӻ�ͬ����Ҫ��������ֵ
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
      // ��������"���ɵֿ�˰��"��һ�飬�����۸�䶯��������
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
    // �����Ϊ�գ��Ҳ�Ϊ0���򷵻�null��������Ϊ�ջ���Ϊ0����ȥ�޸�������
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
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param ctViewVO
   * @param row <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-19 ����02:32:03
   */
  private void setOrigcurrencyValue(CtPubillViewVO ctViewVO, Integer row) {
    if (null == ctViewVO) {
      // ����
      this.panel.setCellEditable(row.intValue(), OrderItemVO.CORIGCURRENCYID,
          this.panel.getBodyItem("material", OrderItemVO.CORIGCURRENCYID)
              .isEdit(), "material");
    }
    else {
      this.panel.setBodyValueAt(ctViewVO.getCorigcurrencyid(), row.intValue(),
          OrderItemVO.CORIGCURRENCYID, "material");
      this.panel.setBodyValueAt(ctViewVO.getNtaxrate(), row.intValue(),
          OrderItemVO.NTAXRATE, "material");
      // ��ͬ���ֲ��ɱ༭
      this.panel.setCellEditable(row.intValue(), OrderItemVO.CORIGCURRENCYID,
          false, "material");

      this.setnexchangerate(this.panel, row.intValue());
    }
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param ctViewVO
   * @param row
   * @param pricePriority
   * @param notCntPriceSet <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-19 ����02:40:55
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
      // ���¼���������ϵ��ֻ��ֵ��ͬ��ԭֵʱ�����¼���
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
      // ����
      String corigcurrencyid =
          (String) this.panel.getBodyValueAt(row.intValue(),
              OrderItemVO.CORIGCURRENCYID);
      // �۱�����
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
   * ����������������Ʒ�У����ü۸�
   * <p>
   * <b>����˵��</b>
   * 
   * @param row <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-19 ����02:45:04
   */
  private void setPriceValueWhenBlargess(Integer row) {
    UFDouble price = null;
    // ����λ������˰����
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NNETPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NNETPRICE, "material");
    }
    // ����λԭ����˰����
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NORIGNETPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NORIGNETPRICE, "material");
    }
    // ����λԭ����˰����
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NORIGPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NORIGPRICE, "material");
    }
    // ����λԭ�Һ�˰����
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NORIGTAXNETPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NORIGTAXNETPRICE, "material");
    }
    // ����λԭ�Һ�˰����
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NORIGTAXPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NORIGTAXPRICE, "material");
    }
    // ����λ���Һ�˰����
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NTAXNETPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NTAXNETPRICE, "material");
    }
    // ���۱�����˰����
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NQTNETPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NQTNETPRICE, "material");
    }
    // ����ԭ����˰����
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NQTORIGNETPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NQTORIGNETPRICE, "material");
    }
    // ����ԭ����˰����
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NQTORIGPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NQTORIGPRICE, "material");
    }
    // ����ԭ�Һ�˰����
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NQTORIGTAXNETPRC);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NQTORIGTAXNETPRC, "material");
    }
    // ����ԭ�Һ�˰����
    price =
        (UFDouble) this.panel.getBodyValueAt(row.intValue(),
            OrderItemVO.NQTORIGTAXPRICE);
    if (ObjectUtil.isEmptyWithTrim(price)) {
      this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row.intValue(),
          OrderItemVO.NQTORIGTAXPRICE, "material");
    }
    // ���۱��Һ�˰����
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
