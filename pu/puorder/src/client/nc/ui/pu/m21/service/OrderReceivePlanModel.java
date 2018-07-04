/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-14 下午03:48:25
 */
package nc.ui.pu.m21.service;

import java.util.List;

import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货计划Model
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-14 下午03:48:25
 */
public class OrderReceivePlanModel extends BatchBillTableModel {

  private UFBoolean confirm = UFBoolean.FALSE;

  private OrderVO orderVO;

  /**
   * @return orderVO
   */
  public OrderVO getOrderVO() {
    return this.orderVO;
  }

  /**
   * 获取打印数据，打印模板用的是订单的元数据，这里需要把视图vo重新组织成订单vo
   * 
   * @return 订单vo
   */
  public Object[] getPrintData() {
    List<Object> rows = this.getRows();
    OrderReceivePlanVO[] vos = new OrderReceivePlanVO[rows.size()];
    for (int i = 0; i < rows.size(); ++i) {
      vos[i] = (OrderReceivePlanVO) rows.get(i);
    }
    return vos;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.BatchBillTableModel#save()
   */
  @Override
  public void save() throws Exception {
    OrderVO orderVo = this.getOrderVO();
    OrderVO oldVO = (OrderVO) orderVo.clone();
    try {
      this.beforeSaveProcess();
      BatchOperateVO vo = this.getCurrentSaveObject();
      // 差异VO处理
      orderVo.getHVO().setStatus(VOStatus.UPDATED);
      ClientBillToServer<OrderVO> tool = new ClientBillToServer<OrderVO>();
      OrderVO[] lightVOs = tool.construct(new OrderVO[] {
        orderVo
      }, new OrderVO[] {
        orderVo
      });
      this.setOrderVO(lightVOs[0]);
      // 调用重写的ModelService
      OrderReceivePlanModelService rp =
          (OrderReceivePlanModelService) this.getService();
      Object[] ret = rp.batchSave(vo, this.getOrderVO(), this.confirm);
      vo = (BatchOperateVO) ret[0];
      orderVo = (OrderVO) ret[1];
      this.directSave(vo);

      new ClientBillCombinServer<OrderVO>().combine(new OrderVO[] {
        oldVO
      }, new OrderVO[] {
        orderVo
      });
      this.setOrderVO(oldVO);
    }
    catch (Exception e) {
      this.setOrderVO(oldVO);
      ExceptionUtils.marsh(e);
    }
  }

  public void setConfirm(UFBoolean confirm) {
    this.confirm = confirm;
  }

  /**
   * @param orderVO
   *          要设置的 orderVO
   */
  public void setOrderVO(OrderVO orderVO) {
    this.orderVO = orderVO;
  }

}
