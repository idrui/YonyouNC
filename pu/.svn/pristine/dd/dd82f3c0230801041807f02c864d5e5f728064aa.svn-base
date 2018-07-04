/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-22 下午03:48:30
 */
package nc.ui.pu.m21.view.sideform;

import nc.itf.pu.reference.ct.Z2CTServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.ct.entity.CtRelatingVO;
import nc.vo.ct.purdaily.entity.CtPubillViewVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-10-22 下午03:48:30
 */
public class OrderSideFormRelCT {

  /**
   * 方法功能描述：取得关联合同参数
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVO
   * @param bodyIndex
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-22 下午03:04:16
   */
  private CtRelatingVO[] getParamVOForCT(OrderVO orderVO, int bodyIndex) {
    OrderHeaderVO hvo = orderVO.getHVO();
    OrderItemVO[] bvos = orderVO.getBVO();

    // if (bodyIndex >= bvos.length || bodyIndex < 0) {
    // return null;
    // }

    if (!OrderSideFormUtil.isNeedFindPrice(bodyIndex, bvos.length)) {
      return null;
    }

    OrderItemVO bvo = bvos[bodyIndex];

    CtRelatingVO[] ctrVO = new CtRelatingVO[1];

    // 主组织
    String pk_org = hvo.getPk_org();
    // 币种
    String currency = hvo.getCorigcurrencyid();
    // 客商
    String cvendor = hvo.getPk_supplier();
    // 签订日期
    UFDate date = hvo.getDbilldate();

    // 有供应商及日期时才需重新寻找合同
    if (StringUtil.isEmptyWithTrim(pk_org)
        || StringUtil.isEmptyWithTrim(cvendor)
        || ObjectUtil.isEmptyWithTrim(date)) {
      return null;
    }

    // 物料
    String material = bvo.getPk_material();
    // 收货地区
    String receiveaddress = bvo.getCdevareaid();
    // 来源行号
    String sourerowno = bvo.getCrowno();
    // 生产厂商
    String cproductorid = bvo.getCproductorid();
    // 质量等级
    String cqualitylevelid = bvo.getCqualitylevelid();
    // 运输方式
    String ctranspmodeid = hvo.getPk_transporttype();

    ctrVO[0] = new CtRelatingVO();
    // 币种
    ctrVO[0].setCurrency(currency);
    // 主组织
    ctrVO[0].setPk_org(pk_org);
    // 客商
    ctrVO[0].setCvendor(cvendor);
    // 签订日期
    ctrVO[0].setDate(date);
    // 物料
    ctrVO[0].setMaterial(material);
    // 收货地区
    ctrVO[0].setReceiveaddress(receiveaddress);
    // 来源行号
    ctrVO[0].setSourerowno(sourerowno);
    // 生产厂商
    ctrVO[0].setCproductorid(cproductorid);
    // 质量等级
    ctrVO[0].setCqualitylevelid(cqualitylevelid);
    // 运输方式
    ctrVO[0].setCtranspmodeid(ctranspmodeid);

    return ctrVO;
  }

  /**
   * 方法功能描述：关联合同
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVO
   * @param bodyIndex
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-22 下午03:54:43
   */
  protected String relatingCT(OrderVO orderVO, int bodyIndex, PricePriority pp) {

    try {
      if (!SysInitGroupQuery.isCTEnabled()) {
        return "";
      }
      // 关联合同
      CtRelatingVO[] ctrVOs = this.getParamVOForCT(orderVO, bodyIndex);

      if (ArrayUtils.isEmpty(ctrVOs)) {
        return "";
      }

      CtPubillViewVO[] ctViewVOs = Z2CTServices.relatingCT(ctrVOs);
      // 2013-01-08 fanly3 适配采购订单侧边栏价格按钮,ctViewVO出现null值,抛异常
      if (ctViewVOs == null) {
        return null;
      }
      StringBuilder sb = new StringBuilder();

      ScaleUtils scaleUtils =
          new ScaleUtils(AppContext.getInstance().getPkGroup());
      if (pp == PricePriority.PRICE_PRIOR_TO_TAXPRICE) {
        for (CtPubillViewVO ctViewVO : ctViewVOs) {
          UFDouble contractPrc = ctViewVO.getNorigprice();
          contractPrc =
              scaleUtils.adjustSoPuPriceScale(contractPrc,
                  ctViewVO.getCorigcurrencyid());
          sb.append(contractPrc);
          sb.append(",");
        }
      }
      else {
        for (CtPubillViewVO ctViewVO : ctViewVOs) {
          UFDouble contractPrc = ctViewVO.getNorigtaxprice();
          contractPrc =
              scaleUtils.adjustSoPuPriceScale(contractPrc,
                  ctViewVO.getCorigcurrencyid());
          sb.append(contractPrc);
          sb.append(",");
        }
      }

      if (sb.length() > 0) {
        return sb.toString().substring(0, sb.length() - 1);
      }

      return sb.toString();

    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);

    }
    return null;
  }
}
