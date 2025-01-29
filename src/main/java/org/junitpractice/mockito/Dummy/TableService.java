package org.junitpractice.mockito.Dummy;

public class TableService implements TableRepository{
    public TableRepository tableRepository;
     public TableService(TableRepository tableRepository){
         this.tableRepository=tableRepository;
     }
    public String getTableName(){
        return tableRepository.getTableName();
    };
}
