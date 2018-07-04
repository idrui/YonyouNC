/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-15 下午02:45:35
 */
package nc.ui.pu.m21.service;

import java.util.ArrayList;
import java.util.List;

import nc.ui.uif2.model.DefaultBatchValidationService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>过滤空行，如果到货计划号为空则为空行
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-15 下午02:45:35
 */
public class RPBatchValidationService extends DefaultBatchValidationService {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.DefaultBatchValidationService#unNecessaryData(java.util.List)
   */
  @Override
  public int[] unNecessaryData(List<Object> rows) {
    if (rows.isEmpty()) {
      return null;
    }

    List<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < rows.size(); ++i) {
      OrderReceivePlanVO rpVO = (OrderReceivePlanVO) rows.get(i);
      if (null == rpVO) {
        continue;
      }

      // 如果到货计划号为空，则视为空行
      if (StringUtil.isEmptyWithTrim(rpVO.getVbillcode())) {
        list.add(Integer.valueOf(i));
      }
    }

    if (!list.isEmpty()) {
      int[] rowi = new int[list.size()];
      for (int i = 0; i < list.size(); ++i) {
        rowi[i] = list.get(i).intValue();
      }
      return rowi;
    }

    return null;
  }
}
