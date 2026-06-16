package Banking_Operations;

import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Scanner;

import javax.print.attribute.standard.JobKOctets;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Banking_Operations;
import org.hibernate.query.Query;

import com.mysql.cj.callback.UsernameCallback;
public class BankOperations {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Configuration cfg = new Configuration();
		
		cfg.configure("hibernate.cfg.xml")
		.addAnnotatedClass(Banking_Operations.class);

		SessionFactory factory = cfg.buildSessionFactory();
		
		Session session = factory.openSession();
		
		while(true) {
			
			System.out.println("*****    BANK MENU    *****");
			
			//Menu Options
			System.out.println("1. Create Account");
			System.out.println("2. View Accounts");
			System.out.println("3. View Balance");
			System.out.println("4. Deposite Money");
			System.out.println("5. Withdraw Money");
			System.out.println("6. Delete Account");
			System.out.println("7. Exit");
			
			System.out.println("Enter Your Choice : ");
			int choice = sc.nextInt();
			
			//Create Account
			if (choice == 1) {
				
				Transaction tx = session.beginTransaction();
				Banking_Operations bk = new Banking_Operations();
				
				System.out.println("Enter Username : ");
				bk.setUsername(sc.nextLine());
				sc.nextLine();
				
				System.out.println("Enter Password : ");
				bk.setPassword(sc.nextLine());
				sc.nextLine();
				
				System.out.println("Enter Your Account Number : ");
				bk.setAcc_num(sc.nextLong());
				sc.nextLine();

				System.out.println("Enter Account Holder Name : ");
				bk.setAcc_name(sc.nextLine());
				
				System.out.println("Enter Your Account Balance : ");
				bk.setAcc_bal(sc.nextDouble());
				    
				sc.nextLine();

				System.out.println("Enter Your Branch Name : ");
				bk.setBranch_name(sc.nextLine());
				
				session.save(bk);
				tx.commit();
				
				System.out.println("Your Account is Created Successfully!");
				
			}
			
			else if (choice == 2) {
				
				Query query = session.createQuery("FROM Banking_Operations");
				
				List<Banking_Operations>list = query.list();
				
//				for (int i=0; i<list.size(); i++)
				for(Banking_Operations bk : list){
					
					
//					Banking_Operations bk = list.get(i);
					System.out.println("------------------------------------");
					
					System.out.println("Account Number : " +bk.getAcc_num());
					System.out.println("Account Holder Name : " +bk.getAcc_name());
					System.out.println("Account Balance : " +bk.getAcc_bal());
					System.out.println("Branch Name : " +bk.getBranch_name());
					
				}
			}
			
			else if (choice == 3) {
				
				Transaction tx = session.beginTransaction();
				
				System.out.println("Enter Your Account Number : ");
				long acc_num=sc.nextLong();
				sc.nextLine();
			
				Query query = session.createQuery("FROM Banking_Operations where acc_num = :acc_num",Banking_Operations.class);
				
				query.setParameter("acc_num", acc_num);
				
				List<Banking_Operations>list = query.list();
				
//				for (int i=0; i<list.size(); i++)
				for(Banking_Operations bk1 : list) {
					
					
//					Banking_Operations bk = list.get(i);
					System.out.println("------------------------------------");
					
					System.out.println("Account Number : " +bk1.getAcc_num());
					System.out.println("Account Holder Name : " +bk1.getAcc_name());
					System.out.println("Account Balance : " +bk1.getAcc_bal());
					System.out.println("Branch Name : " +bk1.getBranch_name());
					tx.commit();
				}
				
				if (list.isEmpty()) {
					System.out.println("Account not Found");
				}
			}
			
			else if (choice == 4) {
				
				//Depositing Money
				Transaction tx = session.beginTransaction();
				
				System.out.println("Enter Your valid Account Number : ");
				long acc_num = sc.nextLong();
				sc.nextLine();
				Banking_Operations bk = session.get(Banking_Operations.class, acc_num);	
				
				System.out.println("Enter Username : ");
				String username = sc.nextLine();

				System.out.println("Enter Password : ");
				String password = sc.nextLine();

				if (bk.getUsername().equals(username) && bk.getPassword().equals(password)) {
					
					System.out.println("Login Success");
					
					System.out.println("Enter Amount : ");
			        double amount = sc.nextDouble();
			        
					
					if (amount > 0) {
						
						bk.setAcc_bal(bk.getAcc_bal() + amount);
						
						session.update(bk);
						
						System.out.println("Amount Deposited Successfully😊😊");
						
						System.out.println("Current Account Balance : " +bk.getAcc_bal());
					}
					
					else {
						
						System.out.println("Account not Found");
					}
				tx.commit();
			}
				else {
					System.out.println("Enter correct Credentials");
				}
				
			}
			else if (choice == 5) {
				
				//Withdraw Money
				Transaction tx = session.beginTransaction();
				
				System.out.println("Enter Your valid Account Number : ");
				long acc_num = sc.nextLong();
				Banking_Operations bk = session.get(Banking_Operations.class, acc_num);
				
				System.out.println("Enter Amount");
				double amount = sc.nextDouble();
				
				if (amount <= bk.getAcc_bal()) {
					
					bk.setAcc_bal(bk.getAcc_bal() - amount);
					
					session.update(bk);
					
					tx.commit();
					
					System.out.println("Amount Withdrawn Successfully😊😊");
					
					System.out.println("Current Account Balance : " +bk.getAcc_bal());
				}
				else {
					System.out.println("Insuficient Funds in Your Account😭😭");
					
					tx.rollback();
					
					System.out.println("Current Account Balance : " +bk.getAcc_bal());
				}
			
			}
			
			else if (choice == 6) {
				
				//Delete Account
				Transaction tx = session.beginTransaction();
				
				System.out.println("Enter Your valid Account Number : ");
				long acc_num = sc.nextLong();
				sc.nextLine();
				
				System.out.println("Enter Username : ");
			    String username = sc.nextLine();

			    System.out.println("Enter Password : ");
			    String password = sc.nextLine();
			    
				Banking_Operations bk = session.get(Banking_Operations.class, acc_num);
				
				if (bk != null && bk.getUsername().equals(username) && bk.getPassword().equals(password)) {
				session.delete(bk);
				tx.commit();
				System.out.println("Your Account is Deleted Sucessfully😊😊");
				}
				
				else {
					System.out.println("Invalid Credentials");
				}
			}
			
			//print statement
			else if (choice == 7) {
				
				System.out.println("Thank You❤️❤️❤️");
				break;
			}
			
			else {
				System.out.println("Your choice is Invalid🥲🥲");
			}
		
		
 	}
		session.close();
		factory.close();
		sc.close();

}
}
