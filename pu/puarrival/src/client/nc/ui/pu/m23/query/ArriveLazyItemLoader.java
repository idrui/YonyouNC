package nc.ui.pu.m23.query;

import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m23.maintain.IArriveMaintain;
import nc.ui.pubapp.uif2app.lazilyload.IBillLazilyLoader;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

/**
 * 到货单懒加载的处理器
 * 
 * @since 6.0
 * @version 2011-6-13 上午11:53:44
 * @author zhaoyha
 */
public class ArriveLazyItemLoader implements IBillLazilyLoader {

  @Override
  public void loadChildrenByClass(
      Map<IBill, List<Class<? extends ISuperVO>>> needLoadChildrenMap)
      throws Exception {
    ArriveVO[] vos =
        needLoadChildrenMap.keySet().toArray(
            new ArriveVO[needLoadChildrenMap.keySet().size()]);
    vos =
        NCLocator.getInstance().lookup(IArriveMaintain.class).refreshItems(vos);
    List<Class<? extends ISuperVO>> bodyClazz =
        needLoadChildrenMap.values().iterator().next();
    needLoadChildrenMap.clear();
    for (ArriveVO vo : vos) {
      needLoadChildrenMap.put(vo, bodyClazz);
    }
  }

}
