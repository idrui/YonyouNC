package nc.test.pu.m21.query.price.cal;

import nc.bs.framework.test.AbstractTestCase;
import nc.bs.pu.m21.query.price.cal.OrderPriceDailyMaintainance;
import nc.vo.pub.lang.UFDate;

public class OrderPriceMaintainanceTest extends AbstractTestCase {
  // 先测testUpdatePriceBByOrderB()才能再测这个
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

    // 测要计算好几个月的情况
    // orderPriceMaintainance.excuteTimeTask(new UFDate("2011-4-09"));
    // 测要计算1个月的情况
    // orderPriceMaintainance.excuteTimeTask("2011-10-09");
    // 测要不需要计算的情况
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

  // // '1002F510000000005U61'这个测试用例和testQueryLatestPrice中的测试用例是有联系的，
  // // 这个数据只要是在order_b表中，无论是否存入price表，整个数据组都会被更新，由6个维度+modifytime限制的一组数据。
  // // 这个数据的id，'2011-05-11
  // // 16:44:1401002F510000000005U61'是完整的在询价表中的id,对应orderb表有18个数据
  // // orderPriceCalculator.updatePriceByOrderB("1002F510000000005U61");
  //
  // // 这个测试用例是为了测如果在一个rno组中如果被修改的元素没有存入price表仍然更新，

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
