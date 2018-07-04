package nc.vo.pu.margin;

import java.util.Map;

import nc.vo.pu.pub.enumeration.PuAttrNameEnum;

/**
 * 默认尾差处理条件
 * 
 * @since 6.0
 * @version 2012-4-22 下午9:17:12
 * @author zhaoyha
 */
public class DefaultPuMarginCondition implements IPuMarginCondition {
  private Map<String, String> curSrcCondJudgeField;

  private Map<String, String> curSrcMarginField;

  private String srcNumFieldName;

  /**
   * 构造一个默认的尾差处理条件
   * 
   * @param curSrcCondJudgeField 用作条件判断字段key映射，当前单据字段key=>来源（源头）单据字段key
   * @param curSrcMarginField 倒挤的字段映射：当前单据字段key=>来源（源头）单据字段key
   * @param srcNumFieldName 来源（源头）单据的主数量字段（依据来判断是否已经是最后一次）
   */
  public DefaultPuMarginCondition(Map<String, String> curSrcCondJudgeField,
      Map<String, String> curSrcMarginField, String srcNumFieldName) {
    super();
    this.curSrcCondJudgeField = curSrcCondJudgeField;
    this.curSrcMarginField = curSrcMarginField;
    this.srcNumFieldName = srcNumFieldName;
  }

  @Override
  public String curNumFieldName() {
    return PuAttrNameEnum.nnum.name();
  }

  @Override
  public Map<String, String> curSrcCondJudgeField() {
    return this.curSrcCondJudgeField;
  }

  @Override
  public Map<String, String> curSrcMarginField() {
    return this.curSrcMarginField;
  }

  @Override
  public String srcNumFieldName() {
    return this.srcNumFieldName;
  }

}
