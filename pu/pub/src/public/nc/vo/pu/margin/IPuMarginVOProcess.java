package nc.vo.pu.margin;

import java.util.Map;

import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * 用于尾差处理(倒挤)的上下游/兄弟VO加工接口
 * <ul>
 * <li>为了适配pubapp的代码，需要生成map结构
 * <li>有场景需要对要进行尾差处理的上下游VO进行加工处理，例如:
 * <ul>
 * <li>上下游单据要进行红蓝数量倒挤（采购订单补货与退货/库单）
 * <li>下游会根据上游孙表倒挤（到货单与采购订单到货计划倒挤）
 * </ul>
 * </ul>
 * 
 * @since 6.0
 * @version 2012-4-20 下午11:00:20
 * @author zhaoyha
 */
public interface IPuMarginVOProcess {
  /**
   * 得到当前单据与其兄弟单据（与其来源相同单据）映射
   * <p>
   * 
   * @param curVos 当前要倒挤的行VO数组
   * @param siblingVos 当前行VO数组，对应的兄弟VO数组
   * @return Map
   *         key=nc.vo.pubapp.margin.MarginContextFactory.uniqueKey,
   *         这个key同时在curVO和siblingVO中用于二者匹配<br>
   *         value=对应的兄弟VO数组
   */
  Map<String, CircularlyAccessibleValueObject[]> procCurSiblingVO(
      CircularlyAccessibleValueObject[] curVos,
      CircularlyAccessibleValueObject[] siblingVos);

  /**
   * 生成当前单据的来源（源头）单据（要与其倒挤的单据）MAP
   * 
   * @param curVos 当前要倒挤的行VO数组
   * @param srcVos 当前要倒挤VO的来源VO数组
   * @return Map
   *         key=nc.vo.pubapp.margin.MarginContextFactory.uniqueKey，
   *         这个key同时在curVO和srcVO中用于二者匹配<br>
   *         value=来源VO
   */
  Map<String, CircularlyAccessibleValueObject> procSrcVO(
      CircularlyAccessibleValueObject[] curVos,
      CircularlyAccessibleValueObject[] srcVos);

}
