package nc.bs.pu.m25.pfxx;

import nc.bs.pu.m25.maintain.InvoiceSaveBP;
import nc.impl.pu.m25.action.InvoiceDeleteAction;
import nc.impl.pu.m25.action.InvoiceInsertAction;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.pfxx.plugins.AbstractPuPfxxPlugin;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmf.pub.util.BatchNCBaseTypeUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 采购发票的外部导入处理类
 * 
 * @since 6.0
 * @version 2011-4-26 上午10:09:53
 * @author 田锋涛
 */

public class M25PfxxPlugin extends AbstractPuPfxxPlugin {

  private void checkMny(AggregatedValueObject vo) {
    StringBuilder errrows = new StringBuilder();
    InvoiceHeaderVO orderHVO = (InvoiceHeaderVO) vo.getParentVO();
    Integer fbuysellflag = orderHVO.getFbuysellflag();
    InvoiceItemVO[] bodyvos = ((InvoiceVO) vo).getChildrenVO();
    for (InvoiceItemVO bodyvo : bodyvos) {
      // 无税金额
      UFDouble nmny = bodyvo.getNmny();
      // 价税合计
      UFDouble ntaxmny = bodyvo.getNtaxmny();
      // 税额
      UFDouble ntax = bodyvo.getNtax();
      // 进口采购
      if (BuySellFlagEnum.IMPORT.value().equals(fbuysellflag)) {
        if (!nmny.equals(ntaxmny)) {
          errrows.append(bodyvo.getCrowno() + ",");
        }
      }
      // 国内采购
      else if (BuySellFlagEnum.NATIONAL_BUY.value().equals(fbuysellflag)) {
        if (!nmny.add(ntax).equals(ntaxmny)) {
          errrows.append(bodyvo.getCrowno() + ",");
        }
      }
      else {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004000_0", "04004000-0140", null,
                new String[] {
                  bodyvo.getCrowno()
                })/*
                   * @res
                   * "采购发票表头购销类型设置不正确！"
                   */);
      }
    }
    if (errrows.length() <= 0) {
      return;
    }
    errrows.deleteCharAt(errrows.length() - 1);
    ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004000_0", "04004000-0137", null,
            new String[] {
              errrows.toString()
            })/*
               * @res
               * "行{0}无税金额、税额、价税合计计算关系错误不可导入！"
               */);
  }

  /**
   * 存在下游回写信息的单据控制不可导入
   * 
   * @param vo
   */
  private void checkRwite(AggregatedValueObject vo) {
    InvoiceItemVO[] bodyvos = ((InvoiceVO) vo).getChildrenVO();
    for (InvoiceItemVO bodyvo : bodyvos) {
      StringBuilder errstr = new StringBuilder();
      // 累计本币结算金额
      UFDouble naccumsettmny = bodyvo.getNaccumsettmny();
      // 累计结算主数量
      UFDouble naccumsettnum = bodyvo.getNaccumsettnum();
      if (!BatchNCBaseTypeUtils.isNullOrZero(naccumsettmny)) {
        errstr.append(InvoiceItemVO.NACCUMSETTMNY + ",");
      }
      if (!BatchNCBaseTypeUtils.isNullOrZero(naccumsettnum)) {
        errstr.append(InvoiceItemVO.NACCUMSETTNUM + ",");
      }
      if (errstr.length() <= 0) {
        return;
      }
      errstr.deleteCharAt(errstr.length() - 1);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0138", null,
              new String[] {
                bodyvo.getCrowno(), errstr.toString()
              })/*
                 * @res
                 * "行:{0}存在回写数据[{1}],不可导入！"
                 */);
    }
  }

  /**
   * 检验vo是否可导入
   * 
   * @param vo
   */
  @Override
  protected void checkCanInster(AggregatedValueObject vo) {
    super.checkCanInster(vo);
    this.checkMny(vo);
    this.checkRwite(vo);
  }

  @Override
  protected void deleteVO(AggregatedValueObject vo) {
    new InvoiceDeleteAction().delete(new InvoiceVO[] {
      (InvoiceVO) vo
    }, null);
  }

  @Override
  protected String getChildrenPkFiled() {
    return InvoiceItemVO.PK_INVOICE_B;
  }

  @Override
  protected String getParentPkFiled() {
    return InvoiceHeaderVO.PK_INVOICE;
  }

  @Override
  protected AggregatedValueObject insert(AggregatedValueObject vo) {
    if (vo != null) {
      this.checkCanInster(vo);
    }
    return new InvoiceInsertAction().insert(new InvoiceVO[] {
      (InvoiceVO) vo
    }, null)[0];
  }

  @Override
  protected AggregatedValueObject queryVOByPk(String voPk) {
    BillQuery<InvoiceVO> billquery = new BillQuery<InvoiceVO>(InvoiceVO.class);
    InvoiceVO[] vos = billquery.query(new String[] {
      voPk
    });
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    return vos[0];
  }

  @Override
  protected AggregatedValueObject update(AggregatedValueObject updatevo,
      AggregatedValueObject origVO) {
    InvoiceUIToBSEnv env = new InvoiceUIToBSEnv();
    InvoiceVO[] savedVos = new InvoiceSaveBP(env).save(null, new InvoiceVO[] {
      (InvoiceVO) updatevo
    }, new InvoiceVO[] {
      (InvoiceVO) origVO
    });
    return savedVos[0];
  }

}
