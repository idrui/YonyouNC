/**
 * 
 */
package nc.itf.pu.m20;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PrayarrangeViewVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 *
 * @version
 * @since
 * @author luojw
 * @time 2014-6-13 ����9:12:42
 */
public interface IPrayarrangeQuery {
	
	/**
	 * ���ݲ�ѯ������ѯ���빺������ͼVO���� 
	 * 
 	 * @param queryScheme
	 * @return
	 * @throws BusinessException
	 * ����9:17:56
	 */
	
	PrayarrangeViewVO[] queryByQueryScheme(IQueryScheme queryScheme)
		      throws BusinessException;
}
