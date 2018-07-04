package nc.ui.pu.m27.match.view.listener;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pub.bill.IBillRelaSortListener;
import nc.vo.pu.m27.entity.StockSettleVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>入库单的排序对象获取监听
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-30 上午11:19:26
 */
public class StockSortRelaObjectListener implements IBillRelaSortListener {
  private MatchManageModel model;

  public StockSortRelaObjectListener(MatchManageModel model) {
    this.model = model;
  }

  @Override
  public List<DataRowNo> getRelaSortObject() {
    List<DataRowNo> listToSort = new ArrayList<DataRowNo>();
    StockSettleVO[] stocks = this.model.getQueryStockVOs();

    for (int i = 0; i < stocks.length; i++) {
      listToSort.add(new DataRowNo(stocks[i], i));
    }

    return listToSort;
  }

}
