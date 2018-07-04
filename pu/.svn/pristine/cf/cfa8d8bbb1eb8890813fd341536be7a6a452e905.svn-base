package nc.impl.pu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.impl.pubapp.pattern.data.view.SchemeViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.pu.IQg20RefQueyService;
import nc.md.data.access.NCObject;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.it.m5805.entity.DetailVO;
import nc.vo.it.m5805.entity.DetailViewVO;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.bill.CombineBill;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.util.CombineViewToAggUtil;
import nc.vo.sm.UserVO;
import nc.vo.uap.rbac.UserRoleVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @author wangzym
 * @version 2017年2月27日 下午3:02:48 增加查询筛选具体看sql 2017-05-03 修改查询 详见line 104
 */
public class Qg20RefQueyServiceImpl implements IQg20RefQueyService {

	@Override
	public PraybillVO[] queryM20ForTJ01(IQueryScheme queryScheme)
			throws BusinessException {
		String sqlStr = queryScheme.getTableJoinFromWhereSQL().getWhere();

		PraybillVO[] b = this.queryDetailByViewQuery(sqlStr);

		return b;

	}

	/**
	 * 根据Sql查询请购单聚合VO(使用viewQuery可过滤到行)
	 * 
	 * @param sqlStr
	 *            将scheme中的where条件拿到整合成完整sql语句
	 * @return PraybillVO[]
	 * @throws BusinessException
	 * @author wangzym
	 */
	public PraybillVO[] queryDetailByViewQuery(String sqlStr)
			throws BusinessException {
		PraybillVO[] rets = null;
		String getSql = this.getSql();
		if (sqlStr.equals("") || sqlStr == null) {
			getSql = getSql + sqlStr;
		} else {
			getSql = getSql + " and " + sqlStr;
		}

		try {
			DataAccessUtils utils = new DataAccessUtils();
			IRowSet set = utils.query(getSql);
			if (set.size() == 0) {
				return new PraybillVO[0];
			}
			String[] ids = set.toOneDimensionStringArray();
			ViewQuery<PraybillViewVO> query = new ViewQuery<PraybillViewVO>(
					PraybillViewVO.class);
			PraybillViewVO[] views = query.query(ids);

			if (null != views && views.length > 0) {
				int len = views.length;
				PraybillVO[] bills = new PraybillVO[len];
				for (int i = 0; i < len; i++) {
					bills[i] = views[i].changeTOBill();
				}
				CombineBill<PraybillVO> combine = new CombineBill<PraybillVO>();
				IVOMeta headMeta = bills[0].getMetaData().getParent();
				String headItemKey = headMeta.getPrimaryAttribute().getName();
				combine.appendKey(headItemKey);
				rets = combine.combine(bills);
			}
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return rets;
	}

	private String getSql() {
		// TODO 自动生成的方法存根
		SqlBuilder sqlb = new SqlBuilder();
		// 直接写sql ，用scheme好烦
		// 2017-05-03 新增加筛选：没有被拒收的被筛选出来
		// 2017-05-03 新增加筛选：筛选总数量和已经拉单的数量大小对比
		// 删除 po_praybill_b.sts_req<>'03' and
		// 找当前登陆用户对应的人员档案pk
		String pk_user = AppContext.getInstance().getPkUser();
		VOQuery<UserVO> query = new VOQuery<UserVO>(UserVO.class);
		UserVO[] user = query.query(new String[] { pk_user });
		String pk_employee = user[0].getPk_psndoc();
		// 2017-11-16增加需求如果是科长或者处长则可以全查
		BaseDAO dao = new BaseDAO();
		Collection<UserRoleVO> users = null;
		try {
			users = dao.retrieveByClause(UserRoleVO.class, "  cuserid='"
					+ pk_user + "'");
		} catch (DAOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		// 一个用户可能有多个角色
		List<String> roles = new ArrayList<String>();
		for (UserRoleVO userRoleVO : users) {
			roles.add(userRoleVO.getPk_role());
		}
		boolean isSectionChief = false;
		// 如果角色能和科长或处长的角色对应
		if (roles != null && roles.size() != 0) {
			if (roles.contains("1001C0100000000GMI63")
					|| roles.contains("1001C0100000000HGUW3")) {
				isSectionChief = true;
			}
		}
		sqlb.append("select po_praybill_b.pk_praybill_b from po_praybill inner join po_praybill_b on po_praybill.pk_praybill = po_praybill_b.pk_praybill where po_praybill.fbillstatus = '3' and nvl(po_praybill_b.jsbz, 'N') = 'N'  AND  nvl(sumcgfanum, 0) < nvl(nastnum, 0)"
				//王梓懿2018-04-09 新增对于拒绝 数据的处理
				+ " AND po_praybill_b.sts_req<>09"
				);
		// and nvl(po_praybill_b.whetherlineclose,'Y')='Y'
		// 不是科长或者处长则不能全查
		if (!isSectionChief) {
			// 2017-12-27 根据鞍钢国贸 苏万斌和魏艺超要求，所有人都能全查，即写的基本上都白写了。注了
			// sqlb.append(" and  pk_employee ='" + pk_employee + "'");
		}
		String sql = sqlb.toString();
		return sql;
	}
}
