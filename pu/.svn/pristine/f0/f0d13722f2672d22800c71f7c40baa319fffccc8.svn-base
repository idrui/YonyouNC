package nc.bs.pu.m23.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 * 本类主要完成以下功能：
 * 新增、修改保存后检查，检查单据号是否存在重复
 * @scene
 * 到货单新增、修改保存后
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-1-26 下午02:39:36
 * @author hanbin
 */

public class ChkBillCodeUniqueRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos) {
    PUBillCodeUtils.getArriveCode().checkUnique(vos);
  }
}
