package nc.vo.pu.m21.rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-4-21 ÉÏÎç09:09:05
 * @author wuxla
 */

public class TrantypeCodeValue implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    Map<String, String> codeMap = new HashMap<String, String>();
    for (OrderVO vo : vos) {
      if (!StringUtil.isEmptyWithTrim(vo.getHVO().getVtrantypecode())) {
        continue;
      }

      String ctrantypeid = vo.getHVO().getCtrantypeid();
      if (StringUtil.isEmptyWithTrim(ctrantypeid)) {
        continue;
      }
      String code = null;
      if (codeMap.containsKey(ctrantypeid)) {
        code = codeMap.get(ctrantypeid);
      }
      else {
        code = PfServiceScmUtil.getTrantypecodeByid(ctrantypeid);
        codeMap.put(ctrantypeid, code);
      }
      vo.getHVO().setVtrantypecode(code);
    }
  }
}
