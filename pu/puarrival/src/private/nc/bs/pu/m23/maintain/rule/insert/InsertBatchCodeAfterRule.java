package nc.bs.pu.m23.maintain.rule.insert;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
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
 * �޸ı���ʱ���������ε���
 * @scene
 * �������޸ı���
 * @param
 * ��
 *
 * @since 6.3
 * @version 2010-5-27 ����10:23:55
 * @author hanbin
 */
public class InsertBatchCodeAfterRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos) {

    for (ArriveVO vo : vos) {
      for (ArriveItemVO item : vo.getBVO()) {
        item.setAttributeValue(ArriveHeaderVO.VBILLCODE, vo.getHVO()
            .getVbillcode());
      }
    }

    ArriveVO[] batchVOs = ArrivePublicUtil.getBatchCodeArriveVO(vos);
    if (ArrayUtils.isEmpty(batchVOs)) {
      return;
    }
    // ���ÿ�����ṩ�Ľӿ�
    this.invokeICService(batchVOs);
  }

  private ArriveVO[] invokeICService(ArriveVO[] vos) {
    BatchCodeFieldMap filedMap = new BatchCodeFieldMap();
    IBatchGenerateForPU service =
        NCLocator.getInstance().lookup(IBatchGenerateForPU.class);
    try {
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0083")/*@res "���ÿ���ṩ��������������κŽӿ�"*/);
      service.batchProcessAfterSaveForInsert(vos, BatchCodeItemAdapter.class,
          filedMap);
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0084")/*@res "�ɹ����ÿ���ṩ��������������κŽӿ�"*/);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    return vos;
  }
}