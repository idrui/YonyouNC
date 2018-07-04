package nc.ui.pu.m422x.billref.ewm.m4b32;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.editor.BillForm;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.rule.CurrencySetter;
import nc.vo.pu.pub.util.BillHelper;

import org.apache.commons.lang.ArrayUtils;

/**
 * 资产维修计划推式生成物资需求申请单数据初始化
 * 
 * @since 6.5
 * @version 2014-1-21 下午03:20:23
 * @author fanly3
 */
public class M4B32InitDataProcessor implements IInitDataProcessor {
  private TransferViewProcessor processor;

  public TransferViewProcessor getProcessor() {
    return this.processor;
  }

  public void setProcessor(TransferViewProcessor processor) {
    this.processor = processor;
  }

  @Override
  public void process(FuncletInitData data) {
    int type = data.getInitType();
    if (type == 200) {
      StoreReqAppVO[] vos = (StoreReqAppVO[]) data.getInitData();
      if (null != vos) {
        BillForm editor = this.processor.getBillForm();
        // 补全信息
        this.fillInformation(vos);
        editor.getModel().initModel(null);
        this.processor.processBillTransfer(vos);
      }
    }

  }

  private void fillInformation(StoreReqAppVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    // TODO 维修计划需要补全哪些信息，未定
    for (StoreReqAppVO vo : vos) {
      StoreReqAppItemVO[] itemVOs = vo.getBVO();
      int[] rows = new int[itemVOs.length];
      for (int i = 0; i < itemVOs.length; ++i) {
        rows[i] = i;
      }
      BillHelper<StoreReqAppVO> helper = new BillHelper<StoreReqAppVO>(vo);
      // 单位，主单位，数量，主数量，换算率都是VO对照过来的
      new CurrencySetter(helper).setCurrency(rows);
    }
  }
}
