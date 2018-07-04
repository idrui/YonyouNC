/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-9 ����11:22:13
 */
package nc.vo.pu.m4t.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���õ�λ
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-9 ����11:22:13
 */
public class AssistUnit {
  private IKeyValue keyValue;

  public AssistUnit(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * �����������������õ�λ
   * <p>
   * <b>����˵��</b>
   * 
   * @param rows
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 ����11:24:53
   */
  public void setAssistUnit(int[] rows) {
    String[] materials = this.getMaterials(rows);
    if (ArrayUtils.isEmpty(materials)) {
      return;
    }

    Map<String, String> assistunitMap = null;
    // ȡ�ɹ�Ĭ�ϵ�λ
    assistunitMap = MaterialPubService.queryPuMeasdocIDByPks(materials);

    this.setAssistunit(rows, assistunitMap);
  }

  /**
   * ��������������ȡ��������
   * <p>
   * <b>����˵��</b>
   * 
   * @param rows
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 ����11:25:53
   */
  private String[] getMaterials(int[] rows) {
    Set<String> materialSet = new HashSet<String>();
    for (int row : rows) {
      String material =
          (String) this.keyValue
              .getBodyValue(row, InitialEstItemVO.PK_MATERIAL);
      if (!StringUtil.isEmptyWithTrim(material)) {
        materialSet.add(material);
      }
    }

    if (materialSet.size() > 0) {
      return materialSet.toArray(new String[materialSet.size()]);
    }

    return null;
  }

  /**
   * �����������������õ�λ��Ĭ�ϸ���λ��>����λ
   * <p>
   * <b>����˵��</b>
   * 
   * @param rows
   * @param assistunitMap
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 ����11:27:29
   */
  private void setAssistunit(int[] rows, Map<String, String> assistunitMap) {
    for (int row : rows) {
      String material =
          (String) this.keyValue
              .getBodyValue(row, InitialEstItemVO.PK_MATERIAL);
      String assistunit = assistunitMap.get(material);
      if (null == assistunit) {
        String unit =
            (String) this.keyValue.getBodyValue(row, InitialEstItemVO.CUNITID);
        this.keyValue.setBodyValue(row, InitialEstItemVO.CASTUNITID, unit);
      }
      else {
        this.keyValue
            .setBodyValue(row, InitialEstItemVO.CASTUNITID, assistunit);
      }
    }
  }
}
