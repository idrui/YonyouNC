package nc.test.pu.pub.opennode;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pu.uif2.PUUif2Utils;
import nc.vo.querytemplate.queryscheme.SimpleQuerySchemeVO;

/**
 * 使用查询方案打开节点的测试
 * 
 * @since 6.0
 * @version 2011-12-29 下午2:14:39
 * @author zhaoyha
 */

public class OpennodeByQueryschemeTest {

  /**
   * 测试以查询方案打开修订节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpen20close() {
    String funcode = "40040406";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1002Z81000000000D5RO"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);

  }

  /**
   * 测试以查询方案打开对方确认节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpen20confirm() {
    String funcode = "40040602";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("11111002Z81000000000DAEB"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);

  }

  /**
   * 测试以查询方案打开维护节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpen20edit() {
    String funcode = "40040800";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1002Z81000000000CK6C"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);
  }

  /**
   * 测试以查询方案打开装货节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpen20load() {
    String funcode = "40040606";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1002Z81000000000DBXZ"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);

  }

  /**
   * 测试以查询方案打开出关节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpen20outcustom() {
    String funcode = "40040610";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1002Z81000000000DBYE"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);

  }

  /**
   * 测试以查询方案打开输出节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpen20output() {
    String funcode = "40040600";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1002Z81000000000D832"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);

  }

  /**
   * 测试以查询方案打开补货维护节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpen20replenish() {
    String funcode = "40040404";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1002Z81000000000CXAU"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);

  }

  /**
   * 测试以查询方案打开报关节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpen20reportcustom() {
    String funcode = "40040608";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1002Z81000000000DBY7"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);

  }

  /**
   * 测试以查询方案打开修订节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpen20revise() {
    String funcode = "40040202";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1004Z810000000007E7E"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);

  }

  /**
   * 测试以查询方案打开发货节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpen20sendout() {
    String funcode = "40040604";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1002Z81000000000DAET"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);

  }

  /**
   * 测试以查询方案打开付款计划节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpen21payplan() {
    String funcode = "40040407";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1004Z810000000007PSG"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);
  }

  /**
   * 测试以查询方案打开期初暂估维护节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpen4t() {
    String funcode = "40041600";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1002Z81000000000CK5X"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);
  }

  /**
   * 测试以查询方案打开到货单检验节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpenArrivalCheck() {
    String funcode = "40040802";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1002Z81000000000D5RM"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);

  }

  /**
   * 测试以查询方案打开价格结算单维护节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpenPricestl() {
    String funcode = "400418";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1002Z81000000000CK61"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);
  }

  /**
   * 测试以查询方案打开采购订单维护节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpenPuorder() {
    String funcode = "40040400";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1001Z810000000000NXB"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);
  }

  /**
   * 测试以查询方案打开采购订单关闭节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpenPuOrderClose() {
    String funcode = "40040406";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1002Z81000000000CXAU"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);

  }

  /**
   * 测试以查询方案打开修订节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpenPuOrderRevise() {
    String funcode = "40040402";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1002Z81000000000DBYX"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);

  }

  /**
   * 测试以查询方案打开结算单维护节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpenSettleBill() {
    String funcode = "40041404";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1002Z81000000000CK5U"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);
  }

  /**
   * 测试以查询方案打开物资需求申请维护节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testOpenStorereq() {
    String funcode = "40040000";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1002Z81000000000CK69"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);
  }

  /**
   * 测试以查询方案打开到货单维护节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testPuArrival() {
    String funcode = "40040800";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1002Z81000000000CK6C"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);
  }

  /**
   * 测试以查询方案打开采购发票维护节点
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   */
  public void testPuInvoice() {
    String funcode = "40041000";
    FuncletInitData fidata = new FuncletInitData();
    fidata.setInitType(0);
    fidata.setInitData(this.getSimpleQuerySchemeVO("1001Z810000000000NXL"));
    PUUif2Utils.openFuncNodeDlg(null, funcode, fidata);
  }

  private SimpleQuerySchemeVO getSimpleQuerySchemeVO(String pk_queryscheme) {
    SimpleQuerySchemeVO vo = new SimpleQuerySchemeVO();
    vo.setPrimaryKey(pk_queryscheme);
    vo.setComplete(true);
    return vo;
  }

}
