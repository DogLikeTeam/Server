package cn.tedu.meta.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;

/**
 * Created by Tarena on 2017/10/20.
 */
public class PageSheetExcel {


    public void pageSheetExcel(String[][] dataMsg,String[] headerMsg){
        //初始化
        HSSFWorkbook wk = new HSSFWorkbook();       //创建工作簿

        /************************************************/
        /*  分页逻辑                */
        /*  定义每页最大记录数           */
        /*  根据最大记录数计算出sheet数    <span style="white-space:pre">    </span>*/
        /*  有可能最后一页记录达不到最大记录数<span style="white-space:pre">   </span>*/
        /************************************************/
        int maxRow = 30;            //每页最大记录数
        int pageSheet = (dataMsg.length/maxRow) + 1;//分的页数
        int unFill = (dataMsg.length%maxRow);       //最后一页可能不满maxRow

        for(int k = 0 ; k<pageSheet; k++){
            HSSFSheet sheet = wk.createSheet("第" + (k+1) + "页");
            sheet.setColumnWidth(0,900);// Excel空一行
            initTitle(headerMsg, "销售榜单", sheet, wk);
            initHeader(headerMsg, sheet, wk);
            String[][] data;
            if(k != pageSheet-1){
                data = new String[maxRow][dataMsg[0].length];
                for(int i = 0;i<maxRow;i++){
                    for(int j= 0; j<dataMsg[0].length; j++){
                        data[i][j] = dataMsg[ k*maxRow + i ][j];
                    }
                }
            }else {//最后一页的记录
                data = new String[unFill][dataMsg[0].length];
                for(int i = 0; i < unFill ;i++){
                    for(int j= 0; j<dataMsg[0].length; j++){
                        data[i][j] = dataMsg[k*maxRow + i ][j];
                    }
                }
            }
            creatCell(data, sheet, wk);
        }

        //生成Excel
        try {
            FileOutputStream fileOut = new FileOutputStream(
                    "E:\\PageSheetExcel.xls");
            wk.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置标题
     * */
    private void initTitle(String[] headerMsg,String title,HSSFSheet sheet,HSSFWorkbook wk){

        //初始化
        HSSFRow titleRow = sheet.createRow(0);
        HSSFCell titleCell = titleRow.createCell(1);
        HSSFCellStyle style = wk.createCellStyle();

        //位置
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        //边框
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);

        //背景
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //字体
        HSSFFont font = wk.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)25);
        style.setFont(font);
        titleRow.setHeightInPoints((float)20);
        titleCell.setCellStyle(style);

        //text
        titleCell.setCellValue(title);

        //合并
        sheet.addMergedRegion(new CellRangeAddress(0,1,1,headerMsg.length));
    }

    /**
     * 设置表头
     * @author longdage
     * @param headerMsg:表头字段
     * @param sheet:所在页
     * @param wk:工作簿
     * */
    private void initHeader(String[] headerMsg,HSSFSheet sheet,HSSFWorkbook wk){

        //初始化
        HSSFRow headerRow = sheet.createRow(2);
        HSSFCellStyle style = wk.createCellStyle();

        //位置
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);

        //背景
        style.setFillForegroundColor(HSSFColor.GREEN.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //字体
        HSSFFont font = wk.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)20);
        style.setFont(font);

        //text
        for(int i=0;i<headerMsg.length;i++){
            HSSFCell headerCell = headerRow.createCell(i+1);//第一行空出来
            headerCell.setCellStyle(style);
            headerCell.setCellValue(headerMsg[i]);
        }
    }

    /**
     * 设置表头
     * @author longdage
     * @param dataMsg:字段内容
     * @param sheet:所在页
     * @param wk:工作簿
     * */
    private void creatCell(String[][] dataMsg,HSSFSheet sheet,HSSFWorkbook wk){
        HSSFCellStyle style = wk.createCellStyle();

        //边框
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);

        //背景
        style.setFillForegroundColor(HSSFColor.YELLOW.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //字体
        HSSFFont font = wk.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)16);
        style.setFont(font);

        //text
        if(dataMsg.length != 0){
            for(int i = 0 ;i < dataMsg.length;i++){  //控制行
                HSSFRow dataRow = sheet.createRow(i+3);
                for(int j = 0;j<dataMsg[0].length;j++){  //控制列
                    HSSFCell headerCell = dataRow.createCell(j+1);//第一行空出来
                    headerCell.setCellStyle(style);
                    headerCell.setCellValue(dataMsg[i][j]);
                }
            }
        }
    }

    /**
     * main函数测试
     * @author longdage
     * @param args
     * */
    /*public static void main(String[] args) {
        new PageSheetExcel().pageSheetExcel();
    }*/
}
