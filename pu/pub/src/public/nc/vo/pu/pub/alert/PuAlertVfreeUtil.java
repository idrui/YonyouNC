package nc.vo.pu.pub.alert;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.md.MDQueryFacade;
import nc.md.MDBaseQueryFacade;
import nc.md.data.access.NCObject;
import nc.md.model.IBean;
import nc.md.model.MetaDataException;
import nc.md.model.type.IType;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.util.MDUtil;
import nc.pubitf.uapbd.assistant.IMarAssistantPubService;
import nc.vo.bd.userdefrule.UserdefitemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapSet;

public class PuAlertVfreeUtil {

  private PuAlertDataSource datasource;

  private String name = "name";

  private String name2 = "name2";

  private String name3 = "name3";

  private String name4 = "name4";

  private String name5 = "name5";

  private String name6 = "name6";

  private String nullstr = "~";

  private String prefix = "vfree";

  private String split = "|";

  private String[][] values;

  private Map<String, Integer> vfreeMap;

  public PuAlertVfreeUtil(PuAlertDataSource datasource, String[][] values,
      Map<String, Integer> vfreeMap) {
    this.datasource = datasource;
    this.values = values;
    this.vfreeMap = vfreeMap;
  }

  public void setMarassist() {
    if (null == this.values || this.values.length == 0) {
      return;
    }

    MapSet<String, String> vfreeMapset = this.getVfreeMapSet();
    if (vfreeMapset.size() == 0) {
      return;
    }
    Map<String, UserdefitemVO> userdefitemMap = this.getUserDefitemMap();
    if (null == userdefitemMap) {
      return;
    }
    Map<String, String[]> nameMap =
        this.getNameMap(vfreeMapset, userdefitemMap);
    for (Entry<String, Integer> entry : this.vfreeMap.entrySet()) {
      int pos = entry.getValue().intValue();
      String key = entry.getKey();
      for (int i = 0; i < this.values.length; ++i) {
        if (this.values[i][pos] != null
            && !this.nullstr.equals(this.values[i][pos])) {
          String[] names = nameMap.get(key + this.split + this.values[i][pos]);
          if (names != null) {
            this.datasource.putData(key, names[0], i);
            this.datasource.putData2(key, names[1], i);
            this.datasource.putData3(key, names[2], i);
            this.datasource.putData4(key, names[3], i);
            this.datasource.putData5(key, names[4], i);
            this.datasource.putData6(key, names[5], i);
          }
        }
      }
    }
  }

  private Class<?> getClazz(String className) {
    try {
      return Class.forName(className);
    }
    catch (ClassNotFoundException e) {
      return null;
    }
  }

  private Map<String, String[]> getNameMap(MapSet<String, String> vfreeset,
      Map<String, UserdefitemVO> userdefitemMap) {
    Map<String, String[]> nameMap = new HashMap<String, String[]>();
    for (Entry<String, Set<String>> entry : vfreeset.entrySet()) {
      String key = entry.getKey();
      UserdefitemVO userdefitem = userdefitemMap.get(key);
      if (null == userdefitem) {
        continue;
      }
      String classid = userdefitem.getClassid();
      IType type = this.getType(classid);

      if (type != null && MDUtil.isEntityType(type)) {
        IBean bean = MDQueryFacade.getBeanByID(classid);
        if (bean == null) {
          continue;
        }
        String className = bean.getFullClassName();
        Class<?> clazz = this.getClazz(className);
        for (String billPK : entry.getValue()) {
          String[] names = this.getNames(clazz, billPK);
          if (names != null) {
            nameMap.put(key + this.split + billPK, names);
          }
        }
      }
    }

    return nameMap;
  }

  private String[] getNames(Class<?> clazz, String billPK) {
    NCObject ncobj = this.getNCObject(clazz, billPK);
    if (ncobj == null) {
      return null;
    }
    String[] names = new String[6];
    Object obj = ncobj.getAttributeValue(this.name);
    if (obj != null) {
      names[0] = obj.toString();
    }
    Object obj2 = ncobj.getAttributeValue(this.name2);
    if (obj2 != null) {
      names[1] = obj2.toString();
    }
    Object obj3 = ncobj.getAttributeValue(this.name3);
    if (obj3 != null) {
      names[2] = obj3.toString();
    }
    Object obj4 = ncobj.getAttributeValue(this.name4);
    if (obj4 != null) {
      names[3] = obj4.toString();
    }
    Object obj5 = ncobj.getAttributeValue(this.name5);
    if (obj5 != null) {
      names[4] = obj5.toString();
    }
    Object obj6 = ncobj.getAttributeValue(this.name6);
    if (obj6 != null) {
      names[5] = obj6.toString();
    }

    return names;
  }

  private NCObject getNCObject(Class<?> clazz, String billPK) {
    try {
      return NCLocator.getInstance().lookup(IMDPersistenceQueryService.class)
          .queryBillOfNCObjectByPKWithDR(clazz, billPK, false);
    }
    catch (MetaDataException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private IType getType(String classid) {
    try {
      return MDBaseQueryFacade.getInstance().getTypeByID(classid,
          IType.STYLE_SINGLE);
    }
    catch (MetaDataException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private Map<String, UserdefitemVO> getUserDefitemMap() {
    UserdefitemVO[] userdefiitems = null;
    String pk_group = AppContext.getInstance().getPkGroup();
    IMarAssistantPubService service =
        NCLocator.getInstance().lookup(IMarAssistantPubService.class);
    try {
      userdefiitems = service.queryMarAssistantDefine(pk_group);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    if (null == userdefiitems) {
      return null;
    }

    Map<String, UserdefitemVO> itemMap = new HashMap<String, UserdefitemVO>();
    for (UserdefitemVO item : userdefiitems) {
      int newIndex = item.getPropindex().intValue() - 5;
      item.setPropindex(Integer.valueOf(newIndex));
      itemMap.put(this.prefix + newIndex, item);
    }
    return itemMap;
  }

  /**
   * @return key:×Ö¶Î£¬value£ºÖµ¼¯ºÏ
   */
  private MapSet<String, String> getVfreeMapSet() {
    MapSet<String, String> vfreeset = new MapSet<String, String>();
    for (Entry<String, Integer> entry : this.vfreeMap.entrySet()) {
      String key = entry.getKey();
      int pos = entry.getValue().intValue();
      for (String[] value : this.values) {
        if (value[pos] != null && !this.nullstr.equals(value[pos])) {
          vfreeset.put(key, value[pos]);
        }
      }
    }

    return vfreeset;
  }
}
