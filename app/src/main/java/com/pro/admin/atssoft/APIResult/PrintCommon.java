package com.pro.admin.atssoft.APIResult;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class PrintCommon {

   static char[] normalSize = new char[]{0x1B,0x21,0x00};  // 0- normal size text
    static  char[] boldText = new char[]{0x1B,0x21,0x08};  // 1- only bold text
    static  char[] boldMediumText = new char[]{0x1B,0x21,0x10}; // 2- bold with medium text
    static  char[] boldLargeText = new char[]{0x1B,0x21,0x20}; // 3- bold with large text
    static  char newline = 0x0A;
    static char[] left = new char[]{ 0x1b, 0x61, 0x00 };
    static char[] center = new char[]{ 0x1b, 0x61, 0x01 };
    static char[] right = new char[]{ 0x1b, 0x61, 0x02 };
    static char[] format = {29, 50, 35 };
    static  char[] FONT_2X = {0x1D, 0x21, 0x11};
    public static enum printStyleText
    {
        NormalSize,
        BoldText,
        BoldMediumText,
        BoldLargeText,
        FONT_2X
    }
    public static enum printPosition
    {
        Left,
        Center,
        Right
    }

    private static void printWritePosition(PrintWriter oStream, printPosition printPosition)
    {
        switch (printPosition) {
            case Center:
                oStream.write(center);
                break;
            case Left:
                oStream.write(left);
                break;
            case Right:
                oStream.write(right);
                break;
            default:
                oStream.write(left);
                break;
        }
    }

    public static void printNewLine(PrintWriter oStream)
    {
        oStream.write(newline);
    }
    public static void printAndNewLine(PrintWriter oStream, printStyleText printStyle,
                                       printPosition printPosition, String value)
    {
        switch (printStyle) {
            case NormalSize:
                oStream.write(normalSize);
                printWritePosition(oStream, printPosition);
                break;
            case BoldText:
                oStream.write(boldText);
                printWritePosition(oStream, printPosition);
                break;
            case BoldMediumText:
                oStream.write(boldMediumText);
                printWritePosition(oStream, printPosition);
                break;
            case BoldLargeText:
                //oStream.write(format);
                oStream.write(boldLargeText);
                printWritePosition(oStream, printPosition);
                break;
            case FONT_2X:
                oStream.write(FONT_2X);
                printWritePosition(oStream, printPosition);
                break;
            default:
                oStream.write(normalSize);
                printWritePosition(oStream, printPosition);
                break;
        }
        value=convertUnsigned(value,true);
        oStream.write(value);
        oStream.write(newline);
    }
    public static String convertUnsigned(String value, boolean bUnsigned){

        if(value == null)
            return "";
        StringBuilder data = new StringBuilder(value);
        if(bUnsigned)
        {
            List<String[]> param=new ArrayList<>();
            String[] e={"éèẻẽẹêếềểễệ","e"};
            String[] E={"ÉÈẺẼẸÊẾỀỂỄỆ","E"};
            String[] u={"úùủũụưứừữự","u"};
            String[] U={"ÚÙỦŨỤƯỨỪỮỰ","U"};
            String[] i={"íìỉĩị","i"};
            String[] I={"ÍÌỈĨỊ","I"};
            String[] y={"ýỳỵỹỷ","y"};
            String[] Y={"ÝỲỶỸỴ","Y"};
            String[] o={"óòỏõọơớờởỡợôốồổỗộ","o"};
            String[] O={"ÓÒỎÕỌƠỚỜỞỠỢÔỐỒỔỖỘ","O"};
            String[] a={"áàảãạăắằẳẵặâấầẩẫậ","a"};
            String[] A={"ÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬ","A"};
            String[] d={"đ","d"};
            String[] D={"Đ","D"};
            param.add(e);param.add(u);param.add(i);
            param.add(o);param.add(a);param.add(d);
            param.add(E);param.add(U);param.add(I);
            param.add(O);param.add(A);param.add(D);
            param.add(y);param.add(Y);
            for (int index =0;index<data.length();index++)
            {
                for (String[] item:param) {
                    if(item[0].contains(String.valueOf( data.charAt(index))))
                    {
                        data.setCharAt(index,  item[1].charAt(0));
                        break;
                    }
                }


            }
        }
        return  data.toString();
    }
}
