/**
 * 
 */
package nc.vo.pu.m25.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.MMBillType;

/**
 * 
 * @description
 * 发票来源信息检查:检查发票来源（源头）信息是否完整：有来源单据类型必须有来源单据号，
 * id,bid,行号，交易类型！有源头单据类型必须有源头单据号，id ,bid,行号，交易类型
 * @scene
 * 保存的BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:18:55
 * @author zhangshqb
 */
public class InvoiceSourceInfoChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      StringBuilder error = new StringBuilder();
      StringBuilder errorSource = this.checkSource(vo);
      StringBuilder errorFirst = new StringBuilder();
      // 20140827 mengjian 来源加工费结算单的发票源头信息可能不全
      if (!MMBillType.PscSettle.getCode().equals(
          vo.getChildrenVO()[0].getCsourcetypecode())) {
        errorFirst = this.checkFirst(vo);
      }
      StringBuilder errorPO = this.checkPO(vo);
      String cr = System.getProperty("line.separator");
      if (0 < errorSource.length()) {
        error.append(errorSource).append(cr);
      }
      if (0 < errorFirst.length()) {
        error.append(errorFirst).append(cr);
      }
      if (0 < errorPO.length()) {
        error.append(errorPO);
      }
      if (0 < error.length()) {
        ExceptionUtils.wrappBusinessException(error.toString());
      }
    }

  }

  /** 检查源头单据信息 **/
  private StringBuilder checkFirst(InvoiceVO vo) {
    StringBuilder builder = new StringBuilder();
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      boolean noFirst =
          StringUtil.isEmptyWithTrim(item.getCfirsttypecode())
              && StringUtil.isEmptyWithTrim(item.getCfirstid())
              && StringUtil.isEmptyWithTrim(item.getCfirstbid())
              && StringUtil.isEmptyWithTrim(item.getVfirsttrantype())
              && StringUtil.isEmptyWithTrim(item.getVfirstcode());
      // && StringUtil.isEmptyWithTrim(item.getVfirstrowno());
      boolean ret =
          noFirst || !StringUtil.isEmptyWithTrim(item.getCfirsttypecode())
              && !StringUtil.isEmptyWithTrim(item.getCfirstid())
              && !StringUtil.isEmptyWithTrim(item.getCfirstbid())
              && !StringUtil.isEmptyWithTrim(item.getVfirsttrantype())
              && !StringUtil.isEmptyWithTrim(item.getVfirstcode());
      // && !StringUtil.isEmptyWithTrim(item.getVfirstrowno());
      if (!ret) {
        builder.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004050_0", "04004050-0114", null, new String[] {
              item.getCrowno()
            })/* 第{0}行的源头单据信息不完整，请检查！ */);
      }
    }
    return builder;
  }

  /** 检查来源采购订单信息 **/
  private StringBuilder checkPO(InvoiceVO vo) {
    StringBuilder builder = new StringBuilder();
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      boolean noFirst =
          StringUtil.isEmptyWithTrim(item.getPk_order())
              && StringUtil.isEmptyWithTrim(item.getPk_order_b())
              && StringUtil.isEmptyWithTrim(item.getVordercode())
              && StringUtil.isEmptyWithTrim(item.getVordertrantype());
      boolean ret =
          noFirst || !StringUtil.isEmptyWithTrim(item.getPk_order())
              && !StringUtil.isEmptyWithTrim(item.getPk_order_b())
              && !StringUtil.isEmptyWithTrim(item.getVordercode())
              && !StringUtil.isEmptyWithTrim(item.getVordertrantype());
      if (!ret) {
        builder.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004050_0", "04004050-0114", null, new String[] {
              item.getCrowno()
            })/* 第{0}行的源头单据信息不完整，请检查！ */);
      }
    }
    return builder;
  }

  /** 检查来源单据信息 **/
  private StringBuilder checkSource(InvoiceVO vo) {
    StringBuilder builder = new StringBuilder();
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      boolean noSource =
          StringUtil.isEmptyWithTrim(item.getCsourcetypecode())
              && StringUtil.isEmptyWithTrim(item.getCsourceid())
              && StringUtil.isEmptyWithTrim(item.getCsourcebid())
              && StringUtil.isEmptyWithTrim(item.getVsourcetrantype())
              && StringUtil.isEmptyWithTrim(item.getVsourcecode());
      // && StringUtil.isEmptyWithTrim(item.getVsourcerowno());
      boolean ret =
          noSource || !StringUtil.isEmptyWithTrim(item.getCsourcetypecode())
              && !StringUtil.isEmptyWithTrim(item.getCsourceid())
              && !StringUtil.isEmptyWithTrim(item.getCsourcebid())
              && !StringUtil.isEmptyWithTrim(item.getVsourcetrantype())
              && !StringUtil.isEmptyWithTrim(item.getVsourcecode());
      // && !StringUtil.isEmptyWithTrim(item.getVsourcerowno());
      if (!ret) {
        builder.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004050_0", "04004050-0115", null, new String[] {
              item.getCrowno()
            })/* 第{0}行的来源单据信息不完整，请检查！ */);
      }
    }
    return builder;
  }

}
