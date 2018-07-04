/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-25 上午11:23:59
 */
package nc.pubimpl.pu.est.m50;

import nc.bs.pu.est.EstVOQueryBP;
import nc.pubitf.pu.est.m50.IVMIEstPubQuery;
import nc.vo.pu.est.entity.m50.VmiEstHeaderVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.m4202.entity.VmiFIFeeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总暂估的查询公共服务实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-25 上午11:23:59
 */
public class VMIEstPubQueryImpl implements IVMIEstPubQuery {

  @Override
  public VmiEstVO[] query(String[] bids) throws BusinessException {
    VmiEstVO[] vos = null;
    try {
      if (!ArrayUtils.isEmpty(bids)) {
        vos =
            new EstVOQueryBP<VmiEstVO>(VmiEstVO.class, VmiEstHeaderVO.class,
                VmiFIFeeVO.class).query(bids);
      }

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return vos;
  }

}
