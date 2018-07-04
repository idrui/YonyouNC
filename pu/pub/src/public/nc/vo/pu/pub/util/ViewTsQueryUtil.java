package nc.vo.pu.pub.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.mddb.constant.ElementConstant;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.entity.view.IDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>IDataView的TS查询工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-8-13 下午05:41:01
 */
public class ViewTsQueryUtil<E extends IDataView> {
  private Class<E> viewClazz;

  private IDataViewMeta viewMeta;

  public ViewTsQueryUtil(Class<E> clazz) {
    this.viewClazz = clazz;
    IDataView instance = Constructor.construct(clazz);
    this.viewMeta = instance.getMetaData();
  }

  public E[] query(String[] ids) {
    int length = ids.length;
    if (length == 0) {
      return this.construct(0);
    }
    E[] views = this.queryMainVO(ids, this.viewMeta);
    IVOMeta left = this.viewMeta.getMainVOMeta();
    this.setRelationVO(views, this.viewMeta, left);
    return views;
  }

  private E[] construct(int size) {
    E[] views = Constructor.construct(this.viewClazz, size);
    return views;
  }

  @SuppressWarnings({
    "unchecked", "rawtypes"
  })
  private ISuperVO[] query(String[] ids, IDataViewMeta viewMeta1, IVOMeta voMeta) {
    Class<? extends ISuperVO> clazz = viewMeta1.getVOClass(voMeta);
    List<String> fields = new ArrayList<String>();
    fields.add(voMeta.getPrimaryAttribute().getName());
    fields.add(ElementConstant.KEY_TS);

    IVOMeta[] rights = this.viewMeta.getRelationVOMetas(voMeta);
    if (rights != null) {
      for (IVOMeta right : rights) {
        IAttributeMeta leftAttribute =
            viewMeta1.getRelationStartAttribute(voMeta, right);
        fields.add(leftAttribute.getName());
      }
    }

    String[] names = fields.toArray(new String[fields.size()]);

    VOQuery<?> query = new VOQuery(clazz, names);
    ISuperVO[] vos = query.query(ids);
    return vos;
  }

  private E[] queryMainVO(String[] ids, IDataViewMeta viewMeta1) {
    IVOMeta left = viewMeta1.getMainVOMeta();
    ISuperVO[] vos = this.query(ids, viewMeta1, left);

    int size = vos.length;
    E[] views = this.construct(size);

    for (int i = 0; i < size; i++) {
      views[i].setVO(vos[i]);
    }
    return views;
  }

  private void setRelationVO(E[] views, IDataViewMeta dataViewMeta, IVOMeta left) {
    IVOMeta[] rights = this.viewMeta.getRelationVOMetas(left);
    if (rights == null) {
      return;
    }
    for (IVOMeta right : rights) {
      this.setRelationVO(views, this.viewMeta, left, right);
      this.setRelationVO(views, dataViewMeta, right);
    }
  }

  private void setRelationVO(E[] views, IDataViewMeta dataViewMeta, IVOMeta left,
      IVOMeta right) {
    IAttributeMeta leftAttribute =
        dataViewMeta.getRelationStartAttribute(left, right);
    IAttributeMeta rightAttribute =
        dataViewMeta.getRelationEndAttribute(left, right);

    MapList<String, E> index = new MapList<String, E>();
    Set<String> set = new HashSet<String>();
    for (E view : views) {
      String id = (String) view.getAttributeValue(leftAttribute.getName());
      set.add(id);
      index.put(id, view);
    }
    int size = set.size();
    if (size == 0) {
      return;
    }
    String[] ids = new String[size];
    ids = set.toArray(ids);
    ISuperVO[] vos = this.query(ids, dataViewMeta, right);
    for (ISuperVO vo : vos) {
      String id = (String) vo.getAttributeValue(rightAttribute.getName());
      List<E> list = index.get(id);
      for (E view : list) {
        view.setVO(vo);
      }
    }
  }
}
