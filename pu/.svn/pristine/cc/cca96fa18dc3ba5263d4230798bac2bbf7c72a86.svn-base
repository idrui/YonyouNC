/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-13 下午02:48:18
 */
package nc.ui.pu.m21.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单关闭model
 * <li>getSelectedCloseVO()获取选中的视图VO
 * <li>getAllBillPk()获取所有选中视图VO对应单据的主键
 * <li>getAllCloseRow()获取与选中视图VO在同一单据的所有视图VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-13 下午02:48:18
 */
public class OrderCloseManageModel extends BillManageModel {

  public OrderCloseManageModel() {
    super();
  }

  // 获得整个单据的所有表头行
  public Map<Integer, OrderCloseVO> getAllCloseRow() {
    Map<Integer, OrderCloseVO> mapAll = new HashMap<Integer, OrderCloseVO>();

    OrderCloseVO[] selectedCloseVOs = this.getSelectedCloseVO();
    List<String> strClosePks = this.getAllBillPk(selectedCloseVOs);

    if ((null == strClosePks) || (strClosePks.size() == 0)) {
      return null;
    }

    OrderCloseVO closeVO = null;

    // 选择其他与选中视图VO同一单据的视图VO行
    for (int row = 0; row < this.getData().size(); row++) {
      closeVO = (OrderCloseVO) this.getData().get(row);
      if ((closeVO != null)
          && strClosePks.contains(closeVO
              .getAttributeValue(OrderHeaderVO.PK_ORDER))) {
        mapAll.put(Integer.valueOf(row), closeVO);
      }
    }
    return mapAll;

  }

  /**
   * 获取打印数据，打印模板用的是订单的元数据，这里需要把视图vo重新组织成订单vo
   * 
   * @return 订单vo
   */
  public Object[] getPrintData() {
    OrderVO[] printVOs = new OrderVO[this.getData().size()];
    for (int i = 0; i < this.getData().size(); i++) {
      OrderCloseVO closeVO = (OrderCloseVO) this.getData().get(i);
      OrderHeaderVO header = (OrderHeaderVO) closeVO.getVO(OrderHeaderVO.class);
      OrderItemVO item = (OrderItemVO) closeVO.getVO(OrderItemVO.class);
      printVOs[i] = new OrderVO();
      printVOs[i].setParent(header);
      printVOs[i].setChildrenVO(new OrderItemVO[] {
        item
      });

    }
    return printVOs;
  }

  /**
   * @return
   */
  public OrderCloseVO[] getSelectedCloseVO() {

    Object[] tempData = this.getSelectedOperaDatas();
    if ((null == tempData) || (0 == tempData.length)) {
      return null;
    }
    OrderCloseVO[] vos = new OrderCloseVO[tempData.length];
    System.arraycopy(tempData, 0, vos, 0, tempData.length);

    // 返回视图VO
    return vos;

  }

  // 获得选中所有单据的主键
  private List<String> getAllBillPk(OrderCloseVO[] vos) {

    if ((null == vos) || (vos.length == 0)) {
      return null;
    }

    List<String> strList = new ArrayList<String>();
    for (int i = 0; i < vos.length; i++) {

      // 判断每行主键(主表)是否与选中行的主键相同
      if (!strList.contains(vos[i].getAttributeValue(OrderHeaderVO.PK_ORDER))) {
        strList.add((String) vos[i].getAttributeValue(OrderHeaderVO.PK_ORDER));
      }
    }

    return strList;
  }

}
