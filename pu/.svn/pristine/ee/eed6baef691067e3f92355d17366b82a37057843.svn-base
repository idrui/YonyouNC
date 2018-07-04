/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-19 下午09:06:31
 */
package nc.bs.pu.m21.query.pu;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.query.pu.rule.CanInvoiceNumCalcRule;
import nc.bs.scmpub.util.SCMDataAccessUtils;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.model.tool.BillComposite;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为采购发票提供的查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-19 下午09:06:31
 */
public class OrderQueryFor25BP {

  /**
   * 方法功能描述：根据传入的条件查询采购订单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @param isLazy
   * @return <p>
   * @since 6.0
   * @author zhyhang
   * @time 2010-1-19 下午09:28:01
   */
  public OrderVO[] query(String cond, UFBoolean isLazy) {
    //DataAccessUtils utils = new DataAccessUtils();
  	/*
  	 * change by wandl
  	 * 转单界面限制查询数据量10000行，超过10000行会报错提示缩小查询范围！
  	 */
  	SCMDataAccessUtils utils = new SCMDataAccessUtils(10000);
    String[] bids = utils.query(cond).toOneDimensionStringArray();

    if (isLazy.booleanValue()) {
      //
    }
    OrderViewVO[] views =
        new ViewQuery<OrderViewVO>(OrderViewVO.class).query(bids);
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }

    // 按表头主键HID进行合单
    AroundProcesser<OrderVO> processer =
        new AroundProcesser<OrderVO>(OrderPluginPoint.PULL25);
    this.addRule(processer);

    List<OrderHeaderVO> headerList = new ArrayList<OrderHeaderVO>();
    List<OrderItemVO> itemList = new ArrayList<OrderItemVO>();
    for (OrderViewVO view : views) {
      headerList.add((OrderHeaderVO) view.getVO(OrderHeaderVO.class));
      itemList.add((OrderItemVO) view.getVO(OrderItemVO.class));
    }

    BillComposite<OrderVO> bc = new BillComposite<OrderVO>(OrderVO.class);
    OrderVO tempVO = new OrderVO();
    bc.append(tempVO.getMetaData().getParent(),
        headerList.toArray(new OrderHeaderVO[headerList.size()]));
    bc.append(tempVO.getMetaData().getVOMeta(OrderItemVO.class),
        itemList.toArray(new OrderItemVO[itemList.size()]));
    OrderVO[] queryVos = bc.composite();

    queryVos = processer.after(queryVos);
    return queryVos;
  }

  private void addRule(AroundProcesser<OrderVO> processer) {
    // 计算可开票数量
    processer.addAfterRule(new CanInvoiceNumCalcRule());
  }
}
