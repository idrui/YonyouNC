/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-12 ����05:14:23
 */
package nc.vo.pu.m21.rule;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��;״̬У�鹤����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-12 ����05:14:23
 */
public class OnwayValidationTool {

  /**
   * �������������������û�����ҵ�����ݵ���Ч�ԣ�������
   * <p>
   * <b>����˵��</b>
   * 
   * @param pvo
   * @param vos
   * @throws Exception
   *           <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-19 ����10:06:26
   */
  public static void chkBusiForSendOut(PoTransTypeVO pvo, OrderOnwayItemVO[] vos)
      throws BusinessException {

    if (null == pvo) {
      return;
    }

    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    StringBuilder retSb = new StringBuilder();
    for (int i = 0; i < vos.length; i++) {
      // [���η�������]����Ϊ0��
      if (ValueUtils.getBoolean(pvo.getBconsignnum())) {
        if (null != vos[i].getNsendoutnum()) {
          UFDouble connum = ValueUtils.getUFDouble(vos[i].getNsendoutnum());
          if (connum.compareTo(UFDouble.ZERO_DBL) <= 0) {
            if (!"".equals(retSb.toString())) {
              retSb.append(", ");
            }
            String crowno = vos[i].getCrowno();
            retSb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
                "4004030_0", "04004030-0307", null, new String[] {
                  crowno
                })/* ��{0}��[���η�������]����Ϊ0�� */);
          }
          // if (connum.compareTo(vos[i].getNnum()) > 0) {
          // if (!"".equals(retStr))
          // retStr += ", ";
          // retStr += "��" + (i + 1) + "��ȷ���������ܴ��ڶ�������";
          // }
        }
      }
      // ȷ������
      // if (ValueUtils.getBoolean(pvo.getBconfirmdate())) {
      // if (null != vos[i].getDconfirmdate()) {
      // UFDate date = ValueUtils.getUFDate(
      // vos[i].getDconfirmdate());
      // UFDate dateNow = ClientContext.getInstance().getBusiDate();
      // if (date.compareTo(dateNow) > 0) {
      // if (!"".equals(retStr))
      // retStr += ", ";
      // retStr += "��" + (i + 1) + "��ȷ�����ڲ��ܴ��ڵ�ǰ����";
      // }
      // }
      // }
    }
    if (!"".equals(retSb.toString())) {
      String retStr =
          NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
              "04004030-0226", null, new String[] {
                retSb.toString()
              })/* ѡ���{0} */;
      throw new BusinessException(retStr);
    }
  }

  /**
   * ����������������鵥�ݺţ����������ڲ�Ϊ�գ������������;��
   * <p>
   * <b>����˵��</b>
   * 
   * @param transvo
   *          ��������VO
   * @param vos
   *          ҪУ���VO
   * @param billCode
   *          ���ݺ���
   * @param billDate
   *          ����������
   * @param statusName
   *          ��ʾ��״̬��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-2 ����11:09:38
   */
  public static void chkEmptyForOnway(PoTransTypeVO transvo,
      OrderOnwayItemVO[] vos, String billCode, String billDate,
      String statusName) throws BusinessException {

    if (null == transvo) {
      return;
    }

    StringBuilder retSb = new StringBuilder();
    for (int i = 0; i < vos.length; i++) {
      String crowno = vos[i].getCrowno();
      // ��������
      if (ValueUtils.getBoolean(transvo.getAttributeValue(billCode))) {
        if (StringUtils.isEmpty(vos[i].getVbillcode())) {
          if (!"".equals(retSb.toString())) {
            retSb.append(", ");
          }
          retSb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4004030_0", "04004030-0308", null, new String[] {
                crowno, statusName
              })/* ��{0}��[{1}����]����Ϊ�� */);
        }
      }
      // ��������
      if (ValueUtils.getBoolean(transvo.getAttributeValue(billDate))) {
        if (null == vos[i].getDbilldate()) {
          if (!"".equals(retSb.toString())) {
            retSb.append(", ");
          }
          retSb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4004030_0", "04004030-0309", null, new String[] {
                crowno, statusName
              })/* ��{0}��[{1}����]����Ϊ�� */);
        }
      }
    }
    if (!"".equals(retSb.toString())) {
      String retStr =
          NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
              "04004030-0226", null, new String[] {
                retSb.toString()
              })/* ѡ���{0} */;
      throw new BusinessException(retStr);
    }
  }

  /**
   * ����������������鵥�ݺţ����������ڲ�Ϊ�գ�������
   * <p>
   * <b>����˵��</b>
   * 
   * @param transvo
   *          ��������VO
   * @param vos
   *          ҪУ���VO
   * @throws Exception
   *           <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-12 ����07:02:42
   */
  public static void chkEmptyForSendOut(PoTransTypeVO transvo,
      OrderOnwayItemVO[] vos) throws BusinessException {

    if (null == transvo) {
      return;
    }

    StringBuilder retSb = new StringBuilder();
    for (int i = 0; i < vos.length; i++) {
      String crowno = vos[i].getCrowno();
      // ��������
      if (ValueUtils.getBoolean(transvo.getBconsigncode())) {
        if (StringUtils.isEmpty(vos[i].getVbillcode())) {
          if (!"".equals(retSb.toString())) {
            retSb.append(", ");
          }
          retSb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4004030_0", "04004030-0310", null, new String[] {
                crowno
              })/* ��{0}��[��������]����Ϊ�� */);
        }
      }
      // ���η�����������Ϊ��
      if (ValueUtils.getBoolean(transvo.getBconsignnum())) {
        if (null == vos[i].getNsendoutnum()) {
          if (!"".equals(retSb.toString())) {
            retSb.append(", ");
          }
          retSb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4004030_0", "04004030-0311", null, new String[] {
                crowno
              })/* ��{0}��[���η�������]����Ϊ�� */);
        }
        // else {
        // UFDouble connum = ValueUtils.getUFDouble(
        // vos[i].getNconfirmnum());
        // if (connum.compareTo(vos[i].getNnum()) > 0) {
        // if (!"".equals(retStr))
        // retStr += ", ";
        // retStr += "��" + (i + 1) + "��ȷ���������ܴ��ڶ�������";
        // }
        // }
      }
      // ��������
      if (ValueUtils.getBoolean(transvo.getBconsigndate())) {
        if (null == vos[i].getDbilldate()) {
          if (!"".equals(retSb.toString())) {
            retSb.append(", ");
          }
          retSb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4004030_0", "04004030-0312", null, new String[] {
                crowno
              })/* ��{0}��[��������]����Ϊ�� */);
        }
      }
    }
    if (!"".equals(retSb.toString())) {
      String retStr =
          NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
              "04004030-0226", null, new String[] {
                retSb.toString()
              })/* ѡ���{0} */;
      throw new BusinessException(retStr);
    }
  }

  /**
   * ����������������ý�������VO
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-12 ����07:02:17
   */
  public static Map<String, PoTransTypeVO> getTransTypeVO(String vtranType[]) {

    HashMap<String, PoTransTypeVO> tranTypeMap = null;
    try {

      tranTypeMap =
          NCLocator.getInstance().lookup(IPoTransTypeQuery.class)
              .queryAttrByTypes(vtranType, null);

    }
    catch (BusinessException ex) {
      // ��־�쳣
      ExceptionUtils.wrappException(ex);
    }

    return tranTypeMap;
  }

}
