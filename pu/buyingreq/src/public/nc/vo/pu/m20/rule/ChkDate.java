/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-25 ����08:27:50
 */
package nc.vo.pu.m20.rule;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺������ʱ�빺���ڡ��������ڡ����鶩�����ڼ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-25 ����08:27:50
 */
public class ChkDate {
  /**
   * ���������������빺���ڡ��������ڡ����鶩�����ڼ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 ����04:27:00
   */
  public void checkDate(PraybillVO vo) {
    PraybillItemVO[] bvos = vo.getBVO();
    for (PraybillItemVO bvo : bvos) {
      // ��������
      UFDate reqdate = bvo.getDreqdate();
      if (null != reqdate) {
        reqdate = reqdate.asBegin();
      }
      // ���鶩������
      UFDate suggestdate = bvo.getDsuggestdate();
      if (null != suggestdate) {
        suggestdate = suggestdate.asBegin();
      }

      String sRowNo = bvo.getCrowno();
      if (null != suggestdate && null != reqdate && suggestdate.after(reqdate)) {
        String errorMsg =
            NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
                "04004020-0093", null, new String[] {
                  sRowNo
                })/* ������{0}:���鶩�����ڱ���С����������! */;
        ExceptionUtils.wrappBusinessException(errorMsg);
      }

      // yangtian�޸ĵ�һ��bug��Ҫ��ǰ̨ѯ���빺�����Ƿ���Դ����������ڣ�
      // ���Խ���̨������ж���ɾ��������ǰ̨�û�Ҫ����ԣ���̨ȴ���쳣������ɲ�һ�¡�
    }
  }
}
