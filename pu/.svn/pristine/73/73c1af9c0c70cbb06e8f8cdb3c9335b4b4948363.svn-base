package nc.vo.pu.margin;

import nc.itf.pubapp.margin.IMarginDudgement;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * @since 6.0
 * @version 2011-6-21 ÏÂÎç07:42:40
 * @author Ìï·æÌÎ
 */

public class MuiltyWordMarginJudgement implements IMarginDudgement {

  private static final long serialVersionUID = 1L;

  private String[] destCompareFields;

  private String[] sourceCompareFields;

  public MuiltyWordMarginJudgement(String[] sourceCompareFields,
      String[] destCompareFields) {
    this.destCompareFields = destCompareFields;
    this.sourceCompareFields = sourceCompareFields;
  }

  @Override
  public boolean shouldBeEqualizedMargin(
      CircularlyAccessibleValueObject childVO,
      CircularlyAccessibleValueObject[] brotherVO,
      CircularlyAccessibleValueObject sourceVO) {

    if (childVO == null || sourceVO == null) {
      return false;
    }
    boolean shouldEqualized = true;
    for (int i = 0; i < this.getSourceCompareFields().length; i++) {
      String sourceCompareField = this.getSourceCompareFields()[i];
      String destCompareField = this.getDestCompareFields()[i];
      Object parentValue = sourceVO.getAttributeValue(sourceCompareField);
      Object childValue = childVO.getAttributeValue(destCompareField);
      if (parentValue != null && childValue != null
          && !parentValue.equals(childValue)) {
        shouldEqualized = false;
        break;
      }
    }
    return shouldEqualized;

  }

  protected String[] getDestCompareFields() {
    return this.destCompareFields;
  }

  protected String[] getSourceCompareFields() {
    return this.sourceCompareFields;
  }

}
