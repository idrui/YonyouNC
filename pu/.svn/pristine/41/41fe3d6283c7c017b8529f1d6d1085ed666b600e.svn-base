package nc.vo.pu.m21.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.pu.reference.so.M30SOServices;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.SOBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-8-25 上午09:06:46
 * @author wuxla
 */

public class DirectFinanceorgValue {
  private OrderVO[] orderVOs;

  private PraybillVO[] prayVOs;

  public DirectFinanceorgValue(PraybillVO[] prayVOs, OrderVO[] orderVOs) {
    this.prayVOs = prayVOs;
    this.orderVOs = orderVOs;
  }

  public void setDirectFinanceorg() {
    if (ArrayUtils.isEmpty(this.prayVOs) || ArrayUtils.isEmpty(this.orderVOs)) {
      return;
    }
    Set<String> directSet = this.getDirectPray();
    if (0 == directSet.size()) {
      return;
    }
    List<OrderItemVO> itemList = this.getDirectItemVO(directSet);
    if (0 == itemList.size()) {
      return;
    }

    Set<String> cfistbidSet = new HashSet<String>();
    for (OrderItemVO item : itemList) {
      cfistbidSet.add(item.getCfirstbid());
    }

    String[] cfirstbids = cfistbidSet.toArray(new String[cfistbidSet.size()]);
    Map<String, String> map = M30SOServices.getFinanceorgByBids(cfirstbids);
    if (null == map) {
      return;
    }
    this.setFinanceOrgValue(itemList, map);
  }

  private void filterItem(List<OrderItemVO> list, OrderItemVO itemVO,
      Set<String> directSet) {
    String csourceid = itemVO.getCsourceid();
    if (!directSet.contains(csourceid)) {
      return;
    }
    String cfirsttypecode = itemVO.getCfirsttypecode();
    if (!SOBillType.Order.getCode().equals(cfirsttypecode)) {
      return;
    }
    list.add(itemVO);
  }

  private List<OrderItemVO> getDirectItemVO(Set<String> directSet) {
    List<OrderItemVO> list = new ArrayList<OrderItemVO>();
    for (OrderVO vo : this.orderVOs) {
      for (OrderItemVO itemVO : vo.getBVO()) {
        this.filterItem(list, itemVO, directSet);
      }
    }
    return list;
  }

  private Set<String> getDirectPray() {
    Set<String> directSet = new HashSet<String>();
    for (PraybillVO prayVO : this.prayVOs) {
      if (UFBoolean.TRUE.equals(prayVO.getHVO().getBdirecttransit())) {
        directSet.add(prayVO.getHVO().getPk_praybill());
      }
    }
    return directSet;
  }

  private void setFinanceOrgValue(List<OrderItemVO> itemList,
      Map<String, String> map) {
    Map<String, String> vidMap = new HashMap<String, String>();
    for (OrderItemVO item : itemList) {
      String cfirstbid = item.getCfirstbid();
      String financeorgoid = map.get(cfirstbid);

      if (financeorgoid != null) {
        // 应该填入结算财务组织psfinanceorg而不是应付财务组织apfinanceorg，
        // item.setPk_apfinanceorg(financeorgoid);
        item.setPk_psfinanceorg(financeorgoid);
        // item.setPk_apliabcenter(financeorgoid);
      }
      String financeorgvid = vidMap.get(financeorgoid);
      if (null == financeorgvid) {
        financeorgvid = OrgUnitPubService.getOrgVid(financeorgoid);
        vidMap.put(financeorgoid, financeorgvid);
      }
      // item.setPk_apfinanceorg_v(financeorgvid);
      item.setPk_psfinanceorg_v(financeorgvid);
      // item.setPk_apliabcenter_v(financeorgvid);
    }
  }
}
