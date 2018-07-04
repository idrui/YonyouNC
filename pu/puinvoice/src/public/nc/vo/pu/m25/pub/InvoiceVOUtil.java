/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-3 ����03:44:56
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ƱVO�Ĺ���������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-3 ����03:44:56
 */
public class InvoiceVOUtil {

  /** ѯ�۲����õ��ı����ֶ� */
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

  /** ѯ�۲����õ��ı�ͷ�ֶ� */
  public static String[] PRICE_QUERY_HEADER_ATTR = {
    InvoiceHeaderVO.PK_ORG, InvoiceHeaderVO.PK_PURCHASEORG,
    InvoiceHeaderVO.DBILLDATE, InvoiceHeaderVO.CORIGCURRENCYID,
    InvoiceHeaderVO.PK_SUPPLIER
  };

  /** ��Դ��Դͷ�ֶ� */
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

  /** �õ���¡��ķ�ƱVO���� **/
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
   * �����ż�ѯ��vo
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
   * ������������������itemvo���priceQueryVO
   * <p>
   * <b>����˵��</b>
   * 
   * @param InvoiceVO
   * @param rows - Itemvo��Ӧ����
   * @return <p>
   *         InvoicePriceQueryVO[] ѯ��vo
   * @since 6.0
   * @author tianft
   * @time 2010-4-12 ����01:28:02
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
    // û��������Ϣ
    if (materialMap.size() == 0) {
      return null;
    }
    for (int i = 0; i < itemVOs.length; i++) {
      String pk_material = itemVOs[i].getPk_material();
      if (StringUtil.isEmptyWithTrim(pk_material)
          || materialMap.get(pk_material) == null) {
        continue;
      }
      // �������Ͽ����Լ����ú��ۿ�����
      if (materialMap.get(pk_material).getFee() != null
          && materialMap.get(pk_material).getFee().booleanValue()
          || materialMap.get(pk_material).getDiscountflag() != null
          && materialMap.get(pk_material).getDiscountflag().booleanValue()) {
        continue;
      }
      queryVO = new InvoicePriceQueryVO();
      queryVO.setItemIndex(rows[i]);
      // ����ȡֵ
      for (int j = 0; j < InvoiceVOUtil.PRICE_QUERY_BODY_ATTR.length; j++) {
        queryVO.setAttributeValue(InvoiceVOUtil.PRICE_QUERY_BODY_ATTR[j],
            itemVOs[i]
                .getAttributeValue(InvoiceVOUtil.PRICE_QUERY_BODY_ATTR[j]));
      }
      // ��ͷȡֵ
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
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-7-1 ����03:19:07
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
        // ���ú��ۿ������ϲ�����ѯ��
        if (materialMap.get(pk_material).getFee() != null
            && materialMap.get(pk_material).getFee().booleanValue()
            || materialMap.get(pk_material).getDiscountflag() != null
            && materialMap.get(pk_material).getDiscountflag().booleanValue()) {
          continue;
        }
        InvoicePriceQueryVO queryVO = new InvoicePriceQueryVO();
        queryVO.setItemIndex(i);
        queryVO.setVoIndex(Integer.valueOf(curVO));
        // ����ȡֵ
        for (int j = 0; j < InvoiceVOUtil.PRICE_QUERY_BODY_ATTR.length; j++) {
          queryVO.setAttributeValue(InvoiceVOUtil.PRICE_QUERY_BODY_ATTR[j],
              items[i]
                  .getAttributeValue(InvoiceVOUtil.PRICE_QUERY_BODY_ATTR[j]));
        }
        // ��ͷȡֵ
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
   * ���ݷ�Ʊ����������˰��־��ѯVO����
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   * 
   * @param bills
   * @return �����봫�뷢Ʊ����һ������һһ��Ӧ�����������
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
   * ���ݷ�Ʊ���ɲ�ѯ������֯VATcode��VO����
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   * 
   * @param bills
   * @return �����봫�뷢Ʊ����һ������һһ��Ӧ�����������
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
   * ���ݴ���ķ�Ʊ���ɹ�Ӧ��VATCode��ѯVO����
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   * 
   * @param bills
   * @return �����봫�뷢Ʊ����һ������һһ��Ӧ�����������
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

  /** �õ���Ʊ��Ӧ�Ľ���������Ϣ **/
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
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * ���ݷ�Ʊ���ɲ�ѯVAT˰����Ϣ��VO����
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   * 
   * @param bills
   * @return ����Ϊ���뵥�����б������֮����һһ��Ӧ�����������
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
   * �����������������ݲ����ж��Ƿ���Դ�ڶ����ķ�Ʊ�Զ����㡣
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo ��������Դ�ڶ����ķ�Ʊ[��Դ����ⵥ�ķ�Ʊһ�����Զ�����]
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-26 ����04:48:57
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
   * ����������������Ҫ�����������Ĺ��ܡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo �ж�һ����Ʊ�Ƿ���Դ�ڶ���[��ͳ�Ʒ�����]
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-29 ����04:20:41
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

  /** �Ƿ��ֹ�ȡ����Ӧ�� **/
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

  /** �Ƿ��ֹ���Ӧ�� **/
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
   * ��������������NC631����������ͨ�ɹ�������ڲɹ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos <p>
   * @since 6.3
   * @author liangchen1
   * @time 2013-9-1 ����11:29:25
   */
  public static boolean isPuInvoice(InvoiceVO[] vos) {
    return vos[0].getParentVO().getFinvoicetype().intValue() == InvoicePuImportEnum.PUINVOICE
        .toInt();
  }

  /**
   * �ж�һ����Ʊ�Ƿ�����ȫ���о�����ԴʱΪ����
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
   * ���������������ж�һ����Ʊ�Ƿ�����[ֻ����Դ�ڶ��������еķ�Ʊ��Ϊ����]��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-29 ����04:23:38
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
   * �������������������÷�Ʊ��������Ӧ���﷢Ʊ֮ǰ��λ���ϡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author zhyhang
   * @time 2010-4-25 ����09:04:29
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
   * ����������������ѯ���ϵķ��á��ۿ���Ϣ
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos ��Ʊȫvo
   * @return Map<String, MaterialVO>������Ϊ����pk_material
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-7-1 ����12:57:04
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
   * �����������������������ٴζԽo���ķ�Ʊ�����������㣬һ������ת������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-13 ����11:29:25
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
    // ǿ��ʹ�ù̶������ʣ�����Ϊ����������ʱ�������������㣬���������
    cal.setBForceFixChgRate(UFBoolean.TRUE);
    for (InvoiceVO vo : vos) {
      InvoiceItemVO[] items = vo.getChildrenVO();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }
      for (InvoiceItemVO item : items) {
        // ����Դ���ݵ���������һ��ʱ�Ž��м���
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
      // ����Դ�ɹ����Ĳ�����
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
      // ����ȡֵ
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
