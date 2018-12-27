import java.util.Comparator;
import java.util.Arrays;

class Challenge {
    

  
  public static String sortCsvColumns( String csv_data ) {

   class CSVColumn{
    private String colName;
    private String[] val;
    
    public CSVColumn(String colName,int rows){
      this.colName = colName;
      this.val = new String[rows];
      for(int i=0;i<rows;++i) this.val[i]="";
    }
    
    public String getColName(){
      return colName;
    }
    
    //public String[] getVal(){
    //  return val;
    //}
    
    public void setVal(int index,String value){
      val[index] = value;
    }
    
    public String getVal(int index){
      return val[index];
    }
  }
    
      String[] lines = csv_data.split("\n");
      String[] colNames = lines[0].split(",");
      String[][] content = new String[lines.length - 1][];

      CSVColumn[] columns = new CSVColumn[colNames.length];
      for(int i=0;i<colNames.length;++i)
        columns[i] = new CSVColumn(colNames[i],lines.length-1);
    
      for(int i=1;i<lines.length;++i){
          content[i-1] = lines[i].split(",");
          for(int j=0;j<content[i-1].length;++j)
            columns[j].setVal(i-1,content[i-1][j]);

      }
      
      Comparator<CSVColumn> colComparator = new Comparator<CSVColumn>(){
                  @Override
                  public int compare(CSVColumn c1,CSVColumn c2){
                      return c1.getColName().compareToIgnoreCase(c2.getColName());
                  }
                };
    
    Arrays.sort(columns,colComparator);
    
    String result = "";
    
    for(int i=0;i<colNames.length;++i){
      result+=columns[i].getColName();
      if(i!=columns.length-1) result+=",";
    }
    
    for(int i=0;i<content.length;++i){
      if(i==0) result+="\n";
      for(int j=0;j<colNames.length;++j){
        result+=columns[j].getVal(i);
        if(j < colNames.length - 1) result+=",";
      }
      if(i < content.length - 1) result+="\n";
    }
    //System.out.println(result);
    return result;
  }
}
