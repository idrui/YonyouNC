/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-3 下午03:44:56
 */
package nc.vo.pu.m25.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.itf.scmpub.reference.uap.bd.vat.CustSuppVATCodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.OppTaxFlagQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.OrgVATCodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoQueryVO;
import nc.pubitf.pu.m25transtype.IQueryTranstype;
import nc.vo.bd.material.MaterialVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoicePriceQueryVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceBillAction;
import nc.vo.pu.m25.enumeration.InvoicePuImportEnum;
import nc.vo.pu.m25.enumeration.InvoiceRowType;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m25trantype.entity.InvcTranTypeVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票VO的公共处理工具
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-3 下午03:44:56
 */
public class InvoiceVOUtil {

  /** 询价参数用到的表体字段 */
  public static String[] PRICE_QUERY_BODY_ATTR = {
    InvoiceItemVO.PK_INVOICE, InvoiceItemVO.PK_INVOICE_B,
    InvoiceItemVO.PK_SRCMATERIAL, InvoiceItemVO.PK_MATERIAL,
    InvoiceItemVO.CPRODUCTORID, InvoiceItemVO.CPROJECTID,
    InvoiceItemVO.CSOURCEBID, InvoiceItemVO.CSOURCEID,
    InvoiceItemVO.CSOURCETYPECODE, InvoiceItemVO.NPRICE,
    InvoiceItemVO.NTAXPRICE, InvoiceItemVO.NORIGPRICE,
    InvoiceItemVO.NORIGTAXPRICE, InvoiceItemVO.PK_ORDER_B,
    InvoiceItemVO.CASTUNITID, InvoiceItemVO.CQUALITYLEVELID,
    InvoiceItemVO.PK_STOCKPS_B
  };

  /** 询价参数用到的表头字段 */
  public static String[] PRICE_QUERY_HEADER_ATTR = {
    InvoiceHeaderVO.PK_ORG, InvoiceHeaderVO.PK_PURCHASEORG,
    InvoiceHeaderVO.DBILLDATE, InvoiceHeaderVO.CORIGCURRENCYID,
    InvoiceHeaderVO.PK_SUPPLIER
  };

  /** 来源及源头字段 */
  public static String[] SRC_FIRST_KEYS = {
    InvoiceItemVO.CFIRSTBID, InvoiceItemVO.CFIRSTID,
    InvoiceItemVO.CFIRSTTYPECODE, InvoiceItemVO.VFIRSTCODE,
    InvoiceItemVO.VFIRSTROWNO, InvoiceItemVO.VFIRSTTRANTYPE,
    InvoiceItemVO.CSOURCEBID, InvoiceItemVO.CSOURCEID,
    InvoiceItemVO.CSOURCETYPECODE, InvoiceItemVO.VSOURCECODE,
    InvoiceItemVO.VSOURCEROWNO, InvoiceItemVO.VSOURCETRANTYPE
  };

  public static IKeyValue[] getBillHelper(InvoiceVO[] vos) {
    IKeyValue[] bills = new BillHelper[vos.length];
    for (int i = 0; i < bills.length; i++) {
      bills[i] = new BillHelper<InvoiceVO>(vos[i]);
    }
    return bills;
  }

  /** 得到克隆后的发票VO数组 **/
  public static InvoiceVO[] getCloneVOs(InvoiceVO[] vos) {
    InvoiceVO[] cvos = new InvoiceVO[vos.length];
    for (int i = 0; i < cvos.length; i++) {
      cvos[i] = (InvoiceVO) vos[i].clone();
    }
    return cvos;
  }

  public static InvoicePriceQueryVO[] getHqHpQueryVO(InvoiceVO vo, int[] rows) {
    return InvoiceVOUtil.getHqHpQueryVO(null, vo, rows);
  }

  /**
   * 优质优价询价vo
   * 
   * @param vo
   * @param rows
   * @return
   */
  public static InvoicePriceQueryVO[] getHqHpQueryVO(InvoiceVO[] vos) {
    InvoicePriceQueryVO[] queryVOs = null;
    for (int i = 0; i < vos.length; i++) {
      InvoicePriceQueryVO[] tempVOs =
          InvoiceVOUtil.getHqHpQueryVO(Integer.valueOf(i), vos[i], null);
      if (queryVOs == null) {
        queryVOs = tempVOs;
      }
      else {
        queryVOs = ArrayUtil.combinArrays(queryVOs, tempVOs);
      }
    }
    return queryVOs;
  }

  /**
   * 方法功能描述：根据itemvo组合priceQueryVO
   * <p>
   * <b>参数说明</b>
   * 
   * @param InvoiceVO
   * @param rows - Itemvo对应得行
   * @return <p>
   *         InvoicePriceQueryVO[] 询价vo
   * @since 6.0
   * @author tianft
   * @time 2010-4-12 下午01:28:02
   */
  public static InvoicePriceQueryVO[] getInvoicePriceQuerVO(InvoiceVO vo,
      int[] rows) {
    if (ArrayUtils.isEmpty(vo.getChildrenVO())) {
      return null;
    }
    ArrayList<InvoicePriceQueryVO> alQueryVO =
        new ArrayList<InvoicePriceQueryVO>();
    InvoicePriceQueryVO queryVO = null;

    InvoiceItemVO[] itemVOs = vo.getChildrenVO();

    Map<String, MaterialVO> materialMap =
        InvoiceVOUtil.queryMaterialBaseInfo(vo);
    // 没有物料信息
    if (materialMap.size() == 0) {
      return null;
    }
    for (int i = 0; i < itemVOs.length; i++) {
      String pk_material = itemVOs[i].getPk_material();
      if (StringUtil.isEmptyWithTrim(pk_material)
          || materialMap.get(pk_material) == null) {
        continue;
      }
      // 过滤物料空行以及费用和折扣物料
      if (materialMap.get(pk_material).getFee() != null
          && materialMap.get(pk_material).getFee().booleanValue()
          || materialMap.get(pk_material).getDiscountflag() != null
          && materialMap.get(pk_material).getDiscountflag().booleanValue()) {
        continue;
      }
      queryVO = new InvoicePriceQueryVO();
      queryVO.setItemIndex(rows[i]);
      // 表体取值
      for (int j = 0; j < InvoiceVOUtil.PRICE_QUERY_BODY_ATTR.length; j++) {
        queryVO.setAttributeValue(InvoiceVOUtil.PRICE_QUERY_BODY_ATTR[j],
            itemVOs[i]
                .getAttributeValue(InvoiceVOUtil.PRICE_QUERY_BODY_ATTR[j]));
      }
      // 表头取值
      for (int j = 0; j < InvoiceVOUtil.PRICE_QUERY_HEADER_ATTR.length; j++) {
        queryVO.setAttributeValue(
            InvoiceVOUtil.PRICE_QUERY_HEADER_ATTR[j],
            vo.getParentVO().getAttributeValue(
                InvoiceVOUtil.PRICE_QUERY_HEADER_ATTR[j]));
      }
      alQueryVO.add(queryVO);
    }
    if (alQueryVO.size() == 0) {
      return null;
    }
    return alQueryVO.toArray(new InvoicePriceQueryVO[alQueryVO.size()]);
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-7-1 下午03:19:07
   */
  public static InvoicePriceQueryVO[] getInvoicePriceQuerVO(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    ArrayList<InvoicePriceQueryVO> alQueryVO =
        new ArrayList<InvoicePriceQueryVO>();
    Map<String, MaterialVO> materialMap =
        InvoiceVOUtil.queryMaterialBaseInfo(vos);
    for (int curVO = 0; curVO < vos.length; curVO++) {
      InvoiceItemVO[] items = vos[curVO].getChildrenVO();
      for (int i = 0; i < items.length; i++) {
        String pk_material = items[i].getPk_material();
        if (materialMap.get(pk_material) == null) {
          continue;
        }
        // 费用和折扣类物料不参与询价
        if (materialMap.get(pk_material).getFee() != null
            && materialMap.get(pk_material).getFee().booleanValue()
            || materialMap.get(pk_material).getDiscountflag() != null
            && materialMap.get(pk_material).getDiscountflag().booleanValue()) {
          continue;
        }
        InvoicePriceQueryVO queryVO = new InvoicePriceQueryVO();
        queryVO.setItemIndex(i);
        queryVO.setVoIndex(Integer.valueOf(curVO));
        // 表体取值
        for (int j = 0; j < InvoiceVOUtil.PRICE_QUERY_BODY_ATTR.length; j++) {
          queryVO.setAttributeValue(InvoiceVOUtil.PRICE_QUERY_BODY_ATTR[j],
              items[i]
                  .getAttributeValue(InvoiceVOUtil.PRICE_QUERY_BODY_ATTR[j]));
        }
        // 表头取值
        for (int j = 0; j < InvoiceVOUtil.PRICE_QUERY_HEADER_ATTR.length; j++) {
          queryVO.setAttributeValue(
              InvoiceVOUtil.PRICE_QUERY_HEADER_ATTR[j],
              vos[curVO].getParentVO().getAttributeValue(
                  InvoiceVOUtil.PRICE_QUERY_HEADER_ATTR[j]));
        }
        alQueryVO.add(queryVO);
      }
    }
    if (alQueryVO.size() == 0) {
      return null;
    }
    return alQueryVO.toArray(new InvoicePriceQueryVO[alQueryVO.size()]);
  }

  /**
   * 根据发票生成逆向征税标志查询VO数组
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @param bills
   * @return 长度与传入发票个数一样，且一一对应；不处理过滤
   */
  public static OppTaxFlagQueryVO[] getOppTaxFlagQueryVos(IKeyValue[] bills) {
    OppTaxFlagQueryVO[] queryVos = new OppTaxFlagQueryVO[bills.length];
    for (int i = 0; i < bills.length; i++) {
      String pk_org = (String) bills[i].getHeadValue(InvoiceHeaderVO.PK_ORG);
      String pk_country =
          (String) bills[i].getHeadValue(InvoiceHeaderVO.CTAXCOUNTRYID);
      Integer bsflag =
          (Integer) bills[i].getHeadValue(InvoiceHeaderVO.FBUYSELLFLAG);
      BuySellFlagEnum bsflagenum =
          null == bsflag ? null : BuySellFlagEnum.valueOf(bsflag);
      OppTaxFlagQueryVO vo =
          new OppTaxFlagQueryVO(pk_org, pk_country, bsflagenum);
      queryVos[i] = vo;
    }
    return queryVos;
  }

  /**
   * 根据发票生成查询财务组织VATcode的VO数组
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @param bills
   * @return 长度与传入发票个数一样，且一一对应；不处理过滤
   */
  public static OrgVATCodeQueryVO[] getOrgVatCodeQueryVos(IKeyValue[] bills) {
    OrgVATCodeQueryVO[] queryvos = new OrgVATCodeQueryVO[bills.length];
    for (int i = 0; i < queryvos.length; i++) {
      String pk_org = (String) bills[i].getHeadValue(InvoiceHeaderVO.PK_ORG);
      String pk_country =
          (String) bills[i].getHeadValue(InvoiceHeaderVO.CTAXCOUNTRYID);
      queryvos[i] = new OrgVATCodeQueryVO(pk_org, pk_country);
    }
    return queryvos;
  }

  /**
   * 根据传入的发票生成供应商VATCode查询VO数组
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @param bills
   * @return 长度与传入发票个数一样，且一一对应；不处理过滤
   */
  public static CustSuppVATCodeQueryVO[] getSupplierVatCodeQueryVos(
      IKeyValue[] bills) {
    CustSuppVATCodeQueryVO[] queryvos =
        new CustSuppVATCodeQueryVO[bills.length];
    for (int i = 0; i < queryvos.length; i++) {
      String pk_supplier =
          (String) bills[i].getHeadValue(InvoiceHeaderVO.PK_SUPPLIER);
      String pk_country =
          (String) bills[i].getHeadValue(InvoiceHeaderVO.CSENDCOUNTRYID);
      queryvos[i] = new CustSuppVATCodeQueryVO(pk_supplier, pk_country);
    }
    return queryvos;
  }

  /** 得到发票对应的交易类型信息 **/
  public static Map<String, InvcTranTypeVO> getTranstype(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    String[] ttcode =
        (String[]) AggVOUtil.getDistinctHeadFieldArray(vos,
            InvoiceHeaderVO.VTRANTYPECODE, String.class);
    IQueryTranstype srv = NCLocator.getInstance().lookup(IQueryTranstype.class);
    String pk_group = vos[0].getParentVO().getPk_group();
    try {
      return srv.queryAttrByTypes(pk_group, ttcode, new String[] {
        InvcTranTypeVO.BSENDPAY
      });
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * 根据发票生成查询VAT税率信息的VO数组
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @param bills
   * @return 长度为传入单据所有表体个数之各且一一对应，不处理过滤
   */
  public static VATInfoQueryVO[] getVatInfoQueryVO(IKeyValue[] bills) {
    int rowcnt = 0;
    for (IKeyValue bill : bills) {
      rowcnt += bill.getItemCount();
    }
    VATInfoQueryVO[] vatqueryvos = new VATInfoQueryVO[rowcnt];
    rowcnt = 0;
    for (IKeyValue bill : bills) {
      int billrowcnt = bill.getItemCount();
      String pk_org = (String) bill.getHeadValue(InvoiceHeaderVO.PK_ORG);
      for (int i = 0; i < billrowcnt; i++) {
        String sendcnty =
            (String) bill.getHeadValue(InvoiceHeaderVO.CSENDCOUNTRYID);
        String rececnty =
            (String) bill.getHeadValue(InvoiceHeaderVO.CRECECOUNTRYID);
        String taxcnty =
            (String) bill.getHeadValue(InvoiceHeaderVO.CTAXCOUNTRYID);
        String pk_supplier =
            (String) bill.getHeadValue(InvoiceHeaderVO.PK_SUPPLIER);
        UFDate bizdate = (UFDate) bill.getHeadValue(InvoiceHeaderVO.DBILLDATE);
        Integer bsflag =
            (Integer) bill.getHeadValue(InvoiceHeaderVO.FBUYSELLFLAG);
        BuySellFlagEnum bsflagenum =
            null == bsflag ? null : BuySellFlagEnum.valueOf(bsflag);
        UFBoolean btriatrade =
            ValueUtils.getUFBoolean(bill
                .getHeadValue(InvoiceHeaderVO.BTRIATRADEFLAG));
        String pk_material =
            (String) bill.getBodyValue(i, InvoiceItemVO.PK_MATERIAL);
        vatqueryvos[rowcnt++] =
            new VATInfoQueryVO(taxcnty, bsflagenum, btriatrade, sendcnty,
                rececnty, pk_supplier, pk_material, bizdate, pk_org);
      }
    }
    return vatqueryvos;

  }

  /**
   * 方法功能描述：根据参数判断是否来源于订单的发票自动结算。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo 必须是来源于订单的发票[来源于入库单的发票一定是自动结算]
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-26 下午04:48:57
   */
  public static boolean isAutoSettleFromOrder(InvoiceVO vo) {
    if (null == vo) {
      return false;
    }
    if (InvoiceVOUtil.isSelfMade(vo)) {
      return false;
    }
    if (!InvoiceVOUtil.isFromOrder(vo)) {
      return false;
    }
    String pk_org = vo.getParentVO().getPk_org();
    return PUParaValue.po46.approve_settle == PUSysParamUtil.getPO46(pk_org);
  }

  /**
   * 方法功能描述：简要描述本方法的功能。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo 判断一个发票是否来源于订单[不统计费用行]
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-29 下午04:20:41
   */
  public static boolean isFromOrder(InvoiceVO vo) {
    if (null == vo) {
      return false;
    }
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      if (!StringUtil.isEmptyWithTrim(item.getCsourcetypecode())
          && InvoiceRowType.GOODS_ROW == item.getFrowtype().intValue()) {
        String srcBilltype = item.getCsourcetypecode();
        if (POBillType.Order.getCode().equals(srcBilltype)
            || SCBillType.Order.getCode().equals(srcBilltype)) {
          return true;
        }
      }
    }
    return false;
  }

  /** 是否手工取消传应付 **/
  public static boolean isManualCancelSendAP(InvoiceUIToBSEnv env) {
    if (null == env) {
      return false;
    }
    if (null == env.getTrigger()) {
      return false;
    }
    if (InvoiceBillAction.CANCELSENDAP == env.getTrigger()
        && ValueUtils.getBoolean(env.getBManual())) {
      return true;
    }
    return false;
  }

  /** 是否手工传应付 **/
  public static boolean isManualSendAP(InvoiceUIToBSEnv env) {
    if (null == env) {
      return false;
    }
    if (null == env.getTrigger()) {
      return false;
    }
    if (InvoiceBillAction.SENDAP == env.getTrigger()
        && ValueUtils.getBoolean(env.getBManual())) {
      return true;
    }
    return false;
  }

  /**
   * 方法功能描述：NC631需求，区分普通采购与进出口采购。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-9-1 上午11:29:25
   */
  public static boolean isPuInvoice(InvoiceVO[] vos) {
    return vos[0].getParentVO().getFinvoicetype().intValue() == InvoicePuImportEnum.PUINVOICE
        .toInt();
  }

  /**
   * 判断一个发票是否自制全部行均无来源时为自制
   * 
   * @param invoice
   * @return
   */
  public static boolean isSelfMade(IKeyValue invoice) {
    for (int i = 0; i < invoice.getItemCount(); i++) {
      if (null != invoice.getBodyValue(i, InvoiceItemVO.CSOURCETYPECODE)) {
        return false;
      }
    }
    return true;
  }

  /**
   * 方法功能描述：判断一个发票是否自制[只有来源于订单费用行的发票认为自制]。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-29 下午04:23:38
   */
  public static boolean isSelfMade(InvoiceVO vo) {
    if (null == vo) {
      return false;
    }
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      if (!StringUtil.isEmptyWithTrim(item.getCsourcetypecode())
          && InvoiceRowType.GOODS_ROW == item.getFrowtype().intValue()) {
        return false;
      }
    }
    return true;
  }

  /**
   * 方法功能描述：将费用发票调整到相应货物发票之前的位置上。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author zhyhang
   * @time 2010-4-25 下午09:04:29
   */
  public static InvoiceVO[] priOrderFeeVO(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    for (int i = 0; i < vos.length; i++) {
      InvoiceHeaderVO fhead = vos[i].getParentVO();
      if (ValueUtils.getBoolean(fhead.getBfee())) {
        continue;
      }
      String fpk = fhead.getPk_invoice();
      for (int j = i + 1; j < vos.length; j++) {
        InvoiceHeaderVO lhead = vos[j].getParentVO();
        if (!ValueUtils.getBoolean(lhead.getBfee())) {
          continue;
        }
        String lpk = lhead.getPk_parentinvoice();
        if (fpk.equals(lpk)) {
          InvoiceVO tempVo = vos[i];
          vos[i] = vos[j];
          vos[j] = tempVo;
          break;
        }
      }

    }
    return vos;
  }

  public static Map<String, MaterialVO> queryMaterialBaseInfo(InvoiceVO vo) {
    return InvoiceVOUtil.queryMaterialBaseInfo(new InvoiceVO[] {
      vo
    });
  }

  /**
   * 方法功能描述：查询物料的费用、折扣信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos 发票全vo
   * @return Map<String, MaterialVO>，主键为物料pk_material
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-7-1 下午12:57:04
   */
  public static Map<String, MaterialVO> queryMaterialBaseInfo(InvoiceVO[] vos) {
    Object[] oPk_material =
        AggVOUtil.getDistinctItemFieldArray(vos, InvoiceItemVO.PK_MATERIAL,
            String.class);

    Map<String, MaterialVO> materialMap = null;
    if (ArrayUtils.isEmpty(oPk_material)) {
      return new HashMap<String, MaterialVO>();
    }
    materialMap =
        MaterialPubService.queryMaterialBaseInfo(
            (String[]) ArrayUtil.convertArrayType(oPk_material), new String[] {
              MaterialVO.FEE, MaterialVO.DISCOUNTFLAG
            });

    return materialMap;
  }

  /**
   * 方法功能描述：从主数量再次对給定的发票进行联动计算，一般用于转单后处理。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-13 上午11:29:25
   */
  public static void reCalculateBasedNum(InvoiceVO[] vos) {
    InvoiceVOUtil.reCalculateBasedNum(vos, false);
  }

  public static void reCalculateBasedNum(InvoiceVO[] vos,
      boolean forceNoTaxPrior) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    VORelationCalculate cal = new VORelationCalculate();
    cal.setForceNoTaxPrior(forceNoTaxPrior);
    // 强制使用固定换算率，否则为浮动换算率时，从主数量计算，数量不会变
    cal.setBForceFixChgRate(UFBoolean.TRUE);
    for (InvoiceVO vo : vos) {
      InvoiceItemVO[] items = vo.getChildrenVO();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }
      for (InvoiceItemVO item : items) {
        // 与来源单据的主数量不一致时才进行计算
        if (!MathTool.equals(item.getNnum(), item.getNsourcenum())) {
          cal.calculate(vo.getParentVO(), item, InvoiceItemVO.NNUM);
        }
      }
    }
  }

  private static InvoicePriceQueryVO[] getHqHpQueryVO(Integer voIndex,
      InvoiceVO vo, int[] rows) {
    if (ArrayUtils.isEmpty(vo.getChildrenVO())) {
      return null;
    }
    ArrayList<InvoicePriceQueryVO> alQueryVO =
        new ArrayList<InvoicePriceQueryVO>();
    InvoicePriceQueryVO queryVO = null;

    InvoiceItemVO[] itemVOs = vo.getChildrenVO();

    for (int i = 0; i < itemVOs.length; i++) {
      String csourceTypeCode = itemVOs[i].getCsourcetypecode();
      // 非来源采购入库的不处理
      if (!ICBillType.PurchaseIn.getCode().equals(csourceTypeCode)) {
        continue;
      }
      queryVO = new InvoicePriceQueryVO();
      if (rows == null) {
        queryVO.setItemIndex(i);
      }
      else {
        queryVO.setItemIndex(rows[i]);

      }
      // 表体取值
      queryVO.setPk_stockps_b(itemVOs[i].getCsourcebid());
      queryVO.setVoIndex(voIndex);
      alQueryVO.add(queryVO);
    }
    if (alQueryVO.size() == 0) {
      return null;
    }
    return alQueryVO.toArray(new InvoicePriceQueryVO[alQueryVO.size()]);
  }
}
