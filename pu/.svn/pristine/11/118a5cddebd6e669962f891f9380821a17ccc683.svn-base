package nc.vo.pu.m422x.rule.api.fill;

import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.org.DeptPubService;
import nc.vo.scmpub.fill.vofill.OIDSetterByVIDVOFill;

public class FillDeptVidInfo extends OIDSetterByVIDVOFill {
  
  private String oidItemField ;
  
  private String vidItemField;

  public FillDeptVidInfo(String oidItemField, String vidItemField) {
    super();
    this.oidItemField = oidItemField;
    this.vidItemField = vidItemField;
  }

  @Override
  protected String getOIDItemFieldName() {
    return this.oidItemField;
  }

  @Override
  protected String getVIDItemFieldName() {
    return this.vidItemField;
  }

  @Override
  protected Map<String, String> getVIDAndOIDMap(List<String> vids) {
    return DeptPubService.getLastVIDSByDeptIDS(vids.toArray(new String[0]));
  }
  
}
