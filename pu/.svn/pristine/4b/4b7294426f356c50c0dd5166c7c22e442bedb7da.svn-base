package nc.bs.pu.m21.writeback.source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.pu.reference.so.M30SOServices;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-3-22 上午09:29:18
 * @author wuxla
 */

public class WriteBackCoop30 {

  /**
   * 协同回写
   * 
   * @param vos
   * @param orgVos
   */
  public void writeback30Coop(OrderVO[] vos, OrderVO[] orgVos) {
    List<OrderVO> voList = new ArrayList<OrderVO>();
    // 删除
    if (ArrayUtils.isEmpty(vos)) {
      for (OrderVO vo : orgVos) {
        if (UFBoolean.TRUE.equals(vo.getHVO().getBsocooptome())) {
          voList.add(vo);
        }
      }
    }
    // 新增
    else if (ArrayUtils.isEmpty(orgVos)) {
      for (OrderVO vo : vos) {
        if (UFBoolean.TRUE.equals(vo.getHVO().getBsocooptome())) {
          voList.add(vo);
        }
      }
    }

    if (0 == voList.size()) {
      return;
    }

    // 删除
    if (ArrayUtils.isEmpty(vos)) {
      Set<String> hidSet = new HashSet<String>();
      for (OrderVO vo : orgVos) {
        for (OrderItemVO itemVO : vo.getBVO()) {
          hidSet.add(itemVO.getCsourceid());
        }
      }
      String[] hids = hidSet.toArray(new String[hidSet.size()]);
      M30SOServices.renovate30For21Delete(hids);
    }
    // 新增
    else if (ArrayUtils.isEmpty(orgVos)) {
      Map<String, String> map = new HashMap<String, String>();
      for (OrderVO vo : vos) {
        String vbillcode = vo.getHVO().getVbillcode();
        for (OrderItemVO itemVO : vo.getBVO()) {
          map.put(itemVO.getCsourceid(), vbillcode);
        }
      }

      M30SOServices.rewriteBillCode(map);
    }
  }

}
