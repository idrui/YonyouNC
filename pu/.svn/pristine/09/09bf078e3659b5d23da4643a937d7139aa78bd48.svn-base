package nc.vo.pu.m23.utils;

import java.util.ArrayList;
import java.util.List;

import nc.vo.ic.batchcode.BatchSynchronizer;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.BatchCodeFieldMap;
import nc.vo.pu.m23.rule.BatchCodeItemAdapter;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;

import org.apache.commons.lang.StringUtils;

public class BatchSynchronizerM23 {

  /**
   * 根据到货单ArriveVO同步批次信息
   * 
   * @param vos
   * @return
   */
  public static ArriveVO[] synchBatchCodeData(ArriveVO[] vos) {
    if (null == vos) {
      return vos;
    }
    List<BatchCodeItemAdapter> list = new ArrayList<BatchCodeItemAdapter>();
    for (int i = 0, len = vos.length; i < len; i++) {
      if (null == vos[i].getBVO()) {
        continue;
      }
      for (ArriveItemVO item : vos[i].getBVO()) {
        if (null == item || StringUtils.isEmpty(item.getPk_batchcode())) {
          continue;
        }
        item.setAttributeValue(ArriveHeaderVO.VBILLCODE, vos[i].getHVO()
            .getVbillcode());
        list.add(new BatchCodeItemAdapter(item));
      }
    }
    if (list.size() == 0) {
      return vos;
    }

    BatchCodeFieldMap filedMap = new BatchCodeFieldMap();
    BatchSynchronizer service = new BatchSynchronizer(filedMap);
    try {
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004040_0", "04004040-0130")/* @res "调用库存提供的同步批次号接口" */);
      BatchCodeItemAdapter[] paras =
          list.toArray(new BatchCodeItemAdapter[list.size()]);
      service.fillBatchVOtoBill(paras);
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004040_0", "04004040-0131")/* @res "成功调用库存提供的同步批次号接口" */);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    return vos;
  }
}
