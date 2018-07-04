package nc.pubimpl.pu.tbb.strategy;

import nc.vo.pub.lang.UFDouble;
import nc.vo.tb.obj.NtbParamVO;

/**
 * 取数策略接口
 * 
 * @since 6.0
 * @version 2011-3-18 下午07:17:31
 * @author yinfy
 */
public interface IExecDataProviderStrategy {
  /**
   * 批量取数，返回执行数
   * 根据参数返回初始的执行数，返回全局本币、集团本币、组织本币、原币四个数据，如果是数量，返回四个相同的值
   */
  public UFDouble[][] getExecDataBatch(NtbParamVO[] param) throws Exception;

}
