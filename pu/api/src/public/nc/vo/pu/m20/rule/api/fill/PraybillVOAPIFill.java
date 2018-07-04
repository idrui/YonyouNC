package nc.vo.pu.m20.rule.api.fill;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.ftpub.reference.uap.bd.DeptPubUtilService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.ic.material.define.InvBasVO;
import nc.vo.ic.material.query.InvInfoQuery;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.enumeration.EnumPraySource;
import nc.vo.pu.m20.rule.SetEmployee;
import nc.vo.pu.m20.rule.SetPurchaseorg;
import nc.vo.pu.m20.rule.api.RelationCalculate;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.fill.billfill.IBillValueFill;

/**
 * 
 * @description 请购单API保存相关内容填充规则
 * @scene
 * 
 * @param
 * 
 * @functionName 请购单API保存
 * @since 6.5
 * @version 2015-11-16 下午4:11:35
 * @author zhangshqb
 */
public class PraybillVOAPIFill implements IBillValueFill {

	@Override
	public AbstractBill[] fillValue(AbstractBill[] billVOs)
			throws BusinessException {
		ArrayList<PraybillHeaderVO> headers = new ArrayList<PraybillHeaderVO>();
		PraybillVO[] vos = (PraybillVO[]) billVOs;
		for (PraybillVO vo : vos) {
			PraybillHeaderVO header = vo.getHVO();
			headers.add(header);
		}
		this.fillHeadvoInfo(headers);
		this.fill(vos);
		RelationCalculate calculate = new RelationCalculate();
		calculate.calculate(vos);
		return billVOs;
	}

	/**
	 * 补充表头信息
	 * 
	 * @param headvos
	 */
	private void fillHeadvoInfo(List<PraybillHeaderVO> headvos) {
		Set<String> pk_orgs = new HashSet<String>();
		Set<String> pk_depts_v = new HashSet<String>();
		for (PraybillHeaderVO vo : headvos) {
			pk_orgs.add(vo.getPk_org());
			pk_depts_v.add(vo.getPk_plandept_v());
		}
		String[] pk_org = pk_orgs.toArray(new String[0]);
		// 查询本币
		Map<String, String> ccurrencyids = OrgUnitPubService
				.queryOrgCurrByPk(pk_org);
		// 查询部门信息
		Map<String, String> deptIDS_V = DeptPubUtilService
				.getDeptOidByVid(pk_depts_v.toArray(new String[0]));
		for (PraybillHeaderVO headvo : headvos) {
			// 设置单据状态为新增
			headvo.setStatus(VOStatus.NEW);
			// headvo.setFbillstatus();
			if (headvo.getPk_plandept() == null) {
				headvo.setPk_plandept(deptIDS_V.get(headvo.getPk_plandept_v()));
			}
			if (headvo.getCcurrencyid() == null) {
				headvo.setCcurrencyid(ccurrencyids.get(headvo.getPk_org()));
			}
			if (headvo.getDbilldate() == null) {
				headvo.setDbilldate(new UFDate());
			}
			if (headvo.getNversion() == null) {
				headvo.setNversion(new Integer(1));
			}
			if (headvo.getFbillstatus() == null) {
				headvo.setFbillstatus(POEnumBillStatus.FREE.toInteger());
			}
			if (headvo.getFpraysource() == null) {
				headvo.setFpraysource(EnumPraySource.MANUAL.toInteger());
			}
			if (headvo.getBsctype() == null) {
				headvo.setBsctype(UFBoolean.FALSE);
			}
			if (headvo.getBdirecttransit() == null) {
				headvo.setBdirecttransit(UFBoolean.FALSE);
			}
			if (headvo.getBislatest() == null) {
				headvo.setBislatest(UFBoolean.TRUE);
			}
		}
	}

	private void fill(PraybillVO[] vos) {
		ArrayList<PraybillItemVO[]> items = new ArrayList<PraybillItemVO[]>();
		Set<String> orgs = new HashSet<String>();
		for (PraybillVO vo : vos) {
			String pk_org = vo.getHVO().getPk_org();
			orgs.add(pk_org);
			items.add(vo.getBVO());
		}
		InvInfoQuery query = new InvInfoQuery();
		HashSet<String> material = new HashSet<String>();
		for (PraybillItemVO[] itemVOs : items) {
			for (PraybillItemVO vo : itemVOs) {
				String pk_material = vo.getPk_material();
				material.add(pk_material);
			}
		}

		// 查询物料基本信息
		Map<String, InvBasVO> basVOs = query.getInvBasVOs(material
				.toArray(new String[0]));
		for (PraybillVO vo : vos) {
			PraybillHeaderVO header = vo.getHVO();
			String chprojectid = (String) header
					.getAttributeValue(PraybillHeaderVO.CHPROJECTID);
			String org = header.getPk_org();
			IKeyValue helper = new BillHelper<PraybillVO>(vo);

			PraybillItemVO[] bvo = vo.getBVO();
			int[] rows = new int[bvo.length];
			for (int i = 0; i < rows.length; i++) {
				rows[i] = i;
			}
			// 设置采购组织默认值、参照和是否可编辑
			new SetPurchaseorg().setPurchaseorg(helper, rows);

			// 设置采购员
			new SetEmployee().setEmployee(helper, rows);
			for (PraybillItemVO item : (PraybillItemVO[]) vo
					.getChildren(PraybillItemVO.class)) {
				item.setStatus(VOStatus.NEW);
				item.setPk_org(org);
				item.setPk_org_v(header.getPk_org_v());

				if (item.getPk_srcmaterial() == null) {
					item.setPk_srcmaterial(basVOs.get(item.getPk_material())
							.getPk_source());
				}
				if (item.getCunitid() == null) {
					item.setCunitid(basVOs.get(item.getPk_material()).getPk_measdoc());
				}
				if (item.getPk_org() == null) {
					item.setPk_org(header.getPk_org());
				}
				if (item.getPk_org_v() == null) {
					item.setPk_org_v(header.getPk_org_v());
				}
				if (item.getCprojectid() == null) {
					item.setCprojectid(chprojectid);
				}
				if (item.getDbilldate() == null) {
					item.setDbilldate(header.getDbilldate());
				}
				if (item.getPk_group() == null) {
					item.setPk_group(header.getPk_group());
				}
			}
		}
	}
}
