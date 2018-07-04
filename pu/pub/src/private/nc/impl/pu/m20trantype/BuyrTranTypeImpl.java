/**
 * 
 */
package nc.impl.pu.m20trantype;

import java.util.ArrayList;

import nc.bs.pub.pf.ITranstypeBiz;
import nc.impl.pu.m20trantype.rule.FillGroupRule;
import nc.impl.pu.m20trantype.rule.NotNullChkRule;
import nc.impl.pu.m20trantype.rule.CheckTranTypeRefForBuy;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m20trantype.entity.BuyrTranTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.sm.funcreg.FuncRegisterVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 *
 * @version
 * @since
 * @author luojw
 * @time 2014-6-18 下午8:44:16
 */
public class BuyrTranTypeImpl implements ITranstypeBiz {

	/* 
	 * 父类方法重写
	 *
	 * @see nc.bs.pub.pf.ITranstypeBiz#saveTransType(java.lang.Object)
	 */
	@Override
	public void saveTransType(Object userObj) throws BusinessException {
		try {
			CompareAroundProcesser<BuyrTranTypeVO> processer = new CompareAroundProcesser<BuyrTranTypeVO>(
					null);

			// 添加规则
			this.addBeforeRule(processer);

			BuyrTranTypeVO[] vos = new BuyrTranTypeVO[] { (BuyrTranTypeVO) userObj };

			processer.before(vos, null);

			BatchOperateVO batchVo = new BatchOperateVO();
			batchVo.setAddObjs(vos);
			this.maintain(batchVo);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	/* 
	 * 父类方法重写
	 *
	 * @see nc.bs.pub.pf.ITranstypeBiz#updateTransType(java.lang.Object)
	 */
	@Override
	public void updateTransType(Object userObj) throws BusinessException {
		try {
			CompareAroundProcesser<BuyrTranTypeVO> processer = new CompareAroundProcesser<BuyrTranTypeVO>(
					null);

			// 添加规则
			this.addUpdateBeforeRule(processer);

			BuyrTranTypeVO[] vos = new BuyrTranTypeVO[] { (BuyrTranTypeVO) userObj };

			BuyrTranTypeVO[] oldVos = new VOQuery<BuyrTranTypeVO>(
					BuyrTranTypeVO.class).query(new String[] { vos[0]
					.getPk_buyrtrantype() });

			processer.before(vos, oldVos);

			BatchOperateVO batchVo = new BatchOperateVO();
			batchVo.setUpdObjs(vos);
			this.maintain(batchVo);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	/* 
	 * 父类方法重写
	 *
	 * @see nc.bs.pub.pf.ITranstypeBiz#deleteTransType(java.lang.Object)
	 */
	@Override
	public void deleteTransType(Object userObj) throws BusinessException {
		try {
			AroundProcesser<BuyrTranTypeVO> processer = new AroundProcesser<BuyrTranTypeVO>(
					null);

			BuyrTranTypeVO[] orgVo = new BuyrTranTypeVO[] { (BuyrTranTypeVO) userObj };

			processer.before(orgVo);

			BatchOperateVO batchVo = new BatchOperateVO();
			batchVo.setDelObjs(orgVo);
			this.maintain(batchVo);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	/* 
	 * 父类方法重写
	 *
	 * @see nc.bs.pub.pf.ITranstypeBiz#execOnPublish(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public void execOnPublish(String nodecode, String newNodecode,
			boolean isExecFunc) throws BusinessException {
		// TODO Auto-generated method stub

	}

	/* 
	 * 父类方法重写
	 *
	 * @see nc.bs.pub.pf.ITranstypeBiz#execOnDelPublish(nc.vo.pub.billtype.BilltypeVO, java.util.ArrayList)
	 */
	@Override
	public void execOnDelPublish(BilltypeVO transTypeVO,
			ArrayList<FuncRegisterVO> funcVOs) throws BusinessException {
		// TODO Auto-generated method stub

	}
	
	private void addBeforeRule(CompareAroundProcesser<BuyrTranTypeVO> processer) {
		// 给集团赋值规则
		processer.addBeforeRule(new FillGroupRule());
		// 非空校验
		processer.addBeforeFinalRule(new NotNullChkRule());
	}
	
	private void addUpdateBeforeRule(
			CompareAroundProcesser<BuyrTranTypeVO> processer) {
		this.addBeforeRule(processer);
		// 检验是否被引用
		processer.addBeforeRule(new CheckTranTypeRefForBuy());
	}
	
	private void maintain(BatchOperateVO batchVo) throws BusinessException {
	    try {
	      new BatchSaveAction<ISuperVO>().batchSave(batchVo);
	    }
	    catch (Exception e) {
	      // 日志异常
	      Log.info(e);
	      // 按规范包装异常
	      ExceptionUtils.marsh(e);
	    }
	  }

}
