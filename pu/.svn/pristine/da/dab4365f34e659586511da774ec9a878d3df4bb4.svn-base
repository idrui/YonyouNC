/**
 * $文件说明$
 *
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-19 上午08:58:58
 */
package nc.impl.pu.m21.onway.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21.entity.OrderOnwayVO;
import nc.vo.pu.m21.rule.OnwayValidationTool;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.AssertUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-19 上午08:58:58
 */
public class SendOutChkEmpty implements IRule<OrderOnwayVO> {

  /**
   * 父类方法重写
   *
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderOnwayVO[] vos) {

    AssertUtils.assertValue(vos != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0170")/*@res "在途状态规则校验时传入的vo不应该为空."*/);

    if (vos == null) {
      return;
    }

    // 取得交易类型VO
    String vtranType = vos[0].getHVO().getVtrantypecode();
    Map<String, PoTransTypeVO> transMap =
        OnwayValidationTool.getTransTypeVO(new String[] {
          vtranType
        });
    PoTransTypeVO transtypeVO = transMap.get(vtranType);

    try {
      for (OrderOnwayVO vo : vos) {
        OrderOnwayItemVO[] itemVO = vo.getBVO();
        OnwayValidationTool.chkEmptyForSendOut(transtypeVO, itemVO);
      }
    }
    catch (Exception e) {
      List<ValidationFailure> failMsg = new ArrayList<ValidationFailure>();
      ValidationFailure vf = new ValidationFailure();
      vf.setMessage(e.getMessage());
      failMsg.add(vf);
      ValidationException ex = new ValidationException(failMsg);
      ExceptionUtils.wrappException(ex);
    }
  }
}