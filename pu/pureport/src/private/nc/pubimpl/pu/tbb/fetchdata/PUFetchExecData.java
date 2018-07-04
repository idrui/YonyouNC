package nc.pubimpl.pu.tbb.fetchdata;

import java.util.Map;

import nc.pubimpl.pu.tbb.ExecDataProviderFactory;
import nc.pubimpl.pu.tbb.strategy.IExecDataProviderStrategy;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-3-7 ÏÂÎç01:26:53
 * @author yinfy
 */

public class PUFetchExecData {
  public UFDouble[][] fetchExecData(NtbParamVO[] paras) {
    UFDouble[][] result = new UFDouble[paras.length][4];
    Map<IExecDataProviderStrategy, NtbParamVO[]> fetchMap =
        ExecDataProviderFactory.getInstance()
            .getExecDataProviderStrategy(paras);
    try {
      for (Map.Entry<IExecDataProviderStrategy, NtbParamVO[]> ite : fetchMap
          .entrySet()) {
        NtbParamVO[] partParas = ite.getValue();
        UFDouble[][] partial = ite.getKey().getExecDataBatch(partParas);
        this.combineResult(paras, partParas, result, partial);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return result;
  }

  private void combineResult(NtbParamVO[] paras, NtbParamVO[] partParas,
      UFDouble[][] result, UFDouble[][] partial) {
    for (int i = 0; i < paras.length; ++i) {
      for (int j = 0; j < partParas.length; ++j) {
        if (paras[i].equals(partParas[j])) {
          result[i] = partial[j];
        }
      }
    }
  }
  
}
