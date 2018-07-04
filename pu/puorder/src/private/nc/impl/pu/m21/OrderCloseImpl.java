/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 ����03:18:25
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ر�ʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-13 ����03:18:25
 */
public class OrderCloseImpl implements IOrderClose {

  /**
   * ���෽����д
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
      // �õ�ȫVO���в���ʱ�����������VO�õ�����VO����
      OrderVO[] fullInfoBill = tool.getClientFullInfoBill();

      // �õ���ͼVO
      OrderCloseVO[] closeVOs = null;
      if (isAllItems) {
        closeVOs = OrderVO.getCloseVO(fullInfoBill);
      }
      else {
        closeVOs = this.getCloseVO(vos, fullInfoBill);
      }

      // �ر�
      // 2012-05-23 tianft ��Ϊ�ӿڵ��ã�Ϊ��¼ҵ����־�á�
      // aopҵ����־�Ĳ�������nc.impl.pu.m21.OrderCloseImpl.close(OrderCloseVO[], int)
      // closeVOs =
      // NCLocator.getInstance().lookup(IOrderClose.class)
      // .close(closeVOs, closeType);
      // this.close(closeVOs, closeType);

      // ����ͼVO�õ�����VO
      OrderVO[] tempVOs =
          this.close(OrderCloseVO.getOrderVO(closeVOs), closeType);

      /*
       * �õ���VO���ܺ�ԭ����VO˳��һ�� ��Ϊ����VO������Ҫ��VOҪ��ԭ����˳��һ�£����Զ�VO���д���ʹ���fullInfoBillһһ��Ӧ
       * ���������������ֻ�����˳��
       */
      if (isAllItems) {
        AggVOUtil.reSortVO(tempVOs, fullInfoBill);
        return tool.getBillForToClient(tempVOs);
      }

      /*
       * ǰ̨�ںϲ�ʱ��ǰ̨�ı���������Ҫ�ͷ��صĲ���VO�ı�������һ�� �����������������ǰ��õ�����ͼ�������ֻ��һ���ֱ��壬
       * �������ڵõ���vo�ı���Ҳֻ��һ���֣�����ʱ��Ҫ������������ȫ ����ǰ��õ���fullInfoBill�õ�����VO����ʱ��Ҫ��VO
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
   * ���෽����д
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
      // �õ�ȫVO�������������VO�õ�����ʱ��Ҫ�Ĳ���VO
      OrderVO[] fullInfoBill = tool.getClientFullInfoBill();

      // �õ���ͼVO
      OrderCloseVO[] openVOs = null;
      if (isAllItems) {
        openVOs = OrderVO.getCloseVO(fullInfoBill);
      }
      else {
        openVOs = this.getCloseVO(vos, fullInfoBill);
      }

      // ��
      // 2012-05-23 tianft ��Ϊ�ӿڵ��ã�Ϊ��¼ҵ����־�á�
      // aopҵ����־�Ĳ�������nc.impl.pu.m21.OrderCloseImpl.close(OrderCloseVO[], int)
      // openVOs = this.open(OrderCloseVO.getOrderVO(openVOs), openType);

      // ����ͼVO�õ�����VO
      OrderVO[] tempVOs = this.open(OrderCloseVO.getOrderVO(openVOs), openType);

      /*
       * �õ���VO���ܺ�ԭ����VO˳��һ�� ��Ϊ����VO������Ҫ��VOҪ��ԭ����˳��һ�£����Զ�VO���д���ʹ���fullInfoBillһһ��Ӧ
       * ���������������ֻ�����˳��
       */
      if (isAllItems) {
        AggVOUtil.reSortVO(tempVOs, fullInfoBill);
        return tool.getBillForToClient(tempVOs);
      }

      /*
       * ǰ̨�ںϲ�ʱ��ǰ̨�ı���������Ҫ�ͷ��صĲ���VO�ı�������һ�� �����������������ǰ��õ�����ͼ�������ֻ��һ���ֱ��壬
       * �������ڵõ���vo�ı���Ҳֻ��һ���֣�����ʱ��Ҫ������������ȫ ����ǰ��õ���fullInfoBill�õ�����VO����ʱ��Ҫ��VO
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
    // �ֶ��رգ�����ʱ�رգ����ù�����ı���Ϊ��ʱ�رա�
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
   * ���������������õ���ͼVO ��Ϊ����VOԭ��tool�õ���VO�ı�����ȫ���ı��壬����vos��fullInfoBill�ı��岻һ��
   * �ڵõ���ͼVOʱ��Ҫ����vos�ı���pk�õ�fullInfoBill�ı��壬�Ӷ��õ���ͼ�������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @param fullInfoBill
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-10 ����02:28:36
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
   * �����������������رջ�򿪲�����VO�ı��岹ȫ�� ʹ���BillTransferTool��clientBills�ı�������һ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param fullInfoBill
   * @param tempVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-7 ����10:05:50
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
      // ���ñ���
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
      // ���ñ�ͷ���в���ʱ���ܸı��ͷ
      fullVO.setHVO(voMap.get(fullVO.getPrimaryKey()).getHVO());
    }
  }

}
