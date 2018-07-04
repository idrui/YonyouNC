package nc.vo.pu.est.rule.feedivide;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.measuredoc.MeasureDocService;
import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.costfactor.enumeration.ApportionMode;
import nc.vo.pu.est.entity.fee.FeeMnyDivideVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleObjectFactory;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>分摊依据数值生成工厂
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-24 下午07:54:51
 */
public class DivideByValueFactory {
  public abstract static class AbstractByValueGen {
    protected FeeMnyDivideVO[] divideVos;

    protected String pk_group;

    protected int unitVolumnPrecision = 2;

    protected int unitWeightPrecision = 2;

    /**
     * AbstractByValueGen 的构造子
     *
     * @param divideVos
     */
    public AbstractByValueGen(FeeMnyDivideVO[] divideVos, String pk_group) {
      this.divideVos = divideVos;
      this.pk_group = pk_group;
      this.initPrecision();
    }

    /**
     * 生成每个分摊依据的分摊占比
     */
    public void genByValue() {
      for (FeeMnyDivideVO divVo : this.divideVos) {
        String vid = divVo.getMvid();
        UFDouble unitval = this.getUnitValue(vid);
        UFDouble num = divVo.getBillnum();
        divVo.setByvalue(unitval.multiply(num, this.getUnitPrecision()));
      }
    }

    private String[] getMPks() {
      String[] pks = new String[this.divideVos.length];
      for (int i = 0; i < pks.length; i++) {
        pks[i] = this.divideVos[i].getMvid();
      }
      return pks;
    }

    private void initPrecision() {
      try {
        Map<String, String> paras =
            SysParaInitQuery.queryBatchParaValues(this.pk_group, new String[] {
              ScaleObjectFactory.SD_VN_SCALE, ScaleObjectFactory.SD_WT_SCALE
            });
        Integer[] digit =
            MeasureDocService.getMeasPrecision(new String[] {
              paras.get(ScaleObjectFactory.SD_VN_SCALE),
              paras.get(ScaleObjectFactory.SD_WT_SCALE)
            });
        if (ArrayUtils.isEmpty(digit) || 2 > digit.length) {
          return;
        }
        this.unitVolumnPrecision = digit[0] == null ? 2 : digit[0].intValue();
        this.unitWeightPrecision = digit[1] == null ? 2 : digit[1].intValue();
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }

    protected Map<String, MaterialVO> getMaterialUnitInfo() {
      return MaterialPubService.queryMaterialBaseInfo(this.getMPks(),
          new String[] {
            MaterialVO.UNITWEIGHT, MaterialVO.UNITVOLUME, MaterialVO.CODE
          });
    }

    protected int getUnitPrecision() {
      return 2;
    }

    /**
     * @param mvid 物料VID
     */
    protected UFDouble getUnitValue(String mvid) {
      UFDouble unitValue = UFDouble.ZERO_DBL;
      return unitValue;
    }
  }

  public static class ByMnyGen extends AbstractByValueGen {
    public ByMnyGen(FeeMnyDivideVO[] divideVos, String pk_group) {
      super(divideVos, pk_group);
    }

    @Override
    public void genByValue() {
      for (FeeMnyDivideVO divVo : this.divideVos) {
        divVo.setByvalue(divVo.getBillmny());
      }
    }
  }

  public static class ByNumGen extends AbstractByValueGen {
    public ByNumGen(FeeMnyDivideVO[] divideVos, String pk_group) {
      super(divideVos, pk_group);
    }

    @Override
    public void genByValue() {
      for (FeeMnyDivideVO divVo : this.divideVos) {
        divVo.setByvalue(divVo.getBillnum());
      }
    }
  }

  public static class ByVolumnGen extends AbstractByValueGen {
    public ByVolumnGen(FeeMnyDivideVO[] divideVos, String pk_group) {
      super(divideVos, pk_group);
    }

    @Override
    protected int getUnitPrecision() {
      return this.unitVolumnPrecision;
    }

    @Override
    protected UFDouble getUnitValue(String mvid) {
      UFDouble value = super.getUnitValue(mvid);
      MaterialVO mvo = this.getMaterialUnitInfo().get(mvid);
      if (null != mvo) {
        value = mvo.getUnitvolume();
      }
      if (UFDouble.ZERO_DBL.equals(MathTool.nvl(value))) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0123")/*@res "物料"*/
            + (null != mvo ? "[" + mvo.getCode() + "]" : "")
            + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0124")/*@res "按体积分摊，请定义其标准体积!"*/);
      }
      return value;
    }
  }

  public static class ByWeightGen extends AbstractByValueGen {
    public ByWeightGen(FeeMnyDivideVO[] divideVos, String pk_group) {
      super(divideVos, pk_group);
    }

    @Override
    protected int getUnitPrecision() {
      return this.unitWeightPrecision;
    }

    @Override
    protected UFDouble getUnitValue(String mvid) {
      UFDouble value = super.getUnitValue(mvid);
      MaterialVO mvo = this.getMaterialUnitInfo().get(mvid);
      if (null != mvo) {
        value = mvo.getUnitweight();
      }
      if (UFDouble.ZERO_DBL.equals(MathTool.nvl(value))) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0123")/*@res "物料"*/
            + (null != mvo ? "[" + mvo.getCode() + "]" : "")
            + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0125")/*@res "按重量分摊，请定义其标准重量!"*/);
      }
      return value;
    }
  }

  /** 得到分摊依据数值生成器 **/
  public static AbstractByValueGen getByValueGen(String pk_group,
      FeeMnyDivideVO[] divideVos, ApportionMode byMode) {
    if (ApportionMode.NUM == byMode) {
      return new ByNumGen(divideVos, pk_group);
    }
    else if (ApportionMode.MONEY == byMode) {
      return new ByMnyGen(divideVos, pk_group);
    }
    else if (ApportionMode.WEIGHT == byMode) {
      return new ByWeightGen(divideVos, pk_group);
    }
    else if (ApportionMode.VOLUME == byMode) {
      return new ByVolumnGen(divideVos, pk_group);
    }
    else {
      return null;
    }
  }
}