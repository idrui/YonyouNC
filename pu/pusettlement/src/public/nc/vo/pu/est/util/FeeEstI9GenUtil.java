/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-19 下午04:36:42
 */
package nc.vo.pu.est.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.ia.mi9.entity.I9ItemVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>费用暂估生成I9的工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-19 下午04:36:42
 */
public class FeeEstI9GenUtil {
  /** 将I9的来源恢复为入库单(消耗汇总)费用暂估分摊明细中记录的传IA的标识PK **/
  public static void restoreI9SrcBID(I9ItemVO[] i9items, FeeEstDistVO[] fdVos) {
    Map<String, FeeEstDistVO> fdMap = CirVOUtil.createKeyVOMap(fdVos);
    for (I9ItemVO item : i9items) {
      String pk_fd = item.getCsrcbid();
      FeeEstI9GenUtil.checkI9SrcInfo(fdMap, pk_fd);
      FeeEstDistVO fdVo = fdMap.get(pk_fd);
      if (null == fdVo) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0126")/*
                                                                     * @res
                                                                     * "生成I9单据时，未找到对应的费用分摊数据!"
                                                                     */);
        continue;
      }
      item.setCsrcbid(fdVo.getPk_iasrc_b());
    }
  }

  /** VO交换完成后设置I9的调整对象 **/
  public static void setAdjustInfo(I9ItemVO[] i9items, FeeEstDistVO[] fdVos) {
    Map<String, FeeEstDistVO> fdMap = CirVOUtil.createKeyVOMap(fdVos);
    for (I9ItemVO itemVO : i9items) {
      String pk_fd = itemVO.getCsrcbid();
      FeeEstI9GenUtil.checkI9SrcInfo(fdMap, pk_fd);
      FeeEstDistVO fdVo = fdMap.get(pk_fd);
      if (null == fdVo) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0126")/*
                                                                     * @res
                                                                     * "生成I9单据时，未找到对应的费用分摊数据!"
                                                                     */);
        continue;
      }
      itemVO.setCadjustbillid(fdVo.getPk_distbybill());
      itemVO.setCadjustbillitemid(fdVo.getPk_distbybill_b());
      itemVO.setNadjustnum(fdVo.getNdistbynum());
    }
  }

  /**
   * 设置I9的成本要素，成本要素号和金额
   * 
   * @param i9item
   * @param cfnum
   * @param mny
   */
  public static void setCostFactor(I9ItemVO i9item, int cfnum, UFDouble mny) {
    if (1 == cfnum) {
      i9item.setNfactor1(mny);
    }
    if (2 == cfnum) {
      i9item.setNfactor2(mny);
    }
    if (3 == cfnum) {
      i9item.setNfactor3(mny);
    }
    if (4 == cfnum) {
      i9item.setNfactor4(mny);
    }
    if (5 == cfnum) {
      i9item.setNfactor5(mny);
    }
    if (6 == cfnum) {
      i9item.setNfactor6(mny);
    }
    if (7 == cfnum) {
      i9item.setNfactor7(mny);
    }
    if (8 == cfnum) {
      i9item.setNfactor8(mny);
    }
  }

  /**
   * 设置I9的成本要素，根据费用暂估和暂估分摊VO
   * 
   * @param items
   * @param fees
   * @param fdVos
   */
  public static void setCostFactor(I9ItemVO[] items, FeeEstVO[] fees,
      FeeEstDistVO[] fdVos) {
    Map<String, FeeEstVO> feeMap = CirVOUtil.createKeyVOMap(fees);
    Map<String, FeeEstDistVO> fdMap = CirVOUtil.createKeyVOMap(fdVos);
    for (I9ItemVO i9item : items) {
      // 实际为暂估分摊明细ID
      String pk_fd = i9item.getCsrcbid();
      FeeEstDistVO fdvo = fdMap.get(pk_fd);
      String pk_fee = fdvo.getPk_stockps_fee();
      FeeEstVO feevo = feeMap.get(pk_fee);
      int cfnum = feevo.getCostfacotorNum();
      UFDouble mny = i9item.getNmny();
      FeeEstI9GenUtil.setCostFactor(i9item, cfnum, mny);
    }
  }

  /**
   * 将费用分摊VO拆分费用VO
   * 
   * @param fdVos 费用分摊VO
   * @param feeVos 相应的费用暂估VO
   * @return 分单后的FeeEstVO数组，保存了对应的FeeEstDistVO的主键、分摊金额
   **/
  public static FeeEstVO[] splitFDVOToFeeVO(FeeEstDistVO[] fdVos,
      FeeEstVO[] feeVos) {
    Map<String, ArrayList<FeeEstDistVO>> feeFDMap =
        CirVOUtil.getFieldVOList(fdVos, FeeEstDistVO.PK_STOCKPS_FEE);
    List<FeeEstVO> newFees = new ArrayList<FeeEstVO>(fdVos.length);
    for (FeeEstVO fee : feeVos) {
      String pk_fee = fee.getPk_stockps_fee();
      List<FeeEstDistVO> feeFDVos = feeFDMap.get(pk_fee);
      for (FeeEstDistVO fdVo : feeFDVos) {
        FeeEstVO cfee = (FeeEstVO) fee.clone();
        cfee.setPk_stockps_fd(fdVo.getPk_stockps_fd());
        // 赵玉行 这里先不进行联动计算，因I9只需要金额，不关心其它，如需要再加联动计算
        cfee.setNestmny(fdVo.getNdistmny());
        cfee.setNcalcostmny(fdVo.getNdistmny());// 计成本金额，I9后续对照用此金额
        newFees.add(cfee);
      }
    }
    return new ListToArrayTool<FeeEstVO>().convertToArray(newFees);
  }

  private static void checkI9SrcInfo(Map<String, FeeEstDistVO> fdMap,
      String pk_fd) {
    if (StringUtil.isEmptyWithTrim(pk_fd) || null == fdMap.get(pk_fd)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0127")/*
                                                                   * @res
                                                                   * "暂估单据到I9的数据交换重要数据[来源单据明细]丢失，请补充!"
                                                                   */);
    }
  }
}
