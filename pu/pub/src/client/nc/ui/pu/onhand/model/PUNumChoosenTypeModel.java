package nc.ui.pu.onhand.model;

import nc.ui.ic.onhand.model.NumChoosenTypeModel;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.constenum.DefaultConstEnum;

public class PUNumChoosenTypeModel extends NumChoosenTypeModel {

  private static final long serialVersionUID = -5881357336589751662L;

  @Override
  public void init() {
    super.init();
    this.setSelectedItem(new DefaultConstEnum(NumChoosenTypeModel.NONENUM,
        NCLangRes.getInstance().getStrByID("4004000_0", "04004000-0157")));
  }

}
