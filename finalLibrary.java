package Library;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.text.ParseException;

/*
 Create a library management system which is capable of issuing books to the students.
 Book should have info like:
 1. Book name
 2. Book Author
 3. Issued to
 4. Issued on
 User should be able to add books, return issued books, issue books
 Assume that all the users are registered with their names in the central database
  */
class Book{
    private final String title;
    private String authorName;
    
    private int numCopies;

    public Book(String title, String authorName,int numCopies) {
        this.title = title;
        this.authorName = authorName;
        this.numCopies= numCopies;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getNumCopies() {
        return numCopies;
    }


    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }

    @Override
    public String toString() {
        return "Book " +
                "name='" + title + '\'' +
                ", author='" + authorName + '\'' +
                numCopies+" Copies";
    }
}


class User{
    //User Details
    private String firstName;
    private String lastName;
    private int userId;
    
    //User Books Inventory
    private Map<Book, LocalDate> booksList = new HashMap<Book, LocalDate>();

    //Constructors
    public User() {
	
    }
    
  
    public User(int id, String firstName,String lastName) {
	
         this.userId = id;
         this.firstName=firstName;
         this.lastName=lastName;
    }
    //Getters and Setters and toString
    public Map<Book, LocalDate> getBooksList() {
        return booksList;
    }


    public void setBooksList(Map<Book, LocalDate> booksList) {
        this.booksList = booksList;
    }
    
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getUserId() {
        return userId;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
	return "User [firstName=" + firstName + ", lastName=" + lastName + ", userId=" + userId + ", booksList="
		+ booksList + "]";
    }
    
}

class MyLibrary{
    LocalDate myObj = LocalDate.now();
    
    List<String> list = new ArrayList<>(); 
    
    private static final SimpleDateFormat dateFormat 
    = new SimpleDateFormat("dd/MM/yyyy");
    private static final Date invalidDate = new Date(0);
    
    Map<Book,Integer> books = new HashMap<>(); //map holding Books
    
    Map<Integer, Map<Book,List<String>> > map = new HashMap<Integer, Map<Book,List<String>>>(); //map holding Issuing of books to User based on ID
    
    Map<Book,List<String>> inner = new HashMap<>(); //Inner Map
    Map<Integer, Map<Book,List<String>> > booksReturn = new HashMap<Integer, Map<Book,List<String>>>();
    Map<Book,List<String>> inner1 = new HashMap<>();
//    public MyLibrary(ArrayList<Book> books) {
//        this.books = books;
//    }
    public void listbooks() {
	System.out.println("The Total Available Books in Inventory are:- \n"+books);
    }
    public void listUsers() {
	System.out.println("Total Books Issued to Users are:- \n"+map);
    }
    public void listUser(int id) {
	System.out.println("Borrowed List of Books on Requested User with Id "+id+" are:- \n"+map.get(id));
    }
    
    public void listReturned() {
	System.out.println("Total Books Returned are:- \n"+booksReturn);
    }
    
    public void listReturnedByUserId(int id) {
	System.out.println("Returned List of Books on Requested User with Id "+id+" are:- \n"+booksReturn.get(id));

    }
    
    public void addBook(Book book,int count){
	books.put(book, count);
        System.out.println("The "+book+" has been added to the library with quantity of "+count);
         }
    
    public void issueBook(int id,Book book,String s){
	inner.putIfAbsent(book,new ArrayList<String>());
	map.putIfAbsent(id,inner);
	((Map)map.get(id)).put(book,s);
//	map.get(id).put(book,inner);
//	 map.put(user,book );
        System.out.println("The "+book+"has been issued from the library to " + id);
        int val = books.get(book);
        books.put(book, val-1);
    }
    
    public void returnBook(int id,Book b,String s){
        System.out.println("The "+b+" has been returned by "+id );
        ((Map)map.get(id)).remove(b);
        int val = books.get(b);
        books.put(b, val+1);
        inner1.putIfAbsent(b,new ArrayList<String>());
	booksReturn.putIfAbsent(id,inner1);
	((Map)booksReturn.get(id)).put(b,s);
        
    }
    
    /**
     * Creates a date from the given string spec. 
     * The date format must be dd/MM/yyyy ie. 
     * 24 december 2009 would be: 24/12/2009
     * @return invalidDate if the format is invalid.
     */
//    private static final Date fromString( String spec ) {
//        try {
//            return dateFormat.parse( spec );
//        } catch( ParseException dfe ) {
//            return invalidDate;
//        }
//    }
//    private static final void print( Date date ) {
//        if( date == invalidDate ) {
//            out.println("Invalid date");
//        } else {
//            out.println( dateFormat.format( date ) );
//        }
//    }

}
public class Library {
    
   public static void main(String[] args) {
       MyLibrary l = new MyLibrary();
       User u=new User();
        System.out.println("========Library Management System========");
        System.out.println("Welcome to My Program If this is a Inital Run Please \n"
        	+ "Consider Adding Books and Users Accordingly as this is a Console Program and Not an App!!");
        System.out.println("Please Enter a Choice");
        System.out.println("1.Add Users \n"
        	+ "2.Add Books \n"
        	+ "3.Issue a Book \n"
        	+ "4.Return a Book \n"
        	+ "5.Print the List of Books \n"
        	+ "6.Print the Library Register \n");
        
        //adding Users
	User u1=new User(1,"ABC");
	User u2=new User(2,"DEF");
	User u3=new User(3,"GHI");
	User u4=new User(4,"JKL");
	
	//adding Books
        Book b1 = new Book("Algorithms", "CLRS");
        l.addBook(b1,2);

        Book b2 = new Book("Algorithms2", "CLRS2");
        l.addBook(b2,2);

        Book b3 = new Book("Algorithms3", "CLRS3");
        l.addBook(b3,2);

        Book b4 = new Book("Algorithms4", "CLRS4");
        l.addBook(b4,2);
//        new Book("Algorithms5", "CLRS5")
        
//        l.addBook(new Book("algo4", "myAuthor"));
        
        l.issueBook(1,b4,"01/09/2022");
        l.issueBook(1,b3,"02/09/2022");
        l.issueBook(1,b1,"03/09/2022");
        l.issueBook(2,b4,"02/09/2022");
        l.issueBook(2,b3,"02/09/2022");
//        l.issueBook(u4, b1);
        
//        l.issueBook(u2, b1);
        System.out.println("================================================");

        l.listUsers();
        System.out.println("================================================");

        l.listbooks();
        System.out.println("================================================");

        l.returnBook(1,b4,"02/09/2022");
        l.returnBook(2,b3,"04/09/2022");
        
        System.out.println("================================================");
        l.listbooks();
        System.out.println("================================================");
        l.listUser(1);
        System.out.println("================================================");
        l.listReturned();
        System.out.println("================================================");
        l.listReturnedByUserId(2);
        System.out.println("================================================");
        u1.getUserDetails();
    }
}
