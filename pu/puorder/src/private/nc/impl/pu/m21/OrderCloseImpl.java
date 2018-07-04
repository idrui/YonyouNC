/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 下午03:18:25
 */
package nc.impl.pu.m21;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m21.state.OrderCloseStateUtil;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.pu.m21.IOrderClose;
import nc.pubitf.pu.m21.pub.IOrderClosePubService;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumCloseFlag;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单关闭实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-13 下午03:18:25
 */
public class OrderCloseImpl implements IOrderClose {

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IOrderClose#close(nc.vo.pu.m21.entity.OrderVO[], int,
   *      boolean)
   */
  @Override
  public OrderVO[] close(OrderVO[] vos, int closeType, boolean isAllItems)
      throws BusinessException {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    try {
      BillTransferTool<OrderVO> tool = new BillTransferTool<OrderVO>(vos);
      // 得到全VO，行操作时最后会利用这个VO得到差异VO返回
      OrderVO[] fullInfoBill = tool.getClientFullInfoBill();

      // 得到视图VO
      OrderCloseVO[] closeVOs = null;
      if (isAllItems) {
        closeVOs = OrderVO.getCloseVO(fullInfoBill);
      }
      else {
        closeVOs = this.getCloseVO(vos, fullInfoBill);
      }

      // 关闭
      // 2012-05-23 tianft 改为接口调用，为记录业务日志用。
      // aop业务日志的插入点放在nc.impl.pu.m21.OrderCloseImpl.close(OrderCloseVO[], int)
      // closeVOs =
      // NCLocator.getInstance().lookup(IOrderClose.class)
      // .close(closeVOs, closeType);
      // this.close(closeVOs, closeType);

      // 由视图VO得到订单VO
      OrderVO[] tempVOs =
          this.close(OrderCloseVO.getOrderVO(closeVOs), closeType);

      /*
       * 得到的VO可能和原来的VO顺序不一致 因为差异VO返回需要的VO要和原来的顺序一致，所以对VO进行处理，使其和fullInfoBill一一对应
       * 如果是整单操作，只需调整顺序
       */
      if (isAllItems) {
        AggVOUtil.reSortVO(tempVOs, fullInfoBill);
        return tool.getBillForToClient(tempVOs);
      }

      /*
       * 前台在合并时，前台的表体数量需要和返回的差异VO的表体数量一致 如果不是整单操作，前面得到的视图所需表体只是一部分表体，
       * 所以现在得到的vo的表体也只是一部分，返回时需要将表体数量补全 利用前面得到的fullInfoBill得到差异VO返回时需要的VO
       */
      this.setVOWhenReturn(fullInfoBill, tempVOs);
      return tool.getBillForToClient(fullInfoBill);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  /**
   */
  public OrderVO[] open(OrderVO[] vos, int openType) throws BusinessException {
    OrderVO[] datas = vos;
    try {
      if (((Integer) EnumCloseFlag.FINAL_CLOSE.value()).intValue() == openType) {
        datas =
            NCLocator.getInstance().lookup(IOrderClosePubService.class)
                .finalOpen(datas);
      }
      else if (((Integer) EnumCloseFlag.ROW_CLOSE.value()).intValue() == openType) {
        datas =
            NCLocator.getInstance().lookup(IOrderClosePubService.class)
                .rowOpen(datas);
      }
      else if (((Integer) EnumCloseFlag.ARRIVE_CLOSE.value()).intValue() == openType) {
        datas =
            NCLocator.getInstance().lookup(IOrderClosePubService.class)
                .arriveOpen(datas);
      }
      else if (((Integer) EnumCloseFlag.STOCK_CLOSE.value()).intValue() == openType) {
        datas =
            NCLocator.getInstance().lookup(IOrderClosePubService.class)
                .storeOpen(datas);
      }
      else if (((Integer) EnumCloseFlag.INVOICE_CLOSE.value()).intValue() == openType) {
        datas =
            NCLocator.getInstance().lookup(IOrderClosePubService.class)
                .invoiceOpen(datas);
      }
      else if (((Integer) EnumCloseFlag.PAY_CLOSE.value()).intValue() == openType) {
        datas =
            NCLocator.getInstance().lookup(IOrderClosePubService.class)
                .payOpen(datas);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return datas;

  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IOrderClose#open(nc.vo.pu.m21.entity.OrderVO[], int,
   *      boolean)
   */
  @Override
  public OrderVO[] open(OrderVO[] vos, int openType, boolean isAllItems)
      throws BusinessException {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    try {
      BillTransferTool<OrderVO> tool = new BillTransferTool<OrderVO>(vos);
      // 得到全VO，最后会利用这个VO得到返回时需要的差异VO
      OrderVO[] fullInfoBill = tool.getClientFullInfoBill();

      // 得到视图VO
      OrderCloseVO[] openVOs = null;
      if (isAllItems) {
        openVOs = OrderVO.getCloseVO(fullInfoBill);
      }
      else {
        openVOs = this.getCloseVO(vos, fullInfoBill);
      }

      // 打开
      // 2012-05-23 tianft 改为接口调用，为记录业务日志用。
      // aop业务日志的插入点放在nc.impl.pu.m21.OrderCloseImpl.close(OrderCloseVO[], int)
      // openVOs = this.open(OrderCloseVO.getOrderVO(openVOs), openType);

      // 由视图VO得到订单VO
      OrderVO[] tempVOs = this.open(OrderCloseVO.getOrderVO(openVOs), openType);

      /*
       * 得到的VO可能和原来的VO顺序不一致 因为差异VO返回需要的VO要和原来的顺序一致，所以对VO进行处理，使其和fullInfoBill一一对应
       * 如果是整单操作，只需调整顺序
       */
      if (isAllItems) {
        AggVOUtil.reSortVO(tempVOs, fullInfoBill);
        return tool.getBillForToClient(tempVOs);
      }

      /*
       * 前台在合并时，前台的表体数量需要和返回的差异VO的表体数量一致 如果不是整单操作，前面得到的视图所需表体只是一部分表体，
       * 所以现在得到的vo的表体也只是一部分，返回时需要将表体数量补全 利用前面得到的fullInfoBill得到差异VO返回时需要的VO
       */
      this.setVOWhenReturn(fullInfoBill, tempVOs);
      return tool.getBillForToClient(fullInfoBill);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  /**
   */
  private OrderVO[] close(OrderVO[] vos, int closeType)
      throws BusinessException {
    // 手动关闭，即即时关闭，设置工具类的变量为即时关闭。
    OrderCloseStateUtil.getInstance().setbInstanceClose(true);
    OrderVO[] datas = vos;
    try {
      if (((Integer) EnumCloseFlag.FINAL_CLOSE.value()).intValue() == closeType) {
        datas =
            NCLocator.getInstance().lookup(IOrderClosePubService.class)
                .finalClose(datas);
      }
      else if (((Integer) EnumCloseFlag.ROW_CLOSE.value()).intValue() == closeType) {
        datas =
            NCLocator.getInstance().lookup(IOrderClosePubService.class)
                .rowClose(datas);
      }
      else if (((Integer) EnumCloseFlag.ARRIVE_CLOSE.value()).intValue() == closeType) {
        datas =
            NCLocator.getInstance().lookup(IOrderClosePubService.class)
                .arriveClose(datas);
      }
      else if (((Integer) EnumCloseFlag.STOCK_CLOSE.value()).intValue() == closeType) {
        datas =
            NCLocator.getInstance().lookup(IOrderClosePubService.class)
                .storeClose(datas);
      }
      else if (((Integer) EnumCloseFlag.INVOICE_CLOSE.value()).intValue() == closeType) {
        datas =
            NCLocator.getInstance().lookup(IOrderClosePubService.class)
                .invoiceClose(datas);
      }
      else if (((Integer) EnumCloseFlag.PAY_CLOSE.value()).intValue() == closeType) {
        datas =
            NCLocator.getInstance().lookup(IOrderClosePubService.class)
                .payClose(datas);
      }

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return datas;
  }

  /**
   * 方法功能描述：得到视图VO 因为差异VO原因，tool得到的VO的表体是全部的表体，所以vos和fullInfoBill的表体不一致
   * 在得到视图VO时需要根据vos的表体pk得到fullInfoBill的表体，从而得到视图所需表体
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param fullInfoBill
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-10 下午02:28:36
   */
  private OrderCloseVO[] getCloseVO(OrderVO[] vos, OrderVO[] fullInfoBill) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    List<OrderCloseVO> closeList = new ArrayList<OrderCloseVO>();
    Map<String, OrderVO> voMap = AggVOUtil.createVOMap(fullInfoBill);
    // Map<String, OrderItemVO> itemMap = AggVOUtil.createItemMap(fullInfoBill);
    BillIndex index = new BillIndex(fullInfoBill);
    IVOMeta meta = fullInfoBill[0].getMetaData().getVOMeta(OrderItemVO.class);
    for (OrderVO vo : vos) {
      if (null == vo) {
        continue;
      }
      OrderVO fullVO = voMap.get(vo.getPrimaryKey());
      OrderHeaderVO fullHeaderVO = fullVO.getHVO();
      for (OrderItemVO itemVO : vo.getBVO()) {
        // OrderItemVO fullItemVO = itemMap.get(itemVO.getPrimaryKey());
        OrderItemVO fullItemVO =
            (OrderItemVO) index.get(meta, itemVO.getPrimaryKey());
        OrderCloseVO closeVO = new OrderCloseVO();
        closeVO.setVO(fullHeaderVO);
        closeVO.setVO(fullItemVO);
        closeList.add(closeVO);
      }
    }

    if (closeList.size() > 0) {
      return new ListToArrayTool<OrderCloseVO>().convertToArray(closeList);
    }

    return null;
  }

  /**
   * 方法功能描述：将关闭或打开操作的VO的表体补全， 使其和BillTransferTool中clientBills的表体数量一致
   * <p>
   * <b>参数说明</b>
   * 
   * @param fullInfoBill
   * @param tempVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-7 上午10:05:50
   */
  private void setVOWhenReturn(OrderVO[] fullInfoBill, OrderVO[] tempVOs) {
    // Map<String, OrderItemVO> itemMap = AggVOUtil.createItemMap(tempVOs);
    // Set<String> keySet = itemMap.keySet();
    BillIndex index = new BillIndex(tempVOs);
    IVOMeta meta = tempVOs[0].getMetaData().getVOMeta(OrderItemVO.class);
    List<String> keyList = new ArrayList<String>();
    for (OrderVO tempVO : tempVOs) {
      for (OrderItemVO itemVO : tempVO.getBVO()) {
        keyList.add(itemVO.getPk_order_b());
      }
    }

    Map<String, OrderVO> voMap = AggVOUtil.createVOMap(tempVOs);
    for (OrderVO fullVO : fullInfoBill) {
      // 设置表体
      OrderItemVO[] itemVOs = fullVO.getBVO();
      OrderItemVO[] tempItemVOs = new OrderItemVO[itemVOs.length];
      for (int i = 0; i < itemVOs.length; ++i) {
        // if (keySet.contains(itemVOs[i].getPrimaryKey())) {
        // tempItemVOs[i] = itemMap.get(itemVOs[i].getPrimaryKey());
        // }
        if (keyList.contains(itemVOs[i].getPrimaryKey())) {
          tempItemVOs[i] =
              (OrderItemVO) index.get(meta, itemVOs[i].getPrimaryKey());
        }
        else {
          tempItemVOs[i] = itemVOs[i];
        }
      }
      fullVO.setBVO(tempItemVOs);
      // 设置表头，行操作时可能改变表头
      fullVO.setHVO(voMap.get(fullVO.getPrimaryKey()).getHVO());
    }
  }

}
