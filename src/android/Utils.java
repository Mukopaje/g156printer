package com.printer.g153;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.List;

/**
 * @author admin
 */
public class Utils {

  public static final String KEY_ALIGN = "key_attributes_align";
  public static final String KEY_TEXTSIZE = "key_attributes_textsize";
  public static final String KEY_TYPEFACE = "key_attributes_typeface";
  public static final String KEY_MARGINLEFT = "key_attributes_marginleft";
  public static final String KEY_MARGINRIGHT = "key_attributes_marginright";
  public static final String KEY_MARGINTOP = "key_attributes_margintop";
  public static final String KEY_MARGINBOTTOM = "key_attributes_marginbottom";
  public static final String KEY_LINESPACE = "key_attributes_linespace";
  public static final String KEY_WEIGHT = "key_attributes_weight";

  public static byte[] byteMergeAll(byte[]... value) {
    int lengthByte = 0;
    for (int i = 0; i < value.length; i++) {
      lengthByte += value[i].length;
    }
    byte[] allByte = new byte[lengthByte];
    int countLength = 0;
    for (int i = 0; i < value.length; i++) {
      byte[] b = value[i];
      System.arraycopy(b, 0, allByte, countLength, b.length);
      countLength += b.length;
    }
    return allByte;
  }

  /**
   * byte[]转string后再转byte[]
   *
   * @param bstr
   * @return
   */
  public static byte[] byteStringTobyte(String bstr) {
    String[] sa = bstr.substring(1, bstr.length() - 1).split(", ");
    byte[] barr = new byte[sa.length];
    try {
      for (int i = 0; i < barr.length; i++) {
        barr[i] = Byte.parseByte(sa[i]);
      }
    } catch (Exception e) {
      return null;
    }
    return barr;
  }

  /**
   * 获取Till设备打印服务类型
   *
   * @param context  上下文
   * @param package1 com.prints.printerservice
   * @param package2 com.xcheng.printerservice
   * @return 0：都不是， 1：com.prints.printerservice ， 2：com.xcheng.printerservice
   */
  public static int getModelType(
    Context context,
    String package1,
    String package2
  ) {
    //获取PackageManager
    PackageManager packageManager = context.getPackageManager();
    //获取所有已安装程序的包信息
    List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
    //从pinfo中将包名字逐一取出，压入pName list中
    if (packageInfos != null) {
      for (int i = 0; i < packageInfos.size(); i++) {
        String packName = packageInfos.get(i).packageName;
        if (package1.equals(packName)) {
          return 1;
        } else if (package2.equals(packName)) {
          return 2;
        }
      }
    }
    return 0;
  }
}
