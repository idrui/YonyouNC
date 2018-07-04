package nc.pubimpl.pu.tbb;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import nc.itf.tb.control.IAccessableBusiVO;
import nc.itf.tb.control.IBusiSysExecDataProvider;
import nc.pubimpl.pu.tbb.fetchdata.PUFetchExecData;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.tb.obj.NtbParamVO;

/**
 * 预算执行取数
 * 
 * @since 6.0
 * @version 2011-3-7 上午11:54:36
 * @author yinfy
 */

public class PuExecDataProvider implements IBusiSysExecDataProvider {

  @Override
  public void createBillType(NtbParamVO[] param) throws BusinessException {
  }

  @Override
  public int getCtlPoint(String sysid) throws RemoteException {
    return 0;
  }

  @Override
  public IAccessableBusiVO[] getCvtProvider(IAccessableBusiVO[] runvos)
      throws RemoteException {
    return null;
  }

  @Override
  public UFDouble[] getExecData(NtbParamVO param) throws BusinessException {
    return this.getExecDataBatch(new NtbParamVO[] {
      param
    })[0];
  }

  @Override
  public UFDouble[][] getExecDataBatch(NtbParamVO[] param)
      throws BusinessException {
    UFDouble[][] ret = null;
    try {
      PUFetchExecData fetchData = new PUFetchExecData();
      ret = fetchData.fetchExecData(param);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return ret;
  }

  @Override
  public Map<NtbParamVO, Map<String, UFDouble[]>> getExecDataGroupBatch(
      String groupDocType, Map<NtbParamVO, List<String>> groupParaVOs,
      Map<String, String[]> childGroupDocs) throws BusinessException {
    // TODO liangchen1 补充规则
    return null;
  }

  @Override
  public UFDouble[] getPointData(NtbParamVO param) throws BusinessException {
    return null;
  }

  @Override
  public UFDouble[][] getPointDataBatch(NtbParamVO[] param)
      throws BusinessException {
    return null;
  }

  @Override
  public UFDouble[] getReadyData(NtbParamVO param) throws BusinessException {
    return this.getExecDataBatch(new NtbParamVO[] {
      param
    })[0];
  }

  @Override
  public UFDouble[][] getReadyDataBatch(NtbParamVO[] param)
      throws BusinessException {
    UFDouble[][] ret = null;
    try {
      PUFetchExecData fetchData = new PUFetchExecData();
      ret = fetchData.fetchExecData(param);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return ret;
  }

  @Override
  public Map<NtbParamVO, Map<String, UFDouble[]>> getReadyDataGroupBatch(
      String groupDocType, Map<NtbParamVO, List<String>> groupParaVOs,
      Map<String, String[]> childGroupDocs) throws BusinessException {
    // TODO liangchen1 补充规则
    return null;
  }
}
