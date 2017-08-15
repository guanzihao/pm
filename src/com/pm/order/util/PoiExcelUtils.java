package com.pm.order.util;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.pm.order.bean.BgEcExpDomain;
import com.pm.order.bean.BgIcExpDomain;
import com.pm.order.bean.CcEcExpDomain;
import com.pm.order.bean.CcIcExpDomain;
import com.pm.order.bean.WlEcExpDomain;
import com.pm.order.bean.WlIcExpDomain;
import com.pm.order.bean.WmEcExpDomain;
import com.pm.order.bean.WmIcExpDomain;

/**
 * 创建类
 * 
 * @title PoiExcelUtils
 * @description TODO
 * @author ccc-cbf02
 * @version
 * @create_date 2014-12-16
 * @copyright (c) SHANGHAI TOPSMOON
 * 
 */
@SuppressWarnings("resource")
public class PoiExcelUtils {
	/**
	 * 创建表头
	 * 
	 * @title createOutPutTitleRow
	 * @description TODO
	 * @author ccc-cbf02
	 * @create_date 2014-12-16
	 * @param wb
	 * @param creationHelper
	 * @param sheet
	 * @param oarr
	 * @param rowIndex
	 */
	private static Workbook createOutPutTitleRow(Workbook wb, CreationHelper creationHelper,
			Sheet sheet, Object[] oarr, int rowIndex) {
		CellStyle styleFont = wb.createCellStyle();
		Font font = wb.createFont();
		// 设置表头字体
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		// 设置表头字体颜色
		font.setColor(Font.COLOR_NORMAL);
		// 添加字体
		styleFont.setFont(font);
		// 根据数据创建行
		Row row = sheet.createRow((int) rowIndex);
		// 填充单元格
		styleFont.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// 单元格背景颜色
		styleFont.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		for (int i = 0; i < oarr.length; i++) {
			sheet.setColumnWidth(i, 1000 * 4);
			Cell cell = row.createCell(i);
			cell.setCellValue(creationHelper.createRichTextString(oarr[i] + ""));
			cell.setCellStyle(styleFont);
		}
       return wb;
	}

	/**
	 * 创建内容行
	 * 
	 * @title createOutPutContentRow
	 * @description TODO
	 * @author ccc-cbf02
	 * @create_date 2014-12-16
	 * @param wb
	 * @param creationHelper
	 * @param sheet
	 * @param listContents
	 * @param contentBeginRowIndex
	 */
	private static Workbook createOutPutContentRowBgEc(Workbook wb, CreationHelper creationHelper,
			Sheet sheet, List<BgEcExpDomain> listContents, int contentBeginRowIndex,String sheetName)
			throws Exception {
		// 创建表内容列
		CellStyle styleFont = wb.createCellStyle();
		// 创建字体
		Font font = wb.createFont();
		// 创建字体颜色
		font.setColor(Font.COLOR_NORMAL);
		styleFont.setFont(font);
		// 遍历RowList集合, 从内容行第一行开始遍历
		int num = 5000000;
		int n = listContents.size()/num;
		if(n==0){
			for (int i = contentBeginRowIndex; i <= listContents.size(); i++) {
				PoiExcelBaseBean bean = listContents.get(i - contentBeginRowIndex);
				// 在表格创建行
				Row r = sheet.createRow(i);
				// 循环列
				for (int j = 0; j < bean.size(); j++) {
					Cell cell = r.createCell(j);
					Object beanO = bean.get(j + 1, bean);
					if(beanO==null){
						beanO="";
					}
					String beanStr=beanO.toString();
					cell.setCellValue(creationHelper.createRichTextString(beanStr));
				}
			}
		}else{
			for(int m=0;m<=n;m++){
			
				if(m==0){
					for (int i = contentBeginRowIndex; i <= num; i++) {
						PoiExcelBaseBean bean = listContents.get(i - contentBeginRowIndex);
						// 在表格创建行
						Row r = sheet.createRow(i);
						// 循环列
						for (int j = 0; j < bean.size(); j++) {
							Cell cell = r.createCell(j);
							Object beanO = bean.get(j + 1, bean);
							if(beanO==null){
								beanO="";
							}
							String beanStr=beanO.toString();
							cell.setCellValue(creationHelper.createRichTextString(beanStr));
						}
					}
				}else if(m!=n){
					// 创建表
					String sheetNameM = sheetName+"_"+m;
					Sheet sheetK = wb.createSheet(sheetNameM);
					for (int i = num*m; i < num*(m+1); i++) {
						PoiExcelBaseBean bean = listContents.get(i);
						int rowN = i-num*m;
						// 在表格创建行
						Row r = sheetK.createRow(rowN);
						// 循环列
						for (int j = 0; j < bean.size(); j++) {
							Cell cell = r.createCell(j);
							Object beanO = bean.get(j + 1, bean);
							if(beanO==null){
								beanO="";
							}
							String beanStr=beanO.toString();
							cell.setCellValue(creationHelper.createRichTextString(beanStr));
						}
					}
				}else if(m==n&&num*m!=listContents.size()){
					// 创建表
					String sheetNameM = sheetName+"_"+m;
					// 创建表
					Sheet sheetK = wb.createSheet(sheetNameM);
					for (int i = num*m; i < listContents.size(); i++) {
						
						PoiExcelBaseBean bean = listContents.get(i);
						int rowN = i-num*m;
						// 在表格创建行
						Row r = sheetK.createRow(rowN);
						// 循环列
						for (int j = 0; j < bean.size(); j++) {
							Cell cell = r.createCell(j);
							Object beanO = bean.get(j + 1, bean);
							if(beanO==null){
								beanO="";
							}
							String beanStr=beanO.toString();
							cell.setCellValue(creationHelper.createRichTextString(beanStr));
						}
					}
				}
			}
		}
		
		return wb;	
	}

	/**
	 * 动态列创建内容行
	 * 
	 * @title createOutPutContentRow_
	 * @description TODO
	 * @author ccc-cbf02
	 * @create_date 2014-12-16
	 * @param wb
	 * @param creationHelper
	 * @param sheet
	 * @param listRows
	 * @param contentBeginRowIndex
	 */
	private static Workbook createOutPutContentRow_(Workbook wb, CreationHelper creationHelper,
			Sheet sheet, List<List<Object>> listRows, int contentBeginRowIndex) throws Exception {
		// 创建表内容列
		CellStyle styleFont = wb.createCellStyle();
		// 创建字体
		Font font = wb.createFont();
		// 创建字体颜色
		font.setColor(Font.COLOR_NORMAL);
		styleFont.setFont(font);
		// 遍历RowList集合, 从内容行第一行开始遍历
		for (int i = contentBeginRowIndex; i <= listRows.size(); i++) {
			List<Object> obj = listRows.get(i - contentBeginRowIndex);
			// 在表格创建行
			Row r = sheet.createRow((short) i);
			// 循环列
			for (int j = 0; j < obj.size(); j++) {
				Cell cell = r.createCell(j);
				Object objC = obj.get(j);
				if (objC == null)
					objC = "";
				cell.setCellValue(creationHelper.createRichTextString(objC.toString()));
			}
		}
		return wb;

	}
	/**
	 * 动态列创建内容行(指标跟踪)
	 * 
	 * @title createOutPutContentRow_
	 * @description TODO
	 * @author ccc-cbf02
	 * @create_date 2014-12-16
	 * @param wb
	 * @param creationHelper
	 * @param sheet
	 * @param listRows
	 * @param contentBeginRowIndex
	 */
	private static Workbook createOutPutContentRow_indexes(Workbook wb, CreationHelper creationHelper,
			Sheet sheet,List<Map<String,Object>> listRows, int contentBeginRowIndex,String indexCode,
			String indexName) throws Exception {
		// 创建表内容列
		CellStyle styleFont = wb.createCellStyle();
		// 创建字体
		Font font = wb.createFont();
		// 创建字体颜色
		font.setColor(Font.COLOR_NORMAL);
		styleFont.setFont(font);
		// 遍历RowList集合, 从内容行第一行开始遍历
		for (int i = contentBeginRowIndex; i <= listRows.size(); i++) {
			Map<String,Object> objMap = listRows.get(i - contentBeginRowIndex);
			// 在表格创建行
			Row r = sheet.createRow((short) i);
			String strategyCode = objMap.get("CATEGORY_ID").toString();
			String strategyName = objMap.get("CATEGORY_NAME").toString();
			String action_id = objMap.get("ACTION_ID").toString();
			@SuppressWarnings("unchecked")
            List<Object> indexValue = (List<Object>)objMap.get("indexValue");
			Cell cell_0 = r.createCell(0);
			Cell cell_1 = r.createCell(1);
			Cell cell_2 = r.createCell(2);
			Cell cell_3 = r.createCell(3);
			Cell cell_4 = r.createCell(4);
			cell_0.setCellValue(creationHelper.createRichTextString(strategyCode));
			cell_1.setCellValue(creationHelper.createRichTextString(strategyName));
			cell_2.setCellValue(creationHelper.createRichTextString(indexCode));
			cell_3.setCellValue(creationHelper.createRichTextString(indexName));
			cell_4.setCellValue(creationHelper.createRichTextString(action_id));
			
			// 循环列
			for (int j = 0; j < indexValue.size(); j++) {
				Cell cell = r.createCell(j+5);
				Object objC = indexValue.get(j);
				if (objC == null)
					objC = "";
				cell.setCellValue(creationHelper.createRichTextString(objC.toString()));
			}
		}
      return wb;
	}
	/**
	 * 导出excel方法
	 * 
	 * @title outPutExcel
	 * @description TODO
	 * @author ccc-cbf02
	 * @create_date 2014-12-25
	 * @param oarr
	 * @param listContents
	 * @param contentBeginRowIndex
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */
	public static Workbook outPutExcelBgEc(Object[] oarr, List<BgEcExpDomain> listContents,
			int contentBeginRowIndex, String sheetName) throws Exception {
		// 创建表格样式
		Workbook wb = new HSSFWorkbook();
		// 创建帮助类
		CreationHelper createHelper = wb.getCreationHelper();
		// 创建表
		Sheet sheet = wb.createSheet(sheetName);
		// 创建表头
		wb = createOutPutTitleRow(wb, createHelper, sheet, oarr, (int) 0);
		// 创建内容行
		wb = createOutPutContentRowBgEc(wb, createHelper, sheet, listContents, contentBeginRowIndex,sheetName);
		return wb;
	}

	/**
	 * 动态列导出excel方法
	 * 
	 * @title outPutExcel
	 * @description TODO
	 * @author ccc-cbf02
	 * @create_date 2014-12-25
	 * @param oarr
	 * @param listRows
	 * @param contentBeginRowIndex
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */
	public static Workbook outPutExcel_(Object[] oarr, List<List<Object>> listRows,
			int contentBeginRowIndex, String sheetName) throws Exception {
		// 创建表格样式
		Workbook wb = new HSSFWorkbook();
		// 创建帮助类
		CreationHelper createHelper = wb.getCreationHelper();
		// 创建表
		Sheet sheet = wb.createSheet(sheetName);
		// 创建表头
		wb = createOutPutTitleRow(wb, createHelper, sheet, oarr, (short) 0);
		// 创建内容行
		wb = createOutPutContentRow_(wb, createHelper, sheet, listRows, contentBeginRowIndex);
		return wb;
	}
	/**
	 * 动态列导出excel方法(指标跟踪)
	 * 
	 * @title outPutExcel_indexes
	 * @description TODO
	 * @author ccc-cbf02
	 * @create_date 2014-12-25
	 * @param oarr
	 * @param listRows
	 * @param contentBeginRowIndex
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */
    public static Workbook outPutExcel_indexes(Object[] oarr, List<Map<String,Object>> listRows,
			int contentBeginRowIndex, String sheetName,String indexCode,
			String indexName) throws Exception {
		// 创建表格样式
		Workbook wb = new HSSFWorkbook();
		// 创建帮助类
		CreationHelper createHelper = wb.getCreationHelper();
		// 创建表
		Sheet sheet = wb.createSheet(sheetName);
		// 创建表头
		wb = createOutPutTitleRow(wb, createHelper, sheet, oarr, (short) 0);
		// 创建内容行
		wb = createOutPutContentRow_indexes(wb, createHelper, sheet, listRows, contentBeginRowIndex,indexCode,
				indexName);
		return wb;
	}

    public static Workbook outPutExcelBgIc(Object[] oarr, List<BgIcExpDomain> listContents, int contentBeginRowIndex, String sheetName) {
     // 创建表格样式
        Workbook wb = new HSSFWorkbook();
        try {
            // 创建帮助类
            CreationHelper createHelper = wb.getCreationHelper();
            // 创建表
            Sheet sheet = wb.createSheet(sheetName);
            // 创建表头
            wb = createOutPutTitleRow(wb, createHelper, sheet, oarr, (int) 0);
            // 创建内容行
            wb = createOutPutContentRowBgIc(wb, createHelper, sheet, listContents, contentBeginRowIndex,sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return wb;
    }

    /**
     * 创建内容行
     * 
     * @title createOutPutContentRow
     * @description TODO
     * @author ccc-cbf02
     * @create_date 2014-12-16
     * @param wb
     * @param creationHelper
     * @param sheet
     * @param listContents
     * @param contentBeginRowIndex
     */
    private static Workbook createOutPutContentRowBgIc(Workbook wb, CreationHelper creationHelper,
            Sheet sheet, List<BgIcExpDomain> listContents, int contentBeginRowIndex,String sheetName)
            throws Exception {
        // 创建表内容列
        CellStyle styleFont = wb.createCellStyle();
        // 创建字体
        Font font = wb.createFont();
        // 创建字体颜色
        font.setColor(Font.COLOR_NORMAL);
        styleFont.setFont(font);
        // 遍历RowList集合, 从内容行第一行开始遍历
        int num = 5000000;
        int n = listContents.size()/num;
        if(n==0){
            for (int i = contentBeginRowIndex; i <= listContents.size(); i++) {
                PoiExcelBaseBean bean = listContents.get(i - contentBeginRowIndex);
                // 在表格创建行
                Row r = sheet.createRow(i);
                // 循环列
                for (int j = 0; j < bean.size(); j++) {
                    Cell cell = r.createCell(j);
                    Object beanO = bean.get(j + 1, bean);
                    if(beanO==null){
                        beanO="";
                    }
                    String beanStr=beanO.toString();
                    cell.setCellValue(creationHelper.createRichTextString(beanStr));
                }
            }
        }else{
            for(int m=0;m<=n;m++){
            
                if(m==0){
                    for (int i = contentBeginRowIndex; i <= num; i++) {
                        PoiExcelBaseBean bean = listContents.get(i - contentBeginRowIndex);
                        // 在表格创建行
                        Row r = sheet.createRow(i);
                        // 循环列
                        for (int j = 0; j < bean.size(); j++) {
                            Cell cell = r.createCell(j);
                            Object beanO = bean.get(j + 1, bean);
                            if(beanO==null){
                                beanO="";
                            }
                            String beanStr=beanO.toString();
                            cell.setCellValue(creationHelper.createRichTextString(beanStr));
                        }
                    }
                }else if(m!=n){
                    // 创建表
                    String sheetNameM = sheetName+"_"+m;
                    Sheet sheetK = wb.createSheet(sheetNameM);
                    for (int i = num*m; i < num*(m+1); i++) {
                        PoiExcelBaseBean bean = listContents.get(i);
                        int rowN = i-num*m;
                        // 在表格创建行
                        Row r = sheetK.createRow(rowN);
                        // 循环列
                        for (int j = 0; j < bean.size(); j++) {
                            Cell cell = r.createCell(j);
                            Object beanO = bean.get(j + 1, bean);
                            if(beanO==null){
                                beanO="";
                            }
                            String beanStr=beanO.toString();
                            cell.setCellValue(creationHelper.createRichTextString(beanStr));
                        }
                    }
                }else if(m==n&&num*m!=listContents.size()){
                    // 创建表
                    String sheetNameM = sheetName+"_"+m;
                    // 创建表
                    Sheet sheetK = wb.createSheet(sheetNameM);
                    for (int i = num*m; i < listContents.size(); i++) {
                        
                        PoiExcelBaseBean bean = listContents.get(i);
                        int rowN = i-num*m;
                        // 在表格创建行
                        Row r = sheetK.createRow(rowN);
                        // 循环列
                        for (int j = 0; j < bean.size(); j++) {
                            Cell cell = r.createCell(j);
                            Object beanO = bean.get(j + 1, bean);
                            if(beanO==null){
                                beanO="";
                            }
                            String beanStr=beanO.toString();
                            cell.setCellValue(creationHelper.createRichTextString(beanStr));
                        }
                    }
                }
            }
        }
        
        return wb;  
    }
    
    /**
     * 物流进口
     * @param oarr
     * @param listContents
     * @param contentBeginRowIndex
     * @param sheetName
     * @return
     */
    public static Workbook outPutExcelWlIc(Object[] oarr, List<WlIcExpDomain> listContents, int contentBeginRowIndex, String sheetName) {
        // 创建表格样式
           Workbook wb = new HSSFWorkbook();
           try {
               // 创建帮助类
               CreationHelper createHelper = wb.getCreationHelper();
               // 创建表
               Sheet sheet = wb.createSheet(sheetName);
               // 创建表头
               wb = createOutPutTitleRow(wb, createHelper, sheet, oarr, (int) 0);
               // 创建内容行
               wb = createOutPutContentRowWlIc(wb, createHelper, sheet, listContents, contentBeginRowIndex,sheetName);
           } catch (Exception e) {
               e.printStackTrace();
           }
           
           return wb;
       }

       /**
        * 创建内容行
        * 
        * @title createOutPutContentRow
        * @description TODO
        * @author ccc-cbf02
        * @create_date 2014-12-16
        * @param wb
        * @param creationHelper
        * @param sheet
        * @param listContents
        * @param contentBeginRowIndex
        */
       private static Workbook createOutPutContentRowWlIc(Workbook wb, CreationHelper creationHelper,
               Sheet sheet, List<WlIcExpDomain> listContents, int contentBeginRowIndex,String sheetName)
               throws Exception {
           // 创建表内容列
           CellStyle styleFont = wb.createCellStyle();
           // 创建字体
           Font font = wb.createFont();
           // 创建字体颜色
           font.setColor(Font.COLOR_NORMAL);
           styleFont.setFont(font);
           // 遍历RowList集合, 从内容行第一行开始遍历
           int num = 5000000;
           int n = listContents.size()/num;
           if(n==0){
               for (int i = contentBeginRowIndex; i <= listContents.size(); i++) {
                   PoiExcelBaseBean bean = listContents.get(i - contentBeginRowIndex);
                   // 在表格创建行
                   Row r = sheet.createRow(i);
                   // 循环列
                   for (int j = 0; j < bean.size(); j++) {
                       Cell cell = r.createCell(j);
                       Object beanO = bean.get(j + 1, bean);
                       if(beanO==null){
                           beanO="";
                       }
                       String beanStr=beanO.toString();
                       cell.setCellValue(creationHelper.createRichTextString(beanStr));
                   }
               }
           }else{
               for(int m=0;m<=n;m++){
               
                   if(m==0){
                       for (int i = contentBeginRowIndex; i <= num; i++) {
                           PoiExcelBaseBean bean = listContents.get(i - contentBeginRowIndex);
                           // 在表格创建行
                           Row r = sheet.createRow(i);
                           // 循环列
                           for (int j = 0; j < bean.size(); j++) {
                               Cell cell = r.createCell(j);
                               Object beanO = bean.get(j + 1, bean);
                               if(beanO==null){
                                   beanO="";
                               }
                               String beanStr=beanO.toString();
                               cell.setCellValue(creationHelper.createRichTextString(beanStr));
                           }
                       }
                   }else if(m!=n){
                       // 创建表
                       String sheetNameM = sheetName+"_"+m;
                       Sheet sheetK = wb.createSheet(sheetNameM);
                       for (int i = num*m; i < num*(m+1); i++) {
                           PoiExcelBaseBean bean = listContents.get(i);
                           int rowN = i-num*m;
                           // 在表格创建行
                           Row r = sheetK.createRow(rowN);
                           // 循环列
                           for (int j = 0; j < bean.size(); j++) {
                               Cell cell = r.createCell(j);
                               Object beanO = bean.get(j + 1, bean);
                               if(beanO==null){
                                   beanO="";
                               }
                               String beanStr=beanO.toString();
                               cell.setCellValue(creationHelper.createRichTextString(beanStr));
                           }
                       }
                   }else if(m==n&&num*m!=listContents.size()){
                       // 创建表
                       String sheetNameM = sheetName+"_"+m;
                       // 创建表
                       Sheet sheetK = wb.createSheet(sheetNameM);
                       for (int i = num*m; i < listContents.size(); i++) {
                           
                           PoiExcelBaseBean bean = listContents.get(i);
                           int rowN = i-num*m;
                           // 在表格创建行
                           Row r = sheetK.createRow(rowN);
                           // 循环列
                           for (int j = 0; j < bean.size(); j++) {
                               Cell cell = r.createCell(j);
                               Object beanO = bean.get(j + 1, bean);
                               if(beanO==null){
                                   beanO="";
                               }
                               String beanStr=beanO.toString();
                               cell.setCellValue(creationHelper.createRichTextString(beanStr));
                           }
                       }
                   }
               }
           }
           
           return wb;  
       }

       /**
        * 物流出口
        * @param oarr
        * @param listContents
        * @param contentBeginRowIndex
        * @param sheetName
        * @return
        */
       public static Workbook outPutExcelWlEc(Object[] oarr, List<WlEcExpDomain> listContents, int contentBeginRowIndex, String sheetName) {
           // 创建表格样式
              Workbook wb = new HSSFWorkbook();
              try {
                  // 创建帮助类
                  CreationHelper createHelper = wb.getCreationHelper();
                  // 创建表
                  Sheet sheet = wb.createSheet(sheetName);
                  // 创建表头
                  wb = createOutPutTitleRow(wb, createHelper, sheet, oarr, (int) 0);
                  // 创建内容行
                  wb = createOutPutContentRowWlEc(wb, createHelper, sheet, listContents, contentBeginRowIndex,sheetName);
              } catch (Exception e) {
                  e.printStackTrace();
              }
              
              return wb;
          }

          /**
           * 创建内容行
           * 
           * @title createOutPutContentRow
           * @description TODO
           * @author ccc-cbf02
           * @create_date 2014-12-16
           * @param wb
           * @param creationHelper
           * @param sheet
           * @param listContents
           * @param contentBeginRowIndex
           */
          private static Workbook createOutPutContentRowWlEc(Workbook wb, CreationHelper creationHelper,
                  Sheet sheet, List<WlEcExpDomain> listContents, int contentBeginRowIndex,String sheetName)
                  throws Exception {
              // 创建表内容列
              CellStyle styleFont = wb.createCellStyle();
              // 创建字体
              Font font = wb.createFont();
              // 创建字体颜色
              font.setColor(Font.COLOR_NORMAL);
              styleFont.setFont(font);
              // 遍历RowList集合, 从内容行第一行开始遍历
              int num = 5000000;
              int n = listContents.size()/num;
              if(n==0){
                  for (int i = contentBeginRowIndex; i <= listContents.size(); i++) {
                      PoiExcelBaseBean bean = listContents.get(i - contentBeginRowIndex);
                      // 在表格创建行
                      Row r = sheet.createRow(i);
                      // 循环列
                      for (int j = 0; j < bean.size(); j++) {
                          Cell cell = r.createCell(j);
                          Object beanO = bean.get(j + 1, bean);
                          if(beanO==null){
                              beanO="";
                          }
                          String beanStr=beanO.toString();
                          cell.setCellValue(creationHelper.createRichTextString(beanStr));
                      }
                  }
              }else{
                  for(int m=0;m<=n;m++){
                  
                      if(m==0){
                          for (int i = contentBeginRowIndex; i <= num; i++) {
                              PoiExcelBaseBean bean = listContents.get(i - contentBeginRowIndex);
                              // 在表格创建行
                              Row r = sheet.createRow(i);
                              // 循环列
                              for (int j = 0; j < bean.size(); j++) {
                                  Cell cell = r.createCell(j);
                                  Object beanO = bean.get(j + 1, bean);
                                  if(beanO==null){
                                      beanO="";
                                  }
                                  String beanStr=beanO.toString();
                                  cell.setCellValue(creationHelper.createRichTextString(beanStr));
                              }
                          }
                      }else if(m!=n){
                          // 创建表
                          String sheetNameM = sheetName+"_"+m;
                          Sheet sheetK = wb.createSheet(sheetNameM);
                          for (int i = num*m; i < num*(m+1); i++) {
                              PoiExcelBaseBean bean = listContents.get(i);
                              int rowN = i-num*m;
                              // 在表格创建行
                              Row r = sheetK.createRow(rowN);
                              // 循环列
                              for (int j = 0; j < bean.size(); j++) {
                                  Cell cell = r.createCell(j);
                                  Object beanO = bean.get(j + 1, bean);
                                  if(beanO==null){
                                      beanO="";
                                  }
                                  String beanStr=beanO.toString();
                                  cell.setCellValue(creationHelper.createRichTextString(beanStr));
                              }
                          }
                      }else if(m==n&&num*m!=listContents.size()){
                          // 创建表
                          String sheetNameM = sheetName+"_"+m;
                          // 创建表
                          Sheet sheetK = wb.createSheet(sheetNameM);
                          for (int i = num*m; i < listContents.size(); i++) {
                              
                              PoiExcelBaseBean bean = listContents.get(i);
                              int rowN = i-num*m;
                              // 在表格创建行
                              Row r = sheetK.createRow(rowN);
                              // 循环列
                              for (int j = 0; j < bean.size(); j++) {
                                  Cell cell = r.createCell(j);
                                  Object beanO = bean.get(j + 1, bean);
                                  if(beanO==null){
                                      beanO="";
                                  }
                                  String beanStr=beanO.toString();
                                  cell.setCellValue(creationHelper.createRichTextString(beanStr));
                              }
                          }
                      }
                  }
              }
              
              return wb;  
          }
          
          /**
           * 外贸出口
           * @param oarr
           * @param listContents
           * @param contentBeginRowIndex
           * @param sheetName
           * @return
           */
          public static Workbook outPutExcelWmIc(Object[] oarr, List<WmIcExpDomain> listContents, int contentBeginRowIndex, String sheetName) {
              // 创建表格样式
                 Workbook wb = new HSSFWorkbook();
                 try {
                     // 创建帮助类
                     CreationHelper createHelper = wb.getCreationHelper();
                     // 创建表
                     Sheet sheet = wb.createSheet(sheetName);
                     // 创建表头
                     wb = createOutPutTitleRow(wb, createHelper, sheet, oarr, (int) 0);
                     // 创建内容行
                     wb = createOutPutContentRowWmIc(wb, createHelper, sheet, listContents, contentBeginRowIndex,sheetName);
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
                 
                 return wb;
             }

             /**
              * 创建内容行
              * 
              * @title createOutPutContentRow
              * @description TODO
              * @author ccc-cbf02
              * @create_date 2014-12-16
              * @param wb
              * @param creationHelper
              * @param sheet
              * @param listContents
              * @param contentBeginRowIndex
              */
             private static Workbook createOutPutContentRowWmIc(Workbook wb, CreationHelper creationHelper,
                     Sheet sheet, List<WmIcExpDomain> listContents, int contentBeginRowIndex,String sheetName)
                     throws Exception {
                 // 创建表内容列
                 CellStyle styleFont = wb.createCellStyle();
                 // 创建字体
                 Font font = wb.createFont();
                 // 创建字体颜色
                 font.setColor(Font.COLOR_NORMAL);
                 styleFont.setFont(font);
                 // 遍历RowList集合, 从内容行第一行开始遍历
                 int num = 5000000;
                 int n = listContents.size()/num;
                 if(n==0){
                     for (int i = contentBeginRowIndex; i <= listContents.size(); i++) {
                         PoiExcelBaseBean bean = listContents.get(i - contentBeginRowIndex);
                         // 在表格创建行
                         Row r = sheet.createRow(i);
                         // 循环列
                         for (int j = 0; j < bean.size(); j++) {
                             Cell cell = r.createCell(j);
                             Object beanO = bean.get(j + 1, bean);
                             if(beanO==null){
                                 beanO="";
                             }
                             String beanStr=beanO.toString();
                             cell.setCellValue(creationHelper.createRichTextString(beanStr));
                         }
                     }
                 }else{
                     for(int m=0;m<=n;m++){
                     
                         if(m==0){
                             for (int i = contentBeginRowIndex; i <= num; i++) {
                                 PoiExcelBaseBean bean = listContents.get(i - contentBeginRowIndex);
                                 // 在表格创建行
                                 Row r = sheet.createRow(i);
                                 // 循环列
                                 for (int j = 0; j < bean.size(); j++) {
                                     Cell cell = r.createCell(j);
                                     Object beanO = bean.get(j + 1, bean);
                                     if(beanO==null){
                                         beanO="";
                                     }
                                     String beanStr=beanO.toString();
                                     cell.setCellValue(creationHelper.createRichTextString(beanStr));
                                 }
                             }
                         }else if(m!=n){
                             // 创建表
                             String sheetNameM = sheetName+"_"+m;
                             Sheet sheetK = wb.createSheet(sheetNameM);
                             for (int i = num*m; i < num*(m+1); i++) {
                                 PoiExcelBaseBean bean = listContents.get(i);
                                 int rowN = i-num*m;
                                 // 在表格创建行
                                 Row r = sheetK.createRow(rowN);
                                 // 循环列
                                 for (int j = 0; j < bean.size(); j++) {
                                     Cell cell = r.createCell(j);
                                     Object beanO = bean.get(j + 1, bean);
                                     if(beanO==null){
                                         beanO="";
                                     }
                                     String beanStr=beanO.toString();
                                     cell.setCellValue(creationHelper.createRichTextString(beanStr));
                                 }
                             }
                         }else if(m==n&&num*m!=listContents.size()){
                             // 创建表
                             String sheetNameM = sheetName+"_"+m;
                             // 创建表
                             Sheet sheetK = wb.createSheet(sheetNameM);
                             for (int i = num*m; i < listContents.size(); i++) {
                                 
                                 PoiExcelBaseBean bean = listContents.get(i);
                                 int rowN = i-num*m;
                                 // 在表格创建行
                                 Row r = sheetK.createRow(rowN);
                                 // 循环列
                                 for (int j = 0; j < bean.size(); j++) {
                                     Cell cell = r.createCell(j);
                                     Object beanO = bean.get(j + 1, bean);
                                     if(beanO==null){
                                         beanO="";
                                     }
                                     String beanStr=beanO.toString();
                                     cell.setCellValue(creationHelper.createRichTextString(beanStr));
                                 }
                             }
                         }
                     }
                 }
                 
                 return wb;  
             }  
          
          
             /**
              * 外贸出口
              * @param oarr
              * @param listContents
              * @param contentBeginRowIndex
              * @param sheetName
              * @return
              */
             public static Workbook outPutExcelWmEc(Object[] oarr, List<WmEcExpDomain> listContents, int contentBeginRowIndex, String sheetName) {
                 // 创建表格样式
                    Workbook wb = new HSSFWorkbook();
                    try {
                        // 创建帮助类
                        CreationHelper createHelper = wb.getCreationHelper();
                        // 创建表
                        Sheet sheet = wb.createSheet(sheetName);
                        // 创建表头
                        wb = createOutPutTitleRow(wb, createHelper, sheet, oarr, (int) 0);
                        // 创建内容行
                        wb = createOutPutContentRowWmEc(wb, createHelper, sheet, listContents, contentBeginRowIndex,sheetName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                    return wb;
                }

                /**
                 * 创建内容行
                 * 
                 * @title createOutPutContentRow
                 * @description TODO
                 * @author ccc-cbf02
                 * @create_date 2014-12-16
                 * @param wb
                 * @param creationHelper
                 * @param sheet
                 * @param listContents
                 * @param contentBeginRowIndex
                 */
                private static Workbook createOutPutContentRowWmEc(Workbook wb, CreationHelper creationHelper,
                        Sheet sheet, List<WmEcExpDomain> listContents, int contentBeginRowIndex,String sheetName)
                        throws Exception {
                    // 创建表内容列
                    CellStyle styleFont = wb.createCellStyle();
                    // 创建字体
                    Font font = wb.createFont();
                    // 创建字体颜色
                    font.setColor(Font.COLOR_NORMAL);
                    styleFont.setFont(font);
                    // 遍历RowList集合, 从内容行第一行开始遍历
                    int num = 5000000;
                    int n = listContents.size()/num;
                    if(n==0){
                        for (int i = contentBeginRowIndex; i <= listContents.size(); i++) {
                            PoiExcelBaseBean bean = listContents.get(i - contentBeginRowIndex);
                            // 在表格创建行
                            Row r = sheet.createRow(i);
                            // 循环列
                            for (int j = 0; j < bean.size(); j++) {
                                Cell cell = r.createCell(j);
                                Object beanO = bean.get(j + 1, bean);
                                if(beanO==null){
                                    beanO="";
                                }
                                String beanStr=beanO.toString();
                                cell.setCellValue(creationHelper.createRichTextString(beanStr));
                            }
                        }
                    }else{
                        for(int m=0;m<=n;m++){
                        
                            if(m==0){
                                for (int i = contentBeginRowIndex; i <= num; i++) {
                                    PoiExcelBaseBean bean = listContents.get(i - contentBeginRowIndex);
                                    // 在表格创建行
                                    Row r = sheet.createRow(i);
                                    // 循环列
                                    for (int j = 0; j < bean.size(); j++) {
                                        Cell cell = r.createCell(j);
                                        Object beanO = bean.get(j + 1, bean);
                                        if(beanO==null){
                                            beanO="";
                                        }
                                        String beanStr=beanO.toString();
                                        cell.setCellValue(creationHelper.createRichTextString(beanStr));
                                    }
                                }
                            }else if(m!=n){
                                // 创建表
                                String sheetNameM = sheetName+"_"+m;
                                Sheet sheetK = wb.createSheet(sheetNameM);
                                for (int i = num*m; i < num*(m+1); i++) {
                                    PoiExcelBaseBean bean = listContents.get(i);
                                    int rowN = i-num*m;
                                    // 在表格创建行
                                    Row r = sheetK.createRow(rowN);
                                    // 循环列
                                    for (int j = 0; j < bean.size(); j++) {
                                        Cell cell = r.createCell(j);
                                        Object beanO = bean.get(j + 1, bean);
                                        if(beanO==null){
                                            beanO="";
                                        }
                                        String beanStr=beanO.toString();
                                        cell.setCellValue(creationHelper.createRichTextString(beanStr));
                                    }
                                }
                            }else if(m==n&&num*m!=listContents.size()){
                                // 创建表
                                String sheetNameM = sheetName+"_"+m;
                                // 创建表
                                Sheet sheetK = wb.createSheet(sheetNameM);
                                for (int i = num*m; i < listContents.size(); i++) {
                                    
                                    PoiExcelBaseBean bean = listContents.get(i);
                                    int rowN = i-num*m;
                                    // 在表格创建行
                                    Row r = sheetK.createRow(rowN);
                                    // 循环列
                                    for (int j = 0; j < bean.size(); j++) {
                                        Cell cell = r.createCell(j);
                                        Object beanO = bean.get(j + 1, bean);
                                        if(beanO==null){
                                            beanO="";
                                        }
                                        String beanStr=beanO.toString();
                                        cell.setCellValue(creationHelper.createRichTextString(beanStr));
                                    }
                                }
                            }
                        }
                    }
                    
                    return wb;  
                }  
          
          
          
                /**
                 * 仓储进口
                 * @param oarr
                 * @param listContents
                 * @param contentBeginRowIndex
                 * @param sheetName
                 * @return
                 */
                public static Workbook outPutExcelCcIc(Object[] oarr, List<CcIcExpDomain> listContents, int contentBeginRowIndex, String sheetName) {
                    // 创建表格样式
                       Workbook wb = new HSSFWorkbook();
                       try {
                           // 创建帮助类
                           CreationHelper createHelper = wb.getCreationHelper();
                           // 创建表
                           Sheet sheet = wb.createSheet(sheetName);
                           // 创建表头
                           wb = createOutPutTitleRow(wb, createHelper, sheet, oarr, (int) 0);
                           // 创建内容行
                           wb = createOutPutContentRowCcIc(wb, createHelper, sheet, listContents, contentBeginRowIndex,sheetName);
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                       
                       return wb;
                   }

                   /**
                    * 创建内容行
                    * 
                    * @title createOutPutContentRow
                    * @description TODO
                    * @author ccc-cbf02
                    * @create_date 2014-12-16
                    * @param wb
                    * @param creationHelper
                    * @param sheet
                    * @param listContents
                    * @param contentBeginRowIndex
                    */
                   private static Workbook createOutPutContentRowCcIc(Workbook wb, CreationHelper creationHelper,
                           Sheet sheet, List<CcIcExpDomain> listContents, int contentBeginRowIndex,String sheetName)
                           throws Exception {
                       // 创建表内容列
                       CellStyle styleFont = wb.createCellStyle();
                       // 创建字体
                       Font font = wb.createFont();
                       // 创建字体颜色
                       font.setColor(Font.COLOR_NORMAL);
                       styleFont.setFont(font);
                       // 遍历RowList集合, 从内容行第一行开始遍历
                       int num = 5000000;
                       int n = listContents.size()/num;
                       if(n==0){
                           for (int i = contentBeginRowIndex; i <= listContents.size(); i++) {
                               PoiExcelBaseBean bean = listContents.get(i - contentBeginRowIndex);
                               // 在表格创建行
                               Row r = sheet.createRow(i);
                               // 循环列
                               for (int j = 0; j < bean.size(); j++) {
                                   Cell cell = r.createCell(j);
                                   Object beanO = bean.get(j + 1, bean);
                                   if(beanO==null){
                                       beanO="";
                                   }
                                   String beanStr=beanO.toString();
                                   cell.setCellValue(creationHelper.createRichTextString(beanStr));
                               }
                           }
                       }else{
                           for(int m=0;m<=n;m++){
                           
                               if(m==0){
                                   for (int i = contentBeginRowIndex; i <= num; i++) {
                                       PoiExcelBaseBean bean = listContents.get(i - contentBeginRowIndex);
                                       // 在表格创建行
                                       Row r = sheet.createRow(i);
                                       // 循环列
                                       for (int j = 0; j < bean.size(); j++) {
                                           Cell cell = r.createCell(j);
                                           Object beanO = bean.get(j + 1, bean);
                                           if(beanO==null){
                                               beanO="";
                                           }
                                           String beanStr=beanO.toString();
                                           cell.setCellValue(creationHelper.createRichTextString(beanStr));
                                       }
                                   }
                               }else if(m!=n){
                                   // 创建表
                                   String sheetNameM = sheetName+"_"+m;
                                   Sheet sheetK = wb.createSheet(sheetNameM);
                                   for (int i = num*m; i < num*(m+1); i++) {
                                       PoiExcelBaseBean bean = listContents.get(i);
                                       int rowN = i-num*m;
                                       // 在表格创建行
                                       Row r = sheetK.createRow(rowN);
                                       // 循环列
                                       for (int j = 0; j < bean.size(); j++) {
                                           Cell cell = r.createCell(j);
                                           Object beanO = bean.get(j + 1, bean);
                                           if(beanO==null){
                                               beanO="";
                                           }
                                           String beanStr=beanO.toString();
                                           cell.setCellValue(creationHelper.createRichTextString(beanStr));
                                       }
                                   }
                               }else if(m==n&&num*m!=listContents.size()){
                                   // 创建表
                                   String sheetNameM = sheetName+"_"+m;
                                   // 创建表
                                   Sheet sheetK = wb.createSheet(sheetNameM);
                                   for (int i = num*m; i < listContents.size(); i++) {
                                       
                                       PoiExcelBaseBean bean = listContents.get(i);
                                       int rowN = i-num*m;
                                       // 在表格创建行
                                       Row r = sheetK.createRow(rowN);
                                       // 循环列
                                       for (int j = 0; j < bean.size(); j++) {
                                           Cell cell = r.createCell(j);
                                           Object beanO = bean.get(j + 1, bean);
                                           if(beanO==null){
                                               beanO="";
                                           }
                                           String beanStr=beanO.toString();
                                           cell.setCellValue(creationHelper.createRichTextString(beanStr));
                                       }
                                   }
                               }
                           }
                       }
                       return wb;  
                   }  
                   /**
                    * 仓储进口
                    * @param oarr
                    * @param listContents
                    * @param contentBeginRowIndex
                    * @param sheetName
                    * @return
                    */
                   public static Workbook outPutExcelCcEc(Object[] oarr, List<CcEcExpDomain> listContents, int contentBeginRowIndex, String sheetName) {
                       // 创建表格样式
                          Workbook wb = new HSSFWorkbook();
                          try {
                              // 创建帮助类
                              CreationHelper createHelper = wb.getCreationHelper();
                              // 创建表
                              Sheet sheet = wb.createSheet(sheetName);
                              // 创建表头
                              wb = createOutPutTitleRow(wb, createHelper, sheet, oarr, (int) 0);
                              // 创建内容行
                              wb = createOutPutContentRowCcEc(wb, createHelper, sheet, listContents, contentBeginRowIndex,sheetName);
                          } catch (Exception e) {
                              e.printStackTrace();
                          }
                          
                          return wb;
                      }

                      /**
                       * 创建内容行
                       * 
                       * @title createOutPutContentRow
                       * @description TODO
                       * @author ccc-cbf02
                       * @create_date 2014-12-16
                       * @param wb
                       * @param creationHelper
                       * @param sheet
                       * @param listContents
                       * @param contentBeginRowIndex
                       */
                      private static Workbook createOutPutContentRowCcEc(Workbook wb, CreationHelper creationHelper,
                              Sheet sheet, List<CcEcExpDomain> listContents, int contentBeginRowIndex,String sheetName)
                              throws Exception {
                          // 创建表内容列
                          CellStyle styleFont = wb.createCellStyle();
                          // 创建字体
                          Font font = wb.createFont();
                          // 创建字体颜色
                          font.setColor(Font.COLOR_NORMAL);
                          styleFont.setFont(font);
                          // 遍历RowList集合, 从内容行第一行开始遍历
                          int num = 5000000;
                          int n = listContents.size()/num;
                          if(n==0){
                              for (int i = contentBeginRowIndex; i <= listContents.size(); i++) {
                                  PoiExcelBaseBean bean = listContents.get(i - contentBeginRowIndex);
                                  // 在表格创建行
                                  Row r = sheet.createRow(i);
                                  // 循环列
                                  for (int j = 0; j < bean.size(); j++) {
                                      Cell cell = r.createCell(j);
                                      Object beanO = bean.get(j + 1, bean);
                                      if(beanO==null){
                                          beanO="";
                                      }
                                      String beanStr=beanO.toString();
                                      cell.setCellValue(creationHelper.createRichTextString(beanStr));
                                  }
                              }
                          }else{
                              for(int m=0;m<=n;m++){
                              
                                  if(m==0){
                                      for (int i = contentBeginRowIndex; i <= num; i++) {
                                          PoiExcelBaseBean bean = listContents.get(i - contentBeginRowIndex);
                                          // 在表格创建行
                                          Row r = sheet.createRow(i);
                                          // 循环列
                                          for (int j = 0; j < bean.size(); j++) {
                                              Cell cell = r.createCell(j);
                                              Object beanO = bean.get(j + 1, bean);
                                              if(beanO==null){
                                                  beanO="";
                                              }
                                              String beanStr=beanO.toString();
                                              cell.setCellValue(creationHelper.createRichTextString(beanStr));
                                          }
                                      }
                                  }else if(m!=n){
                                      // 创建表
                                      String sheetNameM = sheetName+"_"+m;
                                      Sheet sheetK = wb.createSheet(sheetNameM);
                                      for (int i = num*m; i < num*(m+1); i++) {
                                          PoiExcelBaseBean bean = listContents.get(i);
                                          int rowN = i-num*m;
                                          // 在表格创建行
                                          Row r = sheetK.createRow(rowN);
                                          // 循环列
                                          for (int j = 0; j < bean.size(); j++) {
                                              Cell cell = r.createCell(j);
                                              Object beanO = bean.get(j + 1, bean);
                                              if(beanO==null){
                                                  beanO="";
                                              }
                                              String beanStr=beanO.toString();
                                              cell.setCellValue(creationHelper.createRichTextString(beanStr));
                                          }
                                      }
                                  }else if(m==n&&num*m!=listContents.size()){
                                      // 创建表
                                      String sheetNameM = sheetName+"_"+m;
                                      // 创建表
                                      Sheet sheetK = wb.createSheet(sheetNameM);
                                      for (int i = num*m; i < listContents.size(); i++) {
                                          
                                          PoiExcelBaseBean bean = listContents.get(i);
                                          int rowN = i-num*m;
                                          // 在表格创建行
                                          Row r = sheetK.createRow(rowN);
                                          // 循环列
                                          for (int j = 0; j < bean.size(); j++) {
                                              Cell cell = r.createCell(j);
                                              Object beanO = bean.get(j + 1, bean);
                                              if(beanO==null){
                                                  beanO="";
                                              }
                                              String beanStr=beanO.toString();
                                              cell.setCellValue(creationHelper.createRichTextString(beanStr));
                                          }
                                      }
                                  }
                              }
                          }
                          
                          return wb;  
                      }  
          
}
