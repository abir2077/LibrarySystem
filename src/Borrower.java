import java.util.ArrayList;

// 👤 هذا الكلاس يمثل مستعير (طالب مثلاً)
public class Borrower {

    // بيانات المستعير
    private final String name; // الاسم
    private final String universityId;  // الرقم الجامعي
    private ArrayList<Book> borrowedBooks;  // قائمة الكتب المستعارة

    // 🔧 منشئ المستعير
    public Borrower(String name, String universityId) {
        this.name = name;
        this.universityId = universityId;
        borrowedBooks = new ArrayList<>();
    }

    // 📚 إضافة كتاب إلى قائمة الكتب المستعارة
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

        // 📤 إزالة كتاب عند الاسترجاع

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }


    // 📄 جلب الكتب المستعارة
    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // ⚙️ Getter للاسم
    public String getName() {
        return name;
    }

    // ⚙️ Getter للرقم الجامعي
    public String getUniversityId() {
        return universityId;
    }

    // 🖨️ تمثيل نصي للمستعير
    @Override
    public String toString() {
        return "👤 " + name + " (ID: " + universityId + ")";
    }

    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
