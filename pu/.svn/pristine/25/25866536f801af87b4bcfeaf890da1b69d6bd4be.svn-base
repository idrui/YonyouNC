package nc.pubimpl.pu.m4203;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.pu.m4203.ISubcontinFIPubQuery;
import nc.vo.pu.m4203.entity.SubcontinFIItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * 委托加工入对外查询服务
 * 
 * @since 6.0
 * @version 2011-7-11 上午11:14:56
 * @author 田锋涛
 */

public class SubcontinFIPubQueryImpl implements ISubcontinFIPubQuery {

  @Override
  public Map<String, UFDouble> getTotalSettleNum(String[] bids)
      throws BusinessException {
    Map<String, UFDouble> result = new HashMap<String, UFDouble>();
    if (ArrayUtils.isEmpty(bids)) {
      return result;
    }
    try {
      VOQuery<SubcontinFIItemVO> qry =
          new VOQuery<SubcontinFIItemVO>(SubcontinFIItemVO.class);
      SubcontinFIItemVO[] items = qry.query(bids);
      if (ArrayUtils.isEmpty(items)) {
        return result;
      }
      for (SubcontinFIItemVO item : items) {
        result.put(item.getPk_stockps_b(),
            MathTool.nvl(item.getNaccumsettlenum()));
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return result;
  }

}
