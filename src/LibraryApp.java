import java.util.*;


// 🎮 هذا الكلاس يحتوي على واجهة المستخدم (Console) لإدارة المكتبة

public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 🗃️ مجموعات البيانات
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Borrower> borrowers = new ArrayList<>();
        ArrayList<BorrowingProcess> processes = new ArrayList<>();

        // 🔁 حلقة لا نهائية حتى يختار المستخدم الخروج
        while (true) {
            try {
                // 🖥️ عرض القائمة الرئيسية
                System.out.println("\n📚 نظام إدارة المكتبة");
                System.out.println("1. إضافة كتاب");
                System.out.println("2. إضافة مستعير");
                System.out.println("3. إعارة كتاب");
                System.out.println("4. استرجاع كتاب");
                System.out.println("5. عرض الكتب المستعارة لمستعير");
                System.out.println("6. البحث عن كتاب أو مستعير");
                System.out.println("0. خروج");
                System.out.print("اختر العملية: ");

                // لتفادي مشاكل قراءة السطر
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1 -> addBook(scanner, books);  // ➕ إضافة كتاب

                    case 2 -> addBorrower(scanner, borrowers);  // ➕ إضافة مستعير

                    case 3 -> borrowBook(scanner, books, borrowers, processes);  // 📦 إعارة كتاب

                    case 4 -> returnBook(scanner, processes);  // 🔁 استرجاع كتاب

                    case 5 -> displayBorrowedBooks(scanner, borrowers);  // 📜 عرض الكتب المستعارة من مستعير

                    case 6 -> search(scanner, books, borrowers);  // 🔍 البحث

                    case 0 -> {
                        System.out.println("👋 مع السلامة!");
                        return;
                    }

                    default -> System.out.println("❌ خيار غير صحيح");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ يرجى إدخال رقم صحيح.");
            } catch (Exception e) {
                System.out.println("❌ حدث خطأ: " + e.getMessage());
            }
        }
    }

    private static void addBook(Scanner scanner, ArrayList<Book> books) {
        System.out.print("العنوان: ");
        String title = scanner.nextLine();
        System.out.print("المؤلف: ");
        String author = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        books.add(new Book(title, author, isbn));
        System.out.println("✅ تم إضافة الكتاب!");
    }

    private static void addBorrower(Scanner scanner, ArrayList<Borrower> borrowers) {
        System.out.print("اسم المستعير: ");
        String name = scanner.nextLine();
        System.out.print("الرقم الجامعي: ");
        String id = scanner.nextLine();

        borrowers.add(new Borrower(name, id));
        System.out.println("✅ تم إضافة المستعير!");
    }

    private static void borrowBook(Scanner scanner, ArrayList<Book> books, ArrayList<Borrower> borrowers, ArrayList<BorrowingProcess> processes) {
        System.out.print("أدخل ISBN الكتاب: ");
        String isbnToBorrow = scanner.nextLine();
        Book bookToBorrow = findBookByISBN(books, isbnToBorrow);

        if (bookToBorrow == null || bookToBorrow.isBorrowed()) {
            System.out.println("❌ الكتاب غير موجود أو معار مسبقًا");
            return;
        }

        System.out.print("أدخل رقم الطالب الجامعي: ");
        String borrowerId = scanner.nextLine();
        Borrower borrower = findBorrowerById(borrowers, borrowerId);

        if (borrower == null) {
            System.out.println("❌ المستعير غير موجود");
            return;
        }

        bookToBorrow.borrow();
        borrower.borrowBook(bookToBorrow);
        processes.add(new BorrowingProcess(bookToBorrow, borrower));
        System.out.println("📦 تم إعارة الكتاب بنجاح");
    }

    private static void returnBook(Scanner scanner, ArrayList<BorrowingProcess> processes) {
        System.out.print("أدخل ISBN الكتاب المسترجع: ");
        String isbnReturn = scanner.nextLine();
        BorrowingProcess processToReturn = findProcessByISBN(processes, isbnReturn);

        if (processToReturn != null) {
            processToReturn.returnBook();
            System.out.println("✅ تم الاسترجاع!");
        } else {
            System.out.println("❌ لا توجد عملية إعارة لهذا الكتاب");
        }
    }

    private static void displayBorrowedBooks(Scanner scanner, ArrayList<Borrower> borrowers) {
        System.out.print("أدخل رقم الطالب الجامعي: ");
        String idSearch = scanner.nextLine();
        Borrower b = findBorrowerById(borrowers, idSearch);
        if (b != null) {
            ArrayList<Book> userBooks = b.getBorrowedBooks();
            if (userBooks.isEmpty()) {
                System.out.println("📭 لا توجد كتب مستعارة");
            } else {
                userBooks.forEach(System.out::println);
            }
        } else {
            System.out.println("❌ المستعير غير موجود");
        }
    }

    private static void search(Scanner scanner, ArrayList<Book> books, ArrayList<Borrower> borrowers) {
        System.out.print("بحث عن (1. كتاب / 2. مستعير): ");
        int searchType = Integer.parseInt(scanner.nextLine().trim());

        switch (searchType) {
            case 1 -> {
                System.out.print("أدخل عنوان الكتاب: ");
                String searchTitle = scanner.nextLine();
                books.stream()
                        .filter(book -> book.getTitle().equalsIgnoreCase(searchTitle))
                        .forEach(System.out::println);
            }
            case 2 -> {
                System.out.print("أدخل اسم المستعير: ");
                String searchName = scanner.nextLine();
                borrowers.stream()
                        .filter(borrower -> borrower.getName().equalsIgnoreCase(searchName))
                        .forEach(System.out::println);
            }
            default -> System.out.println("❌ خيار بحث غير صحيح");
        }
    }

    static Book findBookByISBN(ArrayList<Book> books, String isbn) {
        return books.stream()
            .filter(book -> book.getISBN().equalsIgnoreCase(isbn))
            .findFirst()
            .orElse(null);
    }

    static Borrower findBorrowerById(ArrayList<Borrower> list, String id) {
        return list.stream()
            .filter(borrower -> borrower.getUniversityId().equalsIgnoreCase(id))
            .findFirst()
            .orElse(null);
    }

    static BorrowingProcess findProcessByISBN(ArrayList<BorrowingProcess> list, String isbn) {
        return list.stream()
            .filter(process -> process.getBook().getISBN().equalsIgnoreCase(isbn))
            .findFirst()
            .orElse(null);
    }
}