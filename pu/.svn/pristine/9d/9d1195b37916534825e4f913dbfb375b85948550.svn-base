/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-5 下午03:14:52
 */
package nc.ui.pu.m25.action.processor;

import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.m25.model.InvoiceBillManageModel;
import nc.ui.pu.m25.view.InvoiceUIState;
import nc.ui.pu.pub.util.BusitypeSetter;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票复制动作的后续处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-5 下午03:14:52
 */
public class CopyActionProcessor implements ICopyActionProcessor<InvoiceVO> {

  private InvoiceBillManageModel model = null;

  /**
   * @return model
   */
  public InvoiceBillManageModel getModel() {
    return this.model;
  }

  @Override
  public void processVOAfterCopy(InvoiceVO billVO, LoginContext context) {
    if (billVO == null) {
      return;
    }
    // 批量处理远程调用规则
    this.batchProc(billVO, context);
    this.setHeadDefaultValues(billVO, context);
    this.setBodyDefaultValues(billVO);
  }

  /**
   * @param model 要设置的 model
   */
  public void setModel(InvoiceBillManageModel model) {
    this.model = model;
  }

  private void batchProc(InvoiceVO vo, LoginContext context) {
    List<IPURemoteCallCombinator> rccRuleLst =
        new ArrayList<IPURemoteCallCombinator>();
    // 注册执行远程调用规则－交易类型及业务流程处理
    this.register_BizRule(rccRuleLst, vo, context);
    // 执行远程调用合并规则
    for (IPURemoteCallCombinator rccRule : rccRuleLst) {
      if (null != rccRule) {
        rccRule.process();
      }
    }
  }

  private void copyTranstype(InvoiceVO vo, String transtype, String pk_trantype) {
    // 业务流程，先清空，后续会补充
    vo.getParentVO().setPk_busitype(null);
    if (StringUtils.isBlank(transtype)) {
      // 订单类型（交易类型）
      vo.getParentVO().setVtrantypecode(null);
      // 订单类型
      vo.getParentVO().setCtrantypeid(null);
    }
    else {
      // 订单类型（交易类型）
      vo.getParentVO().setVtrantypecode(transtype);
      // 订单类型
      vo.getParentVO().setCtrantypeid(pk_trantype);
    }
  }

  private void register_BizRule(List<IPURemoteCallCombinator> rccRuleLst,
      InvoiceVO vo, LoginContext context) {
    // 处理交易类型
    String transtype = TrantypeFuncUtils.getTrantype(context);
    String pk_trantype = TrantypeFuncUtils.getTrantypePk(context);
    this.copyTranstype(vo, transtype, pk_trantype);
    new BusitypeSetter(POBillType.Invoice, new BillHelper<InvoiceVO>(vo),
        context).copySet(rccRuleLst);
  }

  /**
   * 方法功能描述：为表体设置默认值
   * <p>
   * <b>参数说明</b>
   * 
   * @param billVO <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-5 下午03:36:32
   */
  private void setBodyDefaultValues(InvoiceVO billVO) {
    InvoiceItemVO[] items = billVO.getChildrenVO();
    if (ArrayUtils.isEmpty(items)) {
      return;
    }
    for (InvoiceItemVO vo : items) {
      vo.setPk_invoice(null);
      vo.setPk_invoice_b(null);

      vo.setCfirstbid(null);
      vo.setCfirstid(null);
      vo.setCfirsttypecode(null);

      vo.setVfirstcode(null);
      vo.setVfirstrowno(null);
      vo.setVfirsttrantype(null);

      vo.setFirstbts(null);
      vo.setFirstts(null);

      vo.setCsourcebid(null);
      vo.setCsourceid(null);
      vo.setCsourcetypecode(null);

      vo.setSourcebts(null);
      vo.setSourcets(null);

      vo.setVsourcecode(null);
      vo.setVsourcerowno(null);
      vo.setVsourcetrantype(null);

      vo.setTs(null);
      vo.setNaccumsettmny(null);
      vo.setNaccumsettnum(null);

      // 清空来源订单信息
      vo.setPk_order(null);
      vo.setPk_order_b(null);
      vo.setVordercode(null);
      vo.setVordertrantype(null);
      // 特征码
//      vo.setCffileid(null);
    }
  }

  /**
   * 方法功能描述：为表头设置默认值
   * <p>
   * <b>参数说明</b>
   * 
   * @param billVO <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-5 下午03:36:32
   */
  private void setHeadDefaultValues(InvoiceVO billVO, LoginContext context) {
    InvoiceHeaderVO header = billVO.getParentVO();
    if (header == null) {
      return;
    }
    header.setVbillcode(null);
    header.setPk_invoice(null);
    header.setTs(null);
    header.setTaudittime(null);
    header.setApprover(null);
    header.setModifier(null);
    header.setModifiedtime(null);

    header.setIprintcount(Integer.valueOf(0));
    header.setFbillstatus(POEnumBillStatus.FREE.toInteger());

    UFDate curDate = AppContext.getInstance().getBusiDate();
    header.setDarrivedate(curDate);
    header.setDbilldate(curDate);

    header.setCreator(context.getPk_loginUser());
    header.setBillmaker(context.getPk_loginUser());

    // 传应付相关
    header.setBapflag(UFBoolean.FALSE);

    // 正常发票界面复制费用发票的时候，情况费用相关信息
    if (this.model.getInvoiceUIState() != InvoiceUIState.FEE_INVC) {
      header.setBfee(UFBoolean.FALSE);
      header.setPk_parentinvoice(null);
    }

    // 冻结信息清除
    header.setBfrozen(UFBoolean.FALSE);
    header.setVfrozenreason(null);
    header.setTfrozentime(null);
    header.setPk_frozenuser(null);
    // 虚拟
    header.setBvirtual(UFBoolean.FALSE);
    header.setBinitial(UFBoolean.FALSE);
    // 制单日期
    header.setDmakedate(null);
  }

}
