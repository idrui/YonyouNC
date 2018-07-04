/**
 * 
 */
package nc.itf.pu.m20;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PrayarrangeViewVO;
import nc.vo.pub.BusinessException;

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
 * @time 2014-6-13 上午9:12:42
 */
public interface IPrayarrangeQuery {
	
	/**
	 * 根据查询方案查询初请购安排视图VO数组 
	 * 
 	 * @param queryScheme
	 * @return
	 * @throws BusinessException
	 * 上午9:17:56
	 */
	
	PrayarrangeViewVO[] queryByQueryScheme(IQueryScheme queryScheme)
		      throws BusinessException;
}
