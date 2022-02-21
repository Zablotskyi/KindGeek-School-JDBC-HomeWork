import java.sql.*;
import java.util.Scanner;

public class Application {
    static final String USER = "root";
    static final String PASSWORD = "rootroot";
    static final String URL = "jdbc:mysql://localhost:3306/authorsBooks_kindGeek_school_JDBC_homeWork?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false";

        static final String INSERT_AUTHOR = "insert into authors(name, lastName) values " +
            "('%s', '%s')";
    static final String INSERT_BOOK = "insert into books(title, yearOfPrinting) values " +
            "('%s', '%d')";
    static Scanner sc = new Scanner(System.in);

    public void workingWithDatabase() throws SQLException {
        System.out.println("press 1 to create author\n" +
                "press 2 to create book\n" +
                "press 3 to find the author by name\n" +
                "press 4 to find the author by ID\n" +
                "press 5 to find the book by title\n" +
                "press 6 to find a book by ID\n" +
                "press 7 to show all authors\n" +
                "press 8 to show all books\n" +
                "press 0 to exit");
        String step = sc.nextLine();
        switch (step) {
            case "1": {
                createAuthor();
            }
            case "2": {
                createBook();
            }
            case "3": {
                findAuthorByName();
            }
            case "4": {
                findAuthorByID();
            }
            case "5": {
                findBookByTitle();
            }
            case "6": {
                findBookByID();
            }
            case "7": {
                findAllAuthors();
            }
            case "8": {
                findAllBooks();
            }
            case "0": {
                System.exit(0);
            }
        }
    }

    private void createAuthor() throws SQLException {
        Connection connectionAuthorsBooks = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement st = connectionAuthorsBooks.createStatement();
        System.out.println("enter the author's first name:");
        String name = sc.nextLine();
        System.out.println("enter the author's last name:");
        String lastName = sc.nextLine();
        st.execute(String.format(INSERT_AUTHOR, name, lastName));
        System.out.println("author added: " + name + " " + lastName);
        connectionAuthorsBooks.close();
        st.close();
        workingWithDatabase();
    }

    private void createBook() throws SQLException {
        Connection connectionAuthorsBooks = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement st = connectionAuthorsBooks.createStatement();
        System.out.println("enter book title:");
        String title = sc.nextLine();
        System.out.println("enter the year of publication of the book:");
        int yearOfPrinting = Integer.parseInt(sc.nextLine());
        st.execute(String.format(INSERT_BOOK, title, yearOfPrinting));
        System.out.println("book added: " + title + " " + yearOfPrinting);
        connectionAuthorsBooks.close();
        st.close();
        workingWithDatabase();
    }

    private void findAuthorByName() throws SQLException {
        Connection connectionAuthorsBooks = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement st = connectionAuthorsBooks.createStatement();
        PreparedStatement preparedStatement = connectionAuthorsBooks.prepareStatement("select * from authors " +
                "where name like ? and lastName like ?");
        System.out.println("enter the author's first name:");
        String inputName = sc.nextLine();
        System.out.println("enter the author's last name:");
        String inputLastName = sc.nextLine();
        preparedStatement.setString(1, "%" + inputName + "%");
        preparedStatement.setString(2, "%" + inputLastName + "%");
        ResultSet rs = preparedStatement.executeQuery();
        System.out.println("author found: ");
        while (rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            String lastName = rs.getString("lastName");
            System.out.println(id + ". " + name + " " + lastName);
        }
        connectionAuthorsBooks.close();
        st.close();
        preparedStatement.close();
        rs.close();
        workingWithDatabase();
    }

    private void findAuthorByID() throws SQLException {
        Connection connectionAuthorsBooks = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement st = connectionAuthorsBooks.createStatement();
        PreparedStatement preparedStatement = connectionAuthorsBooks.prepareStatement("select * from authors " +
                "where id = ?");
        System.out.println("enter the author's ID:");
        String inputID = sc.nextLine();
        preparedStatement.setString(1, inputID);
        ResultSet rs = preparedStatement.executeQuery();
        System.out.println("author found: ");
        while (rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            String lastName = rs.getString("lastName");
            System.out.println(id + ". " + name + " " + lastName);
        }
        connectionAuthorsBooks.close();
        st.close();
        preparedStatement.close();
        rs.close();
        workingWithDatabase();
    }

    private void findBookByTitle() throws SQLException {
        Connection connectionAuthorsBooks = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement st = connectionAuthorsBooks.createStatement();
        PreparedStatement preparedStatement = connectionAuthorsBooks.prepareStatement("select * from books " +
                "where title like ?");
        System.out.println("enter book title:");
        String inputTitle = sc.nextLine();
        preparedStatement.setString(1, "%" + inputTitle + "%");
        ResultSet rs = preparedStatement.executeQuery();
        System.out.println("book found: ");
        while (rs.next()) {
            long id = rs.getLong("id");
            String title = rs.getString("title");
            int yearOfPrinting = rs.getInt("yearOfPrinting");
            System.out.println(id + ". " + title + " " + yearOfPrinting + " y.");
        }
        connectionAuthorsBooks.close();
        st.close();
        preparedStatement.close();
        rs.close();
        workingWithDatabase();
    }

    private void findBookByID() throws SQLException {
        Connection connectionAuthorsBooks = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement st = connectionAuthorsBooks.createStatement();
        PreparedStatement preparedStatement = connectionAuthorsBooks.prepareStatement("select * from books " +
                "where id = ?");
        System.out.println("enter the book's ID:");
        String inputID = sc.nextLine();
        preparedStatement.setString(1, inputID);
        ResultSet rs = preparedStatement.executeQuery();
        System.out.println("book found: ");
        while (rs.next()) {
            long id = rs.getLong("id");
            String title = rs.getString("title");
            int yearOfPrinting = rs.getInt("yearOfPrinting");
            System.out.println(id + ". " + title + " " + yearOfPrinting + " y.");
        }
        connectionAuthorsBooks.close();
        st.close();
        preparedStatement.close();
        rs.close();
        workingWithDatabase();
    }

    private void findAllAuthors() throws SQLException {
        Connection connectionAuthorsBooks = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement st = connectionAuthorsBooks.createStatement();
        ResultSet rs = st.executeQuery("select * from authors");
        System.out.println("all authors: ");
        while (rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            String lastName = rs.getString("lastName");
            System.out.println(id + ". " + name + " " + lastName);
        }
        connectionAuthorsBooks.close();
        st.close();
        rs.close();
        workingWithDatabase();
    }

    private void findAllBooks() throws SQLException {
        Connection connectionAuthorsBooks = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement st = connectionAuthorsBooks.createStatement();
        ResultSet rs = st.executeQuery("select * from books");
        System.out.println("all books: ");
        while (rs.next()) {
            long id = rs.getLong("id");
            String title = rs.getString("title");
            int yearOfPrinting = rs.getInt("yearOfPrinting");
            System.out.println(id + ". " + title + " " + yearOfPrinting + " y.");
        }
        connectionAuthorsBooks.close();
        st.close();
        rs.close();
        workingWithDatabase();
    }
}