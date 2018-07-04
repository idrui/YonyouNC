/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-14 下午04:27:39
 */
package nc.bs.pu.m21.query.ic.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.pubitf.ic.mstoreadmin.IStoreadminQueryService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              根据库管员权限过滤
 * @scene
 *        采购订单入库单、退库单转单查询
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午8:36:20
 * @author luojw
 */
public class StorePrivilegeChkRule implements IFilterRule<OrderVO> {

  @Override
  public OrderVO[] process(OrderVO[] vos) {
    // 库存服务，库管员权限
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    Set<String> recvstorSet = new HashSet<String>();
    List<String> matList = new ArrayList<String>();
    for (OrderVO vo : vos) {
      for (OrderItemVO itemVO : vo.getBVO()) {
        String pk_recvstordoc = itemVO.getPk_recvstordoc();
        if (!StringUtil.isEmptyWithTrim(pk_recvstordoc)) {
          recvstorSet.add(pk_recvstordoc);
          matList.add(itemVO.getPk_material());
        }
      }
    }

    if (0 == recvstorSet.size()) {
      return vos;
    }

    // 库存接口
    MapList<String, String> mapList = null;
    String userid = BSContext.getInstance().getUserID();
    String[] recvstors = recvstorSet.toArray(new String[recvstorSet.size()]);
    String pk_org = vos[0].getBVO()[0].getPk_arrvstoorg();
    String[] mats = matList.toArray(new String[matList.size()]);
    IStoreadminQueryService service =
        NCLocator.getInstance().lookup(IStoreadminQueryService.class);
    try {
      mapList =
          service.getCmaterialByWarehouseManager(pk_org, recvstors, userid,
              mats);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    if (null == mapList || 0 == mapList.size()) {
      return vos;
    }
    List<OrderVO> voList = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      List<OrderItemVO> itemList = new ArrayList<OrderItemVO>();
      for (OrderItemVO itemVO : vo.getBVO()) {
        String pk_recvstordoc = itemVO.getPk_recvstordoc();
        if (null == pk_recvstordoc) {
          itemList.add(itemVO);
          continue;
        }
        List<String> stormatList = mapList.get(pk_recvstordoc);
        if (!CollectionUtils.isEmpty(stormatList)
            && stormatList.contains(itemVO.getPk_material())) {
          itemList.add(itemVO);
        }
      }

      if (0 == itemList.size()) {
        continue;
      }

      OrderItemVO[] itemVOs =
          itemList.toArray(new OrderItemVO[itemList.size()]);
      vo.setBVO(itemVOs);
      voList.add(vo);
    }

    if (voList.size() > 0) {
      OrderVO[] returnVOs = voList.toArray(new OrderVO[voList.size()]);
      return returnVOs;
    }
    return null;
  }

}
