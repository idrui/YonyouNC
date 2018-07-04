package nc.vo.pu.margin;

import java.util.Map;

/**
 * 采购用于尾差处理(倒挤)的条件获得接口
 * 
 * @since 6.0
 * @version 2012-4-20 下午11:00:20
 * @author zhaoyha
 */
public interface IPuMarginCondition {
  /**
   * 当前单据的主数量字段（依据来判断是否已经是最后一次）
   * 
   * @return
   */
  String curNumFieldName();

  /**
   * 用作条件判断字段key映射，当前单据字段key=>来源（源头）单据字段key。
   * <p>
   * 两种单据的这些值有一个不等时不做倒挤。
   * 
   * @return
   */
  Map<String, String> curSrcCondJudgeField();

  /**
   * 倒挤的字段映射：当前单据字段key=>来源（源头）单据字段key
   * 
   * @return
   */
  Map<String, String> curSrcMarginField();

  /**
   * 来源（源头）单据的主数量字段（依据来判断是否已经是最后一次）
   * 
   * @return
   */
  String srcNumFieldName();

  // 默认实现类构造函数(curItemVOs、srcItemVOs{为null或length==0则重查}、curSiblingItemVOs{为空则重查，否则不查}）
  // 由默认实现类负责查询，生成Map结构及当前VO与兄弟VO的合并在marginprocess中进行
}
