package nc.vo.pu.upgrade.v63;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.common.RuntimeEnv;
import nc.bs.logging.Logger;
import nc.bs.upgrade.AbstractSCMUpgrade;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.md.persist.designer.service.IPublishService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.DBHintConstantValue;

import org.apache.commons.lang.StringUtils;

/**
 * 采购管理v63->v63EHP升级处理类
 * 
 * @since 6.3ehp
 * @version 2014-5-20 上午09:06:52
 * @author zhangyhh
 */
public class PUUpgradeToV63EHP extends AbstractSCMUpgrade {

  private IPublishService service;

  @Override
  public void doAfterUpdateData(String oldVersion, String newVersion)
      throws Exception {
    if (StringUtils.isEmpty(oldVersion) || StringUtils.isEmpty(newVersion)) {
      return;
    }
    if (oldVersion.startsWith("6.3") && newVersion.startsWith("6.3")) {
      this.upgradeDefaultValue();
    }
  }

  @Override
  public void doBeforeUpdateData(String oldVersion, String newVersion)
      throws Exception {
    if (StringUtils.isEmpty(oldVersion) || StringUtils.isEmpty(newVersion)) {
      return;
    }
    if (oldVersion.startsWith("6.3") && newVersion.startsWith("6.3")) {
      this.doUpdateDataToV63EHP();
    }
  }

  private void doUpdateDataToV63EHP() throws BusinessException {
    try {
      this.pulishMetaData();

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  private IPublishService getPublishService() {
    if (this.service == null) {
      this.service = NCLocator.getInstance().lookup(IPublishService.class);
    }

    return this.service;
  }

  private void pulishMetaData() {

    List<String> bmfs = new ArrayList<String>();
    bmfs.add("/modules/pu/METADATA/po_pray.bmf");
    bmfs.add("/modules/pu/METADATA/po_req_v60.bmf");

    String nchome = RuntimeEnv.getInstance().getNCHome();

    for (String bmf : bmfs) {

      StringBuilder destPath = new StringBuilder(nchome);
      destPath.append(bmf);
      try {
        Logger.info(new StringBuilder().append("发布元数据：")
            .append(destPath.toString()).toString());

        this.getPublishService().publishMetaDataForBMF(destPath.toString());
      }
      catch (Exception ex) {
        Logger.error(ex.getMessage(), ex);
        Logger.info(new StringBuilder().append("发布元数据异常：")
            .append(destPath.toString()).append(ex.getMessage()).toString());
      }
    }
  }

  private void upgradeDefaultValue() {
    String parallel_storereq = DBHintConstantValue.getHintCode("po_storereq");
    DataAccessUtils utils = new DataAccessUtils();
    utils.update("update " + parallel_storereq
        + " po_storereq  set  freqtypeflag = 0 where freqtypeflag is null  ");
    parallel_storereq = DBHintConstantValue.getHintCode("po_storereq_b");
    utils
        .update("update "
            + parallel_storereq
            + " po_storereq_b  set pk_reqstoorg=pk_org,pk_nextbalanceorg=pk_org,"
            + "pk_reqstoorg_v=pk_org_v,pk_nextbalanceorg_v=pk_org_v,bendgather='N'  "
            + "where pk_reqstoorg is null and pk_nextbalanceorg is null ");

  }
}
