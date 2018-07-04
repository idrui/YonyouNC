package nc.ui.pu.m21.view.sideform;

import java.util.HashMap;
import java.util.Map;

import nc.itf.pu.m21.IOrderDefPriceSideForm;
import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.query.price.OrderPriceQueryDetail;
import nc.vo.pu.m21.query.price.OrderPriceQueryParam;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.util.remotecallcombination.RemoteCallCombinatorEx;
import nc.vo.util.remotecallcombination.Token;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单侧边栏询价
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-10-22 上午11:29:00
 */
public class OrderSideFormDefPrice implements IPURemoteCallCombinator {

  private Map<PriceSource, UFDouble> priceMap =
      new HashMap<PriceSource, UFDouble>();

  private PricePriority pricePrior;

  private int row;

  private Token tokenLow; // 最低价token

  private Token tokenNew; // 最新价token

  private Token tokenSP;// 供应商价目token

  private OrderVO vo;

  public OrderSideFormDefPrice(OrderVO vo, int row, PricePriority pricePrior) {
    super();
    this.vo = vo;
    this.row = row;
    this.pricePrior = pricePrior;
  }

  public Map<PriceSource, UFDouble> getPriceMap() {
    return this.priceMap;
  }

  @Override
  public void prepare() {
    RemoteCallCombinatorEx rcc = RemoteCallCombinatorEx.getInstance();
    if (this.tokenLow != null) {
      rcc.update(this.tokenLow);
    }
    if (this.tokenNew != null) {
      rcc.update(this.tokenNew);
    }
    if (this.tokenSP != null) {
      rcc.update(this.tokenSP);
    }
    this.getDefPrice(this.vo, this.row, this.pricePrior);
  }

  @Override
  public void process() {
    PriceSource[] pss = this.getPriceSource();
    OrderPriceQueryParam resultPara = null;
    for (int i = 0; i < pss.length; i++) {
      resultPara = this.getPriceResult(pss[i]);
      if (null == resultPara) {
        continue;
      }
      if (this.pricePrior == PricePriority.PRICE_PRIOR_TO_TAXPRICE) {
        this.priceMap.put(pss[i], resultPara.getDetail()[0].getOrigPrice());
      }
      else {
        this.priceMap.put(pss[i], resultPara.getDetail()[0].getOrigTaxPrice());
      }
    }
  }

  private void getDefPrice(OrderVO orderVO, int bodyIndex, PricePriority pp) {

    PriceSource[] pss = this.getPriceSource();

    for (int i = 0; i < pss.length; i++) {
      // 准备询价参数
      OrderPriceQueryParam param = this.getParam(orderVO, bodyIndex);
      // 执行询价的合并调用
      this.getPriceInfo(param, pss[i], pp);
    }

  }

  /**
   * 方法功能描述：准备询价参数
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVO
   * @param bodyIndex
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-22 上午10:43:54
   */
  private OrderPriceQueryParam getParam(OrderVO orderVO, int bodyIndex) {
    // 准备询价参数
    OrderPriceQueryParam param = new OrderPriceQueryParam();

    OrderHeaderVO hvo = orderVO.getHVO();

    if (!OrderSideFormUtil.isNeedFindPrice(bodyIndex, orderVO.getBVO().length)) {
      return null;
    }

    OrderItemVO bvo = orderVO.getBVO()[bodyIndex];

    param.setBillDate(hvo.getDbilldate());
    param.setCurrency(hvo.getCorigcurrencyid());
    param.setPurchaseOrg(hvo.getPk_org());
    param.setSupplier(hvo.getPk_supplier());

    OrderPriceQueryDetail[] detail = new OrderPriceQueryDetail[1];

    detail[0] = new OrderPriceQueryDetail();
    detail[0].setRow(bodyIndex);
    // 物料VID
    detail[0].setMaterial(bvo.getPk_material());
    // 物料OID
    detail[0].setMaterialSourceId(bvo.getPk_srcmaterial());
    // 结算财务组织
    detail[0].setFinanceOrg(bvo.getPk_psfinanceorg());
    // 生产厂商
    detail[0].setProductor(bvo.getCproductorid());
    // 质量等级
    detail[0].setQuantityLevel(bvo.getCqualitylevelid());
    // 收货地区
    detail[0].setReceiveArea(bvo.getCdevareaid());
    // 运输方式
    detail[0].setTransportType(hvo.getPk_transporttype());
    // 设置合同数据
    this.setContract(detail[0], bvo);
    // 设置请购单数据
    this.setBuyingReq(detail[0], bvo);
    // 单位
    detail[0].setCastunitid(bvo.getCastunitid());

    param.setDetail(detail);

    return param;
  }

  /**
   * 方法功能描述：询价
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVO 采购订单VO
   * @param bodyIndex 选择表体行
   * @param ps 价格来源
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-22 上午10:48:26
   */
  private void getPriceInfo(OrderPriceQueryParam param, PriceSource ps,
      PricePriority pp) {
    RemoteCallCombinatorEx rcc = RemoteCallCombinatorEx.getInstance();
    try {
      IOrderDefPriceSideForm getPriceService =
          rcc.getService(IOrderDefPriceSideForm.class);
      getPriceService.queryOrderPrice(param, ps, pp);
      if (PriceSource.OrderNewestPrice == ps) {
        this.tokenNew = rcc.getToken();
      }
      if (PriceSource.OrderMinPrice == ps) {
        this.tokenLow = rcc.getToken();
      }
      if (PriceSource.SupplierPrice == ps) {
        this.tokenSP = rcc.getToken();
      }
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  private OrderPriceQueryParam getPriceResult(PriceSource ps) {
    RemoteCallCombinatorEx rcc = RemoteCallCombinatorEx.getInstance();
    try {
      if (PriceSource.OrderNewestPrice == ps) {
        return (OrderPriceQueryParam) rcc.getResult(this.tokenNew);
      }
      else if (PriceSource.OrderMinPrice == ps) {
        return (OrderPriceQueryParam) rcc.getResult(this.tokenLow);
      }
      else if (PriceSource.SupplierPrice == ps) {
        return (OrderPriceQueryParam) rcc.getResult(this.tokenSP);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private PriceSource[] getPriceSource() {
    // 采购订单价格来源
    PriceSource[] pss =
        new PriceSource[] {
          PriceSource.SupplierPrice, PriceSource.OrderNewestPrice,
          PriceSource.OrderMinPrice
        };
    return pss;
  }

  /**
   * 方法功能描述：设置请购单数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param detail
   * @param bvo
   * @param row <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-21 下午02:48:02
   */
  private void setBuyingReq(OrderPriceQueryDetail detail, OrderItemVO bvo) {
    Object obj = bvo.getCsourcetypecode();
    if (obj != null && obj.equals(POBillType.PrayBill.getCode())) {
      detail.setBuyingReq(bvo.getCsourcebid());
    }
  }

  /**
   * 方法功能描述：设置合同数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param detail
   * @param bvo
   * @param row <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-21 下午02:47:47
   */
  private void setContract(OrderPriceQueryDetail detail, OrderItemVO bvo) {
    Object obj = bvo.getCcontractrowid();
    if (obj != null) {
      detail.setContract((String) obj);
    }
  }
}
