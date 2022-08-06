import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ChainblockImplTest {

    private Chainblock database;
    private List<Transaction> transactions;
    private Transaction transaction;
    private Transaction transaction1;
    private Transaction transaction2;
    private Transaction transaction3;
    private Transaction transaction4;
    private Transaction transaction5;


    @Before
    public void setup() {
        this.database = new ChainblockImpl();
        this.transactions = new ArrayList<>();
        transaction = new TransactionImpl(0, TransactionStatus.SUCCESSFUL, "Pesho", "Sasho", 11.20);
        transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Toshko", 10);
        transaction2 = new TransactionImpl(2, TransactionStatus.UNAUTHORIZED, "Sasho", "Pesho", 11.0);
        transaction3 = new TransactionImpl(3, TransactionStatus.FAILED, "Toshko", "Sasho", 12.20);
        transaction4 = new TransactionImpl(4, TransactionStatus.ABORTED, "Sasho", "Pesho", 10.50);
        transaction5 = new TransactionImpl(5, TransactionStatus.SUCCESSFUL, "Marti", "Sasho", 9.20);

        transactions.add(transaction);
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        transactions.add(transaction5);
        database.add(transaction);
    }

    private void fillDatabaseWithTransactions() {
        database.add(transaction4);
        database.add(transaction3);
        database.add(transaction2);
        database.add(transaction1);
        database.add(transaction5);
    }

    @Test
    public void test_AddShouldAddTransaction() {
        Assert.assertEquals(1, database.getCount());
        database.add(transaction1);
        Assert.assertEquals(2, database.getCount());
    }

    @Test
    public void test_AddShouldNotAddDuplicateTransaction() {
        Assert.assertEquals(1, database.getCount());
    }

    @Test
    public void test_ContainsReturnTrue() {
        boolean chainBlockContainsTransaction = database.contains(transaction);
        Assert.assertTrue(chainBlockContainsTransaction);
    }

    @Test
    public void test_ContainsReturnFalse() {
        boolean chainBlockContainsTransaction = database.contains(transaction1);
        Assert.assertFalse(chainBlockContainsTransaction);
    }

    @Test
    public void test_ContainsWithIDReturnTrue() {
        boolean chainBlockContainsTransaction = database.contains(transaction.getId());
        Assert.assertTrue(chainBlockContainsTransaction);
    }

    @Test
    public void test_ContainsWithIDReturnFalse() {
        boolean chainBlockContainsTransaction = database.contains(transaction1.getId());
        Assert.assertFalse(chainBlockContainsTransaction);
    }

    @Test
    public void test_ChangeTransactionStatusShouldChangeStatus() {
        database.changeTransactionStatus(0, TransactionStatus.UNAUTHORIZED);
        Assert.assertEquals(TransactionStatus.UNAUTHORIZED, transaction.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ChangeTransactionStatusThrowMissingTransaction() {
        database.changeTransactionStatus(transaction1.getId(), TransactionStatus.UNAUTHORIZED);
    }

    @Test
    public void test_GetByIdWhereIdExists() {
        Transaction actual = database.getById(0);
        Assert.assertEquals(0, actual.getId());
        Assert.assertEquals(transaction.getStatus(), actual.getStatus());
        Assert.assertEquals(transaction.getFrom(), actual.getFrom());
        Assert.assertEquals(transaction.getTo(), actual.getTo());
        Assert.assertEquals(transaction.getAmount(), actual.getAmount(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetByIdWhereIdDoesNotExist() {
        database.getById(1);
    }

    @Test
    public void test_RemoveTransactionByIdSuccessWhenIdExists() {
        database.add(transaction1);
        database.removeTransactionById(transaction1.getId());
        Assert.assertFalse(database.contains(transaction1.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_RemoveTransactionByIdThrowWhenIdDoesNotExist() {
        database.removeTransactionById(transaction1.getId());
    }

    @Test
    public void test_GetByTransactionStatus_Success() {
        List<Transaction> expectedTransactions = transactions.stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .collect(Collectors.toList());

        fillDatabaseWithTransactions();

        Iterable<Transaction> result = database.getByTransactionStatus(TransactionStatus.SUCCESSFUL);
        List<Transaction> returnedTransaction = new ArrayList<>();
        result.forEach(returnedTransaction::add);

        Assert.assertEquals(expectedTransactions.size(), returnedTransaction.size());
        Assert.assertEquals(expectedTransactions, returnedTransaction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetByTransactionStatusNoMatchesThrow() {
        database.getByTransactionStatus(TransactionStatus.FAILED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetAllSendersWithTransactionStatusThrow() {
        database.getAllSendersWithTransactionStatus(TransactionStatus.FAILED);
    }


    @Test
    public void test_GetAllSendersWithTransactionStatusSuccess() {
        fillDatabaseWithTransactions();
        Iterable<String> actualSenders = database.getAllSendersWithTransactionStatus(TransactionStatus.SUCCESSFUL);
        List<String> actualSendersList = new ArrayList<>();
        actualSenders.forEach(actualSendersList::add);
        Assert.assertEquals(3, actualSendersList.size());
        Assert.assertEquals("Pesho", actualSendersList.get(0));
        Assert.assertEquals("Pesho", actualSendersList.get(1));
        Assert.assertEquals("Marti", actualSendersList.get(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetAllReceiversWithTransactionStatusThrow() {
        database.getAllReceiversWithTransactionStatus(TransactionStatus.FAILED);
    }


    @Test
    public void test_GetAllReceiversWithTransactionStatusSuccess() {
        fillDatabaseWithTransactions();
        Iterable<String> actualReceivers = database.getAllReceiversWithTransactionStatus(TransactionStatus.SUCCESSFUL);
        List<String> actualReceiversList = new ArrayList<>();
        actualReceivers.forEach(actualReceiversList::add);
        Assert.assertEquals(3, actualReceiversList.size());
        Assert.assertEquals("Sasho", actualReceiversList.get(0));
        Assert.assertEquals("Toshko", actualReceiversList.get(1));
        Assert.assertEquals("Sasho", actualReceiversList.get(2));
    }

    @Test
    public void test_GetAllOrderedByAmountDescendingThenByIdSuccess() {
        List<Transaction> expected = transactions.stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());

        fillDatabaseWithTransactions();
        Iterable<Transaction> actualResult = database.getAllOrderedByAmountDescendingThenById();

        Assert.assertEquals(expected, actualResult);
    }

    @Test
    public void test_GetBySenderOrderedByAmountDescendingSuccess() {
        List<Transaction> expectedList = transactions.stream()
                .filter(tr -> tr.getFrom().equals("Sasho"))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        fillDatabaseWithTransactions();
        Iterable<Transaction> actualResult = database.getBySenderOrderedByAmountDescending("Sasho");

        Assert.assertEquals(expectedList, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetBySenderOrderedByAmountDescendingThrow() {
        fillDatabaseWithTransactions();
       database.getBySenderOrderedByAmountDescending("Koko");
    }


    @Test
    public void test_GetByReceiverOrderedByAmountThenByIdSuccess() {
        List<Transaction> expectedList = transactions.stream()
                .filter(tr -> tr.getTo().equals("Pesho"))
                .sorted(Comparator.comparing(Transaction::getAmount).thenComparing(Transaction::getId))
                .collect(Collectors.toList());

        fillDatabaseWithTransactions();
        Iterable<Transaction> actualResult = database.getByReceiverOrderedByAmountThenById("Pesho");

        Assert.assertEquals(expectedList, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetByReceiverOrderedByAmountThenByIdThrow() {
        fillDatabaseWithTransactions();
        database.getByReceiverOrderedByAmountThenById("Koko");
    }

    @Test
    public void test_GetByTransactionStatusAndMaximumAmount() {
        List<Transaction> expectedList = transactions.stream()
                .filter(tr -> tr.getStatus().equals(TransactionStatus.SUCCESSFUL) && tr.getAmount() <= 10.0)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        fillDatabaseWithTransactions();
        Iterable<Transaction> actualResult = database.getByTransactionStatusAndMaximumAmount(TransactionStatus.SUCCESSFUL, 10.0);

        Assert.assertEquals(expectedList, actualResult);
    }

    @Test
    public void test_GetBySenderAndMinimumAmountDescendingSuccess() {
        List<Transaction> expectedList = transactions.stream()
                .filter(tr -> tr.getFrom().equals("Pesho") && tr.getAmount() >= 5.0)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        fillDatabaseWithTransactions();
        Iterable<Transaction> actualResult = database.getBySenderAndMinimumAmountDescending("Pesho", 5.0);

        Assert.assertEquals(expectedList, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetBySenderAndMinimumAmountDescendingThrow() {
        database.getBySenderAndMinimumAmountDescending("Milen", 10.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetByReceiverAndAmountRangeThrow() {
        database.getByReceiverAndAmountRange("Milen", 10.0, 15.0);
    }

    @Test
    public void test_GetByReceiverAndAmountRangeSuccess() {
        List<Transaction> expectedList = transactions.stream()
                .filter(tr -> tr.getTo().equals("Sasho") && tr.getAmount() >= 9.20 && tr.getAmount() < 12.20)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());

        fillDatabaseWithTransactions();
        Iterable<Transaction> actualResult = database.getByReceiverAndAmountRange("Sasho", 9.20, 12.20);

        Assert.assertEquals(expectedList, actualResult);
    }

    @Test
    public void test_GetAllInAmountRangeSuccess() {
        List<Transaction> expectedList = transactions.stream()
                .filter(tr -> tr.getAmount() >= 9.20 && tr.getAmount() <= 12.20)
                .collect(Collectors.toList());

        fillDatabaseWithTransactions();
        Iterable<Transaction> actualResult = database.getAllInAmountRange(9.20, 12.20);

        Assert.assertEquals(expectedList, actualResult);
    }

    @Test
    public void test_GetAllInAmountRangeEmpty() {
        List<Transaction> expectedList = transactions.stream()
                .filter(tr -> tr.getAmount() >= 150 && tr.getAmount() <= 110000)
                .collect(Collectors.toList());

        fillDatabaseWithTransactions();
        Iterable<Transaction> actualResult = database.getAllInAmountRange(150, 110000);

        Assert.assertEquals(expectedList, actualResult);
    }
}
