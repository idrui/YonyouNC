package nc.bs.bd.material.baseinfo.validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import nc.itf.bd.config.uniquerule.IBDUniqueruleQryService;
import nc.md.model.IBean;
import nc.vo.bd.config.BDUniqueruleVO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.util.BDUniqueRuleValidate;
import org.apache.commons.lang.StringUtils;

public class MaterialUniqueRuleValidator extends BDUniqueRuleValidate {
	public MaterialUniqueRuleValidator() {
	}

	public MaterialUniqueRuleValidator(IBean bean) {
		super(bean);
	}

	protected List<BDUniqueruleVO> getBreakUniqueRule(SuperVO vo,
			Collection<BDUniqueruleVO> rules, String uniquescope,
			ArrayList<String> toBeValidatePKList) throws BusinessException { 
		//由于接口传来的数据本来就存在问题，根据鞍钢国贸张越超指示 暂时不校验唯一性了。
		String filterMsg = null;
		MaterialVO materialVO = (MaterialVO) vo;

		if (!StringUtils.isBlank(materialVO.getPk_source())) {
			filterMsg = " pk_source <> '" + materialVO.getPk_source() + "'";
		}
		/*return getService().getBreakUniqueRuleByVO(vo, bean, rules,
				uniquescope, filterMsg, toBeValidatePKList);*/
		return null;
	}

	protected Map<BDUniqueruleVO, List<SuperVO>> getBreakRuleofVosSelf(
			SuperVO[] vos, Collection<BDUniqueruleVO> rules, IBean bean) {
		return null;
	}
}
