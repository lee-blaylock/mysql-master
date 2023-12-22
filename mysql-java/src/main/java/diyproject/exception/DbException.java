package diyproject.exception;

@SuppressWarnings("serial")
public class DbException extends RuntimeException {

	public DbException() {
		// instructions say to override constructors below however error
		//error appears when I try
	}
//@Override commenting out due to error
	public DbException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
//@Override
	public DbException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
//@Override
	public DbException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
//deleted last constructor per Dr Ron stating not used most of time
	//when creating this type of class under recipes project
}
