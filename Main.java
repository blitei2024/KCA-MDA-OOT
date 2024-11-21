// Interface DataSource
interface DataSource {
	void execute();  // Abstract method that must be implemented by subclasses
}

// Classes implementing DataSource Interface
class Update implements DataSource {
	@Override
	public void execute() {
		System.out.println("Update operation executed.");
	}
}

class View implements DataSource {
	@Override
	public void execute() {
		System.out.println("View operation executed.");
	}
}

class Delete implements DataSource {
	@Override
	public void execute() {
		System.out.println("Delete operation executed.");
	}
}

// Account class using DataSource
class Account {
	private int id;
	private String name;
	private DataSource myData;

	// Constructor
	public Account(int id, String name, DataSource myData) {
		this.id = id;
		this.name = name;
		this.myData = myData;
	}
	// Method that performs an operation using DataSource
	public void performOperation() {
		myData.execute();  // Executes the operation based on the passed DataSource instance
	}
}

// Admin class extends Account
class Admin extends Account {
	private String password;

	// Constructor
	public Admin(int id, String name, String password) {
		super(id, name, new Update());  // By default, Admin has Update as DataSource
		this.password = password;
	}
}

// User class extends Account
class User extends Account {
	// Constructor
	public User(int id, String name) {
		super(id, name, new View());  // By default, User has View as DataSource
	}
}

// Test class to demonstrate usage
public class Main {
	public static void main(String[] args) {
		// Create an Admin with Update operation
		Admin admin = new Admin(1, "AdminUser", "admin123");
		System.out.println("Admin performing operation:");
		admin.performOperation();  // Will execute Update operation

		// Create a User with View operation
		User user = new User(2, "RegularUser");
		System.out.println("User performing operation:");
		user.performOperation();  // Will execute View operation

		// Create an Account with Delete operation
		Account deleteAccount = new Account(3, "DeleteAccount", new Delete());
		System.out.println("Delete Account performing operation:");
		deleteAccount.performOperation();  // Will execute Delete operation
	}
}
