package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

import static java.sql.DriverManager.getConnection;

public class Main {

    public static void main(String[] args)throws Exception {
        MySqlRepository mySqlRepository= new MySqlRepository();
        Optional<FileDTO> res=mySqlRepository.findByPath("a1");
       if(res.isPresent()){
            System.out.println(res.get().getCreateTime());
        }


    }


}
