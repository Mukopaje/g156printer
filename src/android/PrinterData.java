package com.printer.g153;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import java.io.CharArrayWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lin
 *
 * @author admin
 */
public class PrinterData {

  private static final String TAG = "PrinterData";
  public static final String HORIZONTAL_TAB = "HORIZONTAL_TAB";
  public static final String UNDERLINE = "UNDERLINE";
  public static final String BOLD = "BOLD";
  public static final String DOUBLE_PRINT = "DOUBLE_PRINT";
  public static final String ALIGN_POSITION = "ALIGN_POSITION";
  public static final String REVERSE_MODE = "REVERSE_MODE";
  public static final String HRI_POSITION = "HRI_POSITION";
  public static final String ERROR_CORRECTION_LEVEL = "ERROR_CORRECTION_LEVEL";

  /**
   * horizontal tab
   */
  public static byte[] horizontal_tab = new byte[] { 9 };

  /**
   * 走纸
   */
  public static byte[] line_feed = new byte[] { 27, 74, 100 };

  /**
   * 全切
   */
  public static byte[] full_cut_paper = new byte[] { 0x1d, 0x56, 0x41, 0x00 };

  /**
   * 半切
   */
  public static byte[] half_cut_paper = new byte[] { 0x1d, 0x56, 0x42, 0x00 };

  /**
   * 设置字符右间距
   */
  public static byte[] setRightSpacing(int n) {
    return new byte[] { 27, 32, (byte) n };
  }

  /**
   * 选择打印模式
   * 0≤n≤255
   * n=0字体标准大小
   * n=16字体倍高
   * n=32字体倍宽
   * n=48字体倍高宽
   */
  public static byte[] setPrintMode(int n) {
    return new byte[] { 27, 33, (byte) n };
  }

  /**
   * 设置绝对打印位置
   */
  public static byte[] setAbsolutePosition(int nL, int nH) {
    return new byte[] { 27, 36, (byte) nL, (byte) nH };
  }

  /**
   * 选择/取消下划线
   */
  public static byte[] switchUnderline(boolean onOff) {
    if (onOff) {
      return new byte[] { 27, 45, 1 };
    } else {
      return new byte[] { 27, 45, 0 };
    }
  }

  /**
   * 设置默认行高
   */
  public static byte[] default_line_spacing = new byte[] { 27, 50 };

  /**
   * 设置行高
   */
  public static byte[] setLineSpacing(int n) {
    return new byte[] { 27, 51, (byte) n };
  }

  /**
   * 初始化打印机
   */
  public static byte[] initial_printer = new byte[] { 27, 64 };

  /**
   * 设置横向跳格位置
   */
  public static byte[] setHorizontalPositionTab(int... position) {
    byte[] result = new byte[3 + position.length];
    result[0] = 27;
    result[1] = 68;
    for (int i = 0; i < position.length; i++) {
      result[2 + i] = (byte) position[i];
    }
    result[result.length - 1] = 0;
    return result;
  }

  /**
   * 字体加粗/取消
   */
  public static byte[] isTextBold(boolean bold) {
    if (bold) {
      return new byte[] { 27, 69, 1 };
    } else {
      return new byte[] { 27, 69, 0 };
    }
  }

  /**
   * 选择/取消双重打印模式
   */
  public static byte[] doublePrint(boolean twicePrint) {
    if (twicePrint) {
      return new byte[] { 27, 71, 1 };
    } else {
      return new byte[] { 27, 71, 0 };
    }
  }

  /**
   * 打印并走纸
   */
  public static byte[] printAndLineFeed = new byte[] { 10 };

  /**
   * 选择汉字模式
   */
  public static byte[] selectCharacterFont = new byte[] { 28, 38 };

  /**
   * 选择国际字符
   */
  public static byte[] selectInternationalCharacter(int n) {
    return new byte[] { 31, 27, 31, (byte) 253, (byte) n };
  }

  /**
   * 设置相对横向打印位置
   */
  public static byte[] setRelativePosition(int nL, int nH) {
    return new byte[] { 27, 92, (byte) nL, (byte) nH };
  }

  /**
   * 左对齐
   */
  public static byte[] LEFT_ALIGN = new byte[] { 27, 97, 0 };
  /**
   * 中间对齐
   */
  public static byte[] CENTER_ALIGN = new byte[] { 0x1B, 0x61, 0x01 };
  /**
   * 右对齐
   */
  public static byte[] RIGHT_ALIGN = new byte[] { 27, 97, 2 };

  /**
   * 打印并走纸n行
   */
  public static byte[] printAndFeedN(int n) {
    return new byte[] { 27, 100, (byte) n };
  }

  /**
   * 设置字体大小
   */
  public static byte[] setTextSize(int size) {
    return new byte[] { 29, 33, (byte) size };
  }

  /**
   * 选择/取消黑白反显
   */
  public static byte[] reversePrint(boolean reverse) {
    if (reverse) {
      return new byte[] { 29, 66, 1 };
    } else {
      return new byte[] { 29, 66, 0 };
    }
  }

  /**
   * n指定条码字符打印位置：
   * 0,48 不打印
   * 1,49 在条码上方
   * 2,50 在条码下方
   * 3,51 在条码上方及下方
   */
  public static byte[] selectHriPrintPosition(int n) {
    return new byte[] { 29, 72, (byte) n };
  }

  /**
   * 设置左边距
   */
  public static byte[] setLeftMargin(int nL, int nH) {
    return new byte[] { 29, 76, (byte) nL, (byte) nH };
  }

  /**
   * 设置打印区域宽度
   */
  public static byte[] setRegionWidth(int nL, int nH) {
    return new byte[] { 29, 87, (byte) nL, (byte) nH };
  }

  /**
   * 设置条形码高度
   */
  public static byte[] setBarCodeHeight(int n) {
    return new byte[] { 29, 104, (byte) n };
  }

  /**
   * 打印条形码
   * n指定条码字符打印位置：
   * 0,48 不打印
   * 1,49 在条码上方
   * 2,50 在条码下方
   * 3,51 在条码上方及下方
   */
  public static byte[] printBarCode(String str, int n) {
    byte[] strBytes = str.getBytes();
    byte[] result = new byte[4 + strBytes.length];
    result[0] = 29;
    result[1] = 107;
    result[2] = 73;
    result[3] = (byte) strBytes.length;
    for (int i = 0; i < strBytes.length; i++) {
      result[i + 4] = strBytes[i];
    }
    return Utils.byteMergeAll(
      initial_printer,
      selectHriPrintPosition(n),
      result,
      line_feed
    );
  }

  /**
   * 设置条码宽度
   */
  public static byte[] setBarCodeWidth(int n) {
    return new byte[] { 29, 119, (byte) n };
  }

  /**
   * 设置二维码大小
   */
  public static byte[] setQrcodeSize(int n) {
    return new byte[] { 29, 40, 107, 3, 0, 49, 67, (byte) n };
  }

  /**
   * 设置二维码纠错等级
   */
  public static byte[] setErrorLevelForQrcode(int n) {
    return new byte[] { 29, 40, 107, 3, 0, 49, 69, (byte) n };
  }

  // 在小票内容中追加二维码。

  /**
   * qrcode
   * @param module_size 1~8
   * @param ec_level 0~3
   * @param text
   * @return
   */
  public static byte[] appendQRcode(
    int module_size,
    int ec_level,
    String text
  ) {
    byte[] content;
    int text_length;
    System.out.println("QR 1 ");
    try {
      content = text.getBytes("UTF-8");
    } catch (UnsupportedEncodingException e) {
      System.out.println("QR 2 " + e.toString());
      return null;
    }

    text_length = content.length;

    if (text_length == 0) return null;
    if (text_length > 65535) text_length = 65535;
    if (module_size < 1) module_size = 1; else if (
      module_size > 8
    ) module_size = 8;
    if (ec_level < 0) ec_level = 0; else if (ec_level > 3) ec_level = 3;
    System.out.println("QR 3 ");
    CharArrayWriter orderContent = new CharArrayWriter();
    System.out.println("QR 4 ");
    orderContent.append("1d286b040031410000");
    orderContent.append("1d286b03003143" + String.format("%02x", module_size));
    orderContent.append(
      "1d286b03003145" + String.format("%02x", ec_level + 48)
    );
    orderContent.append(
      "1d286b" +
      String.format(
        "%02x%02x315030",
        ((text_length + 3) & 0xff),
        (((text_length + 3) >> 8) & 0xff)
      )
    );
    System.out.println("QR 5 ");
    for (int i = 0; i < text_length; i++) orderContent.append(
      String.format("%02x", content[i])
    );
    orderContent.append("1d286b0300315130");
    System.out.println("QR 5 ");
    return hexStringToBytes(orderContent.toString());
  }

  /**
   * 打印二维码
   */
  public static byte[] printQrCode(String str, byte[] extraOrder) {
    int storeLen = str.length() + 3;
    byte storePl = (byte) (storeLen % 256);
    byte storePh = (byte) (storeLen / 256);
    byte[] storeqr = { 29, 40, 107, storePl, storePh, 49, 80, 48 };
    byte[] printqr = { 29, 40, 107, 3, 0, 49, 81, 48 };
    if (extraOrder == null) {
      return Utils.byteMergeAll(
        initial_printer,
        storeqr,
        str.getBytes(),
        printqr,
        line_feed
      );
    } else {
      return Utils.byteMergeAll(
        initial_printer,
        extraOrder,
        storeqr,
        str.getBytes(),
        printqr,
        line_feed
      );
    }
  }

  /**
   * 设置打印密度
   */
  public static byte[] setPrintDensity(int n) {
    return new byte[] { 29, 40, 75, 2, 0, 49, (byte) n };
  }

  public static byte[] printTest1Text(byte[] newOrder) {
    byte[] initialOrder;
    if (newOrder == null) {
      initialOrder = initial_printer;
    } else {
      initialOrder = Utils.byteMergeAll(initial_printer, newOrder);
    }
    return initialOrder;
  }

  public static byte[] paperFeed() {
    return Utils.byteMergeAll(initial_printer, line_feed, line_feed);
  }

  public static byte[] printBitmap(Bitmap bitmap) {
    byte[] data = decodeBitmap(bitmap);
    if (data == null) {
      return null;
    } else {
      return Utils.byteMergeAll(initial_printer, data);
    }
  }

  public static byte[] raster(Bitmap bitmap) {
    int xL = bitmap.getWidth() % 256;
    int xH = bitmap.getWidth() / 256;
    int yL = bitmap.getHeight() % 256;
    int yH = bitmap.getHeight() / 256;
    byte[] data = decodeBitmap(bitmap);
    byte[] result = {
      29,
      118,
      48,
      0,
      (byte) xL,
      (byte) xH,
      (byte) yL,
      (byte) yH,
    };
    return Utils.byteMergeAll(initial_printer, result, data);
  }

  public static byte[] decodeBitmap(Bitmap bmp) {
    int bmpWidth = bmp.getWidth();
    int bmpHeight = bmp.getHeight();

    //binaryString list
    List<String> list = new ArrayList<String>();
    StringBuffer sb;

    int bitLen = bmpWidth / 8;
    //zeroCount = 4
    int zeroCount = bmpWidth % 8;

    StringBuffer zeroStr = new StringBuffer();
    if (zeroCount > 0) {
      bitLen = bmpWidth / 8 + 1;
      for (int i = 0; i < (8 - zeroCount); i++) {
        zeroStr.append("0");
      }
    }

    for (int i = 0; i < bmpHeight; i++) {
      sb = new StringBuffer();
      for (int j = 0; j < bmpWidth; j++) {
        int color = bmp.getPixel(j, i);

        int r = (color >> 16) & 0xff;
        int g = (color >> 8) & 0xff;
        int b = color & 0xff;

        // if color close to white，bit='0', else bit='1'
        if (r > 160 && g > 160 && b > 160) {
          sb.append("0");
        } else {
          sb.append("1");
        }
      }
      if (zeroCount > 0) {
        sb.append(zeroStr);
      }
      list.add(sb.toString());
    }

    List<String> bmpHexList = binaryListToHexStringList(list);
    String commandHexString = "1D763000";
    String widthHexString = Integer.toHexString(
      bmpWidth % 8 == 0 ? bmpWidth / 8 : (bmpWidth / 8 + 1)
    );
    if (widthHexString.length() > 2) {
      Log.e("decodeBitmap error", " width is too large");
      return null;
    } else if (widthHexString.length() == 1) {
      widthHexString = "0" + widthHexString;
    }
    widthHexString = widthHexString + "00";

    String heightHexString = Integer.toHexString(bmpHeight);
    if (heightHexString.length() > 2) {
      Log.e("decodeBitmap error", " height is too large");
      return null;
    } else if (heightHexString.length() == 1) {
      heightHexString = "0" + heightHexString;
    }
    heightHexString = heightHexString + "00";

    List<String> commandList = new ArrayList<String>();
    commandList.add(commandHexString + widthHexString + heightHexString);
    commandList.addAll(bmpHexList);

    return hexList2Byte(commandList);
  }

  public static List<String> binaryListToHexStringList(List<String> list) {
    List<String> hexList = new ArrayList<String>();
    for (String binaryStr : list) {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < binaryStr.length(); i += 8) {
        String str = binaryStr.substring(i, i + 8);

        String hexString = myBinaryStrToHexString(str);
        sb.append(hexString);
      }
      hexList.add(sb.toString());
    }
    return hexList;
  }

  private static String[] binaryArray = {
    "0000",
    "0001",
    "0010",
    "0011",
    "0100",
    "0101",
    "0110",
    "0111",
    "1000",
    "1001",
    "1010",
    "1011",
    "1100",
    "1101",
    "1110",
    "1111",
  };
  private static String hexStr = "0123456789ABCDEF";

  public static String myBinaryStrToHexString(String binaryStr) {
    String hex = "";
    String f4 = binaryStr.substring(0, 4);
    String b4 = binaryStr.substring(4, 8);
    for (int i = 0; i < binaryArray.length; i++) {
      if (f4.equals(binaryArray[i])) {
        hex += hexStr.substring(i, i + 1);
      }
    }
    for (int i = 0; i < binaryArray.length; i++) {
      if (b4.equals(binaryArray[i])) {
        hex += hexStr.substring(i, i + 1);
      }
    }

    return hex;
  }

  public static byte[] hexList2Byte(List<String> list) {
    List<byte[]> commandList = new ArrayList<byte[]>();

    for (String hexStr : list) {
      commandList.add(hexStringToBytes(hexStr));
    }
    byte[] bytes = sysCopy(commandList);
    return bytes;
  }

  public static byte[] hexStringToBytes(String hexString) {
    if (hexString == null || "".equals(hexString)) {
      return null;
    }
    hexString = hexString.toUpperCase();
    int length = hexString.length() / 2;
    char[] hexChars = hexString.toCharArray();
    byte[] d = new byte[length];
    for (int i = 0; i < length; i++) {
      int pos = i * 2;
      d[i] =
        (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
    }
    return d;
  }

  public static byte[] sysCopy(List<byte[]> srcArrays) {
    int len = 0;
    for (byte[] srcArray : srcArrays) {
      len += srcArray.length;
    }
    byte[] destArray = new byte[len];
    int destLen = 0;
    for (byte[] srcArray : srcArrays) {
      System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
      destLen += srcArray.length;
    }
    return destArray;
  }

  private static byte charToByte(char c) {
    return (byte) "0123456789ABCDEF".indexOf(c);
  }
}
