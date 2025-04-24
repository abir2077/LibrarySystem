import java.time.LocalDate;


// 🔄 هذا الكلاس يمثل عملية إعارة كتاب من مستعير
public class BorrowingProcess {
    private final Book book;  // الكتاب المعار
    private final Borrower borrower;  // الشخص المستعير
    private final LocalDate borrowDate;  // تاريخ الإعارة
    private LocalDate returnDate;  // تاريخ الاسترجاع

    // 🔧 منشئ العملية
    public BorrowingProcess(Book book, Borrower borrower) {
        this.book = book;
        this.borrower = borrower;
        this.borrowDate = LocalDate.now();  // تاريخ اليوم
        this.returnDate = null;   // لم يتم الإرجاع بعد
    }

    // ✅ عند استرجاع الكتاب
    public void returnBook() {
        this.returnDate = LocalDate.now();  // تسجل تاريخ الإرجاع
        book.returnBook();  // استرجاع فعلي للكتاب
        borrower.returnBook(book);   // إزالة الكتاب من المستعير
    }

    // ⚙️ Getter للكتاب
    public Book getBook() {
        return book;
    }

    // ⚙️ Getter للمستعير
    public Borrower getBorrower() {
        return borrower;
    }

    // ⚙️ Getter لتاريخ الإعارة
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    // ⚙️ Getter لتاريخ الاسترجاع
    public LocalDate getReturnDate() {
        return returnDate;
    }
}
