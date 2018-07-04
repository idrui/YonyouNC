
package nc.vo.pu.m4t.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 发票来源信息检查:检查来源信息是否完整：有来源单据类型必须有来源单据号，
 * id,bid,行号，交易类型！
 * @scene
 * 期初暂估单保存
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-31 上午10:06:08
 * @author guoyk
 */

public class InitialEstSourceInfoChkRule implements IRule<InitialEstVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(InitialEstVO[] vos) {
    String[] keys =
        new String[] {
          InitialEstItemVO.VSOURCETRANTYPE, InitialEstItemVO.CSOURCEBID,
          InitialEstItemVO.CSOURCEID, InitialEstItemVO.VSOURCECODE,
          InitialEstItemVO.VSOURCEROWNO, InitialEstItemVO.PK_ORDER,
          InitialEstItemVO.PK_ORDER_B, InitialEstItemVO.VORDERCODE,
          InitialEstItemVO.VORDERTRANTYPE
        };

    for (InitialEstVO vo : vos) {
      this.checkSourceInfo(vo, keys);
    }
  }

  /**
   * 方法功能描述：检查来源信息是否完整
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param keys
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 上午10:18:54
   */
  private void checkSourceInfo(InitialEstVO vo, String[] keys) {
    StringBuilder sb = new StringBuilder();

    for (InitialEstItemVO itemVO : vo.getItems()) {
      String csourcetypecode = itemVO.getCsourcetypecode();
      if (StringUtil.isEmptyWithTrim(csourcetypecode)) {
        continue;
      }

      for (String key : keys) {
        Object obj = itemVO.getAttributeValue(key);
        if (ObjectUtil.isEmptyWithTrim(obj)) {
          sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0", "04004060-0234", null, new String[]{itemVO.getCrowno()})/*第{0}行来源信息不完整，请检查！*/);
        }
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }
}
