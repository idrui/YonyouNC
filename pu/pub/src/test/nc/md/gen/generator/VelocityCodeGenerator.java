package nc.md.gen.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Properties;

import nc.bs.logging.Logger;
import nc.md.gen.CodeGenException;
import nc.md.gen.vmobj.IVMObject;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFDateTime;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * <b> Velocity非单例代码生成 </b>
 * <p>
 * 本类对Velocity非单例代码生成方式进行简单封装。 非单例方式会有一些性能上的损失，但带来配置上的灵活性：支持多个配置文件根路径。
 * <p>
 * 本类被
 * <code><a href="core/VelocityAppCodeGenerator.html">VelocityAppCodeGenerator</a></code>
 * 类使用来生成代码。
 * </p>
 * Create on 2006-5-24 15:48:49
 * 
 * @author 薄奇
 * @version 1.0 Developerworkshop
 */
public class VelocityCodeGenerator {
  /** vm模板文件名称，*.vm格式，可包含路径 */
  private String vmFile;

  /** Velocity代码生成实例 */
  VelocityEngine ve = new VelocityEngine();

  @SuppressWarnings("unused")
  public VelocityCodeGenerator() throws CodeGenException {
    //
  }

  /**
   * 指定vm模板文件夹和vm模板文件名称
   * 
   * @param vmFolderDir
   * @param vmName
   * @throws CodeGenException
   */
  public VelocityCodeGenerator(String vmFile1) {
    this.vmFile = vmFile1;
    try {
      Properties p = new Properties();
      // p.setProperty("file.resource.loader.path",
      // getClass().getResource("/nc/md/gen/generator").getPath());
      p.setProperty("file.resource.loader.class",
          "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
      this.ve.init(p);
    }
    catch (Exception e) {
      Logger.error(e.getMessage(), e);
    }
  }

  public void generatorCode(IVMObject vmObj, File dest) throws CodeGenException {
    if (vmObj == null) {
      Logger.error("No vmdata to merge");
    }
    VelocityContext context = new VelocityContext();
    context.put("vmObject", vmObj);
    // by zhaoyha
    context.put("userName", System.getProperty("user.name"));
    context.put("currentDate", new UFDateTime(new Date()).toString());
    this.generate(dest, context);
  }

  public VelocityEngine getVe() {
    return this.ve;
  }

  public String getVmFile() {
    return this.vmFile;
  }

  public void setVe(VelocityEngine ve1) {
    this.ve = ve1;
  }

  public void setVmFile(String vmFile1) {
    this.vmFile = vmFile1;
  }

  /**
   * 根据context生成代码
   * 
   * @param dest
   * @param context
   * @throws CodeGenException
   */
  private void generate(File dest, VelocityContext context)
      throws CodeGenException {
    BufferedWriter bw = null;
    try {
      /* 解析后数据的输出目标，java.io.Writer的子类 */
      bw =
          new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dest)));
      /* 真正生成代码 */
      Template tpl =
          this.ve.getTemplate("nc/md/gen/generator/" + this.getVmFile(),
              "gb2312");
      tpl.merge(context, bw);
      Logger.info("generator " + dest + " finish!!!!");
    }
    catch (ResourceNotFoundException e1) {
      Logger.error(e1.getMessage(), e1);
      throw new CodeGenException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0", "04004000-0104")/*找不到模板文件: */ + this.vmFile);
    }
    catch (ParseErrorException e2) {
      Logger.error(e2.getMessage(), e2);
      String sMessage = this.vmFile;
      throw new CodeGenException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0", "04004000-0105", null, new String[]{sMessage})/*解析模板{0}错误！*/);
    }
    catch (MethodInvocationException e3) {
      throw new CodeGenException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0", "04004000-0106")/*模板方法调用异常!*/);
    }
    catch (IOException e) {
      Logger.error(e.getMessage(), e);
      throw new CodeGenException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0", "04004000-0107")/*输出文件路径不正确!*/);
    }
    catch (Exception e4) {
      Logger.error(e4.getMessage(), e4);
      throw new CodeGenException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0", "04004000-0108")/*代码生成时未知错误!*/);
    }
    finally {
      // 关闭流
      try {
        if (bw != null) {
          bw.flush();
          bw.close();
        }
      }
      catch (Exception e) {
        Logger.error(e.getMessage(), e);
      }
    }
  }
}
