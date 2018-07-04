package nc.vo.pu.pub.rule.upgrade;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.bd.payment.IPaymentUtil;
import nc.vo.pub.JavaType;

/**
 * 生效日期公共升级类，合同可以调用。
 * 
 * @since 6.3
 * @version 2013-1-15 下午05:02:38
 * @author lixyp
 */
public class EffDateTypeUpgrade {

  private String fieldName = null;

  private String tableName = null;

  public EffDateTypeUpgrade(String tableName, String fieldName) {
    this.tableName = tableName;
    this.fieldName = fieldName;
  }

  /**
   * 执行升级
   */
  public void doUpgrade() {
    new DataAccessUtils().update(this.getSql(), new JavaType[] {
      JavaType.String, JavaType.String
    }, this.getDatas());
  }

  /**
   * 获取升级数据
   * 
   * @return 升级数据
   */
  private List<List<Object>> getDatas() {
    List<List<Object>> datas = new ArrayList<List<Object>>();

    // 采购合同生效日期
    List<Object> list1 = new ArrayList<Object>();
    list1.add(IPaymentUtil.PURCHASE_CONTRACT_EFFECTIVE_DATE);
    list1.add("4");
    datas.add(list1);

    // 采购订单审核日期
    List<Object> list2 = new ArrayList<Object>();
    list2.add(IPaymentUtil.PURCHASE_ORDER_APPROVE_DATE);
    list2.add("5");
    datas.add(list2);

    // 采购发票日期
    List<Object> list3 = new ArrayList<Object>();
    list3.add(IPaymentUtil.PURCHASE_INVOICE_DATE);
    list3.add("6");
    datas.add(list3);

    // 采购发票审核日期
    List<Object> list4 = new ArrayList<Object>();
    list4.add(IPaymentUtil.PURCHASE_INVOICE_APPROVE_DATE);
    list4.add("10");
    datas.add(list4);

    // 到货审核日期
    List<Object> list5 = new ArrayList<Object>();
    list5.add(IPaymentUtil.RECEIPT_APPROVE_DATE);
    list5.add("7");
    datas.add(list5);

    // 入库日期
    List<Object> list6 = new ArrayList<Object>();
    list6.add(IPaymentUtil.STORE_RECEIPT_DATE);
    list6.add("8");
    datas.add(list6);

    // 入库签字日期
    List<Object> list7 = new ArrayList<Object>();
    list7.add(IPaymentUtil.STORE_RECEIPT_SIGNATURE_DATE);
    list7.add("9");
    datas.add(list7);

    // 项目发包合同生效日期
    List<Object> list8 = new ArrayList<Object>();
    list8.add(IPaymentUtil.PROJECT_CONTRACT_EFFECTIVE_DATE);
    list8.add("13");
    datas.add(list8);

    // 项目合同质保金起算日期
    List<Object> list9 = new ArrayList<Object>();
    list9.add(IPaymentUtil.PROJECT_PACT_BAIL_APPROVE_DATE);
    list9.add("14");
    datas.add(list9);
    return datas;
  }

  /**
   * 获取升级SQL
   * 
   * @return 升级SQL
   */
  private String getSql() {
    StringBuilder builder = new StringBuilder();
    builder.append(" update ");
    builder.append(this.tableName);
    builder.append(" set ");
    builder.append(this.fieldName);
    builder.append(" = ? where ");
    builder.append(this.fieldName);
    builder.append(" = ?");
    return builder.toString();
  }
}
