/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 ����03:29:34
 */
package nc.test.pu.m21.query.price.cal;

import nc.bs.framework.test.AbstractTestCase;
import nc.bs.pu.m21.query.price.cal.OrderPriceQuery;
import nc.vo.pu.m21.entity.OrderPriceVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����
 * <li>
 * <li>
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author yangtian
 * @time 2010-11-10 ����03:29:34
 */
public class OrderPriceQueryTest extends AbstractTestCase {

  // ִ��orderPriceCalculator.updatePriceBByOrderB("1002F510000000005U61");2011-05-11
  // 16:44:1401002F510000000005U61' ��˵����price����ȡ��
  // ���Ե�sql���ǰ����sql�Ľ�һ�£�����ѡid

  public void testQueryLatestPrice() {
    // OrderPriceVO[] orderPriceVOs = this.getData();
    // String[] marterials =
    // new String[] {
    // orderPriceVOs[0].getPk_srcmaterial(),
    // orderPriceVOs[1].getPk_srcmaterial()
    // };
    // orderPriceQuery.queryLatestPrice(orderPriceVOs[0].getPk_org(), null,
    // orderPriceVOs[0].getPk_supplier(),
    // orderPriceVOs[0].getCorigcurrencyid(), marterials);
    //
    // orderPriceQuery.queryLowestPrice(orderPriceVOs[0].getPk_org(),
    // orderPriceVOs[0].getPk_supplier(),
    // orderPriceVOs[0].getCorigcurrencyid(), marterials);

  }

  public void testQueryLatestPrice4TO() throws BusinessException {
    OrderPriceQuery orderPriceQuery = new OrderPriceQuery();
    // OrderPriceVO[] orderPriceVO2s = this.getData4To();
    String[] fiorgs = new String[] {
      "0001S210000000001W16", "0001S21000000000R91K"
    };
    String[] moids = new String[] {
      "1001S2100000000000KT", "1014Z8100000000002C1"
    };
    orderPriceQuery.queryLatestPrice4TO(fiorgs, moids, null, null);
  }

  public void testQueryLowestPrice() {

    // OrderPriceVO[] orderPriceVOs = this.getData();
    // String[] marterials =
    // new String[] {
    // orderPriceVOs[0].getPk_srcmaterial(),
    // orderPriceVOs[1].getPk_srcmaterial()
    // };
    // OrderPriceQuery orderPriceQuery = new OrderPriceQuery();
    // orderPriceQuery.queryLatestPrice(orderPriceVOs[0].getPk_org(), null,
    // orderPriceVOs[0].getPk_supplier(),
    // orderPriceVOs[0].getCorigcurrencyid(), marterials, null, null);
    //
    // orderPriceQuery.queryLowestPrice(orderPriceVOs[0].getPk_org(),
    // orderPriceVOs[0].getPk_supplier(),
    // orderPriceVOs[0].getCorigcurrencyid(), marterials, null, null);

  }

  public void testUFDate() {

    // Calendar now = Calendar.getInstance();
    // UFDate nowDate = UFDate.getDate(now.getTime());
    //
    // // �õ��ϸ��µ����һ��
    // UFDate endDate = nowDate.getDateBefore(nowDate.getDay()).asEnd();
    // // �õ��ϸ��µĵ�һ��
    // UFDate beginDate =
    // endDate.getDateBefore(endDate.getDaysMonth() - 1).asBegin();
  }

  private OrderPriceVO[] getBigData() {
    OrderPriceVO[] orderPriceVOs = new OrderPriceVO[1];
    OrderPriceVO orderPriceVO = new OrderPriceVO();
    orderPriceVO.setPk_org("00016010000000000IJT");
    orderPriceVO.setPk_supplier("10026010000000001GUM");
    orderPriceVO.setPk_srcmaterial("10016010000000002HD1");
    orderPriceVO.setCorigcurrencyid("100277100000000001K1");
    orderPriceVOs[0] = orderPriceVO;

    return orderPriceVOs;
  }

  private OrderPriceVO[] getData() {
    OrderPriceVO[] orderPriceVOs = new OrderPriceVO[2];
    OrderPriceVO orderPriceVO = new OrderPriceVO();
    orderPriceVO.setPk_org("0001351000000000064O");
    orderPriceVO.setPk_supplier("1002G610000000000WKP");
    orderPriceVO.setPk_srcmaterial("100235100000000007RT");
    orderPriceVO.setCorigcurrencyid("100277100000000001K1");
    orderPriceVOs[0] = orderPriceVO;

    OrderPriceVO orderPriceVO1 = new OrderPriceVO();
    orderPriceVO1.setPk_org("0001351000000000064O");
    orderPriceVO1.setPk_supplier("1002G610000000000WKP");
    orderPriceVO1.setPk_srcmaterial("100235100000000007S8");
    orderPriceVO1.setCorigcurrencyid("100277100000000001K1");
    orderPriceVOs[1] = orderPriceVO1;

    return orderPriceVOs;
  }

  private OrderPriceVO[] getData4To() {
    OrderPriceVO[] orderPriceVOs = new OrderPriceVO[2];
    OrderPriceVO orderPriceVO = new OrderPriceVO();
    orderPriceVO.setPk_srcmaterial("1001S2100000000000KT");
    orderPriceVO.setPk_psfinanceorg("0001S210000000001W16");
    orderPriceVO.setPk_arrvstoorg("0001S210000000001W16");
    orderPriceVOs[0] = orderPriceVO;

    OrderPriceVO orderPriceVO1 = new OrderPriceVO();
    orderPriceVO1.setPk_srcmaterial("1014Z8100000000002C1");
    orderPriceVO1.setPk_psfinanceorg("0001S21000000000R91K");
    orderPriceVO1.setPk_arrvstoorg("0001S21000000000R91K");
    orderPriceVOs[1] = orderPriceVO1;

    return orderPriceVOs;
  }

  @Override
  protected String getPort() {
    return "80";
  }

  @Override
  protected void setUp() throws Exception {

    super.setUp();
  }

}
