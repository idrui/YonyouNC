/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-5 下午08:42:35
 */
package nc.bs.pu.m20.pf.function;

import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单合计本币价税合计
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-5 下午08:42:35
 */
public class PrayTaxMnyAmount {
  /**
   * 功能描述:审批流业务函数(获取请购单合计本币价税合计)<br>
   * 如果VO为新增则用VO中数据计算<br>
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   *          请购单VO
   * @return <p>
   *         合计本币价税合计
   * @since 6.0
   * @author GGR
   * @time 2010-7-5 下午08:43:19
   */
  public UFDouble getTaxMnyAmount(AggregatedValueObject vo) {
    if ((vo == null) || (vo.getParentVO() == null)
        || (vo.getChildrenVO() == null) || (vo.getChildrenVO().length <= 0)) {
      return new UFDouble(0.0);
    }

    String strHeadPK = ((PraybillVO) vo).getHVO().getPk_praybill();// 获取头表主键
    // 头表主键为空，说明是新增VO,从VO中获取数据计算合计本币价税合计
    if ((strHeadPK == null) || strHeadPK.trim().equals("")) {
      return FunctionUtil.getSum((PraybillVO) vo, PraybillItemVO.NTAXMNY);
    }

    return FunctionUtil.getSumFromDB(strHeadPK, PraybillItemVO.NTAXMNY);

  }
}
