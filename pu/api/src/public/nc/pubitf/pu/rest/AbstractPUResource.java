package nc.pubitf.pu.rest;

import uap.ws.rest.resource.AbstractUAPRestResource;

/**
 * @description
 *
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015年11月14日 下午2:41:48
 * @author wangweir
 */
public abstract class AbstractPUResource {

  /**
   * 
   */
  public static final String PU = "pu";

  public String getModule() {
    return PU;
  }

}
