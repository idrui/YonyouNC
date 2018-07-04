/**
 * $�ļ�˵��$
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-5 ����09:12:28
 */
package nc.bs.pu.m20.pf.function;

import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺���������ظ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-5 ����09:12:28
 */
public class MaterialCodeDuplicated {
  /**
   * ���������������빺���������ϱ����Ƿ��ظ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param VO
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-7-5 ����09:13:26
   */
  public UFBoolean isMaterialCodeDuplicated(AggregatedValueObject VO)
      throws BusinessException {

    if (VO == null) {
      return UFBoolean.TRUE;
    }

    PraybillVO billVO = (PraybillVO) VO;
    PraybillItemVO bodyVO[] = billVO.getBVO();
    if (bodyVO == null || bodyVO.length == 0) {
      return UFBoolean.TRUE;
    }

    // �ظ������ϻ���ID
    Vector<String> dupids = new Vector<String>();

    HashSet<String> ids = new HashSet<String>();
    for (int i = 0; i < bodyVO.length; i++) {
      if (bodyVO[i].getStatus() != VOStatus.DELETED) {
        String s = bodyVO[i].getPk_material().trim();
        if (ids.contains(s)) {
          dupids.add(s);
        }
        else {
          ids.add(s);
        }
      }
    }

    if (dupids.size() == 0 || ids.size() == 0) {
      return UFBoolean.TRUE;
    }

    // ����ID��ȡ���ϱ���
    Map<String, MaterialVO> materialvos =
        MaterialPubService.queryMaterialBaseInfo(
            dupids.toArray(new String[dupids.size()]), new String[] {
              MaterialVO.CODE
            });

    if (null != materialvos && materialvos.size() > 0) {
      StringBuffer s = new StringBuffer();
      for (Map.Entry<String, MaterialVO> vo : materialvos.entrySet()) {
        s.append(vo.getValue().getCode() + ",");
      }
      String a = s.substring(0, s.length() - 1);
      if (a.trim().length() > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002911")/* @res "���ϱ���" */
            + s
            + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                "4004020_0", "04004020-0046")/* @res "�ظ�!" */);
      }
    }

    return UFBoolean.FALSE;
  }
}
