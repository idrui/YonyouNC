package nc.pubitf.pu.m4t.pub;

import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.BusinessException;

/**
 * 期初暂估单迁移数据保存
 * <p>
 * 使用场景：
 * <ul>
 * <li>V57到V61数据迁移框架itf生成VO对象的保存
 * <li>迁移中可能需要档案参照
 * <li>逐个VO循环处理，有异常的VO返回日志信息，以保证数据迁移的连续性，不能异常中断
 * <li>目前主要处理新增
 * </ul>
 * 
 * @since 6.0
 * @version 2012-8-9 下午06:15:42
 * @author liuyand
 */
public interface IInitialEstMigMaintain {

  /**
   * 期初暂估单迁移数据保存(新增)
   * <p>
   * 使用场景：
   * <ul>
   * <li>V57到V61数据迁移框架itf生成VO对象的保存
   * <li>迁移中可能需要档案参照
   * <li>逐个VO循环处理，有异常的VO返回日志信息，以保证数据迁移的连续性，不能异常中断
   * </ul>
   * 
   * @param bills 要保存的期初暂估单VO，必须为全VO（目前不一定完整）
   * @return 日志信息
   * @throws BusinessException
   */
  String[] migSave(InitialEstVO[] bills) throws BusinessException;

}
