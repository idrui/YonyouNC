package nc.vo.pu.m4t.rule;

import java.util.Arrays;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m27.entity.SettleBillItemVOUtil;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description �����ڳ��ݹ����ı���Ӱ��ɱ���־
 * @scene
 * �ڳ��ݹ�������
 * @param ��
 * 
 * @since 6.3
 * @version 2011-12-13 ����1:53:50
 * @author zhaoyha
 */

public class InitialEstAffectCostFlagRule implements IRule<InitialEstVO> {

	
	@Override
	public void process(InitialEstVO[] vos) {
		if (ArrayUtils.isEmpty(vos)) {// ����֧��������ʱ���д˷�֧��
			return;
		}
		// �����Ƿ�Ӱ��ɱ���־
		this.setAffectCost(vos);
	}
	/**
	 *�����ڳ��ݹ����ı���Ӱ��ɱ���־,����V61�����ڳ��ݹ�ֻ�������ϼ�ֵ����
	 */
	private void setAffectCost(InitialEstVO[] vos) {
		// ����V61�����ڳ��ݹ�ֻ�������ϼ�ֵ����
		Map<String, Map<String, UFBoolean>> map = SettleBillItemVOUtil
				.getEffectByMaterial(Arrays.asList(vos));
		for (InitialEstVO vo : vos) {
			String pk_org = vo.getHeader().getPk_org();
			for (InitialEstItemVO item : vo.getItems()) {
				UFBoolean oldflag = item.getBaffectcost();
				UFBoolean flag = map.get(pk_org).get(item.getPk_material());
				if (flag == null) {
					ExceptionUtils
							.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
									.getNCLangRes().getStrByID("4004060_0",
											"04004060-0244")/*
															 * @res
															 * "ҵ�����ݴ������������Ƿ���䵽������֯��"
															 */);
				} else if (flag.equals(oldflag)) {
					continue;
				}
				item.setBaffectcost(flag);
				item.setStatus(VOStatus.UPDATED);
			}
		}
	}

}
