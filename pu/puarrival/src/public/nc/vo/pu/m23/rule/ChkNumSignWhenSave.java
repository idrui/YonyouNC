package nc.vo.pu.m23.rule;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>检查数量符号的正确性,本类主要完成以下功能：</b>
 * <ul>
 * <li>如果是正到货单，“到货数量”、“到货主数量”必须为正数
 * <li>如果是退货单，“到货数量”、“到货主数量”必须为负数
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-25 下午12:40:45
 */
public class ChkNumSignWhenSave {

  public void chkNumSign(ArriveVO vo) {
    boolean isBack = vo.getHVO().getBisback().booleanValue();
    ArriveItemVO[] itemArray = vo.getBVO();
    UFDouble nassitnum, nnum;
    String rowno;
    for (int i = 0, len = itemArray.length; i < len; i++) {
      // 到货数量、到货主数量
      nassitnum = itemArray[i].getNastnum();
      nnum = itemArray[i].getNnum();
      rowno = itemArray[i].getCrowno();

      boolean isError = false;
      if (isBack) {
        // 如果是退货单，“到货数量”、“到货主数量”必须为负数
        if (nassitnum != null && nassitnum.doubleValue() >= 0) {
          isError = true;
        }
        if (nnum != null && nnum.doubleValue() >= 0) {
          isError = true;
        }

        if (isError) {
          String message =
              NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                  "04004040-0175", null, new String[] {
                    rowno
                  })/* 第{0}行的退货单数量必须小于0! */;
          ExceptionUtils.wrappBusinessException(message);
        }
      }
      else {
        // 如果是正到货单，“到货数量”、“到货主数量”必须为正数
        if (nassitnum != null && nassitnum.doubleValue() <= 0) {
          isError = true;
        }
        if (nnum != null && nnum.doubleValue() <= 0) {
          isError = true;
        }

        if (isError) {
          String message =
              NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                  "04004040-0176", null, new String[] {
                    rowno
                  })/* 第{0}行的到货单数量必须大于0! */;
          ExceptionUtils.wrappBusinessException(message);
        }
      }
    }
  }
}
