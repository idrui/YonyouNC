package nc.bs.pu.m23.maintain.rule.update;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.ic.batch.IBatchGenerateForPU;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
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
 * 修改保存时，更新批次档案
 * @scene
 * 到货单修改保存
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-5-27 上午10:23:55
 * @author hanbin
 */

public class UpdateBatchCodeAfterRule implements ICompareRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos, ArriveVO[] originVOs) {

    for (ArriveVO vo : vos) {
      for (ArriveItemVO item : vo.getBVO()) {
        item.setAttributeValue(ArriveHeaderVO.VBILLCODE, vo.getHVO()
            .getVbillcode());
      }
    }
    if (!ArrayUtils.isEmpty(originVOs)) {
      for (ArriveVO vo : originVOs) {
        for (ArriveItemVO item : vo.getBVO()) {
          item.setAttributeValue(ArriveHeaderVO.VBILLCODE, vo.getHVO()
              .getVbillcode());
        }
      }
    }

    ArriveVO[] batchVOs = ArrivePublicUtil.getBatchCodeArriveVO(vos);
    ArriveVO[] orgBatchVOs = ArrivePublicUtil.getBatchCodeArriveVO(originVOs);
    if (ArrayUtils.isEmpty(batchVOs) && ArrayUtils.isEmpty(orgBatchVOs)) {
      return;
    }

    // 调用库存所提供的接口
    this.invokeICService(batchVOs, orgBatchVOs);
  }

  private ArriveVO[] invokeICService(ArriveVO[] vos, ArriveVO[] originVOs) {
    BatchCodeFieldMap filedMap = new BatchCodeFieldMap();
    IBatchGenerateForPU service =
        NCLocator.getInstance().lookup(IBatchGenerateForPU.class);
    try {
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0085")/*@res "调用库存提供的修改保存后批次号接口"*/);
      service.batchProcessAfterSaveForUpdate(vos, originVOs,
          BatchCodeItemAdapter.class, filedMap);
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0086")/*@res "成功调用库存提供的修改保存后批次号接口"*/);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    return vos;
  }

}