package com.cg.mypaymentapp.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class TestClass {

	static WalletService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception 
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Shravya", "9394795219",new Wallet(new BigDecimal(9000)));
		 Customer cust2=new Customer("Aradya", "9963242422",new Wallet(new BigDecimal(6000)));
		 Customer cust3=new Customer("Manasa", "9922950519",new Wallet(new BigDecimal(7000)));
				
		 data.put("9394795219", cust1);
		 data.put("9963242422", cust2);	
		 data.put("9922950519", cust3);	
			service= new WalletServiceImpl(data);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=InvalidInputException.class)
	public void testCreateAccount1() 
	{
		service.createAccount(null, "9942221102", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount2() 
	{
		service.createAccount("", "9942221102", new BigDecimal(1500));
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount3() 
	{
		service.createAccount("jhansy", "", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount4() 
	{
		service.createAccount("", "", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount5() 
	{
		service.createAccount("Shravya", "9394795219", new BigDecimal(-100));
	}
	
	
	@Test
	public void testCreateAccount6() 
	{
		Customer actual=service.createAccount("Mrudhula", "8754922472", new BigDecimal(0));
		Customer expected=new Customer("Mrudhula", "8754922472", new Wallet(new BigDecimal(0)));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCreateAccount7() 
	{
		Customer actual=service.createAccount("Mrudhula", "8754922472", new BigDecimal(5000.75));
		Customer expected=new Customer("Mrudhula", "8754922472", new Wallet(new BigDecimal(5000.75)));
		
		assertEquals(expected, actual);
	}


	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance8() 
	{
		service.showBalance(null);		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance9() 
	{
		service.showBalance("");		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance10() 
	{
		service.showBalance("4567");		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer11() 
	{
		service.fundTransfer("9948484810", "9922950519", new BigDecimal(5000));		
	}
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer12() 
	{
		service.fundTransfer("9922950519", "9922950519", new BigDecimal(5000));		
	}

	
	@Test(expected=InsufficientBalanceException.class)
	public void testFundTransfer13() 
	{
		service.fundTransfer("9394795219", "9922950519", new BigDecimal(12000));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer14() 
	{
		service.fundTransfer("9394795219", "9922950519", new BigDecimal(0));		
	}
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer15() 
	{
		service.fundTransfer("9394795219", "", new BigDecimal(0));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer16() 
	{
		service.fundTransfer("", "9922950519", new BigDecimal(500));		
	}
	
	
	@Test
	public void testFundTransfer17() 
	{
		Customer customer=service.fundTransfer("9394795219", "9922950519", new BigDecimal(500));
		BigDecimal expected=customer.getWallet().getBalance();
		BigDecimal actual=new BigDecimal(8500);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFundTransfer18() 
	{
		Customer customer=service.fundTransfer("9394795219", "9922950519", new BigDecimal(550.50));
		BigDecimal expected=customer.getWallet().getBalance();
		BigDecimal actual=new BigDecimal(8449.50);
		
		assertEquals(expected, actual);
	}
	
	
	@Test(expected=InsufficientBalanceException.class)
	public void testFundTransfer19() 
	{
		Customer customer=service.fundTransfer("9394795219", "9922950519", new BigDecimal(15000));	
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer20() 
	{
		service.fundTransfer("", "9922950519", new BigDecimal(-100));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer21() 
	{
		service.fundTransfer("", "", new BigDecimal(500));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer22() 
	{
		service.fundTransfer(null, null, new BigDecimal(0));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer23() 
	{
		service.fundTransfer("9922950519", "9963242422", new BigDecimal(0));		
	}

	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount24() 
	{
		service.depositAmount(null, new BigDecimal(500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount25() 
	{
		service.depositAmount("", new BigDecimal(500));
	}
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount26() 
	{
		service.depositAmount("9942221102", new BigDecimal(0));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount27() 
	{
		service.depositAmount("9922950519", new BigDecimal(-1000));
	}
	

	
	@Test(expected=InsufficientBalanceException.class)
	public void testWithdrawAmount28() 
	{
		service.withdrawAmount("9394795219", new BigDecimal(15000));	
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testWithdrawAmount29() 
	{
		service.withdrawAmount("9394795219", new BigDecimal(0));
	}
	
	
	
}
