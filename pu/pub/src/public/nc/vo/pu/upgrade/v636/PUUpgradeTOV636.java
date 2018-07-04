package nc.vo.pu.upgrade.v636;

import nc.scmmm.upgrade.scmpub.SCMProductVersion;
import nc.scmmm.upgrade.scmpub.v65.AbstractUpgradeToV65;
import nc.scmmm.upgrade.scmpub.version.ISCMUpdateAccount;

public class PUUpgradeTOV636 extends AbstractUpgradeToV65 {

  @Override
  protected ISCMUpdateAccount[] getUpgradeAccount() {
    return new ISCMUpdateAccount[] {
      new PUUpgradeV63TOV636(), // ��63������636
      new PUUpgradeV631TOV636(), // ��631������636
      new PUUpgradeV633TOV636() // ��633������636
    };
  }
  
  @Override
  public void doAfterUpdateData(String oldVersion, String newVersion)
      throws Exception {
    ISCMUpdateAccount process =
        this.getUpdateAaccount(oldVersion, newVersion);

    if (process != null) {
      process.doAfterUpdateData();
    }
  }

  private ISCMUpdateAccount getUpdateAaccount(String oldVersion,
      String newVersion) {
    if(SCMProductVersion.NC63.equalsVersion(oldVersion) 
        && SCMProductVersion.NC65.equalsVersion(newVersion)){
      return new PUUpgradeV63TOV636();
    } else if(SCMProductVersion.NC631.equalsVersion(oldVersion) 
        && SCMProductVersion.NC65.equalsVersion(newVersion)){
      return new PUUpgradeV631TOV636();
    } else if(SCMProductVersion.NC633.equalsVersion(oldVersion) 
        && SCMProductVersion.NC65.equalsVersion(newVersion)){
      return new PUUpgradeV633TOV636();
    } 
    return null;
  }

}
