package nc.bs.pu.position.maintain.rule;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 计划岗(采购岗)物料设置保存的规则类
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>检查岗位编码是否重复
 * </ul>
 * <p>
 * 
 * @author GGR
 * @time 2009-11-20 下午04:51:25
 * @since 6.0
 */
public class CheckCodeRule implements IRule<PositionVO> {

	@Override
	public void process(PositionVO[] vos) {
		for (PositionVO PositionVO : vos) {
			PositionHeaderVO headVO = (PositionHeaderVO) PositionVO.getParentVO();

			this.checkUsed(headVO);
		}
	}

	private void checkUsed(PositionHeaderVO headVO) {
		SqlBuilder sql = new SqlBuilder();
		sql.append(" select code from po_position ");
		sql.append(" where ");
		sql.append(" dr = 0 ");
		sql.append(" and code ",headVO.getCode());
		sql.append(" and fpositiontype ", headVO.getFpositiontype().intValue());
		if (null != headVO.getPk_position()) {
			sql.append(" and pk_position ", " != " ,headVO.getPk_position());
		}
		if (null != headVO.getPk_org()) {
			sql.append(" and pk_org ",headVO.getPk_org());
		}
		DataAccessUtils util = new DataAccessUtils();
		
		IRowSet rowset = util.query(sql.toString());
		String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
				"4004080_0", "04004080-0005")/* @res "岗位编码:" */
				+ headVO.getCode()
				+ nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004080_0",
						"04004080-0006")/* @res "  重复！请重新输入！" */;
		while(rowset.next()){
			ExceptionUtils.wrappBusinessException(msg);
		}
	}
}
