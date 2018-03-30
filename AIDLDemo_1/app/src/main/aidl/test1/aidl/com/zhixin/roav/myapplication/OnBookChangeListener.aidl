// ShowInterfaces.aidl
package test1.aidl.com.zhixin.roav.myapplication;

// Declare any non-default types here with import statements
import test1.aidl.com.zhixin.roav.myapplication.Book;
interface OnBookChangeListener {
  void onBookAdd(in Book book);
 void onBookDelete(in Book book);
}
