package nc.ui.pu.m27.match.util;

import javax.swing.table.TableColumn;

import nc.ui.pub.bill.BillScrollPane;
import nc.vo.ml.MultiLangContext;
import nc.vo.pu.costfactor.entity.CostfactorHeaderVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 界面（模板）动态显示成本要素信息的处理工具
 * 
 * @since 6.0
 * @version 2011-5-27 下午02:55:49
 * @author zhaoyha
 */
public class CostfactorDynamicDisplayUtil {

  /**
   * 根据成本要素信息动态显示模板的列数据（名称及显示隐藏）
   * 
   * @param cfVos 成本要素信息
   * @param pane 要调整的单据模板pane
   * @param preCFKey 在模板上的成本要素前缀字符
   */
  public static void initCostfactorDisplay(CostfactorVO[] cfVos,
      BillScrollPane pane, String preCFKey) {
    // 先全部隐藏，然后再将应该显示的显示
    for (int i = 1; i <= CostfactorVO.MAX_NUM; ++i) {
      pane.hideTableCol(preCFKey + String.valueOf(i));
    }
    if (ArrayUtils.isEmpty(cfVos)) {
      return;
    }
    String preNameKey = CostfactorHeaderVO.VFACTORNAME;
    int langSeq = MultiLangContext.getInstance().getCurrentLangSeq().intValue();
    for (CostfactorVO cfvo : cfVos) {
      String name = cfvo.getParentVO().getVfactorname();
      if (langSeq > 1) {
        name =
            (String) cfvo.getParentVO().getAttributeValue(
                preNameKey + String.valueOf(langSeq));
      }
      Integer cfSeq = cfvo.getParentVO().getIfactororder();
      // 显示出来
      pane.showTableCol(preCFKey + cfSeq);
      TableColumn tc = pane.getShowCol(preCFKey + cfSeq);
      tc.setHeaderValue(name);
    }
  }

}
