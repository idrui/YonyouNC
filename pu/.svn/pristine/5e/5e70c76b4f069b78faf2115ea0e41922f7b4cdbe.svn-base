package nc.bs.pu.m23.maintain.rule.delete;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.ic.batch.IBatchGenerateForPU;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.BatchCodeFieldMap;
import nc.vo.pu.m23.rule.BatchCodeItemAdapter;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 删除时，更新批次档案
 * @scene
 * 
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-5-27 上午10:23:55
 * @author hanbin
 */

public class DeleteBatchCodeAfterRule implements IRule<ArriveVO> {

  private ArriveVO[] invokeICService(ArriveVO[] vos) {
    BatchCodeFieldMap filedMap = new BatchCodeFieldMap();
    IBatchGenerateForPU service =
        NCLocator.getInstance().lookup(IBatchGenerateForPU.class);
    try {
      TimeLog.info("调用库存提供的删除后批次号接口");/* -=notranslate=- */
      service.batchProcessAfterSaveForDel(vos, BatchCodeItemAdapter.class,
          filedMap);
      TimeLog.info("成功调用库存提供的删除后批次号接口");/* -=notranslate=- */
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    return vos;
  }

  @Override
  public void process(ArriveVO[] vos) {
    ArriveVO[] batchVOs = ArrivePublicUtil.getBatchCodeArriveVO(vos);
    if (ArrayUtils.isEmpty(batchVOs)) {
      return;
    }
    // 调用库存所提供的接口
    invokeICService(vos);
  }

}
