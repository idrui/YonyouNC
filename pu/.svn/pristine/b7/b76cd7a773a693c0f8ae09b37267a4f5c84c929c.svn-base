/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-21 下午07:21:19
 */
package nc.vo.pu.est.entity.m4t;

import java.util.HashMap;
import java.util.Map;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单存放暂估信息的表头VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-10-21 下午07:21:19
 */
public class InitialBillGoodsEstVO extends GoodsEstVO {

  private static final long serialVersionUID = 3697691054683515736L;

  // 字段映射map
  private Map<String, String> attNameMap = null;

  /**
   * 字段不同，需要映射
   * 
   * @return
   */
  public Map<String, String> getAttNameMap() {
    if (this.attNameMap == null) {
      this.attNameMap = new HashMap<String, String>();
      // 累计结算数量
      this.attNameMap.put(GoodsEstVO.NACCUMSETTLENUM,
          InitialEstItemVO.NACCSETTLENUM);
      // 暂估应付标记
      this.attNameMap.put(GoodsEstVO.FTOAPFLAG, InitialEstItemVO.BESTIMATEAP);
      // 入库行
      this.attNameMap.put(GoodsEstVO.PK_STOCKPS_B,
          InitialEstItemVO.PK_INITIALEST_B);
      // TODO 赵玉行 继续补充其它不同的字段
    }
    return this.attNameMap;
  }

  @Override
  public Object getAttributeValue(String name) {
    String realName = this.getAttNameMap().get(name);
    realName = StringUtil.isEmptyWithTrim(realName) ? name : realName;
    // 注意：期初的暂估应付标记为boolean，需转换为枚举供后续用
    if (InitialEstItemVO.BESTIMATEAP.equals(realName)) {
      return UFBoolean.TRUE.equals(super.getAttributeValue(realName)) ? EnumToAPFlag.EstimateToAP
          .toInteger() : EnumToAPFlag.NotToAP.toInteger();
    }
    return super.getAttributeValue(realName);
  }

  @Override
  public String getBillType() {
    return POBillType.InitEstimate.getCode();
  }

  @Override
  public IDataViewMeta getMetaData() {
    // 赵玉行 这里暂时使用表头表体的全部信息，以后根据需求查询只关注的暂估信息
    return DataViewMetaFactory.getInstance()
        .getBillViewMeta(InitialEstVO.class);
  }

  @Override
  public void setAttributeValue(String name, Object value) {
    String realName = this.getAttNameMap().get(name);
    realName = StringUtil.isEmptyWithTrim(realName) ? name : realName;
    super.setAttributeValue(realName, value);
  }

}
