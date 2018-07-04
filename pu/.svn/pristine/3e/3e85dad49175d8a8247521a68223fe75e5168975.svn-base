package nc.vo.pu.upgrade.v63;

import java.io.File;

import nc.newinstall.update.IUpdateResource;

/**
 * 代码升级
 * sourcingrule,
 * vendorprice,pupricedeter组件是没有用的，60时就已经移到scmpub或者价格purp了。63时将其删除。
 * 在覆盖升级时会多出几个jar包，需要将其删除
 * 注册是在setup.ini文件中通过resourceupdateclass 项进行注册
 * 导出jar包放到安装盘的ext目录下
 * modules/pu/lib/pubpu_pupricedeter.jar
 * modules/pu/lib/pubpu_vendorprice.jar
 * modules/pu/lib/pubpu_sourcingrule.jar
 * modules/pu/client/lib/uipu_pupricedeter.jar
 * modules/pu/client/lib/uipu_sourcingrule.jar
 * modules/pu/client/lib/uipu_vendorprice.jar
 * modules/pu/META-INF/lib/pu_pupricedeter.jar
 * modules/pu/META-INF/lib/pu_sourcingrule.jar
 * modules/pu/META-INF/lib/pu_vendorprice.jar
 * modules/pu/META-INF/SCM_PU_vendorprice_EJB60.upm
 * 
 * @since 6.0
 * @version 2013-1-28 上午08:57:34
 * @author wuxla
 */
public class PUUpdateResourceFor63 implements IUpdateResource {

  @Override
  public void doAfterInstall(String ncHome, String newVersion, String oldVersion) {
    if (null == ncHome || ncHome.length() == 0) {
      return;
    }
    if (oldVersion.startsWith("6.1") && newVersion.startsWith("6.3")) {
      this.doAfterInstallFrom61To63(ncHome);
    }
  }

  @Override
  public void doBeforeInstall(String ncHome, String newVersion,
      String oldVersion) {

  }

  private void doAfterInstallFrom61To63(String ncHome) {
    String[] filepaths =
        new String[] {
          "/lib/pubpu_pupricedeter.jar", "/lib/pubpu_vendorprice.jar",
          "/lib/pubpu_sourcingrule.jar", "/client/lib/uipu_pupricedeter.jar",
          "/client/lib/uipu_sourcingrule.jar",
          "/client/lib/uipu_vendorprice.jar",
          "/META-INF/lib/pu_pupricedeter.jar",
          "/META-INF/lib/pu_sourcingrule.jar",
          "/META-INF/lib/pu_vendorprice.jar",
          "/META-INF/SCM_PU_vendorprice_EJB60.upm"
        };
    String puPath = ncHome + "/modules/pu";
    for (String filepath : filepaths) {
      String path = puPath + filepath;
      File file = new File(path);
      if (file.exists()) {
        file.delete();
      }
    }
  }
}
