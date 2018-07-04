package nc.bs.pu.m20.maintain.rule.approve;

import org.apache.commons.lang.ArrayUtils;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.lm.pgjdcgjh.Cgag000001HVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * @author zjf 审时更改表体计划状态为‘计划生效’
 */

public class ApproveUpdateBodyStsRule implements IRule<PraybillVO> {
	@Override
	public void process(PraybillVO[] vos) {
		if (ArrayUtils.isEmpty(vos)) {
			return;
		}
		BaseDAO dao = new BaseDAO();
		for (PraybillVO aggvo : vos) {

			PraybillItemVO[] bvos = (PraybillItemVO[]) aggvo.getChildrenVO();
			for (PraybillItemVO bvo : bvos) {
				String pk_praybill_b = bvo.getPk_praybill_b();
				String a = "08";
				if (bvo.getSts_req() != a) {

					String sql = "update po_praybill_b set sts_req = '10'  where  pk_praybill_b  = '"
							+ pk_praybill_b + "'and nvl(dr,0)=0";
					try {
						dao.executeUpdate(sql);
					} catch (DAOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
		}
	}
}
