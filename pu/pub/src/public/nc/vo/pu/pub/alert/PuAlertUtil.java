package nc.vo.pu.pub.alert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.measuredoc.MeasureDocService;
import nc.itf.scmpub.reference.uap.bd.psn.PsndocPubService;
import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.pubitf.bbd.CurrtypeQuery;
import nc.vo.bd.currtype.CurrtypeVO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.measdoc.MeasdocVO;
import nc.vo.bd.psn.PsndocVO;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.org.OrgVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-10-21 ÉÏÎç10:22:45
 * @author wuxla
 */

public class PuAlertUtil {
  public static Map<String, String[]> getCurrencyMap(String[][] values, int i) {
    Map<String, String[]> map = new HashMap<String, String[]>();
    for (String[] value : values) {
      if (value[i] != null && !"~".equals(value[i])) {
        String[] names = map.get(value[i]);
        if (null == names) {
          CurrtypeVO vo = CurrtypeQuery.getInstance().getCurrtypeVO(value[i]);
          map.put(
              value[i],
              new String[] {
                vo.getName(), vo.getName2(), vo.getName3(), vo.getName4(),
                vo.getName5(), vo.getName6()
              });
        }
      }
    }
    return map;
  }

  public static Map<String, String[]> getMaterialNameMap(String[][] values,
      int i) {
    Set<String> set = new HashSet<String>();
    for (String[] value : values) {
      if (value[i] != null && !"~".equals(value[i])) {
        set.add(value[i]);
      }
    }

    if (set.size() > 0) {
      String[] pks = set.toArray(new String[set.size()]);
      MaterialVO[] vos =
          MaterialPubService.queryMaterialBaseInfoByPks(pks, new String[] {
            MaterialVO.PK_MATERIAL, MaterialVO.NAME, MaterialVO.NAME2,
            MaterialVO.NAME3, MaterialVO.NAME4, MaterialVO.NAME5,
            MaterialVO.NAME6, MaterialVO.CODE, MaterialVO.MATERIALSPEC,
            MaterialVO.MATERIALTYPE
          });
      if (ArrayUtils.isEmpty(vos)) {
        return null;
      }
      Map<String, String[]> map = new HashMap<String, String[]>();
      for (MaterialVO vo : vos) {
        String[] names =
            new String[] {
              vo.getName(), vo.getName2(), vo.getName3(), vo.getName4(),
              vo.getName5(), vo.getName6(), vo.getCode(), vo.getMaterialspec(),
              vo.getMaterialtype()
            };
        map.put(vo.getPk_material(), names);
      }
      return map;
    }

    return null;
  }

  public static Map<String, String[]> getMeasdocMap(String[][] values, int i) {
    Set<String> set = new HashSet<String>();
    for (String[] value : values) {
      if (value[i] != null && !"~".equals(value[i])) {
        set.add(value[i]);
      }
    }

    if (set.size() > 0) {
      String[] pks = set.toArray(new String[set.size()]);
      MeasdocVO[] vos =
          MeasureDocService.queryMeasVOByPks(pks, new String[] {
            MeasdocVO.PK_MEASDOC, MeasdocVO.NAME, MeasdocVO.NAME2,
            MeasdocVO.NAME3, MeasdocVO.NAME4, MeasdocVO.NAME5, MeasdocVO.NAME6
          });
      if (vos != null && vos.length > 0) {
        Map<String, String[]> map = new HashMap<String, String[]>();
        for (MeasdocVO vo : vos) {
          String[] names =
              new String[] {
                vo.getName(), vo.getName2(), vo.getName3(), vo.getName4(),
                vo.getName5(), vo.getName6()
              };
          map.put(vo.getPk_measdoc(), names);
        }
        return map;
      }
    }
    return null;
  }

  public static Map<String, String[]> getMeasdocMap(String[][] values, int i,
      int j) {
    Set<String> set = new HashSet<String>();
    for (String[] value : values) {
      if (value[i] != null && !"~".equals(value[i])) {
        set.add(value[i]);
      }
      if (value[j] != null) {
        set.add(value[j]);
      }
    }

    if (set.size() > 0) {
      String[] pks = set.toArray(new String[set.size()]);
      MeasdocVO[] vos =
          MeasureDocService.queryMeasVOByPks(pks, new String[] {
            MeasdocVO.PK_MEASDOC, MeasdocVO.NAME, MeasdocVO.NAME2,
            MeasdocVO.NAME3, MeasdocVO.NAME4, MeasdocVO.NAME5, MeasdocVO.NAME6
          });
      if (vos != null && vos.length > 0) {
        Map<String, String[]> map = new HashMap<String, String[]>();
        for (MeasdocVO vo : vos) {
          String[] names =
              new String[] {
                vo.getName(), vo.getName2(), vo.getName3(), vo.getName4(),
                vo.getName5(), vo.getName6()
              };
          map.put(vo.getPk_measdoc(), names);
        }
        return map;
      }
    }
    return null;
  }

  public static Map<String, String[]> getOrgNameMap(String[][] values, int i) {
    Set<String> set = new HashSet<String>();
    for (String[] value : values) {
      if (value[i] != null && !"~".equals(value[i])) {
        set.add(value[i]);
      }
    }

    if (set.size() > 0) {
      String[] pks = set.toArray(new String[set.size()]);
      OrgVO[] vos =
          OrgUnitPubService.getOrgsByPks(pks, new String[] {
            OrgVO.PK_ORG, OrgVO.NAME, OrgVO.NAME2, OrgVO.NAME3, OrgVO.NAME4,
            OrgVO.NAME5, OrgVO.NAME6
          });
      if (ArrayUtils.isEmpty(vos)) {
        return null;
      }
      Map<String, String[]> map = new HashMap<String, String[]>();
      for (OrgVO vo : vos) {
        String[] names =
            new String[] {
              vo.getName(), vo.getName2(), vo.getName3(), vo.getName4(),
              vo.getName5(), vo.getName6()
            };
        map.put(vo.getPk_org(), names);
      }
      return map;
    }

    return null;
  }

  public static Map<String, String[]> getPsnNameMap(String[][] values, int i) {
    Set<String> set = new HashSet<String>();
    for (String[] value : values) {
      if (value[i] != null && !"~".equals(value[i])) {
        set.add(value[i]);
      }
    }

    if (set.size() > 0) {
      String[] pks = set.toArray(new String[set.size()]);
      PsndocVO[] vos =
          PsndocPubService.queryPsndocByPks(pks, new String[] {
            PsndocVO.PK_PSNDOC, PsndocVO.NAME, PsndocVO.NAME2, PsndocVO.NAME3,
            PsndocVO.NAME4, PsndocVO.NAME5, PsndocVO.NAME6
          });
      if (ArrayUtils.isEmpty(vos)) {
        return null;
      }
      Map<String, String[]> map = new HashMap<String, String[]>();
      for (PsndocVO vo : vos) {
        if (null == vo) {
          continue;
        }
        String[] names =
            new String[] {
              vo.getName(), vo.getName2(), vo.getName3(), vo.getName4(),
              vo.getName5(), vo.getName6()
            };
        map.put(vo.getPk_psndoc(), names);
      }
      return map;
    }

    return null;
  }

  public static Map<String, String[]> getStocDocMap(String[][] values, int i) {
    Set<String> set = new HashSet<String>();
    for (String[] value : values) {
      if (value[i] != null && !"~".equals(value[i])) {
        set.add(value[i]);
      }
    }

    if (set.size() > 0) {
      String[] pks = set.toArray(new String[set.size()]);
      StordocVO[] vos =
          StordocPubService.queryStordocInfoByPks(pks, new String[] {
            StordocVO.PK_STORDOC, StordocVO.NAME, StordocVO.NAME2,
            StordocVO.NAME3, StordocVO.NAME4, StordocVO.NAME5, StordocVO.NAME6
          });
      if (vos != null && vos.length > 0) {
        Map<String, String[]> map = new HashMap<String, String[]>();
        for (StordocVO vo : vos) {
          String[] names =
              new String[] {
                vo.getName(), vo.getName2(), vo.getName3(), vo.getName4(),
                vo.getName5(), vo.getName6()
              };
          map.put(vo.getPk_stordoc(), names);
        }
        return map;
      }
    }
    return null;
  }

  public static Map<String, String[]> getSupNameMap(String[][] values, int i) {
    Set<String> set = new HashSet<String>();
    for (String[] value : values) {
      if (value[i] != null && !"~".equals(value[i])) {
        set.add(value[i]);
      }
    }

    if (set.size() > 0) {
      String[] pks = set.toArray(new String[set.size()]);
      SupplierVO[] vos =
          SupplierPubService.getSupplierVO(pks, new String[] {
            SupplierVO.PK_SUPPLIER, SupplierVO.NAME, SupplierVO.NAME2,
            SupplierVO.NAME3, SupplierVO.NAME4, SupplierVO.NAME5,
            SupplierVO.NAME6
          });
      if (ArrayUtils.isEmpty(vos)) {
        return null;
      }
      Map<String, String[]> map = new HashMap<String, String[]>();
      for (SupplierVO vo : vos) {
        String[] names =
            new String[] {
              vo.getName(), vo.getName2(), vo.getName3(), vo.getName4(),
              vo.getName5(), vo.getName6()
            };
        map.put(vo.getPk_supplier(), names);
      }
      return map;
    }

    return null;
  }
}
