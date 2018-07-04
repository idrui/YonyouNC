package nc.pubimpl.pu.m25.pu.settle.rule;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfBillItfDefUtil;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.scmpub.reference.uap.pf.TransTypeMapping;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceClass;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 
 * @description
 * 填充虚拟发票的必要信息
 * @scene
 * 生成虚拟发票
 * @param
 * stockVos 虚拟发票的期初暂估入库单VO
 *
 * @since 6.3
 * @version 2014-10-22 下午3:15:39
 * @author zhangshqb
 */
public class VirtualInfoFillRule implements IRule<InvoiceVO> {

  private IBill[] stockVos = null;

  /**
   * VirtualInfoFillRule 的构造子
   * 
   * @param stockVos
   */
  public VirtualInfoFillRule(IBill[] stockVos) {
    this.stockVos = stockVos;
  }

  @Override
  public void process(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      InvoiceHeaderVO header = vo.getParentVO();
      // 虚拟发票标志
      header.setBvirtual(UFBoolean.TRUE);
      // 发票分类为其它
      header.setFinvoiceclass(Integer.valueOf(InvoiceClass.OTHER_INVC));
      // 发票类型(交易类型)
      if (StringUtil.isEmptyWithTrim(header.getVtrantypecode())) {
        String invcTT = this.getInvoiceType(vo);
        header.setVtrantypecode(invcTT);
        header.setCtrantypeid(PfServiceScmUtil.getTrantypeidByCode(
            new String[] {
              invcTT
            }).get(invcTT));
      }
      // 审批人
      header.setApprover(BSContext.getInstance().getUserID());
      // 审批时间
      header.setTaudittime(AppContext.getInstance().getBusiDate());
      // 审批状态
      header.setFbillstatus(POEnumBillStatus.APPROVE.toInteger());
      // 设置费用发票的标识
      header.setBfee(UFBoolean.FALSE);
      // wuxla v61
      // 2012-5-16 wangyf、zhaoyh、tianft确定
      // 虚拟发票原币本币一样，折本汇率设置为1，v5也是这样处理方式

      // 2013-5-24非常痛苦的过程
      // 因为NCdp204550401，按照王印芬、唐江峰、杨永伟意见，改为60形式。
      // header.setCorigcurrencyid(header.getCcurrencyid());
      // header.setNexchangerate(UFDouble.ONE_DBL);
      // 周宁利、王印芬、杨永伟、吴小亮，2013-5-25。
      // 税率也应该取入库单税率
      // for (InvoiceItemVO item : vo.getChildrenVO()) {
      // // 税率、不可抵扣税率为0
      // item.setNtaxrate(UFDouble.ZERO_DBL);
      // item.setNnosubtaxrate(UFDouble.ZERO_DBL);
      // }

    }

    // 补充行号
    VORowNoUtils.setVOsRowNoByRule(vos, InvoiceItemVO.CROWNO);
  }

  /** 确定虚拟发票的交易类型 **/
  private String getInvoiceType(InvoiceVO vo) {
    /**
     * 无发票结算生成采购发票时的发票类型： <br>
     * 根据入库单对应的流程找该流程中定义的采购发票的交易类型—〉 找上下游接口关系 <br>
     * 根据最新需求，不再“随机找一个发票类型”
     */
    // 1、根据入库单对应的流程找该流程中定义的采购发票的交易类型 -- 此步在VO交换后已经处理完成
    String trantype = null;
    // 2、找接口对应关系(采购入库单45,4T,47到采购发票25)
    String pk_stock = vo.getChildrenVO()[0].getCsourceid();
    BillIndex bi = new BillIndex(this.stockVos);
    ISuperVO stockHead =
        bi.get(this.stockVos[0].getParent().getMetaData(), pk_stock);
    trantype = this.getInvoiceTypeByDefine(stockHead);
    if (!StringUtil.isEmptyWithTrim(trantype)) {
      return trantype;
    }
    ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004050_0", "04004050-0043")/*
                                                                 * @res
                                                                 * "未找到虚拟发票的交易类型，无法进行业务操作!"
                                                                 */);
    return trantype;
  }

  private String getInvoiceTypeByDefine(ISuperVO stockHead) {
    /**
     * 由于供应链的规则，交易类型的字段名应该是一致，<br>
     * 如果有变化可以根据流程获得业务接口来得到具体入库单的交易类型字段
     */
    String stockTT =
        (String) stockHead.getAttributeValue(InvoiceHeaderVO.VTRANTYPECODE);
    String stockTTID =
        (String) stockHead.getAttributeValue(InvoiceHeaderVO.CTRANTYPEID);
    TransTypeMapping mapping = new TransTypeMapping();
    mapping.setSrcBillType(PfServiceScmUtil.getBillTypeByTransType(stockTT));
    mapping.setSrcTransType(stockTTID);
    mapping.setSrcTransTypeCode(stockTT);
    mapping.setDestBillType(POBillType.Invoice.getCode());
    mapping =
        PfBillItfDefUtil.queryTransTypeMapping(BSContext.getInstance()
            .getGroupID(), mapping);
    if (null != mapping) {
      return mapping.getDestTransTypeCode();
    }
    return null;
  }
}
