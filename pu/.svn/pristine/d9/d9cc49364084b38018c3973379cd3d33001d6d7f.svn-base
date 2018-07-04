package nc.vo.pu.est.rule;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.JavaType;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>费用暂估非空项检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-6-19 上午09:16:46
 */
public class FeeEstNotNullChkRule {

  public void process(EstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    StringBuilder msg = new StringBuilder();
    for (EstVO vo : vos) {
      this.checkVO(vo, msg);
    }
    if (0 < msg.length()) {
      ExceptionUtils.wrappBusinessException(msg.toString());
    }
  }

  private void checkVO(EstVO vo, StringBuilder msg) {
    FeeEstVO[] fees = vo.getChildrenVO();
    if (ArrayUtils.isEmpty(fees)) {
      return;
    }
    IVOMeta meta = fees[0].getMetaData();
    StringBuilder voerror = new StringBuilder();
    for (int i = 0; i < fees.length; ++i) {
      StringBuilder everyerror = new StringBuilder();
      FeeEstVO fee = fees[i];
      for (String key : EstVOUtil.getFeeEstNotNullKeys()) {
        IAttributeMeta ameta = meta.getAttribute(key);
        // IColumnMeta colmeta = ameta.getColumn();
        Object value = fee.getAttributeValue(key);
        if (ObjectUtil.isEmptyWithTrim(value) && this.isCheck(key, value, fee)) {
          everyerror.append("[");
          // everyerror.append(null == colmeta ? ameta.getName() : colmeta
          // .getLabel());
          everyerror.append(ameta.toString());
          everyerror.append("]");
          continue;
        }
        if (JavaType.UFDouble == ameta.getJavaType()
            && UFDouble.ZERO_DBL.equals(value) && this.isCheck(key, value, fee)) {
          everyerror.append("[");
          // everyerror.append(null == colmeta ? ameta.getName() : colmeta
          // .getLabel());
          everyerror.append(ameta.toString());
          everyerror.append("]");
        }
      }
      if (0 < everyerror.length()) {
        if (0 != voerror.length()) {
          voerror.append(System.getProperty("line.separator"));
        }
        int num = i + 1;
        voerror.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004060_0", "04004060-0226", null, new String[] {
              String.valueOf(num)
            })/* 第{0}条费用项的以下暂估信息为空： */);
        voerror.append(everyerror);
      }
    }
    if (0 < voerror.length()) {
      String billcode = vo.getParentVO().getVbillcode();
      String rowno = vo.getParentVO().getCrowno();
      if (0 < msg.length()) {
        msg.append(System.getProperty("line.separator"));
      }
      msg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
          "04004060-0227", null, new String[] {
            billcode, rowno
          })/* 入库单号：{0}行号：{1}的： */);
      msg.append(System.getProperty("line.separator"));
      msg.append(voerror);
    }
  }

  private boolean isCheck(String key, Object value, FeeEstVO fee) {
    UFDouble taxrate = MathTool.nvl(fee.getNesttaxrate());
    if (FeeEstVO.NESTTAXMNY.equals(key)
        && (null == value || PubAppTool.isEqual(value, UFDouble.ZERO_DBL))) {
      if (UFDouble.ZERO_DBL.equals(taxrate)) {
        return false;
      }
    }
    return true;
  }

}
