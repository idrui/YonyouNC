package nc.vo.pu.m23.rule;

import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * ��鵽���������������Ƿ���ȷ��
 * 
 * @since 6.0
 * @version 2012-9-18 ����10:17:22
 * @author lixyp
 */
public class ArriveNumCheck {

  private IKeyValue keyValue = null;

  private UFDouble oldValue = null;

  public ArriveNumCheck(IKeyValue keyValue, UFDouble oldValue) {
    this.keyValue = keyValue;
    this.oldValue = oldValue;
  }

  /**
   * ��顣
   * 
   * @param row ��ǰ������
   * @param itemKey �༭�ֶ�
   */
  public void check(int row, String itemKey) {
    Boolean bisBack =
        (Boolean) this.keyValue.getHeadValue(ArriveHeaderVO.BISBACK);
    // ��������������������
    UFDouble nnum =
        (UFDouble) this.keyValue.getBodyValue(row, ArriveItemVO.NNUM);
    UFDouble nastnum =
        (UFDouble) this.keyValue.getBodyValue(row, ArriveItemVO.NASTNUM);

    // ;����������;������
    UFDouble nwastnum =
        (UFDouble) this.keyValue.getBodyValue(row, ArriveItemVO.NWASTNUM);
    UFDouble nwastastnum =
        (UFDouble) this.keyValue.getBodyValue(row, ArriveItemVO.NWASTASTNUM);

    // �ƻ��������������ƻ���������
    UFDouble nplannum =
        (UFDouble) this.keyValue.getBodyValue(row, ArriveItemVO.NPLANNUM);
    UFDouble nplanastnum =
        (UFDouble) this.keyValue.getBodyValue(row, ArriveItemVO.NPLANASTNUM);

    // ��Ʒ����������Ʒ����
    UFDouble npresentnum =
        (UFDouble) this.keyValue.getBodyValue(row, ArriveItemVO.NPRESENTNUM);
    UFDouble npresentastnum =
        (UFDouble) this.keyValue.getBodyValue(row, ArriveItemVO.NPRESENTASTNUM);

    if (Boolean.TRUE.equals(bisBack)) {
      if (MathTool.greaterThan(nnum, UFDouble.ZERO_DBL)
          || MathTool.greaterThan(nastnum, UFDouble.ZERO_DBL)
          || MathTool.greaterThan(nwastnum, UFDouble.ZERO_DBL)
          || MathTool.greaterThan(nwastastnum, UFDouble.ZERO_DBL)
          || MathTool.greaterThan(nplannum, UFDouble.ZERO_DBL)
          || MathTool.greaterThan(nplanastnum, UFDouble.ZERO_DBL)
          || MathTool.greaterThan(npresentnum, UFDouble.ZERO_DBL)
          || MathTool.greaterThan(npresentastnum, UFDouble.ZERO_DBL)) {
        this.keyValue.setBodyValue(row, itemKey, this.oldValue);
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004040_0", "04004040-0193")/*
                                                                     * @res
                                                                     * "�˻�����������Ϊ����"
                                                                     */);
      }
    }
    else {
      if (MathTool.lessThan(nnum, UFDouble.ZERO_DBL)
          || MathTool.lessThan(nastnum, UFDouble.ZERO_DBL)
          || MathTool.lessThan(nwastnum, UFDouble.ZERO_DBL)
          || MathTool.lessThan(nwastastnum, UFDouble.ZERO_DBL)
          || MathTool.lessThan(nplannum, UFDouble.ZERO_DBL)
          || MathTool.lessThan(nplanastnum, UFDouble.ZERO_DBL)
          || MathTool.lessThan(npresentnum, UFDouble.ZERO_DBL)
          || MathTool.lessThan(npresentastnum, UFDouble.ZERO_DBL)) {
        this.keyValue.setBodyValue(row, itemKey, this.oldValue);
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004040_0", "04004040-0194")/*
                                                                     * @res
                                                                     * "��������������Ϊ����"
                                                                     */);
      }
    }
  }
}
