package nc.bs.pu.m23.maintain.rule.update;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.pub.PUIDQueryBuilder;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pu.m23.entity.ArriveItemVO;

/**
 * 
 * @description
 * 到货单反检，删除质检明细
 * @scene
 * 到货单反检
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-3-12 上午10:42:17
 * @author zhangshqb
 */
public class UpdateChkRule implements IRule<ArriveItemVO> {

  @Override
	public void process(ArriveItemVO[] vos) {
  	PUIDQueryBuilder builder = new PUIDQueryBuilder();
		List<String> order_bs = new ArrayList<String>();
		for (ArriveItemVO bvo : vos) {
			String order_b = bvo.getPk_arriveorder_b();
			order_bs.add(order_b);
		}
		String sql = builder.buildSQL("pk_arriveorder_b",
				order_bs.toArray(new String[order_bs.size()]));
		VOQuery<ArriveBbVO> voQuery = new VOQuery<ArriveBbVO>(ArriveBbVO.class);
		ArriveBbVO[] bbVOs = voQuery.query(" and " + sql, null);
		if (bbVOs != null && bbVOs.length != 0) {
			VODelete<ArriveBbVO> voDelete = new VODelete<ArriveBbVO>();
			voDelete.delete(bbVOs);
		}
	}
}
