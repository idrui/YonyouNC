/**
 * $文件说明$
 *
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-19 上午10:00:27
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
 * <li>除了发货之外的在途状态的非空项校验
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-19 上午10:00:27
 */
public class OnwayChkEmpty implements IRule<OrderOnwayVO> {

  // 单据号名
  private String billCode = "";

  // 单据日期名
  private String billDate = "";

  // 在途状态名
  private String statusName = "";

  public OnwayChkEmpty(String billCode, String billDate, String statusName) {
    this.billCode = billCode;
    this.billDate = billDate;
    this.statusName = statusName;
  }

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
        // OnwayValidationTool.chkBusiForSendOut(transtypeVO, itemVO);
        OnwayValidationTool.chkEmptyForOnway(transtypeVO, itemVO,
            this.billCode, this.billDate, this.statusName);
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