package nc.test.pu.m21.query.price.cal;

import nc.bs.framework.test.AbstractTestCase;
import nc.bs.pu.m21.query.price.cal.OrderPriceDailyMaintainance;
import nc.vo.pub.lang.UFDate;

public class OrderPriceMaintainanceTest extends AbstractTestCase {
  // �Ȳ�testUpdatePriceBByOrderB()�����ٲ����
  public void testCalculateForDailyTimerTask() {
    // OrderPriceMaintainance orderPriceMaintainance =
    // new OrderPriceDailyMaintainance();
    // orderPriceMaintainance.excuteTimeTask(new UFDate("2011-11-01"));
    UFDate j1 = new UFDate("2011-11-01").asEnd();
    UFDate j2 = new UFDate("2011-11-01").asBegin();
    long a = j1.getMillis() - j2.getMillis();
    System.out.println(a);
  }

  public void testCalculateForInitTask() {
    // OrderPriceDailyMaintainance orderPriceMaintainance =
    // new OrderPriceDailyMaintainance();
    // orderPriceMaintainance.excuteTimeTask(null);

  }

  public void testCalculateForMonthlyTimerTask() {

    // OrderPriceMaintainance orderPriceMaintainance =
    // new OrderPriceMonthlyMaintainance();

    // ��Ҫ����ü����µ����
    // orderPriceMaintainance.excuteTimeTask(new UFDate("2011-4-09"));
    // ��Ҫ����1���µ����
    // orderPriceMaintainance.excuteTimeTask("2011-10-09");
    // ��Ҫ����Ҫ��������
    // orderPriceMaintainance.excuteTimeTask(new UFDate("2011-11-09"));

  }

  public void testCalculateForOrderPriceB() {
    // OrderPriceCalculateTimerTask orderPriceCalculateTimerTask = new
    // OrderPriceCalculateTimerTask();
    // orderPriceCalculateTimerTask.calculateForOrderPriceB();
  }

  public void testInsertAlertDate() {
    // OrderPriceMonthlyMaintainance orderPriceMaintainance =
    // new OrderPriceMonthlyMaintainance();
    // orderPriceMaintainance.insertAlertDate();
  }

  // // '1002F510000000005U61'�������������testQueryLatestPrice�еĲ�������������ϵ�ģ�
  // // �������ֻҪ����order_b���У������Ƿ����price�����������鶼�ᱻ���£���6��ά��+modifytime���Ƶ�һ�����ݡ�
  // // ������ݵ�id��'2011-05-11
  // // 16:44:1401002F510000000005U61'����������ѯ�۱��е�id,��Ӧorderb����18������
  // // orderPriceCalculator.updatePriceByOrderB("1002F510000000005U61");
  //
  // // �������������Ϊ�˲������һ��rno����������޸ĵ�Ԫ��û�д���price����Ȼ���£�

  public void testPutInvalidateDataToTemp() {
    // String[] pk_order_bs = {
    // "1002F510000000005MA2"
    // };
    // OrderPriceMaintainance orderPriceMaintainance =
    // new OrderPriceMaintainance();
    // orderPriceMaintainance.updatePriceToInvalidateByOrderB(pk_order_bs);
    // orderPriceMaintainance.updatePriceBToInvalidateByOrderB(pk_order_bs);

  }

  public void testUpdatePriceBByOrderB() {
    String[] pk_order_bs = {
      "1002F510000000005MA2"
    };
    // OrderPriceDailyMaintainance orderPriceMaintainance =
    new OrderPriceDailyMaintainance();
    // orderPriceMaintainance.updatePriceToInvalidateByOrderB(pk_order_bs);
    // `orderPriceMaintainance.updatePriceBToInvalidateByOrderB(pk_order_bs);
    System.out.println(pk_order_bs);

  }

}
