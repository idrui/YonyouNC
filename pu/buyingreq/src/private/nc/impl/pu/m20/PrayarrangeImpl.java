/**
 * 
 */
package nc.impl.pu.m20;

import nc.impl.pu.m20.action.PrayarrangeSaveAction;
import nc.itf.pu.m20.IPrayarrange;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uif2.LoginContext;

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
 * @time 2014-6-13 上午10:16:04
 */
public class PrayarrangeImpl implements IPrayarrange {

	/* 
	 * 父类方法重写
	 *
	 * @see nc.itf.pubapp.pub.smart.ISmartService#batchSave(nc.vo.bd.meta.BatchOperateVO)
	 */
	@Override
	public BatchOperateVO batchSave(BatchOperateVO batchVO)
			throws BusinessException {
		try {
			if (null == batchVO) {
				return null;
			}
			return new PrayarrangeSaveAction().batchSave(batchVO);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	/* 
	 * 父类方法重写
	 *
	 * @see nc.itf.pubapp.pub.smart.ISmartService#queryByDataVisibilitySetting(nc.vo.uif2.LoginContext, java.lang.Class)
	 */
	@Override
	public ISuperVO[] queryByDataVisibilitySetting(LoginContext context,
			Class<? extends ISuperVO> clz) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * 父类方法重写
	 *
	 * @see nc.itf.pubapp.pub.smart.ISmartService#selectByWhereSql(java.lang.String, java.lang.Class)
	 */
	@Override
	public ISuperVO[] selectByWhereSql(String whereSql,
			Class<? extends ISuperVO> clz) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * 父类方法重写
	 *
	 * @see nc.itf.pu.m20.IPrayarrange#batchUpate(nc.vo.bd.meta.BatchOperateVO)
	 */
	@Override
	public BatchOperateVO batchCancelArrange(BatchOperateVO batchVO) throws Exception {
		try {
			if (null == batchVO) {
				return null;
			}
			return new PrayarrangeSaveAction().batchCancelArrange(batchVO);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

}
