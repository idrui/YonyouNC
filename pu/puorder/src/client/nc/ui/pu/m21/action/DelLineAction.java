package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.BodyDelLineAction;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.CloseCheck;
import nc.vo.pu.m21.rule.DownFlowCheck;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单表体删行按钮处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-25 上午08:34:57
 */
public class DelLineAction extends BodyDelLineAction {

  private static final long serialVersionUID = 6113648250538466001L;

  /**
   * 方法功能描述：得到要删除的表体
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-7 下午06:10:00
   */
  private OrderItemVO[] getSelectedItemVOs() {
    BillCardPanel panel = this.getCardPanel();
    int[] selectIndex = panel.getBodyPanel().getTable().getSelectedRows();
    if (null == selectIndex || 0 == selectIndex.length) {
      return null;
    }

    OrderVO vo =
        (OrderVO) panel.getBillValueVO(OrderVO.class.getName(),
            OrderHeaderVO.class.getName(), OrderItemVO.class.getName());
    // Map<String, OrderItemVO> itemMap = AggVOUtil.createItemMap(new OrderVO[]
    // {
    // vo
    // });
    BillIndex index = new BillIndex(new OrderVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(OrderItemVO.class);

    List<OrderItemVO> list = new ArrayList<OrderItemVO>();
    for (int i = 0; i < selectIndex.length; ++i) {
      String pk_order_b =
          (String) panel.getBodyValueAt(selectIndex[i], OrderItemVO.PK_ORDER_B);
      if (!StringUtil.isEmptyWithTrim(pk_order_b)) {
        // list.add(itemMap.get(pk_order_b));
        list.add((OrderItemVO) index.get(meta, pk_order_b));
      }
    }

    if (0 == list.size()) {
      return null;
    }

    ListToArrayTool<OrderItemVO> tool = new ListToArrayTool<OrderItemVO>();
    return tool.convertToArray(list);
  }

  @Override
  protected boolean doBeforeAction(ActionEvent e) {
    super.doBeforeAction(e);

    // 如果是新增状态，不必检查
    if (AppUiState.ADD.getUiState() == this.getModel().getUiState()) {
      return true;
    }

    // 得到要删除的表体VO数组
    OrderItemVO[] itemVOs = this.getSelectedItemVOs();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return true;
    }

    StringBuilder sb = new StringBuilder();

    // 判断是否关闭
    CloseCheck closeCheck = new CloseCheck();
    String closeStr = closeCheck.closeCheck(itemVOs);
    if (!ObjectUtil.isEmptyWithTrim(closeStr)) {
      sb.append(closeStr);
    }

    // 判断是否有后续操作
    DownFlowCheck downFlowCheck = new DownFlowCheck();
    String downFlowStr = downFlowCheck.hasDownFlow(itemVOs);
    if (!ObjectUtil.isEmptyWithTrim(downFlowStr)) {
      sb.append(downFlowStr);
    }

    if (sb.length() > 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4004030_0", "04004030-0038")/* @res "不能删除" */);
      return false;
    }

    return true;
  }

}
