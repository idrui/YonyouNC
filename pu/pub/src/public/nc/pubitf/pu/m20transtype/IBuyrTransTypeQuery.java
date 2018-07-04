/**
 * 
 */
package nc.pubitf.pu.m20transtype;

import java.util.HashMap;

import nc.vo.pu.m20trantype.entity.BuyrTranTypeVO;
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
 * @time 2014-6-24 上午9:14:05
 */
public interface IBuyrTransTypeQuery {

	  HashMap<String, BuyrTranTypeVO> queryAttrByTypes(String pk_group,
		      String[] transTypes, String[] sAttriName) throws BusinessException;
}
