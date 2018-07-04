package nc.vo.pu.upgrade.v631;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.common.RuntimeEnv;
import nc.bs.logging.Logger;
import nc.bs.sm.accountmanage.AbstractPatchInstall;
import nc.bs.sm.accountmanage.PatchInstallContext;
import nc.md.persist.designer.service.IPublishService;
import nc.vo.pub.BusinessException;

/**
 * 采购给进出口提供的61到631的元数据升级类
 * 
 * @since 6.3
 * @version 2013-10-22 上午11:03:55
 * @author fanly3
 */
public class UpgradeMetaData4JCK extends AbstractPatchInstall {

  private String[] bmfpath;

  private IPublishService service;

  public UpgradeMetaData4JCK() {
    this.bmfpath =
        new String[] {
          "/modules/pu/METADATA/po_pray.bmf",
          "/modules/pu/METADATA/po_order_v60.bmf",
          "/modules/pu/METADATA/po_invoice_v60.bmf",
          "/modules/pu/METADATA/po_stockps.bmf",
          "/modules/pu/METADATA/poinvoice_transtype.bmf",
          "/modules/pu/METADATA/po_settlebill.bmf",
          "/modules/pu/METADATA/invoicestockoptionable.bmf"
        };
  }

  /**
   * 平台元数据升级接口
   * 
   * @return
   */
  public IPublishService getPublishService() {
    if (this.service == null) {
      this.service = NCLocator.getInstance().lookup(IPublishService.class);
    }

    return this.service;
  }

  @Override
  public void pulishMetaData(PatchInstallContext context)
      throws BusinessException {
    String path;
    StringBuilder destPath;
    String nchome = RuntimeEnv.getInstance().getNCHome();
    String[] bmfs = this.bmfpath;
    int bmfLen = bmfs.length;
    for (int i = 0; i < bmfLen; i++) {
      path = bmfs[i];
      destPath = new StringBuilder(nchome);
      destPath.append(path);
      try {
        Logger.error(new StringBuilder().append("PU发布元数据：")
            .append(destPath.toString()).toString());
        this.getPublishService().publishMetaDataForBMF(destPath.toString());
      }
      catch (Exception ex) {
        Logger.error(ex.getMessage(), ex);
        Logger.error(new StringBuilder().append("PU发布元数据异常：")
            .append(destPath.toString()).append("ex.getMessage()").toString());
      }
    }
  }
}
