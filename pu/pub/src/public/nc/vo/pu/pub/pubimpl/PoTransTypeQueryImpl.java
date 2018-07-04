package nc.vo.pu.pub.pubimpl;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.aop.Wrapper;
import nc.pubimpl.bdlayer.cache.CacheVOQuery;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.MetaTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * �ɹ������������Ͳ�ѯ����ʵ����
 * <ul>
 * <li>�ع�Ϊ��ǰ̨�����ʵ�֣�ʹ��UAP��AOP�������ɲμ�UPM������
 * </ul>
 * 
 * @since 6.0
 * @version 2011-12-28 ����10:26:59
 * @author zhaoyha
 */
public class PoTransTypeQueryImpl implements IPoTransTypeQuery, Wrapper {

  @Override
  public String getDirectTransWhereString(String tableName, String joinField) {
    StringBuilder sb = new StringBuilder("inner join po_potrantype ");
    sb.append(" on po_potrantype.pk_group = " + tableName + ".pk_group");
    sb.append(" and po_potrantype.ctrantypeid = " + tableName + "." + joinField);
    sb.append(" and po_potrantype.bdirect = 'Y' ");
    sb.append(" and po_potrantype.ctrantypeid != '~' ");
    sb.append(" and po_potrantype.dr=0 ");
    return sb.toString();
  }

  @Override
  public Map<String, PoTransTypeVO> queryAttrByIDs(String[] ids)
      throws BusinessException {
    Map<String, PoTransTypeVO> map = new HashMap<String, PoTransTypeVO>();
    try {
      String[] attNames = this.getPersistenceFieldName();
      String[][] whereValues = new String[ids.length][1];
      for (int i = 0; i < whereValues.length; i++) {
        whereValues[i][0] = ids[i];
      }
      PoTransTypeVO[] vos =
          new CacheVOQuery<PoTransTypeVO>(PoTransTypeVO.class, attNames).query(
              new String[] {
                PoTransTypeVO.CTRANTYPEID
              }, whereValues);
      if (!ArrayUtils.isEmpty(vos)) {
        for (PoTransTypeVO vo : vos) {
          map.put(vo.getCtrantypeid(), vo);
        }
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return map;
  }

  @Override
  public HashMap<String, PoTransTypeVO> queryAttrByTypes(String[] saTransType,
      String[] saAttriName) throws BusinessException {
    HashMap<String, PoTransTypeVO> hmap = new HashMap<String, PoTransTypeVO>();
    try {
      String[] whereFds = new String[] {
        PoTransTypeVO.VTRANTYPECODE, PoTransTypeVO.PK_GROUP, PoTransTypeVO.DR
      };
      Object[][] whereValues = new Object[saTransType.length][whereFds.length];
      String pk_group = AppContext.getInstance().getPkGroup();
      for (int i = 0; i < whereValues.length; i++) {
        whereValues[i][0] = saTransType[i];
        whereValues[i][1] = pk_group;
        whereValues[i][2] = Integer.valueOf(0);
      }
      String[] attname = null;
      if (null != saAttriName) {
        attname = ArrayUtil.combinArrays(saAttriName, new String[] {
          PoTransTypeVO.VTRANTYPECODE
        });
      }
      else {
        attname = this.getPersistenceFieldName();
      }
      PoTransTypeVO[] vos =
          new CacheVOQuery<PoTransTypeVO>(PoTransTypeVO.class, attname).query(
              whereFds, whereValues);
      if (!ArrayUtils.isEmpty(vos)) {
        for (PoTransTypeVO vo : vos) {
          hmap.put(vo.getVtrantypecode(), vo);
        }
      }
    }
    catch (Exception e) {
      // ���淶��װ�쳣
      ExceptionUtils.marsh(e);
    }
    return hmap;
  }

  @Override
  public void setWrapping(Object wrapping) {
    //
  }

  private String[] getPersistenceFieldName() {
    IAttributeMeta[] patts =
        MetaTool.getVOMeta(PoTransTypeVO.class).getStatisticInfo()
            .getPerisistentAttributes();
    String[] attNames = new String[patts.length];
    for (int i = 0; i < attNames.length; i++) {
      attNames[i] = patts[i].getName();
    }
    return attNames;
  }

}
