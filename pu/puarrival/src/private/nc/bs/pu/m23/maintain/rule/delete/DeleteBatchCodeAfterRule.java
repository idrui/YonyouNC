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
 * ɾ��ʱ���������ε���
 * @scene
 * 
 * @param
 * ��
 *
 * @since 6.3
 * @version 2010-5-27 ����10:23:55
 * @author hanbin
 */

public class DeleteBatchCodeAfterRule implements IRule<ArriveVO> {

  private ArriveVO[] invokeICService(ArriveVO[] vos) {
    BatchCodeFieldMap filedMap = new BatchCodeFieldMap();
    IBatchGenerateForPU service =
        NCLocator.getInstance().lookup(IBatchGenerateForPU.class);
    try {
      TimeLog.info("���ÿ���ṩ��ɾ�������κŽӿ�");/* -=notranslate=- */
      service.batchProcessAfterSaveForDel(vos, BatchCodeItemAdapter.class,
          filedMap);
      TimeLog.info("�ɹ����ÿ���ṩ��ɾ�������κŽӿ�");/* -=notranslate=- */
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
    // ���ÿ�����ṩ�Ľӿ�
    invokeICService(vos);
  }

}
