package nc.bs.pu.m4t.pf.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description 
 * 设置暂估应付标志=Y
 * @scene 
 * 期初暂估单暂估应付
 * @param 无
 * 
 * @since 6.3
 * @version 2010-8-31 下午01:44:12
 * @author zhaoyha
 */
public class EstApInfoUpdateRule implements IRule<InitialEstVO> {

	@Override
	public void process(InitialEstVO[] vos) {
		if (ArrayUtils.isEmpty(vos)) {
			return;
		}
		this.updateAPInfo(vos);

	}
	/**
	 *设置暂估应付标志=Y
	 */
	private void updateAPInfo(InitialEstVO[] vos) {
		for (InitialEstVO initialEstVO : vos) {
			for (InitialEstItemVO item : initialEstVO.getItems()) {
				// 设置暂估应付标志
				item.setBestimateap(UFBoolean.TRUE);
				item.setStatus(VOStatus.UPDATED);
			}
		}
	}
}
