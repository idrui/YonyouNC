package nc.bs.pu.m20.rewrite.pu.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUParaValue.ctrltype;
import nc.vo.pu.pub.rule.ToleranceCalcRule;
import nc.vo.pub.lang.UFBoolean;

/**
 * @description
 *              请购单比较容差回写请购订单
 * @scene
 *        请购单回写订单数量
 * @param String tolerFiled 容差字段名
 *          ctrltype tolerPara 容差参数
 *          String wbNumField 回写的字段名
 *          UFBoolean isUserConfirm 用户确认选项
 * @since 6.3
 * @version 2014-10-21 上午10:20:54
 * @author yanxm5
 */

public class PrayOrderWriteBackTolerRule extends ToleranceCalcRule implements
    IRule<PraybillViewVO> {
  private UFBoolean isUserConfirm;

  private String tolerFiled;

  private ctrltype tolerPara;

  private String wbNumField;

  public PrayOrderWriteBackTolerRule(String tolerFiled, ctrltype tolerPara,
      String wbNumField, UFBoolean isUserConfirm) {
    super();
    this.tolerFiled = tolerFiled;
    this.tolerPara = tolerPara;
    this.wbNumField = wbNumField;
    this.isUserConfirm = isUserConfirm;
  }

  @Override
  public String getBidFiled() {
    return PraybillItemVO.PK_PRAYBILL_B;
  }

  @Override
  public String getNumField() {
    return PraybillItemVO.NNUM;
  }

  @Override
  public String getTable() {
    return PUEntity.M20_B_TABLE;
  }

  @Override
  public void process(PraybillViewVO[] vos) {
    List<String> srcBidList = new ArrayList<String>();
    for (PraybillViewVO vo : vos) {
      srcBidList.add(vo.getPk_praybill_b());
    }
    this.toleranceCompare(this.wbNumField,
        srcBidList.toArray(new String[srcBidList.size()]), this.tolerFiled,
        this.tolerPara, this.isUserConfirm);
  }

}
