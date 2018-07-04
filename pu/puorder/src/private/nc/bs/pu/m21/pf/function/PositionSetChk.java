package nc.bs.pu.m21.pf.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.uap.rbac.UserManageQuery;
import nc.pubitf.pu.position.IQueryPosition;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * 采购岗设置检查
 * 
 * @since 6.0
 * @version 2011-4-13 下午04:25:05
 * @author wuxla
 */

public class PositionSetChk {

  /**
   * 采购岗设置检查
   * 
   * @param vo
   * @return
   * @throws BusinessException
   */
  public UFBoolean positionChk(AggregatedValueObject aggVO)
      throws BusinessException {
    if (null == aggVO) {
      return UFBoolean.FALSE;
    }
    OrderVO vo = (OrderVO) aggVO;
    String cemployeeid = vo.getHVO().getCemployeeid();
    if (StringUtil.isEmptyWithTrim(cemployeeid)) {
      return UFBoolean.FALSE;
    }

    String userid = BSContext.getInstance().getUserID();
    String pk_psn = UserManageQuery.queryPsndocByUserid(userid);
    if (!cemployeeid.equals(pk_psn)) {
      return UFBoolean.FALSE;
    }

    String pk_org = vo.getHVO().getPk_org();
    String pk_stock = vo.getBVO()[0].getPk_arrvstoorg();
    OrderItemVO[] itemVOs = vo.getBVO();
    List<String> matList = new ArrayList<String>();
    for (OrderItemVO itemVO : itemVOs) {
      matList.add(itemVO.getPk_material());
    }
    String[] materials = matList.toArray(new String[matList.size()]);
    IQueryPosition service =
        NCLocator.getInstance().lookup(IQueryPosition.class);
    Map<String, String> map = service.getPurchaser(new String[] {
      pk_org
    }, new String[] {
      pk_stock
    }, materials);
    for (OrderItemVO itemVO : itemVOs) {
      String pk_material = itemVO.getPk_material();
      String pk_purchaser = map.get(pk_org + pk_material);
      if (null == pk_purchaser || !pk_purchaser.equals(cemployeeid)) {
        return UFBoolean.FALSE;
      }
    }

    return UFBoolean.TRUE;
  }
}
