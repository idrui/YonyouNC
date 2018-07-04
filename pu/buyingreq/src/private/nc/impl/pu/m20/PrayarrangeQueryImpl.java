/**
 * 
 */
package nc.impl.pu.m20;

import nc.bs.pu.m20.maintain.PrayarrangeQueryBP;
import nc.itf.pu.m20.IPrayarrangeQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PrayarrangeViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

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
 * @time 2014-6-13 上午9:24:58
 */
public class PrayarrangeQueryImpl implements IPrayarrangeQuery {

	/* 
	 * 父类方法重写
	 *
	 * @see nc.itf.pu.m20.IPrayarrangeQuery#queryByQueryScheme(nc.ui.querytemplate.querytree.IQueryScheme)
	 */
	@Override
	public PrayarrangeViewVO[] queryByQueryScheme(IQueryScheme queryScheme)
			throws BusinessException {
		try {
		     return  new PrayarrangeQueryBP().queryPrayarrangeViewVO(queryScheme);
		    }
		    catch (Exception e) {
		      ExceptionUtils.marsh(e);
		    }
		    return null;
	 }

}
