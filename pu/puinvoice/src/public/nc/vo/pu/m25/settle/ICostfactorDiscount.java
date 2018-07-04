package nc.vo.pu.m25.settle;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>结算时用的成本要素、折扣接口：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author wangyf
 * @time 2010-3-25 上午11:59:06
 */
public interface ICostfactorDiscount {
  /**
   * 将一个金额加至累计结算数量
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param dValue
   *          <p>
   * @author wangyf
   * @time 2010-3-26 下午01:43:25
   */
  public void addtoCurrenttotalsettlemoney(UFDouble dValue);

  public UFDouble getNadjustmny();

  /**
   * 得到分摊金额
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2010-3-26 下午01:06:37
   */
  public UFDouble getNallotmoney();

  /**
   * 得到分摊数量
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2010-3-26 下午01:06:37
   */
  public UFDouble getNallotnum();

  public UFDouble getNcostfactor1();

  public UFDouble getNcostfactor2();

  public UFDouble getNcostfactor3();

  public UFDouble getNcostfactor4();

  public UFDouble getNcostfactor5();

  public UFDouble getNcostfactor6();

  public UFDouble getNcostfactor7();

  public UFDouble getNcostfactor8();

  public UFDouble getNdiscount();

  /**
   * 得到物料SrcID
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2010-3-26 下午01:09:22
   */
  public String getPk_srcmaterial();

  public void setNadjustmny(UFDouble nadjustmny);

  public void setNcostfactor1(UFDouble ncostfactor1);

  public void setNcostfactor2(UFDouble ncostfactor2);

  public void setNcostfactor3(UFDouble ncostfactor3);

  public void setNcostfactor4(UFDouble ncostfactor4);

  public void setNcostfactor5(UFDouble ncostfactor5);

  public void setNcostfactor6(UFDouble ncostfactor6);

  public void setNcostfactor7(UFDouble ncostfactor7);

  public void setNcostfactor8(UFDouble ncostfactor8);

  public void setNdiscount(UFDouble ndiscount);

}
