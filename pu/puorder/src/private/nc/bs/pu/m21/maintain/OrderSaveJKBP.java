/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-12-29 ����09:43:53
 */
package nc.bs.pu.m21.maintain;

import java.util.Iterator;

import nc.bs.pu.m21.maintain.rule.ATPBeforeUpdateRule;
import nc.bs.pu.m21.maintain.rule.ATPUpdateRule;
import nc.bs.pu.m21.maintain.rule.MaintainMPPCtrlChkRule;
import nc.bs.pu.m21.maintain.rule.NumAndPriceCheckRule;
import nc.bs.pu.m21.maintain.rule.OrderScaleCheckRule;
import nc.bs.pu.m21.maintain.rule.PrayToPoLimitRule;
import nc.bs.pu.m21.maintain.rule.WriteBackSourceRule;
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
import nc.bs.pu.m21.maintain.rule.save.MaxPriceChkRule;
import nc.bs.pu.m21.maintain.rule.save.MaxStocksChkRule;
import nc.bs.pu.m21.maintain.rule.save.NecessaryFillRule;
import nc.bs.pu.m21.maintain.rule.save.OrderCodeProceRule;
import nc.bs.pu.m21.maintain.rule.save.OrderDirectPurchaseUpdateRule;
import nc.bs.pu.m21.maintain.rule.save.PaymentCheckDataBeforeRule;
import nc.bs.pu.m21.maintain.rule.save.PlanArriveDateCheckRule;
import nc.bs.pu.m21.maintain.rule.save.PrePayLimitCheckRule;
import nc.bs.pu.m21.maintain.rule.save.PresentStocksChkRule;
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
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.plugin.RuleCollection;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.rule.NewestOrgVersionFillRule;
import nc.vo.pu.pub.rule.TrantypeNotNullCheckRule;
import nc.vo.pu.pub.rule.pf.NoPassUpdateRule;
import nc.vo.pub.VOStatus;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.rule.PurchaseOrgEnableCheckRule;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��������BP
 * <li>
 * <li>
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2009-12-29 ����09:43:53
 */
public class OrderSaveJKBP {

	private OrderContext ctx;

	public OrderSaveJKBP(OrderContext ctx) {
		this.ctx = ctx;
	}

	/**
	 * ����������������������BP��ǰ̨���桢��ʽ���棩��
	 * <p>
	 * <b>examples:</b>
	 * <p>
	 * ʹ��ʾ��
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param orderVos
	 * @return <p>
	 * @since 6.0
	 * @author zhaoyha
	 * @time 2009-12-29 ����01:19:28
	 */
	public OrderVO[] save(OrderVO[] clientVos, OrderVO[] originVos) {

		OrderVO[] returnedVos;
		if (clientVos[0].getHVO().getStatus() != VOStatus.NEW) {
			CompareAroundProcesser<OrderVO> processer = new CompareAroundProcesser<OrderVO>(
					OrderPluginPoint.UPDATE);
			this.addBeforeRule(processer, clientVos);
			
			//processer.before(clientVos, originVos);
			returnedVos = new BillUpdate<OrderVO>().update(clientVos, originVos);
			this.addAfterRule(processer);
			processer.after(returnedVos, originVos);
		} else {
			CompareAroundProcesser<OrderVO> processer = new CompareAroundProcesser<OrderVO>(
					OrderPluginPoint.INSERT);
			this.addBeforeRule(processer, clientVos);
			/*
			Iterator<Object> iterator = this.before.iterator();
			IRule rule = (IRule) this.addBeforeRule(processer, clientVos);
	        rule.process(clientVos);
	        */
			//processer.before(clientVos, originVos);
			returnedVos = new BillInsert<OrderVO>().insert(clientVos);
			this.addAfterRule(processer);
			processer.after(returnedVos, null);
			//this.addAfterRule(processer);
		}

		// �����VO���ܱ�֤�������������ݿ�ͬ�������������Ҫ���ز飬�������������Ҳ��
		return returnedVos;
	}

	private void addAfterRule(CompareAroundProcesser<OrderVO> processer) {
		processer.addAfterRule(new BillCodeUniqueRule());
		// ���ִ������
		processer.addAfterRule(new PresentStocksChkRule(this.ctx));
		// ���ϣ���Ӧ�̣��ɼ��Լ��
		processer.addAfterRule(new DocVisibilityChkRule());
		// ��������������ؼ��
		processer.addAfterRule(new TranTypeChkRule());
		// �����ͬ�ɹ����ϵļ��
		processer.addAfterRule(new CntPurMaterialChkRule());
		// ����޼ۼ��
		processer.addAfterRule(new MaxPriceChkRule(this.ctx));
		// ��д���ε���
		processer.addAfterRule(new WriteBackSourceRule(this.ctx));
		// ��д���ں�ͬ�ɹ�������˰����
		processer.addAfterRule(new WriteBackPriceForET());

		// �������¿�����
		processer.addAfterRule(new ATPUpdateRule());
		// ��߿������飬�ù��������ATPUpdateRule֮��
		processer.addAfterRule(new MaxStocksChkRule(this.ctx));
		// �����ڲ�������Ϣ��V60��ʵ��
		// ����
		// processer.addAfterRule(new BatchCodeAfterRule());
		// ����������¼�
		processer.addAfterRule(new SaveEventAfterRule());

		//
		// processer.addAfterRule(new WriteBusiLogCompareRule<OrderVO>(
		// PuBusiLogActionCode.save));
	}

	private void addBeforeRule(CompareAroundProcesser<OrderVO> processer,
			OrderVO[] orgUpdateVos) {
		
		//processer.addBeforeRule(new CrossRuleValidateRule<OrderVO>(POBillType.Order.getCode()));
		new CrossRuleValidateRule<OrderVO>(POBillType.Order.getCode()).process(orgUpdateVos);
		
		// VO���
		//processer.addBeforeRule(new SaveVOValidateRule());
		new SaveVOValidateRule().process(orgUpdateVos);
		
		// �������
		//processer.addBeforeRule(new AccrateCheckRule());
		new AccrateCheckRule().process(orgUpdateVos);
		// �ʱ���
		//processer.addBeforeRule(new IsdepositCheckRule());
		new IsdepositCheckRule().process(orgUpdateVos);
		// �к�
		//processer.addBeforeRule(new RowNoRule());
		new RowNoRule().process(orgUpdateVos);
		// ��������֯���°�
		//processer.addBeforeFinalRule(new NewestOrgVersionFillRule<OrderVO>(OrderHeaderVO.PK_ORDER));
		new NewestOrgVersionFillRule<OrderVO>(OrderHeaderVO.PK_ORDER).process(orgUpdateVos);
		// �ǿ�����
		//processer.addBeforeRule(new ItemEmptyRule());
		new ItemEmptyRule().process(orgUpdateVos);
		// ���ȼ�����
		//processer.addBeforeRule(new OrderScaleCheckRule());
		new OrderScaleCheckRule().process(orgUpdateVos);
		// ��齻�������Ƿ�Ϊ��
		//processer.addBeforeFinalRule(new TrantypeNotNullCheckRule<OrderVO>());
		new TrantypeNotNullCheckRule<OrderVO>().process(orgUpdateVos);
		// ������ͨ��ʱ�޸ĺ��״̬���������������ڵȴ���
		//processer.addBeforeFinalRule(new NoPassUpdateRule<OrderVO>());
		new NoPassUpdateRule<OrderVO>().process(orgUpdateVos);
		// �����֯�����ϵ�ƥ����
		//processer.addBeforeRule(new MaterialInStorckOrgRule());
		new MaterialInStorckOrgRule().process(orgUpdateVos);
		// �ջ���������У�� by guoyk 2014��9��23��
		//processer.addBeforeFinalRule(new ArrliabCenterRule());
		new ArrliabCenterRule().process(orgUpdateVos);
		// ���ۺ������ĺϷ���У��
		//processer.addBeforeRule(new NumAndPriceCheckRule());
		new NumAndPriceCheckRule().process(orgUpdateVos);
		// ����֯ͣ�ü��
		//processer.addBeforeRule(new PurchaseOrgEnableCheckRule<OrderVO>());
		new PurchaseOrgEnableCheckRule<OrderVO>().process(orgUpdateVos);
		// �����ƻ���ؼ��
		//processer.addBeforeRule(new ArrvPlanChkRule(orgUpdateVos));
		new ArrvPlanChkRule(orgUpdateVos).process(orgUpdateVos);
		// �ɹ���ͬ���ƣ����ݺ�ͬ���Ʒ�ʽ���Ƶ��ۣ��ں�ͬ�ǿ���
		// processer.addBeforeRule(new CntControlRule());
		// �ƻ��������ڵļ��
		//processer.addBeforeRule(new PlanArriveDateCheckRule());
		//new PlanArriveDateCheckRule().process(orgUpdateVos);
		//processer.addBeforeRule(new DirectOrgCheckRule());
		new DirectOrgCheckRule().process(orgUpdateVos);
		// ɾ����飬�ѹرյ��������к����������У�����ɾ��
		//processer.addBeforeRule(new DeleteRule());
		new DeleteRule().process(orgUpdateVos);
		// �Զ�������
		// ��Ҫ���
		//processer.addBeforeRule(new NecessaryFillRule());
		new NecessaryFillRule().process(orgUpdateVos, null);
		// ֱ�˱�־����
		//processer.addBeforeRule(new OrderDirectPurchaseUpdateRule());
		new OrderDirectPurchaseUpdateRule().process(orgUpdateVos);
		// Ԥ�����޶�ļ��
		//processer.addBeforeRule(new PrePayLimitCheckRule());
		new PrePayLimitCheckRule().process(orgUpdateVos);
		// �ɹ��������ݺ�(������)�������
		//processer.addBeforeRule(new OrderCodeProceRule());
		new OrderCodeProceRule().process(orgUpdateVos, orgUpdateVos);
		// �������Ϳ���
		//processer.addBeforeRule(new PrayToPoLimitRule());
		new PrayToPoLimitRule().process(orgUpdateVos);

		// ����������ǰ����
		//processer.addBeforeRule(new ATPBeforeUpdateRule());
		new ATPBeforeUpdateRule().process(orgUpdateVos, null);
		// ƥ��ҵ������(��������)
		//processer.addBeforeRule(new ConfirmOrderBiztypeRule());
		new ConfirmOrderBiztypeRule().process(orgUpdateVos, orgUpdateVos);
		// �ɹ��ƻ����Ƽ��
		//processer.addBeforeRule(new MaintainMPPCtrlChkRule());
		new MaintainMPPCtrlChkRule().process(orgUpdateVos, null);
		// ���ɸ�������У��
		// MarAssistantSaveRule<OrderVO> marRule = new
		// MarAssistantSaveRule<OrderVO>();
		// marRule.setNotNullValidate(OrderItemVO.PK_MATERIAL);
		// processer.addBeforeRule(marRule);
		//processer.addBeforeRule(new PurtypeSetterRule());
		new PurtypeSetterRule().process(orgUpdateVos);
		//processer.addBeforeRule(new UserDefSaveRule<OrderVO>(new Class[] {OrderHeaderVO.class, OrderItemVO.class }));
		new UserDefSaveRule<OrderVO>(new Class[] {OrderHeaderVO.class, OrderItemVO.class }).process(orgUpdateVos);
		// ���ý����־
		//processer.addBeforeFinalRule(new BorrowpurSetterRule());
		new BorrowpurSetterRule().process(orgUpdateVos);
		// ���ι���
		//processer.addBeforeRule(new BatchCodeBeforeRule());
		new BatchCodeBeforeRule().process(orgUpdateVos, null);
		// ��������ǰ�¼�
		//processer.addBeforeRule(new SaveEventBeforeRule());
		new SaveEventBeforeRule().process(orgUpdateVos, null);
		// yanxm5 �ɹ���������ʱУ�飬�����Ӧ�̱�������������ܱ���
		//processer.addBeforeRule(new IsBlistBeforeRule());
		new IsBlistBeforeRule().process(orgUpdateVos);
		// �������������ֵ���̶���������ֵ
		//processer.addBeforeRule(new PaymentCheckDataBeforeRule());
		new PaymentCheckDataBeforeRule().process(orgUpdateVos);
	}

}
