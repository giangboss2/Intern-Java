package com.example.book.service.impl;

import com.example.book.service.BillService;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import com.example.book.model.Bill;
import com.example.book.model.BillDetails;
import com.example.book.model.Book;
import com.example.book.model.User;
import com.example.book.respository.BillDetailsRepository;
import com.example.book.respository.BillRepository;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;


@Service
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final BillDetailsRepository billDetailsRepository;

    public BillServiceImpl(BillRepository billRepository, BillDetailsRepository billDetailsRepository) {
        this.billRepository = billRepository;
        this.billDetailsRepository = billDetailsRepository;
    }


    @Override

    public Bill getBillById(Long billId) throws ChangeSetPersister.NotFoundException {
        return billRepository.findById(billId).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }
    @Override

    public List<Bill> getBillsByUser(User user) {
        return billRepository.findByUser(user);
    }
    @Override

    public List<Book> getBooksByBill(Bill bill) {
        List<BillDetails> billDetailsList = billDetailsRepository.findByBill(bill);
        return billDetailsList.stream().map(BillDetails::getBook).collect(Collectors.toList());
    }

    @Override

    public Bill getActiveBillByUser(User user) {
        return billRepository.findActiveBillByUser(user);
    }
    @Override
    public List<BillDetails> getBillDetailsByBill(Bill bill) {
        // Lấy danh sách BillDetails dựa trên Bill
        return billDetailsRepository.findByBill(bill);
    }
    @Override
    public Bill getActiveBillByUserAndBook(User user, Book book) {
        return billRepository.findActiveBillByUserAndBook(user, book);
    }
    @Override

    public void saveBill(Bill bill) {
        billRepository.save(bill);
    }
    @Override

    public void saveBillDetails(BillDetails billDetails) {
        billDetailsRepository.save(billDetails);
    }
    @Override


    public void deleteBillDetailsByBillAndBook(Bill bill, Book book) {
        billDetailsRepository.deleteByBillAndBook(bill, book);
    }
    @Override
    public List<Bill> getActiveBillsByUser(User user) {
        // Thực hiện truy vấn hoặc xử lý logic để lấy danh sách các hóa đơn (bills) đang hoạt động của user
        // Ví dụ:
        return billRepository.findActiveBillsByUser(user);
    }
    @Override
    public void saveAllBills(List<Bill> bills) {
        billRepository.saveAll(bills);
    }
    @Override
    public List<Bill> getAllBills(){
        return billRepository.findAll();
    }

}

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




