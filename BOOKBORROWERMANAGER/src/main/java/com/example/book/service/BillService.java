package com.example.book.service;
import java.util.stream.Collectors;
import com.example.book.model.Bill;
import com.example.book.model.BillDetails;
import com.example.book.model.Book;
import com.example.book.model.User;
import com.example.book.respository.BillDetailsRepository;
import com.example.book.respository.BillRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BillService {

     Bill getBillById(Long billId)throws ChangeSetPersister.NotFoundException;

    List<Bill> getBillsByUser(User user) ;

     List<Book> getBooksByBill(Bill bill) ;

     Bill getActiveBillByUser(User user);
    List<BillDetails> getBillDetailsByBill(Bill bill);

     Bill getActiveBillByUserAndBook(User user, Book book);

     void saveBill(Bill bill);


     void saveBillDetails(BillDetails billDetails);



     void deleteBillDetailsByBillAndBook(Bill bill, Book book);
     List<Bill> getActiveBillsByUser(User user);
    public void saveAllBills(List<Bill> bills) ;
    public List<Bill> getAllBills();


/*    public void returnBook(Long userId, Long bookId) {

           User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

           Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book not found"));

           // Kiểm tra hóa đơn mượn sách đang hoạt động của người dùng
           Bill activeBill = getActiveBillByUser(user);
           if (activeBill == null) {
               throw new NotFoundException("No active borrow bill found for the user");
           }
           Bill bill = billService.getActiveBillByUser(user);
           List<BillDetails> billDetailsList;

           if (bill != null) {
               // Lấy danh sách chi tiết sách từ hóa đơn
               billDetailsList = billService.getBillDetailsByBill(bill);

               // Kiểm tra chi tiết sách trong hóa đơn
               BillDetails billDetailsToRemove = null;
               for (BillDetails billDetails : billDetailsList) {
                   if (billDetails.getBook().getBookId().equals(book.getBookId())) {
                       billDetailsToRemove = billDetails;
                       break;
                   }
               }
           } else {
               throw new NotFoundException("bill not found");
           }

           if (billDetailsToRemove == null) {
               throw new NotFoundException("Book is not borrowed by the user");
           }

           // Xóa chi tiết sách khỏi hóa đơn
           removeBillDetails(billDetailsToRemove);

           // Tăng giá trị tồn kho sách
           int newInventory = book.getInventory() + 1;
           book.setInventory(newInventory);
           bookRepository.save(book);

           // Cập nhật tổng giá trị hóa đơn
           BigDecimal bookPrice = book.getPrice();
           int totalBorrowedBooks = billDetailsList.size() - 1;
           BigDecimal sumPrice = bookPrice.multiply(new BigDecimal(0.2)).multiply(new BigDecimal(totalBorrowedBooks));
           activeBill.setSumPrice(sumPrice);
           saveBill(activeBill);


    }*/



}
