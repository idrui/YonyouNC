package nc.bs.pu.est.m45.rule;

import nc.impl.pubapp.env.BSContext;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pubapp.AppContext;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>货物暂估必须项后台补充规则(父类)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-18 上午11:06:13
 */
public class GoodsEstNecessarityFillRule {

  public GoodsEstNecessarityFillRule() {
    super();
  }

  public void process(EstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (EstVO vo : vos) {
      // 暂估人
      vo.getParentVO().setPk_costappsn(BSContext.getInstance().getUserID());
      // 暂估日期
      vo.getParentVO().setDtocostapdate(AppContext.getInstance().getBusiDate());
    }
  }

}
