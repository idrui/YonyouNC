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
 * 非空检查:表头非空:主组织,所属集团,采购组织, 发票类型(交易类型), 发票日期,
 * 票到日期,供应商,部门,业务员,原币币种，本币币种,发票分类, 
 * 表体非空: 行号,主组织,所属集团,应付组织,物料,主计量单位,单位
 * @scene
 * 保存的BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:22:33
 * @author zhangshqb
 */
public class InvoiceNotNullChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    StringBuffer buffer = new StringBuffer();
    for (InvoiceVO vo : vos) {
      // 验证表头
      InvoiceHeaderVO headerVO = vo.getParentVO();
      if (null == headerVO) {
        buffer.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004050_0", "04004050-0058")/* @res "表头不能为空;\r\n" */);
      }
      else {
        StringBuffer headerVOBuffer = this.checkHeaderVO(headerVO);
        if (headerVOBuffer.length() != 0) {
          buffer.append(headerVOBuffer);
          buffer.append("\r\n");
        }
      }
      // 验证表体
      InvoiceItemVO[] invoiceItemVOs = vo.getChildrenVO();
      if (null != invoiceItemVOs) {
        for (InvoiceItemVO itemVO : invoiceItemVOs) {
          if (null == itemVO) {
            buffer
                .append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "4004050_0", "04004050-0059")/* @res "表体不能为空;\r\n" */);
            continue;
          }
          // 表体未改变或者已删除的状态,不做处理.
          if (itemVO.getStatus() == VOStatus.UNCHANGED
              || itemVO.getStatus() == VOStatus.DELETED) {
            continue;
          }
          StringBuffer itemVOBuffer = this.checkInvoiceItemVO(itemVO);
          if (itemVOBuffer.length() != 0) {
            buffer.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
                "4004050_0", "04004050-0112", null, new String[] {
                  itemVO.getCrowno()
                })/* 第{0}行: */);
            buffer.append(itemVOBuffer);
            buffer.append("\r\n");
          }
        }
      }
    }
    if (buffer.length() != 0) {
      // 抛出错误信息
      ExceptionUtils.wrappBusinessException(buffer.toString());
    }
  }

  /**
   * 错误信息加入StringBuffer
   * 
   * @author xiebo
   * @time 2010-1-26 下午08:07:45
   * @param
   * @return
   * @throws
   */
  private void addErrorMsg(String msg, StringBuffer buffer) {
    buffer.append(msg);
  }

  /**
   * 验证表头 如果有错误信息则返回错误信息
   * 
   * @author xiebo
   * @time 2010-1-26 下午08:05:58
   * @param headerVO :表头
   * @return 存放错误信息的StringBuffer
   * @throws
   */
  private StringBuffer checkHeaderVO(InvoiceHeaderVO headerVO) {
    StringBuffer headerVOBuffer = new StringBuffer();
    if (StringUtil.isEmptyWithTrim(headerVO.getPk_org())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0060")/* @res "财务组织最新版本不能为空;" */, headerVOBuffer);
    }
    if (StringUtil.isEmptyWithTrim(headerVO.getPk_org_v())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0061")/* @res "财务组织不能为空;" */, headerVOBuffer);
    }
    if (ObjectUtil.isEmptyWithTrim(headerVO)) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0062")/* @res "表头不能为空;" */, headerVOBuffer);
    }
    if (StringUtil.isEmptyWithTrim(headerVO.getPk_group())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0063")/* @res "所属集团不能为空;" */, headerVOBuffer);
    }
    // if (StringUtil.isEmptyWithTrim(headerVO.getPk_purchaseorg())) {
    // addErrorMsg("采购组织不能为空;", headerVOBuffer);
    // }
    if (StringUtil.isEmptyWithTrim(headerVO.getCtrantypeid())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0064")/* @res "发票类型不能为空;" */, headerVOBuffer);
    }
    // if (StringUtil.isEmptyWithTrim(headerVO.getVtrantypecode())) {
    // this.addErrorMsg("发票类型不能为空;", headerVOBuffer);
    // }
    if (ObjectUtil.isEmptyWithTrim(headerVO.getDbilldate())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0065")/* @res "发票日期不能为空;" */, headerVOBuffer);
    }
    if (ObjectUtil.isEmptyWithTrim(headerVO.getDarrivedate())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0066")/* @res "票到日期不能为空;" */, headerVOBuffer);
    }
    if (StringUtil.isEmptyWithTrim(headerVO.getPk_supplier())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0067")/* @res "供应商不能为空;" */, headerVOBuffer);
    }
    // if (StringUtil.isEmptyWithTrim(headerVO.getPk_bizpsn())) {
    // addErrorMsg("业务员不能为空;", headerVOBuffer);
    // }
    if (StringUtil.isEmptyWithTrim(headerVO.getCorigcurrencyid())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0068")/* @res "原币币种不能为空;" */, headerVOBuffer);
    }
    if (StringUtil.isEmptyWithTrim(headerVO.getCcurrencyid())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0069")/* @res "本币币种不能为空;" */, headerVOBuffer);
    }
    if (ObjectUtil.isEmptyWithTrim(headerVO.getFinvoiceclass())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0070")/* @res "发票分类不能为空;" */, headerVOBuffer);
    }

    // 非空校验，等待以后启用，现在暂时没法校验
    if (StringUtil.isEmptyWithTrim(headerVO.getCrececountryid())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0120")/* @res "收货国不能为空;" */, headerVOBuffer);
    }

    if (StringUtil.isEmptyWithTrim(headerVO.getCsendcountryid())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0121")/* @res "发货国不能为空;" */, headerVOBuffer);
    }

    if (ObjectUtil.isEmptyWithTrim(headerVO.getCtaxcountryid())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0122")/* @res "报税国不能为空;" */, headerVOBuffer);
    }

    if (ObjectUtil.isEmptyWithTrim(headerVO.getFbuysellflag())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0123")/* @res "购销类型不能为空;" */, headerVOBuffer);
    }

    return headerVOBuffer;
  }

  /**
   * 验证表体 如果有错误信息则返回错误信息
   * 
   * @author xiebo
   * @time 2010-1-26 下午08:07:12
   * @param invoiceItemVO :表体
   * @return 有错误信息的StringBuffer
   * @throws
   */
  private StringBuffer checkInvoiceItemVO(InvoiceItemVO invoiceItemVO) {
    StringBuffer itemVOErrBuffer = new StringBuffer();
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getCrowno())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0071")/* @res "行号不能为空;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getPk_org())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0060")/* @res "财务组织最新版本不能为空;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getPk_org_v())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0061")/* @res "财务组织不能为空;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getPk_group())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0063")/* @res "所属集团不能为空;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getPk_apfinanceorg())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0072")/* @res "应付组织最新版本不能为空;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getPk_apfinanceorg_v())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0073")/* @res "应付组织不能为空;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getPk_material())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0074")/* @res "物料不能为空;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getCunitid())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0075")/* @res "主单位不能为空;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getCastunitid())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0076")/* @res "单位不能为空;" */, itemVOErrBuffer);
    }
    if (StringUtil.isEmptyWithTrim(invoiceItemVO.getVchangerate())) {
      this.addErrorMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0077")/* @res "换算率不能为空;" */, itemVOErrBuffer);
    }
    // 非空校验，等待以后启用，现在暂时没法校验
    // if (StringUtil.isEmptyWithTrim(invoiceItemVO.getCtaxcodeid())) {
    // this.addErrorMsg(
    // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
    // "04004050-0124")/* @res "税码不能为空;" */, itemVOErrBuffer);
    // }
    //
    // if (ObjectUtil.isEmptyWithTrim(invoiceItemVO.getFtaxtypeflag())) {
    // this.addErrorMsg(
    // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
    // "04004050-0125")/* @res "扣税类别 不能为空;" */, itemVOErrBuffer);
    // }
    return itemVOErrBuffer;
  }

}
