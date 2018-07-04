package nc.vo.pu.pub.util;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.vo.pubapp.pattern.pub.AssertUtils;
import nc.vo.scmpub.res.para.NCPara;

/**
 * @since 6.0
 * @version 2011-5-14 ����01:03:39
 * @author wuxla
 */

public class PubSysParamUtil {

  /** ȫ�ֱ�λ�Ҽ��㷽ʽ�����ڱ�λ�Ҽ��� */
  public static final int GLOBAL_CURRY_BASE = 2;

  /** ȫ�ֱ�λ�Ҽ��㷽ʽ��������ȫ�ֱ�λ�� */
  public static final int GLOBAL_DISABLE = 0;

  /** ȫ�ֱ�λ�Ҽ��㷽ʽ������ԭ�Ҽ��� */
  public static final int GLOBAL_ORIG_BASE = 1;

  /** ���ű�λ�Ҽ��㷽ʽ�����ڱ�λ�Ҽ��� */
  public static final int GROUP_CURRY_BASE = 2;

  /** ���ű�λ�Ҽ��㷽ʽ�������ü��ű�λ�� */
  public static final int GROUP_DISABLE = 0;

  /** ���ű�λ�Ҽ��㷽ʽ������ԭ�Ҽ��� */
  public static final int GROUP_ORIG_BASE = 1;

  /**
   * ���ű�λ�Ҽ��㷽ʽ
   *
   * @return
   */
  public static String getNC001(String pk_group) {
    AssertUtils.assertValue(pk_group != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0091")/*@res "����Ϊ��"*/);
    return SysParaInitQuery.getParaString(pk_group, "NC001");
  }

  /**
   * ���ű�λ�Ҽ��㷽ʽ
   *
   * @return
   */
  public static int getNC001IntValue(String pk_group) {
    AssertUtils.assertValue(pk_group != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0091")/*@res "����Ϊ��"*/);
    String value = SysParaInitQuery.getParaString(pk_group, "NC001");
    if (NCPara.NC001_CALCULATEBYORIGCURRTYPE.getName().equals(value)) {
      return PubSysParamUtil.GROUP_ORIG_BASE;
    }
    else if (NCPara.NC001_CALCULATEBYCURRTYPE.getName().equals(value)) {
      return PubSysParamUtil.GROUP_CURRY_BASE;
    }
    // �����ü��ű�λ��
    return PubSysParamUtil.GROUP_DISABLE;
  }

  /**
   * ȫ�ֱ�λ�Ҽ��㷽ʽ
   *
   * @return
   */
  public static String getNC002() {
    return SysParaInitQuery.getParaString(IOrgConst.GLOBEORG, "NC002");
  }

  /**
   * ȫ�ֱ�λ�Ҽ��㷽ʽ
   *
   * @return
   */
  public static int getNC002IntValue() {
    String value = SysParaInitQuery.getParaString(IOrgConst.GLOBEORG, "NC002");
    if (NCPara.NC001_CALCULATEBYORIGCURRTYPE.getName().equals(value)) {
      return PubSysParamUtil.GLOBAL_ORIG_BASE;
    }
    else if (NCPara.NC001_CALCULATEBYCURRTYPE.getName().equals(value)) {
      return PubSysParamUtil.GLOBAL_CURRY_BASE;
    }
    // ������ȫ�ֱ�λ��
    return PubSysParamUtil.GLOBAL_DISABLE;
  }
}