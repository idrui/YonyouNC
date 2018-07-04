package nc.bs.pu.m20.maintain.rule.unapprove;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

import org.apache.commons.lang.ArrayUtils;

public class UnApproveCgfanRule implements IRule<PraybillVO>{
/**
 * 下游拉单已生成才采购方案的单据弃审校验
 */
	@Override
	public void process(PraybillVO[] vos) {
		if (ArrayUtils.isEmpty(vos)) {
			return;
		}
		
		for (PraybillVO aggvo : vos) {
			PraybillHeaderVO hvo=(PraybillHeaderVO) aggvo.getParent();
			IUAPQueryBS bs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			try {
			String sql = " select csrcid from pu_cgfa where csrcid = '"+hvo.getPrimaryKey()+"' and nvl(dr,0)=0 ";
			List<String> csrcidList = (List<String>) bs.executeQuery(sql, new ColumnListProcessor());
			if(!csrcidList.isEmpty()){
				nc.vo.ecpubapp.pattern.exception.ExceptionUtils.wrappBusinessException("下游单据已经引用，不能弃审！");
			}
		} catch (BusinessException e) {
			e.getMessage();
		}
			
			
		}
		
	}

}
