package nc.ui.pu.m25.query;

import java.util.List;
import java.util.Map;

import nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

/**
 * 发票懒加载，处理计算属性（计划价）
 * 
 * @since 6.0
 * @version 2014-1-26 下午02:39:24
 * @author wuxla
 */
public class InvoiceLazilyLoader extends DefaultBillLazilyLoader {
  @Override
  public void loadChildrenByClass(
      Map<IBill, List<Class<? extends ISuperVO>>> needLoadChildrenMap)
      throws Exception {
    super.loadChildrenByClass(needLoadChildrenMap);

  }
}
