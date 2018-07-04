/**
 * 
 */
package nc.vo.pu.m20.pub;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.tool.ViewConcurrentTool;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.entity.view.IDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.pub.Constructor;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version
 * @since
 * @author luojw
 * @time 2014-6-25 下午7:40:49
 */
public class ViewTransferUtil<E extends AbstractDataView, F extends ISuperVO> {

  private Class<F> clazz;

  private String[] hids;

  private F[] originAddHeadVOs;

  private E[] originDelObjs;

  private E[] originUpdObjs;

  public ViewTransferUtil(E[] updObjs, E[] addObjs, E[] delObjs, Class<F> clazz) {
    this.clazz = clazz;
    this.init(updObjs, addObjs, delObjs);
  }

  /**
   * @return the clazz
   */
  public Class<F> getClazz() {
    return this.clazz;
  }

  /**
   * @return the hids
   */
  public String[] getHids() {
    return this.hids;
  }

  /**
   * @return the originAddHeadVOs
   */
  public F[] getOriginAddHeadVOs() {
    return this.originAddHeadVOs;
  }

  /**
   * @return the originDelObjs
   */
  public E[] getOriginDelObjs() {
    return this.originDelObjs;
  }

  /**
   * @return the originUpdObjs
   */
  public E[] getOriginUpdObjs() {
    return this.originUpdObjs;
  }

  /**
   * @param clazz
   *          the clazz to set
   */
  public void setClazz(Class<F> clazz) {
    this.clazz = clazz;
  }

  /**
   * @param hids
   *          the hids to set
   */
  public void setHids(String[] hids) {
    this.hids = hids;
  }

  /**
   * @param originAddHeadVOs
   *          the originAddHeadVOs to set
   */
  public void setOriginAddHeadVOs(F[] originAddHeadVOs) {
    this.originAddHeadVOs = originAddHeadVOs;
  }

  /**
   * @param originDelObjs
   *          the originDelObjs to set
   */
  public void setOriginDelObjs(E[] originDelObjs) {
    this.originDelObjs = originDelObjs;
  }

  /**
   * @param originUpdObjs
   *          the originUpdObjs to set
   */
  public void setOriginUpdObjs(E[] originUpdObjs) {
    this.originUpdObjs = originUpdObjs;
  }

  @SuppressWarnings("unchecked")
  private E[] combinArrays(E[]... ObjArrays) {
    if (ObjArrays.length == 0) {
      return null;
    }

    List<E> alCombinResult = new ArrayList<E>();
    E[] firstNotNull = null;
    for (int i = 0; i < ObjArrays.length; i++) {
      if (ArrayUtils.isEmpty(ObjArrays[i])) {
        continue;
      }
      if (null == firstNotNull) {
        firstNotNull = ObjArrays[i];
      }
      for (int j = 0; j < ObjArrays[i].length; j++) {
        alCombinResult.add(ObjArrays[i][j]);
      }
    }
    if (null == firstNotNull) {
      return null;
    }

    return alCombinResult.toArray((E[]) Array.newInstance(firstNotNull
        .getClass().getComponentType(), alCombinResult.size()));
  }

  @SuppressWarnings("unchecked")
  private F[] getHeadVOs(E[] views) {
    Map<String, F> map = new HashMap<String, F>();
    for (int i = 0; i < views.length; ++i) {
      F vo = (F) views[i].getVO(this.clazz);
      if (map.containsKey(vo.getPrimaryKey())) {
        continue;
      }
      map.put(vo.getPrimaryKey(), vo);
    }

    F[] vos = Constructor.declareArray(this.clazz, map.size());
    vos = map.values().toArray(vos);
    return vos;
  }

  private String[] getIds(E[] objs) {
    String[] ids = new String[objs.length];
    int length = objs.length;
    for (int i = 0; i < length; i++) {
      ids[i] = objs[i].getPrimaryKey();
    }
    return ids;
  }

  private String[] getPrimaryKeys(F[] bills) {
    if (ArrayUtils.isEmpty(bills)) {
      return null;
    }
    List<String> ids = new ArrayList<String>();
    for (F bill : bills) {
      ids.add(bill.getPrimaryKey());
    }
    return ids.toArray(new String[ids.size()]);
  }

  @SuppressWarnings({
    "unchecked", "rawtypes"
  })
  private void init(E[] updObjs, E[] addObjs, E[] delObjs) {
    E[] allObjs = this.combinArrays(updObjs, addObjs, delObjs);
    if (ArrayUtils.isEmpty(allObjs)) {
      return;
    }
    ViewConcurrentTool tool = new ViewConcurrentTool();
    // tool.lock(allObjs);
    this.lock(allObjs);
    ViewQuery query = new ViewQuery(allObjs[0].getClass());
    if (!ArrayUtils.isEmpty(updObjs)) {
      String[] updIds = this.getIds(updObjs);
      this.originUpdObjs = (E[]) query.query(updIds);
      tool.checkTS(updObjs, this.originUpdObjs);
    }

    if (!ArrayUtils.isEmpty(addObjs)) {
      F[] headVOs = this.getHeadVOs(addObjs);
      String[] addids = this.getPrimaryKeys(headVOs);
      VOQuery<F> voquery = new VOQuery<F>(this.clazz);
      this.originAddHeadVOs = voquery.query(addids);
      VOConcurrentTool votool = new VOConcurrentTool();
      votool.checkTS(headVOs, this.originAddHeadVOs);
    }

    if (!ArrayUtils.isEmpty(delObjs)) {
      String[] delIds = this.getIds(delObjs);
      this.originDelObjs = (E[]) query.query(delIds);
      tool.checkTS(delObjs, this.originDelObjs);
    }

    F[] allHeadVOs = this.getHeadVOs(allObjs);
    this.hids = new String[allHeadVOs.length];
    for (int i = 0; i < allHeadVOs.length; ++i) {
      this.hids[i] = allHeadVOs[i].getPrimaryKey();
    }
  }

  private void lock(IDataView[] views) {
    IDataViewMeta viewMeta = views[0].getMetaData();
    IVOMeta[] voMetas = viewMeta.getVOMetas();
    for (IVOMeta voMeta : voMetas) {
      this.lock(views, viewMeta, voMeta);
    }
  }

  private void lock(IDataView[] views, IDataViewMeta viewMeta, IVOMeta voMeta) {
    Set<String> set = new HashSet<String>();
    Class<? extends ISuperVO> lockClazz = viewMeta.getVOClass(voMeta);
    String key = voMeta.getPrimaryAttribute().getName();
    for (IDataView view : views) {
      ISuperVO vo = view.getVO(lockClazz);
      // 没有设置主键
      if (!vo.usedAttributeNames().contains(key)) {
        continue;
      }
      if (vo.getPrimaryKey() != null) {
        set.add(vo.getPrimaryKey());
      }
    }
    if (set.size() == 0) {
      return;
    }
    String[] ids = new String[set.size()];
    ids = set.toArray(ids);
    LockOperator lock = new LockOperator();
    String label = voMeta.getLabel();
    lock.lock(
        ids,
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4001001_0",
            "04001001-0277", null, new String[] {
              label, String.valueOf(lockClazz)
            })/*
               * 锁定【{0}
               * 】
               * ID失败{
               * 1}
               */);
  }

}
