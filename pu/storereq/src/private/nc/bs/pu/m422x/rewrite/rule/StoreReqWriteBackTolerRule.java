package nc.bs.pu.m422x.rewrite.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUParaValue.ctrltype;
import nc.vo.pu.pub.rule.ToleranceCalcRule;
import nc.vo.pub.lang.UFBoolean;
/**
 * 
 * @description
 * 物资需求申请单回写容差控制计算规则
 * @scene
 * 物资需求申请单回写容差控制
 * @param
 * 
 *
 * @since 6.0
 * @version 2011-6-3 上午09:08:45
 * @author wuxla
 */
public class StoreReqWriteBackTolerRule extends ToleranceCalcRule implements
    IRule<StoreReqAppViewVO> {
  private UFBoolean isUserConfirm;

  private String tolerFiled;

  private ctrltype tolerPara;

  private String wbNumField;

  public StoreReqWriteBackTolerRule(String tolerFiled, ctrltype tolerPara,
      String wbNumField, UFBoolean isUserConfirm) {
    this.tolerFiled = tolerFiled;
    this.tolerPara = tolerPara;
    this.wbNumField = wbNumField;
    this.isUserConfirm = isUserConfirm;
  }

  @Override
  public String getBidFiled() {
    return StoreReqAppItemVO.PK_STOREREQ_B;
  }

  @Override
  public String getNumField() {
    return StoreReqAppItemVO.NNUM;
  }

  @Override
  public String getTable() {
    return PUEntity.M422X_B_TABLE;
  }

  @Override
  public void process(StoreReqAppViewVO[] vos) {
    List<String> srcBidList = new ArrayList<String>();
    for (StoreReqAppViewVO vo : vos) {
      srcBidList.add(vo.getPk_storereq_b());
    }
    this.toleranceCompare(this.wbNumField,
        srcBidList.toArray(new String[srcBidList.size()]), this.tolerFiled,
        this.tolerPara, this.isUserConfirm);
  }

}
