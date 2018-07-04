package nc.bs.pu.m20.maintain.rule.update;

import java.util.ArrayList;

import org.apache.commons.lang.ArrayUtils;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.uap.IUAPQueryBS;
import nc.vo.arap.uforeport.SqlBuffer;
import nc.vo.lm.pgjdcgjh.Cgag000001HVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;

public class RwritepgjktUpdatedataRule  implements IRule<PraybillVO> {

	/**
	 * 根据来表头来源单据主键更新攀钢采购计划接口中间表 处理备注（msginfo）,消息状态（msgflag）字段
	 */
	@Override
	public void process(PraybillVO[] vos) {

		IUAPQueryBS qbs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		BaseDAO dao = new BaseDAO();
		if (ArrayUtils.isEmpty(vos)) {
			return;
		}

		for (PraybillVO aggvo : vos) {
			PraybillItemVO[] bvos = (PraybillItemVO[]) aggvo
					.getChildren(PraybillItemVO.class);
			for (PraybillItemVO bvo : bvos) {
				if(bvo.getStatus() == VOStatus.DELETED){
					SqlBuffer sqlWhere = new SqlBuffer();
					// String csourceid=bvo.getCsourceid();//来源单据主键
					sqlWhere.append("nvl(dr,0)  =0 \n ");
					sqlWhere.append("and pk_pgjdcgjh = '");
					sqlWhere.append(bvo.getCsourceid());
					sqlWhere.append("' ");
					try {
						ArrayList<Cgag000001HVO> cgahvos = (ArrayList<Cgag000001HVO>) qbs
								.retrieveByClause(Cgag000001HVO.class,
										sqlWhere.toString());
						if (cgahvos != null && cgahvos.size() > 0) {
							for (int i = 0; i < cgahvos.size(); i++) {
								Cgag000001HVO cgahvo = cgahvos.get(i);
								cgahvo.setMsginfo("未处理");
								cgahvo.setMsgflag("0");
							}
							// 更新攀钢采购计划接口中间表
							dao.updateVOArray(
									cgahvos.toArray(new Cgag000001HVO[0]),
									new String[] { "msginfo", "msgflag" });
						}
					} catch (BusinessException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
		}
	}
}
