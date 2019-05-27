package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class MySqlRepository {

   public Optional findByPath(String directoryPath){
       FileDTO fileDTO= new FileDTO();
       Optional<FileDTO> result;
       String tblName="fileManager";
       String user="root";
       String pass="";
       try{
           Class.forName("com.mysql.jdbc.Driver");
           Connection connection=DriverManager.getConnection(String.format("jdbc:mysql://localhost:3306/%s",tblName),user,pass);
           Statement stm=connection.createStatement();
           ResultSet resultSet=stm.executeQuery(String.format("select * from directories where fileaddress='%s'",directoryPath));
           while (resultSet.next()){
               fileDTO.setFileName(resultSet.getString("fileName"));
               fileDTO.setLastModified(resultSet.getLong("lastModification"));
               fileDTO.setCreateTime(resultSet.getLong("creationTime"));
               fileDTO.setFileSize(resultSet.getLong("fileSize"));
               fileDTO.setAddress(resultSet.getString("fileaddress"));
           }
           result=Optional.ofNullable(fileDTO);
           return result;
       }catch(Exception e){
           System.out.println(e.getMessage());
           return Optional.empty();
       }
   }
}
