/**
 * 
 */
package nc.itf.pu.m20;

import nc.itf.pubapp.pub.smart.ISmartService;
import nc.vo.bd.meta.BatchOperateVO;

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
 * @time 2014-6-13 ����9:11:12
 */
public interface IPrayarrange extends ISmartService{
	
	BatchOperateVO batchCancelArrange(BatchOperateVO batchVO) throws Exception;

}
