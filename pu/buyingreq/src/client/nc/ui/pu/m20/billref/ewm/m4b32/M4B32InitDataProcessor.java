package nc.ui.pu.m20.billref.ewm.m4b32;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletInitData;
import nc.itf.pu.m20.IPraybillMaintain;
import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.ui.uif2.editor.BillForm;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 资产维修计划推式生成请购单数据初始化
 * 
 * @since 6.5
 * @version 2014-1-14 上午11:05:44
 * @author fanly3
 */
public class M4B32InitDataProcessor implements IInitDataProcessor {
  private TransferViewProcessor processor;

  public TransferViewProcessor getProcessor() {
    return this.processor;
  }

  @Override
  public void process(FuncletInitData data) {
    int type = data.getInitType();
    if (type == 48) {
      PraybillVO[] vos = (PraybillVO[]) data.getInitData();
      if (null != vos) {
        BillForm editor = this.processor.getBillForm();
        // 补全信息,为了减少连接数，此处对信息补全放到接口中
        PraybillVO[] tempVos = this.fillInfo(vos);
        editor.getModel().initModel(null);
        this.processor.processBillTransfer(tempVos);
      }
    }
  }

  public void setProcessor(TransferViewProcessor processor) {
    this.processor = processor;
  }

  /**
   * 补全信息
   * 
   * @param vos
   */
  private PraybillVO[] fillInfo(PraybillVO[] vos) {
    try {
      IPraybillMaintain service =
          NCLocator.getInstance().lookup(IPraybillMaintain.class);
      return service.setDefaultValueForM4B32(vos);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
