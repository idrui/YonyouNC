/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 下午01:19:47
 */
package nc.pubitf.pu.m21.ct.mz2;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>合同回写参数
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-6 下午01:19:47
 */
public interface IOrderWriteBackParaForZ2 {
  /**
   * 方法功能描述：合同表体行id
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-6 下午01:24:52
   */
  public String getCntBID();

  /**
   * 方法功能描述：合同号
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-7 下午07:33:39
   */
  public String getCntCode();

  /**
   * 方法功能描述：合同表头id
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-6 下午01:24:29
   */
  public String getCntHID();

  /**
   * 方法功能描述：原币币种
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-7 上午10:45:55
   */
  public String getCorigcurrencyid();

  /**
   * 方法功能描述：物料
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-7 下午02:13:34
   */
  public String getPkMaterial();

  /**
   * 方法功能描述：供应商
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-7 下午01:34:43
   */
  public String getPkSupplier();

  /**
   * 方法功能描述：上游单据子子表行id
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-6 下午02:31:02
   */
  public String getSourceBBID();

  /**
   * 方法功能描述：上游单据表体行id
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-6 下午01:23:23
   */
  public String getSourceBID();

  /**
   * 方法功能描述：上游单据表头id
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-6 下午01:23:07
   */
  public String getSourceHID();

  /**
   * 方法功能描述：是否强制保存
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-6 下午01:26:11
   */
  public boolean isSaveForced();

  /**
   * 方法功能描述：是否委外
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-6 下午01:25:32
   */
  public boolean isSC();

}
