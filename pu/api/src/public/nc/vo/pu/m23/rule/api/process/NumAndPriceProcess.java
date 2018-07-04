package nc.vo.pu.m23.rule.api.process;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.RelationCalculate;

/**
 * 
 * @description
 *		到货单导入根据数量做联动计算
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		到货单导入处理单价数量
 * @since 6.5
 * @version 2015-11-2 上午11:01:38
 * @author wandl
 */
public class NumAndPriceProcess implements IRule<ArriveVO>{
	@Override
	public void process(ArriveVO[] vos) {
		for (ArriveVO vo : vos){
			RelationCalculate calc = new RelationCalculate();
      calc.calcaulate(vo, ArriveItemVO.NNUM);
		}
	}
}
