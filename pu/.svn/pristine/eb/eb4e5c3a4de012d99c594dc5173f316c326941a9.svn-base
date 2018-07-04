/**
 * 
 */
package nc.impl.pu;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.impl.obm.ca.CAFactory;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.pu.IQueryForLineClose;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.cgfa.CgfaViewVO;
import nc.vo.pu.cgfa.Cgfab;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.bill.CombineBill;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.UserVO;
import nc.vo.uap.rbac.UserRoleVO;

/**
 * @author wangzym
 * @version 2017年5月5日 下午3:13:26
 */
public class QueryForLineCloseImpl implements IQueryForLineClose {

	/**
	 * 
	 */
	public QueryForLineCloseImpl() {
		// TODO 自动生成的构造函数存根
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see nc.itf.pu.IQueryForLineClose#hasForword(java.lang.String[])
	 */
	@Override
	public int[] hasForword(HashMap<Integer, String> bpks) {
		// TODO 自动生成的方法存根
		List<Integer> rows = new ArrayList<Integer>();
		DataAccessUtils dao = new DataAccessUtils();
		for (Map.Entry<Integer, String> entry : bpks.entrySet()) {
			String bpk = entry.getValue();
			String sql = "select count(*) from  PURP_PRICEAUDIT_B where  csrcbid='"
					+ bpk + "'" + "and PURP_PRICEAUDIT_B.dr<>'1'";
			IRowSet rs = dao.query(sql);
			String[] result = rs.toOneDimensionStringArray();
			if (Integer.parseInt(result[0]) != 0) {
				rows.add(entry.getKey());
			}

		}
		int[] rowss = new int[rows.size()];
		Integer[] row = rows.toArray(new Integer[rows.size()]);
		for (int i = 0; i < row.length; i++) {
			Integer integer = row[i];
			rowss[i] = integer.intValue();

		}
		return rowss;
	}

	/*
	 * （非 Javadoc）
	 */
	@Override
	public void reWritePrayBill(String[] bpks, UFDouble[] ufDoubles,
			String actionName) {
		// TODO 自动生成的方法存根
		// 回写上游请购单，将上游行关闭设置成最新的值，并且设置sts_req为02让下游可以参照到
		if (actionName.equals("hgb")) {
			// 行关闭
			PraybillItemVO[] bvos = new PraybillItemVO[bpks.length];
			for (int i = 0; i < bpks.length; i++) {
				VOQuery<PraybillItemVO> query = new VOQuery<PraybillItemVO>(
						PraybillItemVO.class);
				PraybillItemVO[] old = query.query(new String[] { bpks[i] });
				UFDouble oldSumcgfanum = (UFDouble) old[0]
						.getAttributeValue("sumcgfanum");
				UFDouble newSumcgfanum = oldSumcgfanum.sub(ufDoubles[i]);

				PraybillItemVO praybillitemvo = new PraybillItemVO();
				;
				praybillitemvo.setPrimaryKey(bpks[i]);
				praybillitemvo.setAttributeValue("whetherlineclose",
						UFBoolean.TRUE);
				praybillitemvo.setAttributeValue("sts_req", "02");
				// 设置成可以参照
				praybillitemvo.setSts_req("02");
				praybillitemvo.setAttributeValue("sumcgfanum", newSumcgfanum);
				bvos[i] = praybillitemvo;
			}

			VOUpdate<PraybillItemVO> update = new VOUpdate<PraybillItemVO>();
			String[] names = new String[] { "sts_req", "whetherlineclose",
					"sumcgfanum" };
			update.update(bvos, names);

		} else if (actionName.equals("qxhgb")) {
			// 取消行关闭
			PraybillItemVO[] bvos = new PraybillItemVO[bpks.length];
			for (int i = 0; i < bpks.length; i++) {
				VOQuery<PraybillItemVO> query = new VOQuery<PraybillItemVO>(
						PraybillItemVO.class);
				PraybillItemVO praybillitemvo = new PraybillItemVO();
				PraybillItemVO[] old = query.query(new String[] { bpks[i] });
				UFDouble oldSumcgfanum = (UFDouble) old[0]
						.getAttributeValue("sumcgfanum");
				UFDouble newSumcgfanum = oldSumcgfanum.add(ufDoubles[i]);
				praybillitemvo.setPrimaryKey(bpks[i]);
				praybillitemvo.setAttributeValue("whetherlineclose",
						UFBoolean.FALSE);
				praybillitemvo.setAttributeValue("sts_req", "03");
				praybillitemvo.setAttributeValue("sumcgfanum", newSumcgfanum);
				// 设置成可以参照
				praybillitemvo.setSts_req("03");
				bvos[i] = praybillitemvo;
			}

			VOUpdate<PraybillItemVO> update = new VOUpdate<PraybillItemVO>();
			String[] names = new String[] { "sts_req", "whetherlineclose",
					"sumcgfanum" };
			update.update(bvos, names);
		}

	}

	@Override
	public void updateForSelf(String[] pks, String actionName) {
		// 为了保存本地的是否行关闭
		if (actionName.equals("hgb")) {
			List<Cgfab> bvos = new ArrayList<Cgfab>();
			for (String string : pks) {
				Cgfab bvo = new Cgfab();
				bvo.setPrimaryKey(string);
				bvo.setAttributeValue("whetherlineclose", UFBoolean.TRUE);
				bvos.add(bvo);
			}
			VOUpdate<Cgfab> update = new VOUpdate<Cgfab>();
			update.update(bvos.toArray(new Cgfab[bvos.size()]),
					new String[] { "whetherlineclose" });
		} else if (actionName.equals("qxhgb")) {
			List<Cgfab> bvos = new ArrayList<Cgfab>();
			for (String string : pks) {
				Cgfab bvo = new Cgfab();
				bvo.setPrimaryKey(string);
				bvo.setAttributeValue("whetherlineclose", UFBoolean.FALSE);
				bvos.add(bvo);
			}
			VOUpdate<Cgfab> update = new VOUpdate<Cgfab>();
			update.update(bvos.toArray(new Cgfab[bvos.size()]),
					new String[] { "whetherlineclose" });

		}
	}

	/**
	 * 行关闭后找到后续相关的采购方案
	 * 
	 * @param 采购方案来源子表主键
	 *            （请购单子表主键）
	 */
	@Override
	public AggCgfa[] findRelationCgfa(String[] pks) {
		// 拼凑查询sql
		String sql = getSql(pks);
		AggCgfa[] rets = null;
		try {
			DataAccessUtils utils = new DataAccessUtils();
			IRowSet set = utils.query(sql);
			if (set.size() == 0) {
				return new AggCgfa[0];
			}
			String[] ids = set.toOneDimensionStringArray();
			ViewQuery<CgfaViewVO> query = new ViewQuery<CgfaViewVO>(
					CgfaViewVO.class);
			CgfaViewVO[] views = query.query(ids);

			if (null != views && views.length > 0) {
				int len = views.length;
				AggCgfa[] bills = new AggCgfa[len];
				for (int i = 0; i < len; i++) {
					bills[i] = views[i].changeToBill();
				}
				CombineBill<AggCgfa> combine = new CombineBill<AggCgfa>();
				IVOMeta headMeta = bills[0].getMetaData().getParent();
				String headItemKey = headMeta.getPrimaryAttribute().getName();
				combine.appendKey(headItemKey);
				rets = combine.combine(bills);
			}
		} catch (Exception e) {
			ExceptionUtils.wrappBusinessException("操作失败");
			e.printStackTrace();
		}
		return rets;

	}

	private String getSql(String[] pks) {
		// TODO 自动生成的方法存根
		SqlBuilder sql = new SqlBuilder();
		sql.append("select pk_equipment_sub from pu_cgfab  where ");
		sql.append("csrcid", pks);
		sql.append(" and dr =0 and whetherlineclose<>'Y'");
		return sql.toString();
	}

}
