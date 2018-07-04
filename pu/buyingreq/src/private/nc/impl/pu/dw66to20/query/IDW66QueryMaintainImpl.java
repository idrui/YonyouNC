package nc.impl.pu.dw66to20.query;

import java.util.ArrayList;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.trade.business.HYPubBO;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.itf.pu.dw66to20.query.IDW66QueryMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lm.erpcgjhjk.AggErpcgjhjkHVO;
import nc.vo.lm.erpcgjhjk.ErpcgjhjkHVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class IDW66QueryMaintainImpl implements IDW66QueryMaintain {

	@Override
	public AggErpcgjhjkHVO[] queryUpForPrayBillDates(IQueryScheme queryScheme)
			throws BusinessException {
		AggErpcgjhjkHVO[] bills = null;
		bills = this.queryAggCgagForSource(queryScheme);
		return bills;
	}

	private AggErpcgjhjkHVO[] queryAggCgagForSource(IQueryScheme queryScheme)
			throws BusinessException {

		String sql = queryScheme.getWhereSQLOnly();
		List<AggErpcgjhjkHVO> aggvos = new ArrayList<AggErpcgjhjkHVO>();
		HYPubBO bo = new HYPubBO();
		SqlBuilder sb = new SqlBuilder();
		sb.append(sql);
		sb.append(" and nvl(dr,0)=0 and nvl(msgflag, 0) <> 2 ");
		ErpcgjhjkHVO[] hvos = (ErpcgjhjkHVO[]) bo.queryByCondition(
				ErpcgjhjkHVO.class, sb.toString());
		for (ErpcgjhjkHVO hvo : hvos) {
			AggErpcgjhjkHVO aggvo = new AggErpcgjhjkHVO();
			aggvo.setParent(hvo);
			aggvos.add(aggvo);
		}
		return aggvos.toArray(new AggErpcgjhjkHVO[aggvos.size()]);

	}

	@Override
	public void Changestsreg(String pk,String ret) {
		BaseDAO dao = new BaseDAO();
		String sql = "update po_praybill_b set sts_req = '09',vbdef2='"+ret+"' where  pk_praybill_b  = '"
				+ pk + "'and nvl(dr,0)=0";
		try {
			dao.executeUpdate(sql);
		} catch (DAOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

	/**
	 * @Title: ȡ���ܾ�����ܾ���ť����
	 * @param �빺������pk����
	 * @author ����ܲ
	 * @date 2018-05-22
	 */
	@Override
	public void UnRefuse(String[] bpks) {
		// �������¼���Զ�̵��ô���
		if (bpks == null || bpks.length == 0) {
			return;
		}
		int len = bpks.length;
		PraybillItemVO[] vos = new PraybillItemVO[len];
		for (int i = 0; i < vos.length; i++) {
			PraybillItemVO bvo = new PraybillItemVO();
			bvo.setPrimaryKey(bpks[i]);
			// ��ܾ��Ĳ���ȡ�������� ��̬������ֵ
			bvo.setAttributeValue("sts_req", "01");
			vos[i] = bvo;

		}
		VOUpdate<PraybillItemVO> update = new VOUpdate<PraybillItemVO>();
		//ͬʱ�Ѿܾ�ԭ��ɾ��
		update.update(vos, new String[] { "sts_req","vbdef2" });
	}

}
