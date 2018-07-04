/**
 *
 */
package nc.vo.pu.m25.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 * 数值型属性检查
 * @scene
 * 保存的BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:23:58
 * @author zhangshqb
 */
public class InvoiceNumValueChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    StringBuffer errorBuffer = new StringBuffer();
    for (InvoiceVO vo : vos) {
      InvoiceHeaderVO headerVO = vo.getParentVO();
      if (null == headerVO) {
        errorBuffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004050_0", "04004050-0029")/* @res "表头不能为空\r\n" */);
        continue;
      }
      StringBuffer headerErrors = this.checkHeaderVO(headerVO);
      if (headerErrors.length() > 0) {
        errorBuffer.append(headerErrors);
        errorBuffer.append("\r\n");
      }
      InvoiceItemVO[] itemVOs = vo.getChildrenVO();
      for (InvoiceItemVO itemVO : itemVOs) {
        if (null == itemVO) {
          errorBuffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4004050_0", "04004050-0078")/* @res "表体不能为空\r\n" */);
          continue;
        }
        // 表体未改变或者已删除的状态,不做处理.
        if (itemVO.getStatus() == VOStatus.UNCHANGED
            || itemVO.getStatus() == VOStatus.DELETED) {
          continue;
        }
        StringBuffer itemErrors = this.checkItemVO(headerVO, itemVO);
        if (itemErrors.length() > 0) {
          errorBuffer.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4004050_0", "04004050-0113", null, new String[] {
                itemVO.getCrowno()
              })/* 第{0}行:\n */);
          errorBuffer.append(itemErrors);
          errorBuffer.append("\r\n");
        }
      }

    }

    if (errorBuffer.length() > 0) {
      ExceptionUtils.wrappBusinessException(errorBuffer.toString());
    }
  }

  /**
   * 检测表头的某些字段
   * 
   * @author xiebo
   * @time 2010-1-27 上午09:40:24
   * @param
   * @return
   * @throws
   */
  private StringBuffer checkHeaderVO(InvoiceHeaderVO headerVO) {

    StringBuffer errorBuffer = new StringBuffer();
    if (this.isNullUFDouble(headerVO.getNexchangerate())) {
      errorBuffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004050_0", "04004050-0079")/* @res "汇率不能为空或0;" */);
    }
    // tianft 全局集团的不加，可能没有值，否则保存不了
    // if (isNullUFDouble(headerVO.getNgroupexchgrate())) {
    // errorBuffer.append("集团本位币汇率不能为空或0;");
    // }
    // if (isNullUFDouble(headerVO.getNglobalexchgrate())) {
    // errorBuffer.append("全局本位币汇率不能为空或0;");
    // }
    if (MathTool.compareTo(headerVO.getNtaxrateh(), UFDouble.ZERO_DBL) < 0.0) {
      errorBuffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004050_0", "04004050-0080")/* @res "税率不能为负;" */);
    }
    return errorBuffer;
  }

  /**
   * 检测表体的某些字段
   * 
   * @author xiebo
   * @param headerVO
   * @time 2010-1-27 上午09:40:03
   * @param
   * @return
   * @throws
   */
  private StringBuffer checkItemVO(InvoiceHeaderVO headerVO,
      InvoiceItemVO itemVO) {
    StringBuffer errorBuffer = new StringBuffer();
    boolean virtual = ValueUtils.getBoolean(headerVO.getBvirtual());
    if (!virtual && this.isNullUFDouble(itemVO.getNorigmny())) {
      errorBuffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004050_0", "04004050-0081")/* @res "无税金额不能为空或0;" */);
    }
    if (!virtual && this.isNullUFDouble(itemVO.getNorigtaxmny())) {
      errorBuffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004050_0", "04004050-0082")/* @res "税价合计不能为空或0;" */);
    }
    if (!virtual && this.isNullUFDouble(itemVO.getNmny())) {
      errorBuffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004050_0", "04004050-0083")/* @res "本币无税金额不能为空或0;" */);
    }
    if (!virtual && this.isNullUFDouble(itemVO.getNtaxmny())) {
      errorBuffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004050_0", "04004050-0084")/* @res "本币价税合计不能为空或0;" */);
    }

    // 单价验证
    String[][] priceFields =
        new String[][] {
          {
            InvoiceItemVO.NASTORIGPRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
                "04004050-0090")
          /* @res "无税单价不能为负;" */
          },
          {
            InvoiceItemVO.NASTORIGTAXPRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
                "04004050-0091")
          /* @res "含税单价不能为负;" */
          },
          {
            InvoiceItemVO.NORIGPRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
                "04004050-0092")
          /* @res "主无税单价不能为负;" */
          },
          {
            InvoiceItemVO.NORIGTAXPRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
                "04004050-0093")
          /* @res "主含税单价不能为负;" */
          },
          {
            InvoiceItemVO.NASTPRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
                "04004050-0094")
          /* @res "本币无税单价不能为负;" */
          },
          {
            InvoiceItemVO.NASTTAXPRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
                "04004050-0095")
          /* @res "本币含税单价不能为负;" */
          },
          {
            InvoiceItemVO.NPRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
                "04004050-0096")
          /* @res "主本币无税单价不能为负;" */
          },
          {
            InvoiceItemVO.NTAXPRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
                "04004050-0097")
          /* @res "主本币含税单价不能为负;" */
          }
        };

    for (int i = 0; i < priceFields.length; i++) {
      if (MathTool.compareTo(
          ValueUtils.getUFDouble(itemVO.getAttributeValue(priceFields[i][0])),
          UFDouble.ZERO_DBL) < 0) {
        errorBuffer.append(priceFields[i][1]);
      }
    }

    return errorBuffer;
  }

  private boolean isNullUFDouble(UFDouble ufDouble) {
    return null == ufDouble || MathTool.isZero(ufDouble);
  }

}
