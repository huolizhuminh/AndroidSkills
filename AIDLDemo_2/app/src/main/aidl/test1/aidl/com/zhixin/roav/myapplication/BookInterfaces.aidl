// ShowInterfaces.aidl
package test1.aidl.com.zhixin.roav.myapplication;

// Declare any non-default types here with import statements
import test1.aidl.com.zhixin.roav.myapplication.Book;
import test1.aidl.com.zhixin.roav.myapplication.OnBookChangeListener;
interface BookInterfaces {
   void addBookList(in Book book);
   List<Book> getBookList();
   void addBookChangeListener( OnBookChangeListener listener);
   void deleteBookChangeListener( OnBookChangeListener listener);
}
