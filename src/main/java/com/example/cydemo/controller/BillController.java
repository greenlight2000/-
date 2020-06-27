package com.example.cydemo.controller;

import com.example.cydemo.dao.BillDao;
import com.example.cydemo.dao.CustomerDao;
import com.example.cydemo.dto.BillDto;
import com.example.cydemo.dto.CustomerDto;
import com.example.cydemo.entity.Bill;
import com.example.cydemo.entity.BillOrder;
import com.example.cydemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/Bill")
public class BillController {
    @Autowired
    BillDao billDao;

    @Autowired
    CustomerDao customerDao;

    /**
     * 按时间顺序查看该Customer的已支付/未支付Bill (包含BillOrder)
     * @param customerId (long)
     * @param direction (int) : +n正序 -n倒序数
     * @param paid (boolean)
     * @return List
     */
    @GetMapping("/ShowAll")
    public List<BillDto> ShowAll(@RequestParam("customerId") long customerId,@RequestParam("direction") int direction,@RequestParam("paid") boolean paid)
    {
        //获取entity Set对象
        Set<Bill> billSet = customerDao.getOne(customerId).getBills();

        Set<BillDto> unpaidBills = new HashSet<>();
        Set<BillDto> paidBills = new HashSet<>();
        for(Bill bill : billSet)
        {
            if(bill.getPayStatus().equals("未支付"))
            {
                unpaidBills.add(bill.toDto());
            }else if (bill.getPayStatus().equals("已支付"))
            {
                paidBills.add(bill.toDto());
            }
        }
        List<BillDto> billList = new ArrayList<BillDto>(paid? paidBills : unpaidBills);
        Collections.sort(billList, new Comparator<BillDto>() {
            @Override
            public int compare(BillDto o1, BillDto o2) {
                return direction * o1.getPayTime().compareTo(o2.getPayTime());
            }
        });
        return billList;
    }

    /**
     * 向数据库提交新订单
     * @body BillDto
     * @param billDto
     */
    @PostMapping(value = "/submitBill")
    public long submitBill(@RequestBody BillDto billDto)
    {
        Customer customer = customerDao.getOne(billDto.getCustomerId());
        Bill bill = billDto.toEntity();

        Set<Bill> bills = customer.getBills();
        bills.add(bill);
        customer.setBills(bills);

        customerDao.save(customer);
        //billDao.save(bill); 通过customer级联上传或在customerEntity放弃级联操作
        return bill.getBillId();
    }
    @GetMapping(value = "/updatePayStatus")
    public void updatePayStatus(@RequestParam("billId")long billId)
    {
        Bill bill = billDao.getOne(billId);
        bill.setPayStatus("paid");
        bill.setPayTime(new Timestamp(System.currentTimeMillis()));
        billDao.save(bill);
    }

}
