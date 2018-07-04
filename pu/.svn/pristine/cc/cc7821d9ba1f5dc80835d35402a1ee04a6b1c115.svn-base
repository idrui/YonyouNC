/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-20 下午06:01:20
 */
package nc.ui.pu.est.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.est.model.EstUIContext;
import nc.ui.pu.est.rule.EstFilterZeroFeeRule;
import nc.ui.pu.est.view.EditorToModelValueSetter;
import nc.ui.pu.est.view.EstEditor;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmbd.tpa.SCMTpaAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.est.rule.FeeEstNotNullChkRule;
import nc.vo.pu.est.rule.GoodsEstNotNullChkRule;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估顶层动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-20 下午06:01:20
 */
public abstract class AbstractEstAction extends SCMTpaAction {

  private static final long serialVersionUID = 6532052884985380603L;

  private EstEditor editor;

  private BillManageModel model;

  public AbstractEstAction() {
    // this.setBtnName("暂估");
    // this.setCode("puestimate");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_ESTIMATE);
    this.setEnabled(false);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    EditorToModelValueSetter setter =
        new EditorToModelValueSetter(this.getEditor().getBillCardPanel(),
            this.getModel());
    setter.setModelBodyValue(this.getModel().getSelectedRow());
    Object[] data = this.getModel().getSelectedOperaDatas();
    EstVO[] vos = (EstVO[]) EstVOUtil.getCloneEstData(data);
    EstUIContext context = (EstUIContext) this.getModel().getContext();
    QueryEstType esttype = context.getEsttype();
    if (QueryEstType.GOODS_EST == esttype) {
      this.goodsEst(vos);
    }
    else {
      this.feeEst(vos);
    }

  }

  /**
   * @return editor
   */
  public EstEditor getEditor() {
    return this.editor;
  }

  /**
   * @return model
   */
  public BillManageModel getModel() {
    return this.model;
  }

  /**
   * @param editor
   *          要设置的 editor
   */
  public void setEditor(EstEditor editor) {
    this.editor = editor;
  }

  /**
   * @param model
   *          要设置的 model
   */
  public void setModel(BillManageModel model) {
    this.model = model;
    this.setContext(this.model.getContext());
    this.model.addAppEventListener(this);
  }

  private void feeEst(EstVO[] vos) throws Exception {
    // 前处理及检查
    EstVO[] procvos = this.processBeforeEst(vos);
    if (ArrayUtils.isEmpty(EstVOUtil.getFeeEstVOs(procvos))) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004060_0", "04004060-0008")/*
                                                    * @res
                                                    * "未录入任何可用的费用项信息，无法进行暂估!"
                                                    */);
    }
    // 执行暂估
    this.feeEstInvokeBS(procvos);
    // 后处理及检查
    this.processAfterEst();
  }

  private void goodsEst(EstVO[] vos) throws Exception {
    // 前处理及检查
    EstVO[] procVos = this.processBeforeEst(vos);
    // 货物暂估前的检查
    procVos = this.processBeforeGoodsEst(procVos);
    // 执行暂估
    this.goodsEstInvokeBS(procVos);
    // 后处理及检查
    this.processAfterEst();
  }

  private void processAfterEst() throws Exception {
    // 此处一定要处理，暂估后从model中删除行不会报错
    this.getEditor().getBillCardPanel().updateValue();
    // 成功后删除model中数据，这一步应该放在规则最后
    this.getModel().directlyDelete(this.getModel().getSelectedOperaDatas());
  }

  private EstVO[] processBeforeEst(EstVO[] vos) throws Exception {
    // 过滤掉零金额暂估费用行(非暂估)
    EstVO[] filterVos = (EstVO[]) new EstFilterZeroFeeRule().filter(vos);
    // 表体的非空项检查
    new FeeEstNotNullChkRule().process(filterVos);
    return filterVos;
  }

  private EstVO[] processBeforeGoodsEst(EstVO[] vos) throws Exception {
    // 表头非空检查
    new GoodsEstNotNullChkRule().process(vos);
    return vos;
  }

  /**
   * 方法功能描述：费用暂估调用后台服务，真正执行暂估处理。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @throws Exception
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-16 下午02:23:13
   */
  protected abstract void feeEstInvokeBS(EstVO[] vos) throws Exception;

  /**
   * 方法功能描述：货物暂估调用后台服务，真正执行暂估处理。
   * <p>
   * <b>参数说明</b>
   * 
   * @throws Exception
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-16 下午02:21:56
   */
  protected abstract void goodsEstInvokeBS(EstVO[] vos) throws Exception;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    return !ArrayUtils.isEmpty(this.getModel().getSelectedOperaDatas());
  }

}
