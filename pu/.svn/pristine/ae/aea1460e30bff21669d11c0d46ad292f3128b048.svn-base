/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-5-13 上午09:06:29
 */
package nc.ui.pu.costfactor.model;

import java.util.List;

import nc.ui.pubapp.pub.convertion.AggVoToListPanelValue;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.editor.value.BillListPanelValue;
import nc.vo.pu.costfactor.entity.CostfactorItemVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>组织缓存中的数据
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version v60
 * @since v60
 * @author zhaoyha
 * @time 2009-5-13 上午09:06:29
 */
public class CostFactorModel extends BillManageModel {

  /**
   * 方法功能描述： 组织缓存中的数据, 从聚合VO列表转换为BillListPanelValue，用于放置到BillListPanel中。
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author zhaoyha
   * @time 2009-5-15 下午12:29:06
   */
  public BillListPanelValue getCacheData() {
    List<?> modelData = this.getData();
    CostfactorVO curVo = (CostfactorVO) this.getSelectedData();
    CostfactorItemVO[] bodyVos = new CostfactorItemVO[0];
    if (curVo != null) {
      bodyVos = curVo.getChildrenVO();
    }
    return AggVoToListPanelValue.getListPanelValue(modelData, bodyVos);

  }
}
