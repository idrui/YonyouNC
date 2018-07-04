package nc.pubimpl.pu.m21.pub;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.pu.m21.pub.IOrderPubQuery;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pf.SourceBillInfo;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单对外提供的公共查询服务实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-10 上午10:53:21
 */
public class OrderPubQueryImpl implements IOrderPubQuery {

  @Override
  public String[] checkTransTypeReference(String[] transType)
      throws BusinessException {
    try {
      SqlBuilder sqlbd = new SqlBuilder();
      sqlbd.append("select distinct ");
      sqlbd.append(OrderHeaderVO.VTRANTYPECODE);
      sqlbd.append(" from po_order where dr = 0 and ");
      sqlbd.append(OrderHeaderVO.VTRANTYPECODE, transType);
      sqlbd.append(" and " + OrderHeaderVO.PK_GROUP, BSContext.getInstance()
          .getGroupID());

      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sqlbd.toString());
      String[] trantypecodes = rowset.toOneDimensionStringArray();

      return trantypecodes;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public Map<String, String> getCtCodeByBID(String[] pk_order_bs)
      throws BusinessException {
    try {
      VOQuery<OrderItemVO> vq =
          new VOQuery<OrderItemVO>(OrderItemVO.class, new String[] {
            OrderItemVO.PK_ORDER_B, OrderItemVO.VCONTRACTCODE
          });
      OrderItemVO[] items = vq.query(pk_order_bs);
      if (ArrayUtils.isEmpty(items)) {
        return null;
      }
      Map<String, String> bidctcodeMap = new HashMap<String, String>();
      for (OrderItemVO item : items) {
        bidctcodeMap.put(item.getPk_order_b(), item.getVcontractcode());
      }
      return bidctcodeMap;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
  /**
   * @description 根据采购订单表头主键数组查询采购订单主表
   */
  @Override
  public Map<String, String> splitByStypeByBID(String[] pk_orders)
		  throws BusinessException {
	  try {
		  VOQuery<OrderHeaderVO> vq =
				  new VOQuery<OrderHeaderVO>(OrderHeaderVO.class, new String[] {
					  OrderHeaderVO.PK_ORDER, OrderHeaderVO.PK_BALATYPE
				  });
		  OrderHeaderVO[] items = vq.query(pk_orders);
		  if (ArrayUtils.isEmpty(items)) {
			  return null;
		  }
		  Map<String, String> bidctcodeMap = new HashMap<String, String>();
		  for (OrderHeaderVO head : items) {
			  bidctcodeMap.put(head.getPk_order(), head.getPk_balatype());
		  }
		  return bidctcodeMap;
	  }
	  catch (Exception e) {
		  ExceptionUtils.marsh(e);
	  }
	  return null;
  }

  /**
   * 方法功能描述：取得上游采购订单单据信息，发送上游消息时使用
   * <p>
   * <b>参数说明</b>
   * 
   * @param listOrderId
   * @return <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-17 上午09:31:32
   */
  @Override
  public SourceBillInfo[] getOrderBillInfo(String[] listOrderId) {
    try {
      VOQuery<OrderHeaderVO> query =
          new VOQuery<OrderHeaderVO>(OrderHeaderVO.class);

      OrderHeaderVO[] heads = query.query(listOrderId);

      int iLen = heads.length;
      SourceBillInfo[] voaRet = new SourceBillInfo[iLen];

      for (int i = 0; i < iLen; i++) {
        voaRet[i] = new SourceBillInfo();

        voaRet[i].setBillId(heads[i].getPk_order());
        voaRet[i].setApprover(heads[i].getApprover());
        voaRet[i].setBillmaker(heads[i].getCreator());
      }
      return voaRet;
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  @Override
  public OrderHeaderVO[] queryOrderHeaderVOByHids(String[] hids, String[] attrs)
      throws BusinessException {
    try {
      VOQuery<OrderHeaderVO> query =
          new VOQuery<OrderHeaderVO>(OrderHeaderVO.class, attrs);
      return query.query(hids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderItemVO[] queryOrderItem(String[] bidArray)
      throws BusinessException {
    try {
      VOQuery<OrderItemVO> voQuery =
          new VOQuery<OrderItemVO>(OrderItemVO.class);
      return voQuery.query(bidArray);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderViewVO[] queryOrderView(String[] bidArray)
      throws BusinessException {
    try {
      ViewQuery<OrderViewVO> voQuery =
          new ViewQuery<OrderViewVO>(OrderViewVO.class);
      return voQuery.query(bidArray);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderVO[] queryOrderVOByBids(String[] bids) throws BusinessException {
    try {
      ViewQuery<OrderViewVO> voQuery =
          new ViewQuery<OrderViewVO>(OrderViewVO.class);
      OrderViewVO[] views = voQuery.query(bids);
      return OrderViewVO.getOrderVO(views);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
