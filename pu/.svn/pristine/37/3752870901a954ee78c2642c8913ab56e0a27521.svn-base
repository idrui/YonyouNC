/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-12 上午08:42:24
 */
package nc.ui.pu.m21.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.pu.m21.IOrderQuery;
import nc.itf.uap.bbd.func.IFuncRegisterQueryService;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.ui.pu.uif2.PUUif2Utils;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.BillForm;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.sm.funcreg.FuncRegisterVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货计划按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-12 上午08:42:24
 */
public class ReceivePlanAction extends NCAction {

  private static final long serialVersionUID = 3000813841771750521L;

  private BillForm billForm;

  public ReceivePlanAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_ARRIVALPLAN);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
  	this.billForm.getBillCardPanel().getBodyTabbedPane().setSelectedIndex(0);
    OrderVO vo = (OrderVO) this.billForm.getModel().getSelectedData();
    if (null == vo) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0036")/*
                                                                   * @res
                                                                   * "请选择数据"
                                                                   */);
      return;
    }

    // 业务类型，是否进行到货计划安排
    boolean isReceivePlan = this.isReceivePlan(vo);
    if (!isReceivePlan) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0058")/*
                                                                   * @res
                                                                   * "当前业务类型“不进行到货计划安排”"
                                                                   */);
      return;
    }

    this.doBeforeAction(vo);

    this.openRP(vo);
  }

  /**
   * @param billForm
   *          要设置的 billForm
   */
  public void setBillForm(BillForm billForm) {
    this.billForm = billForm;
    billForm.getModel().addAppEventListener(this);
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 上午10:59:39
   */
  private void doBeforeAction(OrderVO vo) {
    if (null == vo) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0059")/*
                                                                   * @res
                                                                   * "请先选择数据"
                                                                   */);
    }

    if (UFBoolean.TRUE.equals(vo.getHVO().getBfrozen())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0060")/*
                                                                   * @res
                                                                   * "该订单已冻结，不能编辑到货计划"
                                                                   */);
    }

    // 是否有正行
    OrderItemVO[] itemVOs = this.getPositiveCheckBodyVO(vo);
    if (ArrayUtils.isEmpty(itemVOs)) {
      // 提示
      // 该订单没有正订单行，不能编辑到货计划
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0208")/*
                                                                   * @res
                                                                   * "该订单没有正订单行，不能编辑到货计划"
                                                                   */);
    }

  }

  private IFuncRegisterQueryService getFuncRegisterQueryService() {
    return NCLocator.getInstance().lookup(IFuncRegisterQueryService.class);
  }

  /**
   * 方法功能描述：得到可以有到货计划的行
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 上午11:09:06
   */
  private OrderItemVO[] getPositiveCheckBodyVO(OrderVO vo) {
    // 所有VO
    OrderItemVO[] itemVOs = vo.getBVO();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return null;
    }

    Set<OrderItemVO> set = new HashSet<OrderItemVO>();
    for (OrderItemVO itemVO : itemVOs) {
      if (MathTool.compareTo(itemVO.getNnum(), UFDouble.ZERO_DBL) > 0) {
        set.add(itemVO);
      }
    }

    if (set.size() > 0) {
      return set.toArray(new OrderItemVO[set.size()]);
    }

    return null;
  }

  private FuncRegisterVO getPuFRVO(FuncRegisterVO frVO) {
    // 将当前打开的采购订单节点号传入给到货计划界面，不能写死，因为有可能交易类型发布节点
    // 通过FuncRegisterVO，可以传入被打开节点的内部
    PUUif2Utils.setUsrObjToFRVO(frVO,
        PUUif2Utils.PUFRVOAttName.sourrounding_funcode.name(), this.billForm
            .getModel().getContext().getNodeCode());
    return frVO;
  }

  /**
   * 方法功能描述：判断是否进行到货计划
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return 是返回true
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-10-16 上午09:18:21
   */
  private boolean isReceivePlan(OrderVO vo) throws BusinessException {
    IPoTransTypeQuery query =
        NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
    HashMap<String, PoTransTypeVO> map = query.queryAttrByTypes(new String[] {
      vo.getHVO().getVtrantypecode()
    }, new String[] {
      PoTransTypeVO.BRECEIVEPLAN
    });
    PoTransTypeVO transtypeVO = map.get(vo.getHVO().getVtrantypecode());
    return transtypeVO != null && transtypeVO.getBreceiveplan().booleanValue();
  }

  /**
   * 方法功能描述：打开到货计划
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-10-16 上午09:28:16
   */
  private void openRP(OrderVO vo) throws BusinessException {
    final String funCode = "40040416";
    FuncRegisterVO frVO = null;
    try {
      frVO = this.getFuncRegisterQueryService().queryFunctionByCode(funCode);
    }
    catch (BusinessException ex) {
      Logger.error(ex.getMessage(), ex);
    }

    // 生成FuncletInitData对象
    FuncletInitData initData = null;
    initData = new FuncletInitData();
    initData.setInitType(-100);// -100表示日志引入
    initData.setInitData(vo);

    // 打开对应功能节点
    if (frVO != null) {
      // 如果对应功能节点查询成功，以对话框形式打开功能节点
      Dimension d = new Dimension(800, 600);
//      UIDialog.setReSet(true);
//      new UIDialog().setReset(true);
      FuncletWindowLauncher.openFuncNodeDialog(WorkbenchEnvironment
          .getInstance().getWorkbench(), this.getPuFRVO(frVO), initData, null,
          true, true, d,true);
//      FuncletWindowLauncher.openFuncNodeForceModalDialog(WorkbenchEnvironment
//    		  .getInstance().getWorkbench(), this.getPuFRVO(frVO), initData, null,
//    		  true,d,true);
    }

    IOrderQuery orderquery = NCLocator.getInstance().lookup(IOrderQuery.class);
    String id = vo.getHVO().getPk_order();

    this.billForm.getModel().directlyUpdate(
        orderquery.queryOrderVOsByIds(new String[] {
          id
        }, UFBoolean.FALSE));
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    Object[] objs =
        ((BillManageModel) this.billForm.getModel()).getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return false;
    }

    OrderVO order = (OrderVO) this.billForm.getModel().getSelectedData();

    // 业务类型未安排到货计划的订单的按钮不可使用
    return order != null && !order.getHVO().getBreturn().booleanValue();
  }

}
