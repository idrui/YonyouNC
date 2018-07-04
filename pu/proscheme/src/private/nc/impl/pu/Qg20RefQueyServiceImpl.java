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
 * @version 2017��2��27�� ����3:02:48 ���Ӳ�ѯɸѡ���忴sql 2017-05-03 �޸Ĳ�ѯ ���line 104
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
	 * ����Sql��ѯ�빺���ۺ�VO(ʹ��viewQuery�ɹ��˵���)
	 * 
	 * @param sqlStr
	 *            ��scheme�е�where�����õ����ϳ�����sql���
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
		// TODO �Զ����ɵķ������
		SqlBuilder sqlb = new SqlBuilder();
		// ֱ��дsql ����scheme�÷�
		// 2017-05-03 ������ɸѡ��û�б����յı�ɸѡ����
		// 2017-05-03 ������ɸѡ��ɸѡ���������Ѿ�������������С�Ա�
		// ɾ�� po_praybill_b.sts_req<>'03' and
		// �ҵ�ǰ��½�û���Ӧ����Ա����pk
		String pk_user = AppContext.getInstance().getPkUser();
		VOQuery<UserVO> query = new VOQuery<UserVO>(UserVO.class);
		UserVO[] user = query.query(new String[] { pk_user });
		String pk_employee = user[0].getPk_psndoc();
		// 2017-11-16������������ǿƳ����ߴ��������ȫ��
		BaseDAO dao = new BaseDAO();
		Collection<UserRoleVO> users = null;
		try {
			users = dao.retrieveByClause(UserRoleVO.class, "  cuserid='"
					+ pk_user + "'");
		} catch (DAOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		// һ���û������ж����ɫ
		List<String> roles = new ArrayList<String>();
		for (UserRoleVO userRoleVO : users) {
			roles.add(userRoleVO.getPk_role());
		}
		boolean isSectionChief = false;
		// �����ɫ�ܺͿƳ��򴦳��Ľ�ɫ��Ӧ
		if (roles != null && roles.size() != 0) {
			if (roles.contains("1001C0100000000GMI63")
					|| roles.contains("1001C0100000000HGUW3")) {
				isSectionChief = true;
			}
		}
		sqlb.append("select po_praybill_b.pk_praybill_b from po_praybill inner join po_praybill_b on po_praybill.pk_praybill = po_praybill_b.pk_praybill where po_praybill.fbillstatus = '3' and nvl(po_praybill_b.jsbz, 'N') = 'N'  AND  nvl(sumcgfanum, 0) < nvl(nastnum, 0)"
				//����ܲ2018-04-09 �������ھܾ� ���ݵĴ���
				+ " AND po_praybill_b.sts_req<>09"
				);
		// and nvl(po_praybill_b.whetherlineclose,'Y')='Y'
		// ���ǿƳ����ߴ�������ȫ��
		if (!isSectionChief) {
			// 2017-12-27 ���ݰ��ֹ�ó ������κ�ճ�Ҫ�������˶���ȫ�飬��д�Ļ����϶���д�ˡ�ע��
			// sqlb.append(" and  pk_employee ='" + pk_employee + "'");
		}
		String sql = sqlb.toString();
		return sql;
	}
}