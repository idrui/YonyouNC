package nc.bs.pu.position.maintain;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.businessevent.bd.BDCommonEvent;
import nc.bs.framework.common.NCLocator;
import nc.itf.pu.position.IPosition;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.pub.BeanHelper;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 监听物料基本分类以更新采购岗的t表
 * 物料基本分类,新增，修改，删除都注册此类
 * 
 * @since 6.0
 * @version 2011-5-20 下午04:42:49
 * @author liugxa
 */

public class DimensionAddistener implements IBusinessListener {
  private IPosition iservice;

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    this.processDBDataLevelUpdated(event);
  }

  private void addMarBas(List<Object[]> list, Object oldObject,
      Object newObject, List<String> diffAttrs) {
    if (diffAttrs.contains(MarBasClassVO.PK_PARENT)
        || diffAttrs.contains(MarBasClassVO.INNERCODE)) {
      list.add(new Object[] {
        oldObject, newObject
      });
    }
  }

  private List<String> diffAttrs(Object oldObject, Object newObject) {
    List<String> attrs = new ArrayList<String>();
    Class<?> clazz = oldObject.getClass();
    Field[] fields = clazz.getDeclaredFields();
    for (int i = 0; i < fields.length; i++) {
      Object oldFieldValue =
          BeanHelper.getProperty(oldObject, fields[i].getName());
      Object newFieldValue =
          BeanHelper.getProperty(newObject, fields[i].getName());
      if (!PubAppTool.isEqual(oldFieldValue, newFieldValue)) {
        attrs.add(fields[i].getName());
      }
    }
    return attrs;
  }

  private List<Object[]> filterBDObject(Object[] oldObjects, Object[] newObjects) {
    List<Object[]> list = new ArrayList<Object[]>();
    for (int i = 0; i < oldObjects.length; i++) {
      Object oldObject = oldObjects[i];
      Object newObject = newObjects[i];
      List<String> diffAttrs = this.diffAttrs(oldObject, newObject);
      if (oldObject instanceof MarBasClassVO) {
        this.addMarBas(list, oldObject, newObject, diffAttrs);
      }
    }
    return list;
  }

  private IPosition getPositionService() {
    if (this.iservice == null) {
      this.iservice = NCLocator.getInstance().lookup(IPosition.class);
    }
    return this.iservice;
  }

  private void processDBDataLevelUpdated(IBusinessEvent event)
      throws BusinessException {
    if (event instanceof BDCommonEvent) {
      // String sourceId = event.getSourceID();
      Object[] oldObjects = ((BDCommonEvent) event).getOldObjs();
      Object[] newObjects = ((BDCommonEvent) event).getNewObjs();
      if (oldObjects == null || oldObjects.length == 0) {
        return;
      }
      List<Object[]> list = this.filterBDObject(oldObjects, newObjects);
      if (list.size() > 0) {
        this.getPositionService().listenBDDataLevelUpdated(list);
      }
    }
  }
}
