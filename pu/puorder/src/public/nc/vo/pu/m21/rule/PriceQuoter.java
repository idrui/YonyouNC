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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ҫְ���Ǹ��ݲ�������ѯ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 ����12:16:37
 */
public class PriceQuoter {
  private IKeyValue keyValue;

  public PriceQuoter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ������������������ѯ�ۡ�
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @return ѯ���۵�������
   * @since 6.0
   * @author duy
   * @time 2010-3-24 ����05:01:30
   */
  public Map<Integer, String> setDefaultPrice(Integer[] rows) {
    Map<Integer, String> map = null;
    try {
      IOrderDefaultPriceQuery service =
          NCLocator.getInstance().lookup(IOrderDefaultPriceQuery.class);

      // ׼��ѯ�۲���
      OrderPriceQueryParam param = this.getParam(rows);

      // ѯ��
      param = service.queryOrderPrice(param);

      // ��������
      map = this.setPrice(param);
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return map;
  }

  private OrderPriceQueryParam getParam(Integer[] rows) {
    // ׼��ѯ�۲���
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
      // ���۵�λ
      detail[i].setCastunitid((String) this.keyValue.getBodyValue(
          rows[i].intValue(), OrderItemVO.CQTUNITID));
      // ����VID
      detail[i].setMaterial((String) this.keyValue.getBodyValue(
          rows[i].intValue(), OrderItemVO.PK_MATERIAL));
      // ����OID
      detail[i].setMaterialSourceId((String) this.keyValue.getBodyValue(
          rows[i].intValue(), OrderItemVO.PK_SRCMATERIAL));
      // ���������֯
      detail[i].setFinanceOrg((String) this.keyValue.getBodyValue(
          rows[i].intValue(), OrderItemVO.PK_PSFINANCEORG));
      // ��������
      detail[i].setProductor((String) this.keyValue.getBodyValue(
          rows[i].intValue(), OrderItemVO.CPRODUCTORID));
      // �����ȼ�
      detail[i].setQuantityLevel((String) this.keyValue.getBodyValue(
          rows[i].intValue(), OrderItemVO.CQUALITYLEVELID));
      // �ջ�����
      detail[i].setReceiveArea((String) this.keyValue.getBodyValue(
          rows[i].intValue(), OrderItemVO.CDEVAREAID));
      // ���䷽ʽ
      detail[i].setTransportType((String) this.keyValue
          .getHeadValue(OrderHeaderVO.PK_TRANSPORTTYPE));
      // ���ú�ͬ����
      this.setContract(detail[i], rows[i].intValue());
      // �����빺������
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

        // ����˰�ʺͿ�˰���Ŀǰֻ�й�Ӧ�̼�Ŀ��ѯ�ۻ᷵��������
        if (detail.getTaxRate() != null) {
          this.keyValue.setBodyValue(row, OrderItemVO.NTAXRATE,
              detail.getTaxRate());
        }
        if (detail.getTaxTypeFlag() != null) {
          this.keyValue.setBodyValue(row, OrderItemVO.FTAXTYPEFLAG,
              detail.getTaxTypeFlag());
        }
        // ���벻�ɵֿ�˰��
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
