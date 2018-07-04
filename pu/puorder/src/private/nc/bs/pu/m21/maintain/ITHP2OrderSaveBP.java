package nc.bs.pu.m21.maintain;

import nc.bs.pu.m21.maintain.rule.ATPBeforeUpdateRule;
import nc.bs.pu.m21.maintain.rule.ATPUpdateRule;
import nc.bs.pu.m21.maintain.rule.MaintainMPPCtrlChkRule;
import nc.bs.pu.m21.maintain.rule.NumAndPriceCheckRule;
import nc.bs.pu.m21.maintain.rule.OrderScaleCheckRule;
import nc.bs.pu.m21.maintain.rule.PrayToPoLimitRule;
import nc.bs.pu.m21.maintain.rule.save.AccrateCheckRule;
import nc.bs.pu.m21.maintain.rule.save.ArrliabCenterRule;
import nc.bs.pu.m21.maintain.rule.save.ArrvPlanChkRule;
import nc.bs.pu.m21.maintain.rule.save.BatchCodeBeforeRule;
import nc.bs.pu.m21.maintain.rule.save.BillCodeUniqueRule;
import nc.bs.pu.m21.maintain.rule.save.BorrowpurSetterRule;
import nc.bs.pu.m21.maintain.rule.save.CntPurMaterialChkRule;
import nc.bs.pu.m21.maintain.rule.save.ConfirmOrderBiztypeRule;
import nc.bs.pu.m21.maintain.rule.save.DirectOrgCheckRule;
import nc.bs.pu.m21.maintain.rule.save.DocVisibilityChkRule;
import nc.bs.pu.m21.maintain.rule.save.IsBlistBeforeRule;
import nc.bs.pu.m21.maintain.rule.save.IsdepositCheckRule;
import nc.bs.pu.m21.maintain.rule.save.ItemEmptyRule;
import nc.bs.pu.m21.maintain.rule.save.MaterialInStorckOrgRule;
import nc.bs.pu.m21.maintain.rule.save.NecessaryFillRule;
import nc.bs.pu.m21.maintain.rule.save.OrderCodeProceRule;
import nc.bs.pu.m21.maintain.rule.save.OrderDirectPurchaseUpdateRule;
import nc.bs.pu.m21.maintain.rule.save.PaymentCheckDataBeforeRule;
import nc.bs.pu.m21.maintain.rule.save.PlanArriveDateCheckRule;
import nc.bs.pu.m21.maintain.rule.save.PrePayLimitCheckRule;
import nc.bs.pu.m21.maintain.rule.save.PurtypeSetterRule;
import nc.bs.pu.m21.maintain.rule.save.SaveEventAfterRule;
import nc.bs.pu.m21.maintain.rule.save.SaveEventBeforeRule;
import nc.bs.pu.m21.maintain.rule.save.SaveVOValidateRule;
import nc.bs.pu.m21.maintain.rule.save.TranTypeChkRule;
import nc.bs.pu.m21.maintain.rule.save.WriteBackPriceForET;
import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.scmpub.rule.CrossRuleValidateRule;
import nc.impl.pu.m21.action.rule.RowNoRule;
import nc.impl.pu.m21.action.rule.revise.DeleteRule;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.rule.NewestOrgVersionFillRule;
import nc.vo.pu.pub.rule.TrantypeNotNullCheckRule;
import nc.vo.pu.pub.rule.pf.NoPassUpdateRule;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.rule.PurchaseOrgEnableCheckRule;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��������BP
 * <li>
 */
public class ITHP2OrderSaveBP {

	private OrderVO[] orderVos;

	public ITHP2OrderSaveBP(OrderVO[] orderVos) {
		this.orderVos = orderVos;
	}

	/**
	 * ����������������������BP��ǰ̨���桢��ʽ���棩��
	 * <p>
	 */
	public OrderVO[] save(OrderVO[] clientVos, OrderVO[] originVos) {

		OrderVO[] returnedVos;
		CompareAroundProcesser<OrderVO> processer = new CompareAroundProcesser<OrderVO>(OrderPluginPoint.INSERT);
		this.addBeforeRule(processer, clientVos);
		this.addAfterRule(processer);
		processer.before(clientVos, originVos);
		returnedVos = new BillInsert<OrderVO>().insert(clientVos);
		processer.after(returnedVos, null);

		// �����VO���ܱ�֤�������������ݿ�ͬ�������������Ҫ���ز飬�������������Ҳ��
		return returnedVos;
	}

	private void addAfterRule(CompareAroundProcesser<OrderVO> processer) {
//		 processer.addAfterRule(new BillCodeUniqueRule());
//		 // ���ִ������
//	       //processer.addAfterRule(new PresentStocksChkRule(this.ctx));
//		// // ���ϣ���Ӧ�̣��ɼ��Լ��
//		 processer.addAfterRule(new DocVisibilityChkRule());
//		// // ��������������ؼ��
//		 processer.addAfterRule(new TranTypeChkRule());
//		// // �����ͬ�ɹ����ϵļ��
//		 processer.addAfterRule(new CntPurMaterialChkRule());
//		// ����޼ۼ��
//		 processer.addAfterRule(new MaxPriceChkRule(this.ctx));
//		// ��д���ε���
//		 processer.addAfterRule(new WriteBackSourceRule(this.ctx));
//		// ��д���ں�ͬ�ɹ�������˰����
//		 processer.addAfterRule(new WriteBackPriceForET());
//
//		// �������¿�����
//		 processer.addAfterRule(new ATPUpdateRule());
//		// ��߿������飬�ù��������ATPUpdateRule֮��
//		 processer.addAfterRule(new MaxStocksChkRule(this.ctx));
//		// �����ڲ�������Ϣ��V60��ʵ��
//		// ����
//		 processer.addAfterRule(new BatchCodeAfterRule());
//		 //����������¼�
//		 processer.addAfterRule(new SaveEventAfterRule());
//
//		
//		 processer.addAfterRule(new WriteBusiLogCompareRule<OrderVO>(
//		 PuBusiLogActionCode.save));
	}

	private void addBeforeRule(CompareAroundProcesser<OrderVO> processer, OrderVO[] orgUpdateVos) {
		// processer.addBeforeRule(new
		// CrossRuleValidateRule<OrderVO>(POBillType.Order.getCode()));
		// // VO���
		// processer.addBeforeRule(new SaveVOValidateRule());
		// // �������
		// processer.addBeforeRule(new AccrateCheckRule());
		// // �ʱ���
		// processer.addBeforeRule(new IsdepositCheckRule());
		// // �к�
		// processer.addBeforeRule(new RowNoRule());
		// // ��������֯���°�
		// processer.addBeforeFinalRule(new
		// NewestOrgVersionFillRule<OrderVO>(OrderHeaderVO.PK_ORDER));
		// // �ǿ�����
		// processer.addBeforeRule(new ItemEmptyRule());
		// // ���ȼ�����
		// processer.addBeforeRule(new OrderScaleCheckRule());
		// // ��齻�������Ƿ�Ϊ��
		// processer.addBeforeFinalRule(new
		// TrantypeNotNullCheckRule<OrderVO>());
		// // ������ͨ��ʱ�޸ĺ��״̬���������������ڵȴ���
		// processer.addBeforeFinalRule(new NoPassUpdateRule<OrderVO>());
		// // �����֯�����ϵ�ƥ����
		// processer.addBeforeRule(new MaterialInStorckOrgRule());
		// // �ջ���������У�� by guoyk 2014��9��23��
		// processer.addBeforeFinalRule(new ArrliabCenterRule());
		// // ���ۺ������ĺϷ���У��
		// processer.addBeforeRule(new NumAndPriceCheckRule());
		// // ����֯ͣ�ü��
		// processer.addBeforeRule(new PurchaseOrgEnableCheckRule<OrderVO>());
		// // �����ƻ���ؼ��
		// processer.addBeforeRule(new ArrvPlanChkRule(orgUpdateVos));
		// // �ɹ���ͬ���ƣ����ݺ�ͬ���Ʒ�ʽ���Ƶ��ۣ��ں�ͬ�ǿ���
		// // processer.addBeforeRule(new CntControlRule());
		// // �ƻ��������ڵļ��
		// processer.addBeforeRule(new PlanArriveDateCheckRule());
		// processer.addBeforeRule(new DirectOrgCheckRule());
		// // ɾ����飬�ѹرյ��������к����������У�����ɾ��
		// processer.addBeforeRule(new DeleteRule());
		// // �Զ�������
		// // ��Ҫ���
		// processer.addBeforeRule(new NecessaryFillRule());
		// // ֱ�˱�־����
		// processer.addBeforeRule(new OrderDirectPurchaseUpdateRule());
		// // Ԥ�����޶�ļ��
		// processer.addBeforeRule(new PrePayLimitCheckRule());
		// // �ɹ��������ݺ�(������)�������
		 processer.addBeforeRule(new OrderCodeProceRule());
		// // �������Ϳ���
		// processer.addBeforeRule(new PrayToPoLimitRule());
		//
		// // ����������ǰ����
		// processer.addBeforeRule(new ATPBeforeUpdateRule());
		// // ƥ��ҵ������(��������)
		// processer.addBeforeRule(new ConfirmOrderBiztypeRule());
		// // �ɹ��ƻ����Ƽ��
		// processer.addBeforeRule(new MaintainMPPCtrlChkRule());
		// // ���ɸ�������У��
		// // MarAssistantSaveRule<OrderVO> marRule = new
		// // MarAssistantSaveRule<OrderVO>();
		// // marRule.setNotNullValidate(OrderItemVO.PK_MATERIAL);
		// // processer.addBeforeRule(marRule);
		// processer.addBeforeRule(new PurtypeSetterRule());
		// processer.addBeforeRule(new UserDefSaveRule<OrderVO>(new Class[] {
		// OrderHeaderVO.class, OrderItemVO.class }));
		// // ���ý����־
		// processer.addBeforeFinalRule(new BorrowpurSetterRule());
		// // ���ι���
		// processer.addBeforeRule(new BatchCodeBeforeRule());
		// // ��������ǰ�¼�
		// processer.addBeforeRule(new SaveEventBeforeRule());
		// // yanxm5 �ɹ���������ʱУ�飬�����Ӧ�̱�������������ܱ���
		// processer.addBeforeRule(new IsBlistBeforeRule());
		// // �������������ֵ���̶���������ֵ
		// processer.addBeforeRule(new PaymentCheckDataBeforeRule());
	}

}
