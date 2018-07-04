package nc.impl.pu;

import java.io.UnsupportedEncodingException;

import nc.impl.pub.ace.AceNewoaspwhPubServiceImpl;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pu.qst.newoaspwh.AggNewoaspwhaHeadVO;
import nc.itf.pu.INewoaspwhMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;

public class NewoaspwhMaintainImpl extends AceNewoaspwhPubServiceImpl implements
		INewoaspwhMaintain {

	@Override
	public void delete(AggNewoaspwhaHeadVO[] vos) throws BusinessException {
		super.pubdeleteBills(vos);
	}

	@Override
	public AggNewoaspwhaHeadVO[] insert(AggNewoaspwhaHeadVO[] vos) throws BusinessException {
		return super.pubinsertBills(vos);
	}

	@Override
	public AggNewoaspwhaHeadVO[] update(AggNewoaspwhaHeadVO[] vos) throws BusinessException {
		return super.pubupdateBills(vos);
	}

	@Override
	public AggNewoaspwhaHeadVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public String queryOpinions(String gm_prono) throws BusinessException {
		// TODO 自动生成的方法存根
		String sql = "select rest_sugg from MSG_OASPJG ";
		DataAccessUtils queryUtil = new DataAccessUtils();
		if(null == gm_prono) return null;
		String strsql = sql + "where gm_prono = '"+ gm_prono +"'";
		IRowSet rs = queryUtil.query(strsql);
		while(rs.next()){
			Object value = rs.getObject(0);
			if(null == value) continue;
			String valuestr = null;
			try {
				valuestr = new String((byte[])value,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return valuestr;
		}
		return null;
	}

}
