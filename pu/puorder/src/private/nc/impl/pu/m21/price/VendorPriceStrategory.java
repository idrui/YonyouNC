package nc.impl.pu.m21.price;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pp.supplierprice.ISupplierPriceQuery;
import nc.vo.pp.supplierprice.entity.SupplierPrice;
import nc.vo.pp.supplierprice.entity.SupplierPriceQueryVO;
import nc.vo.pp.supplierprice.enumeration.QTSoruceType;
import nc.vo.pu.m21.query.price.OrderPriceQueryDetail;
import nc.vo.pu.m21.query.price.OrderPriceQueryParam;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>供应商价目表询价策略
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 下午03:55:25
 */
public class VendorPriceStrategory extends AbstractPriceStrategy {

  @Override
  public void queryPrice() {
    // duy 等待采购价格的供应商价目表提供接口
    if (!SysInitGroupQuery.isPPEnabled()) {
      return;
    }
    ISupplierPriceQuery service =
        NCLocator.getInstance().lookup(ISupplierPriceQuery.class);

    SupplierPriceQueryVO[] spvoParam = this.getParam();

    try {
      SupplierPrice[] sp = service.query(spvoParam, QTSoruceType.PO);

      // 把询到的价格放置到参数上
      this.setPrice(sp);
    }
    catch (BusinessException e) {
      // ExceptionUtils;
      // 日志异常
      ExceptionUtils.wrappException(e);

    }

  }

  /**
   * 方法功能描述：新生成供应商询报价关联查询条件
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-26 上午10:46:56
   */
  private SupplierPriceQueryVO getNewSpVO() {
    SupplierPriceQueryVO spvo = new SupplierPriceQueryVO();
    // 主组织
    String pk_org = this.getQueryParameter().getPurchaseOrg();
    // 币种
    String currency = this.getQueryParameter().getCurrency();
    // 供应商
    String pk_supplier = this.getQueryParameter().getSupplier();
    // 签订日期
    UFDate date = this.getQueryParameter().getBillDate();

    spvo.setPk_org(pk_org);
    spvo.setCurrency(currency);
    spvo.setPk_supplier(pk_supplier);
    spvo.setDate(date);

    return spvo;
  }

  /**
   * 方法功能描述：取得接口参数
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-26 上午10:45:31
   */
  private SupplierPriceQueryVO[] getParam() {

    List<SupplierPriceQueryVO> spvoList = new ArrayList<SupplierPriceQueryVO>();

    OrderPriceQueryParam param = this.getQueryParameter();
    OrderPriceQueryDetail[] details = param.getDetail();
    for (OrderPriceQueryDetail detail : details) {
      SupplierPriceQueryVO spvo = this.getNewSpVO();
      // 生产厂商
      spvo.setCproductorid(detail.getProductor());
      // 质量等级
      spvo.setCqualitylevelid(detail.getQuantityLevel());
      // 运输方式
      spvo.setCtranspmodeid(detail.getTransportType());
      // 物料原始id
      spvo.setPk_srcmaterial(detail.getMaterialSourceId());
      // 收货地区
      spvo.setReceiveaddress(detail.getReceiveArea());
      // 单位
      spvo.setCastunitid(detail.getCastunitid());

      spvoList.add(spvo);
    }

    return spvoList.toArray(new SupplierPriceQueryVO[spvoList.size()]);
  }

  private void setPrice(SupplierPrice[] prices) {

    int len = prices.length;

    for (int i = 0; i < len; i++) {
      OrderPriceQueryDetail[] details = this.getQueryParameter().getDetail();
      if (prices[i] != null) {
        if (this.getPricePriority() == PricePriority.PRICE_PRIOR_TO_TAXPRICE) {
          details[i].setOrigPrice(prices[i].getNorigprice());
          details[i].setQtorigprice(prices[i].getNastorigprice());
        }
        else {
          details[i].setOrigTaxPrice(prices[i].getNorigtaxprice());
          details[i].setQtorigtaxprice(prices[i].getNastorigtaxprice());
        }
        // 补充税率和扣税类别
        details[i].setTaxRate(prices[i].getNtaxrate());
        details[i].setTaxTypeFlag(Integer.valueOf(prices[i].getFtaxtypeflag()));
        details[i].setNnosubtaxrate(prices[i].getNnosubtaxrate());
      }
    }
  }
}
