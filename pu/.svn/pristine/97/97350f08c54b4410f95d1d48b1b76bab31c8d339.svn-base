package nc.impl.pu.m21transtype.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 检查一个交易类型是否可删除<br>
 * 这个规则只负责检查，系统预置的一些交易类型（21-coop）不允许删除<br>
 * 交易类型是否被使用，删除时平台已经检查好，这里不再重复检查
 *
 * @since 6.0
 * @version 2011-7-18 下午02:10:24
 * @author zhaoyha
 */
public class CanDelChkRule implements IRule<PoTransTypeVO> {

  @Override
  public void process(PoTransTypeVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (PoTransTypeVO vo : vos) {
      if (PoTransTypeVO.M21_COOP.equals(vo.getVtrantypecode())) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0052")/*@res "此交易类型是内部预置，只用于销售协同采购时的数据交换区分，不可删除！也建议不用此交易做业务数据！"*/);
      }
    }

  }
}