import java.time.LocalDate;

public class BorrowingProcess {
    private final Book book;
    private final Borrower borrower;
    private final LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowingProcess(Book book, Borrower borrower) {
        this.book = book;
        this.borrower = borrower;
        this.borrowDate = LocalDate.now();
        this.returnDate = null;
    }

    public void returnBook() {
        this.returnDate = LocalDate.now();
        book.returnBook();
        borrower.returnBook(book);
    }

    public Book getBook() {
        return book;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}
