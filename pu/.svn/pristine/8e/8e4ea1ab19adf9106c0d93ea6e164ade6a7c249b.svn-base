package nc.vo.pu.m20.rule.api;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.pubitf.sc.m61.transtype.IQuerySCTransType;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.sc.m61.entity.SCOrderTranstypeVO;

/**
 * 
 * @description 请购单表体订单类型校验
 * 
 * @scene 请购单API保存
 * 
 * @param
 * 
 * @functionName 请购单API保存
 * @since 6.5
 * @version 2015-11-19 上午10:22:52
 * @author zhangshqb
 */
public class TranstypeCheck {

	public void check(PraybillVO[] vos) throws BusinessException {
		// 订单类型校验
		// 委外的交易类型
		Set<String> sctype = new HashSet<String>();
		// 采购的交易类型
		Set<String> potype = new HashSet<String>();
		for (PraybillVO vo : vos) {
			PraybillHeaderVO hvo = vo.getHVO();
			UFBoolean bsctype = hvo.getBsctype();
			PraybillItemVO[] bvo = vo.getBVO();
			for (PraybillItemVO itemVO : bvo) {
				String cordertrantypecode = itemVO.getCordertrantypecode();
				if (cordertrantypecode != null) {
					if (bsctype != null && bsctype.booleanValue()) {
						sctype.add(cordertrantypecode);
					} else {
						potype.add(cordertrantypecode);
					}
				}
			}
		}
		IQuerySCTransType scTransType = NCLocator.getInstance().lookup(
				IQuerySCTransType.class);
		Map<String, SCOrderTranstypeVO> sctypes = scTransType
				.queryByTransIds(sctype.toArray(new String[sctype.size()]));
		Set<String> scts = sctypes.keySet();
		IPoTransTypeQuery poTransTypeQuery = NCLocator.getInstance().lookup(
				IPoTransTypeQuery.class);
		Map<String, PoTransTypeVO> potypes = poTransTypeQuery.queryAttrByIDs(potype
				.toArray(new String[potype.size()]));
		Set<String> pots = potypes.keySet();
		for (PraybillVO vo : vos) {
			PraybillHeaderVO hvo = vo.getHVO();
			UFBoolean bsctype = hvo.getBsctype();
			PraybillItemVO[] bvo = vo.getBVO();
			for (PraybillItemVO itemVO : bvo) {
				String cordertrantypecode = itemVO.getCordertrantypecode();
				if (cordertrantypecode != null) {
					if (bsctype != null && bsctype.booleanValue()) {
						if (!scts.contains(cordertrantypecode)) {
							new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
									.getStrByID("4004020_0", "04004020-0121"));
						}
					} else {
						if (pots.contains(cordertrantypecode)) {
							new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
									.getStrByID("4004020_0", "04004020-0122"));
						}
					}
				}
			}
		}
	}

}
