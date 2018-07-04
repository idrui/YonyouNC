package nc.bs.pu.m20.pfxx;

import nc.bs.pu.m20.maintain.PraybillUpdateBP;
import nc.impl.pu.m20.action.PraybillDeleteAction;
import nc.impl.pu.m20.action.PraybillInsertAction;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.scmmm.vo.scmpub.scale.BillVOScaleCheckProcessor;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.PrayBillScaleRule;
import nc.vo.pu.pfxx.plugins.AbstractPuPfxxPlugin;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmf.pub.util.BatchNCBaseTypeUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-5-5 下午09:01:22
 * @author 田锋涛
 */

public class M20PfxxPlugin extends AbstractPuPfxxPlugin {

  /**
   * 存在下游回写信息的单据控制不可导入
   * 
   * @param vo
   */
  private void checkRwite(AggregatedValueObject vo) {
    PraybillItemVO[] bodyvos = ((PraybillVO) vo).getBVO();
    for (PraybillItemVO bodyvo : bodyvos) {
      StringBuilder errstr = new StringBuilder();
      // 累计订货数量
      UFDouble naccumulatenum = bodyvo.getNaccumulatenum();
      if (!BatchNCBaseTypeUtils.isNullOrZero(naccumulatenum)) {
        errstr.append(PraybillItemVO.NACCUMULATENUM + ",");
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
   * 严格控制数量/单价/金额的精度与系统一致，不一致时不可导入
   * 
   * @param vo
   */
  private void checkScale(AggregatedValueObject vo) {
    String pk_group =
        (String) vo.getParentVO().getAttributeValue(PraybillHeaderVO.PK_GROUP);
    BillVOScaleCheckProcessor scale =
        new BillVOScaleCheckProcessor(pk_group, new PraybillVO[] {
          (PraybillVO) vo
        });
    new PrayBillScaleRule().setScaleForCheck(scale);
  }

  /**
   * 检验vo是否可导入
   * 
   * @param vo
   */
  @Override
  protected void checkCanInster(AggregatedValueObject vo) {
    super.checkCanInster(vo);
    this.checkRwite(vo);
    this.checkScale(vo);
  }

  @Override
  protected void deleteVO(AggregatedValueObject vo) {
    new PraybillDeleteAction().delete(new PraybillVO[] {
      (PraybillVO) vo
    });
  }

  @Override
  protected String getChildrenPkFiled() {
    return PraybillItemVO.PK_PRAYBILL_B;
  }

  @Override
  protected String getParentPkFiled() {
    return PraybillHeaderVO.PK_PRAYBILL;
  }

  @Override
  protected AggregatedValueObject insert(AggregatedValueObject vo) {
    if (vo != null) {
      this.checkCanInster(vo);
    }
    return new PraybillInsertAction().insert(new PraybillVO[] {
      (PraybillVO) vo
    })[0];
  }

  @Override
  protected AggregatedValueObject queryVOByPk(String voPk) {
    BillQuery<PraybillVO> billquery =
        new BillQuery<PraybillVO>(PraybillVO.class);
    PraybillVO[] praybills = billquery.query(new String[] {
      voPk
    });
    if (ArrayUtils.isEmpty(praybills)) {
      return null;
    }
    return praybills[0];
  }

  @Override
  protected AggregatedValueObject update(AggregatedValueObject updatevo,
      AggregatedValueObject origVO) {

    PraybillVO[] returnVos = new PraybillUpdateBP().update(new PraybillVO[] {
      (PraybillVO) updatevo
    }, new PraybillVO[] {
      (PraybillVO) origVO
    }, false);
    return returnVos[0];
  }

}
