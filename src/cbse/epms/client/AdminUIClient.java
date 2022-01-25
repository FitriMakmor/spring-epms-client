package cbse.epms.client;

import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cbse.epms.bean.EmployeeManagement;
import cbse.epms.bean.PaymentManagement;

public class AdminUIClient {

    private static Integer menuInput;
    private static Scanner sc;
    private static EmployeeManagement eMBean;
    private static PaymentManagement pMBean;
    
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        eMBean = (EmployeeManagement) context.getBean("employeeManagement");        
		eMBean.initReadEmployeeFile();
		eMBean.initReadAttendanceFile();
		
		pMBean = (PaymentManagement) context.getBean("paymentManagement");
		pMBean.loadTransactionData();
		pMBean.loadPayrollData();

        System.out.println("\n\nWelcome to the Payroll Management System");

        while(true) {
            while (true){
                System.out.println("\n-----------------------MAIN MENU-----------------------");
                System.out.println("(1) Employee Management (2) Payment Management (0) Exit");
                menuInput = sc.nextInt();
                if (menuInput < 0 || menuInput > 2) {
                    System.out.println("Invalid choice. Please try again.");
                    continue;
                }
                break;
            }
            if (menuInput == 0) {
                break;
            }
            switch (menuInput) {
                case 1 -> employeeManagement();
                case 2 -> paymentManagement();
            }
        }
    }

    public static void employeeManagement() {
        int eMInput;
        while (true){
            System.out.println("\n-------------------------------------------EMPLOYEE MANAGEMENT--------------------------------------------");
            System.out.println("(1) Get List of Employees            (2) Display Employees' Attendance    (3) Get Specific Employee Detail");
            System.out.println("(4) Add a New Employee               (5) Edit an Employee                 (6) Delete an Employee");
            System.out.println("(7) Get Specific Employee Attendance (8) Mark Employee Attendance         (0) Exit");
            eMInput = sc.nextInt();
            if(eMInput == 0){
                break;
            }
            switch (eMInput) {
	            case 1 -> displayEmployeeList();
	            case 2 -> displayAttendanceList();
	            case 3 -> displayEmployeeDetails();
	            case 4 -> createNewEmployee();
	            case 5 -> selectEmployeeToEdit();
	            case 6 -> selectEmployeeToDelete();
	            case 7 -> selectEmployeeToViewAttendance();
	            case 8 -> selectEmployeeToMarkAttendance();
            }
        }
    }

    public static void paymentManagement() {
        int pMInput;
        while (true){
            System.out.println("\n--------------------------------------------PAYMENT MANAGEMENT--------------------------------------------");
            System.out.println("(1) Display Transaction Logs         (2) Display Employee Payrolls        (3) Set Employee's Payroll");
            System.out.println("(4) Delete Employee's Payroll        (5) Execute Employee's Payment       (0) Exit");
            pMInput = sc.nextInt();
            if(pMInput == 0){
                break;
            }
            switch (pMInput) {
	            case 1 -> displayEmployeeTransactionLogs();
	            case 2 -> displayEmployeePayrolls();
	            case 3 -> selectEmployeeToSetPayroll();
	            case 4 -> selectEmployeeToDeletePayroll();
	            case 5 -> selectEmployeeToPay();
            }
        }
    }

    public static void displayAttendanceList(){
		eMBean.getAttendanceList();
    }

    public static void displayEmployeeTransactionLogs(){
    	pMBean.getEmployeeTransaction();
    }

    public static void displayEmployeeList(){
    	eMBean.displayListOfEmployee();
    }

    public static void displayEmployeeDetails(){
		eMBean.displayEmployeeDetails();
    }

    public static void createNewEmployee(){
    	eMBean.addEmployee();
    }

    public static void displayEmployeePayrolls(){
    	pMBean.getEmployeePayrolls();
    }

    public static void selectEmployeeToViewAttendance(){
    	eMBean.getAttendance();
    }

    public static void selectEmployeeToMarkAttendance(){
    	eMBean.markAttendance();
    }

    public static void selectEmployeeToDelete(){
		eMBean.deleteEmployee();
    }

    public static void selectEmployeeToEdit(){
    	eMBean.setEmployee();
    }  	

    public static void selectEmployeeToSetPayroll(){
    	pMBean.setPayroll();
    }

    public static void selectEmployeeToDeletePayroll(){
    	pMBean.deletePayroll();
    }

    public static void selectEmployeeToPay(){
    	pMBean.executePayment();
    }

}
