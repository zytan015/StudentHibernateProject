package org.dxc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.dxc.bean.Student;
import org.dxc.factorydesign.ServiceFactory;
import org.dxc.service.StudentService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Scanner scan = new Scanner(System.in);
        System.out.println("Press 1 -> To insert student record");
        System.out.println("Press 2 -> To get student record");
        System.out.println("Press 3 -> To list all student record");
        System.out.println("Press 4 -> To update student details");
        System.out.println("Press 5 -> To delete student record");
        System.out.println("Press 6 -> To exit app");
        int input = scan.nextInt();
        //scan.nextLine();
        
        StudentService service = ServiceFactory.getServiceObject();
        Student s = new Student();
        int id;
        switch(input) {
        case 1: // insert
        	System.out.println("Student Name:");
        	String name = scan.nextLine();
        	System.out.println("Student Address:");
        	String addr = scan.nextLine();
        	s = new Student();
        	s.setSname(name);
        	s.setSaddr(addr);
        	
        	System.out.println((service.insert(s)) != 0 ? "Student details uploaded" : "Insertion unsuccessful");
        	break;
        case 2: // get details
        	System.out.println("Enter student id and hit enter:");
        	id = scan.nextInt();
        	scan.nextLine();
        	
        	s = service.get(id);
        	System.out.println(s != null ? s : "Student does not exist");
        	break;
        case 3: // list
        	service.list();
        	break;
        case 4: //update
        	try {
        		System.out.println("Student ID:");
    			id = scan.nextInt();
    			scan.nextLine();
    			s=service.get(id);
    			BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
    			System.out.print("Student Name[old name:"+s.getSname()+"]:");
    			String sname=b.readLine();
    			if(sname==null ||sname=="") {
    				sname=s.getSname();
    			}
    			System.out.print("Student Address[old address:"+s.getSaddr()+"]:");
    			String saddr=b.readLine();
    			if(saddr==null||saddr=="") {
    				saddr=s.getSaddr();
    			}
    			s.setSname(sname);
    			s.setSaddr(saddr);
    			
    			service.update(s);
    			System.out.println("Updated");
            	break;
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        case 5: // delete
        	System.out.println("Student ID:");
        	id = scan.nextInt();
        	scan.nextLine();
        	
        	service.delete(id);
        	System.out.println("Student has been removed");
        	break;
        	
        case 6:
        	System.out.println("Exit program");
        	System.exit(0);
        default:
        	System.out.println("Please provide valid input");
        }
    }
}
