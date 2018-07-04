package nc.vo.pu.m21.vochange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m20.pub.IQueryPrayBill;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pp.m28.entity.PriceAuditHeaderVO;
import nc.vo.pp.m28.entity.PriceAuditItemVO;
import nc.vo.pp.m28.entity.PriceAuditVO;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pu.m21.query.supplier.SupplierInfo;
import nc.vo.pu.m21.rule.CurrencyAndExchangerate;
import nc.vo.pu.m21.rule.OrganizationDefaultValue;
import nc.vo.pu.m21.rule.PaymentInfo;
import nc.vo.pu.m21.rule.PlanArriveDate;
import nc.vo.pu.m21.rule.RelationCalculate;
import nc.vo.pu.m21.rule.SupplierDefaultValue;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 价格审批单生成采购订单
 * 
 * @since 6.0
 * @version 2011-6-22 下午12:50:19
 * @author wuxla
 */

public class ChangeVOAdjust28To21 extends AbstractOrderChangeVOAdjust {
  
  private Map<String, PraybillViewVO> prayBillMap;

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    if (!(srcVO instanceof PriceAuditVO)) {
      return srcVO;
    }
    PriceAuditVO m28vo = (PriceAuditVO) srcVO;

    // 填补请购单信息
    // hanbin：此代码逻辑是从前天移过来的，为保证稳定性，不做优化
    this.fillupM20Info(m28vo);

    return m28vo;
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
	  AggregatedValueObject[] AggVOs = super.batchAdjustAfterChange(srcVOs, destVOs, adjustContext);
    for (AggregatedValueObject vo : AggVOs) {
      String pk_payterm = ((OrderVO) vo).getHVO().getPk_payterm();
      if (pk_payterm != null) {
        OrderPaymentVO[] vos = PaymentInfo.getOrderPaymentVOs(pk_payterm);
        ((OrderVO) vo).setChildren(OrderPaymentVO.class, vos);
      }
      else {
        OrderPaymentVO paymentVO = new OrderPaymentVO();
        paymentVO.setShoworder(Integer.valueOf(1));
        ((OrderVO) vo).setChildren(OrderPaymentVO.class, new OrderPaymentVO[] {
          paymentVO
        });
      }
    }
    return AggVOs;
  }
  
  @Override
  protected String[] getNumStrs() {
    return new String[]{"nordnum", "nordastnum", "nordastnum"};
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    // 正常情况下，数组内只有一个vo，界面上面不存在批量生成订单
    // hanbin：此代码逻辑是从前天移过来的，为保证稳定性，不做优化
    for (AggregatedValueObject srcVO : srcVOs) {
      this.adjustBeforeChange(srcVO, adjustContext);
    }
    return srcVOs;
  }

  /**
   * 补请购单号 请购单行号
   * 
   * @param vos
   * @param srcVOs
   */
  private void fillFor20Info(OrderVO[] vos, AggregatedValueObject[] srcVOs) {
    Map<String, CircularlyAccessibleValueObject> srcItemMap =
        AggVOUtil.createItemMap(srcVOs);
    for (OrderVO vo : vos) {
      for (OrderItemVO item : vo.getBVO()) {
        // key:订单表体的来源单据行id
        String key = item.getCsourcebid();
        if (srcItemMap.containsKey(key)) {
          Object vsrctype =
              srcItemMap.get(key).getAttributeValue(PriceAuditItemVO.VSRCTYPE);
          Object vfirsttype =
              srcItemMap.get(key)
                  .getAttributeValue(PriceAuditItemVO.VFIRSTTYPE);
          if (POBillType.PrayBill.getCode().equals(vsrctype)) {
            // 请购单表头主键
            item.setCpraybillhid((String) srcItemMap.get(key)
                .getAttributeValue(PriceAuditItemVO.CSRCID));
            // 请购单号
            item.setCpraybillcode((String) srcItemMap.get(key)
                .getAttributeValue(PriceAuditItemVO.VSRCCODE));
            // 请购单表体主键
            item.setCpraybillbid((String) srcItemMap.get(key)
                .getAttributeValue(PriceAuditItemVO.CSRCBID));
            // 请购单行号
            item.setCpraybillrowno((String) srcItemMap.get(key)
                .getAttributeValue(PriceAuditItemVO.VSRCROWNO));
          }
          else if (POBillType.PrayBill.getCode().equals(vfirsttype)) {
            // 请购单表头主键
            item.setCpraybillhid((String) srcItemMap.get(key)
                .getAttributeValue(PriceAuditItemVO.CFIRSTID));
            // 请购单号
            item.setCpraybillcode((String) srcItemMap.get(key)
                .getAttributeValue(PriceAuditItemVO.VFIRSTCODE));
            // 请购单表体主键
            item.setCpraybillbid((String) srcItemMap.get(key)
                .getAttributeValue(PriceAuditItemVO.CFIRSTBID));
            // 请购单行号
            item.setCpraybillrowno((String) srcItemMap.get(key)
                .getAttributeValue(PriceAuditItemVO.VFIRSTROWNO));

          }
        }
      }
    }
  }

  /**
   * 补充需求库存组织以及需求仓库
   * 
   * @param selectedVO
   */
  private void fillupM20Info(PriceAuditVO selectedVO) {
    // hanbin：此代码逻辑是从前天移过来的，为保证稳定性，不做优化
    PriceAuditItemVO[] children = selectedVO.getChildrenVO();
    PriceAuditHeaderVO header = selectedVO.getParentVO();
    if (ArrayUtils.isEmpty(children)) {
      return;
    }
    // 源头是请购单

    String[] bids = this.getPrayBillBID(new PriceAuditVO[]{selectedVO});
    if(bids.length ==0){
      return;
    }
    Map<String, PraybillViewVO> praybillviewMap = getPraybillViewVO(bids);
    for (PriceAuditItemVO child : children) {
      String vSrcBid = child.getCfirstbid();
      if (null == vSrcBid) {
        continue;
      }
      if (praybillviewMap.get(vSrcBid) == null
          || praybillviewMap.get(vSrcBid).getHead() == null
          || praybillviewMap.get(vSrcBid).getItem() == null) {
        continue;
      }
      // 库存组织，仓库、项目
      String pk_stock = praybillviewMap.get(vSrcBid).getHead().getPk_org();
      String pk_reqstor =
          praybillviewMap.get(vSrcBid).getItem().getPk_reqstor();
      String cprojectid =
          praybillviewMap.get(vSrcBid).getItem().getCprojectid();
      if (StringUtils.isBlank(header.getPk_stockorg())) {
        header.setPk_stockorg(pk_stock);
      }
      child.setPk_reqstor(pk_reqstor);
      child.setCprojectid(cprojectid);
      
      // 赵玉行、吴小亮要求(NCdp203153486)：价格审批单推订单时将来源和源头置为请购单和请购单的源头。
      String m20code = praybillviewMap.get(vSrcBid).getHead().getVbillcode();
      String m20id = praybillviewMap.get(vSrcBid).getItem().getPk_praybill();
      String m20bid = praybillviewMap.get(vSrcBid).getItem().getPk_praybill_b();
      String m20ctrantypeid =
          praybillviewMap.get(vSrcBid).getHead().getCtrantypeid();
      String m20crowno = praybillviewMap.get(vSrcBid).getItem().getCrowno();
      
      child.setVsrctype(POBillType.PrayBill.getCode());
      child.setVsrctrantype(m20ctrantypeid);
      child.setVsrcrowno(m20crowno);
      child.setVsrccode(m20code);
      child.setCsrcid(m20id);
      child.setCsrcbid(m20bid);
    }
  }

  private Map<String, PraybillViewVO> getPraybillViewVO(String[] bids) {
    if(this.prayBillMap == null || this.prayBillMap.size() != bids.length){
      IQueryPrayBill prayBill =
          NCLocator.getInstance().lookup(IQueryPrayBill.class);
      String[] feilds =
          {
            PraybillItemVO.PK_PRAYBILL, PraybillItemVO.PK_PRAYBILL_B,
            PraybillItemVO.PK_ORG, PraybillItemVO.PK_REQSTOR,
            PraybillItemVO.PK_REQSTOORG, PraybillItemVO.PK_REQSTOORG_V,
            PraybillItemVO.CPROJECTID, PraybillHeaderVO.VBILLCODE,
            PraybillHeaderVO.CTRANTYPEID, PraybillItemVO.CROWNO,
            PraybillItemVO.CFIRSTID, PraybillItemVO.CFIRSTBID,
            PraybillItemVO.CFIRSTTYPECODE, PraybillItemVO.VFIRSTCODE,
            PraybillItemVO.VFIRSTROWNO, PraybillItemVO.VFIRSTTRANTYPE,
            PraybillHeaderVO.PK_ORG
          };
      try {
        this.prayBillMap = prayBill.queryViewByItemPK(bids, feilds);
        } 
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return this.prayBillMap;
  }

  /***
   * 取价格审批单来源单据表体主键。
   * 请购单表体主键
   * 
   * @param selectedVO
   * @return
   */
  private String[] getPrayBillBID(PriceAuditVO[] selectedVO) {
    List<String> retList = new ArrayList<String>();
    for(PriceAuditVO vo : selectedVO){
      PriceAuditItemVO[] children = vo.getChildrenVO();
      for (int i = 0; i < children.length; i++) {
        String firstType = children[i].getVfirsttype();
        if (StringUtils.equals(POBillType.PrayBill.getCode(), firstType)) {
          // 价格审批单的源头id记录的是请购单
          retList.add(children[i].getCfirstbid());
        }
      }
    }
    return retList.toArray(new String[0]);
  }

  @Override
  protected OrderVO[] fillInformation(OrderVO[] vos,
      AggregatedValueObject[] srcVOs) {
    if (ArrayUtils.isEmpty(vos) || ArrayUtils.isEmpty(srcVOs)) {
      return null;
    }
    this.setDept(vos);
    PriceAuditVO[] priceVOs = ArrayUtil.convertArrayType(srcVOs);
    String[] bids = this.getPrayBillBID(priceVOs);
    if(bids.length !=0){
      Map<String, PraybillViewVO> praybillviewMap = getPraybillViewVO(bids);
      this.setReqstoorg(vos, praybillviewMap);
    }
    return super.fillInformation(vos, srcVOs);
  }

  private void setReqstoorg(OrderVO[] vos, Map<String, PraybillViewVO> praybillviewMap) {
  //库存计划：来源是请购单的需求库存组织、收获库存组织默认取原始需求库存组织，无取主组织。
    
    Map<String,String> orgs= new HashMap<String,String>();
    Map<String,String> orgs_v= new HashMap<String,String>();
      for(Map.Entry<String, PraybillViewVO> entry: praybillviewMap.entrySet()) {
        String pkb = entry.getKey();
        PraybillViewVO view = entry.getValue();
        Object reqstoorg = view.getAttributeValue(PraybillItemVO.PK_REQSTOORG);
        if(!orgs.containsKey(pkb) && reqstoorg != null){
          orgs.put(pkb, reqstoorg.toString());
          orgs_v.put(pkb, view.getAttributeValue(PraybillItemVO.PK_REQSTOORG_V).toString());
        }
      }
    for(OrderVO vo: vos) {
      OrderItemVO[] items =vo.getBVO();
      for(OrderItemVO item: items) {
        if(orgs.containsKey(item.getCfirstbid())){
          item.setPk_arrvstoorg(orgs.get(item.getCfirstbid()));
          item.setPk_arrvstoorg_v(orgs_v.get(item.getCfirstbid()));
          item.setPk_reqstoorg(orgs.get(item.getCfirstbid()));
          item.setPk_reqstoorg_v(orgs_v.get(item.getCfirstbid()));
        }
      }
    }
  }
  
  @Override
  protected void fillInformation(BillHelper<OrderVO> helper, int[] rows) {
    // 补充组织相关的信息
    new OrganizationDefaultValue(helper).setDefaultOrganizationValue(rows);
    // 获得供应商信息
    SupplierInfo supplier = this.getSupplierInfo(helper);

    // 设置供应商的默认值
    SupplierDefaultValue vendorDefaultValue = new SupplierDefaultValue(helper);
    vendorDefaultValue.setDefaultValueNotClear(supplier);

    // 补充本位币和汇率的相关信息
    new CurrencyAndExchangerate(helper).setCurrencyAndExchangeRate(rows);

    // 计划到货日期
    new PlanArriveDate(helper).setPlanArriveDate(0, helper.getItemCount() - 1);

    // 以后改成批量的
    // TrantypeValue trantype = new TrantypeValue(helper);
    // trantype.setCsrcbilltype(PPBillType.PriceAudit.getCode());
    // trantype.setTrantypeValue();
    // 价格审批单是打开订单界面，可以不用处理流程
  }

  @Override
  protected void fillOtherInfo(OrderVO[] vos, AggregatedValueObject[] srcVOs) {
    this.setDept(vos);
    // 补请购单号 请购单行号
    this.fillFor20Info(vos, srcVOs);
  }

  @Override
  protected void fillVatInfo(OrderVO[] vos) {
    // vat 直接对照
    return;
  }

  @Override
  protected void relationCalculate(RelationCalculate cal, OrderVO vo) {
    if (OrderVOUtil.isTaxPrior(vo.getHVO().getPk_org())) {
      cal.calculate(vo, OrderItemVO.NQTORIGTAXPRICE);
    }
    else {
      cal.calculate(vo, OrderItemVO.NQTORIGPRICE);
    }
  }

  @Override
  protected void setOrderPrice(OrderVO[] vos) {
    this.setPriceByParaPO16(vos);
  }
}
