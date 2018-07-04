/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-30 下午04:37:44
 */
package nc.pubimpl.pu.m4t.pf;

import nc.bs.pu.m4t.pf.InitialEstAPBP;
import nc.pubitf.pu.m4t.pf.IInitialEstAP;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单暂估应付服务实现
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-30 下午04:37:44
 */
public class InitialEstAPImpl implements IInitialEstAP {

  @Override
  public void estimateAP(InitialEstVO[] vos) throws BusinessException {
    try {
      new InitialEstAPBP().estimate(vos);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
