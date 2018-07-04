package nc.ui.pu.billref;

import java.util.Collection;

import nc.funcnode.ui.FuncletInitData;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.ui.uif2.UIState;
import nc.ui.uif2.components.IAutoShowUpComponent;
import nc.vo.pu.uif2.PuMutibillLinkQueryVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 联查多PK单据
 * 联查存储key为 89
 * 
 * @version 6.1
 * @since 6.1
 * @author yangtian
 * @time 2011-12-14 上午11:01:08
 */
public class PUMutiPkLinkQueryProcessor implements IInitDataProcessor {

  private IAutoShowUpComponent autoShowUpComponent;

  private BillManageModel model;

  private String voClass;

  public IAutoShowUpComponent getAutoShowUpComponent() {
    return this.autoShowUpComponent;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public String getVoClass() {
    return this.voClass;
  }

  @Override
  public void process(FuncletInitData data) {
    if (data.getInitType() != PuMutibillLinkQueryVO.INITTYPE) {
      return;
    }
    PuMutibillLinkQueryVO puLinkQueryVO = null;
    // null instanceof 类都是false，所以不用判断data.getInitData()是否为null
    if (!(data.getInitData() instanceof PuMutibillLinkQueryVO)) {
      return;
    }
    else {
      puLinkQueryVO = (PuMutibillLinkQueryVO) data.getInitData();
    }

    // 不仅支持塞pks在里进行查询，也支持塞vos之后直接注入
    if (!ArrayUtils.isEmpty(puLinkQueryVO.getBillvos())) {
      this.model.initModel(puLinkQueryVO.getBillvos());
    }
    else if (!ArrayUtils.isEmpty(puLinkQueryVO.getBillids())) {
      try {
        Class<?> voClasss = Class.forName(this.voClass);
        Collection bills =
            MDPersistenceService.lookupPersistenceQueryService()
                .queryBillOfVOByPKs(voClasss, puLinkQueryVO.getBillids(), true);
        if (bills == null || bills.isEmpty()) {
          return;
        }

        this.model.initModel(bills.toArray());
      }

      catch (Exception e1) {
        ExceptionUtils.wrappException(e1);
      }
    }
    // 显示界面
    if (null != this.getAutoShowUpComponent()) {

      this.getModel().setUiState(UIState.NOT_EDIT);
      this.getAutoShowUpComponent().showMeUp();
    }

  }

  public void setAutoShowUpComponent(IAutoShowUpComponent autoShowUpComponent) {
    this.autoShowUpComponent = autoShowUpComponent;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

  public void setVoClass(String voClass) {
    this.voClass = voClass;
  }

}
