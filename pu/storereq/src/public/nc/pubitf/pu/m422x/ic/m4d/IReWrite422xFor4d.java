package nc.pubitf.pu.m422x.ic.m4d;

import nc.vo.pu.m422x.entity.WriteBack422XVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求申请提供给材料出库单的回写服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-19 下午04:11:19
 */
public interface IReWrite422xFor4d {

  /**
   * 方法功能描述：材料出库单回写物资需求申请数量。
   * <p>
   * <b>参数说明</b>
   * 
   * @param paraVos
   *          回写VO
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-19 下午04:11:52
   */
  void backWriteNum(WriteBack422XVO[] paraVos) throws BusinessException;

}
