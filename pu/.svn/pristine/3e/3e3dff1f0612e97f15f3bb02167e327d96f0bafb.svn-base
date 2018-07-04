package nc.ui.pu.m23.model;

import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.pu.m23.entity.ArriveViewVO;

/**
 * @since 6.0
 * @version 2011-1-21 обнГ04:34:41
 * @author yinfy
 */

public class ArriveCheckManageModel extends BillManageModel {
  @Override
  public int findBusinessData(Object obj) {

    ArriveViewVO target = (ArriveViewVO) obj;
    int i = 0;
    for (Object object : this.getData()) {
      ArriveViewVO tmp = (ArriveViewVO) object;
      if (tmp.getBVO().getPk_arriveorder_b().equals(
          target.getBVO().getPk_arriveorder_b())) {
        return i;
      }
      i++;
    }
    return -1;
  }
}
