// صنف يمثل كتاب في المكتبة
   public class Book {
    // خصائص الكتاب
    private final String title;   // عنوان الكتاب
    private final String author;  // مؤلف الكتاب
    private final String isbn;    // رقم ISBN
    private boolean isBorrowed;   // حالة الإعارة


    // منشئ الكتاب
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
    }


    // هل الكتاب معار؟
    public boolean isBorrowed() {
        return isBorrowed;
    }


    // إعارة الكتاب
    public void borrow() {
        this.isBorrowed = true;
    }


    // استرجاع الكتاب
    public void returnBook() {
        this.isBorrowed = false;
    }

    // ⚙️ Getter لعنوان الكتاب
    public String getTitle() {
        return title;
    }

    // ⚙️ Getter لاسم المؤلف
    public String getAuthor() {
        return author;
    }

    // ⚙️ Getter لـ ISBN
    public String getISBN() {
        return isbn;
    }

    // 🖨️ تمثيل نصي للكتاب (للطباعة)
    @Override
    public String toString() {
        return "📕 " + title + " - " + author + " (ISBN: " + isbn + ") " + (isBorrowed ? "معار" : "متوفر");
    }
}
