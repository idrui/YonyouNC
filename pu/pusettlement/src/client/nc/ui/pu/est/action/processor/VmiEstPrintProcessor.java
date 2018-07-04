package nc.ui.pu.est.action.processor;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.vo.pu.est.util.EstVoScaleUtil;
import nc.vo.pu.m4202.entity.VmiFIVO;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pubapp.scale.BillVOScaleProcessor;

import org.apache.commons.lang.ArrayUtils;

/**
 * 消耗汇总暂估打印处理类
 * 
 * @since 6.0
 * @version 2011-8-16 下午07:28:13
 * @author 田锋涛
 */

public class VmiEstPrintProcessor implements IBeforePrintDataProcess {

  @Override
  public Object[] processData(Object[] datas) {
    VmiFIVO[] vmiFiVOs = (VmiFIVO[]) ArrayUtil.convertArrayType(datas);
    if (ArrayUtils.isEmpty(vmiFiVOs)) {
      return datas;
    }
    String pk_group = vmiFiVOs[0].getParentVO().getPk_group();
    new EstVoScaleUtil().setScaleForVmi(new BillVOScaleProcessor(pk_group,
        vmiFiVOs));

    return vmiFiVOs;
  }

}
