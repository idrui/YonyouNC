package nc.pubimpl.pu.m21.cmp.m36d1.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.pu.m21.writeback.cmp.OrderPayPlanWriteBackFor36D1BP;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.pu.m21.cmp.m36d1.OrderPayPlanWriteBackParaFor36D1;
import nc.vo.cmp.apply.AggApplyVO;
import nc.vo.cmp.apply.ApplyBVO;
import nc.vo.cmp.apply.ApplyVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 采购订单付款计划提供给资金的付款申请单回写工具类
 * 
 * @since 6.3.1
 * @version 2013-10-8 下午03:40:25
 * @author fanly3
 */
public class OrderPayPlanWriteBackFor36D1Util {
	private static final OrderPayPlanWriteBackFor36D1Util instance = new OrderPayPlanWriteBackFor36D1Util();

	public static OrderPayPlanWriteBackFor36D1Util getInstance() {
		return OrderPayPlanWriteBackFor36D1Util.instance;
	}

	/**
	 * 将Object对象转换为AggApplyVO数组
	 * 
	 * @param object
	 *          需要转换的object对象
	 * @return AggApplyVO数组
	 */
	public AggApplyVO[] convertObjectToAggVos(Object object) {
		AggApplyVO[] aggVos = null;
		if (object == null) {
			return aggVos;
		}
		if (object.getClass().isArray()) {
			Object[] obj = (Object[]) object;
			aggVos = new AggApplyVO[obj.length];
			System.arraycopy(obj, 0, aggVos, 0, obj.length);
		} else {
			aggVos = new AggApplyVO[0];
			aggVos[0] = (AggApplyVO) object;
		}
		return aggVos;
	}

	/**
	 * 过滤出来源于采购订单付款计划的付款申请单聚合VO
	 * 
	 * @param aggVos
	 * @return
	 */
	public AggApplyVO[] getFilterAggVos(AggApplyVO[] aggVos) {
		if (ArrayUtils.isEmpty(aggVos)) {
			return null;
		}
		List<AggApplyVO> list = new ArrayList<AggApplyVO>();
		for (AggApplyVO aggVo : aggVos) {
			ApplyBVO[] bodyvos = (ApplyBVO[]) aggVo.getChildren(ApplyBVO.class);
			if (POBillType.Order.getCode().equals(bodyvos[0].getPk_srcbilltypeid())) {
				list.add(aggVo);
			}
		}
		return list.toArray(new AggApplyVO[list.size()]);
	}

	/**
	 * 组织回写参数
	 * 
	 * @param aggVo
	 *          当前聚合vo
	 * @param origAggVo
	 *          原始聚合vo
	 * @return 组织后的回写参数map
	 */
	public Map<String, List<RewritePara>> getRewritePara(AggApplyVO[] aggVo,
			AggApplyVO[] origAggVo) {
		Map<String, List<RewritePara>> rwParaMap = null;
		BillRewriter billRewriter = this.getBillRewriter();
		if (ArrayUtils.isEmpty(aggVo)) {
			rwParaMap = billRewriter.splitForDelete(origAggVo);
		} else if (ArrayUtils.isEmpty(origAggVo)) {
			rwParaMap = billRewriter.splitForInsert(aggVo);
		} else {
			rwParaMap = billRewriter.splitForUpdate(aggVo, origAggVo);
		}
		return rwParaMap;
	}

	/**
	 * 得到回写参数VO数组
	 * 
	 * @param paraList
	 * @return
	 */
	public OrderPayPlanWriteBackParaFor36D1[] getWritebackVO(
			List<RewritePara> paraList) {
		OrderPayPlanWriteBackParaFor36D1[] reWritePara = new OrderPayPlanWriteBackParaFor36D1[paraList
				.size()];
		for (int i = 0; i < paraList.size(); i++) {
			OrderPayPlanWriteBackParaFor36D1 para = new OrderPayPlanWriteBackParaFor36D1();
			para.setPk_order_payplan(paraList.get(i).getCsrcbid());
			para.setDiffOrigMny(paraList.get(i).getNnum());
			para.setDiffMny(paraList.get(i).getNnum2());
			para.setTs(paraList.get(i).getSrcbTS());
			reWritePara[i] = para;
		}
		return reWritePara;
	}

	/**
	 * 资金付款申请单回写采购订单付款计划
	 * 
	 * @param backVOs
	 *          　资金付款申请单回写订单付款计划参数VO数组
	 */
	public void writeBack(OrderPayPlanWriteBackParaFor36D1[] backVOs) {
		new OrderPayPlanWriteBackFor36D1BP().writeBack(backVOs);
	}

	/**
	 * 得到回写采购订单付款计划的工具类
	 * 
	 * @return 回写采购订单付款计划的工具类实例
	 */
	private BillRewriter getBillRewriter() {
		ItemKeyMapping mapping = this.getItemMapping();
		PayPlanBillRewriter billRewriter = new PayPlanBillRewriter(mapping);
		billRewriter.addSRCHeadClazz(POBillType.Order.getCode(),
				OrderHeaderVO.class);
		billRewriter.addSRCItemClazz(POBillType.Order.getCode(), PayPlanVO.class);
		return billRewriter;
	}

	private ItemKeyMapping getItemMapping() {
		ItemKeyMapping mapping = new ItemKeyMapping();
		mapping.setVsrctypeKey(ApplyBVO.PK_SRCBILLTYPEID);
		mapping.setCsrcidKey(ApplyBVO.PK_SRCBILL);
		mapping.setCsrcbidKey(ApplyBVO.PK_SRCBILLROWID);
		mapping.setSrcTSKey(ApplyVO.SRCTS);
		// 申请付款金额
		mapping.setNnumKey(ApplyBVO.APPLYMNY);
		// 申请付款金额(组织本币)
		mapping.setNnum2Key(ApplyBVO.OLCAPPLYMNY);
		return mapping;
	}
}
