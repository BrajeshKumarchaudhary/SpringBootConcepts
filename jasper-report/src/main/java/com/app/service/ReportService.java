package com.app.service;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.app.entity.Employee;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import java.io.File;

@Service
public class ReportService {
	public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "/home/dheeraj/Desktop/Report/";
        List<Employee> employees =prepareData();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Brajesh Kumar");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "employees.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "employees.pdf");
        }

        return "report generated in path : " + path;
	}

private static List<Employee> prepareData(){
	List<Employee> data=new LinkedList<>();
    double salary=new Double(14000000.0);
	   data.add(new Employee(1, "brajesh","Software devloper", salary,"2020-03-20"));
	   data.add(new Employee(2, "brajesh","Software devloper", salary,"2020-03-20"));
	   data.add(new Employee(3, "brajesh","Software devloper", salary,"2020-03-20"));
	   data.add(new Employee(4, "brajesh","Software devloper", salary,"2020-03-20"));
	   data.add(new Employee(5, "brajesh","Software devloper", salary,"2020-03-20"));
	   data.add(new Employee(6, "brajesh","Software devloper", salary,"2020-03-20"));
	   return data;
}
}

