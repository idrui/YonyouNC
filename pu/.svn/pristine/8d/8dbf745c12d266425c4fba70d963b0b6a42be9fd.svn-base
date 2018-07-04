package nc.pubitf.pu.m21transtype;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;

/**
 * 采购订单交易类型查询服务
 * <ul>
 * <li>重构为走前台缓存的实现，使用UAP的AOP技术，可参见UPM的配置 by zhaoyha at 2011.12
 * </ul>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2009-7-6 上午11:02:58
 */
public interface IPoTransTypeQuery {

  /**
   * 方法功能描述：获得过滤直运销售转采购业务数据的SQL片段
   * <p>
   * <b>使用示例：</b><br>
   * 要获得过滤直运销售转类型的采购入库单的SQL片段，查询采购入库单时的表体别名为b，
   * 采购入库单表体记录采购订单交易类型的字段名为cfirsttranstype，则<br>
   * IPoTransTypeQuery query =
   * NCLocator.getInstance().lookup(IPoTransTypeQuery.class);<br>
   * String whereSql = query.getDirectTransWhereString("b",
   * MetaNameConst.CFIRSTTRANSTYPE);<br>
   * 其中whereSql将会返回类型如下的字符串： <br>
   * inner join po_potrantype on po_potrantype.pk_group = b.pk_group and
   * po_potrantype.vtrantypecode = b.cfirsttranstype and po_potrantype.bdirect =
   * 'Y'
   * <p>
   * <b>参数说明</b>
   * 
   * @param tableName
   *          表名（或者别名）
   * @param joinField
   *          关联字段
   * @return SQL片段
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-10-25 上午10:37:25
   */
  public String getDirectTransWhereString(String tableName, String joinField);

  /**
   * 方法功能描述：根据集团和交易类型编码查询采购订单交易类型设置。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param transTypes
   *          交易类型
   * @param sAttriName 需要查询的属性，如果为null则查出所有属性
   * @return HashMap<transType, PoTransTypeVO>
   * @throws BusinessException
   *           <p>
   * @author wangyf
   * @time 2009-7-6 上午11:02:58
   */
  public HashMap<String, PoTransTypeVO> queryAttrByTypes(String[] transTypes,
      String[] sAttriName) throws BusinessException;

  /**
   * 根据主键查询订单交易类型属性
   * 
   * @param ids 主键
   * @return key：主键 value：VO
   * @throws BusinessException
   */
  Map<String, PoTransTypeVO> queryAttrByIDs(String[] ids)
      throws BusinessException;
}
