/**
 *
 */
package nc.vo.pu.m25.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * �ǿռ��:��ͷ�ǿ�:����֯,��������,�ɹ���֯, ��Ʊ����(��������), ��Ʊ����,
 * Ʊ������,��Ӧ��,����,ҵ��Ա,ԭ�ұ��֣����ұ���,��Ʊ����, 
 * ����ǿ�: �к�,����֯,��������,Ӧ����֯,����,��������λ,��λ
 * @scene
 * �����BP
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-22 ����3:22:33
 * @author zhangshqb
 */
public class InvoiceNotNullChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    StringBuffer buffer = new StringBuffer();
    for (InvoiceVO vo : vos) {
      // ��֤��ͷ
      InvoiceHeaderVO headerVO = vo.getParentVO();
      if (null == headerVO) {
        buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004050_0", "04004050-0058")/* @res "��ͷ����Ϊ��;\r\n" */);
      }
      else {
        StringBuffer headerVOBuffer = this.checkHeaderVO(headerVO);
        if (headerVOBuffer.length() != 0) {
          buffer.append(headerVOBuffer);
          buffer.append("\r\n");
        }
      }
      // ��֤����
      InvoiceItemVO[] invoiceItemVOs = vo.getChildrenVO();
      if (null != invoiceItemVOs) {
        for (InvoiceItemVO itemVO : invoiceItemVOs) {
          if (null == itemVO) {
            buffer
                .append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "4004050_0", "04004050-0059")/* @res "���岻��Ϊ��;\r\n" */);
            continue;
          }
          // ����δ�ı������ɾ����״̬,��������.
          if (itemVO.getStatus() == VOStatus.UNCHANGED
              || itemVO.getStatus() == VOStatus.DELETED) {
            continue;
          }
          StringBuffer itemVOBuffer = this.checkInvoiceItemVO(itemVO);
          if (itemVOBuffer.length() != 0) {
            buffer.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
                "4004050_0", "04004050-0112", null, new String[] {
                  itemVO.getCrowno()
                })/* ��{0}��: */);
            buffer.append(itemVOBuffer);
            buffer.append("\r\n");
          }
        }
      }
    }
    if (buffer.length() != 0) {
      // �׳�������Ϣ
      ExceptionUtils.wrappBusinessException(buffer.toString());
    }
  }

  /**
   * ������Ϣ����StringBuffer
   * 
   * @author xiebo
   * @time 2010-1-26 ����08:07:45
   * @param
   * @return
   * @throws
   */
  private void addErrorMsg(String msg, StringBuffer buffer) {
    buffer.append(msg);
  }

  /**
   * ��֤��ͷ ����д�����Ϣ�򷵻ش�����Ϣ
   * 
   * @author xiebo
   * @time 2010-1-26 ����08:05:58
   * @param headerVO :��ͷ
   * @return ��Ŵ�����Ϣ��StringBuffer
   * @throws
   */
  private StringBuffer checkHeaderVO(InvoiceHeaderVO headerVO) {
    StringBuffer headerVOBuffer = new StringBuffer();
    if (StringUtil.isEmptyWithTrim(headerVO.getPk_org())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0060")/* @res "������֯���°汾����Ϊ��;" */, headerVOBuffer);
    }
    if (StringUtil.isEmptyWithTrim(headerVO.getPk_org_v())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0061")/* @res "������֯����Ϊ��;" */, headerVOBuffer);
    }
    if (ObjectUtil.isEmptyWithTrim(headerVO)) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0062")/* @res "��ͷ����Ϊ��;" */, headerVOBuffer);
    }
    if (StringUtil.isEmptyWithTrim(headerVO.getPk_group())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0063")/* @res "�������Ų���Ϊ��;" */, headerVOBuffer);
    }
    // if (StringUtil.isEmptyWithTrim(headerVO.getPk_purchaseorg())) {
    // addErrorMsg("�ɹ���֯����Ϊ��;", headerVOBuffer);
    // }
    if (StringUtil.isEmptyWithTrim(headerVO.getCtrantypeid())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0064")/* @res "��Ʊ���Ͳ���Ϊ��;" */, headerVOBuffer);
    }
    // if (StringUtil.isEmptyWithTrim(headerVO.getVtrantypecode())) {
    // this.addErrorMsg("��Ʊ���Ͳ���Ϊ��;", headerVOBuffer);
    // }
    if (ObjectUtil.isEmptyWithTrim(headerVO.getDbilldate())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0065")/* @res "��Ʊ���ڲ���Ϊ��;" */, headerVOBuffer);
    }
    if (ObjectUtil.isEmptyWithTrim(headerVO.getDarrivedate())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0066")/* @res "Ʊ�����ڲ���Ϊ��;" */, headerVOBuffer);
    }
    if (StringUtil.isEmptyWithTrim(headerVO.getPk_supplier())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0067")/* @res "��Ӧ�̲���Ϊ��;" */, headerVOBuffer);
    }
    // if (StringUtil.isEmptyWithTrim(headerVO.getPk_bizpsn())) {
    // addErrorMsg("ҵ��Ա����Ϊ��;", headerVOBuffer);
    // }
    if (StringUtil.isEmptyWithTrim(headerVO.getCorigcurrencyid())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0068")/* @res "ԭ�ұ��ֲ���Ϊ��;" */, headerVOBuffer);
    }
    if (StringUtil.isEmptyWithTrim(headerVO.getCcurrencyid())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0069")/* @res "���ұ��ֲ���Ϊ��;" */, headerVOBuffer);
    }
    if (ObjectUtil.isEmptyWithTrim(headerVO.getFinvoiceclass())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0070")/* @res "��Ʊ���಻��Ϊ��;" */, headerVOBuffer);
    }

    // �ǿ�У�飬�ȴ��Ժ����ã�������ʱû��У��
    if (StringUtil.isEmptyWithTrim(headerVO.getCrececountryid())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0120")/* @res "�ջ�������Ϊ��;" */, headerVOBuffer);
    }

    if (StringUtil.isEmptyWithTrim(headerVO.getCsendcountryid())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0121")/* @res "����������Ϊ��;" */, headerVOBuffer);
    }

    if (ObjectUtil.isEmptyWithTrim(headerVO.getCtaxcountryid())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0122")/* @res "��˰������Ϊ��;" */, headerVOBuffer);
    }

    if (ObjectUtil.isEmptyWithTrim(headerVO.getFbuysellflag())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0123")/* @res "�������Ͳ���Ϊ��;" */, headerVOBuffer);
    }

    return headerVOBuffer;
  }

  /**
   * ��֤���� ����д�����Ϣ�򷵻ش�����Ϣ
   * 
   * @author xiebo
   * @time 2010-1-26 ����08:07:12
   * @param invoiceItemVO :����
   * @return �д�����Ϣ��StringBuffer
   * @throws
   */
  private StringBuffer checkInvoiceItemVO(InvoiceItemVO invoiceItemVO) {
    StringBuffer itemVOErrBuffer = new StringBuffer();
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getCrowno())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0071")/* @res "�кŲ���Ϊ��;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getPk_org())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0060")/* @res "������֯���°汾����Ϊ��;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getPk_org_v())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0061")/* @res "������֯����Ϊ��;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getPk_group())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0063")/* @res "�������Ų���Ϊ��;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getPk_apfinanceorg())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0072")/* @res "Ӧ����֯���°汾����Ϊ��;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getPk_apfinanceorg_v())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0073")/* @res "Ӧ����֯����Ϊ��;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getPk_material())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0074")/* @res "���ϲ���Ϊ��;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getCunitid())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0075")/* @res "����λ����Ϊ��;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getCastunitid())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0076")/* @res "��λ����Ϊ��;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getVchangerate())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0077")/* @res "�����ʲ���Ϊ��;" */, itemVOErrBuffer);
    }
    // �ǿ�У�飬�ȴ��Ժ����ã�������ʱû��У��
    // if (StringUtil.isEmptyWithTrim(invoiceItemVO.getCtaxcodeid())) {
    // this.addErrorMsg(
    // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
    // "04004050-0124")/* @res "˰�벻��Ϊ��;" */, itemVOErrBuffer);
    // }
    //
    // if (ObjectUtil.isEmptyWithTrim(invoiceItemVO.getFtaxtypeflag())) {
    // this.addErrorMsg(
    // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
    // "04004050-0125")/* @res "��˰��� ����Ϊ��;" */, itemVOErrBuffer);
    // }
    return itemVOErrBuffer;
  }

}
