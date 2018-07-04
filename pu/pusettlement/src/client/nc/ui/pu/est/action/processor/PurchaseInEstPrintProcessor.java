package nc.ui.pu.est.action.processor;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.vo.pu.est.util.EstVoScaleUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pubapp.scale.BillVOScaleProcessor;

import org.apache.commons.lang.ArrayUtils;

/**
 * �ɹ����ݹ���ӡ������
 * 
 * @since 6.0
 * @version 2011-8-16 ����07:28:13
 * @author �����
 */

public class PurchaseInEstPrintProcessor implements IBeforePrintDataProcess {

  @Override
  public Object[] processData(Object[] datas) {
    PurchaseinFIVO[] fiVOs =
        (PurchaseinFIVO[]) ArrayUtil.convertArrayType(datas);
    if (ArrayUtils.isEmpty(fiVOs)) {
      return datas;
    }
    String pk_group = fiVOs[0].getParentVO().getPk_group();
    new EstVoScaleUtil().setScale(new BillVOScaleProcessor(pk_group, fiVOs));
    return fiVOs;
  }

}
