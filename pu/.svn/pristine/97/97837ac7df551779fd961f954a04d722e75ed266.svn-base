package nc.pubitf.pu.m25transtype;

import java.util.HashMap;

import nc.vo.pu.m25trantype.entity.InvcTranTypeVO;
import nc.vo.pub.BusinessException;

/**
 * 采购发票交易类型查询服务
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据集团和交易类型编码查询采购发票交易类型设置。
 * </ul>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2009-12-22 下午04:33:56
 */
public interface IQueryTranstype {
  /**
   * 方法功能描述：根据集团和交易类型编码查询采购发票交易类型设置。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_group 集团
   * @param transTypes 交易类型
   * @param sAttriName 需要查询的属性
   * @return HashMap<transType, InvcTranTypeVO>
   * @throws BusinessException
   *           <p>
   * @author GGR
   * @time 2009-12-22 下午04:33:56
   */
  HashMap<String, InvcTranTypeVO> queryAttrByTypes(String pk_group,
      String[] transTypes, String[] sAttriName) throws BusinessException;
}
