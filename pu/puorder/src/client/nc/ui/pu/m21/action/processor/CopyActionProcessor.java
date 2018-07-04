/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-31 上午09:02:24
 */
package nc.ui.pu.m21.action.processor;

import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.pub.util.BusitypeSetter;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
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
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-31 上午09:02:24
 */
public class CopyActionProcessor<E extends OrderVO> implements
    ICopyActionProcessor<E> {

  @Override
  public void processVOAfterCopy(OrderVO billVO, LoginContext context) {
		// 批处理
		this.batchProc(billVO, context);
		// 设定表头
		this.setHeadValue(billVO, context);

		// 设定表体
		this.setBodyValue(billVO);

		// 设置付款协议页签内容
		this.setBodyPaymentValue(billVO);
  }
  
	private void setBodyPaymentValue(OrderVO ordervo) {
		OrderPaymentVO[] vos = (OrderPaymentVO[]) ordervo
				.getChildren(OrderPaymentVO.class);
		 if (ArrayUtils.isEmpty(vos)) {
	      return;
	    }
		for (OrderPaymentVO vo : vos) {
		  if(vo==null) {
		    continue;
		  }
			vo.setPk_payment(null);
		}
	}

  private void batchProc(OrderVO vo, LoginContext context) {
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

  private void copyTranstype(OrderVO vo, String transtype, String pk_trantype) {
    // 业务流程，先清空，后续会补充
    vo.getHVO().setPk_busitype(null);
    if (StringUtils.isBlank(transtype)) {
      // 订单类型（交易类型）
      vo.getHVO().setVtrantypecode(null);
      // 订单类型
      vo.getHVO().setCtrantypeid(null);
    }
    else {
      // 订单类型（交易类型）
      vo.getHVO().setVtrantypecode(transtype);
      // 订单类型
      vo.getHVO().setCtrantypeid(pk_trantype);
    }
  }

  private void register_BizRule(List<IPURemoteCallCombinator> rccRuleLst,
      OrderVO vo, LoginContext context) {
    // 处理交易类型
    String transtype = TrantypeFuncUtils.getTrantype(context);
    String pk_trantype = TrantypeFuncUtils.getTrantypePk(context);
    this.copyTranstype(vo, transtype, pk_trantype);
    new BusitypeSetter(POBillType.Order, new BillHelper<OrderVO>(vo), context)
        .copySet(rccRuleLst);
  }

  /**
   * 方法功能描述：设定表体值
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVOs
   * @param context <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-31 上午10:12:29
   */
  private void setBodyValue(OrderVO billVO) {
    if (null == billVO) {
      return;
    }

    OrderItemVO[] itemVOs = billVO.getBVO();

    if (ArrayUtils.isEmpty(itemVOs)) {
      return;
    }

    for (int i = 0; i < itemVOs.length; ++i) {
      // 采购订单行ID
      itemVOs[i].setPk_order_b(null);
      // 采购订单表头_主键
      itemVOs[i].setPk_order(null);
      // 计划到货日期，在Action中进行设值
      itemVOs[i].setDplanarrvdate(null);
      // 累计到货数量
      itemVOs[i].setNaccumarrvnum(null);
      // 累计发票数量
      itemVOs[i].setNaccuminvoicenum(null);
      // 累计到货计划数量
      itemVOs[i].setNaccumrpnum(null);
      // 累计入库数量
      itemVOs[i].setNaccumstorenum(null);
      // 累计途耗数量
      itemVOs[i].setNaccumwastnum(null);
      // 累计退货数量
      itemVOs[i].setNbackarrvnum(null);
      // 累计退库数量
      itemVOs[i].setNbackstorenum(null);
      // 累计运输数量
      itemVOs[i].setNaccumdevnum(null);
      // 累计已核销本币开票金额
      itemVOs[i].setNacccancelinvmny(null);
      // 累计本币开票金额
      itemVOs[i].setNaccuminvoicemny(null);
      // 费用累计开票金额
      itemVOs[i].setNfeemny(null);
      // 预留数量
      itemVOs[i].setNsuprsnum(null);
      // 是否运输关闭
      itemVOs[i].setBtransclosed(UFBoolean.FALSE);
      // 到货关闭
      itemVOs[i].setBtransclosed(UFBoolean.FALSE);
      // 是否运输关闭
      itemVOs[i].setBtransclosed(UFBoolean.FALSE);
      // 到货关闭
      itemVOs[i].setBarriveclose(UFBoolean.FALSE);
      // 开票关闭
      itemVOs[i].setBinvoiceclose(UFBoolean.FALSE);
      // 付款关闭
      itemVOs[i].setBpayclose(UFBoolean.FALSE);
      // 入库关闭
      itemVOs[i].setBstockclose(UFBoolean.FALSE);
      // 确认数量
      itemVOs[i].setNconfirmnum(null);
      // 确认日期
      itemVOs[i].setDconfirmdate(null);
      // 修正日期
      itemVOs[i].setDcorrectdate(null);
      // 对方订单号
      itemVOs[i].setVvendorordercode(null);
      // 对方订单行号
      itemVOs[i].setVvendororderrow(null);
      // 操作员ID
      itemVOs[i].setChandler(null);
      // 来源单据号
      itemVOs[i].setVsourcecode(null);
      // 来源单据ID
      itemVOs[i].setCsourceid(null);
      // 来源单据行ID
      itemVOs[i].setCsourcebid(null);
      // 来源单据类型
      itemVOs[i].setCsourcetypecode(null);
      // 来源交易类型
      itemVOs[i].setVsourcetrantype(null);
      // 来源单据行号
      itemVOs[i].setVsourcerowno(null);
      // 源头单据ID
      itemVOs[i].setCfirstid(null);
      // 源头单据行ID
      itemVOs[i].setCfirstbid(null);
      // 源头单据类型
      itemVOs[i].setCfirsttypecode(null);
      // 源头单据号
      itemVOs[i].setVfirstcode(null);
      // 源头单据行号
      itemVOs[i].setVfirstrowno(null);
      // 源头交易类型
      itemVOs[i].setVfirsttrantype(null);
      // 请购单
      itemVOs[i].setCpraybillbid(null);
      itemVOs[i].setCpraybillcode(null);
      itemVOs[i].setCpraybillhid(null);
      itemVOs[i].setCpraybillrowno(null);
      itemVOs[i].setCpraytypecode(null);
      // 电子商务
      itemVOs[i].setCecbillbid(null);
      itemVOs[i].setCecbillid(null);
      itemVOs[i].setCectypecode(null);
      itemVOs[i].setVecbillcode(null);
      // 价格审批单号
      itemVOs[i].setVpriceauditcode(null);
      // 价格审批单ID
      itemVOs[i].setCpriceauditid(null);
      // 价格审批单存货子表ID
      itemVOs[i].setCpriceaudit_bid(null);
      // 价格审批单存货子子表ID
      itemVOs[i].setCpriceaudit_bb1id(null);
      // 合同头ID
      itemVOs[i].setCcontractid(null);
      // 合同行ID
      itemVOs[i].setCcontractrowid(null);
      // 合同号
      itemVOs[i].setVcontractcode(null);
      // ts
      itemVOs[i].setTs(null);
      // sourcebts
      itemVOs[i].setSourcebts(null);
      // sourcets
      itemVOs[i].setSourcets(null);
      // 根据最新设计，去掉订单行状态
      // 激活状态
      itemVOs[i].setFisactive((Integer) EnumActive.ACTIVE.value());
      // 存在到货计划
      itemVOs[i].setBreceiveplan(UFBoolean.FALSE);
      itemVOs[i].setNorigordernum(null);// 原始订单数量
      itemVOs[i].setNorigorderprice(null);// 原始订单单价
      itemVOs[i].setDorigplanarrvdate(null);// 原始计划到货日期
      itemVOs[i].setBborrowpur(UFBoolean.FALSE);// 借入转采购
      itemVOs[i].setVvendinventorycode(null);// 对应物料编码
      itemVOs[i].setVvendinventoryname(null);// 对应物料名称
      itemVOs[i].setFneedpurtype(null);
      itemVOs[i].setFactpurtype(null);
      itemVOs[i].setPk_cenpurule_b(null);
      itemVOs[i].setPk_discount(null);// 复制折扣规则编码会导致在界面无法编辑
      itemVOs[i].setIstorestatus(null);// 供应商交货状态，电商扩展字段，不清空会影响供应商门户的信息。
      itemVOs[i].setNsendplannum(null);
      itemVOs[i].setPk_schedule(null);
      itemVOs[i].setPk_schedule_b(null);
      itemVOs[i].setNaccumpickupnum(null);// 累计拣货主数量
      // 特征码
//      itemVOs[i].setCffileid(null);
    }
  }

  /**
   * 方法功能描述：设定表头值
   * <p>
   * <b>参数说明</b>
   * 
   * @param headerVO <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-31 上午09:25:50
   */
  private void setHeadValue(OrderVO billVO, LoginContext context) {
    if (null == billVO) {
      return;
    }

    OrderHeaderVO headerVO = billVO.getHVO();

    if (null == headerVO) {
      return;
    }

    // 采购订单ID
    headerVO.setPk_order(null);
    // 订单编号
    headerVO.setVbillcode(null);
    // 原币预付款限额
    headerVO.setNorgprepaylimit(null);
    // 是否已协同生成销售订单
    headerVO.setBcooptoso(UFBoolean.FALSE);
    // 是否由销售订单协同生成
    headerVO.setBsocooptome(UFBoolean.FALSE);
    // 对方订单号
    headerVO.setVcoopordercode(null);
    // 订单日期
    UFDate busidate = AppContext.getInstance().getBusiDate();
    headerVO.setDbilldate(busidate);
    // 订单状态
    headerVO.setForderstatus((Integer) POEnumBillStatus.FREE.value());
    // 版本信息
    headerVO.setNversion(Integer.valueOf(1));
    // 是否最新版本
    headerVO.setBislatest(UFBoolean.TRUE);
    // 补货标志
    headerVO.setBisreplenish(UFBoolean.FALSE);
    // 打印次数
    headerVO.setIprintcount(null);
    // 最终关闭
    headerVO.setBfinalclose(UFBoolean.FALSE);
    // 是否冻结
    headerVO.setBfrozen(UFBoolean.FALSE);
    // ts
    headerVO.setTs(null);
    // 制单人
    headerVO.setBillmaker(context.getPk_loginUser());
    // 创建时间
    headerVO.setCreationtime(null);
    // 修改人
    headerVO.setModifier(null);
    // 修改时间
    headerVO.setModifiedtime(null);
    // 修订人
    headerVO.setCrevisepsn(null);
    // 修订时间
    headerVO.setTrevisiontime(null);
    // 审批人
    headerVO.setApprover(null);
    // 审批时间
    headerVO.setTaudittime(null);
    // 冻结人
    headerVO.setPk_freezepsndoc(null);
    // 冻结时间
    headerVO.setTfreezetime(null);
    // 冻结原因
    headerVO.setVfrozenreason(null);
    headerVO.setIrespstatus(null);
    headerVO.setBpublish(UFBoolean.FALSE);
    // 曾经发布
    headerVO.setBreleasedover(null);
    // 制单日期
    headerVO.setDmakedate(null);
    // 响应人
    headerVO.setPk_resppsn(null);
    // 响应日期
    headerVO.setTresptime(null);
    // 拒绝/变更理由
    headerVO.setVreason(null);
  }

}
