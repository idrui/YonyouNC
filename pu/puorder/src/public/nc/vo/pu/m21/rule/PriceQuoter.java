package nc.vo.pu.m21.rule;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderDefaultPriceQuery;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.query.price.OrderPriceQueryDetail;
import nc.vo.pu.m21.query.price.OrderPriceQueryParam;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>主要职责是根据参数进行询价
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 下午12:16:37
 */
public class PriceQuoter {
  private IKeyValue keyValue;

  public PriceQuoter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：订单询价。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @return 询到价的行数组
   * @since 6.0
   * @author duy
   * @time 2010-3-24 下午05:01:30
   */
  public Map<Integer, String> setDefaultPrice(Integer[] rows) {
    Map<Integer, String> map = null;
    try {
      IOrderDefaultPriceQuery service =
          NCLocator.getInstance().lookup(IOrderDefaultPriceQuery.class);

      // 准备询价参数
      OrderPriceQueryParam param = this.getParam(rows);

      // 询价
      param = service.queryOrderPrice(param);

      // 设置数据
      map = this.setPrice(param);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return map;
  }

  private OrderPriceQueryParam getParam(Integer[] rows) {
    // 准备询价参数
    OrderPriceQueryParam param = new OrderPriceQueryParam();
    param.setBillDate((UFDate) this.keyValue
        .getHeadValue(OrderHeaderVO.DBILLDATE));
    param.setCurrency((String) this.keyValue
        .getHeadValue(OrderHeaderVO.CORIGCURRENCYID));
    param.setPurchaseOrg((String) this.keyValue
        .getHeadValue(OrderHeaderVO.PK_ORG));
    param.setSupplier((String) this.keyValue
        .getHeadValue(OrderHeaderVO.PK_SUPPLIER));

    OrderPriceQueryDetail[] detail = new OrderPriceQueryDetail[rows.length];
    for (int i = 0; i < rows.length; i++) {
      detail[i] = new OrderPriceQueryDetail();
      detail[i].setRow(rows[i].intValue());
      // 报价单位
      detail[i].setCastunitid((String) this.keyValue.getBodyValue(
          rows[i].intValue(), OrderItemVO.CQTUNITID));
      // 物料VID
      detail[i].setMaterial((String) this.keyValue.getBodyValue(
          rows[i].intValue(), OrderItemVO.PK_MATERIAL));
      // 物料OID
      detail[i].setMaterialSourceId((String) this.keyValue.getBodyValue(
          rows[i].intValue(), OrderItemVO.PK_SRCMATERIAL));
      // 结算财务组织
      detail[i].setFinanceOrg((String) this.keyValue.getBodyValue(
          rows[i].intValue(), OrderItemVO.PK_PSFINANCEORG));
      // 生产厂商
      detail[i].setProductor((String) this.keyValue.getBodyValue(
          rows[i].intValue(), OrderItemVO.CPRODUCTORID));
      // 质量等级
      detail[i].setQuantityLevel((String) this.keyValue.getBodyValue(
          rows[i].intValue(), OrderItemVO.CQUALITYLEVELID));
      // 收货地区
      detail[i].setReceiveArea((String) this.keyValue.getBodyValue(
          rows[i].intValue(), OrderItemVO.CDEVAREAID));
      // 运输方式
      detail[i].setTransportType((String) this.keyValue
          .getHeadValue(OrderHeaderVO.PK_TRANSPORTTYPE));
      // 设置合同数据
      this.setContract(detail[i], rows[i].intValue());
      // 设置请购单数据
      this.setBuyingReq(detail[i], rows[i].intValue());
    }
    param.setDetail(detail);

    return param;
  }

  private void setBuyingReq(OrderPriceQueryDetail detail, int row) {
    Object obj = this.keyValue.getBodyValue(row, OrderItemVO.CSOURCETYPECODE);
    if (obj != null && obj.equals(POBillType.PrayBill.getCode())) {
      detail.setBuyingReq((String) this.keyValue.getBodyValue(row,
          OrderItemVO.CSOURCEBID));
    }
  }

  private void setContract(OrderPriceQueryDetail detail, int row) {
    Object obj = this.keyValue.getBodyValue(row, OrderItemVO.CCONTRACTROWID);
    if (obj != null) {
      detail.setContract((String) obj);
    }
  }

  private Map<Integer, String> setPrice(OrderPriceQueryParam param) {
    OrderPriceQueryDetail[] details = param.getDetail();
    Map<Integer, String> map = new HashMap<Integer, String>();
    for (OrderPriceQueryDetail detail : details) {
      if (detail != null
          && (MathTool.nvl(detail.getPrice()).compareTo(UFDouble.ZERO_DBL) > 0
              || MathTool.nvl(detail.getTaxPrice())
                  .compareTo(UFDouble.ZERO_DBL) > 0
              || MathTool.nvl(detail.getOrigPrice()).compareTo(
                  UFDouble.ZERO_DBL) > 0
              || MathTool.nvl(detail.getOrigTaxPrice()).compareTo(
                  UFDouble.ZERO_DBL) > 0
              || MathTool.nvl(detail.getQtorigprice()).compareTo(
                  UFDouble.ZERO_DBL) > 0 || MathTool.nvl(
              detail.getQtorigtaxprice()).compareTo(UFDouble.ZERO_DBL) > 0)) {
        int row = detail.getRow();
        if (detail.getPrice() != null) {
          this.keyValue.setBodyValue(row, OrderItemVO.NNETPRICE,
              detail.getPrice());
          map.put(Integer.valueOf(row), OrderItemVO.NNETPRICE);
        }
        else if (detail.getTaxPrice() != null) {
          this.keyValue.setBodyValue(row, OrderItemVO.NTAXNETPRICE,
              detail.getTaxPrice());
          map.put(Integer.valueOf(row), OrderItemVO.NTAXNETPRICE);
        }
        else if (detail.getQtorigprice() != null) {
          this.keyValue.setBodyValue(row, OrderItemVO.NQTORIGNETPRICE,
              detail.getQtorigprice());
          map.put(Integer.valueOf(row), OrderItemVO.NQTORIGNETPRICE);
        }
        else if (detail.getQtorigtaxprice() != null) {
          this.keyValue.setBodyValue(row, OrderItemVO.NQTORIGTAXNETPRC,
              detail.getQtorigtaxprice());
          map.put(Integer.valueOf(row), OrderItemVO.NQTORIGTAXNETPRC);
        }
        else if (detail.getOrigPrice() != null) {
          this.keyValue.setBodyValue(row, OrderItemVO.NORIGNETPRICE,
              detail.getOrigPrice());
          map.put(Integer.valueOf(row), OrderItemVO.NORIGNETPRICE);
        }
        else if (detail.getOrigTaxPrice() != null) {
          this.keyValue.setBodyValue(row, OrderItemVO.NORIGTAXNETPRICE,
              detail.getOrigTaxPrice());
          map.put(Integer.valueOf(row), OrderItemVO.NORIGTAXNETPRICE);
        }

        // 补充税率和扣税类别，目前只有供应商价目表询价会返回这两个
        if (detail.getTaxRate() != null) {
          this.keyValue.setBodyValue(row, OrderItemVO.NTAXRATE,
              detail.getTaxRate());
        }
        if (detail.getTaxTypeFlag() != null) {
          this.keyValue.setBodyValue(row, OrderItemVO.FTAXTYPEFLAG,
              detail.getTaxTypeFlag());
        }
        // 填入不可抵扣税率
        if (detail.getNnosubtaxrate() != null) {
          this.keyValue.setBodyValue(row, OrderItemVO.NNOSUBTAXRATE,
              detail.getNnosubtaxrate());
        }
        // rows.add(Integer.valueOf(row));
      }
    }
    return map;
  }
}
